package com.subex.rocps.automation.testcases.functionaltesting;

import com.subex.rocps.automation.helpers.application.referenceTable.ElementSet;
import com.subex.rocps.automation.helpers.application.tariffs.AutoRateSheetConfig;
import com.subex.rocps.automation.helpers.application.tariffs.PSTariffHelper;
import com.subex.rocps.automation.helpers.application.tariffs.RateSheetImportRequest;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;
import com.subex.automation.helpers.application.screens.TariffClassHelper;
import com.subex.automation.helpers.util.FailureHelper;

public class TCAutoRateSheetConfig extends PSAcceptanceTest
{
	String path = System.getProperty( "user.dir" ) + "\\src\\main\\resources\\";
	String workBookName = "FunctionalTestCases.xlsx";
	String sheetName = "AutoRateSheetConfig";

	@org.testng.annotations.Test( priority = 1, description = "Tariff creation", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void createTariffTransit() throws Exception
	{
		try
		{

			TariffClassHelper trffClassObj = new TariffClassHelper();
			trffClassObj.createTariffClass( path, workBookName, sheetName, "TariffClass RS", 1 );

			PSTariffHelper pstrffObj = new PSTariffHelper();
			pstrffObj.createTariff( path, workBookName, sheetName, "TariffRS", 1 );

		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 2, description = "AutoRateSheet config", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void autoRateSheetConfig() throws Exception
	{

		try
		{
			AutoRateSheetConfig ratesheetObj = new AutoRateSheetConfig( path, workBookName, sheetName, "AutoRateSheetConfig" );
			ratesheetObj.autoRateSheetConfiguration();
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			;
			throw e;
		}

	}

	@org.testng.annotations.Test( priority = 3, description = "AutoRateSheet config", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void autoRateSheetConfigMultiple() throws Exception
	{

		try
		{
			AutoRateSheetConfig ratesheetObj = new AutoRateSheetConfig( path, workBookName, sheetName, "AutoRateSheetConfig Multiple" );
			ratesheetObj.autoRateSheetConfiguration();
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			;
			throw e;
		}

	}

	@org.testng.annotations.Test( priority = 4, description = "rateSheetImportRequest column validation", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void autoRateSheetImportRequestColVal() throws Exception
	{

		try
		{
			AutoRateSheetConfig ratesheetObj = new AutoRateSheetConfig( path, workBookName, sheetName, "AutoRateSheetConfigColVal" );
			ratesheetObj.searchScreenColumnsValidation();
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			;
			throw e;
		}

	}

	@org.testng.annotations.Test( priority = 5, description = "AutoRateSheet config-Poll and EMail Dir Destination", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void autoRateSheetConfigEmailPollDest() throws Exception
	{

		try
		{
			AutoRateSheetConfig ratesheetObj = new AutoRateSheetConfig( path, workBookName, sheetName, "AutoRateSheetPollAndEmailConfig_dest" );
			ratesheetObj.autoRateSheetConfiguration();
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			;
			throw e;
		}

	}

	@org.testng.annotations.Test( priority = 6, description = "AutoRateSheet config-Poll Dir Destination", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void autoRateSheetConfigPollDest() throws Exception
	{

		try
		{
			AutoRateSheetConfig ratesheetObj = new AutoRateSheetConfig( path, workBookName, sheetName, "AutoRateSheetPollConfig_dest" );
			ratesheetObj.autoRateSheetConfiguration();
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			;
			throw e;
		}

	}

	@org.testng.annotations.Test( priority = 7, description = "AutoRateSheet config", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void autoRateSheetConfigPollOrigin() throws Exception
	{

		try
		{
			AutoRateSheetConfig ratesheetObj = new AutoRateSheetConfig( path, workBookName, sheetName, "AutoRateSheetPollConfig_origin" );
			ratesheetObj.autoRateSheetConfiguration();
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			;
			throw e;
		}

	}

	@org.testng.annotations.Test( priority = 8, description = "AutoRateSheet config", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void autoRateSheetConfigEmailPollOrigin() throws Exception
	{

		try
		{
			AutoRateSheetConfig ratesheetObj = new AutoRateSheetConfig( path, workBookName, sheetName, "AutoRateSheetPollConfig_origin2" );
			ratesheetObj.autoRateSheetConfiguration();
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			;
			throw e;
		}

	}

	@org.testng.annotations.Test( priority = 9, description = "AutoRateSheet config" )
	public void autoRateSheetConfigDelete() throws Exception
	{

		try
		{
			AutoRateSheetConfig ratesheetObj = new AutoRateSheetConfig( path, workBookName, sheetName, "AutoRateSheetConfig Delete" );
			ratesheetObj.autoRatesheetconfigDelete();
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			;
			throw e;
		}

	}

	@org.testng.annotations.Test( priority = 10, description = "AutoRateSheet config" )
	public void autoRateSheetConfigUnDelete() throws Exception
	{

		try
		{
			AutoRateSheetConfig ratesheetObj = new AutoRateSheetConfig( path, workBookName, sheetName, "AutoRateSheetConfig Undelete" );
			ratesheetObj.autoRatesheetconfigUnDelete();
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			;
			throw e;
		}

	}
}
