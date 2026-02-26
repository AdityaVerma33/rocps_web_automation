package com.subex.automation.testcases.regressiontesting.usermanagement.usermgnt_issues;

import java.util.ArrayList;
import java.util.HashMap;

import org.testng.annotations.Test;

import com.subex.automation.helpers.application.LoginHelper;
import com.subex.automation.helpers.application.NavigationHelper;
import com.subex.automation.helpers.application.screens.PasswordValidationHelper;
import com.subex.automation.helpers.application.screens.UserHelper;
import com.subex.automation.helpers.component.ButtonHelper;
import com.subex.automation.helpers.component.GenericHelper;
import com.subex.automation.helpers.component.GridHelper;
import com.subex.automation.helpers.component.LabelHelper;
import com.subex.automation.helpers.component.PopupHelper;
import com.subex.automation.helpers.component.TextBoxHelper;
import com.subex.automation.helpers.file.ExcelReader;
import com.subex.automation.helpers.util.FailureHelper;

public class test154071 extends testUserManagement {
	
	final String sheetName = "test154071";
	
	public test154071() throws Exception {
		super();
	}
	
	private void saveChangePassword(String screenTitle) throws Exception {
		try {
			ButtonHelper.click("ChangePassword_OK");
			GenericHelper.waitForLoadmask(searchScreenWaitSec);
			assertTrue(LabelHelper.isTitlePresent(screenTitle), "Password length check did not happen.");
			assertTrue(TextBoxHelper.isMandatory("Users_NewPassword"), "New Password option is not highlighted with error message.");
		} catch (AssertionError e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	private void checkPassword(String screenTitle, String userName, String currentPassword, String newPassword1, String newPassword2,
			String newPassword3) throws Exception {
		try {
			assertTrue(LabelHelper.isTitlePresent(screenTitle));
			if (TextBoxHelper.isEnabled("ChangePassword_CurrentPassword"))
				TextBoxHelper.type("ChangePassword_CurrentPassword", currentPassword);
			TextBoxHelper.type("ChangePassword_NewPassword", newPassword1);
			TextBoxHelper.type("ChangePassword_ConfirmPassword", newPassword1);
			
			saveChangePassword(screenTitle);
			
			TextBoxHelper.type("ChangePassword_NewPassword", newPassword2);
			TextBoxHelper.type("ChangePassword_ConfirmPassword", newPassword2);
			
			saveChangePassword(screenTitle);
			
			TextBoxHelper.type("ChangePassword_NewPassword", newPassword3);
			TextBoxHelper.type("ChangePassword_ConfirmPassword", newPassword3);
			LoginHelper login = new LoginHelper();
			login.saveChangePassword(userName);
		} catch (AssertionError e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	@Test(priority=1, description="Test case 1 of Bug 154071", groups = { "passwordLength" })
	public void testCase1() throws Exception {
		try {
			// While creating a user, validate that Password option follows the minimum and maximum password length
			// as per the Password Validation selected for that user
			PasswordValidationHelper passwordValidation = new PasswordValidationHelper();
			passwordValidation.updatePasswordValidation(path, fileName, sheetName, "PasswordValidation", 1);
			
			ExcelReader excelReader = new ExcelReader();
			HashMap<String, ArrayList<String>> excelData = excelReader.readDataByColumn( path, fileName, sheetName, "Users", 1 );
			String partition = excelData.get("Partition").get(0);
			String userName = excelData.get("Username").get(0);
			
			UserHelper users = new UserHelper();
			boolean isPresent = users.navigateToUsers(userName, partition);
			
			if (!isPresent) {
				String detailScreenTitle = NavigationHelper.getScreenTitle();
				GenericHelper.waitForLoadmask(detailScreenWaitSec);
				users.updateUser(path, fileName, sheetName, "Users", 1);
				
				ButtonHelper.click("SaveButton");
				GenericHelper.waitForLoadmask(detailScreenWaitSec);
				assertTrue(LabelHelper.isTitlePresent(detailScreenTitle), "Password length check did not happen.");
				assertTrue(TextBoxHelper.isMandatory("Users_NewPassword"));
				
				TextBoxHelper.type("Users_NewPassword", "welcome12345");
				ButtonHelper.click("SaveButton");
				GenericHelper.waitForLoadmask(detailScreenWaitSec);
				assertTrue(LabelHelper.isTitlePresent(detailScreenTitle), "Password length check did not happen.");
				assertTrue(TextBoxHelper.isMandatory("Users_NewPassword"));
				
				TextBoxHelper.type("Users_NewPassword", "welcome1");
				users.saveUsers(userName, detailScreenTitle);
			}
		} catch (AssertionError e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	@Test(priority=2, description="Test case 2 of Bug 154071", dependsOnMethods = { "testCase1" }, groups = { "passwordLength" })
	public void testCase2() throws Exception {
		try {
			// On first time login as a new user, validate that Change Password option follows the minimum and maximum password length
			// as per the Password Validation for that user
			ExcelReader excelReader = new ExcelReader();
			HashMap<String, ArrayList<String>> excelData = excelReader.readDataByColumn( path, fileName, sheetName, "Users", 1 );
			String userName = excelData.get("Username").get(0);
			String password = "welcome1";
			
			LoginHelper login = new LoginHelper();
			login.logout();
			login.setLoginDetails(configProp.getApplicationName(), userName, password);
			
			assertTrue(PopupHelper.isTextPresent("Your password has expired. You must reset your password before continuing."));
			ButtonHelper.click("OKButton");
			GenericHelper.waitForElement("ChangePassword_CurrentPassword", searchScreenWaitSec);
			
			checkPassword("Change Password", userName, password, "welcome", "welcome12345", "welcome2");
		} catch (AssertionError e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	@Test(priority=3, description="Test case 3 of Bug 154071", dependsOnMethods = { "testCase2" }, groups = { "passwordLength" })
	public void testCase3() throws Exception {
		try {
			// For an existing user, validate that Change Password action in Options follows the minimum and maximum password length
			// as per the Password Validation for that user
			LoginHelper login = new LoginHelper();
			login.login(path, fileName, sheetName, "UserLogin", 1);
			
			ExcelReader excelReader = new ExcelReader();
			HashMap<String, ArrayList<String>> excelData = excelReader.readDataByColumn( path, fileName, sheetName, "UserLogin", 1 );
			String userName = excelData.get("Username").get(0);
			String password = "welcome2";
			
			NavigationHelper.navigateToScreen("Change Password", "Change Password");
			
			checkPassword("Change Password", userName, password, "welcome", "welcome12345", "welcome3");
		} catch (AssertionError e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	@Test(priority=4, description="Test case 4 of Bug 154071", dependsOnMethods = { "testCase1" }, groups = { "passwordLength" })
	public void testCase4() throws Exception {
		try {
			// For an existing user, validate that Change Password action in Options follows the minimum and maximum password length
			// as per the Password Validation for that user
			UserHelper users = new UserHelper();
			users.createUser(path, fileName, sheetName, "Users", 2);
			
			ExcelReader excelReader = new ExcelReader();
			HashMap<String, ArrayList<String>> excelData = excelReader.readDataByColumn( path, fileName, sheetName, "UserLogin", 1 );
			String userName = excelData.get("Username").get(0);
			String password = "welcome3";
			
			int row = users.navigateToUsers(userName);
			GridHelper.clickRow("SearchGrid", row, 1);
			NavigationHelper.navigateToAction("User Actions", "Change Password");
			
			checkPassword("Edit Change Password", userName, password, "welcome", "welcome12345", "welcome4");
			
			excelData = excelReader.readDataByColumn( path, fileName, sheetName, "Users", 2 );
			userName = excelData.get("Username").get(0);
			password = excelData.get("Password").get(0);
			
			row = users.navigateToUsers(userName);
			GridHelper.clickRow("SearchGrid", row, 1);
			NavigationHelper.navigateToAction("User Actions", "Change Password");
			
			String newPassword = "welcome12345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012";
			checkPassword("Edit Change Password", userName, password, "welco", newPassword, "welcome1");
		} catch (AssertionError e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
}