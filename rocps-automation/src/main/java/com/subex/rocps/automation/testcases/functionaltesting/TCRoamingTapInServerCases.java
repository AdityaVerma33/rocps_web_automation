package com.subex.rocps.automation.testcases.functionaltesting;

import com.subex.rocps.automation.helpers.application.monitoring.PSCollectedFileSearchHelper;
import com.subex.rocps.automation.helpers.application.roaming.RoamingFileStatus;
import com.subex.rocps.automation.helpers.application.system.PSTaskSearchHelper;
import com.subex.rocps.automation.helpers.application.system.TaskSchedule;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;

import com.subex.automation.helpers.application.screens.TaskControllerHelper;
import com.subex.automation.helpers.util.FailureHelper;

public class TCRoamingTapInServerCases extends PSAcceptanceTest
{

	String path = System.getProperty( "user.dir" ) + "\\src\\main\\resources\\";
	String workBookName = "FunctionalTestCases.xlsx";
	String sheetName = "RoamingTapInServerCases";

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

	@org.testng.annotations.Test( priority = 2, enabled = true, description = "Roaming 'TapIn' file collection and task verification  without error for 'MOC' Context" )
	public void tapInFlCollTaskStatusWithoutError_MOC() throws Exception

	{
		try
		{
			TaskSchedule taskObj = new TaskSchedule();
			taskObj.fileCollection( path, workBookName, sheetName, "FileSchedule_Roaming_TapIn_WithoutError_MOC", 1 );
			PSCollectedFileSearchHelper collectedFlObj = new PSCollectedFileSearchHelper( path, workBookName, sheetName, "CollectedFileSearchTapIn_WithoutError_MOC" );
			collectedFlObj.validationOfCollectedFile();
			PSTaskSearchHelper psTaskobj = new PSTaskSearchHelper();
			psTaskobj.psVerifyTaskStatus( path, workBookName, sheetName, "TapInTaskStatus", 1 );
		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 3, enabled = true, description = "'Roaming File Status' TapIn without error for 'MOC' Context" )
	public void tapInRoamFileStatusWithoutError_MOC() throws Exception

	{
		try
		{
			RoamingFileStatus roamingFileStatus = new RoamingFileStatus( path, workBookName, sheetName, "RoamingFlStatusTapInWithoutError_MOC" );
			roamingFileStatus.roamFlStatusValidateSrchResWithAction();
		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 4, enabled = true, description = "Roaming 'TapIn' file collection and task verification with Severe Return for 'MOC' Context" )
	public void tapInFlCollTaskStatusWithSevere_MOC() throws Exception

	{
		try
		{
			TaskSchedule taskObj = new TaskSchedule();
			taskObj.fileCollection( path, workBookName, sheetName, "FileSchedule_Roaming_TapIn_WithSevereReturn_MOC", 1 );
			PSCollectedFileSearchHelper collectedFlObj = new PSCollectedFileSearchHelper( path, workBookName, sheetName, "CollectedFileSearchTapIn_WithSevereReturn_MOC" );
			collectedFlObj.validationOfCollectedFile();
			PSTaskSearchHelper psTaskobj = new PSTaskSearchHelper();
			psTaskobj.psVerifyTaskStatus( path, workBookName, sheetName, "TapInTaskStatus", 1 );
			psTaskobj.psVerifyTaskStatus( path, workBookName, sheetName, "RapOutTaskStatus", 1 );
		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 5, enabled = true, description = "'Roaming File Status' TapIn  with Severe Return for 'MOC' Context" )
	public void tapInRoamFileStatusWithSevere_MOC() throws Exception

	{
		try
		{
			RoamingFileStatus roamingFileStatus = new RoamingFileStatus( path, workBookName, sheetName, "RoamingFlStatusTapInWithSevereReturn_MOC" );
			roamingFileStatus.roamFlStatusValidateSrchResWithAction();
		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}
	@org.testng.annotations.Test( priority = 6, enabled = true, description = "'Roaming File Status' Validate Rap Out  with Severe Return for 'MOC' Context" )
	public void validateRapOutRoamFileStatusWithSevere_MOC() throws Exception

	{
		try
		{
			RoamingFileStatus roamingFlStatusRapOb = new RoamingFileStatus( path, workBookName, sheetName, "RoamingRapOutFlValidate_WithSevereReturn_MOC" );
			roamingFlStatusRapOb.validateRapOutFile();
		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}
	@org.testng.annotations.Test( priority = 7, enabled = true, description = "Roaming 'TapIn' file collection and task verification with Fatal Return for 'MOC' Context" )
	public void tapInFlCollTaskStatusWithFatal_MOC() throws Exception

	{
		try
		{
			TaskSchedule taskObj = new TaskSchedule();
			taskObj.fileCollection( path, workBookName, sheetName, "FileSchedule_Roaming_TapIn_WithFatalReturn_MOC", 1 );
			PSCollectedFileSearchHelper collectedFlObj = new PSCollectedFileSearchHelper( path, workBookName, sheetName, "CollectedFileSearchTapIn_WithFatalReturn_MOC" );
			collectedFlObj.validationOfCollectedFile();
			PSTaskSearchHelper psTaskobj = new PSTaskSearchHelper();
			psTaskobj.psVerifyTaskStatus( path, workBookName, sheetName, "TapInTaskStatus", 1 );
			psTaskobj.psVerifyTaskStatus( path, workBookName, sheetName, "RapOutTaskStatus", 1 );
		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 8, enabled = true, description = "'Roaming File Status' TapIn  with Fatal Return for 'MOC' Context" )
	public void tapInRoamFileStatusWithFatal_MOC() throws Exception

	{
		try
		{
			RoamingFileStatus roamingFileStatus = new RoamingFileStatus( path, workBookName, sheetName, "RoamingFlStatusTapInWithFatalReturn_MOC" );
			roamingFileStatus.roamFlStatusValidateSrchResWithAction();
			
		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}
	@org.testng.annotations.Test( priority = 9, enabled = true, description = "'Roaming File Status' validate Rap Out  with Fatal Return for 'MOC' Context" )
	public void validateRapOutRoamFileStatusWithFatal_MOC() throws Exception

	{
		try
		{
			
			RoamingFileStatus roamingFlStatusRapOb = new RoamingFileStatus( path, workBookName, sheetName, "RoamingRapOutFlValidate_WithFatalReturn_MOC" );
			roamingFlStatusRapOb.validateRapOutFile();
		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 10, enabled = true, description = "Roaming 'TapIn' file collection and task verification  without error for 'MTC' Context" )
	public void tapInFlCollTaskStatusWithoutError_MTC() throws Exception

	{
		try
		{
			TaskSchedule taskObj = new TaskSchedule();
			taskObj.fileCollection( path, workBookName, sheetName, "FileScheduleTapIn_WithoutError_MTC", 1 );
			PSCollectedFileSearchHelper collectedFlObj = new PSCollectedFileSearchHelper( path, workBookName, sheetName, "CollFileSearchTapIn_WithoutError_MTC" );
			collectedFlObj.validationOfCollectedFile();
			PSTaskSearchHelper psTaskobj = new PSTaskSearchHelper();
			psTaskobj.psVerifyTaskStatus( path, workBookName, sheetName, "TapInTaskStatus", 1 );
		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 11, enabled = true, description = "'Roaming File Status' TapIn without error for 'MTC' Context" )
	public void tapInRoamFileStatusWithoutError_MTC() throws Exception

	{
		try
		{
			RoamingFileStatus roamingFileStatus = new RoamingFileStatus( path, workBookName, sheetName, "RoamingFlStatusTapInWithoutError_MTC" );
			roamingFileStatus.roamFlStatusValidateSrchResWithAction();
		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 12, enabled = true, description = "Roaming 'TapIn' file collection and task verification  without error for 'GPRS' Context" )
	public void tapInFlCollTaskStatusWithoutError_GPRS() throws Exception

	{
		try
		{
			TaskSchedule taskObj = new TaskSchedule();
			taskObj.fileCollection( path, workBookName, sheetName, "FileScheduleTapIn_WithoutError_GPRS", 1 );
			PSCollectedFileSearchHelper collectedFlObj = new PSCollectedFileSearchHelper( path, workBookName, sheetName, "CollFileSearchTapIn_WithoutError_GPRS" );
			collectedFlObj.validationOfCollectedFile();
			PSTaskSearchHelper psTaskobj = new PSTaskSearchHelper();
			psTaskobj.psVerifyTaskStatus( path, workBookName, sheetName, "TapInTaskStatus", 1 );
		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 13, enabled = true, description = "'Roaming File Status' TapIn without error for 'GPRS' Context" )
	public void tapInRoamFileStatusWithoutError_GPRS() throws Exception

	{
		try
		{
			RoamingFileStatus roamingFileStatus = new RoamingFileStatus( path, workBookName, sheetName, "RoamingFlStatusTapInWithoutError_GPRS" );
			roamingFileStatus.roamFlStatusValidateSrchResWithAction();
		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 14, enabled = true, description = "Roaming 'TapIn' file collection and task verification  without error for 'GPRS, MOC, MTC' Context" )
	public void tapInFlCollTaskStatusWithoutError_AllContext() throws Exception

	{
		try
		{
			TaskSchedule taskObj = new TaskSchedule();
			taskObj.fileCollection( path, workBookName, sheetName, "FileScheduleTapIn_WithoutError_AllContext", 1 );
			PSCollectedFileSearchHelper collectedFlObj = new PSCollectedFileSearchHelper( path, workBookName, sheetName, "CollFileSearchTapIn_WithoutError_AllContext" );
			collectedFlObj.validationOfCollectedFile();
			PSTaskSearchHelper psTaskobj = new PSTaskSearchHelper();
			psTaskobj.psVerifyTaskStatus( path, workBookName, sheetName, "TapInTaskStatus", 1 );
		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 15, enabled = true, description = "'Roaming File Status' TapIn without error for 'GPRS, MOC, MTC' Context" )
	public void tapInRoamFileStatusWithoutError_AllContext() throws Exception

	{
		try
		{
			RoamingFileStatus roamingFileStatus = new RoamingFileStatus( path, workBookName, sheetName, "RoamingFlStatusTapInWithoutError_AllContext" );
			roamingFileStatus.roamFlStatusValidateSrchResWithAction();
		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 16, enabled = true, description = "Roaming 'TapIn' file collection and task verification  with Severe Return for 'GPRS, MOC, MTC' Context" )
	public void tapInFlCollTaskStatusWithSevere_AllContext() throws Exception

	{
		try
		{
			TaskSchedule taskObj = new TaskSchedule();
			taskObj.fileCollection( path, workBookName, sheetName, "FileScheduleTapIn_SevereError_AllContext", 1 );
			PSCollectedFileSearchHelper collectedFlObj = new PSCollectedFileSearchHelper( path, workBookName, sheetName, "CollFileSearchTapIn_SevereError_AllContext" );
			collectedFlObj.validationOfCollectedFile();
			PSTaskSearchHelper psTaskobj = new PSTaskSearchHelper();
			psTaskobj.psVerifyTaskStatus( path, workBookName, sheetName, "TapInTaskStatus", 1 );
			psTaskobj.psVerifyTaskStatus( path, workBookName, sheetName, "RapOutTaskStatus", 1 );
		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 17, enabled = true, description = "'Roaming File Status' TapIn  with Severe Return for 'GPRS, MOC, MTC' Context" )
	public void tapInRoamFileStatusWithSevere_AllContext() throws Exception

	{
		try
		{
			RoamingFileStatus roamingFileStatus = new RoamingFileStatus( path, workBookName, sheetName, "RoamingFlStatusTapInSevereError_AllContext" );
			roamingFileStatus.roamFlStatusValidateSrchResWithAction();
			
		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}
	@org.testng.annotations.Test( priority = 18, enabled = true, description = "'Roaming File Status' validate Rap out   with Severe Return for 'GPRS, MOC, MTC' Context" )
	public void validateRapoutRoamFileStatusWithSevere_AllContext() throws Exception

	{
		try
		{
			RoamingFileStatus roamingFlStatusRapOb = new RoamingFileStatus( path, workBookName, sheetName, "RoamingRapOutFlValidate__TAPIN_SevereError_AllContext" );
			roamingFlStatusRapOb.validateRapOutFile();
		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 19, enabled = true, description = "Roaming 'TapIn' file collection and task verification  without error for 'GPRS' Context FOR TD(Test Data)" )
	public void tapInFlCollTaskStatusWithoutError_TD() throws Exception

	{
		try
		{
			TaskSchedule taskObj = new TaskSchedule();
			taskObj.fileCollection( path, workBookName, sheetName, "FileScheduleTapIn_WithoutError_TD", 1 );
			PSCollectedFileSearchHelper collectedFlObj = new PSCollectedFileSearchHelper( path, workBookName, sheetName, "CollFileSearchTapIn_WithoutError_TD" );
			collectedFlObj.validationOfCollectedFile();
			PSTaskSearchHelper psTaskobj = new PSTaskSearchHelper();
			psTaskobj.psVerifyTaskStatus( path, workBookName, sheetName, "TapInTaskStatus", 1 );
		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 20, enabled = true, description = "'Roaming File Status' TapIn without error for 'GPRS' Context for TD(Test Data) " )
	public void tapInRoamFileStatusWithoutError_TD() throws Exception

	{
		try
		{
			RoamingFileStatus roamingFileStatus = new RoamingFileStatus( path, workBookName, sheetName, "RoamingFlStatusTapInWithoutError_TD" );
			roamingFileStatus.roamFlStatusValidateSrchResWithAction();
		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}

}
