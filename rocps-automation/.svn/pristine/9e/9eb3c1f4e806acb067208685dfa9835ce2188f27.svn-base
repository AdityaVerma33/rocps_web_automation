package com.subex.rocps.automation.testcases.functionaltesting;

import org.testng.annotations.Test;

import com.subex.automation.helpers.util.FailureHelper;
import com.subex.rocps.automation.helpers.application.referenceTable.ChargeType;
import com.subex.rocps.automation.helpers.application.referenceTable.TariffType;
import com.subex.rocps.automation.helpers.application.referenceTable.TariffTypeToChargeTypeMap;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;

public class TCTariffTypeToChargeTypeMap extends PSAcceptanceTest
{
	String path = System.getProperty( "user.dir" ) + "\\src\\main\\resources\\";

	String workBookName = "FunctionalTestCases.xlsx";
	String sheetName = "TariffTypeToChargeTypeMap";

	@Test( priority = 1, enabled = true, description = "Tariff Type,Charge Type creation ", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void prerequisiteTariffTypeChargeType() throws Exception
	{
		try
		{
			TariffType tariffType = new TariffType( path, workBookName, "TariffType", "TariffTypeCreation" );
			tariffType.tariffTypeCreation();
			ChargeType chargeType = new ChargeType( path, workBookName, "ChargeType", "TestChargeTypeCreation" );
			chargeType.chargeTypeCreation();
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

	@Test( priority = 2, enabled = true, description = "'Tariff Type To Charge Type Map'  verify the column Headers Of Search Screen" )
	public void verifyTheColHdrs() throws Exception
	{
		try
		{
			TariffTypeToChargeTypeMap tarTypeToChargeTypeMap = new TariffTypeToChargeTypeMap( path, workBookName, sheetName, "TariffTypeToChargeTypeMapScreencolVal" );
			tarTypeToChargeTypeMap.tariffTypeToChargeTypeMapColumsValidation();

		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

	@Test( priority = 3, enabled = true, description = "'Tariff Type To Charge Type Map'  creation" )
	public void tariffTypeToChargeTypeMapCreation() throws Exception
	{
		try
		{
			TariffTypeToChargeTypeMap tarTypeToChargeTypeMap = new TariffTypeToChargeTypeMap( path, workBookName, sheetName, "TariffTypeToChargeTypeMapCreation" );
			tarTypeToChargeTypeMap.tariffTypeToChargeTypeMapCreation();

		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

	@Test( priority = 4, enabled = true, description = "'Tariff Type To Charge Type Map'  edit" )
	public void tariffTypeToChargeTypeMapEdit() throws Exception
	{
		try
		{
			TariffTypeToChargeTypeMap tarTypeToChargeTypeMap = new TariffTypeToChargeTypeMap( path, workBookName, sheetName, "TariffTypeToChargeTypeMapCreation" );
			tarTypeToChargeTypeMap.tariffTypeToChargeTypeMapEdit();

		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}
}
