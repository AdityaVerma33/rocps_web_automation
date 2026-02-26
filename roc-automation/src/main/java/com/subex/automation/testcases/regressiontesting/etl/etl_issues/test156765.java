package com.subex.automation.testcases.regressiontesting.etl.etl_issues;

import org.testng.annotations.Test;

import com.subex.automation.helpers.application.screens.TableInstanceHelper;
import com.subex.automation.helpers.util.FailureHelper;

public class test156765 extends testETLIssues {
	
	final String sheetName = "test156765";
	
	public test156765() throws Exception {
		super();
	}
	
	@Test(priority=91, description="Test Case 1 for Bug 156765")
	public void testCase1() throws Exception {
		try {
			// Create a Standard Table Instance
			createTDTI(sheetName, "ImportFromDiamond_TC1", 1, "TableInstance_TC1", 1);
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	@Test(priority=92, description="Test Case 2 for Bug 156765")
	public void testCase2() throws Exception {
		try {
			// Create a Usage Table Instance for Partitioned Schema
			createTDTI(sheetName, "ImportFromDiamond_TC2", 1, "TableInstance_TC2", 1);
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	@Test(priority=93, description="Test Case 3 for Bug 156765")
	public void testCase3() throws Exception {
		try {
			// Create a Usage Table Instance for user created Schema
			TableInstanceHelper tableInstance = new TableInstanceHelper();
			tableInstance.createTableInstance(path, fileName, sheetName, "TableInstance_TC3", 1);
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
}