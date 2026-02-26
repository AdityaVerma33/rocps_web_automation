package com.subex.rocps.automation.testcases.functionaltesting;

import com.subex.rocps.automation.helpers.application.aggregation.AggregationResult;
import com.subex.rocps.automation.helpers.application.carrierinvoice.CarrierInvoice;
import com.subex.rocps.automation.helpers.application.carrierinvoice.CarrierInvoiceImport;
import com.subex.rocps.automation.helpers.application.carrierinvoice.InvoiceReconciliationRequest;
import com.subex.rocps.automation.helpers.application.dispute.DisputeHelper;
import com.subex.rocps.automation.helpers.application.system.TaskSchedule;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;

import com.subex.automation.helpers.application.screens.TaskSearchHelper;
import com.subex.automation.helpers.util.FailureHelper;

public class TCCarrierInvoiceManualServerCases extends PSAcceptanceTest
{
	String path = System.getProperty( "user.dir" ) + "\\src\\main\\resources\\";
	String workBookName = "FunctionalTestCases.xlsx";
	String sheetName = "CarrierInvoice";

	@org.testng.annotations.Test( priority = 1, description = "Mnr and Aggregation for bills" )
	public void mnrAndAggregation() throws Exception
	{
		try
		{
			TaskSchedule taskObj = new TaskSchedule();
			taskObj.fileCollection( path, workBookName, sheetName, "FileSchedule CI", 1 );
	
			TaskSearchHelper tskObj = new TaskSearchHelper();
			tskObj.verifyTaskStatus( path, workBookName, "AggregationResults", "MnrTaskStatus", 1 );
	
			taskObj.scheduleRecurringTask( path, workBookName, "AggregationResults", "RecurringTask", 1 );
	
			tskObj.verifyTaskStatus( path, workBookName, "AggregationResults", "AggrTaskStatus", 1 );
	
			AggregationResult aggrResObj = new AggregationResult( path, workBookName, sheetName, "AggregationResult CI" );
			aggrResObj.viewAggregationResult();
	
		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}
	
	@org.testng.annotations.Test( priority = 2, description = "Carrier Invoice Search creation- Template 1" )
	public void ciLoadCreation() throws Exception
	{
		try
		{
			CarrierInvoice ciObj = new CarrierInvoice( path, workBookName, sheetName, "CI_ManualTmpl1_TableInst_Load", 1 );
			ciObj.carrierInvoiceSearchConfig();
		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}
	
	@org.testng.annotations.Test( priority = 3, description = "Carrier Invoice Manual NonDelta -Reject " )
	public void ciNonDeltaReject() throws Exception
	{
		try
		{
	
			CarrierInvoice ciObj = new CarrierInvoice( path, workBookName, "ReconRequest", "CI_ManualTmpl1_nondelta_recon", 1 );
			ciObj.carrierInvoiceSearchConfig();
	
			InvoiceReconciliationRequest autoObj = new InvoiceReconciliationRequest( path, workBookName, "ReconRequest", "Recon_Request1_nondelta", 1 );
			autoObj.invoiceReconciliationRequestCreation();
			autoObj.invoiceReconConfigScheduleNow();
	
			TaskSearchHelper tskObj = new TaskSearchHelper();
			tskObj.verifyTaskStatus( path, workBookName, "ReconRequest", "ReconTaskStatus", 1 );
	
			InvoiceReconciliationRequest reconObj = new InvoiceReconciliationRequest( path, workBookName, "ReconRequest", "Recon_Request1_nondelta", 1 );
			reconObj.reconValidation();
	
			CarrierInvoice ci1Obj = new CarrierInvoice( path, workBookName, "ReconRequest", "CI_ManualTmpl1_nondelta_recon_Rejected", 1 );
			ci1Obj.carrierInvoiceSearchConfig();
	
		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}
	
	@org.testng.annotations.Test( priority = 4, description = "Carrier Invoice Delta -Draft " )
	public void ciDeltaDraft() throws Exception
	{
		try
		{
	
			CarrierInvoice ciObj = new CarrierInvoice( path, workBookName, "ReconRequest", "CI_ManualTmpl1_Delta_Recon", 1 );
			ciObj.carrierInvoiceSearchConfig();
	
			InvoiceReconciliationRequest autoObj = new InvoiceReconciliationRequest( path, workBookName, "ReconRequest", "Recon_Request2_delta", 1 );
			autoObj.invoiceReconciliationRequestCreation();
			autoObj.invoiceReconConfigScheduleNow();
	
			TaskSearchHelper tskObj = new TaskSearchHelper();
			tskObj.verifyTaskStatus( path, workBookName, "ReconRequest", "ReconTaskStatus", 1 );
	
			InvoiceReconciliationRequest reconObj = new InvoiceReconciliationRequest( path, workBookName, "ReconRequest", "Recon_Request2_delta", 1 );
			reconObj.reconValidation();
	
			CarrierInvoice ci1Obj = new CarrierInvoice( path, workBookName, "ReconRequest", "CI_ManualTmpl1_Delta_Recon_Draft", 1 );
			ci1Obj.carrierInvoiceSearchConfig();
	
		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}
	
	@org.testng.annotations.Test( priority = 5, description = "Carrier Invoice NonDelta -Reject " )
	public void ciNonDeltaReplaced() throws Exception
	{
		try
		{
	
			CarrierInvoice ciObj = new CarrierInvoice( path, workBookName, "ReconRequest", "CI_ManualTmpl1_nondelta_recon", 1 );
			ciObj.carrierInvoiceSearchConfig();
	
			InvoiceReconciliationRequest autoObj = new InvoiceReconciliationRequest( path, workBookName, "ReconRequest", "Recon_Request1_nondelta", 1 );
			autoObj.invoiceReconciliationRequestCreation();
			autoObj.invoiceReconConfigScheduleNow();
	
			TaskSearchHelper tskObj = new TaskSearchHelper();
			tskObj.verifyTaskStatus( path, workBookName, "ReconRequest", "ReconTaskStatus", 1 );
	
			InvoiceReconciliationRequest reconObj = new InvoiceReconciliationRequest( path, workBookName, "ReconRequest", "Recon_Request1_nondelta", 1 );
			reconObj.reconValidation();
	
			CarrierInvoice ci1Obj = new CarrierInvoice( path, workBookName, "ReconRequest", "CI_ManualTmpl1_nondelta_recon_Rejected", 1 );
			ci1Obj.carrierInvoiceSearchConfig();
	
		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 6, description = "CI Manual - Authorize " )
	public void ciManualAuthorizeJumpTo() throws Exception
	{
		try
		{
			CarrierInvoice ciObj = new CarrierInvoice( path, workBookName, "CarrierInvoiceServerCase", "CI_ManualTmpl1_authorize", 1 );
			ciObj.carrierInvoiceSearchConfig();

			InvoiceReconciliationRequest autoObj = new InvoiceReconciliationRequest( path, workBookName, "CarrierInvoiceServerCase", "Recon_Request1_Authorize", 1 );
			autoObj.invoiceReconciliationRequestCreation();
			autoObj.invoiceReconConfigScheduleNow();

			InvoiceReconciliationRequest reconObj = new InvoiceReconciliationRequest( path, workBookName, "CarrierInvoiceServerCase", "Recon_Request1_Authorize", 1 );
			reconObj.reconValidation();

			CarrierInvoice ci1Obj = new CarrierInvoice( path, workBookName, "CarrierInvoiceServerCase", "CI_ManualTmpl1_nondelta_recon_Rejected", 1 );
			ci1Obj.carrierInvoiceSearchConfig();
			
			InvoiceReconciliationRequest reconBaseObj = new InvoiceReconciliationRequest( path, workBookName, "CarrierInvoiceServerCase", "Recon_Baseline", 1 );
			reconBaseObj.invoiceReconConfigBaseLine();

			CarrierInvoice ci2Obj = new CarrierInvoice( path, workBookName, "CarrierInvoiceServerCase", "CI_ManualTmpl1_AuthorizeAction", 1 );
			ci2Obj.carrierInvoiceAuthorize();
			ci2Obj.ciValidation();
			CarrierInvoice ciJumptoObj = new CarrierInvoice( path, workBookName, "CarrierInvoiceServerCase", "CI_ManualTmpl1_AuthorizeAction", 1 );
			ciJumptoObj.carrierInvoiceJumpTo();

		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}
	
		@org.testng.annotations.Test( priority = 7, description = "CI Manual - Authorize " )
		public void ciManualAuthorize() throws Exception
		{
			try
			{
				
				InvoiceReconciliationRequest autoObj = new InvoiceReconciliationRequest( path, workBookName, "CarrierInvoiceServerCase", "Recon_Request1_Authorize", 1 );
				autoObj.invoiceReconciliationRequestCreation();
				autoObj.invoiceReconConfigScheduleNow();

				InvoiceReconciliationRequest reconObj = new InvoiceReconciliationRequest( path, workBookName, "CarrierInvoiceServerCase", "Recon_Request1_Authorize", 1 );
				reconObj.reconValidation();

				CarrierInvoice ci1Obj = new CarrierInvoice( path, workBookName, "CarrierInvoiceServerCase", "CI_ManualTmpl1_nondelta_recon_Rejected", 1 );
				ci1Obj.carrierInvoiceSearchConfig();
				
				InvoiceReconciliationRequest reconBaseObj = new InvoiceReconciliationRequest( path, workBookName, "CarrierInvoiceServerCase", "Recon_Baseline", 1 );
				reconBaseObj.invoiceReconConfigBaseLine();
				CarrierInvoice ciJumptoObj = new CarrierInvoice( path, workBookName, "CarrierInvoiceServerCase", "CI_ManualTmpl1_AuthorizeAction", 1 );
				ciJumptoObj.carrierInvoiceJumpTo();
				
				DisputeHelper disputeobj = new DisputeHelper(path, workBookName, "CarrierInvoiceServerCase", "ValidateTheDispute");
				disputeobj.validateDispute();
				
				DisputeHelper disputeHelper = new DisputeHelper(path, workBookName, "CarrierInvoiceServerCase", "MoveToDisputeResolve");
				disputeHelper.moveToDisputeResolve();
				
				DisputeHelper disputeObj = new DisputeHelper(path, workBookName, "CarrierInvoiceServerCase", "CloseDispute");
				disputeObj.closeDispute();
				
				CarrierInvoice ci3Obj = new CarrierInvoice( path, workBookName, "CarrierInvoiceServerCase", "CI_ManualTmpl1_AuthorizeAction2", 1 );
				ci3Obj.carrierInvoiceAuthorize();
	
			}
			catch ( Exception e )
			{
				FailureHelper.reportFailure( e );
				throw e;
			}
		}
}
