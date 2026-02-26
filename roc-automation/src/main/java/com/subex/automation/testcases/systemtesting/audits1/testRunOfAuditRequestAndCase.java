package com.subex.automation.testcases.systemtesting.audits1;

import org.testng.annotations.Test;

import com.subex.automation.helpers.application.screens.AuditRequestHelper;
import com.subex.automation.helpers.application.screens.TaskSearchHelper;
import com.subex.automation.helpers.selenium.ROCAcceptanceTest;
import com.subex.automation.helpers.util.FailureHelper;

public class testRunOfAuditRequestAndCase extends ROCAcceptanceTest {
	
	private static String path = null;
	final String dataLocation = "/src/main/resources/Data/Audit_Flow";
	final String fileName = "AuditFlow1_TestData.xlsx";
	final String sheetName = "AuditFlow1";
	
	public testRunOfAuditRequestAndCase() throws Exception {
		try {
			path = automationPath + "\\src\\main\\resources\\System_Test_Flows\\";
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	@Test(priority=1, description="Schedule the Audit Request & verify if it is successfully completed", dependsOnGroups = { "runParseAndDataLoad" }, groups = { "runAuditRequest" })
	public void scheduleAuditRequest() throws Exception
	{	
		try {
			AuditRequestHelper auditRequest = new AuditRequestHelper();
			auditRequest.scheduleAuditRequest(path, fileName, sheetName, "AuditRequest", 1);
			Thread.sleep(60000);
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	@Test(priority=2, description="Schedule the Audit Request & verify if it is successfully completed", dependsOnMethods = { "scheduleAuditRequest" }, groups = { "runAuditRequest" })
	public void verifyAuditRequest() throws Exception
	{	
		try {
			AuditRequestHelper auditRequest = new AuditRequestHelper();
			auditRequest.verifyAuditRequest(path, fileName, sheetName, "VerifyAuditRequest", 1);
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	@Test(priority=3, description="Resume Case Manager and Case Manager Allocator", dependsOnMethods = { "verifyAuditRequest" }, groups = { "runAuditRequest" })
	public void resumeStreamStage() throws Exception
	{
		try {
			String[] stream = {"Common Stream"};
			String[][] streamStage = {{"Case Manager", "Case Manager Allocator"}};

			TaskSearchHelper taskSearch = new TaskSearchHelper();
			taskSearch.resumeStreamStage(stream, streamStage);
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	@Test(priority=4, description="Verify Audit Request task in Task Search Screen", dependsOnMethods = { "resumeStreamStage" }, groups = { "runAuditRequest" })
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