package com.subex.rocps.automation.testcases.functionaltesting;

import org.testng.annotations.Test;

import com.subex.automation.helpers.util.FailureHelper;
import com.subex.rocps.automation.helpers.application.arms.ConsolidatedBCRPlan;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;

public class TCConsolidatedBCRPlan extends PSAcceptanceTest
{

	String path = System.getProperty( "user.dir" ) + "\\src\\main\\resources\\";

	String workBookName = "FunctionalTestCasesRO.xlsx";
	String sheetName = "ConsolidatedBCRPlan";

	@Test( priority = 1, enabled = true, description = "'Consolidated BCR Plan '  verify the column Headers Of Search Screen" )
	public void verifyTheColHdrs() throws Exception
	{
		try
		{
			ConsolidatedBCRPlan consolidatedBCRPlan = new ConsolidatedBCRPlan( path, workBookName, sheetName, "ConsolidatedBCRPlanScreencolVal" );
			consolidatedBCRPlan.consolidatedBCRPlanColumsValidation();

		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}
}
