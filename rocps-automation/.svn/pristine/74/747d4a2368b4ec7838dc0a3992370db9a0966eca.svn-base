package com.subex.rocps.automation.testcases.functionaltesting;

import com.subex.rocps.automation.helpers.application.carrierinvoice.CarrierInvoiceExcelTemplate;
import com.subex.rocps.automation.helpers.application.carrierinvoice.CarrierInvoiceImport;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;

import com.subex.automation.helpers.util.FailureHelper;

public class TCCarrierInvoiceImportRequest extends PSAcceptanceTest
{
	String path = System.getProperty( "user.dir" ) + "\\src\\main\\resources\\";
	String workBookName = "FunctionalTestCases.xlsx";
	String sheetName = "CarrierInvoiceImport";

	@org.testng.annotations.Test( priority = 1, description = "Carrier Invoice Import Request search Screen column validation" )
	public void ciImportColVal() throws Exception
	{
		try
		{
			CarrierInvoiceImport ciObj = new CarrierInvoiceImport( path, workBookName, sheetName, "CIImport_SearchScreenColumnVal", 1 );
			ciObj.searchScreenColumnsValidation();
		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}
	
	@org.testng.annotations.Test( priority = 2, description = "Carrier Invoice Import Request" )
	public void ciImportRequest1() throws Exception
	{
		try
		{
			CarrierInvoiceImport ciObj = new CarrierInvoiceImport( path, workBookName, sheetName, "CIImportRequest1", 1 );
			ciObj.carrierInvoiceImportRequest();
		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}
	
		@org.testng.annotations.Test( priority = 3, description = "Carrier Invoice Import Request-Schedule Now Action" )
		public void ciImportRequest2() throws Exception
		{
			try
			{
				CarrierInvoiceImport ciObj = new CarrierInvoiceImport( path, workBookName, sheetName, "CIImportScheduleAction", 1 );
				ciObj.carrierInvoiceImportRequest();
				ciObj.scheduleNow();
			}
			catch ( Exception e )
			{
				FailureHelper.reportFailure( e );
				throw e;
			}
		}

	@org.testng.annotations.Test( priority = 4, description = "Carrier Invoice Import Request-Schedule Now Action" )
	public void ciImportViewTemplate() throws Exception
	{
		try
		{
			CarrierInvoiceImport ciObj = new CarrierInvoiceImport( path, workBookName, sheetName, "CIImportViewTemplate", 1 );
			ciObj.viewTemplate();

		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}
}
