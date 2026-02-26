package com.subex.rocps.automation.testcases.functionaltesting;

import org.testng.annotations.Test;

import com.subex.rocps.automation.helpers.application.aggregation.AggregationResult;
import com.subex.rocps.automation.helpers.application.bills.Bills;
import com.subex.rocps.automation.helpers.application.partnerConfiguration.EventUsgRequestHelper;
import com.subex.rocps.automation.helpers.application.settlements.SettlementsHelper;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;

import com.subex.automation.helpers.util.FailureHelper;

public class TCSettlementsBill extends PSAcceptanceTest
{

	String path = System.getProperty( "user.dir" ) + "\\src\\main\\resources\\";

	String workBookName = "FunctionalTestCases.xlsx";
	String sheetName = "SettlementsBill";

	@org.testng.annotations.Test( priority = 1, enabled = true, description = "Generation of Bill for BillServerPrerequistes2" )
	public void generateBills() throws Exception

	{
		try
		{

			Bills billObjec = new Bills( path, workBookName, sheetName, "HotBillCreate_InvoiceBill1" );
			billObjec.billAction();
			Bills billObjec1 = new Bills( path, workBookName, sheetName, "HotBillCreate_StatementBill2" );
			billObjec1.billAction();

		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}

	@Test( priority = 2, enabled = true, description = "Settlements creation with draft bill", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void settlementsCreationWithDraftBill() throws Exception
	{
		try
		{
			SettlementsHelper settlementsHelper = new SettlementsHelper( path, workBookName, sheetName, "SettlementCreationWithDraftBill" );
			settlementsHelper.createSettlment();
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

	@Test( priority = 3, enabled = true, description = "Settlements  Validate the error message in 'Approved' action with draft Bill", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void settlementApprovedMessageValidate() throws Exception
	{
		try
		{
			SettlementsHelper settlementsHelper = new SettlementsHelper( path, workBookName, sheetName, "SettlementApproveMesageValidate" );
			settlementsHelper.settlementApproveActValidateMsz();
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

	@Test( priority = 4, enabled = true, description = "Settlements changed Status to 'Cancelled'" )
	public void settlementsChangeStatusCancel() throws Exception
	{
		try
		{
			SettlementsHelper settlementsHelper = new SettlementsHelper( path, workBookName, sheetName, "SettlementChangeStatusCancel" );
			settlementsHelper.settlementCancelAction();
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 5, enabled = true, description = "Generation of Bill for BillServerPrerequistes2" )
	public void changeBillStatus() throws Exception

	{
		try
		{

			Bills billObjec = new Bills( path, workBookName, sheetName, "HotBill_InvoiceBillChangeStatus" );
			billObjec.billAction();
			Bills billObjec1 = new Bills( path, workBookName, sheetName, "HotBill_StatementBillChangeStatus" );
			billObjec1.billAction();

		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}

	@Test( priority = 6, enabled = true, description = "Settlements creation with Accepted bill", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void settlementsCreationWithAcceptedBill() throws Exception
	{
		try
		{
			SettlementsHelper settlementsHelper = new SettlementsHelper( path, workBookName, sheetName, "SettlementCreationWithAcceptedBill" );
			settlementsHelper.createSettlment();
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

	@Test( priority = 7, enabled = true, description = "Settlements changed Status to 'Approved'", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void settlementsChangeStatusApprove() throws Exception
	{
		try
		{
			SettlementsHelper settlementsHelper = new SettlementsHelper( path, workBookName, sheetName, "SettlementChangeStatusApprove" );
			settlementsHelper.settlementApproveAction();
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

}
