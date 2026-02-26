package com.subex.rocps.automation.testcases.functionaltesting;

import com.subex.rocps.automation.helpers.application.genericHelpers.PSGenericHelper;
import com.subex.rocps.automation.helpers.application.partnerConfiguration.BillProfile;
import com.subex.rocps.automation.helpers.application.prepayments.PrePayments;
import com.subex.rocps.automation.helpers.application.prepayments.PrepaidBalanceSearch;
import com.subex.rocps.automation.helpers.application.system.Streams;
import com.subex.rocps.automation.helpers.application.system.TaskSchedule;

import com.subex.automation.helpers.application.screens.TaskSearchHelper;
import com.subex.automation.helpers.util.FailureHelper;

public class TCPrePayments extends PSGenericHelper
{

	String path = System.getProperty( "user.dir" ) + "\\src\\main\\resources\\";
	String workBookName = "FunctionalTestCases.xlsx";
	String sheetName = "PrePayments";

	@org.testng.annotations.Test( priority = 1, description = "bill profile- Prepaid creation",retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void billProfileCreation() throws Exception
	{
		try
		{
			BillProfile billObj = new BillProfile( path, workBookName, sheetName, "BillProfilePrepaid", 1 );
			billObj.billProfileCreation();
		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}
	@org.testng.annotations.Test( priority = 2, description = "Pre-Payments total columns", groups =
	{ "Client" } )
	public void prePaymentsSearchScreenTotalcolumns() throws Exception
	{
		try
		{
			PrePayments prePayObj = new PrePayments( path, workBookName, sheetName, "prepayments totalcolumns", 1 );
			prePayObj.searchScreenColumnsValidation();
		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}
	
	@org.testng.annotations.Test( priority = 3, description = "Pre-Payments creation", groups =
	{ "Client" },retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void prePaymentsCreation() throws Exception
	{
		try
		{
			PrePayments prePayObj = new PrePayments( path, workBookName, sheetName, "prepayments", 1 );
			prePayObj.prePaymentsCreation();
		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}
	
	@org.testng.annotations.Test( priority = 4, description = "edit Pre-Payments creation", groups =
		{ "Client" } )
		public void editPrePaymentsCreation() throws Exception
		{
			try
			{
				PrePayments prePayObj = new PrePayments( path, workBookName, sheetName, "EditPrePayments", 1 );
				prePayObj.editPrePayments();
			}
			catch ( Exception e )
			{
				FailureHelper.reportFailure( e );
				throw e;
			}
		}
	
	@org.testng.annotations.Test( priority = 5, description = "Pre-Payments Delete", groups =
	{ "Client" } )
	public void prePaymentsDelete() throws Exception
	{
		try
		{
			PrePayments prePayObj = new PrePayments( path, workBookName, sheetName, "PrePayments Delete", 1 );
			prePayObj.prePaymentsDelete();
		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}
	
	@org.testng.annotations.Test( priority = 6, description = "Pre-Payments unDelete", groups =
	{ "Client" } )
	public void prePaymentsUnDelete() throws Exception
	{
		try
		{
			PrePayments prePayObj = new PrePayments( path, workBookName, sheetName, "PrePayments UnDelete", 1 );
			prePayObj.prePaymentsUnDelete();
		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}
	
	@org.testng.annotations.Test( priority = 7, description = "Pre-Payments change status", groups =
		{ "Client" } , dependsOnMethods = {"prePaymentsUnDelete"}, retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class)
		public void prePaymentsChangeStatus() throws Exception
		{
			try
			{
				PrePayments prePayObj = new PrePayments( path, workBookName, sheetName, "PrePayments ChangeStatus", 1 );
				prePayObj.prePaymentsChangeStatus();
			}
			catch ( Exception e )
			{
				FailureHelper.reportFailure( e );
				throw e;
			}
		}
	
	

	@org.testng.annotations.Test( priority = 8, description = "Pre-Payments reverse", groups =
		{ "Client" } , dependsOnMethods = {"prePaymentsChangeStatus"})
	
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
	
	@org.testng.annotations.Test( priority = 9, description = "add prepayments stream stages" )
	public void editVoiceStreamPrepayments() throws Exception
	{
	
	try
	{
		Streams streamObj = new Streams();
		streamObj.editStreamConfig( path, workBookName, "ROCPreRequisites2", "VoiceStreamsPrepayments", 1 );
		streamObj.prepaymentsStreamConfig( path, workBookName, "ROCPreRequisites2", testCaseName, 1 );
		streamObj.saveStreamDetail();
	}
	catch ( Exception e )
	{
		FailureHelper.setErrorMessage( e );
		throw e;
	}
	}
	
	
}
