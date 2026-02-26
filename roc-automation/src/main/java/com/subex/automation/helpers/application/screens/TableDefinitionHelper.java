package com.subex.automation.helpers.application.screens;

import java.util.ArrayList;
import java.util.HashMap;

import com.subex.automation.helpers.application.NavigationHelper;
import com.subex.automation.helpers.component.ButtonHelper;
import com.subex.automation.helpers.component.CheckBoxHelper;
import com.subex.automation.helpers.component.ComboBoxHelper;
import com.subex.automation.helpers.component.EntityComboHelper;
import com.subex.automation.helpers.component.GenericHelper;
import com.subex.automation.helpers.component.GridHelper;
import com.subex.automation.helpers.component.LabelHelper;
import com.subex.automation.helpers.component.PopupHelper;
import com.subex.automation.helpers.component.SearchGridHelper;
import com.subex.automation.helpers.component.TextBoxHelper;
import com.subex.automation.helpers.data.ValidationHelper;
import com.subex.automation.helpers.file.ExcelReader;
import com.subex.automation.helpers.report.Log4jHelper;
import com.subex.automation.helpers.scripts.TestDataHelper;
import com.subex.automation.helpers.selenium.ROCAcceptanceTest;
import com.subex.automation.helpers.util.FailureHelper;

public class TableDefinitionHelper extends ROCAcceptanceTest {
	
	public void createTableDefinition(String path, String WorkbookName, String workSheetName, String testCaseName, int occurence) throws Exception {
		try {
			TestDataHelper testData = new TestDataHelper();
			ExcelReader excelReader = new ExcelReader();
			HashMap<String, ArrayList<String>> excelData = excelReader.readDataByColumn( path, WorkbookName, workSheetName, testCaseName, occurence );
			
			for(int i = 0; i < excelData.get("Name").size(); i++)
			{
				String partition = excelData.get("Partition").get(i);
				String tableName = excelData.get("Name").get(i);
				String prefix = excelData.get("Prefix").get(i);
				String schemaType = excelData.get("Schema Type").get(i);
				boolean[] primaryKey = testData.getBooleanValue(excelData.get("Primary Key").get(i), firstLevelDelimiter);
				String[] columnNames = testData.getStringValue(excelData.get("Column Name").get(i), firstLevelDelimiter);
				String[] displayNames = testData.getStringValue(excelData.get("Display Name").get(i), firstLevelDelimiter);
				String[] dataTypes = testData.getStringValue(excelData.get("Datatype").get(i), firstLevelDelimiter);
				boolean[] isNullable = testData.getBooleanValue(excelData.get("Nullable").get(i), firstLevelDelimiter);
				boolean[] hourlyPartKey = testData.getBooleanValue(excelData.get("Hourly Part Key").get(i), firstLevelDelimiter);
				String[] entityReference = testData.getStringValue(excelData.get("Entity Reference").get(i), firstLevelDelimiter);
				boolean enableHybrid = ValidationHelper.isTrue(excelData.get("Enable Hybrid").get(i));
				String[] indexNames = testData.getStringValue(excelData.get("Index Name").get(i), firstLevelDelimiter);
				boolean[] isUnique = testData.getBooleanValue(excelData.get("Unique").get(i), firstLevelDelimiter);
				boolean[] isClustered = testData.getBooleanValue(excelData.get("Clustered").get(i), firstLevelDelimiter);
				boolean[] enableActive = testData.getBooleanValue(excelData.get("Enable Active Period").get(i), firstLevelDelimiter);
				boolean[] enableInactive = testData.getBooleanValue(excelData.get("Enable Inactive Period").get(i), firstLevelDelimiter);
				String[][] columns = testData.getStringValue(excelData.get("Index Columns").get(i), firstLevelDelimiter, secondLevelDelimiter);
				
				createTableDefinition(partition, tableName, prefix, schemaType, primaryKey, columnNames, displayNames, dataTypes, isNullable,
						hourlyPartKey, entityReference, enableHybrid, indexNames, isUnique, isClustered, enableActive, enableInactive, columns);
			}
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public void createTableDefinition(String partition, String tableName, String prefix, String type, boolean[] primaryKey, String[] columnNames,
			String[] displayNames, String[] dataTypes, boolean[] isNullable, boolean[] hourlyPartKey, String[] entityReference, boolean enableHybrid,
			String[] indexNames, boolean[] isUnique, boolean[] isClustered, boolean[] enableActive, boolean[] enableInactive, String[][] columns) throws Exception
	{
		try {
			int row = navigateToTableDefinition(tableName);
			
			if (row == 0) {
				Log4jHelper.logWarning("Table Definition '" + tableName + "' is already present");
			}
			else {
				String detailScreenTitle = NavigationHelper.navigateToNew(partition, "TableDfn_Name");
				
				TextBoxHelper.type("TableDfn_Name", tableName);
				TextBoxHelper.type("TableDfn_Prefix", prefix);
				ComboBoxHelper.select("TableDfn_SchemaType", type);
				
				if (ValidationHelper.isNotEmpty(columnNames)) {
					for (int i = 0; i < columnNames.length; i++)
					{
						addTableColumn(primaryKey[i], columnNames[i], displayNames[i], dataTypes[i], isNullable[i], hourlyPartKey[i], entityReference[i]);
					}
				}
	
				if (enableHybrid)
					CheckBoxHelper.check("TableDfn_EnableHybrid");
				
				if (ValidationHelper.isNotEmpty(indexNames)) {
					for (int i = 0; i < indexNames.length; i++)
					{
						addTableIndex(indexNames[i], isUnique[i], isClustered[i], enableActive[i], enableInactive[i], columns[i]);
					}
				}
	
				saveTableDefinition(tableName, detailScreenTitle);
			}
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public void editTableDefinition(String path, String WorkbookName, String workSheetName, String testCaseName, int occurence) throws Exception {
		try {
			TestDataHelper testData = new TestDataHelper();
			ExcelReader excelReader = new ExcelReader();
			HashMap<String, ArrayList<String>> excelData = excelReader.readDataByColumn( path, WorkbookName, workSheetName, testCaseName, occurence );
			
			for(int i = 0; i < excelData.get("Table Name").size(); i++)
			{
				String tableName = excelData.get("Table Name").get(i);
				String[] oldColumnName = testData.getStringValue(excelData.get("Old Column Name").get(i), firstLevelDelimiter);
				boolean[] primaryKey = testData.getBooleanValue(excelData.get("Primary Key").get(i), firstLevelDelimiter);
				String[] newColumnName = testData.getStringValue(excelData.get("New Column Name").get(i), firstLevelDelimiter);
				String[] displayName = testData.getStringValue(excelData.get("Display Name").get(i), firstLevelDelimiter);
				String[] dataType = testData.getStringValue(excelData.get("Datatype").get(i), firstLevelDelimiter);
				boolean[] isNullable = testData.getBooleanValue(excelData.get("Nullable").get(i), firstLevelDelimiter);
				boolean[] hourlyPartKey = testData.getBooleanValue(excelData.get("Hourly Part Key").get(i), firstLevelDelimiter);
				String[] entityReference = testData.getStringValue(excelData.get("Entity Reference").get(i), firstLevelDelimiter);
				String[] deleteColumn = testData.getStringValue(excelData.get("Delete Column").get(i), firstLevelDelimiter);
				
				editTableDefinition(tableName, oldColumnName, primaryKey, newColumnName, displayName, dataType, isNullable, hourlyPartKey,
						entityReference, deleteColumn);
			}
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public void editTableDefinition(String tableName, String[] oldColumnName, boolean[] primaryKey, String[] newColumnName, String[] displayName,
			String[] dataType, boolean[] isNullable, boolean[] hourlyPartKey, String[] entityReference, String[] deleteColumn) throws Exception {
		try {
			int row = navigateToTableDefinition(tableName);
			
			if (row == 0) {
				FailureHelper.failTest("Table Definition '" + tableName + "' is not present.");
			}
			else {
				String detailScreenTitle = NavigationHelper.navigateToEdit("SearchGrid", row, "TableDfn_Name");
				assertTrue(LabelHelper.isTitlePresent("Edit Table Definition"));
				
				String[] columnName = oldColumnName;
				if (ValidationHelper.isEmpty(oldColumnName) || oldColumnName[0].equals("")) {
					columnName = newColumnName;
				}
				
				if (ValidationHelper.isNotEmpty(columnName)) {
					for (int i = 0; i < columnName.length; i++) {
						boolean primarykey = false;
						if (primaryKey.length > i)
							primarykey = primaryKey[i];
						
						String colName = null;
						if (ValidationHelper.isNotEmpty(columnName) && ValidationHelper.isNotEmpty(columnName[i]))
							colName = columnName[i];
						else
							colName = newColumnName[i];
						
						String disName = null;
						if (ValidationHelper.isNotEmpty(displayName) && ValidationHelper.isNotEmpty(displayName[i]))
							disName = displayName[i];
						
						String dType = null;
						if (ValidationHelper.isNotEmpty(dataType) && ValidationHelper.isNotEmpty(dataType[i]))
							dType = dataType[i];
						
						boolean nullable = false;
						if (isNullable.length > i)
							nullable = isNullable[i];
						
						boolean partKey = false;
						if (hourlyPartKey.length > i)
							partKey = hourlyPartKey[i];
						
						String entReference = null;
						if (ValidationHelper.isNotEmpty(entityReference) && ValidationHelper.isNotEmpty(entityReference[i]))
							entReference = entityReference[i];
						
						int rowNum = GridHelper.getRowNumber("TableDfn_Column_Grid", colName, "Column Name");
						
						if (rowNum > 0) {
							editColumn(rowNum, colName, newColumnName[i], disName, dType, nullable, partKey, entReference);
						}
						else {
							addTableColumn(primarykey, colName, disName, dType, nullable, partKey, entReference);
						}
					}
				}
				
				if (ValidationHelper.isNotEmpty(deleteColumn)) {
					for (int i = 0; i < deleteColumn.length; i++) {
						int rowNum = GridHelper.getRowNumber("TableDfn_Column_Grid", deleteColumn[i], "Column Name");
						
						if (rowNum > 0) {
							deleteColumn(rowNum, deleteColumn[i]);
						}
						else {
							FailureHelper.failTest("Table Column '" + deleteColumn[i] + "' is not found in Table '" + tableName + "'.");
						}
					}
				}
				
				saveTableDefinition(tableName, detailScreenTitle);
			}
		} catch (AssertionError e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}

	public void importFromDiamond(String path, String WorkbookName, String workSheetName, String testCaseName, int occurence)	throws Exception {
		try {
			ExcelReader excelReader = new ExcelReader();
			HashMap<String, ArrayList<String>> excelData = excelReader.readDataByColumn( path, WorkbookName, workSheetName, testCaseName, occurence );
	
			for(int i = 0; i < excelData.get("Table Name").size(); i++)
			{
				String fileName = excelData.get("File Name").get(i);
				String fileNameWithPath = GenericHelper.getUploadFilePath(fileName);
				
				String tableName = excelData.get("Table Name").get(i);
				String prefix = excelData.get("Prefix").get(i);
				String schemaType = excelData.get("Schema Type").get(i);
				String partition = excelData.get("Partition").get(i);
				String recordType = excelData.get("Diamond Record Types").get(i);
				
				importFromDiamond(fileNameWithPath, tableName, prefix, schemaType, partition, recordType);
			}
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public void importFromDiamond(String fileNameWithPath, String tableName, String prefix, String schemaType, String partition, String recordType)	throws Exception {
		try {
			int row = navigateToTableDefinition(tableName);
			
			if (row > 0) {
				Log4jHelper.logWarning("Table Definition '" + tableName + "' is already present.");
			}
			else {
				NavigationHelper.navigateToAction("Table Actions", "Import From Diamond");
				GenericHelper.waitForElement("TableDfn_ImportFromDiamond_FileUpload", searchScreenWaitSec);
				assertTrue(LabelHelper.isTitlePresent("Import"), "Import popup did not appear.");
				GenericHelper.fileUpload(fileNameWithPath);
				
				ButtonHelper.click("TableDfn_ImportFromDiamond_FileUpload");
				GenericHelper.waitForLoadmask(searchScreenWaitSec);
				String importFromDiamondPanel = "TableDfn_ImportFromDiamond_Panel";
				GenericHelper.waitForElement(importFromDiamondPanel, searchScreenWaitSec);
				assertTrue(LabelHelper.isTitlePresent("Import Table Definition"), "Import Table Definition popup did not appear.");
				
				TextBoxHelper.type(importFromDiamondPanel, "TableDfn_Name", tableName);
				TextBoxHelper.type(importFromDiamondPanel, "TableDfn_Prefix", prefix);
				ComboBoxHelper.select(importFromDiamondPanel, "TableDfn_ImportFromDiamond_Partition", partition);
				ComboBoxHelper.select(importFromDiamondPanel, "TableDfn_ImportFromDiamond_RecordType", recordType);
				ComboBoxHelper.select(importFromDiamondPanel, "TableDfn_SchemaType", schemaType);
				ButtonHelper.click("TableDfn_ImportFromDiamond_OK");
				GenericHelper.waitForLoadmask(searchScreenWaitSec);
				GenericHelper.waitForElement("TableDfn_Name", detailScreenWaitSec);
				assertTrue(LabelHelper.isTitlePresent("New Table Definition"), "Table Definition screen did not appear.");
				String detailScreenTitle = NavigationHelper.getScreenTitle();
				
				saveTableDefinition(tableName, detailScreenTitle);
			}
		} catch (AssertionError e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public void synchTableSchema(String path, String workBookName, String workSheetName, String testCaseName, int occurence)	throws Exception {
		try {
			TestDataHelper testData = new TestDataHelper();
			ExcelReader excelReader = new ExcelReader();
			HashMap<String, ArrayList<String>> excelData = excelReader.readDataByColumn( path, workBookName, workSheetName, testCaseName, occurence );
	
			for(int i = 0; i < excelData.get("Table Name").size(); i++)
			{
				String[] tableName = testData.getStringValue(excelData.get("Table Name").get(i), firstLevelDelimiter);
				
				for (int j = 0; j < tableName.length; j++)
					synchTableSchema(tableName[j]);
			}
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public void synchTableSchema(String tableName) throws Exception {
		try {
			int row = navigateToTableDefinition(tableName);
			
			if (row == 0) {
				FailureHelper.failTest("Table Definition '" + tableName + "' is not present.");
			}
			else {
				GridHelper.clickRow("SearchGrid", row, "Name");
				NavigationHelper.navigateToAction("Table Actions");
				
				if (NavigationHelper.isActionPresent("Sync Table Schema")) {
					NavigationHelper.navigateToAction("Sync Table Schema");
					GenericHelper.waitForLoadmask(searchScreenWaitSec);
					GenericHelper.waitForElement("OKButton", configProp.getCustomScreenWaitSec());
					assertTrue(PopupHelper.isTextPresent("Schema has been modified successfully for underlying tables."), "Synch Table action failed.");
					
					ButtonHelper.click("OKButton");
					GenericHelper.waitForLoadmask(searchScreenWaitSec);
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
			String partitionColumn, String subpartitionColumn, String dataSourceConnection, String[] searchColumns, String[] filterColumns) throws Exception
	{
		try {
			int row = navigateToTableDefinition(tableDfnName);
			
			if (row == 0) {
				Log4jHelper.logWarning("Table Definition '" + tableName + "' is not found.");
			}
			else {
				GridHelper.clickRow("SearchGrid", tableDfnName, "Name");
				NavigationHelper.navigateToAction("Table Instances");
				if (NavigationHelper.isActionPresent(displayName)) {
					Log4jHelper.logWarning("Table Instance '" + displayName + "' is already present.");
				}
				else {
					NavigationHelper.navigateToAction("New Table Instance");
					NavigationHelper.selectPartition(partition);
					
					TableInstanceHelper tableInstance = new TableInstanceHelper();
					tableInstance.updateTableInstance(schema, tableName, displayName, alias, partitionColumn, subpartitionColumn, dataSourceConnection,
							searchColumns, filterColumns);
		
					ButtonHelper.click("SaveButton");
					Thread.sleep(1000);
					GenericHelper.waitForLoadmask(configProp.getCustomScreenWaitSec());
					
					ButtonHelper.click("SearchButton");
					GenericHelper.waitForLoadmask(configProp.getCustomScreenWaitSec());
					GridHelper.clickRow("SearchGrid", tableDfnName, "Name");
					Thread.sleep(1000);
					
					NavigationHelper.navigateToAction("Table Instances");
					if (NavigationHelper.isActionPresent(displayName)) {
						Log4jHelper.logInfo("Table Instance '" + tableName + "' is created.");
					}
					else {
						FailureHelper.failTest("Table Instance '" + tableName + "' did not get created.");
					}
				}
			}
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public void editColumn(int rowNum, String oldColumnName, String newColumnName, String displayName, String dataType, boolean isNullable,
			boolean hourlyPartKey, String entityReference) throws Exception {
		try {
			GridHelper.clickRow("TableDfn_Column_Grid", rowNum, "Column Name");
			ButtonHelper.click("TableDfn_Column_Edit");
			GenericHelper.waitForLoadmask(detailScreenWaitSec);
			assertTrue(LabelHelper.isTitlePresent("Table Column"));
			
			updateColumn(newColumnName, displayName, dataType, isNullable, hourlyPartKey, entityReference);
		} catch (AssertionError e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public void deleteColumn(int rowNum, String colName) throws Exception {
		try {
			GridHelper.clickRow("TableDfn_Column_Grid", rowNum, "Column Name");
			ButtonHelper.click("TableDfn_Column_Delete");
			GenericHelper.waitForLoadmask(detailScreenWaitSec);
			assertTrue(PopupHelper.isTextPresent("Are you sure you wish to delete the selected item?"));
			ButtonHelper.click("YesButton");
			GenericHelper.waitForLoadmask(detailScreenWaitSec);
			
			int row = GridHelper.getRowNumber("TableDfn_Column_Grid", colName, "Column Name");
			assertTrue(row == 0, "Table Column '" + colName + "' did not get deleted.");
		} catch (AssertionError e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public int navigateToTableDefinition(String tableName) throws Exception {
		try {
			NavigationHelper.navigateToScreen( "Table Definitions", "Table Definition Search" );
			int row = SearchGridHelper.searchWithTextBox("TableDfn_Name", tableName, "Name");
			
			return row;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	private void addTableColumn(boolean primaryKey, String colName, String displayName, String dataType, boolean isNullable,
			boolean hourlyPartKey, String entityReference) throws Exception {
		try {
			ButtonHelper.click("TableDfn_Column_Add");
			GenericHelper.waitForLoadmask(detailScreenWaitSec);
			assertTrue(LabelHelper.isTitlePresent("Table Column"));
			
			if (primaryKey)
				CheckBoxHelper.check("TableDfn_Column_Primary_Key");
			
			updateColumn(colName, displayName, dataType, isNullable, hourlyPartKey, entityReference);
		} catch (AssertionError e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	private void updateColumn(String colName, String displayName, String dataType, boolean isNullable, boolean hourlyPartKey, String entityReference) throws Exception {
		try {
			TextBoxHelper.type("TableDfn_Column_Name", colName);
			ComboBoxHelper.select("TableDfn_Column_DataType", dataType);
			TextBoxHelper.type("TableDfn_Column_DisplayName", displayName);
			
			if (isNullable)
				CheckBoxHelper.check("TableDfn_Column_Nullable");
			if (hourlyPartKey)
				CheckBoxHelper.check("TableDfn_Column_HourlyPartKey");
			
			if (ValidationHelper.isNotEmpty(dataType) && dataType.equalsIgnoreCase("Integer") && ValidationHelper.isNotEmpty(entityReference)) {
				CheckBoxHelper.check("TableDfn_Column_LookupColumn");
				GenericHelper.waitForLoadmask(detailScreenWaitSec);
				
				EntityComboHelper.selectUsingGridFilterTextBox("TableDfn_Column_EntityRef", "Entity Search", "TableDfn_Column_Entity_TextBox", entityReference, "Entity");
			}
			
			ButtonHelper.click("TableDfn_Column_Save");
			GenericHelper.waitForLoadmask(detailScreenWaitSec);
			assertTrue(GridHelper.isValuePresent("TableDfn_Column_Grid", colName, "Column Name"), "Value '" + colName + "' is not found in grid.");
		} catch (AssertionError e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	private void addTableIndex(String indexName, boolean isUnique, boolean isClustered, boolean enableActive, boolean enableInactive,
			String[] columnNames) throws Exception {
		try {
			ButtonHelper.click("TableDfn_Index_Add");
			GenericHelper.waitForLoadmask(detailScreenWaitSec);
			
			TextBoxHelper.type("TableDfn_Index_Name", indexName);
			if (isUnique)
				CheckBoxHelper.check("TableDfn_Index_Unique");
			if (isClustered)
				CheckBoxHelper.check("TableDfn_Index_Clustered");
			
			if (enableActive)
				CheckBoxHelper.check("TableDfn_Index_ActivePeriod");
			if (enableInactive)
				CheckBoxHelper.check("TableDfn_Index_InactivePeriod");
			
			for (int i = 0; i < columnNames.length; i++) {
				int row = GridHelper.getRowNumber("TableDfn_Index_AvailableColumns_Grid", columnNames[i], "Column Name");
				
				if (row > 0) {
					GridHelper.doubleClick("TableDfn_Index_AvailableColumns_Grid", row, 1);
					Thread.sleep(500);
					assertTrue(GridHelper.isValuePresent("TableDfn_Index_Selected_Columns_Grid", columnNames[i], "Column Name"), "Value '" + columnNames[i] + "' is not found in grid.");
				}
			}
			
			ButtonHelper.click("TableDfn_Index_Save");
			GenericHelper.waitForLoadmask(detailScreenWaitSec);
			assertTrue(GridHelper.isValuePresent("TableDfn_Index_Grid", indexName, "Name"), "Grid does not have value '" + indexName + "'");
		} catch (AssertionError e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	private void saveTableDefinition(String tableName, String detailScreenTitle) throws Exception {
		try {
			ButtonHelper.click("SaveButton");
			Thread.sleep(1000);
			GenericHelper.waitForLoadmask(configProp.getCustomScreenWaitSec());
			if (ButtonHelper.isPresent("YesButton")) {
				ButtonHelper.click("YesButton");
				GenericHelper.waitForLoadmask(configProp.getCustomScreenWaitSec());
			}
			Thread.sleep(1000);
			assertTrue(LabelHelper.isTitleNotPresent(detailScreenTitle), "Table Definition save did not happen.");
			
			ButtonHelper.click("SearchButton");
			GenericHelper.waitForLoadmask(searchScreenWaitSec);
			assertTrue(GridHelper.isValuePresent("SearchGrid", tableName, "Name"), "Value '" + tableName + "' is not found in grid.");
			Log4jHelper.logInfo("Table Definition '" + tableName + "' created/updated.");
		} catch (AssertionError e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
}