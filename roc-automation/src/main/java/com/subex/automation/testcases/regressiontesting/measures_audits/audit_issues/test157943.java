package com.subex.automation.testcases.regressiontesting.measures_audits.audit_issues;

import org.testng.annotations.Test;

import com.subex.automation.helpers.application.screens.QueryMeasureHelper;
import com.subex.automation.helpers.report.Log4jHelper;
import com.subex.automation.helpers.util.FailureHelper;

public class test157943 extends testAuditIssues {
	
	final String sheetName = "test157943";
	
	public test157943() throws Exception {
		super();
	}

	@Test(priority=1, description="Test Case 1 for Bug 157943")
	public void testCase1() throws Exception {
		try {
			// User should be able to create a new query measure.
			Log4jHelper.logInfo("Creating new Query Measure");
			createQueryMeasure("QueryMeasure");
			
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public void createQueryMeasure(String testCaseName) throws Exception {
		try {
			QueryMeasureHelper queryMeasure = new QueryMeasureHelper();
			queryMeasure.createQueryMeasure(path, fileName, sheetName, testCaseName, 1);
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
}