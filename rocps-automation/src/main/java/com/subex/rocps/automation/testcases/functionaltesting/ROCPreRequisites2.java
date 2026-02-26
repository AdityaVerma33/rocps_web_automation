package com.subex.rocps.automation.testcases.functionaltesting;

import com.subex.rocps.automation.helpers.application.system.Streams;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;

import com.subex.automation.helpers.application.screens.FileCollectionHelper;
import com.subex.automation.helpers.application.screens.FileSourceHelper;
import com.subex.automation.helpers.application.screens.RecurringTaskHelper;
import com.subex.automation.helpers.application.screens.TriggerHelper;
import com.subex.automation.helpers.util.FailureHelper;

public class ROCPreRequisites2 extends PSAcceptanceTest
{
	String path = System.getProperty( "user.dir" ) + "\\src\\main\\resources\\";
	String workBookName = "FunctionalTestCases.xlsx";
	String sheetName = "ROCPreRequisites2";

	@org.testng.annotations.Test( priority = 1, description = "file source creation", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void fileSource() throws Exception
	{
		try
		{
			FileSourceHelper fileSrcObj = new FileSourceHelper();
			fileSrcObj.createFileSource( path, workBookName, sheetName, "FileSource", 1 );

		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 2, description = "file collection creation", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void fileCollection() throws Exception
	{
		try
		{
			FileCollectionHelper fileCollObj = new FileCollectionHelper();
			fileCollObj.createFileCollection( path, workBookName, sheetName, "FileCollection", 1 );

		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 3, description = "edit billing stream", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void editBillingstream() throws Exception
	{
		try
		{
			String partition = null;
			Streams streamObj = new Streams();
			streamObj.editStreamConfig( path, workBookName, sheetName, "Streams", 1 );
			streamObj.billingStreamConfig( path, workBookName, sheetName, "Credit", 1 );
			streamObj.saveStreamDetail();

		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 4, description = "edit Voice stream", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void editVoicestream() throws Exception
	{
		try
		{
			String partition = null;
			Streams streamObj = new Streams();
			streamObj.editStreamConfig( path, workBookName, sheetName, "VoiceStreams", 1 );
			streamObj.reaggregationStreamConfig( path, workBookName, sheetName, testCaseName, 1 );
			streamObj.saveStreamDetail();

		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 5, description = "edit Voice stream-carrierInvoice", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void editVoicestreamCarrierInvoice() throws Exception
	{
		try
		{
			String partition = null;
			Streams streamObj = new Streams();
			streamObj.editStreamConfig( path, workBookName, sheetName, "VoiceStreams", 1 );
			streamObj.carrierInvoiceStreamConfig( path, workBookName, sheetName, testCaseName, 1 );
			streamObj.saveStreamDetail();

		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

	/*@org.testng.annotations.Test( priority = 6, description = "add ratesheet stream stages" )
	public void editVoiceStream() throws Exception
	{
	
		try
		{
			Streams streamObj = new Streams();
			streamObj.editStreamConfig( path, workBookName, "Streams", "StreamsEdit", 1 );
			streamObj.rateSheetStreamConfig( path, workBookName, "Streams", testCaseName, 1 );
			streamObj.saveStreamDetail();
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}
	
	@org.testng.annotations.Test( priority = 7, description = "add prepayments stream stages" )
	public void editVoiceStreamPrepayments() throws Exception
	{
	
		try
		{
			Streams streamObj = new Streams();
			streamObj.editStreamConfig( path, workBookName, sheetName, "VoiceStreamsPrepayments", 1 );
			streamObj.prepaymentsStreamConfig( path, workBookName, sheetName, testCaseName, 1 );
			streamObj.saveStreamDetail();
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}
	*/
	@org.testng.annotations.Test( priority = 8, description = "Trigger creation", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void createTrigger() throws Exception
	{
		try
		{

			TriggerHelper triggerObj = new TriggerHelper();
			triggerObj.createTrigger( path, workBookName, sheetName, "Trigger", 1 );

		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 9, description = "Recurring task creation", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void createRecurringTask() throws Exception
	{
		try
		{

			RecurringTaskHelper triggerObj = new RecurringTaskHelper();
			triggerObj.createRecurringTask( path, workBookName, sheetName, "RecurringTask", 1 );

		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 10, enabled = true, description = "edit Voice stream add settlement stream stage", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void editVoicestreamForSetllement() throws Exception
	{
		try
		{
			String partition = null;
			Streams streamObj = new Streams();
			streamObj.editStreamConfig( path, workBookName, "SettlementsPrerequisite", "VoiceStreams", 1 );
			streamObj.settlementStreamConfig( path, workBookName, "SettlementsPrerequisite", "Settlements", 1 );
			streamObj.saveStreamDetail();

		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 11, enabled = true, description = "edit Voice stream add 'GenericBirtReportStream' stream stage", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void editVoiceStreamForGenBirtReport() throws Exception
	{
		try
		{
			String partition = null;
			Streams streamObj = new Streams();
			streamObj.editStreamConfig( path, workBookName, "ReportsAndExtracts", "VoiceStreams", 1 );
			streamObj.genBirtReportStreamConfig( path, workBookName, "ReportsAndExtracts", "GenericBirtReportStream", 1 );
			streamObj.saveStreamDetail();

		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 12, enabled = true, description = "edit Voice stream add Audit stream stage", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void editVoiceStreamForAudit() throws Exception
	{
		try
		{
			String partition = null;
			Streams streamObj = new Streams();
			streamObj.editStreamConfig( path, workBookName, "ReportsAndExtracts", "VoiceStreams", 1 );
			streamObj.auditStreamConfig( path, workBookName, "ReportsAndExtracts", "AuditStream", 1 );
			streamObj.saveStreamDetail();

		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 13, enabled = true, description = "edit Voice stream add Manual Measures stream stage", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void editVoiceStreamForManualMeasures() throws Exception
	{
		try
		{
			String partition = null;
			Streams streamObj = new Streams();
			streamObj.editStreamConfig( path, workBookName, "ReportsAndExtracts", "VoiceStreams", 1 );
			streamObj.manualMeasuresStreamConfig( path, workBookName, "ReportsAndExtracts", "ManualMeasuresStream", 1 );
			streamObj.saveStreamDetail();

		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 14, enabled = true, description = "edit Voice stream add Manual Measures stream stage", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void editVoiceStreamForBulkEntityExport() throws Exception
	{
		try
		{
			String partition = null;
			Streams streamObj = new Streams();
			streamObj.editStreamConfig( path, workBookName, "BulkEntityExportStream", "VoiceStreams", 1 );
			streamObj.bulkEntityExportStreamConfig( path, workBookName, "BulkEntityExportStream", "BulkStreamStage", 1 );
			streamObj.saveStreamDetail();

		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}
	
	@org.testng.annotations.Test( priority = 15, enabled = true, description = "edit Voice stream add Alert Task stream stage", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void editVoiceStreamForAlertTask() throws Exception
	{
		try
		{
			String partition = null;
			Streams streamObj = new Streams();
			streamObj.editStreamConfig( path, "FunctionalTestCases", "AlertStream", "VoiceStreams", 1 );
			streamObj.alertTaskStreamConfig( path, "FunctionalTestCases", "AlertStream", "AlertStreamStage", 1 );
			streamObj.saveStreamDetail();

		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}
	
	@org.testng.annotations.Test( priority = 16, description = "edit Voice stream-dealImport" )
	public void editVoicestreamDealImportExcelWriter() throws Exception
	{
		try
		{
			String partition = null;
			Streams streamObj = new Streams();
			streamObj.editStreamConfig( path, workBookName, sheetName, "VoiceStreams", 1 );
			streamObj.dealImportExcelWriterStreamConfig( path, workBookName, sheetName, testCaseName, 1 );
			streamObj.saveStreamDetail();

		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

}
