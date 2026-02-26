package com.subex.automation.testcases.systemtesting.duplicatecheck;

import org.testng.annotations.Test;

import com.subex.automation.helpers.application.ControllerHelper;
import com.subex.automation.helpers.application.screens.CollectedFilesHelper;
import com.subex.automation.helpers.application.screens.TaskControllerHelper;
import com.subex.automation.helpers.application.screens.TaskSearchHelper;
import com.subex.automation.helpers.component.FileHelper;
import com.subex.automation.helpers.selenium.ROCAcceptanceTest;
import com.subex.automation.helpers.util.FailureHelper;

public class testRun extends ROCAcceptanceTest {
	static String path = null;
	static String dataDir = null;
	final String fileName = "DuplicateCheckFlow_TestData.xlsx";
	final String dataFileName = "Duplicate1.dat";
	
	public testRun() throws Exception {
		try {
			path = automationPath + "\\src\\main\\resources\\System_Test_Flows\\";
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	@Test(priority=1, description="Start controller", dependsOnGroups = { "prerequisite2" }, groups = { "run1" })
	public void startSC_TC() throws Exception {
		try {
			//Create output&Input directory for parse
			dataDir = configProp.getDataDirPath();
			FileHelper.createDir(applicationOS, dataDir + "/Input");
			FileHelper.createDir(applicationOS, dataDir + "/Collected Files");
			FileHelper.createDir(applicationOS, dataDir + "/ParseOutput");
			
			FileHelper.cleanUpDir(applicationOS, dataDir + "/Input/Duplicate_Flow/", true);
			FileHelper.cleanUpDir(applicationOS, dataDir + "/Collected Files/Duplicate_Flow/", true);
			FileHelper.cleanUpDir(applicationOS, dataDir + "/ParseOutput/Duplicate_Flow", true);
			
			// Delete all existing files in Input and ParseOutput directory and place input file in Input directory
			FileHelper.deleteFile(applicationOS, dataDir + "/Input/Duplicate_Flow/*");
			FileHelper.deleteFile(applicationOS, dataDir + "/ParseOutput/Duplicate_Flow/*");
			
			String dataLocation = automationPath + "\\src\\main\\resources\\Data\\Duplicate_XDR";
			FileHelper.copyFile(applicationOS, dataLocation, dataDir + "\\Input\\Duplicate_Flow\\", dataFileName, dataFileName, true);
			
			// Start Controllers
			ControllerHelper controller = new ControllerHelper();
			controller.startStreamController();
			controller.startTaskController(configProp.getTaskControllerExeFile());
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	@Test(priority=2, description="Set Task Controller Capability for all the 3 stages", dependsOnMethods = { "startSC_TC" }, groups = { "run1" })
	public void setTaskControllerCapability() throws Exception {
		try {
			TaskControllerHelper taskController = new TaskControllerHelper();
			taskController.setTaskControllerCapability(path, fileName, "DuplicateCheck", "TCCapability", 1);
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	@Test(priority=3, description="Pause Parse stage in Task Search", dependsOnMethods = { "setTaskControllerCapability" }, groups = { "run1" })
	public void pauseParseStreamStage() throws Exception {
		try {
			TaskSearchHelper taskSearch = new TaskSearchHelper();
			taskSearch.pauseStreamStage("Duplicate Check Flow - Stream", "DC Parse");
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	@Test(priority=4, description="Process Pending file in Collected Files", dependsOnMethods = { "pauseParseStreamStage" }, groups = { "run1" })
	public void processCollectedFile() throws Exception {
		try {
			CollectedFilesHelper collectedFiles = new CollectedFilesHelper();
			collectedFiles.processCollectedFile(dataFileName, 10);
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	@Test(priority=5, description="Cancel Parse Task", dependsOnMethods = { "processCollectedFile" }, groups = { "run1" })
	public void cancelParseTask() throws Exception {
		try {
			TaskSearchHelper taskSearch = new TaskSearchHelper();
			taskSearch.cancelTask("Duplicate Check Flow - Stream", "DC Parse", "Last hour", "Waiting", 60, null);
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	@Test(priority=6, description="Resume Parse Stream Stage", dependsOnMethods = { "cancelParseTask" }, groups = { "run1" })
	public void resumeParseStreamStage() throws Exception {
		try {
			TaskSearchHelper taskSearch = new TaskSearchHelper();
			taskSearch.resumeStreamStage("Duplicate Check Flow - Stream", "DC Parse");
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	@Test(priority=7, description="Restart Parse Task", dependsOnMethods = { "resumeParseStreamStage" }, groups = { "run1" })
	public void restartParseTask() throws Exception {
		try {
			TaskSearchHelper taskSearch = new TaskSearchHelper();
			taskSearch.restartTask("Duplicate Check Flow - Stream", "DC Parse", "Last hour", "Cancelled", "Completed", 60, null);
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	@Test(priority=8, description="Schedule Tag Duplicates", dependsOnMethods = { "restartParseTask" }, groups = { "run1" })
	public void scheduleTagDuplicatesTask() throws Exception {
		try {
			TaskSearchHelper taskSearch = new TaskSearchHelper();
			taskSearch.scheduleTask("Duplicate Check Flow - Stream", "DC Tag Duplicates", "Last day", null, "Completed", 60, null);
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	@Test(priority=9, description="Stop Services", dependsOnMethods = { "scheduleTagDuplicatesTask" }, groups = { "run1" })
	public void stopSC_TC() throws Exception {
		try {
			ControllerHelper controller = new ControllerHelper();
			controller.stopTaskController("Task Controller");
			controller.stopStreamController();
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
}