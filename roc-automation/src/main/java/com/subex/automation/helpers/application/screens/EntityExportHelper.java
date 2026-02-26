package com.subex.automation.helpers.application.screens;

import java.util.ArrayList;
import java.util.HashMap;

import com.subex.automation.helpers.application.NavigationHelper;
import com.subex.automation.helpers.component.ButtonHelper;
import com.subex.automation.helpers.component.ComboBoxHelper;
import com.subex.automation.helpers.component.GenericHelper;
import com.subex.automation.helpers.component.GridHelper;
import com.subex.automation.helpers.component.LabelHelper;
import com.subex.automation.helpers.component.LinkHelper;
import com.subex.automation.helpers.component.SearchGridHelper;
import com.subex.automation.helpers.component.TextBoxHelper;
import com.subex.automation.helpers.component.TreeHelper;
import com.subex.automation.helpers.file.ExcelReader;
import com.subex.automation.helpers.report.Log4jHelper;
import com.subex.automation.helpers.scripts.TestDataHelper;
import com.subex.automation.helpers.selenium.ROCAcceptanceTest;
import com.subex.automation.helpers.util.FailureHelper;

public class EntityExportHelper extends ROCAcceptanceTest {
	
	public void createEntityExport(String path, String workBookName, String workSheetName, String testCaseName, int occurance) throws Exception 
	{
		try {
			TestDataHelper testData = new TestDataHelper();
			ExcelReader excelReader = new ExcelReader();
			HashMap<String, ArrayList<String>> excelData = excelReader.readDataByColumn( path, workBookName, workSheetName, testCaseName, occurance);
			
			for(int i = 0; i < excelData.get("Name").size(); i++)
			{
				String partition = excelData.get("Partition").get(i);
				String entityExportName = excelData.get("Name").get(i);
				String fileName = excelData.get("Filename").get(i);
				
				String[] exportType = testData.getStringValue(excelData.get("Export Type").get(i), firstLevelDelimiter);
				String[][] exportEntity = testData.getStringValue(excelData.get("Export Entity").get(i), firstLevelDelimiter, secondLevelDelimiter);
				
				createEntityExport(partition, entityExportName, fileName, exportType, exportEntity);
			}
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public void createEntityExport(String partition, String entityExportName, String fileName, String[] exportType, String[][] exportEntity) throws Exception
	{
		try {
			int row = navigateToEntityExport(partition, entityExportName);
			
			if (row > 0) {
				Log4jHelper.logWarning("Entity Export '" + entityExportName + "' is already present.");
			}
			else {
				String detailScreenTitle = NavigationHelper.navigateToNew(partition, "EntityExport_Name");
				
				TextBoxHelper.type( "EntityExport_Name", entityExportName );
				TextBoxHelper.type( "EntityExport_FileName", fileName );
				
				addExportType(exportType, exportEntity);
				
				saveEntityExport(entityExportName, detailScreenTitle);
			}
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	private int navigateToEntityExport(String partition, String entityExportName) throws Exception {
		try {
			NavigationHelper.navigateToScreen("Entity Export", "Entity Export Search");
			SearchGridHelper.gridFilterSearchWithTextBox("EntityExport_Name", entityExportName, "Name");
			int row = GridHelper.getRowNumber("SearchGrid", entityExportName, "Name");
			
			return row;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	private void addExportType(String[] exportType, String[][] exportEntity) throws Exception {
		try {
			for (int i = 0; i < exportType.length; i++) {
				ComboBoxHelper.select( "EntityExport_ExportType", exportType[i] );
				ButtonHelper.click("EntityExport_AddExportType");
				GenericHelper.waitForLoadmask(detailScreenWaitSec);
				
				for (int j = 0; j < exportEntity[i].length; j++) {
					LinkHelper.click("Collapse All");
					GenericHelper.waitForLoadmask(detailScreenWaitSec);
					
					TreeHelper.rightClick("EntityExport_ExportTree", exportType[i]);
					ButtonHelper.click("Add Items");
					GenericHelper.waitForLoadmask(detailScreenWaitSec);
					
					int rowNum = GridHelper.getRowNumber("SearchGrid", exportEntity[i][j]);
					GridHelper.clickRow("SearchGrid", rowNum, 1);
					ButtonHelper.click("OKButton");
					GenericHelper.waitForLoadmask(detailScreenWaitSec);
					assertTrue(TreeHelper.isChildPresent("EntityExport_ExportTree", exportType[i], exportEntity[i][j]));
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
	
	private void saveEntityExport(String entityExportName, String detailScreenTitle) throws Exception {
		try {
			ButtonHelper.click("SaveButton");
			GenericHelper.waitForLoadmask(configProp.getCustomScreenWaitSec());
			
			assertTrue(LabelHelper.isTitleNotPresent(detailScreenTitle), "Entity Export save did not happen.");
			ButtonHelper.click("SearchButton");
			GenericHelper.waitForLoadmask(searchScreenWaitSec);
			assertTrue(GridHelper.isValuePresent("SearchGrid", entityExportName, "Name"), "Value '" + entityExportName + "' is not found in grid.");
			Log4jHelper.logInfo("Entity Export '" + entityExportName + "' created.");
		} catch (AssertionError e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
}