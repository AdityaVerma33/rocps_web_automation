package com.subex.rocps.automation.testcases.functionaltesting;

import org.testng.annotations.Test;

import com.subex.rocps.automation.helpers.application.referenceTable.BandTypToCalTypGrpMaping;
import com.subex.rocps.automation.helpers.application.referenceTable.CallTypeGroup;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;

import com.subex.automation.helpers.util.FailureHelper;

public class TCBandTyToCalTyGrpMapping extends PSAcceptanceTest
{

	String path = System.getProperty( "user.dir" ) + "\\src\\main\\resources\\";

	String workBookName = "FunctionalTestCases.xlsx";
	String sheetName = "BandTypeToCallTypeGrpMapping";

	@Test( priority = 1, enabled = true, description = "'Band Type to Call Type Group Mapping'  prerequiste of Call Type Group creation",retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void callTypeGrpPrerequisite() throws Exception
	{
		try
		{
			CallTypeGroup callTypeGroup = new CallTypeGroup( path, workBookName, "CallTypeGroup", "TestCallTypeGroupCreation" );
			callTypeGroup.callTypeGrpCreation();

		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

	@Test( priority = 2, enabled = true, description = "'Band Type to Call Type Group Mapping'  verify the column Headers Of Search Screen" )
	public void verifyTheColHdrs() throws Exception
	{
		try
		{
			BandTypToCalTypGrpMaping baCalTypGrpMaping = new BandTypToCalTypGrpMaping( path, workBookName, sheetName, "BandTypeToCallTypeGrpMappingScreencolVal" );
			baCalTypGrpMaping.bandTyToCallTyGrpColumsValidation();

		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

	@Test( priority = 3, enabled = true, description = "'Band Type to Call Type Group Mapping'  creation" )
	public void BandTypeToCallTypeGrpMappingCreation() throws Exception
	{
		try
		{
			BandTypToCalTypGrpMaping baCalTypGrpMaping = new BandTypToCalTypGrpMaping( path, workBookName, sheetName, "TestBandTypeToCallTypeGrpCreation" );
			baCalTypGrpMaping.bandTyToCallTyGrpCreation();

		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}
	@Test( priority = 4, enabled = true, description = "'Band Type to Call Type Group Mapping'  Edit" )
	public void BandTypeToCallTypeGrpMappingEdit() throws Exception
	{
		try
		{
			BandTypToCalTypGrpMaping baCalTypGrpMaping = new BandTypToCalTypGrpMaping( path, workBookName, sheetName, "TestBandTypeToCallTypeGrpEdit" );
			baCalTypGrpMaping.bandTyToCallTyGrpEdit();

		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}
}
