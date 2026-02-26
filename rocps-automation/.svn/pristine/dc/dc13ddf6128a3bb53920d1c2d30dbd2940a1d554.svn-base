package com.subex.rocps.automation.testcases.functionaltesting;

import java.io.File;

import com.subex.automation.helpers.application.screens.FileCollectionHelper;
import com.subex.automation.helpers.application.screens.FileSourceHelper;
import com.subex.automation.helpers.application.screens.RecurringTaskHelper;
import com.subex.automation.helpers.application.screens.TriggerHelper;
import com.subex.automation.helpers.file.CopyFile;
import com.subex.automation.helpers.report.Log4jHelper;
import com.subex.automation.helpers.util.FailureHelper;
import com.subex.rocps.automation.helpers.application.aggregation.AggregationConfiguration;
import com.subex.rocps.automation.helpers.application.aggregation.AggregationProcessor;
import com.subex.rocps.automation.helpers.application.matchandrate.EventModellingDefinition;
import com.subex.rocps.automation.helpers.application.matchandrate.EventModellingInstance;
import com.subex.rocps.automation.helpers.application.matchandrate.EventType;
import com.subex.rocps.automation.helpers.application.referenceTable.AggrComponentMapping;
import com.subex.rocps.automation.helpers.application.system.Streams;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;

public class TCVoiceROPrerequisite extends PSAcceptanceTest
{
	String path = System.getProperty( "user.dir" ) + "\\src\\main\\resources\\";
	String workBookName = "FunctionalTestCasesRO.xlsx";
	String sheetName = "RO_Prerequisites";

	/*ROC Prerequisite for Route Optimization*/

	@org.testng.annotations.Test( priority = 1, description = "copy 'RateSheetImportStatusAlert' folder and Diamond Folder" )
	public void copyRateSheetImportStatusAlertAndDiamondFile() throws Exception
	{
		try
		{

			String diamondDirPath = configProp.getDataDirPath() + "\\Diamond";
			String diamondSrcPath = automationPath + "\\src\\main\\resources\\Diamond";
			String rateSheetImportStatusAlertDirPath = configProp.getDataDirPath() + "\\RateSheetImportStatusAlert";
			String rateSheetImportStatusAlertSrcPath = automationPath + "\\src\\main\\resources\\RateSheetImportStatusAlert";
			File diamondDir = new File( diamondDirPath );
			File diamondSrc = new File( diamondSrcPath );
			File rateSheetImportStatusAlertSrc = new File( rateSheetImportStatusAlertSrcPath );
			File rateSheetImportStatusAlertDest = new File( rateSheetImportStatusAlertDirPath );
			CopyFile.copyFile( diamondSrc, diamondDir );
			Log4jHelper.logInfo( "Diamond folder is successfully copied" );
			CopyFile.copyFile( rateSheetImportStatusAlertSrc, rateSheetImportStatusAlertDest );
			Log4jHelper.logInfo( "'RateSheetImportStatusAlert' folder is successfully copied" );

		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 2, description = "Voice-RO Prerequisite for Event Type, Event modelling Dfn", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void VoiceROPrerequisite2() throws Exception
	{
		try
		{

			EventType eventObj = new EventType( path, workBookName, sheetName, "EventType", 1 );
			eventObj.eventTypeCreation();

			EventModellingDefinition eventeditObj = new EventModellingDefinition( path, workBookName, sheetName, "EventModellingDefnEdit", 1 );
			eventeditObj.modifyEventModellingDefn();

			EventModellingDefinition eventviewObj = new EventModellingDefinition( path, workBookName, sheetName, "EventModellingDefnView", 1 );
			eventviewObj.modifyEventModellingDefn();

		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 3, description = "Voice-RO Prerequisite  Event modelling instance", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void VoiceROPrerequisite3_EveModInstance() throws Exception
	{
		try
		{

			EventModellingInstance eventundelObj = new EventModellingInstance( path, workBookName, sheetName, "EventModellingInst", 1 );
			eventundelObj.eventModellingInstance();

		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 4, description = "aggregation Configuration creation for Voice-RO Without Rating Component", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void aggregationConfigurationCreationVoiceRO() throws Exception
	{
		try
		{
			AggregationConfiguration aggrObj = new AggregationConfiguration( path, workBookName, sheetName, "AggregationConfiguration-RO", 1 );
			aggrObj.configureAggregation();
			aggrObj.changeAggregationStatus();
		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 5, description = "aggregation processor  creation for Voice-RO", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void aggregationProcessorVoiceRO() throws Exception
	{

		try
		{
			AggregationProcessor aggObj = new AggregationProcessor( path, workBookName, sheetName, "AggregationProcessor-RO", 1 );
			aggObj.aggregationProcessorCreation();
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 6, description = "Streams creation for Voice-RO", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void createStreamForVoiceRO() throws Exception
	{
		try
		{

			Streams streamObj = new Streams();
			if ( !streamObj.newStreamConfig( path, workBookName, sheetName, "Streams", 1 ) )
			{
				streamObj.voiceROStreamNewConfig( path, workBookName, sheetName, testCaseName, 1 );
				streamObj.saveStreamDetail();
			}

		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 7, description = "add rerate stream stages", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void editVoiceROStreamRerate() throws Exception
	{

		try
		{
			Streams streamObj = new Streams();
			if ( streamObj.editStreamConfig( path, workBookName, sheetName, "StreamsVoice", 1 ) )
			{
				streamObj.rerateStreamConfig( path, workBookName, sheetName, testCaseName, 1 );
				streamObj.saveStreamDetail();
			}
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 8, description = "add ratesheet stream stages" )
	public void editVoiceSROtreamRatesheet() throws Exception
	{

		try
		{
			Streams streamObj = new Streams();
			streamObj.editStreamConfig( path, workBookName, sheetName, "StreamsVoice", 1 );
			streamObj.rateSheetStreamConfig( path, workBookName, sheetName, testCaseName, 1 );
			streamObj.saveStreamDetail();
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 9, description = "add BCR G stream stages" )
	public void editVoiceROBCRGenRoutingRateCalcStream() throws Exception
	{

		try
		{
			Streams streamObj = new Streams();
			streamObj.editStreamConfig( path, workBookName, sheetName, "Streams", 1 );
			streamObj.voiceROBCRGenRoutingRateCalcStream( path, workBookName, sheetName, testCaseName, 1 );
			streamObj.saveStreamDetail();
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 10, description = "aggrComponentMapping creation for Voice-RO", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void aggrComponentMappingCreation() throws Exception
	{
		try
		{
			AggrComponentMapping aggrObj = new AggrComponentMapping( path, workBookName, sheetName, "AggrCompMapping", 1 );
			aggrObj.aggrComponentMappingCreation();
		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 11, description = "Trigger creation for Voice-RO" )
	public void createTriggerVoiceRO() throws Exception
	{
		try
		{

			TriggerHelper triggerObj = new TriggerHelper();
			triggerObj.createTrigger( path, workBookName, sheetName, "Trigger_VoiceRO", 1 );

		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 12, description = "Trigger creation Rerate", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void createTriggerRerate() throws Exception
	{
		try
		{

			TriggerHelper triggerObj = new TriggerHelper();
			triggerObj.createTrigger( path, workBookName, sheetName, "Rerate Trigger", 1 );

		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 13, description = "Recurring task creation VoiceRO" )
	public void createRecurringTaskVoiceRO() throws Exception
	{
		try
		{

			RecurringTaskHelper triggerObj = new RecurringTaskHelper();
			triggerObj.createRecurringTask( path, workBookName, sheetName, "RecurringTaskVoiceRO", 1 );

		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 14, description = "Recurring task creation BCR Generation and Routing Rate" )
	public void createRecurringTaskBCRGenRoutingRate() throws Exception
	{
		try
		{

			RecurringTaskHelper triggerObj = new RecurringTaskHelper();
			triggerObj.createRecurringTask( path, workBookName, sheetName, "RecurringTask_BCRGenerationRoutingRate", 1 );

		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 15, description = "file source creation Voice RO  ", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void fileSourceVoiceRO() throws Exception
	{
		try
		{
			FileSourceHelper fileSrcObj = new FileSourceHelper();
			fileSrcObj.createFileSource( path, workBookName, sheetName, "FileSource_VoiceRO", 1 );

		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 16, description = "file collection creation Voice RO  ", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void fileCollectionVoiceRO() throws Exception
	{
		try
		{
			FileCollectionHelper fileCollObj = new FileCollectionHelper();
			fileCollObj.createFileCollection( path, workBookName, sheetName, "FileCollection_VoiceRO", 1 );

		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}
}
