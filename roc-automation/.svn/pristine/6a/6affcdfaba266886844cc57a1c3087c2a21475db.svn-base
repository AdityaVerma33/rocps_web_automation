package com.subex.automation.testcases.systemtesting.audits1;

import org.testng.annotations.Test;

import com.subex.automation.helpers.application.ControllerHelper;
import com.subex.automation.helpers.application.screens.CollectedFilesHelper;
import com.subex.automation.helpers.application.screens.FileCollectionHelper;
import com.subex.automation.helpers.application.screens.SyncReferenceTableHelper;
import com.subex.automation.helpers.application.screens.TaskControllerHelper;
import com.subex.automation.helpers.application.screens.TaskSearchHelper;
import com.subex.automation.helpers.component.FileHelper;
import com.subex.automation.helpers.selenium.ROCAcceptanceTest;
import com.subex.automation.helpers.util.FailureHelper;

public class testRunOfParseAndDataLoad extends ROCAcceptanceTest {
	
	private static String path = null;
	private static String dataDir = null;
	final String dataLocation = "\\src\\main\\resources\\Data\\Audit_Flow";
	final String fileName = "AuditFlow1_TestData.xlsx";
	final String sheetName = "AuditFlow1";
	
	public testRunOfParseAndDataLoad() throws Exception {
		try {
			path = automationPath + "\\src\\main\\resources\\System_Test_Flows\\";
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	@Test(priority=1, description="Start SC and TC", dependsOnGroups = { "prerequisite4" }, groups = { "runParseAndDataLoad" })
	public void startServices() throws Exception
	{
		try {
			// Place input files in Input directory
			dataDir = configProp.getDataDirPath();
			FileHelper.copyFile(applicationOS, automationPath + dataLocation, dataDir + "/Input/Audit_Flow1/Reference/", "Audits_ano_join_Ref_ParseDL.txt", "Audits_ano_join_Ref_ParseDL.txt", true);
			FileHelper.copyFile(applicationOS, automationPath + dataLocation, dataDir + "/Input/Audit_Flow1/Reference1/", "Audits_ano_join_Ref_ParseDL.txt", "Audits_ano_join_Ref_ParseDL.txt", true);
			FileHelper.copyFile(applicationOS, automationPath + dataLocation, dataDir + "/Input/Audit_Flow1/Usage1/", "Audits_Join_Usage1.txt", "Audits_Join_Usage1.txt", true);
			FileHelper.copyFile(applicationOS, automationPath + dataLocation, dataDir + "/Input/Audit_Flow1/Usage2/", "Audits_Join_Usage2.txt", "Audits_Join_Usage2.txt", true);
			FileHelper.copyFile(applicationOS, automationPath + dataLocation, dataDir + "/Input/Audit_Flow1/Usage3/", "Audits_Join_Usage3.txt", "Audits_Join_Usage3.txt", true);
			FileHelper.copyFile(applicationOS, automationPath + dataLocation, dataDir + "/Input/Audit_Flow1/Usage4/", "Audits_Join_Usage4.txt", "Audits_Join_Usage4.txt", true);
			
			// Start Controllers
			ControllerHelper controller = new ControllerHelper();
			controller.startStreamController();
			controller.startTaskController(configProp.getTaskControllerExeFile());
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	@Test(priority=2, description="Pause Case Manager and Case Manager Allocator", dependsOnMethods = { "startServices" }, groups = { "runParseAndDataLoad" })
	public void pauseStreamStage() throws Exception
	{
		try {
			// Pausing Case Manager and Case Manager Allocator Tasks
			String[] pauseStream = {"Common Stream"};
			String[][] pauseStage = {{"Case Manager", "Case Manager Allocator"}};

			TaskSearchHelper taskSearch = new TaskSearchHelper();
			taskSearch.pauseStreamStage(pauseStream, pauseStage);
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	@Test(priority=3, description="Set Task Controller Capability for all the stages", dependsOnMethods = { "pauseStreamStage" }, groups = { "runParseAndDataLoad" })
	public void setTaskControllerCapability() throws Exception
	{
		try {
			FileCollectionHelper fileCollection = new FileCollectionHelper();
			fileCollection.scheduleFileCollection(path, fileName, sheetName, "FileCollection", 1);
			
			TaskControllerHelper taskController = new TaskControllerHelper();
			taskController.setTaskControllerCapability(path, fileName, sheetName, "TCCapability", 1);
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	@Test(priority=4, description="Verifying if the file got collected", dependsOnMethods = { "setTaskControllerCapability" }, groups = { "runParseAndDataLoad" })
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
	
	@Test(priority=5, description="Verifying ETL tasks in Task Search Screen", dependsOnMethods = { "verifyCollectedFiles" }, groups = { "runParseAndDataLoad" })
	public void verifyETLTask() throws Exception
	{
		try {
			TaskSearchHelper taskSearch = new TaskSearchHelper();
			taskSearch.verifyTaskCount(path, fileName, sheetName, "ETLTask", 1);
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	@Test(priority=6, description="Enable Schedule for Sync Reference Tables", dependsOnMethods = { "verifyETLTask" }, groups = { "runParseAndDataLoad" })
	public void enableSyncReferenceTable() throws Exception
	{
		try {
			SyncReferenceTableHelper syncReferenceTable = new SyncReferenceTableHelper();
			syncReferenceTable.enableSyncReferenceTable(path, fileName, sheetName, "SyncReferenceTable", 1);
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	@Test(priority=7, description="Verifying Sync Reference Task in Task Search Screen", dependsOnMethods = { "enableSyncReferenceTable" }, groups = { "runParseAndDataLoad" })
	public void runParseAndDataLoad() throws Exception
	{
		try {
			TaskSearchHelper taskSearch = new TaskSearchHelper();
			taskSearch.verifyTaskCount(path, fileName, sheetName, "SyncRefTableTask", 1);
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
}