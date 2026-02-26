package com.subex.automation.helpers.application.screens;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.WebElement;

import com.subex.automation.helpers.application.NavigationHelper;
import com.subex.automation.helpers.component.ButtonHelper;
import com.subex.automation.helpers.component.ElementHelper;
import com.subex.automation.helpers.component.EntitySearchHelper;
import com.subex.automation.helpers.component.GenericHelper;
import com.subex.automation.helpers.component.GridCheckBoxHelper;
import com.subex.automation.helpers.component.GridHelper;
import com.subex.automation.helpers.component.LabelHelper;
import com.subex.automation.helpers.component.PropertyGridHelper;
import com.subex.automation.helpers.component.SearchGridHelper;
import com.subex.automation.helpers.component.TabHelper;
import com.subex.automation.helpers.component.TextBoxHelper;
import com.subex.automation.helpers.component.TreeHelper;
import com.subex.automation.helpers.data.StringHelper;
import com.subex.automation.helpers.data.ValidationHelper;
import com.subex.automation.helpers.file.ExcelReader;
import com.subex.automation.helpers.report.Log4jHelper;
import com.subex.automation.helpers.scripts.TestDataHelper;
import com.subex.automation.helpers.selenium.ROCAcceptanceTest;
import com.subex.automation.helpers.util.FailureHelper;

public class RolesHelper extends ROCAcceptanceTest {

	public void createRole(String path, String fileName, String sheetName, String testCaseName, int occurance) throws Exception
	{
		try {
			TestDataHelper testData = new TestDataHelper();
			ExcelReader excelReader = new ExcelReader();
			HashMap<String, ArrayList<String>> excelData = excelReader.readDataByColumn( path, fileName, sheetName, testCaseName, occurance );
	
			for(int i = 0; i < excelData.get("Role Name").size(); i++) 
			{
				String partition = excelData.get("Partition").get(i);
				String roleName = excelData.get("Role Name").get(i);
				String[] propertyValues = testData.getStringValue(excelData.get("Property Values").get(i), firstLevelDelimiter);
				
				boolean selectAllGroups = ValidationHelper.isTrue(excelData.get("Select All Groups").get(i));
				String[] groupName = testData.getStringValue(excelData.get("Group Name").get(i), firstLevelDelimiter);
				boolean[] selectAllScreens = testData.getBooleanValue(excelData.get("Select All Screens").get(i), firstLevelDelimiter);
				String[] screenName = testData.getStringValue(excelData.get("Screen Name").get(i), firstLevelDelimiter);
				boolean[] selectAllActions = testData.getBooleanValue(excelData.get("Select All Actions").get(i), firstLevelDelimiter);
				String[][] actionGroupName = testData.getStringValue(excelData.get("Action Group Name").get(i), firstLevelDelimiter, secondLevelDelimiter);
				String[][] actionName = testData.getStringValue(excelData.get("Action Name").get(i), firstLevelDelimiter, secondLevelDelimiter);
				
				boolean selectAllRefTbl = ValidationHelper.isTrue(excelData.get("Select All Reference Table").get(i));
				String[] refTableEntity = testData.getStringValue(excelData.get("Reference Table Entity").get(i), firstLevelDelimiter);
				String[][] refTablePrivilege = testData.getStringValue(excelData.get("Reference Table Privilege").get(i), firstLevelDelimiter, secondLevelDelimiter);
				
				String[] rocFlow = testData.getStringValue(excelData.get("ROC Flow").get(i), firstLevelDelimiter);
				String[] tableInstance = testData.getStringValue(excelData.get("Table Instance").get(i), firstLevelDelimiter);
				String[] tiQueryFilter = testData.getStringValue(excelData.get("Table Instance Query Filter").get(i), firstLevelDelimiter);
				String[] tableInstanceGroup = testData.getStringValue(excelData.get("Table Instance Group").get(i), firstLevelDelimiter);
				String[] tigQueryFilter = testData.getStringValue(excelData.get("Table Instance Group Query Filter").get(i), firstLevelDelimiter);
				
				createRole(partition, roleName, propertyValues, selectAllGroups, groupName, selectAllScreens, screenName, selectAllActions,
						actionGroupName, actionName, selectAllRefTbl, refTableEntity, refTablePrivilege, rocFlow, tableInstance, tiQueryFilter,
						tableInstanceGroup, tigQueryFilter);
			}
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public void createRole(String partition, String roleName, String[] propertyValues, boolean selectAllGroups, String[] groupName, boolean[] selectAllScreens,
			String[] screenName, boolean[] selectAllActions, String[][] actionGroupName, String[][] actionName, boolean selectAllRefTbl, String[] refTableEntity,
			String[][] refTablePrivilege, String[] rocFlow, String[] tableInstance, String[] tiQueryFilter, String[] tableInstanceGroup, String[] tigQueryFilter) throws Exception
	{
		try {
			int row = navigateToRoles(roleName, partition);
			String detailScreenTitle = NavigationHelper.getScreenTitle();
			GenericHelper.waitForLoadmask(detailScreenWaitSec);
			
			if (row == 0)
				TextBoxHelper.type("Roles_RoleName", roleName);
			
			if (ValidationHelper.isNotEmpty(propertyValues)) {
				PropertyGridHelper.clickCheckBox("Administrator Role", propertyValues[0]);
				PropertyGridHelper.clickCheckBox("Edit Administrator Role Properties", propertyValues[1]);
				PropertyGridHelper.clickCheckBox("Edit Administrator User Properties", propertyValues[2]);
				PropertyGridHelper.clickCheckBox("View data without masking", propertyValues[3]);
			}
			
			assignRolePrivileges(selectAllGroups, groupName, selectAllScreens, screenName, selectAllActions, actionGroupName, actionName);
			
			assignReferenceTables(selectAllRefTbl, refTableEntity, refTablePrivilege);
			
			assignROCFlows(rocFlow);
			
			assignDataFilters(tableInstance, tiQueryFilter, tableInstanceGroup, tigQueryFilter);

			saveRoles(roleName, detailScreenTitle);
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public void assignRolePrivileges(String[] groupName, boolean[] selectAllScreens, String[][] screenName) throws Exception
	{
		try {
			TabHelper.gotoTab("Role Privileges");
			
			if (ValidationHelper.isNotEmpty(groupName)) {
				for (int i = 0; i < groupName.length; i++) {
					int row = GridHelper.getRowNumber("Roles_Privileges_Group_Grid", groupName[i], "Screens");
					
					if (row > 0) {
						GridHelper.clickRow("Roles_Privileges_Group_Grid", row, "Screens");
						GenericHelper.waitForLoadmask(detailScreenWaitSec);
						
						if (selectAllScreens != null && selectAllScreens.length > i && selectAllScreens[i]) {
							ButtonHelper.click("Roles_Privileges_Screens_SelectAll");
							GenericHelper.waitForLoadmask(searchScreenWaitSec);
						}
						else {
							if (ValidationHelper.isNotEmpty(screenName) && ValidationHelper.isNotEmpty(screenName[i])) {
								for (int j = 0; j < screenName[i].length; j++) {
									int rowNum = GridHelper.getRowNumber("Roles_Privileges_Screens_Grid", screenName[i][j], "Screens");
									
									if (rowNum > 0) {
										GridHelper.clickRow("Roles_Privileges_Screens_Grid", rowNum, "Screens");
										GenericHelper.waitForLoadmask(detailScreenWaitSec);
										
										ButtonHelper.click("Roles_Privileges_Actions_SelectAll");
										GenericHelper.waitForLoadmask(searchScreenWaitSec);
									}
									else {
										FailureHelper.failTest("Screen name '" + screenName[i] + "' not found under Group '" + groupName[i] + "' in Role Privileges tab.");
									}
								}
							}
						}
					}
					else {
						FailureHelper.failTest("Group name '" + groupName[i] + "' not found in Role Privileges tab.");
					}
				}
			}
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public void assignRolePrivileges(boolean selectAllGroups, String[] groupName, boolean[] selectAllScreens, String[] screenName,
			boolean[] selectAllActions, String[][] actionGroupName, String[][] actionName) throws Exception
	{
		try {
			TabHelper.gotoTab("Role Privileges");
			
			if (selectAllGroups) {
				ButtonHelper.click("Roles_Privileges_Group_SelectAll");
				GenericHelper.waitForLoadmask(searchScreenWaitSec);
			}
			else {
				if (ValidationHelper.isNotEmpty(groupName)) {
					for (int i = 0; i < groupName.length; i++) {
						int row = GridHelper.getRowNumber("Roles_Privileges_Group_Grid", groupName[i], "Screens");
						
						if (row > 0) {
							GridHelper.clickRow("Roles_Privileges_Group_Grid", row, "Screens");
							GenericHelper.waitForLoadmask(detailScreenWaitSec);
							
							if (selectAllScreens != null && selectAllScreens.length > i && selectAllScreens[i]) {
								ButtonHelper.click("Roles_Privileges_Screens_SelectAll");
								GenericHelper.waitForLoadmask(searchScreenWaitSec);
							}
							else {
								if (ValidationHelper.isNotEmpty(screenName) && screenName.length > i && ValidationHelper.isNotEmpty(screenName[i])) {
									int rowNum = GridHelper.getRowNumber("Roles_Privileges_Screens_Grid", screenName[i], "Screens");
									
									if (rowNum > 0) {
										GridHelper.clickRow("Roles_Privileges_Screens_Grid", rowNum, "Screens");
										GenericHelper.waitForLoadmask(detailScreenWaitSec);
										
										if (selectAllActions != null && selectAllActions.length > i && selectAllActions[i]) {
											ButtonHelper.click("Roles_Privileges_Actions_SelectAll");
											GenericHelper.waitForLoadmask(searchScreenWaitSec);
										}
										else {
											if (ValidationHelper.isNotEmpty(actionName) && actionName.length > i && ValidationHelper.isNotEmpty(actionName[i])) {
												assignScreenActions(actionGroupName[i], actionName[i]);
											}
										}
									}
									else {
										FailureHelper.failTest("Screen name '" + screenName[i] + "' not found under Group '" + groupName[i] + "' in Role Privileges tab.");
									}
								}
							}
						}
						else {
							FailureHelper.failTest("Group name '" + groupName[i] + "' not found in Role Privileges tab.");
						}
					}
				}
			}
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public void assignScreenActions(String[] actionGroupName, String[] actionName) throws Exception
	{
		try {
			for (int i = 0; i < actionName.length; i++) {
				if (ValidationHelper.isNotEmpty(actionGroupName) && ValidationHelper.isNotEmpty(actionGroupName[i])) {
					TreeHelper.checkCheckBox("Roles_Privileges_Actions_Tree", actionGroupName[i], actionName[i]);
				}
				else {
					TreeHelper.checkCheckBox("Roles_Privileges_Actions_Tree", actionName[i]);
				}
			}
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public void assignReferenceTables(boolean selectAllRefTbl, String[] refTableEntity, String[][] refTablePrivilege) throws Exception
	{
		try {
			if (selectAllRefTbl || ValidationHelper.isNotEmpty(refTableEntity)) {
				TabHelper.gotoTab("Reference Tables");
				
				if (selectAllRefTbl) {
					ButtonHelper.click("Roles_ReferenceTable_SelectAll");
					GenericHelper.waitForLoadmask(searchScreenWaitSec);
				}
				else {
					if (ValidationHelper.isNotEmpty(refTableEntity)) {
						for (int i = 0; i < refTableEntity.length; i++) {
							int row = GridHelper.getRowNumber("Roles_ReferenceTable_Grid", refTableEntity[i], "Reference Table");
							
							if (row > 0) {
								if (ValidationHelper.isNotEmpty(refTablePrivilege) && ValidationHelper.isNotEmpty(refTablePrivilege[i])) {
									for (int j = 0; j < refTablePrivilege[i].length; j++) {
										String privilege = StringHelper.convertToCamelCase(refTablePrivilege[i][j]);
										int col = GridHelper.getColumnNumber("Roles_ReferenceTable_Grid", privilege);
										
										if (col > 0) {
											GridHelper.updateGridCheckBox("Roles_ReferenceTable_Grid", row, privilege, true);
										}
										else {
											FailureHelper.failTest("Privilege '" + privilege + "' not found in Reference Table tab.");
										}
									}
								}
							}
							else {
								FailureHelper.failTest("Reference Table '" + refTableEntity[i] + "' not found in Reference Table tab.");
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
	
	public void assignROCFlows(String[] rocFlow) throws Exception {
		try {
			if (ValidationHelper.isNotEmpty(rocFlow)) {
				TabHelper.gotoTab("ROC Flows");
				Thread.sleep(2000);
				
				for (int i = 0; i < rocFlow.length; i++)
					GridCheckBoxHelper.check("Roles_ROCFlows_Grid", "Roles_ROCFlows_View", rocFlow[i], "ROC Flows", "View");
			}
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public void assignDataFilters(String[] tableInstance, String[] tiQueryFilter, String[] tableInstanceGroup, String[] tigQueryFilter) throws Exception {
		try {
			if (ValidationHelper.isNotEmpty(tableInstance) || ValidationHelper.isNotEmpty(tableInstanceGroup)) {
				TabHelper.gotoTab("Data Filters");
				int rows = GridHelper.getRowCount("Roles_DataFilter_Grid");
				
				if (ValidationHelper.isNotEmpty(tableInstance) && ValidationHelper.isNotEmpty(tiQueryFilter)) {
					EntitySearchHelper entitySearch = new EntitySearchHelper();
					
					for (int i = 0; i < tableInstance.length; i++) {
						ButtonHelper.click("Roles_DataFilter_AddTableInstance");
						GenericHelper.waitForLoadmask(searchScreenWaitSec);
						entitySearch.selectUsingGridFilterTextBox("Query Filter Search", "QueryFilter_Name", tableInstance[i], "Name");
						rows++;
						
						GridHelper.updateGridEntityCombo("Roles_DataFilter_Grid", "", rows, "Query Filter Model", tiQueryFilter[i]);
					}
				}
				
				if (ValidationHelper.isNotEmpty(tableInstanceGroup) && ValidationHelper.isNotEmpty(tigQueryFilter)) {
					EntitySearchHelper entitySearch = new EntitySearchHelper();
					
					for (int i = 0; i < tableInstanceGroup.length; i++) {
						ButtonHelper.click("Roles_DataFilter_AddTableInstanceGroup");
						GenericHelper.waitForLoadmask(searchScreenWaitSec);
						entitySearch.selectUsingGridFilterTextBox("Query Filter Search", "QueryFilter_Name", tableInstanceGroup[i], "Name");
						rows++;
						
						GridHelper.updateGridEntityCombo("Roles_DataFilter_Grid", "", rows, "Query Filter Model", tigQueryFilter[i]);
					}
				}
			}
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public int navigateToRoles(String roleName, String partition) throws Exception {
		try {
			NavigationHelper.navigateToScreen( "Roles", "Role Search" );
			int row = SearchGridHelper.gridFilterSearchWithTextBox("Roles_RoleName", roleName, "Name");
			NavigationHelper.navigateToNewOrEdit(row, partition, "Role", "Roles_RoleName");
			
			return row;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public void saveRoles(String roleName, String detailScreenTitle) throws Exception {
		try {
			ButtonHelper.click("SaveButton");
			GenericHelper.waitForLoadmask(detailScreenWaitSec);
			assertTrue(LabelHelper.isTitleNotPresent(detailScreenTitle), "Role save did not happen.");
			
			ButtonHelper.click("SearchButton");
			GenericHelper.waitForLoadmask(searchScreenWaitSec);
			assertTrue(GridHelper.isValuePresent("SearchGrid", roleName, "Name"), "Value '" + roleName + "' is not found in grid.");
			Log4jHelper.logInfo("Role '" + roleName + "' created/updated.");
		} catch (AssertionError e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public String[][] getScreenActions() throws Exception {
		try {
			List<WebElement> actionGroupElements = ElementHelper.getElements("Roles_Privileges_ActionGroups");
			String[][] actions = new String[actionGroupElements.size()-1][];
			
			for (int i = 0; i < actionGroupElements.size()-1; i++) {
				WebElement actionGroup = actionGroupElements.get(i+1);
				String values = ElementHelper.getText(actionGroup);
				actions[i] = values.split("\n");
			}
			
			return actions;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
}