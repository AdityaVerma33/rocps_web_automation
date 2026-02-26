package com.subex.rocps.automation.testcases.functionaltesting;

import org.testng.annotations.Test;

import com.subex.automation.helpers.application.screens.BandHelper;
import com.subex.automation.helpers.application.screens.ElementCreateHelper;
import com.subex.automation.helpers.application.screens.FileCollectionHelper;
import com.subex.automation.helpers.application.screens.FileSourceHelper;
import com.subex.automation.helpers.application.screens.RecurringTaskHelper;
import com.subex.automation.helpers.application.screens.TariffClassHelper;
import com.subex.automation.helpers.application.screens.TariffHelper;
import com.subex.automation.helpers.application.screens.TriggerHelper;
import com.subex.automation.helpers.util.FailureHelper;

import com.subex.rocps.automation.helpers.application.aggregation.AggregationProcessor;
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
import com.subex.rocps.automation.helpers.application.xdrextraction.XdrExtrTempHelper;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;

public class TCXdrExtTemplate extends PSAcceptanceTest
{

	String path = System.getProperty( "user.dir" ) + "\\src\\main\\resources\\";

	String workBookName = "FunctionalTestCases.xlsx";
	String sheetName = "XDR Extraction";

	@Test( priority = 1, enabled = true, description = "XDR Extraction Template verify the column Headers Of Search Screen" )
	public void verifyTheColHdrs() throws Exception
	{
		try
		{
			XdrExtrTempHelper xdrExtrTempHelper = new XdrExtrTempHelper( path, workBookName, sheetName, "XdrExtrTempSearchScreencolVal" );
			xdrExtrTempHelper.verifyColmnHeaderOfXdrExtrTempl();

		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

	@Test( priority = 2, enabled = true, description = "XDR Extraction Template creation without Bill", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void createXdrExtTempWithoutBill() throws Exception
	{
		try
		{
			XdrExtrTempHelper xdrExtrTempHelper = new XdrExtrTempHelper( path, workBookName, sheetName, "XdrExtTempCreationWithoutBill" );
			xdrExtrTempHelper.xdrExtTemplCreation();

		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

	@Test( priority = 3, enabled = true, description = "XDR Extraction Template creation with Bill", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void createXdrExtTempWithBill() throws Exception
	{
		try
		{
			XdrExtrTempHelper xdrExtrTempHelper = new XdrExtrTempHelper( path, workBookName, sheetName, "XdrExtTempCreationWithBill" );
			xdrExtrTempHelper.xdrExtTemplCreation();

		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

	@Test( priority = 4, enabled = true, description = "XDR Extraction Template change status to Accepted", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void changeStatusToAccepted() throws Exception
	{
		try
		{
			XdrExtrTempHelper xdrExtrTempHelper = new XdrExtrTempHelper( path, workBookName, sheetName, "XdrExtTemplateChangeStatusToAccepted" );
			xdrExtrTempHelper.changeToAcceptedStatus();

		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

	@Test( priority = 5, enabled = true, description = "XDR Extraction Template View action", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void xdrExtTempViewAction() throws Exception
	{
		try
		{
			XdrExtrTempHelper xdrExtrTempHelper = new XdrExtrTempHelper( path, workBookName, sheetName, "XdrTempViewAction" );
			xdrExtrTempHelper.xdrExtTemplViewAction();

		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

	@Test( priority = 6, enabled = true, description = "XDR Extraction Template creation For Roaming", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void createXdrExtTempRoaming() throws Exception
	{
		try
		{
			XdrExtrTempHelper xdrExtrTempHelper = new XdrExtrTempHelper( path, workBookName, sheetName, "XdrExtTempCreationRoaming" );
			xdrExtrTempHelper.xdrExtTemplCreation();

		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

	@Test( priority = 7, enabled = true, description = "XDR Extraction Template update xdr extract option action performed", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void updateXdrOpAction() throws Exception
	{
		try
		{
			XdrExtrTempHelper xdrExtrTempHelper = new XdrExtrTempHelper( path, workBookName, sheetName, "UpdateXdrExtOptionAction" );
			xdrExtrTempHelper.updateXdrExtOpAction();

		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

	@Test( priority = 8, enabled = true, description = "XDR Extraction Template creation with save as action", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void createXdrExtTempWithSaveAsAct() throws Exception
	{
		try
		{
			XdrExtrTempHelper xdrExtrTempHelper = new XdrExtrTempHelper( path, workBookName, sheetName, "XdrTempCreationWithSaveAs" );
			xdrExtrTempHelper.xdrExtTemplCrtWithSaveAs();

		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

	@Test( priority = 9, enabled = true, description = "XDR Extraction Template creation For Roaming", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void xdrTemplEdit() throws Exception
	{
		try
		{
			XdrExtrTempHelper xdrExtrTempHelper = new XdrExtrTempHelper( path, workBookName, sheetName, "XdrExtTempEdit" );
			xdrExtrTempHelper.xdrExtTemplEdit();

		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

	@Test( priority = 10, enabled = true, description = "XDR Extraction Template delete action" )
	public void xdrExtTempDeleteAction() throws Exception
	{
		try
		{
			XdrExtrTempHelper xdrExtrTempHelper = new XdrExtrTempHelper( path, workBookName, sheetName, "XdrExtTemplateDelete" );
			xdrExtrTempHelper.xdrExtTempDelete();

		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

	@Test( priority = 11, enabled = true, dependsOnMethods = "xdrExtTempDeleteAction", description = "XDR Extraction Template Undelete action" )
	public void xdrExtTempUnDeleteAction() throws Exception
	{
		try
		{
			XdrExtrTempHelper xdrExtrTempHelper = new XdrExtrTempHelper( path, workBookName, sheetName, "XdrExtTemplateUndelete" );
			xdrExtrTempHelper.xdrExtTempUnDelete();

		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

	@Test( priority = 12, enabled = true, description = "XDR Extraction Template update from and to date from backend" )
	public void updateXdrExtTempBackend() throws Exception
	{
		try
		{
			XdrExtrTempHelper xdrExtrTempHelper = new XdrExtrTempHelper( path, workBookName, sheetName, "XdrExtTemplBackend" );
			xdrExtrTempHelper.xdrExtTempUpdateDateBackend();

		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 13, description = "aggregation processor  creation with XDR Extraction Template without bill", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void aggregationProcessor() throws Exception
	{

		try
		{
			AggregationProcessor aggObj = new AggregationProcessor( path, workBookName, "AggregationProcessor", "XDRAggregationProcessor", 1 );
			aggObj.aggregationProcessorCreation();
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 14, description = "aggregation processor  creation of XDR Extraction Template with bill", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void aggregationProcessorWithBill() throws Exception
	{

		try
		{
			AggregationProcessor aggObj = new AggregationProcessor( path, workBookName, "AggregationProcessor", "XDRAggregationProcessorWithBill", 1 );
			aggObj.aggregationProcessorCreation();
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

	//@Test( priority = 15, enabled = true, description = "Insert into 'xdr_agg_keys' table from backend" )
	public void insertXdrAggKeysBackend() throws Exception
	{
		try
		{
			XdrExtrTempHelper xdrExtrTempHelper = new XdrExtrTempHelper( path, workBookName, sheetName, "XdrAggKeysInsertBackend" );
			xdrExtrTempHelper.xdrAggKeysInsertBackend();

		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

	//@Test( priority = 16, enabled = true, description = "Insert into 'Xdr_Agg_File_Sequence' table from backend" )
	public void insertXdrAggFileSeqBackend() throws Exception
	{
		try
		{
			XdrExtrTempHelper xdrExtrTempHelper = new XdrExtrTempHelper( path, workBookName, sheetName, "XdrAggFileSeqBackend" );
			xdrExtrTempHelper.xdrAggFlSeqInsertBackend();

		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

	/*PreRequiste of XDR Extraction*/

	@org.testng.annotations.Test( priority = 17, enabled = true, description = "Mnr for Xdr prerequistes", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void XdrPrerequistes1() throws Exception

	{
		try
		{

			Agent agobj = new Agent( path, workBookName, "Agent", "Agent", 1 );
			agobj.agentCreation();

			Account accobj = new Account( path, workBookName, "Account", "Account_Xdr", 1 );
			accobj.accountCreation();

			BillProfile billObj = new BillProfile( path, workBookName, "BillProfile", "BillProfile_Xdr", 1 );
			billObj.billProfileCreation();

		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 18, enabled = true, description = "Mnr for Xdr prerequistes", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void XdrPrerequistes2() throws Exception

	{
		try
		{

			Switch switchObj = new Switch( path, workBookName, "Switch", "Switch" );
			switchObj.configureSwitch();

			Operator ope1Obj = new Operator( path, workBookName, "Operator", "Operator_Xdr", 1 );
			ope1Obj.operatorCreation();

			RouteGroup routeGrpObj = new RouteGroup( path, workBookName, "RouteGrp", "RouteGroup_Xdr" );
			routeGrpObj.routeGrpCreation();

			Route routeColObj = new Route( path, workBookName, "Route", "Route_Xdr" );
			routeColObj.routeCreation();

		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 19, enabled = true, description = "Mnr for Xdr prerequistes", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void XdrPrerequistes3() throws Exception

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

	@org.testng.annotations.Test( priority = 20, enabled = true, description = "Mnr for Xdr prerequistes", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void XdrPrerequistes4() throws Exception

	{
		try
		{

			EventMatchRuleGroup eventValObj = new EventMatchRuleGroup( path, workBookName, "EventMatchRuleGroup", "EMRG  OutAdvancedRating", 1 );
			eventValObj.configureEventMatchRuleGroup();

			EventMatchRule emrObj = new EventMatchRule( path, workBookName, "EMR", "EMR_Xdr" );
			emrObj.configureEventMatchRule();

		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}

	@Test( priority = 21, enabled = true, description = "XDR Extraction Template creation With Extract Filter", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void createXdrExtTempWithextractFilter() throws Exception
	{
		try
		{
			XdrExtrTempHelper xdrExtrTempHelper = new XdrExtrTempHelper( path, workBookName, sheetName, "XdrExtCreationWithExtractFilter" );
			xdrExtrTempHelper.xdrExtTemplCreation();

		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

	@Test( priority = 22, enabled = true, description = "XDR Extraction Template creation With latest rated eventFlg", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void createXdrExtTempWithLatestRatedEveFlg() throws Exception
	{
		try
		{
			XdrExtrTempHelper xdrExtrTempHelper = new XdrExtrTempHelper( path, workBookName, sheetName, "XdrExtCreationWithBilllatestrated" );
			xdrExtrTempHelper.xdrExtTemplCreation();

		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

}
