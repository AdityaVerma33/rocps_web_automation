package com.subex.automation.testcases.systemtesting.audits1;

import org.testng.annotations.Test;

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
	
	private static String path = null;
	private static String dataDir = null;
	final String dataLocation = "/src/main/resources/Data/Audit_Flow";
	final String fileName = "AuditFlow1_TestData.xlsx";
	final String sheetName = "AuditFlow1";
	
	public testPrerequisite2() throws Exception {
		try {
			path = automationPath + "\\src\\main\\resources\\System_Test_Flows\\";
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	@Test(priority=1, description="Create Input, Collected Files and ParseOutput directories for parse task", groups = { "prerequisite2" }, dependsOnGroups = { "prerequisite1" })
	public void dataDirCleanup() throws Exception {
		try {
			//Make Input, Collected Files and ParseOutput directories and empty them if any file is already present
			dataDir = configProp.getDataDirPath();
			FileHelper.createDir(applicationOS, dataDir + "/Input");
			FileHelper.createDir(applicationOS, dataDir + "/Collected Files");
			FileHelper.createDir(applicationOS, dataDir + "/ParseOutput");
			
			FileHelper.cleanUpDir(applicationOS, dataDir + "/Input", true);
			FileHelper.cleanUpDir(applicationOS, dataDir + "/Input/Audit_Flow1", true);
			FileHelper.cleanUpDir(applicationOS, dataDir + "/Input/Audit_Flow1/Reference", true);
			FileHelper.cleanUpDir(applicationOS, dataDir + "/Input/Audit_Flow1/Reference1", true);
			FileHelper.cleanUpDir(applicationOS, dataDir + "/Input/Audit_Flow1/Usage1", true);
			FileHelper.cleanUpDir(applicationOS, dataDir + "/Input/Audit_Flow1/Usage2", true);
			FileHelper.cleanUpDir(applicationOS, dataDir + "/Input/Audit_Flow1/Usage3", true);
			FileHelper.cleanUpDir(applicationOS, dataDir + "/Input/Audit_Flow1/Usage4", true);
			
			FileHelper.cleanUpDir(applicationOS, dataDir + "/Collected Files/Audit_Flow1", true);
			FileHelper.cleanUpDir(applicationOS, dataDir + "/ParseOutput/Audit_Flow1", true);
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	@Test(priority=2, description="Create Streams", dependsOnMethods = { "dataDirCleanup" }, groups = { "prerequisite2" })
	public void createStream() throws Exception {
		try {
			dataDir = configProp.getDataDirPath();
			String destinationDirectory = dataDir + "/Diamond_XMLs/";
			FileHelper.createDir(applicationOS, destinationDirectory);
			FileHelper.deleteFile(applicationOS, destinationDirectory + "Audits_Join_Usage1.xml");
			FileHelper.deleteFile(applicationOS, destinationDirectory + "Audits_Join_Usage2.xml");
			FileHelper.deleteFile(applicationOS, destinationDirectory + "Audits_Join_Usage3.xml");
			FileHelper.deleteFile(applicationOS, destinationDirectory + "Audits_Join_Usage4.xml");
			FileHelper.deleteFile(applicationOS, destinationDirectory + "Ref_Audit_ano_Join.xml");
			FileHelper.copyFile(applicationOS, automationPath + dataLocation, destinationDirectory, "Audits1_Join_Usage.xml", "Audits_Join_Usage1.xml", true);
			FileHelper.copyFile(applicationOS, automationPath + dataLocation, destinationDirectory, "Audits1_Join_Usage.xml", "Audits_Join_Usage2.xml", true);
			FileHelper.copyFile(applicationOS, automationPath + dataLocation, destinationDirectory, "Audits1_Join_Usage.xml", "Audits_Join_Usage3.xml", true);
			FileHelper.copyFile(applicationOS, automationPath + dataLocation, destinationDirectory, "Audits1_Join_Usage.xml", "Audits_Join_Usage4.xml", true);
			FileHelper.copyFile(applicationOS, automationPath + dataLocation, destinationDirectory, "Ref_Audit_ano_Join.xml", "Ref_Audit_ano_Join.xml", true);
			
			StreamHelper stream = new StreamHelper(); 
			stream.createStream(path, fileName, sheetName, "Stream", 2);
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	@Test(priority=3, description="Create File Sources", dependsOnMethods = { "createStream" }, groups = { "prerequisite2" })
	public void createFileSource() throws Exception {
		try {
			FileSourceHelper fileSource = new FileSourceHelper();
			fileSource.createFileSource(path, fileName, sheetName, "FileSource", 1);
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	@Test(priority=4, description="Create File Collections", dependsOnMethods = { "createFileSource" }, groups = { "prerequisite2" })
	public void createFileCollection() throws Exception {
		try {
			FileCollectionHelper fileCollection = new FileCollectionHelper();
			fileCollection.createFileCollection(path, fileName, sheetName, "FileCollection", 1);
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	@Test(priority=5, description="Create Triggers", dependsOnMethods = { "createFileCollection" }, groups = { "prerequisite2" })
	public void createTrigger() throws Exception {
		try {
			TriggerHelper trigger = new TriggerHelper();
			trigger.createTrigger(path, fileName, sheetName, "Trigger", 1);
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	@Test(priority=6, description="Create recurring task for Case Manager Allocator Stream Stage", dependsOnMethods = { "createTrigger" }, groups = { "prerequisite2" })
	public void createRecurringTask()throws Exception {
		try {
			RecurringTaskHelper recurringTask = new RecurringTaskHelper();
			recurringTask.createRecurringTask(path, fileName, sheetName, "RecurringTask", 1);
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	@Test(priority=7, description="Configure Stream Controller", dependsOnMethods = { "createRecurringTask" }, groups = { "prerequisite2" })
	public void createStreamController() throws Exception {
		try {
			StreamControllerHelper streamController = new StreamControllerHelper();
			streamController.createStreamController(path, fileName, sheetName, "StreamController", 1);
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	@Test(priority=8, description="Configure Task Controller", dependsOnMethods = { "createStreamController" }, groups = { "prerequisite2" })
	public void createTaskController() throws Exception {
		try {
			TaskControllerHelper taskController = new TaskControllerHelper();
			taskController.createTaskController(path, fileName, sheetName, "TaskController", 1);
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
}