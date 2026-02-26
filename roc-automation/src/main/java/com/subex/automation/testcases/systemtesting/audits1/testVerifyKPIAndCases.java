package com.subex.automation.testcases.systemtesting.audits1;

import org.testng.annotations.Test;

import com.subex.automation.helpers.application.ControllerHelper;
import com.subex.automation.helpers.application.screens.AuditRequestHelper;
import com.subex.automation.helpers.application.screens.CaseInstanceHelper;
import com.subex.automation.helpers.selenium.ROCAcceptanceTest;
import com.subex.automation.helpers.util.FailureHelper;

public class testVerifyKPIAndCases extends ROCAcceptanceTest
{
	private static String path = null;
	final String dataLocation = "\\src\\main\\resources\\Data\\Audit_Flow";
	final String fileName = "AuditFlow1_TestData.xlsx";
	final String sheetName = "AuditFlow1";
	
	public testVerifyKPIAndCases() throws Exception {
		try {
			path = automationPath + "\\src\\main\\resources\\System_Test_Flows\\";
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	@Test(priority=1, description="Verify KPI Result", dependsOnGroups = { "verifyAuditRequest" }, groups = { "VerifyKPIAndCases" })
	public void verifyKPIResult() throws Exception
	{
		try {
			AuditRequestHelper auditRequest = new AuditRequestHelper();
			auditRequest.verifyKPIResult(path, fileName, sheetName, "VerifyKPIResult", 1);
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	@Test(priority=2, description="Verify Case Instances generated", dependsOnMethods = { "verifyKPIResult" }, groups = { "VerifyKPIAndCases" })
	public void verifyCaseInstance() throws Exception
	{
		try {
			CaseInstanceHelper caseInstance = new CaseInstanceHelper();
			caseInstance.verifyCaseInstance(path, fileName, sheetName, "VerifyCaseInstance", 1);
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	@Test(priority=3, description="Stop Services", dependsOnMethods = { "verifyCaseInstance" }, groups = { "VerifyKPIAndCases" })
	public static void stopServices()throws Exception
	{
		try {
			ControllerHelper controller = new ControllerHelper();
			controller.stopServerService();
			controller.stopTaskController("Task Controller");
			controller.stopStreamController();
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
}