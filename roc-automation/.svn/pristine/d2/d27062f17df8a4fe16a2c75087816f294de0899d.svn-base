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

public class LDCHelper extends ROCAcceptanceTest{
	
	public void createLDC(String path, String WorkbookName, String workSheetName, String testCaseName, int occurence) throws Exception {
		try {
			TestDataHelper testData = new TestDataHelper();
			ExcelReader excelReader = new ExcelReader();
			HashMap<String, ArrayList<String>> excelData = excelReader.readDataByColumn( path, WorkbookName, workSheetName, testCaseName, occurence );
	
			for(int i = 0; i < excelData.get("Name").size(); i++)
			{
				String partition = excelData.get("Partition").get(i);
				String name = excelData.get("Name").get(i);
				String partialTable = excelData.get("Partial Record Table").get(i);
				String recordTable = excelData.get("Record Table").get(i);
				
				String indicatorField = excelData.get("LDC Indicator Field").get(i);
				String initialSegment = excelData.get("Initial Segment").get(i);
				String intermediateSegment = excelData.get("Intermediate Segment").get(i);
				String endSegment = excelData.get("End Segment").get(i);
				String timestampField = excelData.get("Timestamp Field").get(i);
				String statusField = excelData.get("Status Field").get(i);
				String[] primaryFields = testData.getStringValue(excelData.get("Primary Fields").get(i), firstLevelDelimiter);
				
				boolean contiguousVerification = ValidationHelper.isTrue(excelData.get("Is Contiguous Verification").get(i));
				String baseField = excelData.get("Base Field").get(i);
				String incrementField = excelData.get("Increment Field").get(i);
				String incrementValue = excelData.get("Increment Value").get(i);
				String tolerance = excelData.get("Tolerance").get(i);
				
				boolean rejectDuplicateRecord = ValidationHelper.isTrue(excelData.get("Reject Duplicate Record").get(i));
				boolean treatAsIndependentRecord = ValidationHelper.isTrue(excelData.get("Treat As Independent Record").get(i));
				String duplicateCheck = excelData.get("Duplicate Check").get(i);
				
				boolean populateGroupId = ValidationHelper.isTrue(excelData.get("Populate Group Id").get(i));
				String groupIdField = excelData.get("Group Id Field").get(i);
				
				boolean loadPartialRecord = ValidationHelper.isTrue(excelData.get("Load Partial Record").get(i));
				String callCorrelationId = excelData.get("Call Correlation Id").get(i);
				
				boolean handleMultiCall = ValidationHelper.isTrue(excelData.get("Handle Multi-Call").get(i));
				String splitOnBoundaryType = excelData.get("Split On Boundary Type").get(i);
				String splitField = excelData.get("Split Field").get(i);
				String[][] aggregationLogic = testData.getStringValue(excelData.get("Aggregation Logic").get(i), firstLevelDelimiter, secondLevelDelimiter);
				
				boolean useFirstLeg = ValidationHelper.isTrue(excelData.get("Use First Leg").get(i));
				boolean useLastLeg = ValidationHelper.isTrue(excelData.get("Use Last Leg").get(i));
				
				String expiryPeriod = excelData.get("Expiry Period").get(i);
				boolean isSubsetExpiry = ValidationHelper.isTrue(excelData.get("Is Subset Expiry").get(i));
				boolean isSetExpiry = ValidationHelper.isTrue(excelData.get("Is Set Expiry").get(i));
				
				boolean addDurationToExpiry = ValidationHelper.isTrue(excelData.get("Add Duration To Expiry").get(i));
				String durationField = excelData.get("Duration Field").get(i);
				
				boolean tagError = ValidationHelper.isTrue(excelData.get("Tag Error").get(i));
				String taggingField = excelData.get("Tagging Field").get(i);
				boolean tagAsIncomplete = ValidationHelper.isTrue(excelData.get("Tag As Incomplete").get(i));
				boolean enableMissingInitialSeg = ValidationHelper.isTrue(excelData.get("Enable Missing Initial Segment").get(i));
				boolean enableMissingIntermediateSeg = ValidationHelper.isTrue(excelData.get("Enable Missing Intermediate Segment").get(i));
				boolean enableMissingFinalSeg = ValidationHelper.isTrue(excelData.get("Enable Missing Final Segment").get(i));
				String processingAction = excelData.get("Processing Action").get(i);
				
				boolean isSameAsAggregationRule = ValidationHelper.isTrue(excelData.get("Is Same As Aggregation Rule").get(i));
				String[][] exceptionLogic = testData.getStringValue(excelData.get("Exception Logic").get(i), firstLevelDelimiter, secondLevelDelimiter);
				
				createLDC(partition, name, partialTable, recordTable, indicatorField, initialSegment, timestampField, intermediateSegment,
						statusField, endSegment, primaryFields, contiguousVerification, baseField, incrementField, incrementValue, tolerance,
						rejectDuplicateRecord, treatAsIndependentRecord, duplicateCheck, populateGroupId, groupIdField, loadPartialRecord,
						callCorrelationId, handleMultiCall, splitOnBoundaryType, splitField, aggregationLogic, useFirstLeg, useLastLeg, expiryPeriod,
						isSubsetExpiry, isSetExpiry, addDurationToExpiry, durationField, tagError, taggingField, tagAsIncomplete, enableMissingInitialSeg,
						enableMissingIntermediateSeg, enableMissingFinalSeg, processingAction, isSameAsAggregationRule, exceptionLogic);
			}
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public void createLDC(String partition, String name, String partialTable, String recordTable, String indicatorField, String initialSegment,
			String timestampField, String intermediateSegment, String statusField, String endSegment, String[] primaryFields, boolean contiguousVerification,
			String baseField, String incrementField, String incrementValue, String tolerance, boolean rejectDuplicateRecord, boolean treatAsIndependentRecord,
			String duplicateCheck, boolean populateGroupId, String groupIdField, boolean loadPartialRecord, String callCorrelationId, boolean handleMultiCall,
			String splitOnBoundaryType, String splitField, String[][] aggregationLogic, boolean useFirstLeg, boolean useLastLeg, String expiryPeriod,
			boolean isSubsetExpiry, boolean isSetExpiry, boolean addDurationToExpiry, String durationField, boolean tagError, String taggingField,
			boolean tagAsIncomplete, boolean enableMissingInitialSeg, boolean enableMissingIntermediateSeg, boolean enableMissingFinalSeg,
			String processingAction, boolean isSameAsAggregationRule, String[][] exceptionLogic) throws Exception {
		try {
			NavigationHelper.navigateToScreen("LDC Correlators", "LDC Correlator Search");
			int row = SearchGridHelper.gridFilterSearchWithTextBox("OfflineLDC_Name", name, "Name");

			if (row > 0) {
				Log4jHelper.logWarning("LDC Correlator '" + name + "' is already present.");
			}
			else {
				String detailScreenTitle = NavigationHelper.navigateToNew(partition, "OfflineLDC_Name");
				
				TextBoxHelper.type("OfflineLDC_Name", name);
				ComboBoxHelper.select("OfflineLDC_PartialRecordTable", partialTable);
				GenericHelper.waitForLoadmask(detailScreenWaitSec);
				if (ButtonHelper.isPresent("YesButton")) {
					ButtonHelper.click("YesButton");
					GenericHelper.waitForLoadmask(detailScreenWaitSec);
				}
				
				ComboBoxHelper.select("OfflineLDC_RecordTable", recordTable);
				ComboBoxHelper.select("OfflineLDC_LDCIndicatorField", indicatorField);
				TextBoxHelper.type("OfflineLDC_InitialSegmentIndicator", initialSegment);
				ComboBoxHelper.select("OfflineLDC_TimestampField", timestampField);
				TextBoxHelper.type("OfflineLDC_IntermediateSegmentIndicator", intermediateSegment);
				ComboBoxHelper.select("OfflineLDC_StatusField", statusField);
				TextBoxHelper.type("OfflineLDC_EndSegmentIndicator", endSegment);
				
				addPrimaryFields(primaryFields);
				
				if(contiguousVerification) {
					CheckBoxHelper.check("OfflineLDC_ContiguousVerification");
					ComboBoxHelper.select("OfflineLDC_Contiguous_BaseField", baseField);
					ComboBoxHelper.select("OfflineLDC_Contiguous_IncrementField", incrementField);
					TextBoxHelper.type("OfflineLDC_Contiguous_IncrementValue", incrementValue);
					TextBoxHelper.type("OfflineLDC_Contiguous_Tolerance", tolerance);
				}
				
				if(rejectDuplicateRecord)
					RadioHelper.click("OfflineLDC_DuplicateRecord_RejectRecord");
				if(treatAsIndependentRecord)
					RadioHelper.click("OfflineLDC_DuplicateRecord_IndependentRecord");
				ComboBoxHelper.select("OfflineLDC_DuplicateRecord_DuplicateCheck", duplicateCheck);
				
				if(populateGroupId) {
					CheckBoxHelper.check("OfflineLDC_PostGrouping_PopulateGroupId");
					ComboBoxHelper.select("OfflineLDC_PostGrouping_GroupIdField", groupIdField);
				}
				
				if(loadPartialRecord) {
					CheckBoxHelper.check("OfflineLDC_PostStitch_LoadPartialIntoUsage");
					ComboBoxHelper.select("OfflineLDC_PostStitch_CallCorrelationId", callCorrelationId);
				}
				
				addAggregation(handleMultiCall, splitOnBoundaryType, splitField, aggregationLogic, useFirstLeg, useLastLeg);
				
				addException(expiryPeriod, isSubsetExpiry, isSetExpiry, addDurationToExpiry, durationField, tagError, taggingField, tagAsIncomplete,
						enableMissingInitialSeg, enableMissingIntermediateSeg, enableMissingFinalSeg, processingAction, isSameAsAggregationRule, exceptionLogic);
				saveLDC(name, detailScreenTitle);
			}


		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public void addPrimaryFields(String[] primaryFields) throws Exception {
		try {
			for (int i = 0; i < primaryFields.length; i++) {
				int rowNum = GridHelper.getRowNumber("OfflineLDC_PrimaryField_Grid", primaryFields[i], "Name");
				
				if (rowNum == 0) {
					ButtonHelper.click("OfflineLDC_PrimaryField_Add");
					GenericHelper.waitForLoadmask(detailScreenWaitSec);
					ComboBoxHelper.select("OfflineLDC_PrimaryField_Name", primaryFields[i]);
					ButtonHelper.click("OfflineLDC_PrimaryField_OK");
					GenericHelper.waitForLoadmask(detailScreenWaitSec);
				}
			}
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}

	public void addAggregation(boolean handleMultiCall, String splitOnBoundaryType, String splitField, String[][] aggregationLogic, 
			boolean useFirstLeg, boolean useLastLeg) throws Exception {
		try {
			TabHelper.gotoTab("Aggregation");
			
			if(handleMultiCall)
				CheckBoxHelper.check("OfflineLDC_Aggregate_HandleMultipleCall");

			ComboBoxHelper.select("OfflineLDC_Aggregate_SplitOnBoundaryType", splitOnBoundaryType);
			ComboBoxHelper.select("OfflineLDC_Aggregate_SplitField", splitField);
			
			if (ValidationHelper.isNotEmpty(aggregationLogic)) {
				for (int i = 0; i < aggregationLogic.length; i++) {
					GridHelper.updateGridComboBox("OfflineLDC_AggregationLogic_Grid", "OfflineLDC_AggregationLogic_BaseField", (i+1), "Base Field", "Threshold", aggregationLogic[i][0]);
					
					GridHelper.updateGridComboBox("OfflineLDC_AggregationLogic_Grid", "OfflineLDC_AggregationLogic_Function", (i+1), "Function", "Base Field", aggregationLogic[i][1]);
					
					GridHelper.updateGridComboBox("OfflineLDC_AggregationLogic_Grid", "OfflineLDC_AggregationLogic_RecordField", (i+1), "Record Field", "Base Field", aggregationLogic[i][2]);
					
					GridHelper.updateGridTextBox("OfflineLDC_AggregationLogic_Grid", "OfflineLDC_AggregationLogic_Threshold", (i+1), "Threshold", "Base Field", aggregationLogic[i][3]);
				}
			}
			
			if(useFirstLeg)
				RadioHelper.click("OfflineLDC_UseAvailableFirstLeg");
			if(useLastLeg)
				RadioHelper.click("OfflineLDC_UseAvailableLastLeg");
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}

	public void addException(String expiryPeriod, boolean isSubsetExpiry, boolean isSetExpiry, boolean addDurationToExpiry, String durationField,
			boolean tagError, String taggingField, boolean tagAsIncomplete, boolean enableMissingInitialSeg, boolean enableMissingIntermediateSeg, 
			boolean enableMissingFinalSeg, String processingAction, boolean isSameAsAggregationRule, String[][] exceptionLogic) throws Exception {
		try {
			TabHelper.gotoTab("Exception");
			
			TextBoxHelper.type("OfflineLDC_PartialRecordExpiryPeriod", expiryPeriod);
			if(isSubsetExpiry)
				RadioHelper.click("OfflineLDC_SubsetExpiry");
			
			if(isSetExpiry)
				RadioHelper.click("OfflineLDC_SetExpiry");
			
			if(addDurationToExpiry) {
				CheckBoxHelper.check("OfflineLDC_ExpiryTime_AddDuration");
				ComboBoxHelper.select("OfflineLDC_ExpiryTime_DurationField", durationField);
			}

			if(tagError)
				RadioHelper.click("OfflineLDC_Tagging_TagAsError");
			ComboBoxHelper.select("OfflineLDC_Tagging_TaggingField", taggingField);

			if(tagAsIncomplete) {
				RadioHelper.click("OfflineLDC_Tagging_TagAsIncomplete");
				if(enableMissingInitialSeg)
					CheckBoxHelper.check("OfflineLDC_Tagging_MissingInitialSegment");
				
				if(enableMissingIntermediateSeg)
					CheckBoxHelper.check("OfflineLDC_Tagging_MissingIntermediateSegment");
				
				if(enableMissingFinalSeg)
					CheckBoxHelper.check("OfflineLDC_Tagging_MissingFinalSegment");
			}

			ComboBoxHelper.select("OfflineLDC_ProcessingAction", processingAction);
			if(isSameAsAggregationRule) {
				CheckBoxHelper.check("OfflineLDC_SameAsAggregateRule");
			}
			else if (ValidationHelper.isNotEmpty(exceptionLogic)) {
				for (int i = 0; i < exceptionLogic.length; i++) {
					GridHelper.updateGridComboBox("OfflineLDC_ExpiryLogic_Grid", "OfflineLDC_ExpiryLogic_BaseField", (i+1), "Base Field", "Threshold", exceptionLogic[i][0]);
					
					GridHelper.updateGridComboBox("OfflineLDC_ExpiryLogic_Grid", "OfflineLDC_ExpiryLogic_Function", (i+1), "Function", "Base Field", exceptionLogic[i][1]);
					
					GridHelper.updateGridComboBox("OfflineLDC_ExpiryLogic_Grid", "OfflineLDC_ExpiryLogic_RecordField", (i+1), "Record Field", "Base Field", exceptionLogic[i][2]);
					
					GridHelper.updateGridTextBox("OfflineLDC_ExpiryLogic_Grid", "OfflineLDC_ExpiryLogic_Threshold", (i+1), "Threshold", "Base Field", exceptionLogic[i][3]);
				}
			}
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	private void saveLDC(String name, String detailScreenTitle) throws Exception {
		try {
			ButtonHelper.click("SaveButton");
			GenericHelper.waitForLoadmask(detailScreenWaitSec);

			assertTrue(LabelHelper.isTitleNotPresent(detailScreenTitle), "LDC Correlator save did not happen");
			ButtonHelper.click("SearchButton");
			GenericHelper.waitForLoadmask(searchScreenWaitSec);
			assertTrue(GridHelper.isValuePresent("SearchGrid", name, "Name"));
			Log4jHelper.logInfo("LDC Correlator '" + name + "' created.");
		} catch (AssertionError e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
}