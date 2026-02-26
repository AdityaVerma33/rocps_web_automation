package com.subex.rocps.automation.testcases.functionaltesting;

import org.testng.annotations.Test;

import com.subex.automation.helpers.util.FailureHelper;
import com.subex.rocps.automation.helpers.application.bcrManagement.BCRPlanSelection;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;

public class TCBCRPlanSelection extends PSAcceptanceTest
{

	String path = System.getProperty( "user.dir" ) + "\\src\\main\\resources\\";

	String workBookName = "FunctionalTestCasesRO.xlsx";
	String sheetName = "BCRPlanSelection";

	@Test( priority = 1, enabled = true, description = "' BCR Plan Selection'  verify the column Headers Of Search Screen" )
	public void verifyTheColHdrs() throws Exception
	{
		try
		{
			BCRPlanSelection bcrPlanSelection = new BCRPlanSelection( path, workBookName, sheetName, "BCRPlanSelectionScreencolVal" );
			bcrPlanSelection.bcrPlanSelectionColumsValidation();

		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}
}
