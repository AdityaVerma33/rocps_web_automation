package com.subex.rocps.automation.testcases.functionaltesting;

import com.subex.automation.helpers.util.FailureHelper;
import com.subex.rocps.automation.helpers.application.bills.BillBreakdownConfiguration;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;

public class TCBillBreakdownConfiguration extends PSAcceptanceTest
{

	String path = System.getProperty( "user.dir" ) + "\\src\\main\\resources\\";
	String workBookName = "FunctionalTestCases.xlsx";
	String sheetName = "BillBreakdownConfiguration";

	@org.testng.annotations.Test( priority = 1, description = "bill Breakdownconfig creation", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void billBreakdownconfigCreation() throws Exception
	{
		try
		{
			BillBreakdownConfiguration billconfigObj = new BillBreakdownConfiguration( path, workBookName, sheetName, "BillBreakdownConfig", 1 );
			billconfigObj.billConfigCreation();
			billconfigObj.billbreakdownChangeStatus();
		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 2, description = "bill Breakdownconfig creation", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void billBreakdownconfigDateRangeCreation() throws Exception
	{
		try
		{
			BillBreakdownConfiguration billconfigObj = new BillBreakdownConfiguration( path, workBookName, sheetName, "BillBreakdownConfig date Range", 1 );
			billconfigObj.billConfigCreation();
			billconfigObj.billbreakdownChangeStatus();
		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 3, description = "bill Breakdownconfig for search screen col validation" )
	public void billBreakdownconfigColVal() throws Exception
	{
		try
		{
			BillBreakdownConfiguration billconfigObj = new BillBreakdownConfiguration( path, workBookName, sheetName, "billConfigSearchScreencolVal", 1 );
			billconfigObj.searchScreenColumnsValidation();

		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 4, description = "bill Breakdown config with rating component", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void billBreakdownconfigRatingComp() throws Exception
	{
		try
		{
			BillBreakdownConfiguration billconfigObj = new BillBreakdownConfiguration( path, workBookName, sheetName, "BillBreakdownConfig_rating comp", 1 );
			billconfigObj.billConfigCreation();

		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 5, description = "bill Breakdown config with rating component with date range", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void billBreakdownconfigRatingCompWithDate() throws Exception
	{
		try
		{
			BillBreakdownConfiguration billconfigObj = new BillBreakdownConfiguration( path, workBookName, sheetName, "BillBreakdownConfigdateRange_RatingComp", 1 );
			billconfigObj.billConfigCreation();
			billconfigObj.billbreakdownChangeStatus();

		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 6, description = "bill Breakdown config with rating component for Fios", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void billBreakdownconfigFios() throws Exception
	{
		try
		{
			BillBreakdownConfiguration billconfigObj = new BillBreakdownConfiguration( path, workBookName, sheetName, "BillBreakdownConfig_Fios", 1 );
			billconfigObj.billConfigCreation();

		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 7, description = "bill Breakdownconfig deletion" )
	public void billBreakdownconfigDelete() throws Exception
	{
		try
		{
			BillBreakdownConfiguration billconfigDelObj = new BillBreakdownConfiguration( path, workBookName, sheetName, "BillBreakdownConfig_Fios", 1 );
			billconfigDelObj.billbreakdownConfigDelete();
		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 8, description = "bill Breakdownconfig un delete" )
	public void billBreakdownconfigUnDelete() throws Exception
	{
		try
		{
			BillBreakdownConfiguration billconfigUnDelObj = new BillBreakdownConfiguration( path, workBookName, sheetName, "BillBreakdownConfig_Fios", 1 );
			billconfigUnDelObj.billbreakdownConfigUnDelete();
		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 9, enabled = true, description = "bill Breakdown config edit" )
	public void billBreakdownedit() throws Exception
	{
		try
		{
			BillBreakdownConfiguration billconfigObj = new BillBreakdownConfiguration( path, workBookName, sheetName, "BillBreakdownConfig_edit", 1 );
			billconfigObj.billConfigEdit();

		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 10, enabled = true, description = "bill Breakdown configRatingComp change status", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void billBreakdownconfigRatingCompChangeStatus() throws Exception
	{
		try
		{
			BillBreakdownConfiguration billconfigObj = new BillBreakdownConfiguration( path, workBookName, sheetName, "BillBreakdownConfig_rating comp", 1 );
			billconfigObj.billbreakdownChangeStatus();

		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}
}
