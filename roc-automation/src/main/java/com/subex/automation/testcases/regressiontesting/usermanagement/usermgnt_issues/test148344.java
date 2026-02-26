package com.subex.automation.testcases.regressiontesting.usermanagement.usermgnt_issues;

import java.util.ArrayList;
import java.util.HashMap;

import org.testng.annotations.Test;

import com.subex.automation.helpers.application.LoginHelper;
import com.subex.automation.helpers.application.screens.UserHelper;
import com.subex.automation.helpers.component.LabelHelper;
import com.subex.automation.helpers.component.TextBoxHelper;
import com.subex.automation.helpers.file.ExcelReader;
import com.subex.automation.helpers.util.FailureHelper;

public class test148344 extends testUserManagement {
	
	final String sheetName = "test148344";
	
	public test148344() throws Exception {
		super();
	}
	
	@Test(priority=1, description="Test case 1 of Bug 148344", groups = { "disableUser" })
	public void testCase1() throws Exception {
		try {
			// Verify if on logging in as active user, login happens successfully
			UserHelper users = new UserHelper();
			users.createUser(path, fileName, sheetName, "Users", 1);
			
			LoginHelper login = new LoginHelper();
			login.login(path, fileName, sheetName, "UserLogin", 1);
			ExcelReader excelReader = new ExcelReader();
			HashMap<String, ArrayList<String>> excelData = excelReader.readDataByColumn( path, fileName, sheetName, "UserLogin", 1 );
			String userName = excelData.get("Username").get(0);
			
			String loggedInUser = login.getLoginUser();
			assertEquals(userName, loggedInUser, "Expected user '" + userName + "' is not logged in.");
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
	
	@Test(priority=2, description="Test case 2 of Bug 148344", groups = { "disableUser" })
	public void testCase2() throws Exception {
		try {
			// Verify if on logging in as disabled user, login should not happen and valid error message should be displayed
			UserHelper users = new UserHelper();
			users.createUser(path, fileName, sheetName, "Users", 2);
			users.disableUser(path, fileName, sheetName, "Users", 2);
			
			LoginHelper login = new LoginHelper();
			login.login(path, fileName, sheetName, "UserLogin", 2);
			assertTrue(TextBoxHelper.isPresent("Login_Username_TextBox"));
			assertTrue(LabelHelper.isTextPresent("User disabled. Contact system administrator"));
		} catch (AssertionError e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	@Test(priority=3, description="Test case 3 of Bug 148344", dependsOnMethods = { "testCase2" }, groups = { "disableUser" })
	public void testCase3() throws Exception {
		try {
			// Verify if on logging in as disabled user multiple times, login should not happen and valid error message should be displayed
			LoginHelper login = new LoginHelper();
			login.login(path, fileName, sheetName, "UserLogin", 2);
			assertTrue(TextBoxHelper.isPresent("Login_Username_TextBox"));
			assertTrue(LabelHelper.isTextPresent("User disabled. Contact system administrator"));
			
			login.login(path, fileName, sheetName, "UserLogin", 2);
			assertTrue(TextBoxHelper.isPresent("Login_Username_TextBox"));
			assertTrue(LabelHelper.isTextPresent("User disabled. Contact system administrator"));
			
			login.login(path, fileName, sheetName, "UserLogin", 2);
			assertTrue(TextBoxHelper.isPresent("Login_Username_TextBox"));
			assertTrue(LabelHelper.isTextPresent("User disabled. Contact system administrator"));
		} catch (AssertionError e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
}