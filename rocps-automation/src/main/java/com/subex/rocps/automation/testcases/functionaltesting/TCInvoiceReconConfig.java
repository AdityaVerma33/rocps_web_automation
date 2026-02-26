package com.subex.rocps.automation.testcases.functionaltesting;

import com.subex.rocps.automation.helpers.application.carrierinvoice.AutomaticInvoiceConfig;
import com.subex.rocps.automation.helpers.application.carrierinvoice.InvoiceReconConfig;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;

import com.subex.automation.helpers.application.screens.TaskControllerHelper;
import com.subex.automation.helpers.util.FailureHelper;

public class TCInvoiceReconConfig extends PSAcceptanceTest
{
	String path = System.getProperty( "user.dir" ) + "\\src\\main\\resources\\";
	String workBookName = "FunctionalTestCases.xlsx";
	String sheetName = "InvoiceReconConfig";

	@org.testng.annotations.Test( priority = 1, description = "Invoice Recon Config search screen column validation" )
	public void invoiceReconConfigColVal() throws Exception
	{
		try
		{
			InvoiceReconConfig autoObj = new InvoiceReconConfig( path, workBookName, sheetName, "Recon_ColumnVal", 1 );
			autoObj.searchScreenColumnsValidation();
		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}
	@org.testng.annotations.Test( priority = 2, description = "Invoice Recon Config Creation" , retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class)
	public void invoiceReconConfigManualCreation() throws Exception
	{
		try
		{
			InvoiceReconConfig autoObj = new InvoiceReconConfig( path, workBookName, sheetName, "ReconConfig Manul", 1 );
			autoObj.invoiceReconConfigCreation();
		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}
	@org.testng.annotations.Test( priority = 3, description = "Invoice Recon Config Creation" )
	public void invoiceReconConfigCreation() throws Exception
	{
		try
		{
			InvoiceReconConfig autoObj = new InvoiceReconConfig( path, workBookName, sheetName, "ReconConfig", 1 );
			autoObj.invoiceReconConfigCreation();
		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}
	
	@org.testng.annotations.Test( priority = 4, description = "Invoice Recon Config Creation with SFL" )
	public void invoiceReconConfigSFLCreation() throws Exception
	{
		try
		{
			InvoiceReconConfig autoObj = new InvoiceReconConfig( path, workBookName, sheetName, "ReconConfig_SFL", 1 );
			autoObj.invoiceReconConfigCreation();
		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}
	
	@org.testng.annotations.Test( priority = 5, description = "Invoice Recon Config Creation with SFL" )
	public void invoiceReconConfigSFLBlendedCreation() throws Exception
	{
		try
		{
			InvoiceReconConfig autoObj = new InvoiceReconConfig( path, workBookName, sheetName, "ReconConfig_SFL_Blended", 1 );
			autoObj.invoiceReconConfigCreation();
		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}
	
	@org.testng.annotations.Test( priority = 6, description = "Invoice Recon Config Creation with SFL" ,retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class)
	public void invoiceReconConfigSFLAutoConfigCreation() throws Exception
	{
		try
		{
			InvoiceReconConfig autoObj = new InvoiceReconConfig( path, workBookName, sheetName, "ReconConfig_SFL_AutoConfig", 1 );
			autoObj.invoiceReconConfigCreation();
		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}
	
	@org.testng.annotations.Test( priority = 7, description = "Invoice Recon Config -Delete" )
	public void invoiceReconConfigDelete() throws Exception
	{
		try
		{
			InvoiceReconConfig autoObj = new InvoiceReconConfig( path, workBookName, sheetName, "ReconDelete", 1 );
			autoObj.invoiceReconConfigDelete();
		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}
	
	@org.testng.annotations.Test( priority = 8, description = "Invoice Recon Config UnDelete" )
	public void invoiceReconConfigUnDelete() throws Exception
	{
		try
		{
			InvoiceReconConfig autoObj = new InvoiceReconConfig( path, workBookName, sheetName, "ReconUnDelete", 1 );
			autoObj.invoiceReconConfigUnDelete();
		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}
	
	/*@org.testng.annotations.Test( priority = 9, description = "Invoice Recon Config Re-Order" )
	public void invoiceReconConfigReorder() throws Exception
	{
		try
		{
			InvoiceReconConfig autoObj = new InvoiceReconConfig( path, workBookName, sheetName, "ReconReorder", 1 );
			autoObj.invoiceReconConfigReorder();
		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}*/
	@org.testng.annotations.Test( priority = 9, description = "task Controller capabilities" , retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class)
	public void taskControllerCapabilities() throws Exception
	{
		try
		{

			TaskControllerHelper taskObj = new TaskControllerHelper();
			taskObj.setTaskControllerCapability( path, workBookName, "ReconRequest", "TCCapability", 1 );

		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}
	@org.testng.annotations.Test( priority = 10, description = "Invoice Recon Config Creation  non usage" ,retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class)
	public void invoiceReconConfigNonUsgCreation() throws Exception
	{
		try
		{
			InvoiceReconConfig autoObj = new InvoiceReconConfig( path, workBookName, sheetName, "ReconConfig_SFL_Nonusg", 1 );
			autoObj.invoiceReconConfigCreation();
		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}
	@org.testng.annotations.Test( priority = 11, description = "Invoice Recon Config Creation  withCompareTotalAmount" ,retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class)
	public void invoiceReconConfigUsgCompTotlAmtCreation() throws Exception
	{
		try
		{
			InvoiceReconConfig autoObj = new InvoiceReconConfig( path, workBookName, sheetName, "ReconConfig_withCompareTotalAmount", 1 );
			autoObj.invoiceReconConfigCreation();
		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}
}
