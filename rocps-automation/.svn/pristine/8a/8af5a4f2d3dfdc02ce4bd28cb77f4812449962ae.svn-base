package com.subex.rocps.automation.testcases.functionaltesting;

import com.subex.automation.helpers.application.screens.TaskControllerHelper;
import com.subex.automation.helpers.util.FailureHelper;
import com.subex.rocps.automation.helpers.application.aggregation.AggregationResult;
import com.subex.rocps.automation.helpers.application.monitoring.PSCollectedFileSearchHelper;
import com.subex.rocps.automation.helpers.application.system.PSTaskSearchHelper;
import com.subex.rocps.automation.helpers.application.system.TaskSchedule;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;

public class TCVoiceSurchargeServerCases extends PSAcceptanceTest
{

	String path = System.getProperty( "user.dir" ) + "\\src\\main\\resources\\";

	String workBookName = "FunctionalTestCases.xlsx";
	String sheetName = "SurchargeRuleServerCases";

	@org.testng.annotations.Test( priority = 1, enabled = true, description = "task Controller capabilities", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void taskControllerCapabilities() throws Exception
	{
		try
		{

			TaskControllerHelper taskObj = new TaskControllerHelper();
			taskObj.setTaskControllerCapability( path, workBookName, "SurchargeRuleServerCases", "TCCapability", 1 );

		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 2, enabled = true, description = "Mnr   for Surcharge Rule" )
	public void surchargeRuleMnr() throws Exception

	{
		try
		{
			TaskSchedule taskObj = new TaskSchedule();
			PSTaskSearchHelper psTaskobj = new PSTaskSearchHelper();
			taskObj.fileCollection( path, workBookName, sheetName, "FileSchedule_SurchargeRule", 1 );
			PSCollectedFileSearchHelper collectedFlObj = new PSCollectedFileSearchHelper( path, workBookName, sheetName, "CollectedFileSearch_SurchargeRule" );
			collectedFlObj.validationOfCollectedFile();
			psTaskobj.psVerifyTaskStatus( path, workBookName, sheetName, "MnrTaskStatus", 1 );

		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 3, enabled = true, description = "schedule and verify  Aggregation  for Surcharge Rule" )
	public void surchargeRule_AggregationResult() throws Exception

	{
		try
		{
			TaskSchedule taskObj = new TaskSchedule();
			PSTaskSearchHelper psTaskobj = new PSTaskSearchHelper();
			taskObj.scheduleRecurringTask( path, workBookName, sheetName, "RecurringTask", 1 );
			psTaskobj.psVerifyTaskStatus( path, workBookName, sheetName, "AggrTaskStatus", 1 );
			AggregationResult aggrResObj = new AggregationResult( path, workBookName, sheetName, "AggregationResult_SurchargeRule" );
			aggrResObj.viewAggregationResult();

		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}
}
