package com.subex.rocps.automation.testcases.functionaltesting;

import com.subex.automation.helpers.util.FailureHelper;
import com.subex.rocps.automation.helpers.application.roaming.RoamingFileStatus;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;

public class TCRoamingRapOutServerCases extends PSAcceptanceTest
{

	String path = System.getProperty( "user.dir" ) + "\\src\\main\\resources\\";
	String workBookName = "FunctionalTestCases.xlsx";
	String sheetName = "RoamingRapOutServerCases";

	@org.testng.annotations.Test( priority = 1, enabled = true, description = "'Roaming File Status' Validate Rap Out  with Severe Return for 'MOC' Context" )
	public void validateRapOutRoamFileStatusWithSevere_MOC() throws Exception

	{
		try
		{
			RoamingFileStatus roamingFlStatusRapOb = new RoamingFileStatus( path, workBookName, sheetName, "RoamingRapOutFlValidate_TAPINSevereReturn_MOC" );
			roamingFlStatusRapOb.validateRapOutFileWithActionPerform();
		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 2, enabled = true, description = "'Roaming File Status' validate Rap Out  with Fatal Return for 'MOC' Context" )
	public void validateRapOutRoamFileStatusWithFatal_MOC() throws Exception

	{
		try
		{

			RoamingFileStatus roamingFlStatusRapOb = new RoamingFileStatus( path, workBookName, sheetName, "RoamingRapOutFlValidate_WithFatalReturn_MOC" );
			roamingFlStatusRapOb.validateRapOutFileWithActionPerform();
		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 3, enabled = true, description = "'Roaming File Status' validate Rap out   with Severe Return for 'GPRS, MOC, MTC' Context" )
	public void validateRapoutRoamFileStatusWithSevere_AllContext() throws Exception

	{
		try
		{
			RoamingFileStatus roamingFlStatusRapOb = new RoamingFileStatus( path, workBookName, sheetName, "RoamingRapOutFlValidate__TAPIN_SevereError_AllContext" );
			roamingFlStatusRapOb.validateRapOutFileWithActionPerform();
		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}
}
