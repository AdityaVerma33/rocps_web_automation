package com.subex.rocps.automation.testcases.functionaltesting;

import org.testng.annotations.Test;

import com.subex.rocps.automation.helpers.application.referenceTable.CallTypeLevel2;

import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;

import com.subex.automation.helpers.util.FailureHelper;

public class TCCallTypeLevel2 extends PSAcceptanceTest
{
	String path = System.getProperty( "user.dir" ) + "\\src\\main\\resources\\";

	String workBookName = "FunctionalTestCases.xlsx";
	String sheetName = "CallTypeLevel2";

	@Test( priority = 1, enabled = true, description = "'Call Type Level 2'  verify the column Headers Of Search Screen" )
	public void verifyTheColHdrs() throws Exception
	{
		try
		{
			CallTypeLevel2 callTypeLevel2 = new CallTypeLevel2( path, workBookName, sheetName, "CallTypeLevel2ScreencolVal" );
			callTypeLevel2.callTypeLevel2ColumsValidation();

		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

	@Test( priority = 2, enabled = true, description = "'Call Type Level 2'creation" )
	public void callTypeLevel2Creation() throws Exception
	{
		try
		{
			CallTypeLevel2 callTypeLevel2 = new CallTypeLevel2( path, workBookName, sheetName, "TestCallTypeLevel2Creation" );
			callTypeLevel2.callTypeLevel2Creation();

		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

	@Test( priority = 3, enabled = true, description = "'Call Type Level 2'Edit",retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class  )
	public void callTypeLevel2Edit() throws Exception
	{
		try
		{
			CallTypeLevel2 callTypeLevel2 = new CallTypeLevel2( path, workBookName, sheetName, "TestCallTypeLevel2Edit" );
			callTypeLevel2.callTypeLevel2Edit();

		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

}
