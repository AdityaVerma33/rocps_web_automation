package com.subex.rocps.automation.testcases.functionaltesting;

import org.testng.annotations.Test;

import com.subex.rocps.automation.helpers.application.bills.BillingGroupCode;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;

import com.subex.automation.helpers.util.FailureHelper;

public class TCBillingGroupCode extends PSAcceptanceTest
{
	String path = System.getProperty( "user.dir" ) + "\\src\\main\\resources\\";

	String workBookName = "FunctionalTestCases.xlsx";
	String sheetName = "BillingGroupCode";

	@Test( priority = 1, enabled = true, description = "Billing Group verify the column Headers Of Search Screen" )
	public void verifyTheColHdrs() throws Exception
	{
		try
		{

			BillingGroupCode billingGroupCode = new BillingGroupCode( path, workBookName, sheetName, "BillingGroupCodeScreencolVal" );
			billingGroupCode.billingGrpCdColumsValidation();
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}

	}

	@Test( priority = 2, enabled = true, description = "Billing Group creation" )
	public void createBillingGroupCode() throws Exception
	{
		try
		{

			BillingGroupCode billingGroupCode = new BillingGroupCode( path, workBookName, sheetName, "BillingGroupCodeCreate" );
			billingGroupCode.createBillingGroupCode();
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}

	}

	@Test( priority = 3, enabled = true, description = "Billing Group Edit" )
	public void editBillingGroupCode() throws Exception
	{
		try
		{

			BillingGroupCode billingGroupCode = new BillingGroupCode( path, workBookName, sheetName, "BillingGroupCodeEdit" );
			billingGroupCode.editBillingGroupCode();
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}

	}

	@Test( priority = 4, enabled = true, description = "Billing Group Code deletion" )
	public void billingGroupCodeDelete() throws Exception
	{
		try
		{
			BillingGroupCode billingGroupCode = new BillingGroupCode( path, workBookName, sheetName, "BillingGroupCodeDelete" );
			billingGroupCode.billingGrpCodeDelete();
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}

	}

	@Test( priority = 5, enabled = true, description = "Billing Group Code undeletion" )
	public void billingGroupCodeUnDelete() throws Exception
	{
		try
		{
			BillingGroupCode billingGroupCode = new BillingGroupCode( path, workBookName, sheetName, "BillingGroupCodeUnDelete" );
			billingGroupCode.billingGrpCodeUnDelete();
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}

	}

}
