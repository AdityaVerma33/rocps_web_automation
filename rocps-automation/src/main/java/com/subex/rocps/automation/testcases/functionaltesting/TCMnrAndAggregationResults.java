package com.subex.rocps.automation.testcases.functionaltesting;

import com.subex.rocps.automation.helpers.application.aggregation.AggregationResult;
import com.subex.rocps.automation.helpers.application.genericHelpers.PSGenericHelper;
import com.subex.rocps.automation.helpers.application.system.PSTaskSearchHelper;
import com.subex.rocps.automation.helpers.application.system.TaskSchedule;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;

import com.subex.automation.helpers.application.screens.TaskControllerHelper;
import com.subex.automation.helpers.application.screens.TaskSearchHelper;
import com.subex.automation.helpers.util.FailureHelper;

public class TCMnrAndAggregationResults extends PSAcceptanceTest {
	String path = System.getProperty("user.dir") + "\\src\\main\\resources\\";
	String workBookName = "FunctionalTestCases.xlsx";
	String sheetName = "AggregationResults";
	PSTaskSearchHelper tskObj = new PSTaskSearchHelper();

	@org.testng.annotations.Test(priority = 1, description = "task Controller capabilities", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class)
	public void taskControllerCapabilities() throws Exception {
		try {

			TaskControllerHelper taskObj = new TaskControllerHelper();
			taskObj.setTaskControllerCapability(path, workBookName, "ROCPreRequisites2", "TCCapability", 1);

		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}

	@org.testng.annotations.Test(priority = 2, description = "aggregation Configuration Grid Columns Config", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class)
	public void aggregationConfigurationGridColumnsConfig() throws Exception {
		try {
			AggregationResult aggrResultObj = new AggregationResult(path, workBookName, "AggregationConfiguration",
					"AggregationConfigGridColumn");
			aggrResultObj.ConfigureGridColumns();

		} catch (Exception e) {
			FailureHelper.reportFailure(e);
			throw e;
		}
	}

	@org.testng.annotations.Test(priority = 3, description = "mnr - Outgoing")
	public void aggregationOutgoing() throws Exception {
		try {

			TaskSchedule taskObj = new TaskSchedule();
			taskObj.fileCollection(path, workBookName, sheetName, "FileSchedule Outgoing", 1);

			PSTaskSearchHelper tskObj = new PSTaskSearchHelper();
			tskObj.psVerifyTaskStatus(path, workBookName, sheetName, "MnrTaskStatus", 1);

		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}

	@org.testng.annotations.Test(priority = 4, description = "aggregation results- Outgoing")
	public void aggregationOutgoingRecurringTask() throws Exception {
		try {
			TaskSchedule taskObj = new TaskSchedule();
			taskObj.scheduleRecurringTask(path, workBookName, sheetName, "RecurringTask", 1);

			PSTaskSearchHelper tskObj = new PSTaskSearchHelper();
			tskObj.psVerifyTaskStatus(path, workBookName, sheetName, "AggrTaskStatus", 1);

			AggregationResult aggrResObj = new AggregationResult(path, workBookName, sheetName,
					"AggregationResult Outgoing");
			aggrResObj.viewAggregationResult();

		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}

	@org.testng.annotations.Test(priority = 5, description = "mnr- Incoming and Transit incoming scenario ")
	public void aggregationIncomingAndTransitIn() throws Exception {
		try {

			TaskSchedule taskObj = new TaskSchedule();
			taskObj.fileCollection(path, workBookName, sheetName, "FileSchedule Incoming", 1);

			tskObj.psVerifyTaskStatus(path, workBookName, sheetName, "MnrTaskStatus", 1);

		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}

	@org.testng.annotations.Test(priority = 6, description = "aggregation results- Incoming and Transit incoming scenario ")
	public void aggregationIncomingRecurringTask() throws Exception {
		try {
			TaskSchedule taskObj = new TaskSchedule();
			taskObj.scheduleRecurringTask(path, workBookName, sheetName, "RecurringTask", 1);

			tskObj.psVerifyTaskStatus(path, workBookName, sheetName, "AggrTaskStatus", 1);

			AggregationResult aggrResObj = new AggregationResult(path, workBookName, sheetName,
					"AggregationResult IncomingAndTransitIn");
			aggrResObj.viewAggregationResult();

		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}

	@org.testng.annotations.Test(priority = 7, description = "mnr and aggregation results- Ano")
	public void aggregationAno() throws Exception {
		try {

			TaskSchedule taskObj = new TaskSchedule();
			taskObj.fileCollection(path, workBookName, sheetName, "FileSchedule Ano", 1);

			tskObj.psVerifyTaskStatus(path, workBookName, sheetName, "MnrTaskStatus", 1);

		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}

	@org.testng.annotations.Test(priority = 8, description = "aggregation results- Ano")
	public void aggregationAnoRecurringTask() throws Exception {
		try {
			TaskSchedule taskObj = new TaskSchedule();
			taskObj.scheduleRecurringTask(path, workBookName, sheetName, "RecurringTask", 1);

			tskObj.psVerifyTaskStatus(path, workBookName, sheetName, "AggrTaskStatus", 1);

			AggregationResult aggrResObj = new AggregationResult(path, workBookName, sheetName,
					"AggregationResult Ano");
			aggrResObj.viewAggregationResult();

		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}

	@org.testng.annotations.Test(priority = 9, description = "mnr and aggregation results- Transit Outgoing")
	public void aggregationTransitOut() throws Exception {
		try {
			AggregationResult aggrResObj = new AggregationResult(path, workBookName, sheetName,
					"AggregationResult Transit");
			aggrResObj.viewAggregationResult();

		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}

	@org.testng.annotations.Test(priority = 10, description = "mnr - Revenue Sharing")
	public void aggregationRevenueSharing() throws Exception {
		try {

			TaskSchedule taskObj = new TaskSchedule();
			taskObj.fileCollection(path, workBookName, sheetName, "FileSchedule Revenue", 1);

			tskObj.psVerifyTaskStatus(path, workBookName, sheetName, "MnrTaskStatus", 1);

		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}

	@org.testng.annotations.Test(priority = 11, description = "aggregation results- Revenue Sharing")
	public void aggregationRevenueSharingRecurringTask() throws Exception {
		try {

		    TaskSchedule taskObj = new TaskSchedule();
			taskObj.scheduleRecurringTask(path, workBookName, sheetName, "RecurringTask", 1);
			
			tskObj.psVerifyTaskStatus(path, workBookName, sheetName, "AggrTaskStatus", 1);

			AggregationResult aggrResObj = new AggregationResult(path, workBookName, sheetName,
					"AggregationResult Revenue");
			aggrResObj.viewAggregationResult();

		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}

	@org.testng.annotations.Test(priority = 12, description = "Aggregation Result column validation")
	public void aggregationResultColVal() throws Exception {
		try {
			AggregationResult aggrResObj = new AggregationResult(path, workBookName, sheetName,
					"AggrResultSearchScreencolVal");
			aggrResObj.searchScreenColumnsValidation();

		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}

}
