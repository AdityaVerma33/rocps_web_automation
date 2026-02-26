package com.subex.rocps.automation.testcases.functionaltesting;

import org.testng.annotations.Test;

import com.subex.automation.helpers.util.FailureHelper;
import com.subex.rocps.automation.helpers.application.payments.ViewBalance;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;

public class TCViewBalance extends PSAcceptanceTest
{

	String path = System.getProperty( "user.dir" ) + "\\src\\main\\resources\\";

	String workBookName = "FunctionalTestCases.xlsx";
	String sheetName = "ViewBalance";

	@Test( priority = 1, enabled = true, description = "' View Balance'  verify the column Headers Of Search Screen" )
	public void verifyTheColHdrs() throws Exception
	{
		try
		{
			ViewBalance viewBalance = new ViewBalance( path, workBookName, sheetName, "ViewBalanceScreenColumns" );
			viewBalance.viewBalanceColumsValidation();

		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}
}
