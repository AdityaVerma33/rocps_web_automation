package com.subex.automation.testcases.regressiontesting.duplicatecheck;

import org.testng.annotations.Test;

import com.subex.automation.helpers.application.ControllerHelper;
import com.subex.automation.helpers.application.screens.AlertsHelper;
import com.subex.automation.helpers.application.screens.DuplicateXDRHelper;
import com.subex.automation.helpers.application.screens.EntityImportHelper;
import com.subex.automation.helpers.application.screens.JumpToSearchHelper;
import com.subex.automation.helpers.application.screens.RecurringTaskHelper;
import com.subex.automation.helpers.application.screens.StreamControllerHelper;
import com.subex.automation.helpers.application.screens.StreamHelper;
import com.subex.automation.helpers.application.screens.TableInstanceHelper;
import com.subex.automation.helpers.application.screens.TaskControllerHelper;
import com.subex.automation.helpers.application.screens.TaskSearchHelper;
import com.subex.automation.helpers.component.FileHelper;
import com.subex.automation.helpers.selenium.ROCAcceptanceTest;
import com.subex.automation.helpers.util.FailureHelper;

public class testWithoutTagStatus extends ROCAcceptanceTest {
	
	static String path = null;
	static String dataDir = null;
	final String fileName = "DuplicateCheck_TestData.xlsx";
	final String sheetName = "WithoutTagStatus";
	final String dataLocation = "\\src\\main\\resources\\Data\\Duplicate_XDR";
	final String dataFileName = "Duplicate1.dat";
	final String alertNo = "3,013";
	
	public testWithoutTagStatus() throws Exception {
		try {
			path = automationPath + "\\src\\main\\resources\\Regression\\";
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	// Verify if Duplicate Check happens without selecting "Tag Status" option
	// Verify if Duplicate Check happens without selecting "Enable Hour Part Key Field" option
	// Verify if Duplicate Check happens without selecting "Generate Alert" option
	// Verify if Duplicate Check happens with single "Match Duplicates On Fields"
	@Test(priority=1, description="Configure Duplicate XDR Check", groups = { "withoutTagStatus" })
	public void testWithoutTagCase1() throws Exception
	{
		try {
			ControllerHelper controller = new ControllerHelper();
			controller.stopServices();
			
			dataDir = configProp.getDataDirPath();
			String destinationDirectory = dataDir + "/Diamond_XMLs/";
			FileHelper.createDir(applicationOS, destinationDirectory);
			FileHelper.deleteFile(applicationOS, destinationDirectory + "Duplicate_Ref.xml");
			FileHelper.copyFile(applicationOS, automationPath + dataLocation, destinationDirectory, "Duplicate_CDR.xml", "Duplicate_Ref.xml", true);
			FileHelper.copyFile(applicationOS, automationPath + dataLocation, destinationDirectory, "Duplicate_CDR.xml", "Duplicate_CDR.xml", false);
			
			EntityImportHelper entityImport = new EntityImportHelper();
			entityImport.createEntityImport(path, fileName, sheetName, "EntityImport", 1);
			
			TableInstanceHelper tableInstance = new TableInstanceHelper();
			tableInstance.truncateTable(path, fileName, sheetName, "TableInstance", 1);
			
			DuplicateXDRHelper duplicateCheck = new DuplicateXDRHelper();
			duplicateCheck.createDuplicateXDR(path, fileName, sheetName, "DuplicateXDRCheck", 1);
			
			StreamHelper stream = new StreamHelper();
			stream.createStream(path, fileName, sheetName, "Stream", 1);
			
			StreamControllerHelper streamController = new StreamControllerHelper();
			streamController.createStreamController(path, fileName, sheetName, "StreamController", 1);
			
			TaskControllerHelper taskController = new TaskControllerHelper();
			taskController.createTaskController(path, fileName, sheetName, "TaskController", 1);
			
			RecurringTaskHelper recurringTask = new RecurringTaskHelper();
			recurringTask.createRecurringTask(path, fileName, sheetName, "RecurringTask", 1);
			
			FileHelper.createDir(applicationOS, dataDir + "/Input");
			FileHelper.createDir(applicationOS, dataDir + "/Collected Files");
			FileHelper.createDir(applicationOS, dataDir + "/ParseOutput");
			
			FileHelper.cleanUpDir(applicationOS, dataDir + "/Input/Duplicate_Check1/", true);
			FileHelper.cleanUpDir(applicationOS, dataDir + "/Collected Files/Duplicate_Check1/", true);
			FileHelper.cleanUpDir(applicationOS, dataDir + "/ParseOutput/Duplicate_Check1", true);
			
			FileHelper.copyFile(applicationOS, automationPath + dataLocation, dataDir + "\\Input\\Duplicate_Check1\\", dataFileName, dataFileName, true);
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	@Test(priority=2, description="Schedule Duplicate XDR Check", dependsOnMethods = { "testWithoutTagCase1" }, groups = { "withoutTagStatus" })
	public void testWithoutTagCase2() throws Exception
	{
		try {
			// Start Controllers
			ControllerHelper controller = new ControllerHelper();
			controller.startStreamController();
			controller.startTaskController(configProp.getTaskControllerExeFile());
			
			TaskControllerHelper taskController = new TaskControllerHelper();
			taskController.setTaskControllerCapability(path, fileName, sheetName, "TCCapability", 1);
			
			TaskSearchHelper taskSearch = new TaskSearchHelper();
			taskSearch.verifyTaskStatus(path, fileName, sheetName, "Task", 1);
			
			AlertsHelper alerts = new AlertsHelper();
			alerts.verifyAlertInstance("Task", "Today", null, null, alertNo, null, null, 0);
			
			JumpToSearchHelper jumpToSearch = new JumpToSearchHelper();
			jumpToSearch.verifyJumpToSearchResult(path, fileName, sheetName, "J2S", 1);
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		} finally {
			ControllerHelper controller = new ControllerHelper();
			controller.stopTaskController("Task Controller");
			controller.stopStreamController();
		}
	}
}