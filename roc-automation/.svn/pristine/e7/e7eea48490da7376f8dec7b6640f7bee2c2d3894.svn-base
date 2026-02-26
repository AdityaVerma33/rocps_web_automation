package com.subex.automation.testcases.systemtesting.filesequencecheck;

import org.testng.annotations.Test;

import com.subex.automation.helpers.application.ControllerHelper;
import com.subex.automation.helpers.application.screens.CacheStrategyHelper;
import com.subex.automation.helpers.application.screens.CollectedFilesHelper;
import com.subex.automation.helpers.application.screens.FileSequenceCheckHelper;
import com.subex.automation.helpers.application.screens.TaskControllerHelper;
import com.subex.automation.helpers.application.screens.TaskSearchHelper;
import com.subex.automation.helpers.component.FileHelper;
import com.subex.automation.helpers.selenium.ROCAcceptanceTest;
import com.subex.automation.helpers.util.FailureHelper;

public class testRun extends ROCAcceptanceTest {
	
	private static String path = null;
	static String dataDir = null;
	final String fileName = "FileSequenceCheck_TestData.xlsx";
	final String sheetName = "FileSequenceCheck";
	final String dataFileName = "String_Rule_Set_Data.dat";
	
	public testRun() throws Exception {
		try {
			path = automationPath + "\\src\\main\\resources\\System_Test_Flows\\";
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	@Test(priority=1, description="Start controller", dependsOnGroups = { "prerequisite3" }, groups = { "run" })
	public void startSC_TC() throws Exception
	{
		try {
			//Create output&Input directory for parse
			dataDir = configProp.getDataDirPath();
			FileHelper.createDir(applicationOS, dataDir + "/Input");
			FileHelper.createDir(applicationOS, dataDir + "/Collected Files");
			FileHelper.createDir(applicationOS, dataDir + "/ParseOutput");
			
			FileHelper.cleanUpDir(applicationOS, dataDir + "/Input/StringRuleSet/", true);
			FileHelper.cleanUpDir(applicationOS, dataDir + "/Collected Files/StringRuleSet/", true);
			FileHelper.cleanUpDir(applicationOS, dataDir + "/ParseOutput/StringRuleSet", true);
			
			// Delete all existing files in Input and ParseOutput directory and place input file in Input directory
			FileHelper.deleteFile(applicationOS, dataDir + "/Input/StringRuleSet/*");
			FileHelper.deleteFile(applicationOS, dataDir + "/ParseOutput/StringRuleSet/*");
			
			String dataLocation = automationPath + "\\src\\main\\resources\\Data\\StringRuleSet";
			FileHelper.copyFile(applicationOS, dataLocation, dataDir + "\\Input\\StringRuleSet\\", dataFileName, "String_Rule_Set_Data001.dat", true);
			
			// Start Controllers
			ControllerHelper controller = new ControllerHelper();
			controller.startStreamController();
			controller.startTaskController(configProp.getTaskControllerExeFile());
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	@Test(priority=2, description="Set Task Controller Capability for all the stages", dependsOnMethods = { "startSC_TC" }, groups = { "run" })
	public void setTaskControllerCapability() throws Exception {
		try {
			TaskControllerHelper taskController = new TaskControllerHelper();
			taskController.setTaskControllerCapability(path, fileName, sheetName, "TCCapability", 1);
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	@Test(priority=3, description="Verifying if the file got collected", dependsOnMethods = { "setTaskControllerCapability" }, groups = { "run" })
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
	
	@Test(priority=4, description="Verifying tasks in Task Search Screen", dependsOnMethods = { "verifyCollectedFiles" }, groups = { "run" })
	public void verifyTask() throws Exception
	{
		try {
			TaskSearchHelper taskSearch = new TaskSearchHelper();
			taskSearch.verifyTaskCount(path, fileName, sheetName, "Task", 1);
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	@Test(priority=5, description="Update Cache Strategy to On Demand", dependsOnMethods = { "verifyTask" }, groups = { "run" })
	public void updateCacheStrategy() throws Exception
	{
		try {
			CacheStrategyHelper cacheStrategy = new CacheStrategyHelper();
			cacheStrategy.updateStrategy(path, fileName, sheetName, "CacheStrategy", 1);
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	@Test(priority=6, description="Reinitialize Task Controller", dependsOnMethods = { "updateCacheStrategy" }, groups = { "run" })
	public void reinitializeTaskController() throws Exception
	{
		try {
			TaskSearchHelper taskSearch = new TaskSearchHelper();
			taskSearch.reinitializeTaskController("Task Controller");
			
			String dataLocation = automationPath + "\\src\\main\\resources\\Data\\StringRuleSet";
			FileHelper.copyFile(applicationOS, dataLocation, dataDir + "\\Input\\StringRuleSet\\", dataFileName, "String_Rule_Set_Data004.dat", true);
			Thread.sleep(60000);
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	@Test(priority=7, description="Verifying if the file got collected", dependsOnMethods = { "reinitializeTaskController" }, groups = { "run" })
	public void verifyCollectedFile() throws Exception
	{
		try {
			CollectedFilesHelper collectedFiles = new CollectedFilesHelper();
			collectedFiles.verifyCollectedFiles(path, fileName, sheetName, "CollectedFiles", 2);
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	@Test(priority=8, description="Verifying tasks in Task Search Screen", dependsOnMethods = { "verifyCollectedFile" }, groups = { "run" })
	public void verifyETLTask() throws Exception
	{
		try {
			TaskSearchHelper taskSearch = new TaskSearchHelper();
			taskSearch.verifyTaskCount(path, fileName, sheetName, "Task", 2);
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	@Test(priority=9, description="Schedule File Sequence Check", dependsOnMethods = { "verifyETLTask" }, groups = { "run" })
	public void scheduleFileSequenceCheck() throws Exception
	{
		try {
			FileSequenceCheckHelper fileSequenceCheck = new FileSequenceCheckHelper();
			fileSequenceCheck.scheduleFileSequenceCheck(path, fileName, sheetName, "ScheduleFSC", 1);
			fileSequenceCheck.verifyFileSequenceCheckStatus(path, fileName, sheetName, "ScheduleFSC", 1);
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
}