package com.subex.automation.helpers.application.screens;

import java.util.ArrayList;
import java.util.HashMap;

import com.subex.automation.helpers.application.MeasureHelper;
import com.subex.automation.helpers.application.NavigationHelper;
import com.subex.automation.helpers.component.ButtonHelper;
import com.subex.automation.helpers.component.CheckBoxHelper;
import com.subex.automation.helpers.component.ComboBoxHelper;
import com.subex.automation.helpers.component.EntityComboHelper;
import com.subex.automation.helpers.component.GenericHelper;
import com.subex.automation.helpers.component.GridCheckBoxHelper;
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

public class TrendMeasureHelper extends ROCAcceptanceTest {
	
	public void createTrendMeasure(String path, String workBookName, String workSheetName, String testCaseName, int occurence) throws Exception {
		try {
			TestDataHelper testData = new TestDataHelper();
			ExcelReader excelReader = new ExcelReader();
			HashMap<String, ArrayList<String>> excelData = excelReader.readDataByColumn( path, workBookName, workSheetName, testCaseName, occurence );
	
			for(int i = 0; i < excelData.get("Name").size(); i++)
			{
				String partition = excelData.get("Partition").get(i);
				String name = excelData.get("Name").get(i);
				String trendSummary = excelData.get("Trend Summary").get(i);
				
				String [][] profileFields = testData.getStringValue(excelData.get("Profile Fields").get(i), firstLevelDelimiter, secondLevelDelimiter);
				String [][] summaryFields = testData.getStringValue(excelData.get("Summary Fields").get(i), firstLevelDelimiter, secondLevelDelimiter);
				
				boolean enableTime = ValidationHelper.isTrue(excelData.get("Enable Time").get(i));
				String frequency = excelData.get("Frequency").get(i);
				String datetimeColumn = excelData.get("Datetime Column").get(i);
				String alignDate = excelData.get("Align Date").get(i);
				
				String caseTemplateName = excelData.get("Case Template Name").get(i);
				String[][] casePropertyMapping = testData.getStringValue(excelData.get("Case Property Mapping").get(i), firstLevelDelimiter, secondLevelDelimiter);
				
				String historyType = excelData.get("History Type").get(i);
				String windowSpanType = excelData.get("Window Span Type").get(i);
				String[] startDate = testData.getStringValue(excelData.get("Start Date").get(i), firstLevelDelimiter);
				String[] endDate = testData.getStringValue(excelData.get("End Date").get(i), firstLevelDelimiter);
				String[][] staticValues = testData.getStringValue(excelData.get("Static Values").get(i), firstLevelDelimiter, secondLevelDelimiter);
				
				String [] expressionType = testData.getStringValue(excelData.get("Expression Type").get(i), firstLevelDelimiter);
				String [] filterName = testData.getStringValue(excelData.get("Filter Name").get(i), firstLevelDelimiter);
				String [] expClause = testData.getStringValue(excelData.get("Expression Clause").get(i), firstLevelDelimiter);
				String [] expLeftIndent = testData.getStringValue(excelData.get("Expression Left Indent").get(i), firstLevelDelimiter);
				String [] expExpression1 = testData.getStringValue(excelData.get("Expression Expression1").get(i), firstLevelDelimiter);
				String [] expOperator = testData.getStringValue(excelData.get("Expression Operator").get(i), firstLevelDelimiter);
				String [] expExpression2 = testData.getStringValue(excelData.get("Expression Expression2").get(i), firstLevelDelimiter);
				String [] expRightIndent = testData.getStringValue(excelData.get("Expression Right Indent").get(i), firstLevelDelimiter);
				
				createTrendMeasure(partition, name, trendSummary, profileFields, summaryFields, enableTime, frequency, datetimeColumn, alignDate,
						caseTemplateName, casePropertyMapping, historyType, windowSpanType, startDate, endDate, staticValues, expressionType,
						filterName, expClause, expLeftIndent, expExpression1, expOperator, expExpression2, expRightIndent);
			}
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public void createTrendMeasure (String partition, String name, String trendSummary, String[][] profileFields, String[][] summaryFields,
			boolean enableTime, String frequency, String datetimeColumn, String alignDate, String caseTemplateName, String[][] casePropertyMapping,
			String historyType, String windowSpanType, String[] startDate, String[] endDate, String[][] staticValues, String[] expressionType,
			String[] filterName, String[] expClause, String[] expLeftIndent, String[] expExpression1, String[] expOperator, String[] expExpression2,
			String[] expRightIndent) throws Exception {
		try {
			NavigationHelper.navigateToScreen("Measures", "Measure Search");
			int row = SearchGridHelper.gridFilterSearchWithTextBox("Measure_Name", name, "Name");

			if ( row > 0 ) {
				Log4jHelper.logWarning("Measure '" + name + "' is already present.");
			}
			else {
				NavigationHelper.navigateToAction("Common Tasks", "New", "Trend Measure");
				NavigationHelper.selectPartition(partition);
				assertTrue(LabelHelper.isTitlePresent("New Trend Measure"));
				String detailScreenTitle = NavigationHelper.getScreenTitle();
				
				TextBoxHelper.type("TM_Name", name);
				EntityComboHelper.selectUsingGridFilterTextBox("TM_TrendSummary", "Trend Summary Table Search", "TrendSummary_Name", trendSummary, "Name");
				GenericHelper.waitForLoadmask(detailScreenWaitSec);
				
				addProfileFields(profileFields);
				
				addSummaryFields(summaryFields);
				
				if (enableTime)
					CheckBoxHelper.check("TM_Time");
				
				ComboBoxHelper.select("TM_Frequency", frequency);
				ComboBoxHelper.select("TM_TrendDatetime", datetimeColumn);
				TextBoxHelper.type("TM_AlignDate", alignDate);
				
				MeasureHelper measure = new MeasureHelper();
				measure.linkCaseTemplate("TM_CaseTemplate", caseTemplateName, casePropertyMapping);
				
				addHistory(historyType, windowSpanType, startDate, endDate, staticValues);
				
				TabHelper.gotoTab("QM_Workflow_Panel", "Filter");
				QueryFilterHelper queryFilter = new QueryFilterHelper();
				queryFilter.addCondition(expressionType, filterName, expClause, expLeftIndent, expExpression1, expOperator, expExpression2, expRightIndent);
				
				measure.saveMeasure("Trend Measure", name, detailScreenTitle);
			}
		} catch (AssertionError e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public void addProfileFields(String[][] profileFields) throws Exception {
		try {
			if (ValidationHelper.isNotEmpty(profileFields)) {
				for (int i = 0; i < profileFields.length; i++ ){
					int row = GridHelper.getRowNumber("TM_ProfileFields_Grid", profileFields[i][0], "Column Name");
					
					if (row > 0) {
						GridCheckBoxHelper.check("TM_ProfileFields_Grid", "TM_ProfileFields_Select", row, "Select");
						
						if (profileFields[i].length > 1 && ValidationHelper.isNotEmpty(profileFields[i][1]))
							GridHelper.updateGridComboBox("TM_ProfileFields_Grid", "TM_ProfileFields_Frequency", row, "Frequency", "Column Name", profileFields[i][1]);
					}
					else {
						FailureHelper.failTest("Column '" + profileFields[i][0] + "' is not found in Profile Fields grid.");
					}
				}
			}
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public void addSummaryFields(String[][] summaryFields) throws Exception {
		try {
			if (ValidationHelper.isNotEmpty(summaryFields)) {
				for (int i = 0; i < summaryFields.length; i++ ){
					int row = GridHelper.getRowNumber("TM_SummaryFields_Grid", summaryFields[i][0], "Column Name");
					
					if (row > 0) {
						GridCheckBoxHelper.check("TM_SummaryFields_Grid", "TM_SummaryFields_Select", row, "Select");
						
						if (summaryFields[i].length > 1 && ValidationHelper.isNotEmpty(summaryFields[i][1]))
							GridHelper.updateGridComboBox("TM_SummaryFields_Grid", "TM_SummaryFields_Aggregate", row, "Aggregate", "Column Name", summaryFields[i][1]);
					}
					else {
						FailureHelper.failTest("Column '" + summaryFields[i][0] + "' is not found in Summary Fields grid.");
					}
				}
			}
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public void addHistory(String historyType, String windowSpanType, String[] startDate, String[] endDate, String[][] staticValues) throws Exception {
		try {
			if (ValidationHelper.isNotEmpty(historyType)) {
				TabHelper.gotoTab("TM_Workflow_Panel", "History");
				ComboBoxHelper.select("TM_HistoryType", historyType);
				ComboBoxHelper.select("TM_WindowSpanType", windowSpanType);
				
				if (windowSpanType.equals("Fixed Window Span")) {
					for (int i = 0; i < startDate.length; i++) {
						ButtonHelper.click("TM_FixedWindowSpan_Add");
						GridHelper.updateGridTextBox("TM_FixedWindowSpan_Grid", "TM_FixedWindowSpan_StartDate", (i+1), "Start Date", "Period", startDate[i]);
						
						if (ValidationHelper.isNotEmpty(endDate) && ValidationHelper.isNotEmpty(endDate[i]))
							GridHelper.updateGridTextBox("TM_FixedWindowSpan_Grid", "TM_FixedWindowSpan_EndDate", (i+1), "End Date", "Period", endDate[i]);
					}
				}
				else if (windowSpanType.equals("Static Values")) {
					for (int i = 0; i < staticValues.length; i++) {
						int row = GridHelper.getRowNumber("TM_StaticValues_Grid", staticValues[i][0], "Column Name");
						
						if (row > 0)
							GridHelper.updateGridTextBox("TM_StaticValues_Grid", "TM_StaticValues_Value", row, "Value", "Column Name", staticValues[i][1]);
						else
							FailureHelper.failTest("Column '" + staticValues[i][0] + "' not found in Static Values grid.");
					}
				}
			}
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
}