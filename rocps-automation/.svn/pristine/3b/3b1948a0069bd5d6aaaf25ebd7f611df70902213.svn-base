package com.subex.rocps.automation.testcases.functionaltesting;

import com.subex.automation.helpers.util.FailureHelper;
import com.subex.rocps.automation.helpers.application.matchandrate.EventIdentiferDefinition;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;

public class TCEventIdentifierDefinition extends PSAcceptanceTest
{

	String path = System.getProperty( "user.dir" ) + "\\src\\main\\resources\\";
	String workBookName = "FunctionalTestCases.xlsx";
	String sheetName = "EventDefn";

	@org.testng.annotations.Test( priority = 1, description = "event identifier defn- String Match creation", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void eventIdentifierDefnStringMatchCreation() throws Exception
	{
		try
		{
			EventIdentiferDefinition eventObj = new EventIdentiferDefinition( path, workBookName, sheetName, "EventDefn StringMatch", 1 );
			eventObj.eventCreation();
		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 2, description = "event identifier defn-Route Group Match creation", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void eventIdentifierDefnRGCreation() throws Exception
	{
		try
		{
			EventIdentiferDefinition eventObj = new EventIdentiferDefinition( path, workBookName, sheetName, "EventDefn RG", 1 );
			eventObj.eventCreation();
		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 3, description = "event identifier defn-Dial Stringset creation", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void eventIdentifierDefnDialStringCreation() throws Exception
	{
		try
		{
			EventIdentiferDefinition eventObj = new EventIdentiferDefinition( path, workBookName, sheetName, "EventDefn DialString", 1 );
			eventObj.eventCreation();
		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 4, description = "event identifier defn-With critiria value creation", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void eventIdentifierDefnCriteriaCreation() throws Exception
	{
		try
		{
			EventIdentiferDefinition eventObj = new EventIdentiferDefinition( path, workBookName, sheetName, "EventDefn Criteria", 1 );
			eventObj.eventCreation();
		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 5, description = "event identifier defn-full Match creation", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void eventIdentifierDefnFullMatchCreation() throws Exception
	{
		try
		{
			EventIdentiferDefinition eventObj = new EventIdentiferDefinition( path, workBookName, sheetName, "EventDefn FullMatch", 1 );
			eventObj.eventCreation();
		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 6, description = "event identifier defn-SuffixMatch creation", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void eventIdentifierDefnSuffixMatchCreation() throws Exception
	{
		try
		{
			EventIdentiferDefinition eventObj = new EventIdentiferDefinition( path, workBookName, sheetName, "EventDefn suffixMatch", 1 );
			eventObj.eventCreation();
		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 7, description = "event identifier defn-Different Event type creation", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void eventIdentifierDefnEventtypeCreation() throws Exception
	{
		try
		{
			EventIdentiferDefinition eventObj = new EventIdentiferDefinition( path, workBookName, sheetName, "EvenDefn EventType", 1 );
			eventObj.eventCreation();
		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 8, description = "event identifier defn deletion", dependsOnMethods = "eventIdentifierDefnStringMatchCreation" )
	public void eventIdentifierDefinitionDelete() throws Exception
	{
		try
		{
			EventIdentiferDefinition eventdelObj = new EventIdentiferDefinition( path, workBookName, sheetName, "EventDefnDelete", 1 );
			eventdelObj.eventIdentifierDefnDelete();
		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 9, description = "event identifier defn un delete", dependsOnMethods = "eventIdentifierDefinitionDelete" )
	public void eventIdentifierDefinitionUnDelete() throws Exception
	{
		try
		{
			EventIdentiferDefinition eventundelObj = new EventIdentiferDefinition( path, workBookName, sheetName, "EventDefnUnDelete", 1 );
			eventundelObj.eventIdentifierDefnUnDelete();
		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 10, description = "event identifier defn col Val" )
	public void eventIdentifierDefinitionColVal() throws Exception
	{
		try
		{
			EventIdentiferDefinition eventcolValObj = new EventIdentiferDefinition( path, workBookName, sheetName, "EventDefnSearchScreencolVal", 1 );
			eventcolValObj.searchScreenColumnsValidation();
		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 11, description = "event identifier defn reorder", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void eventIdentifierDefinitionReorder() throws Exception
	{
		try
		{
			EventIdentiferDefinition eventcolValObj = new EventIdentiferDefinition( path, workBookName, sheetName, "Reorder", 1 );
			eventcolValObj.reOrderAction();
		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 12, description = "Edit event identifier defn-Different Event type creation", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void editEventIdentifierDefn() throws Exception
	{
		try
		{
			EventIdentiferDefinition eventObj = new EventIdentiferDefinition( path, workBookName, sheetName, "EditEventDefn RGMultiple", 1 );
			eventObj.editEventIdentifierDefinition();
		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}

}
