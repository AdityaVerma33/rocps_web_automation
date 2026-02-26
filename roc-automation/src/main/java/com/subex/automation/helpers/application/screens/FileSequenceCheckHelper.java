package com.subex.automation.helpers.application.screens;

import java.util.ArrayList;
import java.util.HashMap;

import com.subex.automation.helpers.application.NavigationHelper;
import com.subex.automation.helpers.application.ROCHelper;
import com.subex.automation.helpers.component.ButtonHelper;
import com.subex.automation.helpers.component.CheckBoxHelper;
import com.subex.automation.helpers.component.EntitySearchHelper;
import com.subex.automation.helpers.component.GenericHelper;
import com.subex.automation.helpers.component.GridHelper;
import com.subex.automation.helpers.component.LabelHelper;
import com.subex.automation.helpers.component.SearchGridHelper;
import com.subex.automation.helpers.component.TabHelper;
import com.subex.automation.helpers.component.TextBoxHelper;
import com.subex.automation.helpers.data.ValidationHelper;
import com.subex.automation.helpers.file.ExcelReader;
import com.subex.automation.helpers.report.Log4jHelper;
import com.subex.automation.helpers.scripts.TestDataHelper;
import com.subex.automation.helpers.selenium.ROCAcceptanceTest;
import com.subex.automation.helpers.util.FailureHelper;

public class FileSequenceCheckHelper extends ROCAcceptanceTest {
	
	public void createFileSequenceCheck(String path, String WorkbookName, String workSheetName, String testCaseName, int occurence) throws Exception {
		try {
			TestDataHelper testData = new TestDataHelper();
			ExcelReader excelReader = new ExcelReader();
			HashMap<String, ArrayList<String>> excelData = excelReader.readDataByColumn( path, WorkbookName, workSheetName, testCaseName, occurence );
	
			for(int i = 0; i < excelData.get("Name").size(); i++)
			{
				String partition = excelData.get("Partition").get(i);
				String name = excelData.get("Name").get(i);
				
				String[] groupInfo = testData.getStringValue(excelData.get("Group Info").get(i), firstLevelDelimiter);
				String[] sequenceInfo = testData.getStringValue(excelData.get("Sequence Info").get(i), firstLevelDelimiter);
				
				boolean variableLengthSeq = ValidationHelper.isTrue(excelData.get("Variable Length Sequence").get(i));
				String initialRunLookback = excelData.get("Initial Run Lookback").get(i);
				String settlingTime = excelData.get("Settling Time").get(i);
				String timeTolerance = excelData.get("Time Tolerance").get(i);
				
				boolean generateAlert = ValidationHelper.isTrue(excelData.get("Generate Alert").get(i));
				boolean generateExpiredFileAlert = ValidationHelper.isTrue(excelData.get("Generate Expired File Alert").get(i));
				boolean retainSequenceNumber = ValidationHelper.isTrue(excelData.get("Retain Sequence Number").get(i));
				boolean generateDuplicateFile = ValidationHelper.isTrue(excelData.get("Generate Duplicate File").get(i));
				
				String cancellationLookback = excelData.get("Cancellation Lookback").get(i);
				String rangeAlertThreshold = excelData.get("Range Alert Threshold").get(i);
				String runCheckTolerance = excelData.get("Run Check Tolerance").get(i);
				
				String[][] fileCollection = testData.getStringValue(excelData.get("File Collection").get(i), firstLevelDelimiter, secondLevelDelimiter);
				boolean runTaskAfterFailure = ValidationHelper.isTrue(excelData.get("Run Task After Failure").get(i));
				
				String frequencyMultiplier = excelData.get("Frequency Multiplier").get(i);
				String frequency = excelData.get("Frequency").get(i);
				String nextSchedule = excelData.get("Next Schedule").get(i);
				String[][] dayGroups = testData.getStringValue(excelData.get("Day Groups").get(i), firstLevelDelimiter, secondLevelDelimiter);
				
				createFileSequenceCheck(partition, name, groupInfo, sequenceInfo, variableLengthSeq, initialRunLookback, settlingTime, timeTolerance,
						generateAlert, generateExpiredFileAlert, retainSequenceNumber, generateDuplicateFile, cancellationLookback, rangeAlertThreshold,
						runCheckTolerance, fileCollection, runTaskAfterFailure, frequencyMultiplier, frequency, nextSchedule, dayGroups);
			}
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public void createFileSequenceCheck(String partition, String name, String[] groupInfo, String[] sequenceInfo, boolean variableLengthSeq,
			String initialRunLookback, String settlingTime, String timeTolerance, boolean generateAlert, boolean generateExpiredFileAlert,
			boolean retainSequenceNumber, boolean generateDuplicateFile, String cancellationLookback, String rangeAlertThreshold, 
			String runCheckTolerance, String[][] fileCollection, boolean runTaskAfterFailure, String frequencyMultiplier, String frequency,
			String nextSchedule, String[][] dayGroups) throws Exception {
		try {
			NavigationHelper.navigateToScreen("File Sequence Check", "File Sequence Check Search");
			int row = SearchGridHelper.gridFilterSearchWithTextBox("FSC_Name", name, "Name");

			if (row > 0) {
				Log4jHelper.logWarning("File Sequence Check '" + name + "' is already present.");
			}
			else {
				String detailScreenTitle = NavigationHelper.navigateToNew(partition, "FSC_Name");
				
				updateFileSequenceCheck(name, groupInfo, sequenceInfo, variableLengthSeq, initialRunLookback, settlingTime, timeTolerance,
						generateAlert, generateExpiredFileAlert, retainSequenceNumber, generateDuplicateFile, cancellationLookback, rangeAlertThreshold,
						runCheckTolerance, fileCollection, runTaskAfterFailure, frequencyMultiplier, frequency, nextSchedule, dayGroups);
				
				saveFileSequenceCheck(name, detailScreenTitle);
			}
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public void scheduleFileSequenceCheck(String path, String WorkbookName, String workSheetName, String testCaseName, int occurence) throws Exception {
		try {
			ExcelReader excelReader = new ExcelReader();
			HashMap<String, ArrayList<String>> excelData = excelReader.readDataByColumn( path, WorkbookName, workSheetName, testCaseName, occurence );
	
			for(int i = 0; i < excelData.get("Name").size(); i++)
			{
				String name = excelData.get("Name").get(i);
				
				scheduleFileSequenceCheck(name);
			}
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public void scheduleFileSequenceCheck(String name) throws Exception {
		try {
			NavigationHelper.navigateToScreen("File Sequence Check", "File Sequence Check Search");
			int row = SearchGridHelper.gridFilterSearchWithTextBox("FSC_Name", name, "Name");

			if (row > 0) {
				GridHelper.clickRow("SearchGrid", row, 1);
				NavigationHelper.navigateToAction("File Sequence Check Actions");
				if (NavigationHelper.isActionPresent("Schedule"))
					NavigationHelper.navigateToAction("Schedule");
				else
					NavigationHelper.navigateToAction("Reschedule");
				GenericHelper.waitForLoadmask(searchScreenWaitSec);
				Log4jHelper.logInfo("File Sequence Check '" + name + "' is scheduled.");
			}
			else {
				FailureHelper.failTest("File Sequence Check '" + name + "' is not found.");
			}
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public void verifyFileSequenceCheckStatus(String path, String WorkbookName, String workSheetName, String testCaseName, int occurence) throws Exception {
		try {
			TestDataHelper testData = new TestDataHelper();
			ExcelReader excelReader = new ExcelReader();
			HashMap<String, ArrayList<String>> excelData = excelReader.readDataByColumn( path, WorkbookName, workSheetName, testCaseName, occurence );
	
			for(int i = 0; i < excelData.get("Name").size(); i++)
			{
				String name = excelData.get("Name").get(i);
				String expectedStatus = excelData.get("Expected Status").get(i);
				int waitTimeInSecs = testData.getIntegerValue(excelData.get("Task Wait Time In Secs").get(i));
				
				verifyFileSequenceCheckStatus(name, expectedStatus, waitTimeInSecs);
			}
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public void verifyFileSequenceCheckStatus(String name, String expectedStatus, int waitTimeInSecs) throws Exception {
		try {
			NavigationHelper.navigateToScreen("File Sequence Check", "File Sequence Check Search");
			int row = SearchGridHelper.gridFilterSearchWithTextBox("FSC_Name", name, "Name");

			if (row > 0) {
				String actualStatus = null;
				int tryCount = 0;
				
				while (true) {
					ButtonHelper.click("SearchButton");
					GenericHelper.waitForLoadmask(searchScreenWaitSec);
					actualStatus = GridHelper.getCellValue("SearchGrid", row, "Status");
					
					if (actualStatus != null && actualStatus.trim().equals(expectedStatus)) {
						break;
					}
					else if (tryCount > (waitTimeInSecs/1000)) {
						FailureHelper.failTest("File Sequence Check '" + name + "' is supposed to be of Status '" + expectedStatus + 
								"'. But current Status is  '" + actualStatus + "'");
					}
					else {
						Thread.sleep(1000);
						tryCount++;
					}
				}
			}
			else {
				FailureHelper.failTest("File Sequence Check '" + name + "' is not found.");
			}
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public void updateFileSequenceCheck(String name, String[] groupInfo, String[] sequenceInfo, boolean variableLengthSeq,
			String initialRunLookback, String settlingTime, String timeTolerance, boolean generateAlert, boolean generateExpiredFileAlert,
			boolean retainSequenceNumber, boolean generateDuplicateFile, String cancellationLookback, String rangeAlertThreshold, 
			String runCheckTolerance, String[][] fileCollection, boolean runTaskAfterFailure, String frequencyMultiplier, String frequency,
			String nextSchedule, String[][] dayGroups) throws Exception {
		try {
			TextBoxHelper.type("FSC_Name", name);
			
			for (int i = 0; i < groupInfo.length; i++) {
				GridHelper.updateGridTextBox("FSC_GroupInfo_Grid", "FSC_GroupInfo_TextBox", (i+1), 2, "Property", groupInfo[i]);
			}
			
			for (int i = 0; i < sequenceInfo.length; i++) {
				GridHelper.updateGridTextBox("FSC_SequenceInfo_Grid", "FSC_SequenceInfo_TextBox", (i+1), 2, "Property", sequenceInfo[i]);
			}
						
			if (variableLengthSeq)
				CheckBoxHelper.check("FSC_VariableLengthSequence");
			
			TextBoxHelper.type("FSC_InitialRunLookback", initialRunLookback);
			TextBoxHelper.type("FSC_SettlingTime", settlingTime);
			TextBoxHelper.type("FSC_TimeTolerance", timeTolerance);
			
			if (generateAlert)
				CheckBoxHelper.check("FSC_GenerateAlert");
			
			if (generateExpiredFileAlert)
				CheckBoxHelper.check("FSC_GenerateExpiredFileAlert");
			
			if (retainSequenceNumber)
				CheckBoxHelper.check("FSC_RetainSequenceNumber");
			
			if (generateDuplicateFile)
				CheckBoxHelper.check("FSC_GenerateDuplicateFile");
			
			TextBoxHelper.type("FSC_CancellationLookback", cancellationLookback);
			TextBoxHelper.type("FSC_RangeAlertThreshold", rangeAlertThreshold);
			TextBoxHelper.type("FSC_RunCheckTolerance", runCheckTolerance);
			
			addSchedule(fileCollection, runTaskAfterFailure, frequencyMultiplier, frequency, nextSchedule, dayGroups);
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public void addSchedule(String[][] fileCollection, boolean runTaskAfterFailure, String frequencyMultiplier, String frequency, String nextSchedule,
			String[][] dayGroups) throws Exception {
		try {
			TabHelper.gotoTab("Schedule");
			EntitySearchHelper entitySearch = new EntitySearchHelper();
			
			for (int i = 0; i < fileCollection.length; i++) {
				ButtonHelper.click("FSC_FileCollections_Add");
				GenericHelper.waitForLoadmask(detailScreenWaitSec);
				entitySearch.selectUsingGridFilterTextBox("File Collection Search", "FileCollection_Name", fileCollection[i][0], "Name");
				int row = GridHelper.getRowNumber("FSC_FileCollections_Grid", fileCollection[i][0], "File Collection");
				
				GridHelper.updateGridTextBox("FSC_FileCollections_Grid", "FSC_FileCollections_FilelistMask", row, "Filelist Mask", "File Collection", fileCollection[i][1]);
				
				GridHelper.updateGridCheckBox("FSC_FileCollections_Grid", "FSC_FileCollections_Enable", row, "Enable", fileCollection[i][2]);
			}
			
			if (runTaskAfterFailure)
				CheckBoxHelper.check("FSC_RunTaskAfterFailure");
			
			ROCHelper rocHelper = new ROCHelper();
			rocHelper.updateCollectionTimes(frequencyMultiplier, frequency, nextSchedule, dayGroups);
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	private void saveFileSequenceCheck(String name, String detailScreenTitle) throws Exception {
		try {
			ButtonHelper.click("SaveButton");
			GenericHelper.waitForLoadmask(detailScreenWaitSec);
			
			assertTrue(LabelHelper.isTitleNotPresent(detailScreenTitle), "File Sequence Check save did not happen");
			ButtonHelper.click("SearchButton");
			GenericHelper.waitForLoadmask(searchScreenWaitSec);
			assertTrue(GridHelper.isValuePresent("SearchGrid", name, "Name"), "Value '" + name + "' is not found in grid.");
			Log4jHelper.logInfo("File Sequence Check '" + name + "' created.");
		} catch (AssertionError e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
}