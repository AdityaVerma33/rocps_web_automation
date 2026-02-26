package com.subex.rocps.automation.testcases.functionaltesting;

import java.io.File;

import com.subex.rocps.automation.helpers.application.aggregation.AggregationConfiguration;
import com.subex.rocps.automation.helpers.application.aggregation.AggregationProcessor;
import com.subex.rocps.automation.helpers.application.matchandrate.EventModellingDefinition;
import com.subex.rocps.automation.helpers.application.matchandrate.EventModellingInstance;
import com.subex.rocps.automation.helpers.application.matchandrate.EventType;
import com.subex.rocps.automation.helpers.application.referenceTable.AggrComponentMapping;
import com.subex.rocps.automation.helpers.application.system.PSUsageGroupHelper;
import com.subex.rocps.automation.helpers.application.system.Streams;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;

import com.subex.automation.helpers.application.ReferenceTableHelper;
import com.subex.automation.helpers.application.screens.FileCollectionHelper;
import com.subex.automation.helpers.application.screens.FileSourceHelper;
import com.subex.automation.helpers.application.screens.RecurringTaskHelper;
import com.subex.automation.helpers.application.screens.TriggerHelper;
import com.subex.automation.helpers.application.screens.UsageGroupHelper;
import com.subex.automation.helpers.file.CopyFile;
import com.subex.automation.helpers.report.Log4jHelper;
import com.subex.automation.helpers.util.FailureHelper;

public class TCRoamingPrerequisite extends PSAcceptanceTest
{

	String path = System.getProperty( "user.dir" ) + "\\src\\main\\resources\\";
	String workBookName = "FunctionalTestCases.xlsx";
	String sheetName = "RoamingPrerequisite";

	/*ROC Prerequiste for Roaming*/

	@org.testng.annotations.Test( priority = 1, description = "copy 'TAPIN_Parse' folder and Diamond Folder" )
	public void copyTapInParseAndDiamondFile() throws Exception
	{
		try
		{

			String tapInParseDirPath = configProp.getDataDirPath() + "\\TAPIN_Parse";
			String tapInParseSrcPath = automationPath + "\\src\\main\\resources\\TAPIN_Parse";
			String diamondDirPath = configProp.getDataDirPath() + "\\Diamond";
			String diamondSrcPath = automationPath + "\\src\\main\\resources\\Diamond";
			File tapInParseSrc = new File( tapInParseSrcPath );
			File tapInParseDest = new File( tapInParseDirPath );
			File diamondDir = new File( diamondDirPath );
			File diamondSrc = new File( diamondSrcPath );

			CopyFile.copyFile( tapInParseSrc, tapInParseDest );
			Log4jHelper.logInfo( "'TAPIN_Parse' folder is successfully copied" );
			CopyFile.copyFile( diamondSrc, diamondDir );
			Log4jHelper.logInfo( "Diamond folder is successfully copied" );

		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 2, description = "usage group creation for Roaming", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void createUsageGroup() throws Exception
	{
		try
		{
			PSUsageGroupHelper obj = new PSUsageGroupHelper();
			obj.editUsageGroup( path, workBookName, sheetName, "UsageGroup", 1 );

		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 3, description = "attach usage server for Roaming", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void attachUsageServer() throws Exception
	{
		try
		{
			UsageGroupHelper obj = new UsageGroupHelper();
			obj.attachUsageServer( path, workBookName, sheetName, "AttachUsageServer", 1 );

		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 4, description = "schema creation for Roaming", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void schema() throws Exception
	{
		try
		{
			ReferenceTableHelper obj = new ReferenceTableHelper();
			obj.schema( path, workBookName, sheetName, "Schema", 1 );

		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 5,invocationCount = 2, description = "Roaming Prerequisite for Event Type, Event modelling Dfn" , retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class)
	public void roamingPrerequisite2() throws Exception
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
	@org.testng.annotations.Test( priority = 6, description = "Roaming Prerequisite  Event modelling instance" , retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class)
	public void roamingPrerequisite3_EveModInstance() throws Exception
	{
		try
		{


			EventModellingInstance eventundelObj = new EventModellingInstance( path, workBookName, sheetName, "EventModellingInst", 1 );
			eventundelObj.eventModellingInstance();

			EventModellingInstance eventundelObjAct = new EventModellingInstance( path, workBookName, sheetName, "EventModellingInst_MapAdditionalFields", 1 );
			eventundelObjAct.eventModInstMapAdditionalFieldAction();

		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 7, description = "aggregation Configuration creation for Roaming Without Rating Component", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void aggregationConfigurationCreationRoaming() throws Exception
	{
		try
		{
			AggregationConfiguration aggrObj = new AggregationConfiguration( path, workBookName, "AggregationConfiguration", "AggregationConfigurationRoaming", 1 );
			aggrObj.configureAggregation();
			aggrObj.changeAggregationStatus();
		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 8, description = "aggregation Configuration creation for Roaming With 'Roaming Aggregation Rating Component'", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void aggregationConfigCreationWithRatingComp() throws Exception
	{
		try
		{
			AggregationConfiguration aggrObj = new AggregationConfiguration( path, workBookName, "AggregationConfiguration", "AggregationConfigurationRoamingWithRating", 1 );
			aggrObj.configureAggregation();
			aggrObj.changeAggregationStatus();
		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 9, description = "aggregation processor  creation for Roaming" , retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class)
	public void aggregationProcessorRoaming() throws Exception
	{

		try
		{
			AggregationProcessor aggObj = new AggregationProcessor( path, workBookName, "AggregationProcessor", "AggregationProcessor_Roaming", 1 );
			aggObj.aggregationProcessorCreation();
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 10, description = "aggregation processor  creation for Roaming for HUR" )
	public void aggregationProcessorRoamingHUR() throws Exception
	{

		try
		{
			AggregationProcessor aggObj = new AggregationProcessor( path, workBookName, "AggregationProcessor", "AggregationProcessor_RoamingHUR", 1 );
			aggObj.aggregationProcessorCreation();
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 11, description = "Streams creation for Roaming", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void createStreamForRoaming() throws Exception
	{
		try
		{

			Streams streamObj = new Streams();
			if ( !streamObj.newStreamConfig( path, workBookName, sheetName, "Streams", 1 ) )
			{
				streamObj.roamingStreamNewConfig( path, workBookName, sheetName, testCaseName, 1 );
				streamObj.saveStreamDetail();
			}

		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 12, enabled = true, description = " Roaming stream creation for RAP", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void createRoamingStreamForRap() throws Exception
	{
		try
		{

			Streams streamObj = new Streams();
			streamObj.editStreamConfig( path, workBookName, sheetName, "Streams", 1 );
			streamObj.roamingRapInParseStreamConfig( path, workBookName, sheetName, "Roaming_RapIn_Parse", 1 );
			streamObj.rapAcknowledgementTaskStreamConfig( path, workBookName, sheetName, "RapAcknowledgementTaskStream", 1 );
			streamObj.rapInStreamConfig( path, workBookName, sheetName, "RapInStream", 1 );
			streamObj.rapOutStreamConfig( path, workBookName, sheetName, "RapOutStream", 1 );
			streamObj.saveStreamDetail();

		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 13, enabled = true, description = " Roaming stream creation for TAP", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void createRoamingStreamForTap() throws Exception
	{
		try
		{

			Streams streamObj = new Streams();
			streamObj.editStreamConfig( path, workBookName, sheetName, "Streams", 1 );
			streamObj.tapInParseStreamConfig( path, workBookName, sheetName, "TAP IN Parse", 1 );
			streamObj.tapInStreamConfig( path, workBookName, sheetName, "TapInStream", 1 );
			streamObj.tapInDataLoadStreamConfig( path, workBookName, sheetName, "TapINDataLoads", 1 );
			//streamObj.tapNotificationStreamConfig( path, workBookName, sheetName, "TapNotificationStream", 1 );

			streamObj.parseStreamStageConfig( path, workBookName, sheetName, "Roaming_TapOut_Parse", 1 );
			streamObj.roamingMNRStreamConfig( path, workBookName, sheetName, "RoamingMNR", 1 );
			streamObj.tapOutStreamConfig( path, workBookName, sheetName, "TapOutStream", 1 );
			streamObj.tapOutMasterRequestStreamConfig( path, workBookName, sheetName, "TapOutMasterRequestStream", 1 );
			streamObj.saveStreamDetail();

		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 14, enabled = true, description = " Roaming stream creation for NRTRDE", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void createRoamingStreamForNRTRDE() throws Exception
	{
		try
		{

			Streams streamObj = new Streams();
			streamObj.editStreamConfig( path, workBookName, sheetName, "Streams", 1 );
			streamObj.parseStreamStageConfig( path, workBookName, sheetName, "Roaming_NRTRDE_Parse", 1 );

			streamObj.nRTRDEErrorReportStreamConfig( path, workBookName, sheetName, "NRTRDEErrorReportStream", 1 );
			streamObj.nRTRDEFileDeliveryReportStreamConfig( path, workBookName, sheetName, "NRTRDEFileDeliveryReportStream", 1 );
			streamObj.nRTRDEFileGeneratorStreamConfig( path, workBookName, sheetName, "NRTRDEFileGeneratorStream", 1 );
			streamObj.nRTRDEMasterRequestStreamConfig( path, workBookName, sheetName, "NRTRDEMasterRequestStream", 1 );

			streamObj.saveStreamDetail();

		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 15, enabled = true, description = " Roaming stream creation for HUR", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void createRoamingStreamForHUR() throws Exception
	{
		try
		{

			Streams streamObj = new Streams();
			streamObj.editStreamConfig( path, workBookName, sheetName, "Streams", 1 );
			streamObj.hurFileGenerationStreamConfig( path, workBookName, sheetName, "HurFileGenerationStream", 1 );
			streamObj.hurMasterRequestStreamConfig( path, workBookName, sheetName, "HurMasterRequestStream", 1 );
			streamObj.emailStreamStageConfig( path, workBookName, sheetName, "EmailStreamStageRoaming", 1 );
			streamObj.saveStreamDetail();

		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 16, description = "Creat Master Task, Aggregation Stream stages creation for Roaming HUR", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void createMasterTaskAggForRoamingHur() throws Exception
	{
		try
		{

			Streams streamObj = new Streams();
			streamObj.editStreamConfig( path, workBookName, sheetName, "Streams", 1 );
			streamObj.createMasterTaskAggStreamStageHur( path, workBookName, sheetName, testCaseName, 1 );
			streamObj.saveStreamDetail();

		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 17, description = "aggrComponentMapping creation for Roaming HUR" )
	public void aggrComponentMappingCreationHurRoaming() throws Exception
	{
		try
		{
			AggrComponentMapping aggrObj = new AggrComponentMapping( path, workBookName, sheetName, "AggrCompMappingHUR", 1 );
			aggrObj.aggrComponentMappingCreation();
		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 18, description = "aggrComponentMapping creation for Roaming" , retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class)
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

	@org.testng.annotations.Test( priority = 19, description = "Trigger creation for TAP In" )
	public void createTriggerTapIn() throws Exception
	{
		try
		{

			TriggerHelper triggerObj = new TriggerHelper();
			triggerObj.createTrigger( path, workBookName, sheetName, "Trigger_TapIn", 1 );

		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 20, description = "file source creation for Tap In", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void fileSourceTapIn() throws Exception
	{
		try
		{
			FileSourceHelper fileSrcObj = new FileSourceHelper();
			fileSrcObj.createFileSource( path, workBookName, sheetName, "FileSource_TapIn", 1 );

		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 21, description = "file collection creation Tap In", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void fileCollectionTapIn() throws Exception
	{
		try
		{
			FileCollectionHelper fileCollObj = new FileCollectionHelper();
			fileCollObj.createFileCollection( path, workBookName, sheetName, "FileCollection_TapIn", 1 );

		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 22, description = "Trigger creation for TAP Out" )
	public void createTriggerTapOut() throws Exception
	{
		try
		{

			TriggerHelper triggerObj = new TriggerHelper();
			triggerObj.createTrigger( path, workBookName, sheetName, "Trigger_TapOut", 1 );

		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 23, description = "Recurring task creation TapOut" )
	public void createRecurringTaskTapOut() throws Exception
	{
		try
		{

			RecurringTaskHelper triggerObj = new RecurringTaskHelper();
			triggerObj.createRecurringTask( path, workBookName, sheetName, "RecurringTask_TapOut", 1 );

		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 24, description = "file source creation Tap Out ", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void fileSourceTapOut() throws Exception
	{
		try
		{
			FileSourceHelper fileSrcObj = new FileSourceHelper();
			fileSrcObj.createFileSource( path, workBookName, sheetName, "FileSource_TapOut", 1 );

		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 25, description = "file collection creation Tap Out ", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void fileCollectionTapOut() throws Exception
	{
		try
		{
			FileCollectionHelper fileCollObj = new FileCollectionHelper();
			fileCollObj.createFileCollection( path, workBookName, sheetName, "FileCollection_TapOut", 1 );

		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 26, description = "Trigger creation for RAP In" )
	public void createTriggerRapIn() throws Exception
	{
		try
		{

			TriggerHelper triggerObj = new TriggerHelper();
			triggerObj.createTrigger( path, workBookName, sheetName, "Trigger_RapIn", 1 );

		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 27, description = "file source creation FOR Rap In", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void fileSourceRapIn() throws Exception
	{
		try
		{
			FileSourceHelper fileSrcObj = new FileSourceHelper();
			fileSrcObj.createFileSource( path, workBookName, sheetName, "FileSource_RapIn", 1 );

		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 28, description = "file collection creation for RapIn", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void fileCollectionRapIn() throws Exception
	{
		try
		{
			FileCollectionHelper fileCollObj = new FileCollectionHelper();
			fileCollObj.createFileCollection( path, workBookName, sheetName, "FileCollection_RapIn", 1 );

		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 29, description = "Recurring task creation HurOut" )
	public void createRecurringTaskHurOut() throws Exception
	{
		try
		{

			RecurringTaskHelper triggerObj = new RecurringTaskHelper();
			triggerObj.createRecurringTask( path, workBookName, sheetName, "RecurringTask_HurMasterRequest", 1 );

		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 30, description = "Trigger creation for NRTRDE Out" )
	public void createTriggerNRTRDEOut() throws Exception
	{
		try
		{

			TriggerHelper triggerObj = new TriggerHelper();
			triggerObj.createTrigger( path, workBookName, sheetName, "Trigger_NrtrdeOut", 1 );

		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 31, description = "Recurring task creation NRTRDEOut" )
	public void createRecurringTaskNRTRDEOut() throws Exception
	{
		try
		{

			RecurringTaskHelper triggerObj = new RecurringTaskHelper();
			triggerObj.createRecurringTask( path, workBookName, sheetName, "RecurringTask_NRTRDEMasterRequest", 1 );

		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 32, description = "file source creation NRTRDE Out ", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void fileSourceNRTRDEOut() throws Exception
	{
		try
		{
			FileSourceHelper fileSrcObj = new FileSourceHelper();
			fileSrcObj.createFileSource( path, workBookName, sheetName, "FileSource_NRTRDEOut", 1 );

		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 33, description = "file collection creation Tap Out ", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void fileCollectionNRTRDEOut() throws Exception
	{
		try
		{
			FileCollectionHelper fileCollObj = new FileCollectionHelper();
			fileCollObj.createFileCollection( path, workBookName, sheetName, "FileCollection_NRTRDEOut", 1 );

		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

}
