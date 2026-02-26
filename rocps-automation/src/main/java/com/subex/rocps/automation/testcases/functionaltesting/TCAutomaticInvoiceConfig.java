package com.subex.rocps.automation.testcases.functionaltesting;

import com.subex.rocps.automation.helpers.application.bills.CreditNotes;
import com.subex.rocps.automation.helpers.application.carrierinvoice.AutomaticInvoiceConfig;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;

import com.subex.automation.helpers.util.FailureHelper;

public class TCAutomaticInvoiceConfig extends PSAcceptanceTest
{
	String path = System.getProperty( "user.dir" ) + "\\src\\main\\resources\\";
	String workBookName = "FunctionalTestCases.xlsx";
	String sheetName = "AutomaticInvoiceConfig";
	
	
	@org.testng.annotations.Test( priority = 1, description = "Automatic Invoice Config search screen column validation" )
	public void autoInvoiceConfigColVal() throws Exception
	{
		try
		{
			AutomaticInvoiceConfig autoObj = new AutomaticInvoiceConfig( path, workBookName, sheetName, "AutoInvoice_ColumnVal", 1 );
			autoObj.searchScreenColumnsValidation();
		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 2, description = "Automatic Invoice Config creation" )
	public void autoInvoiceConfig() throws Exception
	{
		try
		{
			AutomaticInvoiceConfig autoObj = new AutomaticInvoiceConfig( path, workBookName, sheetName, "AutoInvoiceCreation", 1 );
			autoObj.autoInvoiceCongigCreation();
		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}
	
	@org.testng.annotations.Test( priority = 3, description = "Automatic Invoice Config Delete" )
	public void autoInvoiceConfigDelete() throws Exception
	{
		try
		{
			AutomaticInvoiceConfig autoObj = new AutomaticInvoiceConfig( path, workBookName, sheetName, "AutoInvoiceDelete", 1 );
			autoObj.automaticInvoiceConfigDelete();
		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}
	
	@org.testng.annotations.Test( priority = 4, description = "Automatic Invoice Config unDelete" )
	public void autoInvoiceConfigUnDelete() throws Exception
	{
		try
		{
			AutomaticInvoiceConfig autoObj = new AutomaticInvoiceConfig( path, workBookName, sheetName, "AutoInvoiceUnDelete", 1 );
			autoObj.automaticInvoiceConfigUnDelete();
		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 5, description = "Automatic Invoice Config creation-Only dispute" )
	public void autoInvoiceConfigDispute() throws Exception
	{
		try
		{
			AutomaticInvoiceConfig autoObj = new AutomaticInvoiceConfig( path, workBookName, sheetName, "AutoInvoiceCreation_onlyDispute", 1 );
			autoObj.autoInvoiceCongigCreation();
		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}
	
	@org.testng.annotations.Test( priority = 6, description = "Automatic Invoice Config creation-With compareTotalAmount " )
	public void autoInvoiceConfigCompareTotalAmt() throws Exception
	{
		try
		{
			AutomaticInvoiceConfig autoObj = new AutomaticInvoiceConfig( path, workBookName, sheetName, "AutoInvoiceCreation_WithcompareTotalAmount", 1 );
			autoObj.autoInvoiceCongigCreation();
		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}
	

	@org.testng.annotations.Test( priority = 7, description = "Automatic Invoice Config edit " )
	public void autoInvoiceConfigEdit() throws Exception
	{
		try
		{
			AutomaticInvoiceConfig autoObj = new AutomaticInvoiceConfig( path, workBookName, sheetName, "EditAutoInvoiceCreation", 1 );
			autoObj.editAutoInvoiceCongigCreation();
		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}
}
