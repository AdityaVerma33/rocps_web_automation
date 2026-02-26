package com.subex.automation.testcases.systemtesting.tariff;

import org.testng.annotations.Test;

import com.subex.automation.helpers.application.ControllerHelper;
import com.subex.automation.helpers.application.screens.CollectedFilesHelper;
import com.subex.automation.helpers.application.screens.JumpToSearchHelper;
import com.subex.automation.helpers.application.screens.TaskControllerHelper;
import com.subex.automation.helpers.application.screens.TaskSearchHelper;
import com.subex.automation.helpers.component.FileHelper;
import com.subex.automation.helpers.selenium.ROCAcceptanceTest;
import com.subex.automation.helpers.util.FailureHelper;

public class testRun extends ROCAcceptanceTest{
	
	private static String path = null;
	final String fileName = "Tariff_TestData.xlsx";
	final String sheetName = "Tariff";
	
	private static String dataDir = null;
	final String folderName = "Tariff";
	final String dataLocation = "\\src\\main\\resources\\Data\\Tariff";
	final String dataFileName = "tariff_cdr.txt";
	
	public testRun() throws Exception {
		try {
			path = automationPath + "\\src\\main\\resources\\System_Test_Flows\\";
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	@Test(priority=22, description="Starting Stream Controller and Task Controller", dependsOnGroups = { "prerequisite2" }, groups = { "run" })
	public void StartSCTC()throws Exception {
		try {
			//Create output&Input directory for parse
			dataDir = configProp.getDataDirPath();
			FileHelper.createDir(applicationOS, dataDir + "/Input");
			FileHelper.createDir(applicationOS, dataDir + "/Collected Files");
			FileHelper.createDir(applicationOS, dataDir + "/ParseOutput");
			
			FileHelper.cleanUpDir(applicationOS, dataDir + "/Input/" + folderName, true);
			FileHelper.cleanUpDir(applicationOS, dataDir + "/Collected Files/" + folderName, true);
			FileHelper.cleanUpDir(applicationOS, dataDir + "/ParseOutput/" + folderName, true);
			
			ControllerHelper controller = new ControllerHelper();
			controller.startStreamController();
			controller.startTaskController(configProp.getTaskControllerExeFile());
			
			FileHelper.copyFile(applicationOS, automationPath + dataLocation, dataDir + "/Input/" + folderName, dataFileName, dataFileName, true);
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	@Test(priority=23, description="Set Task Controller Capability for all the stages", dependsOnMethods = { "StartSCTC" }, groups = { "run" })
	public void setTaskControllerCapability()throws Exception {
		try {
			TaskControllerHelper taskController = new TaskControllerHelper();
			taskController.setTaskControllerCapability(path, fileName, sheetName, "TCCapability", 1);
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	@Test(priority=24, description="Verifying if the file got collected", dependsOnMethods = { "setTaskControllerCapability" }, groups = { "run" })
	public void verifyCollectedFiles()throws Exception {
		try {
			CollectedFilesHelper collectedFiles = new CollectedFilesHelper();
			collectedFiles.verifyCollectedFiles(path, fileName, sheetName, "CollectedFile", 1);
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	@Test(priority=25, description="Verifying tasks in Task Search Screen", dependsOnMethods = { "verifyCollectedFiles" }, groups = { "run" })
	public void verifyTaskStatus()throws Exception {
		try {
			TaskSearchHelper taskSearch = new TaskSearchHelper();
			taskSearch.verifyTaskStatus(path, fileName, sheetName, "ETLTask", 1);
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	@Test(priority=26, description="Stop Services", dependsOnMethods = { "verifyTaskStatus" }, groups = { "run" })
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
	
	@Test(priority=27, description="Verify result", dependsOnMethods = { "stopSC_TC" }, groups = { "run" })
	public void verifyJ2SResult() throws Exception {
		try {
			JumpToSearchHelper jumpToSearch = new JumpToSearchHelper();
			jumpToSearch.verifyJumpToSearchResult(path, fileName, sheetName, "J2S", 1);
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
}