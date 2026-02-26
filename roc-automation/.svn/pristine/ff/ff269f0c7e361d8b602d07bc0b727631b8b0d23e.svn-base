package com.subex.automation.testcases.systemtesting.audits2;

import org.testng.annotations.Test;

import com.subex.automation.helpers.application.ControllerHelper;
import com.subex.automation.helpers.application.screens.AuditRequestHelper;
import com.subex.automation.helpers.selenium.ROCAcceptanceTest;
import com.subex.automation.helpers.util.FailureHelper;

public class testVerifyAuditRequest extends ROCAcceptanceTest
{
	private static String path = null;
	final String fileName = "AuditFlow2_TestData.xlsx";
	final String sheetName = "AuditFlow2";
	
	public testVerifyAuditRequest() throws Exception {
		try {
			path = automationPath + "\\src\\main\\resources\\System_Test_Flows\\";
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	@Test(priority=1, description="Starting Server Service", dependsOnGroups = { "runAuditRequest" }, groups = { "verifyAuditRequest" })
	public void startServerService() throws Exception
	{
		try {
			ControllerHelper controller = new ControllerHelper();
			controller.startServerService();
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	@Test(priority=2, description="Verify CM Results", dependsOnMethods = { "startServerService" }, groups = { "verifyAuditRequest" })
	public void verifyCMResult() throws Exception
	{
		try {
			AuditRequestHelper auditRequest = new AuditRequestHelper();
			auditRequest.verifyResult(path, fileName, sheetName, "VerifyCMResult", 1);
			
			auditRequest.verifyResultDrillDown(path, fileName, sheetName, "VerifyCMDrillDown", 1);
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	@Test(priority=3, description="Verify QM Results", dependsOnMethods = { "verifyCMResult" }, groups = { "verifyAuditRequest" })
	public void verifyQMResult() throws Exception
	{
		try {
			AuditRequestHelper auditRequest = new AuditRequestHelper();
			auditRequest.verifyResult(path, fileName, sheetName, "VerifyQMResult", 1);
			
			auditRequest.verifyResultDrillDown(path, fileName, sheetName, "VerifyQMDrillDown", 1);
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	@Test(priority=4, description="Verifying result of usage_cdr_02 through drill down action from 'DMM - 1'", dependsOnMethods = { "verifyQMResult" }, groups = { "verifyAuditRequest" })
	public void verifyDMMResult() throws Exception
	{
		try {
			AuditRequestHelper auditRequest = new AuditRequestHelper();
			auditRequest.verifyResult(path, fileName, sheetName, "VerifyDMResult", 1);
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	@Test(priority=5, description="Stop controllers", dependsOnMethods = { "verifyDMMResult" }, groups = { "verifyAuditRequest" })
	public void stopServices() throws Exception
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