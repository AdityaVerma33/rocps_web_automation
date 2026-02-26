package com.subex.rocps.automation.testcases.functionaltesting;

import org.testng.annotations.Test;

import com.subex.automation.helpers.util.FailureHelper;
import com.subex.rocps.automation.helpers.application.referenceTable.ElementSet;
import com.subex.rocps.automation.helpers.application.referenceTable.SystemTariffMapping;
import com.subex.rocps.automation.helpers.application.referenceTable.TariffType;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;

public class TCSystemTariffMapping extends PSAcceptanceTest
{
	String path = System.getProperty( "user.dir" ) + "\\src\\main\\resources\\";

	String workBookName = "FunctionalTestCasesRO.xlsx";
	String sheetName = "SystemTariffMapping";

	@Test( priority = 1, enabled = true, description = "'System Tariff Mapping '  verify the column Headers Of Search Screen" )
	public void verifyTheColHdrs() throws Exception
	{
		try
		{
			SystemTariffMapping systemTariffMapping = new SystemTariffMapping( path, workBookName, sheetName, "SystemTariffMappingScreencolVal" );
			systemTariffMapping.systemTariffMappingColumsValidation();
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}
	
	@Test( priority = 2, enabled = true, description = "'System Tariff Mapping '  pre" )
	public void systemTariffMappingPrerequisite() throws Exception
	{
		try
		{
			TariffType tariffType = new TariffType( path, workBookName, sheetName, "TariffTypeCreation" );
			tariffType.tariffTypeCreation();

			ElementSet eleObj = new ElementSet( path, workBookName, sheetName, "ElementSetCreation", 1 );
			eleObj.elementSetCreation();

		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}
	
	@Test( priority = 3, enabled = true, description = "'System Tariff Mapping '  creation" )
	public void systemTariffMappingCreation() throws Exception
	{
		try
		{
			SystemTariffMapping systemTariffMapping = new SystemTariffMapping( path, workBookName, sheetName, "SystemTariffMappingCreation" );
			systemTariffMapping.systemTariffMappingCreation();
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}
	@Test( priority = 4, enabled = true, description = "'System Tariff Mapping '  edit" )
	public void systemTariffMappingEdit() throws Exception
	{
		try
		{
			SystemTariffMapping systemTariffMapping = new SystemTariffMapping( path, workBookName, sheetName, "SystemTariffMappingCreation" );
			systemTariffMapping.systemTariffMappingEdit();
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}
}
