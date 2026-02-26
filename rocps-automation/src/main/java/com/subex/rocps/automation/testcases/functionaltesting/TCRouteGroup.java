package com.subex.rocps.automation.testcases.functionaltesting;

import com.subex.automation.helpers.util.FailureHelper;
import com.subex.rocps.automation.helpers.application.networkConfiguraiton.RouteGroup;

import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;

public class TCRouteGroup extends PSAcceptanceTest
{

	String path = System.getProperty( "user.dir" ) + "\\src\\main\\resources\\";
	String workBookName = "FunctionalTestCases.xlsx";
	String sheetName = "RouteGrp";

	@org.testng.annotations.Test( priority = 1, description = "RouteGrp creation", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void routeGrpCreation() throws Exception
	{
		try
		{
			RouteGroup routeGrpObj = new RouteGroup( path, workBookName, sheetName, "RouteGroup Ano" );
			routeGrpObj.routeGrpCreation();
		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 2, description = "RouteGroup -Transit creation", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void routeGrpTransitCreation() throws Exception
	{
		try
		{
			RouteGroup routeGrpObj = new RouteGroup( path, workBookName, sheetName, "RouteGroup Transit" );
			routeGrpObj.routeGrpCreation();
		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 3, description = "RouteGroup diffOperator creation", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void routeGrpdiffOperatorCreation() throws Exception
	{
		try
		{
			RouteGroup routeGrpObj = new RouteGroup( path, workBookName, sheetName, "RouteGroup diffOperator FreePhone" );
			routeGrpObj.routeGrpCreation();
		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 4, description = "RouteGrp - Home carrier creation", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void routeGrpHCCreation() throws Exception
	{
		try
		{
			RouteGroup routeGrpObj = new RouteGroup( path, workBookName, sheetName, "RouteGroup HomeCarrier" );
			routeGrpObj.routeGrpCreation();
		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 5, description = "RouteGroup Bno creation", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void routeGrpBNoCreation() throws Exception
	{
		try
		{
			RouteGroup routeGrpObj = new RouteGroup( path, workBookName, sheetName, "RouteGroup Bno" );
			routeGrpObj.routeGrpCreation();
		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 6, description = "RouteGroup OutAdvanceRating creation", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void routeGrpOutAdvanceRatingCreation() throws Exception
	{
		try
		{
			RouteGroup routeGrpObj = new RouteGroup( path, workBookName, sheetName, "RouteGroup OutAdvance" );
			routeGrpObj.routeGrpCreation();
		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 7, description = "RouteGroup InAdvanceRating creation", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void routeGrpInAdvanceCreation() throws Exception
	{
		try
		{
			RouteGroup routeGrpObj = new RouteGroup( path, workBookName, sheetName, "RouteGroup InAdvance" );
			routeGrpObj.routeGrpCreation();
		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 8, description = "RouteGrp deletion" )
	public void routeGrpDelete() throws Exception
	{
		try
		{
			RouteGroup routeGrpDelObj = new RouteGroup( path, workBookName, sheetName, "RouteGroupDelete" );
			routeGrpDelObj.routeGroupDelete();
		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 9, description = "RouteGrp un delete" )
	public void routeGrpUnDelete() throws Exception
	{
		try
		{
			RouteGroup routeGrpUnDelObj = new RouteGroup( path, workBookName, sheetName, "RouteGroupUnDelete" );
			routeGrpUnDelObj.routeGroupUnDelete();
		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 10, description = "RouteGrp search screen col validation" )
	public void routeGrpColVal() throws Exception
	{
		try
		{
			RouteGroup routeGrpcolValObj = new RouteGroup( path, workBookName, sheetName, "RoutGrpSearchScreencolVal" );
			routeGrpcolValObj.searchScreenColumnsValidation();
		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 11, description = "RouteGrp for non matching elements", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void routeGrpnonMatching() throws Exception
	{
		try
		{
			RouteGroup routeGrpObj = new RouteGroup( path, workBookName, sheetName, "RouteGroup NonMatching" );
			routeGrpObj.routeGrpCreation();
		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}

	/*@org.testng.annotations.Test( priority = 12, description = "RouteGrp to view route" )
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
	}*/

}
