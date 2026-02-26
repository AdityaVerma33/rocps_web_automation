package com.subex.automation.testcases.regressiontesting.measures_audits.audit_issues;

import org.testng.annotations.Test;

import com.subex.automation.helpers.application.ControllerHelper;
import com.subex.automation.helpers.application.screens.AuditDefinitionHelper;
import com.subex.automation.helpers.application.screens.AuditRequestHelper;
import com.subex.automation.helpers.application.screens.DataMatchMeasureHelper;
import com.subex.automation.helpers.util.FailureHelper;

public class test138308 extends testAuditIssues {

	final String sheetName = "test138308";

	public test138308() throws Exception {
		super();
	}

	@Test(priority=1, description="Test Case 1 for Bug 138308")
	public void testCase1() throws Exception {
		try {
			// Validate that user is able to create DM measure having duplicate records in a source with tolerance set in DM.
			DataMatchMeasureHelper dataMatchMeasure = new DataMatchMeasureHelper();
			dataMatchMeasure.createDataMatchMeasure(path, fileName, sheetName, "DataMatchMeasure", 1);
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}

	@Test(priority=2, description="Test Case 2 for Bug 138308", dependsOnMethods={"testCase1"})
	public void testCase2() throws Exception {
		ControllerHelper controller = new ControllerHelper();

		try {
			// Validate that user is able to create and Schedule Audit.
			AuditDefinitionHelper auditDfn = new AuditDefinitionHelper();
			auditDfn.createAuditDefinition(path, fileName, sheetName, "Audits", 1);
			
			AuditRequestHelper auditRequest = new AuditRequestHelper();
			auditRequest.createAuditRequest(path, fileName, sheetName, "AuditRequest", 1);
			
			// Start Controllers
			controller.startStreamController();
			controller.startTaskController(configProp.getTaskControllerExeFile());
			
			auditRequest.scheduleAuditRequest(path, fileName, sheetName, "AuditRequest", 1);
			auditRequest.verifyAuditRequest(path, fileName, sheetName, "VerifyAuditRequest", 1);
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		} finally {
			controller.stopTaskController("Task Controller");
			controller.stopStreamController();
		}
	}
	
	@Test(priority=3, description="Test Case 3 for Bug 138308", dependsOnMethods={"testCase2"})
	public void testCase3() throws Exception {
		try {
			// Validate the DM Results For above mentioned DM.
			AuditRequestHelper auditRequest = new AuditRequestHelper();
			auditRequest.verifyResult(path, fileName, sheetName, "VerifyDMResult", 1);
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
}