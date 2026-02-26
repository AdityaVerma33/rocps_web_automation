package com.subex.rocps.automation.testcases.functionaltesting;

import com.subex.rocps.automation.helpers.application.networkConfiguraiton.Route;
import com.subex.rocps.automation.helpers.application.networkConfiguraiton.RouteGroup;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;

import com.subex.automation.helpers.util.FailureHelper;

public class TCRouteGroupAction extends PSAcceptanceTest
{
	String path = System.getProperty( "user.dir" ) + "\\src\\main\\resources\\";
	String workBookName = "FunctionalTestCases.xlsx";
	String sheetName = "RouteGrp";

	
	@org.testng.annotations.Test( priority = 1, description = "Route - Element association", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void routeNonMatch() throws Exception
	{
		try
		{
			Route routeColObj = new Route( path, workBookName, "Route", "Routes ElementAssociation" );
			routeColObj.routeCreation();

		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}
	@org.testng.annotations.Test( priority = 2, description = "RouteGrp to view route" )
	public void routeGrpViewRoute() throws Exception
	{
		try
		{
			RouteGroup routeGrpObj = new RouteGroup( path, workBookName, sheetName, "RouteGroupViewRoute" );
			routeGrpObj.viewRoute();
		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}
}
