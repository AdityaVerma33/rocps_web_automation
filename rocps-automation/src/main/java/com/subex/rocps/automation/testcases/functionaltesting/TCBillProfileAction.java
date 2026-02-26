package com.subex.rocps.automation.testcases.functionaltesting;

import com.subex.rocps.automation.helpers.application.partnerConfiguration.BillProfile;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;

import com.subex.automation.helpers.util.FailureHelper;

public class TCBillProfileAction extends PSAcceptanceTest
{
	String path = System.getProperty( "user.dir" ) + "\\src\\main\\resources\\";
	String workBookName = "FunctionalTestCases.xlsx";
	String sheetName = "BillProfile";

	@org.testng.annotations.Test( priority = 1, description = "bill profile view aggregation results" )
	public void billProfileViewAggregation() throws Exception
	{
		try
		{
			BillProfile billObj = new BillProfile( path, workBookName, sheetName, "AggregationResult", 1 );
			billObj.navigateToViewAggregationResults();
		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 2, description = "bill profile- View Balance" )
	public void billProfileViewBalance() throws Exception
	{
		try
		{
			BillProfile billObj = new BillProfile( path, workBookName, sheetName, "ViewBalance", 1 );
			billObj.navigateToViewBalanceAction();
		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}

}
