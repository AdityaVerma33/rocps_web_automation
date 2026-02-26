package com.subex.rocps.automation.testcases.functionaltesting;

import com.subex.automation.helpers.util.FailureHelper;

import com.subex.rocps.automation.helpers.application.matchandrate.EventIdentiferDefinition;
import com.subex.rocps.automation.helpers.application.matchandrate.EventIdentifierValueGroup;
import com.subex.rocps.automation.helpers.application.networkConfiguraiton.RouteGroup;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;

public class TCEventIdentifierValueGroup extends PSAcceptanceTest
{

	String path = System.getProperty( "user.dir" ) + "\\src\\main\\resources\\";
	String workBookName = "FunctionalTestCases.xlsx";
	String sheetName = "EventIdentiferValueGroup";
	String sheetNameRG = "RouteGrp";
	String sheetNameDefn = "EventDefn";

	@org.testng.annotations.Test( priority = 1, description = "Event Identifier ValueGrp creation",retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void eventIdentifierValueGrpCreation() throws Exception
	{
		try
		{
			EventIdentifierValueGroup valueGrpObj = new EventIdentifierValueGroup( path, workBookName, sheetName, "EventValueGrp", 1 );
			valueGrpObj.eventValueGrpCreation();
		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}
	
	@org.testng.annotations.Test( priority = 2, description = "Event Identifier ValueGrp - with multiple values creation",retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void eventIdentifierValueGrpMultipleValCreation() throws Exception
	{
		try
		{
			EventIdentifierValueGroup valueGrpObj = new EventIdentifierValueGroup( path, workBookName, sheetName, "EventValueGrp MultipleValues", 1 );
			valueGrpObj.eventValueGrpCreation();
		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}
	
	@org.testng.annotations.Test( priority = 3, description = "Event Identifier ValueGrp search col Validation" )
	public void eventIdentifierValueGrpColVal() throws Exception
	{
		try
		{
			EventIdentifierValueGroup valueGrpDelObj = new EventIdentifierValueGroup( path, workBookName, sheetName, "EventValGrpSearchScreencolVal", 1 );
			valueGrpDelObj.searchScreenColumnsValidation();
		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}
	
	@org.testng.annotations.Test( priority = 4, description = "event identifier defn-String Multiple Match creation ",retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void eventIdentifierDefnEventtypeCreation() throws Exception
	{
		try
		{
			EventIdentiferDefinition eventObj = new EventIdentiferDefinition( path, workBookName, sheetNameDefn, "EventDefn RGMultiple", 1 );
			eventObj.eventCreation();
		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}
	
	@org.testng.annotations.Test( priority = 5, description = "RouteGrp creation",retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void routeGrpCreation() throws Exception
	{
		try
		{
			RouteGroup routeGrpObj = new RouteGroup( path, workBookName, sheetNameRG, "RouteGroupCreate1" );
			routeGrpObj.routeGrpCreation();
		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}
	
	@org.testng.annotations.Test( priority = 6, description = "RouteGrp creation",retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void routeGrpCreation2() throws Exception
	{
		try
		{
			RouteGroup routeGrpObj = new RouteGroup( path, workBookName, sheetNameRG, "RouteGroupCreate2" );
			routeGrpObj.routeGrpCreation();
		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}
	
	@org.testng.annotations.Test( priority = 7, description = "Event Identifier ValueGrp creation for route group",retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void eventIdentifierValueGrpCreationRG() throws Exception
	{
		try
		{
			EventIdentifierValueGroup valueGrpObj = new EventIdentifierValueGroup( path, workBookName, sheetName, "EventValueGrp2", 1 );
			valueGrpObj.eventValueGrpCreation();
		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}
	
	@org.testng.annotations.Test( priority = 8, description = "Event Identifier ValueGrp - with multiple values creation RG",retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void eventIdentifierValueGrpMultipleValCreationRG() throws Exception
	{
		try
		{
			EventIdentifierValueGroup valueGrpObj = new EventIdentifierValueGroup( path, workBookName, sheetName, "EventValueGrp MultipleValues2", 1 );
			valueGrpObj.eventValueGrpCreation();
		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}
	
	@org.testng.annotations.Test( priority = 9, description = "Event Identifier ValueGrp - for string match creation",retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void eventIdentifierValueGrpStringMatchCreation() throws Exception
	{
		try
		{
			EventIdentifierValueGroup valueGrpObj = new EventIdentifierValueGroup( path, workBookName, sheetName, "EventValueGrp MultipleValues3", 1 );
			valueGrpObj.eventValueGrpCreation();
		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}
	
	@org.testng.annotations.Test( priority = 10, description = "Event Identifier ValueGrp deletion" )
	public void eventIdentifierValueGrpDelete() throws Exception
	{
		try
		{
			EventIdentifierValueGroup valueGrpDelObj = new EventIdentifierValueGroup( path, workBookName, sheetName, "EventValueGrpDelete", 1 );
			valueGrpDelObj.eventIdentifierValueGrpDelete();
		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}
	
	@org.testng.annotations.Test( priority = 11, description = "Event Identifier ValueGrp un delete" )
	public void eventIdentifierValueGrpUnDelete() throws Exception
	{
		try
		{
			EventIdentifierValueGroup valueGrpDelObj = new EventIdentifierValueGroup( path, workBookName, sheetName, "EventValueGrpUnDelete", 1 );
			valueGrpDelObj.eventIdentifierValueGrpUnDelete();
		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 12, description = "Edi tEvent Identifier ValueGrp" )
	public void editEventIdentifierValueGrp() throws Exception
	{
		try
		{
			EventIdentifierValueGroup valueGrpObj = new EventIdentifierValueGroup( path, workBookName, sheetName, "EditEventValueGrp2", 1 );
			valueGrpObj.editeventValueGrp();
		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}
}
