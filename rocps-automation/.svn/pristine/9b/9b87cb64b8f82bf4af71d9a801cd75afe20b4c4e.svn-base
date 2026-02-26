package com.subex.rocps.automation.testcases.functionaltesting;

import org.testng.annotations.Test;

import com.subex.automation.helpers.util.FailureHelper;

import com.subex.rocps.automation.helpers.application.partnerConfiguration.Account;
import com.subex.rocps.automation.helpers.application.partnerConfiguration.Agent;
import com.subex.rocps.automation.helpers.application.partnerConfiguration.BillProfile;
import com.subex.rocps.automation.helpers.application.partnerConfiguration.EventUsageHelper;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;

public class TCEventUsage extends PSAcceptanceTest
{

	String path = System.getProperty( "user.dir" ) + "\\src\\main\\resources\\";

	String workBookName = "FunctionalTestCases.xlsx";
	String sheetName = "EventUsage";

	@org.testng.annotations.Test( priority = 1, enabled = true, description = "Event usage prerequisite", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void EventUsagePrerequistes() throws Exception

	{
		try
		{
			Agent agobj = new Agent( path, workBookName, "Agent", "Agent", 1 );
			agobj.agentCreation();

			Account accobj = new Account( path, workBookName, "Account", "Account_Xdr", 1 );
			accobj.accountCreation();

			BillProfile billObj = new BillProfile( path, workBookName, "BillProfile", "BillProfile_Xdr", 1 );
			billObj.billProfileCreation();

		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}

	@Test( priority = 2, enabled = true, description = "Event Usage  verify the column Headers Of Search Screen", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void verifyTheColHdrs() throws Exception
	{
		try
		{
			EventUsageHelper eventUsageHelper = new EventUsageHelper( path, workBookName, sheetName, "EventUsgSearchScreencolVal" );
			eventUsageHelper.verifyColmnHeaderOfeventUsg();
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

	@Test( priority = 3, enabled = true, description = "Event Usage  Search with daily trend Screen" )
	public void eventUsgSearchWithDailyTrend() throws Exception
	{
		try
		{
			EventUsageHelper eventUsageHelper = new EventUsageHelper( path, workBookName, sheetName, "EventUsgSearchWithDaily" );
			eventUsageHelper.validateEventUsgSearchResult();
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

	@Test( priority = 4, enabled = true, description = "Event Usage  Search with hourly trend Screen" )
	public void eventUsgSearchWithHourlyTrend() throws Exception
	{
		try
		{
			EventUsageHelper eventUsageHelper = new EventUsageHelper( path, workBookName, sheetName, "EventUsgSearchWithHourly" );
			eventUsageHelper.validateEventUsgSearchResult();
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

	@Test( priority = 5, enabled = true, description = "Event Usage  Search with ViewTotals action" )
	public void eventUsgSearchWithViewTotalAction() throws Exception
	{
		try
		{
			EventUsageHelper eventUsageHelper = new EventUsageHelper( path, workBookName, sheetName, "EventUsgSearchWithViewTotalAction" );
			eventUsageHelper.validateViewTotalActionResult();
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

}
