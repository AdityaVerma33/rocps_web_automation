package com.subex.automation.testcases.regressiontesting.usermanagement;

import org.testng.annotations.Test;

import com.subex.automation.helpers.application.LoginHelper;
import com.subex.automation.helpers.application.NavigationHelper;
import com.subex.automation.helpers.application.screens.RolesHelper;
import com.subex.automation.helpers.application.screens.UserHelper;
import com.subex.automation.helpers.component.GridHelper;
import com.subex.automation.helpers.util.FailureHelper;

public class testAdministratorUser extends testUserManagement {

	final String sheetName = "AdministratorUser";
	
	public testAdministratorUser() throws Exception {
		super();
	}
	
	private void verifyActions(int row) throws Exception {
		try {
			GridHelper.clickRow("SearchGrid", row, 1);
			
			NavigationHelper.navigateToAction("Common Tasks");
			assertTrue(NavigationHelper.isActionPresent("New"));
			assertTrue(NavigationHelper.isActionPresent("Edit"));
			assertTrue(NavigationHelper.isActionPresent("Delete"));
			assertFalse(NavigationHelper.isActionPresent("View"));
			NavigationHelper.navigateToAction("Common Tasks");
			
			NavigationHelper.navigateToAction("User Actions");
			assertTrue(NavigationHelper.isActionPresent("Disable User"));
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
	
	@Test(priority=1, description="Administrator user edit")
	public void testAdministratorUserEdit() throws Exception {
		try {
			// Modifying Administrator users
			String userName = "Administrator";
			String email = "administrator@subex.com";
			int row = navigateToUsers(userName);
			assertTrue(row > 0);
			verifyActions(row);
			
			verifyUserEdit(userName, userName, "Common", email);
			
			RolesHelper roles = new RolesHelper();
			roles.createRole(path, fileName, sheetName, "Roles", 1);
			
			UserHelper users = new UserHelper();
			users.createUser(path, fileName, sheetName, "Users", 1);
			
			LoginHelper login = new LoginHelper();
			login.login(path, fileName, sheetName, "Login1", 1);
			verifyUserView(userName, userName, "Common");
			
			login.login(path, fileName, sheetName, "Login2", 1);
			verifyUserEdit(userName, userName, "Common", email);
		} catch (AssertionError e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
}