package com.subex.automation.testcases.regressiontesting.datafederation;

import java.util.ArrayList;
import java.util.HashMap;

import org.testng.annotations.Test;

import com.subex.automation.helpers.application.NavigationHelper;
import com.subex.automation.helpers.application.screens.FederatedDataSourceHelper;
import com.subex.automation.helpers.application.screens.FederatedViewHelper;
import com.subex.automation.helpers.component.ButtonHelper;
import com.subex.automation.helpers.component.GenericHelper;
import com.subex.automation.helpers.component.GridHelper;
import com.subex.automation.helpers.component.LabelHelper;
import com.subex.automation.helpers.component.SearchGridHelper;
import com.subex.automation.helpers.component.TextBoxHelper;
import com.subex.automation.helpers.file.ExcelReader;
import com.subex.automation.helpers.scripts.ExecuteScript;
import com.subex.automation.helpers.selenium.ROCAcceptanceTest;
import com.subex.automation.helpers.util.FailureHelper;

public class testRemoteDataSource extends ROCAcceptanceTest {
	
	static String path = null;
	final String fileName = "DataFederation_TestData.xlsx";
	final String sheetName = "EditView";
	
	public testRemoteDataSource() throws Exception {
		try {
			path = automationPath + "\\src\\main\\resources\\Regression\\";
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	@Test(priority=1, description="Verify Open Remote Data Source action", groups = { "remoteDataSource" })
	public void testOpenRemoteDataSource() throws Exception
	{
		try {
			// 1. A new window should open with title 'View Federated Data Source' and should have all the Federated Data Source details.
			// 2. User should not be allowed to make any changes in this screen.
			FederatedDataSourceHelper fedDataSource = new FederatedDataSourceHelper();
			fedDataSource.createFederatedDataSource(path, fileName, sheetName, "FederatedDataSource", 1);
			
			String scriptFileName = automationPath + "\\src\\main\\resources\\System_Test_Flows\\FederatedDataSource_Script.sql";
			ExecuteScript.exeScript(scriptFileName, "FederatedDataSource");
			
			FederatedViewHelper fedView = new FederatedViewHelper();
			fedView.createFederatedView(path, fileName, sheetName, "FederatedView", 1);
			
			ExcelReader excelReader = new ExcelReader();
			HashMap<String, ArrayList<String>> excelData = excelReader.readDataByColumn( path, fileName, sheetName, "FederatedView", 1 );
			String name = excelData.get("Name").get(0);
			String federatedDataSource = excelData.get("Federated Data Source").get(0);
			String prefix = name.substring(0, 3);
			
			int row = SearchGridHelper.gridFilterSearchWithTextBox("FedView_Name_Filter", name, "Name");

			if (row > 0) {
				GridHelper.clickRow("SearchGrid", row, 1);
				assertTrue(NavigationHelper.isActionPresent("Federated View"));
				NavigationHelper.navigateToAction("Federated View");
				assertTrue(NavigationHelper.isActionPresent("Open Remote Data Source"));
				NavigationHelper.navigateToAction("Open Remote Data Source");
				GenericHelper.waitForElement("FedDataSource_Name", detailScreenWaitSec);
				
				assertTrue(LabelHelper.isTitlePresent("View Federated Data Source"));
				assertTrue(TextBoxHelper.isDisabled("FedDataSource_Name"));
				assertEquals(TextBoxHelper.getValue("FedDataSource_Name"), federatedDataSource);
				assertTrue(TextBoxHelper.isDisabled("FedDataSource_Prefix"));
				assertEquals(TextBoxHelper.getValue("FedDataSource_Prefix"), prefix);
				assertTrue(ButtonHelper.isDisabled("SaveButton"));
				assertTrue(ButtonHelper.isPresent("CloseButton"));
				ButtonHelper.click("CloseButton");
				GenericHelper.waitForLoadmask(searchScreenWaitSec);
			}
			else {
				FailureHelper.failTest("Federated View '" + name + "' is not found.");
			}
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
}