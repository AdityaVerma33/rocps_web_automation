package com.subex.rocps.automation.testcases.functionaltesting;

import com.subex.automation.helpers.util.FailureHelper;
import com.subex.rocps.automation.helpers.application.bills.BillingCycle;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;

public class TCBillingCycle extends PSAcceptanceTest
{
	String path = System.getProperty( "user.dir" ) + "\\src\\main\\resources\\";
	String workBookName = "FunctionalTestCases.xlsx";
	String sheetName = "BillingCycle";

	@org.testng.annotations.Test( priority = 1, description = "Billing Cycle creation", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void billingCycleCreation() throws Exception
	{
		try
		{
			BillingCycle billCycleObj = new BillingCycle( path, workBookName, sheetName, "BillingCycle", 1 );
			billCycleObj.billingCycleCreation();
		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 2, description = "Billing Cycle- All Fields creation", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void billingCycleAllfieldsCreation() throws Exception
	{
		try
		{
			BillingCycle billCycleObj1 = new BillingCycle( path, workBookName, sheetName, "BillingCycle AllFields", 1 );
			billCycleObj1.billingCycleCreation();
		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 3, description = "Billing Cycle deletion", dependsOnMethods = "billingCycleCreation" )
	public void billingCycleDelete() throws Exception
	{
		try
		{
			BillingCycle billCycleDelobj = new BillingCycle( path, workBookName, sheetName, "BillingCycleDelete", 1 );
			billCycleDelobj.billingCycleDelete();
		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 4, description = "Billing Cycle un delete", dependsOnMethods = "billingCycleDelete" )
	public void billingCycleUnDelete() throws Exception
	{
		try
		{
			BillingCycle billCycleunDelobj = new BillingCycle( path, workBookName, sheetName, "BillingCycleUnDelete", 1 );
			billCycleunDelobj.billingCycleUnDelete();
		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 5, description = "Billing Cycle search screen col validation" )
	public void billingCycleColVal() throws Exception
	{
		try
		{
			BillingCycle billCycleColValobj = new BillingCycle( path, workBookName, sheetName, "billCycleSearchScreencolVal", 1 );
			billCycleColValobj.searchScreenColumnsValidation();
		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 6, description = "Billing Cycle - Weekly creation", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void billingCycleWeeklyCreation() throws Exception
	{
		try
		{
			BillingCycle billCycleObj = new BillingCycle( path, workBookName, sheetName, "BillingCycle-Weekly", 1 );
			billCycleObj.billingCycleCreation();
		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 7, description = "Billing Cycle - Closed Bill Period creation", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void billingCycleClosedBilPeriodCreation() throws Exception
	{
		try
		{
			BillingCycle billCycleObj = new BillingCycle( path, workBookName, sheetName, "BillingCycle -closedBillPeriod", 1 );
			billCycleObj.billingCycleCreation();
		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 8, description = "Billing Cycle - Weekly creation", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void billingCycleMonthlyCloseCreation() throws Exception
	{
		try
		{
			BillingCycle billCycleObj = new BillingCycle( path, workBookName, sheetName, "BillingCycle MonthlyClosePeriod", 1 );
			billCycleObj.billingCycleCreation();
		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 9, description = "Billing Cycle - Closed Bill Period creation", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void billingCycleMonthlyAlignCreation() throws Exception
	{
		try
		{
			BillingCycle billCycleObj = new BillingCycle( path, workBookName, sheetName, "BillingCycle MonthlyAlign", 1 );
			billCycleObj.billingCycleCreation();
		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 10, description = "Billing Cycle - edit" )
	public void billingCycleEdit() throws Exception
	{
		try
		{
			BillingCycle billCycleObj = new BillingCycle( path, workBookName, sheetName, "BillingCycleEdit", 1 );
			billCycleObj.billingCycleEdit();
		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}
}
