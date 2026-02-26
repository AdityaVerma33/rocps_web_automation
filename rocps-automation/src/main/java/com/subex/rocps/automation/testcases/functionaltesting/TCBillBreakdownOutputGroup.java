package com.subex.rocps.automation.testcases.functionaltesting;

import com.subex.automation.helpers.util.FailureHelper;
import com.subex.rocps.automation.helpers.application.bills.BillBreakdownOutputGroup;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;

public class TCBillBreakdownOutputGroup extends PSAcceptanceTest
{

	String path = System.getProperty( "user.dir" ) + "\\src\\main\\resources\\";
	String workBookName = "FunctionalTestCases.xlsx";
	String sheetName = "BillOutputGrp";

	@org.testng.annotations.Test( priority = 1, description = "Bill BreakDown Output Group creation", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void billBreakdownOutputGrpCreation() throws Exception
	{
		try
		{
			BillBreakdownOutputGroup billOutputGrpObj = new BillBreakdownOutputGroup( path, workBookName, sheetName, "BillOutputGrp", 1 );
			billOutputGrpObj.billOutputGrpCreation();
		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 2, description = "Bill BreakDown Output Group - DateRange creation", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void billBreakdownOutputGrpDateRangeCreation() throws Exception
	{
		try
		{
			BillBreakdownOutputGroup billOutputGrpObj = new BillBreakdownOutputGroup( path, workBookName, sheetName, "BillOutputGrp DateRange", 1 );
			billOutputGrpObj.billOutputGrpCreation();
		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 3, description = "Bill BreakDownOutput Group search screen column validation" )
	public void billBreakDownOutputColVal() throws Exception
	{
		try
		{
			BillBreakdownOutputGroup outputGrpValObj = new BillBreakdownOutputGroup( path, workBookName, sheetName, "BillOutputGrpSearchScreencolVal", 1 );
			outputGrpValObj.searchScreenColumnsValidation();
		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 4, description = "Bill BreakDown Output Group creation With Rating component", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void billBreakdownOutputGrpCreationWithRating() throws Exception
	{
		try
		{
			BillBreakdownOutputGroup billOutputGrpObj = new BillBreakdownOutputGroup( path, workBookName, sheetName, "BillOutputGrp_rating", 1 );
			billOutputGrpObj.billOutputGrpCreation();
		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 5, description = "Bill BreakDown Output Group - DateRange creation With Rating component", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void billBreakdownOutputGrpDateRangeCreationWithRating() throws Exception
	{
		try
		{
			BillBreakdownOutputGroup billOutputGrpObj = new BillBreakdownOutputGroup( path, workBookName, sheetName, "BillOutputGrp DateRange_rating", 1 );
			billOutputGrpObj.billOutputGrpCreation();
		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 6, description = "Bill BreakDown Output MultiGroup  creation With Rating component", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void billBreakdownOutputMultiGrpCreationWithRating() throws Exception
	{
		try
		{
			BillBreakdownOutputGroup billOutputGrpObj = new BillBreakdownOutputGroup( path, workBookName, sheetName, "BillOutputMultiGrp_rating", 1 );
			billOutputGrpObj.billOutputGrpCreation();
		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 7, description = "Bill BreakDownOutput Group deletion" )
	public void billBreakDownOutputGrpDelete() throws Exception
	{
		try
		{
			BillBreakdownOutputGroup billOutputGrpDelObj = new BillBreakdownOutputGroup( path, workBookName, sheetName, "BillOutputGrpDelete", 1 );
			billOutputGrpDelObj.billbreakdownOutputGrpDelete();
		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 8, description = "Bill BreakDownOutput Group un delete" )
	public void billBreakDownOutputGrpUnDelete() throws Exception
	{
		try
		{
			BillBreakdownOutputGroup billOutputGrpUnDelObj = new BillBreakdownOutputGroup( path, workBookName, sheetName, "BillOutputGrpUnDelete", 1 );
			billOutputGrpUnDelObj.billbreakdownOutputGrpUnDelete();
		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 9, description = "Bill BreakDown Output Group creation With Rating component" )
	public void editbillBreakdownOutputGrpWithRating() throws Exception
	{
		try
		{
			BillBreakdownOutputGroup billOutputGrpObj = new BillBreakdownOutputGroup( path, workBookName, sheetName, "EditBillOutputGrp DateRange_rating", 1 );
			billOutputGrpObj.editBillOutputGrpCreation();
		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}

}
