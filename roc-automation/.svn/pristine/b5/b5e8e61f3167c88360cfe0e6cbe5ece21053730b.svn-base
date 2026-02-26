package com.subex.automation.testcases.regressiontesting.etl.etl_issues;

import org.testng.annotations.Test;

import com.subex.automation.helpers.application.screens.TaskSearchHelper;
import com.subex.automation.helpers.component.ButtonHelper;
import com.subex.automation.helpers.component.GenericHelper;
import com.subex.automation.helpers.component.TabHelper;
import com.subex.automation.helpers.component.TreeHelper;
import com.subex.automation.helpers.util.FailureHelper;

public class test139548 extends testETLIssues {
	
	static String stream = "Common Stream";
	static String streamStage = "Export All Rows";
	
	public test139548() throws Exception {
		super();
	}
	
	@Test(priority=181, description="Test Case 1 for Bug 139548")
	public void testCase1() throws Exception {
		try {
			// In Task Search Screen, click Stream Controller in the left side tree.
			// Click on Summary tab and check if all the Streams and Stream Stages are visible
			TaskSearchHelper taskSearch = new TaskSearchHelper();
			taskSearch.navigateToTaskSummary();
			
			getStreamStageDetails();
			
			TabHelper.gotoTab("SearchTab");
			Thread.sleep(500);
			GenericHelper.waitForLoadmask(searchScreenWaitSec);
			
			TreeHelper.click("TaskSearch_LeftTree", "Stream Controller");
			TabHelper.gotoTab("SummaryTab");
			Thread.sleep(500);
			GenericHelper.waitForLoadmask(searchScreenWaitSec);
			
			verifyStreamStage(streams.length);
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	@Test(priority=182, description="Test Case 2 for Bug 139548")
	public void testCase2() throws Exception {
		try {
			// In Task Search Screen, click a Stream in the left side tree.
			// Click on Summary tab and check if the corresponding Stream and its Stream Stages are visible
			TaskSearchHelper taskSearch = new TaskSearchHelper();
			taskSearch.navigateToTaskSummary();
			
			TabHelper.gotoTab("SearchTab");
			Thread.sleep(500);
			GenericHelper.waitForLoadmask(searchScreenWaitSec);
			
			TreeHelper.click("TaskSearch_LeftTree", streams[0]);
			TabHelper.gotoTab("SummaryTab");
			Thread.sleep(500);
			GenericHelper.waitForLoadmask(searchScreenWaitSec);
			
			verifyStreamStage(1);
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	@Test(priority=183, description="Test Case 3 for Bug 139548")
	public void testCase3() throws Exception {
		try {
			// In Task Search Screen, click a Stream Stage in the left side tree.
			// Click on Summary tab and check if the corresponding Streams and the Stream Stage is visible
			TaskSearchHelper taskSearch = new TaskSearchHelper();
			taskSearch.navigateToTaskSummary();
			
			TabHelper.gotoTab("SearchTab");
			Thread.sleep(500);
			GenericHelper.waitForLoadmask(searchScreenWaitSec);
			
			TreeHelper.click("TaskSearch_LeftTree", streams[0], streamStages[0][0]);
			TabHelper.gotoTab("SummaryTab");
			Thread.sleep(500);
			GenericHelper.waitForLoadmask(searchScreenWaitSec);
			
			verifyStreamStage(1);
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	@Test(priority=184, description="Test Case 4 for Bug 139548")
	public void testCase4() throws Exception {
		try {
			// In Task Search Screen, click on Summary tab and double click on a stream stage count.
			// Screen should redirect to Search tab and should show the relevant tasks
			TaskSearchHelper taskSearch = new TaskSearchHelper();
			taskSearch.navigateToTaskSummary();
			
			ButtonHelper.click("ClearButton");
			GenericHelper.waitForLoadmask(searchScreenWaitSec);
			ButtonHelper.click("TaskSearch_SearchButton");
			GenericHelper.waitForLoadmask(searchScreenWaitSec);
			
			doubleClickSummary(stream, streamStage);
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	@Test(priority=185, description="Test Case 5 for Bug 139548")
	public void testCase5() throws Exception {
		try {
			// In Task Search Screen, click on Summary tab and double click on a stream stage count.
			// Screen should redirect to Search tab and should show the relevant tasks.
			// Click Summary tab again. All the streams and stream stages should be visible
			TaskSearchHelper taskSearch = new TaskSearchHelper();
			taskSearch.navigateToTaskSummary();
			
			doubleClickSummary(stream, streamStage);
			
			TabHelper.gotoTab("SummaryTab");
			Thread.sleep(500);
			GenericHelper.waitForLoadmask(searchScreenWaitSec);
			
			verifyStreamStage(streams.length);
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	@Test(priority=186, description="Test Case 6 for Bug 139548")
	public void testCase6() throws Exception {
		try {
			// In Task Search Screen, click Stream Controller in the left side tree, click on Summary tab and double click on a stream stage count.
			// Screen should redirect to Search tab and should show the relevant tasks
			TaskSearchHelper taskSearch = new TaskSearchHelper();
			taskSearch.navigateToTaskSearch(false);
			
			TreeHelper.click("TaskSearch_LeftTree", "Stream Controller");
			TabHelper.gotoTab("SummaryTab");
			Thread.sleep(500);
			GenericHelper.waitForLoadmask(searchScreenWaitSec);
			
			verifyStreamStage(streams.length);
			
			doubleClickSummary(stream, streamStage);
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	@Test(priority=187, description="Test Case 7 for Bug 139548")
	public void testCase7() throws Exception {
		try {
			// In Task Search Screen, click Stream Controller in the left side tree, click on Summary tab and double click on a stream stage count.
			// Screen should redirect to Search tab and should show the relevant tasks.
			// Click Summary tab again. All the streams and stream stages should be visible
			TaskSearchHelper taskSearch = new TaskSearchHelper();
			taskSearch.navigateToTaskSearch(false);
			
			TreeHelper.click("TaskSearch_LeftTree", "Stream Controller");
			TabHelper.gotoTab("SummaryTab");
			Thread.sleep(500);
			GenericHelper.waitForLoadmask(searchScreenWaitSec);
			
			verifyStreamStage(streams.length);
			
			doubleClickSummary(stream, streamStage);
			
			TabHelper.gotoTab("SummaryTab");
			Thread.sleep(500);
			GenericHelper.waitForLoadmask(searchScreenWaitSec);
			
			verifyStreamStage(streams.length);
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	@Test(priority=188, description="Test Case 8 for Bug 139548")
	public void testCase8() throws Exception {
		try {
			// In Task Search Screen, click a Stream in the left side tree.
			// Click on Summary tab and check if the corresponding Stream and its Stream Stages are visible
			TaskSearchHelper taskSearch = new TaskSearchHelper();
			taskSearch.navigateToTaskSearch(false);
			
			TreeHelper.click("TaskSearch_LeftTree", streams[0]);
			TabHelper.gotoTab("SummaryTab");
			Thread.sleep(500);
			GenericHelper.waitForLoadmask(searchScreenWaitSec);
			
			verifyStreamStage(1);
			
			doubleClickSummary(streams[0], streamStages[0][0]);
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	@Test(priority=189, description="Test Case 9 for Bug 139548")
	public void testCase9() throws Exception {
		try {
			// In Task Search Screen, click a Stream Stage in the left side tree.
			// Click on Summary tab and check if the corresponding Streams and the Stream Stage is visible
			TaskSearchHelper taskSearch = new TaskSearchHelper();
			taskSearch.navigateToTaskSearch(false);
			
			TreeHelper.click("TaskSearch_LeftTree", streams[0], streamStages[0][0]);
			TabHelper.gotoTab("SummaryTab");
			Thread.sleep(500);
			GenericHelper.waitForLoadmask(searchScreenWaitSec);
			
			verifyStreamStage(1);
			
			doubleClickSummary(streams[0], streamStages[0][0]);
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	@Test(priority=190, description="Test Case 10 for Bug 139548")
	public void testCase10() throws Exception {
		try {
			// In Task Search Screen, click Task Controller in the left side tree, click on Summary tab and double click on a stream stage count.
			// Screen should redirect to Search tab and should show the relevant tasks.
			// Click Summary tab again. All the streams and stream stages should be visible
			TaskSearchHelper taskSearch = new TaskSearchHelper();
			taskSearch.navigateToTaskSummary();
			
			TabHelper.gotoTab("SearchTab");
			Thread.sleep(500);
			GenericHelper.waitForLoadmask(searchScreenWaitSec);
			
			TreeHelper.click("TaskSearch_LeftTree", "Task Controllers", "Task Controller");
			TabHelper.gotoTab("SummaryTab");
			Thread.sleep(500);
			GenericHelper.waitForLoadmask(searchScreenWaitSec);
			
			verifyStreamStage(streams.length);
			
			doubleClickSummary(stream, streamStage);
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
}