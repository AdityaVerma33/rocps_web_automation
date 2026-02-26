package com.subex.rocps.automation.testcases.functionaltesting;

import org.testng.annotations.Test;

import com.subex.automation.helpers.application.screens.TaskControllerHelper;
import com.subex.automation.helpers.util.FailureHelper;
import com.subex.rocps.automation.helpers.application.aggregation.AggregationResult;
import com.subex.rocps.automation.helpers.application.bills.Bills;
import com.subex.rocps.automation.helpers.application.carrierinvoice.CarrierInvoice;
import com.subex.rocps.automation.helpers.application.carrierinvoice.CarrierInvoiceExcelTemplate;
import com.subex.rocps.automation.helpers.application.carrierinvoice.CarrierInvoiceManualTemplate;
import com.subex.rocps.automation.helpers.application.carrierinvoice.SystemFieldList;
import com.subex.rocps.automation.helpers.application.system.PSTaskSearchHelper;
import com.subex.rocps.automation.helpers.application.system.TaskSchedule;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;

public class TCPaymCollectionServerPrereq extends PSAcceptanceTest
{
	String path = System.getProperty( "user.dir" ) + "\\src\\main\\resources\\";
	String workBookName = "FunctionalTestCases.xlsx";
	String sheetName = "PaymentCollectionPrerequisite";

	@org.testng.annotations.Test( priority = 1, enabled = true, description = "task Controller capabilities", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void taskControllerCapabilities() throws Exception
	{
		try
		{

			TaskControllerHelper taskObj = new TaskControllerHelper();
			taskObj.setTaskControllerCapability( path, workBookName, "ROCPreRequisites2", "TCCapability", 1 );
			taskObj.setTaskControllerCapability( path, workBookName, sheetName, "TCCapability", 1 );

		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 2, enabled = true, description = "Mnr and Aggregation for BillServerPrerequistes" )
	public void BillServerPrerequistes() throws Exception

	{
		try
		{
			TaskSchedule taskObj = new TaskSchedule();
			PSTaskSearchHelper psTaskobj = new PSTaskSearchHelper();
			taskObj.fileCollection( path, workBookName, sheetName, "FileSchedule_PaymColl", 1 );
			psTaskobj.psVerifyTaskStatus( path, workBookName, "AggregationResults", "MnrTaskStatus", 1 );

		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 3, enabled = true, description = "schedule and verify  Aggregation for BillServerPrerequistes" )
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

	@org.testng.annotations.Test( priority = 4, enabled = true, description = "Generation of Bill for BillServerPrerequistes2", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void BillServerPrerequistes2() throws Exception

	{
		try
		{

			AggregationResult aggrResObj = new AggregationResult( path, workBookName, sheetName, "AggregationResult_BillPaymColl1" );
			aggrResObj.isAggregationResultPresent();

			AggregationResult aggrResObj1 = new AggregationResult( path, workBookName, sheetName, "AggregationResult_BillPaymColl2" );
			aggrResObj1.isAggregationResultPresent();

			Bills billObjec = new Bills( path, workBookName, sheetName, "HotBillCreate_BillPaymColl_invoice" );
			billObjec.billAction();
			Bills billObjec1 = new Bills( path, workBookName, sheetName, "HotBillCreate_Bill_statement" );
			billObjec1.billAction();

		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}

	@Test( priority = 5, enabled = true, description = "Create Carrier Invoice ", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
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

	@Test( priority = 6, enabled = true, description = "Create Carrier Invoice ", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void carrierInvoiceCreation2() throws Exception
	{
		try
		{

			CarrierInvoice ciObj1 = new CarrierInvoice( path, workBookName, "CarrierInvoice", "CI_ManualTmpl5_SFL", 1 );
			ciObj1.carrierInvoiceSearchConfig();
			CarrierInvoice ciObj2 = new CarrierInvoice( path, workBookName, "CarrierInvoice", "CI_AuthorizedACtion2", 1 );
			ciObj2.carrierInvoiceAuthorize();
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

	@Test( priority = 7, enabled = true, description = "Create Carrier Invoice ", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void carrierInvoiceCreation3() throws Exception
	{
		try
		{

			CarrierInvoice ciObj1 = new CarrierInvoice( path, workBookName, "CarrierInvoice", "CI_ManualTmpl6_SFL", 1 );
			ciObj1.carrierInvoiceSearchConfig();
			CarrierInvoice ciObj2 = new CarrierInvoice( path, workBookName, "CarrierInvoice", "CI_AuthorizedACtion3", 1 );
			ciObj2.carrierInvoiceAuthorize();
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}
}
