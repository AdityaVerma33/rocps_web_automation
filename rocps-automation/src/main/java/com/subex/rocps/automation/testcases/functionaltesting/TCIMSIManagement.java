package com.subex.rocps.automation.testcases.functionaltesting;

import org.testng.annotations.Test;

import com.subex.rocps.automation.helpers.application.roaming.IMSIManagement;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;
import com.subex.automation.helpers.util.FailureHelper;

public class TCIMSIManagement extends PSAcceptanceTest
{

	String path = System.getProperty( "user.dir" ) + "\\src\\main\\resources\\";

	String workBookName = "FunctionalTestCases.xlsx";
	String sheetName = "IMSIManagement";

	@Test( priority = 1, enabled = true, description = "'IMSI Management'  verify the column Headers Of Search Screen" )
	public void verifyTheColHdrs() throws Exception
	{
		try
		{
			IMSIManagement imsiManagement = new IMSIManagement( path, workBookName, sheetName, "IMSIManagementScreencolVal" );
			imsiManagement.imsiManagementColumsValidation();

		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

	@Test( priority = 2, enabled = true, description = "'IMSI Management'  creation with Home type of agreement",retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void imsiManagementCreationHome() throws Exception
	{
		try
		{
			IMSIManagement imsiManagement = new IMSIManagement( path, workBookName, sheetName, "TestIMSIManagementCreationHome" );
			imsiManagement.imsiManagementCreation();

		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

	@Test( priority = 3, enabled = true, description = "'IMSI Management'  creation with Direct type of agreement",retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void imsiManagementCreationDirect() throws Exception
	{
		try
		{
			IMSIManagement imsiManagement = new IMSIManagement( path, workBookName, sheetName, "TestIMSIManagementCreationDirect" );
			imsiManagement.imsiManagementCreation();

		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

	@Test( priority = 4, enabled = true, description = "'IMSI Management'  edit" )
	public void imsiManagementEdit() throws Exception
	{
		try
		{
			IMSIManagement imsiManagement = new IMSIManagement( path, workBookName, sheetName, "TestIMSIManagementEdit" );
			imsiManagement.imsiManagementEdit();

		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}
}
