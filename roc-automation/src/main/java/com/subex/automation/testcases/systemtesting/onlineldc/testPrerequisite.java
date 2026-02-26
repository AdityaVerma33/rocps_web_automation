package com.subex.automation.testcases.systemtesting.onlineldc;

import org.testng.annotations.Test;

import com.subex.automation.helpers.application.ControllerHelper;
import com.subex.automation.helpers.application.ReferenceTableHelper;
import com.subex.automation.helpers.application.screens.EntityImportHelper;
import com.subex.automation.helpers.application.screens.OnlineLDCCorrelatorHelper;
import com.subex.automation.helpers.application.screens.RecurringTaskHelper;
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
	final String fileName = "OnlineLDC_TestData.xlsx";
	final String sheetName = "OnlineLDC";
	final String dataLocation = "/src/main/resources/Data/Online_LDC";
	
	public testPrerequisite() throws Exception {
		try {
			path = automationPath + "\\src\\main\\resources\\System_Test_Flows\\";
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	@Test(priority=1, description="Create Data Source Location for Usage DB", groups = { "prerequisite" })
	public void createDataSourceLocation()throws Exception
	{
		try {
			Log4jHelper.logInfo("Running Online LDC Flow");
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
	
	@Test(priority=2, description="Create Data Source Connection For Usage DB", dependsOnMethods = { "createDataSourceLocation" }, groups = { "prerequisite" })
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
	
	@Test(priority=3, description="Create Usage Server for Usage DB", dependsOnMethods = { "createDataSourceConnection" }, groups = { "prerequisite" })
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
	
	@Test(priority=4, description="Create Usage Group", dependsOnMethods = { "createUsageServer" }, groups = { "prerequisite" })
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
	
	@Test(priority=5, description="Create schema for Usage Group", dependsOnMethods = { "createUsageGroup" }, groups = { "prerequisite" })
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
	
	@Test(priority=6, description="Attach usage server and do refresh usage partitions", dependsOnMethods = { "createSchema" }, groups = { "prerequisite" })
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
	
	@Test(priority=7, description="Entity Import", dependsOnMethods = { "attachUsageServer" }, groups = { "prerequisite" })
	public void entityImport() throws Exception
	{
		try {
			//Copying the diamond decoders in the Diamond_XMLs directory
			String dataDir = configProp.getDataDirPath();
			String destinationDirectory = dataDir + "/Diamond_XMLs/";
			FileHelper.createDir(applicationOS, destinationDirectory);
			FileHelper.deleteFile(applicationOS, destinationDirectory + "/nikira_onlineldc.xml");
			FileHelper.copyFile(applicationOS, automationPath + dataLocation, destinationDirectory, "nikira_onlineldc.xml", "nikira_onlineldc.xml", true);
			
			StreamHelper stream = new StreamHelper();
			stream.createStream(path, fileName, sheetName, "Stream", 1);
			
			EntityImportHelper entityImport = new EntityImportHelper();
			entityImport.createEntityImport(path, fileName, sheetName, "EntityImport", 1);
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	@Test(priority=8, description="Create Online LDC configuration", dependsOnMethods = { "entityImport" }, groups = { "prerequisite" })
	public void createOnlineLDC() throws Exception {	
		try {
			OnlineLDCCorrelatorHelper onlineLDC = new OnlineLDCCorrelatorHelper();
			onlineLDC.createOnlineLDCCorrelator(path, fileName, sheetName, "OnlineLDC", 1);
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	@Test(priority=9, description="Create Stream", dependsOnMethods = { "createOnlineLDC" }, groups = { "prerequisite" })
	public void createStream() throws Exception
	{
		try {
			StreamHelper stream = new StreamHelper();
			stream.createStream(path, fileName, sheetName, "Stream", 2);
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	@Test(priority=10, description="Configure Stream Controller", dependsOnMethods = { "createStream" }, groups = { "prerequisite" })
	public void createStreamController() throws Exception
	{
		try {
			StreamControllerHelper streamController = new StreamControllerHelper();
			streamController.createStreamController(path, fileName, sheetName, "StreamController", 1);
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	@Test(priority=11, description="Configure Task Controller", dependsOnMethods = { "createStreamController" }, groups = { "prerequisite" })
	public void createTaskController() throws Exception
	{
		try {
			TaskControllerHelper taskController = new TaskControllerHelper();
			taskController.createTaskController(path, fileName, sheetName, "TaskController", 1);
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	@Test(priority=12, description="Create recurring task for LDC Correlator", dependsOnMethods = { "createTaskController" }, groups = { "prerequisite" })
	public void createRecurringTask() throws Exception {	
		try {
			RecurringTaskHelper recurringTask = new RecurringTaskHelper();
			recurringTask.createRecurringTask(path, fileName, sheetName, "RecurringTask", 1);
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	@Test(priority=13, description="Create Trigger for Online LDC Correlator", dependsOnMethods = { "createRecurringTask" }, groups = { "prerequisite" })
	public void createTrigger() throws Exception {	
		try {
			TriggerHelper trigger = new TriggerHelper();
			trigger.createTrigger(path, fileName, sheetName, "Trigger", 1);
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	@Test(priority=14, description="Truncate Table Instances", dependsOnMethods = { "createTrigger" }, groups = { "prerequisite" })
	public void truncateTables() throws Exception 
	{
		try {
			TableDefinitionHelper tableDfn = new TableDefinitionHelper();
			tableDfn.synchTableSchema(path, fileName, sheetName, "TableInstance", 1);
			TableInstanceHelper tableInstance = new TableInstanceHelper();
			tableInstance.truncateTable(path, fileName, sheetName, "TableInstance", 1);
			
			ExecuteScript.exeQuery("update property_inst set pri_value = '90' where prd_id = (select prd_id from property_dfn where prd_key like 'SvrMaxJumpSearchDays')");
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
}