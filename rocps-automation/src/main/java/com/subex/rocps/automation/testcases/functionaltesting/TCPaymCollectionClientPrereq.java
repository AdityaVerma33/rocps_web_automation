package com.subex.rocps.automation.testcases.functionaltesting;

import org.testng.annotations.Test;

import com.subex.automation.helpers.application.screens.BandHelper;
import com.subex.automation.helpers.application.screens.ElementCreateHelper;
import com.subex.automation.helpers.application.screens.TariffClassHelper;
import com.subex.automation.helpers.application.screens.TariffHelper;
import com.subex.automation.helpers.application.screens.TaskControllerHelper;
import com.subex.automation.helpers.util.FailureHelper;
import com.subex.rocps.automation.helpers.application.aggregation.AggregationResult;
import com.subex.rocps.automation.helpers.application.bills.Bills;
import com.subex.rocps.automation.helpers.application.carrierinvoice.CarrierInvoice;
import com.subex.rocps.automation.helpers.application.carrierinvoice.CarrierInvoiceExcelTemplate;
import com.subex.rocps.automation.helpers.application.carrierinvoice.CarrierInvoiceManualTemplate;
import com.subex.rocps.automation.helpers.application.carrierinvoice.SystemFieldList;
import com.subex.rocps.automation.helpers.application.matchandrate.EventMatchRule;
import com.subex.rocps.automation.helpers.application.matchandrate.EventMatchRuleGroup;
import com.subex.rocps.automation.helpers.application.matchandrate.Operator;
import com.subex.rocps.automation.helpers.application.networkConfiguraiton.Route;
import com.subex.rocps.automation.helpers.application.networkConfiguraiton.RouteGroup;
import com.subex.rocps.automation.helpers.application.networkConfiguraiton.Switch;
import com.subex.rocps.automation.helpers.application.partnerConfiguration.Account;
import com.subex.rocps.automation.helpers.application.partnerConfiguration.Agent;
import com.subex.rocps.automation.helpers.application.partnerConfiguration.BillProfile;
import com.subex.rocps.automation.helpers.application.system.PSTaskSearchHelper;
import com.subex.rocps.automation.helpers.application.system.Streams;
import com.subex.rocps.automation.helpers.application.system.TaskSchedule;
import com.subex.rocps.automation.helpers.application.tariffs.PSTariffHelper;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;

public class TCPaymCollectionClientPrereq extends PSAcceptanceTest
{
	String path = System.getProperty( "user.dir" ) + "\\src\\main\\resources\\";
	String workBookName = "FunctionalTestCases.xlsx";
	String sheetName = "PaymentCollectionPrerequisite";

	@org.testng.annotations.Test( priority = 1, description = "Streams Stage Creation  for 'Update Bill Profile Balance'", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void createStreamStgUpdateBillProfBalance() throws Exception
	{

		try
		{
			String partition = null;
			Streams streamObj = new Streams();
			streamObj.editStreamConfig( path, workBookName, sheetName, "VoiceStreams", 1 );
			streamObj.updateBillProfBalanceStreamStgConfig( path, workBookName, sheetName, "UpdateBillProfileBalanceStreamStage", 1 );
			streamObj.saveStreamDetail();
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 2, enabled = true, description = "Mnr for BillClientPrerequistes", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void BillClientPrerequistes() throws Exception

	{
		try
		{
			Agent agobj = new Agent( path, workBookName, "Agent", "Agent", 1 );
			agobj.agentCreation();

			Account accobj = new Account( path, workBookName, "Account", "Account_PaymColl", 1 );
			accobj.accountCreation();

			BillProfile billObj = new BillProfile( path, workBookName, "BillProfile", "BillProfile_PaymColl", 1 );
			billObj.billProfileCreation();

		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 3, enabled = true, description = "Mnr for BillClientPrerequistes", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void BillClientPrerequistes2() throws Exception

	{
		try
		{

			Switch switchObj = new Switch( path, workBookName, "Switch", "Switch" );
			switchObj.configureSwitch();

			Operator ope1Obj = new Operator( path, workBookName, "Operator", "Operator_PaymColl", 1 );
			ope1Obj.operatorCreation();

			RouteGroup routeGrpObj = new RouteGroup( path, workBookName, "RouteGrp", "RouteGroupTransit_PaymColl" );
			routeGrpObj.routeGrpCreation();

			Route routeColObj = new Route( path, workBookName, "Route", "RouteTransit_PaymColl" );
			routeColObj.routeCreation();

		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 4, enabled = true, description = "Mnr for BillClientPrerequistes", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void BillClientPrerequistes3() throws Exception

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

	@org.testng.annotations.Test( priority = 5, enabled = true, description = "Mnr for BillClientPrerequistes ", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void BillClientPrerequistes4() throws Exception

	{
		try
		{

			EventMatchRuleGroup eventValObj = new EventMatchRuleGroup( path, workBookName, "EventMatchRuleGroup", "EMRG Transit", 1 );
			eventValObj.configureEventMatchRuleGroup();

			EventMatchRule emrObj = new EventMatchRule( path, workBookName, "EMR", "EMR Transit_PaymColl" );
			emrObj.configureEventMatchRule();

		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 6, enabled = true, description = "Mnr for Carrier Invoice Client Prerequist", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void CarrierInvoiceClientPrerequist() throws Exception

	{
		try
		{

			Agent agobj = new Agent( path, workBookName, "Agent", "Agent", 1 );
			agobj.agentCreation();

			Account accobj = new Account( path, workBookName, "Account", "AccountMultiAddress", 1 );
			accobj.accountCreation();

			BillProfile bill1Obj1 = new BillProfile( path, workBookName, "CarrierInvoice", "BillProfileRecon", 1 );
			bill1Obj1.billProfileCreation();

		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 7, enabled = true, description = "Mnr for Carrier Invoice Client Prerequist", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void CarrierInvoiceClientPrerequist2() throws Exception

	{
		try
		{

			ElementCreateHelper eleObj = new ElementCreateHelper();
			eleObj.createElement( path, workBookName, "Elements", "Elements Outgoing", 1 );

			BandHelper bandObj = new BandHelper();
			bandObj.createBand( path, workBookName, "Bands", "Bands Outgoing", 1 );

			TariffClassHelper trffClassObj = new TariffClassHelper();
			trffClassObj.createTariffClass( path, workBookName, "TariffClass", "TariffClass Outgoing", 1 );

			PSTariffHelper pstrffObj1 = new PSTariffHelper();
			pstrffObj1.createTariff( path, workBookName, "Tariff", "Tariff CI", 1 );
			pstrffObj1.createFastEntry( path, workBookName, "Tariff", "Tariff CI FastEntry", 1 );

		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}

}
