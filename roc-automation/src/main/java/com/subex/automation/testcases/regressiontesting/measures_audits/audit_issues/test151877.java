package com.subex.automation.testcases.regressiontesting.measures_audits.audit_issues;

import java.util.ArrayList;

import org.testng.annotations.Test;

import com.subex.automation.helpers.application.ConfigureGridHelper;
import com.subex.automation.helpers.component.ButtonHelper;
import com.subex.automation.helpers.component.GenericHelper;
import com.subex.automation.helpers.component.GridHelper;
import com.subex.automation.helpers.data.StringHelper;
import com.subex.automation.helpers.util.FailureHelper;

public class test151877 extends testAuditIssues {
	
	final String sheetName = "test151877";
	
	public test151877() throws Exception {
		super();
	}
	
	@Test(priority=1, description="Test Case 1 for Bug 151877")
	public void testCase1() throws Exception {
		try {
			// Validate that Ascending order Sorting functionality in Configure Grid applying on Measure Page list of measures
			ConfigureGridHelper configureGrid = new ConfigureGridHelper();
			configureGrid.updateConfigureGrid(path, fileName, sheetName, "MeasureConfigureGrid", 1);
			
			GridHelper.setPagination("SearchGrid", "50 per page");
			ButtonHelper.click("SearchButton");
			GenericHelper.waitForLoadmask(searchScreenWaitSec);
			int colNum = GridHelper.getColumnNumber("SearchGrid", "Name");
			ArrayList<String> actualValues = GridHelper.getColumnValues("SearchGrid", colNum);
			ArrayList<String> expectedValues = StringHelper.sortDescending(actualValues);
			
			assertEquals(actualValues, expectedValues);
		} catch (AssertionError e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	@Test(priority=2, description="Test Case 2 for Bug 151877")
	public void testCase2() throws Exception {
		try {
			// Validate that Descending order Sorting functionality in Configure Grid applying on Measure Page list of measures
			ConfigureGridHelper configureGrid = new ConfigureGridHelper();
			configureGrid.updateConfigureGrid(path, fileName, sheetName, "MeasureConfigureGrid", 1);
			
			GridHelper.setPagination("SearchGrid", "50 per page");
			ButtonHelper.click("SearchButton");
			GenericHelper.waitForLoadmask(searchScreenWaitSec);
			int colNum = GridHelper.getColumnNumber("SearchGrid", "Name");
			ArrayList<String> actualValues = GridHelper.getColumnValues("SearchGrid", colNum);
			ArrayList<String> expectedValues = StringHelper.sortAscending(actualValues);
			
			assertEquals(actualValues, expectedValues);
		} catch (AssertionError e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
}