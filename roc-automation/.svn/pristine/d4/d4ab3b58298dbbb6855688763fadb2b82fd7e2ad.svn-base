package com.subex.automation.helpers.application.screens;

import java.util.ArrayList;
import java.util.HashMap;

import com.subex.automation.helpers.application.NavigationHelper;
import com.subex.automation.helpers.application.ROCHelper;
import com.subex.automation.helpers.component.ButtonHelper;
import com.subex.automation.helpers.component.CalendarHelper;
import com.subex.automation.helpers.component.CheckBoxHelper;
import com.subex.automation.helpers.component.ComboBoxHelper;
import com.subex.automation.helpers.component.EntityComboHelper;
import com.subex.automation.helpers.component.GenericHelper;
import com.subex.automation.helpers.component.GridHelper;
import com.subex.automation.helpers.component.LabelHelper;
import com.subex.automation.helpers.component.RadioHelper;
import com.subex.automation.helpers.component.SearchGridHelper;
import com.subex.automation.helpers.component.TabHelper;
import com.subex.automation.helpers.component.TextBoxHelper;
import com.subex.automation.helpers.data.ValidationHelper;
import com.subex.automation.helpers.file.ExcelReader;
import com.subex.automation.helpers.report.Log4jHelper;
import com.subex.automation.helpers.scripts.TestDataHelper;
import com.subex.automation.helpers.selenium.ROCAcceptanceTest;
import com.subex.automation.helpers.util.FailureHelper;

public class FileCollectionHelper extends ROCAcceptanceTest {
	
	public void createFileCollection(String path, String WorkbookName, String workSheetName, String testCaseName, int occurence) throws Exception {
		try {
			TestDataHelper testData = new TestDataHelper();
			ExcelReader excelReader = new ExcelReader();
			HashMap<String, ArrayList<String>> excelData = excelReader.readDataByColumn( path, WorkbookName, workSheetName, testCaseName, occurence );
	
			for(int i = 0; i < excelData.get("Name").size(); i++)
			{
				String partition = excelData.get("Partition").get(i);
				String fcName = excelData.get("Name").get(i);
				boolean manualFileTransfer = ValidationHelper.isTrue(excelData.get("Manual File Transfer").get(i));
				String fileSource = excelData.get("File Source").get(i);
				String relativePath = excelData.get("Relative Path").get(i);
				String fileListMask = excelData.get("Filelist Mask").get(i);
				String validationMask = excelData.get("Validation Mask").get(i);
				String compressionType = excelData.get("Compression Type").get(i);
				String outputPath = excelData.get("Output Path").get(i);
				
				boolean enableMaxRetries = ValidationHelper.isTrue(excelData.get("Enable Max Retries").get(i));
				String maxRetries = excelData.get("Max Retries").get(i);
				String retrySeconds = excelData.get("Retry Seconds").get(i);
				boolean detectDuplicates = ValidationHelper.isTrue(excelData.get("Detect Duplicates").get(i));
				boolean processDuplicates = ValidationHelper.isTrue(excelData.get("Process Duplicates").get(i));
				boolean oldestFileFirst = ValidationHelper.isTrue(excelData.get("Oldest File First").get(i));
				boolean latestFileFirst = ValidationHelper.isTrue(excelData.get("Latest File First").get(i));
				boolean sequenceBased = ValidationHelper.isTrue(excelData.get("Sequence Based").get(i));
				String idleAlertInterval = excelData.get("Idle Alert Interval").get(i);
				
				String streamName = excelData.get("Stream").get(i);
				String streamStageName = excelData.get("Stream Stage").get(i);
				String collectionAction = excelData.get("Collection Action").get(i);
				String movePath = excelData.get("Move Path").get(i);
				String renamePrefix = excelData.get("Rename Prefix").get(i);
				String renameSuffix = excelData.get("Rename Suffix").get(i);
				boolean zipCollectedFiles = ValidationHelper.isTrue(excelData.get("Zip Collected Files").get(i));
				
				String frequencyMultiplier = excelData.get("Frequency Multiplier").get(i);
				String frequency = excelData.get("Frequency").get(i);
				String nextSchedule = excelData.get("Next Schedule").get(i);
				String[][] dayGroups = testData.getStringValue(excelData.get("Day Groups").get(i), firstLevelDelimiter, secondLevelDelimiter);
				
				boolean enableRecordIntegrityCheck = ValidationHelper.isTrue(excelData.get("Enable Record Integrity Check").get(i));
				boolean isAutomaticRIC = ValidationHelper.isTrue(excelData.get("Is Automatic Record Integrity Check").get(i));
				String minRecordCount = excelData.get("Minimum Record Count").get(i);
				String maxRecordCount = excelData.get("Maximum Record Count").get(i);
				
				boolean enableFileIntegrityCheck = ValidationHelper.isTrue(excelData.get("Enable File Integrity Check").get(i));
				boolean isAutomaticFIC = ValidationHelper.isTrue(excelData.get("Is Automatic File Integrity Check").get(i));
				String minFileSize = excelData.get("Minimum File Size").get(i);
				String maxFileSize = excelData.get("Maximum File Size").get(i);
				
				createFileCollection(partition, fcName, manualFileTransfer, fileSource, relativePath, fileListMask, validationMask, compressionType,
						outputPath, enableMaxRetries, maxRetries, retrySeconds, detectDuplicates, processDuplicates, oldestFileFirst,
						latestFileFirst, sequenceBased, idleAlertInterval, streamName, streamStageName, collectionAction, movePath,
						renamePrefix, renameSuffix, zipCollectedFiles, frequencyMultiplier, frequency, nextSchedule, dayGroups, enableRecordIntegrityCheck,
						isAutomaticRIC, minRecordCount, maxRecordCount, enableFileIntegrityCheck, isAutomaticFIC, minFileSize, maxFileSize);
			}
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public void createFileCollection(String partition, String fcName, boolean manualFileTransfer, String fileSource, String relativePath, String fileListMask,
			String validationMask, String compressionType, String outputPath, boolean enableMaxRetries, String maxRetries, String retrySeconds,
			boolean detectDuplicates, boolean processDuplicates, boolean oldestFileFirst, boolean latestFileFirst, boolean sequenceBased,
			String idleAlertInterval, String streamName, String streamStageName, String collectionAction, String movePath, String renamePrefix,
			String renameSuffix, boolean zipCollectedFiles, String frequencyMultiplier, String frequency, String nextSchedule,
			String[][] dayGroups, boolean enableRecordIntegrityCheck, boolean isAutomaticRIC, String minRecordCount, String maxRecordCount,
			boolean enableFileIntegrityCheck, boolean isAutomaticFIC, String minFileSize, String maxFileSize) throws Exception {
		try {
			int row = navigateToFileCollection(fcName);
			boolean isPresent = NavigationHelper.navigateToNewOrEdit(row, partition, "File Collection", "FileCollection_Name");
			String detailScreenTitle = NavigationHelper.getScreenTitle();
			
			updateFileCollection(fcName, manualFileTransfer, fileSource, relativePath, fileListMask, validationMask, compressionType, outputPath,
					enableMaxRetries, maxRetries, retrySeconds, detectDuplicates, processDuplicates, oldestFileFirst, latestFileFirst,sequenceBased,
					idleAlertInterval, streamName, streamStageName, collectionAction, movePath, renamePrefix, renameSuffix, zipCollectedFiles,
					frequencyMultiplier, frequency, nextSchedule, dayGroups, enableRecordIntegrityCheck, isAutomaticRIC, minRecordCount, maxRecordCount,
					enableFileIntegrityCheck, isAutomaticFIC, minFileSize, maxFileSize);
			
			saveFileCollection(fcName, detailScreenTitle, isPresent);
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public void updateFileCollection(String path, String WorkbookName, String workSheetName, String testCaseName, int occurence) throws Exception {
		try {
			TestDataHelper testData = new TestDataHelper();
			ExcelReader excelReader = new ExcelReader();
			HashMap<String, ArrayList<String>> excelData = excelReader.readDataByColumn( path, WorkbookName, workSheetName, testCaseName, occurence );
	
			for(int i = 0; i < excelData.get("Name").size(); i++)
			{
				String fcName = excelData.get("Name").get(i);
				boolean manualFileTransfer = ValidationHelper.isTrue(excelData.get("Manual File Transfer").get(i));
				String fileSource = excelData.get("File Source").get(i);
				String relativePath = excelData.get("Relative Path").get(i);
				String fileListMask = excelData.get("Filelist Mask").get(i);
				String validationMask = excelData.get("Validation Mask").get(i);
				String compressionType = excelData.get("Compression Type").get(i);
				String outputPath = excelData.get("Output Path").get(i);
				
				boolean enableMaxRetries = ValidationHelper.isTrue(excelData.get("Enable Max Retries").get(i));
				String maxRetries = excelData.get("Max Retries").get(i);
				String retrySeconds = excelData.get("Retry Seconds").get(i);
				boolean detectDuplicates = ValidationHelper.isTrue(excelData.get("Detect Duplicates").get(i));
				boolean processDuplicates = ValidationHelper.isTrue(excelData.get("Process Duplicates").get(i));
				boolean oldestFileFirst = ValidationHelper.isTrue(excelData.get("Oldest File First").get(i));
				boolean latestFileFirst = ValidationHelper.isTrue(excelData.get("Latest File First").get(i));
				boolean sequenceBased = ValidationHelper.isTrue(excelData.get("Sequence Based").get(i));
				String idleAlertInterval = excelData.get("Idle Alert Interval").get(i);
				
				String streamName = excelData.get("Stream").get(i);
				String streamStageName = excelData.get("Stream Stage").get(i);
				String collectionAction = excelData.get("Collection Action").get(i);
				String movePath = excelData.get("Move Path").get(i);
				String renamePrefix = excelData.get("Rename Prefix").get(i);
				String renameSuffix = excelData.get("Rename Suffix").get(i);
				boolean zipCollectedFiles = ValidationHelper.isTrue(excelData.get("Zip Collected Files").get(i));
				
				String frequencyMultiplier = excelData.get("Frequency Multiplier").get(i);
				String frequency = excelData.get("Frequency").get(i);
				String nextSchedule = excelData.get("Next Schedule").get(i);
				String[][] dayGroups = testData.getStringValue(excelData.get("Day Groups").get(i), firstLevelDelimiter, secondLevelDelimiter);
				
				boolean enableRecordIntegrityCheck = ValidationHelper.isTrue(excelData.get("Enable Record Integrity Check").get(i));
				boolean isAutomaticRIC = ValidationHelper.isTrue(excelData.get("Is Automatic Record Integrity Check").get(i));
				String minRecordCount = excelData.get("Minimum Record Count").get(i);
				String maxRecordCount = excelData.get("Maximum Record Count").get(i);
				
				boolean enableFileIntegrityCheck = ValidationHelper.isTrue(excelData.get("Enable File Integrity Check").get(i));
				boolean isAutomaticFIC = ValidationHelper.isTrue(excelData.get("Is Automatic File Integrity Check").get(i));
				String minFileSize = excelData.get("Minimum File Size").get(i);
				String maxFileSize = excelData.get("Maximum File Size").get(i);
				
				updateFileCollection(fcName, manualFileTransfer, fileSource, relativePath, fileListMask, validationMask, compressionType, outputPath,
						enableMaxRetries, maxRetries, retrySeconds, detectDuplicates, processDuplicates, oldestFileFirst, latestFileFirst, sequenceBased,
						idleAlertInterval, streamName, streamStageName, collectionAction, movePath, renamePrefix, renameSuffix, zipCollectedFiles,
						frequencyMultiplier, frequency, nextSchedule, dayGroups, enableRecordIntegrityCheck, isAutomaticRIC, minRecordCount, maxRecordCount,
						enableFileIntegrityCheck, isAutomaticFIC, minFileSize, maxFileSize);
			}
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public void updateFileCollection(String fcName, boolean manualFileTransfer, String fileSource, String relativePath, String fileListMask,
			String validationMask, String compressionType, String outputPath, boolean enableMaxRetries, String maxRetries, String retrySeconds,
			boolean detectDuplicates, boolean processDuplicates, boolean oldestFileFirst, boolean latestFileFirst, boolean sequenceBased,
			String idleAlertInterval, String streamName, String streamStageName, String collectionAction, String movePath, String renamePrefix,
			String renameSuffix, boolean zipCollectedFiles, String frequencyMultiplier, String frequency, String nextSchedule,
			String[][] dayGroups, boolean enableRecordIntegrityCheck, boolean isAutomaticRIC, String minRecordCount, String maxRecordCount,
			boolean enableFileIntegrityCheck, boolean isAutomaticFIC, String minFileSize, String maxFileSize) throws Exception {
		try {
			TextBoxHelper.type("FileCollection_Name", fcName);
			if (manualFileTransfer)
				CheckBoxHelper.check("FileCollection_ManualFileTransfer");
			EntityComboHelper.selectUsingGridFilterTextBox("FileCollection_FileSource", "File Source Search", "FileSource_Name", fileSource, "Name");
			TextBoxHelper.type("FileCollection_RelativePath", relativePath);
			TextBoxHelper.type("FileCollection_FilelistMask", fileListMask);
			TextBoxHelper.type("FileCollection_ValidationMask", validationMask);
			ComboBoxHelper.select("FileCollection_CompressionType", compressionType);
			GenericHelper.selectDataDir("FileCollection_OutputPath", outputPath, configProp.getThirdLevelDelimiter());
			
			setMaximumRetries(enableMaxRetries, maxRetries, retrySeconds);
			setDetectDuplicateFile(detectDuplicates, processDuplicates);
			setFileCollectionOrder(oldestFileFirst, latestFileFirst, sequenceBased);
			TextBoxHelper.type("FileCollection_IdleAlertInterval", idleAlertInterval);
			
			postCollectionAction(streamName, streamStageName, collectionAction, movePath, renamePrefix, renameSuffix, zipCollectedFiles);
			TabHelper.gotoTab("Collection Times");
			ROCHelper rocHelper = new ROCHelper();
			rocHelper.updateCollectionTimes(frequencyMultiplier, frequency, nextSchedule, dayGroups);
			
			setRecordIntegrityCheck(enableRecordIntegrityCheck, isAutomaticRIC, minRecordCount, maxRecordCount);
			setFileSizeCheck(enableFileIntegrityCheck, isAutomaticFIC, minFileSize, maxFileSize);
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public void scheduleFileCollection(String path, String WorkbookName, String workSheetName, String testCaseName, int occurence) throws Exception {
		try {
			ExcelReader excelReader = new ExcelReader();
			HashMap<String, ArrayList<String>> excelData = excelReader.readDataByColumn( path, WorkbookName, workSheetName, testCaseName, occurence );
	
			for(int i = 0; i < excelData.get("Name").size(); i++)
			{
				String fcName = excelData.get("Name").get(i);
				
				scheduleFileCollection(fcName);
			}
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public void scheduleFileCollection(String fcName) throws Exception {
		try {
			int row = navigateToFileCollection(fcName);
			
			if (row > 0) {
				String detailScreenTitle = NavigationHelper.navigateToEdit("SearchGrid", row, "FileCollection_Name");
				
				TabHelper.gotoTab("Collection Times");
				CalendarHelper.setNow("Scheduling_NextScheduled");
				
				saveFileCollection(fcName, detailScreenTitle, true);
			}
			else {
				FailureHelper.failTest("File Collection '" + fcName + "' is not found.");
			}
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public int navigateToFileCollection(String fcName) throws Exception {
		try {
			NavigationHelper.navigateToScreen( "File Collections", "File Collection Search" );
			int row = SearchGridHelper.gridFilterSearchWithTextBox("FileCollection_Name", fcName, "Name");
			
			return row;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public void setMaximumRetries(boolean enableMaxRetries, String maxRetries, String retrySeconds) throws Exception {
		try {
			if (enableMaxRetries) {
				CheckBoxHelper.check("FileCollection_MaxRetries_CheckBox");
				TextBoxHelper.type("FileCollection_MaxRetries_TextBox", maxRetries);
				TextBoxHelper.type("FileCollection_RetrySeconds", retrySeconds);
			}
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public void setDetectDuplicateFile(boolean detectDuplicates, boolean processDuplicates) throws Exception {
		try {
			if (detectDuplicates) {
				CheckBoxHelper.check("FileCollection_DuplicateFile_Detect");
				
				if (processDuplicates)
					CheckBoxHelper.check("FileCollection_DuplicateFile_Process");
			}
			else {
				CheckBoxHelper.uncheck("FileCollection_DuplicateFile_Detect");
				
				if (!processDuplicates)
					CheckBoxHelper.uncheck("FileCollection_DuplicateFile_Process");
			}
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public void setFileCollectionOrder(boolean oldestFileFirst, boolean latestFileFirst, boolean sequenceBased) throws Exception {
		try {
			if (oldestFileFirst)
				CheckBoxHelper.check("FileCollection_OldestFileFirst");
			
			if (latestFileFirst)
				CheckBoxHelper.check("FileCollection_LatestFileFirst");
			
			if (sequenceBased)
				CheckBoxHelper.check("FileCollection_SequenceBased");
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public void postCollectionAction(String streamName, String streamStageName, String collectionAction, String movePath, String renamePrefix,
			String renameSuffix, boolean zipCollectedFiles) throws Exception {
		try {
			if (ValidationHelper.isNotEmpty(streamName)) {
				ComboBoxHelper.select("FileCollection_Stream", streamName);
				ComboBoxHelper.select("FileCollection_StreamStage", streamStageName);
			}
			
			if (zipCollectedFiles)
				CheckBoxHelper.check("FileCollection_ZipCollectedFiles");
			
			ComboBoxHelper.select("FileCollection_CollectionAction", collectionAction);
			switch (collectionAction) {
			case "Move":
				TextBoxHelper.type("FileCollection_MaxRetries_TextBox", movePath);
				break;
				
			case "Rename":
				TextBoxHelper.type("FileCollection_MaxRetries_TextBox", renamePrefix);
				TextBoxHelper.type("FileCollection_RetrySeconds", renameSuffix);
				break;
				
			default:
				break;
			}
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public void setRecordIntegrityCheck(boolean enableRecordIntegrityCheck, boolean isAutomaticRIC, String minRecordCount, String maxRecordCount) throws Exception {
		try {
			if (enableRecordIntegrityCheck) {
				TabHelper.gotoTab("File Integrity Checks");
				CheckBoxHelper.check("FileCollection_RecordIntegrityCheck");
				
				if (isAutomaticRIC)
					RadioHelper.click("FileCollection_RecordIntegrityCheck_Automatic");
				else
					RadioHelper.click("FileCollection_RecordIntegrityCheck_Manual");
				TextBoxHelper.type("FileCollection_MinimumRecordCount", minRecordCount);
				TextBoxHelper.type("FileCollection_MaximumRecordCount", maxRecordCount);
			}
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public void setFileSizeCheck(boolean enableFileIntegrityCheck, boolean isAutomaticFIC, String minFileSize, String maxFileSize) throws Exception {
		try {
			if (enableFileIntegrityCheck) {
				TabHelper.gotoTab("File Integrity Checks");
				CheckBoxHelper.check("FileCollection_FileIntegrityCheck");
				
				if (isAutomaticFIC)
					RadioHelper.click("FileCollection_FileIntegrityCheck_Automatic");
				else
					RadioHelper.click("FileCollection_FileIntegrityCheck_Manual");
				TextBoxHelper.type("FileCollection_MinimumFileSize", minFileSize);
				TextBoxHelper.type("FileCollection_MaximumFileSize", maxFileSize);
			}
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public void saveFileCollection(String fcName, String detailScreenTitle, boolean isPresent) throws Exception {
		try {
			ButtonHelper.click("SaveButton");
			GenericHelper.waitForLoadmask(detailScreenWaitSec);
			
			assertTrue(LabelHelper.isTitleNotPresent(detailScreenTitle), "File Collection save did not happen.");
			ButtonHelper.click("SearchButton");
			GenericHelper.waitForLoadmask(searchScreenWaitSec);
			assertTrue(GridHelper.isValuePresent("SearchGrid", fcName, "Name"), "Value '" + fcName + "' is not found in grid.");
			if (isPresent)
				Log4jHelper.logInfo("File Collection '" + fcName + "' updated.");
			else
				Log4jHelper.logInfo("File Collection '" + fcName + "' created.");
		} catch (AssertionError e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
}