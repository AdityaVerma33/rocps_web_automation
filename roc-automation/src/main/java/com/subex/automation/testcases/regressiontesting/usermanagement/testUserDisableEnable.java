package com.subex.automation.testcases.regressiontesting.usermanagement;

import org.testng.annotations.Test;

import com.subex.automation.helpers.application.LoginHelper;
import com.subex.automation.helpers.application.NavigationHelper;
import com.subex.automation.helpers.application.screens.RolesHelper;
import com.subex.automation.helpers.application.screens.UserHelper;
import com.subex.automation.helpers.component.LabelHelper;
import com.subex.automation.helpers.component.TextBoxHelper;
import com.subex.automation.helpers.util.FailureHelper;

public class testUserDisableEnable extends testUserManagement {

	final String sheetName = "DisableUser";
	
	public testUserDisableEnable() throws Exception {
		super();
	}
	
	@Test(priority=1, description="Disable User", groups = { "disableEnable" })
	public void testDisableUser() throws Exception {
		try {
			// Disable User
			RolesHelper roles = new RolesHelper();
			roles.createRole(path, fileName, sheetName, "Roles", 1);
			
			UserHelper users = new UserHelper();
			users.createUser(path, fileName, sheetName, "User1", 1);
			
			String[] expectedGroups = {"Admin", "ROC View"};
			String[][] expectedScreens = {{"Table Definitions", "Table Instances", "Table Instance Groups", "Ageing"},
										{"Visualizer"}};
			LoginHelper login = new LoginHelper();
			login.login(path, fileName, sheetName, "Login1", 1);
			NavigationHelper.checkScreens(expectedGroups, expectedScreens);
			
			login.loginWithConfigPropertyDetails();
			users.disableUser(path, fileName, sheetName, "User1", 1);
			
			verifyUserLogin(sheetName, "UserLogin1", 1, 0);
			
			login.login(path, fileName, sheetName, "Login1", 1);
			assertTrue(TextBoxHelper.isPresent("Login_Username_TextBox"));
			assertTrue(LabelHelper.isTextPresent("User disabled. Contact system administrator"));
			
			login.loginWithConfigPropertyDetails();
			verifyUserLogin(sheetName, "UserLogin2", 1, 0);
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
	
	@Test(priority=2, description="Edit of disabled user", dependsOnMethods = { "testDisableUser" }, groups = { "disableEnable" })
	public void testDisabledUserEdit() throws Exception {
		try {
			// Edit Disabled User
			UserHelper users = new UserHelper();
			users.createUser(path, fileName, sheetName, "User1", 1);
			
			LoginHelper login = new LoginHelper();
			login.login(path, fileName, sheetName, "Login1", 1);
			assertTrue(TextBoxHelper.isPresent("Login_Username_TextBox"));
			assertTrue(LabelHelper.isTextPresent("User disabled. Contact system administrator"));
			
			login.loginWithConfigPropertyDetails();
			verifyUserLogin(sheetName, "UserLogin2", 1, 0);
		} catch (AssertionError e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	@Test(priority=3, description="Enabled User", dependsOnMethods = { "testDisableUser" }, groups = { "disableEnable" })
	public void testEnableUser() throws Exception {
		try {
			// Enable User
			LoginHelper login = new LoginHelper();
			login.loginWithConfigPropertyDetails();
			
			UserHelper users = new UserHelper();
			users.enableUser(path, fileName, sheetName, "EnableUser", 1);
			
			verifyUserLogin(sheetName, "UserLogin3", 1, 0);
			
			String[] expectedGroups = {"Admin", "ROC View"};
			String[][] expectedScreens = {{"Table Definitions", "Table Instances", "Table Instance Groups", "Ageing"},
										{"Visualizer"}};
			login.login(path, fileName, sheetName, "Login2", 1);
			NavigationHelper.checkScreens(expectedGroups, expectedScreens);
			
			login.loginWithConfigPropertyDetails();
			verifyUserLogin(sheetName, "UserLogin4", 1, 0);
		} catch (AssertionError e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
}