package com.subex.rocps.automation.testcases.functionaltesting;

import org.testng.annotations.Test;

import com.subex.rocps.automation.helpers.application.referenceTable.Currency;
import com.subex.rocps.automation.helpers.application.referenceTable.ImfCurrency;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;

import com.subex.automation.helpers.util.FailureHelper;

public class TCCurrency extends PSAcceptanceTest
{

	String path = System.getProperty( "user.dir" ) + "\\src\\main\\resources\\";

	String workBookName = "FunctionalTestCases.xlsx";
	String sheetName = "Currency";

	@Test( priority = 1, enabled = true, description = "'Currency'  verify the column Headers Of Search Screen" )
	public void verifyTheColHdrs() throws Exception
	{
		try
		{
			Currency currency = new Currency( path, workBookName, sheetName, "CurrencyScreencolVal" );
			currency.currencyColumsValidation();

		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

	@Test( priority = 2, enabled = true, description = "'Currency'  creation" )
	public void currencyCreation() throws Exception
	{
		try
		{
			Currency currency = new Currency( path, workBookName, sheetName, "CurrencyCreation" );
			currency.currencyCreation();

		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

	@Test( priority = 3, enabled = true, description = "'Currency' edit" )
	public void currencyEdit() throws Exception
	{
		try
		{
			Currency currency = new Currency( path, workBookName, sheetName, "CurrencyEdit" );
			currency.currencyEdit();

		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

	@Test( priority = 4, enabled = true, description = "'Currency'  creation  with SDR", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void currencyCreationSDR() throws Exception
	{
		try
		{
			Currency currency = new Currency( path, workBookName, sheetName, "CurrencyCreationSDR" );
			currency.currencyCreation();

		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

	@Test( priority = 5, enabled = true, description = "'Currency'  creation for IMF Exchange Rate Import", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void currencyCreation_IMFExchRateImport() throws Exception
	{
		try
		{
			Currency currency = new Currency( path, workBookName, sheetName, "CurrencyCreationIMF" );
			currency.currencyCreation();

		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

}
