package com.subex.automation.testcases.regressiontesting.measures_audits.audit_issues;

import org.testng.annotations.Test;

import com.subex.automation.helpers.application.ControllerHelper;
import com.subex.automation.helpers.application.ROCHelper;
import com.subex.automation.helpers.application.screens.AuditDefinitionHelper;
import com.subex.automation.helpers.application.screens.AuditRequestHelper;
import com.subex.automation.helpers.application.screens.QueryMeasureHelper;
import com.subex.automation.helpers.util.FailureHelper;

public class test145484 extends testAuditIssues {
	
	final String sheetName = "test145484";
	
	public test145484() throws Exception {
		super();
	}
	
	@Test(priority=1, description="Test Case 1 for Bug 145484")
	public void testCase1() throws Exception {
		ControllerHelper controller = new ControllerHelper();
		
		try {
			// Validate that user is able to create and run Audit.
			QueryMeasureHelper queryMeasure = new QueryMeasureHelper();
			queryMeasure.createQueryMeasure(path, fileName, sheetName, "QueryMeasure", 1);
			
			AuditDefinitionHelper auditDfn = new AuditDefinitionHelper();
			auditDfn.createAuditDefinition(path, fileName, sheetName, "Audits", 1);
			
			AuditRequestHelper auditRequest = new AuditRequestHelper();
			auditRequest.createAuditRequest(path, fileName, sheetName, "AuditRequest", 1);
			
			controller.startStreamController();
			controller.startTaskController(configProp.getTaskControllerExeFile());
			
			auditRequest.scheduleAuditRequest(path, fileName, sheetName, "AuditRequest", 1);
			Thread.sleep(3000);
			
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
	
	@Test(priority=2, description="Test Case 2 for Bug 145484", dependsOnMethods={"testCase1"})
	public void testCase2() throws Exception {
		try {
			// Validate that on 1st record check the count for the measures in that audit.
			AuditRequestHelper auditRequest = new AuditRequestHelper();
			auditRequest.verifyResult(path, fileName, sheetName, "VerifyQMResult", 1);
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	@Test(priority=3, description="Test Case 3 for Bug 145484", dependsOnMethods={"testCase1"})
	public void testCase3() throws Exception {
		try {
			// Validate that  on 2nd record check the count for the measures in that audit.
			AuditRequestHelper auditRequest = new AuditRequestHelper();
			auditRequest.verifyResult(path, fileName, sheetName, "VerifyQMResult", 1);
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
}