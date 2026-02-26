package com.subex.automation.helpers.application.screens;

import java.util.ArrayList;
import java.util.HashMap;

import com.subex.automation.helpers.application.MeasureHelper;
import com.subex.automation.helpers.application.NavigationHelper;
import com.subex.automation.helpers.component.ButtonHelper;
import com.subex.automation.helpers.component.GridHelper;
import com.subex.automation.helpers.component.SearchGridHelper;
import com.subex.automation.helpers.component.TextAreaHelper;
import com.subex.automation.helpers.component.TextBoxHelper;
import com.subex.automation.helpers.data.ValidationHelper;
import com.subex.automation.helpers.file.ExcelReader;
import com.subex.automation.helpers.report.Log4jHelper;
import com.subex.automation.helpers.scripts.TestDataHelper;
import com.subex.automation.helpers.selenium.ROCAcceptanceTest;
import com.subex.automation.helpers.util.FailureHelper;

public class SQLMeasureHelper extends ROCAcceptanceTest {
	
	public void createSQLMeasure(String path, String workBookName, String workSheetName, String testCaseName, int occurence) throws Exception {
		try {
			TestDataHelper testData = new TestDataHelper();
			ExcelReader excelReader = new ExcelReader();
			HashMap<String, ArrayList<String>> excelData = excelReader.readDataByColumn( path, workBookName, workSheetName, testCaseName, occurence );
	
			for(int i = 0; i < excelData.get("Name").size(); i++)
			{
				String partition = excelData.get("Partition").get(i);
				String name = excelData.get("Name").get(i);
				String sqlQuery = excelData.get("SQL Query").get(i);
				
				String [][] outputTable = testData.getStringValue(excelData.get("Output Table").get(i), firstLevelDelimiter, secondLevelDelimiter);
				String [] inputMeasure = testData.getStringValue(excelData.get("Input Measure").get(i), firstLevelDelimiter);
				
				String caseTemplateName = excelData.get("Case Template Name").get(i);
				String[][] casePropertyMapping = testData.getStringValue(excelData.get("Case Property Mapping").get(i), firstLevelDelimiter, secondLevelDelimiter);
				
				boolean isExistingTable = ValidationHelper.isTrue(excelData.get("Is Existing Table").get(i));
				String rtTableName = excelData.get("Reporting Table Name").get(i);
				String rtDisplayName = excelData.get("Reporting Table Display Name").get(i);
				boolean rtTruncateBeforeLoad = ValidationHelper.isTrue(excelData.get("RT Truncate Before Load").get(i));
				String[][] rtColumnDetails = testData.getStringValue(excelData.get("RT Column Details").get(i), firstLevelDelimiter, secondLevelDelimiter);
				
				createSQLMeasure(partition, name, sqlQuery, outputTable, inputMeasure, caseTemplateName, casePropertyMapping, isExistingTable,
						rtTableName, rtDisplayName, rtTruncateBeforeLoad, rtColumnDetails);
			}
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public void createSQLMeasure(String partition, String name, String sqlQuery, String[][] outputTable, String[] inputMeasure,
			String caseTemplateName, String[][] casePropertyMapping, boolean isExistingTable, String rtTableName, String rtDisplayName,
			boolean rtTruncateBeforeLoad, String[][] rtColumnDetails) throws Exception {
		try {
			NavigationHelper.navigateToScreen("Measures", "Measure Search");
			int row = SearchGridHelper.gridFilterSearchWithTextBox("Measure_Name", name, "Name");

			if (row >0 ) {
				Log4jHelper.logWarning("Measure '" + name + " ' is already present.");
			}
			else {
				NavigationHelper.navigateToAction("Common Tasks", "New", "SQL Measure");
				NavigationHelper.selectPartition(partition);
				String detailScreenTitle = NavigationHelper.getScreenTitle();
				
				updateSQLMeasure(name, sqlQuery, outputTable, inputMeasure, caseTemplateName, casePropertyMapping, isExistingTable, rtTableName,
						rtDisplayName, rtTruncateBeforeLoad, rtColumnDetails);
				
				MeasureHelper measure = new MeasureHelper();
				measure.saveMeasure("SQL Measure", name, detailScreenTitle);

			}
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public void updateSQLMeasure(String path, String workBookName, String workSheetName, String testCaseName, int occurence) throws Exception {
		try {
			TestDataHelper testData = new TestDataHelper();
			ExcelReader excelReader = new ExcelReader();
			HashMap<String, ArrayList<String>> excelData = excelReader.readDataByColumn( path, workBookName, workSheetName, testCaseName, occurence );
	
			for(int i = 0; i < excelData.get("Name").size(); i++)
			{
				String name = excelData.get("Name").get(i);
				String sqlQuery = excelData.get("SQL Query").get(i);
				
				String [][] outputTable = testData.getStringValue(excelData.get("Output Table").get(i), firstLevelDelimiter, secondLevelDelimiter);
				String [] inputMeasure = testData.getStringValue(excelData.get("Input Measure").get(i), firstLevelDelimiter);
				
				String caseTemplateName = excelData.get("Case Template Name").get(i);
				String[][] casePropertyMapping = testData.getStringValue(excelData.get("Case Property Mapping").get(i), firstLevelDelimiter, secondLevelDelimiter);
				
				boolean isExistingTable = ValidationHelper.isTrue(excelData.get("Is Existing Table").get(i));
				String rtTableName = excelData.get("Reporting Table Name").get(i);
				String rtDisplayName = excelData.get("Reporting Table Display Name").get(i);
				boolean rtTruncateBeforeLoad = ValidationHelper.isTrue(excelData.get("RT Truncate Before Load").get(i));
				String[][] rtColumnDetails = testData.getStringValue(excelData.get("RT Column Details").get(i), firstLevelDelimiter, secondLevelDelimiter);
				
				updateSQLMeasure(name, sqlQuery, outputTable, inputMeasure, caseTemplateName, casePropertyMapping, isExistingTable, rtTableName,
						rtDisplayName, rtTruncateBeforeLoad, rtColumnDetails);
			}
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public void updateSQLMeasure(String name, String sqlQuery, String[][] outputTable, String[] inputMeasure, String caseTemplateName,
			String[][] casePropertyMapping, boolean isExistingTable, String rtTableName, String rtDisplayName,
			boolean rtTruncateBeforeLoad, String[][] rtColumnDetails) throws Exception {
		try {
			TextBoxHelper.type("SQLM_Name", name);
			TextAreaHelper.type("SQLM_Query_TextArea", sqlQuery);

			addOutputTable(outputTable);

			MeasureHelper measure = new MeasureHelper();
			measure.addInputMeasure(null, inputMeasure, null);
			
			measure.linkCaseTemplate("SQLM_CaseTemplate", caseTemplateName, casePropertyMapping);
			measure.addReportingTable("SQLM_ReportingTable", isExistingTable, rtTableName, rtDisplayName, rtTruncateBeforeLoad, rtColumnDetails);
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}

	public void addOutputTable (String[][] outputTable) throws Exception {
		try {
			if (ValidationHelper.isNotEmpty(outputTable)) {
				String gridId = "SQLM_OutputTable_Grid";
				int rows = GridHelper.getRowCount(gridId);
				
				for (int i = 0; i < outputTable.length; i++) {
					int row = GridHelper.getRowNumber(gridId, outputTable[i][0], "Column Name");
					
					if (row == 0) {
						ButtonHelper.click("SQLM_OutputTable_Add");
						rows++;
						row = rows;
					}
					
					GridHelper.updateGridTextBox(gridId, "SQLM_OutputTable_ColumnName", row, "Column Name", "Display Name", outputTable[i][0]);
					
					GridHelper.updateGridTextBox(gridId, "SQLM_OutputTable_DisplayName", row, "Display Name", "Column Name", outputTable[i][1]);
					
					GridHelper.updateGridComboBox(gridId, "SQLM_OutputTable_Type", row, "Type", "Column Name", outputTable[i][2]);
					
					GridHelper.updateGridCheckBox(gridId, "SQLM_OutputTable_NotNull", row, "Not Null", outputTable[i][3]);
				}
			}
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
}