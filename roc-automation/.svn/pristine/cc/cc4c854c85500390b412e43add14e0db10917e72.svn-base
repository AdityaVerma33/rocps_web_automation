package com.subex.automation.helpers.application.rocra;

import java.util.ArrayList;
import java.util.HashMap;

import com.subex.automation.helpers.application.NavigationHelper;
import com.subex.automation.helpers.component.ButtonHelper;
import com.subex.automation.helpers.component.GenericHelper;
import com.subex.automation.helpers.component.GridHelper;
import com.subex.automation.helpers.component.LabelHelper;
import com.subex.automation.helpers.component.TextBoxHelper;
import com.subex.automation.helpers.data.ValidationHelper;
import com.subex.automation.helpers.file.ExcelReader;
import com.subex.automation.helpers.scripts.TestDataHelper;
import com.subex.automation.helpers.selenium.ROCAcceptanceTest;
import com.subex.automation.helpers.util.FailureHelper;

public class LVRIdentifierHelper extends ROCAcceptanceTest {
	
	public void createLVRIdentifier(String path, String workBookName, String workSheetName, String testCaseName, int occurence) throws Exception {
		try {
			TestDataHelper testData = new TestDataHelper();
			ExcelReader excelReader = new ExcelReader();
			HashMap<String, ArrayList<String>> excelData = excelReader.readDataByColumn( path, workBookName, workSheetName, testCaseName, occurence );
	
			for(int i = 0; i < excelData.get("Reporting Table").size(); i++)
			{
				String [] reportingTable = testData.getStringValue(excelData.get("Reporting Table").get(i), firstLevelDelimiter);
				
				String [][] measure = testData.getStringValue(excelData.get("Measure").get(i), firstLevelDelimiter, secondLevelDelimiter);
				String [][] metric = testData.getStringValue(excelData.get("Metric").get(i), firstLevelDelimiter, secondLevelDelimiter);
				String [][] revenue = testData.getStringValue(excelData.get("Revenue").get(i), firstLevelDelimiter, secondLevelDelimiter);
				String [][] channel = testData.getStringValue(excelData.get("Channel").get(i), firstLevelDelimiter, secondLevelDelimiter);
				String [][] service = testData.getStringValue(excelData.get("Service").get(i), firstLevelDelimiter, secondLevelDelimiter);
				String [][] callType = testData.getStringValue(excelData.get("Call Type").get(i), firstLevelDelimiter, secondLevelDelimiter);
				String [][] pulseUnit = testData.getStringValue(excelData.get("Pulse Unit").get(i), firstLevelDelimiter, secondLevelDelimiter);
				String [][] matchOrder = testData.getStringValue(excelData.get("Match Order").get(i), firstLevelDelimiter, secondLevelDelimiter);
				
				createLVRIdentifier(reportingTable, measure, metric, revenue, channel, service, callType, pulseUnit, matchOrder);
			}
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public void createLVRIdentifier(String[] reportingTable, String[][] measure, String[][] metric, String[][] revenue, String[][] channel,
			String[][] service, String[][] callType, String[][] pulseUnit, String[][] matchOrder) throws Exception {
		try {
			NavigationHelper.navigateToScreen("LVR Identifier", "LVR Identifier");
			String detailScreenTitle = NavigationHelper.getScreenTitle();
			
			for (int i =0; i < reportingTable.length; i++) {
				TextBoxHelper.type("LVRIdentifier_ReportingTable_Panel", "LVRIdentifier_ReportingTable_Filter", reportingTable[i]);
				
				addColumnMapping(measure[i], metric[i], revenue[i], channel[i], service[i], callType[i], pulseUnit[i], matchOrder[i]);	
			}
			
			saveLVRIdentifier(detailScreenTitle);
		} catch (AssertionError e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public void addColumnMapping(String[] measure, String[] metric, String[] revenue, String[] channel, String[] service, String[] callType,
			String[] pulseUnit, String[] matchOrder) throws Exception {
		try {
			if (ValidationHelper.isNotEmpty(measure)) {
				String gridId = "LVRIdentifier_ColumnMapping_Grid";
				
				for (int i = 0; i < measure.length; i++ ) {
					int row = GridHelper.getRowNumber("", measure[i], "Measure");
					
					if (row == 0) {
						ButtonHelper.click("LVRIdentifier_ColumnMapping_Add");
						GenericHelper.waitForLoadmask(detailScreenWaitSec);
						Thread.sleep(200);
						row = GridHelper.getRowCount(gridId);
					}
					
					GridHelper.updateGridComboBox(gridId, "LVRIdentifier_ColumnMapping_Measure", row, "Measure", "Metric", measure[i]);
					
					GridHelper.updateGridComboBox(gridId, "LVRIdentifier_ColumnMapping_Metric", row, "Metric", "Measure", metric[i]);
					
					GridHelper.updateGridComboBox(gridId, "LVRIdentifier_ColumnMapping_Revenue", row, "Revenue", "Measure", revenue[i]);
					
					GridHelper.updateGridComboBox(gridId, "LVRIdentifier_ColumnMapping_Channel", row, "Channel", "Measure", channel[i]);
					
					GridHelper.updateGridComboBox(gridId, "LVRIdentifier_ColumnMapping_Service", row, "Service", "Measure", service[i]);
					
					GridHelper.updateGridComboBox(gridId, "LVRIdentifier_ColumnMapping_CallType", row, "Call Type", "Measure", callType[i]);
					
					GridHelper.updateGridComboBox(gridId, "LVRIdentifier_ColumnMapping_PulseUnit", row, "Pulse Unit", "Measure", pulseUnit[i]);
					
					GridHelper.updateGridTextBox(gridId, "LVRIdentifier_ColumnMapping_MatchOrder", row, "Match Order", "Measure", matchOrder[i]);
				}
			}
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public void saveLVRIdentifier(String detailScreenTitle) throws Exception {
		try {
			ButtonHelper.click("LVRIdentifier_Save");
			GenericHelper.waitForLoadmask(detailScreenWaitSec);
			
			if (ButtonHelper.isPresent("YesButton")) {
				ButtonHelper.click("YesButton");
				GenericHelper.waitForLoadmask(detailScreenWaitSec);
			}
			
			assertTrue(LabelHelper.isTitleNotPresent(detailScreenTitle));
		} catch (AssertionError e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
}