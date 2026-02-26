package com.subex.automation.testcases.regressiontesting.usermanagement;

import java.util.ArrayList;
import java.util.HashMap;

import org.testng.annotations.Test;

import com.subex.automation.helpers.application.LoginHelper;
import com.subex.automation.helpers.application.NavigationHelper;
import com.subex.automation.helpers.application.screens.RolesHelper;
import com.subex.automation.helpers.application.screens.UserHelper;
import com.subex.automation.helpers.component.ButtonHelper;
import com.subex.automation.helpers.component.GenericHelper;
import com.subex.automation.helpers.component.GridHelper;
import com.subex.automation.helpers.component.LabelHelper;
import com.subex.automation.helpers.component.TextBoxHelper;
import com.subex.automation.helpers.file.ExcelReader;
import com.subex.automation.helpers.util.FailureHelper;

public class testUserDelete extends testUserManagement {

	final String sheetName = "DeleteUser";
	
	public testUserDelete() throws Exception {
		super();
	}
	
	@Test(priority=1, description="Delete User", groups = { "deleteUser" })
	public void testDeleteUser() throws Exception {
		try {
			// Delete User
			RolesHelper roles = new RolesHelper();
			roles.createRole(path, fileName, sheetName, "Roles", 1);
			
			UserHelper users = new UserHelper();
			users.createUser(path, fileName, sheetName, "User", 1);
			
			ExcelReader excelReader = new ExcelReader();
			HashMap<String, ArrayList<String>> excelData = excelReader.readDataByColumn( path, fileName, sheetName, "User", 1 );
			String userName = excelData.get("Username").get(0);
			NavigationHelper.delete("SearchGrid", userName, "Name");
			
			verifyUserLogin(sheetName, "UserLogin1", 1, 0);
			assertTrue(GridHelper.isValuePresent("SearchGrid", userName, "User"));
			
			LoginHelper login = new LoginHelper();
			login.login(path, fileName, sheetName, "Login", 1);
			assertTrue(TextBoxHelper.isPresent("Login_Username_TextBox"));
			assertTrue(LabelHelper.isTextPresent("Login failed for username delete_user"));
			
			login.loginWithConfigPropertyDetails();
			verifyUserLogin(sheetName, "UserLogin2", 1, 0);
			assertTrue(GridHelper.isValuePresent("SearchGrid", userName, "User"));
		} catch (AssertionError e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	@Test(priority=2, description="Reuse deleted user username", dependsOnMethods = { "testDeleteUser" }, groups = { "deleteUser" })
	public void testDeletedUserReuse() throws Exception {
		try {
			// Deleted Username for user creation
			ExcelReader excelReader = new ExcelReader();
			HashMap<String, ArrayList<String>> excelData = excelReader.readDataByColumn( path, fileName, sheetName, "User", 1 );
			String partition = excelData.get("Partition").get(0);
			String userName = excelData.get("Username").get(0);
			
			UserHelper users = new UserHelper();
			int row = users.navigateToUsers(userName);
			
			if (row == 0) {
				NavigationHelper.navigateToNew(partition, "Users_Username");
				String detailScreenTitle = NavigationHelper.getScreenTitle();
				
				users.updateUser(path, fileName, sheetName, "User", 1);
				
				ButtonHelper.click("SaveButton");
				GenericHelper.waitForLoadmask(detailScreenWaitSec);
				assertTrue(LabelHelper.isTitlePresent(detailScreenTitle));
				assertTrue(TextBoxHelper.isMandatory("Users_Username"));
			}
		} catch (AssertionError e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
}