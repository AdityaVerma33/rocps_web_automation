package com.subex.rocps.automation.testcases.systemtesting;

import com.subex.rocps.automation.helpers.application.aggregation.AggregationConfiguration;
import com.subex.rocps.automation.helpers.application.aggregation.AggregationProcessor;
import com.subex.rocps.automation.helpers.application.matchandrate.EventModellingDefinition;
import com.subex.rocps.automation.helpers.application.matchandrate.EventModellingInstance;
import com.subex.rocps.automation.helpers.application.matchandrate.EventNormalization;
import com.subex.rocps.automation.helpers.application.matchandrate.EventType;
import com.subex.rocps.automation.helpers.application.referenceTable.AggrComponentMapping;
import com.subex.rocps.automation.helpers.application.system.Streams;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;

import com.subex.automation.helpers.application.ControllerHelper;
import com.subex.automation.helpers.application.screens.FileCollectionHelper;
import com.subex.automation.helpers.application.screens.FileSourceHelper;
import com.subex.automation.helpers.application.screens.RecurringTaskHelper;
import com.subex.automation.helpers.application.screens.StreamControllerHelper;
import com.subex.automation.helpers.application.screens.TriggerHelper;
import com.subex.automation.helpers.util.FailureHelper;

public class TCVoiceStream extends PSAcceptanceTest {
	String path = System.getProperty("user.dir") + "\\src\\main\\resources\\";
	String workBookName = "SystemTestCases.xlsx";
	String sheetName = "VoiceStream";

	@org.testng.annotations.Test(priority = 1, description = "edit eventModellingDefn", groups = {
			"Prerequisites3" }, retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class)
	public void editEventModellingDefn() throws Exception {
		try {

			EventModellingDefinition eventDefnObj = new EventModellingDefinition(path, workBookName, sheetName,
					"EventModellingDefn");
			eventDefnObj.modifyEventModellingDefn();

		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}

	@org.testng.annotations.Test(priority = 2, description = "edit EventModellingInst", groups = {
			"Prerequisites3" }, retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class,dependsOnMethods = {
			"editEventModellingDefn" })
	public void editEventModellingInst() throws Exception {
		try {

			EventModellingInstance eventInstObj = new EventModellingInstance(path, workBookName, sheetName,
					"EventModellingInst");
			eventInstObj.eventModellingInstance();

		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}

	@org.testng.annotations.Test(priority = 3, description = "edit event type", groups = {
			"Prerequisites3" }, retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class,dependsOnMethods = {
			"editEventModellingInst" })
	public void editEventtype() throws Exception {
		try {

			EventType eventObj = new EventType(path, workBookName, sheetName, "EventType");
			eventObj.eventTypeCreation();

		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}

	@org.testng.annotations.Test(priority = 4, description = "event normalization creation", groups = {
			"Prerequisites3" }, retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class,dependsOnMethods = {
			"editEventtype" })
	public void createEventNormalization() throws Exception {
		try {

			EventNormalization assignObj = new EventNormalization(path, workBookName, sheetName, "AssignComponent");
			assignObj.eventNormalizationCreation();

			EventNormalization routeObj = new EventNormalization(path, workBookName, sheetName, "RouteLookupComp");
			routeObj.eventNormalizationCreation();

			EventNormalization ruleObj = new EventNormalization(path, workBookName, sheetName, "RuleStringComponent");
			ruleObj.eventNormalizationCreation();

			EventNormalization currecnyObj = new EventNormalization(path, workBookName, sheetName,
					"CurrencyLookUpComponent");
			currecnyObj.eventNormalizationCreation();

		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}

	@org.testng.annotations.Test(priority = 5, description = "Aggreagation Configuration creation", groups = {
			"Prerequisites3" }, retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class,dependsOnMethods = {
			"createEventNormalization" })
	public void createAggregationConfig() throws Exception {
		try {
			AggregationConfiguration aggrConfigObj = new AggregationConfiguration(path, workBookName, sheetName,
					"AggregationConfiguration");
			aggrConfigObj.configureAggregation();
			aggrConfigObj.changeAggregationStatus();

		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}

	@org.testng.annotations.Test(priority = 6, description = "aggreagation processor creation", groups = {
			"Prerequisites3" }, retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class,dependsOnMethods = {
			"createAggregationConfig" })
	public void createAggregationProcessor() throws Exception {
		try {
			AggregationProcessor aggrObj = new AggregationProcessor(path, workBookName, sheetName,
					"AggregationProcessor", 1);
			aggrObj.aggregationProcessorCreation();

		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}

	/*
	 * @org.testng.annotations.Test(priority = 7, description = "Streams creation",
	 * groups = { "Prerequisites3" }, retryAnalyzer =
	 * com.subex.rocps.automation.helpers.listener.Retry.class,dependsOnMethods = {
	 * "createAggregationProcessor" }) public void createStream() throws Exception {
	 * // String partition = null;
	 * 
	 * try { Streams streamObj = new Streams(); streamObj.newStreamConfig(path,
	 * workBookName, sheetName, "Streams", 1); streamObj.voiceStreamNewConfig(path,
	 * workBookName, sheetName, testCaseName, 1); streamObj.saveStreamDetail(); }
	 * catch (Exception e) { FailureHelper.setErrorMessage(e); throw e; }
	 * 
	 * try {
	 * 
	 * Streams streamObj = new Streams(); if (!streamObj.newStreamConfig(path,
	 * workBookName, sheetName, "Streams", 1)) {
	 * streamObj.voiceStreamNewConfig(path, workBookName, sheetName, testCaseName,
	 * 1); streamObj.saveStreamDetail(); }
	 * 
	 * } catch (Exception e) { FailureHelper.setErrorMessage(e); throw e; }
	 * 
	 * }
	 */

	@org.testng.annotations.Test(priority = 8, description = "file source creation", groups = {
			"Prerequisites3" }, retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class,dependsOnMethods = {
			"createAggregationProcessor" })
	public void fileSource() throws Exception {
		try {
			FileSourceHelper fileSrcObj = new FileSourceHelper();
			fileSrcObj.createFileSource(path, workBookName, sheetName, "FileSource", 1);

		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}

	@org.testng.annotations.Test(priority = 9, description = "file collection creation", groups = {
			"Prerequisites3" }, retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class,dependsOnMethods = {
			"fileSource" })
	public void fileCollection() throws Exception {
		try {
			FileCollectionHelper fileCollObj = new FileCollectionHelper();
			fileCollObj.createFileCollection(path, workBookName, sheetName, "FileCollection", 1);

		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}

	@org.testng.annotations.Test(priority = 10, description = "Trigger creation", groups = {
			"Prerequisites3" }, retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class,dependsOnMethods = {
			"fileCollection" })
	public void createTrigger() throws Exception {
		try {

			TriggerHelper triggerObj = new TriggerHelper();
			triggerObj.createTrigger(path, workBookName, sheetName, "Trigger", 1);

		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}

	@org.testng.annotations.Test(priority = 11, description = "Trigger creation", groups = {
			"Prerequisites3" }, retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class,dependsOnMethods = {
			"createTrigger" })
	public void createRecurringTask() throws Exception {
		try {

			RecurringTaskHelper triggerObj = new RecurringTaskHelper();
			triggerObj.createRecurringTask(path, workBookName, sheetName, "RecurringTask", 1);

		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}

	@org.testng.annotations.Test(priority = 12, description = "aggreagation component mapping creation", groups = {
			"Prerequisites3" }, retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class,dependsOnMethods = {
			"createRecurringTask" })
	public void createAggreagtionCompMapping() throws Exception {
		try {
			AggrComponentMapping aggrCompObj = new AggrComponentMapping(path, workBookName, sheetName,
					"AggregationComponentMapping");
			aggrCompObj.aggrComponentMappingCreation();

		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}

}
