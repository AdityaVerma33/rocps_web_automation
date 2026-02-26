package com.subex.automation.testcases.systemtesting.offlineldc;

import org.testng.annotations.Test;

import com.subex.automation.helpers.application.ControllerHelper;
import com.subex.automation.helpers.application.screens.CollectedFilesHelper;
import com.subex.automation.helpers.application.screens.JumpToSearchHelper;
import com.subex.automation.helpers.application.screens.TaskControllerHelper;
import com.subex.automation.helpers.application.screens.TaskSearchHelper;
import com.subex.automation.helpers.component.FileHelper;
import com.subex.automation.helpers.selenium.ROCAcceptanceTest;
import com.subex.automation.helpers.util.FailureHelper;

public class testRun1 extends ROCAcceptanceTest {
	
	private static String path = null;
	private static String dataDir = null;
	final String fileName = "OfflineLDC_TestData.xlsx";
	final String sheetName = "OfflineLDC";
	final String dataLocation = "/src/main/resources/Data/Offline_LDC";
	final String dataFileName = "ldc.dat";
	
	public testRun1() throws Exception {
		try {
			path = automationPath + "\\src\\main\\resources\\System_Test_Flows\\";
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	@Test(priority=1, description="Start controller", dependsOnGroups = { "prerequisite" }, groups = { "run1" })
	public void startSC_TC() throws Exception {
		try {
			//Create output&Input directory for parse
			dataDir = configProp.getDataDirPath();
			FileHelper.createDir(applicationOS, dataDir + "/Input");
			FileHelper.createDir(applicationOS, dataDir + "/Collected Files");
			FileHelper.createDir(applicationOS, dataDir + "/ParseOutput");
			
			FileHelper.cleanUpDir(applicationOS, dataDir + "/Input/Offline_LDC/", true);
			FileHelper.cleanUpDir(applicationOS, dataDir + "/Collected Files/Offline_LDC/", true);
			FileHelper.cleanUpDir(applicationOS, dataDir + "/ParseOutput/Offline_LDC", true);
			
			// Delete all existing files in Input and ParseOutput directory and place input file in Input directory
			FileHelper.deleteFile(applicationOS, dataDir + "/Input/Offline_LDC/*");
			FileHelper.deleteFile(applicationOS, dataDir + "/ParseOutput/Offline_LDC/*");
			
			String dataLocation = automationPath + "\\src\\main\\resources\\Data\\Offline_LDC";
			FileHelper.copyFile(applicationOS, dataLocation, dataDir + "\\Input\\Offline_LDC\\", dataFileName, dataFileName, true);
			
			// Start Controllers
			ControllerHelper controller = new ControllerHelper();
			controller.startStreamController();
			controller.startTaskController(configProp.getTaskControllerExeFile());
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	@Test(priority=2, description="Pause Roll-Over Stream Stage", dependsOnMethods = { "startSC_TC" }, groups = { "run1" })
	public void pauseRollOverStreamStage() throws Exception {
		try {
			TaskSearchHelper taskSearch = new TaskSearchHelper();
			taskSearch.pauseStreamStage(path, fileName, sheetName, "PauseStreamStage", 1);
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	@Test(priority=3, description="Set Task Controller Capability for all the stages", dependsOnMethods = { "pauseRollOverStreamStage" }, groups = { "run1" })
	public void setTaskControllerCapability() throws Exception {
		try {
			TaskControllerHelper taskController = new TaskControllerHelper();
			taskController.setTaskControllerCapability(path, fileName, sheetName, "TCCapability", 1);
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	@Test(priority=4, description="Verifying if the file got collected", dependsOnMethods = { "setTaskControllerCapability" }, groups = { "run1" })
	public void verifyCollectedFiles() throws Exception
	{
		try {
			CollectedFilesHelper collectedFiles = new CollectedFilesHelper();
			collectedFiles.verifyCollectedFiles(path, fileName, sheetName, "CollectedFiles", 1);
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	@Test(priority=5, description="Verifying tasks in Task Search Screen", dependsOnMethods = { "verifyCollectedFiles" }, groups = { "run1" })
	public void verifyETLTask() throws Exception
	{
		try {
			TaskSearchHelper taskSearch = new TaskSearchHelper();
			taskSearch.verifyTaskStatus(path, fileName, sheetName, "ETLTask", 1);
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	@Test(priority=6, description="Verify Result", dependsOnMethods = { "verifyETLTask" }, groups = { "run1" })
	public void verifyJ2SResults() throws Exception {
		try {
			JumpToSearchHelper jumpToSearch = new JumpToSearchHelper();
			jumpToSearch.verifyJumpToSearchResult(path, fileName, sheetName, "J2S", 1);
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
}