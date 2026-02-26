package com.subex.rocps.automation.testcases.functionaltesting;

import com.subex.rocps.automation.helpers.application.carrierinvoice.CarrierInvoiceEmailConfig;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;

import com.subex.automation.helpers.util.FailureHelper;

public class TCCarrierInvoiceEmailConfig extends PSAcceptanceTest
{
	String path = System.getProperty( "user.dir" ) + "\\src\\main\\resources\\";
	String workBookName = "FunctionalTestCases.xlsx";
	String sheetName = "CarrierInvoiceEmailConfig";

	@org.testng.annotations.Test( priority = 1, description = "Carrier Invoice EmailConfig search Screen column validation" )
	public void ciEmailConfigColVal() throws Exception
	{
		try
		{
			CarrierInvoiceEmailConfig ciObj = new CarrierInvoiceEmailConfig( path, workBookName, sheetName, "CIEmailConfig_SearchScreenColumnVal", 1 );
			ciObj.searchScreenColumnsValidation();
		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}
	
	@org.testng.annotations.Test( priority = 2, description = "CarrierInvoiceEmailConfig" )
	public void ciEmailConfigCreation() throws Exception
	{
		try
		{
			CarrierInvoiceEmailConfig ciObj = new CarrierInvoiceEmailConfig( path, workBookName, sheetName, "CIEmailConfig", 1 );
			ciObj.carrierInvoiceEmailConfigCreation();
			}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}
	
	@org.testng.annotations.Test( priority = 3, description = "CarrierInvoiceEmailConfig - Delete" )
	public void ciEmailConfigDelete() throws Exception
	{
		try
		{
			CarrierInvoiceEmailConfig ciObj = new CarrierInvoiceEmailConfig( path, workBookName, sheetName, "CIEmailConfig Delete", 1 );
			ciObj.carrierInvoiceEmailConfigDelete();
			}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}
	@org.testng.annotations.Test( priority = 4, description = "CarrierInvoiceEmailConfig- UnDelete" )
	public void ciEmailConfigUnDelete() throws Exception
	{
		try
		{
			CarrierInvoiceEmailConfig ciObj = new CarrierInvoiceEmailConfig( path, workBookName, sheetName, "CIEmailConfig UnDelete", 1 );
			ciObj.carrierInvoiceEmailConfigUnDelete();
			}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}

}
