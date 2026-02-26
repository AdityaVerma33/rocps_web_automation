package com.subex.automation.testcases.systemtesting.audits2;

import org.testng.annotations.Test;

import com.subex.automation.helpers.application.screens.AuditRequestHelper;
import com.subex.automation.helpers.application.screens.TaskSearchHelper;
import com.subex.automation.helpers.application.screens.TrendSummaryTableHelper;
import com.subex.automation.helpers.selenium.ROCAcceptanceTest;
import com.subex.automation.helpers.util.FailureHelper;

public class testRunOfAuditRequest extends ROCAcceptanceTest
{
	private static String path = null;
	final String fileName = "AuditFlow2_TestData.xlsx";
	final String sheetName = "AuditFlow2";
	
	public testRunOfAuditRequest() throws Exception {
		try {
			path = automationPath + "\\src\\main\\resources\\System_Test_Flows\\";
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	@Test(priority=1, description="Scheduling the trend summary which will be used for trend measure", dependsOnGroups = { "runETL" }, groups = { "runAuditRequest" })
	public void scheduleTrendSummaryTable() throws Exception
	{	
		try {
			TrendSummaryTableHelper trendSummary = new TrendSummaryTableHelper();
			trendSummary.scheduleTrendSummaryTable(path, fileName, sheetName, "VerifyTrendSummary", 1);
			Thread.sleep(30000);
			
			trendSummary.verifyTrendSummaryTable(path, fileName, sheetName, "VerifyTrendSummary", 1);
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	@Test(priority=2, description="Verify the trend summary result", dependsOnMethods = { "scheduleTrendSummaryTable" }, groups = { "runAuditRequest" })
	public void verifyTrendSummaryResult() throws Exception
	{	
		try {
			TrendSummaryTableHelper trendSummary = new TrendSummaryTableHelper();
			trendSummary.verifyResult(path, fileName, sheetName, "VerifyTrendSummaryResult", 1);
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	@Test(priority=3,description="Schedule the Audit Request and verify that it is successfully completed", dependsOnMethods = { "verifyTrendSummaryResult" }, groups = { "runAuditRequest" })
	public void scheduleAuditRequest() throws Exception
	{
		try {
			AuditRequestHelper auditRequest = new AuditRequestHelper();
			auditRequest.scheduleAuditRequest(path, fileName, sheetName, "AuditRequest", 1);
			
			auditRequest.verifyAuditRequest(path, fileName, sheetName, "VerifyAuditRequest", 1);
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	@Test(priority=4, description="Verify Audit & Trend Summary tasks in Task Search Screen", dependsOnMethods = { "scheduleAuditRequest" }, groups = { "runAuditRequest" })
	public void verifyCommonTaskCount() throws Exception
	{
		try {
			TaskSearchHelper taskSearch = new TaskSearchHelper();
			taskSearch.verifyTaskCount(path, fileName, sheetName, "CommonTasks", 1);
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
}