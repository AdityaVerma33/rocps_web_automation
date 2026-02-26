package com.subex.automation.helpers.application.screens;

import java.util.ArrayList;
import java.util.HashMap;

import com.subex.automation.helpers.application.BrowserHelper;
import com.subex.automation.helpers.application.NavigationHelper;
import com.subex.automation.helpers.application.ROCHelper;
import com.subex.automation.helpers.component.ButtonHelper;
import com.subex.automation.helpers.component.CheckBoxHelper;
import com.subex.automation.helpers.component.ComboBoxHelper;
import com.subex.automation.helpers.component.EntitySearchHelper;
import com.subex.automation.helpers.component.GenericHelper;
import com.subex.automation.helpers.component.GridHelper;
import com.subex.automation.helpers.component.LabelHelper;
import com.subex.automation.helpers.component.PopupHelper;
import com.subex.automation.helpers.component.SearchGridHelper;
import com.subex.automation.helpers.component.TabHelper;
import com.subex.automation.helpers.component.TextBoxHelper;
import com.subex.automation.helpers.component.TreeHelper;
import com.subex.automation.helpers.data.DataAssertion;
import com.subex.automation.helpers.data.ValidationHelper;
import com.subex.automation.helpers.file.ExcelReader;
import com.subex.automation.helpers.report.Log4jHelper;
import com.subex.automation.helpers.scripts.TestDataHelper;
import com.subex.automation.helpers.selenium.ROCAcceptanceTest;
import com.subex.automation.helpers.util.FailureHelper;

public class TrendSummaryTableHelper extends ROCAcceptanceTest {
	
	public void createTrendSummaryTable(String path, String workBookName, String workSheetName, String testCaseName, int occurence) throws Exception {
		try {
			TestDataHelper testData = new TestDataHelper();
			ExcelReader excelReader = new ExcelReader();
			HashMap<String, ArrayList<String>> excelData = excelReader.readDataByColumn( path, workBookName, workSheetName, testCaseName, occurence );
	
			for(int i = 0; i < excelData.get("Name").size(); i++)
			{
				String partition = excelData.get("Partition").get(i);
				String name = excelData.get("Name").get(i);
				String description = excelData.get("Description").get(i);
				boolean reuseTask = ValidationHelper.isTrue(excelData.get("Reuse Task").get(i));
				boolean generate = ValidationHelper.isTrue(excelData.get("Generate").get(i));
				String outputTable = excelData.get("Output Table Name").get(i);
				String outputDSC = excelData.get("Output DSC").get(i);
				
				String [] tableInst = testData.getStringValue(excelData.get("Table Instance").get(i), firstLevelDelimiter);
				String [] tableInstGroup = testData.getStringValue(excelData.get("Table Instance Group").get(i), firstLevelDelimiter);
				String [][] joinDetails = testData.getStringValue(excelData.get("Join Details").get(i), firstLevelDelimiter, secondLevelDelimiter);
				
				String alignDateSettings = excelData.get("Align Date Settings").get(i);
				String datetimeColumn = excelData.get("Datetime Column").get(i);
				String frequency = excelData.get("Frequency").get(i);
				String lookbackStart = excelData.get("Lookback Start").get(i);
				String lookbackLength = excelData.get("Lookback Length").get(i);
				String lookbackPrune = excelData.get("Lookback Prune").get(i);
				String lookbackFrom = excelData.get("Lookback From").get(i);
				String lookbackTo = excelData.get("Lookback To").get(i);
				
				String[] columnTable = testData.getStringValue(excelData.get("Column Table").get(i), firstLevelDelimiter);
				String [] columns = testData.getStringValue(excelData.get("Columns to Add").get(i), firstLevelDelimiter);
				String [][] columnDetails = testData.getStringValue(excelData.get("Column Details").get(i), firstLevelDelimiter, secondLevelDelimiter);
				
				String [] expressionType = testData.getStringValue(excelData.get("Expression Type").get(i), firstLevelDelimiter);
				String [] filterName = testData.getStringValue(excelData.get("Filter Name").get(i), firstLevelDelimiter);
				String [] expClause = testData.getStringValue(excelData.get("Expression Clause").get(i), firstLevelDelimiter);
				String [] expLeftIndent = testData.getStringValue(excelData.get("Expression Left Indent").get(i), firstLevelDelimiter);
				String [] expExpression1 = testData.getStringValue(excelData.get("Expression Expression1").get(i), firstLevelDelimiter);
				String [] expOperator = testData.getStringValue(excelData.get("Expression Operator").get(i), firstLevelDelimiter);
				String [] expExpression2 = testData.getStringValue(excelData.get("Expression Expression2").get(i), firstLevelDelimiter);
				String [] expRightIndent = testData.getStringValue(excelData.get("Expression Right Indent").get(i), firstLevelDelimiter);
				
				boolean enable = ValidationHelper.isTrue(excelData.get("Enable").get(i));
				String frequencyMultiplier = excelData.get("Schedule Frequency Multiplier").get(i);
				String scheduleFrequency = excelData.get("Schedule Frequency").get(i);
				String nextSchedule = excelData.get("Next Schedule").get(i);
				String[][] dayGroups = testData.getStringValue(excelData.get("Day Groups").get(i), firstLevelDelimiter, secondLevelDelimiter);
				
				createTrendSummaryTable(partition, name, description, reuseTask, generate, outputTable, outputDSC, tableInst, tableInstGroup,
						joinDetails, alignDateSettings, datetimeColumn, frequency, lookbackStart, lookbackLength, lookbackPrune, lookbackFrom,
						lookbackTo, columnTable, columns, columnDetails, expressionType, filterName, expClause, expLeftIndent, expExpression1,
						expOperator, expExpression2, expRightIndent, enable, frequencyMultiplier, scheduleFrequency, nextSchedule, dayGroups);
			}
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public void createTrendSummaryTable(String partition, String name, String description, boolean reuseTask, boolean generate, String outputTable,
			String outputDSC, String[] tableInst, String[] tableInstGroup, String[][] joinDetails, String alignDateSettings, String datetimeColumn,
			String frequency, String lookbackStart, String lookbackLength, String lookbackPrune, String lookbackFrom, String lookbackTo,
			String[] columnTable, String[] columns, String[][] columnDetails, String[] expressionType, String[] filterName, String[] expClause,
			String[] expLeftIndent, String[] expExpression1, String[] expOperator, String[] expExpression2, String[] expRightIndent, boolean enable,
			String frequencyMultiplier, String scheduleFrequency, String nextSchedule, String[][] dayGroups) throws Exception {
		try {
			int row = navigateToTrendSummary(name);

			if ( row > 0 ) {
				Log4jHelper.logWarning("Trend Summary Table '" + name + "' is already present.");
			}
			else {
				String detailScreenTitle = NavigationHelper.navigateToNew(partition, "TrendSummary_Name");
				assertTrue(LabelHelper.isTitlePresent("New Trend Summary Table"));
				
				TextBoxHelper.type("TrendSummary_Name", name);
				TextBoxHelper.type("TrendSummary_Description", description);
				if (!reuseTask)
					CheckBoxHelper.check("TrendSummary_ReuseTask");
				if (generate)
					CheckBoxHelper.check("TrendSummary_Generate");
				TextBoxHelper.type("TrendSummary_OutputTableName", outputTable);
				ComboBoxHelper.select("TrendSummary_OutputTableDSCName", outputDSC);
				
				addTableInstance(tableInst);
				
				addTableInstanceGroup(tableInstGroup);
				
				updateJoin(joinDetails);
				
				TextBoxHelper.type("TrendSummary_AlignDateSettings", alignDateSettings);
				ComboBoxHelper.select("TrendSummary_DatetimeColumn", datetimeColumn);
				ComboBoxHelper.select("TrendSummary_Frequency", frequency);
				TextBoxHelper.type("TrendSummary_Lookback_Start", lookbackStart);
				TextBoxHelper.type("TrendSummary_Lookback_Length", lookbackLength);
				TextBoxHelper.type("TrendSummary_Lookback_Prune", lookbackPrune);
				TextBoxHelper.type("TrendSummary_Lookback_From", lookbackFrom);
				TextBoxHelper.type("TrendSummary_Lookback_To", lookbackTo);
				
				TabHelper.gotoTab("Columns");
				addColumns(columnTable, columns, columnDetails);
				
				if (ValidationHelper.isNotEmpty(expressionType)) {
					TabHelper.gotoTab("Filter");
					QueryFilterHelper queryFilter = new QueryFilterHelper("QueryFilter_Expression_GridWrapper");
					queryFilter.addCondition(expressionType, filterName, expClause, expLeftIndent, expExpression1, expOperator, expExpression2, expRightIndent);
				}
				
				if (enable) {
					TabHelper.gotoTab("Schedule");
					CheckBoxHelper.check("TrendSummary_Schedule_Enable");
					ROCHelper rocHelper = new ROCHelper();
					rocHelper.updateCollectionTimes(frequencyMultiplier, scheduleFrequency, nextSchedule, dayGroups);
				}
				
				saveTrendSummary(name, detailScreenTitle);
			}
		} catch (AssertionError e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public void scheduleTrendSummaryTable(String path, String workBookName, String workSheetName, String testCaseName, int occurence) throws Exception {
		try {
			ExcelReader excelReader = new ExcelReader();
			HashMap<String, ArrayList<String>> excelData = excelReader.readDataByColumn( path, workBookName, workSheetName, testCaseName, occurence );
	
			for(int i = 0; i < excelData.get("Name").size(); i++)
			{
				String name = excelData.get("Name").get(i);
				scheduleTrendSummaryTable(name);
			}
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public void scheduleTrendSummaryTable(String name) throws Exception {
		try {
			int row = navigateToTrendSummary(name);

			if ( row > 0 ) {
				GridHelper.clickRow("SearchGrid", row, "Name");
				NavigationHelper.navigateToAction("Trend Summary Table Actions");
				
				if (NavigationHelper.isActionPresent("Schedule"))
					NavigationHelper.navigateToAction("Schedule");
				else
					NavigationHelper.navigateToAction("Reschedule");
				GenericHelper.waitForLoadmask(searchScreenWaitSec);
			}
			else {
				FailureHelper.failTest("Trend Summary Table '" + name + "' is not present.");
			}
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public void verifyTrendSummaryTable(String path, String workBookName, String workSheetName, String testCaseName, int occurence) throws Exception {
		try {
			ExcelReader excelReader = new ExcelReader();
			HashMap<String, ArrayList<String>> excelData = excelReader.readDataByColumn( path, workBookName, workSheetName, testCaseName, occurence );
	
			for(int i = 0; i < excelData.get("Name").size(); i++)
			{
				String name = excelData.get("Name").get(i);
				String status = excelData.get("Status").get(i);
				
				verifyTrendSummaryTable(name, status);
			}
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public void verifyTrendSummaryTable(String name, String status) throws Exception {
		try {
			int row = navigateToTrendSummary(name);
			
			if (row > 0) {
				SearchGridHelper.gridFilterSearchWithComboBox("TrendSummary_Status_Filter", status, "Status");
				
				ButtonHelper.click("SearchButton");
				GenericHelper.waitForLoadmask(searchScreenWaitSec);
				int rows = GridHelper.getRowCount("SearchGrid");
				
				if (rows >= 0) {
					Log4jHelper.logInfo("Trend Summary Table '" + name + "' with status '" + status + "' is found as expected.");
				}
				else {
					FailureHelper.failTest("Trend Summary Table '" + name + "' with status '" + status + "' is not found.");
				}
			}
			else {
				FailureHelper.failTest("Trend Summary Table '" + name + "' is not found.");
			}
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public void verifyResult(String path, String workBookName, String workSheetName, String testCaseName, int occurence) throws Exception {
		try {
			TestDataHelper testData = new TestDataHelper();
			ExcelReader excelReader = new ExcelReader();
			HashMap<String, ArrayList<String>> excelData = excelReader.readDataByColumn( path, workBookName, workSheetName, testCaseName, occurence );
	
			for(int i = 0; i < excelData.get("Name").size(); i++)
			{
				String name = excelData.get("Name").get(i);
				int resultCount = testData.getIntegerValue(excelData.get("Result Count").get(i));
				
				String resultExcelPath = excelData.get("Result Excel Path").get(i);
				String resultExcelFilename = excelData.get("Result Excel Filename").get(i);
				String resultExcelSheetname = excelData.get("Result Excel Sheetname").get(i);
				String resultExcelTCName = excelData.get("Result Excel TCName").get(i);
				
				String[] expressionType = testData.getStringValue(excelData.get("Expression Type").get(i), firstLevelDelimiter);
				String[] filterName = testData.getStringValue(excelData.get("Filter Name").get(i), firstLevelDelimiter);
				String[] expClause = testData.getStringValue(excelData.get("Expression Clause").get(i), firstLevelDelimiter);
				String[] expLeftIndent = testData.getStringValue(excelData.get("Expression Left Indent").get(i), firstLevelDelimiter);
				String[] expExpression1 = testData.getStringValue(excelData.get("Expression Expression1").get(i), firstLevelDelimiter);
				String[] expOperator = testData.getStringValue(excelData.get("Expression Operator").get(i), firstLevelDelimiter);
				String[] expExpression2 = testData.getStringValue(excelData.get("Expression Expression2").get(i), firstLevelDelimiter);
				String[] expRightIndent = testData.getStringValue(excelData.get("Expression Right Indent").get(i), firstLevelDelimiter);
				
				verifyResult(name, resultCount, resultExcelPath, resultExcelFilename, resultExcelSheetname, resultExcelTCName, expressionType, filterName,
						expClause, expLeftIndent, expExpression1, expOperator, expExpression2, expRightIndent);
			}
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public void verifyResult(String name, int resultCount, String resultExcelPath, String resultExcelFilename, String resultExcelSheetname, String resultExcelTCName,
			String[] expressionType, String[] filterName, String[] expClause, String[] expLeftIndent, String[] expExpression1, String[] expOperator,
			String[] expExpression2, String[] expRightIndent) throws Exception {
		try {
			int row = navigateToTrendSummary(name);
			
			if (row > 0) {
				if (resultCount > 0 || (ValidationHelper.isNotEmpty(resultExcelPath) && ValidationHelper.isNotEmpty(resultExcelSheetname))) {
					GridHelper.clickRow("SearchGrid", row, "Name");
					NavigationHelper.navigateToAction("Trend Summary Results", "Jump To Results");
					GenericHelper.waitForLoadmask(searchScreenWaitSec);
					GenericHelper.waitForElement("JumpToResult_From", searchScreenWaitSec);
					
					DataAssertion dataAssertion = new DataAssertion();
					BrowserHelper browser = new BrowserHelper();
					
					if (ValidationHelper.isNotEmpty(expressionType))
						applyCondition(expressionType, filterName, expClause, expLeftIndent, expExpression1, expOperator, expExpression2, expRightIndent);
					
					if (resultCount > 0) {
						ButtonHelper.click("J2S_RecordCount");
						GenericHelper.waitForLoadmask(searchScreenWaitSec);
						GenericHelper.waitForElement("OKButton", searchScreenWaitSec);
						
						assertTrue(PopupHelper.isTextPresent("Search query returned " + resultCount + " row(s)."));
						ButtonHelper.click("OKButton");
						GenericHelper.waitForLoadmask(searchScreenWaitSec);
					}
					
					if (ValidationHelper.isNotEmpty(resultExcelPath) && ValidationHelper.isNotEmpty(resultExcelSheetname))
						dataAssertion.testDataAssertion(resultExcelPath, resultExcelFilename, resultExcelSheetname, resultExcelTCName);
					browser.back();
				}
			}
			else {
				FailureHelper.failTest("Trend Summary Table '" + name + "' is not found.");
			}
		} catch (AssertionError e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public void addTableInstance(String[] tableInst) throws Exception {
		try {
			if (ValidationHelper.isNotEmpty(tableInst)) {
				for (int i = 0; i < tableInst.length; i++ ){
					boolean isPresent = GridHelper.isValuePresent("TrendSummary_Sources_Grid", tableInst[i], "Table Name");
					
					if (!isPresent) {
						ButtonHelper.click("TrendSummary_Sources_AddTableInstance");
						GenericHelper.waitForLoadmask(detailScreenWaitSec);
						
						EntitySearchHelper entitySearch = new EntitySearchHelper();
						entitySearch.selectUsingGridFilterTextBox("Table Instance Search", "TableInst_DisplayName", tableInst[i], "Display Name");
					}
				}
			}
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public void addTableInstanceGroup(String[] tableInstGroup) throws Exception {
		try {
			if (ValidationHelper.isNotEmpty(tableInstGroup)) {
				for (int i = 0; i < tableInstGroup.length; i++ ){
					boolean isPresent = GridHelper.isValuePresent("TrendSummary_Sources_Grid", tableInstGroup[i], "Table Name");
					
					if (!isPresent) {
						ButtonHelper.click("TrendSummary_Sources_AddTableInstanceGroup");
						GenericHelper.waitForLoadmask(detailScreenWaitSec);
						
						EntitySearchHelper entitySearch = new EntitySearchHelper();
						entitySearch.selectUsingGridFilterTextBox("Table Instance Group Search", "TableInstanceGroup_Name", tableInstGroup[i], "Group Name");
					}
				}
			}
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public void updateJoin(String[][] joinDetails) throws Exception {
		try {
			if (ValidationHelper.isNotEmpty(joinDetails)) {
				String gridId = "TrendSummary_Sources_Grid";
				
				for (int i = 0; i < joinDetails.length; i++) {
					if (ValidationHelper.isNotEmpty(joinDetails[i])) {
						int rowNum = i + 1;
						GridHelper.updateGridComboBox(gridId, "TrendSummary_Source_JoinType", rowNum, "Join Type", "Table Name", joinDetails[i][0]);
						
						GridHelper.updateGridTextBox(gridId, "TrendSummary_Source_TableAlias", rowNum, "Table Alias", "Table Name", joinDetails[i][1]);
						if (rowNum > 1) {
							GridHelper.clickRow(gridId, rowNum, "Column 1");
							if (!ComboBoxHelper.isPresent("TrendSummary_Source_EditColumn1"))
								GridHelper.clickRow(gridId, rowNum, "Column 1");
							GridHelper.clickRow(gridId, 1, "Table Name");
							GridHelper.clickRow(gridId, 1, "Table Name");
						}
						
						GridHelper.updateGridTextBox(gridId, "TrendSummary_Source_EditColumn1", rowNum, "Edit Column 1", "Table Name", joinDetails[i][3]);
						GridHelper.updateGridComboBox(gridId, "TrendSummary_Source_Column1", rowNum, "Column 1", "Table Name", joinDetails[i][2]);
						
						if (rowNum > 1) {
							GridHelper.clickRow(gridId, rowNum, "Column 2");
							if (!ComboBoxHelper.isPresent("TrendSummary_Source_EditColumn2"))
								GridHelper.clickRow(gridId, rowNum, "Column 2");
							GridHelper.clickRow(gridId, 1, "Table Name");
							GridHelper.clickRow(gridId, 1, "Table Name");
						}
						
						GridHelper.updateGridTextBox(gridId, "TrendSummary_Source_EditColumn2", rowNum, "Edit Column 2", "Table Name", joinDetails[i][5]);
						GridHelper.updateGridComboBox(gridId, "TrendSummary_Source_Column2", rowNum, "Column 2", "Table Name", joinDetails[i][4]);
					}
				}
			}
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public void addColumns(String[] columnTable, String[] columns, String[][] columnDetails) throws Exception {
		try {
			if (ValidationHelper.isNotEmpty(columns)) {
				String gridId = "TrendSummary_SelectedColumns_Grid";
				
				for (int i = 0; i < columns.length; i++) {
					String statement = columns[i];
					if (statement.contains("(")) {
						int length = statement.length();
						int index = statement.indexOf("(");
						statement = statement.substring((index+1), length);
						index = statement.indexOf(")");
						statement = statement.substring(0, (index-1)).trim();
					}
					
					int rowNum = GridHelper.getRowNumber(gridId, statement, "Column Name");
					
					if (rowNum == 0) {
						TreeHelper.clickChild("TrendSummary_AvailableColumns_Tree", columnTable[i], columns[i]);
						ButtonHelper.click("TrendSummary_Columns_AddButton");
						GenericHelper.waitForLoadmask(detailScreenWaitSec);
						
						rowNum = GridHelper.getRowNumber(gridId, statement, "Statement");
					}
					
					if(ValidationHelper.isNotEmpty(columnDetails)) {
						GridHelper.updateGridComboBox(gridId, "TrendSummary_SelectedColumns_Aggregate", rowNum, "Aggregate", "Frequency", columnDetails[i][0]);
						
						GridHelper.updateGridTextBox(gridId, "TrendSummary_SelectedColumns_Statement", rowNum, "Statement", "Frequency", columnDetails[i][1]);
						
						GridHelper.updateGridTextBox(gridId, "TrendSummary_SelectedColumns_ColumnName", rowNum, "Column Name", "Frequency", columnDetails[i][2]);
						
						GridHelper.updateGridTextBox(gridId, "TrendSummary_SelectedColumns_DisplayName", rowNum, "Display Name", "Frequency", columnDetails[i][3]);
						
						GridHelper.updateGridComboBox(gridId, "TrendSummary_SelectedColumns_Type", rowNum, "Type", "Frequency", columnDetails[i][4]);
						
						boolean isNull = ValidationHelper.isTrue(columnDetails[i][5]);
						GridHelper.updateGridCheckBox(gridId, "TrendSummary_SelectedColumns_NotNull", rowNum, "Not Null", isNull);
						if (ButtonHelper.isPresent("YesButton")) {
							ButtonHelper.click("YesButton");
							GenericHelper.waitForLoadmask(detailScreenWaitSec);
						}
					}
				}
			}
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	private void applyCondition(String[] expressionType, String[] filterName, String[] expClause, String[] expLeftIndent, String[] expExpression1,
			String[] expOperator, String[] expExpression2, String[] expRightIndent) throws Exception {
		try {
			if (ValidationHelper.isNotEmpty(expressionType)) {
				QueryFilterHelper queryFilter = new QueryFilterHelper();
				queryFilter.addCondition(expressionType, filterName, expClause, expLeftIndent, expExpression1, expOperator, expExpression2, expRightIndent);
				
				ButtonHelper.click("Search");
				GenericHelper.waitForLoadmask(searchScreenWaitSec);
			}
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	private int navigateToTrendSummary(String name) throws Exception {
		try {
			NavigationHelper.navigateToScreen("Trend Summary Tables", "Trend Summary Table Search");
			int row = SearchGridHelper.gridFilterSearchWithTextBox("TrendSummary_Name", name, "Name");
			
			return row;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	private void saveTrendSummary(String name, String detailScreenTitle) throws Exception {
		try {
			ButtonHelper.click("SaveButton");
			GenericHelper.waitForLoadmask(detailScreenWaitSec);
			
			assertTrue(LabelHelper.isTitleNotPresent(detailScreenTitle));
			ButtonHelper.click("SearchButton");
			GenericHelper.waitForLoadmask(searchScreenWaitSec);
			assertTrue(GridHelper.isValuePresent("SearchGrid", name, "Name"));
			Log4jHelper.logInfo("Trend Summary Table '" + name + "' created.");
		} catch (AssertionError e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
}