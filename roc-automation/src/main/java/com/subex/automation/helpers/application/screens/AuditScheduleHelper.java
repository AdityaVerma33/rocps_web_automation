package com.subex.automation.helpers.application.screens;

import java.util.ArrayList;
import java.util.HashMap;

import com.subex.automation.helpers.application.NavigationHelper;
import com.subex.automation.helpers.application.ROCHelper;
import com.subex.automation.helpers.component.ButtonHelper;
import com.subex.automation.helpers.component.CheckBoxHelper;
import com.subex.automation.helpers.component.ComboBoxHelper;
import com.subex.automation.helpers.component.EntityComboHelper;
import com.subex.automation.helpers.component.GenericHelper;
import com.subex.automation.helpers.component.GridHelper;
import com.subex.automation.helpers.component.LabelHelper;
import com.subex.automation.helpers.component.SearchGridHelper;
import com.subex.automation.helpers.component.TextBoxHelper;
import com.subex.automation.helpers.data.ValidationHelper;
import com.subex.automation.helpers.file.ExcelReader;
import com.subex.automation.helpers.report.Log4jHelper;
import com.subex.automation.helpers.scripts.TestDataHelper;
import com.subex.automation.helpers.selenium.ROCAcceptanceTest;
import com.subex.automation.helpers.util.FailureHelper;

public class AuditScheduleHelper extends ROCAcceptanceTest {
	
	public void createAuditSchedule( String path, String workBookName, String workSheetName, String testCaseName, int occurence ) throws Exception
	{
		try {
			TestDataHelper testData = new TestDataHelper();
			ExcelReader excelReader = new ExcelReader();
			HashMap<String, ArrayList<String>> excelData = excelReader.readDataByColumn( path, workBookName, workSheetName, testCaseName, occurence );
			
			for ( int i = 0; i < excelData.get( "Name" ).size(); i++ )
			{
				String partition = excelData.get( "Partition" ).get( i );
				String name = excelData.get( "Name" ).get( i );
				String auditName = excelData.get( "Audit Name" ).get( i );
				boolean generateItems = ValidationHelper.isTrue(excelData.get( "Generate Items" ).get( i ));
				String description = excelData.get( "Description" ).get( i );
				
				String lookbackFrequency = excelData.get( "Lookback Frequency" ).get( i );
				String frequencyDate = excelData.get( "Frequency Date" ).get( i );
				String lookbackStart = excelData.get( "Lookback Start" ).get( i );
				String lookbackLength = excelData.get( "Lookback Length" ).get( i );
				String lookbackPrune = excelData.get( "Lookback Prune" ).get( i );
				String fromDate = excelData.get( "From Date" ).get( i );
				String toDate = excelData.get( "To Date" ).get( i );
				
				boolean reuseRequest = ValidationHelper.isTrue(excelData.get( "Reuse Request" ).get( i ));
				boolean enable = ValidationHelper.isTrue(excelData.get( "Enable" ).get( i ));
				
				String frequencyMultiplier = excelData.get("Frequency Multiplier").get(i);
				String frequency = excelData.get("Frequency").get(i);
				String nextSchedule = excelData.get("Next Schedule").get(i);
				String[][] dayGroups = testData.getStringValue(excelData.get("Day Groups").get(i), firstLevelDelimiter, secondLevelDelimiter);
				
				createAuditSchedule(partition, name, auditName, generateItems, description, lookbackFrequency, frequencyDate, lookbackStart, lookbackLength,
						lookbackPrune, fromDate, toDate, reuseRequest, enable, frequencyMultiplier, frequency, nextSchedule, dayGroups);
			}
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public void createAuditSchedule(String partition, String name, String auditName, boolean generateItems, String description, String lookbackFrequency,
			String frequencyDate, String lookbackStart, String lookbackLength, String lookbackPrune, String fromDate, String toDate,
			boolean reuseRequest, boolean enable, String frequencyMultiplier, String frequency, String nextSchedule, String[][] dayGroups) throws Exception {
		try {
			NavigationHelper.navigateToScreen("Audit Schedule", "Audit Schedule Search");
			int row = SearchGridHelper.gridFilterSearchWithTextBox("AuditSchedule_Name", name, "Name");

			if (row > 0 ) {
				Log4jHelper.logWarning("Audit Schedule '" + name + " ' is already present.");
			}
			else {
				NavigationHelper.navigateToNewOrEdit(row, partition, "Audit Schedule", "AuditSchedule_Name");
				String detailScreenTitle = NavigationHelper.getScreenTitle();
				
				updateAuditSchedule(name, auditName, generateItems, description, lookbackFrequency, frequencyDate, lookbackStart, lookbackLength,
						lookbackPrune, fromDate, toDate, reuseRequest, enable, frequencyMultiplier, frequency, nextSchedule, dayGroups);

				saveAuditSchedule(name, detailScreenTitle);
			}
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public void updateAuditSchedule( String path, String workBookName, String workSheetName, String testCaseName, int occurence ) throws Exception
	{
		try {
			TestDataHelper testData = new TestDataHelper();
			ExcelReader excelReader = new ExcelReader();
			HashMap<String, ArrayList<String>> excelData = excelReader.readDataByColumn( path, workBookName, workSheetName, testCaseName, occurence );
			
			for ( int i = 0; i < excelData.get( "Name" ).size(); i++ )
			{
				String name = excelData.get( "Name" ).get( i );
				String auditName = excelData.get( "Audit Name" ).get( i );
				boolean generateItems = ValidationHelper.isTrue(excelData.get( "Generate Items" ).get( i ));
				String description = excelData.get( "Description" ).get( i );
				
				String lookbackFrequency = excelData.get( "Lookback Frequency" ).get( i );
				String frequencyDate = excelData.get( "Frequency Date" ).get( i );
				String lookbackStart = excelData.get( "Lookback Start" ).get( i );
				String lookbackLength = excelData.get( "Lookback Length" ).get( i );
				String lookbackPrune = excelData.get( "Lookback Prune" ).get( i );
				String fromDate = excelData.get( "From Date" ).get( i );
				String toDate = excelData.get( "To Date" ).get( i );
				
				boolean reuseRequest = ValidationHelper.isTrue(excelData.get( "Reuse Request" ).get( i ));
				boolean enable = ValidationHelper.isTrue(excelData.get( "Enable" ).get( i ));
				
				String frequencyMultiplier = excelData.get("Frequency Multiplier").get(i);
				String frequency = excelData.get("Frequency").get(i);
				String nextSchedule = excelData.get("Next Schedule").get(i);
				String[][] dayGroups = testData.getStringValue(excelData.get("Day Groups").get(i), firstLevelDelimiter, secondLevelDelimiter);
				
				updateAuditSchedule(name, auditName, generateItems, description, lookbackFrequency, frequencyDate, lookbackStart, lookbackLength,
						lookbackPrune, fromDate, toDate, reuseRequest, enable, frequencyMultiplier, frequency, nextSchedule, dayGroups);
			}
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public void updateAuditSchedule(String name, String auditName, boolean generateItems, String description, String lookbackFrequency,
			String frequencyDate, String lookbackStart, String lookbackLength, String lookbackPrune, String fromDate, String toDate,
			boolean reuseRequest, boolean enable, String frequencyMultiplier, String frequency, String nextSchedule, String[][] dayGroups) throws Exception {
		try {
			TextBoxHelper.type("AuditSchedule_Name", name);
			if (NavigationHelper.getScreenTitle().startsWith("New"))
				EntityComboHelper.selectUsingGridFilterTextBox("AuditSchedule_Audit", "Audit Search", "AuditDefinition_Name", auditName, "Name");
			
			if (generateItems)
				CheckBoxHelper.check("AuditSchedule_GenerateItems");
			TextBoxHelper.type("AuditSchedule_Description", description);
			
			ComboBoxHelper.select("AuditSchedule_Frequency", lookbackFrequency);
			TextBoxHelper.type("AuditSchedule_Lookback_StartDate", frequencyDate);
			TextBoxHelper.type("AuditSchedule_Lookback_Start", lookbackStart);
			TextBoxHelper.type("AuditSchedule_Lookback_Length", lookbackLength);
			TextBoxHelper.type("AuditSchedule_Lookback_Prune", lookbackPrune);

			if(TextBoxHelper.isEnabled("AuditSchedule_From"))
				TextBoxHelper.type("AuditSchedule_From", fromDate);
			if(TextBoxHelper.isEnabled("AuditSchedule_To"))
				TextBoxHelper.type("AuditSchedule_To", toDate);

			if(reuseRequest)
				CheckBoxHelper.check("AuditSchedule_ReuseRequests");
			if (enable)
				CheckBoxHelper.check("AuditSchedule_Enabled");
			
			ROCHelper rocHelper = new ROCHelper();
			rocHelper.updateCollectionTimes(frequencyMultiplier, frequency, nextSchedule, dayGroups);
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}

	private void saveAuditSchedule(String name, String detailScreenTitle) throws Exception {
		try {
			ButtonHelper.click("SaveButton");
			GenericHelper.waitForLoadmask(detailScreenWaitSec);

			assertTrue(LabelHelper.isTitleNotPresent(detailScreenTitle));
			ButtonHelper.click("SearchButton");
			GenericHelper.waitForLoadmask(searchScreenWaitSec);
			assertTrue(GridHelper.isValuePresent("SearchGrid", name, "Name"));
			Log4jHelper.logInfo("Audit Definition '" + name + "' created.");
		} catch (AssertionError e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
}