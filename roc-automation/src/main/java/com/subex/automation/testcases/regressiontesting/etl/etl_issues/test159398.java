package com.subex.automation.testcases.regressiontesting.etl.etl_issues;

import java.util.ArrayList;
import java.util.HashMap;

import org.testng.annotations.Test;

import com.subex.automation.helpers.application.ControllerHelper;
import com.subex.automation.helpers.application.NavigationHelper;
import com.subex.automation.helpers.application.screens.StreamHelper;
import com.subex.automation.helpers.application.screens.TaskSearchHelper;
import com.subex.automation.helpers.component.ButtonHelper;
import com.subex.automation.helpers.component.CheckBoxHelper;
import com.subex.automation.helpers.component.FileHelper;
import com.subex.automation.helpers.component.GenericHelper;
import com.subex.automation.helpers.component.GridHelper;
import com.subex.automation.helpers.component.LabelHelper;
import com.subex.automation.helpers.component.TextBoxHelper;
import com.subex.automation.helpers.file.ExcelReader;
import com.subex.automation.helpers.util.FailureHelper;

public class test159398 extends testETLIssues {
	
	final String dataLocation = "\\src\\main\\resources\\Data";
	final String folderName = "ASCII/MSC";
	final String sheetName = "test159398";
	final String dataFileName = "MSC_0001241019_101112_Error.txt";
	
	public test159398() throws Exception {
		super();
	}
	
	public void updateStreamStage(String streamName, String streamStage, String restartAttempts, String restartInterval, String restartLookback)throws Exception
	{
		try {
			StreamHelper stream = new StreamHelper();
			int rowNo = stream.navigateToStream(streamName);
			
			if (rowNo > 0) {
				String detailScreenTitle = NavigationHelper.navigateToEdit("SearchGrid", rowNo, "Stream_StreamStage_Grid");
				assertTrue(LabelHelper.isTitlePresent("Edit Stream"));
				
				GridHelper.clickRow("Stream_StreamStage_Grid", streamStage, "Name");
				ButtonHelper.click("Stream_StreamStage_Edit");
				GenericHelper.waitForLoadmask(detailScreenWaitSec);
				assertTrue(LabelHelper.isTitlePresent("Edit Stream Stage"));
				
				CheckBoxHelper.check("Stream_StreamStage_RestartTask");
				TextBoxHelper.type("Stream_StreamStage_RestartAttempts", restartAttempts);
				TextBoxHelper.type("Stream_StreamStage_RestartInterval", restartInterval);
				TextBoxHelper.type("Stream_StreamStage_RestartLookback", restartLookback);
				ButtonHelper.click("Stream_StreamStage_OK");
				GenericHelper.waitForLoadmask(detailScreenWaitSec);
				
				if (ButtonHelper.isPresent("YesButton")) {
					ButtonHelper.click("YesButton");
					GenericHelper.waitForLoadmask(detailScreenWaitSec);
				}
				
				stream.saveStream(streamName, detailScreenTitle);
			}
			else {
				FailureHelper.failTest("Stream '" + streamName + "' is not found.");
			}
		} catch (AssertionError e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
//	, dependsOnGroups = { "prerequisite" }
	@Test(priority=221, description="Test Case 1 for Bug 159398 ", groups="autorestart")
	public void testCase1()throws Exception
	{
		try {
			// When Auto Restart is configured for a task, if the task fails for lesser number of times than "Number of restart attempts",
			// task should restart automatically
			ControllerHelper controller = new ControllerHelper();
			controller.startStreamController();
			controller.startTaskController(configProp.getTaskControllerExeFile());
			
			ExcelReader excelReader = new ExcelReader();
			HashMap<String, ArrayList<String>> excelData = excelReader.readDataByColumn( path, fileName, sheetName, "Stream", 1 );
			String streamName = excelData.get( "Name" ).get( 0 );
			String streamStage = excelData.get( "Stream Stage" ).get( 0 );
			String restartAttempts = excelData.get( "No of Restart Attempts" ).get( 0 );
			String restartInterval = excelData.get( "Restart Interval(minutes)" ).get( 0 );
			String restartLookback = excelData.get( "Restart Lookback(minutes)" ).get( 0 );
			
			updateStreamStage(streamName, streamStage, restartAttempts, restartInterval, restartLookback);
			TaskSearchHelper taskSearch = new TaskSearchHelper();
			taskSearch.reinitializeStreamStage(path, fileName, sheetName, "TaskStatus", 1);
			
			String dataDir = configProp.getDataDirPath();
			FileHelper.copyFile(applicationOS, automationPath + dataLocation, dataDir + "/Input/" + folderName, dataFileName, dataFileName, true);
			Thread.sleep(60000);
			
			taskSearch.verifyTaskStatus(path, fileName, sheetName, "TaskStatus", 1);
			Thread.sleep(70000);
			taskSearch.verifyTaskCount(path, fileName, sheetName, "TaskCount", 1);
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	@Test(priority=222, description="Test Case 2 for Bug 159398 ", dependsOnMethods = { "testCase1" }, groups="autorestart")
	public void testCase2()throws Exception
	{
		try {
			// When Auto Restart is configured for a task, if the task fails for lesser number of times than "Number of restart attempts",
			// and user manually restarts the task and the task fails again, task should restart automatically after "restart interval"
			TaskSearchHelper taskSearch = new TaskSearchHelper();
			taskSearch.restartTask(path, fileName, sheetName, "RestartTask", 1);
			taskSearch.verifyTaskStatus(path, fileName, sheetName, "TaskStatus", 2);
			
			Thread.sleep(70000);
			taskSearch.verifyTaskCount(path, fileName, sheetName, "TaskCount", 2);
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	@Test(priority=223, description="Test Case 3 for Bug 159398 ", dependsOnMethods = { "testCase1" }, groups="autorestart")
	public void testCase3()throws Exception
	{
		try {
			// When Auto Restart is configured for a task, if the task fails for same number of times as "Number of restart attempts",
			// task should not restart automatically after that
			Thread.sleep(70000);
			TaskSearchHelper taskSearch = new TaskSearchHelper();
			taskSearch.verifyTaskCount(path, fileName, sheetName, "TaskCount", 3);
			
			Thread.sleep(70000);
			taskSearch.verifyTaskCount(path, fileName, sheetName, "TaskCount", 3);
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