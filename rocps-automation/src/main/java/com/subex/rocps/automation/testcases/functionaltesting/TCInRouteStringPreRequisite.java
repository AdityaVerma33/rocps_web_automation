package com.subex.rocps.automation.testcases.functionaltesting;

import com.subex.automation.helpers.application.screens.BandHelper;
import com.subex.automation.helpers.application.screens.ElementCreateHelper;
import com.subex.automation.helpers.application.screens.TariffClassHelper;
import com.subex.automation.helpers.application.screens.TariffHelper;
import com.subex.automation.helpers.util.FailureHelper;
import com.subex.rocps.automation.helpers.application.matchandrate.EventIdentiferDefinition;
import com.subex.rocps.automation.helpers.application.matchandrate.EventIdentifierValue;
import com.subex.rocps.automation.helpers.application.matchandrate.EventMatchRule;
import com.subex.rocps.automation.helpers.application.matchandrate.EventMatchRuleGroup;
import com.subex.rocps.automation.helpers.application.matchandrate.Operator;
import com.subex.rocps.automation.helpers.application.networkConfiguraiton.Route;
import com.subex.rocps.automation.helpers.application.networkConfiguraiton.RouteGroup;
import com.subex.rocps.automation.helpers.application.networkConfiguraiton.Switch;
import com.subex.rocps.automation.helpers.application.partnerConfiguration.Account;
import com.subex.rocps.automation.helpers.application.partnerConfiguration.Agent;
import com.subex.rocps.automation.helpers.application.partnerConfiguration.BillProfile;
import com.subex.rocps.automation.helpers.application.tariffs.PSTariffHelper;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;

public class TCInRouteStringPreRequisite extends PSAcceptanceTest
{
	String path = System.getProperty( "user.dir" ) + "\\src\\main\\resources\\";

	String workBookName = "FunctionalTestCases.xlsx";
	String sheetName = "InRouteStringMnrPrerequisite";

	@org.testng.annotations.Test( priority = 1, enabled = true, description = "Bill Profile, Route for in RouteString Prerequistes", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void inRouteStringPrerequistes_BillProfile_Route() throws Exception

	{
		try
		{
			Agent agobj = new Agent( path, workBookName, "Agent", "Agent", 1 );
			agobj.agentCreation();

			Account accobj = new Account( path, workBookName, "InRouteStringMnrPrerequisite", "Account_InRouteString", 1 );
			accobj.accountCreation();

			BillProfile billObj = new BillProfile( path, workBookName, "InRouteStringMnrPrerequisite", "BillProfile_InRouteString", 1 );
			billObj.billProfileCreation();

			Switch switchObj = new Switch( path, workBookName, "InRouteStringMnrPrerequisite", "Switch_InRouteString" );
			switchObj.configureSwitch();

			Operator ope1Obj = new Operator( path, workBookName, "InRouteStringMnrPrerequisite", "Operator_InRouteString", 1 );
			ope1Obj.operatorCreation();

			RouteGroup routeGrpObj = new RouteGroup( path, workBookName, "InRouteStringMnrPrerequisite", "RouteGroup_In_InRouteString" );
			routeGrpObj.routeGrpCreation();

			Route routeColObj = new Route( path, workBookName, "InRouteStringMnrPrerequisite", "Route_In_InRouteString" );
			routeColObj.routeCreation();

		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 2, description = "Event identifier Defn, value creation for in RouteString Prerequistes", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void inRouteStringPrerequiste_EvenIdentDfnValue() throws Exception
	{
		try
		{
			EventIdentiferDefinition eventObj = new EventIdentiferDefinition( path, workBookName, "InRouteStringMnrPrerequisite", "EventDefn_InRouteString_StringMatch", 1 );
			eventObj.eventCreation();

			EventIdentifierValue eventValObj = new EventIdentifierValue( path, workBookName, "InRouteStringMnrPrerequisite", "EventIdenValue_InRouteString_StringMatch", 1 );
			eventValObj.eventIdentifierValue();

		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 3, enabled = true, description = "Element, Band, Tariff for inRouteStringPrerequistes", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void inRouteStringPrerequistes_ElemBandTariff() throws Exception

	{
		try
		{

			ElementCreateHelper eleObj = new ElementCreateHelper();
			eleObj.createElement( path, workBookName, "Elements", "Elements Transit", 1 );

			BandHelper bandObj = new BandHelper();
			bandObj.createBand( path, workBookName, "Bands", "Bands Transit", 1 );

			TariffClassHelper trffClassObj = new TariffClassHelper();
			trffClassObj.createTariffClass( path, workBookName, "TariffClass", "TariffClass Transit", 1 );

			PSTariffHelper pstrffObj1 = new PSTariffHelper();
			pstrffObj1.createTariff( path, workBookName, "Tariff", "Tariff Transit", 1 );
			pstrffObj1.createFastEntry( path, workBookName, "Tariff", "Tariff Transit FastEntry", 1 );

		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 4, enabled = true, description = "Event matchRule for inRouteStringPrerequistes ", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void inRouteStringPrerequistes_EMR() throws Exception

	{
		try
		{

			EventMatchRuleGroup eventValObj = new EventMatchRuleGroup( path, workBookName, "InRouteStringMnrPrerequisite", "EMRG_InRouteString", 1 );
			eventValObj.configureEventMatchRuleGroup();

			EventMatchRule emrObj = new EventMatchRule( path, workBookName, "InRouteStringMnrPrerequisite", "EMR_InRouteString" );
			emrObj.configureEventMatchRule();

		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}
}
