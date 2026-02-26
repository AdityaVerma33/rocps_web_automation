package com.subex.automation.testcases.regressiontesting.measures_audits.audit_issues;

import java.util.ArrayList;
import java.util.HashMap;

import org.testng.annotations.Test;

import com.subex.automation.helpers.application.NavigationHelper;
import com.subex.automation.helpers.application.screens.DataMatchMeasureHelper;
import com.subex.automation.helpers.component.GridHelper;
import com.subex.automation.helpers.component.SearchGridHelper;
import com.subex.automation.helpers.component.TabHelper;
import com.subex.automation.helpers.file.ExcelReader;
import com.subex.automation.helpers.scripts.TestDataHelper;
import com.subex.automation.helpers.util.FailureHelper;

public class test140186 extends testAuditIssues {
	
	final String sheetName = "test140186";
	
	public test140186() throws Exception {
		super();
	}
	
	@Test(priority=1, description="Test Case 1 for Bug 140186")
	public void testCase1() throws Exception {
		try {
			// Validate that selected measure from summary tab is working properly after saving the configurations i.e. Duration and Volume
			DataMatchMeasureHelper dataMatchMeasure = new DataMatchMeasureHelper();
			dataMatchMeasure.createDataMatchMeasure(path, fileName, sheetName, "DataMatchMeasure", 1);
			
			TestDataHelper testData = new TestDataHelper();
			ExcelReader excelReader = new ExcelReader();
			HashMap<String, ArrayList<String>> excelData = excelReader.readDataByColumn( path, fileName, sheetName, "DataMatchMeasure", 1 );
			
			String measureName = excelData.get("Name").get(0);
			String[] tableInstance = testData.getStringValue(excelData.get("Table Instance").get(0), firstLevelDelimiter);
			String[][] summaryMeasure = testData.getStringValue(excelData.get("Summary Measure").get(0), firstLevelDelimiter, secondLevelDelimiter);
			
			int row = SearchGridHelper.gridFilterSearchWithTextBox("Measure_Name", measureName, "Name");
			NavigationHelper.navigateToEdit("SearchGrid", row, "DMM_Name");
			
			TabHelper.gotoTab("Summary  Information");
			String gridId = "DMM_Summary_Measures_Grid";
			for (int i = 0; i < summaryMeasure.length; i++) {
				assertTrue(GridHelper.isValuePresent(gridId, summaryMeasure[i][0], "Aggregate"));
				assertTrue(GridHelper.isValuePresent(gridId, summaryMeasure[i][1], tableInstance[0]));
				assertTrue(GridHelper.isValuePresent(gridId, summaryMeasure[i][2], tableInstance[1]));
				assertTrue(GridHelper.isValuePresent(gridId, summaryMeasure[i][3], "Unit"));
			}
		} catch (AssertionError e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
}