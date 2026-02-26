package com.subex.rocps.automation.testcases.functionaltesting;

import org.testng.annotations.Test;

import com.subex.rocps.automation.helpers.application.roaming.IMSIManagement;
import com.subex.rocps.automation.helpers.application.roaming.TestSIMManagement;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;

import com.subex.automation.helpers.util.FailureHelper;

public class TCTestSIMManagement extends PSAcceptanceTest
{
	String path = System.getProperty( "user.dir" ) + "\\src\\main\\resources\\";

	String workBookName = "FunctionalTestCases.xlsx";
	String sheetName = "TestSIMManagement";

	@Test( priority = 1, enabled = true, description = "'Test SIM Management'  verify the column Headers Of Search Screen" )
	public void verifyTheColHdrs() throws Exception
	{
		try
		{
			TestSIMManagement testSIMManagement = new TestSIMManagement( path, workBookName, sheetName, "TestSIManagementScreencolVal" );
			testSIMManagement.testSIMManagementColumsValidation();

		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

	@Test( priority = 2, enabled = true, description = "'Test SIM Management' create with  Inbound direction ",retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void createInBoundTestManagement() throws Exception
	{
		try
		{
			TestSIMManagement testSIMManagement = new TestSIMManagement( path, workBookName, sheetName, "TestSIManagCreationInbound" );
			testSIMManagement.testSIMManagementCreation();

		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

	@Test( priority = 3, enabled = true, description = "'Test SIM Management'  create with  Outbound direction",retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void createOutBoundTestManagement() throws Exception
	{
		try
		{
			TestSIMManagement testSIMManagement = new TestSIMManagement( path, workBookName, sheetName, "TestSIManagCreationOutBound" );
			testSIMManagement.testSIMManagementCreation();

		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}
	@Test( priority = 4, enabled = true, description = "'Test SIM Management'  edit" )
	public void editTestManagement() throws Exception
	{
		try
		{
			TestSIMManagement testSIMManagement = new TestSIMManagement( path, workBookName, sheetName, "TestSIManagEdit" );
			testSIMManagement.testSIMManagementEdit();

		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}
}
