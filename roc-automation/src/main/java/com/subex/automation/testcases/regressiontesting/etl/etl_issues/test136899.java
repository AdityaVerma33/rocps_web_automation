package com.subex.automation.testcases.regressiontesting.etl.etl_issues;

import java.util.ArrayList;
import java.util.HashMap;

import org.testng.annotations.Test;

import com.subex.automation.helpers.application.ControllerHelper;
import com.subex.automation.helpers.application.screens.JumpToSearchHelper;
import com.subex.automation.helpers.application.screens.SettingsHelper;
import com.subex.automation.helpers.application.screens.StreamHelper;
import com.subex.automation.helpers.application.screens.TableDefinitionHelper;
import com.subex.automation.helpers.application.screens.TableInstanceHelper;
import com.subex.automation.helpers.application.screens.TaskControllerHelper;
import com.subex.automation.helpers.application.screens.TaskSearchHelper;
import com.subex.automation.helpers.file.ExcelReader;
import com.subex.automation.helpers.util.FailureHelper;

public class test136899 extends testETLIssues {
	
	String exportDirectory = null;
	String downloadDirectory = null;
	final String sheetName = "test136899";
	
	public test136899() throws Exception {
		super();
	}
	
	@Test(priority=131, description="Test Case 1 for Bug 136899")
	public void testCase1() throws Exception {
		try {
			SettingsHelper settings = new SettingsHelper();
			settings.updateSettings(path, fileName, sheetName, "UpdateSettings", 1);
			
			StreamHelper stream = new StreamHelper();
			stream.createStream(path, fileName, sheetName, "Stream", 1);
			
			// Start Controllers
			ControllerHelper controller = new ControllerHelper();
			controller.startStreamController();
			controller.startTaskController(configProp.getTaskControllerExeFile());
			
			TaskControllerHelper taskController = new TaskControllerHelper();
			taskController.setTaskControllerCapability(path, fileName, sheetName, "TCCapability", 1);
			
			// Export All should work for System Table Instance in J2S screen.
			JumpToSearchHelper jumpToSearch = new JumpToSearchHelper();
			jumpToSearch.exportAllRows(path, fileName, sheetName, "SystemTableExport", 1);
			
			TaskSearchHelper taskSearch = new TaskSearchHelper();
			taskSearch.verifyTaskStatus(path, fileName, sheetName, "ExportTask", 1);
			
			ExcelReader excelReader = new ExcelReader();
			HashMap<String, ArrayList<String>> excelData = excelReader.readDataByColumn( path, fileName, sheetName, "SystemTableExport", 1 );
			String tableName = excelData.get("Display Name").get(0);
			exportDirectory = configProp.getDataDirPath() + "/ExportData";
			downloadDirectory = configProp.getDownloadDirectory();
			String[] linesValuesToIgnore = {"Exported on:"};
			jumpToSearch.verifyExportAllRows(tableName, exportDirectory, downloadDirectory, path + "country_Export.txt", linesValuesToIgnore);
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	@Test(priority=132, description="Test Case 2 for Bug 136899")
	public void testCase2() throws Exception {
		try {
			// Export All should work for Reference Table Instance in J2S screen.
			TableDefinitionHelper tableDefinition = new TableDefinitionHelper();
			tableDefinition.importFromDiamond(path, fileName, sheetName, "ImportFromDiamond", 1);
			
			TableInstanceHelper tableInstance = new TableInstanceHelper();
			tableInstance.createTableInstance(path, fileName, sheetName, "TableInstance", 1);
			
			JumpToSearchHelper jumpToSearch = new JumpToSearchHelper();
			jumpToSearch.manualDataLoad(path, fileName, sheetName, "ManualDataLoad", 1);
			jumpToSearch.exportAllRows(path, fileName, sheetName, "ManualDataLoad", 1);
			
			TaskSearchHelper taskSearch = new TaskSearchHelper();
			taskSearch.verifyTaskStatus(path, fileName, sheetName, "ExportTask", 1);
			
			ExcelReader excelReader = new ExcelReader();
			HashMap<String, ArrayList<String>> excelData = excelReader.readDataByColumn( path, fileName, sheetName, "ManualDataLoad", 1 );
			String tableName = excelData.get("Display Name").get(0);
			String[] linesValuesToIgnore = {"Exported on:"};
			jumpToSearch.verifyExportAllRows(tableName, exportDirectory, downloadDirectory, path + "reference_cdr_136899_Export.txt", linesValuesToIgnore);
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	@Test(priority=133, description="Test Case 3 for Bug 136899")
	public void testCase3() throws Exception {
		try {
			// Export All should work for Usage Table Instance in J2S screen.
			JumpToSearchHelper jumpToSearch = new JumpToSearchHelper();
			jumpToSearch.exportAllRows(path, fileName, sheetName, "UsageTableExport", 1);
			
			TaskSearchHelper taskSearch = new TaskSearchHelper();
			taskSearch.verifyTaskStatus(path, fileName, sheetName, "ExportTask", 1);
			
			ExcelReader excelReader = new ExcelReader();
			HashMap<String, ArrayList<String>> excelData = excelReader.readDataByColumn( path, fileName, sheetName, "UsageTableExport", 1 );
			String tableName = excelData.get("Table Name").get(0);
			String[] linesValuesToIgnore = {"Exported on:"};
			jumpToSearch.verifyExportAllRows(tableName, exportDirectory, downloadDirectory, path + "msc_fingerprint_Export.txt", linesValuesToIgnore);
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