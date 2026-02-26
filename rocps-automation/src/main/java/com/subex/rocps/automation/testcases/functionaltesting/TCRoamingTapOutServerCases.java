package com.subex.rocps.automation.testcases.functionaltesting;

import org.testng.annotations.Test;

import com.subex.rocps.automation.helpers.application.aggregation.AggregationResult;
import com.subex.rocps.automation.helpers.application.matchandrate.EventIdentiferDefinition;
import com.subex.rocps.automation.helpers.application.matchandrate.EventIdentifierValue;
import com.subex.rocps.automation.helpers.application.matchandrate.EventIdentifierValueGroup;
import com.subex.rocps.automation.helpers.application.matchandrate.EventMatchRule;
import com.subex.rocps.automation.helpers.application.matchandrate.EventMatchRuleGroup;
import com.subex.rocps.automation.helpers.application.monitoring.PSCollectedFileSearchHelper;
import com.subex.rocps.automation.helpers.application.partnerConfiguration.Account;
import com.subex.rocps.automation.helpers.application.partnerConfiguration.Agent;
import com.subex.rocps.automation.helpers.application.partnerConfiguration.BillProfile;
import com.subex.rocps.automation.helpers.application.referenceTable.RoamingSerMatchExpresion;
import com.subex.rocps.automation.helpers.application.roaming.RoamingFileStatus;
import com.subex.rocps.automation.helpers.application.roaming.TapOutErrExclRecords;
import com.subex.rocps.automation.helpers.application.system.PSTaskSearchHelper;
import com.subex.rocps.automation.helpers.application.system.TaskSchedule;
import com.subex.rocps.automation.helpers.application.tariffs.PSTariffHelper;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;

import com.subex.automation.helpers.application.screens.BandHelper;
import com.subex.automation.helpers.application.screens.ElementCreateHelper;
import com.subex.automation.helpers.application.screens.TariffClassHelper;
import com.subex.automation.helpers.application.screens.TariffHelper;
import com.subex.automation.helpers.application.screens.TaskControllerHelper;
import com.subex.automation.helpers.util.FailureHelper;

public class TCRoamingTapOutServerCases extends PSAcceptanceTest
{
	String path = System.getProperty( "user.dir" ) + "\\src\\main\\resources\\";

	String workBookName = "FunctionalTestCases.xlsx";
	String sheetName = "RoamingTapOutServerCases";

	@org.testng.annotations.Test( priority = 1, enabled = true, description = "task Controller capabilities", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
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

	@org.testng.annotations.Test( priority = 2, enabled = true, description = "Roaming Service Match Expresion Prerequisite", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void RoamingServMatchExprPrerequisite() throws Exception
	{
		try
		{

			RoamingSerMatchExpresion roamingSerMatchExpresion = new RoamingSerMatchExpresion( path, workBookName, "RoamingServMatchExpr", "SMS_MTC_OriginatorEdit" );
			roamingSerMatchExpresion.roamSerMatExpEdit();

		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 3, enabled = true, description = "Tap Out  ,File collection and mnr task verification For Voice" )
	public void tapOutVoiceMNRPrerequisite() throws Exception

	{
		try
		{
			TaskSchedule taskObj = new TaskSchedule();
			taskObj.fileCollection( path, workBookName, "RoamingTapOutServerCases", "FileSchedule_Roaming_TapOut_Voice", 1 );
			PSCollectedFileSearchHelper collectedFlObj = new PSCollectedFileSearchHelper( path, workBookName, "RoamingTapOutServerCases", "CollectedFileSearchTapOut_Voice" );
			collectedFlObj.validationOfCollectedFile();
			PSTaskSearchHelper psTaskobj = new PSTaskSearchHelper();
			psTaskobj.psVerifyTaskStatus( path, workBookName, "RoamingTapOutServerCases", "TapOutVoiceTaskStatus", 1 );

		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 4, enabled = true, description = "Tap Out ,File collection and mnr task verification for SMS" )
	public void tapOutSMSMNRPrerequisite() throws Exception

	{
		try
		{
			TaskSchedule taskObj = new TaskSchedule();
			taskObj.fileCollection( path, workBookName, "RoamingTapOutServerCases", "FileSchedule_Roaming_TapOut_SMS", 1 );
			PSCollectedFileSearchHelper collectedFlObj = new PSCollectedFileSearchHelper( path, workBookName, "RoamingTapOutServerCases", "CollectedFileSearchTapOut_SMS" );
			collectedFlObj.validationOfCollectedFile();
			PSTaskSearchHelper psTaskobj = new PSTaskSearchHelper();
			psTaskobj.psVerifyTaskStatus( path, workBookName, "RoamingTapOutServerCases", "TapOutSMSTaskStatus", 1 );

		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 5, enabled = true, description = "Schedule and verify Recurring task for  Aggregation of Tap Out Voice and SMS " )
	public void tapOutVoiceVerifyAggregationResultForSmsVoice() throws Exception

	{
		try
		{
			TaskSchedule taskObj = new TaskSchedule();
			PSTaskSearchHelper psTaskobj = new PSTaskSearchHelper();
			taskObj.scheduleRecurringTask( path, workBookName, "RoamingTapOutServerCases", "RecurringTaskMaterTask", 1 );
			psTaskobj.psVerifyTaskStatus( path, workBookName, "RoamingTapOutServerCases", "AggrTaskStatus", 1 );
			AggregationResult aggrResObj = new AggregationResult( path, workBookName, "RoamingTapOutServerCases", "AggregationResult_VerifyForVoiceSms" );
			aggrResObj.isAggregationResultPresent();

		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 6, enabled = true, description = "Verify the Tap Out File with Voice, SMS From 'Roaming file status', Folder and perform the task action" )
	public void verifyTapOutFileForVoiceSMS() throws Exception

	{
		try
		{

			RoamingFileStatus roamingFileStatus = new RoamingFileStatus( path, workBookName, "RoamingTapOutServerCases", "RoamingFlStatusTapOutVoiceAndSMS" );
			roamingFileStatus.validateTapOutFile();
		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 7, enabled = true, description = "Tap Out ,File collection and mnr task verification for Excluded Context Records" )
	public void tapOutExclContextMNRPrerequisite() throws Exception

	{
		try
		{
			TaskSchedule taskObj = new TaskSchedule();
			taskObj.fileCollection( path, workBookName, "RoamingTapOutServerCases", "FileSchedule_Roaming_TapOut_ExcludedRecords", 1 );
			PSCollectedFileSearchHelper collectedFlObj = new PSCollectedFileSearchHelper( path, workBookName, "RoamingTapOutServerCases", "CollectedFileSearchTapOut_ExcludedRecords" );
			collectedFlObj.validationOfCollectedFile();
			PSTaskSearchHelper psTaskobj = new PSTaskSearchHelper();
			psTaskobj.psVerifyTaskStatus( path, workBookName, "RoamingTapOutServerCases", "TapOutVoiceTaskStatus", 1 );

		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 8, enabled = true, description = "Schedule and verify Recurring task for  Aggregation of Tap Out Excluded Context Records" )
	public void tapOutExclContextVerifyAggregationResult() throws Exception

	{
		try
		{
			TaskSchedule taskObj = new TaskSchedule();
			PSTaskSearchHelper psTaskobj = new PSTaskSearchHelper();
			taskObj.scheduleRecurringTask( path, workBookName, "RoamingTapOutServerCases", "RecurringTaskMaterTask", 1 );
			psTaskobj.psVerifyTaskStatus( path, workBookName, "RoamingTapOutServerCases", "AggrTaskStatus", 1 );
			AggregationResult aggrResObj = new AggregationResult( path, workBookName, "RoamingTapOutServerCases", "AggregationResult_VerifyForExcludedRecords" );
			aggrResObj.isAggregationResultPresent();

		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}

	@Test( priority = 9, enabled = true, description = "Schdule Verify TapOut task and 'TAP Out Errored & Excluded Records'  verify the search result of 'excluded Records' action" )
	public void validateSearchResultOfExclRecordsAction() throws Exception
	{
		try
		{
			TaskSchedule taskObj = new TaskSchedule();
			PSTaskSearchHelper psTaskobj = new PSTaskSearchHelper();
			taskObj.scheduleRecurringTask( path, workBookName, "RoamingTapOutServerCases", "RecurringTapOutStreamTask", 1 );
			psTaskobj.psVerifyTaskStatus( path, workBookName, "RoamingTapOutServerCases", "TapOutStreamTaskStatus", 1 );
			TapOutErrExclRecords tapOutErrExclRecords = new TapOutErrExclRecords( path, workBookName, sheetName, "TapOutErrExclValidateExclRecordsSearchResult" );
			tapOutErrExclRecords.validateTapOutErrExclRecordSearchResult();

		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

}
