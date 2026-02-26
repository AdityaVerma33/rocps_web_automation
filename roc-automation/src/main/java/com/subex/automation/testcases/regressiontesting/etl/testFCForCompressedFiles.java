package com.subex.automation.testcases.regressiontesting.etl;

import org.testng.annotations.Test;

import com.subex.automation.helpers.application.ControllerHelper;
import com.subex.automation.helpers.application.NavigationHelper;
import com.subex.automation.helpers.application.screens.CollectedFilesHelper;
import com.subex.automation.helpers.application.screens.FileCollectionHelper;
import com.subex.automation.helpers.application.screens.FileSourceHelper;
import com.subex.automation.helpers.application.screens.TaskSearchHelper;
import com.subex.automation.helpers.component.CheckBoxHelper;
import com.subex.automation.helpers.component.ComboBoxHelper;
import com.subex.automation.helpers.component.FileHelper;
import com.subex.automation.helpers.selenium.ROCAcceptanceTest;
import com.subex.automation.helpers.util.FailureHelper;

public class testFCForCompressedFiles extends ROCAcceptanceTest {
	
	private static String path = null;
	final String fileName = "ETLRegression_TestData.xlsx";
	final String sheetName = "CompressedFiles";
	final String dataLocation = "\\src\\main\\resources\\Data\\ASCII";
	final String folderName = "Reference";
	
	public testFCForCompressedFiles() throws Exception {
		try {
			path = automationPath + "\\src\\main\\resources\\Regression\\";
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	@Test(priority=1, description="File Collection for Compressed Files")
	public void testCompressedFilesFC()throws Exception
	{
		try {
			String fcName = "Reference FC";
			String fileSourceName = "Reference FS";
			FileCollectionHelper fileCollection = new FileCollectionHelper();
			int row = fileCollection.navigateToFileCollection(fcName);
			String detailScreenTitle = NavigationHelper.navigateToEdit("SearchGrid", row, "FileCollection_RelativePath");
			
			fileCollection.setDetectDuplicateFile(false, false);
			CheckBoxHelper.uncheck("FileCollection_ZipCollectedFiles");
			ComboBoxHelper.select("FileCollection_CompressionType", "ZIP Compression");
			fileCollection.saveFileCollection(fcName, detailScreenTitle, true);
			
			FileSourceHelper fileSource = new FileSourceHelper();
			fileSource.reintialiseFileSource(fileSourceName);
			
			// Start Controllers
			ControllerHelper controller = new ControllerHelper();
			controller.startStreamController();
			controller.startTaskController(configProp.getTaskControllerExeFile());
			String dataDir = configProp.getDataDirPath();
			FileHelper.copyFile(applicationOS, automationPath + dataLocation, dataDir + "/Input/" + folderName, "ASCII_Data.zip", "ASCII_Data.zip", true);
			
			CollectedFilesHelper collectedFiles = new CollectedFilesHelper();
			collectedFiles.verifyCollectedFiles(path, fileName, sheetName, "CollectedFiles", 1);
			collectedFiles.viewParseStatistics(path, fileName, sheetName, "ParseStatistics", 1);
			collectedFiles.viewCompressedEntries(path, fileName, sheetName, "CompressedEntries", 1);
			
			TaskSearchHelper taskSearch = new TaskSearchHelper();
			taskSearch.verifyTaskCount(path, fileName, sheetName, "ETLTaskCount", 1);
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