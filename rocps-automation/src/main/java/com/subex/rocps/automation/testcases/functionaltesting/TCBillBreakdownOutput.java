package com.subex.rocps.automation.testcases.functionaltesting;

import com.subex.automation.helpers.util.FailureHelper;
import com.subex.rocps.automation.helpers.application.bills.BillBreakdownOutput;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;

public class TCBillBreakdownOutput extends PSAcceptanceTest
{

	String path = System.getProperty( "user.dir" ) + "\\src\\main\\resources\\";
	String workBookName = "FunctionalTestCases.xlsx";
	String sheetName = "BillOutput";

	@org.testng.annotations.Test( priority = 1, description = "Bill BreakDownOutput creation", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void billBreakdownOutputCreation() throws Exception
	{
		try
		{
			BillBreakdownOutput billOutputObj = new BillBreakdownOutput( path, workBookName, sheetName, "BillBreakdownOutput", 1 );
			billOutputObj.billOutputCreation();
		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 2, description = "Bill BreakDownOutput - Date Range creation", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void billBreakdownOutputDateRangeCreation() throws Exception
	{
		try
		{
			BillBreakdownOutput billOutputObj = new BillBreakdownOutput( path, workBookName, sheetName, "BillBreakdownOutput DateRange", 1 );
			billOutputObj.billOutputCreation();
		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 3, description = "Bill BreakDownOutput search search column validation" )
	public void billBreakDownOutputColVal() throws Exception
	{
		try
		{
			BillBreakdownOutput outputColValObj = new BillBreakdownOutput( path, workBookName, sheetName, "BillOutputSearchScreencolVal", 1 );
			outputColValObj.searchScreenColumnsValidation();
		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 4, description = "Bill BreakDownOutput creation With Rating component", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void billBreakdownOutputCreationWithRating() throws Exception
	{
		try
		{
			BillBreakdownOutput billOutputObj = new BillBreakdownOutput( path, workBookName, sheetName, "BillBreakdownOutput_rating", 1 );
			billOutputObj.billOutputCreation();
		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 5, description = "Bill BreakDownOutput - Date Range creation With Rating component", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void billBreakdownOutputDateRangeCreationWithRating() throws Exception
	{
		try
		{
			BillBreakdownOutput billOutputObj = new BillBreakdownOutput( path, workBookName, sheetName, "BillBreakdownOutput DateRange_rating", 1 );
			billOutputObj.billOutputCreation();
		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 6, description = "Bill BreakDownOutput deletion" )
	public void billBreakDownOutputDelete() throws Exception
	{
		try
		{
			BillBreakdownOutput billOutputDelObj = new BillBreakdownOutput( path, workBookName, sheetName, "BillBreakdownOutputDelete", 1 );
			billOutputDelObj.billbreakdownOutputDelete();
		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 7, description = "Bill BreakDownOutput un delete" )
	public void billBreakDownOutputUnDelete() throws Exception
	{
		try
		{
			BillBreakdownOutput billOutputUnDelObj = new BillBreakdownOutput( path, workBookName, sheetName, "BillBreakdownOutputUnDelete", 1 );
			billOutputUnDelObj.billbreakdownOutputUnDelete();
		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 8, description = "Edit Bill BreakDownOutput - Date Range creation With Rating component" )
	public void editbillBreakdownOutputDateRangeCreationWithRating() throws Exception
	{
		try
		{
			BillBreakdownOutput billOutputObj = new BillBreakdownOutput( path, workBookName, sheetName, "Edit BillBreakdownOutput DateRange_rating", 1 );
			billOutputObj.editBillBreakdownOutput();
		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}
}
