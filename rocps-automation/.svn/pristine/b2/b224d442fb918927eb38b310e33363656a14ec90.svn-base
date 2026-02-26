package com.subex.rocps.automation.testcases.functionaltesting;

import com.subex.automation.helpers.util.FailureHelper;
import com.subex.rocps.automation.helpers.application.bills.BillBreakDownInput;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;

public class TCBillBreakdownInput extends PSAcceptanceTest
{

	String path = System.getProperty( "user.dir" ) + "\\src\\main\\resources\\";
	String workBookName = "FunctionalTestCases.xlsx";
	String sheetName = "BillInput";

	@org.testng.annotations.Test( priority = 1, description = "Bill BreakDownInput creation", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void billBreakdownInputCreation() throws Exception
	{
		try
		{
			BillBreakDownInput billInputObj = new BillBreakDownInput( path, workBookName, sheetName, "BillInput", 1 );
			billInputObj.billInputCreation();
		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 2, description = "Bill BreakDownInput - DateRange creation", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void billBreakdownInputDateRangeCreation() throws Exception
	{
		try
		{
			BillBreakDownInput billInputObj = new BillBreakDownInput( path, workBookName, sheetName, "BillInput DateRange", 1 );
			billInputObj.billInputCreation();
		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 3, description = "Bill BreakDownInput Search creen Col Validation" )
	public void billBreakDownInputColVal() throws Exception
	{
		try
		{
			BillBreakDownInput billInputColValObj = new BillBreakDownInput( path, workBookName, sheetName, "BillInputSearchScreencolVal", 1 );
			billInputColValObj.searchScreenColumnsValidation();
		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 4, description = "Bill BreakDownInput creation with Rating Component", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void billBreakdownInputWithRatingCreation() throws Exception
	{
		try
		{
			BillBreakDownInput billInputObj = new BillBreakDownInput( path, workBookName, sheetName, "BillInput_rating", 1 );
			billInputObj.billInputCreation();
		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 5, description = "Bill BreakDownInput - DateRange creation with Rating Component", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void billBreakdownInputWithRatingDateRangeCreation() throws Exception
	{
		try
		{
			BillBreakDownInput billInputObj = new BillBreakDownInput( path, workBookName, sheetName, "BillInput DateRange_Rating", 1 );
			billInputObj.billInputCreation();
		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 6, description = "Bill BreakDownInput deletion" )
	public void billBreakDownInputDelete() throws Exception
	{
		try
		{
			BillBreakDownInput billInputDelObj = new BillBreakDownInput( path, workBookName, sheetName, "BillInputDelete", 1 );
			billInputDelObj.billbreakdownInputDelete();
		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 7, description = "Bill BreakDownInput un delete" )
	public void billBreakDownInputUnDelete() throws Exception
	{
		try
		{
			BillBreakDownInput billInputUnDelObj = new BillBreakDownInput( path, workBookName, sheetName, "BillInputUnDelete", 1 );
			billInputUnDelObj.billbreakdownInputUnDelete();
		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 8, description = "Edit Bill BreakDownInput creation with Rating Component" )
	public void editbillBreakdownInputWithRatingCreation() throws Exception
	{
		try
		{
			BillBreakDownInput billInputObj = new BillBreakDownInput( path, workBookName, sheetName, "EditBillInput_rating", 1 );
			billInputObj.editBillInput();
		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}
}
