package com.subex.rocps.automation.testcases.functionaltesting;

import com.subex.automation.helpers.util.FailureHelper;
import com.subex.rocps.automation.helpers.application.referenceTable.SalesTax;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;

public class TCSalesTax extends PSAcceptanceTest
{

	String path = System.getProperty( "user.dir" ) + "\\src\\main\\resources\\";
	String workBookName = "FunctionalTestCases.xlsx";
	String sheetName = "SalesTax";

	@org.testng.annotations.Test( priority = 1, description = "SalesTax creation", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void salesTaxCreation() throws Exception
	{
		try
		{
			SalesTax salesTaxObj = new SalesTax( path, workBookName, sheetName, "SalesTax", 1 );
			salesTaxObj.salesTaxCreation();
		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 2, description = "SalesTax- VAT creation", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void salesTaxVATCreation() throws Exception
	{
		try
		{
			SalesTax salesTaxObj = new SalesTax( path, workBookName, sheetName, "SalesTax VAT", 1 );
			salesTaxObj.salesTaxCreation();
		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 3, description = "SalesTax- Indiasale creation", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void salesTaxIndiaSaleCreation() throws Exception
	{
		try
		{
			SalesTax salesTaxObj = new SalesTax( path, workBookName, sheetName, "SalesTax IndiaSale", 1 );
			salesTaxObj.salesTaxCreation();
		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 4, description = "SalesTax search screen column validation" )
	public void salesTaxColVal() throws Exception
	{
		try
		{
			SalesTax salesTaxColValobj = new SalesTax( path, workBookName, sheetName, "SalesTaxSearchScreencolVal", 1 );
			salesTaxColValobj.searchScreenColumnsValidation();
		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 5, description = "SalesTax deletion" )
	public void salesTaxDelete() throws Exception
	{
		try
		{
			SalesTax salesTaxDelobj = new SalesTax( path, workBookName, sheetName, "SalesTaxDelete", 1 );
			salesTaxDelobj.salesTaxDelete();
		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 6, description = "SalesTax un delete" )
	public void salesTaxUnDelete() throws Exception
	{
		try
		{
			SalesTax salesTaxunDelobj = new SalesTax( path, workBookName, sheetName, "SalesTaxUnDelete", 1 );
			salesTaxunDelobj.salesTaxUnDelete();
		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 7, description = "SalesTax Edit" )
	public void salesTaxEdit() throws Exception
	{
		try
		{
			SalesTax salesTaxunDelobj = new SalesTax( path, workBookName, sheetName, "SalesTaxEdit", 1 );
			salesTaxunDelobj.salesTaxEdit();
		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}
}
