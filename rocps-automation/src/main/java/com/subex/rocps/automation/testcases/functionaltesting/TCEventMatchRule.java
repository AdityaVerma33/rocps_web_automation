package com.subex.rocps.automation.testcases.functionaltesting;

import com.subex.rocps.automation.helpers.application.matchandrate.EventMatchRule;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;

import com.subex.automation.helpers.application.screens.BandHelper;
import com.subex.automation.helpers.application.screens.ElementCreateHelper;
import com.subex.automation.helpers.application.screens.TariffClassHelper;
import com.subex.automation.helpers.application.screens.TariffHelper;
import com.subex.automation.helpers.util.FailureHelper;

public class TCEventMatchRule extends PSAcceptanceTest
{

	String path = System.getProperty( "user.dir" ) + "\\src\\main\\resources\\";
	String workBookName = "FunctionalTestCases.xlsx";
	String sheetName = "EMR";

	@org.testng.annotations.Test( priority = 1, description = "EventMatchRule - Outgoing creation", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void eventMatchRuleOutgoingCreation() throws Exception
	{
		try
		{
			EventMatchRule emrObj = new EventMatchRule( path, workBookName, sheetName, "EMR Ougoing" );
			emrObj.configureEventMatchRule();
		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 2, description = "EventMatchRule- Incoming creation", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void eventMatchRuleIncomingCreation() throws Exception
	{
		try
		{
			EventMatchRule emrObj = new EventMatchRule( path, workBookName, sheetName, "EMR Incoming" );
			emrObj.configureEventMatchRule();
		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 3, description = "EventMatchRule -RevenueSharing creation", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void eventMatchRuleRevenueSharingCreation() throws Exception
	{
		try
		{
			EventMatchRule emrObj = new EventMatchRule( path, workBookName, sheetName, "EMR RevenueSharing" );
			emrObj.configureEventMatchRule();
		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 4, description = "EventMatchRule-EMR Transit creation", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void eventMatchRuleTransitCreation() throws Exception
	{
		try
		{
			EventMatchRule emrObj = new EventMatchRule( path, workBookName, sheetName, "EMR Transit" );
			emrObj.configureEventMatchRule();
		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 5, description = "EventMatchRule-AnoInroute-2defn creation", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void eventMatchRuleANoDefnsCreation() throws Exception
	{
		try
		{
			EventMatchRule emrObj = new EventMatchRule( path, workBookName, sheetName, "EMR AnoInroute-2defn" );
			emrObj.configureEventMatchRule();
		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 6, description = "EventMatchRule-OutRating creation", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void eventMatchRuleOutRatingCreation() throws Exception
	{
		try
		{
			EventMatchRule emrObj = new EventMatchRule( path, workBookName, sheetName, "EventMatchRule OutAdvance" );
			emrObj.configureEventMatchRule();
		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 7, description = "EventMatchRule-InAdvanceRAting creation", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void eventMatchRuleInRatingCreation() throws Exception
	{
		try
		{
			EventMatchRule emrObj = new EventMatchRule( path, workBookName, sheetName, "EventMatchRule InAdvance" );
			emrObj.configureEventMatchRule();
		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 8, description = "EventMatchRule deletion" )
	public void eventMatchRuleDelete() throws Exception
	{
		try
		{
			EventMatchRule emrDelObj = new EventMatchRule( path, workBookName, sheetName, "EventMatchRuleDelete" );
			emrDelObj.eventMatchRuleDelete();
		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 9, description = "EventMatchRule un delete" )
	public void eventMatchRuleUnDelete() throws Exception
	{
		try
		{
			EventMatchRule emrUnDelObj = new EventMatchRule( path, workBookName, sheetName, "EventMatchRuleUnDelete" );
			emrUnDelObj.eventMatchRuleUnDelete();
		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 10, description = "EventMatchRule search screen col validation", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void eventMatchRuleColVal() throws Exception
	{
		try
		{
			EventMatchRule emrColValObj = new EventMatchRule( path, workBookName, sheetName, "EMRSearchScreencolVal" );
			emrColValObj.searchScreenColumnsValidation();
		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}

}
