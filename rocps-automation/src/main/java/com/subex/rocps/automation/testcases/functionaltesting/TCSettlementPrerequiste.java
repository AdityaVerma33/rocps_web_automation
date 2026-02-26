package com.subex.rocps.automation.testcases.functionaltesting;

import org.testng.annotations.Test;

import com.subex.rocps.automation.helpers.application.aggregation.AggregationResult;
import com.subex.rocps.automation.helpers.application.bills.Bills;
import com.subex.rocps.automation.helpers.application.bills.CreditNotes;
import com.subex.rocps.automation.helpers.application.carrierinvoice.CarrierInvoice;
import com.subex.rocps.automation.helpers.application.carrierinvoice.CarrierInvoiceExcelTemplate;
import com.subex.rocps.automation.helpers.application.carrierinvoice.CarrierInvoiceManualTemplate;
import com.subex.rocps.automation.helpers.application.carrierinvoice.SystemFieldList;
import com.subex.rocps.automation.helpers.application.matchandrate.EventMatchRule;
import com.subex.rocps.automation.helpers.application.matchandrate.EventMatchRuleGroup;
import com.subex.rocps.automation.helpers.application.matchandrate.Operator;
import com.subex.rocps.automation.helpers.application.monitoring.PSCollectedFileSearchHelper;
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

import com.subex.automation.helpers.application.screens.BandHelper;
import com.subex.automation.helpers.application.screens.ElementCreateHelper;
import com.subex.automation.helpers.application.screens.TariffClassHelper;
import com.subex.automation.helpers.application.screens.TariffHelper;
import com.subex.automation.helpers.application.screens.TaskControllerHelper;
import com.subex.automation.helpers.util.FailureHelper;

public class TCSettlementPrerequiste extends PSAcceptanceTest
{
	String path = System.getProperty( "user.dir" ) + "\\src\\main\\resources\\";

	String workBookName = "FunctionalTestCases.xlsx";
	String sheetName = "SettlementsPrerequisite";

	@org.testng.annotations.Test( priority = 1, enabled = true, description = "task Controller capabilities", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void taskControllerCapabilities() throws Exception
	{
		try
		{

			TaskControllerHelper taskObj = new TaskControllerHelper();
			taskObj.setTaskControllerCapability( path, workBookName, sheetName, "TCCapability", 1 );

		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 2, enabled = true, description = "Mnr for BillClientPrerequistes", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void BillClientPrerequistes1() throws Exception

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

	@org.testng.annotations.Test( priority = 3, enabled = true, description = "Mnr for BillClientPrerequistes", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void BillClientPrerequistes2() throws Exception

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

	@org.testng.annotations.Test( priority = 4, enabled = true, description = "Mnr for BillClientPrerequistes", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void BillClientPrerequistes3() throws Exception

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

	@org.testng.annotations.Test( priority = 5, enabled = true, description = "Mnr and Aggregation for BillServerPrerequistes" )
	public void BillServerPrerequistes() throws Exception

	{
		try
		{
			TaskSchedule taskObj = new TaskSchedule();
			taskObj.fileCollection( path, workBookName, sheetName, "FileSchedule_SettlementBill", 1 );
			PSCollectedFileSearchHelper collectedFlObj = new PSCollectedFileSearchHelper( path, workBookName, sheetName, "CollectedFileSearchSettlementBill" );
			collectedFlObj.validationOfCollectedFile();
			PSTaskSearchHelper psTaskobj = new PSTaskSearchHelper();
			psTaskobj.psVerifyTaskStatus( path, workBookName, "AggregationResults", "MnrTaskStatus", 1 );

		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 6, enabled = true, description = "Mnr and Aggregation for BillServerPrerequistes" )
	public void BillServerPrerequistes2() throws Exception

	{
		try
		{
			TaskSchedule taskObj = new TaskSchedule();
			PSTaskSearchHelper psTaskobj = new PSTaskSearchHelper();
			taskObj.scheduleRecurringTask( path, workBookName, "AggregationResults", "RecurringTask", 1 );
			psTaskobj.psVerifyTaskStatus( path, workBookName, "AggregationResults", "AggrTaskStatus", 1 );

		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 7, enabled = true, description = "Aggregation task for BillServerPrerequistes2" )
	public void BillServerPrerequistes3() throws Exception

	{
		try
		{

			AggregationResult aggrResObj = new AggregationResult( path, workBookName, sheetName, "AggregationResult_InvoiceBill1" );
			aggrResObj.isAggregationResultPresent();

			AggregationResult aggrResObj1 = new AggregationResult( path, workBookName, sheetName, "AggregationResult_StatementBill2" );
			aggrResObj1.isAggregationResultPresent();

		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 8, enabled = true, description = "Credit Notes - creation for settlements", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void creditNotesCreationSettlements() throws Exception
	{
		try
		{
			CreditNotes creditstatusObj = new CreditNotes( path, workBookName, sheetName, "CreditNotes creationsSettlements", 1 );
			creditstatusObj.creditNotesCreation();

		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 9, enabled = true, description = "Credit Notes -change status for settlements", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void creditNotesChangeStatus1() throws Exception
	{
		try
		{
			CreditNotes creditstatusObj = new CreditNotes( path, workBookName, sheetName, "CreditNotes changeStatusSettlements1", 1 );
			creditstatusObj.changeCreditNoteStatus();

		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 10, enabled = true, description = "Credit Notes -change status for settlements", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void creditNotesChangeStatus2() throws Exception
	{
		try
		{
			CreditNotes creditstatusObj = new CreditNotes( path, workBookName, sheetName, "CreditNotes changeStatusSettlements2", 1 );
			creditstatusObj.changeCreditNoteStatus();

		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 11, enabled = true, description = "bill profile- Settlement  creation", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void billProfileCreation() throws Exception
	{
		try
		{
			BillProfile billObj = new BillProfile( path, workBookName, "BillProfile", "SettlementBillProfile", 1 );
			billObj.billProfileCreation();
		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}

}
