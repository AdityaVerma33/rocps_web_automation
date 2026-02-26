package com.subex.automation.testcases.regressiontesting.etl;

import org.testng.annotations.Test;

import com.subex.automation.helpers.application.ControllerHelper;
import com.subex.automation.helpers.application.NavigationHelper;
import com.subex.automation.helpers.application.screens.CollectedFilesHelper;
import com.subex.automation.helpers.application.screens.FileCollectionHelper;
import com.subex.automation.helpers.application.screens.FileSourceHelper;
import com.subex.automation.helpers.application.screens.TaskSearchHelper;
import com.subex.automation.helpers.component.FileHelper;
import com.subex.automation.helpers.selenium.ROCAcceptanceTest;
import com.subex.automation.helpers.util.FailureHelper;

public class testFCWithDetectDuplicate extends ROCAcceptanceTest {
	
	private static String path = null;
	final String fileName = "ETLRegression_TestData.xlsx";
	final String sheetName = "DetectDuplicate";
	final String dataLocation = "\\src\\main\\resources\\Data\\ASCII";
	final String folderName = "Reference";
	
	public testFCWithDetectDuplicate() throws Exception {
		try {
			path = automationPath + "\\src\\main\\resources\\Regression\\";
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	@Test(priority=1, description="File Collection with Detect Duplicate")
	public void testWithDetectDuplicate()throws Exception
	{
		try {
			String fcName = "Reference FC";
			String fileSourceName = "Reference FS";
			FileCollectionHelper fileCollection = new FileCollectionHelper();
			int row = fileCollection.navigateToFileCollection(fcName);
			String detailScreenTitle = NavigationHelper.navigateToEdit("SearchGrid", row, "FileCollection_Name");
			
			fileCollection.setDetectDuplicateFile(true, false);
			fileCollection.saveFileCollection(fcName, detailScreenTitle, true);
			
			FileSourceHelper fileSource = new FileSourceHelper();
			fileSource.reintialiseFileSource(fileSourceName);
			
			// Start Controllers
			ControllerHelper controller = new ControllerHelper();
			controller.startStreamController();
			controller.startTaskController(configProp.getTaskControllerExeFile());
			String dataDir = configProp.getDataDirPath();
			FileHelper.copyFile(applicationOS, automationPath + dataLocation, dataDir + "/Input/" + folderName, "Data_Load_Sample1.dat", "Sample4.dat", true);
			
			CollectedFilesHelper collectedFiles = new CollectedFilesHelper();
			collectedFiles.verifyCollectedFiles(path, fileName, sheetName, "CollectedFiles", 1);
			
			TaskSearchHelper taskSearch = new TaskSearchHelper();
			taskSearch.verifyTaskCount(path, fileName, sheetName, "ETLTaskCount", 1);
			
			FileHelper.copyFile(applicationOS, automationPath + dataLocation, dataDir + "/Input/" + folderName, "Data_Load_Sample1.dat", "Sample5.dat", true);
			fileCollection.scheduleFileCollection(fcName);
			collectedFiles.verifyCollectedFiles(path, fileName, sheetName, "CollectedFiles", 2);
			taskSearch.verifyTaskCount(path, fileName, sheetName, "ETLTaskCount", 2);
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	@Test(priority=2, description="File Collection with Detect Duplicate and Process", dependsOnMethods = { "testWithDetectDuplicate" })
	public void testWithDetectDuplicateProcess()throws Exception
	{
		try {
			String fcName = "Reference FC";
			String fileSourceName = "Reference FS";
			FileCollectionHelper fileCollection = new FileCollectionHelper();
			int row = fileCollection.navigateToFileCollection(fcName);
			String detailScreenTitle = NavigationHelper.navigateToEdit("SearchGrid", row, "FileCollection_Name");
			
			fileCollection.setDetectDuplicateFile(true, true);
			fileCollection.saveFileCollection(fcName, detailScreenTitle, true);
			
			FileSourceHelper fileSource = new FileSourceHelper();
			fileSource.reintialiseFileSource(fileSourceName);
			
			String dataDir = configProp.getDataDirPath();
			FileHelper.copyFile(applicationOS, automationPath + dataLocation, dataDir + "/Input/" + folderName, "Data_Load_Sample1.dat", "Sample6.dat", true);
			
			CollectedFilesHelper collectedFiles = new CollectedFilesHelper();
			collectedFiles.verifyCollectedFiles(path, fileName, sheetName, "CollectedFiles", 3);
			
			TaskSearchHelper taskSearch = new TaskSearchHelper();
			taskSearch.verifyTaskCount(path, fileName, sheetName, "ETLTaskCount", 3);
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