package com.subex.automation.helpers.application.screens;

import java.util.ArrayList;
import java.util.HashMap;

import com.subex.automation.helpers.application.NavigationHelper;
import com.subex.automation.helpers.application.ROCHelper;
import com.subex.automation.helpers.component.ButtonHelper;
import com.subex.automation.helpers.component.ComboBoxHelper;
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

public class DayGroupHelper extends ROCAcceptanceTest {
	
	public void createDayGroup(String path, String WorkbookName, String workSheetName, String testCaseName, int occurence) throws Exception {
		try {
			TestDataHelper testData = new TestDataHelper();
			ExcelReader excelReader = new ExcelReader();
			HashMap<String, ArrayList<String>> excelData = excelReader.readDataByColumn( path, WorkbookName, workSheetName, testCaseName, occurence );
	
			for(int i = 0; i < excelData.get("Name").size(); i++)
			{
				String partition = excelData.get("Partition").get(i);
				String dayGroupName = excelData.get("Name").get(i);
				String type = excelData.get("Type").get(i);
				
				String[] date = testData.getStringValue(excelData.get("Date").get(i), firstLevelDelimiter);
				String[] dateName = testData.getStringValue(excelData.get("Date Name").get(i), firstLevelDelimiter);
				String[][] dayGroups = testData.getStringValue(excelData.get("Day Groups").get(i), firstLevelDelimiter, secondLevelDelimiter);
				
				createDayGroup(partition, dayGroupName, type, date, dateName, dayGroups);
			}
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public void createDayGroup(String partition, String dayGroupName, String type, String[] date, String[] dateName, String[][] dayGroups) throws Exception {
		try {
			int row = navigateToDayGroup(dayGroupName);
			boolean isPresent = NavigationHelper.navigateToNewOrEdit(row, partition, "Day Group", "DayGroup_Name");
			String detailScreenTitle = NavigationHelper.getScreenTitle();
			
			updateDayGroup(dayGroupName, type, date, dateName, dayGroups);
			
			saveDayGroup(dayGroupName, detailScreenTitle, isPresent);
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public void updateDayGroup(String path, String WorkbookName, String workSheetName, String testCaseName, int occurence) throws Exception {
		try {
			TestDataHelper testData = new TestDataHelper();
			ExcelReader excelReader = new ExcelReader();
			HashMap<String, ArrayList<String>> excelData = excelReader.readDataByColumn( path, WorkbookName, workSheetName, testCaseName, occurence );
	
			for(int i = 0; i < excelData.get("Name").size(); i++)
			{
				String dayGroupName = excelData.get("Name").get(i);
				String type = excelData.get("Type").get(i);
				
				String[] date = testData.getStringValue(excelData.get("Date").get(i), firstLevelDelimiter);
				String[] dateName = testData.getStringValue(excelData.get("Date Name").get(i), firstLevelDelimiter);
				String[][] dayGroups = testData.getStringValue(excelData.get("Day Groups").get(i), firstLevelDelimiter, secondLevelDelimiter);
				
				updateDayGroup(dayGroupName, type, date, dateName, dayGroups);
			}
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public void updateDayGroup(String dayGroupName, String type, String[] date, String[] dateName, String[][] dayGroups) throws Exception {
		try {
			TextBoxHelper.type("DayGroup_Name", dayGroupName);
			if (ComboBoxHelper.isEnabled("DayGroup_Type"))
				ComboBoxHelper.select("DayGroup_Type", type);
			
			if (type.equals("Date Day Group")) {
				TabHelper.gotoTab("Tab_Panel", "Date Day Group");
				addDate(date, dateName);
			}
			else {
				TabHelper.gotoTab("Tab_Panel", "Day String Day Group");
				ROCHelper rocHelper = new ROCHelper();
				rocHelper.addDayGroup("DayGroup_DayString_Grid", dayGroups);
			}
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public void addDate(String[] date, String[] dateName) throws Exception {
		try {
			if (ValidationHelper.isNotEmpty(date)) {
				for (int i = 0; i < date.length; i++) {
					int row = GridHelper.getRowNumber("DayGroup_Date_Grid", date[i], "Date");
					
					if (row == 0) {
						ButtonHelper.click("DayGroup_Date_Add");
						row = GridHelper.getRowCount("DayGroup_Date_Grid");
					}
					
					GridHelper.updateGridTextBox("DayGroup_Date_Grid", "DayGroup_Date_Date", row, "Date", "Name", date[i]);
					GridHelper.updateGridTextBox("DayGroup_Date_Grid", "DayGroup_Date_Name", row, "Name", "Date", dateName[i]);
				}
			}
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public int navigateToDayGroup(String dayGroupName) throws Exception {
		try {
			NavigationHelper.navigateToScreen( "Day Groups", "Day Group Search" );
			int row = SearchGridHelper.gridFilterSearchWithTextBox("DayGroup_Name", dayGroupName, "Name");
			
			return row;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public void saveDayGroup(String dayGroupName, String detailScreenTitle, boolean isPresent) throws Exception {
		try {
			ButtonHelper.click("SaveButton");
			GenericHelper.waitForLoadmask(detailScreenWaitSec);
			
			assertTrue(LabelHelper.isTitleNotPresent(detailScreenTitle), "Day Group save did not happen.");
			ButtonHelper.click("SearchButton");
			GenericHelper.waitForLoadmask(searchScreenWaitSec);
			assertTrue(GridHelper.isValuePresent("SearchGrid", dayGroupName, "Name"), "Value '" + dayGroupName + "' is not found in grid.");
			if (isPresent)
				Log4jHelper.logInfo("Day Group '" + dayGroupName + "' updated.");
			else
				Log4jHelper.logInfo("Day Group '" + dayGroupName + "' created.");
		} catch (AssertionError e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
}