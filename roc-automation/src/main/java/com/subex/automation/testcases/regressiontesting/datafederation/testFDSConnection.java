package com.subex.automation.testcases.regressiontesting.datafederation;

import java.util.ArrayList;
import java.util.HashMap;

import org.testng.annotations.Test;

import com.subex.automation.helpers.application.NavigationHelper;
import com.subex.automation.helpers.application.screens.FederatedDataSourceHelper;
import com.subex.automation.helpers.component.ButtonHelper;
import com.subex.automation.helpers.component.GenericHelper;
import com.subex.automation.helpers.component.GridHelper;
import com.subex.automation.helpers.component.LabelHelper;
import com.subex.automation.helpers.component.SearchGridHelper;
import com.subex.automation.helpers.file.ExcelReader;
import com.subex.automation.helpers.scripts.ExecuteScript;
import com.subex.automation.helpers.selenium.ROCAcceptanceTest;
import com.subex.automation.helpers.util.FailureHelper;

public class testFDSConnection extends ROCAcceptanceTest {
	
	static String path = null;
	final String fileName = "DataFederation_TestData.xlsx";
	final String sheetName = "FDSConnection";
	
	public testFDSConnection() throws Exception {
		try {
			path = automationPath + "\\src\\main\\resources\\Regression\\";
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	private void testConnection(String expectedMessage) throws Exception {
		try {
			NavigationHelper.navigateToAction("Federated Data Source Actions", "Test Connection");
			GenericHelper.waitForLoadmask(searchScreenWaitSec);
			GenericHelper.waitForElement("OKButton", searchScreenWaitSec);
			assertTrue(LabelHelper.isTextPresent(expectedMessage));
			ButtonHelper.click("OKButton");
			GenericHelper.waitForLoadmask(searchScreenWaitSec);
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	@Test(priority=1, description="Federated Data Source - Test Connection action", groups = { "search" })
	public void testConnectionAction() throws Exception
	{
		try {
			// Federated Data Source - Validate the presence of Test Connection action
			FederatedDataSourceHelper fedDataSource = new FederatedDataSourceHelper();
			fedDataSource.createFederatedDataSource(path, fileName, sheetName, "FederatedDataSource", 1);
			
			ExcelReader excelReader = new ExcelReader();
			HashMap<String, ArrayList<String>> excelData = excelReader.readDataByColumn( path, fileName, sheetName, "FederatedDataSource", 1 );
			String name = excelData.get("Name").get(0);
			
			SearchGridHelper.gridFilterSearchWithTextBox("FedDataSource_Name", name, "Name");
			int row = GridHelper.getRowNumber("SearchGrid", name, "Name");
			GridHelper.clickRow("SearchGrid", row, 1);
			assertTrue(NavigationHelper.isActionPresent("Federated Data Source Actions"));
			NavigationHelper.navigateToAction("Federated Data Source Actions");
			assertTrue(NavigationHelper.isActionPresent("Test Connection"));
			GridHelper.click("SearchGrid");
			
			SearchGridHelper.gridFilterSearchWithTextBox("FedDataSource_Name", name.substring(0, 14), "Name");
			GridHelper.clickMultipleCells("SearchGrid", 1, 2);
			assertFalse(NavigationHelper.isActionPresent("Federated Data Source Actions"));
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	@Test(priority=2, description="Federated Data Source - Test Connection action", groups = { "search" })
	public void testSuccessfulConnection() throws Exception
	{
		try {
			// Federated Data Source - Test Connection for successful connection
			FederatedDataSourceHelper fedDataSource = new FederatedDataSourceHelper();
			fedDataSource.createFederatedDataSource(path, fileName, sheetName, "FederatedDataSource", 2);
			
			ExcelReader excelReader = new ExcelReader();
			HashMap<String, ArrayList<String>> excelData = excelReader.readDataByColumn( path, fileName, sheetName, "FederatedDataSource", 2 );
			String name = excelData.get("Name").get(0);
			
			SearchGridHelper.gridFilterSearchWithTextBox("FedDataSource_Name", name, "Name");
			int row = GridHelper.getRowNumber("SearchGrid", name, "Name");
			GridHelper.clickRow("SearchGrid", row, 1);
			
			testConnection("Connection Successful.");
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	@Test(priority=3, description="Federated Data Source - Test Connection action", groups = { "search" })
	public void testFailureConnection() throws Exception
	{
		try {
			// Federated Data Source - Test Connection for failure connection
			FederatedDataSourceHelper fedDataSource = new FederatedDataSourceHelper();
			fedDataSource.createFederatedDataSource(path, fileName, sheetName, "FederatedDataSource", 2);
			String[][] dbDetail = fedDataSource.getFDSDBDetails();
			String[] dbDetails = {dbDetail[0][0], dbDetail[1][0], dbDetail[2][0], dbDetail[3][0], dbDetail[4][0], dbDetail[5][0]};
			
			ExcelReader excelReader = new ExcelReader();
			HashMap<String, ArrayList<String>> excelData = excelReader.readDataByColumn( path, fileName, sheetName, "FederatedDataSource", 2 );
			String name = excelData.get("Name").get(0);
			
			String updateQuery = "ALTER USER " + dbDetails[3] + " IDENTIFIED BY welcome";
			ExecuteScript.exeQuery(updateQuery, dbDetails);
			
			ButtonHelper.click("SearchButton");
			GenericHelper.waitForLoadmask(searchScreenWaitSec);
			int row = GridHelper.getRowNumber("SearchGrid", name, "Name");
			
			GridHelper.clickRow("SearchGrid", row, 1);
			testConnection("Failed To Connect.");
			
			updateQuery = "ALTER USER " + dbDetails[3] + " IDENTIFIED BY " + dbDetails[3];
			dbDetails[3] = "welcome";
			ExecuteScript.exeQuery(updateQuery, dbDetails);
			GridHelper.clickRow("SearchGrid", row, 1);
			testConnection("Connection Successful.");
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
}