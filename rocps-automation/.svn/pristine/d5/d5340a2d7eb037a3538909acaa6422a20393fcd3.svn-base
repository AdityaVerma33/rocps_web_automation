package com.subex.rocps.automation.testcases.functionaltesting;

import com.subex.rocps.automation.helpers.application.aggregation.AggregationResult;
import com.subex.rocps.automation.helpers.application.matchandrate.EventIdentiferDefinition;
import com.subex.rocps.automation.helpers.application.matchandrate.EventMatchRule;
import com.subex.rocps.automation.helpers.application.matchandrate.EventMatchRuleGroup;
import com.subex.rocps.automation.helpers.application.matchandrate.Operator;
import com.subex.rocps.automation.helpers.application.networkConfiguraiton.Route;
import com.subex.rocps.automation.helpers.application.networkConfiguraiton.RouteGroup;
import com.subex.rocps.automation.helpers.application.networkConfiguraiton.Switch;
import com.subex.rocps.automation.helpers.application.partnerConfiguration.Account;
import com.subex.rocps.automation.helpers.application.partnerConfiguration.Agent;
import com.subex.rocps.automation.helpers.application.partnerConfiguration.BillProfile;
import com.subex.rocps.automation.helpers.application.system.PSTaskSearchHelper;
import com.subex.rocps.automation.helpers.application.system.TaskSchedule;
import com.subex.rocps.automation.helpers.application.tariffs.PSTariffHelper;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;

import com.subex.automation.helpers.application.screens.BandHelper;
import com.subex.automation.helpers.application.screens.ElementCreateHelper;
import com.subex.automation.helpers.application.screens.TariffClassHelper;
import com.subex.automation.helpers.application.screens.TariffHelper;
import com.subex.automation.helpers.application.screens.TaskSearchHelper;
import com.subex.automation.helpers.util.FailureHelper;

public class TCSwitchToBand extends PSAcceptanceTest
{
	String path = System.getProperty( "user.dir" ) + "\\src\\main\\resources\\";
	String workBookName = "FunctionalTestCases.xlsx";
	String sheetName = "SwitchToBand";
	TaskSchedule taskObj = new TaskSchedule();
	PSTaskSearchHelper tskObj = new PSTaskSearchHelper();
	
	@org.testng.annotations.Test( priority = 1, description = "Account , Bill Profile and Route for STB", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void stbPrerequisite() throws Exception
	{
		try
		{
			Switch switchObj = new Switch( path, workBookName, "Switch", "SwitchElementsAssociation" );
			switchObj.configureSwitch();
			
			RouteGroup routeGrpObj = new RouteGroup( path, workBookName, "RouteGrp", "RouteGroup STB" );
			routeGrpObj.routeGrpCreation();

			Route routeColObj = new Route( path, workBookName, "Route", "Routes STB" );
			routeColObj.routeCreation();

			ElementCreateHelper eleObj = new ElementCreateHelper();
			eleObj.createElement( path, workBookName, "Elements", "Elements AdvanceRating", 1 );
			
			ElementCreateHelper ele1Obj = new ElementCreateHelper();
			ele1Obj.createElement( path, workBookName, "Elements", "Elements NonMatching", 1 );

			BandHelper bandObj = new BandHelper();
			bandObj.createBand( path, workBookName, "Bands", "Bands AdvanceRating", 1 );

			TariffClassHelper trffClassObj = new TariffClassHelper();
			trffClassObj.createTariffClass( path, workBookName, "TariffClass", "TariffClass STB", 1 );

		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 2, description = "EMR for STB", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void emrSTB() throws Exception
	{
		try
		{
			PSTariffHelper pstrffObj1 = new PSTariffHelper();
			pstrffObj1.createTariff( path, workBookName, "Tariff", "Tariff STB", 1 );
			pstrffObj1.createFastEntry( path, workBookName, "Tariff", "Tariff STBFastentry", 1 );
			

			EventMatchRuleGroup eventValObj = new EventMatchRuleGroup( path, workBookName, "EventMatchRuleGroup", "EMRG-CI", 1 );
			eventValObj.configureEventMatchRuleGroup();

			
			EventMatchRule emr1Obj = new EventMatchRule( path, workBookName, "EMR", "EventMatchRule STB" );
			emr1Obj.configureEventMatchRule();

		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}
	
	@org.testng.annotations.Test( priority = 3, description = "mnr and aggregation results- STB" )
	public void aggregationSTB() throws Exception
	{
		try
		{
			
			taskObj.fileCollection( path, workBookName, sheetName, "FileSchedule STB", 1 );
			
			tskObj.psVerifyTaskStatus( path, workBookName, sheetName, "MnrTaskStatus", 1 );			
	
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}
	
	@org.testng.annotations.Test( priority = 4, description = "mnr and aggregation results- STB" )
	public void recurringTaskSTB() throws Exception
	{
		try
		{
	
			taskObj.scheduleRecurringTask( path, workBookName, sheetName, "RecurringTask", 1 );
	
			tskObj.psVerifyTaskStatus( path, workBookName, sheetName, "AggrTaskStatus", 1 );
	
			AggregationResult aggrResObj = new AggregationResult( path, workBookName, sheetName, "AggregationResult STB" );
			aggrResObj.viewAggregationResult();
	
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}
	
	@org.testng.annotations.Test( priority = 5, description = "mnr and aggregation results- STB" )
	public void aggregationAdvanceRating() throws Exception
	{
		try
		{
	
		
			taskObj.fileCollection( path, workBookName, sheetName, "FileSchedule AdvRating", 1 );
			
			tskObj.psVerifyTaskStatus( path, workBookName, sheetName, "MnrTaskStatus", 1 );			
	
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}
	
	@org.testng.annotations.Test( priority = 6, description = "mnr and aggregation results- STB" )
	public void recurringTaskAdvanceRating() throws Exception
	{
		try
		{				
	
			taskObj.scheduleRecurringTask( path, workBookName, sheetName, "RecurringTask", 1 );
	
			tskObj.psVerifyTaskStatus( path, workBookName, sheetName, "AggrTaskStatus", 1 );
	
			AggregationResult aggrResObj = new AggregationResult( path, workBookName, sheetName, "AggregationResult AdvRating" );
			aggrResObj.viewAggregationResult();
	
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}
}
