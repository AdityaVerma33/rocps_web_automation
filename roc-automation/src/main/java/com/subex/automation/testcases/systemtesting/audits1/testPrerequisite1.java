package com.subex.automation.testcases.systemtesting.audits1;

import org.testng.annotations.Test;

import com.subex.automation.helpers.application.ControllerHelper;
import com.subex.automation.helpers.application.ReferenceTableHelper;
import com.subex.automation.helpers.application.screens.JumpToSearchHelper;
import com.subex.automation.helpers.application.screens.SettingsHelper;
import com.subex.automation.helpers.application.screens.StreamHelper;
import com.subex.automation.helpers.application.screens.SyncReferenceTableHelper;
import com.subex.automation.helpers.application.screens.TableDefinitionHelper;
import com.subex.automation.helpers.application.screens.TableInstanceHelper;
import com.subex.automation.helpers.application.screens.UsageGroupHelper;
import com.subex.automation.helpers.report.Log4jHelper;
import com.subex.automation.helpers.scripts.ExecuteScript;
import com.subex.automation.helpers.selenium.ROCAcceptanceTest;
import com.subex.automation.helpers.util.FailureHelper;

public class testPrerequisite1 extends ROCAcceptanceTest {
	
	private static String path = null;
	final String fileName = "AuditFlow1_TestData.xlsx";
	final String sheetName = "AuditFlow1";
	
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
			Log4jHelper.logInfo("Running Audit Flow 1");
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
	
	@Test(priority=2, description="Creating Data Source Connection for Usage DB", dependsOnMethods = { "createDataSourceLocation" }, groups = { "prerequisite1" })
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
	public void createUsageServer()throws Exception {
		try {
			ReferenceTableHelper referenceTable = new ReferenceTableHelper();
			referenceTable.usageServer(path, fileName, sheetName, "UsageServer", 1);
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	@Test(priority=4, description="Create Usage Group", dependsOnMethods = { "createUsageServer" }, groups = { "AuditFlow1" })
	public void createUsageGroup() throws Exception {
		try {
			UsageGroupHelper usageGroup = new UsageGroupHelper();
			usageGroup.createUsageGroup(path, fileName, sheetName, "UsageGroup", 1);
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	@Test(priority=5, description="Create Schema", dependsOnMethods = { "createUsageGroup" }, groups = { "prerequisite1" })
	public void createSchema() throws Exception {
		try {
			ReferenceTableHelper referenceTable = new ReferenceTableHelper();
			referenceTable.schema(path, fileName, sheetName, "Schema", 1);
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	@Test(priority=6, description="Attaching usage server and doing refresh usage partitions", dependsOnMethods = { "createSchema" }, groups = { "prerequisite1" })
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
	
	@Test(priority=7, description="Create Table Definition", dependsOnMethods = { "attachUsageServer" }, groups = { "prerequisite1" })
	public void createTD() throws Exception {
		try {
			StreamHelper stream = new StreamHelper(); 
			stream.createStream(path, fileName, sheetName, "Stream", 1);
			
			TableDefinitionHelper tableDefinition = new TableDefinitionHelper();
			tableDefinition.importFromDiamond(path, fileName, sheetName, "ImportFromDiamond", 1);
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	@Test(priority=8, description="Create Table Definition and Create Table Instance", dependsOnMethods = { "createTD" }, groups = { "prerequisite1" })
	public void createTI() throws Exception {
		try {
			TableInstanceHelper tableInstance = new TableInstanceHelper();
			tableInstance.createTableInstance(path, fileName, sheetName, "TableInstance", 1);
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
		
	@Test(priority=9, description="Manually loading data in the second reference table", dependsOnMethods = { "createTI" }, groups = { "prerequisite1" })
	public void manualDataLoad()throws Exception
	{
		try {
			JumpToSearchHelper jumpToSearch = new JumpToSearchHelper();
			jumpToSearch.manualDataLoad(path, fileName, sheetName, "ManualDataLoad", 1);
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
		
	@Test(priority=10, description="Create Sync reference table for ref_cdr_02", dependsOnMethods = { "manualDataLoad" }, groups = { "prerequisite1" })
	public void createSynchReferenceTable()throws Exception
	{
		try {
			SyncReferenceTableHelper syncRefTable = new SyncReferenceTableHelper();
			syncRefTable.createSyncReferenceTable(path, fileName, sheetName, "SyncReferenceTable", 1);
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
		
	@Test(priority=11, description="Truncate Table Instances", dependsOnMethods = { "createSynchReferenceTable" }, groups = { "prerequisite1" })
	public void truncateTables() throws Exception {
		try {
			TableInstanceHelper tableInstance = new TableInstanceHelper();
			tableInstance.truncateTable(path, fileName, sheetName, "TableInstance", 1);
			
			ExecuteScript.exeUsgQuery("truncate table qm_report");
			ExecuteScript.exeUsgQuery("truncate table dm_report");
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
}