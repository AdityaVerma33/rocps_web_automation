package com.subex.rocps.automation.testcases.functionaltesting;

import com.subex.rocps.automation.helpers.application.carrierinvoice.CarrierInvoiceExcelTemplate;
import com.subex.rocps.automation.helpers.application.carrierinvoice.CarrierInvoiceManualTemplate;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;

import com.subex.automation.helpers.util.FailureHelper;

public class TCCarrierInvoiceManualTemplate extends PSAcceptanceTest
{
	String path = System.getProperty( "user.dir" ) + "\\src\\main\\resources\\";
	String workBookName = "FunctionalTestCases.xlsx";
	String sheetName = "CarrierInvoiceTemplateManual";

	@org.testng.annotations.Test( priority = 1, description = "Carrier Invoice manual Template creation- without SFL" , retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class)
	public void ciTemplate1() throws Exception
	{
		try
		{
			CarrierInvoiceManualTemplate ciObj = new CarrierInvoiceManualTemplate( path, workBookName, sheetName, "CI_ManualTmpl1", 1 );
			ciObj.ciManualTemplateConfig();
			CarrierInvoiceExcelTemplate ciAppObj = new CarrierInvoiceExcelTemplate( path, workBookName, sheetName, "CI_ManualTmpl1", 1 );
			ciAppObj.approveCarrireInvoiceTemplate();
		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 2, description = "Carrier Invoice Manual Template creation- SFL Blended" )
	public void ciTemplate2() throws Exception
	{
		try
		{
			CarrierInvoiceManualTemplate ciObj = new CarrierInvoiceManualTemplate( path, workBookName, sheetName, "CI_ManualTmpl2_SFLBlended", 1 );
			ciObj.ciManualTemplateConfig();
			CarrierInvoiceExcelTemplate ciAppObj = new CarrierInvoiceExcelTemplate( path, workBookName, sheetName, "CI_ManualTmpl2_SFLBlended", 1 );
			ciAppObj.approveCarrireInvoiceTemplate();

		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 3, description = "Carrier Invoice ManualTemplate creation- SFL" )
	public void ciTemplate3SFL() throws Exception
	{
		try
		{
			CarrierInvoiceManualTemplate ciObj = new CarrierInvoiceManualTemplate( path, workBookName, sheetName, "CI_ManualTmpl5_SFL", 1 );
			ciObj.ciManualTemplateConfig();
			CarrierInvoiceExcelTemplate ciAppObj = new CarrierInvoiceExcelTemplate( path, workBookName, sheetName, "CI_ManualTmpl5_SFL", 1 );
			ciAppObj.approveCarrireInvoiceTemplate();

		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 4, description = "Carrier Invoice Template creation- nonusg" )
	public void ciTemplatenonUsage() throws Exception
	{
		try
		{
			CarrierInvoiceManualTemplate ciObj = new CarrierInvoiceManualTemplate( path, workBookName, sheetName, "CI_ManualTmpl3_nonusage", 1 );
			ciObj.ciManualTemplateConfig();
		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 5, description = "Carrier Invoice Template creation- creditnote" )
	public void ciTemplatecreditnote() throws Exception
	{
		try
		{
			CarrierInvoiceManualTemplate ciObj = new CarrierInvoiceManualTemplate( path, workBookName, sheetName, "CI_ManualTmpl4_creditnote", 1 );
			ciObj.ciManualTemplateConfig();
		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}
}
