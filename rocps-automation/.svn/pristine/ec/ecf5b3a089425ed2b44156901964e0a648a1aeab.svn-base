package com.subex.rocps.automation.testcases.functionaltesting;

import org.testng.annotations.Test;

import com.subex.automation.helpers.util.FailureHelper;
import com.subex.rocps.automation.helpers.application.bcrManagement.BCRRequest;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;

public class TCBCRRequest extends PSAcceptanceTest
{

	String path = System.getProperty( "user.dir" ) + "\\src\\main\\resources\\";

	String workBookName = "FunctionalTestCasesRO.xlsx";
	String sheetName = "BCRRequest";

	@Test( priority = 1, enabled = true, description = "' BCR Request'  verify the column Headers Of Search Screen" )
	public void verifyTheColHdrs() throws Exception
	{
		try
		{
			BCRRequest bcrRequest = new BCRRequest( path, workBookName, sheetName, "BCRRequestScreencolVal" );
			bcrRequest.bcrRequestColumsValidation();

		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}
}
