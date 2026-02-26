package com.subex.rocps.automation.testcases.functionaltesting;

import com.subex.automation.helpers.util.FailureHelper;
import com.subex.rocps.automation.helpers.application.bills.BillBreakdownInputGroup;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;

public class TCBillBreakdownInputGroup extends PSAcceptanceTest
{

	String path = System.getProperty( "user.dir" ) + "\\src\\main\\resources\\";
	String workBookName = "FunctionalTestCases.xlsx";
	String sheetName = "BillInputGrp";

	@org.testng.annotations.Test( priority = 1, description = "Bill BreakDownInput Group creation", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void billBreakdownInputGrpCreation() throws Exception
	{
		try
		{
			BillBreakdownInputGroup billInputObj = new BillBreakdownInputGroup( path, workBookName, sheetName, "BillInputGrp", 1 );
			billInputObj.billInputGrpCreation();
		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 2, description = "Bill BreakDownInput Group - DateRange creation", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void billBreakdownInputGrpDateRangeCreation() throws Exception
	{
		try
		{
			BillBreakdownInputGroup billInputObj = new BillBreakdownInputGroup( path, workBookName, sheetName, "BillInputGrp DateRange", 1 );
			billInputObj.billInputGrpCreation();
		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 3, description = "Bill BreakDownInput Group search screen column validation" )
	public void billBreakDownInputColVal() throws Exception
	{
		try
		{
			BillBreakdownInputGroup billconfigColValObj = new BillBreakdownInputGroup( path, workBookName, sheetName, "BillInputGrpSearchScreencolVal", 1 );
			billconfigColValObj.searchScreenColumnsValidation();
		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 4, description = "Bill BreakDownInput Group creation with Rating Component", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void billBreakdownInputGrpCreationWithRating() throws Exception
	{
		try
		{
			BillBreakdownInputGroup billInputObj = new BillBreakdownInputGroup( path, workBookName, sheetName, "BillInputGrp_rating", 1 );
			billInputObj.billInputGrpCreation();
		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 5, description = "Bill BreakDownInput Group - DateRange creation with Rating Component", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void billBreakdownInputGrpDateRangeCreationWithRating() throws Exception
	{
		try
		{
			BillBreakdownInputGroup billInputObj = new BillBreakdownInputGroup( path, workBookName, sheetName, "BillInputGrp DateRange_rating", 1 );
			billInputObj.billInputGrpCreation();
		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 6, description = "Bill BreakDownInput Multi Group -  creation with Rating Component", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void billBreakdownInputMultiGrpCreationWithRating() throws Exception
	{
		try
		{
			BillBreakdownInputGroup billInputObj = new BillBreakdownInputGroup( path, workBookName, sheetName, "BillInputMultiGrp_rating", 1 );
			billInputObj.billInputGrpCreation();
		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 7, description = "Bill BreakDownInput Group deletion" )
	public void billBreakDownInputDelete() throws Exception
	{
		try
		{
			BillBreakdownInputGroup billInputDelObj = new BillBreakdownInputGroup( path, workBookName, sheetName, "BillInputGrpDelete", 1 );
			billInputDelObj.billbreakdownInputDelete();
		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 8, description = "Bill BreakDownInput Group un delete" )
	public void billBreakDownInputUnDelete() throws Exception
	{
		try
		{
			BillBreakdownInputGroup billconfigUnDelObj = new BillBreakdownInputGroup( path, workBookName, sheetName, "BillInputGrpUnDelete", 1 );
			billconfigUnDelObj.billbreakdownInputUnDelete();
		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 9, description = "Edit Bill BreakDownInput Multi Group -  creation with Rating Component", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void editbillBreakdownInputMultiGrpWithRating() throws Exception
	{
		try
		{
			BillBreakdownInputGroup billInputObj = new BillBreakdownInputGroup( path, workBookName, sheetName, "EditBillInputGrp DateRange_rating", 1 );
			billInputObj.editBillInputGrpCreation();
		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}

}
