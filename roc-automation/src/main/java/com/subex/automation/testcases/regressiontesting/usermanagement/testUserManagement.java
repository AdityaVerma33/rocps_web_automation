package com.subex.automation.testcases.regressiontesting.usermanagement;

import java.util.ArrayList;
import java.util.HashMap;

import com.subex.automation.helpers.application.NavigationHelper;
import com.subex.automation.helpers.application.screens.UserLoginHelper;
import com.subex.automation.helpers.component.ButtonHelper;
import com.subex.automation.helpers.component.CheckBoxHelper;
import com.subex.automation.helpers.component.ComboBoxHelper;
import com.subex.automation.helpers.component.GenericHelper;
import com.subex.automation.helpers.component.GridCheckBoxHelper;
import com.subex.automation.helpers.component.GridHelper;
import com.subex.automation.helpers.component.LabelHelper;
import com.subex.automation.helpers.component.SearchGridHelper;
import com.subex.automation.helpers.component.TabHelper;
import com.subex.automation.helpers.component.TextBoxHelper;
import com.subex.automation.helpers.data.ValidationHelper;
import com.subex.automation.helpers.file.ExcelReader;
import com.subex.automation.helpers.selenium.ROCAcceptanceTest;
import com.subex.automation.helpers.util.FailureHelper;

public class testUserManagement extends ROCAcceptanceTest {
	
	String path = null;
	final String fileName = "UserManagement_TestData.xlsx";
	
	public testUserManagement() throws Exception {
		try {
			path = automationPath + "\\src\\main\\resources\\Regression\\";
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	protected int navigateToRoles(String roleName) throws Exception {
		try {
			NavigationHelper.navigateToScreen( "Roles", "Role Search" );
			ButtonHelper.click("ClearButton");
			GenericHelper.waitForLoadmask(searchScreenWaitSec);
			
			int row = SearchGridHelper.gridFilterSearchWithTextBox("Roles_RoleName", roleName, "Name");
			return row;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	protected void verifyRoleEdit(String roleName) throws Exception {
		try {
			int row = navigateToRoles(roleName);
			assertTrue(row > 0);
			GridHelper.clickRow("SearchGrid", row, 1);
			
			NavigationHelper.navigateToAction("Common Tasks");
			assertTrue(NavigationHelper.isActionPresent("Edit"));
			assertFalse(NavigationHelper.isActionPresent("View"));
			
			NavigationHelper.navigateToAction("Edit");
			GenericHelper.waitForLoadmask(detailScreenWaitSec);
			GenericHelper.waitForElement("Roles_RoleName", detailScreenWaitSec);
			
			assertTrue(LabelHelper.isTitlePresent("Edit Role"));
			assertTrue(TextBoxHelper.isEnabled("Roles_RoleName"));
			assertTrue(CheckBoxHelper.isEnabled("Roles_ShowAssignedPrivileges"));
			assertTrue(CheckBoxHelper.isEnabled("Roles_ShowUnassignedPrivileges"));
			assertTrue(ButtonHelper.isEnabled("SaveButton"));
			assertTrue(ButtonHelper.isPresent("CancelButton"));
			assertFalse(ButtonHelper.isPresent("CloseButton"));
			assertTrue(ButtonHelper.isEnabled("CancelButton"));
			ButtonHelper.click("SaveButton");
			GenericHelper.waitForLoadmask(detailScreenWaitSec);
			assertTrue(LabelHelper.isTitlePresent("Role Search"));
		} catch (AssertionError e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	protected void verifyRoleView(String roleName) throws Exception {
		try {
			int row = navigateToRoles(roleName);
			assertTrue(row > 0);
			GridHelper.clickRow("SearchGrid", row, 1);
			
			NavigationHelper.navigateToAction("Common Tasks");
			assertTrue(NavigationHelper.isActionPresent("View"));
			assertFalse(NavigationHelper.isActionPresent("Edit"));
			
			NavigationHelper.navigateToAction("View");
			GenericHelper.waitForLoadmask(detailScreenWaitSec);
			GenericHelper.waitForElement("Roles_RoleName", detailScreenWaitSec);
			
			assertTrue(LabelHelper.isTitlePresent("View Role"));
			assertTrue(TextBoxHelper.isDisabled("Roles_RoleName"));
			assertTrue(CheckBoxHelper.isDisabled("Roles_ShowAssignedPrivileges"));
			assertTrue(CheckBoxHelper.isDisabled("Roles_ShowUnassignedPrivileges"));
			assertTrue(ButtonHelper.isDisabled("SaveButton"));
			assertFalse(ButtonHelper.isPresent("CancelButton"));
			assertTrue(ButtonHelper.isPresent("CloseButton"));
			assertTrue(ButtonHelper.isEnabled("CloseButton"));
			ButtonHelper.click("CloseButton");
			GenericHelper.waitForLoadmask(detailScreenWaitSec);
			assertTrue(LabelHelper.isTitlePresent("Role Search"));
		} catch (AssertionError e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	protected int navigateToUsers(String userName) throws Exception {
		try {
			NavigationHelper.navigateToScreen( "Users", "User Search" );
			ButtonHelper.click("ClearButton");
			GenericHelper.waitForLoadmask(searchScreenWaitSec);
			
			int row = SearchGridHelper.searchWithTextBox("Users_Username", userName, "Name");
			return row;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	protected void verifyUserEdit(String userName, String roleName, String partition, String email) throws Exception {
		try {
			int row = navigateToUsers(userName);
			assertTrue(row > 0);
			GridHelper.clickRow("SearchGrid", row, 1);
			
			NavigationHelper.navigateToAction("Common Tasks");
			assertTrue(NavigationHelper.isActionPresent("Edit"));
			assertFalse(NavigationHelper.isActionPresent("View"));
			
			NavigationHelper.navigateToAction("Edit");
			GenericHelper.waitForLoadmask(detailScreenWaitSec);
			GenericHelper.waitForElement("Users_NewPassword", detailScreenWaitSec);
			
			assertTrue(LabelHelper.isTitlePresent("Edit User"));
			assertTrue(TextBoxHelper.isDisabled("Users_Username"));
			assertTrue(TextBoxHelper.isDisabled("Users_NewPassword"));
			assertTrue(TextBoxHelper.isDisabled("Users_UserDisplayName"));
			
			assertTrue(TextBoxHelper.isEnabled("Users_FirstName"));
			assertTrue(TextBoxHelper.isEnabled("Users_LastName"));
			assertTrue(TextBoxHelper.isEnabled("Users_PhoneNo"));
			assertTrue(TextBoxHelper.isEnabled("Users_EmailAddress"));
			assertTrue(TextBoxHelper.isEnabled("Users_Description"));
			assertTrue(TextBoxHelper.isEnabled("Users_AllowedMachines"));
			assertTrue(TextBoxHelper.isEnabled("Users_AccountExpiry"));
			assertTrue(TextBoxHelper.isEnabled("Users_ContactAddress"));
			assertTrue(ComboBoxHelper.isEnabled("Users_DiceGroup"));
			assertTrue(ComboBoxHelper.isDisabled("Users_UserPasswordValidation"));
			
			TabHelper.gotoTab("Roles And Partitions");
			row = GridHelper.getRowNumber("Users_RolePrivileges_Grid", roleName, "Role");
			
			if (GridCheckBoxHelper.isChecked("Users_RolePrivileges_Grid", row, partition)) {
				GridCheckBoxHelper.uncheck("Users_RolePrivileges_Grid", row, partition);
				assertFalse(GridCheckBoxHelper.isChecked("Users_RolePrivileges_Grid", row, partition));
				GridCheckBoxHelper.check("Users_RolePrivileges_Grid", row, partition);
			}
			else {
				GridCheckBoxHelper.check("Users_RolePrivileges_Grid", row, partition);
				assertTrue(GridCheckBoxHelper.isChecked("Users_RolePrivileges_Grid", row, partition));
				GridCheckBoxHelper.uncheck("Users_RolePrivileges_Grid", row, partition);
			}
			
			assertTrue(ButtonHelper.isEnabled("SaveButton"));
			assertTrue(ButtonHelper.isPresent("CancelButton"));
			assertFalse(ButtonHelper.isPresent("CloseButton"));
			assertTrue(ButtonHelper.isEnabled("CancelButton"));
			
			if (ValidationHelper.isNotEmpty(email))
				TextBoxHelper.type("Users_EmailAddress", email);
			ButtonHelper.click("SaveButton");
			GenericHelper.waitForLoadmask(detailScreenWaitSec);
			assertTrue(LabelHelper.isTitlePresent("User Search"));
		} catch (AssertionError e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	protected void verifyUserView(String userName, String roleName, String partition) throws Exception {
		try {
			int row = navigateToUsers(userName);
			assertTrue(row > 0);
			GridHelper.clickRow("SearchGrid", row, 1);
			
			NavigationHelper.navigateToAction("Common Tasks");
			assertFalse(NavigationHelper.isActionPresent("Edit"));
			assertTrue(NavigationHelper.isActionPresent("View"));
			
			NavigationHelper.navigateToAction("View");
			GenericHelper.waitForLoadmask(detailScreenWaitSec);
			GenericHelper.waitForElement("Users_NewPassword", detailScreenWaitSec);
			
			assertTrue(LabelHelper.isTitlePresent("View User"));
			assertTrue(TextBoxHelper.isDisabled("Users_Username"));
			assertTrue(TextBoxHelper.isDisabled("Users_NewPassword"));
			assertTrue(TextBoxHelper.isDisabled("Users_UserDisplayName"));
			
			assertTrue(TextBoxHelper.isDisabled("Users_FirstName"));
			assertTrue(TextBoxHelper.isDisabled("Users_LastName"));
			assertTrue(TextBoxHelper.isDisabled("Users_PhoneNo"));
			assertTrue(TextBoxHelper.isDisabled("Users_EmailAddress"));
			assertTrue(TextBoxHelper.isDisabled("Users_Description"));
			assertTrue(TextBoxHelper.isDisabled("Users_AllowedMachines"));
			assertTrue(TextBoxHelper.isDisabled("Users_AccountExpiry"));
			assertTrue(TextBoxHelper.isDisabled("Users_ContactAddress"));
			assertTrue(ComboBoxHelper.isDisabled("Users_DiceGroup"));
			assertTrue(ComboBoxHelper.isDisabled("Users_UserPasswordValidation"));
			
			TabHelper.gotoTab("Roles And Partitions");
			row = GridHelper.getRowNumber("Users_RolePrivileges_Grid", roleName, "Role");
			
			if (GridCheckBoxHelper.isChecked("Users_RolePrivileges_Grid", row, partition)) {
				GridCheckBoxHelper.uncheck("Users_RolePrivileges_Grid", row, partition);
				assertTrue(GridCheckBoxHelper.isChecked("Users_RolePrivileges_Grid", row, partition));
			}
			else {
				GridCheckBoxHelper.check("Users_RolePrivileges_Grid", row, partition);
				assertFalse(GridCheckBoxHelper.isChecked("Users_RolePrivileges_Grid", row, partition));
			}
			
			assertTrue(ButtonHelper.isDisabled("SaveButton"));
			assertFalse(ButtonHelper.isPresent("CancelButton"));
			assertTrue(ButtonHelper.isPresent("CloseButton"));
			assertTrue(ButtonHelper.isEnabled("CloseButton"));
			ButtonHelper.click("CloseButton");
			GenericHelper.waitForLoadmask(detailScreenWaitSec);
			assertTrue(LabelHelper.isTitlePresent("User Search"));
		} catch (AssertionError e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	protected void verifyFileSource(String sheetName, String[] fsPartition) throws Exception {
		try {
			ExcelReader excelReader = new ExcelReader();
			HashMap<String, ArrayList<String>> excelData = excelReader.readDataByColumn( path, fileName, sheetName, "FileSource", 1 );
			NavigationHelper.navigateToScreen("File Sources", "File Source Search");
			
			for(int i = 0; i < excelData.get("Name").size(); i++)
			{
				String partition = excelData.get("Partition").get(i);
				String name = excelData.get("Name").get(i);
				boolean isPartitionPresent = false;
				
				for (int j = 0; j < fsPartition.length; j++) {
					if (partition.equals(fsPartition[j])) {
						isPartitionPresent = true;
						break;
					}
				}
				
				int row = SearchGridHelper.gridFilterSearchWithTextBox("FileSource_Name", name, "Name");
				if (isPartitionPresent)
					assertTrue(row >= 1);
				else
					assertTrue(row == 0);
			}
		} catch (AssertionError e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	protected void verifyUserLogin(String sheetName, String testCaseName, int occurance, int expectedRows) throws Exception {
		try {
			UserLoginHelper userLogin = new UserLoginHelper();
			userLogin.applyFilter(path, fileName, sheetName, testCaseName, occurance);
			int rows = GridHelper.getRowCount("SearchGrid");
			
			if (expectedRows == 0)
				assertTrue(rows > expectedRows, "Expected atleast 1 row in search result. But found '" + rows + "' rows.");
			else
				assertTrue(rows >= expectedRows, "Expected '" + expectedRows + "' rows in search result. But found '" + rows + "' rows.");
		} catch (AssertionError e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
		
	}
}