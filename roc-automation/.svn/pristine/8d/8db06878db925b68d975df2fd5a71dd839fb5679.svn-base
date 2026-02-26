package com.subex.automation.helpers.application.screens;

import java.util.ArrayList;
import java.util.HashMap;

import com.subex.automation.helpers.application.NavigationHelper;
import com.subex.automation.helpers.component.ButtonHelper;
import com.subex.automation.helpers.component.ComboBoxHelper;
import com.subex.automation.helpers.component.EntityComboHelper;
import com.subex.automation.helpers.component.EntitySearchHelper;
import com.subex.automation.helpers.component.GenericHelper;
import com.subex.automation.helpers.component.GridHelper;
import com.subex.automation.helpers.component.LabelHelper;
import com.subex.automation.helpers.component.LinkHelper;
import com.subex.automation.helpers.component.MouseHelper;
import com.subex.automation.helpers.component.PropertyGridHelper;
import com.subex.automation.helpers.component.SearchGridHelper;
import com.subex.automation.helpers.component.TextBoxHelper;
import com.subex.automation.helpers.data.ValidationHelper;
import com.subex.automation.helpers.file.ExcelReader;
import com.subex.automation.helpers.report.Log4jHelper;
import com.subex.automation.helpers.scripts.TestDataHelper;
import com.subex.automation.helpers.selenium.ROCAcceptanceTest;
import com.subex.automation.helpers.util.FailureHelper;

public class QueryFilterHelper extends ROCAcceptanceTest {
	
	private String gridId = null;
	
	public QueryFilterHelper() throws Exception {
		try {
			gridId = "QueryFilter_Expression_Grid";
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public QueryFilterHelper(String gridId) throws Exception {
		try {
			this.gridId = gridId;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public void createQueryFilter(String path, String WorkbookName, String workSheetName, String testCaseName, int occurence) throws Exception {
		try {
			TestDataHelper testData = new TestDataHelper();
			ExcelReader excelReader = new ExcelReader();
			HashMap<String, ArrayList<String>> excelData = excelReader.readDataByColumn( path, WorkbookName, workSheetName, testCaseName, occurence );
	
			for(int i = 0; i < excelData.get("Name").size(); i++)
			{
				String partition = excelData.get("Partition").get(i);
				String name = excelData.get("Name").get(i);
				String tableDefinition = excelData.get("Table Definition").get(i);
				String [] expressionType = testData.getStringValue(excelData.get("Expression Type").get(i), firstLevelDelimiter);
				String [] filterName = testData.getStringValue(excelData.get("Filter Name").get(i), firstLevelDelimiter);
				String [] expClause = testData.getStringValue(excelData.get("Expression Clause").get(i), firstLevelDelimiter);
				String [] expLeftIndent = testData.getStringValue(excelData.get("Expression Left Indent").get(i), firstLevelDelimiter);
				String [] expExpression1 = testData.getStringValue(excelData.get("Expression Expression1").get(i), firstLevelDelimiter);
				String [] expOperator = testData.getStringValue(excelData.get("Expression Operator").get(i), firstLevelDelimiter);
				String [] expExpression2 = testData.getStringValue(excelData.get("Expression Expression2").get(i), firstLevelDelimiter);
				String [] expRightIndent = testData.getStringValue(excelData.get("Expression Right Indent").get(i), firstLevelDelimiter);
				
				createQueryFilter(partition, name, tableDefinition, expressionType, filterName, expClause, expLeftIndent, expExpression1,
						expOperator, expExpression2, expRightIndent);
			}
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public void createQueryFilter(String partition, String name, String tableDefinition, String[] expressionType, String[] filterName, String[] expClause,
			String[] expLeftIndent, String[] expExpression1, String[] expOperator, String[] expExpression2, String[] expRightIndent) throws Exception {
		try {
			NavigationHelper.navigateToScreen( "Query Filters", "Query Filter Search" );
			int row = SearchGridHelper.gridFilterSearchWithTextBox("QueryFilter_Name", name, "Name");
			
			if (row > 0) {
				Log4jHelper.logWarning("Query Filter '" + name + "' is already present.");
			}
			else {
				String detailScreenTitle = NavigationHelper.navigateToNew(partition, "QueryFilter_Name");
				
				TextBoxHelper.type("QueryFilter_Name", name);
				EntityComboHelper.selectUsingSearchTextBox("QueryFilter_TableDfn_EntityCombo", "Table Definition Search", "TableDfn_Name", tableDefinition, "Name");
				String actualValue = EntityComboHelper.getValue("QueryFilter_TableDfn_EntityCombo");
				assertEquals(actualValue, tableDefinition, "Expected '" + tableDefinition + "' but found '" + actualValue + "' in Table Definition options.");
				
				for (int i = 0; i < expressionType.length; i++) {
					MouseHelper.click("QueryFilter_Expression_Add");
					
					if (expressionType[i].equals("Filter")) {
						addFilter(filterName[i]);
					}
					else if (expressionType[i].equals("Condition")) {
						addCondition((i+1), expClause[i], expLeftIndent[i], expExpression1[i], expOperator[i],
								expExpression2[i], expRightIndent[i]);
					}
				}
			
				saveQueryFilter(name, detailScreenTitle);
			}
		} catch (AssertionError e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	/**
	 * This method is used to multiple conditions or filters to Query Filter grid.
	 * @param expressionType - Whether this is a condition or a filter.
	 * @param filterName - In case of filter, filter name.
	 * @param expClause - In case of condition, 'and' or 'or' clause. For first condition, this will be empty as per product.
	 * @param expLeftIndent - In case of condition, left indent '(' in case condition requires one.
	 * @param expExpression1 - In case of condition, left side expression or function or constant or column. Value should be same as seen in GUI.
	 * @param expOperator - In case of condition, condition operator.
	 * @param expExpression2 - In case of condition, right side expression or function or constant or column. Value should be same as seen in GUI.
	 * @param expRightIndent - In case of condition, right indent ')' in case condition requires one.
	 * @throws Exception
	 */
	public void addCondition(String[] expressionType, String[] filterName, String[] expClause, String[] expLeftIndent, String[] expExpression1,
			String[] expOperator, String[] expExpression2, String[] expRightIndent) throws Exception {
		try {
			if (ValidationHelper.isNotEmpty(expressionType)) {
				GenericHelper.expandSearchFilterPanel();
				for (int i = 0; i < expressionType.length; i++) {
					ButtonHelper.click(gridId, "QueryFilter_Expression_Add");
					
					if (expressionType[i].equals("Filter")) {
						addFilter(filterName[i]);
					}
					else if (expressionType[i].equals("Condition")) {
						Thread.sleep(500);
						String clause = null;
						if (ValidationHelper.isNotEmpty(expClause) && ValidationHelper.isNotEmpty(expClause[i]))
							clause = expClause[i];
						
						String leftIndent = null;
						if (ValidationHelper.isNotEmpty(expLeftIndent) && ValidationHelper.isNotEmpty(expLeftIndent[i]))
							leftIndent = expLeftIndent[i];
						
						String expression2 = null;
						if (ValidationHelper.isNotEmpty(expExpression2) && ValidationHelper.isNotEmpty(expExpression2[i]))
							expression2 = expExpression2[i];
						
						String rightIndent = null;
						if (ValidationHelper.isNotEmpty(expRightIndent) && ValidationHelper.isNotEmpty(expRightIndent[i]))
							rightIndent = expRightIndent[i];
						
						addCondition((i+1), clause, leftIndent, expExpression1[i], expOperator[i],
								expression2, rightIndent);
					}
				}
			}
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	/**
	 * This method is used to add a Filter to Query Filter grid.
	 * @param name - Filter name.
	 * @throws Exception
	 */
	public void addFilter(String name) throws Exception {
		try {
			if (LinkHelper.isLinkEnabled("QueryFilter_Expression_Add_Filter")) {
				ButtonHelper.click("QueryFilter_Expression_Add_Filter");
				GenericHelper.waitForLoadmask(searchScreenWaitSec);
				assertTrue(LabelHelper.isTitlePresent("Query Filter Search"), "Query Filter popup did not appear.");
				
				EntitySearchHelper entitySearch = new EntitySearchHelper("Popup_Panel", "SearchGrid");
				entitySearch.selectUsingGridFilterTextBox("Query Filter Search", "QueryFilter_Name", name, "Name");
				assertTrue(LabelHelper.isTitleNotPresent("Query Filter Search"), "Query Filter did not get added.");
			}
		} catch (AssertionError e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	/**
	 * This method is used to a condition to Query Filter grid.
	 * @param gridId - Grid ID of Query Filter grid.
	 * @param row - Condition's row number.
	 * @param expClause - 'and' or 'or' clause. For first condition, this will be empty as per product.
	 * @param expLeftIndent - Left indent '(' in case condition requires one.
	 * @param expExpression1 - Left side expression or function or constant or column. Value should be same as seen in GUI.
	 * @param expOperator - Condition operator.
	 * @param expExpression2 - Right side expression or function or constant or column. Value should be same as seen in GUI.
	 * @param expRightIndent - Right indent ')' in case condition requires one.
	 * @throws Exception
	 */
	public void addCondition(int row, String expClause, String expLeftIndent, String expExpression1, String expOperator, String expExpression2,
			String expRightIndent) throws Exception {
		try {
			ButtonHelper.click("QueryFilter_Expression_Add_Condition");
			
			GridHelper.updateGridComboBox(gridId, "QueryFilter_Expression_Clause", row, "Clause", "(", expClause);
			
			GridHelper.updateGridTextBox(gridId, "QueryFilter_Expression_LeftIndent", row, "(", "Clause", expLeftIndent);
			
			GridHelper.clickRow(gridId, row, "Expression", 1);
			if(!ComboBoxHelper.isPresent("QueryFilter_Expression_Expression_Combo"))
				GridHelper.clickRow(gridId, row, "Expression", 1);
			subfunction(gridId, expExpression1);
			
			GridHelper.updateGridComboBox(gridId, "QueryFilter_Expression_Operator", row, "Operator", "Clause", expOperator);
			
			if (ValidationHelper.isNotEmpty(expExpression2)) {
				GridHelper.clickRow(gridId, row, "Expression", 2);
				if(!TextBoxHelper.isPresent("QueryFilter_Expression_Expression_Text"))
					GridHelper.clickRow(gridId, row, "Expression", 2);
				subfunction(gridId, expExpression2);
			}
			
			GridHelper.updateGridTextBox(gridId, "QueryFilter_Expression_RightIndent", row, ")", "Clause", expRightIndent);
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	/**
	 * This method is used to a having clause to Having Clause grid.
	 * @param gridId - Grid ID of Query Filter grid.
	 * @param row - Condition's row number.
	 * @param expClause - 'and' or 'or' clause. For first condition, this will be empty as per product.
	 * @param expLeftIndent - Left indent '(' in case condition requires one.
	 * @param aggregate1 - Left side expression's aggregate.
	 * @param expExpression1 - Left side expression or function or constant or column. Value should be same as seen in GUI.
	 * @param expOperator - Condition operator.
	 * @param aggregate2 - Right side expression's aggregate.
	 * @param expExpression2 - Right side expression or function or constant or column. Value should be same as seen in GUI.
	 * @param expRightIndent - Right indent ')' in case condition requires one.
	 * @throws Exception
	 */
	public void addCondition(int row, String expClause, String expLeftIndent, String aggregate1, String expExpression1, String expOperator,
			String aggregate2, String expExpression2, String expRightIndent) throws Exception {
		try {
			addCondition(row, expClause, expLeftIndent, expExpression1, expOperator, expExpression2, expRightIndent);
			
			GridHelper.updateGridComboBox(gridId, "QueryFilter_Expression_Aggregate1", row, "Aggregate", "Clause", aggregate1);
			
			if (ValidationHelper.isNotEmpty(aggregate2)) {
				GridHelper.clickRow(gridId, row, "Aggregate", 2);
				if (!ComboBoxHelper.isPresent("QueryFilter_Expression_Aggregate2"))
					GridHelper.clickRow(gridId, row, "Aggregate", 2);
				ComboBoxHelper.select("QueryFilter_Expression_Aggregate2", aggregate2);
				GridHelper.clickRow(gridId, row, "Clause");
			}
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	private void saveQueryFilter(String name, String detailScreenTitle) throws Exception {
		try {
			ButtonHelper.click("SaveButton");
			GenericHelper.waitForLoadmask(detailScreenWaitSec);
			
			assertTrue(LabelHelper.isTitleNotPresent(detailScreenTitle), "Query Filter save did not happen.");
			ButtonHelper.click("SearchButton");
			GenericHelper.waitForLoadmask(searchScreenWaitSec);
			assertTrue(GridHelper.isValuePresent("SearchGrid", name, "Name"), "Value '" + name + "' is not found in grid.");
			Log4jHelper.logInfo("Query Filter '" + name + "' created.");
		} catch (AssertionError e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public void addFunction(String function) throws Exception {
		try {
			MouseHelper.click("QueryFilter_Expression_Function");
			GenericHelper.waitForLoadmask(searchScreenWaitSec);
			assertTrue(LabelHelper.isTitlePresent("Add/Edit Function"), "Add/Edit Function popup did not appear.");
			
			GridHelper.updateGridComboBox("QueryFilter_Expression_Function_Grid", "QueryFilter_Expression_Function_Combo", 1, "Value", "Datatype", function);
			
			ButtonHelper.click("QueryFilter_Expression_Function_OK");
			GenericHelper.waitForLoadmask(searchScreenWaitSec);
			assertTrue(GridHelper.isValuePresent(gridId, function), "Function '" + function + "' is not found in grid.");
		} catch (AssertionError e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public void addConstant(String dataType, String constant) throws Exception {
		try {
			MouseHelper.click("QueryFilter_Expression_Constant");
			GenericHelper.waitForLoadmask(searchScreenWaitSec);
			assertTrue(LabelHelper.isTitlePresent("Add/Edit Constant"), "Add/Edit Constant popup did not appear.");
			
			if (dataType.equals("boolean")) {
				GridHelper.updateGridComboBox("QueryFilter_Expression_Function_Grid", "QueryFilter_Expression_Function_Combo", 1, "Value", "Datatype", constant);
			}
			else {
				GridHelper.updateGridTextBox("QueryFilter_Expression_Function_Grid", "QueryFilter_Expression_Function_Value", 1, "Value", "Datatype", constant);
			}
			
			ButtonHelper.click("QueryFilter_Expression_Function_OK");
			GenericHelper.waitForLoadmask(searchScreenWaitSec);
			assertTrue(GridHelper.isValuePresent(gridId, constant), "Constant '" + constant + "' is not found in grid.");
		} catch (AssertionError e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public void addStandardExpression(int startIndex, String[] expression) throws Exception {
		try {
			assertTrue(LabelHelper.isTitlePresent("Expression Editor"));
			ComboBoxHelper.select("QueryFilter_Expression_StdExp_Function", expression[startIndex++]);
			
			if (ValidationHelper.isNotEmpty(expression[startIndex]))
				ComboBoxHelper.select("QueryFilter_Expression_StdExp_Overload", expression[startIndex]);
			startIndex++;
			
			for (int i = startIndex; i < expression.length; i+=2) {
				PropertyGridHelper.typeInTextBox(expression[i], expression[i+1]);
			}
			
			ButtonHelper.click("QueryFilter_Expression_StdExp_OK");
			GenericHelper.waitForLoadmask(searchScreenWaitSec);
		} catch (AssertionError e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public void subfunction(String gridId, String expressions) throws Exception {
		try {
			TestDataHelper testData = new TestDataHelper();
			String[] expression = null;
			if (expressions.contains(secondLevelDelimiter))
				expression = testData.getStringValue(expressions, secondLevelDelimiter);
			else
				expression = testData.getStringValue(expressions, configProp.getThirdLevelDelimiter());
			
			if (expression[0].equals("Function") || expression[0].equals("Constant")) {
				ButtonHelper.click(gridId, "QueryFilter_Expression_ExpressionAction");
				GenericHelper.waitForLoadmask(searchScreenWaitSec);
	
				if (expression[0].equals("Function")) {
					addFunction(expression[2]);
				}
				else {
					addConstant(expression[1], expression[2]);
				}
			}
			else if (expression[0].equals("Standard Expression")) {
				MouseHelper.click("QueryFilter_Expression_ExpressionAction");
				MouseHelper.click("QueryFilter_Expression_StandardExpression");
				GenericHelper.waitForLoadmask(searchScreenWaitSec);
				addStandardExpression(1, expression);
			}
			else {
				if(ComboBoxHelper.isPresent("QueryFilter_Expression_Expression_Combo"))
					ComboBoxHelper.select("QueryFilter_Expression_Expression_Combo", expression[0]);
				else
					TextBoxHelper.type("QueryFilter_Expression_Expression_Text", expression[0]);
			}
			
			GridHelper.clickRow(gridId, 1, 1);
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
}