package com.subex.rocps.automation.testcases.functionaltesting;

import com.subex.rocps.automation.helpers.application.aggregation.AggregationProcessor;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;

import com.subex.automation.helpers.util.FailureHelper;

public class TCAggregationProcessor extends PSAcceptanceTest
{
	String path = System.getProperty( "user.dir" ) + "\\src\\main\\resources\\";
	String workBookName = "FunctionalTestCases.xlsx";
	String sheetName = "AggregationProcessor";

	@org.testng.annotations.Test( priority = 1, description = "aggregation processor  creation", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void aggregationProcessor() throws Exception
	{

		try
		{
			AggregationProcessor aggObj = new AggregationProcessor( path, workBookName, sheetName, "AggregationProcessor", 1 );
			aggObj.aggregationProcessorCreation();
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 2, description = "aggregation processor  search screen column validation" )
	public void aggregationProcessorColVal() throws Exception
	{

		try
		{
			AggregationProcessor aggObj = new AggregationProcessor( path, workBookName, sheetName, "AggrproSearchScreencolVal", 1 );
			aggObj.searchScreenColumnsValidation();
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 3, description = "aggregation processor Fios creation", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void aggregationProcessorFios() throws Exception
	{

		try
		{
			AggregationProcessor aggObj = new AggregationProcessor( path, workBookName, sheetName, "AggregationProcessor FIOS", 1 );
			aggObj.aggregationProcessorCreation();
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 4, description = "aggregation processor  Multi Event Type creation" ,retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class)
	public void aggregationProcessorMultiEvent() throws Exception
	{

		try
		{
			AggregationProcessor aggObj = new AggregationProcessor( path, workBookName, sheetName, "AggregationProcessor MultiEvent", 1 );
			aggObj.aggregationProcessorCreation();
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}
}
