package com.subex.rocps.automation.testcases.functionaltesting;

import org.testng.annotations.Test;

import com.subex.rocps.automation.helpers.application.referenceTable.CallTypeGroup;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;

import com.subex.automation.helpers.util.FailureHelper;

public class TCCallTypeGroup extends PSAcceptanceTest
{
	String path = System.getProperty( "user.dir" ) + "\\src\\main\\resources\\";

	String workBookName = "FunctionalTestCases.xlsx";
	String sheetName = "CallTypeGroup";

	@Test( priority = 1, enabled = true, description = "'Call Type Group'  verify the column Headers Of Search Screen" )
	public void verifyTheColHdrs() throws Exception
	{
		try
		{
			CallTypeGroup callTypeGroup = new CallTypeGroup( path, workBookName, sheetName, "CallTypeGroupScreencolVal" );
			callTypeGroup.callTypeGrpColumsValidation();

		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

	@Test( priority = 2, enabled = true, description = "'Call Type Group'  creation" )
	public void callTypeGroupCreation() throws Exception
	{
		try
		{
			CallTypeGroup callTypeGroup = new CallTypeGroup( path, workBookName, sheetName, "TestCallTypeGroupCreation" );
			callTypeGroup.callTypeGrpCreation();

		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

	@Test( priority = 3, enabled = true, description = "'Call Type Group'  edit" ,retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void callTypeGroupEdit() throws Exception
	{
		try
		{
			CallTypeGroup callTypeGroup = new CallTypeGroup( path, workBookName, sheetName, "TestCallTypeGroupEdit" );
			callTypeGroup.callTypeGrpEdit();

		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

}
