package com.subex.automation.testcases.regressiontesting.duplicatecheck;

import org.testng.annotations.Test;

import com.subex.automation.helpers.application.ControllerHelper;
import com.subex.automation.helpers.application.ReferenceTableHelper;
import com.subex.automation.helpers.application.screens.TableDefinitionHelper;
import com.subex.automation.helpers.application.screens.TableInstanceHelper;
import com.subex.automation.helpers.application.screens.UsageGroupHelper;
import com.subex.automation.helpers.report.Log4jHelper;
import com.subex.automation.helpers.scripts.ExecuteScript;
import com.subex.automation.helpers.selenium.ROCAcceptanceTest;
import com.subex.automation.helpers.util.FailureHelper;

public class testPrerequisite extends ROCAcceptanceTest {
	
	static String path = null;
	final String fileName = "DuplicateCheck_TestData.xlsx";
	final String sheetName = "Prerequisite";
	
	public testPrerequisite() throws Exception {
		try {
			path = automationPath + "\\src\\main\\resources\\Regression\\";
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	@Test(priority=1, description="Create Usage Group", groups = { "prerequisite" } )
	public void createUsageGroup() throws Exception {
		try {
			Log4jHelper.logInfo("Running Duplicate XDR Check Regression");
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
	
	@Test(priority=2, description="Create Table Definition", dependsOnMethods = { "createUsageGroup" }, groups = { "prerequisite" })
	public void createTD_TI() throws Exception {
		try {
			TableDefinitionHelper tableDefinition = new TableDefinitionHelper();
			tableDefinition.importFromDiamond(path, fileName, sheetName, "ImportFromDiamond", 1);
			tableDefinition.createTableInstance(path, fileName, sheetName, "TableInstance", 1);
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