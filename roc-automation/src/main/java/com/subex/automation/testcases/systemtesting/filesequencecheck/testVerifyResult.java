package com.subex.automation.testcases.systemtesting.filesequencecheck;

import org.testng.annotations.Test;

import com.subex.automation.helpers.application.ControllerHelper;
import com.subex.automation.helpers.application.screens.AlertsHelper;
import com.subex.automation.helpers.application.screens.TrendSummaryTableHelper;
import com.subex.automation.helpers.selenium.ROCAcceptanceTest;
import com.subex.automation.helpers.util.FailureHelper;

public class testVerifyResult extends ROCAcceptanceTest {
	
	private static String path = null;
	final String fileName = "FileSequenceCheck_TestData.xlsx";
	final String sheetName = "FileSequenceCheck";
	
	public testVerifyResult() throws Exception {
		try {
			path = automationPath + "\\src\\main\\resources\\System_Test_Flows\\";
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	@Test(priority=1, description="Starting Server Service", dependsOnGroups = { "run" }, groups = { "VerifyResult" })
	public void startServerService() throws Exception {
		try {
			ControllerHelper controller = new ControllerHelper();
			controller.startServerService();
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	@Test(priority=2, description="Verify File Sequence Check Alerts", dependsOnMethods = { "startServerService" }, groups = { "VerifyResult" })
	public void verifyAlertInstance() throws Exception {
		try {
			AlertsHelper alerts = new AlertsHelper();
			alerts.verifyAlertInstance(path, fileName, sheetName, "VerifyAlert", 1);
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	@Test(priority=3, description="Verifying Trend summary results", dependsOnMethods = { "verifyAlertInstance" }, groups = { "VerifyResult" })
	public void scheduleTrendSummaryTable() throws Exception {
		try {
			TrendSummaryTableHelper trendSummary = new TrendSummaryTableHelper();
			trendSummary.scheduleTrendSummaryTable(path, fileName, sheetName, "VerifyTrendSummary", 1);
			trendSummary.verifyTrendSummaryTable(path, fileName, sheetName, "VerifyTrendSummary", 1);
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	@Test(priority=4, description="Verify Trend summary results", dependsOnMethods = { "scheduleTrendSummaryTable" }, groups = { "VerifyResult" })
	public void verifyTrendSummaryResult() throws Exception {
		try {
			TrendSummaryTableHelper trendSummary = new TrendSummaryTableHelper();
			trendSummary.verifyResult(path, fileName, sheetName, "VerifyTrendSummaryResult", 1);
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	@Test(priority=5, description="Stop all the services", dependsOnMethods = { "verifyTrendSummaryResult" }, groups = { "VerifyResult" })
	public void stopServices() throws Exception {
		try {
			ControllerHelper controller = new ControllerHelper();
			controller.stopTaskController("Task Controller");
			controller.stopServerService();
			controller.stopStreamController();
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
}