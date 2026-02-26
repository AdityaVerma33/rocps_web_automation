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
import com.subex.automation.helpers.component.TextAreaHelper;
import com.subex.automation.helpers.component.TextBoxHelper;
import com.subex.automation.helpers.data.ValidationHelper;
import com.subex.automation.helpers.file.ExcelReader;
import com.subex.automation.helpers.report.Log4jHelper;
import com.subex.automation.helpers.selenium.ROCAcceptanceTest;
import com.subex.automation.helpers.util.FailureHelper;

public class CasePropertyHelper extends ROCAcceptanceTest {
	
	public void createCaseProperty(String path, String WorkbookName, String workSheetName, String testCaseName, int occurence) throws Exception
	{
		try {
			ExcelReader excelReader = new ExcelReader();
			HashMap<String, ArrayList<String>> excelData = excelReader.readDataByColumn( path, WorkbookName, workSheetName, testCaseName, occurence );
	
			for(int i = 0; i < excelData.get("Name").size(); i++)
			{
				String partition = excelData.get("Partition").get(i);
				String name = excelData.get("Name").get(i);
				String type = excelData.get("Type").get(i);
				String defaultValue = excelData.get("Default Value").get(i);
				String minimumValue = excelData.get("Minimum Value").get(i);
				String maximumValue = excelData.get("Maximum Value").get(i);
				String displayValue = excelData.get("Display Value").get(i);
				String storageValue = excelData.get("Storage Value").get(i);
				String tableInst = excelData.get("Table Instance").get(i);
				String displayColumn = excelData.get("Display Column").get(i);
				String lookupConstraint = excelData.get("SQL Lookup Constraint").get(i);
				String patternValue = excelData.get("Pattern Value").get(i);
				
				createCaseProperty(partition, name, type, defaultValue, minimumValue, maximumValue, displayValue, storageValue, tableInst,
						displayColumn, lookupConstraint, patternValue);
			}
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public void createCaseProperty(String partition, String name, String type, String defaultValue, String minimumValue, String maximumValue, 
			String displayValue, String storageValue, String tableInst, String displayColumn, String lookupConstraint, String patternValue) throws Exception {
		try {
			NavigationHelper.navigateToScreen("Case Properties", "Case Property Search");
			int row = SearchGridHelper.gridFilterSearchWithTextBox("CaseProperty_NameFilter", name, "Name");

			if (row > 0) {
				Log4jHelper.logWarning("Case Properties '" + name + "' is already present.");
			}
			else {
				String detailScreenTitle = NavigationHelper.navigateToNew(partition, "CaseProperty_Name");
				assertTrue(LabelHelper.isTitlePresent("New Case Property"));
				
				updateCaseProperty(name, type, defaultValue, minimumValue, maximumValue, displayValue, storageValue, tableInst, displayColumn,
						lookupConstraint, patternValue);
				
				saveCaseProperties(name, detailScreenTitle);
			}
		} catch (AssertionError e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public void updateCaseProperty(String name, String type, String defaultValue, String minimumValue, String maximumValue, 
			String displayValue, String storageValue, String tableInst, String displayColumn, String lookupConstraint, String patternValue) throws Exception {
		try {
			TextBoxHelper.type("CaseProperty_Name", name);
			ComboBoxHelper.select("CaseProperty_Type", type);
			
			if (type.equals("Data Property")) {
				TextBoxHelper.type("CaseProperty_Date_Default", defaultValue);
			}
			else if (type.equals("Decimal Property")) {
				TextBoxHelper.type("CaseProperty_Decimal_Default", defaultValue);
				TextBoxHelper.type("CaseProperty_Decimal_Minimum", minimumValue);
				TextBoxHelper.type("CaseProperty_Decimal_Maximum", maximumValue);
			}
			else if (type.equals("Flag Property")) {
				if (ValidationHelper.isTrue(defaultValue))
					CheckBoxHelper.check("CaseProperty_Flag_Default");
			}
			else if (type.equals("Hard Lookup Property")) {
				TextBoxHelper.type("CaseProperty_HardLookup_Default", defaultValue);
				TextBoxHelper.type("CaseProperty_HardLookup_DisplayValue", displayValue);
				TextBoxHelper.type("CaseProperty_HardLookup_StorageValue", storageValue);
			}
			else if (type.equals("Integer Property")) {
				TextBoxHelper.type("CaseProperty_Integer_Default", defaultValue);
				TextBoxHelper.type("CaseProperty_Integer_Minimum", minimumValue);
				TextBoxHelper.type("CaseProperty_Integer_Maximum", maximumValue);
			}
			else if (type.equals("Long Property")) {
				TextBoxHelper.type("CaseProperty_Long_Default", defaultValue);
				TextBoxHelper.type("CaseProperty_Long_Minimum", minimumValue);
				TextBoxHelper.type("CaseProperty_Long_Maximum", maximumValue);
			}
			else if (type.equals("SQL Lookup Property")) {
				ComboBoxHelper.select("CaseProperty_SQLLookup_Default", defaultValue);
				ComboBoxHelper.select("CaseProperty_SQLLookup_TableInstance", tableInst);
				ComboBoxHelper.select("CaseProperty_SQLLookup_DisplayColumn", displayColumn);
				TextAreaHelper.type("CaseProperty_SQLLookup_Constraint", lookupConstraint);
			}
			else if (type.equals("String Property")) {
				TextBoxHelper.type("CaseProperty_String_Default", defaultValue);
				TextBoxHelper.type("CaseProperty_String_Pattern", patternValue);
			}
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	private void saveCaseProperties(String name, String detailScreenTitle) throws Exception {
		try {
			ButtonHelper.click("CaseProperty_Save");
			GenericHelper.waitForLoadmask(detailScreenWaitSec);

			assertTrue(LabelHelper.isTitleNotPresent(detailScreenTitle));
			ButtonHelper.click("SearchButton");
			GenericHelper.waitForLoadmask(searchScreenWaitSec);
			assertTrue(GridHelper.isValuePresent("SearchGrid", name, "Name"));
			Log4jHelper.logInfo("Case Property '" + name + "' created.");
		} catch (AssertionError e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
}