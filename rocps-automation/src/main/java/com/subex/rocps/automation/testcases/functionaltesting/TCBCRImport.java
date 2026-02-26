package com.subex.rocps.automation.testcases.functionaltesting;

import org.testng.annotations.Test;

import com.subex.automation.helpers.util.FailureHelper;
import com.subex.rocps.automation.helpers.application.arms.BCRImport;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;

public class TCBCRImport extends PSAcceptanceTest
{

	String path = System.getProperty( "user.dir" ) + "\\src\\main\\resources\\";

	String workBookName = "FunctionalTestCasesRO.xlsx";
	String sheetName = "BCRImport";

	@Test( priority = 1, enabled = true, description = "' BCR Import'  verify the column Headers Of Search Screen" )
	public void verifyTheColHdrs() throws Exception
	{
		try
		{
			BCRImport bcrImport = new BCRImport( path, workBookName, sheetName, "BCRImportScreencolVal" );
			bcrImport.bcrImportColumsValidation();

		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}
}
