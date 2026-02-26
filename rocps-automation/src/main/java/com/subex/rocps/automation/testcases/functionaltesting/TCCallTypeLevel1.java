package com.subex.rocps.automation.testcases.functionaltesting;

import org.testng.annotations.Test;

import com.subex.rocps.automation.helpers.application.referenceTable.CallTypeLevel1;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;

import com.subex.automation.helpers.util.FailureHelper;

public class TCCallTypeLevel1 extends PSAcceptanceTest
{
	String path = System.getProperty( "user.dir" ) + "\\src\\main\\resources\\";

	String workBookName = "FunctionalTestCases.xlsx";
	String sheetName = "CallTypeLevel1";

	@Test( priority = 1, enabled = true, description = "'Call Type Level 1'  verify the column Headers Of Search Screen" )
	public void verifyTheColHdrs() throws Exception
	{
		try
		{
			CallTypeLevel1 callTypeLevel1 = new CallTypeLevel1( path, workBookName, sheetName, "CallTypeLevel1ScreencolVal" );
			callTypeLevel1.callTypeLevel1ColumsValidation();
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

	@Test( priority = 2, enabled = true, description = "'Call Type Level 1'  creation" )
	public void callTypelevel1Creation() throws Exception
	{
		try
		{
			CallTypeLevel1 callTypeLevel1 = new CallTypeLevel1( path, workBookName, sheetName, "TestCallTypeLevel1Creation" );
			callTypeLevel1.callTypeLevel1Creation();
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

	@Test( priority = 3, enabled = true, description = "'Call Type Level 1'  Edit" )
	public void callTypelevel1Edit() throws Exception
	{
		try
		{
			CallTypeLevel1 callTypeLevel1 = new CallTypeLevel1( path, workBookName, sheetName, "TestCallTypeLevel1Edit" );
			callTypeLevel1.callTypeLevel1Edit();
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}
}
