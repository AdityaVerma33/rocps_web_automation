package com.subex.rocps.automation.testcases.functionaltesting;

import com.subex.automation.helpers.util.FailureHelper;
import com.subex.rocps.automation.helpers.application.matchandrate.EventModellingDefinition;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;

public class TCEventModellingDefinition extends PSAcceptanceTest
{

	String path = System.getProperty( "user.dir" ) + "\\src\\main\\resources\\";
	String workBookName = "FunctionalTestCases.xlsx";
	String sheetName = "EventModellingDefn";

	@org.testng.annotations.Test( priority = 1, description = "event Modelling defn-Edit",retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void eventModellingDefinitionEdit() throws Exception
	{
		try
		{
			EventModellingDefinition eventeditObj = new EventModellingDefinition( path, workBookName, sheetName, "EventModellingDefnEdit", 1 );
			eventeditObj.modifyEventModellingDefn();
		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 2, description = "event Modelling defn - View Config Mapping",retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void eventModellingDefinitionView() throws Exception
	{
		try
		{
			EventModellingDefinition eventviewObj = new EventModellingDefinition( path, workBookName, sheetName, "EventModellingDefnView", 1 );
			eventviewObj.modifyEventModellingDefn();
		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 3, description = "EventModellingDefn creation with Normalization Event" ,retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class)
	public void createEventModDefnWithNormEvent() throws Exception
	{
		try
		{

			EventModellingDefinition eventMDefnob = new EventModellingDefinition( path, workBookName, sheetName, "EventModDefnNewWithNormEvent" );
			eventMDefnob.eventModellingDefnCreation();
		}

		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}

	}

	@org.testng.annotations.Test( priority = 4, description = "EventModellingDefn creation without Normaliation Event ",retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void createEventModDefnWithoutNormEvent() throws Exception
	{
		try
		{

			EventModellingDefinition eventMDefnob = new EventModellingDefinition( path, workBookName, sheetName, "EventModDefnNewWithoutNormEvent" );
			eventMDefnob.eventModellingDefnCreation();
		}

		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}

	}

	@org.testng.annotations.Test( priority = 5, description = "Validate Table Columns of event Modelling defn Configure Search Grid",retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void ValidateTableColmnEventModDefnConfgSearchGrid() throws Exception
	{
		try
		{

			EventModellingDefinition eventMDefnob = new EventModellingDefinition( path, workBookName, sheetName, "EveModConfgSearchGridValidateCoumn" );
			eventMDefnob.verifyTheColHdrsOfConfgSearchGrid();
		}

		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 6, description = "event Modelling defn Configure Search Grid for Usage",retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void eventModDefnConfgSearchGridForUsage() throws Exception
	{
		try
		{

			EventModellingDefinition eventMDefnob = new EventModellingDefinition( path, workBookName, sheetName, "EventModConfgSearchGridUsage" );
			eventMDefnob.confgSearchGridAction();
		}

		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 7, description = "event Modelling defn deletion" )
	public void eventModellingDefinitionDelete() throws Exception
	{
		try
		{
			EventModellingDefinition eventdelObj = new EventModellingDefinition( path, workBookName, sheetName, "EventModellingDefnDelete", 1 );
			eventdelObj.eventModellingDefnDelete();
		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 8, description = "event Modelling defn un delete" )
	public void eventModellingDefinitionUnDelete() throws Exception
	{
		try
		{
			EventModellingDefinition eventundelObj = new EventModellingDefinition( path, workBookName, sheetName, "EventModellingDefnUnDelete", 1 );
			eventundelObj.eventModellingDefnUnDelete();
		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 9, description = "event Modelling defn search screen column validation" )
	public void eventModellingDefnColVal() throws Exception
	{
		try
		{
			EventModellingDefinition eventColValObj = new EventModellingDefinition( path, workBookName, sheetName, "EventDefnSearchScreencolVal", 1 );
			eventColValObj.searchScreenColumnsValidation();
		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}

}
