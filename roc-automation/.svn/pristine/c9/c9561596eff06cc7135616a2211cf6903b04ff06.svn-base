package com.subex.automation.testcases.regressiontesting.measures_audits.audit_issues;

import org.testng.annotations.Test;

import com.subex.automation.helpers.application.screens.QueryMeasureHelper;
import com.subex.automation.helpers.application.screens.SQLMeasureHelper;
import com.subex.automation.helpers.component.GenericHelper;
import com.subex.automation.helpers.component.GridHelper;
import com.subex.automation.helpers.component.SearchGridHelper;
import com.subex.automation.helpers.util.FailureHelper;

public class test164698 extends testAuditIssues {
	
	final String sheetName = "test164698";
	
	public test164698() throws Exception {
		super();
	}

	@Test(priority=1, description="Test Case 1 for Bug 164698")
	public void testCase1() throws Exception {
		try {
			// Validate that on Measure page applying filter on Name Column and sorting Name in Ascending order page load succesfully.
			QueryMeasureHelper queryMeasure = new QueryMeasureHelper();
			queryMeasure.createQueryMeasure(path, fileName, sheetName, "QueryMeasure", 1);
			
			SQLMeasureHelper sqlMeasure = new SQLMeasureHelper();
			sqlMeasure.createSQLMeasure(path, fileName, sheetName, "SQLMeasure", 1);
			
			SearchGridHelper.gridFilterSearchWithTextBox("Measure_Name", "QM - ", "Name");
			int rows = GridHelper.getRowCount("SearchGrid");
			assertTrue(rows >= 2, "Expected atleast 2 Query Measures.");
			
			SearchGridHelper.sortAscending("Name");
			GenericHelper.waitForLoadmask(searchScreenWaitSec);
			rows = GridHelper.getRowCount("SearchGrid");
			assertTrue(rows >= 2, "Expected atleast 2 Query Measures.");
			
			GridHelper.sortGrid("SearchGrid", "Type");
			SearchGridHelper.gridFilterSearchWithTextBox("Measure_Name", "%164698%", "Name");
			rows = GridHelper.getRowCount("SearchGrid");
			assertTrue(rows >= 2, "Expected atleast 2 Measures.");
			
			SearchGridHelper.sortAscending("Name");
			GenericHelper.waitForLoadmask(searchScreenWaitSec);
			rows = GridHelper.getRowCount("SearchGrid");
			assertTrue(rows >= 2);
		} catch (AssertionError e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	@Test(priority=2, description="Test Case 2 for Bug 164698")
	public void testCase2() throws Exception {
		try {
			// Validate that on Measure page applying filter on Name Column and sorting Name in Descending order page load succesfully.
			QueryMeasureHelper queryMeasure = new QueryMeasureHelper();
			queryMeasure.createQueryMeasure(path, fileName, sheetName, "QueryMeasure", 1);
			
			SQLMeasureHelper sqlMeasure = new SQLMeasureHelper();
			sqlMeasure.createSQLMeasure(path, fileName, sheetName, "SQLMeasure", 1);
			
			SearchGridHelper.gridFilterSearchWithTextBox("Measure_Name", "QM - ", "Name");
			int rows = GridHelper.getRowCount("SearchGrid");
			assertTrue(rows >= 2, "Expected atleast 2 Query Measures.");
			
			SearchGridHelper.sortDescending("Name");
			GenericHelper.waitForLoadmask(searchScreenWaitSec);
			rows = GridHelper.getRowCount("SearchGrid");
			assertTrue(rows >= 2, "Expected atleast 2 Query Measures.");
			
			GridHelper.sortGrid("SearchGrid", "Type");
			SearchGridHelper.gridFilterSearchWithTextBox("Measure_Name", "%164698%", "Name");
			rows = GridHelper.getRowCount("SearchGrid");
			assertTrue(rows >= 2);
			
			SearchGridHelper.sortDescending("Name");
			GenericHelper.waitForLoadmask(searchScreenWaitSec);
			rows = GridHelper.getRowCount("SearchGrid");
			assertTrue(rows >= 2, "Expected atleast 2 Measures.");
		} catch (AssertionError e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
}