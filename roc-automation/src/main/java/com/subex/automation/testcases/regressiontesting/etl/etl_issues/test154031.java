package com.subex.automation.testcases.regressiontesting.etl.etl_issues;

import org.testng.annotations.Test;

import com.subex.automation.helpers.application.ControllerHelper;
import com.subex.automation.helpers.application.NavigationHelper;
import com.subex.automation.helpers.application.screens.TaskSearchHelper;
import com.subex.automation.helpers.component.ButtonHelper;
import com.subex.automation.helpers.component.CheckBoxHelper;
import com.subex.automation.helpers.component.FileHelper;
import com.subex.automation.helpers.component.GenericHelper;
import com.subex.automation.helpers.component.GridHelper;
import com.subex.automation.helpers.util.FailureHelper;

public class test154031 extends testETLIssues {
	
	String dataLocation = "\\src\\main\\resources\\Data";
	final String sheetName = "test154031";
	
	public test154031() throws Exception {
		super();
	}
	
	public void checkTaskStatus(int row) throws Exception {
		try {
			String status = "Waiting";
			int tryCount = 1;
			while (!status.equals("Completed") && tryCount < 5) {
				String progress = GridHelper.getCellValue("SearchGrid", row, "Progress");
				status = GridHelper.getCellValue("SearchGrid", row, "Status");
				assertTrue(!progress.equals("100%"));
				Thread.sleep(1000);
				tryCount++;
			}
			
			assertTrue(status.equals("Completed"));
		} catch (AssertionError e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	@Test(priority=211, description="Test Case 1 for Bug 154031")
	public void testCase1() throws Exception {
		try {
			// In Task Search screen > Monitoring tab, when "Add New Task" filter is not checked,
			// any new task should not get added to screen automatically
			ControllerHelper controller = new ControllerHelper();
			controller.startStreamController();
			controller.startTaskController(configProp.getTaskControllerExeFile());
			
			TaskSearchHelper taskSearch = new TaskSearchHelper();
			taskSearch.verifyTaskStatus(path, fileName, sheetName, "ETLTask", 1);
			int initialRowCount = GridHelper.getRowCount("SearchGrid");
			
			GridHelper.rightClick("SearchGrid", 1, 2);
			Thread.sleep(3000);
			NavigationHelper.navigateToAction("Recurring Task", "Schedule Now");
			
			ButtonHelper.click("YesButton");
			GenericHelper.waitForLoadmask(searchScreenWaitSec);
			Thread.sleep(5000);
			int newRowCount = GridHelper.getRowCount("SearchGrid");
			assertEquals(newRowCount, initialRowCount);
			
			ButtonHelper.click("TaskSearch_SearchButton");
			GenericHelper.waitForLoadmask(searchScreenWaitSec);
			newRowCount = GridHelper.getRowCount("SearchGrid");
			assertTrue(newRowCount > initialRowCount);
		} catch (AssertionError e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	@Test(priority=212, description="Test Case 2 for Bug 154031")
	public void testCase2() throws Exception {
		try {
			// In Task Search screen > Monitoring tab, when "Add New Task" filter is checked,
			// any new task should get added to screen automatically
			TaskSearchHelper taskSearch = new TaskSearchHelper();
			taskSearch.verifyTaskStatus(path, fileName, sheetName, "ETLTask", 1);
			int initialRowCount = GridHelper.getRowCount("SearchGrid");
			
			NavigationHelper.rightClickAction("SearchGrid", 1, 2, "Recurring Task", "Schedule Now");
			ButtonHelper.click("YesButton");
			GenericHelper.waitForLoadmask(searchScreenWaitSec);
			Thread.sleep(10000);
			int newRowCount = GridHelper.getRowCount("SearchGrid");
			assertTrue(newRowCount > initialRowCount, "Tasks are not getting added automatically even though 'Add New Task' is checked.");
		} catch (AssertionError e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	@Test(priority=213, description="Test Case 3 for Bug 154031")
	public void testCase3() throws Exception {
		try {
			// In Task Search screen > Monitoring tab, when "Add New Task" filter is checked,
			// for newly added task progress bar and status should update automatically
			TaskSearchHelper taskSearch = new TaskSearchHelper();
			int row = taskSearch.getTask("ETL Issues - Stream", "MSC Parse", "Last 5 mins", null);
			GenericHelper.expandSearchFilterPanel();
			CheckBoxHelper.check("TaskSearch_AddNewTask");
			ButtonHelper.click("TaskSearch_SearchButton");
			GenericHelper.waitForLoadmask(searchScreenWaitSec);
			int initialRowCount = GridHelper.getRowCount("SearchGrid");
			
			String dataDir = configProp.getDataDirPath();
			String dataFileName = "MSC_0001241019_101112_1.txt";
			FileHelper.copyFile(applicationOS, automationPath + dataLocation, dataDir + "/Input/ASCII/MSC", dataFileName, dataFileName, true);
			Thread.sleep(40000);
			
			int newRowCount = GridHelper.getRowCount("SearchGrid");
			assertTrue(newRowCount > initialRowCount, "Tasks are not getting added automatically even though 'Add New Task' is checked.");
			
			row++;
			checkTaskStatus(row);
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
		}
	}
}