package com.subex.automation.testcases.systemtesting.offlineldc;

import org.testng.annotations.Test;

import com.subex.automation.helpers.application.screens.JumpToSearchHelper;
import com.subex.automation.helpers.application.screens.RecurringTaskHelper;
import com.subex.automation.helpers.application.screens.TaskSearchHelper;
import com.subex.automation.helpers.selenium.ROCAcceptanceTest;
import com.subex.automation.helpers.util.FailureHelper;

public class testRun2 extends ROCAcceptanceTest {
	
	private static String path = null;
	final String fileName = "OfflineLDC_TestData.xlsx";
	final String sheetName = "OfflineLDC";
	
	public testRun2() throws Exception {
		try {
			path = automationPath + "\\src\\main\\resources\\System_Test_Flows\\";
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	@Test(priority=1, description="Creating recurring task for LDC Correlator", dependsOnGroups = { "run1" }, groups = { "run2" })
	public void createRecurringTask() throws Exception {
		try {
			RecurringTaskHelper recurringTask = new RecurringTaskHelper();
			recurringTask.createRecurringTask(path, fileName, sheetName, "RecurringTask", 1);
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	@Test(priority=2, description="Verifying if LDC Task got Completed", dependsOnMethods = { "createRecurringTask" }, groups = { "run2" })
	public void verifyLDCTask() throws Exception {
		try {
			TaskSearchHelper taskSearch = new TaskSearchHelper();
			taskSearch.verifyTaskStatus(path, fileName, sheetName, "LDCTask", 1);
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	@Test(priority=3, description="Verifying the LDC Stitched table for the stitched records.", dependsOnMethods = { "verifyLDCTask" }, groups = { "run2" })
	public void verifyPartialTableResults() throws Exception {
		try {
			JumpToSearchHelper jumpToSearch = new JumpToSearchHelper();
			jumpToSearch.verifyJumpToSearchResult(path, fileName, sheetName, "PartialJ2S", 1);
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	@Test(priority=4, description="Verifying the LDC Partial table for the partial records.", dependsOnMethods = { "verifyPartialTableResults" }, groups = { "run2" })
	public void verifyStitchedTableResults() throws Exception {
		try {
			JumpToSearchHelper jumpToSearch = new JumpToSearchHelper();
			jumpToSearch.verifyJumpToSearchResult(path, fileName, sheetName, "StitchedJ2S", 1);
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
}