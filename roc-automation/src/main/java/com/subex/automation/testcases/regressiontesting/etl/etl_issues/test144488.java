package com.subex.automation.testcases.regressiontesting.etl.etl_issues;

import org.testng.annotations.Test;

import com.subex.automation.helpers.application.screens.CollectedFilesHelper;
import com.subex.automation.helpers.util.FailureHelper;

public class test144488 extends testETLIssues {
	
	final String sheetName = "CollectedFiles";
	
	public test144488() throws Exception {
		super();
	}
	
	@Test(priority=111, description="Test Case 1 for Bug 144488")
	public void testCase1() throws Exception {
		try {
			// View Parse Statistic action should be available for files parsed with XML which loads data into one table
			CollectedFilesHelper collectedFiles = new CollectedFilesHelper();
			collectedFiles.viewParseStatistics(path, fileName, sheetName, "ASCIIParseStatistics", 1);
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	@Test(priority=112, description="Test Case 2 for Bug 144488")
	public void testCase2() throws Exception {
		try {
			// View Parse Statistic action should be available for files parsed with XML which loads data into two tables
			CollectedFilesHelper collectedFiles = new CollectedFilesHelper();
			collectedFiles.viewParseStatistics(path, fileName, sheetName, "ASCIIParseStatistics", 2);
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
}