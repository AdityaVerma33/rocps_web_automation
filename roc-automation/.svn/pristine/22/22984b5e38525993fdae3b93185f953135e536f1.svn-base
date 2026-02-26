package com.subex.automation.helpers.application.screens;

import java.util.ArrayList;
import java.util.HashMap;

import com.subex.automation.helpers.application.NavigationHelper;
import com.subex.automation.helpers.component.ButtonHelper;
import com.subex.automation.helpers.component.GenericHelper;
import com.subex.automation.helpers.component.GridCheckBoxHelper;
import com.subex.automation.helpers.component.GridHelper;
import com.subex.automation.helpers.component.LabelHelper;
import com.subex.automation.helpers.component.SearchGridHelper;
import com.subex.automation.helpers.component.TextBoxHelper;
import com.subex.automation.helpers.file.ExcelReader;
import com.subex.automation.helpers.report.Log4jHelper;
import com.subex.automation.helpers.scripts.TestDataHelper;
import com.subex.automation.helpers.selenium.ROCAcceptanceTest;
import com.subex.automation.helpers.util.FailureHelper;

public class CaseGroupHelper extends ROCAcceptanceTest {
	
	public void createCaseGroup(String path, String fileName, String sheetName, String testCaseName, int occurance) throws Exception
	{
		try {
			TestDataHelper testData = new TestDataHelper();
			ExcelReader excelReader = new ExcelReader();
			HashMap<String, ArrayList<String>> excelData = excelReader.readDataByColumn( path, fileName, sheetName, testCaseName, occurance );
	
			for(int i = 0; i < excelData.get("Name").size(); i++) 
			{
				String partition = excelData.get("Partition").get(i);
				String name = excelData.get("Name").get(i);
				String[] groupFields = testData.getStringValue(excelData.get("Group Fields").get(i), firstLevelDelimiter);
				String[] teamPrivilege = testData.getStringValue(excelData.get("Team Privilege").get(i), firstLevelDelimiter);
				
				createCaseGroup(partition, name, groupFields, teamPrivilege);
			}
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public void createCaseGroup(String partition, String name, String[] groupFields, String[] teamPrivilege) throws Exception {
		try {
			NavigationHelper.navigateToScreen("Case Groups", "Case Group Definition Search");
			int row = SearchGridHelper.gridFilterSearchWithTextBox("CaseGroup_Name", name, "Name");

			if (row > 0) {
				Log4jHelper.logWarning("Case Group '" + name + " ' is already present.");
			}
			else {
				String detailScreenTitle = NavigationHelper.navigateToNew(partition, "CaseGroup_Name");
				
				TextBoxHelper.type("CaseGroup_Name", name);
				
				for (int i = 0; i < groupFields.length; i++) {
					GridCheckBoxHelper.check("CaseGroup_GroupFields_Grid", "CaseGroup_GroupFields_Include", groupFields[i], "Case Group Fields", "Include");
				}

				for (int i = 0; i < teamPrivilege.length; i++) {
					GridCheckBoxHelper.check("CaseGroup_Privileges_Grid", "CaseGroup_Privileges_Own", teamPrivilege[i], "Team", "Own");
				}
				
				saveCaseGroup(name, detailScreenTitle);
			}
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	private void saveCaseGroup(String name, String detailScreenTitle) throws Exception {
		try {
			ButtonHelper.click("SaveButton");
			GenericHelper.waitForLoadmask(detailScreenWaitSec);

			assertTrue(LabelHelper.isTitleNotPresent(detailScreenTitle), "Case Group save did not happen");
			ButtonHelper.click("SearchButton");
			GenericHelper.waitForLoadmask(searchScreenWaitSec);
			assertTrue(GridHelper.isValuePresent("SearchGrid", name, "Name"), "Value '" + name + "' is not found in grid.");
			Log4jHelper.logInfo("Case Group '" + name + "' created.");
		} catch (AssertionError e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
}