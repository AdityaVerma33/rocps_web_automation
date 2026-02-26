package com.subex.automation.testcases.systemtesting.filesequencecheck;

import org.testng.annotations.Test;

import com.subex.automation.helpers.application.screens.CaseInstanceHelper;
import com.subex.automation.helpers.application.screens.JumpToSearchHelper;
import com.subex.automation.helpers.application.screens.TaskSearchHelper;
import com.subex.automation.helpers.selenium.ROCAcceptanceTest;
import com.subex.automation.helpers.util.FailureHelper;

public class testVerifyCase extends ROCAcceptanceTest {
	
	private static String path = null;
	final String fileName = "FileSequenceCheck_TestData.xlsx";
	final String sheetName = "FileSequenceCheck";
	
	public testVerifyCase() throws Exception {
		try {
			path = automationPath + "\\src\\main\\resources\\System_Test_Flows\\";
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	@Test(priority=1, description="Create Case Instance", dependsOnGroups = { "run" }, groups = { "VerifyCase" })
	public void verifyJumpToSearchResult() throws Exception
	{
		try {
			JumpToSearchHelper jumpToSearch = new JumpToSearchHelper();
			jumpToSearch.verifyJumpToSearchResult(path, fileName, sheetName, "J2S", 1);
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	@Test(priority=2, description="Create Case Instance for single record", dependsOnMethods = { "verifyJumpToSearchResult" }, groups = { "VerifyCase" })
	public void createSingleCaseInstance() throws Exception
	{
		try {
			JumpToSearchHelper jumpToSearch = new JumpToSearchHelper();
			jumpToSearch.createCaseInstance(path, fileName, sheetName, "CreateCase", 1);
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	@Test(priority=3, description="Create Case Instance for multiple records", dependsOnMethods = { "createSingleCaseInstance" }, groups = { "VerifyCase" })
	public void createMultipleCaseInstance() throws Exception
	{
		try {
			JumpToSearchHelper jumpToSearch = new JumpToSearchHelper();
			jumpToSearch.createCaseInstance(path, fileName, sheetName, "CreateCase", 2);
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	@Test(priority=4, description="Schedule Case Management tasks", dependsOnMethods = { "createMultipleCaseInstance" }, groups = { "VerifyCase" })
	public void scheduleTask() throws Exception
	{
		try {
			TaskSearchHelper taskSearch = new TaskSearchHelper();
			taskSearch.scheduleTask(path, fileName, sheetName, "ScheduleTask", 1);
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	@Test(priority=5, description="Verifying CM tasks in Task Search Screen", dependsOnMethods = { "scheduleTask" }, groups = { "VerifyCase" })
	public void verifyCMTask() throws Exception
	{
		try {
			TaskSearchHelper taskSearch = new TaskSearchHelper();
			taskSearch.verifyTaskCount(path, fileName, sheetName, "CMTask", 1);
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	@Test(priority=6, description="Verify created Case Instance", dependsOnMethods = { "verifyCMTask" }, groups = { "VerifyCase" })
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
	
	@Test(priority=7, description="Verify Case Instance details", dependsOnMethods = { "verifyCaseInstance" }, groups = { "VerifyCase" })
	public void verifyCaseDetails() throws Exception
	{
		try {
			CaseInstanceHelper caseInstance = new CaseInstanceHelper();
			caseInstance.verifyCaseDetails(path, fileName, sheetName, "VerifyCaseDetails", 1);
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
}