package com.subex.rocps.automation.testcases.functionaltesting;

import com.subex.automation.helpers.util.FailureHelper;
import com.subex.rocps.automation.helpers.application.matchandrate.EventMatchRuleGroup;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;

public class TCEventMatchRuleGroup extends PSAcceptanceTest
{
	String path = System.getProperty( "user.dir" ) + "\\src\\main\\resources\\";
	String workBookName = "FunctionalTestCases.xlsx";
	String sheetName = "EventMatchRuleGroup";

	@org.testng.annotations.Test( priority = 1, description = "event MatchRuleGroup - Prerating creation", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void eventMatchRuleGroupPreratingCreation() throws Exception
	{
		try
		{
			EventMatchRuleGroup eventValObj = new EventMatchRuleGroup( path, workBookName, sheetName, "EMRG Prerating", 1 );
			eventValObj.configureEventMatchRuleGroup();
		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 2, description = "event MatchRuleGroup creation", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void eventMatchRuleGroupCreation() throws Exception
	{
		try
		{
			EventMatchRuleGroup eventValObj = new EventMatchRuleGroup( path, workBookName, sheetName, "EMRG Incoming", 1 );
			eventValObj.configureEventMatchRuleGroup();
		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 3, description = "event MatchRuleGroup - Outgoing creation", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void eventMatchRuleGroupOutgoingCreation() throws Exception
	{
		try
		{
			EventMatchRuleGroup eventValObj = new EventMatchRuleGroup( path, workBookName, sheetName, "EMRG Outgoing", 1 );
			eventValObj.configureEventMatchRuleGroup();
		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 4, description = "event MatchRuleGroup - Transit creation", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void eventMatchRuleGroupTransitCreation() throws Exception
	{
		try
		{
			EventMatchRuleGroup eventValObj = new EventMatchRuleGroup( path, workBookName, sheetName, "EMRG Transit", 1 );
			eventValObj.configureEventMatchRuleGroup();
		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 5, description = "event MatchRuleGroup with 2 Identifier Definitions creation", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void eventMatchRuleGroup2DefnsCreation() throws Exception
	{
		try
		{
			EventMatchRuleGroup eventValObj = new EventMatchRuleGroup( path, workBookName, sheetName, "EMRG-2defn", 1 );
			eventValObj.configureEventMatchRuleGroup();
		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 6, description = "event MatchRuleGroup - Advanced rating creation", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void eventMatchRuleGroupOutRatingCreation() throws Exception
	{
		try
		{
			EventMatchRuleGroup eventValObj = new EventMatchRuleGroup( path, workBookName, sheetName, "EMRG  OutAdvancedRating", 1 );
			eventValObj.configureEventMatchRuleGroup();
		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 7, description = "event MatchRuleGroup - Advanced rating creation", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void eventMatchRuleGroupInRatingCreation() throws Exception
	{
		try
		{
			EventMatchRuleGroup eventValObj = new EventMatchRuleGroup( path, workBookName, sheetName, "EMRG InAdvancedRating", 1 );
			eventValObj.configureEventMatchRuleGroup();
		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 8, description = "event MatchRuleGroup deletion" )
	public void eventMatchRuleGroupDelete() throws Exception
	{
		try
		{
			EventMatchRuleGroup eventValDelObj = new EventMatchRuleGroup( path, workBookName, sheetName, "EventMatchRuleGroupDelete", 1 );
			eventValDelObj.eventMatchRuleGrpDelete();
		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 9, description = "event MatchRuleGroup delete" )
	public void eventIdentifierValueUnDelete() throws Exception
	{
		try
		{
			EventMatchRuleGroup eventValUnDelObj = new EventMatchRuleGroup( path, workBookName, sheetName, "EventMatchRuleGroupUnDelete", 1 );
			eventValUnDelObj.eventMatchRuleGrpUnDelete();
		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 10, description = "event MatchRuleGroup search screen col validation" )
	public void eventIdentifierValueColVal() throws Exception
	{
		try
		{
			EventMatchRuleGroup eventValObj = new EventMatchRuleGroup( path, workBookName, sheetName, "EMRGSearchScreencolVal", 1 );
			eventValObj.searchScreenColumnsValidation();
		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 11, description = "edit event MatchRuleGroup" )
	public void editEventMatchRuleGroupInRatingCreation() throws Exception
	{
		try
		{
			EventMatchRuleGroup eventValObj = new EventMatchRuleGroup( path, workBookName, sheetName, "EditEMRG InAdvancedRating", 1 );
			eventValObj.editEventMatchRuleGroup();
		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}

}
