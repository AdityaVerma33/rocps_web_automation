package com.subex.rocps.automation.testcases.functionaltesting;

import com.subex.rocps.automation.helpers.application.aggregation.AggregationResult;
import com.subex.rocps.automation.helpers.application.carrierinvoice.CarrierInvoice;
import com.subex.rocps.automation.helpers.application.carrierinvoice.CarrierInvoiceErrorSearch;
import com.subex.rocps.automation.helpers.application.carrierinvoice.CarrierInvoiceImport;
import com.subex.rocps.automation.helpers.application.carrierinvoice.InvoiceReconConfig;
import com.subex.rocps.automation.helpers.application.carrierinvoice.InvoiceReconciliationRequest;
import com.subex.rocps.automation.helpers.application.matchandrate.EventIdentiferDefinition;
import com.subex.rocps.automation.helpers.application.matchandrate.EventMatchRule;
import com.subex.rocps.automation.helpers.application.matchandrate.EventMatchRuleGroup;
import com.subex.rocps.automation.helpers.application.matchandrate.Operator;
import com.subex.rocps.automation.helpers.application.networkConfiguraiton.Route;
import com.subex.rocps.automation.helpers.application.networkConfiguraiton.RouteGroup;
import com.subex.rocps.automation.helpers.application.networkConfiguraiton.Switch;
import com.subex.rocps.automation.helpers.application.partnerConfiguration.Account;
import com.subex.rocps.automation.helpers.application.partnerConfiguration.Agent;
import com.subex.rocps.automation.helpers.application.partnerConfiguration.BillProfile;
import com.subex.rocps.automation.helpers.application.system.TaskSchedule;
import com.subex.rocps.automation.helpers.application.tariffs.PSTariffHelper;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;

import com.subex.automation.helpers.application.screens.BandHelper;
import com.subex.automation.helpers.application.screens.ElementCreateHelper;
import com.subex.automation.helpers.application.screens.TariffClassHelper;
import com.subex.automation.helpers.application.screens.TariffHelper;
import com.subex.automation.helpers.application.screens.TaskSearchHelper;
import com.subex.automation.helpers.util.FailureHelper;

public class TCCarrierInvoice extends PSAcceptanceTest
{
	String path = System.getProperty( "user.dir" ) + "\\src\\main\\resources\\";
	String workBookName = "FunctionalTestCases.xlsx";
	String sheetName = "CarrierInvoice";	
	
	@org.testng.annotations.Test( priority = 1, description = "Mnr for Carrier Invoice", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void ciPrerequisite1() throws Exception
	{
		try
		{
			Agent agobj = new Agent( path, workBookName, "Agent", "Agent", 1 );
			agobj.agentCreation();
			
			Account accobj = new Account( path, workBookName, "Account", "AccountMultiAddress", 1 );
			accobj.accountCreation();
			
			BillProfile billObj = new BillProfile( path, workBookName, "BillProfile", "BillProfileCarrierInvoice", 1 );
			billObj.billProfileCreation();
		
			Operator ope4Obj = new Operator( path, workBookName, "Operator", "Operator AllFields", 1 );
			ope4Obj.operatorCreation();
			
			Switch switchObj = new Switch( path, workBookName, "Switch", "Switch" );
			switchObj.configureSwitch();			
			
			RouteGroup routeGrpObj = new RouteGroup( path, workBookName, "RouteGrp", "RouteGroup CI" );
			routeGrpObj.routeGrpCreation();
			
			Route routeColObj = new Route( path, workBookName, "Route", "Routes CI1" );
			routeColObj.routeCreation();
			
			EventIdentiferDefinition eventObj = new EventIdentiferDefinition( path, workBookName, "EventDefn", "EventDefn Criteria", 1 );
			eventObj.eventCreation();	
			
			ElementCreateHelper eleObj = new ElementCreateHelper();
			eleObj.createElement( path, workBookName, "Elements", "Elements Outgoing", 1 );

		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}
	
	@org.testng.annotations.Test( priority = 2, description = "band creation" , retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class)
	public void ciBandCreation() throws Exception
	{
		try
		{		
			
			BandHelper bandObj = new BandHelper();
			bandObj.createBand( path, workBookName, "Bands", "Bands Outgoing", 1 );			
		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}
	
	@org.testng.annotations.Test( priority = 3, description = "tariff class creation" , retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class)
	public void ciTariffClass() throws Exception
	{
		try
		{			
			TariffClassHelper trffClassObj = new TariffClassHelper();
			trffClassObj.createTariffClass( path, workBookName, "TariffClass", "TariffClass Outgoing", 1 );
		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}
	
	@org.testng.annotations.Test( priority = 4, description = "tariff class creation" , retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class)
	public void ciEMRCreation() throws Exception
	{
		try
		{			
			PSTariffHelper pstrffObj1 = new PSTariffHelper();
			pstrffObj1.createTariff( path, workBookName, "Tariff", "Tariff CI", 1 );
			pstrffObj1.createFastEntry( path, workBookName, "Tariff", "Tariff CI FastEntry", 1 );

			EventMatchRuleGroup eventValObj = new EventMatchRuleGroup( path, workBookName, "EventMatchRuleGroup", "EMRG-CI", 1 );
			eventValObj.configureEventMatchRuleGroup();
			
			EventMatchRule emrObj = new EventMatchRule( path, workBookName, "EMR", "EMR CI" );
			emrObj.configureEventMatchRule();
		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}


	@org.testng.annotations.Test( priority = 5, description = "Carrier Invoice Search Screen Column Validation" )
	public void ciColVal() throws Exception
	{
		try
		{
			CarrierInvoice ciObj = new CarrierInvoice( path, workBookName, sheetName, "CI_SearchScreenColumnVal", 1 );
			ciObj.searchScreenColumnsValidation();
		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 6, description = "Carrier Invoice Search creation- ManualTemplate 1" , retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class)
	public void ciTemplate1ManualCreation() throws Exception
	{
		try
		{
			BillProfile bill1Obj = new BillProfile( path, workBookName, sheetName, "BillProfileRecon", 1 );
			bill1Obj.billProfileCreation();

			CarrierInvoice ciObj = new CarrierInvoice( path, workBookName, sheetName, "CI_ManualTmpl1_TableInst", 1 );
			ciObj.carrierInvoiceSearchConfig();
		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 7, description = "Carrier Invoice Search creation- Template 2" )
	public void ciManualCreation1() throws Exception
	{
		try
		{
			CarrierInvoice ciObj = new CarrierInvoice( path, workBookName, sheetName, "CI_ManualTmpl2_SFLblended", 1 );
			ciObj.carrierInvoiceSearchConfig();
		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 8, description = "Carrier Invoice Search creation- template 3" )
	public void ciManualCreation2() throws Exception
	{
		try
		{
			CarrierInvoice ciObj = new CarrierInvoice( path, workBookName, sheetName, "CI_ManualTmpl3_SFL", 1 );
			ciObj.carrierInvoiceSearchConfig();

		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 9, description = "Carrier Invoice Search creation- ManualTemplate 1" )
	public void ciViewHistory() throws Exception
	{
		try
		{

			CarrierInvoice ciObj = new CarrierInvoice( path, workBookName, sheetName, "CI_ManualTmpl1_TableInst", 1 );
			ciObj.carrierInvoiceViewCIHistory();
		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 10, description = "Carrier Invoice Search creation- ManualTemplate 1" )
	public void ciAuthorize() throws Exception
	{
		try
		{
			CarrierInvoice ci1Obj = new CarrierInvoice( path, workBookName, sheetName, "CI_AuthorizedACtion", 1 );
			ci1Obj.carrierInvoiceAuthorize();

		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 11, description = "Carrier Invoice Search creation- ManualTemplate 1" )
	public void ciTemplate1Reject() throws Exception
	{
		try
		{
	
			CarrierInvoice ciObj = new CarrierInvoice( path, workBookName, sheetName, "CI_ManualTmpl2_SFLblended", 1 );
			ciObj.carrierInvoiceReject();
		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}
	
	@org.testng.annotations.Test( priority = 12, description = "Carrier Invoice Error Screen Column Validation" )
	public void ciErrorColVal() throws Exception
	{
		try
		{
			CarrierInvoiceErrorSearch ciObj = new CarrierInvoiceErrorSearch( path, workBookName, "CarrierInvoiceError", "CIError_SearchScreenColumnVal", 1 );
			ciObj.searchScreenColumnsValidation();
		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}

}
