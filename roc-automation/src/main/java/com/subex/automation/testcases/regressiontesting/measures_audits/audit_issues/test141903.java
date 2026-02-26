package com.subex.automation.testcases.regressiontesting.measures_audits.audit_issues;

import java.util.ArrayList;
import java.util.HashMap;

import org.testng.annotations.Test;

import com.subex.automation.helpers.application.NavigationHelper;
import com.subex.automation.helpers.application.screens.DataMatchMeasureHelper;
import com.subex.automation.helpers.component.GenericHelper;
import com.subex.automation.helpers.component.GridHelper;
import com.subex.automation.helpers.component.SearchGridHelper;
import com.subex.automation.helpers.component.TabHelper;
import com.subex.automation.helpers.file.ExcelReader;
import com.subex.automation.helpers.scripts.TestDataHelper;
import com.subex.automation.helpers.util.FailureHelper;

public class test141903 extends testAuditIssues {
	
	final String sheetName = "test141903";
	
	public test141903() throws Exception {
		super();
	}
	
	@Test(priority=1, description="Test Case 1 for Bug 141903")
	public void testCase1() throws Exception {
		try {
			// Validate that user is able to create DM measure.
			DataMatchMeasureHelper dataMatchMeasure = new DataMatchMeasureHelper();
			TestDataHelper testData = new TestDataHelper();
			ExcelReader excelReader = new ExcelReader();
			HashMap<String, ArrayList<String>> excelData = excelReader.readDataByColumn( path, fileName, sheetName, "DataMatchMeasure", 1 );
			
			String measureName = excelData.get("Name").get(0);
			String[] tableInstance = testData.getStringValue(excelData.get("Table Instance").get(0), firstLevelDelimiter);
			String[][] tableColumns = testData.getStringValue(excelData.get("Table Columns").get(0), firstLevelDelimiter, secondLevelDelimiter);
			String[][] outputColumnName = testData.getStringValue(excelData.get("Output Column Name").get(0), firstLevelDelimiter, secondLevelDelimiter);
			String[][] outputColumnDisplayName = testData.getStringValue(excelData.get("Output Column Display Name").get(0), firstLevelDelimiter, secondLevelDelimiter);
			
			NavigationHelper.navigateToScreen("Measures", "Measure Search");
			int row = SearchGridHelper.gridFilterSearchWithTextBox("Measure_Name", measureName, "Name");
			
			if (row == 0) {
				NavigationHelper.navigateToAction("Common Tasks", "New", "Data Match Measure");
				GenericHelper.waitForLoadmask(searchScreenWaitSec);
				
				dataMatchMeasure.addTableInstance(tableInstance, tableColumns, outputColumnName, outputColumnDisplayName);
				
				TabHelper.gotoTab("DMM_Workflow_Panel", "Match Steps");
				TabHelper.gotoTab("DMM_Workflow_Panel", "Match Columns");
			}
			
			dataMatchMeasure.createDataMatchMeasure(path, fileName, sheetName, "DataMatchMeasure", 1);
			row = GridHelper.getRowNumber("SearchGrid", measureName, "Name");
			NavigationHelper.navigateToEdit("SearchGrid", row, "DMM_Name");
			
			TabHelper.gotoTab("DMM_Workflow_Panel", "Match Steps");
			TabHelper.gotoTab("DMM_Workflow_Panel", "Match Columns");
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
}