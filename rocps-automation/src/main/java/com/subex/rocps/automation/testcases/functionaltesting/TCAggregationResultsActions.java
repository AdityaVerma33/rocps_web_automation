package com.subex.rocps.automation.testcases.functionaltesting;

import com.subex.rocps.automation.helpers.application.aggregation.AggregationResult;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;

import com.subex.automation.helpers.util.FailureHelper;

public class TCAggregationResultsActions extends PSAcceptanceTest
{
	
	String path = System.getProperty( "user.dir" ) + "\\src\\main\\resources\\";
	String workBookName = "FunctionalTestCases.xlsx";
	String sheetName = "AggregationResults";
	@org.testng.annotations.Test( priority = 9, description = "Aggregation Result column validation" )
	public void aggregationlineItem() throws Exception
	{
		try
		{
			AggregationResult aggrResObj = new AggregationResult( path, workBookName, sheetName, "AggregationResult AddNewLine" );
			aggrResObj.addNewLineItemAction();
	
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 10, description = "Aggregation Result column validation" )
	public void aggregationAddAdjustments() throws Exception
	{
		try
		{
			AggregationResult aggrResObj = new AggregationResult( path, workBookName, sheetName, "AggregationResult adjustments" );
			aggrResObj.addAdjustments();
	
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}
	
	@org.testng.annotations.Test( priority = 11, description = "Aggregation Result column validation" )
	public void aggregationReverserTraffic() throws Exception
	{
		try
		{
			AggregationResult aggrResObj = new AggregationResult( path, workBookName, sheetName, "AggregationResult reverseTraffic" );
			aggrResObj.reverseTraffic();
	
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}
	
	@org.testng.annotations.Test( priority = 12, description = "Aggregation Result column validation" )
	public void aggregationViewTotals() throws Exception
	{
		try
		{
			AggregationResult aggrResObj = new AggregationResult( path, workBookName, sheetName, "AggregationResult viewTotals" );
			aggrResObj.viewTotals();
	
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}
	
	@org.testng.annotations.Test( priority = 13, description = "Aggregation Result column validation" )
	public void aggregationViewEvents() throws Exception
	{
		try
		{
			AggregationResult aggrResObj = new AggregationResult( path, workBookName, sheetName, "AggregationResult viewEvents" );
			aggrResObj.viewEvents();
	
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}
	
	//@org.testng.annotations.Test( priority = 14, description = "Aggregation Result column validation" )
	public void aggregationExporytAllRows() throws Exception
	{
		try
		{
			AggregationResult aggrResObj = new AggregationResult( path, workBookName, sheetName, "AggregationExport" );
			aggrResObj.exportAllRows();
	
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}
}
