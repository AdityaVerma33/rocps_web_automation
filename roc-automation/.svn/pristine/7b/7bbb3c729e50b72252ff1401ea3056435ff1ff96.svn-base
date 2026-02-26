package com.subex.automation.testcases.regressiontesting.measures_audits.audit_issues;

import org.testng.annotations.Test;

import com.subex.automation.helpers.application.ControllerHelper;
import com.subex.automation.helpers.application.ROCHelper;
import com.subex.automation.helpers.application.screens.AuditDefinitionHelper;
import com.subex.automation.helpers.application.screens.AuditRequestHelper;
import com.subex.automation.helpers.application.screens.KPIDefinitionHelper;
import com.subex.automation.helpers.application.screens.KPIResultsHelper;
import com.subex.automation.helpers.application.screens.QueryMeasureHelper;
import com.subex.automation.helpers.util.FailureHelper;

public class test164597 extends testAuditIssues {
	
	final String sheetName = "test164597";
	
	public test164597() throws Exception {
		super();
	}

	@Test(priority=1, description="Test Case 1 for Bug 164597")
	public void testCase1() throws Exception {
		try {
			// Validate that User is able to create Measure with group by fields and an aggregated fields.
			QueryMeasureHelper queryMeasure = new QueryMeasureHelper();
			queryMeasure.createQueryMeasure(path, fileName, sheetName, "QueryMeasure", 1);
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	@Test(priority=2, description="Test Case 2 for Bug 164597", dependsOnMethods={"testCase1"})
	public void testCase2() throws Exception {
		try {
			// Validate that User is able to create KPI on aggregated field with "apply kpi at group level".
			KPIDefinitionHelper kpiDefinition = new KPIDefinitionHelper();
			kpiDefinition.createKPIDefinition(path, fileName, sheetName, "KPIDefinition", 1);
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	@Test(priority=3, description="Test Case 3 for Bug 164597", dependsOnMethods={"testCase2"})
	public void testCase3() throws Exception {
		ControllerHelper controller = new ControllerHelper();
		
		try {
			// Validate that user is able to create and Schedule Audit.
			AuditDefinitionHelper auditDfn = new AuditDefinitionHelper();
			auditDfn.createAuditDefinition(path, fileName, sheetName, "Audits", 1);
			
			// Start Controllers
			controller.startStreamController();
			controller.startTaskController(configProp.getTaskControllerExeFile());
			
			AuditRequestHelper auditRequest = new AuditRequestHelper();
			auditRequest.createAuditRequest(path, fileName, sheetName, "AuditRequest", 1);
			auditRequest.scheduleAuditRequest(path, fileName, sheetName, "AuditRequest", 1);
			auditRequest.verifyAuditRequest(path, fileName, sheetName, "VerifyAuditRequest", 1);
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		} finally {
			ROCHelper rocHelper = new ROCHelper();
			rocHelper.handleFailures();
			
			controller.stopTaskController("Task Controller");
			controller.stopStreamController();
		}
	}
	
	@Test(priority=4, description="Test Case 4 for Bug 164597", dependsOnMethods={"testCase3"})
	public void testCase4() throws Exception {
		try {
			// Validate that user is able to see Violated KPI value in GUI for group by checked.
			KPIResultsHelper kpiResults = new KPIResultsHelper();
			kpiResults.verifyKPIResult(path, fileName, sheetName, "VerifyKPIResult", 1);
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	@Test(priority=5, description="Test Case 5 for Bug 164597", dependsOnMethods={"testCase3"})
	public void testCase5() throws Exception {
		try {
			// "Get Record and Page Count" button should show the row count of the records for Measure Result table.
			AuditRequestHelper auditRequest = new AuditRequestHelper();
			auditRequest.verifyResult(path, fileName, sheetName, "VerifyQMResult", 1);
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
}