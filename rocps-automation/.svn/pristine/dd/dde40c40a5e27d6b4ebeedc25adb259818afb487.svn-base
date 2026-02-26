package com.subex.rocps.automation.testcases.functionaltesting;

import com.subex.automation.helpers.util.FailureHelper;
import com.subex.rocps.automation.helpers.application.aggregation.AggregationConfiguration;
import com.subex.rocps.automation.helpers.application.aggregation.AggregationResult;
import com.subex.rocps.automation.helpers.application.matchandrate.EventType;
import com.subex.rocps.automation.helpers.application.referenceTable.EventLegCodeGroup;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;

public class TCAggregationConfiguration extends PSAcceptanceTest
{

	String path = System.getProperty( "user.dir" ) + "\\src\\main\\resources\\";
	String workBookName = "FunctionalTestCases.xlsx";
	String sheetName = "AggregationConfiguration";

	@org.testng.annotations.Test( priority = 1, description = "aggregation Configuration creation", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void aggregationConfigurationCreation() throws Exception
	{
		try
		{
			AggregationConfiguration aggrObj = new AggregationConfiguration( path, workBookName, sheetName, "AggregationConfiguration", 1 );
			aggrObj.configureAggregation();
			aggrObj.changeAggregationStatus();
		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 2, description = "aggregation Configuration search screen col validation" )
	public void aggregationConfigurationColVal() throws Exception
	{
		try
		{
			AggregationConfiguration aggrColValObj = new AggregationConfiguration( path, workBookName, sheetName, "AggrConfigSearchScreencolVal", 1 );
			aggrColValObj.searchScreenColumnsValidation();

		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 3,invocationCount =2, description = "aggregation Configuration creation for Fios Event Type", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void aggregationConfigurationFiosCreation() throws Exception
	{
		try
		{

			EventLegCodeGroup legObj = new EventLegCodeGroup( path, workBookName, "EventType", "LegCode", 1 );
			legObj.eventLegCodeGroupCreation();

			EventType eventObj = new EventType( path, workBookName, "EventType", "EventType Fios", 1 );
			eventObj.eventTypeCreation();

			AggregationConfiguration aggrObj = new AggregationConfiguration( path, workBookName, sheetName, "AggregationConfiguration Fios", 1 );
			aggrObj.configureAggregation();
			aggrObj.changeAggregationStatus();
		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 4, description = "aggregation Configuration creation for Fios Event Type", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void aggregationConfigurationMultiEventCreation() throws Exception
	{
		try
		{
			AggregationConfiguration aggrObj = new AggregationConfiguration( path, workBookName, sheetName, "AggregationConfiguration MultiEvent", 1 );
			aggrObj.configureAggregation();
			//aggrObj.changeAggregationStatus();
		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 5, description = "aggregation Configuration deletion" )
	public void aggregationConfigurationDelete() throws Exception
	{
		try
		{
			AggregationConfiguration aggrDelObj = new AggregationConfiguration( path, workBookName, sheetName, "AggregationConfigDelete", 1 );
			aggrDelObj.aggregationConfigDelete();
		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 6, description = "aggregation Configuration un delete" )
	public void aggregationConfigurationUnDelete() throws Exception
	{
		try
		{
			AggregationConfiguration aggrUnDelObj = new AggregationConfiguration( path, workBookName, sheetName, "AggregationConfigUnDelete", 1 );
			aggrUnDelObj.aggregationConfigUnDelete();
		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 7, description = "aggregation Configuration change status",retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void aggregationConfigurationchangestatus() throws Exception
	{
		try
		{
			AggregationConfiguration aggrStatusObj = new AggregationConfiguration( path, workBookName, sheetName, "AggregationConfiguration MultiEvent", 1 );
			aggrStatusObj.configureAggregation();
			aggrStatusObj.changeAggregationStatus();
		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}
}
