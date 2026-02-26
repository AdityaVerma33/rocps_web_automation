package com.subex.rocps.automation.testcases.functionaltesting;

import org.testng.annotations.Test;

import com.subex.automation.helpers.util.FailureHelper;
import com.subex.rocps.automation.helpers.application.arms.RoutePeriorAndPercenRouting;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;

public class TCRoutePeriorPercentRouting extends PSAcceptanceTest
{

	String path = System.getProperty( "user.dir" ) + "\\src\\main\\resources\\";

	String workBookName = "FunctionalTestCasesRO.xlsx";
	String sheetName = "RoutePeriorityPercenRouting";

	@Test( priority = 1, enabled = true, description = "'Routing Periority Percentage Routing '  verify the column Headers Of Search Screen" )
	public void verifyTheColHdrs() throws Exception
	{
		try
		{
			RoutePeriorAndPercenRouting rAndPercenRouting = new RoutePeriorAndPercenRouting( path, workBookName, sheetName, "RoutePeriorityPercenRoutingScreencolVal" );
			rAndPercenRouting.routePriorPercenRoutingColumsValidation();

		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}
}
