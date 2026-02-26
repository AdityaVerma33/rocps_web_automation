package com.subex.rocps.automation.testcases.functionaltesting;

import org.testng.annotations.Test;

import com.subex.automation.helpers.util.FailureHelper;
import com.subex.rocps.automation.helpers.application.bcrManagement.BCRPlan;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;

public class TCBCRPlan extends PSAcceptanceTest
{

	String path = System.getProperty( "user.dir" ) + "\\src\\main\\resources\\";

	String workBookName = "FunctionalTestCasesRO.xlsx";
	String sheetName = "BCRPlan";

	@Test( priority = 1, enabled = true, description = "'BCR Plan '  verify the column Headers Of Search Screen" )
	public void verifyTheColHdrs() throws Exception
	{
		try
		{

			BCRPlan bcrPlan = new BCRPlan( path, workBookName, sheetName, "BCRPlanScreencolVal" );
			bcrPlan.bcrPlanColumsValidation();
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}
}
