package com.subex.automation.testcases.regressiontesting.etl.etl_issues;

import org.testng.annotations.Test;

import com.subex.automation.helpers.application.screens.TaskSearchHelper;
import com.subex.automation.helpers.component.ButtonHelper;
import com.subex.automation.helpers.component.GenericHelper;
import com.subex.automation.helpers.component.GridHelper;
import com.subex.automation.helpers.component.TabHelper;
import com.subex.automation.helpers.component.TreeHelper;
import com.subex.automation.helpers.util.FailureHelper;

public class test139540 extends testETLIssues {
	
	final String stream = "Common Stream";
	final String streamStage = "Export All Rows";
	
	public test139540() throws Exception {
		super();
	}
	
	@Test(priority=171, description="Test Case 1 for Bug 139540")
	public void testCase1() throws Exception {
		try {
			// In Task Search Screen, click on Summary tab once and all the Summary Information should be visible on the screen
			TaskSearchHelper taskSearch = new TaskSearchHelper();
			taskSearch.navigateToTaskSummary();
			
			assertTrue(GridHelper.isValuePresent("SearchGrid", stream, "Stream Stage"));
			int rows = GridHelper.getRowCount("SearchGrid");
			assertTrue(rows >= 1);
			TreeHelper.expandTreeGrid("SearchGrid", stream);
			assertTrue(GridHelper.isValuePresent("SearchGrid", streamStage, "Stream Stage"));
		} catch (AssertionError e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	@Test(priority=172, description="Test Case 2 for Bug 139540")
	public void testCase2() throws Exception {
		try {
			// In Task Search Screen, click on Summary tab twice and all the Summary Information should be visible on the screen
			TaskSearchHelper taskSearch = new TaskSearchHelper();
			taskSearch.navigateToTaskSummary();
			
			assertTrue(GridHelper.isValuePresent("SearchGrid", stream, "Stream Stage"));
			TreeHelper.expandTreeGrid("SearchGrid", stream);
			assertTrue(GridHelper.isValuePresent("SearchGrid", streamStage, "Stream Stage"));
			
			int row = GridHelper.getRowNumber("SearchGrid", streamStage, "Stream Stage");
			String count = GridHelper.getCellValue("SearchGrid", row, "Completed");
			int expectedRows = Integer.parseInt(count);
			
			if (expectedRows > 0) {
				GridHelper.doubleClick("SearchGrid", row, "Completed");
				GenericHelper.waitForLoadmask(searchScreenWaitSec);
				assertTrue(TabHelper.isSelected("SearchTab"));
				int rows = GridHelper.getRowCount("SearchGrid");
				assertEquals(rows, expectedRows);
			}
		} catch (AssertionError e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	@Test(priority=173, description="Test Case 3 for Bug 139540")
	public void testCase3() throws Exception {
		try {
			// In Task Search Screen, click on Summary tab and check if Search functionality is working
			TaskSearchHelper taskSearch = new TaskSearchHelper();
			taskSearch.navigateToTaskSummary();
			
			assertTrue(GridHelper.isValuePresent("SearchGrid", stream, "Stream Stage"));
			TreeHelper.expandTreeGrid("SearchGrid", stream);
			assertTrue(GridHelper.isValuePresent("SearchGrid", streamStage, "Stream Stage"));
			
			int row = GridHelper.getRowNumber("SearchGrid", streamStage, "Stream Stage");
			String waitingCount = GridHelper.getCellValue("SearchGrid", row, "Waiting");
			String pausedCount = GridHelper.getCellValue("SearchGrid", row, "Paused");
			String runningCount = GridHelper.getCellValue("SearchGrid", row, "Running");
			String cancelledCount = GridHelper.getCellValue("SearchGrid", row, "Cancelled");
			String failedCount = GridHelper.getCellValue("SearchGrid", row, "Failed");
			String completedCount = GridHelper.getCellValue("SearchGrid", row, "Completed");
			
			row = taskSearch.getSummaryTask(stream, streamStage);
			assertEquals(GridHelper.getCellValue("SearchGrid", row, "Waiting"), waitingCount);
			assertEquals(GridHelper.getCellValue("SearchGrid", row, "Paused"), pausedCount);
			assertEquals(GridHelper.getCellValue("SearchGrid", row, "Running"), runningCount);
			assertEquals(GridHelper.getCellValue("SearchGrid", row, "Cancelled"), cancelledCount);
			assertEquals(GridHelper.getCellValue("SearchGrid", row, "Failed"), failedCount);
			assertEquals(GridHelper.getCellValue("SearchGrid", row, "Completed"), completedCount);
		} catch (AssertionError e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	@Test(priority=174, description="Test Case 4 for Bug 139540")
	public void testCase4() throws Exception {
		try {
			// In Task Search Screen, click on Summary tab and check if Clear functionality is working
			TaskSearchHelper taskSearch = new TaskSearchHelper();
			taskSearch.navigateToTaskSummary();
			
			assertTrue(GridHelper.isValuePresent("SearchGrid", stream, "Stream Stage"));
			TreeHelper.expandTreeGrid("SearchGrid", stream);
			assertTrue(GridHelper.isValuePresent("SearchGrid", streamStage, "Stream Stage"));
			
			int row = taskSearch.getSummaryTask(stream, streamStage);
			assertTrue(row > 0);
			
			ButtonHelper.click("ClearButton");
			GenericHelper.waitForLoadmask(searchScreenWaitSec);
			int rows = GridHelper.getRowCount("SearchGrid");
			assertTrue(rows == 0);
		} catch (AssertionError e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
}