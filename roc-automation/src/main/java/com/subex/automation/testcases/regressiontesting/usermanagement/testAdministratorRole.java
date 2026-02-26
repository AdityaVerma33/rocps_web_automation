package com.subex.automation.testcases.regressiontesting.usermanagement;

import org.testng.annotations.Test;

import com.subex.automation.helpers.application.LoginHelper;
import com.subex.automation.helpers.application.screens.RolesHelper;
import com.subex.automation.helpers.application.screens.UserHelper;
import com.subex.automation.helpers.util.FailureHelper;

public class testAdministratorRole extends testUserManagement {

	final String sheetName = "AdministratorRole";
	
	public testAdministratorRole() throws Exception {
		super();
	}
	
	@Test(priority=1, description="Administrator role edit")
	public void testAdministratorRoleEdit() throws Exception {
		try {
			// Ensure the Root role is read-only.
			LoginHelper login = new LoginHelper();
			login.loginWithConfigPropertyDetails();
			
			String roleName = "Administrator";
			verifyRoleEdit(roleName);
			
			RolesHelper roles = new RolesHelper();
			roles.createRole(path, fileName, sheetName, "Roles", 1);
			
			UserHelper users = new UserHelper();
			users.createUser(path, fileName, sheetName, "Users", 1);
			
			login.login(path, fileName, sheetName, "Login1", 1);
			verifyRoleEdit(roleName);
			
			login.login(path, fileName, sheetName, "Login2", 1);
			verifyRoleView(roleName);
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
	
	@Test(priority=2, description="Administrator role is editable by admin only")
	public void testAdminRoleEdit() throws Exception {
		try {
			// Administrators Admin1 , Admin2 roles with the privilege “Edit Administrator Role Properties” set
			String roleName = "Administrator";
			RolesHelper roles = new RolesHelper();
			roles.createRole(path, fileName, sheetName, "Roles", 1);
			
			UserHelper users = new UserHelper();
			users.createUser(path, fileName, sheetName, "Users", 1);
			
			LoginHelper login = new LoginHelper();
			login.login(path, fileName, sheetName, "Login1", 1);
			verifyRoleEdit(roleName);
			
			login.login(path, fileName, sheetName, "Login2", 1);
			verifyRoleView(roleName);
			
			login.login(path, fileName, sheetName, "Login3", 1);
			verifyRoleEdit(roleName);
		} catch (AssertionError e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
}