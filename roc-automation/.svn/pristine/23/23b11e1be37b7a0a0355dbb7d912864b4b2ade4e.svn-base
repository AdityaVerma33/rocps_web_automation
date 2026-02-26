package com.subex.automation.testcases.regressiontesting.etl;

import org.testng.annotations.Test;

import com.subex.automation.helpers.application.ControllerHelper;
import com.subex.automation.helpers.application.NavigationHelper;
import com.subex.automation.helpers.application.screens.CollectedFilesHelper;
import com.subex.automation.helpers.application.screens.FileCollectionHelper;
import com.subex.automation.helpers.application.screens.FileSourceHelper;
import com.subex.automation.helpers.application.screens.RecurringTaskHelper;
import com.subex.automation.helpers.application.screens.TaskControllerHelper;
import com.subex.automation.helpers.application.screens.TaskSearchHelper;
import com.subex.automation.helpers.component.CheckBoxHelper;
import com.subex.automation.helpers.component.FileHelper;
import com.subex.automation.helpers.selenium.ROCAcceptanceTest;
import com.subex.automation.helpers.util.FailureHelper;

public class testFCWithZipCollectedFiles extends ROCAcceptanceTest {
	
	private static String path = null;
	final String fileName = "ETLRegression_TestData.xlsx";
	final String sheetName = "ZipCollectedFile";
	final String dataLocation = "\\src\\main\\resources\\Data\\ASCII";
	final String folderName = "Reference";
	
	public testFCWithZipCollectedFiles() throws Exception {
		try {
			path = automationPath + "\\src\\main\\resources\\Regression\\";
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	@Test(priority=1, description="File Collection with Zip Collected File")
	public void testWithZipCollectedFiles()throws Exception
	{
		try {
			String fcName = "Reference FC";
			String fileSourceName = "Reference FS";
			FileCollectionHelper fileCollection = new FileCollectionHelper();
			int row = fileCollection.navigateToFileCollection(fcName);
			String detailScreenTitle = NavigationHelper.navigateToEdit("SearchGrid", row, "FileCollection_Name");
			
			fileCollection.setDetectDuplicateFile(false, false);
			CheckBoxHelper.check("FileCollection_ZipCollectedFiles");
			fileCollection.saveFileCollection(fcName, detailScreenTitle, true);
			
			FileSourceHelper fileSource = new FileSourceHelper();
			fileSource.reintialiseFileSource(fileSourceName);
			
			// Start Controllers
			ControllerHelper controller = new ControllerHelper();
			controller.startStreamController();
			controller.startTaskController(configProp.getTaskControllerExeFile());
			String dataDir = configProp.getDataDirPath();
			FileHelper.copyFile(applicationOS, automationPath + dataLocation, dataDir + "/Input/" + folderName, "Data_Load_Sample1.dat", "Sample7.dat", true);
			
			CollectedFilesHelper collectedFiles = new CollectedFilesHelper();
			collectedFiles.verifyCollectedFiles(path, fileName, sheetName, "CollectedFiles", 1);
			
			TaskSearchHelper taskSearch = new TaskSearchHelper();
			taskSearch.verifyTaskCount(path, fileName, sheetName, "ETLTaskCount", 1);
			
			TaskControllerHelper taskController = new TaskControllerHelper();
			taskController.setTaskControllerCapability(path, fileName, sheetName, "TCCapability", 1);
			
			RecurringTaskHelper recurringTask = new RecurringTaskHelper();
			recurringTask.createRecurringTask(path, fileName, sheetName, "RecurringTask", 1);
			Thread.sleep(10000);
			taskSearch.verifyTaskStatus(path, fileName, sheetName, "TaskStatus", 1);
			
			FileHelper.checkFileExists(dataDir + "/Collected Files/Reference/Sample7.dat.gz");
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