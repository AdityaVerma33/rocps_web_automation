package com.subex.automation.helpers.application;

import java.util.ArrayList;
import java.util.HashMap;

import com.subex.automation.helpers.application.screens.QueryFilterHelper;
import com.subex.automation.helpers.component.ButtonHelper;
import com.subex.automation.helpers.component.EntitySearchHelper;
import com.subex.automation.helpers.component.GenericHelper;
import com.subex.automation.helpers.component.GridHelper;
import com.subex.automation.helpers.component.LabelHelper;
import com.subex.automation.helpers.component.TextBoxHelper;
import com.subex.automation.helpers.data.ValidationHelper;
import com.subex.automation.helpers.file.ExcelReader;
import com.subex.automation.helpers.report.Log4jHelper;
import com.subex.automation.helpers.scripts.TestDataHelper;
import com.subex.automation.helpers.selenium.ROCAcceptanceTest;
import com.subex.automation.helpers.util.FailureHelper;

public class ConfigureGridHelper extends ROCAcceptanceTest {
	
	/**
	 * This method is used to update the existing Configure Grid configuration based on Caption.
	 * @param path
	 * @param fileName
	 * @param sheetName
	 * @param testCaseName
	 * @param occurance
	 * @throws Exception
	 */
	public void updateConfigureGrid(String path, String fileName, String sheetName, String testCaseName, int occurance) throws Exception {
		try {
			TestDataHelper testData = new TestDataHelper();
			ExcelReader excelReader = new ExcelReader();
			HashMap<String, ArrayList<String>> excelData = excelReader.readDataByColumn( path, fileName, sheetName, testCaseName, occurance );
	
			for(int i = 0; i < excelData.get("Screen Name").size(); i++) 
			{
				String screenName = excelData.get("Screen Name").get(i);
				String screenTitle = excelData.get("Screen Title").get(i);
				
				String[] caption = testData.getStringValue(excelData.get("Caption").get(i), firstLevelDelimiter);
				String[] type = testData.getStringValue(excelData.get("Type").get(i), firstLevelDelimiter);
				String[] visible = testData.getStringValue(excelData.get("Visible").get(i), firstLevelDelimiter);
				String[] thousandSeparator = testData.getStringValue(excelData.get("Thousand Separator").get(i), firstLevelDelimiter);
				String[] alignment = testData.getStringValue(excelData.get("Alignment").get(i), firstLevelDelimiter);
				String[] sort = testData.getStringValue(excelData.get("Sort").get(i), firstLevelDelimiter);
				String[] nullDisplay = testData.getStringValue(excelData.get("Null Display").get(i), firstLevelDelimiter);
				String[] dataProperty = testData.getStringValue(excelData.get("Data Property").get(i), firstLevelDelimiter);
				
				updateConfigureGrid(screenName, screenTitle, caption, type, visible, thousandSeparator, alignment, sort, nullDisplay, dataProperty);
			}
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	/**
	 * This method is used to update the existing Configure Grid configuration based on Caption.
	 * @param screenName - Screen name where configure grid has to be updated.
	 * @param screenTitle - Screen's title on navigation.
	 * @param caption - Caption based on which configure grid will be updated.
	 * @param type - Data type of the column.
	 * @param visible - Whether column should be visible (true) or not (false).
	 * @param thousandSeparator - Whether thousand separator should be enabled (true) or not (false).
	 * @param alignment - Alignment of the column. Value should be same as seen in GUI.
	 * @param sort - What type of Sort. Value should be same as seen in GUI.
	 * @param nullDisplay - Display value in case the column is null in database.
	 * @param dataProperty - Column mapping in database.
	 * @throws Exception
	 */
	public void updateConfigureGrid(String screenName, String screenTitle, String[] caption, String[] type, String[] visible, String[] thousandSeparator,
			String[] alignment, String[] sort, String[] nullDisplay, String[] dataProperty) throws Exception {
		try {
			if (ValidationHelper.isNotEmpty(caption)) {
				boolean isConfigureGridPresen = navigateToConfigureGrid(screenName, screenTitle);
				
				if (isConfigureGridPresen) {
					String gridId = "ConfigureGrid_Grid";
					
					for (int i = 0; i < caption.length; i++) {
						int row = GridHelper.getRowNumber(gridId, caption[i], "Caption");
						
						if (row > 0) {
							updateConfigureGrid(row, i, type, visible, thousandSeparator, alignment, sort, nullDisplay, dataProperty);
						}
						else {
							FailureHelper.failTest("Caption '" + caption[i] + "' is not found in '" + screenName + "' Configure Grid.");
						}
					}
					
					saveConfigureGrid();
				}
				else {
					FailureHelper.failTest("Configure Grid is not present in '" + screenName + "'");
				}
			}
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	/**
	 * This method is used to add new column to the Configure Grid configuration.
	 * @param path
	 * @param fileName
	 * @param sheetName
	 * @param testCaseName
	 * @param occurance
	 * @throws Exception
	 */
	public void addColumnToConfigureGrid(String path, String fileName, String sheetName, String testCaseName, int occurance) throws Exception {
		try {
			TestDataHelper testData = new TestDataHelper();
			ExcelReader excelReader = new ExcelReader();
			HashMap<String, ArrayList<String>> excelData = excelReader.readDataByColumn( path, fileName, sheetName, testCaseName, occurance );
	
			for(int i = 0; i < excelData.get("Screen Name").size(); i++) 
			{
				String screenName = excelData.get("Screen Name").get(i);
				String screenTitle = excelData.get("Screen Title").get(i);
				
				String[] caption = testData.getStringValue(excelData.get("Caption").get(i), firstLevelDelimiter);
				String[] type = testData.getStringValue(excelData.get("Type").get(i), firstLevelDelimiter);
				String[] visible = testData.getStringValue(excelData.get("Visible").get(i), firstLevelDelimiter);
				String[] thousandSeparator = testData.getStringValue(excelData.get("Thousand Separator").get(i), firstLevelDelimiter);
				String[] alignment = testData.getStringValue(excelData.get("Alignment").get(i), firstLevelDelimiter);
				String[] sort = testData.getStringValue(excelData.get("Sort").get(i), firstLevelDelimiter);
				String[] nullDisplay = testData.getStringValue(excelData.get("Null Display").get(i), firstLevelDelimiter);
				String[] dataProperty = testData.getStringValue(excelData.get("Data Property").get(i), firstLevelDelimiter);
				
				addColumnToConfigureGrid(screenName, screenTitle, caption, type, visible, thousandSeparator, alignment, sort, nullDisplay, dataProperty);
			}
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	/**
	 * This method is used to add new column to the Configure Grid configuration.
	 * @param screenName - Screen name where configure grid has to be updated.
	 * @param screenTitle - Screen's title on navigation.
	 * @param caption - Caption of the column.
	 * @param type - Data type of the column.
	 * @param visible - Whether column should be visible (true) or not (false).
	 * @param thousandSeparator - Whether thousand separator should be enabled (true) or not (false).
	 * @param alignment - Alignment of the column. Value should be same as seen in GUI.
	 * @param sort - What type of Sort. Value should be same as seen in GUI.
	 * @param nullDisplay - Display value in case the column is null in database.
	 * @param dataProperty - Column mapping in database.
	 * @throws Exception
	 */
	public void addColumnToConfigureGrid(String screenName, String screenTitle, String[] caption, String[] type, String[] visible, String[] thousandSeparator,
			String[] alignment, String[] sort, String[] nullDisplay, String[] dataProperty) throws Exception {
		try {
			if (ValidationHelper.isNotEmpty(caption)) {
				boolean isConfigureGridPresen = navigateToConfigureGrid(screenName, screenTitle);
				
				if (isConfigureGridPresen) {
					String gridId = "ConfigureGrid_Grid";
					int rows = GridHelper.getRowCount(gridId);
					
					for (int i = 0; i < caption.length; i++) {
						int row = GridHelper.getRowNumber(gridId, caption[i], "Caption");
						
						if (row == 0) {
							ButtonHelper.click("ConfigureGrid_Add");
							rows++;
							row = rows;
							GridHelper.updateGridTextBox(gridId, "ConfigureGrid_Caption", row, "Caption", "Type", caption[i]);
							
							updateConfigureGrid(row, i, type, visible, thousandSeparator, alignment, sort, nullDisplay, dataProperty);
						}
						else {
							Log4jHelper.logWarning("Caption '" + caption[i] + "' is already present in '" + screenName + "' Configure Grid.");
						}
					}
					
					saveConfigureGrid();
				}
				else {
					FailureHelper.failTest("Configure Grid is not present in '" + screenName + "'");
				}
			}
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	/**
	 * This method is used to add new column to the Configure Grid configuration.
	 * @param path
	 * @param fileName
	 * @param sheetName
	 * @param testCaseName
	 * @param occurance
	 * @throws Exception
	 */
	public void deleteColumnFromConfigureGrid(String path, String fileName, String sheetName, String testCaseName, int occurance) throws Exception {
		try {
			TestDataHelper testData = new TestDataHelper();
			ExcelReader excelReader = new ExcelReader();
			HashMap<String, ArrayList<String>> excelData = excelReader.readDataByColumn( path, fileName, sheetName, testCaseName, occurance );
	
			for(int i = 0; i < excelData.get("Screen Name").size(); i++) 
			{
				String screenName = excelData.get("Screen Name").get(i);
				String screenTitle = excelData.get("Screen Title").get(i);
				String[] caption = testData.getStringValue(excelData.get("Caption").get(i), firstLevelDelimiter);
				
				deleteColumnFromConfigureGrid(screenName, screenTitle, caption);
			}
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	/**
	 * This method is used to add new column to the Configure Grid configuration.
	 * @param screenName - Screen name where configure grid has to be updated.
	 * @param screenTitle - Screen's title on navigation.
	 * @param caption - Caption of the column.
	 * @param type - Data type of the column.
	 * @param visible - Whether column should be visible (true) or not (false).
	 * @param thousandSeparator - Whether thousand separator should be enabled (true) or not (false).
	 * @param alignment - Alignment of the column. Value should be same as seen in GUI.
	 * @param sort - What type of Sort. Value should be same as seen in GUI.
	 * @param nullDisplay - Display value in case the column is null in database.
	 * @param dataProperty - Column mapping in database.
	 * @throws Exception
	 */
	public void deleteColumnFromConfigureGrid(String screenName, String screenTitle, String[] caption) throws Exception {
		try {
			if (ValidationHelper.isNotEmpty(caption)) {
				boolean isConfigureGridPresen = navigateToConfigureGrid(screenName, screenTitle);
				
				if (isConfigureGridPresen) {
					String gridId = "ConfigureGrid_Grid";
					
					for (int i = 0; i < caption.length; i++) {
						int row = GridHelper.getRowNumber(gridId, caption[i], "Caption");
						
						if (row == 0) {
							GridHelper.clickRow(gridId, row, "Caption");
							ButtonHelper.click("ConfigureGrid_Delete");
							Thread.sleep(500);
						}
						else {
							Log4jHelper.logWarning("Caption '" + caption[i] + "' is not found in '" + screenName + "' Configure Grid.");
						}
					}
					
					saveConfigureGrid();
				}
				else {
					FailureHelper.failTest("Configure Grid is not present in '" + screenName + "'");
				}
			}
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	/**
	 * This method is used to add Color Filter to the Configure Grid configuration.
	 * @param path
	 * @param fileName
	 * @param sheetName
	 * @param testCaseName
	 * @param occurance
	 * @throws Exception
	 */
	public void addColorFilter(String path, String fileName, String sheetName, String testCaseName, int occurance) throws Exception {
		try {
			TestDataHelper testData = new TestDataHelper();
			ExcelReader excelReader = new ExcelReader();
			HashMap<String, ArrayList<String>> excelData = excelReader.readDataByColumn(path, fileName, sheetName, testCaseName, occurance);

			for (int i = 0; i < excelData.get("Name").size(); i++) {
				String colorFilterName = excelData.get("Name").get(i);
				String [] filterExpressionType = testData.getStringValue(excelData.get("Filter Expression Type").get(i), firstLevelDelimiter);
				String [] filterName = testData.getStringValue(excelData.get("Filter Name").get(i), firstLevelDelimiter);
				String [] filterExpClause = testData.getStringValue(excelData.get("Filter Expression Clause").get(i), firstLevelDelimiter);
				String [] filterExpLeftIndent = testData.getStringValue(excelData.get("Filter Expression Left Indent").get(i), firstLevelDelimiter);
				String [] filterExpExpression1 = testData.getStringValue(excelData.get("Filter Expression Expression1").get(i), firstLevelDelimiter);
				String [] filterExpOperator = testData.getStringValue(excelData.get("Filter Expression Operator").get(i), firstLevelDelimiter);
				String [] filterExpExpression2 = testData.getStringValue(excelData.get("Filter Expression Expression2").get(i), firstLevelDelimiter);
				String [] filterExpRightIndent = testData.getStringValue(excelData.get("Filter Expression Right Indent").get(i), firstLevelDelimiter);
				
				boolean colorCheck = ValidationHelper.isTrue(excelData.get("Color Check").get(i));
				String prioritySequence = excelData.get("Priority Sequence").get(i);
				String ruleColor = excelData.get("Rule Color").get(i);
				
				addColorFilter(colorFilterName, filterExpressionType, filterName, filterExpClause, filterExpLeftIndent, filterExpExpression1,
						filterExpOperator, filterExpExpression2, filterExpRightIndent, colorCheck, prioritySequence, ruleColor);
			}
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	/**
	 * This method is used to add Color Filter to the Configure Grid configuration.
	 * @param colorFilterName
	 * @param filterExpressionType
	 * @param filterName
	 * @param filterExpClause
	 * @param filterExpLeftIndent
	 * @param filterExpExpression1
	 * @param filterExpOperator
	 * @param filterExpExpression2
	 * @param filterExpRightIndent
	 * @param colorCheck
	 * @param prioritySequence
	 * @param ruleColor
	 * @throws Exception
	 */
	public void addColorFilter(String colorFilterName, String[] filterExpressionType, String[] filterName, String[] filterExpClause,
			String[] filterExpLeftIndent, String[] filterExpExpression1, String[] filterExpOperator, String[] filterExpExpression2,
			String[] filterExpRightIndent, boolean colorCheck, String prioritySequence, String ruleColor) throws Exception {
		try {			
			boolean isConfigureGridPresent = navigateToConfigureGrid();

			if (isConfigureGridPresent) {
				String gridId = "ConfigureGrid_Color_Grid";
				int rows = GridHelper.getRowCount(gridId);
				int row = GridHelper.getRowNumber(gridId, colorFilterName, "Rule Filter");

				if (row == 0) {
					createColorFilter(colorFilterName, filterExpressionType, filterName, filterExpClause, filterExpLeftIndent, filterExpExpression1,
							filterExpOperator, filterExpExpression2, filterExpRightIndent);
					
					rows++;
					row = rows;
				}
				
				GridHelper.updateGridCheckBox(gridId, row, "Color Check", colorCheck);
				
				GridHelper.updateGridTextBox(gridId, "ConfigureGrid_Color_PrioritySequence", row, "Priority Sequence", "Rule Color", prioritySequence);
				
				GridHelper.updateGridTextBox(gridId, "ConfigureGrid_Color_RuleColor", row, "Rule Color", "Priority Sequence", ruleColor);
				
				saveConfigureGrid();
			} 
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	/**
	 * This method is used to add Color Filter to the Configure Grid configuration.
	 * @param path
	 * @param fileName
	 * @param sheetName
	 * @param testCaseName
	 * @param occurance
	 * @throws Exception
	 */
	public void attachColorFilter(String path, String fileName, String sheetName, String testCaseName, int occurance) throws Exception {
		try {
			ExcelReader excelReader = new ExcelReader();
			HashMap<String, ArrayList<String>> excelData = excelReader.readDataByColumn(path, fileName, sheetName, testCaseName, occurance);

			for (int i = 0; i < excelData.get("Name").size(); i++) {
				String colorFilterName = excelData.get("Name").get(i);
				boolean colorCheck = ValidationHelper.isTrue(excelData.get("Color Check").get(i));
				String prioritySequence = excelData.get("Priority Sequence").get(i);
				String ruleColor = excelData.get("Rule Color").get(i);
				
				attachColorFilter(colorFilterName, colorCheck, prioritySequence, ruleColor);
			}
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	/**
	 * This method is used to attach existing Filter to the Configure Grid > Color Rule configuration.
	 * @param colorFilterName
	 * @param colorCheck
	 * @param prioritySequence
	 * @param ruleColor
	 * @throws Exception
	 */
	public void attachColorFilter(String colorFilterName, boolean colorCheck, String prioritySequence, String ruleColor) throws Exception {
		try {			
			boolean isConfigureGridPresen = navigateToConfigureGrid();

			if (isConfigureGridPresen) {
				String gridId = "ConfigureGrid_Color_Grid";
				int rows = GridHelper.getRowCount(gridId);
				int row = GridHelper.getRowNumber(gridId, colorFilterName, "Rule Filter");

				if (row == 0) {
					ButtonHelper.click("ConfigureGrid_Color_AttachColorFilter");
					rows++;
					row = rows;
				}
				
				GridHelper.updateGridCheckBox(gridId, row, "Color Check", colorCheck);
				
				GridHelper.updateGridEntityCombo(gridId, "ConfigureGrid_Color_RuleFilter", row, "Rule Filter", colorFilterName);
				EntitySearchHelper entitySearch = new EntitySearchHelper();
				entitySearch.selectUsingGridFilterTextBox("Query Filter Search", "QueryFilter_Name", colorFilterName, "Name");
				
				GridHelper.updateGridTextBox(gridId, "ConfigureGrid_Color_PrioritySequence", row, "Priority Sequence", "Rule Color", prioritySequence);
				
				GridHelper.updateGridTextBox(gridId, "ConfigureGrid_Color_RuleColor", row, "Rule Color", "Priority Sequence", ruleColor);
				
				saveConfigureGrid();
			} 
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public void createColorFilter(String colorFilterName, String[] filterExpressionType, String[] filterName, String[] filterExpClause,
			String[] filterExpLeftIndent, String[] filterExpExpression1, String[] filterExpOperator, String[] filterExpExpression2,
			String[] filterExpRightIndent) throws Exception {
		try {			
			ButtonHelper.click("ConfigureGrid_Color_AddColorFilter");
			GenericHelper.waitForLoadmask(searchScreenWaitSec);
			assertTrue(LabelHelper.isTitlePresent("Query Filter"));
			
			TextBoxHelper.type("QueryFilter_Name", colorFilterName);
			
			QueryFilterHelper queryFilter = new QueryFilterHelper("QueryFilter_Expression_GridWrapper");
			queryFilter.addCondition(filterExpressionType, filterName, filterExpClause, filterExpLeftIndent, filterExpExpression1,
					filterExpOperator, filterExpExpression2, filterExpRightIndent);
			
			ButtonHelper.click("SaveButton");
			GenericHelper.waitForLoadmask(detailScreenWaitSec);
			assertTrue(LabelHelper.isTitleNotPresent("Query Filter"), "Query Filter save did not happen.");
		} catch (AssertionError e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public boolean navigateToConfigureGrid() throws Exception {
		try {
			if (ButtonHelper.isPresent("ConfigureGrid_Icon")) {
				ButtonHelper.click("ConfigureGrid_Icon");
				GenericHelper.waitForLoadmask(searchScreenWaitSec);
				assertTrue(LabelHelper.isTitlePresent("Configure Grid Columns"));
				
				return true;
			}
			else {
				return false;
			}
		} catch (AssertionError e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public boolean navigateToConfigureGrid(String screenName, String screenTitle) throws Exception {
		try {
			NavigationHelper.navigateToScreen(screenName, screenTitle);
			
			return navigateToConfigureGrid();
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public void updateConfigureGrid(int row, int i, String[] type, String[] visible, String[] thousandSeparator, String[] alignment,
			String[] sort, String[] nullDisplay, String[] dataProperty) throws Exception {
		try {
			String gridId = "ConfigureGrid_Grid";
			
			if (ValidationHelper.isNotEmpty(type) && ValidationHelper.isNotEmpty(type[i]))
				GridHelper.updateGridComboBox(gridId, "ConfigureGrid_Type", row, "Type", "Caption", type[i]);
			
			if (ValidationHelper.isNotEmpty(visible) && ValidationHelper.isNotEmpty(visible[i]))
				GridHelper.updateGridCheckBox(gridId, "ConfigureGrid_Visible", row, "Visible", visible[i]);
			
			if (ValidationHelper.isNotEmpty(thousandSeparator) && ValidationHelper.isNotEmpty(thousandSeparator[i]))
				GridHelper.updateGridCheckBox(gridId, "ConfigureGrid_ThousandSeparator", row, "Thousand Separator", thousandSeparator[i]);
			
			if (ValidationHelper.isNotEmpty(alignment) && ValidationHelper.isNotEmpty(alignment[i]))
				GridHelper.updateGridComboBox(gridId, "ConfigureGrid_Alignment", row, "Alignment", "Caption", alignment[i]);
			
			if (ValidationHelper.isNotEmpty(sort) && ValidationHelper.isNotEmpty(sort[i]))
				GridHelper.updateGridComboBox(gridId, "ConfigureGrid_Sort", row, "Sort", "Caption", sort[i]);
			
			if (ValidationHelper.isNotEmpty(nullDisplay) && ValidationHelper.isNotEmpty(nullDisplay[i]))
				GridHelper.updateGridTextBox(gridId, "ConfigureGrid_NullDisplay", row, "Null Display", "Caption", nullDisplay[i]);
			
			if (ValidationHelper.isNotEmpty(dataProperty) && ValidationHelper.isNotEmpty(dataProperty[i]))
				GridHelper.updateGridTextBox(gridId, "ConfigureGrid_DataProperty", row, "Data Property", "Caption", dataProperty[i]);
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public void saveConfigureGrid() throws Exception {
		try {
			ButtonHelper.click("ConfigureGrid_OK");
			GenericHelper.waitForLoadmask(searchScreenWaitSec);
			assertTrue(LabelHelper.isTitleNotPresent("Configure Grid Columns"));
			GenericHelper.waitForLoadmask(searchScreenWaitSec);
		} catch (AssertionError e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
}