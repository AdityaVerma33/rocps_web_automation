package com.subex.automation.helpers.application.screens;

import java.util.ArrayList;
import java.util.HashMap;

import com.subex.automation.helpers.application.NavigationHelper;
import com.subex.automation.helpers.component.ButtonHelper;
import com.subex.automation.helpers.component.ComboBoxHelper;
import com.subex.automation.helpers.component.GenericHelper;
import com.subex.automation.helpers.component.GridHelper;
import com.subex.automation.helpers.component.LabelHelper;
import com.subex.automation.helpers.component.PopupHelper;
import com.subex.automation.helpers.component.SearchGridHelper;
import com.subex.automation.helpers.component.TabHelper;
import com.subex.automation.helpers.component.TextBoxHelper;
import com.subex.automation.helpers.data.ValidationHelper;
import com.subex.automation.helpers.file.ExcelReader;
import com.subex.automation.helpers.report.Log4jHelper;
import com.subex.automation.helpers.scripts.TestDataHelper;
import com.subex.automation.helpers.selenium.ROCAcceptanceTest;
import com.subex.automation.helpers.util.FailureHelper;

public class TableInstanceHelper extends ROCAcceptanceTest {
	
	public void createTableInstance(String path, String WorkbookName, String workSheetName, String testCaseName, int occurence) throws Exception {
		try {
			TestDataHelper testData = new TestDataHelper();
			ExcelReader excelReader = new ExcelReader();
			HashMap<String, ArrayList<String>> excelData = excelReader.readDataByColumn( path, WorkbookName, workSheetName, testCaseName, occurence );
			
			for(int i = 0; i < excelData.get("Table Name").size(); i++) {
				String partition = excelData.get("Partition").get(i);
				String tableDfnName = excelData.get("Table Definition").get(i);
				String schema = excelData.get("Schema").get(i);
				String tableName = excelData.get("Table Name").get(i);
				String displayName = excelData.get("Display Name").get(i);
				String alias = excelData.get("Alias").get(i);
				String partitionColumn = excelData.get("Partition Column").get(i);
				String subpartitionColumn = excelData.get("Subpartition Column").get(i);
				String dataSourceConnection = excelData.get("Data Source Connection").get(i);
				String[] searchColumns = testData.getStringValue(excelData.get("Search Columns").get(i), firstLevelDelimiter);
				String[] filterColumns = testData.getStringValue(excelData.get("Filter Columns").get(i), firstLevelDelimiter);
				
				createTableInstance(partition, tableDfnName, schema, tableName, displayName, alias, partitionColumn, subpartitionColumn,
						dataSourceConnection, searchColumns, filterColumns);
			}
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public void createTableInstance(String partition, String tableDfnName, String schema, String tableName, String displayName, String alias,
			String partitionColumn, String subpartitionColumn, String dataSourceConnection, String[] searchColumns, String[] filterColumns) throws Exception {
		try {
			int row = navigateToTableInstance(tableName);
			
			if (row > 0) {
				Log4jHelper.logWarning("Table Instance '" + tableName + "' is already present.");
			}
			else {
				String detailScreenTitle = NavigationHelper.navigateToNew(partition, "TableInst_TableName");
				
				loadTableDefinition(tableDfnName);
	
				updateTableInstance(schema, tableName, displayName, alias, partitionColumn, subpartitionColumn, dataSourceConnection,
						searchColumns, filterColumns);
				
				saveTableInstance(tableName, detailScreenTitle);
			}
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public void updateTableInstance(String schema, String tableName, String displayName, String alias, String partitionColumn,
			String subpartitionColumn, String dataSourceConnection, String[] searchColumns, String[] filterColumns) throws Exception {
		try {
			ComboBoxHelper.select("TableInst_Schema", schema);
			TextBoxHelper.type("TableInst_TableName", tableName);
			TextBoxHelper.type("TableInst_DisplayName", displayName);
			TextBoxHelper.type("TableInst_Alias", alias);
			
			updateUsageTableProperties(schema, partitionColumn, subpartitionColumn, dataSourceConnection);
			addSearchColumns(searchColumns);
			addSearchFilters(filterColumns);
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public void truncateTable(String path, String WorkbookName, String workSheetName, String testCaseName, int occurence) throws Exception {
		try {
			TestDataHelper testData = new TestDataHelper();
			ExcelReader excelReader = new ExcelReader();
			HashMap<String, ArrayList<String>> excelData = excelReader.readDataByColumn( path, WorkbookName, workSheetName, testCaseName, occurence );
			
			for(int i = 0; i < excelData.get("Table Name").size(); i++) {
				String[] tableName = testData.getStringValue(excelData.get("Table Name").get(i), firstLevelDelimiter);
				
				truncateTable(tableName);
			}
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public void truncateTable(String[] tableName) throws Exception {
		try {
			for (int i = 0; i < tableName.length; i++) {
				int row = navigateToTableInstance(tableName[i]);
				
				if (row > 0) {
					GridHelper.clickRow("SearchGrid", row, 1);
					NavigationHelper.navigateToAction("Table Actions");
					
					if (NavigationHelper.isActionPresent("Truncate Table")) {
						NavigationHelper.navigateToAction("Truncate Table");
						GenericHelper.waitForLoadmask(searchScreenWaitSec);
						
						ButtonHelper.click("YesButton");
						GenericHelper.waitForLoadmask(searchScreenWaitSec);
						String message = "Successfully truncated the table " + tableName[i];
						GenericHelper.waitForElement("OKButton", configProp.getCustomScreenWaitSec());
						assertTrue(PopupHelper.isTextPresent(message), "Table '" + tableName[i] + "' did not get truncated.");
						
						ButtonHelper.click("OKButton");
						Log4jHelper.logInfo("Table Instance '" + tableName[i] + "' truncated.");
					}
					else {
						FailureHelper.failTest("Truncate table action is not found for table '" + tableName[i] + "'.");
						NavigationHelper.navigateToAction("Table Actions");
					}
				}
				else
					FailureHelper.failTest("Table Instance '" + tableName[i] + "' is not found.");
			}
		} catch (AssertionError e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public int navigateToTableInstance(String tableName) throws Exception {
		try {
			NavigationHelper.navigateToScreen( "Table Instances", "Table Instance Search" );
			ButtonHelper.click("ClearButton");
			GenericHelper.waitForLoadmask(searchScreenWaitSec);
			int row = SearchGridHelper.gridFilterSearchWithTextBox("TableInst_TableName", tableName, "Table Name");
			
			return row;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	private void loadTableDefinition(String tableDfnName) throws Exception {
		try {
			if (ComboBoxHelper.isEnabled("TableInst_TableDefinition")) {
				ComboBoxHelper.select("TableInst_TableDefinition", tableDfnName);
				ButtonHelper.click("TableInst_Load_Button");
				GenericHelper.waitForLoadmask(detailScreenWaitSec);
				
				assertTrue(PopupHelper.isTextPresent("Do you wish to load the selected table definition?"), "Confirmation popup did not appear.");
				ButtonHelper.click("YesButton");
				GenericHelper.waitForLoadmask(detailScreenWaitSec);
			}
			else {
				String currentValue = ComboBoxHelper.getValue("TableInst_TableDefinition");
				
				if (ValidationHelper.isEmpty(currentValue))
					FailureHelper.failTest("Table Definition option is empty. It is supposed to have '" + tableDfnName + "'");
				if (!currentValue.equals(tableDfnName))
					FailureHelper.failTest("Table Definition option value is '" + currentValue + "'. Expected '" + tableDfnName + "'");
			}
		} catch (AssertionError e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	private void updateUsageTableProperties(String schema, String partitionColumn, String subpartitionColumn, String dataSourceConnection) throws Exception {
		try {
			if (!schema.equals("Reference")) {
				ComboBoxHelper.select("TableInst_PartitionColumn", partitionColumn);
				
				if (ComboBoxHelper.isEnabled("TableInst_SubpartitionColumn"))
					ComboBoxHelper.select("TableInst_SubpartitionColumn", subpartitionColumn);
				if (ComboBoxHelper.isEnabled("TableInst_DataSourceConnection"))
					ComboBoxHelper.select("TableInst_DataSourceConnection", dataSourceConnection);
			}
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	private void saveTableInstance(String tableName, String detailScreenTitle) throws Exception {
		try {
			ButtonHelper.click("SaveButton");
			Thread.sleep(1000);
			GenericHelper.waitForLoadmask(configProp.getCustomScreenWaitSec());
			assertTrue(LabelHelper.isTitleNotPresent(detailScreenTitle), "Table Instance save did not happen.");
			
			ButtonHelper.click("SearchButton");
			GenericHelper.waitForLoadmask(searchScreenWaitSec);
			assertTrue(GridHelper.isValuePresent("SearchGrid", tableName, "Table Name"), "Value '" + tableName + "' is not found in grid.");
			Log4jHelper.logInfo("Table Instance '" + tableName + "' created");
		} catch (AssertionError e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	private void addSearchColumns(String searchColumns[]) throws Exception {
		try {
			if(ValidationHelper.isNotEmpty(searchColumns) && !searchColumns[0].equals("")) {
				TabHelper.gotoTab("TableInst_SearchColumns_Tab");
				GenericHelper.waitForLoadmask(detailScreenWaitSec);
				
				for (int i = 0; i < searchColumns.length; i++)
				{
					GridHelper.doubleClick("TableInst_AvailableColumns_Grid", searchColumns[i], "Column Name");
					assertTrue(GridHelper.isValuePresent("TableInst_SearchColumns_Grid", searchColumns[i], "Column Name"), "Value '" + searchColumns[i] + "' is not found in grid.");
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
	
	private void addSearchFilters(String filterColumns[]) throws Exception {
		try {
			if(ValidationHelper.isNotEmpty(filterColumns) && !filterColumns[0].equals("")) {
				TabHelper.gotoTab("TableInst_SearchFilters_Tab");
				GenericHelper.waitForLoadmask(detailScreenWaitSec);
				
				for (int i = 0; i < filterColumns.length; i++)
				{
					GridHelper.doubleClick("TableInst_Filter_AvailableColumns_Grid", filterColumns[i], "Column Name");
					assertTrue(GridHelper.isValuePresent("TableInst_FilterColumns_Grid", filterColumns[i], "Column Name"), "Value '" + filterColumns[i] + "' is not found in grid.");
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