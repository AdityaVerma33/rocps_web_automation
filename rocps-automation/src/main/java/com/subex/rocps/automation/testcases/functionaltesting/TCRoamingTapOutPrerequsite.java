package com.subex.rocps.automation.testcases.functionaltesting;

import com.subex.rocps.automation.helpers.application.matchandrate.EventIdentiferDefinition;
import com.subex.rocps.automation.helpers.application.matchandrate.EventIdentifierValue;
import com.subex.rocps.automation.helpers.application.matchandrate.EventIdentifierValueGroup;
import com.subex.rocps.automation.helpers.application.matchandrate.EventMatchRule;
import com.subex.rocps.automation.helpers.application.matchandrate.EventMatchRuleGroup;
import com.subex.rocps.automation.helpers.application.partnerConfiguration.Account;
import com.subex.rocps.automation.helpers.application.partnerConfiguration.Agent;
import com.subex.rocps.automation.helpers.application.partnerConfiguration.BillProfile;
import com.subex.rocps.automation.helpers.application.referenceTable.RoamingSerMatchExpresion;
import com.subex.rocps.automation.helpers.application.tariffs.PSTariffHelper;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;

import com.subex.automation.helpers.application.screens.BandHelper;
import com.subex.automation.helpers.application.screens.ElementCreateHelper;
import com.subex.automation.helpers.application.screens.TariffClassHelper;
import com.subex.automation.helpers.application.screens.TariffHelper;
import com.subex.automation.helpers.util.FailureHelper;

public class TCRoamingTapOutPrerequsite extends PSAcceptanceTest
{

	String path = System.getProperty( "user.dir" ) + "\\src\\main\\resources\\";

	String workBookName = "FunctionalTestCases.xlsx";
	String sheetName = "RoamingTapOutPrerequisite";

	@org.testng.annotations.Test( priority = 1, enabled = true, description = " Prerequisite for TapOut Client prequisite Account, Bill profile creation", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void tapOutClientPrerequisites1() throws Exception

	{
		try
		{
			Agent agobj = new Agent( path, workBookName, "Agent", "Agent", 1 );
			agobj.agentCreation();

			Account accobj = new Account( path, workBookName, "Account", "Account_Roaming", 1 );
			accobj.accountCreation();

			BillProfile billObj = new BillProfile( path, workBookName, "BillProfile", "BillProfile_Roaming", 1 );
			billObj.billProfileCreation();
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 2, enabled = true, description = " Prerequisite for TapOut Client prequisite 2", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void tapOutClientPrerequisites2() throws Exception

	{
		try
		{

			ElementCreateHelper eleObj = new ElementCreateHelper();
			eleObj.createElement( path, workBookName, "Elements", "Elements Incoming", 1 );

			BandHelper bandObj = new BandHelper();
			bandObj.createBand( path, workBookName, "Bands", "Bands Incoming", 1 );

			TariffClassHelper trffClassObj = new TariffClassHelper();
			trffClassObj.createTariffClass( path, workBookName, "TariffClass", "TariffClass Incoming", 1 );

			PSTariffHelper pstrffObj1 = new PSTariffHelper();
			pstrffObj1.createTariff( path, workBookName, "Tariff", "Tariff Incoming", 1 );

			pstrffObj1.createFastEntry( path, workBookName, "Tariff", "Tariff Incoming FastEntry", 1 );

		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 3, enabled = true, description = "Prerequisiste for TapOut Voice MTC-1", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void tapOutVoiceMTCPrerequistes() throws Exception

	{
		try
		{
			EventIdentiferDefinition eventObj = new EventIdentiferDefinition( path, workBookName, sheetName, "EventDefnVoice_MTC", 1 );
			eventObj.eventCreation();

			EventIdentifierValue eventValObj = new EventIdentifierValue( path, workBookName, sheetName, "EventIdentifierValueVoice_MTC", 1 );
			eventValObj.eventIdentifierValue();

			EventIdentifierValueGroup valueGrpObj = new EventIdentifierValueGroup( path, workBookName, sheetName, "EventValueGrpVoice_MTC", 1 );
			valueGrpObj.eventValueGrpCreation();

		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 4, enabled = true, description = " MNR Prerequisiste for TapOut Voice MTC", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void tapOutVoiceMTCPrerequistes2() throws Exception

	{
		try
		{

			EventMatchRuleGroup eventValObj = new EventMatchRuleGroup( path, workBookName, sheetName, "EMRG Voice_MTC", 1 );
			eventValObj.configureEventMatchRuleGroup();

			EventMatchRule emrObj = new EventMatchRule( path, workBookName, sheetName, "EMR_Voice_MTC" );
			emrObj.configureEventMatchRule();

		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 5, enabled = true, description = "Prerequisiste for TapOut Voice MOC-1", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void tapOutVoiceMOCPrerequistes() throws Exception

	{
		try
		{
			EventIdentiferDefinition eventObj = new EventIdentiferDefinition( path, workBookName, sheetName, "EventDefnVoice_MOC", 1 );
			eventObj.eventCreation();

			EventIdentifierValue eventValObj = new EventIdentifierValue( path, workBookName, sheetName, "EventIdentifierValueVoice_MOC", 1 );
			eventValObj.eventIdentifierValue();

			EventIdentifierValueGroup valueGrpObj = new EventIdentifierValueGroup( path, workBookName, sheetName, "EventValueGrpVoice_MOC", 1 );
			valueGrpObj.eventValueGrpCreation();

		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 6, enabled = true, description = " MNR Prerequisiste for TapOut Voice MTC", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void tapOutVoiceMOCPrerequistes2() throws Exception

	{
		try
		{

			EventMatchRuleGroup eventValObj = new EventMatchRuleGroup( path, workBookName, sheetName, "EMRG Voice_MOC", 1 );
			eventValObj.configureEventMatchRuleGroup();

			EventMatchRule emrObj = new EventMatchRule( path, workBookName, sheetName, "EMR_Voice_MOC" );
			emrObj.configureEventMatchRule();

		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 7, enabled = true, description = "Prerequisiste for TapOut  SMS", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void tapOutSMSPrerequistes() throws Exception

	{
		try
		{
		

			EventIdentiferDefinition eventObj = new EventIdentiferDefinition( path, workBookName, sheetName, "EventDefnSMS_IMSI", 1 );
			eventObj.eventCreation();

			EventIdentifierValue eventValObj = new EventIdentifierValue( path, workBookName, sheetName, "EventIdentifierValueSMS", 1 );
			eventValObj.eventIdentifierValue();

			EventIdentifierValueGroup valueGrpObj = new EventIdentifierValueGroup( path, workBookName, sheetName, "EventValueGrpSMS", 1 );
			valueGrpObj.eventValueGrpCreation();

		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 8, enabled = true, description = " MNR Prerequisiste for TapOut SMS", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void tapOutSMSPrerequistes2() throws Exception

	{
		try
		{

			EventMatchRuleGroup eventValObj = new EventMatchRuleGroup( path, workBookName, sheetName, "EMRG SMS", 1 );
			eventValObj.configureEventMatchRuleGroup();

			EventMatchRule emrObj = new EventMatchRule( path, workBookName, sheetName, "EMR_SMS" );
			emrObj.configureEventMatchRule();

		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}

	//@org.testng.annotations.Test( priority = 9, enabled = true, description = "Prerequisiste for TapOut  GPRS", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void tapOutGPRSPrerequistes() throws Exception

	{
		try
		{
			EventIdentiferDefinition eventObj = new EventIdentiferDefinition( path, workBookName, sheetName, "EventDefnGPRS", 1 );
			eventObj.eventCreation();

			EventIdentifierValue eventValObj = new EventIdentifierValue( path, workBookName, sheetName, "EventIdentifierValueGPRS", 1 );
			eventValObj.eventIdentifierValue();

			EventIdentifierValueGroup valueGrpObj = new EventIdentifierValueGroup( path, workBookName, sheetName, "EventValueGrpGPRS", 1 );
			valueGrpObj.eventValueGrpCreation();

		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}

	//@org.testng.annotations.Test( priority = 10, enabled = true, description = " MNR Prerequisiste for TapOut GPRS", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void tapOutGPRSPrerequistes2() throws Exception

	{
		try
		{

			EventMatchRuleGroup eventValObj = new EventMatchRuleGroup( path, workBookName, sheetName, "EMRG GPRS", 1 );
			eventValObj.configureEventMatchRuleGroup();

			EventMatchRule emrObj = new EventMatchRule( path, workBookName, sheetName, "EMR_GPRS" );
			emrObj.configureEventMatchRule();

		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}

}
