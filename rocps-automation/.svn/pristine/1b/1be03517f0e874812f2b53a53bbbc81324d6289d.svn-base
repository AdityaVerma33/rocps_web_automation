package com.subex.rocps.automation.testcases.functionaltesting;

import org.testng.annotations.Test;

import com.subex.automation.helpers.util.FailureHelper;
import com.subex.rocps.automation.helpers.application.referenceTable.TariffType;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;

public class TCTariffType extends PSAcceptanceTest
{

	String path = System.getProperty( "user.dir" ) + "\\src\\main\\resources\\";

	String workBookName = "FunctionalTestCases.xlsx";
	String sheetName = "TariffType";

	@Test( priority = 1, enabled = true, description = "'Tariff Type'  verify the column Headers Of Search Screen" )
	public void verifyTheColHdrs() throws Exception
	{
		try
		{
			TariffType tariffType = new TariffType( path, workBookName, sheetName, "TariffTypeScreencolVal" );
			tariffType.searchScreenColumnsValidation();

		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

	@Test( priority = 2, enabled = true, description = "'Tariff Type'  creation" )
	public void tariffTypeCreation() throws Exception
	{
		try
		{
			TariffType tariffType = new TariffType( path, workBookName, sheetName, "TariffTypeCreation" );
			tariffType.tariffTypeCreation();

		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}
}
