package com.subex.automation.helpers.application.screens;

import java.util.ArrayList;
import java.util.HashMap;

import com.subex.automation.helpers.application.NavigationHelper;
import com.subex.automation.helpers.component.ButtonHelper;
import com.subex.automation.helpers.component.CheckBoxHelper;
import com.subex.automation.helpers.component.ComboBoxHelper;
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

public class OnlineLDCCorrelatorHelper extends ROCAcceptanceTest{
	
	public void createOnlineLDCCorrelator(String path, String workBookName, String workSheetName, String testCaseName, int occurance) throws Exception 
	{
		try {
			TestDataHelper testData = new TestDataHelper();
			ExcelReader excelReader = new ExcelReader();
			HashMap<String, ArrayList<String>> excelData = excelReader.readDataByColumn( path, workBookName, workSheetName, testCaseName, occurance);
			
			for(int i = 0; i < excelData.get("Name").size(); i++)
			{
				String partition = excelData.get("Partition").get(i);
				String onlineLDCName = excelData.get("Name").get(i);
				String partialRecordTable = excelData.get("Partial Record Table").get(i);
				String recordTable = excelData.get("Record Table").get(i);
				String indicatorField = excelData.get("LDC Indicator Field").get(i);
				String timeStampField = excelData.get("Timestamp Field").get(i);
				String initialSegment = excelData.get("Initial Segment Indicator Field").get(i);
				String statusField = excelData.get("Status Field").get(i);
				String intermediateSegment = excelData.get("Intermediate Segment Indicator Field").get(i);
				String endSegment = excelData.get("End Segment Indicator Field").get(i);
				String[] primaryFields = testData.getStringValue(excelData.get("Primary Fields").get(i), firstLevelDelimiter);
				String[][] aggregationLogic = testData.getStringValue(excelData.get("Aggregation Logic").get(i), firstLevelDelimiter, secondLevelDelimiter);
				String expiryPeriod = excelData.get("Partial Record Expiry Period(Secs)").get(i);
				
				boolean tagAsError = ValidationHelper.isTrue(excelData.get("Tag as Error if One or More Sequences are Missing").get(i));
				String taggingField = excelData.get("Tagging Field").get(i);
				
				boolean tagAsIncomplete = ValidationHelper.isTrue(excelData.get("Tag As Incomplete").get(i));
				boolean missingInitialSeg = ValidationHelper.isTrue(excelData.get("Missing Initial Segment").get(i));
				boolean missingFinalSeg = ValidationHelper.isTrue(excelData.get("Missing Final Segment").get(i));
				
				createOnlineLDCCorrelator(partition, onlineLDCName, partialRecordTable, recordTable, indicatorField, timeStampField, initialSegment,
						statusField, intermediateSegment, endSegment, primaryFields, aggregationLogic, expiryPeriod, tagAsError, taggingField,
						tagAsIncomplete, missingInitialSeg, missingFinalSeg);
			}
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public void createOnlineLDCCorrelator(String partition, String onlineLDCName, String partialRecordTable, String recordTable, String indicatorField, 
			String timeStampField, String initialSegment, String statusField, String intermediateSegment, String endSegment, String[] primaryFields,
			String[][] aggregationLogic, String expiryPeriod, boolean tagAsError, String taggingField, boolean tagAsIncomplete, boolean missingInitialSeg,
			boolean missingFinalSeg) throws Exception {
		try {
			NavigationHelper.navigateToScreen("Online LDC Correlators", "Online LDC Correlator Search");
			int row = SearchGridHelper.gridFilterSearchWithTextBox("OnlineLDC_Name", onlineLDCName, "Name");

			if (row > 0) {
				Log4jHelper.logWarning("Online LDC Correlators '" + onlineLDCName + "' is already present.");
			}
			else {
				String detailScreenTitle = NavigationHelper.navigateToNew(partition, "OnlineLDC_Name");
				GenericHelper.waitForElement("OnlineLDC_Name", detailScreenWaitSec);
				
				TextBoxHelper.type("OnlineLDC_Name", onlineLDCName);
				ComboBoxHelper.select("OnlineLDC_PartialRecordTable", partialRecordTable);
				ComboBoxHelper.select("OnlineLDC_RecordTable", recordTable);
				
				ComboBoxHelper.select("OnlineLDC_LDCIndicatorField", indicatorField);
				ComboBoxHelper.select("OnlineLDC_TimestampField", timeStampField);
				TextBoxHelper.type("OnlineLDC_InitialSegmentIndicator", initialSegment);
				ComboBoxHelper.select("OnlineLDC_StatusField", statusField);
				TextBoxHelper.type("OnlineLDC_IntermediateDegmentIndicator", intermediateSegment);
				TextBoxHelper.type("OnlineLDC_EndSegmentIndicator", endSegment);
				
				addPrimaryField(primaryFields);
				
				addAggregationLogic(aggregationLogic);
				addException(expiryPeriod, tagAsError, taggingField, tagAsIncomplete, missingInitialSeg, missingFinalSeg);
				
				saveOnlineLDCCorrelator(onlineLDCName, detailScreenTitle);
			}
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public void addPrimaryField(String[] primaryFields) throws Exception {
		try {
			for (int i = 0; i < primaryFields.length; i++) {
				int rowNum = GridHelper.getRowNumber("OnlineLDC_PrimaryField_Grid", primaryFields[i], "Name");
				
				if (rowNum == 0) {
					ButtonHelper.click("OnlineLDC_PrimaryField_Add");
					GenericHelper.waitForLoadmask(detailScreenWaitSec);
					assertTrue(LabelHelper.isTitlePresent("Add Primary Field"));
					
					ComboBoxHelper.select("OnlineLDC_PrimaryField_FieldName", primaryFields[i]);
					ButtonHelper.click("OnlineLDC_PrimaryField_OK");
					GenericHelper.waitForLoadmask(detailScreenWaitSec);
					assertTrue(GridHelper.isValuePresent("OnlineLDC_PrimaryField_Grid", primaryFields[i], "Name"));
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
	
	public void addAggregationLogic(String[][] aggregationLogic) throws Exception {
		try {
			if (ValidationHelper.isNotEmpty(aggregationLogic)) {
				TabHelper.gotoTab("OnlineLDC_Tab_Panel", "OnlineLDC_Tab_Aggregation");
				GenericHelper.waitForLoadmask(detailScreenWaitSec);
				
				for (int i = 0; i < aggregationLogic.length; i++) {
					GridHelper.updateGridComboBox("OnlineLDC_AggregationLogic_Grid", "OnlineLDC_AggregationLogic_BaseField", (i+1), "Base Field", "Function", aggregationLogic[i][0]);
					
					GridHelper.updateGridComboBox("OnlineLDC_AggregationLogic_Grid", "OnlineLDC_AggregationLogic_Function", (i+1), "Function", "Base Field", aggregationLogic[i][1]);
					
					GridHelper.updateGridComboBox("OnlineLDC_AggregationLogic_Grid", "OnlineLDC_AggregationLogic_RecordField", (i+1), "Record Field", "Base Field", aggregationLogic[i][2]);
					
					GridHelper.updateGridTextBox("OnlineLDC_AggregationLogic_Grid", "OnlineLDC_AggregationLogic_Threshold", (i+1), "Threshold", "Base Field", aggregationLogic[i][3]);
				}
			}
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}

	public void addException(String expiryPeriod, boolean tagAsError, String taggingField, boolean tagAsIncomplete, boolean missingInitialSeg,
			boolean missingFinalSeg) throws Exception {
		try {
			TabHelper.gotoTab("OnlineLDC_Tab_Panel", "Exception");
			GenericHelper.waitForLoadmask(detailScreenWaitSec);
			TextBoxHelper.type("OnlineLDC_PartialRecordExpiryPeriod", expiryPeriod);

			if(tagAsError)
				RadioHelper.click("OnlineLDC_TagAsErrorForMissingSequence");
			
			ComboBoxHelper.select("OnlineLDC_TaggingField", taggingField);

			if(tagAsIncomplete)
				RadioHelper.click("OnlineLDC_TagAsIncomplete");
			
			if(missingInitialSeg)
				CheckBoxHelper.check("OnlineLDC_MissingInitialSegment");

			if(missingFinalSeg)
				CheckBoxHelper.check("OnlineLDC_MissingFinalSegment");
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	private void saveOnlineLDCCorrelator(String onlineLDCName, String detailScreenTitle) throws Exception {
		try {
			ButtonHelper.click("SaveButton");
			GenericHelper.waitForLoadmask(detailScreenWaitSec);

			assertTrue(LabelHelper.isTitleNotPresent(detailScreenTitle), "Online LDC Correlator save did not happen");
			ButtonHelper.click("SearchButton");
			GenericHelper.waitForLoadmask(searchScreenWaitSec);
			assertTrue(GridHelper.isValuePresent("SearchGrid", onlineLDCName, "Name"));
			Log4jHelper.logInfo("Online LDC Correlator '" + onlineLDCName + "' created.");
		} catch (AssertionError e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
}