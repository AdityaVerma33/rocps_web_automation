package com.subex.rocps.automation.testcases.functionaltesting;

import org.testng.annotations.Test;

import com.subex.rocps.automation.helpers.application.referenceTable.ImfCurrency;
import com.subex.rocps.automation.helpers.application.referenceTable.RoamingDiscounting;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;

import com.subex.automation.helpers.util.FailureHelper;

public class TCIMFCurrency extends PSAcceptanceTest
{

	String path = System.getProperty( "user.dir" ) + "\\src\\main\\resources\\";

	String workBookName = "FunctionalTestCases.xlsx";
	String sheetName = "ImfCurrency";

	@Test( priority = 1, enabled = true, description = "'IMF Currency'  verify the column Headers Of Search Screen" )
	public void verifyTheColHdrs() throws Exception
	{
		try
		{
			ImfCurrency imfCurrency = new ImfCurrency( path, workBookName, sheetName, "ImfCurrencyScreencolVal" );
			imfCurrency.imfCurrencyColumsValidation();

		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

	@Test( priority = 2, enabled = true, description = "'IMF Currency'  creation " )
	public void imfCurrencyCreation() throws Exception
	{
		try
		{
			ImfCurrency imfCurrency = new ImfCurrency( path, workBookName, sheetName, "ImfCurrencyCreation" );
			imfCurrency.imfCurrencyCreation();

		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

	@Test( priority = 3, enabled = true, description = "'IMF Currency'  Edit " )
	public void imfCurrencyEdit() throws Exception
	{
		try
		{
			ImfCurrency imfCurrency = new ImfCurrency( path, workBookName, sheetName, "ImfCurrencyEdit" );
			imfCurrency.imfCurrencyEdit();

		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

	@Test( priority = 4, enabled = true, description = "'IMF Currency'  creation  for IMF Exchange Rate Import", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void imfCurrencyCreationImfExchRateImport() throws Exception
	{
		try
		{
			ImfCurrency imfCurrency = new ImfCurrency( path, workBookName, sheetName, "ImfCurrencyCreation_Import" );
			imfCurrency.imfCurrencyCreation();

		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}
}
