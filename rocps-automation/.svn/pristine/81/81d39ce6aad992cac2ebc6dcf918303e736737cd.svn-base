package com.subex.rocps.automation.testcases.functionaltesting;

import com.subex.automation.helpers.util.FailureHelper;
import com.subex.rocps.automation.helpers.application.referenceTable.Bank;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;

public class TCBank extends PSAcceptanceTest
{

	String path = System.getProperty( "user.dir" ) + "\\src\\main\\resources\\";
	String workBookName = "FunctionalTestCases.xlsx";
	String sheetName = "Bank";

	@org.testng.annotations.Test( priority = 1, description = "Bank creation" ,retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class)
	public void bankCreation() throws Exception
	{
		try
		{
			Bank bankobj = new Bank( path, workBookName, sheetName, "Bank", 1 );
			bankobj.bankCreation();
		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 2, description = "Bank-Multiple creation" ,retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class)
	public void bankMultipleCreation() throws Exception
	{
		try
		{
			Bank bankobj = new Bank( path, workBookName, sheetName, "BankMultiple", 1 );
			bankobj.bankCreation();
		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 3, description = "Bank deletion" )
	public void bankDelete() throws Exception
	{
		try
		{
			Bank bankdelObj = new Bank( path, workBookName, sheetName, "BankDelete", 1 );
			bankdelObj.bankDelete();
		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 4, description = "Bank un delete" )
	public void bankUnDelete() throws Exception
	{
		try
		{
			Bank bankundelObj = new Bank( path, workBookName, sheetName, "BankUnDelete", 1 );
			bankundelObj.bankUnDelete();
		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 5, description = "Bank screen screen column validation" )
	public void bankColVal() throws Exception
	{
		try
		{
			Bank bankColValObj = new Bank( path, workBookName, sheetName, "BankSearchScreencolVal", 1 );
			bankColValObj.searchScreenColumnsValidation();
		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}

}
