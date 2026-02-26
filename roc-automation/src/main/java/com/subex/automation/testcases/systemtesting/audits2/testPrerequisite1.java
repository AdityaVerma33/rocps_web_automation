package com.subex.automation.testcases.systemtesting.audits2;

import org.testng.annotations.Test;

import com.subex.automation.helpers.application.ControllerHelper;
import com.subex.automation.helpers.application.ReferenceTableHelper;
import com.subex.automation.helpers.application.screens.EntityImportHelper;
import com.subex.automation.helpers.application.screens.SettingsHelper;
import com.subex.automation.helpers.application.screens.StreamControllerHelper;
import com.subex.automation.helpers.application.screens.StreamHelper;
import com.subex.automation.helpers.application.screens.TableDefinitionHelper;
import com.subex.automation.helpers.application.screens.TableInstanceHelper;
import com.subex.automation.helpers.application.screens.TaskControllerHelper;
import com.subex.automation.helpers.application.screens.UsageGroupHelper;
import com.subex.automation.helpers.component.FileHelper;
import com.subex.automation.helpers.report.Log4jHelper;
import com.subex.automation.helpers.scripts.ExecuteScript;
import com.subex.automation.helpers.selenium.ROCAcceptanceTest;
import com.subex.automation.helpers.util.FailureHelper;

public class testPrerequisite1 extends ROCAcceptanceTest
{
	private static String path = null;
	private static String dataDir = null;
	final String fileName = "AuditFlow2_TestData.xlsx";
	final String sheetName = "AuditFlow2";
	final String dataLocation = "/src/main/resources/Data/Audit_Flow";
	
	public testPrerequisite1() throws Exception {
		try {
			path = automationPath + "\\src\\main\\resources\\System_Test_Flows\\";
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	@Test(priority=1, description="Creating Data Source Location for Usage DB", groups = { "prerequisite1" })
	public void createDataSourceLocation()throws Exception
	{
		try {
			Log4jHelper.logInfo("Running Audit2 Flow");
			ControllerHelper controller = new ControllerHelper();
			controller.stopServices();
			
			SettingsHelper settings = new SettingsHelper();
			settings.updateSettings(path, fileName, sheetName, "UpdateSettings", 1);
			
			ReferenceTableHelper referenceTable = new ReferenceTableHelper();
			referenceTable.dataSourceLocation(path, fileName, sheetName, "DSL", 1);
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	@Test(priority=2, description="Creating Data Source Connection For Usage DB", dependsOnMethods = { "createDataSourceLocation" }, groups = { "prerequisite1" })
	public void createDataSourceConnection()throws Exception
	{
		try {
			ReferenceTableHelper referenceTable = new ReferenceTableHelper();
			referenceTable.dataSourceConnection(path, fileName, sheetName, "DSC", 1);
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	@Test(priority=3, description="Creating Usage Server for Usage DB", dependsOnMethods = { "createDataSourceConnection" }, groups = { "prerequisite1" })
	public void createUsageServer()throws Exception
	{
		try {
			ReferenceTableHelper referenceTable = new ReferenceTableHelper();
			referenceTable.usageServer(path, fileName, sheetName, "UsageServer", 1);
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	

	@Test(priority=4, description="Create Audit and Trend Summary Stream Stages", dependsOnMethods = { "createUsageServer" }, groups = { "prerequisite1" })
	public void createStream() throws Exception {
		try {
			StreamHelper stream = new StreamHelper();
			stream.createStream(path, fileName, sheetName, "Stream", 1);
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	@Test(priority=5, description="Entity Import the pre-requisite Config", dependsOnMethods = { "createStream" }, groups = { "prerequisite1" })
	public void entityImport()throws Exception
	{
		try {
			dataDir = configProp.getDataDirPath();
			String destinationDirectory = dataDir + "/Diamond_XMLs/";
			FileHelper.createDir(applicationOS, destinationDirectory);
			FileHelper.deleteFile(applicationOS, destinationDirectory + "Audits_Join_Usage5.xml");
			FileHelper.deleteFile(applicationOS, destinationDirectory + "Audits_Join_Usage6.xml");
			
			//Copying the diamond decoders in the Diamond_XMLs directory
			FileHelper.copyFile(applicationOS, automationPath + dataLocation, destinationDirectory, "Audits2_Join_Usage.xml", "Audits_Join_Usage5.xml", true);
			FileHelper.copyFile(applicationOS, automationPath + dataLocation, destinationDirectory, "Audits2_Join_Usage.xml", "Audits_Join_Usage6.xml", true);
			
			EntityImportHelper entityImport = new EntityImportHelper();
			entityImport.createEntityImport(path, fileName, sheetName, "EntityImport", 1);
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	@Test(priority=6, description="Creating Data Source Location for Usage DB", dependsOnMethods = { "entityImport" }, groups = { "prerequisite1" })
	public void createUsageGroup()throws Exception
	{
		try {
			UsageGroupHelper usageGroup = new UsageGroupHelper();
			usageGroup.createUsageGroup(path, fileName, sheetName, "UsageGroup", 1);
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	@Test(priority=7, description="Attaching usage schema to Audits usage group", dependsOnMethods = { "createUsageGroup" }, groups = { "prerequisite1" })
	public void createSchema()throws Exception
	{
		try {
			ReferenceTableHelper refTable = new ReferenceTableHelper();
			refTable.schema(path, fileName, sheetName, "Schema", 1);
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	@Test(priority=8, description="Attaching usage server and doing refresh usage partitions", dependsOnMethods = { "createSchema" }, groups = { "prerequisite1" })
	public void attachUsageServer()throws Exception
	{
		try {
			UsageGroupHelper usageGroup = new UsageGroupHelper();
			usageGroup.attachUsageServer(path, fileName, sheetName, "AttachUsageServer", 1);
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	@Test(priority=9, description="Configure Stream Controller", dependsOnMethods = { "attachUsageServer" }, groups = { "prerequisite1" })
	public void createStreamController() throws Exception {
		try {
			StreamControllerHelper streamController = new StreamControllerHelper();
			streamController.createStreamController(path, fileName, sheetName, "StreamController", 1);
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	@Test(priority=10, description="Configure Task Controller", dependsOnMethods = { "createStreamController" }, groups = { "prerequisite1" })
	public void createTaskController() throws Exception {
		try {
			TaskControllerHelper taskController = new TaskControllerHelper();
			taskController.createTaskController(path, fileName, sheetName, "TaskController", 1);
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	@Test(priority=11, description="Truncate Table Instances", dependsOnMethods = { "createTaskController" }, groups = { "prerequisite1" })
	public void truncateTables() throws Exception 
	{
		try {
			TableDefinitionHelper tableDefinition = new TableDefinitionHelper();
			tableDefinition.synchTableSchema(path, fileName, sheetName, "TableInstance", 1);
			TableInstanceHelper tableInstance = new TableInstanceHelper();
			tableInstance.truncateTable(path, fileName, sheetName, "TableInstance", 1);
			
			ExecuteScript.exeQuery("update property_inst set pri_value = '90' where prd_id = (select prd_id from property_dfn where prd_key like 'SvrMaxJumpSearchDays')");
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
}