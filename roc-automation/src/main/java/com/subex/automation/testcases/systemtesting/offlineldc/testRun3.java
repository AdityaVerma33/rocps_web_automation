package com.subex.automation.testcases.systemtesting.offlineldc;

import org.testng.annotations.Test;

import com.subex.automation.helpers.application.ControllerHelper;
import com.subex.automation.helpers.application.screens.JumpToSearchHelper;
import com.subex.automation.helpers.application.screens.RecurringTaskHelper;
import com.subex.automation.helpers.application.screens.TaskSearchHelper;
import com.subex.automation.helpers.selenium.ROCAcceptanceTest;
import com.subex.automation.helpers.util.FailureHelper;

public class testRun3 extends ROCAcceptanceTest {
	
	private static String path = null;
	final String fileName = "OfflineLDC_TestData.xlsx";
	final String sheetName = "OfflineLDC";
	
	public testRun3() throws Exception {
		try {
			path = automationPath + "\\src\\main\\resources\\System_Test_Flows\\";
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	@Test(priority=1,description="Create recurring task fro Roll-over partition", dependsOnGroups = { "run2" }, groups = { "run3" })
	public void createRecurringTask() throws Exception {
		try {
			RecurringTaskHelper recurringTask = new RecurringTaskHelper();
			recurringTask.createRecurringTask(path, fileName, sheetName, "RollOverRecurringTask", 1);
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	@Test(priority=2, description="Resume Roll-over partition Stream Stage", dependsOnMethods = { "createRecurringTask" }, groups = { "run3" })
	public void resumeRollOverStreamStage() throws Exception {
		try {
			TaskSearchHelper taskSearch = new TaskSearchHelper();
			taskSearch.resumeStreamStage(path, fileName, sheetName, "ResumeStreamStage", 1);
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	@Test(priority=3, description="Verify if Roll Over Partition Task got Completed", dependsOnMethods = { "resumeRollOverStreamStage" }, groups = { "run3" })
	public void verifyRollOverTaskStatus() throws Exception {
		try {
			TaskSearchHelper taskSearch = new TaskSearchHelper();
			taskSearch.verifyTaskStatus(path, fileName, sheetName, "RollOverTask", 1);
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	@Test(priority=4, description="Verify the LDC Stitched table for the stitched records.", dependsOnMethods = { "verifyRollOverTaskStatus" }, groups = { "run3" })
	public void verifyStitchedTableResults() throws Exception {
		try {
			JumpToSearchHelper jumpToSearch = new JumpToSearchHelper();
			jumpToSearch.verifyJumpToSearchResult(path, fileName, sheetName, "StitchedJ2S", 2);
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	@Test(priority=5, description="Verify the LDC Partial table for the partial records.", dependsOnMethods = { "verifyStitchedTableResults" }, groups = { "run3" })
	public void verifyPartialTableResults() throws Exception {
		try {
			JumpToSearchHelper jumpToSearch = new JumpToSearchHelper();
			jumpToSearch.verifyJumpToSearchResult(path, fileName, sheetName, "PartialJ2S", 2);
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	@Test(priority=6, description="Pause Roll-over partition Stream Stage", dependsOnMethods = { "verifyPartialTableResults" }, groups = { "run3" })
	public void pauseRollOverStreamStage() throws Exception {
		try {
			TaskSearchHelper taskSearch = new TaskSearchHelper();
			taskSearch.pauseStreamStage(path, fileName, sheetName, "PauseStreamStage", 1);
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	@Test(priority=7, description="Stop all the services.", dependsOnMethods = { "pauseRollOverStreamStage" }, groups = { "run3" })
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