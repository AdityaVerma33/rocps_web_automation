package com.subex.automation.testcases.regressiontesting.datafederation;

import java.util.ArrayList;
import java.util.HashMap;

import org.testng.annotations.Test;

import com.subex.automation.helpers.application.NavigationHelper;
import com.subex.automation.helpers.application.screens.FederatedDataSourceHelper;
import com.subex.automation.helpers.component.ButtonHelper;
import com.subex.automation.helpers.component.ComboBoxHelper;
import com.subex.automation.helpers.component.GenericHelper;
import com.subex.automation.helpers.component.GridHelper;
import com.subex.automation.helpers.component.LabelHelper;
import com.subex.automation.helpers.component.SearchGridHelper;
import com.subex.automation.helpers.component.TextBoxHelper;
import com.subex.automation.helpers.file.ExcelReader;
import com.subex.automation.helpers.selenium.ROCAcceptanceTest;
import com.subex.automation.helpers.util.FailureHelper;

public class testFDSView extends ROCAcceptanceTest {
	
	static String path = null;
	final String fileName = "DataFederation_TestData.xlsx";
	final String sheetName = "FDSView";
	
	public testFDSView() throws Exception {
		try {
			path = automationPath + "\\src\\main\\resources\\Regression\\";
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	@Test(priority=1, description="Federated Data Source - View action", groups = { "search" })
	public void testView() throws Exception
	{
		try {
			// Federated Data Source - Only View is possbile for already created source. Edit should not be available
			FederatedDataSourceHelper fedDataSource = new FederatedDataSourceHelper();
			fedDataSource.createFederatedDataSource(path, fileName, sheetName, "FederatedDataSource", 1);
			
			ExcelReader excelReader = new ExcelReader();
			HashMap<String, ArrayList<String>> excelData = excelReader.readDataByColumn( path, fileName, sheetName, "FederatedDataSource", 1 );
			String name = excelData.get("Name").get(0);
			
			SearchGridHelper.gridFilterSearchWithTextBox("FedDataSource_Name", name, "Name");
			int row = GridHelper.getRowNumber("SearchGrid", name, "Name");
			GridHelper.clickRow("SearchGrid", row, 1);
			NavigationHelper.navigateToAction("Common Tasks");
			assertFalse(NavigationHelper.isActionPresent("Edit"));
			assertTrue(NavigationHelper.isActionPresent("View"));
			
			NavigationHelper.navigateToAction("View");
			GenericHelper.waitForLoadmask(detailScreenWaitSec);
			GenericHelper.waitForElement("FedDataSource_Name", detailScreenWaitSec);
			assertTrue(LabelHelper.isTitlePresent("View Federated Data Source"));
			assertTrue(TextBoxHelper.isDisabled("FedDataSource_Name"));
			assertTrue(TextBoxHelper.isDisabled("FedDataSource_Prefix"));
			assertTrue(ComboBoxHelper.isDisabled("FedDataSource_DataSourceType"));
			assertTrue(ButtonHelper.isDisabled("SaveButton"));
			assertTrue(ButtonHelper.isPresent("CloseButton"));
			assertTrue(ButtonHelper.isEnabled("CloseButton"));
			ButtonHelper.click("CloseButton");
			GenericHelper.waitForLoadmask(detailScreenWaitSec);
			GenericHelper.waitForElement("Search_Panel", detailScreenWaitSec);
			assertTrue(LabelHelper.isTitlePresent("Federated Data Sour"));
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
}