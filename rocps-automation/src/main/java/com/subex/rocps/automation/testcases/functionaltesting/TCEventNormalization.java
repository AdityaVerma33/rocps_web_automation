package com.subex.rocps.automation.testcases.functionaltesting;

import com.subex.automation.helpers.util.FailureHelper;
import com.subex.rocps.automation.helpers.application.matchandrate.EventNormalization;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;

public class TCEventNormalization extends PSAcceptanceTest
{

	String path = System.getProperty( "user.dir" ) + "\\src\\main\\resources\\";
	String workBookName = "FunctionalTestCases.xlsx";
	String sheetName = "EventNormalization";

	@org.testng.annotations.Test( priority = 1, description = "Event Normalization creation", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void eventNormalizationCreation() throws Exception
	{
		try
		{
			EventNormalization eventNorObj = new EventNormalization( path, workBookName, sheetName, "AssignComponent" );
			eventNorObj.eventNormalizationCreation();
		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 2, description = "Event Normalization-RouteLookupComp creation", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void eventNorRouteLookupCompCreation() throws Exception
	{
		try
		{
			EventNormalization eventNorObj = new EventNormalization( path, workBookName, sheetName, "RouteLookupComp" );
			eventNorObj.eventNormalizationCreation();
		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 3, description = "Event Normalization-RuleStringComponent creation", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void eventNorRuleStringComponentCreation() throws Exception
	{
		try
		{
			EventNormalization eventNorObj = new EventNormalization( path, workBookName, sheetName, "RuleStringComponent" );
			eventNorObj.eventNormalizationCreation();
		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 4, description = "Event Normalization- CurrencyLookUpComponent creation", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void eventNorCurrencyLookUpComponentCreation() throws Exception
	{
		try
		{
			EventNormalization eventNorObj = new EventNormalization( path, workBookName, sheetName, "CurrencyLookUpComponent" );
			eventNorObj.eventNormalizationCreation();
		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 5, description = "Event Normalization-Prerating creation", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void eventNorPreratingCreation() throws Exception
	{
		try
		{
			EventNormalization eventNorObj = new EventNormalization( path, workBookName, sheetName, "Prerating" );
			eventNorObj.eventNormalizationCreation();
		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 6, description = "Event Normalization-Voice Surcharge creation", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void eventNorVoicSurchargeCreation() throws Exception
	{
		try
		{
			EventNormalization eventNorObj = new EventNormalization( path, workBookName, sheetName, "Voice-Surcharge AssignComponent" );
			eventNorObj.eventNormalizationCreation();
		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 7, description = "Edit Event Normalization-Voice Surcharge", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void editeventNorVoicSurchargeCreation() throws Exception
	{
		try
		{
			EventNormalization eventNorObj = new EventNormalization( path, workBookName, sheetName, "EditVoice-Surcharge AssignComponent" );
			eventNorObj.editEventNormalization();
		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 8, description = "Event Normalization deletion", dependsOnMethods = "eventNormalizationCreation" )
	public void eventNormalizationDelete() throws Exception
	{
		try
		{
			EventNormalization eventNorObj = new EventNormalization( path, workBookName, sheetName, "AssignComponentDelete" );
			eventNorObj.eventNormalizationDelete();
		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 9, description = "Event Normalization un delete", dependsOnMethods = "eventNormalizationDelete" )
	public void eventNormalizationUnDelete() throws Exception
	{
		try
		{
			EventNormalization eventNorUnDelObj = new EventNormalization( path, workBookName, sheetName, "AssignComponentUnDelete" );
			eventNorUnDelObj.eventNormalizationUnDelete();
		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 10, description = "Event Normalization earch screen col validation" )
	public void eventNormalizationColVal() throws Exception
	{
		try
		{
			EventNormalization eventNorValObj = new EventNormalization( path, workBookName, sheetName, "EventNorSearchScreencolVal" );
			eventNorValObj.searchScreenColumnsValidation();
		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}

}
