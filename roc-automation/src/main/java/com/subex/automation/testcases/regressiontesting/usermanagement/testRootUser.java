package com.subex.automation.testcases.regressiontesting.usermanagement;

import org.testng.annotations.Test;

import com.subex.automation.helpers.application.ControllerHelper;
import com.subex.automation.helpers.application.LoginHelper;
import com.subex.automation.helpers.application.NavigationHelper;
import com.subex.automation.helpers.application.screens.UserHelper;
import com.subex.automation.helpers.component.ButtonHelper;
import com.subex.automation.helpers.component.ComboBoxHelper;
import com.subex.automation.helpers.component.GenericHelper;
import com.subex.automation.helpers.component.GridCheckBoxHelper;
import com.subex.automation.helpers.component.GridHelper;
import com.subex.automation.helpers.component.LabelHelper;
import com.subex.automation.helpers.component.SearchGridHelper;
import com.subex.automation.helpers.component.TabHelper;
import com.subex.automation.helpers.component.TextBoxHelper;
import com.subex.automation.helpers.scripts.ExecuteScript;
import com.subex.automation.helpers.util.FailureHelper;

public class testRootUser extends testUserManagement {

	final String sheetName = "RootRole";
	
	public testRootUser() throws Exception {
		super();
	}
	
	private void checkUserDetails() throws Exception {
		try {
			assertTrue(TextBoxHelper.isEnabled("Users_FirstName"));
			assertTrue(TextBoxHelper.isEnabled("Users_LastName"));
			assertTrue(TextBoxHelper.isEnabled("Users_PhoneNo"));
			assertTrue(TextBoxHelper.isEnabled("Users_EmailAddress"));
			assertTrue(TextBoxHelper.isEnabled("Users_Description"));
			assertTrue(TextBoxHelper.isEnabled("Users_AllowedMachines"));
			assertTrue(TextBoxHelper.isDisabled("Users_AccountExpiry"));
			assertTrue(TextBoxHelper.isEnabled("Users_ContactAddress"));
			assertTrue(ComboBoxHelper.isEnabled("Users_DiceGroup"));
			assertTrue(ComboBoxHelper.isDisabled("Users_UserPasswordValidation"));
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	private void checkRole(boolean shouldBePresent) throws Exception {
		try {
			TabHelper.gotoTab("Roles And Partitions");
			int row = GridHelper.getRowNumber("Users_RolePrivileges_Grid", "Root", "Role");
			
			if (shouldBePresent) {
				assertTrue(GridCheckBoxHelper.isChecked("Users_RolePrivileges_Grid", row, "Common"));
				GridCheckBoxHelper.uncheck("Users_RolePrivileges_Grid", row, "Common");
				assertTrue(GridCheckBoxHelper.isChecked("Users_RolePrivileges_Grid", row, "Common"));
			}
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	@Test(priority=1, description="Root user edit")
	public void testRootUserEdit() throws Exception {
		try {
			// Ensure that only user information fields like First Name, Last Name, Email Address, etc. are editable for Root user 
			String userName = "Root";
			int row = navigateToUsers(userName);
			assertTrue(row > 0);
			
			GridHelper.clickRow("SearchGrid", row, 1);
			NavigationHelper.navigateToAction("Common Tasks");
			assertTrue(NavigationHelper.isActionPresent("Edit"));
			
			NavigationHelper.navigateToAction("Edit");
			GenericHelper.waitForLoadmask(detailScreenWaitSec);
			GenericHelper.waitForElement("Users_NewPassword", detailScreenWaitSec);
			
			assertTrue(LabelHelper.isTitlePresent("Edit User"));
			assertTrue(TextBoxHelper.isDisabled("Users_Username"));
			assertTrue(TextBoxHelper.isDisabled("Users_NewPassword"));
			assertTrue(TextBoxHelper.isDisabled("Users_UserDisplayName"));
			
			checkUserDetails();
			
			checkRole(true);
			
			assertTrue(ButtonHelper.isEnabled("SaveButton"));
			assertTrue(ButtonHelper.isPresent("CancelButton"));
			assertFalse(ButtonHelper.isPresent("CloseButton"));
			
			ButtonHelper.click("CancelButton");
			GenericHelper.waitForLoadmask(detailScreenWaitSec);
			if (ButtonHelper.isPresent("YesButton")) {
				ButtonHelper.click("YesButton");
				GenericHelper.waitForLoadmask(detailScreenWaitSec);
			}
			assertTrue(LabelHelper.isTitlePresent("User Search"));
			
			UserHelper users = new UserHelper();
			users.createUser(path, fileName, sheetName, "Users", 1);
			LoginHelper login = new LoginHelper();
			login.login(path, fileName, sheetName, "UserLogin", 1);
			
			NavigationHelper.navigateToScreen( "Users", "User Search" );
			row = SearchGridHelper.searchWithTextBox("Users_Username", userName, "Name");
			assertTrue(row == 0);
		} catch (AssertionError e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		} finally {
			LoginHelper login = new LoginHelper();
			login.loginWithConfigPropertyDetails();
		}
	}
	
	@Test(priority=2, description="Root role delete")
	public void testRootUserDelete() throws Exception {
		String userName = "Root";
		
		try {
			// Ensure that the Root user cannot be deleted from UI. If deleted from back end, will be un-deleted on Tomcat startup.
			int row = navigateToUsers(userName);
			assertTrue(row > 0);
			
			GridHelper.clickRow("SearchGrid", row, 1);
			NavigationHelper.navigateToAction("Common Tasks");
			assertFalse(NavigationHelper.isActionPresent("Delete"));
			
			String query = "Update user_tbl set usr_delete_fl='Y' where usr_name like '" + userName + "'";
			ExecuteScript.exeQuery(query);
			row = SearchGridHelper.searchWithTextBox("Users_Username", userName, "Name");
			assertTrue(row == 0);
			
			ControllerHelper controller = new ControllerHelper();
			controller.restartTomcat();
			
			LoginHelper login = new LoginHelper();
			login.logout();
			login.loginWithConfigPropertyDetails();
			NavigationHelper.navigateToScreen( "Users", "User Search" );
			ButtonHelper.click("ClearButton");
			GenericHelper.waitForLoadmask(searchScreenWaitSec);
			
			row = SearchGridHelper.searchWithTextBox("Users_Username", userName, "Name");
			assertTrue(row > 0);
		} catch (AssertionError e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		} finally {
			String query = "Update user_tbl set usr_delete_fl='N' where usr_name like '" + userName + "'";
			ExecuteScript.exeQuery(query);
		}
	}
	
	private void verifyEnableDisableAction(int row) throws Exception {
		try {
			GridHelper.clickRow("SearchGrid", row, 1);
			
			assertTrue(NavigationHelper.isActionPresent("User Actions"));
			NavigationHelper.navigateToAction("User Actions");
			assertFalse(NavigationHelper.isActionPresent("Disable User"));
			assertTrue(NavigationHelper.isActionPresent("Change Password"));
			NavigationHelper.navigateToAction("User Actions");
		} catch (AssertionError e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	@Test(priority=3, description="Root user edit")
	public void testRootEnableDisable() throws Exception {
		try {
			// Ensure that only user information fields like First Name, Last Name, Email Address, etc. are editable for Root user 
			String userName = "Root";
			int row = navigateToUsers(userName);
			assertTrue(row > 0);
			
			verifyEnableDisableAction(row);
			
			UserHelper users = new UserHelper();
			users.createUser(path, fileName, sheetName, "Users", 1);
			
			LoginHelper login = new LoginHelper();
			login.login(path, fileName, sheetName, "UserLogin", 1);
			
			row = navigateToUsers(userName);
			assertTrue(row == 0);
		} catch (AssertionError e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
}