package com.subex.automation.testcases.systemtesting.tariff;

import org.testng.annotations.Test;

import com.subex.automation.helpers.application.ControllerHelper;
import com.subex.automation.helpers.application.ReferenceTableHelper;
import com.subex.automation.helpers.application.screens.FileCollectionHelper;
import com.subex.automation.helpers.application.screens.FileSourceHelper;
import com.subex.automation.helpers.application.screens.SettingsHelper;
import com.subex.automation.helpers.application.screens.StreamControllerHelper;
import com.subex.automation.helpers.application.screens.StreamHelper;
import com.subex.automation.helpers.application.screens.TableDefinitionHelper;
import com.subex.automation.helpers.application.screens.TableInstanceHelper;
import com.subex.automation.helpers.application.screens.TaskControllerHelper;
import com.subex.automation.helpers.application.screens.TriggerHelper;
import com.subex.automation.helpers.application.screens.UsageGroupHelper;
import com.subex.automation.helpers.component.FileHelper;
import com.subex.automation.helpers.report.Log4jHelper;
import com.subex.automation.helpers.scripts.ExecuteScript;
import com.subex.automation.helpers.selenium.ROCAcceptanceTest;
import com.subex.automation.helpers.util.FailureHelper;

public class testPrerequisite1 extends ROCAcceptanceTest {
	
	private static String path = null;
	private static String dataDir = null;
	final String fileName = "Tariff_TestData.xlsx";
	final String sheetName = "Tariff";
	final String dataLocation = "\\src\\main\\resources\\Data\\Tariff";
	final String xmlFileName = "Rating_Tariff_Scenario8.xml";
	
	public testPrerequisite1() throws Exception {
		try {
			path = automationPath + "\\src\\main\\resources\\System_Test_Flows\\";
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	@Test(priority=1, description="Create Data Source Location", groups = { "prerequisite1" } )
	public void createDataSourceLocation() throws Exception {
		try {
			Log4jHelper.logInfo("Running Tariff Flow");
			ControllerHelper controller = new ControllerHelper();
			controller.stopServices();
			
			ReferenceTableHelper referenceTable = new ReferenceTableHelper();
			referenceTable.dataSourceLocation(path, fileName, sheetName, "DSL", 1);
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	@Test(priority=2, description="Create Data Source Connection", dependsOnMethods = { "createDataSourceLocation" }, groups = { "prerequisite1" } )
	public void createDataSourceConnection() throws Exception {
		try {
			ReferenceTableHelper referenceTable = new ReferenceTableHelper();
			referenceTable.dataSourceConnection(path, fileName, sheetName, "DSC", 1);
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	@Test(priority=3, description=" Create Usage Server", dependsOnMethods = { "createDataSourceConnection" }, groups = { "prerequisite1" })
	public void createUsageServer() throws Exception {
		try {
			ReferenceTableHelper referenceTable = new ReferenceTableHelper();
			referenceTable.usageServer(path, fileName, sheetName, "UsageServer", 1);
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	@Test(priority=4, description="Change Valid Digits to accept alphabets in Settings", dependsOnMethods = { "createUsageServer" }, groups = { "prerequisite1" })
	public void updateSettings() throws Exception {
		try {
			SettingsHelper settings = new SettingsHelper();
			settings.updateSettings(path, fileName, sheetName, "UpdateSettings", 1);
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	@Test(priority=5, description="Create Manual Usage Group", dependsOnMethods = { "updateSettings" }, groups = { "prerequisite1" })
	public void createUsageGroup() throws Exception {
		try {
			UsageGroupHelper usageGroup = new UsageGroupHelper();
			usageGroup.createUsageGroup(path, fileName, sheetName, "UsageGroup", 1);
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	@Test(priority=6, description="Create Schema", dependsOnMethods = { "createUsageGroup" }, groups = { "prerequisite1" })
	public void createSchema() throws Exception {
		try {
			ReferenceTableHelper referenceTable = new ReferenceTableHelper();
			referenceTable.schema(path, fileName, sheetName, "Schema", 1);
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	@Test(priority=7, description="Attach Usage Server to Usage Group", dependsOnMethods = { "createSchema" }, groups = { "prerequisite1" })
	public void attachUsageServer() throws Exception {
		try {
			UsageGroupHelper usageGroup = new UsageGroupHelper();
			usageGroup.attachUsageServer(path, fileName, sheetName, "AttachUsageServer", 1);
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	@Test(priority=8, description="Create Table Definition", dependsOnMethods = { "attachUsageServer" }, groups = { "prerequisite1" })
	public void createTD() throws Exception {
		try {
			TableDefinitionHelper tableDefinition = new TableDefinitionHelper();
			tableDefinition.importFromDiamond(path, fileName, sheetName, "ImportFromDiamond", 1);
			tableDefinition.synchTableSchema(path, fileName, sheetName, "ImportFromDiamond", 1);
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	@Test(priority=9, description="Create Table Instance", dependsOnMethods = { "createTD" }, groups = { "prerequisite1" })
	public void createTI() throws Exception {
		try {
			TableInstanceHelper tableInstance = new TableInstanceHelper();
			tableInstance.createTableInstance(path, fileName, sheetName, "TableInstance", 1);
			tableInstance.truncateTable(path, fileName, sheetName, "TableInstance", 1);
			ExecuteScript.exeQuery("update property_inst set pri_value = '90' where prd_id = (select prd_id from property_dfn where prd_key like 'SvrMaxJumpSearchDays')");
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	@Test(priority=10, description="Create Stream", dependsOnMethods = { "createTI" }, groups = { "prerequisite1" })
	public void createStream() throws Exception {
		try {
			dataDir = configProp.getDataDirPath();
			String destinationDirectory = dataDir + "/Diamond_XMLs/";
			FileHelper.createDir(applicationOS, destinationDirectory);
			FileHelper.createDir(applicationOS, destinationDirectory);
			FileHelper.deleteFile(applicationOS, destinationDirectory + xmlFileName);
			FileHelper.copyFile(applicationOS, automationPath + dataLocation, destinationDirectory, xmlFileName, xmlFileName, true);
			
			StreamHelper stream = new StreamHelper();
			stream.createStream(path, fileName, sheetName, "Stream", 1);
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	@Test(priority=11, description="Configure SC", dependsOnMethods = { "createStream" }, groups = { "prerequisite1" })
	public void createStreamController() throws Exception {
		try {
			StreamControllerHelper streamController = new StreamControllerHelper();
			streamController.createStreamController(path, fileName, sheetName, "StreamController", 1);
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	@Test(priority=12, description="Configure TC", dependsOnMethods = { "createStreamController" }, groups = { "prerequisite1" })
	public void createTaskController() throws Exception {
		try {
			TaskControllerHelper taskController = new TaskControllerHelper();
			taskController.createTaskController(path, fileName, sheetName, "TaskController", 1);
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	@Test(priority=13, description="Create File Source", dependsOnMethods = { "createTaskController" }, groups = { "prerequisite1" })
	public void createFileSource() throws Exception {
		try {
			FileSourceHelper fileSource = new FileSourceHelper();
			fileSource.createFileSource(path, fileName, sheetName, "FileSource", 1);
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	@Test(priority=14, description="Create File Collection", dependsOnMethods = { "createFileSource" }, groups = { "prerequisite1" })
	public void createFileCollection() throws Exception {
		try {
			FileCollectionHelper fileCollection = new FileCollectionHelper();
			fileCollection.createFileCollection(path, fileName, sheetName, "FileCollection", 1);
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	@Test(priority=15, description="Create Trigger", dependsOnMethods = { "createFileCollection" }, groups = { "prerequisite1" })
	public void createTrigger() throws Exception {
		try {
			TriggerHelper trigger = new TriggerHelper();
			trigger.createTrigger(path, fileName, sheetName, "Trigger", 1);
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
}