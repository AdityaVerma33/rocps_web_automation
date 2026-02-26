package com.subex.automation.helpers.application.screens;

import java.util.ArrayList;
import java.util.HashMap;

import com.subex.automation.helpers.application.NavigationHelper;
import com.subex.automation.helpers.component.ButtonHelper;
import com.subex.automation.helpers.component.CheckBoxHelper;
import com.subex.automation.helpers.component.ComboBoxHelper;
import com.subex.automation.helpers.component.GenericHelper;
import com.subex.automation.helpers.component.GridHelper;
import com.subex.automation.helpers.component.LabelHelper;
import com.subex.automation.helpers.component.SearchGridHelper;
import com.subex.automation.helpers.data.ValidationHelper;
import com.subex.automation.helpers.file.ExcelReader;
import com.subex.automation.helpers.report.Log4jHelper;
import com.subex.automation.helpers.scripts.TestDataHelper;
import com.subex.automation.helpers.selenium.ROCAcceptanceTest;
import com.subex.automation.helpers.util.FailureHelper;

public class EntitiesHelper extends ROCAcceptanceTest {
	
	public void editEntity(String path, String workBookName, String workSheetName, String testCaseName, int occurance) throws Exception 
	{
		try {
			TestDataHelper testData = new TestDataHelper();
			ExcelReader excelReader = new ExcelReader();
			HashMap<String, ArrayList<String>> excelData = excelReader.readDataByColumn( path, workBookName, workSheetName, testCaseName, occurance);
			
			for(int i = 0; i < excelData.get("Entity").size(); i++)
			{
				String entityName = excelData.get("Entity").get(i);
				
				boolean allowNotes = ValidationHelper.isTrue(excelData.get("Allow Notes").get(i));
				boolean globallyCached = ValidationHelper.isTrue(excelData.get("Globally Cached").get(i));
				boolean idReset = ValidationHelper.isTrue(excelData.get("ID Reset").get(i));
				
				String[][] extraArguments = testData.getStringValue(excelData.get("Extra Arguments").get(i), firstLevelDelimiter, secondLevelDelimiter);
				String extraControl = excelData.get("Extra Control").get(i);
				
				editEntity(entityName, allowNotes, globallyCached, idReset, extraArguments, extraControl);
			}
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public void editEntity(String entityName, boolean allowNotes, boolean globallyCached, boolean idReset, String[][] extraArguments,
			String extraControl) throws Exception
	{
		try {
			int row = navigateToEntities(entityName);
			
			if (row > 0) {
				String detailScreenTitle = NavigationHelper.navigateToEdit("SearchGrid", row, "Entities_AllowNotes");
				
				if (allowNotes && CheckBoxHelper.isEnabled("Entities_AllowNotes"))
					CheckBoxHelper.check("Entities_AllowNotes");
				
				if (globallyCached && CheckBoxHelper.isEnabled("Entities_GloballyCached"))
					CheckBoxHelper.check("Entities_GloballyCached");
				
				if (idReset && CheckBoxHelper.isEnabled("Entities_IDReset"))
					CheckBoxHelper.check("Entities_IDReset");
				
				addExtraArgs(extraArguments);
				ComboBoxHelper.select("Entities_ExtraControl", extraControl);
				
				saveEntities(entityName, detailScreenTitle);
			}
			else {
				FailureHelper.failTest("Entity '" + entityName + "' is not found.");
			}
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public int navigateToEntities(String entityName) throws Exception {
		try {
			NavigationHelper.navigateToScreen("Entities", "Entity Search");
			int row = SearchGridHelper.gridFilterSearchWithTextBox("Entities_Entity", entityName, "Entity");
			
			return row;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public void addExtraArgs(String[][] extraArguments) throws Exception {
		try {
			if (ValidationHelper.isNotEmpty(extraArguments)) {
				for (int i = 0; i < extraArguments.length; i++) {
					int rowNum = GridHelper.getRowNumber("Entities_ExtraArgument_Grid", extraArguments[i][0], "Name");
					
					if (rowNum == 0) {
						ButtonHelper.click("Entities_ExtraArgument_Add");
						GenericHelper.waitForLoadmask(detailScreenWaitSec);
						rowNum = GridHelper.getRowCount("Entities_ExtraArgument_Grid");
						
						GridHelper.updateGridTextBox("Entities_ExtraArgument_Grid", "Entities_ExtraArgument_Name", rowNum, "Name", extraArguments[i][0]);
					}
					
					GridHelper.updateGridComboBox("Entities_ExtraArgument_Grid", "Entities_ExtraArgument_Type", rowNum, "Type", extraArguments[i][1]);
					
					GridHelper.updateGridCheckBox("Entities_ExtraArgument_Grid", "Entities_ExtraArgument_Mandatory", rowNum, "Mandatory", extraArguments[i][2]);
				}
			}
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	private void saveEntities(String entityName, String detailScreenTitle) throws Exception {
		try {
			ButtonHelper.click("SaveButton");
			GenericHelper.waitForLoadmask(configProp.getCustomScreenWaitSec());
			
			assertTrue(LabelHelper.isTitleNotPresent(detailScreenTitle), "Entity save did not happen.");
			ButtonHelper.click("SearchButton");
			GenericHelper.waitForLoadmask(searchScreenWaitSec);
			assertTrue(GridHelper.isValuePresent("SearchGrid", entityName, "Entity"), "Value '" + entityName + "' is not found in grid.");
			Log4jHelper.logInfo("Entity '" + entityName + "' created.");
		} catch (AssertionError e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
}