package com.subex.automation.testcases.regressiontesting.measures_audits.audit_issues;

import org.testng.annotations.Test;

import com.subex.automation.helpers.application.screens.AuditDefinitionHelper;
import com.subex.automation.helpers.application.screens.ComparisonMeasureHelper;
import com.subex.automation.helpers.application.screens.QueryMeasureHelper;
import com.subex.automation.helpers.util.FailureHelper;

public class test140021 extends testAuditIssues {

	final String sheetName = "test140021";

	public test140021() throws Exception {
		super();
	}

	@Test(priority=1, description="Test case 1 for Bug 140021")
	public void testCase1()throws Exception
	{
		try {
			// Validate that user is able to add multiple measures in an Audit
			QueryMeasureHelper queryMeasure = new QueryMeasureHelper();
			queryMeasure.createQueryMeasure(path, fileName, sheetName, "QueryMeasure", 1);
			
			ComparisonMeasureHelper comparisonMeasure = new ComparisonMeasureHelper();
			comparisonMeasure.createComparisonMeasure(path, fileName, sheetName, "ComparisonMeasure", 1);
			
			AuditDefinitionHelper auditDfn = new AuditDefinitionHelper();
			auditDfn.createAuditDefinition(path, fileName, sheetName, "Audits", 1);
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
}