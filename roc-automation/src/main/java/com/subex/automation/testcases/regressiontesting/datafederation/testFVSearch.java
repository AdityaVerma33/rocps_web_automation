package com.subex.automation.testcases.regressiontesting.datafederation;

import java.util.ArrayList;
import java.util.HashMap;

import org.testng.annotations.Test;

import com.subex.automation.helpers.application.screens.FederatedDataSourceHelper;
import com.subex.automation.helpers.application.screens.FederatedViewHelper;
import com.subex.automation.helpers.component.ButtonHelper;
import com.subex.automation.helpers.component.GenericHelper;
import com.subex.automation.helpers.component.GridHelper;
import com.subex.automation.helpers.component.SearchGridHelper;
import com.subex.automation.helpers.file.ExcelReader;
import com.subex.automation.helpers.scripts.ExecuteScript;
import com.subex.automation.helpers.selenium.ROCAcceptanceTest;
import com.subex.automation.helpers.util.FailureHelper;

public class testFVSearch extends ROCAcceptanceTest {
	
	static String path = null;
	final String fileName = "DataFederation_TestData.xlsx";
	final String sheetName = "FVSearch";
	
	public testFVSearch() throws Exception {
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
			// 1. Name and Source Table filters should do starts with search by default.
			// 2. Remote Data Source filter should do exact search
			FederatedDataSourceHelper fedDataSource = new FederatedDataSourceHelper();
			fedDataSource.createFederatedDataSource(path, fileName, sheetName, "FederatedDataSource", 1);
			
			String scriptFileName = automationPath + "\\src\\main\\resources\\Regression\\FederatedDataSource_Script.sql";
			ExecuteScript.exeScript(scriptFileName, "FederatedDataSource");
			
			FederatedViewHelper fedView = new FederatedViewHelper();
			fedView.createFederatedView(path, fileName, sheetName, "FederatedView", 1);
			
			ExcelReader excelReader = new ExcelReader();
			HashMap<String, ArrayList<String>> excelData = excelReader.readDataByColumn( path, fileName, sheetName, "FederatedView", 1 );
			ArrayList<String> name = excelData.get("Name");
			ArrayList<String> sourceTable = excelData.get("Source Table");
			ArrayList<String> remoteDataSource = excelData.get("Federated Data Source");
			
			String searchString = "sff_r_roam";
			SearchGridHelper.gridFilterSearchWithTextBox("FedView_Name_Filter", searchString, "Name");
			for(int i = 0; i < name.size(); i++) {
				String eName = name.get(i);
				if (eName.startsWith(searchString))
					assertTrue(GridHelper.isValuePresent("SearchGrid", eName, "Name"));
				else
					assertFalse(GridHelper.isValuePresent("SearchGrid", eName, "Name"));
			}
			
			ButtonHelper.click("ClearButton");
			GenericHelper.waitForLoadmask(searchScreenWaitSec);
			int rows = GridHelper.getRowCount("SearchGrid");
			assertTrue(rows == 0);
			
			searchString = "r_";
			SearchGridHelper.gridFilterSearchWithTextBox("FedView_SourceTable_SearchFilter", searchString, "Source Table");
			for(int i = 0; i < sourceTable.size(); i++) {
				String source = sourceTable.get(i);
				if (source.startsWith(searchString) || source.startsWith("R_"))
					assertTrue(GridHelper.isValuePresent("SearchGrid", source, "Source Table"));
				else
					assertFalse(GridHelper.isValuePresent("SearchGrid", source, "Source Table"));
			}
			
			ButtonHelper.click("ClearButton");
			GenericHelper.waitForLoadmask(searchScreenWaitSec);
			rows = GridHelper.getRowCount("SearchGrid");
			assertTrue(rows == 0);
			
			searchString = remoteDataSource.get(0);
			SearchGridHelper.gridFilterSearchWithComboBox("FedView_DataSource", searchString, "Remote Data Source");
			for(int i = 0; i < remoteDataSource.size(); i++) {
				String dataSource = remoteDataSource.get(i);
				if (dataSource.startsWith(searchString))
					assertTrue(GridHelper.isValuePresent("SearchGrid", dataSource, "Remote Data Source"));
				else
					assertFalse(GridHelper.isValuePresent("SearchGrid", dataSource, "Remote Data Source"));
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
			// Like search for Name and Source Table filters should work
			ExcelReader excelReader = new ExcelReader();
			HashMap<String, ArrayList<String>> excelData = excelReader.readDataByColumn( path, fileName, sheetName, "FederatedView", 1 );
			ArrayList<String> name = excelData.get("Name");
			ArrayList<String> sourceTable = excelData.get("Source Table");
			
			String searchString = "%roam%";
			SearchGridHelper.gridFilterSearchWithTextBox("FedView_Name_Filter", searchString, "Name");
			for(int i = 0; i < name.size(); i++) {
				String eName = name.get(i);
				if (eName.contains("roam") || eName.contains("Roam"))
					assertTrue(GridHelper.isValuePresent("SearchGrid", eName, "Name"));
				else
					assertFalse(GridHelper.isValuePresent("SearchGrid", eName, "Name"));
			}
			
			ButtonHelper.click("ClearButton");
			GenericHelper.waitForLoadmask(searchScreenWaitSec);
			
			searchString = "%partner%";
			SearchGridHelper.gridFilterSearchWithTextBox("FedView_SourceTable_SearchFilter", searchString, "Source Table");
			for(int i = 0; i < sourceTable.size(); i++) {
				String source = sourceTable.get(i);
				if (source.contains("partner") || source.contains("Partner") || source.contains("PARTNER"))
					assertTrue(GridHelper.isValuePresent("SearchGrid", source, "Source Table"));
				else
					assertFalse(GridHelper.isValuePresent("SearchGrid", source, "Source Table"));
			}
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
}