package com.subex.automation.testcases.systemtesting.audits1;

import org.testng.annotations.Test;

import com.subex.automation.helpers.application.ControllerHelper;
import com.subex.automation.helpers.application.screens.AuditRequestHelper;
import com.subex.automation.helpers.application.screens.JumpToSearchHelper;
import com.subex.automation.helpers.selenium.ROCAcceptanceTest;
import com.subex.automation.helpers.util.FailureHelper;

public class testVerifyAuditRequest extends ROCAcceptanceTest
{
	private static String path = null;
	final String dataLocation = "\\src\\main\\resources\\Data\\Audit_Flow";
	final String fileName = "AuditFlow1_TestData.xlsx";
	final String sheetName = "AuditFlow1";
	
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
	
	@Test(priority=2, description="Verify Audit Request Results for QM", dependsOnMethods = { "startServerService" }, groups = { "verifyAuditRequest" })
	public void verifyQMResult() throws Exception
	{
		try {
			AuditRequestHelper auditRequest = new AuditRequestHelper();
			auditRequest.verifyResult(path, fileName, sheetName, "VerifyQMResult", 1);
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	@Test(priority=3, description="Verify Audit Request Results for CM", dependsOnMethods = { "startServerService" }, groups = { "verifyAuditRequest" })
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
	
	@Test(priority=4, description="Verify Audit Request Results for DMM-1", dependsOnMethods = { "startServerService" }, groups = { "verifyAuditRequest" })
	public void verifyDMResult() throws Exception
	{
		try {
			AuditRequestHelper auditRequest = new AuditRequestHelper();
			auditRequest.verifyResult(path, fileName, sheetName, "VerifyDMResult", 1);
			
			auditRequest.verifyResultDrillDown(path, fileName, sheetName, "VerifyDMResultDrillDown", 1);
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	@Test(priority=5, description="Verify Jump to Search Result", dependsOnMethods = { "startServerService" }, groups = { "verifyAuditRequest" })
	public void verifyJ2SResult() throws Exception {
		try {
			JumpToSearchHelper jumpToSearch = new JumpToSearchHelper();
			jumpToSearch.verifyJumpToSearchResult(path, fileName, sheetName, "J2S", 1);
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
}