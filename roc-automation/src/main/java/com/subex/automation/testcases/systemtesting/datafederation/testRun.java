package com.subex.automation.testcases.systemtesting.datafederation;

import org.testng.annotations.Test;

import com.subex.automation.helpers.application.ControllerHelper;
import com.subex.automation.helpers.application.screens.AuditRequestHelper;
import com.subex.automation.helpers.application.screens.TaskControllerHelper;
import com.subex.automation.helpers.application.screens.TaskSearchHelper;
import com.subex.automation.helpers.application.screens.TrendSummaryTableHelper;
import com.subex.automation.helpers.selenium.ROCAcceptanceTest;
import com.subex.automation.helpers.util.FailureHelper;

public class testRun extends ROCAcceptanceTest {
	
	private static String path = null;
	final String fileName = "FederatedDataSource_TestData.xlsx";
	final String sheetName = "FederatedDataSource";
	
	public testRun() throws Exception {
		try {
			path = automationPath + "\\src\\main\\resources\\System_Test_Flows\\";
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	@Test(priority=1, description="Start controller", dependsOnGroups = { "prerequisite3" }, groups = { "run" })
	public void startSC_TC() throws Exception
	{
		try {
			// Start Controllers
			ControllerHelper controller = new ControllerHelper();
			controller.startStreamController();
			controller.startTaskController(configProp.getTaskControllerExeFile());
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	@Test(priority=2, description="Set Task Controller Capability for all the stages", dependsOnMethods = { "startSC_TC" }, groups = { "run" })
	public void setTaskControllerCapability() throws Exception {
		try {
			TaskControllerHelper taskController = new TaskControllerHelper();
			taskController.setTaskControllerCapability(path, fileName, sheetName, "TCCapability", 1);
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	@Test(priority=3, description="Schedule the Trend Summary", dependsOnMethods = { "setTaskControllerCapability" }, groups = { "run" })
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
		
	@Test(priority=4, description="Schedule the Audit Request", dependsOnMethods = { "scheduleTrendSummaryTable" }, groups = { "run" })
	public void scheduleAuditRequest() throws Exception {
		try {
			AuditRequestHelper auditRequest = new AuditRequestHelper();
			auditRequest.scheduleAuditRequest(path, fileName, sheetName, "AuditRequest", 1);
			
			auditRequest.verifyAuditRequest(path, fileName, sheetName, "VerifyAuditRequest", 1);
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	@Test(priority=5,description="Verify Audit & Trend Summary task in Task Search screen", dependsOnMethods = { "scheduleAuditRequest" }, groups = { "run" })
	public void verifyTask() throws Exception {
		try {
			TaskSearchHelper taskSearch = new TaskSearchHelper();
			taskSearch.verifyTaskCount(path, fileName, sheetName, "Task", 1);
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
}