package com.subex.rocps.automation.testcases.functionaltesting;

import org.testng.annotations.Test;

import com.subex.automation.helpers.application.screens.TaskControllerHelper;
import com.subex.automation.helpers.util.FailureHelper;
import com.subex.rocps.automation.helpers.application.aggregation.AggregationResult;
import com.subex.rocps.automation.helpers.application.monitoring.PSCollectedFileSearchHelper;
import com.subex.rocps.automation.helpers.application.reaggregation.UsageBackoutRequest;
import com.subex.rocps.automation.helpers.application.system.PSTaskSearchHelper;
import com.subex.rocps.automation.helpers.application.system.TaskSchedule;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;

public class TCUsageBackoutRequest extends PSAcceptanceTest
{
	String path = System.getProperty( "user.dir" ) + "\\src\\main\\resources\\";

	String workBookName = "FunctionalTestCases.xlsx";
	String sheetName = "UsageBackoutRequest";

	@org.testng.annotations.Test( priority = 1, enabled = true, description = "task Controller capabilities", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void taskControllerCapabilities() throws Exception
	{
		try
		{

			TaskControllerHelper taskObj = new TaskControllerHelper();
			taskObj.setTaskControllerCapability( path, workBookName, "UsageBackoutRequest", "TCCapability", 1 );
			taskObj.setTaskControllerCapability( path, workBookName, "ROCPreRequisites2", "TCCapability", 1 );

		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 2, enabled = true, description = "Mnr   for Usage Backout Request single usage partitioned" )
	public void usgBackoutReqServerPrereq_SingleUsgPart() throws Exception

	{
		try
		{
			TaskSchedule taskObj = new TaskSchedule();
			PSTaskSearchHelper psTaskobj = new PSTaskSearchHelper();
			taskObj.fileCollection( path, workBookName, sheetName, "FileSchedule_UsgBackoutRequest_SingleUsgPartition", 1 );
			PSCollectedFileSearchHelper collectedFlObj = new PSCollectedFileSearchHelper( path, workBookName, sheetName, "CollectedFileSearch_UsgBackoutRequest_SingleUsgPartition" );
			collectedFlObj.validationOfCollectedFile();
			psTaskobj.psVerifyTaskStatus( path, workBookName, "AggregationResults", "MnrTaskStatus", 1 );

		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 3, enabled = true, description = "schedule and verify  Aggregation for Usage Backout Request single usage partitioned" )
	public void usgBackoutReqServerPrereq_Aggregation_SingleUsgPart() throws Exception

	{
		try
		{
			TaskSchedule taskObj = new TaskSchedule();
			PSTaskSearchHelper psTaskobj = new PSTaskSearchHelper();
			taskObj.scheduleRecurringTask( path, workBookName, "AggregationResults", "RecurringTask", 1 );
			psTaskobj.psVerifyTaskStatus( path, workBookName, "AggregationResults", "AggrTaskStatus", 1 );
			AggregationResult aggrResObj = new AggregationResult( path, workBookName, sheetName, "AggregationResult_UsgBackoutRequest_SingleUsgPartition" );
			aggrResObj.isAggregationResultPresent();

		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 4, enabled = true, description = "Mnr   for Usage Backout Request multiple usage partitioned" )
	public void usgBackoutReqServerPrereq_MultipleUsgPart() throws Exception

	{
		try
		{
			TaskSchedule taskObj = new TaskSchedule();
			PSTaskSearchHelper psTaskobj = new PSTaskSearchHelper();
			taskObj.fileCollection( path, workBookName, sheetName, "FileSchedule_UsgBackoutRequest_MultipleUsgPartition", 1 );
			PSCollectedFileSearchHelper collectedFlObj = new PSCollectedFileSearchHelper( path, workBookName, sheetName, "CollectedFileSearch_UsgBackoutRequest_MultipleUsgPartition" );
			collectedFlObj.validationOfCollectedFile();
			psTaskobj.psVerifyTaskStatus( path, workBookName, "AggregationResults", "MnrTaskStatus", 1 );

		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 5, enabled = true, description = "schedule and verify  Aggregation for Usage Backout Request multiple usage partitioned" )
	public void usgBackoutReqServerPrereq_Aggregation_MultipleUsgPart() throws Exception

	{
		try
		{
			TaskSchedule taskObj = new TaskSchedule();
			PSTaskSearchHelper psTaskobj = new PSTaskSearchHelper();
			taskObj.scheduleRecurringTask( path, workBookName, "AggregationResults", "RecurringTask", 1 );
			psTaskobj.psVerifyTaskStatus( path, workBookName, "AggregationResults", "AggrTaskStatus", 1 );
			AggregationResult aggrResObj = new AggregationResult( path, workBookName, sheetName, "AggregationResult_UsgBackoutRequest_MultipleUsgPartition" );
			aggrResObj.isAggregationResultPresent();

		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}

	@Test( priority = 6, enabled = true, description = "Usage Backout Request verify the column Headers Of Search Screen" )
	public void verifyTheColHdrs() throws Exception
	{
		try
		{
			UsageBackoutRequest usageBackoutRequest = new UsageBackoutRequest( path, workBookName, sheetName, "UsageBackoutRequestScreencolVal" );
			usageBackoutRequest.usageBackoutReqColumsValidation();
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

	@Test( priority = 7, enabled = true, description = "Usage Backout Request creation", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void usageBackoutRequestCreation() throws Exception
	{
		try
		{
			UsageBackoutRequest usageBackoutRequest = new UsageBackoutRequest( path, workBookName, sheetName, "UsageBackoutRequestCreation" );
			usageBackoutRequest.usgBackoutReqCreation();
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

	@Test( priority = 8, enabled = true, description = "Usage Backout Request edit", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void usageBackoutRequestEdit() throws Exception
	{
		try
		{
			UsageBackoutRequest usageBackoutRequest = new UsageBackoutRequest( path, workBookName, sheetName, "UsageBackoutRequestEdit" );
			usageBackoutRequest.usgBackoutReqEdit();
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

	@Test( priority = 9, enabled = true, description = "Usage Backout Request schedule single usage partitioned" )
	public void usageBackoutRequestSchedule_singleUsgPart() throws Exception
	{
		try
		{
			UsageBackoutRequest usageBackoutRequest = new UsageBackoutRequest( path, workBookName, sheetName, "UsageBackoutRequestSchedule_singleUsgPart" );
			usageBackoutRequest.usgBackoutReqSchedule();
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

	@Test( priority = 10, enabled = true, description = "Usage Backout Request verify Aggregation result, collected  file deletion single usage partitioned" )
	public void verifyAggResultCollectedFlDeletion_singleUsgPart() throws Exception
	{
		try
		{
			TaskSchedule taskObj = new TaskSchedule();
			PSTaskSearchHelper psTaskobj = new PSTaskSearchHelper();
			taskObj.scheduleRecurringTask( path, workBookName, "AggregationResults", "RecurringTask", 1 );
			psTaskobj.psVerifyTaskStatus( path, workBookName, "AggregationResults", "AggrTaskStatus", 1 );
			AggregationResult aggrResObj = new AggregationResult( path, workBookName, sheetName, "TS101_AggResult_Verifydeletion_SingleUsgPartition" );
			aggrResObj.validateEmptyAggregationResult();
			PSCollectedFileSearchHelper collectedFlObj = new PSCollectedFileSearchHelper( path, workBookName, sheetName, "TS101_CollFileSearch_Verifydeletion_SingleUsgPartition" );
			collectedFlObj.validateOfNotPresentCollectedFile();
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

	@Test( priority = 11, enabled = true, description = "Usage Backout Request schedule multiple usage partitioned" )
	public void usageBackoutRequestSchedule_multipleUsgPart() throws Exception
	{
		try
		{
			UsageBackoutRequest usageBackoutRequest = new UsageBackoutRequest( path, workBookName, sheetName, "UsageBackoutRequestSchedule_multipleUsgPart" );
			usageBackoutRequest.usgBackoutReqSchedule();
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

	@Test( priority = 12, enabled = true, description = "Usage Backout Request verify Aggregation result, collected  file deletion multiple usage partitioned" )
	public void verifyAggResultCollectedFlDeletion_multipleUsgPart() throws Exception
	{
		try
		{
			TaskSchedule taskObj = new TaskSchedule();
			PSTaskSearchHelper psTaskobj = new PSTaskSearchHelper();
			taskObj.scheduleRecurringTask( path, workBookName, "AggregationResults", "RecurringTask", 1 );
			psTaskobj.psVerifyTaskStatus( path, workBookName, "AggregationResults", "AggrTaskStatus", 1 );
			AggregationResult aggrResObj = new AggregationResult( path, workBookName, sheetName, "TS102_AggResult_verifyDeletion_MultipleUsgPartition" );
			aggrResObj.validateEmptyAggregationResult();
			PSCollectedFileSearchHelper collectedFlObj = new PSCollectedFileSearchHelper( path, workBookName, sheetName, "TS102_CollFileSearch_verifyDeletion_MultipleUsgPartition" );
			collectedFlObj.validateOfNotPresentCollectedFile();
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

	@Test( priority = 13, enabled = true, description = "Usage Backout Request view action", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void usageBackoutRequestViewAction() throws Exception
	{
		try
		{
			UsageBackoutRequest usageBackoutRequest = new UsageBackoutRequest( path, workBookName, sheetName, "UsageBackoutRequestViewAction" );
			usageBackoutRequest.usgBackoutReqViewAction();
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

	@Test( priority = 14, enabled = true, description = "Usage Backout Request view log action", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void usageBackoutRequestViewLogAction() throws Exception
	{
		try
		{
			UsageBackoutRequest usageBackoutRequest = new UsageBackoutRequest( path, workBookName, sheetName, "UsageBackoutRequestViewLogAction" );
			usageBackoutRequest.usgBackoutReqViewLogValidateAction();
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}
}
