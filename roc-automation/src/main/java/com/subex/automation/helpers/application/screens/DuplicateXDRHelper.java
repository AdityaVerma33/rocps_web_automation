package com.subex.automation.helpers.application.screens;

import java.util.ArrayList;
import java.util.HashMap;

import com.subex.automation.helpers.application.NavigationHelper;
import com.subex.automation.helpers.component.ButtonHelper;
import com.subex.automation.helpers.component.CheckBoxHelper;
import com.subex.automation.helpers.component.ComboBoxHelper;
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

public class DuplicateXDRHelper extends ROCAcceptanceTest {
	
	public void createDuplicateXDR( String path, String workBookName, String workSheetName, String testCaseName, int occurence ) throws Exception {
		try {
			TestDataHelper testData = new TestDataHelper();
			ExcelReader excelReader = new ExcelReader();
			HashMap<String, ArrayList<String>> excelData = excelReader.readDataByColumn( path, workBookName, workSheetName, testCaseName, occurence );
			
			for ( int i = 0; i < excelData.get( "Name" ).size(); i++ )
			{
				String partition = excelData.get( "Partition" ).get( i );
				String duplicateXDRName = excelData.get( "Name" ).get( i );
				String xdrTable = excelData.get( "XDR Table" ).get( i );
				String hashCodeField = excelData.get( "Hash Code Field" ).get( i );
				String flagField = excelData.get( "Flag Field" ).get( i );
				String idField = excelData.get( "Id Field" ).get( i );
				String tagStatusField = excelData.get( "Tag Status Field" ).get( i );
				
				boolean enableHourPartKey = ValidationHelper.isTrue(excelData.get( "Enable Hour Part Key" ).get( i ));
				String hourPartField = excelData.get( "Hour Part Field" ).get( i );
				boolean generateAlert = ValidationHelper.isTrue(excelData.get( "Generate Alert" ).get( i ));
				
				String[] matchFields = testData.getStringValue(excelData.get( "Match Fields" ).get( i ), firstLevelDelimiter);
				String[] tableInstances = testData.getStringValue(excelData.get( "Table Instance" ).get( i ), firstLevelDelimiter);
				
				createDuplicateXDR(partition, duplicateXDRName, xdrTable, hashCodeField, flagField, idField, tagStatusField, enableHourPartKey,
						hourPartField, generateAlert, matchFields, tableInstances);
			}
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public void createDuplicateXDR( String partition, String duplicateXDRName, String xdrTable, String hashCodeField, String flagField,
			String idField, String tagStatusField, boolean enableHourPartKey, String hourPartField, boolean generateAlert, String[] matchFields,
			String[] tableInstances) throws Exception {
		try {
			int rowNo = navigateToDuplicateXDR(duplicateXDRName);
			
			if (rowNo == 0) {
				String detailScreenTitle = NavigationHelper.navigateToNew(partition, "DuplicateXDR_Name");
				
				updateDuplicateXDR(duplicateXDRName, xdrTable, hashCodeField, flagField, idField, tagStatusField, enableHourPartKey,
						hourPartField, generateAlert, matchFields, tableInstances);
				
				saveDuplicateXDR(duplicateXDRName, detailScreenTitle);
			}
			else {
				Log4jHelper.logWarning("Duplicate XDR Check with name '" + duplicateXDRName + "' is already present.");
			}
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public void updateDuplicateXDR( String path, String workBookName, String workSheetName, String testCaseName, int occurence ) throws Exception {
		try {
			TestDataHelper testData = new TestDataHelper();
			ExcelReader excelReader = new ExcelReader();
			HashMap<String, ArrayList<String>> excelData = excelReader.readDataByColumn( path, workBookName, workSheetName, testCaseName, occurence );
			
			for ( int i = 0; i < excelData.get( "Name" ).size(); i++ )
			{
				String duplicateXDRName = excelData.get( "Name" ).get( i );
				String xdrTable = excelData.get( "XDR Table" ).get( i );
				String hashCodeField = excelData.get( "Hash Code Field" ).get( i );
				String flagField = excelData.get( "Flag Field" ).get( i );
				String idField = excelData.get( "Id Field" ).get( i );
				String tagStatusField = excelData.get( "Tag Status Field" ).get( i );
				
				boolean enableHourPartKey = ValidationHelper.isTrue(excelData.get( "Enable Hour Part Key" ).get( i ));
				String hourPartField = excelData.get( "Hour Part Field" ).get( i );
				boolean generateAlert = ValidationHelper.isTrue(excelData.get( "Generate Alert" ).get( i ));
				
				String[] matchFields = testData.getStringValue(excelData.get( "Match Fields" ).get( i ), firstLevelDelimiter);
				String[] tableInstances = testData.getStringValue(excelData.get( "Table Instance" ).get( i ), firstLevelDelimiter);
				
				updateDuplicateXDR(duplicateXDRName, xdrTable, hashCodeField, flagField, idField, tagStatusField, enableHourPartKey, hourPartField,
						generateAlert, matchFields, tableInstances);
			}
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public void updateDuplicateXDR( String duplicateXDRName, String xdrTable, String hashCodeField, String flagField, String idField,
			String tagStatusField, boolean enableHourPartKey, String hourPartField, boolean generateAlert, String[] matchFields,
			String[] tableInstances) throws Exception {
		try {
			TextBoxHelper.type( "DuplicateXDR_Name", duplicateXDRName);
			ComboBoxHelper.select( "DuplicateXDR_XDRTable", xdrTable );
			ComboBoxHelper.select( "DuplicateXDR_HashCodeField", hashCodeField );
			ComboBoxHelper.select( "DuplicateXDR_FlagField", flagField );
			ComboBoxHelper.select( "DuplicateXDR_IdField", idField );
			ComboBoxHelper.select( "DuplicateXDR_TagStatusField", tagStatusField );
			
			if (enableHourPartKey) {
				CheckBoxHelper.check( "DuplicateXDR_HourPartKey" );
				ComboBoxHelper.select( "DuplicateXDR_PartKeyField", hourPartField );
			}
			
			if (generateAlert)
				CheckBoxHelper.check( "DuplicateXDR_GenerateAlert");
			
			selectMatchDuplicateFields(matchFields);
			selectTableInstances(tableInstances);
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public int navigateToDuplicateXDR(String duplicateXDRName) throws Exception {
		try {
			NavigationHelper.navigateToScreen( "Duplicate XDR Check", "Duplicate XDR Check Search" );
			int row = SearchGridHelper.gridFilterSearchWithTextBox("DuplicateXDR_Name", duplicateXDRName, "Name");
			
			return row;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public void saveDuplicateXDR(String duplicateXDRName, String detailScreenTitle) throws Exception {
		try {
			ButtonHelper.click("SaveButton");
			GenericHelper.waitForLoadmask(detailScreenWaitSec);
			
			assertTrue(LabelHelper.isTitleNotPresent(detailScreenTitle), "Duplicate XDR Check save did not happen.");
			ButtonHelper.click("SearchButton");
			GenericHelper.waitForLoadmask(searchScreenWaitSec);
			assertTrue(GridHelper.isValuePresent("SearchGrid", duplicateXDRName, "Name"), "Value '" + duplicateXDRName + "' is not found in grid.");
			Log4jHelper.logInfo("Duplicate XDR Check '" + duplicateXDRName + "' created");
		} catch (AssertionError e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public void selectMatchDuplicateFields(String[] matchFields) throws Exception {
		try {
			if (ValidationHelper.isNotEmpty(matchFields)) {
				for (int i = 0; i < matchFields.length; i++)
					GridCheckBoxHelper.check("DuplicateXDR_MatchDuplicates_Grid", "DuplicateXDR_Match_CheckBox", matchFields[i], "Name", "Select");
			}
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public void selectTableInstances(String[] tableInstances) throws Exception {
		try {
			if (ValidationHelper.isNotEmpty(tableInstances)) {
				GridHelper.clickRow("DuplicateXDR_TableInstance_Grid", 1, 2);
				for (int i = 0; i < tableInstances.length; i++)
					GridCheckBoxHelper.check("DuplicateXDR_TableInstance_Grid", "DuplicateXDR_TableInstance_CheckBox", tableInstances[i], "Name", "Select");
			}
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
}