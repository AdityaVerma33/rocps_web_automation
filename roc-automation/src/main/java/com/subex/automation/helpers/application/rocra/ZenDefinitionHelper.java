package com.subex.automation.helpers.application.rocra;

import java.util.ArrayList;
import java.util.HashMap;

import com.subex.automation.helpers.application.NavigationHelper;
import com.subex.automation.helpers.application.screens.QueryFilterHelper;
import com.subex.automation.helpers.component.ButtonHelper;
import com.subex.automation.helpers.component.ComboBoxHelper;
import com.subex.automation.helpers.component.EntityComboHelper;
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

public class ZenDefinitionHelper extends ROCAcceptanceTest {
	
	public void createZenDefinition(String path, String workBookName, String workSheetName, String testCaseName, int occurence) throws Exception {
		try {
			TestDataHelper testData = new TestDataHelper();
			ExcelReader excelReader = new ExcelReader();
			HashMap<String, ArrayList<String>> excelData = excelReader.readDataByColumn( path, workBookName, workSheetName, testCaseName, occurence );
	
			for(int i = 0; i < excelData.get("Name").size(); i++)
			{
				String partition = excelData.get("Partition").get(i);
				String name = excelData.get("Name").get(i);
				String dataMatchMeasure = excelData.get("Data Match Measure").get(i);
				String caseTemplate = excelData.get("Case Template").get(i);
				String sampling = excelData.get("Sampling").get(i);
				String minUnderMatch = excelData.get("Min Under Match").get(i);
				String minAccuracy = excelData.get("Min Accuracy").get(i);
				
				String fromDate = excelData.get("From Date").get(i);
				String toDate = excelData.get("To Date").get(i);
				
				boolean addAllFeatures = ValidationHelper.isTrue(excelData.get("Add All Features").get(i));
				String [] featureName = testData.getStringValue(excelData.get("Feature Name").get(i), firstLevelDelimiter);
				String [] featureExpression = testData.getStringValue(excelData.get("Feature Expression").get(i), firstLevelDelimiter);
				String [] featureType = testData.getStringValue(excelData.get("Feature Type").get(i), firstLevelDelimiter);
				boolean [] featureCategorical = testData.getBooleanValue(excelData.get("Feature Categorical").get(i), firstLevelDelimiter);
				
				String [] expressionType = testData.getStringValue(excelData.get("Expression Type").get(i), firstLevelDelimiter);
				String [] filterName = testData.getStringValue(excelData.get("Filter Name").get(i), firstLevelDelimiter);
				String [] expClause = testData.getStringValue(excelData.get("Expression Clause").get(i), firstLevelDelimiter);
				String [] expLeftIndent = testData.getStringValue(excelData.get("Expression Left Indent").get(i), firstLevelDelimiter);
				String [] expExpression1 = testData.getStringValue(excelData.get("Expression Expression1").get(i), firstLevelDelimiter);
				String [] expOperator = testData.getStringValue(excelData.get("Expression Operator").get(i), firstLevelDelimiter);
				String [] expExpression2 = testData.getStringValue(excelData.get("Expression Expression2").get(i), firstLevelDelimiter);
				String [] expRightIndent = testData.getStringValue(excelData.get("Expression Right Indent").get(i), firstLevelDelimiter);
				
				String frequency = excelData.get("Frequency").get(i);
				String source1DatetimeColumn = excelData.get("Source1 Datetime Column").get(i);
				String source2DatetimeColumn = excelData.get("Source2 Datetime Column").get(i);
				
				createZenDefinition(partition, name, dataMatchMeasure, caseTemplate, sampling, minUnderMatch, minAccuracy, fromDate, toDate, addAllFeatures,
						featureName, featureExpression, featureType, featureCategorical, expressionType, filterName, expClause, expLeftIndent, expExpression1,
						expOperator, expExpression2, expRightIndent, frequency, source1DatetimeColumn, source2DatetimeColumn);
			}
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public void createZenDefinition(String partition, String name, String dataMatchMeasure, String caseTemplate, String sampling, String minUnderMatch,
			String minAccuracy, String fromDate, String toDate, boolean addAllFeatures, String[] featureName, String[] featureExpression, String[] featureType,
			boolean[] featureCategorical, String[] expressionType, String[] filterName, String[] expClause, String[] expLeftIndent, String[] expExpression1,
			String[] expOperator, String[] expExpression2, String[] expRightIndent, String frequency, String source1DatetimeColumn,
			String source2DatetimeColumn) throws Exception {
		try {
			NavigationHelper.navigateToScreen("Zen Definitions", "Zen Definition Search");
			int row = SearchGridHelper.gridFilterSearchWithTextBox("ZenDfn_Name", name, "Name");

			if ( row > 0 ) {
				Log4jHelper.logWarning("Zen Definition '" + name + "' is already present.");
			}
			else {
				String detailScreenTitle = NavigationHelper.navigateToNew(partition, "ZenDfn_Name");
				assertTrue(LabelHelper.isTitlePresent("New Zen Definition"));
				
				updateZenDefinition(name, dataMatchMeasure, caseTemplate, sampling, minUnderMatch, minAccuracy, fromDate, toDate, addAllFeatures, featureName,
						featureExpression, featureType, featureCategorical, expressionType, filterName, expClause, expLeftIndent, expExpression1, expOperator,
						expExpression2, expRightIndent, frequency, source1DatetimeColumn, source2DatetimeColumn);
				
				saveZenDefinition(name, detailScreenTitle);
			}
		} catch (AssertionError e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public void updateZenDefinition(String path, String workBookName, String workSheetName, String testCaseName, int occurence) throws Exception {
		try {
			TestDataHelper testData = new TestDataHelper();
			ExcelReader excelReader = new ExcelReader();
			HashMap<String, ArrayList<String>> excelData = excelReader.readDataByColumn( path, workBookName, workSheetName, testCaseName, occurence );
	
			for(int i = 0; i < excelData.get("Name").size(); i++)
			{
				String name = excelData.get("Name").get(i);
				String dataMatchMeasure = excelData.get("Data Match Measure").get(i);
				String caseTemplate = excelData.get("Case Template").get(i);
				String sampling = excelData.get("Sampling").get(i);
				String minUnderMatch = excelData.get("Min Under Match").get(i);
				String minAccuracy = excelData.get("Min Accuracy").get(i);
				
				String fromDate = excelData.get("From Date").get(i);
				String toDate = excelData.get("To Date").get(i);
				
				boolean addAllFeatures = ValidationHelper.isTrue(excelData.get("Add All Features").get(i));
				String [] featureExpression = testData.getStringValue(excelData.get("Feature Expression").get(i), firstLevelDelimiter);
				String [] featureName = testData.getStringValue(excelData.get("Feature Name").get(i), firstLevelDelimiter);
				String [] featureType = testData.getStringValue(excelData.get("Feature Type").get(i), firstLevelDelimiter);
				boolean [] featureCategorical = testData.getBooleanValue(excelData.get("Feature Categorical").get(i), firstLevelDelimiter);
				
				String [] expressionType = testData.getStringValue(excelData.get("Expression Type").get(i), firstLevelDelimiter);
				String [] filterName = testData.getStringValue(excelData.get("Filter Name").get(i), firstLevelDelimiter);
				String [] expClause = testData.getStringValue(excelData.get("Expression Clause").get(i), firstLevelDelimiter);
				String [] expLeftIndent = testData.getStringValue(excelData.get("Expression Left Indent").get(i), firstLevelDelimiter);
				String [] expExpression1 = testData.getStringValue(excelData.get("Expression Expression1").get(i), firstLevelDelimiter);
				String [] expOperator = testData.getStringValue(excelData.get("Expression Operator").get(i), firstLevelDelimiter);
				String [] expExpression2 = testData.getStringValue(excelData.get("Expression Expression2").get(i), firstLevelDelimiter);
				String [] expRightIndent = testData.getStringValue(excelData.get("Expression Right Indent").get(i), firstLevelDelimiter);
				
				String frequency = excelData.get("Frequency").get(i);
				String source1DatetimeColumn = excelData.get("Source1 Datetime Column").get(i);
				String source2DatetimeColumn = excelData.get("Source2 Datetime Column").get(i);
				
				updateZenDefinition(name, dataMatchMeasure, caseTemplate, sampling, minUnderMatch, minAccuracy, fromDate, toDate, addAllFeatures, featureName,
						featureExpression, featureType, featureCategorical, expressionType, filterName, expClause, expLeftIndent, expExpression1, expOperator,
						expExpression2, expRightIndent, frequency, source1DatetimeColumn, source2DatetimeColumn);
			}
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public void updateZenDefinition (String name, String dataMatchMeasure, String caseTemplate, String sampling, String minUnderMatch, String minAccuracy,
			String fromDate, String toDate, boolean addAllFeatures, String[] featureName, String[] featureExpression, String[] featureType,
			boolean[] featureCategorical, String[] expressionType, String[] filterName, String[] expClause, String[] expLeftIndent, String[] expExpression1,
			String[] expOperator, String[] expExpression2, String[] expRightIndent, String frequency, String source1DatetimeColumn,
			String source2DatetimeColumn) throws Exception {
		try {
			TextBoxHelper.type("ZenDfn_Name", name);
			EntityComboHelper.selectUsingGridFilterTextBox("ZenDfn_DataMatchMeasure", "Measure Search", "Measure_Name", dataMatchMeasure, "Name");
			GenericHelper.waitForLoadmask(detailScreenWaitSec);
			
			EntityComboHelper.selectUsingGridFilterTextBox("ZenDfn_CaseTemplate", "Case Template Search", "CaseTemplate_Name", caseTemplate, "Name");
			GenericHelper.waitForLoadmask(detailScreenWaitSec);
			Thread.sleep(500);
			
			ComboBoxHelper.select("ZenDfn_Sampling", sampling);
			TextBoxHelper.type("ZenDfn_MinUnderMatch", minUnderMatch);
			TextBoxHelper.type("ZenDfn_MinAccuracy", minAccuracy);
			
			addFeature(addAllFeatures, featureName, featureExpression, featureType, featureCategorical);
			
			TextBoxHelper.type("ZenDfn_FromDate", fromDate);
			TextBoxHelper.type("ZenDfn_ToDate", toDate);
			GenericHelper.waitForLoadmask(detailScreenWaitSec);
			
			if (ValidationHelper.isNotEmpty(expressionType)) {
				TabHelper.gotoTab("ZenDfn_TabPanel", "Filters");
				QueryFilterHelper queryFilter = new QueryFilterHelper();
				queryFilter.addCondition(expressionType, filterName, expClause, expLeftIndent, expExpression1, expOperator, expExpression2, expRightIndent);
			}
			
			addFrequency(frequency, source1DatetimeColumn, source2DatetimeColumn);
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public void addFeature(boolean addAllFeatures, String[] featureName, String[] featureExpression, String[] featureType, boolean[] featureCategorical) throws Exception {
		try {
			if (addAllFeatures) {
				ButtonHelper.click("ZenDfn_Feature_AddAllFeature");
				GenericHelper.waitForLoadmask(detailScreenWaitSec);
			}
			
			if (ValidationHelper.isNotEmpty(featureName)) {
				GenericHelper.waitForLoadmask(detailScreenWaitSec);
				
				for (int i = 0; i < featureName.length; i++ ) {
					int row = GridHelper.getRowNumber("ZenDfn_Feature_Grid", featureName[i], "Name");
					
					if (row == 0) {
						ButtonHelper.click("ZenDfn_Feature_AddFeature");
						GenericHelper.waitForLoadmask(detailScreenWaitSec);
						Thread.sleep(200);
						row = GridHelper.getRowCount("ZenDfn_Feature_Grid");
						
						GridHelper.updateGridComboBox("ZenDfn_Feature_Grid", "ZenDfn_Feature_Name", row, "Name", "Expression", featureName[i]);
					}
					
					if (ValidationHelper.isNotEmpty(featureExpression) && featureExpression.length > i && ValidationHelper.isNotEmpty(featureExpression[i]))
						GridHelper.updateGridTextBox("ZenDfn_Feature_Grid", "ZenDfn_Feature_Expression", row, "Expression", "Name", featureExpression[i]);
					
					if (ValidationHelper.isNotEmpty(featureType) && featureType.length > i && ValidationHelper.isNotEmpty(featureType[i]))
						GridHelper.updateGridComboBox("ZenDfn_Feature_Grid", "ZenDfn_Feature_Type", row, "Type", "Name", featureType[i]);
					
					if (featureCategorical.length > i && featureCategorical.length == featureName.length)
						GridHelper.updateGridCheckBox("ZenDfn_Feature_Grid", "ZenDfn_Feature_Categorical", row, "Categorical", featureCategorical[i]);
				}
			}
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public void addFrequency(String frequency, String source1DatetimeColumn, String source2DatetimeColumn) throws Exception {
		try {
			if (ValidationHelper.isNotEmpty(frequency)) {
				TabHelper.gotoTab("ZenDfn_TabPanel", "Frequency");
				
				ComboBoxHelper.select("ZenDfn_Frequency", frequency);
				GenericHelper.waitForLoadmask(detailScreenWaitSec);
				ComboBoxHelper.select("ZenDfn_Source1DatetimeColumn", source1DatetimeColumn);
				ComboBoxHelper.select("ZenDfn_Source2DatetimeColumn", source2DatetimeColumn);
			}
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public void saveZenDefinition(String name, String detailScreenTitle) throws Exception {
		try {
			ButtonHelper.click("SaveButton");
			GenericHelper.waitForLoadmask(detailScreenWaitSec);
			
			if (ButtonHelper.isPresent("YesButton")) {
				ButtonHelper.click("YesButton");
				GenericHelper.waitForLoadmask(detailScreenWaitSec);
			}
			
			assertTrue(LabelHelper.isTitleNotPresent(detailScreenTitle));
			ButtonHelper.click("SearchButton");
			GenericHelper.waitForLoadmask(searchScreenWaitSec);
			assertTrue(GridHelper.isValuePresent("SearchGrid", name, "Name"));
			Log4jHelper.logInfo("Zen Definition '" + name + "' created.");
		} catch (AssertionError e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
}