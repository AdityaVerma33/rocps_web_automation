package com.subex.automation.testcases.regressiontesting.usermanagement;

import org.testng.annotations.Test;

import com.subex.automation.helpers.application.ControllerHelper;
import com.subex.automation.helpers.application.LoginHelper;
import com.subex.automation.helpers.application.NavigationHelper;
import com.subex.automation.helpers.application.screens.UserHelper;
import com.subex.automation.helpers.component.ButtonHelper;
import com.subex.automation.helpers.component.GenericHelper;
import com.subex.automation.helpers.component.GridCheckBoxHelper;
import com.subex.automation.helpers.component.GridHelper;
import com.subex.automation.helpers.component.LabelHelper;
import com.subex.automation.helpers.component.SearchGridHelper;
import com.subex.automation.helpers.component.TabHelper;
import com.subex.automation.helpers.report.Log4jHelper;
import com.subex.automation.helpers.scripts.ExecuteScript;
import com.subex.automation.helpers.util.FailureHelper;

public class testRootRole extends testUserManagement {

	final String sheetName = "RootRole";
	
	public testRootRole() throws Exception {
		super();
	}
	
	@Test(priority=1, description="Root role is read-only")
	public void testRootRoleReadOnly() throws Exception {
		try {
			// Ensure the Root role is read-only.
			Log4jHelper.logInfo("Running User Management Regression Test Cases");
			String roleName = "Root";
			
			verifyRoleView(roleName);
		} catch (AssertionError e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	private void checkRole(boolean shouldBePresent, boolean isClickable) throws Exception {
		try {
			TabHelper.gotoTab("Roles And Partitions");
			int row = GridHelper.getRowNumber("Users_RolePrivileges_Grid", "Root", "Role");
			
			if (shouldBePresent) {
				assertTrue(row > 0);
				
				if (isClickable) {
					if (GridCheckBoxHelper.isChecked("Users_RolePrivileges_Grid", row, "Common")) {
						GridCheckBoxHelper.uncheck("Users_RolePrivileges_Grid", row, "Common");
						assertFalse(GridCheckBoxHelper.isChecked("Users_RolePrivileges_Grid", row, "Common"));
					}
					else {
						GridCheckBoxHelper.check("Users_RolePrivileges_Grid", row, "Common");
						assertTrue(GridCheckBoxHelper.isChecked("Users_RolePrivileges_Grid", row, "Common"));
					}
				}
				else {
					if (GridCheckBoxHelper.isChecked("Users_RolePrivileges_Grid", row, "Common")) {
						GridCheckBoxHelper.uncheck("Users_RolePrivileges_Grid", row, "Common");
						assertTrue(GridCheckBoxHelper.isChecked("Users_RolePrivileges_Grid", row, "Common"));
					}
					else {
						GridCheckBoxHelper.check("Users_RolePrivileges_Grid", row, "Common");
						assertFalse(GridCheckBoxHelper.isChecked("Users_RolePrivileges_Grid", row, "Common"));
					}
				}
			}
			else
				assertFalse(row == 0);
			
			if (ButtonHelper.isPresent("CancelButton"))
				ButtonHelper.click("CancelButton");
			else
				ButtonHelper.click("CloseButton");
			GenericHelper.waitForLoadmask(detailScreenWaitSec);
			if (ButtonHelper.isPresent("YesButton")) {
				ButtonHelper.click("YesButton");
				GenericHelper.waitForLoadmask(detailScreenWaitSec);
			}
			assertTrue(LabelHelper.isTitlePresent("User Search"));
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	@Test(priority=2, description="Linking Root Role to Users")
	public void testRootRoleInUsers() throws Exception {
		try {
			// Ensure that the Root role can be linked with 'Root' user only.
			NavigationHelper.navigateToScreen( "Users", "User Search" );
			NavigationHelper.navigateToNew("Common", "Users_NewPassword");
			GenericHelper.waitForAJAXReady(detailScreenWaitSec);
			checkRole(true, false);
			
			UserHelper users = new UserHelper();
			String userName = "Administrator";
			int row = users.navigateToUsers(userName);
			NavigationHelper.navigateToEdit("SearchGrid", row, "Users_NewPassword");
			checkRole(true, false);
			
			userName = "Root";
			row = users.navigateToUsers(userName);
			NavigationHelper.navigateToEdit("SearchGrid", row, "Users_NewPassword");
			checkRole(true, false);
			
			users.createUser(path, fileName, sheetName, "Users", 1);
			LoginHelper login = new LoginHelper();
			login.login(path, fileName, sheetName, "UserLogin", 1);
			
			NavigationHelper.navigateToScreen( "Users", "User Search" );
			NavigationHelper.navigateToNew("Common", "Users_NewPassword");
			checkRole(false, false);
			
			userName = "Administrator";
			row = users.navigateToUsers(userName);
			GridHelper.clickRow("SearchGrid", row, 1);
			NavigationHelper.navigateToAction("Common Tasks");
			if (NavigationHelper.isActionPresent("Edit"))
				NavigationHelper.navigateToAction("Edit");
			else
				NavigationHelper.navigateToAction("View");
			
			GenericHelper.waitForLoadmask(detailScreenWaitSec);
			GenericHelper.waitForElement("Users_NewPassword", detailScreenWaitSec);
			checkRole(false, false);
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
	
	@Test(priority=3, description="Root role delete")
	public void testRootRoleDelete() throws Exception {
		String roleName = "Root";
		
		try {
			// Ensure that the Root role cannot be deleted from UI. If deleted from back end, will be un-deleted on Tomcat startup.
			NavigationHelper.navigateToScreen( "Roles", "Role Search" );
			ButtonHelper.click("ClearButton");
			GenericHelper.waitForLoadmask(searchScreenWaitSec);
			
			int row = SearchGridHelper.gridFilterSearchWithTextBox("Roles_RoleName", roleName, "Name");
			assertTrue(row > 0);
			GridHelper.clickRow("SearchGrid", row, 1);
			NavigationHelper.navigateToAction("Common Tasks");
			assertFalse(NavigationHelper.isActionPresent("Delete"));
			
			String query = "Update role_tbl set rol_delete_fl='Y' where rol_name like '" + roleName + "'";
			ExecuteScript.exeQuery(query);
			row = SearchGridHelper.gridFilterSearchWithTextBox("Roles_RoleName", roleName, "Name");
			assertTrue(row == 0, "Expected '0' rows but found '" + row + "'");
			
			ControllerHelper controller = new ControllerHelper();
			controller.restartTomcat();
			
			LoginHelper login = new LoginHelper();
			login.logout();
			login.loginWithConfigPropertyDetails();
			NavigationHelper.navigateToScreen( "Roles", "Role Search" );
			ButtonHelper.click("ClearButton");
			GenericHelper.waitForLoadmask(searchScreenWaitSec);
			
			row = SearchGridHelper.gridFilterSearchWithTextBox("Roles_RoleName", roleName, "Name");
			assertTrue(row > 0);
		} catch (AssertionError e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		} finally {
			String query = "Update role_tbl set rol_delete_fl='N' where rol_name like '" + roleName + "'";
			ExecuteScript.exeQuery(query);
		}
	}
}