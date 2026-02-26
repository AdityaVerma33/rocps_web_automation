package com.subex.automation.helpers.application.screens;

import java.util.ArrayList;
import java.util.HashMap;

import com.subex.automation.helpers.application.MeasureHelper;
import com.subex.automation.helpers.application.NavigationHelper;
import com.subex.automation.helpers.component.ButtonHelper;
import com.subex.automation.helpers.component.CheckBoxHelper;
import com.subex.automation.helpers.component.ComboBoxHelper;
import com.subex.automation.helpers.component.EntitySearchHelper;
import com.subex.automation.helpers.component.GenericHelper;
import com.subex.automation.helpers.component.GridCheckBoxHelper;
import com.subex.automation.helpers.component.GridHelper;
import com.subex.automation.helpers.component.LabelHelper;
import com.subex.automation.helpers.component.SearchGridHelper;
import com.subex.automation.helpers.component.TabHelper;
import com.subex.automation.helpers.component.TextBoxHelper;
import com.subex.automation.helpers.component.TreeHelper;
import com.subex.automation.helpers.componentHelpers.GridElementHelper;
import com.subex.automation.helpers.data.ValidationHelper;
import com.subex.automation.helpers.file.ExcelReader;
import com.subex.automation.helpers.report.Log4jHelper;
import com.subex.automation.helpers.scripts.TestDataHelper;
import com.subex.automation.helpers.selenium.ROCAcceptanceTest;
import com.subex.automation.helpers.util.FailureHelper;

public class DataMatchMeasureHelper extends ROCAcceptanceTest {
	
	public void createDataMatchMeasure(String path, String workBookName, String workSheetName, String testCaseName, int occurence) throws Exception {
		try {
			TestDataHelper testData = new TestDataHelper();
			ExcelReader excelReader = new ExcelReader();
			HashMap<String, ArrayList<String>> excelData = excelReader.readDataByColumn( path, workBookName, workSheetName, testCaseName, occurence );
	
			for(int i = 0; i < excelData.get("Name").size(); i++)
			{
				String partition = excelData.get("Partition").get(i);
				String name = excelData.get("Name").get(i);
				
				String[] tableInstance = testData.getStringValue(excelData.get("Table Instance").get(i), firstLevelDelimiter);
				String[] inputMeasure = testData.getStringValue(excelData.get("Input Measure").get(i), firstLevelDelimiter);
				String[] inputTable = testData.getStringValue(excelData.get("Input Table").get(i), firstLevelDelimiter);
				
				boolean addAllColumns = ValidationHelper.isTrue(excelData.get("Add All Columns").get(i));
				String[][] tableColumns = testData.getStringValue(excelData.get("Table Columns").get(i), firstLevelDelimiter, secondLevelDelimiter);
				String[][] outputColName = testData.getStringValue(excelData.get("Output Column Name").get(i), firstLevelDelimiter, secondLevelDelimiter);
				String[][] outputColDisplayName = testData.getStringValue(excelData.get("Output Column Display Name").get(i), firstLevelDelimiter, secondLevelDelimiter);
				
				String caseTemplateName = excelData.get("Case Template Name").get(i);
				String[][] casePropertyMapping = testData.getStringValue(excelData.get("Case Property Mapping").get(i), firstLevelDelimiter, secondLevelDelimiter);
				
				boolean isExistingTable = ValidationHelper.isTrue(excelData.get("Is Existing Table").get(i));
				String rtTableName = excelData.get("Reporting Table Name").get(i);
				String rtDisplayName = excelData.get("Reporting Table Display Name").get(i);
				boolean rtTruncateBeforeLoad = ValidationHelper.isTrue(excelData.get("RT Truncate Before Load").get(i));
				String[][] rtColumnDetails = testData.getStringValue(excelData.get("RT Column Details").get(i), firstLevelDelimiter, secondLevelDelimiter);
				
				String[][] matchSteps = testData.getStringValue(excelData.get("Match Steps").get(i), firstLevelDelimiter, secondLevelDelimiter);
				String[][] matchKeys = testData.getStringValue(excelData.get("Match Keys").get(i), firstLevelDelimiter, secondLevelDelimiter);
				
				String[][] matchComparison = testData.getStringValue(excelData.get("Match Comparison").get(i), firstLevelDelimiter, secondLevelDelimiter);
				boolean enableBestCaseMatch = ValidationHelper.isTrue(excelData.get("Enable Best Case Match").get(i));
				String bestCaseKey = excelData.get("Best Case Key").get(i);
				
				String[][] matchSummary = testData.getStringValue(excelData.get("Match Summary").get(i), firstLevelDelimiter, secondLevelDelimiter);
				boolean generateSummary = ValidationHelper.isTrue(excelData.get("Generate Summary").get(i));
				String alignDateSetting = excelData.get("Align Date Setting").get(i);
				boolean exactMatch = ValidationHelper.isTrue(excelData.get("Exact Match").get(i));
				boolean onlyCompare = ValidationHelper.isTrue(excelData.get("Only Compare Discrepancy").get(i));
				boolean overMatch = ValidationHelper.isTrue(excelData.get("Over Match").get(i));
				boolean underMatch = ValidationHelper.isTrue(excelData.get("Under Match").get(i));
				
				String source1Alias = excelData.get("Source1 Alias").get(i);
				String source2Alias = excelData.get("Source2 Alias").get(i);
				String[][] summaryDimension = testData.getStringValue(excelData.get("Summary Dimension").get(i), firstLevelDelimiter, secondLevelDelimiter);
				String[][] summaryMeasure = testData.getStringValue(excelData.get("Summary Measure").get(i), firstLevelDelimiter, secondLevelDelimiter);
				
				createDataMatchMeasure(partition, name, tableInstance, addAllColumns, tableColumns, inputMeasure, inputTable, outputColName, outputColDisplayName,
						caseTemplateName, casePropertyMapping, isExistingTable, rtTableName, rtDisplayName, rtTruncateBeforeLoad, rtColumnDetails,
						matchSteps, matchKeys, matchComparison, enableBestCaseMatch, bestCaseKey, matchSummary, generateSummary, alignDateSetting,
						exactMatch, onlyCompare, overMatch, underMatch, source1Alias, source2Alias, summaryDimension, summaryMeasure);
			}
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public void createDataMatchMeasure (String partition, String name, String[] tableInstance, boolean addAllColumns, String[][] tableColumns, String[] inputMeasure,
			String[] inputTable, String[][] outputColName, String[][] outputColDisplayName, String caseTemplateName, String[][] casePropertyMapping,
			boolean isExistingTable, String rtTableName, String rtDisplayName, boolean rtTruncateBeforeLoad, String[][] rtColumnDetails,
			String[][] matchSteps, String[][] matchKeys, String[][] matchComparison, boolean enableBestCaseMatch, String bestCaseKey,
			String[][] matchSummary, boolean generateSummary, String alignDateSetting, boolean exactMatch, boolean onlyCompare, boolean overMatch,
			boolean underMatch, String source1Alias, String source2Alias, String[][] summaryDimension, String[][] summaryMeasure) throws Exception {
		try {
			NavigationHelper.navigateToScreen("Measures", "Measure Search");
			int row = SearchGridHelper.gridFilterSearchWithTextBox("Measure_Name", name, "Name");

			if (row >0 ) {
				Log4jHelper.logWarning("Measure '" + name + "' is already present.");
			}
			else {
				NavigationHelper.navigateToAction("Common Tasks", "New", "Data Match Measure");
				NavigationHelper.selectPartition(partition);
				GenericHelper.waitForElement("DMM_Name", detailScreenWaitSec);
				String detailScreenTitle = NavigationHelper.getScreenTitle();

				updateDataMatchMeasure(name, tableInstance, addAllColumns, tableColumns, inputMeasure, inputTable, outputColName, outputColDisplayName,
						caseTemplateName, casePropertyMapping, isExistingTable, rtTableName, rtDisplayName, rtTruncateBeforeLoad,
						rtColumnDetails, matchSteps, matchKeys, matchComparison, enableBestCaseMatch, bestCaseKey, matchSummary, generateSummary,
						alignDateSetting, exactMatch, onlyCompare, overMatch, underMatch, source1Alias, source2Alias, summaryDimension,
						summaryMeasure);
				
				MeasureHelper measure = new MeasureHelper();
				measure.saveMeasure("Data Match Measure", name, detailScreenTitle);
			}
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public void updateDataMatchMeasure (String name, String[] tableInstance, boolean addAllColumns, String[][] tableColumns, String[] inputMeasure,
			String[] inputTable, String[][] outputColName, String[][] outputColDisplayName, String caseTemplateName, String[][] casePropertyMapping,
			boolean isExistingTable, String rtTableName, String rtDisplayName, boolean rtTruncateBeforeLoad, String[][] rtColumnDetails,
			String[][] matchSteps, String[][] matchKeys, String[][] matchComparison, boolean enableBestCaseMatch, String bestCaseKey,
			String[][] matchSummary, boolean generateSummary, String alignDateSetting, boolean exactMatch, boolean onlyCompare, boolean overMatch,
			boolean underMatch, String source1Alias, String source2Alias, String[][] summaryDimension, String[][] summaryMeasure) throws Exception {
		try {
			TextBoxHelper.type("DMM_Name", name);
			
			addTableInstance(tableInstance);
			
			addInputMeasure(inputMeasure, inputTable);
			
			if (addAllColumns) {
				ButtonHelper.click("DMM_MatchSources_AddAllColumns");
				GenericHelper.waitForLoadmask(detailScreenWaitSec);
			}
			else {
				if (ValidationHelper.isNotEmpty(tableInstance)) {
					addColumn(tableInstance, tableColumns, outputColName, outputColDisplayName);
				}
				
				if (ValidationHelper.isNotEmpty(inputMeasure) && ValidationHelper.isNotEmpty(inputTable)) {
					addColumn(inputTable, tableColumns, outputColName, outputColDisplayName);
				}
			}
			
			MeasureHelper measure = new MeasureHelper();
			measure.linkCaseTemplate("Measure_CaseTemplate", caseTemplateName, casePropertyMapping);
			measure.addReportingTable("Measure_ReportingTable", isExistingTable, rtTableName, rtDisplayName, rtTruncateBeforeLoad, rtColumnDetails);
			
			addMatchSteps(matchSteps);
			addMatchKeys(matchKeys);
			addMatchComparison(matchComparison, enableBestCaseMatch, bestCaseKey);
			
			addMatchSummary(matchSummary, generateSummary, alignDateSetting, exactMatch, onlyCompare, overMatch, underMatch);
			
			addSummaryInfo(source1Alias, source2Alias, summaryDimension, summaryMeasure);
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public void addTableInstance(String[] tableInstance) throws Exception {
		try {
			if (ValidationHelper.isNotEmpty(tableInstance)) {
				EntitySearchHelper entitySearch = new EntitySearchHelper();
				
				for (int i = 0; i < tableInstance.length; i++ ){
					ButtonHelper.click("DMM_AddTableInstance");
					GenericHelper.waitForLoadmask(detailScreenWaitSec);
					if (ButtonHelper.isPresent("YesButton")) {
						ButtonHelper.click("YesButton");
						GenericHelper.waitForLoadmask(detailScreenWaitSec);
					}
					
					entitySearch.selectUsingGridFilterTextBox("Table Instance Search", "TableInst_DisplayName", tableInstance[i], "Display Name");
					GenericHelper.waitForLoadmask(detailScreenWaitSec);
				}
			}
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public void addTableInstance(String[] tableInstance, String[][] tableColumns, String[][] outputColumnName, String[][] outputColumnDisplayName) throws Exception {
		try {
			if (ValidationHelper.isNotEmpty(tableInstance)) {
				EntitySearchHelper entitySearch = new EntitySearchHelper();
				
				for (int i = 0; i < tableInstance.length; i++ ){
					ButtonHelper.click("DMM_AddTableInstance");
					GenericHelper.waitForLoadmask(detailScreenWaitSec);
					if (ButtonHelper.isPresent("YesButton")) {
						ButtonHelper.click("YesButton");
						GenericHelper.waitForLoadmask(detailScreenWaitSec);
					}
					
					entitySearch.selectUsingGridFilterTextBox("Table Instance Search", "TableInst_DisplayName", tableInstance[i], "Display Name");
					GenericHelper.waitForLoadmask(detailScreenWaitSec);
					
					for (int j = 0; j < tableColumns[i].length; j++) {
						if (!TreeHelper.isTreeExpanded("DMM_MatchSources_Tree", tableInstance[i])) {
							TreeHelper.expandTree("DMM_MatchSources_Tree", tableInstance[i]);
						}
						
						TreeHelper.clickChild("DMM_MatchSources_Tree", tableInstance[i], tableColumns[i][j]);
						ButtonHelper.click("DMM_MatchSources_Add");
						GenericHelper.waitForLoadmask(detailScreenWaitSec);
						
						if (ValidationHelper.isNotEmpty(outputColumnName) && ValidationHelper.isNotEmpty(outputColumnDisplayName))
							updateMatchColumns(tableInstance[i], tableColumns[i][j], outputColumnName[i][j], outputColumnDisplayName[i][j]);
					}
				}
			}
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public void addInputMeasure(String[] inputMeasure, String[] inputTable) throws Exception {
		try {
			if (ValidationHelper.isNotEmpty(inputMeasure)) {
				MeasureHelper measure = new MeasureHelper();
				measure.addInputMeasure("DMM_MatchSources_Tree", inputMeasure, inputTable);
			}
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public void addInputMeasure(String[] inputMeasure, String[] inputTable, String[][] tableColumns, String[][] outputColName, String[][] outputColDisplayName) throws Exception {
		try {
			if (ValidationHelper.isNotEmpty(inputMeasure)) {
				MeasureHelper measure = new MeasureHelper();
				measure.addInputMeasure("DMM_MatchSources_Tree", inputMeasure, inputTable);
				
				for (int i = 0; i < inputTable.length; i++ ){
					for (int j = 0; j < tableColumns.length; j++) {
						TreeHelper.expandTree("DMM_MatchSources_Tree", inputTable[i]);
						
						TreeHelper.clickChild("DMM_MatchSources_Tree", inputTable[i], tableColumns[i][j]);
						ButtonHelper.click("DMM_MatchSources_Add");
						GenericHelper.waitForLoadmask(detailScreenWaitSec);
						
						updateMatchColumns(inputTable[i], tableColumns[i][j], outputColName[i][j], outputColDisplayName[i][j]);
					}
				}
			}
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public void addColumn(String[] tableInstance, String[][] tableColumns, String[][] outputColumnName, String[][] outputColumnDisplayName) throws Exception {
		try {
			if (ValidationHelper.isNotEmpty(tableInstance)) {
				for (int i = 0; i < tableInstance.length; i++ ){
					
					for (int j = 0; j < tableColumns[i].length; j++) {
						if (!TreeHelper.isTreeExpanded("DMM_MatchSources_Tree", tableInstance[i])) {
							TreeHelper.expandTree("DMM_MatchSources_Tree", tableInstance[i]);
						}
						
						TreeHelper.clickChild("DMM_MatchSources_Tree", tableInstance[i], tableColumns[i][j]);
						ButtonHelper.click("DMM_MatchSources_Add");
						GenericHelper.waitForLoadmask(detailScreenWaitSec);
						
						if (ValidationHelper.isNotEmpty(outputColumnName) && ValidationHelper.isNotEmpty(outputColumnDisplayName))
							updateMatchColumns(tableInstance[i], tableColumns[i][j], outputColumnName[i][j], outputColumnDisplayName[i][j]);
					}
				}
			}
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public void updateMatchColumns(String tableInstance, String tableColumn, String outputColumnName, String outputColumnDisplayName) throws Exception {
		try {
			if (ValidationHelper.isNotEmpty(outputColumnName) && ValidationHelper.isNotEmpty(outputColumnDisplayName)) {
				String matchColumnsGridId = "DMM_MatchColumns_Grid";
				int[] rows = GridHelper.getRowNumbers(matchColumnsGridId, tableColumn, "Match Column Name");
				int rowNum = 0;
				
				for (int i = 0; i < rows.length; i++) {
					String value = GridHelper.getCellValue(matchColumnsGridId, rows[i], "Match Source");
					if (value.equals(tableInstance)) {
						rowNum = rows[i];
						break;
					}
				}
				
				GridHelper.updateGridTextBox(matchColumnsGridId, "DMM_MatchColumns_OutputColumnName", rowNum, "Output Column Name", "Match Column Name", outputColumnName);
				
				GridHelper.updateGridTextBox(matchColumnsGridId, "DMM_MatchColumns_OutputDisplayName", rowNum, "Output Display Name", "Match Column Name", outputColumnDisplayName);
			}
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	//	Adding Match Steps
	public void addMatchSteps(String[][] matchSteps) throws Exception {
		try {
			if (ValidationHelper.isNotEmpty(matchSteps) && !matchSteps[0][0].equals("")) {
				TabHelper.gotoTab("DMM_Workflow_Panel", "Match Steps");
				
				for (int i = 0; i < matchSteps.length; i++ ){
					ButtonHelper.click("DMM_MatchSteps_Add");
					GenericHelper.waitForLoadmask(detailScreenWaitSec);
					assertTrue(LabelHelper.isTitlePresent("Select Sources"));
					int start = 0;
					
					if (i > 0) {
						start = 1;
						ComboBoxHelper.select("DMM_MatchSteps_PrimarySource", matchSteps[i][0]);
					}
					
					for (int j = start; j < matchSteps[i].length; j++)
						GridCheckBoxHelper.check("DMM_MatchSteps_MatchSource_Grid", "DMM_MatchSteps_MatchSource_Select", matchSteps[i][j], "Source", "Select");
					ButtonHelper.click("DMM_MatchSteps_MatchSource_OK");
					GenericHelper.waitForLoadmask(detailScreenWaitSec);
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

	//	Adding Match Keys
	public void addMatchKeys(String[][] matchKeys) throws Exception {
		try {
			if (ValidationHelper.isNotEmpty(matchKeys) && !matchKeys[0][0].equals("")) {
				String gridId = "DMM_MatchKeys_Grid";
				TabHelper.gotoTab("DMM_Workflow_Panel", "Match Steps");
				
				for (int i = 0; i < matchKeys.length; i++ ) {
					GridHelper.clickRow("DMM_MatchSteps_Grid", matchKeys[i][0], "Step Name");
					ButtonHelper.click("DMM_MatchKeys_Add");
					int rows = GridHelper.getRowCount(gridId);
					int column = 1;
					int start = 1;
					boolean hasTolerance = false;
					if (GridElementHelper.getHeaderColumn(or.getProperty(gridId), "Tolerance") > 0)
						hasTolerance = true;
					
					if (hasTolerance) {
						GridHelper.updateGridTextBox(gridId, "DMM_MatchKeys_Tolerance", rows, "Tolerance", "", matchKeys[i][1]);
						
						GridHelper.updateGridTextBox(gridId, "DMM_MatchKeys_NegativeTolerance", rows, "Negative Tolerance", "", matchKeys[i][2]);
						column = 3;
						start = 3;
					}
					
					for (int j = start, combo = 0; j < matchKeys[i].length; j++, combo++) {
						String comboID = GenericHelper.getORProperty("DMM_MatchSourceColumn_Combo").replace("NUM", combo+"");
						if (hasTolerance)
							GridHelper.updateGridComboBox(gridId, comboID, rows, column, "Tolerance", matchKeys[i][j]);
						else
							GridHelper.updateGridComboBox(gridId, comboID, rows, column, "", matchKeys[i][j]);
						column++;
					}
				}
			}
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}

	//	MatchComparison
	public void addMatchComparison(String[][] matchComparison, boolean enableBestCaseMatch, String bestCaseKey) throws Exception {
		try {
			if (ValidationHelper.isNotEmpty(matchComparison) && !matchComparison[0][0].equals("")) {
				for (int i = 0; i < matchComparison.length; i++ ) {
					GridHelper.clickRow("DMM_MatchSteps_Grid", matchComparison[i][0], "Step Name");
					ButtonHelper.click("DMM_MatchComparisons_Add");
					GenericHelper.waitForLoadmask(detailScreenWaitSec);
					assertTrue(LabelHelper.isTitlePresent("Data Match Compare"));
					
					TextBoxHelper.type("DMM_MatchComparisons_Name", matchComparison[i][1]);
					ComboBoxHelper.select("DMM_MatchComparisons_Component", matchComparison[i][2]);
					
					//Color setting pending with value matchComparison[i][3]
					ComboBoxHelper.select("DMM_MatchComparisons_Source1", matchComparison[i][4]);
					ComboBoxHelper.select("DMM_MatchComparisons_Source1Column", matchComparison[i][5]);
					ComboBoxHelper.select("DMM_MatchComparisons_Source2", matchComparison[i][6]);
					ComboBoxHelper.select("DMM_MatchComparisons_Source2Column", matchComparison[i][7]);
					TextBoxHelper.type("DMM_MatchComparisons_Delimiter", matchComparison[i][8]);
					
					if (ValidationHelper.isTrue(matchComparison[i][9]))
						CheckBoxHelper.check("DMM_MatchComparisons_Ordered");
					
					ButtonHelper.click("DMM_MatchComparisons_OK");
					GenericHelper.waitForLoadmask(detailScreenWaitSec);
				}
				 
				if (enableBestCaseMatch) {
					CheckBoxHelper.check("DMM_MatchSteps_BestCaseMatch");
					ComboBoxHelper.select("DMM_MatchSteps_BestCaseKey", bestCaseKey);
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
	
	public void addMatchSummary(String[][] matchSummary, boolean generateSummary, String alignDateSetting, boolean exactMatch, boolean onlyCompare,
			boolean overMatch, boolean underMatch) throws Exception {
		try {
			TabHelper.gotoTab("DMM_Workflow_Panel", "Data Match ...");
			
			if (ValidationHelper.isNotEmpty(matchSummary)) {
				String gridId = "DMM_MatchSummary_Grid";
				int colNum = GridHelper.getColumnNumber(gridId, "Output Column Name");
				int rowNum = GridHelper.getRowCount(gridId);
				
				for (int i = 0; i < matchSummary.length; i++ ){
					ButtonHelper.click("DMM_MatchSummary_Add");
					rowNum++;
					
					for (int j = 0; j < (colNum-1); j++) {
						String comboID = GenericHelper.getORProperty("DMM_MatchSummary_Source").replace("NUM", j+"");
						GridHelper.updateGridComboBox(gridId, comboID, rowNum, (j+1), "Output Column Name", matchSummary[i][j]);
					}
					
					GridHelper.updateGridTextBox(gridId, "DMM_MatchSummary_OutputColumn", rowNum, "Output Column Name", "Output Display Name", matchSummary[i][colNum-1]);
					
					GridHelper.updateGridTextBox(gridId, "DMM_MatchSummary_OutputDisplayName", rowNum, "Output Display Name", "Output Column Name", matchSummary[i][colNum]);
				}
			
				if (generateSummary)
					CheckBoxHelper.check("DMM_GenerateSummary");
				
				if (TextBoxHelper.isEnabled("DMM_AlignDateSettings"))
					TextBoxHelper.type("DMM_AlignDateSettings", alignDateSetting);
			}
			
			if(exactMatch)
				CheckBoxHelper.check("DMM_ExactMatch");
			else
				CheckBoxHelper.uncheck("DMM_ExactMatch");
			
			if(onlyCompare)
				CheckBoxHelper.check("DMM_OnlyCompareDiscrepancy");
			else
				CheckBoxHelper.uncheck("DMM_OnlyCompareDiscrepancy");
			
			if(overMatch)
				CheckBoxHelper.check("DMM_OverMatch");
			else
				CheckBoxHelper.uncheck("DMM_OverMatch");
			
			if(underMatch)
				CheckBoxHelper.check("DMM_UnderMatch");
			else
				CheckBoxHelper.uncheck("DMM_UnderMatch");
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}

	public void addSummaryInfo(String source1Alias, String source2Alias, String[][] summaryDimension, String[][] summaryMeasure) throws Exception {
		try {
			if (ValidationHelper.isNotEmpty(summaryDimension) || ValidationHelper.isNotEmpty(summaryMeasure)) {
				TabHelper.gotoTab("Summary  Information");
				TextBoxHelper.type("DMM_Summary_Source1Alias", source1Alias);
				TextBoxHelper.type("DMM_Summary_Source2Alias", source2Alias);
					
				if (ValidationHelper.isNotEmpty(summaryDimension)) {
					String gridId = "DMM_Summary_Dimensions_Grid";
					int colNum = GridHelper.getColumnNumber(gridId, "Alias");
					
					for (int i = 0; i < summaryDimension.length; i++) {
						ButtonHelper.click("DMM_Summary_Dimensions_Add");
						
						for (int j = 0; j < colNum-1; j++) {
							String comboId = GenericHelper.getORProperty("DMM_Summary_Dimensions_SourceColumn").replace("NUM", j+1+"");
							GridHelper.updateGridComboBox(gridId, comboId, (i+1), (j+1), "Alias", summaryDimension[i][j]);
						}
						
						int lastValueIndex = summaryDimension[i].length - 1;
						GridHelper.updateGridTextBox(gridId, "DMM_Summary_Dimensions_Alias", (i+1), "Alias", "1", summaryDimension[i][lastValueIndex]);
					}
				}
				
				if (ValidationHelper.isNotEmpty(summaryMeasure)) {
					String gridId = "DMM_Summary_Measures_Grid";
					int colNum = GridHelper.getColumnNumber(gridId, "Unit");
					
					for (int i = 0; i < summaryMeasure.length; i++){
						ButtonHelper.click("DMM_Summary_Measures_Add");
						GridHelper.updateGridComboBox(gridId, "DMM_Summary_Measures_Aggregate", (i+1), "Aggregate", "Unit", summaryMeasure[i][0]);
						
						for (int j = 1; j < colNum-1; j++) {
							GridHelper.updateGridComboBox(gridId, "DMM_Summary_Measures_SourceColumn", (i+1), (j+1), "Unit", summaryMeasure[i][j]);
						}
						
						int lastValueIndex = summaryMeasure[i].length - 1;
						GridHelper.updateGridComboBox(gridId, "DMM_Summary_Measures_Unit", (i+1), "Unit", "Aggregate", summaryMeasure[i][lastValueIndex]);
					}
				}
			}
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
}