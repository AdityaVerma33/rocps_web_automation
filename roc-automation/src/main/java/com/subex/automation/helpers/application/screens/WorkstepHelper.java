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
import com.subex.automation.helpers.component.LinkHelper;
import com.subex.automation.helpers.component.MouseHelper;
import com.subex.automation.helpers.component.RadioHelper;
import com.subex.automation.helpers.component.SearchGridHelper;
import com.subex.automation.helpers.component.TextBoxHelper;
import com.subex.automation.helpers.data.ValidationHelper;
import com.subex.automation.helpers.file.ExcelReader;
import com.subex.automation.helpers.report.Log4jHelper;
import com.subex.automation.helpers.scripts.TestDataHelper;
import com.subex.automation.helpers.selenium.ROCAcceptanceTest;
import com.subex.automation.helpers.util.FailureHelper;

public class WorkstepHelper extends ROCAcceptanceTest{
	
	public void createWorkstep(String path, String fileName, String sheetName, String testCaseName, int occurance) throws Exception
	{
		try {
			TestDataHelper testData = new TestDataHelper();
			ExcelReader excelReader = new ExcelReader();
			HashMap<String, ArrayList<String>> excelData = excelReader.readDataByColumn( path, fileName, sheetName, testCaseName, occurance );
	
			for(int i = 0; i < excelData.get("Name").size(); i++) 
			{
				String partition = excelData.get("Partition").get(i);
				String name = excelData.get("Name").get(i);
				String type = excelData.get("Type").get(i);
				String externalReference = excelData.get("External Reference").get(i);
				String[] expiresIn = testData.getStringValue(excelData.get("Expires In").get(i), firstLevelDelimiter);
				String allocationComponent = excelData.get("Allocation Component").get(i);
				boolean notify = ValidationHelper.isTrue(excelData.get("Notify").get(i));
				boolean noMovementLock = ValidationHelper.isTrue(excelData.get("No Movement Lock").get(i));
				boolean fullLock = ValidationHelper.isTrue(excelData.get("Full Lock").get(i));
				
				String[] caseProperty = testData.getStringValue(excelData.get("Case Property").get(i), firstLevelDelimiter);
				String[][] propMandatoryCheck = testData.getStringValue(excelData.get("Property Mandatory Check").get(i), firstLevelDelimiter, secondLevelDelimiter);
				
				String[] teamName = testData.getStringValue(excelData.get("Team Name").get(i), firstLevelDelimiter);
				String[][] teamPrivilege = testData.getStringValue(excelData.get("Team Privilege").get(i), firstLevelDelimiter, secondLevelDelimiter);
				
				String[][] escalation = testData.getStringValue(excelData.get("Escalation").get(i), firstLevelDelimiter, secondLevelDelimiter);
				String[] notifyTeam = testData.getStringValue(excelData.get("Team Notification").get(i), firstLevelDelimiter);
				String[] notifyTeamOptions = testData.getStringValue(excelData.get("Team Notification Options").get(i), firstLevelDelimiter);
				String[] notifyUser = testData.getStringValue(excelData.get("User Notification").get(i), firstLevelDelimiter);
				String[] notifyUserOptions = testData.getStringValue(excelData.get("User Notification Options").get(i), firstLevelDelimiter);
				
				String[] businessRule = testData.getStringValue(excelData.get("Business Rule").get(i), firstLevelDelimiter);
				boolean[] enableRule = testData.getBooleanValue(excelData.get("Enable Rule").get(i), firstLevelDelimiter);
				String[] nextStep = testData.getStringValue(excelData.get("Rule Next Step").get(i), firstLevelDelimiter);
				
				createWorkstep(partition, name, type, externalReference, expiresIn, allocationComponent, notify, noMovementLock, fullLock,
						caseProperty, propMandatoryCheck, teamName, teamPrivilege, escalation, notifyTeam, notifyTeamOptions, notifyUser,
						notifyUserOptions, businessRule, enableRule, nextStep);
			}
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public void createWorkstep(String partition, String name, String type, String externalReference, String[] expiresIn, String allocationComponent,
			boolean notify, boolean noMovementLock, boolean fullLock, String[] caseProperty, String[][] propMandatoryCheck, String[] teamName,
			String[][] teamPrivilege, String[][] escalation, String[] notifyTeam, String[] notifyTeamOptions, String[] notifyUser,
			String[] notifyUserOptions, String[] businessRule, boolean[] enableRule, String[] nextStep) throws Exception {
		try
		{
			NavigationHelper.navigateToScreen("Worksteps", "Workstep Search");
			int row = SearchGridHelper.gridFilterSearchWithTextBox("Workstep_Name", name, "Name");

			if (row > 0) {
				Log4jHelper.logWarning("Workstep '" + name + " ' is already present.");
			}
			else {
				String detailScreenTitle = NavigationHelper.navigateToNew(partition, "Workstep_Name");
				assertTrue(LabelHelper.isTitlePresent("New Workstep"));
				
				TextBoxHelper.type("Workstep_Name", name);
				ComboBoxHelper.select("Workstep_Type", type);
				GenericHelper.waitForLoadmask(detailScreenWaitSec);
				
				TextBoxHelper.type("Workstep_ExternalReference", externalReference);
				
				if (ValidationHelper.isNotEmpty(expiresIn)) {
					TextBoxHelper.type("Workstep_ExpiresInMultiplier", expiresIn[0]);
					Thread.sleep(500);
					ComboBoxHelper.select("Workstep_ExpiresInPeriod", expiresIn[1]);
				}
				
				addPrivileges(teamName, teamPrivilege);
				
				if (!type.equals("Resolved")) {
					if (noMovementLock)
						RadioHelper.click("Workstep_NoMovementLock");
					if (fullLock)
						RadioHelper.click("Workstep_FullLock");
					
					addEscalations(escalation, notifyTeam, notifyTeamOptions, notifyUser, notifyUserOptions);
				}
				
				if (type.equals("External Interface")){
					addCaseProperties(caseProperty, propMandatoryCheck);
				}
				else if (type.equals("Manual - Data Entry")){
					ComboBoxHelper.select("Workstep_AllocationComponent", allocationComponent);
					if(notify)
						CheckBoxHelper.check("Workstep_Notify");
					
					addCaseProperties(caseProperty, propMandatoryCheck);
					addBusinessRule(businessRule, enableRule, nextStep);
				}
				
				saveWorkStep(name, detailScreenTitle);
			}
		} catch (AssertionError e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public void addPrivileges(String[] teamName, String[][] teamPrivilege) throws Exception {
		try {
			if (ValidationHelper.isNotEmpty(teamName)) {
				if (ValidationHelper.isNotEmpty(teamPrivilege)) {
					for (int i = 0; i < teamName.length; i++) {
						int row = GridHelper.getRowNumber("Workstep_Privileges_Grid", teamName[i], "Team");
						
						if (row == 0) {
							FailureHelper.failTest("Team '" + teamName[i] + "' not found in Privileges grid.");
						}
						else {
							if (ValidationHelper.isNotEmpty(teamPrivilege[i])) {
								for (int j = 0; j < teamPrivilege[i].length; j++)
									GridHelper.updateGridCheckBox("Workstep_Privileges_Grid", row, teamPrivilege[i][j], true);
							}
						}
					}
				}
			}
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public void addCaseProperties(String[] caseProperty, String[][] propMandatoryCheck) throws Exception {
		try {
			if (ValidationHelper.isNotEmpty(caseProperty)) {
				EntitySearchHelper entitySearch = new EntitySearchHelper();
				
				if (ValidationHelper.isNotEmpty(propMandatoryCheck)) {
					for (int i = 0; i < caseProperty.length; i++) {
						MouseHelper.click("Workstep_Properties_Add");
						GenericHelper.waitForLoadmask(detailScreenWaitSec);
						entitySearch.selectUsingGridFilterTextBox("Case Property Search", "CaseProperty_NameFilter", caseProperty[i], "Name");
						
						int row = GridHelper.getRowNumber("Workstep_Properties_Grid", caseProperty[i], "Property Name");
						
						if (propMandatoryCheck.length > i && ValidationHelper.isNotEmpty(propMandatoryCheck[i])) {
							for (int j = 0; j < propMandatoryCheck[i].length; j++)
								GridHelper.updateGridCheckBox("Workstep_Properties_Grid", row, propMandatoryCheck[i][j], true);
						}
					}
				}
			}
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}

	public void addEscalations(String[][] escalation, String[] notifyTeam, String[] notifyTeamOptions, String[] notifyUser,
			String[] notifyUserOptions) throws Exception {
		try {
			if (ValidationHelper.isNotEmpty(escalation)) {
				String gridId = "Workstep_Escalation_Grid";
				TestDataHelper testData = new TestDataHelper();
				String thirdLevelDelimiter = configProp.getThirdLevelDelimiter();
				
				for (int i = 0; i < escalation.length; i++) {
					int row = i + 1;
					GridHelper.updateGridTextBox(gridId, "Workstep_Escalation_EscalatesIn", row, "Escalates In", "Level", escalation[i][0]);
					
					GridHelper.updateGridComboBox(gridId, "Workstep_Escalation_Period", row, "Period", "Level", escalation[i][1]);
					
					GridHelper.updateGridComboBox(gridId, "Workstep_Escalation_AssignTeam", row, "Assign Team", "Level", escalation[i][2]);
					
					GridHelper.updateGridComboBox(gridId, "Workstep_Escalation_AssignUser", row, "Assign User", "Level", escalation[i][3]);
					
					GridHelper.updateGridComboBox(gridId, "Workstep_Escalation_Priority", row, "Priority", "Level", escalation[i][4]);
					
					if (ValidationHelper.isNotEmpty(notifyTeam) || ValidationHelper.isNotEmpty(notifyUser)) {
						String[] notifyTeams = testData.getStringValue(notifyTeam[i], secondLevelDelimiter);
						String[][] teamNotifyOptions = testData.getStringValue(notifyTeamOptions[i], secondLevelDelimiter, thirdLevelDelimiter);
						String[] notifyUsers = testData.getStringValue(notifyUser[i], secondLevelDelimiter);
						String[][] userNotifyOptions = testData.getStringValue(notifyUserOptions[i], secondLevelDelimiter, thirdLevelDelimiter);
						
						ButtonHelper.click("Workstep_Escalation_EditNotification");
						GenericHelper.waitForLoadmask(detailScreenWaitSec);
						assertTrue(LabelHelper.isTitlePresent("Configure Notifications"));
						
						ROCHelper rocHelper = new ROCHelper();
						rocHelper.addTeamNotification(notifyTeams, teamNotifyOptions);
						rocHelper.addUserNotification(notifyUsers, userNotifyOptions);
						
						ButtonHelper.click("OKButton");
						GenericHelper.waitForLoadmask(detailScreenWaitSec);
						if (LabelHelper.isTitlePresent("Configure Notifications")) {
							LinkHelper.click("roc-dialog-close");
							GenericHelper.waitForLoadmask(detailScreenWaitSec);
						}
					}
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

	public void addBusinessRule(String[] businessRule, boolean[] enableRule, String[] nextStep) throws Exception {
		try {
			if (ValidationHelper.isNotEmpty(businessRule)) {
				EntitySearchHelper entitySearch = new EntitySearchHelper();
				String gridId = "Workstep_Rules_Grid";
				
				for (int i = 0; i < businessRule.length; i++) {
					ButtonHelper.click("Workstep_Rules_Add");
					GenericHelper.waitForLoadmask(detailScreenWaitSec);
					assertTrue(LabelHelper.isTitlePresent("Business Rule Search"));
					
					entitySearch.selectUsingGridFilterTextBox("Business Rule Search", "BusinessRule_Name", businessRule[i], "Name");
					int row = GridHelper.getRowNumber(gridId, businessRule[i], "Rules");
					
					GridHelper.updateGridCheckBox(gridId, "Workstep_Rules_Enable", row, "Enable", enableRule[i]);
					
					GridHelper.updateGridComboBox(gridId, "Workstep_Rules_NextStep", row, "Next Step", "Rules", nextStep[i]);
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

	private void saveWorkStep(String name, String detailScreenTitle) throws Exception {
		try {
			ButtonHelper.click("SaveButton");
			GenericHelper.waitForLoadmask(detailScreenWaitSec);
			if (ButtonHelper.isPresent("YesButton")) {
				ButtonHelper.click("YesButton");
				GenericHelper.waitForLoadmask(detailScreenWaitSec);
			}
			
			assertTrue(LabelHelper.isTitleNotPresent(detailScreenTitle));
			ButtonHelper.click("SearchButton");
			GenericHelper.waitForLoadmask(searchScreenWaitSec);
			assertTrue(GridHelper.isValuePresent("SearchGrid", name, "Name"));
			Log4jHelper.logInfo("Workstep '" + name + "' created.");
		} catch (AssertionError e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
}