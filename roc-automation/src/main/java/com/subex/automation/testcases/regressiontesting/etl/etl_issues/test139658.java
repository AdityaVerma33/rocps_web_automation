package com.subex.automation.testcases.regressiontesting.etl.etl_issues;

import org.testng.annotations.Test;

import com.subex.automation.helpers.application.screens.TaskSearchHelper;
import com.subex.automation.helpers.component.GenericHelper;
import com.subex.automation.helpers.component.TabHelper;
import com.subex.automation.helpers.util.FailureHelper;

public class test139658 extends testETLIssues {
	
	static String stream = "Common Stream";
	static String streamStage = "Export All Rows";
	
	public test139658() throws Exception {
		super();
	}
	
	@Test(priority=201, description="Test Case 1 for Bug 139548")
	public void testCase1() throws Exception {
		try {
			// In Task Search Screen, click on Summary tab and double click on a stream stage count.
			// Screen should redirect to Search tab and should show the relevant tasks.
			// Click Summary tab again. All the streams and stream stages should be visible. Double click some other Stream Stage.
			// Screen should redirect to Search tab and should show the relevant tasks.
			TaskSearchHelper taskSearch = new TaskSearchHelper();
			taskSearch.navigateToTaskSummary();
			getStreamStageDetails();
			
			doubleClickSummary(stream, streamStage);
			
			TabHelper.gotoTab("SummaryTab");
			Thread.sleep(500);
			GenericHelper.waitForLoadmask(searchScreenWaitSec);
			verifyStreamStage(streams.length);
			
			doubleClickSummary(streams[0], streamStages[0][0]);
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
}