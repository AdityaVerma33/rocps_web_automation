package com.subex.rocps.automation.testcases.functionaltesting;

import com.subex.rocps.automation.helpers.application.bills.Bills;
import com.subex.rocps.automation.helpers.application.prepayments.PrePayments;
import com.subex.rocps.automation.helpers.application.prepayments.PrepaidBalanceSearch;
import com.subex.rocps.automation.helpers.application.system.PSTaskSearchHelper;
import com.subex.rocps.automation.helpers.application.system.TaskSchedule;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;

import com.subex.automation.helpers.application.screens.RecurringTaskHelper;
import com.subex.automation.helpers.application.screens.TaskControllerHelper;
import com.subex.automation.helpers.application.screens.TaskSearchHelper;
import com.subex.automation.helpers.util.FailureHelper;

public class TCPrepaymentServerCases extends PSAcceptanceTest
{
	String path = System.getProperty( "user.dir" ) + "\\src\\main\\resources\\";
	String workBookName = "FunctionalTestCases.xlsx";
	String sheetName = "PrePaymentsServerCases";


	@org.testng.annotations.Test( priority = 1, description = "task Controller capabilities" ,retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class)
	public void taskControllerCapabilities() throws Exception
	{
		try
		{

			TaskControllerHelper taskObj = new TaskControllerHelper();
			taskObj.setTaskControllerCapability( path, workBookName, sheetName, "TCCapability", 1 );

		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}
	@org.testng.annotations.Test( priority = 2, description = "prepayment creation", groups =
	{ "Server" } )
	public void prepaymentCreationUnBilled() throws Exception
	{
		try
		{
			PrePayments prePayObj3 = new PrePayments( path, workBookName, sheetName, "prepayments", 1 );
			prePayObj3.prePaymentsCreation();
			PrePayments prePayStatusObj = new PrePayments( path, workBookName, sheetName, "PrePayments changeStatus", 1 );
			prePayStatusObj.prePaymentsChangeStatus();
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

	

	@org.testng.annotations.Test( priority = 3, description = "server Side cases-Recurring task, taskStatus, prepaidBalance", groups =
	{ "Server" } )
	public void recurringTaskAndTaskStatuscheckUnBilled() throws Exception
	{
		try
		{

			RecurringTaskHelper triggerObj = new RecurringTaskHelper();
			triggerObj.createRecurringTask( path, workBookName, sheetName, "RecurringTask", 1 );
			
			PSTaskSearchHelper tskObj = new PSTaskSearchHelper();
			TaskSchedule taskObj1 = new TaskSchedule();
			taskObj1.scheduleRecurringTask( path, workBookName, sheetName, "RecurringTask UnBilled", 1 );

			tskObj.psVerifyTaskStatus( path, workBookName, sheetName, "PrepaymentsTaskStatus UnBilled", 1 );

		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 4, description = "Pre-Payments reverse", groups =
	{ "Server" } )
	public void prepaidBalanceVerificationUnbilled() throws Exception
	{
		try
		{
			PrepaidBalanceSearch prePayObj = new PrepaidBalanceSearch( path, workBookName, sheetName, "PrePaidBalance Unbilled", 1 );
			prePayObj.prepaidBalanceResults();

		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 5, description = "bill creation", groups =
	{ "Server" } )
	public void billGeneration() throws Exception
	{
		try
		{
			Bills billObj = new Bills( path, workBookName, sheetName, "BillGeneration" );
			billObj.billAction();
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 6, description = "prepayment creation", groups =
	{ "Server" } )
	public void prepaidBalanceVerificationBilled() throws Exception
	{
		try
		{
			PrepaidBalanceSearch prePayObj = new PrepaidBalanceSearch( path, workBookName, sheetName, "PrePaidBalancebilled", 1 );
			prePayObj.prepaidBalanceResults();
			
			
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 7, description = "Pre-Payments reverse", groups =
	{ "Client" } )
	public void prePaymentsReverse() throws Exception
	{
		try
		{
			PrePayments prePayObj = new PrePayments( path, workBookName, sheetName, "PrePayments reverse", 1 );
			prePayObj.prePaymentsReverse();
		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 8, description = "prepayment creation", groups =
	{ "Server" } )
	public void prepaymentCreationbilled() throws Exception
	{
		try
		{
			PrePayments prePayObj3 = new PrePayments( path, workBookName, sheetName, "PrePayments creation", 1 );
			prePayObj3.prePaymentsCreation();
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 9, description = "server Side cases-Recurring task, taskStatus, prepaidBalance", groups =
	{ "Server" } )
	public void recurringTaskAndTaskStatuscheck() throws Exception
	{
		try
		{

			PSTaskSearchHelper tskObj = new PSTaskSearchHelper();
			TaskSchedule taskObj1 = new TaskSchedule();
			taskObj1.scheduleRecurringTask( path, workBookName, sheetName, "RecurringTask UnBilled", 1 );

			tskObj.psVerifyTaskStatus( path, workBookName, sheetName, "PrepaymentsTaskStatus", 1 );

		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 10, description = "Pre-Payments reverse", groups =
	{ "Client" } )
	public void prepaidBalanceVerification() throws Exception
	{
		try
		{
			PrepaidBalanceSearch prePayObj = new PrepaidBalanceSearch( path, workBookName, sheetName, "PrePaidBalance", 1 );
			prePayObj.prepaidBalanceResults();

			PrepaidBalanceSearch prePayObj1 = new PrepaidBalanceSearch( path, workBookName, sheetName, "PrePaidBalance totalcolumns", 1 );
			prePayObj1.searchScreenColumnsValidation();
		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}
}
