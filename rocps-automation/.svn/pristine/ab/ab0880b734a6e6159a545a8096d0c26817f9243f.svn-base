package com.subex.rocps.automation.testcases.functionaltesting;

import com.subex.automation.helpers.application.screens.BandHelper;
import com.subex.automation.helpers.application.screens.ElementCreateHelper;
import com.subex.automation.helpers.application.screens.FileCollectionHelper;
import com.subex.automation.helpers.application.screens.FileSourceHelper;
import com.subex.automation.helpers.application.screens.RecurringTaskHelper;
import com.subex.automation.helpers.application.screens.TariffClassHelper;
import com.subex.automation.helpers.application.screens.TariffHelper;
import com.subex.automation.helpers.application.screens.TriggerHelper;
import com.subex.automation.helpers.util.FailureHelper;
import com.subex.rocps.automation.helpers.application.aggregation.AggregationConfiguration;
import com.subex.rocps.automation.helpers.application.aggregation.AggregationProcessor;
import com.subex.rocps.automation.helpers.application.matchandrate.EventMatchRule;
import com.subex.rocps.automation.helpers.application.matchandrate.EventMatchRuleGroup;
import com.subex.rocps.automation.helpers.application.matchandrate.EventModellingDefinition;
import com.subex.rocps.automation.helpers.application.matchandrate.EventModellingInstance;
import com.subex.rocps.automation.helpers.application.matchandrate.EventType;
import com.subex.rocps.automation.helpers.application.matchandrate.Operator;
import com.subex.rocps.automation.helpers.application.networkConfiguraiton.Route;
import com.subex.rocps.automation.helpers.application.networkConfiguraiton.RouteGroup;
import com.subex.rocps.automation.helpers.application.networkConfiguraiton.Switch;
import com.subex.rocps.automation.helpers.application.partnerConfiguration.Account;
import com.subex.rocps.automation.helpers.application.partnerConfiguration.Agent;
import com.subex.rocps.automation.helpers.application.partnerConfiguration.BillProfile;
import com.subex.rocps.automation.helpers.application.referenceTable.AggrComponentMapping;
import com.subex.rocps.automation.helpers.application.system.Streams;
import com.subex.rocps.automation.helpers.application.tariffs.PSTariffHelper;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;

public class TCVoiceSurchargePrerequisite extends PSAcceptanceTest
{
	String path = System.getProperty( "user.dir" ) + "\\src\\main\\resources\\";
	String workBookName = "FunctionalTestCases.xlsx";
	String sheetName = "SurchargePrerequisite";

	@org.testng.annotations.Test( priority = 1, description = "Voice-surcharge Prerequisite for Event Type, Event modelling Dfn, Event modelling instance", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void voiceSurchargePrerequisite1() throws Exception
	{
		try
		{

			EventType eventObj = new EventType( path, workBookName, sheetName, "EventType", 1 );
			eventObj.eventTypeCreation();

			EventModellingDefinition eventeditObj = new EventModellingDefinition( path, workBookName, sheetName, "EventModellingDefnEdit", 1 );
			eventeditObj.modifyEventModellingDefn();

			EventModellingDefinition eventviewObj = new EventModellingDefinition( path, workBookName, sheetName, "EventModellingDefnView", 1 );
			eventviewObj.modifyEventModellingDefn();

			EventModellingInstance eventundelObj = new EventModellingInstance( path, workBookName, sheetName, "EventModellingInst", 1 );
			eventundelObj.eventModellingInstance();

		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 2, description = "aggregation Configuration creation for VoideSurcharge With Rating Component", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void aggregationConfigurationCreationVoideSurcharge() throws Exception
	{
		try
		{
			AggregationConfiguration aggrObj = new AggregationConfiguration( path, workBookName, "SurchargePrerequisite", "AggregationConfigurationVoiceSurcharge", 1 );
			aggrObj.configureAggregation();
			aggrObj.changeAggregationStatus();
		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 3, description = "aggregation processor  creation for VoiceSurcharge", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void aggregationProcessorVoiceSurcharge() throws Exception
	{

		try
		{
			AggregationProcessor aggObj = new AggregationProcessor( path, workBookName, "SurchargePrerequisite", "AggregationProcessorVoiceSurcharge", 1 );
			aggObj.aggregationProcessorCreation();
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 4, description = "Streams creation for VoiceSurcharge", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void createStreamForVoiceSurcharge() throws Exception
	{
		try
		{

			Streams streamObj = new Streams();
			if ( !streamObj.newStreamConfig( path, workBookName, sheetName, "Streams", 1 ) )
			{
				streamObj.voiceSurchargeStreamNewConfig( path, workBookName, sheetName, testCaseName, 1 );
				streamObj.saveStreamDetail();
			}

		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 5, enabled = true, description = " Voice Surcharge stream stages creation, Parse, MNR", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void createVoiceSurchargeStreamStages() throws Exception
	{
		try
		{

			Streams streamObj = new Streams();
			streamObj.editStreamConfig( path, workBookName, sheetName, "Streams", 1 );
			streamObj.parseStreamStageConfig( path, workBookName, sheetName, "VoiceSurchargeParse", 1 );
			streamObj.mnrStreamStageConfig( path, workBookName, sheetName, "VoiceSurchargeMnr", 1 );
			streamObj.saveStreamDetail();

		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 6, description = "aggrComponentMapping creation for Voice Surcharge" ,retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class)
	public void aggrComponentMappingCreationVoiceSurcharge() throws Exception
	{
		try
		{
			AggrComponentMapping aggrObj = new AggrComponentMapping( path, workBookName, sheetName, "VoiceSurchargeAggrCompMapping", 1 );
			aggrObj.aggrComponentMappingCreation();
		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 7, description = "Trigger creation for VoiceSurcharge",retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void createTriggerVoiceSurcharge() throws Exception
	{
		try
		{

			TriggerHelper triggerObj = new TriggerHelper();
			triggerObj.createTrigger( path, workBookName, sheetName, "VoiceSurchargeTrigger", 1 );

		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 8, description = "Recurring task creation Voice Surcharge",retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void createRecurringTaskVoiceSurcharge() throws Exception
	{
		try
		{

			RecurringTaskHelper triggerObj = new RecurringTaskHelper();
			triggerObj.createRecurringTask( path, workBookName, sheetName, "VoiceSurchargeRecurringTask", 1 );

		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 9, description = "file source creation FOR Rap In", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void fileSourceVoiceSurcharge() throws Exception
	{
		try
		{
			FileSourceHelper fileSrcObj = new FileSourceHelper();
			fileSrcObj.createFileSource( path, workBookName, sheetName, "VoiceSurchargeFileSource", 1 );

		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 10, description = "file collection creation for VoiceSurcharge", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void fileCollectionVoiceSurcharge() throws Exception
	{
		try
		{
			FileCollectionHelper fileCollObj = new FileCollectionHelper();
			fileCollObj.createFileCollection( path, workBookName, sheetName, "VoiceSurchargeFileCollection", 1 );

		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}



}
