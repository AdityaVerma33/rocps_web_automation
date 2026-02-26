package com.subex.automation.helpers.application.screens;

import java.util.ArrayList;
import java.util.HashMap;

import com.subex.automation.helpers.application.NavigationHelper;
import com.subex.automation.helpers.component.ButtonHelper;
import com.subex.automation.helpers.component.ComboBoxHelper;
import com.subex.automation.helpers.component.GenericHelper;
import com.subex.automation.helpers.component.GridHelper;
import com.subex.automation.helpers.component.LabelHelper;
import com.subex.automation.helpers.component.SearchGridHelper;
import com.subex.automation.helpers.component.TextBoxHelper;
import com.subex.automation.helpers.file.ExcelReader;
import com.subex.automation.helpers.report.Log4jHelper;
import com.subex.automation.helpers.selenium.ROCAcceptanceTest;
import com.subex.automation.helpers.util.FailureHelper;

public class CaseGroupFieldHelper extends ROCAcceptanceTest {
	
	public void createCaseGroupField(String path, String fileName, String sheetName, String testCaseName, int occurance) throws Exception
	{
		try {
			ExcelReader excelReader = new ExcelReader();
			HashMap<String, ArrayList<String>> excelData = excelReader.readDataByColumn( path, fileName, sheetName, testCaseName, occurance );
	
			for(int i = 0; i < excelData.get("Name").size(); i++) 
			{
				String partition = excelData.get("Partition").get(i);
				String name = excelData.get("Name").get(i);
				String type = excelData.get("Type").get(i);
				
				createCaseGroupField(partition, name, type);
			}
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public void createCaseGroupField(String partition, String name, String type) throws Exception {
		try {
			NavigationHelper.navigateToScreen("Case Group Fields", "Case Group Field Search");
			int row = SearchGridHelper.gridFilterSearchWithTextBox("CaseGroupFields_Name", name, "Name");

			if (row > 0) {
				Log4jHelper.logWarning("Case Group Field '" + name + "' is already present.");
			}
			else {
				String detailScreenTitle = NavigationHelper.navigateToNew(partition, "CaseGroupFields_Name");
				
				TextBoxHelper.type("CaseGroupFields_Panel", "CaseGroupFields_Name", name);
				ComboBoxHelper.select("CaseGroupFields_Panel", "CaseGroupFields_Type", type);
					
				saveCaseGroupField(name, detailScreenTitle);
			}
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	private void saveCaseGroupField(String name, String detailScreenTitle) throws Exception {
		try {
			ButtonHelper.click("SaveButton");
			GenericHelper.waitForLoadmask(searchScreenWaitSec);
			
			assertTrue(LabelHelper.isTitleNotPresent(detailScreenTitle), "Case Group Field save did not happen");
			ButtonHelper.click("SearchButton");
			GenericHelper.waitForLoadmask(searchScreenWaitSec);
			assertTrue(GridHelper.isValuePresent("SearchGrid", name, "Name"), "Value '" + name + "' is not found in grid.");
			Log4jHelper.logInfo("Case Group Field '" + name + "' created.");
		} catch (AssertionError e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
}