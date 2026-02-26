package com.subex.automation.helpers.application.screens;

import java.util.ArrayList;
import java.util.HashMap;

import com.subex.automation.helpers.application.NavigationHelper;
import com.subex.automation.helpers.component.ButtonHelper;
import com.subex.automation.helpers.component.ComboBoxHelper;
import com.subex.automation.helpers.component.EntityComboHelper;
import com.subex.automation.helpers.component.GenericHelper;
import com.subex.automation.helpers.component.GridCheckBoxHelper;
import com.subex.automation.helpers.component.GridHelper;
import com.subex.automation.helpers.component.LabelHelper;
import com.subex.automation.helpers.component.SearchGridHelper;
import com.subex.automation.helpers.component.TextBoxHelper;
import com.subex.automation.helpers.data.ValidationHelper;
import com.subex.automation.helpers.file.ExcelReader;
import com.subex.automation.helpers.report.Log4jHelper;
import com.subex.automation.helpers.scripts.TestDataHelper;
import com.subex.automation.helpers.selenium.ROCAcceptanceTest;
import com.subex.automation.helpers.util.FailureHelper;

public class FederatedViewHelper extends ROCAcceptanceTest {
	
	public void createFederatedView(String path, String WorkbookName, String workSheetName, String testCaseName, int occurence) throws Exception {
		try {
			TestDataHelper testData = new TestDataHelper();
			ExcelReader excelReader = new ExcelReader();
			HashMap<String, ArrayList<String>> excelData = excelReader.readDataByColumn( path, WorkbookName, workSheetName, testCaseName, occurence );
			
			for(int i = 0; i < excelData.get("Name").size(); i++)
			{
				String partition = excelData.get("Partition").get(i);
				String federatedDataSource = excelData.get("Federated Data Source").get(i);
				String sourceTable = excelData.get("Source Table").get(i);
				String name = excelData.get("Name").get(i);
				
				String[] removeColumns = testData.getStringValue(excelData.get("Remove Columns").get(i), firstLevelDelimiter);
				String[] selectColumns = testData.getStringValue(excelData.get("Select Columns").get(i), firstLevelDelimiter);
				String[] targetName = testData.getStringValue(excelData.get("Target Name").get(i), firstLevelDelimiter);
				String[] targetDataType = testData.getStringValue(excelData.get("Target DataType").get(i), firstLevelDelimiter);
				String[] unique = testData.getStringValue(excelData.get("Unique").get(i), firstLevelDelimiter);
				
				createFederatedView(partition, federatedDataSource, sourceTable, name, removeColumns, selectColumns, targetName, targetDataType, unique);
			}
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public void createFederatedView(String partition, String federatedDataSource, String sourceTable, String name, String[] removeColumns,
			String[] selectColumns, String[] targetName, String[] targetDataType, String[] unique) throws Exception {
		try {
			NavigationHelper.navigateToScreen("Federated Views", "Federated View Search");
			int row = SearchGridHelper.gridFilterSearchWithTextBox("FedView_Name_Filter", name, "Name");

			if (row > 0) {
				Log4jHelper.logWarning("Federated View '" + name + "' is already present.");
			}
			else {
				String detailScreenTitle = NavigationHelper.navigateToNew(partition, "FedView_DataSource");
				
				updateFederatedView(federatedDataSource, sourceTable, name, removeColumns, selectColumns, targetName, targetDataType, unique);
				
				saveFederatedView(name, detailScreenTitle);
			}
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public void updateFederatedView(String path, String WorkbookName, String workSheetName, String testCaseName, int occurence) throws Exception {
		try {
			TestDataHelper testData = new TestDataHelper();
			ExcelReader excelReader = new ExcelReader();
			HashMap<String, ArrayList<String>> excelData = excelReader.readDataByColumn( path, WorkbookName, workSheetName, testCaseName, occurence );
			
			for(int i = 0; i < excelData.get("Name").size(); i++)
			{
				String federatedDataSource = excelData.get("Federated Data Source").get(i);
				String sourceTable = excelData.get("Source Table").get(i);
				String name = excelData.get("Name").get(i);
				
				String[] removeColumns = testData.getStringValue(excelData.get("Remove Columns").get(i), firstLevelDelimiter);
				String[] selectColumns = testData.getStringValue(excelData.get("Select Columns").get(i), firstLevelDelimiter);
				String[] targetName = testData.getStringValue(excelData.get("Target Name").get(i), firstLevelDelimiter);
				String[] targetDataType = testData.getStringValue(excelData.get("Target DataType").get(i), firstLevelDelimiter);
				String[] unique = testData.getStringValue(excelData.get("Unique").get(i), firstLevelDelimiter);
				
				updateFederatedView(federatedDataSource, sourceTable, name, removeColumns, selectColumns, targetName, targetDataType, unique);
			}
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public void updateFederatedView(String federatedDataSource, String sourceTable, String name, String[] removeColumns,
			String[] selectColumns, String[] targetName, String[] targetDataType, String[] unique) throws Exception {
		try {
			selectTableDefinition(federatedDataSource, sourceTable);
			TextBoxHelper.type("FedView_Name", name);
			
			removeColumns(removeColumns);
			
			selectColumns(selectColumns, targetName, targetDataType, unique);
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	private void waitForSourceTableEnabling() throws Exception {
		try {
			int tryCount = 0;
			
			while (true) {
				if (EntityComboHelper.isEnabled("FedView_SourceTable"))
					break;
				else {
					if (tryCount > detailScreenWaitSec)
						FailureHelper.failTest("Source Table option did not get enabled.");
					else {
						Thread.sleep(200);
						tryCount++;
					}
				}
			}
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public void selectTableDefinition(String federatedDataSource, String sourceTable) throws Exception {
		try {
			if (ComboBoxHelper.isEnabled("FedView_DataSource")) {
				ComboBoxHelper.select("FedView_DataSource", federatedDataSource);
				GenericHelper.waitForLoadmask(detailScreenWaitSec);
				GridHelper.click("FedView_Columns_Grid");
				waitForSourceTableEnabling();
			}
			
			if (EntityComboHelper.isEnabled("FedView_SourceTable"))
				EntityComboHelper.selectUsingGridFilterTextBox("FedView_SourceTable", "Source Table Search", "FedView_SourceTable_Filter", sourceTable, "Source Table");
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public void selectColumns(String[] selectColumns, String[] targetName, String[] targetDataType, String[] unique) throws Exception {
		try {
			if (ValidationHelper.isNotEmpty(selectColumns)) {
				String gridId = "FedView_Columns_Grid";
				for (int i = 0; i < selectColumns.length; i++) {
					int rowNum = GridHelper.getRowNumber(gridId, selectColumns[i], "Source Name");
					
					if (rowNum > 0) {
						GridCheckBoxHelper.check(gridId, "FedView_Columns_Select", rowNum, "Select");
						
						if (ValidationHelper.isNotEmpty(targetName) && targetName.length > i && ValidationHelper.isNotEmpty(targetName[i]))
							GridHelper.updateGridTextBox(gridId, "FedView_Columns_TargetColumnName", rowNum, "Target Name", "Source Name", targetName[i]);
						
						if (ValidationHelper.isNotEmpty(targetDataType) && targetDataType.length > i && ValidationHelper.isNotEmpty(targetDataType[i]))
							GridHelper.updateGridComboBox(gridId, "FedView_Columns_TargetDataType", rowNum, "Target Type", "Source Name", targetDataType[i]);
						
						if (ValidationHelper.isNotEmpty(unique) && unique.length > i && ValidationHelper.isNotEmpty(unique[i]))
							GridHelper.updateGridCheckBox(gridId, "FedView_Columns_Unique", rowNum, "Unique", unique[i]);
						
						GridHelper.click(gridId);
					}
					else {
						FailureHelper.failTest("Column '" + selectColumns[i] + "' is not found in Federated View grid.");
					}
				}
			}
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public void removeColumns(String[] removeColumns) throws Exception {
		try {
			if (ValidationHelper.isNotEmpty(removeColumns)) {
				for (int i = 0; i < removeColumns.length; i++) {
					GridCheckBoxHelper.uncheck("FedView_Columns_Grid", "FedView_Columns_Select", removeColumns[i], "Source Name", "Select");
				}
			}
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public void saveFederatedView(String name, String detailScreenTitle) throws Exception {
		try {
			ButtonHelper.click("SaveButton");
			GenericHelper.waitForLoadmask(configProp.getCustomScreenWaitSec());
			
			if (ButtonHelper.isPresent("YesButton")) {
				ButtonHelper.click("YesButton");
				GenericHelper.waitForLoadmask(configProp.getCustomScreenWaitSec());
			}
			assertTrue(LabelHelper.isTitleNotPresent(detailScreenTitle), "Federated View save did not happen");
			
			ButtonHelper.click("SearchButton");
			GenericHelper.waitForLoadmask(searchScreenWaitSec);
			assertTrue(GridHelper.isValuePresent("SearchGrid", name, "Name"), "Value '" + name + "' is not found in grid.");
			Log4jHelper.logInfo("Federated View '" + name + "' created.");
		} catch (AssertionError e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
}