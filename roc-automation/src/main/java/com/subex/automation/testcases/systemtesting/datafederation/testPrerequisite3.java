package com.subex.automation.testcases.systemtesting.datafederation;

import org.testng.annotations.Test;

import com.subex.automation.helpers.application.screens.StreamControllerHelper;
import com.subex.automation.helpers.application.screens.TaskControllerHelper;
import com.subex.automation.helpers.selenium.ROCAcceptanceTest;
import com.subex.automation.helpers.util.FailureHelper;

public class testPrerequisite3 extends ROCAcceptanceTest{
	
	private static String path = null;
	final String fileName = "FederatedDataSource_TestData.xlsx";
	final String sheetName = "FederatedDataSource";
	
	public testPrerequisite3() throws Exception {
		try {
			path = automationPath + "\\src\\main\\resources\\System_Test_Flows\\";
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	@Test(priority=1, description="Configure Stream Controller", dependsOnGroups = { "prerequisite2" }, groups = { "prerequisite3" })
	public void createStreamController() throws Exception
	{
		try {
			StreamControllerHelper streamController = new StreamControllerHelper();
			streamController.createStreamController(path, fileName, sheetName, "StreamController", 1);
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	@Test(priority=2, description="Configure Task Controller", dependsOnMethods = { "createStreamController" }, groups = { "prerequisite3" })
	public void createTaskController() throws Exception
	{
		try {
			TaskControllerHelper taskController = new TaskControllerHelper();
			taskController.createTaskController(path, fileName, sheetName, "TaskController", 1);
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
//	@Test(priority=3, description="Configure ROCView Dashboard", dependsOnMethods = { "createTaskController" }, groups = { "prerequisite3" })
//	public void createROCViewDashboard() throws Exception
//	{
//		try {
//			ROCViewHelper rocView = new ROCViewHelper();
//			rocView.createDashboard(path, fileName, sheetName, "ROCView", 1);
//		} catch (Exception e) {
//			FailureHelper.setErrorMessage(e);
//			throw e;
//		}
//	}
}