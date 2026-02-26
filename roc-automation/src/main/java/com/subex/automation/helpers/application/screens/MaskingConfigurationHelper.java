package com.subex.automation.helpers.application.screens;

import java.util.ArrayList;
import java.util.HashMap;

import com.subex.automation.helpers.application.NavigationHelper;
import com.subex.automation.helpers.component.ButtonHelper;
import com.subex.automation.helpers.component.EntityComboHelper;
import com.subex.automation.helpers.component.GenericHelper;
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

public class MaskingConfigurationHelper extends ROCAcceptanceTest {

	public void createMaskingConfiguration(String path, String WorkbookName, String workSheetName, String testCaseName, int occurence) throws Exception {
		try {
			TestDataHelper testData = new TestDataHelper();
			ExcelReader excelReader = new ExcelReader();
			HashMap<String, ArrayList<String>> excelData = excelReader.readDataByColumn( path, WorkbookName, workSheetName, testCaseName, occurence );
	
			for(int i = 0; i < excelData.get("Name").size(); i++)
			{
				String partition = excelData.get("Partition").get(i);
				String name = excelData.get("Name").get(i);
				String tableDfnName = excelData.get("Table Definition").get(i);
				String[] columnName = testData.getStringValue(excelData.get("Column Name").get(i), firstLevelDelimiter);
				String[] masking = testData.getStringValue(excelData.get("Masking").get(i), firstLevelDelimiter);
				
				createMaskingConfiguration(partition, name, tableDfnName, columnName, masking);
			}
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public void createMaskingConfiguration(String partition, String name, String tableDfnName, String[] columnName, String[] masking) throws Exception {
		try {
			int row = navigateToMaskingConfiguration(name);
			
			NavigationHelper.navigateToNewOrEdit(row, partition, "Masking Config", "Masking_Name");
			String detailScreenTitle = NavigationHelper.getScreenTitle();
			
			updateMaskingConfiguration(name, tableDfnName, columnName, masking);
			
			saveMaskingConfiguration(name, detailScreenTitle);
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public void updateMaskingConfiguration(String path, String WorkbookName, String workSheetName, String testCaseName, int occurence) throws Exception {
		try {
			TestDataHelper testData = new TestDataHelper();
			ExcelReader excelReader = new ExcelReader();
			HashMap<String, ArrayList<String>> excelData = excelReader.readDataByColumn( path, WorkbookName, workSheetName, testCaseName, occurence );
	
			for(int i = 0; i < excelData.get("Name").size(); i++)
			{
				String name = excelData.get("Name").get(i);
				String tableDfnName = excelData.get("Table Definition").get(i);
				String[] columnName = testData.getStringValue(excelData.get("Column Name").get(i), firstLevelDelimiter);
				String[] masking = testData.getStringValue(excelData.get("Masking").get(i), firstLevelDelimiter);
				
				updateMaskingConfiguration(name, tableDfnName, columnName, masking);
			}
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public void updateMaskingConfiguration(String name, String tableDfnName, String[] columnName, String[] masking) throws Exception {
		try {
			TextBoxHelper.type("Masking_Name", name);
			String tableDfn = EntityComboHelper.getValue("Masking_TableDefinition");
			if (!tableDfn.equals(tableDfnName)) {
				EntityComboHelper.selectUsingSearchTextBox("Masking_TableDefinition", "Table Definition Search", "TableDfn_Name", tableDfnName, "Name");
				GenericHelper.waitForLoadmask(detailScreenWaitSec);
			}
			
			updateMaskingColumns(tableDfnName, columnName, masking);
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public void updateMaskingColumns(String masking) throws Exception {
		try {
			ArrayList<String> columnName = GridHelper.getColumnValues("Masking_Columns_Grid", "Column Name");
			
			if (ValidationHelper.isNotEmpty(columnName)) {
				for (int i = 0; i < columnName.size(); i++) {
					GridHelper.updateGridTextBox("Masking_Columns_Grid", "Masking_Columns_Masking", (i+1), "Masking", "Type", masking);
				}
			}
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public void updateMaskingColumns(String tableDfnName, String[] columnName, String[] masking) throws Exception {
		try {
			if (ValidationHelper.isNotEmpty(columnName)) {
				String gridId = "Masking_Columns_Grid";
				GenericHelper.waitForAJAXReady(detailScreenWaitSec);
				
				for (int i = 0; i < columnName.length; i++) {
					int rowNum = GridHelper.getRowNumber("Masking_Columns_Grid", columnName[i], "Column Name");
					
					if (rowNum > 0) {
						if (ValidationHelper.isNotEmpty(masking[i]))
							GridHelper.updateGridTextBox(gridId, "Masking_Columns_Masking", rowNum, "Masking", "Type", masking[i]);
						else {
							GridHelper.clickRow(gridId, rowNum, "Masking");
							if(!TextBoxHelper.isPresent("Masking_Columns_Masking"))
								GridHelper.clickRow(gridId, rowNum, "Masking");
							TextBoxHelper.clear("Masking_Columns_Masking");
							GridHelper.clickRow(gridId, rowNum, "Type");
						}
					}
					else {
						FailureHelper.failTest("Column '" + columnName[i] + "' is not found for Table Definition '" + tableDfnName + "'.");
					}
				}
			}
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public int navigateToMaskingConfiguration(String name) throws Exception {
		try {
			NavigationHelper.navigateToScreen("Masking Configuration", "Masking Config Search");
			GenericHelper.waitForLoadmask(searchScreenWaitSec);
			
			SearchGridHelper.gridFilterSearchWithTextBox("Masking_Name", name, "Name");
//			searchByName(name);
			int row = GridHelper.getRowNumber("SearchGrid", name, "Name");
			
			return row;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
//	public void searchByName(String name) throws Exception {
//		try {
//			GenericHelper.waitForLoadmask(searchScreenWaitSec);
//			SearchHelper searchHelper = new SearchHelper();
//			ElementHelper.waitForClickableElement("Masking_Name_FilterIcon", searchScreenWaitSec);
//			searchHelper.clickFilterIcon("Masking_Name_FilterIcon");
//			GenericHelper.waitForElement("Masking_Name", searchScreenWaitSec);
//			
//			TextBoxHelper.type("Masking_Name", name);
//			searchHelper.clickSearch();
//		} catch (Exception e) {
//			FailureHelper.setErrorMessage(e);
//			throw e;
//		}
//	}
//	
//	public void searchByTableDefinition(String tableDfnName) throws Exception {
//		try {
//			SearchHelper searchHelper = new SearchHelper();
//			searchHelper.clickFilterIcon("Masking_TableDefinition_FilterIcon");
//			
//			TextBoxHelper.type("Masking_TableDefinition_Filter", tableDfnName);
//			searchHelper.clickSearch();
//		} catch (Exception e) {
//			FailureHelper.setErrorMessage(e);
//			throw e;
//		}
//	}
	
	public void saveMaskingConfiguration(String name, String detailScreenTitle) throws Exception {
		try {
			ButtonHelper.click("SaveButton");
			GenericHelper.waitForLoadmask(detailScreenWaitSec);
			
			if (ButtonHelper.isPresent("OKButton")) {
				ButtonHelper.click("OKButton");
				GenericHelper.waitForLoadmask(searchScreenWaitSec);
			}
			
			assertTrue(LabelHelper.isTitleNotPresent(detailScreenTitle), "Masking Configuration save did not happen");
			ButtonHelper.click("SearchButton");
			GenericHelper.waitForLoadmask(searchScreenWaitSec);
			assertTrue(GridHelper.isValuePresent("SearchGrid", name, "Name"), "Value '" + name + "' is not found in grid.");
			Log4jHelper.logInfo("Masking Configuration '" + name + "' created.");
		} catch (AssertionError e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
}