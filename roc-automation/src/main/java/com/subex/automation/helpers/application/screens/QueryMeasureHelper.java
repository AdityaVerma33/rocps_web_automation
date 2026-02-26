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
import com.subex.automation.helpers.component.GridHelper;
import com.subex.automation.helpers.component.LabelHelper;
import com.subex.automation.helpers.component.SearchGridHelper;
import com.subex.automation.helpers.component.TabHelper;
import com.subex.automation.helpers.component.TextAreaHelper;
import com.subex.automation.helpers.component.TextBoxHelper;
import com.subex.automation.helpers.component.TreeHelper;
import com.subex.automation.helpers.data.ValidationHelper;
import com.subex.automation.helpers.file.ExcelReader;
import com.subex.automation.helpers.report.Log4jHelper;
import com.subex.automation.helpers.scripts.TestDataHelper;
import com.subex.automation.helpers.selenium.ROCAcceptanceTest;
import com.subex.automation.helpers.util.FailureHelper;

public class QueryMeasureHelper extends ROCAcceptanceTest {
	
	private static String partition = null;
	private static String name = null;
	private static String outputDSC = null;
	
	public QueryMeasureHelper() {
		
	}
	
	public QueryMeasureHelper(String qmName) throws Exception {
		try {
			partition = "Common";
			name = qmName;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public QueryMeasureHelper(String securityPartition, String qmName) throws Exception {
		try {
			partition = securityPartition;
			name = qmName;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public QueryMeasureHelper(String securityPartition, String qmName, String outputTableDSC) throws Exception {
		try {
			partition = securityPartition;
			name = qmName;
			outputDSC = outputTableDSC;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public void createQueryMeasure(String path, String workBookName, String workSheetName, String testCaseName, int occurence) throws Exception {
		try {
			TestDataHelper testData = new TestDataHelper();
			ExcelReader excelReader = new ExcelReader();
			HashMap<String, ArrayList<String>> excelData = excelReader.readDataByColumn( path, workBookName, workSheetName, testCaseName, occurence );
	
			for(int i = 0; i < excelData.get("Name").size(); i++)
			{
				partition = excelData.get("Partition").get(i);
				name = excelData.get("Name").get(i);
				outputDSC = excelData.get("Output DSC").get(i);
				
				String [] tableInst = testData.getStringValue(excelData.get("Table Instance").get(i), firstLevelDelimiter);
				String [] tableDfn = testData.getStringValue(excelData.get("Table Definition").get(i), firstLevelDelimiter);
				String [] inputMeasure = testData.getStringValue(excelData.get("Input Measure").get(i), firstLevelDelimiter);
				String [] inputTable = testData.getStringValue(excelData.get("Input Table").get(i), firstLevelDelimiter);
				String [][] joinDetails = testData.getStringValue(excelData.get("Join Details").get(i), firstLevelDelimiter, secondLevelDelimiter);
				
				String maxRows = excelData.get("Maximum Rows").get(i);
				boolean returnDistinctRowsFl = ValidationHelper.isTrue(excelData.get("Return Distinct Rows").get(i));
				boolean truncateBeforeLoadFl = ValidationHelper.isTrue(excelData.get("Truncate Before Load").get(i));
				
				String[] columnTable = testData.getStringValue(excelData.get("Column Table").get(i), firstLevelDelimiter);
				String [] columns = testData.getStringValue(excelData.get("Columns to Add").get(i), firstLevelDelimiter);
				String [][] columnDetails = testData.getStringValue(excelData.get("Column Details").get(i), firstLevelDelimiter, secondLevelDelimiter);
				
				String datetimeColumn = excelData.get("Datetime Column").get(i);
				String frequency = excelData.get("Frequency").get(i);
				String frequencyDate = excelData.get("Frequency Date").get(i);
				
				boolean isExistingTable = ValidationHelper.isTrue(excelData.get("Is Existing Table").get(i));
				String rtTableName = excelData.get("Reporting Table Name").get(i);
				String rtDisplayName = excelData.get("Reporting Table Display Name").get(i);
				boolean rtTruncateBeforeLoad = ValidationHelper.isTrue(excelData.get("RT Truncate Before Load").get(i));
				String[][] rtColumnDetails = testData.getStringValue(excelData.get("RT Column Details").get(i), firstLevelDelimiter, secondLevelDelimiter);
				
				String caseTemplateName = excelData.get("Case Template Name").get(i);
				String[][] casePropertyMapping = testData.getStringValue(excelData.get("Case Property Mapping").get(i), firstLevelDelimiter, secondLevelDelimiter);
				
				String [] expressionType = testData.getStringValue(excelData.get("Expression Type").get(i), firstLevelDelimiter);
				String [] filterName = testData.getStringValue(excelData.get("Filter Name").get(i), firstLevelDelimiter);
				String [] expClause = testData.getStringValue(excelData.get("Expression Clause").get(i), firstLevelDelimiter);
				String [] expLeftIndent = testData.getStringValue(excelData.get("Expression Left Indent").get(i), firstLevelDelimiter);
				String [] expExpression1 = testData.getStringValue(excelData.get("Expression Expression1").get(i), firstLevelDelimiter);
				String [] expOperator = testData.getStringValue(excelData.get("Expression Operator").get(i), firstLevelDelimiter);
				String [] expExpression2 = testData.getStringValue(excelData.get("Expression Expression2").get(i), firstLevelDelimiter);
				String [] expRightIndent = testData.getStringValue(excelData.get("Expression Right Indent").get(i), firstLevelDelimiter);
				
				String [] havingExpType = testData.getStringValue(excelData.get("Having Expression Type").get(i), firstLevelDelimiter);
				String [] havingExpFilter = testData.getStringValue(excelData.get("Having Filter Name").get(i), firstLevelDelimiter);
				String [] havingExpClause = testData.getStringValue(excelData.get("Having Expression Clause").get(i), firstLevelDelimiter);
				String [] havingExpLeftIndent = testData.getStringValue(excelData.get("Having Expression Left Indent").get(i), firstLevelDelimiter);
				String [] havingExpAggregate1 = testData.getStringValue(excelData.get("Having Expression Left Aggregate").get(i), firstLevelDelimiter);
				String [] havingExpExpression1 = testData.getStringValue(excelData.get("Having Expression1").get(i), firstLevelDelimiter);
				String [] havingExpOperator = testData.getStringValue(excelData.get("Having Expression Operator").get(i), firstLevelDelimiter);
				String [] havingExpAggregate2 = testData.getStringValue(excelData.get("Having Expression Right Aggregate").get(i), firstLevelDelimiter);
				String [] havingExpExpression2 = testData.getStringValue(excelData.get("Having Expression2").get(i), firstLevelDelimiter);
				String [] havingExpRightIndent = testData.getStringValue(excelData.get("Having Expression Right Indent").get(i), firstLevelDelimiter);
				
				createQueryMeasure(tableInst, tableDfn, inputMeasure, inputTable, joinDetails, maxRows, returnDistinctRowsFl, truncateBeforeLoadFl, columnTable,
						columns, columnDetails, datetimeColumn, frequency, frequencyDate, isExistingTable, rtTableName, rtDisplayName, rtTruncateBeforeLoad,
						rtColumnDetails, caseTemplateName, casePropertyMapping, expressionType, filterName, expClause, expLeftIndent, expExpression1,
						expOperator, expExpression2, expRightIndent, havingExpType, havingExpFilter, havingExpClause, havingExpLeftIndent, havingExpAggregate1,
						havingExpExpression1, havingExpOperator, havingExpAggregate2, havingExpExpression2, havingExpRightIndent);
			}
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public void createQueryMeasure (String[] tableInst, String[] tableDfn, String[] inputMeasure, String[] inputTable, String[][] joinDetails, String maxRows,
			boolean returnDistinctRowsFl, boolean truncateBeforeLoadFl, String[] columnTable, String[] columns, String[][] columnDetails,
			String datetimeColumn, String frequency, String frequencyDate, boolean isExistingTable, String rtTableName, String rtDisplayName,
			boolean rtTruncateBeforeLoad, String[][] rtColumnDetails, String caseTemplateName, String[][] casePropertyMapping, String[] expressionType,
			String[] filterName, String[] expClause, String[] expLeftIndent, String[] expExpression1, String[] expOperator, String[] expExpression2,
			String[] expRightIndent, String[] havingExpType, String[] havingExpFilter, String[] havingExpClause, String[] havingExpLeftIndent,
			String[] havingExpAggregate1, String[] havingExpExpression1, String[] havingExpOperator, String[] havingExpAggregate2,
			String[] havingExpExpression2, String[] havingExpRightIndent) throws Exception {
		try {
			NavigationHelper.navigateToScreen("Measures", "Measure Search");
			int row = SearchGridHelper.gridFilterSearchWithTextBox("Measure_Name", name, "Name");

			if ( row > 0 ) {
				Log4jHelper.logWarning("Measure '" + name + "' is already present.");
			}
			else {
				NavigationHelper.navigateToAction("Common Tasks", "New", "Query Measure");
				NavigationHelper.selectPartition(partition);
				GenericHelper.waitForElement("QM_Name", detailScreenWaitSec);
				assertTrue(LabelHelper.isTitlePresent("New Query Measure"));
				String detailScreenTitle = NavigationHelper.getScreenTitle();
				
				updateQueryMeasure(tableInst, tableDfn, inputMeasure, inputTable, joinDetails, maxRows, returnDistinctRowsFl, truncateBeforeLoadFl,
						columnTable, columns, columnDetails, datetimeColumn, frequency, frequencyDate, isExistingTable, rtTableName, rtDisplayName,
						rtTruncateBeforeLoad, rtColumnDetails, caseTemplateName, casePropertyMapping, expressionType, filterName, expClause, expLeftIndent,
						expExpression1, expOperator, expExpression2, expRightIndent, havingExpType, havingExpFilter, havingExpClause, havingExpLeftIndent,
						havingExpAggregate1, havingExpExpression1, havingExpOperator, havingExpAggregate2, havingExpExpression2, havingExpRightIndent);
				
				MeasureHelper measure = new MeasureHelper();
				measure.saveMeasure("Query Measure", name, detailScreenTitle);
			}
		} catch (AssertionError e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public void updateQueryMeasure(String path, String workBookName, String workSheetName, String testCaseName, int occurence) throws Exception {
		try {
			TestDataHelper testData = new TestDataHelper();
			ExcelReader excelReader = new ExcelReader();
			HashMap<String, ArrayList<String>> excelData = excelReader.readDataByColumn( path, workBookName, workSheetName, testCaseName, occurence );
	
			for(int i = 0; i < excelData.get("Name").size(); i++)
			{
				name = excelData.get("Name").get(i);
				outputDSC = excelData.get("Output DSC").get(i);
				
				String [] tableInst = testData.getStringValue(excelData.get("Table Instance").get(i), firstLevelDelimiter);
				String [] tableDfn = testData.getStringValue(excelData.get("Table Definition").get(i), firstLevelDelimiter);
				String [] inputMeasure = testData.getStringValue(excelData.get("Input Measure").get(i), firstLevelDelimiter);
				String [] inputTable = testData.getStringValue(excelData.get("Input Table").get(i), firstLevelDelimiter);
				String [][] joinDetails = testData.getStringValue(excelData.get("Join Details").get(i), firstLevelDelimiter, secondLevelDelimiter);
				
				String maxRows = excelData.get("Maximum Rows").get(i);
				boolean returnDistinctRowsFl = ValidationHelper.isTrue(excelData.get("Return Distinct Rows").get(i));
				boolean truncateBeforeLoadFl = ValidationHelper.isTrue(excelData.get("Truncate Before Load").get(i));
				
				String[] columnTable = testData.getStringValue(excelData.get("Column Table").get(i), firstLevelDelimiter);
				String [] columns = testData.getStringValue(excelData.get("Columns to Add").get(i), firstLevelDelimiter);
				String [][] columnDetails = testData.getStringValue(excelData.get("Column Details").get(i), firstLevelDelimiter, secondLevelDelimiter);
				
				String datetimeColumn = excelData.get("Datetime Column").get(i);
				String frequency = excelData.get("Frequency").get(i);
				String frequencyDate = excelData.get("Frequency Date").get(i);
				
				boolean isExistingTable = ValidationHelper.isTrue(excelData.get("Is Existing Table").get(i));
				String rtTableName = excelData.get("Reporting Table Name").get(i);
				String rtDisplayName = excelData.get("Reporting Table Display Name").get(i);
				boolean rtTruncateBeforeLoad = ValidationHelper.isTrue(excelData.get("RT Truncate Before Load").get(i));
				String[][] rtColumnDetails = testData.getStringValue(excelData.get("RT Column Details").get(i), firstLevelDelimiter, secondLevelDelimiter);
				
				String caseTemplateName = excelData.get("Case Template Name").get(i);
				String[][] casePropertyMapping = testData.getStringValue(excelData.get("Case Property Mapping").get(i), firstLevelDelimiter, secondLevelDelimiter);
				
				String [] expressionType = testData.getStringValue(excelData.get("Expression Type").get(i), firstLevelDelimiter);
				String [] filterName = testData.getStringValue(excelData.get("Filter Name").get(i), firstLevelDelimiter);
				String [] expClause = testData.getStringValue(excelData.get("Expression Clause").get(i), firstLevelDelimiter);
				String [] expLeftIndent = testData.getStringValue(excelData.get("Expression Left Indent").get(i), firstLevelDelimiter);
				String [] expExpression1 = testData.getStringValue(excelData.get("Expression Expression1").get(i), firstLevelDelimiter);
				String [] expOperator = testData.getStringValue(excelData.get("Expression Operator").get(i), firstLevelDelimiter);
				String [] expExpression2 = testData.getStringValue(excelData.get("Expression Expression2").get(i), firstLevelDelimiter);
				String [] expRightIndent = testData.getStringValue(excelData.get("Expression Right Indent").get(i), firstLevelDelimiter);
				
				String [] havingExpType = testData.getStringValue(excelData.get("Having Expression Type").get(i), firstLevelDelimiter);
				String [] havingExpFilter = testData.getStringValue(excelData.get("Having Filter Name").get(i), firstLevelDelimiter);
				String [] havingExpClause = testData.getStringValue(excelData.get("Having Expression Clause").get(i), firstLevelDelimiter);
				String [] havingExpLeftIndent = testData.getStringValue(excelData.get("Having Expression Left Indent").get(i), firstLevelDelimiter);
				String [] havingExpAggregate1 = testData.getStringValue(excelData.get("Having Expression Left Aggregate").get(i), firstLevelDelimiter);
				String [] havingExpExpression1 = testData.getStringValue(excelData.get("Having Expression1").get(i), firstLevelDelimiter);
				String [] havingExpOperator = testData.getStringValue(excelData.get("Having Expression Operator").get(i), firstLevelDelimiter);
				String [] havingExpAggregate2 = testData.getStringValue(excelData.get("Having Expression Right Aggregate").get(i), firstLevelDelimiter);
				String [] havingExpExpression2 = testData.getStringValue(excelData.get("Having Expression2").get(i), firstLevelDelimiter);
				String [] havingExpRightIndent = testData.getStringValue(excelData.get("Having Expression Right Indent").get(i), firstLevelDelimiter);
				
				updateQueryMeasure(tableInst, tableDfn, inputMeasure, inputTable, joinDetails, maxRows, returnDistinctRowsFl, truncateBeforeLoadFl, columnTable,
						columns, columnDetails, datetimeColumn, frequency, frequencyDate, isExistingTable, rtTableName, rtDisplayName, rtTruncateBeforeLoad,
						rtColumnDetails, caseTemplateName, casePropertyMapping, expressionType, filterName, expClause, expLeftIndent, expExpression1, expOperator,
						expExpression2, expRightIndent, havingExpType, havingExpFilter, havingExpClause, havingExpLeftIndent, havingExpAggregate1, havingExpExpression1,
						havingExpOperator, havingExpAggregate2, havingExpExpression2, havingExpRightIndent);
			}
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public void updateQueryMeasure (String[] tableInst, String[] tableDfn, String[] inputMeasure, String[] inputTable, String[][] joinDetails, String maxRows,
			boolean returnDistinctRowsFl, boolean truncateBeforeLoadFl, String[] columnTable, String[] columns, String[][] columnDetails,
			String datetimeColumn, String frequency, String frequencyDate, boolean isExistingTable, String rtTableName, String rtDisplayName,
			boolean rtTruncateBeforeLoad, String[][] rtColumnDetails, String caseTemplateName, String[][] casePropertyMapping, String[] expressionType,
			String[] filterName, String[] expClause, String[] expLeftIndent, String[] expExpression1, String[] expOperator, String[] expExpression2,
			String[] expRightIndent, String[] havingExpType, String[] havingExpFilter, String[] havingExpClause, String[] havingExpLeftIndent,
			String[] havingExpAggregate1, String[] havingExpExpression1, String[] havingExpOperator, String[] havingExpAggregate2,
			String[] havingExpExpression2, String[] havingExpRightIndent) throws Exception {
		try {
			TextBoxHelper.type("QM_Name", name);
			ComboBoxHelper.select("QM_OutputTableDSC", outputDSC);
			
			MeasureHelper measure = new MeasureHelper();
			measure.addInputMeasure("QM_Source_Grid", inputMeasure, inputTable);
			
			addTableInstance(tableInst);
			
			addTableDefinition(tableDfn);
			
			updateJoin(joinDetails);
			
			TextBoxHelper.type("QM_MaximumRows", maxRows);
			if (returnDistinctRowsFl)
				CheckBoxHelper.check("QM_ReturnDistinctRows");
			
			if (truncateBeforeLoadFl && CheckBoxHelper.isEnabled("QM_TruncateBeforeLoad"))
				CheckBoxHelper.check("QM_TruncateBeforeLoad");
			
			TabHelper.gotoTab("QM_Workflow_Panel", "Columns");
			addColumns(columnTable, columns, columnDetails);
			
			ComboBoxHelper.select("QM_DatetimeColumn", datetimeColumn);
			ComboBoxHelper.select("QM_Frequency", frequency);
			TextBoxHelper.type("QM_FrequencyDate", frequencyDate);
			
			if (ValidationHelper.isNotEmpty(rtTableName) || ValidationHelper.isNotEmpty(caseTemplateName)) {
				TabHelper.gotoTab("QM_Workflow_Panel", "Sources");
				measure.addReportingTable("Measure_ReportingTable", isExistingTable, rtTableName, rtDisplayName, rtTruncateBeforeLoad, rtColumnDetails);
				measure.linkCaseTemplate("Measure_CaseTemplate", caseTemplateName, casePropertyMapping);
			}
			
			if (ValidationHelper.isNotEmpty(expressionType) || ValidationHelper.isNotEmpty(havingExpType)) {
				TabHelper.gotoTab("QM_Workflow_Panel", "Filters");
				QueryFilterHelper queryFilter = new QueryFilterHelper("QueryFilter_Expression_GridWrapper");
				queryFilter.addCondition(expressionType, filterName, expClause, expLeftIndent, expExpression1, expOperator, expExpression2, expRightIndent);
				
				measure.addHavingClause(havingExpType, havingExpFilter, havingExpClause, havingExpLeftIndent, havingExpAggregate1,
						havingExpExpression1, havingExpOperator, havingExpAggregate2, havingExpExpression2, havingExpRightIndent);
			}
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public void addTableInstance(String[] tableInst) throws Exception {
		try {
			if (ValidationHelper.isNotEmpty(tableInst)) {
				for (int i = 0; i < tableInst.length; i++ ){
					boolean isPresent = GridHelper.isValuePresent("QM_Source_Grid", tableInst[i], "Table Name");
					
					if (!isPresent) {
						ButtonHelper.click("QM_AddTableInstance");
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
	
	public void addTableDefinition(String[] tableDfn) throws Exception {
		try {
			if (ValidationHelper.isNotEmpty(tableDfn)) {
				for (int i = 0; i < tableDfn.length; i++ ){
					boolean isPresent = GridHelper.isValuePresent("QM_Source_Grid", tableDfn[i], "Table Name");
					
					if (!isPresent) {
						ButtonHelper.click("QM_AddTableDefinition");
						GenericHelper.waitForLoadmask(detailScreenWaitSec);
						
						EntitySearchHelper entitySearch = new EntitySearchHelper();
						entitySearch.selectUsingGridFilterTextBox("Table Definition Search", "TableDfn_Name", tableDfn[i], "Name");
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
				for (int i = 0; i < joinDetails.length; i++){
					if (ValidationHelper.isNotEmpty(joinDetails[i])) {
						int rowNum = i + 1;
						GridHelper.updateGridComboBox("QM_Source_Grid", "QM_Source_JoinType", rowNum, "Join Type", "Table Name", joinDetails[i][0]);
						
						GridHelper.updateGridTextBox("QM_Source_Grid", "QM_Source_TableAlias", rowNum, "Table Alias", "Table Name", joinDetails[i][1]);
						if (rowNum > 1) {
							GridHelper.clickRow("QM_Source_Grid", rowNum, "Column 1");
							if (!ComboBoxHelper.isPresent("QM_Source_Column1"))
								GridHelper.clickRow("QM_Source_Grid", rowNum, "Column 1");
							GridHelper.clickRow("QM_Source_Grid", 1, "Table Name");
							GridHelper.clickRow("QM_Source_Grid", 1, "Table Name");
						}
						
						GridHelper.updateGridTextBox("QM_Source_Grid", "QM_Source_EditColumn1", rowNum, "Edit Column 1", "Table Name", joinDetails[i][3]);
						
						if (rowNum > 1) {
							GridHelper.clickRow("QM_Source_Grid", rowNum, "Column 1");
							if (!ComboBoxHelper.isPresent("QM_Source_Column1"))
								GridHelper.clickRow("QM_Source_Grid", rowNum, "Column 1");
							GridHelper.clickRow("QM_Source_Grid", 1, "Table Name");
							GridHelper.clickRow("QM_Source_Grid", 1, "Table Name");
						}
						GridHelper.updateGridComboBox("QM_Source_Grid", "QM_Source_Column1", rowNum, "Column 1", "Table Name", joinDetails[i][2]);
						
						if (rowNum > 1) {
							GridHelper.clickRow("QM_Source_Grid", rowNum, "Column 2");
							if (!ComboBoxHelper.isPresent("QM_Source_Column2"))
								GridHelper.clickRow("QM_Source_Grid", rowNum, "Column 2");
							GridHelper.clickRow("QM_Source_Grid", 1, "Table Name");
							GridHelper.clickRow("QM_Source_Grid", 1, "Table Name");
						}
						
						GridHelper.updateGridTextBox("QM_Source_Grid", "QM_Source_EditColumn2", rowNum, "Edit Column 2", "Table Name", joinDetails[i][5]);
						GridHelper.updateGridComboBox("QM_Source_Grid", "QM_Source_Column2", rowNum, "Column 2", "Table Name", joinDetails[i][4]);
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
				String gridId = "QM_SelectedColumns_Grid";
				
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
						TextBoxHelper.type("QM_AvailableColumns_Filter", columns[i]);
						Thread.sleep(1500);
						TreeHelper.clickChild("QM_AvailableColumns_Tree", columnTable[i], columns[i]);
						ButtonHelper.click("QM_AvailableColumns_Add");
						GenericHelper.waitForLoadmask(detailScreenWaitSec);
						
						rowNum = GridHelper.getRowNumber(gridId, statement, "Statement");
					}
					
					if(ValidationHelper.isNotEmpty(columnDetails)) {
						GridHelper.updateGridComboBox(gridId, "QM_SelectedColumns_Aggregate", rowNum, "Aggregate", "Order", columnDetails[i][0]);
						
						if (ValidationHelper.isNotEmpty(columnDetails[i][1])) {
							GridHelper.updateGridEntityCombo(gridId, "QM_SelectedColumns_Statement", rowNum, "Statement", columnDetails[i][1]);
							assertTrue(LabelHelper.isTitlePresent("Statement"));
							TextAreaHelper.type("QM_SelectedColumns_Statement_TextArea", columnDetails[i][1]);
							ButtonHelper.click("QM_SelectedColumns_Statement_OK");
							GenericHelper.waitForLoadmask(detailScreenWaitSec);
						}
						
						GridHelper.updateGridTextBox(gridId, "QM_SelectedColumns_ColumnName", rowNum, "Column Name", "Order", columnDetails[i][2]);
						
						GridHelper.updateGridTextBox(gridId, "QM_SelectedColumns_DisplayName", rowNum, "Display Name", "Order", columnDetails[i][3]);
						
						GridHelper.updateGridComboBox(gridId, "QM_SelectedColumns_Type", rowNum, "Type", "Order", columnDetails[i][4]);
						
						boolean isNull = ValidationHelper.isTrue(columnDetails[i][5]);
						GridHelper.updateGridCheckBox(gridId, "QM_SelectedColumns_NotNull", rowNum, "Not Null", isNull);
						
						if (ButtonHelper.isPresent("YesButton")) {
							ButtonHelper.click("YesButton");
							GenericHelper.waitForLoadmask(detailScreenWaitSec);
						}
						
						GridHelper.updateGridComboBox(gridId, "QM_SelectedColumns_Sort", rowNum, "Sort", "Order", columnDetails[i][6]);
						
						GridHelper.updateGridComboBox(gridId, "QM_SelectedColumns_Order", rowNum, "Order", "Sort", columnDetails[i][7]);
					}
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
}