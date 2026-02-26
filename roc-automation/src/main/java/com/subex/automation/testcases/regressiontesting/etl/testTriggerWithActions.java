package com.subex.automation.testcases.regressiontesting.etl;

import org.testng.annotations.Test;

import com.subex.automation.helpers.application.ControllerHelper;
import com.subex.automation.helpers.application.screens.TaskSearchHelper;
import com.subex.automation.helpers.application.screens.TriggerHelper;
import com.subex.automation.helpers.component.FileHelper;
import com.subex.automation.helpers.selenium.ROCAcceptanceTest;
import com.subex.automation.helpers.util.FailureHelper;

public class testTriggerWithActions extends ROCAcceptanceTest {
	
	private static String path = null;
	final String fileName = "ETLRegression_TestData.xlsx";
	final String sheetName = "TriggerWithActions";
	
	final String dataLocation = "\\src\\main\\resources\\Data\\ASCII";
	final String folderName = "Reference1";
	final String dataFileName = "Trigger_Sample1.dat";
	
	public testTriggerWithActions() throws Exception {
		try {
			path = automationPath + "\\src\\main\\resources\\Regression\\";
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	@Test(priority=1, description="File Collection for Manual File Size Check")
	public void testTrigger()throws Exception
	{
		try {
			String dataDir = configProp.getDataDirPath();
			String inputFolder = dataDir + "/Input/" + folderName;
			String collectedFilesFolder = dataDir + "/Collected Files/" + folderName;
			FileHelper.cleanUpDir(applicationOS, inputFolder, true);
			FileHelper.cleanUpDir(applicationOS, collectedFilesFolder, true);
			
			TriggerHelper trigger = new TriggerHelper();
			trigger.createTrigger(path, fileName, sheetName, "Trigger", 1);
			
			// Start Controllers
			ControllerHelper controller = new ControllerHelper();
			controller.startStreamController();
			controller.startTaskController(configProp.getTaskControllerExeFile());
			FileHelper.copyFile(applicationOS, automationPath + dataLocation, inputFolder, "Data_Load_Sample1.dat", dataFileName, true);
			
			TaskSearchHelper taskSearch = new TaskSearchHelper();
			taskSearch.verifyTaskCount(path, fileName, sheetName, "ETLTaskCount", 1);
			assertTrue(FileHelper.checkFileExists(inputFolder + "/" + dataFileName),
					"File '" + dataFileName + "' is not found in Input folder '" + inputFolder + "'.");
			assertTrue(FileHelper.checkFileExists(collectedFilesFolder + "/" + dataFileName),
					"File '" + dataFileName + "' is not found in Collected Files folder '" + collectedFilesFolder + "'.");
		} catch (AssertionError e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		} finally {
			ControllerHelper controller = new ControllerHelper();
			controller.stopTaskController("Task Controller");
			controller.stopStreamController();
			
			TriggerHelper trigger = new TriggerHelper();
			trigger.deleteTrigger(path, fileName, sheetName, "Trigger", 1);
		}
	}
}