package com.subex.automation.testcases.systemtesting.duplicatecheck;

import org.testng.annotations.Test;

import com.subex.automation.helpers.application.screens.DuplicateXDRHelper;
import com.subex.automation.helpers.application.screens.FileCollectionHelper;
import com.subex.automation.helpers.application.screens.FileSourceHelper;
import com.subex.automation.helpers.application.screens.RecurringTaskHelper;
import com.subex.automation.helpers.application.screens.StreamControllerHelper;
import com.subex.automation.helpers.application.screens.StreamHelper;
import com.subex.automation.helpers.application.screens.TaskControllerHelper;
import com.subex.automation.helpers.application.screens.TriggerHelper;
import com.subex.automation.helpers.component.FileHelper;
import com.subex.automation.helpers.selenium.ROCAcceptanceTest;
import com.subex.automation.helpers.util.FailureHelper;

public class testPrerequisite2 extends ROCAcceptanceTest {
	
	String path = null;
	private static String dataDir = null;
	final String fileName = "DuplicateCheckFlow_TestData.xlsx";
	final String sheetName = "DuplicateCheck";
	final String dataLocation = "\\src\\main\\resources\\Data\\Duplicate_XDR";
	
	public testPrerequisite2() throws Exception {
		try {
			path = automationPath + "\\src\\main\\resources\\System_Test_Flows\\";
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	@Test(priority=1, description="Create Duplicate XDR Check", dependsOnGroups = { "prerequisite1" }, groups = { "prerequisite2" })
	public void createDuplicateXDRCheck() throws Exception {
		try {
			DuplicateXDRHelper duplicateXDR = new DuplicateXDRHelper();
			duplicateXDR.createDuplicateXDR(path, fileName, sheetName, "DuplicateXDRCheck", 1);
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	@Test(priority=2, description="Create Stream", dependsOnMethods = { "createDuplicateXDRCheck" }, groups = { "prerequisite2" })
	public void createStream() throws Exception {
		try {
			dataDir = configProp.getDataDirPath();
			String destinationDirectory = dataDir + "/Diamond_XMLs/";
			FileHelper.createDir(applicationOS, destinationDirectory);
			FileHelper.createDir(applicationOS, destinationDirectory);
			FileHelper.deleteFile(applicationOS, destinationDirectory + "Duplicate_CDR.xml");
			FileHelper.copyFile(applicationOS, automationPath + dataLocation, destinationDirectory, "Duplicate_CDR.xml", "Duplicate_CDR.xml", true);
			
			StreamHelper stream = new StreamHelper();
			stream.createStream(path, fileName, sheetName, "Stream", 1);
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	@Test(priority=3, description="Configure SC", dependsOnMethods = { "createStream" }, groups = { "prerequisite2" })
	public void createStreamController() throws Exception {
		try {
			StreamControllerHelper streamController = new StreamControllerHelper();
			streamController.createStreamController(path, fileName, sheetName, "StreamController", 1);
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	@Test(priority=4, description="Configure TC", dependsOnMethods = { "createStreamController" }, groups = { "prerequisite2" })
	public void createTaskController() throws Exception {
		try {
			TaskControllerHelper taskController = new TaskControllerHelper();
			taskController.createTaskController(path, fileName, sheetName, "TaskController", 1);
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	@Test(priority=5, description="Create File Source", dependsOnMethods = { "createTaskController" }, groups = { "prerequisite2" })
	public void createFileSource() throws Exception {
		try {
			FileSourceHelper fileSource = new FileSourceHelper();
			fileSource.createFileSource(path, fileName, sheetName, "FileSource", 1);
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	@Test(priority=6, description="Create File Collection", dependsOnMethods = { "createFileSource" }, groups = { "prerequisite2" })
	public void createFileCollection() throws Exception {
		try {
			FileCollectionHelper fileCollection = new FileCollectionHelper();
			fileCollection.createFileCollection(path, fileName, sheetName, "FileCollection", 1);
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	@Test(priority=7, description="Create Trigger", dependsOnMethods = { "createFileCollection" }, groups = { "prerequisite2" })
	public void createTrigger() throws Exception {
		try {
			TriggerHelper trigger = new TriggerHelper();
			trigger.createTrigger(path, fileName, sheetName, "Trigger", 1);
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	@Test(priority=8, description="Create Recurring Task", dependsOnMethods = { "createTrigger" }, groups = { "prerequisite2" })
	public void createRecurringTask() throws Exception {
		try {
			RecurringTaskHelper recurringTask = new RecurringTaskHelper();
			recurringTask.createRecurringTask(path, fileName, sheetName, "RecurringTask", 1);
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
}