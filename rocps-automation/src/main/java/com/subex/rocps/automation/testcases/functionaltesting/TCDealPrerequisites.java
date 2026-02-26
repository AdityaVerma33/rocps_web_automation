package com.subex.rocps.automation.testcases.functionaltesting;

import com.subex.rocps.automation.helpers.application.matchandrate.EventMatchRule;
import com.subex.rocps.automation.helpers.application.matchandrate.EventMatchRuleGroup;
import com.subex.rocps.automation.helpers.application.networkConfiguraiton.Route;
import com.subex.rocps.automation.helpers.application.networkConfiguraiton.RouteGroup;
import com.subex.rocps.automation.helpers.application.partnerConfiguration.Account;
import com.subex.rocps.automation.helpers.application.partnerConfiguration.BillProfile;
import com.subex.rocps.automation.helpers.application.system.Streams;
import com.subex.rocps.automation.helpers.application.tariffs.PSTariffHelper;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;

import com.subex.automation.helpers.application.screens.BandHelper;
import com.subex.automation.helpers.application.screens.ElementCreateHelper;
import com.subex.automation.helpers.application.screens.RecurringTaskHelper;
import com.subex.automation.helpers.application.screens.TariffClassHelper;
import com.subex.automation.helpers.application.screens.TariffHelper;
import com.subex.automation.helpers.application.screens.TriggerHelper;
import com.subex.automation.helpers.util.FailureHelper;

public class TCDealPrerequisites extends PSAcceptanceTest
{
	String path = System.getProperty( "user.dir" ) + "\\src\\main\\resources\\";
	String workBookName = "FunctionalTestingDeals.xlsx";
	String sheetName = "Prerequsities";

	@org.testng.annotations.Test( priority = 1, description = "Account , Bill Profile and Route for deal", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void dealPrerequisite1() throws Exception
	{
		try
		{
			Account accobj = new Account( path, workBookName, "Prerequsities", "AccountDeal", 1 );
			accobj.accountCreation();

			BillProfile billObj = new BillProfile( path, workBookName, "Prerequsities", "BillProfileDeal", 1 );
			billObj.billProfileCreation();

			RouteGroup routeGrpObj = new RouteGroup( path, workBookName, "Prerequsities", "RouteGroup Deal" );
			routeGrpObj.routeGrpCreation();

			Route routeColObj = new Route( path, workBookName, "Prerequsities", "Routes Deals" );
			routeColObj.routeCreation();

		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 2, description = "EMR for deal", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void dealPrerequisite2() throws Exception
	{
		try
		{
			PSTariffHelper pstrffObj1 = new PSTariffHelper();
			pstrffObj1.createTariff( path, workBookName, "Prerequsities", "Tariff Incoming1", 1 );
			pstrffObj1.createFastEntry( path, workBookName, "Prerequsities", "Tariff Incoming1 FastEntry", 1 );

			PSTariffHelper pstrff1Obj1 = new PSTariffHelper();
			pstrff1Obj1.createTariff( path, workBookName, "Prerequsities", "Tariff Incoming2", 1 );
			pstrff1Obj1.createFastEntry( path, workBookName, "Prerequsities", "Tariff Incoming2FastEntry", 1 );

			PSTariffHelper pstrff2Obj1 = new PSTariffHelper();
			pstrff2Obj1.createTariff( path, workBookName, "Prerequsities", "Tariff OutgoingDeal1", 1 );
			pstrff2Obj1.createFastEntry( path, workBookName, "Prerequsities", "Tariff Outgoing FastEntry1", 1 );

			PSTariffHelper pstrff3Obj1 = new PSTariffHelper();
			pstrff3Obj1.createTariff( path, workBookName, "Prerequsities", "Tariff OutgoingDeal2", 1 );
			pstrff3Obj1.createFastEntry( path, workBookName, "Prerequsities", "Tariff Outgoing2 FastEntry", 1 );

			EventMatchRuleGroup eventValObj = new EventMatchRuleGroup( path, workBookName, "Prerequsities", "EMRG-CI", 1 );
			eventValObj.configureEventMatchRuleGroup();

			EventMatchRuleGroup eventVal1Obj = new EventMatchRuleGroup( path, workBookName, "Prerequsities", "EMRG-Bills", 1 );
			eventVal1Obj.configureEventMatchRuleGroup();

			EventMatchRule emrObj = new EventMatchRule( path, workBookName, "Prerequsities", "EMR DealOut" );
			emrObj.configureEventMatchRule();
			EventMatchRule emr1Obj = new EventMatchRule( path, workBookName, "Prerequsities", "EMR DealIn" );
			emr1Obj.configureEventMatchRule();

		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 3, description = "Account , Bill Profile and Route for deal", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void dealPrerequisite3() throws Exception
	{
		try
		{

			Account accobj = new Account( path, workBookName, "Prerequsities", "AccountDeal", 1 );
			accobj.accountCreation();

			BillProfile billObj = new BillProfile( path, workBookName, "Prerequsities", "BillProfileDeal1", 1 );
			billObj.billProfileCreation();

			RouteGroup routeGrpObj = new RouteGroup( path, workBookName, "Prerequsities", "RouteGroup Deal1" );
			routeGrpObj.routeGrpCreation();

			Route routeColObj = new Route( path, workBookName, "Prerequsities", "Routes Deals1" );
			routeColObj.routeCreation();

			ElementCreateHelper eleObj = new ElementCreateHelper();
			eleObj.createElement( path, workBookName, "Prerequsities", "Elements Deals", 1 );

			BandHelper bandObj = new BandHelper();
			bandObj.createBand( path, workBookName, "Prerequsities", "Bands Deals", 1 );

			TariffClassHelper trffClassObj = new TariffClassHelper();
			trffClassObj.createTariffClass( path, workBookName, "Prerequsities", "TariffClass commiteddeal", 1 );

		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 4, description = "EMR for deal", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void dealPrerequisite4() throws Exception
	{
		try
		{
			PSTariffHelper pstrffObj1 = new PSTariffHelper();
			pstrffObj1.createTariff( path, workBookName, "Prerequsities", "Tariff Incoming3", 1 );
			pstrffObj1.createFastEntry( path, workBookName, "Prerequsities", "Tariff Incoming3FastEntry", 1 );

			PSTariffHelper pstrff1Obj1 = new PSTariffHelper();
			pstrff1Obj1.createTariff( path, workBookName, "Prerequsities", "Tariff Incoming4", 1 );
			pstrff1Obj1.createFastEntry( path, workBookName, "Prerequsities", "Tariff Incoming4FastEntry", 1 );

			PSTariffHelper pstrff2Obj1 = new PSTariffHelper();
			pstrff2Obj1.createTariff( path, workBookName, "Prerequsities", "Tariff OutgoingDeal3", 1 );
			pstrff2Obj1.createFastEntry( path, workBookName, "Prerequsities", "Tariff Outgoing FastEntry3", 1 );

			PSTariffHelper pstrff3Obj1 = new PSTariffHelper();
			pstrff3Obj1.createTariff( path, workBookName, "Prerequsities", "Tariff OutgoingDeal4", 1 );
			pstrff3Obj1.createFastEntry( path, workBookName, "Prerequsities", "Tariff Outgoing4FastEntry", 1 );

			EventMatchRuleGroup eventValObj = new EventMatchRuleGroup( path, workBookName, "Prerequsities", "EMRG-CI", 1 );
			eventValObj.configureEventMatchRuleGroup();

			EventMatchRuleGroup eventVal1Obj = new EventMatchRuleGroup( path, workBookName, "Prerequsities", "EMRG-Bills", 1 );
			eventVal1Obj.configureEventMatchRuleGroup();

			EventMatchRule emrObj = new EventMatchRule( path, workBookName, "Prerequsities", "EMR DealOut1" );
			emrObj.configureEventMatchRule();
			EventMatchRule emr1Obj = new EventMatchRule( path, workBookName, "Prerequsities", "EMR DealIn1" );
			emr1Obj.configureEventMatchRule();

		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 5, description = "edit Voice stream- Deals",retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class  )
	public void editVoicestream() throws Exception
	{
		try
		{
			Streams streamObj = new Streams();
			streamObj.editStreamConfig( path, workBookName, "Prerequsities", "VoiceStreamsDeals", 1 );
			streamObj.dealStreamConfig( path, workBookName, "Prerequsities", testCaseName, 1 );
			streamObj.saveStreamDetail();

		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 6, description = "Trigger creation",retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class  )
	public void createTrigger() throws Exception
	{
		try
		{

			TriggerHelper triggerObj = new TriggerHelper();
			triggerObj.createTrigger( path, workBookName, sheetName, "Trigger", 1 );

		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 7, description = "Recurring task creation" ,retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void createRecurringTask() throws Exception
	{
		try
		{

			RecurringTaskHelper triggerObj = new RecurringTaskHelper();
			triggerObj.createRecurringTask( path, workBookName, sheetName, "RecurringTask", 1 );

		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

}
