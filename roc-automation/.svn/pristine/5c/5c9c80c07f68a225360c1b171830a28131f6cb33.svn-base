package com.subex.automation.testcases.regressiontesting.duplicatecheck;

import java.util.ArrayList;
import java.util.HashMap;

import org.testng.annotations.Test;

import com.subex.automation.helpers.application.ControllerHelper;
import com.subex.automation.helpers.application.NavigationHelper;
import com.subex.automation.helpers.application.screens.AlertsHelper;
import com.subex.automation.helpers.application.screens.DuplicateXDRHelper;
import com.subex.automation.helpers.application.screens.JumpToSearchHelper;
import com.subex.automation.helpers.application.screens.TableInstanceHelper;
import com.subex.automation.helpers.application.screens.TaskSearchHelper;
import com.subex.automation.helpers.component.FileHelper;
import com.subex.automation.helpers.file.ExcelReader;
import com.subex.automation.helpers.selenium.ROCAcceptanceTest;
import com.subex.automation.helpers.util.FailureHelper;

public class testMultipleTableInstances extends ROCAcceptanceTest {
	
	static String path = null;
	static String dataDir = null;
	final String fileName = "DuplicateCheck_TestData.xlsx";
	final String sheetName = "MultipleTableInstances";
	final String dataLocation = "\\src\\main\\resources\\Data\\Duplicate_XDR";
	final String dataFileName = "Duplicate1.dat";
	final String alertNo = "3,013";
	
	public testMultipleTableInstances() throws Exception {
		try {
			path = automationPath + "\\src\\main\\resources\\Regression\\";
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	// Verify if Duplicate Check happens with multiple Table Instances
	@Test(priority=1, description="Duplicate XDR Check with multiple Table Instances")
	public void testMultipleTable() throws Exception
	{
		try {
			ControllerHelper controller = new ControllerHelper();
			controller.stopServices();
			
			TableInstanceHelper tableInstance = new TableInstanceHelper();
			tableInstance.truncateTable(path, fileName, sheetName, "TableInstance", 1);
			
			ExcelReader excelReader = new ExcelReader();
			HashMap<String, ArrayList<String>> excelData = excelReader.readDataByColumn( path, fileName, sheetName, "DuplicateXDRCheck", 1 );
			String duplicateXDRName = excelData.get( "Name" ).get( 0 );
			
			DuplicateXDRHelper duplicateCheck = new DuplicateXDRHelper();
			int rowNo = duplicateCheck.navigateToDuplicateXDR(duplicateXDRName);
			
			if (rowNo > 0) {
				String detailScreenTitle = NavigationHelper.navigateToEdit("SearchGrid", rowNo, "DuplicateXDR_Name");
				duplicateCheck.updateDuplicateXDR(path, fileName, sheetName, "DuplicateXDRCheck", 1);
				duplicateCheck.saveDuplicateXDR(duplicateXDRName, detailScreenTitle);
			}
			else {
				FailureHelper.failTest("Duplicate XDR Check '" + duplicateXDRName + "' is not found.");
			}
			
			dataDir = configProp.getDataDirPath();
			FileHelper.copyFile(applicationOS, automationPath + dataLocation, dataDir + "\\Input\\Duplicate_Check1\\", dataFileName, "Duplicate2.dat", true);
			
			controller.startStreamController();
			controller.startTaskController(configProp.getTaskControllerExeFile());
			
			TaskSearchHelper taskSearch = new TaskSearchHelper();
			taskSearch.reinitializeStreamStage(path, fileName, sheetName, "ReinitializeStreamStage", 1);
			taskSearch.scheduleTask(path, fileName, sheetName, "SchduleTask", 1);
			taskSearch.verifyTaskStatus(path, fileName, sheetName, "Task", 1);
			
			AlertsHelper alerts = new AlertsHelper();
			alerts.verifyAlertInstance("Task", "Today", null, null, alertNo, null, null, 0);
			
			JumpToSearchHelper jumpToSearch = new JumpToSearchHelper();
			jumpToSearch.verifyJumpToSearchResult(path, fileName, sheetName, "J2S", 1);
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