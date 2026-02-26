package com.subex.automation.testcases.systemtesting.duplicatecheck;

import org.testng.annotations.Test;

import com.subex.automation.helpers.application.ControllerHelper;
import com.subex.automation.helpers.application.ReferenceTableHelper;
import com.subex.automation.helpers.application.screens.QueryFilterHelper;
import com.subex.automation.helpers.application.screens.SettingsHelper;
import com.subex.automation.helpers.application.screens.StandardExpressionHelper;
import com.subex.automation.helpers.application.screens.TableDefinitionHelper;
import com.subex.automation.helpers.application.screens.TableInstanceHelper;
import com.subex.automation.helpers.application.screens.UsageGroupHelper;
import com.subex.automation.helpers.report.Log4jHelper;
import com.subex.automation.helpers.scripts.ExecuteScript;
import com.subex.automation.helpers.selenium.ROCAcceptanceTest;
import com.subex.automation.helpers.util.FailureHelper;

public class testPrerequisite1 extends ROCAcceptanceTest {
	
	private static String path = null;
	final String fileName = "DuplicateCheckFlow_TestData.xlsx";
	final String sheetName = "DuplicateCheck";
	
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
			Log4jHelper.logInfo("Running Duplicate XDR Check Flow");
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
	
	@Test(priority=4, description="Create Manual Usage Group", dependsOnMethods = { "createUsageServer" }, groups = { "prerequisite1" })
	public void createUsageGroup() throws Exception {
		try {
			UsageGroupHelper usageGroup = new UsageGroupHelper();
			usageGroup.createUsageGroup(path, fileName, sheetName, "UsageGroup", 1);
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	@Test(priority=5, description="Take offline of Usage Partition P1", dependsOnMethods = { "createUsageGroup" }, groups = { "prerequisite1" })
	public void takeUsagePartitionOffline() throws Exception {
		try {
			UsageGroupHelper usageGroup = new UsageGroupHelper();
			usageGroup.takePartitionOffline(path, fileName, sheetName, "TakePartitionOffline", 1);
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	@Test(priority=6, description="Create Schema", dependsOnMethods = { "takeUsagePartitionOffline" }, groups = { "prerequisite1" })
	public void createSchema() throws Exception {
		try {
			ReferenceTableHelper referenceTable = new ReferenceTableHelper();
			referenceTable.schema(path, fileName, sheetName, "Schema", 1);
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	@Test(priority=7, description="Create Table Definition", dependsOnMethods = { "createSchema" }, groups = { "prerequisite1" })
	public void createTD() throws Exception {
		try {
			TableDefinitionHelper tableDefinition = new TableDefinitionHelper();
			tableDefinition.importFromDiamond(path, fileName, sheetName, "ImportFromDiamond", 1);
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	@Test(priority=8, description="Create Table Instance", dependsOnMethods = { "createTD" }, groups = { "prerequisite1" })
	public void createTI() throws Exception {
		try {
			TableInstanceHelper tableInstance = new TableInstanceHelper();
			tableInstance.createTableInstance(path, fileName, sheetName, "TableInstance", 1);
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	@Test(priority=9, description="Attach Usage Server to Default Usage Group", dependsOnMethods = { "createTI" }, groups = { "prerequisite1" })
	public void attachUsageServer() throws Exception {
		try {
			UsageGroupHelper usageGroup = new UsageGroupHelper();
			usageGroup.attachUsageServer(path, fileName, sheetName, "AttachUsageServer", 1);
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	@Test(priority=10, description="Create Standard Expression", dependsOnMethods = { "attachUsageServer" }, groups = { "prerequisite1" })
	public void createStandardExpression() throws Exception {
		try {
			StandardExpressionHelper standardExpression = new StandardExpressionHelper();
			standardExpression.createStandardExpression(path, fileName, sheetName, "StandardExpression", 1);
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	@Test(priority=11, description="Create Query Filter", dependsOnMethods = { "createStandardExpression" }, groups = { "prerequisite1" })
	public void createQueryFilter() throws Exception {
		try {
			QueryFilterHelper queryFilter = new QueryFilterHelper();
			queryFilter.createQueryFilter(path, fileName, sheetName, "QueryFilter", 1);		
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}

	@Test(priority=12, description="Truncate Table Instance", dependsOnMethods = { "createQueryFilter" }, groups = { "prerequisite1" })
	public void truncateUsageTable() throws Exception {
		try {
			TableInstanceHelper tableInstance = new TableInstanceHelper();
			tableInstance.truncateTable(path, fileName, sheetName, "TableInstance", 1);
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	@Test(priority=13, description="Set Jump to Search property 'Max no.of days allowed for Jump To Search' to value 90", dependsOnMethods = { "truncateUsageTable" }, groups = { "prerequisite1" })
	public void setJ2SNoOfDaysPropertyTo90Days() throws Exception {
		try {
			ExecuteScript.exeQuery("update property_inst set pri_value = '90' where prd_id = (select prd_id from property_dfn where prd_key like 'SvrMaxJumpSearchDays')");
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
}