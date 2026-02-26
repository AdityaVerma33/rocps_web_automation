package com.subex.automation.testcases.systemtesting.etl;

import org.testng.annotations.Test;

import com.subex.automation.helpers.application.ControllerHelper;
import com.subex.automation.helpers.application.ReferenceTableHelper;
import com.subex.automation.helpers.application.screens.EntityImportHelper;
import com.subex.automation.helpers.application.screens.FileCollectionHelper;
import com.subex.automation.helpers.application.screens.FileSourceHelper;
import com.subex.automation.helpers.application.screens.JumpToSearchHelper;
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

public class testPrerequisite extends ROCAcceptanceTest {
	
	private static String path = null;
	private static String dataDir = null;
	final String fileName = "ETLFlow_TestData.xlsx";
	final String sheetName = "ETLFlow";
	final String asciiDataLocation = "\\src\\main\\resources\\Data\\ASCII";
	final String asn1DataLocation = "\\src\\main\\resources\\Data\\ASN1";
	
	public testPrerequisite() throws Exception {
		try {
			path = automationPath + "\\src\\main\\resources\\System_Test_Flows\\";
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	@Test(priority=1, description="Entity Import the lookup tables", groups = { "prerequisite" })
	public void entityImport()throws Exception
	{
		try {
			Log4jHelper.logInfo("Running ETL Flow");
			ControllerHelper controller = new ControllerHelper();
			controller.stopServices();
			
			StreamHelper stream = new StreamHelper();
			stream.createStream(path, fileName, sheetName, "Stream", 1);
			
			EntityImportHelper entityImport = new EntityImportHelper();
			entityImport.createEntityImport(path, fileName, sheetName, "EntityImport", 1);
			
			SettingsHelper settings = new SettingsHelper();
			settings.updateSettings(path, fileName, sheetName, "UpdateSettings", 1);
			
			JumpToSearchHelper jumpToSearch = new JumpToSearchHelper();
			jumpToSearch.manualDataLoad(path, fileName, sheetName, "ManualDataLoad", 1);
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	@Test(priority=2, description="Create Usage Group", dependsOnMethods = { "entityImport" }, groups = { "prerequisite" } )
	public void createUsageGroup() throws Exception {
		try {
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
	
	@Test(priority=3, description="Create TD and TI", dependsOnMethods = { "createUsageGroup" }, groups = { "prerequisite" })
	public void createTD_TI() throws Exception {
		try {
			TableDefinitionHelper tableDefinition = new TableDefinitionHelper();
			tableDefinition.importFromDiamond(path, fileName, sheetName, "ImportFromDiamond", 1);
			tableDefinition.createTableInstance(path, fileName, sheetName, "TableInstance", 1);
			tableDefinition.synchTableSchema(path, fileName, sheetName, "ImportFromDiamond", 1);
			
			TableInstanceHelper tableInstance = new TableInstanceHelper();
			tableInstance.truncateTable(path, fileName, sheetName, "TableInstance", 1);
			ExecuteScript.exeQuery("update property_inst set pri_value = '90' where prd_id = (select prd_id from property_dfn where prd_key like 'SvrMaxJumpSearchDays')");
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	@Test(priority=4, description="Create Stream", dependsOnMethods = { "createTD_TI" }, groups = { "prerequisite" })
	public void createStream() throws Exception {
		try {
			dataDir = configProp.getDataDirPath();
			String destinationDirectory = dataDir + "/Diamond_XMLs/";
			FileHelper.createDir(applicationOS, destinationDirectory);
			FileHelper.deleteFile(applicationOS, destinationDirectory + "Data_Load_Usage.xml");
			FileHelper.copyFile(applicationOS, automationPath + asciiDataLocation, destinationDirectory, "Data_Load_Usage.xml", "Data_Load_Usage.xml", true);
			FileHelper.deleteFile(applicationOS, destinationDirectory + "Jawwal_SMSC_Mod.xml");
			FileHelper.copyFile(applicationOS, automationPath + asn1DataLocation, destinationDirectory, "Jawwal_SMSC_Mod.xml", "Jawwal_SMSC_Mod.xml", true);
			
			StreamHelper stream = new StreamHelper();
			stream.createStream(path, fileName, sheetName, "Stream", 2);
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	@Test(priority=5, description="Configure SC & TC", dependsOnMethods = { "createStream" }, groups = { "prerequisite" })
	public void createSC_TC() throws Exception {
		try {
			StreamControllerHelper streamController = new StreamControllerHelper();
			streamController.createStreamController(path, fileName, sheetName, "StreamController", 1);
			
			TaskControllerHelper taskController = new TaskControllerHelper();
			taskController.createTaskController(path, fileName, sheetName, "TaskController", 1);
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	@Test(priority=6, description="Create File Source & File Collection", dependsOnMethods = { "createSC_TC" }, groups = { "prerequisite" })
	public void createFileCollection() throws Exception {
		try {
			FileSourceHelper fileSource = new FileSourceHelper();
			fileSource.createFileSource(path, fileName, sheetName, "FileSource", 1);
			
			FileCollectionHelper fileCollection = new FileCollectionHelper();
			fileCollection.createFileCollection(path, fileName, sheetName, "FileCollection", 1);
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	@Test(priority=7, description="Create Trigger", dependsOnMethods = { "createFileCollection" }, groups = { "prerequisite" })
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