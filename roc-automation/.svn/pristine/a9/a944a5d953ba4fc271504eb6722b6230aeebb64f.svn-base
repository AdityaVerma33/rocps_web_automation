package com.subex.automation.testcases.regressiontesting.measures_audits.audit_issues;

import org.testng.annotations.Test;

import com.subex.automation.helpers.application.ControllerHelper;
import com.subex.automation.helpers.application.ROCHelper;
import com.subex.automation.helpers.application.ReferenceTableHelper;
import com.subex.automation.helpers.application.screens.FileCollectionHelper;
import com.subex.automation.helpers.application.screens.FileSourceHelper;
import com.subex.automation.helpers.application.screens.JumpToSearchHelper;
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
import com.subex.automation.helpers.util.FailureHelper;

public class testPrerequisite extends testAuditIssues {
	
	String dataDir = null;
	final String dataLocation = "\\src\\main\\resources\\Data";
	final String dataFileName1 = "Tapin_Data001.csv";
	final String dataFileName2 = "Tapout_Data001.csv";
	final String sheetName = "Prerequisite";
	
	public testPrerequisite() throws Exception {
		super();
	}
	
	@Test(priority=1, description="Create Usage Group", groups = { "prerequisite" })
	public void createUsageGroup() throws Exception {
		try {
			Log4jHelper.logInfo("Running Audit Issues Test Cases");
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
			
			StreamHelper stream = new StreamHelper();
			stream.createStream(path, fileName, sheetName, "Stream", 1);
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	@Test(priority=2, description="Create Table Definition", dependsOnMethods = { "createUsageGroup" }, groups = { "prerequisite" })
	public void createTDTI() throws Exception {
		try {
			TableDefinitionHelper tableDefinition = new TableDefinitionHelper();
			tableDefinition.importFromDiamond(path, fileName, sheetName, "ImportFromDiamond", 1);
			
			TableInstanceHelper tableInstance = new TableInstanceHelper();
			tableInstance.createTableInstance(path, fileName, sheetName, "TableInstance", 1);
			tableInstance.truncateTable(path, fileName, sheetName, "TableInstance", 1);
			
			JumpToSearchHelper jumpToSearch = new JumpToSearchHelper();
			jumpToSearch.manualDataLoad(path, fileName, sheetName, "ManualDataLoad", 1);
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	@Test(priority=3, description="Create Stream, SC & TC", dependsOnMethods = { "createTDTI" }, groups = { "prerequisite" })
	public void createStream() throws Exception {
		try {
			dataDir = configProp.getDataDirPath();
			String destinationDirectory = dataDir + "/Diamond_XMLs/";
			FileHelper.createDir(applicationOS, destinationDirectory);
			FileHelper.deleteFile(applicationOS, destinationDirectory + "Tapin.xml");
			FileHelper.copyFile(applicationOS, automationPath + dataLocation, destinationDirectory, "Tapin.xml", "Tapin.xml", true);
			
			FileHelper.deleteFile(applicationOS, destinationDirectory + "Tapout.xml");
			FileHelper.copyFile(applicationOS, automationPath + dataLocation, destinationDirectory, "Tapout.xml", "Tapout.xml", true);
			
			StreamHelper stream = new StreamHelper();
			stream.createStream(path, fileName, sheetName, "Stream", 2);
			
			StreamControllerHelper streamController = new StreamControllerHelper();
			streamController.createStreamController(path, fileName, sheetName, "StreamController", 1);
			
			TaskControllerHelper taskController = new TaskControllerHelper();
			taskController.createTaskController(path, fileName, sheetName, "TaskController", 1);
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	@Test(priority=4, description="Create File Source, File Collection & Trigger", dependsOnMethods = { "createStream" }, groups = { "prerequisite" })
	public void createFileCollection() throws Exception {
		try {
			FileSourceHelper fileSource = new FileSourceHelper();
			fileSource.createFileSource(path, fileName, sheetName, "FileSource", 1);
			
			FileCollectionHelper fileCollection = new FileCollectionHelper();
			fileCollection.createFileCollection(path, fileName, sheetName, "FileCollection", 1);
			
			TriggerHelper trigger = new TriggerHelper();
			trigger.createTrigger(path, fileName, sheetName, "Trigger", 1);
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	@Test(priority=5, description="Cleanup input and output directories", dependsOnMethods = { "createFileCollection" }, groups = { "prerequisite" })
	public void dirCleanup() throws Exception {
		try {
			//Create output&Input directory for parse
			String tapinFolderName = "/Tapin";
			String tapoutFolderName = "/Tapout";
			FileHelper.cleanUpDir(applicationOS, dataDir + "/Input/" + tapinFolderName, true);
			FileHelper.cleanUpDir(applicationOS, dataDir + "/Input/" + tapoutFolderName, true);
			FileHelper.cleanUpDir(applicationOS, dataDir + "/Collected Files/" + tapinFolderName, true);
			FileHelper.cleanUpDir(applicationOS, dataDir + "/Collected Files/" + tapoutFolderName, true);
			FileHelper.cleanUpDir(applicationOS, dataDir + "/ParseOutput/" + tapinFolderName, true);
			FileHelper.cleanUpDir(applicationOS, dataDir + "/ParseOutput/" + tapoutFolderName, true);
			
			// Start Controllers
			ControllerHelper controller = new ControllerHelper();
			controller.startStreamController();
			controller.startTaskController(configProp.getTaskControllerExeFile());
			
			FileHelper.copyFile(applicationOS, automationPath + dataLocation, dataDir + "/Input/" + tapinFolderName, dataFileName1, dataFileName1, true);
			FileHelper.copyFile(applicationOS, automationPath + dataLocation, dataDir + "/Input/" + tapoutFolderName, dataFileName2, dataFileName2, true);
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	@Test(priority=6, description="Set Task Controller Capability for all the stages", dependsOnMethods = { "dirCleanup" }, groups = { "prerequisite" })
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
			ROCHelper rocHelper = new ROCHelper();
			rocHelper.handleFailures();
			
			ControllerHelper controller = new ControllerHelper();
			controller.stopTaskController("Task Controller");
			controller.stopStreamController();
		}
	}
}