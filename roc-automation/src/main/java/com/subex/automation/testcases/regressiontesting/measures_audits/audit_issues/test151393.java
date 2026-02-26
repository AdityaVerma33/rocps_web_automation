package com.subex.automation.testcases.regressiontesting.measures_audits.audit_issues;

import org.testng.annotations.Test;

import com.subex.automation.helpers.application.screens.CaseInstanceHelper;
import com.subex.automation.helpers.util.FailureHelper;

public class test151393 extends testAuditIssues {
	
	//final String fileName = "AuditIssues_RekhaData.xlsx";
	final String sheetName = "test151393";
	
	public test151393() throws Exception {
		super();
	}
	
	@Test(priority=1, description="Test Case 1 for Bug 151393")
	public void testCase1() throws Exception {
		try {
			// Validate that user is able to see cases based on selected case Template.
            CaseInstanceHelper caseinstance = new CaseInstanceHelper();
            caseinstance.verifyCaseInstance(path, fileName, sheetName, "CaseTemplateFilter", 1);
		}
		catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
	}
  }
}