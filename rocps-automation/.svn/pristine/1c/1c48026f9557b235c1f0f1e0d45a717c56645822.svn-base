package com.subex.rocps.automation.testcases.functionaltesting;

import org.testng.annotations.Test;

import com.subex.rocps.automation.helpers.application.partnerConfiguration.Account;
import com.subex.rocps.automation.helpers.application.partnerConfiguration.Agent;
import com.subex.rocps.automation.helpers.application.partnerConfiguration.BillProfile;
import com.subex.rocps.automation.helpers.application.referenceTable.RoamingDiscounting;
import com.subex.rocps.automation.helpers.application.roaming.RoamingConfiguration;
import com.subex.rocps.automation.helpers.application.tariffs.PSTariffHelper;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;

import com.subex.automation.helpers.application.screens.BandHelper;
import com.subex.automation.helpers.application.screens.ElementCreateHelper;
import com.subex.automation.helpers.application.screens.TariffClassHelper;
import com.subex.automation.helpers.application.screens.TariffHelper;
import com.subex.automation.helpers.util.FailureHelper;

public class TCRoamingConfiguration extends PSAcceptanceTest
{

	String path = System.getProperty( "user.dir" ) + "\\src\\main\\resources\\";

	String workBookName = "FunctionalTestCases.xlsx";
	String sheetName = "RoamingConfig";

	@org.testng.annotations.Test( priority = 1, enabled = true, description = " Prerequisiste for bill profile creation", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void BillProfileClientPrerequistes() throws Exception

	{
		try
		{
			Agent agobj = new Agent( path, workBookName, "Agent", "Agent", 1 );
			agobj.agentCreation();

			Account accobj = new Account( path, workBookName, "Account", "Account_Roaming", 1 );
			accobj.accountCreation();

			BillProfile billObj = new BillProfile( path, workBookName, "BillProfile", "BillProfile_Roaming", 1 );
			billObj.billProfileCreation();
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 2, enabled = true, description = " Prerequisiste for roaming configuration creation of tariff, Discounting", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void roamingConfiguPrerequistes2() throws Exception

	{
		try
		{
			ElementCreateHelper eleObj = new ElementCreateHelper();
			eleObj.createElement( path, workBookName, "Elements", "Elements AdvanceRating", 1 );

			BandHelper bandObj = new BandHelper();
			bandObj.createBand( path, workBookName, "Bands", "Bands AdvanceRating", 1 );

			TariffClassHelper trffClassObj = new TariffClassHelper();
			trffClassObj.createTariffClass( path, workBookName, "TariffClass", "TariffClass OutAdvanceRating", 1 );

			PSTariffHelper pstrffObj1 = new PSTariffHelper();
			pstrffObj1.createTariff( path, workBookName, "Tariff", "Tariff Outadvance", 1 );
			pstrffObj1.createFastEntry( path, workBookName, "Tariff", "Tariff OutAdvanceFastEntry", 1 );

			RoamingDiscounting roamingDiscounting = new RoamingDiscounting( path, workBookName, "RoamingDiscounting", "TestRoamingDiscountingCreation" );
			roamingDiscounting.roamingDiscountingCreation();
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

	@Test( priority = 3, enabled = true, description = "'Roaming  Configuration'  verify the column Headers Of Search Screen" )
	public void verifyTheColHdrs() throws Exception
	{
		try
		{
			RoamingConfiguration roamingConfiguration = new RoamingConfiguration( path, workBookName, sheetName, "RoamingConfigScreencolVal" );
			roamingConfiguration.roamingConfigColumsValidation();
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

	@Test( priority = 4, enabled = true, description = "'Roaming  Configuration'  creation Tap In configuration type", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void roamingConfigCreationTapIn() throws Exception
	{
		try
		{
			RoamingConfiguration roamingConfiguration = new RoamingConfiguration( path, workBookName, sheetName, "TestTapInRoamingConfigCreation" );
			roamingConfiguration.roamingConfigCreation();
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

	@Test( priority = 5, enabled = true, description = "'Roaming  Configuration'  creation Tap Out configuration type", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void roamingConfigCreationTapOut() throws Exception
	{
		try
		{
			RoamingConfiguration roamingConfiguration = new RoamingConfiguration( path, workBookName, sheetName, "TestTapOutRoamingConfigCreation" );
			roamingConfiguration.roamingConfigCreation();
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

	@Test( priority = 6, enabled = true, description = "'Roaming  Configuration'  creation Rap In configuration type", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void roamingConfigCreationRapIn() throws Exception
	{
		try
		{
			RoamingConfiguration roamingConfiguration = new RoamingConfiguration( path, workBookName, sheetName, "TestRapInRoamingConfigCreation" );
			roamingConfiguration.roamingConfigCreation();
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

	@Test( priority = 7, enabled = true, description = "'Roaming  Configuration'  creation Rap Out configuration type", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void roamingConfigCreationRapOut() throws Exception
	{
		try
		{
			RoamingConfiguration roamingConfiguration = new RoamingConfiguration( path, workBookName, sheetName, "TestRapOutRoamingConfigCreation" );
			roamingConfiguration.roamingConfigCreation();
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

	@Test( priority = 8, enabled = true, description = "'Roaming  Configuration'  creation NRTRDE Out configuration type", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void roamingConfigCreationNrtrdeOut() throws Exception
	{
		try
		{
			RoamingConfiguration roamingConfiguration = new RoamingConfiguration( path, workBookName, sheetName, "TestNrtrdeOutRoamingConfigCreation" );
			roamingConfiguration.roamingConfigCreation();
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

	@Test( priority = 9, enabled = true, description = "'Roaming  Configuration'  creation HUR Out configuration type", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void roamingConfigCreationHurOut() throws Exception
	{
		try
		{
			RoamingConfiguration roamingConfiguration = new RoamingConfiguration( path, workBookName, sheetName, "TestHUROutRoamingConfigCreation" );
			roamingConfiguration.roamingConfigCreation();
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

	@Test( priority = 10, enabled = true, description = "'Roaming  Configuration'  creation Tap In configuration type for IOT", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void roamingConfigCreationTapInForIOT() throws Exception
	{
		try
		{
			RoamingConfiguration roamingConfiguration = new RoamingConfiguration( path, workBookName, sheetName, "TestTapInRoamingConfigCreationIOT" );
			roamingConfiguration.roamingConfigCreation();
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

	@Test( priority = 11, enabled = true, description = "'Roaming  Configuration'  creation Tap In configuration type for camelCase", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void roamingConfigCreationTapInForCamelCase() throws Exception
	{
		try
		{
			RoamingConfiguration roamingConfiguration = new RoamingConfiguration( path, workBookName, sheetName, "TestTapInRoamingConfigCreation_CamelCase" );
			roamingConfiguration.roamingConfigCreation();
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

}
