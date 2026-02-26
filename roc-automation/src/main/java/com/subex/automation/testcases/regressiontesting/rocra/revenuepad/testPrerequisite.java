package com.subex.automation.testcases.regressiontesting.rocra.revenuepad;

import org.testng.annotations.Test;

import com.subex.automation.helpers.application.ControllerHelper;
import com.subex.automation.helpers.application.ReferenceTableHelper;
import com.subex.automation.helpers.application.screens.EntityImportHelper;
import com.subex.automation.helpers.application.screens.JumpToSearchHelper;
import com.subex.automation.helpers.application.screens.SettingsHelper;
import com.subex.automation.helpers.application.screens.StreamControllerHelper;
import com.subex.automation.helpers.application.screens.StreamHelper;
import com.subex.automation.helpers.application.screens.TableDefinitionHelper;
import com.subex.automation.helpers.application.screens.TableInstanceHelper;
import com.subex.automation.helpers.application.screens.TaskControllerHelper;
import com.subex.automation.helpers.application.screens.TaskSearchHelper;
import com.subex.automation.helpers.application.screens.UsageGroupHelper;
import com.subex.automation.helpers.component.FileHelper;
import com.subex.automation.helpers.report.Log4jHelper;
import com.subex.automation.helpers.scripts.ExecuteScript;
import com.subex.automation.helpers.selenium.ROCAcceptanceTest;
import com.subex.automation.helpers.util.FailureHelper;

public class testPrerequisite extends ROCAcceptanceTest {
	
	String path = null;
	private static String dataDir = null;
	
	final String fileName = "ROCRA_TestData.xlsx";
	final String sheetName = "ETL";
	final String dataLocation = "\\src\\main\\resources\\Data\\ROCRA";
	
	public testPrerequisite() throws Exception {
		try {
			path = automationPath + "\\src\\main\\resources\\Regression\\";
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	private void copyXMLFiles(String destinationDirectory) throws Exception {
		try {
			FileHelper.cleanUpDir(applicationOS, dataDir + "/Input", true);
			FileHelper.cleanUpDir(applicationOS, dataDir + "/Collected Files", true);
			FileHelper.cleanUpDir(applicationOS, dataDir + "/ParseOutput", true);
			
			FileHelper.deleteFile(applicationOS, destinationDirectory + "MSC1.xml");
			FileHelper.copyFile(applicationOS, automationPath + dataLocation, destinationDirectory, "MSC1.xml", "MSC1.xml", true);
			
			FileHelper.deleteFile(applicationOS, destinationDirectory + "Mediation1.xml");
			FileHelper.copyFile(applicationOS, automationPath + dataLocation, destinationDirectory, "Mediation1.xml", "Mediation1.xml", true);
			
			FileHelper.deleteFile(applicationOS, destinationDirectory + "Tapin1.xml");
			FileHelper.copyFile(applicationOS, automationPath + dataLocation, destinationDirectory, "Tapin1.xml", "Tapin1.xml", true);
			
			FileHelper.deleteFile(applicationOS, destinationDirectory + "Tapout1.xml");
			FileHelper.copyFile(applicationOS, automationPath + dataLocation, destinationDirectory, "Tapout1.xml", "Tapout1.xml", true);
			
			FileHelper.deleteFile(applicationOS, destinationDirectory + "Interconnect_Mediation.xml");
			FileHelper.copyFile(applicationOS, automationPath + dataLocation, destinationDirectory, "Interconnect_Mediation.xml", "Interconnect_Mediation.xml", true);
			
			FileHelper.deleteFile(applicationOS, destinationDirectory + "Interconnect_Billing.xml");
			FileHelper.copyFile(applicationOS, automationPath + dataLocation, destinationDirectory, "Interconnect_Billing.xml", "Interconnect_Billing.xml", true);
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	private void copyDataFiles(String folderName, String dataFileName) throws Exception {
		try {
			FileHelper.cleanUpDir(applicationOS, dataDir + "/Input/" + folderName, true);
			FileHelper.cleanUpDir(applicationOS, dataDir + "/Collected Files/" + folderName, true);
			FileHelper.cleanUpDir(applicationOS, dataDir + "/ParseOutput/" + folderName, true);
			FileHelper.copyFile(applicationOS, automationPath + dataLocation, dataDir + "/Input/" + folderName, dataFileName, dataFileName, true);
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	@Test(priority=1, description="Prerequisite", groups = {"ROCRAPrerequisite"})
	public void runPrerequisite() throws Exception {
		try {
			Log4jHelper.logInfo("Running ROC RA Regression Suite");
			ControllerHelper controller = new ControllerHelper();
			controller.stopServices();
			
			StreamHelper stream = new StreamHelper();
			stream.createStream(path, fileName, sheetName, "Stream", 1);
			
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
			
			dataDir = configProp.getDataDirPath();
			String destinationDirectory = dataDir + "/Diamond_XMLs/";
			FileHelper.createDir(applicationOS, destinationDirectory);
			copyXMLFiles(destinationDirectory);
			
			EntityImportHelper entityImport = new EntityImportHelper();
			entityImport.createEntityImport(path, fileName, sheetName, "EntityImport", 1);
			
			TableDefinitionHelper tableDefinition = new TableDefinitionHelper();
			tableDefinition.synchTableSchema(path, fileName, sheetName, "TableDfn", 1);
			TableInstanceHelper tableInstance = new TableInstanceHelper();
			tableInstance.truncateTable(path, fileName, sheetName, "TableInstance", 1);
			ExecuteScript.exeQuery("update property_inst set pri_value = '90' where prd_id = (select prd_id from property_dfn where prd_key like 'SvrMaxJumpSearchDays')");
			
			JumpToSearchHelper jumpToSearch = new JumpToSearchHelper();
			jumpToSearch.manualDataLoad(path, fileName, sheetName, "ManualDataLoad", 1);
			
			StreamControllerHelper streamController = new StreamControllerHelper();
			streamController.createStreamController(path, fileName, sheetName, "StreamController", 1);
			
			TaskControllerHelper taskController = new TaskControllerHelper();
			taskController.createTaskController(path, fileName, sheetName, "TaskController", 1);
			
			copyDataFiles("msc", "MSC_0001241019_101112_1.txt");
			copyDataFiles("mediation", "Mediation_Data00104112013_1.csv");
			copyDataFiles("ict_mediation", "Interconnect_Mediation_Data001.csv");
			copyDataFiles("ict_billing", "Interconnect_Billing_Data001.csv");
			copyDataFiles("tapin", "Tapin_Data001.csv");
			copyDataFiles("tapout", "Tapout_Data001.csv");
			
			controller.startStreamController();
			controller.startTaskController(configProp.getTaskControllerExeFile());
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
}