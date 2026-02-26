package com.subex.rocps.automation.testcases.functionaltesting;

import com.subex.rocps.automation.helpers.application.carrierinvoice.CarrierInvoice;
import com.subex.rocps.automation.helpers.application.carrierinvoice.CarrierInvoiceImport;
import com.subex.rocps.automation.helpers.application.carrierinvoice.InvoiceReconciliationRequest;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;

import com.subex.automation.helpers.application.screens.TaskSearchHelper;
import com.subex.automation.helpers.util.FailureHelper;

public class TCCarrierInvoiceExcelServerCases extends PSAcceptanceTest
{
	String path = System.getProperty( "user.dir" ) + "\\src\\main\\resources\\";
	String workBookName = "FunctionalTestCases.xlsx";
	String sheetName = "CarrierInvoice";
	@org.testng.annotations.Test( priority = 1, description = "CI Excel Import and CI Validation- without SFL" )
	public void ciExcelImport1Creation() throws Exception
	{
		try
		{
			CarrierInvoiceImport ciimpObj = new CarrierInvoiceImport( path, workBookName, sheetName, "CIImportRequest1", 1 );
			ciimpObj.carrierInvoiceImportRequest();
	
			TaskSearchHelper tskObj = new TaskSearchHelper();
			tskObj.verifyTaskStatus( path, workBookName, sheetName, "CITaskStatus", 1 );
	
			CarrierInvoiceImport ciReqObj = new CarrierInvoiceImport( path, workBookName, sheetName, "CIImportRequest1", 1 );
			ciReqObj.requestValidation();
	
			CarrierInvoice ciObj = new CarrierInvoice( path, workBookName, sheetName, "CI_ExcelImportVal1", 1 );
			ciObj.carrierInvoiceValidation();
		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}
	
	@org.testng.annotations.Test( priority = 2, description = "CI Excel Import and CI Validation- SFL" )
	public void ciExcelImport2Creation() throws Exception
	{
		try
		{
			CarrierInvoiceImport ciimpObj = new CarrierInvoiceImport( path, workBookName, sheetName, "CIImportRequest2", 1 );
			ciimpObj.carrierInvoiceImportRequest();
	
			TaskSearchHelper tskObj = new TaskSearchHelper();
			tskObj.verifyTaskStatus( path, workBookName, sheetName, "CITaskStatus", 1 );
	
			CarrierInvoiceImport ciReqObj = new CarrierInvoiceImport( path, workBookName, sheetName, "CIImportRequest2", 1 );
			ciReqObj.requestValidation();
	
			CarrierInvoice ciObj = new CarrierInvoice( path, workBookName, sheetName, "CI_ExcelImportVal2", 1 );
			ciObj.carrierInvoiceValidation();
		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}
	
	@org.testng.annotations.Test( priority = 3, description = "CI Excel Import and CI Validation- SFL Blened" )
	public void ciExcelImport3Creation() throws Exception
	{
		try
		{
			CarrierInvoiceImport ciimpObj = new CarrierInvoiceImport( path, workBookName, sheetName, "CIImportRequest3", 1 );
			ciimpObj.carrierInvoiceImportRequest();
	
			TaskSearchHelper tskObj = new TaskSearchHelper();
			tskObj.verifyTaskStatus( path, workBookName, sheetName, "CITaskStatus", 1 );
	
			CarrierInvoiceImport ciReqObj = new CarrierInvoiceImport( path, workBookName, sheetName, "CIImportRequest3", 1 );
			ciReqObj.requestValidation();
	
			CarrierInvoice ciObj = new CarrierInvoice( path, workBookName, sheetName, "CI_ExcelImportVal3", 1 );
			ciObj.carrierInvoiceValidation();
		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}
	
	@org.testng.annotations.Test( priority = 4, description = "CI Excel Import and CI Validation- SFL Blened" )
	public void ciExcelImport4Creation() throws Exception
	{
		try
		{
			CarrierInvoiceImport ciimpObj = new CarrierInvoiceImport( path, workBookName, sheetName, "CIImportRequest6", 1 );
			ciimpObj.carrierInvoiceImportRequest();
	
			TaskSearchHelper tskObj = new TaskSearchHelper();
			tskObj.verifyTaskStatus( path, workBookName, sheetName, "CITaskStatus", 1 );
	
			CarrierInvoiceImport ciReqObj = new CarrierInvoiceImport( path, workBookName, sheetName, "CIImportRequest3", 1 );
			ciReqObj.requestValidation();
	
			CarrierInvoice ciObj = new CarrierInvoice( path, workBookName, sheetName, "CI_ExcelImportVal3", 1 );
			ciObj.carrierInvoiceValidation();
		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}
	
	@org.testng.annotations.Test( priority = 5, description = "CI Excel Import duplciate error check" )
	public void ciExcelImportDuplicateErrorCheck() throws Exception
	{
		try
		{
			CarrierInvoiceImport ciimpObj = new CarrierInvoiceImport( path, workBookName, sheetName, "CIImportRequest4", 1 );
			ciimpObj.carrierInvoiceImportRequest();
	
			TaskSearchHelper tskObj = new TaskSearchHelper();
			tskObj.verifyTaskStatus( path, workBookName, sheetName, "CITaskStatus", 1 );
	
			CarrierInvoiceImport ciReqObj = new CarrierInvoiceImport( path, workBookName, sheetName, "CIImportRequest4", 1 );
			ciReqObj.requestValidation();
	
		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}
	
	@org.testng.annotations.Test( priority = 6, description = "CI Excel Import Mapping missing error check" )
	public void ciExcelImportMappingMissing() throws Exception
	{
		try
		{
			CarrierInvoiceImport ciimpObj = new CarrierInvoiceImport( path, workBookName, sheetName, "CIImportRequest5", 1 );
			ciimpObj.carrierInvoiceImportRequest();
	
			TaskSearchHelper tskObj = new TaskSearchHelper();
			tskObj.verifyTaskStatus( path, workBookName, sheetName, "CITaskStatus", 1 );
	
			CarrierInvoiceImport ciReqObj = new CarrierInvoiceImport( path, workBookName, sheetName, "CIImportRequest5", 1 );
			ciReqObj.requestValidation();
	
		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}
	
	@org.testng.annotations.Test( priority = 7, description = "CI Excel Import and CI Validation- Rejected - nonDelta-Excel" )
	public void ciExcelImport1RejectCreation() throws Exception
	{
		try
		{
			CarrierInvoiceImport ciimpObj = new CarrierInvoiceImport( path, workBookName, "ReconRequest", "CIImportRequest_Rejected_NonDelta", 1 );
			ciimpObj.carrierInvoiceImportRequest();
	
			TaskSearchHelper tskObj = new TaskSearchHelper();
			tskObj.verifyTaskStatus( path, workBookName, "ReconRequest", "CITaskStatus", 1 );
	
			CarrierInvoiceImport ciReqObj = new CarrierInvoiceImport( path, workBookName, "ReconRequest", "CIImportRequest_Rejected_NonDelta", 1 );
			ciReqObj.requestValidation();
	
			InvoiceReconciliationRequest autoObj = new InvoiceReconciliationRequest( path, workBookName, "ReconRequest", "Recon_CIImportRequest_Rejected_nondelta", 1 );
			autoObj.invoiceReconciliationRequestCreation();
			autoObj.invoiceReconConfigScheduleNow();
	
			tskObj.verifyTaskStatus( path, workBookName, "ReconRequest", "ReconTaskStatus", 1 );
	
			InvoiceReconciliationRequest reconObj = new InvoiceReconciliationRequest( path, workBookName, "ReconRequest", "Recon_CIImportRequest_Rejected_nondelta", 1 );
			reconObj.reconValidation();
	
			CarrierInvoiceImport ciimpAfterReconObj = new CarrierInvoiceImport( path, workBookName, "ReconRequest", "CIImportRequest_Rejected_afterRecon", 1 );
			ciimpAfterReconObj.carrierInvoiceImportRequest();
	
			tskObj.verifyTaskStatus( path, workBookName, "ReconRequest", "CITaskStatus", 1 );
	
			CarrierInvoiceImport ciReqAfterReconObj = new CarrierInvoiceImport( path, workBookName, "ReconRequest", "CIImportRequest_Rejected_afterRecon", 1 );
			ciReqAfterReconObj.requestValidation();
	
			CarrierInvoice ciAfterReconObj = new CarrierInvoice( path, workBookName, "ReconRequest", "CI_ExcelImportVal1_Rejected_nondelta", 1 );
			ciAfterReconObj.carrierInvoiceValidation();
	
		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}
	
	@org.testng.annotations.Test( priority = 8, description = "CI Excel Import and CI Validation- REplaced - nonDelta-Excel" )
	public void ciExcelImport2ReplacedCreation() throws Exception
	{
		try
		{
			CarrierInvoiceImport ciimpObj = new CarrierInvoiceImport( path, workBookName, "ReconRequest", "CIImportRequest2_Replaced_NonDelta", 1 );
			ciimpObj.carrierInvoiceImportRequest();
	
			TaskSearchHelper tskObj = new TaskSearchHelper();
			tskObj.verifyTaskStatus( path, workBookName, "ReconRequest", "CITaskStatus", 1 );
	
			CarrierInvoiceImport ciReqObj = new CarrierInvoiceImport( path, workBookName, "ReconRequest", "CIImportRequest2_Replaced_NonDelta", 1 );
			ciReqObj.requestValidation();
	
			CarrierInvoice ciObj = new CarrierInvoice( path, workBookName, "ReconRequest", "CI_AuthorizedACtion_NonDelta", 1 );
			ciObj.carrierInvoiceAuthorize();
	
			InvoiceReconciliationRequest autoObj = new InvoiceReconciliationRequest( path, workBookName, "ReconRequest", "Recon_CIImportRequest_Replaced_nondelta", 1 );
			autoObj.invoiceReconciliationRequestCreation();
			autoObj.invoiceReconConfigScheduleNow();
	
			tskObj.verifyTaskStatus( path, workBookName, "ReconRequest", "ReconTaskStatus", 1 );
	
			InvoiceReconciliationRequest reconObj = new InvoiceReconciliationRequest( path, workBookName, "ReconRequest", "Recon_CIImportRequest_Replaced_nondelta", 1 );
			reconObj.reconValidation();
	
			CarrierInvoiceImport ciimpAfterReconObj = new CarrierInvoiceImport( path, workBookName, "ReconRequest", "CIImportRequest_Replaced_afterRecon", 1 );
			ciimpAfterReconObj.carrierInvoiceImportRequest();
	
			tskObj.verifyTaskStatus( path, workBookName, "ReconRequest", "CITaskStatus", 1 );
	
			CarrierInvoiceImport ciReqAfterReconObj = new CarrierInvoiceImport( path, workBookName, "ReconRequest", "CIImportRequest_Replaced_afterRecon", 1 );
			ciReqAfterReconObj.requestValidation();
	
			CarrierInvoice ciAfterReconObj = new CarrierInvoice( path, workBookName, "ReconRequest", "CI_ExcelImportVal1_Replaced_nondelta", 1 );
			ciAfterReconObj.carrierInvoiceValidation();
	
		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}
	
	@org.testng.annotations.Test( priority = 9, description = "CI Excel Import and CI Validation- Rejected - Delta-Excel" )
	public void ciExcelImport1RejectDeltaCreation() throws Exception
	{
		try
		{
			CarrierInvoiceImport ciimpObj = new CarrierInvoiceImport( path, workBookName, "ReconRequest", "CIImportRequest_Rejected_Delta", 1 );
			ciimpObj.carrierInvoiceImportRequest();
	
			TaskSearchHelper tskObj = new TaskSearchHelper();
		///	tskObj.verifyTaskStatus( path, workBookName, "ReconRequest", "CITaskStatus", 1 );
	
			CarrierInvoiceImport ciReqObj = new CarrierInvoiceImport( path, workBookName, "ReconRequest", "CIImportRequest_Rejected_Delta", 1 );
			ciReqObj.requestValidation();			
	
			InvoiceReconciliationRequest autoObj = new InvoiceReconciliationRequest( path, workBookName, "ReconRequest", "Recon_Rejected_delta", 1 );
			autoObj.invoiceReconciliationRequestCreation();
			autoObj.invoiceReconConfigScheduleNow();
	
			//tskObj.verifyTaskStatus( path, workBookName, "ReconRequest", "ReconTaskStatus", 1 );
	
			InvoiceReconciliationRequest reconObj = new InvoiceReconciliationRequest( path, workBookName, "ReconRequest", "Recon_Rejected_delta", 1 );
			reconObj.reconValidation();
	
			CarrierInvoiceImport ciimpAfterReconObj = new CarrierInvoiceImport( path, workBookName, "ReconRequest", "CIImportReq_Rejected_afterRecon_Delta", 1 );
			ciimpAfterReconObj.carrierInvoiceImportRequest();
	
			//tskObj.verifyTaskStatus( path, workBookName, "ReconRequest", "CITaskStatus", 1 );
	
			CarrierInvoiceImport ciReqAfterReconObj = new CarrierInvoiceImport( path, workBookName, "ReconRequest", "CIImportReq_Rejected_afterRecon_Delta", 1 );
			ciReqAfterReconObj.requestValidation();
	
			CarrierInvoice ciAfterReconObj = new CarrierInvoice( path, workBookName, "ReconRequest", "CExcelImportVal_RejectedDelta_usg", 1 );
			ciAfterReconObj.carrierInvoiceValidation();
	
		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}

}
