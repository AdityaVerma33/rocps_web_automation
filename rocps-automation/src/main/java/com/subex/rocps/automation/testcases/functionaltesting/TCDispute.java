package com.subex.rocps.automation.testcases.functionaltesting;

import org.testng.annotations.Test;

import com.subex.automation.helpers.application.screens.BandHelper;
import com.subex.automation.helpers.application.screens.ElementCreateHelper;
import com.subex.automation.helpers.application.screens.TariffClassHelper;
import com.subex.automation.helpers.application.screens.TariffHelper;
import com.subex.automation.helpers.application.screens.TaskControllerHelper;
import com.subex.automation.helpers.application.screens.TaskSearchHelper;
import com.subex.automation.helpers.util.FailureHelper;

import com.subex.rocps.automation.helpers.application.aggregation.AggregationResult;
import com.subex.rocps.automation.helpers.application.bills.Bills;
import com.subex.rocps.automation.helpers.application.carrierinvoice.CarrierInvoice;
import com.subex.rocps.automation.helpers.application.carrierinvoice.CarrierInvoiceExcelTemplate;
import com.subex.rocps.automation.helpers.application.carrierinvoice.CarrierInvoiceManualTemplate;
import com.subex.rocps.automation.helpers.application.carrierinvoice.SystemFieldList;
import com.subex.rocps.automation.helpers.application.dispute.DisputeHelper;
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
import com.subex.rocps.automation.helpers.application.system.TaskSchedule;
import com.subex.rocps.automation.helpers.application.tariffs.PSTariffHelper;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;

public class TCDispute extends PSAcceptanceTest
{
	String path = System.getProperty( "user.dir" ) + "\\src\\main\\resources\\";
	String workBookName = "FunctionalTestCases.xlsx";
	String sheetName = "Dispute";

	@org.testng.annotations.Test( priority = 1, enabled = true, description = "task Controller capabilities", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void taskControllerCapabilities() throws Exception
	{
		try
		{

			TaskControllerHelper taskObj = new TaskControllerHelper();
			taskObj.setTaskControllerCapability( path, workBookName, "ROCPreRequisites2", "TCCapability", 1 );

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

	@org.testng.annotations.Test( priority = 3, enabled = true, description = "Mnr for BillClientPrerequistes2", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void BillClientPrerequistes2() throws Exception

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

	@org.testng.annotations.Test( priority = 4, enabled = true, description = "Mnr and Aggregation for BillServerPrerequistes" )
	public void BillServerPrerequistes() throws Exception

	{
		try
		{
			TaskSchedule taskObj = new TaskSchedule();
			PSTaskSearchHelper psTaskobj = new PSTaskSearchHelper();
			taskObj.fileCollection( path, workBookName, sheetName, "FileSchedule_Dispute", 1 );
			psTaskobj.psVerifyTaskStatus( path, workBookName, "AggregationResults", "MnrTaskStatus", 1 );

		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 5, enabled = true, description = "schedule and verify  Aggregation for BillServerPrerequistes" )
	public void BillServerPrerequistes_Aggregation() throws Exception

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

	@org.testng.annotations.Test( priority = 6, enabled = true, description = "Generation of Bill for BillServerPrerequistes2" )
	public void BillServerPrerequistes2() throws Exception

	{
		try
		{

			AggregationResult aggrResObj = new AggregationResult( path, workBookName, sheetName, "AggregationResult_BillDispute1" );
			aggrResObj.isAggregationResultPresent();

			AggregationResult aggrResObj1 = new AggregationResult( path, workBookName, sheetName, "AggregationResult_BillDispute2" );
			aggrResObj1.isAggregationResultPresent();

			Bills billObjec = new Bills( path, workBookName, sheetName, "HotBillCreate_BillDispute_WithoutShortPay" );
			billObjec.billAction();
			Bills billObjec1 = new Bills( path, workBookName, sheetName, "HotBillCreate_Bill_WithShortPay" );
			billObjec1.billAction();

		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 7, enabled = true, description = "Mnr for Carrier Invoice Client Prerequist", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
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

	@Test( priority = 8, enabled = true, description = "Create Carrier Invoice ", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void carrierInvoiceCreation() throws Exception
	{
		try
		{
			SystemFieldList sysObj = new SystemFieldList( path, workBookName, "SystemFieldList", "SystemFieldLine_StdStructure" );
			sysObj.configureSystemFieldList();
			sysObj.systemFieldListChangeStatus();

			CarrierInvoiceManualTemplate ciObj = new CarrierInvoiceManualTemplate( path, workBookName, "CarrierInvoiceTemplateManual", "CI_ManualTmpl5_SFL", 1 );
			ciObj.ciManualTemplateConfig();
			CarrierInvoiceExcelTemplate ciAppObj = new CarrierInvoiceExcelTemplate( path, workBookName, "CarrierInvoiceTemplateManual", "CI_ManualTmpl5_SFL", 1 );
			ciAppObj.approveCarrireInvoiceTemplate();

			CarrierInvoice ciObj1 = new CarrierInvoice( path, workBookName, "CarrierInvoice", "CI_ManualTmpl4_SFL", 1 );
			ciObj1.carrierInvoiceSearchConfig();
			CarrierInvoice ciObj2 = new CarrierInvoice( path, workBookName, "CarrierInvoice", "CI_AuthorizedACtion1", 1 );
			ciObj2.carrierInvoiceAuthorize();
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

	@Test( priority = 9, enabled = true, description = "Create Dispute Type" )
	public void createDisputeType() throws Exception
	{
		try
		{
			DisputeHelper disputeHelper = new DisputeHelper( path, workBookName, sheetName, "DisputeTypeCreation" );
			disputeHelper.disputeTypeCreation();
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

	@Test( priority = 10, enabled = true, description = "DisputeValidation:empty dispute creation" )
	public void disputeValidation() throws Exception
	{
		try
		{
			DisputeHelper disputeHelper = new DisputeHelper( path, workBookName, sheetName, "EmptyDisputeCreation" );
			disputeHelper.disputeValidation();
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

	@Test( priority = 11, enabled = true, description = "Dispute verify the column Headers Of Search Screen" )
	public void verifyTheColHdrs() throws Exception
	{
		try
		{
			DisputeHelper disputeHelper = new DisputeHelper( path, workBookName, sheetName, "VerifyColumnHeaders" );
			disputeHelper.verifyTheColHdrsOfDisputeSrch();
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

	@Test( priority = 12, enabled = true, description = "Create Dispute Type: Account", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void createDisputeWithAccount() throws Exception
	{
		try
		{

			DisputeHelper disputeHelper = new DisputeHelper( path, workBookName, sheetName, "DisputeWithAccountCreate" );
			disputeHelper.createDispute();
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

	@Test( priority = 13, enabled = true, description = "Create Dispute Type: Bill profile", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void createDisputeWithBillProfile() throws Exception
	{
		try
		{
			DisputeHelper disputeHelper = new DisputeHelper( path, workBookName, sheetName, "DisputeWithBillProfileCreate" );
			disputeHelper.createDispute();
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

	@Test( priority = 14, enabled = true, description = "Create Dispute Type: Bill with shortPay" )
	public void createDisputeBillWithShortPay() throws Exception
	{
		try
		{
			DisputeHelper disputeHelper = new DisputeHelper( path, workBookName, sheetName, "DisputeWithBillCreateWithShortPay" );
			disputeHelper.createDispute();
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

	@Test( priority = 15, enabled = true, description = "Create Dispute Type: Bill without shortPay" )
	public void createDisputeBillWithoutShortPay() throws Exception
	{
		try
		{
			DisputeHelper disputeHelper = new DisputeHelper( path, workBookName, sheetName, "DisputeWithBillCreateWithoutShortPay" );
			disputeHelper.createDispute();
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

	@Test( priority = 16, enabled = true, description = "Create Dispute Type: Carrier Invoice" )
	public void createDisputeWithCarrInvoice() throws Exception
	{
		try
		{

			DisputeHelper disputeHelper = new DisputeHelper( path, workBookName, sheetName, "DisputeWithCarrierInvoiceCreate" );
			disputeHelper.createDispute();
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

	@Test( priority = 17, enabled = true, description = "Action: reject The Dispute" )
	public void rejectTheDispute() throws Exception
	{
		try
		{
			DisputeHelper disputeHelper = new DisputeHelper( path, workBookName, sheetName, "RejectTheDispute" );
			disputeHelper.rejectDispute();
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

	@Test( priority = 18, enabled = true, description = "Action: validate The Dispute" )
	public void validateTheDispute() throws Exception
	{
		try
		{
			DisputeHelper disputeHelper = new DisputeHelper( path, workBookName, sheetName, "ValidateTheDispute" );
			disputeHelper.validateDispute();
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

	@Test( priority = 19, enabled = true, description = "Action: view Update History Dispute" )
	public void viewUpdateHistoryDispute() throws Exception
	{
		try
		{
			DisputeHelper disputeHelper = new DisputeHelper( path, workBookName, sheetName, "ViewUpdateHistoryDispute" );
			disputeHelper.viewUpdateHistoryDispute();
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

	@Test( priority = 20, enabled = true, description = "Action: move To Dispute Resolve" )
	public void moveToDisputeResolve() throws Exception
	{
		try
		{
			DisputeHelper disputeHelper = new DisputeHelper( path, workBookName, sheetName, "MoveToDisputeResolve" );
			disputeHelper.moveToDisputeResolve();
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

	@Test( priority = 21, enabled = true, description = "Action:closeDispute" )
	public void closeDispute() throws Exception
	{
		try
		{
			DisputeHelper disputeHelper = new DisputeHelper( path, workBookName, sheetName, "CloseDispute" );
			disputeHelper.closeDispute();
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}
	
	@Test( priority = 22, enabled = true, description = "edit Dispute" )
	public void editDispute() throws Exception
	{
		try
		{
			DisputeHelper disputeHelper = new DisputeHelper( path, workBookName, sheetName, "DisputeEdit" );
			disputeHelper.editDispute();
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

	@Test( priority = 23, enabled = true, description = "Delete Dispute" )
	public void deleteDispute() throws Exception
	{
		try
		{
			DisputeHelper disputeHelper = new DisputeHelper( path, workBookName, sheetName, "DisputeDelete" );
			disputeHelper.disputeDelete();
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

	@Test( priority = 24, enabled = true, description = "Undelete Dispute", dependsOnMethods = "deleteDispute" )
	public void unDeleteDispute() throws Exception
	{
		try
		{
			DisputeHelper disputeHelper = new DisputeHelper( path, workBookName, sheetName, "DisputeUnDelete" );
			disputeHelper.disputeUnDelete();
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

	
}
