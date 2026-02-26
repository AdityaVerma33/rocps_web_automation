package com.subex.rocps.automation.testcases.functionaltesting;

import com.subex.rocps.automation.helpers.application.tariffs.PSTariffHelper;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;

import com.subex.automation.helpers.application.screens.TariffHelper;
import com.subex.automation.helpers.util.FailureHelper;

public class TCTariff extends PSAcceptanceTest
{
	String path = System.getProperty( "user.dir" ) + "\\src\\main\\resources\\";
	String workBookName = "FunctionalTestCases.xlsx";
	String sheetName = "Tariff";

	@org.testng.annotations.Test( priority = 1, description = "Tariff creation for Transit", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void createTariffTransit() throws Exception
	{
		try
		{
			PSTariffHelper pstrffObj = new PSTariffHelper();
			pstrffObj.createTariff( path, workBookName, sheetName, "Tariff Transit", 1 );
			pstrffObj.createFastEntry( path, workBookName, sheetName, "Tariff Transit FastEntry", 1 );
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 2, description = "Tariff creation for Incoming", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void createTariffncoming() throws Exception
	{
		try
		{
			PSTariffHelper pstrffObj1 = new PSTariffHelper();
			pstrffObj1.createTariff( path, workBookName, sheetName, "Tariff Incoming", 1 );
			pstrffObj1.createFastEntry( path, workBookName, sheetName, "Tariff Incoming FastEntry", 1 );
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 3, description = "Tariff creation for FreePhone", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void createTariffFreePhone() throws Exception
	{
		try
		{
			PSTariffHelper pstrffObj2 = new PSTariffHelper();
			pstrffObj2.createTariff( path, workBookName, sheetName, "Tariff FreePhone", 1 );
			pstrffObj2.createFastEntry( path, workBookName, sheetName, "Tariff FreePhone FastEntry", 1 );
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 4, description = "Tariff creation for Outgoing", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void createTariffOutgoing() throws Exception
	{
		try
		{
			PSTariffHelper pstrffObj2 = new PSTariffHelper();
			pstrffObj2.createTariff( path, workBookName, sheetName, "Tariff Outgoing", 1 );
			pstrffObj2.createFastEntry( path, workBookName, sheetName, "Tariff Outgoing FastEntry", 1 );
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 5, description = "Tariff creation for Outgoing- Advance Rating", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void createTariffOutAdvanceRating() throws Exception
	{
		try
		{
			PSTariffHelper pstrffObj2 = new PSTariffHelper();
			pstrffObj2.createTariff( path, workBookName, sheetName, "Tariff Outadvance", 1 );
			pstrffObj2.createFastEntry( path, workBookName, sheetName, "Tariff OutAdvanceFastEntry", 1 );
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 6, description = "Tariff creation for In Advance Rating", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void createTariffInAdvanceRating() throws Exception
	{
		try
		{
			PSTariffHelper pstrffObj2 = new PSTariffHelper();
			pstrffObj2.createTariff( path, workBookName, sheetName, "Tariff InAdvance", 1 );
			pstrffObj2.createFastEntry( path, workBookName, sheetName, "Tariff InAdvanceFastEntry", 1 );
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}
}
