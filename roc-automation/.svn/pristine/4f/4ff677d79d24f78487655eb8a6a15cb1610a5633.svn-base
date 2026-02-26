package com.subex.automation.testcases.systemtesting.datafederation;

import org.testng.annotations.Test;

import com.subex.automation.helpers.application.ControllerHelper;
import com.subex.automation.helpers.application.screens.AuditRequestHelper;
import com.subex.automation.helpers.application.screens.TrendSummaryTableHelper;
import com.subex.automation.helpers.selenium.ROCAcceptanceTest;
import com.subex.automation.helpers.util.FailureHelper;

public class testVerify extends ROCAcceptanceTest {
	
	private static String path = null;
	final String fileName = "FederatedDataSource_TestData.xlsx";
	final String sheetName = "FederatedDataSource";
	
	public testVerify() throws Exception {
		try {
			path = automationPath + "\\src\\main\\resources\\System_Test_Flows\\";
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	@Test(priority=1, description="Starting Server Service", dependsOnGroups = { "run" }, groups = { "verify" })
	public void startServerService() throws Exception {
		try {
			ControllerHelper controller = new ControllerHelper();
			controller.startServerService();
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	@Test(priority=2, description="Verify Trend Summary result", dependsOnMethods = { "startServerService" }, groups = { "verify" })
	public void verifyTrendSummaryResult() throws Exception {
		try {
			TrendSummaryTableHelper trendSummary = new TrendSummaryTableHelper();
			trendSummary.verifyResult(path, fileName, sheetName, "VerifyTrendSummaryResult", 1);
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	@Test(priority=3, description="Verify CM result", dependsOnMethods = { "verifyTrendSummaryResult" }, groups = { "verify" })
	public void verifyCMResult() throws Exception {
		try {
			AuditRequestHelper auditRequest = new AuditRequestHelper();
			auditRequest.verifyResult(path, fileName, sheetName, "VerifyCMResult", 1);
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	@Test(priority=4, description="Verify DMM result", dependsOnMethods = { "verifyCMResult" }, groups = { "verify" })
	public void verifyDMMResult() throws Exception {
		try {
			AuditRequestHelper auditRequest = new AuditRequestHelper();
			auditRequest.verifyResult(path, fileName, sheetName, "VerifyDMMResult", 1);
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	@Test(priority=5, description="Verify SQL Measure result", dependsOnMethods = { "verifyDMMResult" }, groups = { "verify" })
	public void verifySQLResult() throws Exception {
		try {
			AuditRequestHelper auditRequest = new AuditRequestHelper();
			auditRequest.verifyResult(path, fileName, sheetName, "VerifySQLResult", 1);
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	@Test(priority=6, description="Verify Trend Measure result", dependsOnMethods = { "verifySQLResult" }, groups = { "verify" })
	public void verifyTMResult() throws Exception {
		try {
			AuditRequestHelper auditRequest = new AuditRequestHelper();
			auditRequest.verifyResult(path, fileName, sheetName, "VerifyTMResult", 1);
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	@Test(priority=7, description="Stop all the services", dependsOnMethods = { "verifyTMResult" }, groups = { "VerifyResult" })
	public void stopServices() throws Exception {
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