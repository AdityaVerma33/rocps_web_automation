package com.subex.automation.helpers.application.screens;

import java.util.ArrayList;
import java.util.HashMap;

import com.subex.automation.helpers.application.NavigationHelper;
import com.subex.automation.helpers.component.ButtonHelper;
import com.subex.automation.helpers.component.FileHelper;
import com.subex.automation.helpers.component.GenericHelper;
import com.subex.automation.helpers.component.GridHelper;
import com.subex.automation.helpers.component.LabelHelper;
import com.subex.automation.helpers.component.RadioHelper;
import com.subex.automation.helpers.component.TextBoxHelper;
import com.subex.automation.helpers.component.TreeHelper;
import com.subex.automation.helpers.componentHelpers.SearchHelper;
import com.subex.automation.helpers.data.ValidationHelper;
import com.subex.automation.helpers.file.ExcelReader;
import com.subex.automation.helpers.file.XMLReader;
import com.subex.automation.helpers.report.Log4jHelper;
import com.subex.automation.helpers.scripts.TestDataHelper;
import com.subex.automation.helpers.selenium.ROCAcceptanceTest;
import com.subex.automation.helpers.util.FailureHelper;

public class EntityImportHelper extends ROCAcceptanceTest {
	
	static int waitTime = 300;
	
	public void createEntityImport(String path, String workBookName, String workSheetName, String testCaseName, int occurance) throws Exception 
	{
		try {
			TestDataHelper testData = new TestDataHelper();
			ExcelReader excelReader = new ExcelReader();
			HashMap<String, ArrayList<String>> excelData = excelReader.readDataByColumn( path, workBookName, workSheetName, testCaseName, occurance);
			ArrayList<String> waitTimeInSecs = excelData.get("Wait In Secs");
			
			for(int i = 0; i < excelData.get("Name").size(); i++)
			{
				String partition = excelData.get("Partition").get(i);
				String entityImportName = excelData.get("Name").get(i);
				String fileName = excelData.get("Filename").get(i);
				String fileNameWithPath = GenericHelper.getUploadFilePath(fileName);
				
				boolean importAll = ValidationHelper.isTrue(excelData.get("Import All").get(i));
				String[] excludeEntityType = testData.getStringValue(excelData.get("Exclude Entity Type").get(i), firstLevelDelimiter);
				String[][] excludeEntity = testData.getStringValue(excelData.get("Exclude Entity").get(i), firstLevelDelimiter, secondLevelDelimiter);
				
				if (waitTimeInSecs != null && waitTimeInSecs.size() > i)
					waitTime = Integer.parseInt(waitTimeInSecs.get(i));
				else
					waitTime = 300;
				
				createEntityImport(partition, entityImportName, fileNameWithPath, importAll, excludeEntityType, excludeEntity);
			}
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public void createEntityImport(String partition, String entityImportName, String fileNameWithPath, boolean importAll, String[] excludeEntityType,
			String[][] excludeEntity) throws Exception
	{
		try {
			navigateToEntityImport(partition, entityImportName);
			NavigationHelper.navigateToNew(partition, "EntityImport_UploadButton");
			assertTrue(LabelHelper.isTitlePresent("Select an import file"));
			
			String exportVersion = configProp.getEntityExportVersion();
			String sourceDir = null;
			String sourceFileName = null;
			String destinationFileName = null;
			
			if (ValidationHelper.isNotEmpty(exportVersion)) {
				int index = fileNameWithPath.lastIndexOf("\\");
				sourceDir = fileNameWithPath.substring(0, index);
				sourceFileName = fileNameWithPath.substring(index+1);
				destinationFileName = "Copy_" + sourceFileName;
				FileHelper.copyFile(automationOS, sourceDir, sourceDir, sourceFileName, destinationFileName, false);
				XMLReader.updateAllAttributes(automationOS, fileNameWithPath, "EntityExport", "version", exportVersion);
			}
			
			GenericHelper.fileUpload("EntityImport_FileUpload", fileNameWithPath);
			ButtonHelper.click("EntityImport_UploadButton");
			GenericHelper.waitForLoadmask(searchScreenWaitSec);
			GenericHelper.waitForElement("EntityImport_Name", searchScreenWaitSec);
			GenericHelper.waitForLoadmask(searchScreenWaitSec);
			
			assertTrue(LabelHelper.isTitlePresent("New Entity Import"), "New Entity Import screen did not appear.");
			String detailScreenTitle = NavigationHelper.getScreenTitle();
			
			TextBoxHelper.type( "EntityImport_Name", entityImportName );
			if (importAll)
				RadioHelper.click("EntityImport_ImportAll");
			else
				RadioHelper.click("EntityImport_ImportSelected");
			
			GenericHelper.waitForLoadmask(detailScreenWaitSec);
			removeEntities(excludeEntityType, excludeEntity);
			
			saveEntityImport(entityImportName, detailScreenTitle);
			if (ValidationHelper.isNotEmpty(exportVersion)) {
				FileHelper.copyFile(automationOS, sourceDir, sourceDir, destinationFileName, sourceFileName, false);
			}
		} catch (AssertionError e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	private int navigateToEntityImport(String partition, String entityImportName) throws Exception {
		try {
			NavigationHelper.navigateToScreen("Entity Import", "Entity Import Search");
			
			SearchHelper searchHelper = new SearchHelper();
			searchHelper.clickFilterIcon("EntityImport_Name_FilterIcon");
			TextBoxHelper.type("Grid_Filter_Panel", "EntityImport_Name", entityImportName);
			searchHelper.clickSearch();
			int row = GridHelper.getRowNumber("SearchGrid", entityImportName);
			
			return row;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	private void removeEntities(String[] excludeEntityType, String[][] excludeEntity) throws Exception {
		try {
			if (ValidationHelper.isNotEmpty(excludeEntityType) && ValidationHelper.isNotEmpty(excludeEntity)) {
				for (int i = 0; i < excludeEntityType.length; i++) {
					if (ValidationHelper.isNotEmpty(excludeEntity[i])) {
						for (int j = 0; j < excludeEntity[i].length; j++) {
							TreeHelper.unCheckCheckBox("EntityImport_Tree", excludeEntityType[i], excludeEntity[i][j]);
						}
					}
				}
			}
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	private void saveEntityImport(String entityImportName, String detailScreenTitle) throws Exception {
		try {
			ButtonHelper.click("SaveButton");
			GenericHelper.waitForLoadmask(waitTime);
			
			if (ButtonHelper.isPresent("YesButton")) {
				ButtonHelper.click("YesButton");
				GenericHelper.waitForLoadmask(waitTime);
			}
			
			if (ButtonHelper.isPresent("OKButton")) {
				ButtonHelper.click("OKButton");
				GenericHelper.waitForLoadmask(waitTime);
			}
			
			assertTrue(LabelHelper.isTitleNotPresent(detailScreenTitle), "Entity Import save did not happen.");
			ButtonHelper.click("SearchButton");
			GenericHelper.waitForLoadmask(searchScreenWaitSec);
			assertTrue(GridHelper.isValuePresent("SearchGrid", entityImportName, "Name"), "Value '" + entityImportName + "' is not found in grid.");
			Log4jHelper.logInfo("Entity Import '" + entityImportName + "' created.");
		} catch (AssertionError e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
}