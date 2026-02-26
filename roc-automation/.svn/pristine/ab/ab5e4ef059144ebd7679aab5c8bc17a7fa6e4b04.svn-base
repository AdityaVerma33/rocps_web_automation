package com.subex.automation.testcases.regressiontesting.etl.etl_issues;

import org.testng.annotations.Test;

import com.subex.automation.helpers.application.ControllerHelper;
import com.subex.automation.helpers.application.ROCHelper;
import com.subex.automation.helpers.application.ReferenceTableHelper;
import com.subex.automation.helpers.application.screens.FileCollectionHelper;
import com.subex.automation.helpers.application.screens.FileSourceHelper;
import com.subex.automation.helpers.application.screens.SettingsHelper;
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

public class testASCII extends ROCAcceptanceTest {
	
	String path = null;
	String dataDir = null;
	final String fileName = "ETLIssues_TestData.xlsx";
	final String sheetName = "ASCII";
	final String folderName = "ASCII";
	final String dataLocation = "\\src\\main\\resources\\Data";
	final String dataFileName1 = "MSC_0001241019_101112.txt";
	final String dataFileName2 = "Mediation_Data00104112013.csv";
	
	public testASCII() throws Exception {
		try {
			path = automationPath + "\\src\\main\\resources\\Issues\\";
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	@Test(priority=1, description="Create Usage Group", groups = { "prerequisite" })
	public void createUsageGroup() throws Exception {
		try {
			Log4jHelper.logInfo("Running ASCII ETL Flow");
			ControllerHelper controller = new ControllerHelper();
			controller.stopServices();
			
			SettingsHelper settings = new SettingsHelper();
			settings.updateSettings(path, fileName, sheetName, "UpdateSettings", 1);
			
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
	
	@Test(priority=2, description="Create Table Definition", groups = { "prerequisite" })
	public void createTDTI() throws Exception {
		try {
			TableDefinitionHelper tableDefinition = new TableDefinitionHelper();
			tableDefinition.importFromDiamond(path, fileName, sheetName, "ImportFromDiamond", 1);
			
			TableInstanceHelper tableInstance = new TableInstanceHelper();
			tableInstance.createTableInstance(path, fileName, sheetName, "TableInstance", 1);
			
			tableDefinition.synchTableSchema(path, fileName, sheetName, "ImportFromDiamond", 1);
			tableInstance.truncateTable(path, fileName, sheetName, "TableInstance", 1);
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	@Test(priority=3, description="Create Stream, SC & TC", groups = { "prerequisite" })
	public void createStream() throws Exception {
		try {
			dataDir = configProp.getDataDirPath();
			String destinationDirectory = dataDir + "/Diamond_XMLs/";
			FileHelper.createDir(applicationOS, destinationDirectory);
			FileHelper.deleteFile(applicationOS, destinationDirectory + "MSC.xml");
			FileHelper.copyFile(applicationOS, automationPath + dataLocation, destinationDirectory, "MSC.xml", "MSC.xml", true);
			
			FileHelper.deleteFile(applicationOS, destinationDirectory + "Mediation.xml");
			FileHelper.copyFile(applicationOS, automationPath + dataLocation, destinationDirectory, "Mediation.xml", "Mediation.xml", true);
			
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
	
	@Test(priority=4, description="Create File Source, File Collection & Trigger", groups = { "prerequisite" })
	public void createFileCollection_Trigger() throws Exception {
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
	
	@Test(priority=5, description="Cleanup input and output directories", groups = { "prerequisite" })
	public void dirCleanup() throws Exception {
		try {
			//Create output&Input directory for parse
			String mscFolderName = folderName + "/MSC";
			String mediationFolderName = folderName + "/Mediation";
			FileHelper.cleanUpDir(applicationOS, dataDir + "/Input/" + folderName, true);
			FileHelper.cleanUpDir(applicationOS, dataDir + "/Input/" + mscFolderName, true);
			FileHelper.cleanUpDir(applicationOS, dataDir + "/Input/" + mediationFolderName, true);
			
			FileHelper.cleanUpDir(applicationOS, dataDir + "/Collected Files/" + folderName, true);
			FileHelper.cleanUpDir(applicationOS, dataDir + "/Collected Files/" + mscFolderName, true);
			FileHelper.cleanUpDir(applicationOS, dataDir + "/Collected Files/" + mediationFolderName, true);
			
			FileHelper.cleanUpDir(applicationOS, dataDir + "/ParseOutput/" + folderName, true);
			FileHelper.cleanUpDir(applicationOS, dataDir + "/ParseOutput/" + mscFolderName, true);
			FileHelper.cleanUpDir(applicationOS, dataDir + "/ParseOutput/" + mediationFolderName, true);
			
			// Start Controllers
			ControllerHelper controller = new ControllerHelper();
			controller.startStreamController();
			controller.startTaskController(configProp.getTaskControllerExeFile());
			
			FileHelper.copyFile(applicationOS, automationPath + dataLocation, dataDir + "/Input/" + mscFolderName, dataFileName1, dataFileName1, true);
			FileHelper.copyFile(applicationOS, automationPath + dataLocation, dataDir + "/Input/" + mediationFolderName, dataFileName2, dataFileName2, true);
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	@Test(priority=6, description="Set Task Controller Capability for all the stages", groups = { "prerequisite" })
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