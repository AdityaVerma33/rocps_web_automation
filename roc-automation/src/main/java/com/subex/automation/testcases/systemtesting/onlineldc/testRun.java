package com.subex.automation.testcases.systemtesting.onlineldc;

import org.testng.annotations.Test;

import com.subex.automation.helpers.application.ControllerHelper;
import com.subex.automation.helpers.application.screens.CollectedFilesHelper;
import com.subex.automation.helpers.application.screens.TaskControllerHelper;
import com.subex.automation.helpers.application.screens.TaskSearchHelper;
import com.subex.automation.helpers.component.FileHelper;
import com.subex.automation.helpers.selenium.ROCAcceptanceTest;
import com.subex.automation.helpers.util.FailureHelper;

public class testRun extends ROCAcceptanceTest {
	
	private static String path = null;
	static String dataDir = null;
	final String fileName = "OnlineLDC_TestData.xlsx";
	final String sheetName = "OnlineLDC";
	final String dataLocation = "/src/main/resources/Data/Online_LDC";
	final String dataFileName = "OnlineLDC_01.txt";
	
	public testRun() throws Exception {
		try {
			path = automationPath + "\\src\\main\\resources\\System_Test_Flows\\";
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	@Test(priority=1, description="Start SC and TC", dependsOnGroups={"prerequisite"}, groups = { "run" })
	public void startSC_TC() throws Exception {
		try {
			ControllerHelper controller = new ControllerHelper();
			controller.startStreamController();
			controller.startTaskController(configProp.getTaskControllerExeFile());
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	@Test(priority=2, description="Pause Online LDC Correlator stage in Task Search", dependsOnMethods={"startSC_TC"}, groups = { "run" })
	public void pauseStreamStage() throws Exception {
		try {
			TaskSearchHelper taskSearch = new TaskSearchHelper();
			taskSearch.pauseStreamStage(path, fileName, sheetName, "PauseStreamStage", 1);
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	@Test(priority=3, description="Set Task Controller Capability", dependsOnMethods={"pauseStreamStage"}, groups = { "run" })
	public void setTaskControllerCapability() throws Exception {
		try {
			//Create output&Input directory for parse
			dataDir = configProp.getDataDirPath();
			FileHelper.createDir(applicationOS, dataDir + "/Input");
			FileHelper.createDir(applicationOS, dataDir + "/Collected Files");
			FileHelper.createDir(applicationOS, dataDir + "/ParseOutput");
			FileHelper.createDir(applicationOS, dataDir + "/Online_LDC");
			
			FileHelper.cleanUpDir(applicationOS, dataDir + "/Online_LDC/", true);
			FileHelper.cleanUpDir(applicationOS, dataDir + "/Input/Online_LDC/", true);
			FileHelper.cleanUpDir(applicationOS, dataDir + "/Collected Files/Online_LDC/", true);
			FileHelper.cleanUpDir(applicationOS, dataDir + "/ParseOutput/Online_LDC", true);
			
			// Delete all existing files in Input and ParseOutput directory and place input file in Input directory
			FileHelper.deleteFile(applicationOS, dataDir + "/Online_LDC/*");
			FileHelper.deleteFile(applicationOS, dataDir + "/Input/Online_LDC/*");
			FileHelper.deleteFile(applicationOS, dataDir + "/ParseOutput/Online_LDC/*");
			
			FileHelper.copyFile(applicationOS, automationPath + dataLocation, dataDir + "/Input/Online_LDC", dataFileName, dataFileName, true);
			
			TaskControllerHelper taskController = new TaskControllerHelper();
			taskController.setTaskControllerCapability(path, fileName, sheetName, "TCCapability", 1);
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}

	@Test(priority=4, description="Verifying if the file got collected", dependsOnMethods={"setTaskControllerCapability"}, groups = { "run" })
	public void verifyCollectedFiles() throws Exception {
		try {
			CollectedFilesHelper collectedFiles = new CollectedFilesHelper();
			collectedFiles.verifyCollectedFiles(path, fileName, sheetName, "VerifyCollectedFile", 1);
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}

	@Test(priority=5, description="Resume Online LDC Correlator", dependsOnMethods={"verifyCollectedFiles"}, groups = { "run" })
	public void resumeStreamStage() throws Exception {
		try {
			TaskSearchHelper taskSearch = new TaskSearchHelper();
			taskSearch.resumeStreamStage(path, fileName, sheetName, "PauseStreamStage", 1);
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	@Test(priority=6, description="Verifying tasks in Task Search Screen", dependsOnMethods={"resumeStreamStage"}, groups = { "run" })
	public void verifyTask() throws Exception {
		try {
			TaskSearchHelper taskSearch = new TaskSearchHelper();
			taskSearch.verifyTaskStatus(path, fileName, sheetName, "VerifyTask", 1);
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
}