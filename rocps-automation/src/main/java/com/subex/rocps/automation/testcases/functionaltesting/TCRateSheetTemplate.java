package com.subex.rocps.automation.testcases.functionaltesting;

import com.subex.rocps.automation.helpers.application.referenceTable.ElementSet;
import com.subex.rocps.automation.helpers.application.system.Streams;
import com.subex.rocps.automation.helpers.application.tariffs.PSTariffHelper;
import com.subex.rocps.automation.helpers.application.tariffs.RateSheetTemplateConfiguration;
import com.subex.rocps.automation.helpers.application.tariffs.RateSheetValidation;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;

import com.subex.automation.helpers.application.screens.TariffClassHelper;
import com.subex.automation.helpers.util.FailureHelper;

public class TCRateSheetTemplate extends PSAcceptanceTest
{

	String path = System.getProperty( "user.dir" ) + "\\src\\main\\resources\\";
	String workBookName = "FunctionalTestCases.xlsx";
	String sheetName = "RateSheet";

	@org.testng.annotations.Test( priority = 1, description = "add ratesheet stream stages", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void editVoiceStream() throws Exception
	{

		try
		{
			Streams streamObj = new Streams();
			streamObj.editStreamConfig( path, workBookName, "Streams", "StreamsEdit", 1 );
			streamObj.rateSheetStreamConfig( path, workBookName, "Streams", testCaseName, 1 );
			streamObj.saveStreamDetail();
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 2, description = "Tariff creation for Transit", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void createTariffTransit() throws Exception
	{
		try
		{
			TariffClassHelper trffClassObj = new TariffClassHelper();
			trffClassObj.createTariffClass( path, workBookName, sheetName, "TariffClass RS", 1 );

			//TariffHelper trffObj = new TariffHelper();
			PSTariffHelper pstrffObj = new PSTariffHelper();
			pstrffObj.createTariff( path, workBookName, sheetName, "TariffRS", 1 );

			ElementSet eleObj = new ElementSet( path, workBookName, "RatesheetServerSideDest", "TariffClassExp ElementSet", 1 );
			eleObj.elementSetCreation();

			TariffClassHelper trffClassObj1 = new TariffClassHelper();
			trffClassObj1.createTariffClass( path, workBookName, "RatesheetServerSideDest", "TariffClassExp RS", 1 );

			PSTariffHelper pstrffObj1 = new PSTariffHelper();
			pstrffObj1.createTariff( path, workBookName, "RatesheetServerSideDest", "TariffExpRS", 1 );

		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 3, description = "RateSheet tempalte config- destination", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void ratesheetTemplateConfigDestination() throws Exception
	{

		try
		{
			RateSheetTemplateConfiguration ratesheetObj = new RateSheetTemplateConfiguration( path, workBookName, sheetName, "T1_Destination" );
			ratesheetObj.destinationConfigRateSheetTemplate();

		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			;
			throw e;
		}

	}

	@org.testng.annotations.Test( priority = 4, description = "RateSheet tempalte config- Origin", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void ratesheetTemplateConfigOrigin() throws Exception
	{

		try
		{
			RateSheetTemplateConfiguration ratesheetObj = new RateSheetTemplateConfiguration( path, workBookName, sheetName, "T2_Origin" );
			ratesheetObj.originConfigRateSheetTemplate();
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );

			throw e;
		}

	}

	@org.testng.annotations.Test( priority = 5, description = "RateSheet tempalte config- destination - All Fields", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void rsTemplateConfigDestAllFields() throws Exception
	{

		try
		{
			RateSheetTemplateConfiguration ratesheetObj = new RateSheetTemplateConfiguration( path, workBookName, sheetName, "T3_AllFields" );
			ratesheetObj.destinationConfigRateSheetTemplate();

		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );

			throw e;
		}

	}

	@org.testng.annotations.Test( priority = 6, description = "RateSheet tempalte config- Origin - All Fields", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void rsTemplateConfigOriginAllFields() throws Exception
	{

		try
		{
			RateSheetTemplateConfiguration ratesheetObj = new RateSheetTemplateConfiguration( path, workBookName, sheetName, "T4_AllFields" );
			ratesheetObj.originConfigRateSheetTemplate();
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );

			throw e;
		}

	}

	@org.testng.annotations.Test( priority = 7, description = "RateSheet tempalte config- search screen column validation", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void rsTemplateColVal() throws Exception
	{

		try
		{
			RateSheetTemplateConfiguration ratesheetObj = new RateSheetTemplateConfiguration( path, workBookName, sheetName, "RSTemplateSearchScreencolVal" );
			ratesheetObj.searchScreenColumnsValidation();
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );

			throw e;
		}

	}

	@org.testng.annotations.Test( priority = 8, description = "RateSheet tempalte config- Expiration Strategy", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void rsTemplateExpirationStrategy() throws Exception
	{

		try
		{
			RateSheetTemplateConfiguration ratesheetObj = new RateSheetTemplateConfiguration( path, workBookName, sheetName, "T1_DestinationTest" );
			ratesheetObj.destinationConfigRateSheetTemplate();

		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			;
			throw e;
		}

	}

	@org.testng.annotations.Test( priority = 9, description = "RateSheet tempalte config- Expiration Strategy Countrywise - Minimum", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void rsTemplateExpirationStrategyMin() throws Exception
	{

		try
		{
			RateSheetTemplateConfiguration ratesheetObj = new RateSheetTemplateConfiguration( path, workBookName, sheetName, "T1_DestinationEXPMin" );
			ratesheetObj.destinationConfigRateSheetTemplate();

		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			;
			throw e;
		}

	}

	@org.testng.annotations.Test( priority = 10, description = "RateSheet tempalte config- Expiration Strategy Countrywise - Maximum", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void rsTemplateExpirationStrategyMax() throws Exception
	{

		try
		{
			RateSheetTemplateConfiguration ratesheetObj = new RateSheetTemplateConfiguration( path, workBookName, sheetName, "T1_DestinationEXPMax" );
			ratesheetObj.destinationConfigRateSheetTemplate();

		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}

	}

}
