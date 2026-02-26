package com.subex.rocps.automation.testcases.functionaltesting;

import org.testng.annotations.Test;

import com.subex.automation.helpers.util.FailureHelper;
import com.subex.rocps.automation.helpers.application.bcrManagement.Overrides;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;

public class TCOverrides extends PSAcceptanceTest
{

	String path = System.getProperty( "user.dir" ) + "\\src\\main\\resources\\";

	String workBookName = "FunctionalTestCasesRO.xlsx";
	String sheetName = "Overrides";

	@Test( priority = 1, enabled = true, description = "'Overrides '  verify the column Headers Of Search Screen" )
	public void verifyTheColHdrs() throws Exception
	{
		try
		{
			Overrides overrides = new Overrides( path, workBookName, sheetName, "OverridesScreencolVal" );
			overrides.overridesColumsValidation();

		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}
}
