package com.subex.rocps.automation.testcases.functionaltesting;

import com.subex.automation.helpers.util.FailureHelper;
import com.subex.rocps.automation.helpers.application.partnerConfiguration.Account;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;

public class TCAccount extends PSAcceptanceTest
{

	String path = System.getProperty( "user.dir" ) + "\\src\\main\\resources\\";
	String workBookName = "FunctionalTestCases.xlsx";
	String sheetName = "Account";

	@org.testng.annotations.Test( priority = 1, description = "account customer creation", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void accountCreation() throws Exception
	{
		try
		{
			Account accobj = new Account( path, workBookName, sheetName, "AccountCustomer", 1 );
			accobj.accountCreation();
		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 2, description = "account vendor creation", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void accountVendorCreation() throws Exception
	{
		try
		{
			Account accobj = new Account( path, workBookName, sheetName, "AccountVendor", 1 );
			accobj.accountCreation();
		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 3, description = "account bank creation", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void accountbankCreation() throws Exception
	{
		try
		{
			Account accobj = new Account( path, workBookName, sheetName, "AccountBank", 1 );
			accobj.accountCreation();
		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 4, description = "parent account creation", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void parentAccountCreation() throws Exception
	{
		try
		{
			Account accobj = new Account( path, workBookName, sheetName, "ParentAccount", 1 );
			accobj.accountCreation();
		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 5, description = "account with multiple address creation", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void accountMultiAddressCreation() throws Exception
	{
		try
		{
			Account accobj = new Account( path, workBookName, sheetName, "AccountMultiAddress", 1 );
			accobj.accountCreation();
		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 6, description = "account with multiple contact creation", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void accountMultiContactCreation() throws Exception
	{
		try
		{
			Account accobj = new Account( path, workBookName, sheetName, "AccountMultiContact", 1 );
			accobj.accountCreation();
		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 7, description = "account deletion" )
	public void accountDelete() throws Exception
	{
		try
		{
			Account accobj = new Account( path, workBookName, sheetName, "AccountDelete", 1 );
			accobj.accountDelete();
		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 8, description = "account un delete" )
	public void accountUnDelete() throws Exception
	{
		try
		{
			Account accobj = new Account( path, workBookName, sheetName, "AccountUnDelete", 1 );
			accobj.accountUnDelete();
		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 9, description = "account - Search Screen column Validation" )
	public void accountColVal() throws Exception
	{
		try
		{
			Account accobj = new Account( path, workBookName, sheetName, "AccountSearchScreencolVal", 1 );
			accobj.searchScreenColumnsValidation();
		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 10, description = "Edit account customer creation" )
	public void editAccount() throws Exception
	{
		try
		{
			Account accobj = new Account( path, workBookName, sheetName, "EditAccountMultiContact", 1 );
			accobj.editAccountCreation();

		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}

}
