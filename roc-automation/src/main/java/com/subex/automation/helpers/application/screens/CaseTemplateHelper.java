package com.subex.automation.helpers.application.screens;

import java.util.ArrayList;
import java.util.HashMap;

import com.subex.automation.helpers.application.NavigationHelper;
import com.subex.automation.helpers.application.ROCHelper;
import com.subex.automation.helpers.component.ButtonHelper;
import com.subex.automation.helpers.component.CheckBoxHelper;
import com.subex.automation.helpers.component.ComboBoxHelper;
import com.subex.automation.helpers.component.EntitySearchHelper;
import com.subex.automation.helpers.component.GenericHelper;
import com.subex.automation.helpers.component.GridHelper;
import com.subex.automation.helpers.component.LabelHelper;
import com.subex.automation.helpers.component.MouseHelper;
import com.subex.automation.helpers.component.SearchGridHelper;
import com.subex.automation.helpers.component.TabHelper;
import com.subex.automation.helpers.component.TextAreaHelper;
import com.subex.automation.helpers.component.TextBoxHelper;
import com.subex.automation.helpers.data.ValidationHelper;
import com.subex.automation.helpers.file.ExcelReader;
import com.subex.automation.helpers.report.Log4jHelper;
import com.subex.automation.helpers.scripts.TestDataHelper;
import com.subex.automation.helpers.selenium.ROCAcceptanceTest;
import com.subex.automation.helpers.util.FailureHelper;

public class CaseTemplateHelper extends ROCAcceptanceTest{
	
	public void createCaseTemplate(String path, String fileName, String sheetName, String testCaseName, int occurance) throws Exception
	{
		try {
			TestDataHelper testData = new TestDataHelper();
			ExcelReader excelReader = new ExcelReader();
			HashMap<String, ArrayList<String>> excelData = excelReader.readDataByColumn( path, fileName, sheetName, testCaseName, occurance );
	
			for(int i = 0; i < excelData.get("Name").size(); i++) 
			{
				String partition = excelData.get("Partition").get(i);
				String name = excelData.get("Name").get(i);
				String caseDescription = excelData.get("Case Description").get(i);
				String workFlow = excelData.get("Workflow").get(i);
				
				String[] expiresIn = testData.getStringValue(excelData.get("Expires In").get(i), firstLevelDelimiter);
				String priority = excelData.get("Priority").get(i);
				boolean autoUpdate = ValidationHelper.isTrue(excelData.get("Autoupdate").get(i));
				boolean autoCure = ValidationHelper.isTrue(excelData.get("Autocure").get(i));
				boolean alert = ValidationHelper.isTrue(excelData.get("Alert").get(i));
				boolean generatePendingCase = ValidationHelper.isTrue(excelData.get("Generate Pending Case").get(i));
				String pendingCaseDuration = excelData.get("Pending Case Duration").get(i);
				
				String[] caseProperty = testData.getStringValue(excelData.get("Case Property").get(i), firstLevelDelimiter);
				String[][] propertyOptions = testData.getStringValue(excelData.get("Property Options").get(i), firstLevelDelimiter, secondLevelDelimiter);
				
				String[] caseGroup = testData.getStringValue(excelData.get("Case Group").get(i), firstLevelDelimiter);
				String[][] groupPropertyMapping = testData.getStringValue(excelData.get("Group Property Mapping").get(i), firstLevelDelimiter, secondLevelDelimiter);
				
				String[] privileges = testData.getStringValue(excelData.get("Privileges").get(i), firstLevelDelimiter);
				String[][] privilegeType = testData.getStringValue(excelData.get("Privilege Type").get(i), firstLevelDelimiter, secondLevelDelimiter);
				
				String noteNewCase = excelData.get("Note New Case").get(i);
				String noteCloseCase = excelData.get("Note Close Case").get(i);
				String noteAutocureCase = excelData.get("Note Autocure Case").get(i);
				String[] teamNotification = testData.getStringValue(excelData.get("Team Notification").get(i), firstLevelDelimiter);
				String[][] teamNotificationOptions = testData.getStringValue(excelData.get("Team Notification Options").get(i), firstLevelDelimiter, secondLevelDelimiter);
				String[] userNotification = testData.getStringValue(excelData.get("User Notification").get(i), firstLevelDelimiter);
				String[][] userNotificationOptions = testData.getStringValue(excelData.get("User Notification Options").get(i), firstLevelDelimiter, secondLevelDelimiter);
				String[] businessRule = testData.getStringValue(excelData.get("Business Rule").get(i), firstLevelDelimiter);
				boolean[] enableRule = testData.getBooleanValue(excelData.get("Enable Rule").get(i), firstLevelDelimiter);
				
				createCaseTemplate(partition, name, caseDescription, workFlow, expiresIn, priority, autoUpdate, autoCure, alert,
						generatePendingCase, pendingCaseDuration, caseProperty, propertyOptions, caseGroup, groupPropertyMapping, privileges,
						privilegeType, noteNewCase, noteCloseCase, noteAutocureCase, teamNotification, teamNotificationOptions, userNotification,
						userNotificationOptions, businessRule, enableRule);
			}
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public void createCaseTemplate(String partition, String name, String caseDescription, String workFlow, String[] expiresIn, String priority,
			boolean autoUpdate, boolean autoCure, boolean alert, boolean generatePendingCase, String pendingCaseDuration, String[] caseProperty,
			String[][] propertyOptions, String[] caseGroup, String[][] groupPropertyMapping, String[] privileges, String[][] privilegeType,
			String noteNewCase, String noteCloseCase, String noteAutocureCase, String[] teamNotification, String[][] teamNotificationOptions,
			String[] userNotification, String[][] userNotificationOptions, String[] businessRule, boolean[] enableRule) throws Exception{
		try
		{
			NavigationHelper.navigateToScreen("Case Templates", "Case Template Search");
			int row = SearchGridHelper.gridFilterSearchWithTextBox("CaseTemplate_Name", name, "Name");
			
			if (row > 0) {
				Log4jHelper.logWarning("Case Template '" + name + " ' is already present.");
			}
			else {
				String detailScreenTitle = NavigationHelper.navigateToNew(partition, "CaseTemplate_Name");
				
				updateCaseTemplate(name, caseDescription, workFlow, expiresIn, priority, autoUpdate, autoCure, alert, generatePendingCase,
						pendingCaseDuration, caseProperty, propertyOptions, caseGroup, groupPropertyMapping, privileges, privilegeType,
						noteNewCase, noteCloseCase, noteAutocureCase, teamNotification, teamNotificationOptions, userNotification,
						userNotificationOptions, businessRule, enableRule);
				
				saveCaseTemplate(name, detailScreenTitle);
			}
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public void updateCaseTemplate(String name, String caseDescription, String workFlow, String[] expiresIn, String priority,
			boolean autoUpdate, boolean autoCure, boolean alert, boolean generatePendingCase, String pendingCaseDuration, String[] caseProperty,
			String[][] propertyOptions, String[] caseGroup, String[][] groupPropertyMapping, String[] privileges, String[][] privilegeType,
			String noteNewCase, String noteCloseCase, String noteAutocureCase, String[] teamNotification, String[][] teamNotificationOptions,
			String[] userNotification, String[][] userNotificationOptions, String[] businessRule, boolean[] enableRule) throws Exception{
		try
		{
			TextBoxHelper.type("CaseTemplate_Name", name);
			TextAreaHelper.type("CaseTemplate_Description", caseDescription);
			ComboBoxHelper.select("CaseTemplate_Workflow", workFlow);
			if (ValidationHelper.isNotEmpty(expiresIn)) {
				TextBoxHelper.type("CaseTemplate_ExpiresInMultiplier", expiresIn[0]);
				ComboBoxHelper.select("CaseTemplate_ExpiresIn", expiresIn[1]);
			}
			
			ComboBoxHelper.select("CaseTemplate_Priority", priority);
			if (autoUpdate)
				CheckBoxHelper.check("CaseTemplate_Autoupdate");
			if (autoCure)
				CheckBoxHelper.check("CaseTemplate_Autocure");

			if (alert){
				CheckBoxHelper.check("CaseTemplate_Alert");
				GenericHelper.waitForLoadmask(detailScreenWaitSec);
				assertTrue(LabelHelper.isTitlePresent("Information"));
				ButtonHelper.click("OKButton");
				GenericHelper.waitForLoadmask(detailScreenWaitSec);
			}
			
			if (generatePendingCase) {
				CheckBoxHelper.check("CaseTemplate_GeneratePendingCases");
				TextBoxHelper.type("CaseTemplate_PendingCaseDuration", pendingCaseDuration);
			}
			
			TabHelper.gotoTab("CaseTemplate_Tab", "Properties and Grouping");
			
			addPropertyAndGrouping(caseProperty, propertyOptions);
			addCaseGroup(caseGroup, groupPropertyMapping);
			
			addPrivileges(privileges, privilegeType);
			addNotification(noteNewCase, noteCloseCase, noteAutocureCase, teamNotification, teamNotificationOptions, userNotification, userNotificationOptions);
			addBusinessRule(businessRule, enableRule);
		} catch (AssertionError e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}

	public void addPropertyAndGrouping(String[] caseProperty, String[][] propertyOptions) throws Exception {
		try {
			if (ValidationHelper.isNotEmpty(caseProperty)) {
				String gridId = "CaseTemplate_CaseProperty_Grid";
				EntitySearchHelper entitySearch = new EntitySearchHelper();
				
				for (int i = 0; i < caseProperty.length; i++) {
					int row = GridHelper.getRowNumber(gridId, caseProperty[i], "Name");
					
					if (row == 0) {
						ButtonHelper.click("CaseTemplate_CaseProperty_Add");
						GenericHelper.waitForLoadmask(detailScreenWaitSec);
						
						entitySearch.selectUsingGridFilterTextBox("Case Property Search", "CaseProperty_NameFilter", caseProperty[i], "Name");
						row = GridHelper.getRowNumber(gridId, caseProperty[i], "Name");
					}
					
					GridHelper.updateGridCheckBox(gridId, "CaseTemplate_CaseProperty_Unique", row, "Unique", propertyOptions[i][0]);
					
					GridHelper.updateGridComboBox(gridId, "CaseTemplate_CaseProperty_UpdateType", row, "Update Type", "Name", propertyOptions[i][1]);
					
					GridHelper.updateGridCheckBox(gridId, "CaseTemplate_CaseProperty_DisplayInSearch", row, "Display In Case Search", propertyOptions[i][2]);
				}
			}
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}

	public void addCaseGroup(String[] caseGroup, String[][] groupPropertyMapping) throws Exception {
		try {
			if (ValidationHelper.isNotEmpty(caseGroup)) {
				for (int i = 0; i < caseGroup.length; i++){
					MouseHelper.click("CaseTemplate_CaseGroup_Add");
					GridHelper.updateGridComboBox("CaseTemplate_CaseGroup_Grid", "CaseTemplate_CaseGroup_GroupDfn", (i+1), "Case Group Dfn", caseGroup[i]);
					
					for (int j = 0; j < groupPropertyMapping.length; j+=2) {
						int row = GridHelper.getRowNumber("CaseTemplate_GroupProperty_Grid", groupPropertyMapping[i][j], "Case Grp Dfn Field Map");
						GridHelper.updateGridComboBox("CaseTemplate_GroupProperty_Grid", "CaseTemplate_GroupProperty_Property", row, "Property", "Case Grp Dfn Field Map", groupPropertyMapping[i][j+1]);
						GridHelper.click("CaseTemplate_GroupProperty_Grid");
					}
				}
			}
		}
		catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}

	public void addPrivileges(String[] privileges, String[][] privilegeType) throws Exception {
		try {
			if (ValidationHelper.isNotEmpty(privileges)) {
				TabHelper.gotoTab("CaseTemplate_Tab", "Privileges");
				String gridId = "CaseTemplate_Privileges_Grid";
				
				for (int i = 0; i < privileges.length; i++) {
					int row = GridHelper.getRowNumber(gridId, privileges[i], "Team");
					
					for (int j = 0; j < privilegeType[i].length; j++) {
						switch(privilegeType[i][j]) {
						case "Browse":
							GridHelper.updateGridCheckBox(gridId, "CaseTemplate_Privileges_Browse", row, privilegeType[i][j], true);
							break;
						case "New":
							GridHelper.updateGridCheckBox(gridId, "CaseTemplate_Privileges_New", row, privilegeType[i][j], true);
							break;
						case "Edit":
							GridHelper.updateGridCheckBox(gridId, "CaseTemplate_Privileges_Edit", row, privilegeType[i][j], true);
							break;
						case "Delete":
							GridHelper.updateGridCheckBox(gridId, "CaseTemplate_Privileges_Delete", row, privilegeType[i][j], true);
							break;
						}
					}
				}
			}
		}
		catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public void addNotification(String noteNewCase, String noteCloseCase, String noteAutocureCase, String[] teamNotification,
			String[][] teamNotificationOptions, String[] userNotification, String[][] userNotificationOptions) throws Exception {
		try {
			TabHelper.gotoTab("CaseTemplate_Tab", "Notifications");
			TextAreaHelper.type("CaseTemplate_Notifications_NoteNewCase", noteNewCase);
			TextAreaHelper.type("CaseTemplate_Notifications_NoteCloseCase", noteCloseCase);
			TextAreaHelper.type("CaseTemplate_Notifications_NoteAutocureCase", noteAutocureCase);
			
			ROCHelper rocHelper = new ROCHelper();
			rocHelper.addTeamNotification(teamNotification, teamNotificationOptions);
			rocHelper.addUserNotification(userNotification, userNotificationOptions);
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public void addBusinessRule(String[] businessRule, boolean[] enableRule) throws Exception {
		try {
			if (ValidationHelper.isNotEmpty(businessRule)) {
				TabHelper.gotoTab("CaseTemplate_Tab", "Rules");
				EntitySearchHelper entitySearch = new EntitySearchHelper();
				
				for (int i = 0; i < businessRule.length; i++){
					MouseHelper.click("CaseTemplate_Rules_Add");
					GenericHelper.waitForLoadmask(detailScreenWaitSec);
					entitySearch.selectUsingGridFilterTextBox("Business Rule Search", "BusinessRule_Name", businessRule[i], "Name");
					
					int row = GridHelper.getRowNumber("CaseTemplate_Rules_Grid", businessRule[i], "Name");
					GridHelper.updateGridCheckBox("CaseTemplate_Rules_Grid", "CaseTemplate_Rules_Enable", row, "Enable", enableRule[i]);
				}
			}
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	private void saveCaseTemplate(String name, String detailScreenTitle) throws Exception {
		try {
			ButtonHelper.click("SaveButton");
			GenericHelper.waitForLoadmask(detailScreenWaitSec);
			assertTrue(LabelHelper.isTitleNotPresent(detailScreenTitle));
			
			ButtonHelper.click("SearchButton");
			GenericHelper.waitForLoadmask(searchScreenWaitSec);
			assertTrue(GridHelper.isValuePresent("SearchGrid", name, "Name"));
			Log4jHelper.logInfo("Case Template '" + name + "' created.");
		} catch (AssertionError e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
}