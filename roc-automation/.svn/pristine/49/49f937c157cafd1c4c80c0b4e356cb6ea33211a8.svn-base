package com.subex.automation.testcases.regressiontesting.etl.etl_issues;

import org.testng.annotations.Test;

import com.subex.automation.helpers.application.ControllerHelper;
import com.subex.automation.helpers.application.ReferenceTableHelper;
import com.subex.automation.helpers.application.screens.JumpToSearchHelper;
import com.subex.automation.helpers.application.screens.RecurringTaskHelper;
import com.subex.automation.helpers.application.screens.StreamControllerHelper;
import com.subex.automation.helpers.application.screens.StreamHelper;
import com.subex.automation.helpers.application.screens.TableDefinitionHelper;
import com.subex.automation.helpers.application.screens.TableInstanceHelper;
import com.subex.automation.helpers.application.screens.TaskControllerHelper;
import com.subex.automation.helpers.application.screens.TaskSearchHelper;
import com.subex.automation.helpers.application.screens.TriggerHelper;
import com.subex.automation.helpers.application.screens.UsageGroupHelper;
import com.subex.automation.helpers.component.FileHelper;
import com.subex.automation.helpers.report.Log4jHelper;
import com.subex.automation.helpers.selenium.ROCAcceptanceTest;
import com.subex.automation.helpers.util.FailureHelper;

public class testDatabaseParse extends ROCAcceptanceTest {
	
	String path = null;
	String dataDir = null;
	final String fileName = "ETLIssues_TestData.xlsx";
	final String sheetName = "DatabaseParse";
	final String folderName = "DatabaseParse";
	final String dataLocation = "\\src\\main\\resources\\Data\\Database_Parse\\";
	
	public testDatabaseParse() throws Exception {
		try {
			path = automationPath + "\\src\\main\\resources\\Issues\\";
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	@Test(priority=13, description="Create Usage Group", groups = { "prerequisite" })
	public void createUsageGroup() throws Exception {
		try {
			Log4jHelper.logInfo("Running Database Parse ETL Flow");
			ControllerHelper controller = new ControllerHelper();
			controller.stopServices();
			
			ReferenceTableHelper referenceTable = new ReferenceTableHelper();
			referenceTable.dataSourceLocation(path, fileName, sheetName, "DSL", 1);
			referenceTable.dataSourceConnection(path, fileName, sheetName, "DSC", 1);
			referenceTable.usageServer(path, fileName, sheetName, "UsageServer", 1);
			
			UsageGroupHelper usageGroup = new UsageGroupHelper();
			usageGroup.createUsageGroup(path, fileName, sheetName, "UsageGroup", 1);
			referenceTable.schema(path, fileName, sheetName, "Schema", 1);
			usageGroup.attachUsageServer(path, fileName, sheetName, "AttachUsageServer", 1);
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	@Test(priority=14, description="Create Table Definition", groups = { "prerequisite" })
	public void createTDTI() throws Exception {
		try {
			TableDefinitionHelper tableDefinition = new TableDefinitionHelper();
			tableDefinition.importFromDiamond(path, fileName, sheetName, "ImportFromDiamond", 1);
			
			TableInstanceHelper tableInstance = new TableInstanceHelper();
			tableInstance.createTableInstance(path, fileName, sheetName, "TableInstance", 1);
			
			tableDefinition.synchTableSchema(path, fileName, sheetName, "ImportFromDiamond", 1);
			tableInstance.truncateTable(path, fileName, sheetName, "TableInstance", 1);
			
			JumpToSearchHelper jumpToSearch = new JumpToSearchHelper();
			jumpToSearch.manualDataLoad(path, fileName, sheetName, "ManualDataLoad", 1);
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	@Test(priority=15, description="Create Stream, SC & TC", groups = { "prerequisite" })
	public void createStream() throws Exception {
		try {
			dataDir = configProp.getDataDirPath();
			String destinationDirectory = dataDir + "/Diamond_XMLs/";
			FileHelper.createDir(applicationOS, destinationDirectory);
			FileHelper.deleteFile(applicationOS, destinationDirectory + "Database_Parse.xml");
			FileHelper.copyFile(applicationOS, automationPath + dataLocation, destinationDirectory, "Database_Parse.xml", "Database_Parse.xml", true);
			
			StreamHelper stream = new StreamHelper();
			stream.createStream(path, fileName, sheetName, "Stream", 1);
			
			StreamControllerHelper streamController = new StreamControllerHelper();
			streamController.createStreamController(path, fileName, sheetName, "StreamController", 1);
			
			TaskControllerHelper taskController = new TaskControllerHelper();
			taskController.createTaskController(path, fileName, sheetName, "TaskController", 1);
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	@Test(priority=16, description="Create Recurring Task & Trigger", groups = { "prerequisite" })
	public void createRecurringTask() throws Exception {
		try {
			RecurringTaskHelper recurringTask = new RecurringTaskHelper();
			recurringTask.createRecurringTask(path, fileName, sheetName, "RecurringTask", 1);
			
			TriggerHelper trigger = new TriggerHelper();
			trigger.createTrigger(path, fileName, sheetName, "Trigger", 1);
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	@Test(priority=17, description="Cleanup input and output directories", groups = { "prerequisite" })
	public void dirCleanup() throws Exception {
		try {
			//Create output&Input directory for parse
			FileHelper.cleanUpDir(applicationOS, dataDir + "/ParseOutput/" + folderName, true);
			
			// Start Controllers
			ControllerHelper controller = new ControllerHelper();
			controller.startStreamController();
			controller.startTaskController(configProp.getTaskControllerExeFile());
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	@Test(priority=18, description="Set Task Controller Capability for all the stages", groups = { "prerequisite" })
	public void setTaskControllerCapability() throws Exception {
		try {
			TaskControllerHelper taskController = new TaskControllerHelper();
			taskController.setTaskControllerCapability(path, fileName, sheetName, "TCCapability", 1);
			
			TaskSearchHelper taskSearch = new TaskSearchHelper();
			taskSearch.verifyTaskStatus(path, fileName, sheetName, "ETLTask", 1);
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		} finally {
			ControllerHelper controller = new ControllerHelper();
			controller.stopTaskController("Task Controller");
			controller.stopStreamController();
		}
	}
	
	@Test(priority=19, description="Verify Jump to Search Result", groups = { "prerequisite" })
	public void verifyJ2SResult() throws Exception {
		try {
			JumpToSearchHelper jumpToSearch = new JumpToSearchHelper();
			jumpToSearch.verifyJumpToSearchResult(path, fileName, sheetName, "J2S", 1);
			jumpToSearch.viewParseStatistics(path, fileName, sheetName, "ParseStatistics", 1);
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
}