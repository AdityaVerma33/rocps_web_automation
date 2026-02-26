package com.subex.rocps.automation.testcases.functionaltesting;

import com.subex.automation.helpers.util.FailureHelper;
import com.subex.rocps.automation.helpers.application.networkConfiguraiton.Route;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;

public class TCRoute extends PSAcceptanceTest
{

	String path = System.getProperty( "user.dir" ) + "\\src\\main\\resources\\";
	String workBookName = "FunctionalTestCases.xlsx";
	String sheetName = "Route";

	@org.testng.annotations.Test( priority = 1, description = "Route-Transit creation", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void routeTransitCreation() throws Exception
	{
		try
		{
			Route routeObj = new Route( path, workBookName, sheetName, "Route Transit" );
			routeObj.routeCreation();
		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 2, description = "OutRoute FreePhone creation", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void routeOutCreation() throws Exception
	{
		try
		{
			Route routeObj = new Route( path, workBookName, sheetName, "OutRoute FreePhone" );
			routeObj.routeCreation();
		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 3, description = "Route - In creation", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void routeInCreation() throws Exception
	{
		try
		{
			Route routeObj = new Route( path, workBookName, sheetName, "Routes sameRGdiffMatchString Ano" );
			routeObj.routeCreation();
		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 4, description = "Route HomeCarrier creation", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void routeHCCreation() throws Exception
	{
		try
		{
			Route routeObj = new Route( path, workBookName, sheetName, "Route HomeCarrier" );
			routeObj.routeCreation();
		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 5, description = "Routes Outgoing creation", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void routeOutgoingCreation() throws Exception
	{
		try
		{
			Route routeObj = new Route( path, workBookName, sheetName, "Routes Outgoing" );
			routeObj.routeCreation();
		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 6, description = "Routes Outgoing creation", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void routeOutAdvanceRatingCreation() throws Exception
	{
		try
		{
			Route routeObj = new Route( path, workBookName, sheetName, "Routes advanceRating" );
			routeObj.routeCreation();
		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 7, description = "Routes Outgoing creation", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void routeInAdvanceRatingCreation() throws Exception
	{
		try
		{
			Route routeObj = new Route( path, workBookName, sheetName, "Routes InAdvance" );
			routeObj.routeCreation();
		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 8, description = "Route deletion" )
	public void routeDelete() throws Exception
	{
		try
		{
			Route routeDelObj = new Route( path, workBookName, sheetName, "RouteDelete" );
			routeDelObj.routeDelete();
		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 9, description = "Route un delete" )
	public void routeUnDelete() throws Exception
	{
		try
		{
			Route routeUnDelObj = new Route( path, workBookName, sheetName, "RouteUnDelete" );
			routeUnDelObj.routeUnDelete();
		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 10, description = "Route Column Validation" )
	public void routecolVal() throws Exception
	{
		try
		{
			Route routeColValObj = new Route( path, workBookName, sheetName, "RouteSearchScreencolVal" );
			routeColValObj.searchScreenColumnsValidation();

		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 11, description = "Route - Element association", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void routeNonMatch() throws Exception
	{
		try
		{
			Route routeColObj = new Route( path, workBookName, sheetName, "Routes ElementAssociation" );
			routeColObj.routeCreation();

		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 12, description = "Route - Rule String set", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void routeRuleStringSet() throws Exception
	{
		try
		{
			Route routeColObj = new Route( path, workBookName, sheetName, "RoutesRuleStringSet" );
			routeColObj.routeCreation();

		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 13, description = "Route - edit Rule String set" )
	public void editroute() throws Exception
	{
		try
		{
			Route routeColObj = new Route( path, workBookName, sheetName, "EditRoutesRuleStringSet" );
			routeColObj.editRoute();

		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}
}
