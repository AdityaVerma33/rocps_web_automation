package com.subex.rocps.automation.testcases.functionaltesting;

import org.testng.annotations.Test;

import com.subex.automation.helpers.application.screens.BandHelper;
import com.subex.automation.helpers.application.screens.ElementCreateHelper;
import com.subex.automation.helpers.application.screens.TariffClassHelper;
import com.subex.automation.helpers.application.screens.TariffHelper;
import com.subex.automation.helpers.application.screens.TaskControllerHelper;
import com.subex.automation.helpers.util.FailureHelper;
import com.subex.rocps.automation.helpers.application.aggregation.AggregationResult;
import com.subex.rocps.automation.helpers.application.bills.Bills;
import com.subex.rocps.automation.helpers.application.matchandrate.Operator;
import com.subex.rocps.automation.helpers.application.monitoring.PSCollectedFileSearchHelper;
import com.subex.rocps.automation.helpers.application.networkConfiguraiton.Route;
import com.subex.rocps.automation.helpers.application.networkConfiguraiton.RouteGroup;
import com.subex.rocps.automation.helpers.application.networkConfiguraiton.Switch;
import com.subex.rocps.automation.helpers.application.partnerConfiguration.Account;
import com.subex.rocps.automation.helpers.application.partnerConfiguration.Agent;
import com.subex.rocps.automation.helpers.application.partnerConfiguration.BillProfile;
import com.subex.rocps.automation.helpers.application.partnerConfiguration.EventUsgRequestHelper;
import com.subex.rocps.automation.helpers.application.system.PSTaskSearchHelper;
import com.subex.rocps.automation.helpers.application.system.TaskSchedule;
import com.subex.rocps.automation.helpers.application.tariffs.PSTariffHelper;
import com.subex.rocps.automation.helpers.application.xdrextraction.XdrExtrTempHelper;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;

public class TCEventUsgRequest extends PSAcceptanceTest
{

	String path = System.getProperty( "user.dir" ) + "\\src\\main\\resources\\";

	String workBookName = "FunctionalTestCases.xlsx";
	String sheetName = "EventUsageRequest";

	@org.testng.annotations.Test( priority = 1, enabled = true, description = "task Controller capabilities", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void taskControllerCapabilities() throws Exception
	{
		try
		{

			TaskControllerHelper taskObj = new TaskControllerHelper();
			taskObj.setTaskControllerCapability( path, workBookName, "EventUsgPrerequisite", "TCCapability", 1 );
			taskObj.setTaskControllerCapability(path, workBookName, "XDR RocPrerequiste", "TCCapability", 1);

		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 2, enabled = true, description = "Mnr  for Event Usage Request  bill Based" )
	public void billServerPrerequisiteMnr() throws Exception

	{
		try
		{
			TaskSchedule taskObj = new TaskSchedule();
			taskObj.fileCollection( path, workBookName, sheetName, "FileSchedule_Xdr_EventUsgReq_BillBased", 1 );
			PSCollectedFileSearchHelper collectedFlObj = new PSCollectedFileSearchHelper( path, workBookName, sheetName, "CollectedFileSearch_EventUsgReq_BillBased" );
			collectedFlObj.validationOfCollectedFile();
			PSTaskSearchHelper psTaskobj = new PSTaskSearchHelper();
			psTaskobj.psVerifyTaskStatus( path, workBookName, sheetName, "MnrTaskStatus", 1 );

		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 3, enabled = true, description = "schedule and verify  Aggregation for BillServerPrerequistes" )
	public void BillServerPrerequistes_Aggregation() throws Exception

	{
		try
		{
			TaskSchedule taskObj = new TaskSchedule();
			PSTaskSearchHelper psTaskobj = new PSTaskSearchHelper();
			taskObj.scheduleRecurringTask( path, workBookName, sheetName, "RecurringTask", 1 );
			psTaskobj.psVerifyTaskStatus( path, workBookName, sheetName, "AggrTaskStatus", 1 );

		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 4, enabled = true, description = "Generation of Bill for BillServerPrerequistes" )
	public void BillServerPrerequistes_GenerateBill() throws Exception

	{
		try
		{

			AggregationResult aggrResObj = new AggregationResult( path, workBookName, sheetName, "AggregationResult_EventUsgReq_BillBased" );
			aggrResObj.isAggregationResultPresent();

			Bills billObjec1 = new Bills( path, workBookName, sheetName, "HotBillCreate_EventUsgReq_BillBased" );
			billObjec1.billAction();

		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}

	@Test( priority = 5, enabled = true, description = "Event Usage Request verify the column Headers Of Search Screen" )
	public void verifyTheColHdrs() throws Exception
	{
		try
		{
			EventUsgRequestHelper eventUsgRequestHelper = new EventUsgRequestHelper( path, workBookName, sheetName, "EventUsgReqSearchScreencolVal", 1 );
			eventUsgRequestHelper.verifyColmnHeaderOfeventUsgReq();
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

	@Test( priority = 6, enabled = true, description = "Event Usage Request creation  with Stream Stage Based and with entity", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void creationEventUsgReq_StreamStgWithEntities() throws Exception
	{
		try
		{
			EventUsgRequestHelper eventUsgRequestHelper = new EventUsgRequestHelper( path, workBookName, sheetName, "EventUsgReqCreationStreamStageBasedWithEntities", 1 );
			eventUsgRequestHelper.creationOfeventUsgReq();
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

	@Test( priority = 7, enabled = true, description = "Event Usage Request creation with Stream Stage Based and No entity", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void creationEventUsg_WithStreamStgWithOutEntities() throws Exception
	{
		try
		{
			EventUsgRequestHelper eventUsgRequestHelper = new EventUsgRequestHelper( path, workBookName, sheetName, "EventUsgReqCreationStreamStageBasedWithoutEntities", 1 );
			eventUsgRequestHelper.creationOfeventUsgReq();
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

	@Test( priority = 8, enabled = true, description = "Event Usage Request schedule  with Stream Stage Based and with entity", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void scheduleEventUsgReq_StreamStgWithEntities() throws Exception
	{
		try
		{
			EventUsgRequestHelper eventUsgRequestHelper = new EventUsgRequestHelper( path, workBookName, sheetName, "EventUsgReqScheduleStreamStageBasedWithEntities", 1 );
			eventUsgRequestHelper.scheduleEventUsgReq();
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

	@Test( priority = 9, enabled = true, description = "Event Usage Request schedule with Stream Stage Based and No entity", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void scheduleEventUsg_WithStreamStgWithOutEntities() throws Exception
	{
		try
		{
			EventUsgRequestHelper eventUsgRequestHelper = new EventUsgRequestHelper( path, workBookName, sheetName, "EventUsgReqScheduleStreamStageBasedWithoutEntities", 1 );
			eventUsgRequestHelper.scheduleEventUsgReq();
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

	@Test( priority = 10, enabled = true, description = "Event Usage Request creation with view Template", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void creationEventUsgReqWithViewTemplate() throws Exception
	{
		try
		{
			EventUsgRequestHelper eventUsgRequestHelper = new EventUsgRequestHelper( path, workBookName, sheetName, "EventUsgReqCreationWithViewTemplateAction", 1 );
			eventUsgRequestHelper.creationOfeventUsgReq();
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

	@Test( priority = 11, enabled = true, description = "Event Usage Request creation with Save AS", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void creationEventUsgReqWithSaveAs() throws Exception
	{
		try
		{
			EventUsgRequestHelper eventUsgRequestHelper = new EventUsgRequestHelper( path, workBookName, sheetName, "EventUsgReqCreationSaveAsAction", 1 );
			eventUsgRequestHelper.creationOfeventUsgReqWithSaveAs();
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

	@Test( priority = 12, enabled = true, description = "Event Usage Request creation with Auto schedule", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void creationEventUsgReqWithAutoSchedule() throws Exception
	{
		try
		{
			EventUsgRequestHelper eventUsgRequestHelper = new EventUsgRequestHelper( path, workBookName, sheetName, "EventUsgReqCreationWithAutoSchedule", 1 );
			eventUsgRequestHelper.creationOfeventUsgReq();
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

	@Test( priority = 13, enabled = true, description = "Event Usage Request creation with Bill based with entities", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void creationEventUsgReq_BillWithEntities() throws Exception
	{
		try
		{
			EventUsgRequestHelper eventUsgRequestHelper = new EventUsgRequestHelper( path, workBookName, sheetName, "EventUsgReqCreationBillBasedWithEntities", 1 );
			eventUsgRequestHelper.creationOfeventUsgReq();
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

	@Test( priority = 14, enabled = true, description = "Event Usage Request creation with Bill and without entity", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void creationEventUsgReq_BillWithoutEntities() throws Exception
	{
		try
		{
			EventUsgRequestHelper eventUsgRequestHelper = new EventUsgRequestHelper( path, workBookName, sheetName, "EventUsgReqCreationBillBasedWithoutEntities", 1 );
			eventUsgRequestHelper.creationOfeventUsgReq();
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

	@Test( priority = 15, enabled = true, description = "Event Usage Request schedule with Bill based", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void scheduleEventUsgReq_Bill() throws Exception
	{
		try
		{
			EventUsgRequestHelper eventUsgRequestHelper = new EventUsgRequestHelper( path, workBookName, sheetName, "EventUsgReqScheduleBillBasedWithoutEntities", 1 );
			eventUsgRequestHelper.scheduleEventUsgReq();
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

	@Test( priority = 16, enabled = true, description = "Event Usage Request creation with Auto schedule Bill Based", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void creationEventUsgReqWithAutoSch_Bill() throws Exception
	{
		try
		{
			EventUsgRequestHelper eventUsgRequestHelper = new EventUsgRequestHelper( path, workBookName, sheetName, "EventUsgReqCreationBillBased_Auto", 1 );
			eventUsgRequestHelper.creationOfeventUsgReq();
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}
}
