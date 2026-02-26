package com.subex.rocps.automation.testcases.functionaltesting;

import com.subex.rocps.automation.helpers.application.monitoring.PSCollectedFileSearchHelper;
import com.subex.rocps.automation.helpers.application.roaming.RoamingFileStatus;
import com.subex.rocps.automation.helpers.application.system.PSTaskSearchHelper;
import com.subex.rocps.automation.helpers.application.system.TaskSchedule;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;

import com.subex.automation.helpers.application.screens.TaskControllerHelper;
import com.subex.automation.helpers.util.FailureHelper;

public class TCRoamingRapInServerCases extends PSAcceptanceTest
{

	String path = System.getProperty( "user.dir" ) + "\\src\\main\\resources\\";

	String workBookName = "FunctionalTestCases.xlsx";
	String sheetName = "RoamingRapInServerCases";

	@org.testng.annotations.Test( priority = 1, enabled = true, description = "task Controller capabilities",retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void taskControllerCapabilities() throws Exception
	{
		try
		{

			TaskControllerHelper taskObj = new TaskControllerHelper();
			taskObj.setTaskControllerCapability( path, workBookName, "RoamingPrerequisite", "TCCapability", 1 );

		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 2, enabled = true, description = "Roaming 'RapIn' file collection and task verification with Fatal Return" )
	public void rapInFlCollTaskStatusWithFatalReturn() throws Exception

	{
		try
		{
			TaskSchedule taskObj = new TaskSchedule();
			taskObj.fileCollection( path, workBookName, sheetName, "FileSchedule_Roaming_RapIn_FatalReturn", 1 );
			PSCollectedFileSearchHelper collectedFlObj = new PSCollectedFileSearchHelper( path, workBookName, sheetName, "CollectedFileSearchRapIn_FatalReturn" );
			collectedFlObj.validationOfCollectedFile();
			PSTaskSearchHelper psTaskobj = new PSTaskSearchHelper();
			psTaskobj.psVerifyTaskStatus( path, workBookName, sheetName, "RapInTaskStatus", 1 );
		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 3, enabled = true, description = "'Roaming File Status' TapIn with  Fatal Return" )
	public void rapInRoamFileStatusWithFatalReturn() throws Exception

	{
		try
		{
			RoamingFileStatus roamingFileStatus = new RoamingFileStatus( path, workBookName, sheetName, "RoamingFlStatusRapIn_FatalReturn" );
			roamingFileStatus.roamFlStatusValidateSrchResWithAction();
		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}
}
