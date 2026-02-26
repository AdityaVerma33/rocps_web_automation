package com.subex.automation.testcases.regressiontesting.measures_audits.audit_issues;

import org.testng.annotations.Test;

import com.subex.automation.helpers.application.screens.DataMatchMeasureHelper;
import com.subex.automation.helpers.util.FailureHelper;

public class test141902 extends testAuditIssues {

	final String sheetName = "test141902";

	public test141902() throws Exception {
		super();
	}

	@Test(priority=1, description="Test Case 1 for Bug 141902")
	public void testCase1()throws Exception
	{
		try {
			// Validate that user is able to create DM measure.
			DataMatchMeasureHelper dataMatchMeasure = new DataMatchMeasureHelper();
			dataMatchMeasure.createDataMatchMeasure(path, fileName, sheetName, "DataMatchMeasure", 1);
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
}