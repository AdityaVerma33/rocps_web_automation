package com.subex.rocps.automation.testcases.functionaltesting;

import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;

import com.subex.automation.helpers.application.screens.BandHelper;
import com.subex.automation.helpers.application.screens.TariffClassHelper;
import com.subex.automation.helpers.util.FailureHelper;

public class TCTariffClass extends PSAcceptanceTest
{
	String path = System.getProperty( "user.dir" ) + "\\src\\main\\resources\\";
	String workBookName = "FunctionalTestCases.xlsx";
	String sheetName = "TariffClass";

	@org.testng.annotations.Test( priority = 1, description = "TariffClass creation for Transit", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void createTariffClassTransit() throws Exception
	{
		try
		{
			TariffClassHelper trffClassObj = new TariffClassHelper();
			trffClassObj.createTariffClass( path, workBookName, sheetName, "TariffClass Transit", 1 );
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 2, description = "TariffClass creation for Incoming", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void createTariffClassIncoming() throws Exception
	{
		try
		{
			TariffClassHelper trffClassObj = new TariffClassHelper();
			trffClassObj.createTariffClass( path, workBookName, sheetName, "TariffClass Incoming", 1 );
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 3, description = "TariffClass creation for FreePhone", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void createTariffClassFreePhone() throws Exception
	{
		try
		{
			TariffClassHelper trffClassObj = new TariffClassHelper();
			trffClassObj.createTariffClass( path, workBookName, sheetName, "TariffClass FreePhone", 1 );
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 4, description = "TariffClass creation for Outgoing", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void createTariffClassOutgoing() throws Exception
	{
		try
		{
			TariffClassHelper trffClassObj = new TariffClassHelper();
			trffClassObj.createTariffClass( path, workBookName, sheetName, "TariffClass Outgoing", 1 );
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 5, description = "TariffClass creation for advance rating", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void createTariffClassAdvanceRating() throws Exception
	{
		try
		{
			TariffClassHelper trffClassObj = new TariffClassHelper();
			trffClassObj.createTariffClass( path, workBookName, sheetName, "TariffClass OutAdvanceRating", 1 );
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 6, description = "TariffClass creation for In advance rating", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void createTariffClassInAdvanceRating() throws Exception
	{
		try
		{
			TariffClassHelper trffClassObj = new TariffClassHelper();
			trffClassObj.createTariffClass( path, workBookName, sheetName, "TariffClass InAdvanceRating", 1 );
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}
}
