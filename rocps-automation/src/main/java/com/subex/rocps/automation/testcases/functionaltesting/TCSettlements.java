package com.subex.rocps.automation.testcases.functionaltesting;

import org.testng.annotations.Test;

import com.subex.rocps.automation.helpers.application.partnerConfiguration.EventUsgRequestHelper;
import com.subex.rocps.automation.helpers.application.settlements.SettlementsHelper;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;

import com.subex.automation.helpers.util.FailureHelper;

public class TCSettlements extends PSAcceptanceTest
{

	String path = System.getProperty( "user.dir" ) + "\\src\\main\\resources\\";

	String workBookName = "FunctionalTestCases.xlsx";
	String sheetName = "Settlements";

	@Test( priority = 1, enabled = true, description = "Settlements verify the column Headers Of Search Screen" )
	public void verifyTheColHdrs() throws Exception
	{
		try
		{
			SettlementsHelper settlementsHelper = new SettlementsHelper( path, workBookName, sheetName, "SettlementsScreencolVal" );
			settlementsHelper.settlementsColumsValidation();
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

	@Test( priority = 2, enabled = true, description = "Settlements verify the results Of Search Screen" )
	public void settlementsSrchScreenValidation() throws Exception
	{
		try
		{
			SettlementsHelper settlementsHelper = new SettlementsHelper( path, workBookName, sheetName, "SettlementSearchResults" );
			settlementsHelper.settlementsSearchResultValidation();
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

	@Test( priority = 3, enabled = true, description = "Settlements creation with empty data Error message validation" )
	public void settlementsCreationWithEmptyData() throws Exception
	{
		try
		{
			SettlementsHelper settlementsHelper = new SettlementsHelper( path, workBookName, sheetName, "SettlementCreationWithEmptyData" );
			settlementsHelper.settlementValidation();
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

	@Test( priority = 4, enabled = true, description = "Settlements creation without bill/credit note/carrier invoice" )
	public void settlementsCreationWithoutAddOption() throws Exception
	{
		try
		{
			SettlementsHelper settlementsHelper = new SettlementsHelper( path, workBookName, sheetName, "SettlementCreationWithoutAddoption" );
			settlementsHelper.createSettlment();
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

	@Test( priority = 5, enabled = true, description = "Settlements creation with bill/credit note/carrier invoice", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void settlementsCreationWithAddOption() throws Exception
	{
		try
		{
			SettlementsHelper settlementsHelper = new SettlementsHelper( path, workBookName, sheetName, "SettlementCreationWithAddoption" );
			settlementsHelper.createSettlment();
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

	@Test( priority = 6, enabled = true, description = "Settlements changed Status to 'Cancelled'" )
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

	@Test( priority = 7, enabled = true, description = "Settlements  action   'Create Test Settlement'" )
	public void createTestSettlementAction() throws Exception
	{
		try
		{
			SettlementsHelper settlementsHelper = new SettlementsHelper( path, workBookName, sheetName, "CreateTestSettlementAction" );
			settlementsHelper.createTestSettlementAction();
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

	@Test( priority = 8, enabled = true, description = "Settlements changed Status to 'Approved'", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
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

	@Test( priority = 9, enabled = true, description = "Settlements changed Status to 'Reversion'" )
	public void settlementsChangeStatusReversioned() throws Exception
	{
		try
		{

			SettlementsHelper settlementsHelper = new SettlementsHelper( path, workBookName, sheetName, "SettlementChangeStatusReversioned" );
			settlementsHelper.settlementReversionAction();
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

	@Test( priority = 10, enabled = true, description = "Settlements changed Status to 'Settle'" )
	public void settlementsChangeStatusSettled() throws Exception
	{
		try
		{
			SettlementsHelper settlementsHelperOb = new SettlementsHelper( path, workBookName, sheetName, "SettlementChangeStatusApprove" );
			settlementsHelperOb.settlementApproveAction();
			SettlementsHelper settlementsHelper = new SettlementsHelper( path, workBookName, sheetName, "SettlementChangeStatusSettled" );
			settlementsHelper.settlementSettleAction();
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

	@Test( priority = 11, enabled = true, description = "Settlements  action   'Jump to Settlement history'" )
	public void settlementJumpToHistory() throws Exception
	{
		try
		{
			SettlementsHelper settlementsHelper = new SettlementsHelper( path, workBookName, sheetName, "SettlementJumpToHistory" );
			settlementsHelper.settlementJumpHistoryAction();
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

	@Test( priority = 12, enabled = true, description = "Settlements  action   'Settlement Currency Breakdown'" )
	public void settlementCurrencyBreakDown() throws Exception
	{
		try
		{
			SettlementsHelper settlementsHelper = new SettlementsHelper( path, workBookName, sheetName, "SettlementViewCurrencyBreakdown" );
			settlementsHelper.settlementCurrencyBreakdownAction();
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

	@Test( priority = 13, enabled = true, description = "Settlements  action edit" )
	public void settlementEdit() throws Exception
	{
		try
		{
			SettlementsHelper settlementsHelper = new SettlementsHelper( path, workBookName, sheetName, "SettlementEdit" );
			settlementsHelper.editSettlement();
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

}
