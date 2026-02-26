package com.subex.rocps.automation.testcases.functionaltesting;

import com.subex.automation.helpers.application.screens.BandHelper;
import com.subex.automation.helpers.application.screens.ElementCreateHelper;
import com.subex.automation.helpers.application.screens.TariffClassHelper;
import com.subex.automation.helpers.application.screens.TariffHelper;
import com.subex.automation.helpers.util.FailureHelper;
import com.subex.rocps.automation.helpers.application.matchandrate.EventIdentiferDefinition;
import com.subex.rocps.automation.helpers.application.matchandrate.EventIdentifierValueGroup;
import com.subex.rocps.automation.helpers.application.matchandrate.EventMatchRule;
import com.subex.rocps.automation.helpers.application.matchandrate.EventMatchRuleGroup;
import com.subex.rocps.automation.helpers.application.matchandrate.Operator;
import com.subex.rocps.automation.helpers.application.networkConfiguraiton.DialStringSet;
import com.subex.rocps.automation.helpers.application.networkConfiguraiton.Route;
import com.subex.rocps.automation.helpers.application.networkConfiguraiton.RouteGroup;
import com.subex.rocps.automation.helpers.application.networkConfiguraiton.Switch;
import com.subex.rocps.automation.helpers.application.partnerConfiguration.Account;
import com.subex.rocps.automation.helpers.application.partnerConfiguration.Agent;
import com.subex.rocps.automation.helpers.application.partnerConfiguration.BillProfile;
import com.subex.rocps.automation.helpers.application.tariffs.PSTariffHelper;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;

public class TCVoiceSurchargeClientPrerequisite extends PSAcceptanceTest
{
	String path = System.getProperty( "user.dir" ) + "\\src\\main\\resources\\";
	String workBookName = "FunctionalTestCases.xlsx";
	String sheetName = "SurchargeClientPrerequisite";

	@org.testng.annotations.Test( priority = 1, enabled = true, description = "Bill Profile for VoiceSurchargeClientPrerequistes", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void VoiceSurchargeClientPrerequistes1() throws Exception

	{
		try
		{
			Agent agobj = new Agent( path, workBookName, "Agent", "Agent", 1 );
			agobj.agentCreation();

			Account accobj = new Account( path, workBookName, "SurchargeClientPrerequisite", "Account_VoiceSurcharge", 1 );
			accobj.accountCreation();

			BillProfile billObj = new BillProfile( path, workBookName, "SurchargeClientPrerequisite", "BillProfile_VoiceSurcharge", 1 );
			billObj.billProfileCreation();

		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 2, enabled = true, description = "Route for VoiceSurchargeClientPrerequistes", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void VoiceSurchargeClientPrerequistes2() throws Exception

	{
		try
		{

			Switch switchObj = new Switch( path, workBookName, "Switch", "Switch" );
			switchObj.configureSwitch();

			Operator ope1Obj = new Operator( path, workBookName, "SurchargeClientPrerequisite", "Operator_VoiceSurcharge", 1 );
			ope1Obj.operatorCreation();

			RouteGroup routeGrpObj = new RouteGroup( path, workBookName, "SurchargeClientPrerequisite", "RouteGroup_VoiceSurcharge" );
			routeGrpObj.routeGrpCreation();

			Route routeColObj = new Route( path, workBookName, "SurchargeClientPrerequisite", "Route_VoiceSurcharge" );
			routeColObj.routeCreation();

		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 3, enabled = true, description = "Tariff for VoiceSurchargeClientPrerequistes", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void VoiceSurchargeClientPrerequistes3() throws Exception

	{
		try
		{

			ElementCreateHelper eleObj = new ElementCreateHelper();
			eleObj.createElement( path, workBookName, "Elements", "Elements AdvanceRating", 1 );

			BandHelper bandObj = new BandHelper();
			bandObj.createBand( path, workBookName, "Bands", "Bands AdvanceRating", 1 );

			TariffClassHelper trffClassObj = new TariffClassHelper();
			trffClassObj.createTariffClass( path, workBookName, "TariffClass", "TariffClass OutAdvanceRating", 1 );

			PSTariffHelper pstrffObj1 = new PSTariffHelper();
			pstrffObj1.createTariff( path, workBookName, "Tariff", "Tariff Outadvance", 1 );
			pstrffObj1.createFastEntry( path, workBookName, "Tariff", "Tariff OutAdvanceFastEntry", 1 );

		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 4, enabled = true, description = "Tariff of surcharge for VoiceSurchargeClientPrerequistes", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void VoiceSurchargeClientPrerequistes4() throws Exception

	{
		try
		{

			PSTariffHelper pstrffObj1 = new PSTariffHelper();
			pstrffObj1.createTariff( path, workBookName, "SurchargeClientPrerequisite", "Tariff Outadvance_Surcharge", 1 );
			pstrffObj1.createFastEntry( path, workBookName, "SurchargeClientPrerequisite", "Tariff OutAdvanceFastEntry_Surcharge", 1 );

		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 5, description = "event identifier defn-Different Event type creation", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void eventIdentifierDefnValueGrpPrerequisite() throws Exception
	{
		try
		{
			EventIdentiferDefinition eventObj = new EventIdentiferDefinition( path, workBookName, "SurchargeClientPrerequisite", "EventIdenDfn_OutRG_VoiceSurcharge", 1 );
			eventObj.eventCreation();

		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 6, enabled = true, description = "Mnr for VoiceSurchargeClientPrerequistes", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void VoiceSurchargeClientPrerequistes5() throws Exception

	{
		try
		{

			EventMatchRuleGroup eventValObj = new EventMatchRuleGroup( path, workBookName, "SurchargeClientPrerequisite", "EMRG_Surcharge", 1 );
			eventValObj.configureEventMatchRuleGroup();

			EventMatchRule emrObj = new EventMatchRule( path, workBookName, "SurchargeClientPrerequisite", "EMR_Surcharge" );
			emrObj.configureEventMatchRule();

		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}
}
