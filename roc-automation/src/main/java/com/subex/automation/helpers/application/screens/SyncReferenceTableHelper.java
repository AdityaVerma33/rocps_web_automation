package com.subex.automation.helpers.application.screens;

import java.util.ArrayList;
import java.util.HashMap;

import com.subex.automation.helpers.application.NavigationHelper;
import com.subex.automation.helpers.application.ROCHelper;
import com.subex.automation.helpers.component.ButtonHelper;
import com.subex.automation.helpers.component.CheckBoxHelper;
import com.subex.automation.helpers.component.ComboBoxHelper;
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

public class SyncReferenceTableHelper extends ROCAcceptanceTest {
	
	public void createSyncReferenceTable(String path, String WorkbookName, String workSheetName, String testCaseName, int occurence) throws Exception {
		try {
			TestDataHelper testData = new TestDataHelper();
			ExcelReader excelReader = new ExcelReader();
			HashMap<String, ArrayList<String>> excelData = excelReader.readDataByColumn( path, WorkbookName, workSheetName, testCaseName, occurence );
	
			for(int i = 0; i < excelData.get("Name").size(); i++)
			{
				String partition = excelData.get("Partition").get(i);
				String name = excelData.get("Name").get(i);
				String[] tableName = testData.getStringValue(excelData.get("Table Name").get(i), firstLevelDelimiter);
				String[] sourceDSC = testData.getStringValue(excelData.get("Source DSC").get(i), firstLevelDelimiter);
				String[] destinationDSC = testData.getStringValue(excelData.get("Destination DSC").get(i), firstLevelDelimiter);
				String[] deltaColumn = testData.getStringValue(excelData.get("Delta Column").get(i), firstLevelDelimiter);
				String[] uniqueColumn = testData.getStringValue(excelData.get("Unique Column").get(i), firstLevelDelimiter);
				
				String trunateAndLoadMultiplier = excelData.get("Trunate and Load Multiplier").get(i);
				String trunateAndLoadFreq = excelData.get("Trunate and Load Frequency").get(i);
				String prevTruncatedTime = excelData.get("Previously Truncated Time").get(i);
				boolean reuseTask = ValidationHelper.isTrue(excelData.get("Reuse Task").get(i));
				
				String frequencyMultiplier = excelData.get("Frequency Multiplier").get(i);
				String frequency = excelData.get("Frequency").get(i);
				String nextSchedule = excelData.get("Next Schedule").get(i);
				String[][] dayGroups = testData.getStringValue(excelData.get("Day Groups").get(i), firstLevelDelimiter, secondLevelDelimiter);
				
				createSyncReferenceTable(partition, name, tableName, sourceDSC, destinationDSC, deltaColumn, uniqueColumn,
						trunateAndLoadMultiplier, trunateAndLoadFreq, prevTruncatedTime, reuseTask, frequencyMultiplier, frequency, nextSchedule,
						dayGroups);
			}
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public void createSyncReferenceTable(String partition, String name, String[] tableName, String[] sourceDSC, String[] destinationDSC,
			String[] deltaColumn, String[] uniqueColumn, String trunateAndLoadMultiplier, String trunateAndLoadFreq, String prevTruncatedTime,
			boolean reuseTask, String frequencyMultiplier, String frequency, String nextSchedule, String[][] dayGroups) throws Exception {
		try {
			int row = navigateToSyncReferenceTable(name);
			boolean isPresent = NavigationHelper.navigateToNewOrEdit(row, partition, "Sync Reference Table", "SyncRefTable_Name");
			String detailScreenTitle = NavigationHelper.getScreenTitle();
			
			TextBoxHelper.type("SyncRefTable_Name", name);
			
			addTable(tableName, sourceDSC, destinationDSC, deltaColumn, uniqueColumn);
			
			updateScheduleTimes(trunateAndLoadMultiplier, trunateAndLoadFreq, prevTruncatedTime, reuseTask, frequencyMultiplier, frequency,
					nextSchedule, dayGroups);
			
			saveSyncReferenceTable(name, detailScreenTitle, isPresent);
		} catch (AssertionError e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public void enableSyncReferenceTable(String path, String WorkbookName, String workSheetName, String testCaseName, int occurence) throws Exception {
		try {
			ExcelReader excelReader = new ExcelReader();
			HashMap<String, ArrayList<String>> excelData = excelReader.readDataByColumn( path, WorkbookName, workSheetName, testCaseName, occurence );
	
			for(int i = 0; i < excelData.get("Name").size(); i++)
			{
				String name = excelData.get("Name").get(i);
				enableSyncReferenceTable(name);
			}
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public void enableSyncReferenceTable(String name) throws Exception {
		try {
			int row = navigateToSyncReferenceTable(name);
			
			if (row > 0) {
				GridHelper.clickRow("SearchGrid", row, "Name");
				NavigationHelper.navigateToAction("Sync Reference Table Actions", "Enable Schedule");
				GenericHelper.waitForLoadmask(searchScreenWaitSec);
				Log4jHelper.logInfo("Schedule enabled for Synch Reference Table '" + name + "'.");
			}
			else {
				FailureHelper.failTest("Sync Reference Table '" + name + "' is not found.");
			}
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public int navigateToSyncReferenceTable(String name) throws Exception {
		try {
			NavigationHelper.navigateToScreen( "Sync Reference Tables", "Sync Reference Table Search" );
			ButtonHelper.click("ClearButton");
			GenericHelper.waitForLoadmask(searchScreenWaitSec);
			int row = SearchGridHelper.gridFilterSearchWithTextBox("SyncRefTable_Name", name, "Name");
			
			return row;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public void addTable(String[] tableName, String[] sourceDSC, String[] destinationDSC, String[] deltaColumn, String[] uniqueColumn) throws Exception {
		try {
			String gridId = "SyncRefTable_Grid";
			EntitySearchHelper entitySearch = new EntitySearchHelper();
			
			for (int i = 0; i < tableName.length; i++) {
				int rowNum = GridHelper.getRowNumber(gridId, tableName[i], "Table Name");
				
				if (rowNum == 0) {
					ButtonHelper.click("SyncRefTable_Add_Button");
					GenericHelper.waitForLoadmask(searchScreenWaitSec);
					entitySearch.selectUsingGridFilterTextBox("Sync Reference Table Search", "TableInst_TableName", tableName[i], "Table Name");
					rowNum = GridHelper.getRowNumber(gridId, tableName[i], "Table Name");
				}
				
				if (rowNum == 0) {
					FailureHelper.failTest("Table '" + tableName[i] + "' is not added to Sync Reference Table grid.");
				}
				else {
					if (ValidationHelper.isNotEmpty(sourceDSC))
						GridHelper.updateGridComboBox(gridId, "SyncRefTable_SourceDSC", rowNum, "Source Dsc", "Table Name", sourceDSC[i]);
					
					GridHelper.updateGridComboBox(gridId, "SyncRefTable_DestinationDSC", rowNum, "Destination Dsc", "Table Name", destinationDSC[i]);
					
					if (ValidationHelper.isNotEmpty(deltaColumn))
						GridHelper.updateGridComboBox(gridId, "SyncRefTable_DeltaColumn", rowNum, "Delta Column", "Table Name", deltaColumn[i]);
					
					if (ValidationHelper.isNotEmpty(uniqueColumn))
						GridHelper.updateGridComboBox(gridId, "SyncRefTable_UniqueColumn", rowNum, "Unique Column", "Table Name", uniqueColumn[i]);
				}
			}
		} catch (AssertionError e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public void updateScheduleTimes(String trunateAndLoadMultiplier, String trunateAndLoadFreq, String prevTruncatedTime,
			boolean reuseTask, String frequencyMultiplier, String frequency, String nextSchedule, String[][] dayGroups) throws Exception {
		try {
			TabHelper.gotoTab("Schedule Times");
			TextBoxHelper.type("SyncRefTable_TrunateAndLoad_Multiplier", trunateAndLoadMultiplier);
			ComboBoxHelper.select("SyncRefTable_TrunateAndLoad_Frequency", trunateAndLoadFreq);
			
			if (ValidationHelper.isNotEmpty(prevTruncatedTime))
				TextBoxHelper.type("SyncRefTable_PreviouslyTruncatedTime", prevTruncatedTime);
			
			if (!reuseTask)
				CheckBoxHelper.uncheck("SyncRefTable_ReuseTask");
			
			ROCHelper rocHelper = new ROCHelper();
			rocHelper.updateCollectionTimes(frequencyMultiplier, frequency, nextSchedule, dayGroups);
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public void saveSyncReferenceTable(String name, String detailScreenTitle, boolean isPresent) throws Exception {
		try {
			ButtonHelper.click("SaveButton");
			GenericHelper.waitForLoadmask(detailScreenWaitSec);
			
			assertTrue(LabelHelper.isTitleNotPresent(detailScreenTitle), "Sync Reference Table save did not happen.");
			ButtonHelper.click("SearchButton");
			GenericHelper.waitForLoadmask(searchScreenWaitSec);
			assertTrue(GridHelper.isValuePresent("SearchGrid", name, "Name"), "Value '" + name + "' is not found in grid.");
			if (isPresent)
				Log4jHelper.logInfo("Sync Reference Table '" + name + "' updated.");
			else
				Log4jHelper.logInfo("Sync Reference Table '" + name + "' created.");
		} catch (AssertionError e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
}