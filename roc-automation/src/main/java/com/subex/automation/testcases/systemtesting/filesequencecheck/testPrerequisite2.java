package com.subex.automation.testcases.systemtesting.filesequencecheck;

import org.testng.annotations.Test;

import com.subex.automation.helpers.application.screens.FileCollectionHelper;
import com.subex.automation.helpers.application.screens.FileSequenceCheckHelper;
import com.subex.automation.helpers.application.screens.FileSourceHelper;
import com.subex.automation.helpers.application.screens.RecurringTaskHelper;
import com.subex.automation.helpers.application.screens.StreamControllerHelper;
import com.subex.automation.helpers.application.screens.StreamHelper;
import com.subex.automation.helpers.application.screens.StringRuleSetHelper;
import com.subex.automation.helpers.application.screens.TaskControllerHelper;
import com.subex.automation.helpers.application.screens.TrendSummaryTableHelper;
import com.subex.automation.helpers.application.screens.TriggerHelper;
import com.subex.automation.helpers.component.FileHelper;
import com.subex.automation.helpers.selenium.ROCAcceptanceTest;
import com.subex.automation.helpers.util.FailureHelper;

public class testPrerequisite2 extends ROCAcceptanceTest {
	
	private static String path = null;
	final String fileName = "FileSequenceCheck_TestData.xlsx";
	final String sheetName = "FileSequenceCheck";
	final String dataLocation = "\\src\\main\\resources\\Data";
	
	public testPrerequisite2() throws Exception {
		try {
			path = automationPath + "\\src\\main\\resources\\System_Test_Flows\\";
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	@Test(priority=1, description="Create String Rule Set", dependsOnGroups = { "prerequisite1" }, groups = { "prerequisite2" })
	public void createStringRuleSet() throws Exception {
		try {
			StringRuleSetHelper stringRuleSet = new StringRuleSetHelper();
			stringRuleSet.createStringRuleSet(path, fileName, sheetName, "StringRuleSet", 1);
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	@Test(priority=2, description="Create Stream", dependsOnMethods = { "createStringRuleSet" }, groups = { "prerequisite2" })
	public void createStream() throws Exception {
		try {
			String destinationDirectory = configProp.getDataDirPath() + "/Diamond_XMLs/";
			FileHelper.createDir(applicationOS, destinationDirectory);
			FileHelper.deleteFile(applicationOS, destinationDirectory + "String_Rule_Set.xml");
			FileHelper.copyFile(applicationOS, automationPath + dataLocation, destinationDirectory, "String_Rule_Set.xml", "String_Rule_Set.xml", true);
			
			StreamHelper stream = new StreamHelper();
			stream.createStream(path, fileName, sheetName, "Stream", 2);
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	@Test(priority=3, description="Create Stream Controller", dependsOnMethods = { "createStream" }, groups = { "prerequisite2" })
	public void createStreamController() throws Exception {
		try {
			StreamControllerHelper streamController = new StreamControllerHelper();
			streamController.createStreamController(path, fileName, sheetName, "StreamController", 1);
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	@Test(priority=4, description="Create Task Controller", dependsOnMethods = { "createStreamController" }, groups = { "prerequisite2" })
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
	
	@Test(priority=8, description="Create Stream", dependsOnMethods = { "createTrigger" }, groups = { "prerequisite2" })
	public void createFileSequenceCheck() throws Exception {
		try {
			FileSequenceCheckHelper fileSequenceCheck = new FileSequenceCheckHelper();
			fileSequenceCheck.createFileSequenceCheck(path, fileName, sheetName, "FileSequenceCheck", 1);
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	@Test(priority=9, description="Create Recurring Task", dependsOnMethods = { "createFileSequenceCheck" }, groups = { "prerequisite2" })
	public void createRecurringTask() throws Exception {
		try {
			RecurringTaskHelper recurringTask = new RecurringTaskHelper();
			recurringTask.createRecurringTask(path, fileName, sheetName, "RecurringTask", 1);
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	@Test(priority=10, description="Create Trend Summary", dependsOnMethods = { "createRecurringTask" }, groups = { "prerequisite2" })
	public void createTrendSummaryTable() throws Exception {
		try {
			TrendSummaryTableHelper trendSummary = new TrendSummaryTableHelper();
			trendSummary.createTrendSummaryTable(path, fileName, sheetName, "TrendSummaryTable", 1);
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
}