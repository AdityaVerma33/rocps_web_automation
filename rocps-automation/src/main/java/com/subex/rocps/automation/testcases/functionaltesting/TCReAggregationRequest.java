package com.subex.rocps.automation.testcases.functionaltesting;

import com.subex.rocps.automation.helpers.application.aggregation.AggregationResult;
import com.subex.rocps.automation.helpers.application.reaggregation.ReAggregationRequest;
import com.subex.rocps.automation.helpers.application.system.TaskSchedule;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;

import com.subex.automation.helpers.application.screens.TaskSearchHelper;
import com.subex.automation.helpers.util.FailureHelper;

public class TCReAggregationRequest extends PSAcceptanceTest
{
	String path = System.getProperty( "user.dir" ) + "\\src\\main\\resources\\";
	String workBookName = "FunctionalTestCases.xlsx";
	String sheetName = "ReAggregationRequest";

	@org.testng.annotations.Test( priority = 1, description = "ReAggregationRequest creation", groups =
	{ "Client" } ,retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class)
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

	@org.testng.annotations.Test( priority = 2, description = "ReAggregationRequest - Bill Profile creation", groups =
	{ "Client" } )
	public void reAggregationRequestBillProfileCreation() throws Exception
	{
		try
		{
			ReAggregationRequest reaggrObj = new ReAggregationRequest( path, workBookName, sheetName, "ReAggregation billProfile", 1 );
			reaggrObj.reAggregationRequestCreation();
		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 3, description = "ReAggregationRequest - Bill Period-Single BillProfile creation", groups =
	{ "Client" } )
	public void reAggregationbillPeriodSingleBillProfileCreation() throws Exception
	{
		try
		{
			ReAggregationRequest reaggrObj = new ReAggregationRequest( path, workBookName, sheetName, "ReAggregation billPeriod_SingleBillProfile", 1 );
			reaggrObj.reAggregationRequestCreation();
		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 4, description = "ReAggregationRequest - Bill Period -Multiple Bill Profile creation", groups =
	{ "Client" },retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void reAggregationbillPeriodMultiBillProfileCreation() throws Exception
	{
		try
		{
			ReAggregationRequest reaggrObj = new ReAggregationRequest( path, workBookName, sheetName, "ReAggregation billPeriod_Multiple BillProfile", 1 );
			reaggrObj.reAggregationRequestCreation();
		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}

	/*@org.testng.annotations.Test( priority = 5, description = "ReAggregationRequest creation" , groups = {"Client"})
	public void reAggregationRequestMultiEventType() throws Exception
	{
		try
		{
			ReAggregationRequest reaggrObj = new ReAggregationRequest( path, workBookName, sheetName, "ReAggregation multipleEventtypes", 1 );
			reaggrObj.reAggregationRequestCreation();
		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}*/

	@org.testng.annotations.Test( priority = 6, description = "ReAggregationRequest - Edit", groups =
	{ "Client" } )
	public void reAggregationEdit() throws Exception
	{
		try
		{
			ReAggregationRequest reaggrObj = new ReAggregationRequest( path, workBookName, sheetName, "ReAggregation Edit", 1 );
			reaggrObj.editReAggregationRequest();
		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 7, description = "ReAggregationRequest - search screen column validation", groups =
	{ "Client" } )
	public void reAggregationColVal() throws Exception
	{
		try
		{
			ReAggregationRequest reaggrObj = new ReAggregationRequest( path, workBookName, sheetName, "reAggrSearchScreencolVal", 1 );
			reaggrObj.searchScreenColumnsValidation();
		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}

}
