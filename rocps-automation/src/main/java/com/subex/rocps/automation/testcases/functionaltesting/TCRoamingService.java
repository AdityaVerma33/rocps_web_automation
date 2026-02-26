package com.subex.rocps.automation.testcases.functionaltesting;

import org.testng.annotations.Test;

import com.subex.rocps.automation.helpers.application.referenceTable.RoamingService;
import com.subex.rocps.automation.helpers.application.roaming.TestSIMManagement;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;

import com.subex.automation.helpers.util.FailureHelper;

public class TCRoamingService extends PSAcceptanceTest
{
	String path = System.getProperty( "user.dir" ) + "\\src\\main\\resources\\";

	String workBookName = "FunctionalTestCases.xlsx";
	String sheetName = "RoamingService";

	@Test( priority = 1, enabled = true, description = "'Roaming Service'  verify the column Headers Of Search Screen" )
	public void verifyTheColHdrs() throws Exception
	{
		try
		{
			RoamingService roamingService=new RoamingService( path, workBookName, sheetName, "RoamingServiceScreencolVal" );
			roamingService.roamingServiceColumsValidation();

		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}
	
	@Test( priority = 2, enabled = true, description = "'Roaming Service'  creation" )
	public void roamingServiceCreation() throws Exception
	{
		try
		{
			RoamingService roamingService=new RoamingService( path, workBookName, sheetName, "TestRoamingServiceCreation" );
			roamingService.roamingServiceCreation();

		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}
	@Test( priority = 3, enabled = true, description = "'Roaming Service'  Edit" )
	public void roamingServiceEdit() throws Exception
	{
		try
		{
			RoamingService roamingService=new RoamingService( path, workBookName, sheetName, "TestRoamingServiceEdit" );
			roamingService.roamingServiceEdit();

		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

}
