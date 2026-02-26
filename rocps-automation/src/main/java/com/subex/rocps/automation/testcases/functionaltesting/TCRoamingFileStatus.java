package com.subex.rocps.automation.testcases.functionaltesting;

import org.testng.annotations.Test;
import com.subex.rocps.automation.helpers.application.roaming.RoamingFileStatus;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;

import com.subex.automation.helpers.util.FailureHelper;

public class TCRoamingFileStatus extends PSAcceptanceTest
{
	String path = System.getProperty( "user.dir" ) + "\\src\\main\\resources\\";

	String workBookName = "FunctionalTestCases.xlsx";
	String sheetName = "RoamingFileStatus";

	@Test( priority = 1, enabled = true, description = "'Roaming File Status'  verify the column Headers Of Search Screen" )
	public void verifyTheColHdrs() throws Exception
	{
		try
		{
			RoamingFileStatus roamingFileStatus = new RoamingFileStatus( path, workBookName, sheetName, "RoamingFileStatusScreencolVal" );
			roamingFileStatus.roamingFlStatusolumsValidation();
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

	//@Test( priority = 2, enabled = true, description = "'Roaming File Status'  validate search result" )
	public void roamingFileStatusValidateSearchResult() throws Exception
	{
		try
		{
			RoamingFileStatus roamingFileStatus = new RoamingFileStatus( path, workBookName, sheetName, "RoamingFileStatusValidateSearchResult" );
			roamingFileStatus.validateRoamingFlStatusSearchResult();
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

	//@Test( priority = 3, enabled = true, description = "'Roaming File Status'  TAP IN ' validate action result with action task" )
	public void roamFlStatusValidateSrchResWithActionTapIn() throws Exception
	{
		try
		{
			RoamingFileStatus roamingFileStatus = new RoamingFileStatus( path, workBookName, sheetName, "TestRoamingFlStatusTapInAction" );
			roamingFileStatus.roamFlStatusValidateSrchResWithAction();
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}
}
