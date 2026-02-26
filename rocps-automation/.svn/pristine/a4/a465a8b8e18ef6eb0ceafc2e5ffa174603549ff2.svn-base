package com.subex.rocps.automation.testcases.functionaltesting;

import com.subex.automation.helpers.application.screens.BandHelper;
import com.subex.automation.helpers.application.screens.ElementCreateHelper;
import com.subex.automation.helpers.application.screens.TariffClassHelper;
import com.subex.automation.helpers.application.screens.TariffHelper;
import com.subex.automation.helpers.util.FailureHelper;
import com.subex.rocps.automation.helpers.application.matchandrate.EventMatchRule;
import com.subex.rocps.automation.helpers.application.matchandrate.EventMatchRuleGroup;
import com.subex.rocps.automation.helpers.application.matchandrate.Operator;
import com.subex.rocps.automation.helpers.application.networkConfiguraiton.Route;
import com.subex.rocps.automation.helpers.application.networkConfiguraiton.RouteGroup;
import com.subex.rocps.automation.helpers.application.networkConfiguraiton.Switch;
import com.subex.rocps.automation.helpers.application.partnerConfiguration.Account;
import com.subex.rocps.automation.helpers.application.partnerConfiguration.Agent;
import com.subex.rocps.automation.helpers.application.partnerConfiguration.BillProfile;
import com.subex.rocps.automation.helpers.application.system.Streams;
import com.subex.rocps.automation.helpers.application.tariffs.PSTariffHelper;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;

public class TCUsgBackoutReqPrerequisite extends PSAcceptanceTest
{
	String path = System.getProperty( "user.dir" ) + "\\src\\main\\resources\\";

	String workBookName = "FunctionalTestCases.xlsx";
	String sheetName = "UsageBackoutRequest";

	@org.testng.annotations.Test( priority = 1, enabled = true, description = "edit Voice stream add'UsageBackout Master Request and UsageBackout stream stages'", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void editVoiceStreamForUsgBackoutReq() throws Exception
	{
		try
		{
			String partition = null;
			Streams streamObj = new Streams();
			streamObj.editStreamConfig( path, workBookName, "UsageBackoutRequest", "VoiceStreams", 1 );
			streamObj.usgBackoutMasterReqStreamStageConfig( path, workBookName, sheetName, "UsageBackoutMasterRequest_StreamStage", 1 );
			streamObj.usgBackoutStreamStageConfig( path, workBookName, sheetName, "UsageBackout_StreamStage", 1 );
			streamObj.saveStreamDetail();

		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 2, enabled = true, description = "Bill Profile, Route for usgBackoutReqPrerequistes", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void usgBackoutReqPrerequistes() throws Exception

	{
		try
		{
			Agent agobj = new Agent( path, workBookName, "Agent", "Agent", 1 );
			agobj.agentCreation();

			Account accobj = new Account( path, workBookName, "Account", "Account_Dispute", 1 );
			accobj.accountCreation();

			BillProfile billObj = new BillProfile( path, workBookName, "BillProfile", "BillProfile_Dispute", 1 );
			billObj.billProfileCreation();

			Switch switchObj = new Switch( path, workBookName, "Switch", "Switch" );
			switchObj.configureSwitch();

			Operator ope1Obj = new Operator( path, workBookName, "Operator", "Operator_Dispute", 1 );
			ope1Obj.operatorCreation();

			RouteGroup routeGrpObj = new RouteGroup( path, workBookName, "RouteGrp", "RouteGroup Transit_Dispute" );
			routeGrpObj.routeGrpCreation();

			Route routeColObj = new Route( path, workBookName, "Route", "Route Transit_Dispute" );
			routeColObj.routeCreation();

		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 3, enabled = true, description = "Element, Band, Tariff for usgBackoutReqPrerequistes", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void usgBackoutReqPrerequistes2() throws Exception

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

	@org.testng.annotations.Test( priority = 4, enabled = true, description = "Event matchRule for usgBackoutReqPrerequistes2", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void usgBackoutReqPrerequistes3() throws Exception

	{
		try
		{

			EventMatchRuleGroup eventValObj = new EventMatchRuleGroup( path, workBookName, "EventMatchRuleGroup", "EMRG Transit", 1 );
			eventValObj.configureEventMatchRuleGroup();

			EventMatchRule emrObj = new EventMatchRule( path, workBookName, "EMR", "EMR Transit_Disp" );
			emrObj.configureEventMatchRule();

		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}

}
