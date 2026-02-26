package com.subex.automation.testcases.regressiontesting.etl.etl_issues;

import org.testng.annotations.Test;

import com.subex.automation.helpers.util.FailureHelper;

public class test156583 extends testETLIssues {
	
	final String sheetName = "test156583";
	
	public test156583() throws Exception {
		super();
	}
	
	@Test(priority=81, description="Test Case 1 for Bug 156583")
	public void testCase1() throws Exception {
		try {
			// Create a Standard Table Instance with Table name length of 25 characters
			createTDTI(sheetName, "ImportFromDiamond_TC1", 1, "TableInstance_TC1", 1);
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	@Test(priority=82, description="Test Case 2 for Bug 156583")
	public void testCase2() throws Exception {
		try {
			// Create a Standard Table Instance with Table name length of less than 25 characters
			createTDTI(sheetName, "ImportFromDiamond_TC2", 1, "TableInstance_TC2", 1);
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	@Test(priority=83, description="Test Case 3 for Bug 156583")
	public void testCase3() throws Exception {
		try {
			// Create a Usage Table Instance with Table name length of 18 characters
			createTDTI(sheetName, "ImportFromDiamond_TC3", 1, "TableInstance_TC3", 1);
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	@Test(priority=84, description="Test Case 4 for Bug 156583")
	public void testCase4() throws Exception {
		try {
			// Create a Usage Table Instance with Table name length of less than 18 characters
			createTDTI(sheetName, "ImportFromDiamond_TC4", 1, "TableInstance_TC4", 1);
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
}