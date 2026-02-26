package com.subex.automation.testcases.regressiontesting.etl.etl_issues;

import org.testng.annotations.Test;

import com.subex.automation.helpers.application.screens.CollectedFilesHelper;
import com.subex.automation.helpers.util.FailureHelper;

public class test159123 extends testETLIssues {
	
	final String sheetName = "CollectedFiles";
	
	public test159123() throws Exception {
		super();
	}
	
	@Test(priority=121, description="Test Case 1 for Bug 159123")
	public void testCase1() throws Exception {
		try {
			// In Collected Files > View Parse Statistic, parse related details like Input Records, Output Records, Input Usage, Output Usage, etc. has to be captured for ASCII parser
			CollectedFilesHelper collectedFiles = new CollectedFilesHelper();
			collectedFiles.viewParseStatistics(path, fileName, sheetName, "ASCIIParseStatistics", 1);
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
//	Testcase 2 is covered as part of ETL System Flow.
//	@Test(priority=2, description="Test Case 2 for Bug 159123")
//	public void testCase2() throws Exception {
//		try {
//			// In Collected Files > View Parse Statistic, parse related details like Input Records, Output Records, Input Usage, Output Usage, etc. has to be captured for ASN1 parser
//			CollectedFilesHelper collectedFiles = new CollectedFilesHelper();
//			collectedFiles.viewParseStatistics(path, fileName, sheetName, "ASN1ParseStatistics", 1);
//		} catch (Exception e) {
//			FailureHelper.setErrorMessage(e);
//			throw e;
//		}
//	}
	
	@Test(priority=122, description="Test Case 3 for Bug 159123")
	public void testCase3() throws Exception {
		try {
			// In Collected Files > View Parse Statistic, parse related details like Input Records, Output Records, Input Usage, Output Usage, etc. has to be captured for Binary parser
			CollectedFilesHelper collectedFiles = new CollectedFilesHelper();
			collectedFiles.viewParseStatistics(path, fileName, sheetName, "BinaryParseStatistics", 1);
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
}