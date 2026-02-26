package com.subex.automation.testcases.regressiontesting.measures_audits.audit_issues;

import org.testng.annotations.Test;

import com.subex.automation.helpers.application.screens.AuditDefinitionHelper;
import com.subex.automation.helpers.application.screens.QueryMeasureHelper;
import com.subex.automation.helpers.util.FailureHelper;

public class test151102 extends testAuditIssues {

	final String sheetName = "test151102";

	public test151102() throws Exception {
		super();
	}

	@Test(priority=1, description="Test Case 1 for Bug 151102")
	public void testCase1() throws Exception {
		try {
			// Validate that user is able to create Audit.
			QueryMeasureHelper queryMeasure = new QueryMeasureHelper();
			queryMeasure.createQueryMeasure(path, fileName, sheetName, "QueryMeasure", 1);

			AuditDefinitionHelper auditDfn = new AuditDefinitionHelper();
			auditDfn.createAuditDefinition(path, fileName, sheetName, "Audits", 1);
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}

	@Test(priority=2, description="Test Case 2 for Bug 151102")
	public void testCase2() throws Exception {
		try {
			// Validate that user is able to create Audit Request by adding audit from audit search screen.
			AuditDefinitionHelper auditDfn = new AuditDefinitionHelper();
			auditDfn.createAuditRequest(path, fileName, sheetName, "AuditRequest", 1);
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
}