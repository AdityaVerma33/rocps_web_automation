package com.subex.automation.testcases.regressiontesting.datafederation;

import java.util.ArrayList;
import java.util.HashMap;

import org.testng.annotations.Test;

import com.subex.automation.helpers.application.screens.FederatedDataSourceHelper;
import com.subex.automation.helpers.component.ButtonHelper;
import com.subex.automation.helpers.component.GenericHelper;
import com.subex.automation.helpers.component.GridHelper;
import com.subex.automation.helpers.component.SearchGridHelper;
import com.subex.automation.helpers.file.ExcelReader;
import com.subex.automation.helpers.report.Log4jHelper;
import com.subex.automation.helpers.selenium.ROCAcceptanceTest;
import com.subex.automation.helpers.util.FailureHelper;

public class testFDSSearch extends ROCAcceptanceTest {
	
	static String path = null;
	final String fileName = "DataFederation_TestData.xlsx";
	final String sheetName = "FDSSearch";
	
	public testFDSSearch() throws Exception {
		try {
			path = automationPath + "\\src\\main\\resources\\Regression\\";
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	@Test(priority=1, description="Search filter's starts with search", groups = { "search" })
	public void testStartsWithSearch() throws Exception
	{
		try {
			// 1. Name and Prefix filters should do starts with search by default.
			// 2. Data Source Type filter should do exact search
			Log4jHelper.logInfo("Running Data Federation Regression");
			FederatedDataSourceHelper fedDataSource = new FederatedDataSourceHelper();
			fedDataSource.createFederatedDataSource(path, fileName, sheetName, "FederatedDataSource", 1);
			
			ExcelReader excelReader = new ExcelReader();
			HashMap<String, ArrayList<String>> excelData = excelReader.readDataByColumn( path, fileName, sheetName, "FederatedDataSource", 1 );
			ArrayList<String> name = excelData.get("Name");
			ArrayList<String> prefix = excelData.get("Prefix");
			
			SearchGridHelper.gridFilterSearchWithTextBox("FedDataSource_Name", "regression", "Name");
			for(int i = 0; i < name.size(); i++) {
				String eName = name.get(i);
				if (eName.startsWith("Regression") || eName.startsWith("regression"))
					assertTrue(GridHelper.isValuePresent("SearchGrid", eName, "Name"));
				else
					assertFalse(GridHelper.isValuePresent("SearchGrid", eName, "Name"));
			}
			
			ButtonHelper.click("ClearButton");
			GenericHelper.waitForLoadmask(searchScreenWaitSec);
			int rows = GridHelper.getRowCount("SearchGrid");
			assertTrue(rows == 0);
			
			SearchGridHelper.gridFilterSearchWithTextBox("FedDataSource_Prefix", "rf", "Prefix");
			for(int i = 0; i < prefix.size(); i++) {
				assertTrue(GridHelper.isValuePresent("SearchGrid", prefix.get(i), "Prefix"));
			}
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	@Test(priority=2, description="Search filter's like search", dependsOnMethods = { "testStartsWithSearch" }, groups = { "search" })
	public void testLikeSearch() throws Exception
	{
		try {
			// Like search for Name and Prefix filters should work
			ExcelReader excelReader = new ExcelReader();
			HashMap<String, ArrayList<String>> excelData = excelReader.readDataByColumn( path, fileName, sheetName, "FederatedDataSource", 1 );
			ArrayList<String> name = excelData.get("Name");
			ArrayList<String> prefix = excelData.get("Prefix");
			
			SearchGridHelper.gridFilterSearchWithTextBox("FedDataSource_Name", "%FDS%", "Name");
			for(int i = 0; i < name.size(); i++) {
				String eName = name.get(i);
				if (eName.contains("FDS") || eName.contains("fds"))
					assertTrue(GridHelper.isValuePresent("SearchGrid", eName, "Name"));
				else
					assertFalse(GridHelper.isValuePresent("SearchGrid", eName, "Name"));
			}
			
			ButtonHelper.click("ClearButton");
			GenericHelper.waitForLoadmask(searchScreenWaitSec);
			
			SearchGridHelper.gridFilterSearchWithTextBox("FedDataSource_Prefix", "%f%", "Prefix");
			for(int i = 0; i < prefix.size(); i++) {
				String eName = prefix.get(i);
				if (eName.contains("f"))
					assertTrue(GridHelper.isValuePresent("SearchGrid", eName, "Prefix"));
				else
					assertFalse(GridHelper.isValuePresent("SearchGrid", eName, "Prefix"));
			}
			
			ButtonHelper.click("ClearButton");
			GenericHelper.waitForLoadmask(searchScreenWaitSec);
			
			SearchGridHelper.gridFilterSearchWithTextBox("FedDataSource_Name", "%Regression%", "Name");
			for(int i = 0; i < name.size(); i++) {
				String eName = name.get(i);
				if (eName.contains("Regression") || eName.contains("regression"))
					assertTrue(GridHelper.isValuePresent("SearchGrid", eName, "Name"));
				else
					assertFalse(GridHelper.isValuePresent("SearchGrid", eName, "Name"));
			}
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
}