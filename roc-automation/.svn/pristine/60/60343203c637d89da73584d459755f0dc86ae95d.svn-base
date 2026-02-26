package com.subex.automation.testcases.systemtesting.audits2;

import org.testng.annotations.Test;

import com.subex.automation.helpers.application.ControllerHelper;
import com.subex.automation.helpers.application.screens.CollectedFilesHelper;
import com.subex.automation.helpers.application.screens.FileCollectionHelper;
import com.subex.automation.helpers.application.screens.TaskControllerHelper;
import com.subex.automation.helpers.application.screens.TaskSearchHelper;
import com.subex.automation.helpers.component.FileHelper;
import com.subex.automation.helpers.selenium.ROCAcceptanceTest;
import com.subex.automation.helpers.util.FailureHelper;

public class testRunOfParseAndDataLoad extends ROCAcceptanceTest
{
	private static String path = null;
	private static String dataDir = null;
	final String dataLocation = "/src/main/resources/Data/Audit_Flow";
	final String fileName = "AuditFlow2_TestData.xlsx";
	final String sheetName = "AuditFlow2";
	
	public testRunOfParseAndDataLoad() throws Exception {
		try {
			path = automationPath + "\\src\\main\\resources\\System_Test_Flows\\";
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	@Test(priority=1, description="Start SC", dependsOnGroups = { "prerequisite2" }, groups = { "runETL" })
	public void startServices() throws Exception
	{
		try {
			ControllerHelper controller = new ControllerHelper();
			controller.startStreamController();
			controller.startTaskController(configProp.getTaskControllerExeFile());
			dataDir = configProp.getDataDirPath();
			
			FileHelper.copyFile(applicationOS, automationPath + dataLocation, dataDir + "/Input/Audit_Flow2/Usage1", "Audits2_Join_Usage5.txt", "Audits2_Join_Usage5.txt", true);
			FileHelper.copyFile(applicationOS, automationPath + dataLocation, dataDir + "/Input/Audit_Flow2/Usage2", "Audits2_Join_Usage6.txt", "Audits2_Join_Usage6.txt", true);
			
			FileCollectionHelper fileCollection = new FileCollectionHelper();
			fileCollection.scheduleFileCollection("Audit2 Flow FC - Usage1");
			fileCollection.scheduleFileCollection("Audit2 Flow FC - Usage2");
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	@Test(priority=2, description="Set Task Controller Capability for all the stages", dependsOnMethods = { "startServices" }, groups = { "runETL" })
	public void setTaskControllerCapability() throws Exception
	{
		try {
			TaskControllerHelper taskController = new TaskControllerHelper();
			taskController.setTaskControllerCapability(path, fileName, sheetName, "TCCapability", 1);
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	@Test(priority=3, description="Verifying if the file got collected", dependsOnMethods = { "setTaskControllerCapability" }, groups = { "runETL" })
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
	
	@Test(priority=4, description="Verifying tasks in Task Search Screen", dependsOnMethods = { "verifyCollectedFiles" }, groups = { "runETL" })
	public void verifyTaskCount() throws Exception
	{
		try {
			TaskSearchHelper taskSearch = new TaskSearchHelper();
			taskSearch.verifyTaskCount(path, fileName, sheetName, "ETLTask", 1);
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
}