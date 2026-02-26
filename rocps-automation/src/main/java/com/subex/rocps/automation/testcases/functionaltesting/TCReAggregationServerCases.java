package com.subex.rocps.automation.testcases.functionaltesting;

import com.subex.rocps.automation.helpers.application.aggregation.AggregationResult;
import com.subex.rocps.automation.helpers.application.reaggregation.ReAggregationRequest;
import com.subex.rocps.automation.helpers.application.system.PSTaskSearchHelper;
import com.subex.rocps.automation.helpers.application.system.TaskSchedule;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;

import com.subex.automation.helpers.application.screens.TaskSearchHelper;
import com.subex.automation.helpers.util.FailureHelper;

public class TCReAggregationServerCases extends PSAcceptanceTest
{
	String path = System.getProperty( "user.dir" ) + "\\src\\main\\resources\\";
	String workBookName = "FunctionalTestCases.xlsx";
	String sheetName = "ReAggregationRequest";

	@org.testng.annotations.Test( priority = 1, description = "ReAggregationRequest creation", groups =
	{ "Server" } )
	public void reAggregationRequestCreation() throws Exception
	{
		try
		{
			ReAggregationRequest reaggrObj = new ReAggregationRequest( path, workBookName, sheetName, "ReAggregation", 1 );
			reaggrObj.reAggregationRequestCreation();
		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 2, description = "ReAggregationRequest - Schedule Action", groups =
	{ "Server" } )
	public void reAggregationschedule() throws Exception
	{
		try
		{
			ReAggregationRequest reaggrObj = new ReAggregationRequest( path, workBookName, sheetName, "ReAggregation ScheduleAction", 1 );
			reaggrObj.scheduleReAggregationRequest();
		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 3, description = "run aggregation master task and verifying task status and aggreg", groups =
	{ "Server" } )
	public void serverSideCases() throws Exception
	{
		try
		{
			PSTaskSearchHelper tskObj1 = new PSTaskSearchHelper();
			tskObj1.psVerifyTaskStatus( path, workBookName, sheetName, "ReaggreagtionTaskStatus", 1 );

			AggregationResult aggrResObj = new AggregationResult( path, workBookName, sheetName, "Re-AggregationResult" );
			aggrResObj.isAggregationPresent();

			TaskSchedule taskObj1 = new TaskSchedule();
			taskObj1.scheduleRecurringTask( path, workBookName, sheetName, "RecurringTask", 1 );

			tskObj1.psVerifyTaskStatus( path, workBookName, sheetName, "AggrTaskStatus", 1 );

			AggregationResult aggrResObj1 = new AggregationResult( path, workBookName, sheetName, "AggregationResult" );
			aggrResObj1.viewAggregationResult();
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}
}
