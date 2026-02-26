package com.subex.rocps.automation.testcases.functionaltesting;

import com.subex.rocps.automation.helpers.application.bills.SalesTaxRate;
import com.subex.rocps.automation.helpers.application.referenceTable.SalesTax;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;

import com.subex.automation.helpers.util.FailureHelper;

public class TCSalesTaxRate extends PSAcceptanceTest
{
	String path = System.getProperty( "user.dir" ) + "\\src\\main\\resources\\";
	String workBookName = "FunctionalTestCases.xlsx";
	String sheetName = "SalesTaxRate";

	@org.testng.annotations.Test( priority = 1, description = "SalesTax Rate creation", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void salesTaxRateCreation() throws Exception
	{
		try
		{
			SalesTaxRate salesTaxObj = new SalesTaxRate( path, workBookName, sheetName, "Sales Tax Rate", 1 );
			salesTaxObj.salesTaxRateCreation();
		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 2, description = "SalesTax Rate - VAT creation", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void salesTaxRateVATCreation() throws Exception
	{
		try
		{
			SalesTaxRate salesTaxObj = new SalesTaxRate( path, workBookName, sheetName, "Sales Tax Rate-VAT", 1 );
			salesTaxObj.salesTaxRateCreation();
		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 3, description = "SalesTax Rate - India Sales Tax creation", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void salesTaxRateIndiaCreation() throws Exception
	{
		try
		{
			SalesTaxRate salesTaxObj = new SalesTaxRate( path, workBookName, sheetName, "Sales Tax Rate-IndiaSaleTax", 1 );
			salesTaxObj.salesTaxRateCreation();
		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 4, description = "SalesTax Rate - search screen column validation" )
	public void salesTaxRateColVal() throws Exception
	{
		try
		{
			SalesTaxRate salesTaxObj = new SalesTaxRate( path, workBookName, sheetName, "SalesTaxSearchScreencolVal", 1 );
			salesTaxObj.searchScreenColumnsValidation();
		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 5, description = "sales Tax Creation -UT", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void salesTaxCreationUT() throws Exception
	{
		try
		{
			SalesTax salesTaxObj = new SalesTax( path, workBookName, "SalesTax", "SalesTaxUT", 1 );
			salesTaxObj.salesTaxCreation();
		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 6, description = "sales Tax Creation-RST", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void salesTaxCreationRST() throws Exception
	{
		try
		{
			SalesTax salesTaxObj = new SalesTax( path, workBookName, "SalesTax", "SalesTaxRST", 1 );
			salesTaxObj.salesTaxCreation();
		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 7, description = "sales Tax Creation -TT", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void salesTaxCreationTT() throws Exception
	{
		try
		{
			SalesTax salesTaxObj = new SalesTax( path, workBookName, "SalesTax", "SalesTaxTT", 1 );
			salesTaxObj.salesTaxCreation();
		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 8, description = "salesTax Creation FT", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void salesTaxCreationFT() throws Exception
	{
		try
		{
			SalesTax salesTaxObj = new SalesTax( path, workBookName, "SalesTax", "SalesTaxFT", 1 );
			salesTaxObj.salesTaxCreation();
		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 9, description = "sales Tax Rate Creation-UT", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void salesTaxRateCreationUT() throws Exception
	{
		try
		{
			SalesTaxRate salesTaxObj = new SalesTaxRate( path, workBookName, sheetName, "SalesTaxUT Rate", 1 );
			salesTaxObj.salesTaxRateCreation();
		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 10, description = "sales Tax Rate Creation-RST", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void salesTaxRateCreationRST() throws Exception
	{
		try
		{
			SalesTaxRate salesTaxObj = new SalesTaxRate( path, workBookName, sheetName, "SalesTaxRST Rate", 1 );
			salesTaxObj.salesTaxRateCreation();
		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 11, description = "sales Tax Rate Creation-TT", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void salesTaxRateCreationTT() throws Exception
	{
		try
		{
			SalesTaxRate salesTaxObj = new SalesTaxRate( path, workBookName, sheetName, "SalesTaxTT Rate", 1 );
			salesTaxObj.salesTaxRateCreation();
		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 12, description = "sales Tax Rate Creation-FT", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void salesTaxRateCreationFT() throws Exception
	{
		try
		{
			SalesTaxRate salesTaxObj = new SalesTaxRate( path, workBookName, sheetName, "SalesTaxFT Rate", 1 );
			salesTaxObj.salesTaxRateCreation();
		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 13, description = "sales Tax Rate Edit" )
	public void salesTaxRateEdit() throws Exception
	{
		try
		{
			SalesTaxRate salesTaxObj = new SalesTaxRate( path, workBookName, sheetName, "SalesTaxEdit", 1 );
			salesTaxObj.salesTaxRateEdit();
		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 14, description = "sales Tax Rate Valid On Filter Verify" )
	public void salesTaxRateValidOnFilterVerify() throws Exception
	{
		try
		{
			SalesTaxRate salesTaxObj = new SalesTaxRate( path, workBookName, sheetName, "SalesTaxRate_ValidOnFilter", 1 );
			salesTaxObj.salesTaxRateValidOnFilterVerify();
		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}
}
