package com.subex.automation.testcases.regressiontesting.usermanagement;

import java.util.ArrayList;
import java.util.HashMap;

import org.testng.annotations.Test;

import com.subex.automation.helpers.application.LoginHelper;
import com.subex.automation.helpers.application.NavigationHelper;
import com.subex.automation.helpers.application.screens.ChangePasswordHelper;
import com.subex.automation.helpers.application.screens.UserHelper;
import com.subex.automation.helpers.component.ButtonHelper;
import com.subex.automation.helpers.component.FileHelper;
import com.subex.automation.helpers.component.GenericHelper;
import com.subex.automation.helpers.component.LabelHelper;
import com.subex.automation.helpers.component.TextBoxHelper;
import com.subex.automation.helpers.file.ExcelReader;
import com.subex.automation.helpers.util.FailureHelper;

public class testChangePassword extends testUserManagement {

	final String sheetName = "ChangePassword";
	
	public testChangePassword() throws Exception {
		super();
	}
	
	private void updatePropertyFile(String userName, String newPassword) throws Exception {
		try {
			if (configProp.getApplicationUsername().equals(userName))
				FileHelper.updatePropertyFile(configFile, "applicationPassword", newPassword);
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	private void checkLogin(String userName, String currentPassword, String newPassword, boolean shouldLoginSucceed) throws Exception {
		try {
			LoginHelper login = new LoginHelper();
			login.logout();
			login.login(configProp.getApplicationName(), userName, currentPassword, newPassword);
			
			if (shouldLoginSucceed) {
				assertTrue(login.isLoginSuccessful());
			}
			else {
				String errorMsg = login.getLoginScreenError();
				assertTrue(errorMsg.contains("Invalid Credentials"));
			}
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	@Test(priority=1, description="Root user change password from Options")
	public void testRootUser() throws Exception {
		try {
			// Root user - Change Password from Options
			LoginHelper login = new LoginHelper();
			login.loginWithConfigPropertyDetails();
			
			String tcName = "RootChangePassword1";
			ExcelReader excelReader = new ExcelReader();
			HashMap<String, ArrayList<String>> excelData = excelReader.readDataByColumn( path, fileName, sheetName, tcName, 1 );
			String userName = excelData.get("Username").get(0);
			String currentPassword = excelData.get("Current Password").get(0);
			String newPassword = excelData.get("New Password").get(0);
			
			ChangePasswordHelper changePassword = new ChangePasswordHelper();
			changePassword.changePassword(path, fileName, sheetName, tcName, 1);
			updatePropertyFile(userName, newPassword);
			
			checkLogin(userName, currentPassword, newPassword, false);
			checkLogin(userName, newPassword, "", true);
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	@Test(priority=2, description="Other user change password from Options")
	public void testOtherUsers() throws Exception {
		try {
			// Other user - Change Password from Options
			UserHelper users = new UserHelper();
			users.createUser(path, fileName, sheetName, "Users", 1);
			
			LoginHelper login = new LoginHelper();
			login.login(path, fileName, sheetName, "Login1", 1);
			
			String tcName = "UserChangePassword1";
			ExcelReader excelReader = new ExcelReader();
			HashMap<String, ArrayList<String>> excelData = excelReader.readDataByColumn( path, fileName, sheetName, tcName, 1 );
			String userName = excelData.get("Username").get(0);
			String currentPassword = excelData.get("Current Password").get(0);
			String newPassword = excelData.get("New Password").get(0);
			
			ChangePasswordHelper changePassword = new ChangePasswordHelper();
			changePassword.changePassword(path, fileName, sheetName, tcName, 1);
			
			checkLogin(userName, currentPassword, newPassword, false);
			checkLogin(userName, newPassword, "", true);
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	@Test(priority=3, description="Root user change password from Users")
	public void testRootFromUsers() throws Exception {
		try {
			// Root user - Change Password from Users
			LoginHelper login = new LoginHelper();
			login.loginWithConfigPropertyDetails();
			
			String tcName = "RootChangePassword2";
			ExcelReader excelReader = new ExcelReader();
			HashMap<String, ArrayList<String>> excelData = excelReader.readDataByColumn( path, fileName, sheetName, tcName, 1 );
			String userName = excelData.get("Username").get(0);
			String currentPassword = excelData.get("Current Password").get(0);
			String newPassword = excelData.get("New Password").get(0);
			
			UserHelper users = new UserHelper();
			users.changePassword(path, fileName, sheetName, tcName, 1);
			updatePropertyFile(userName, newPassword);
			
			checkLogin(userName, currentPassword, newPassword, false);
			checkLogin(userName, newPassword, "", true);
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	@Test(priority=4, description="Other user change password from Users")
	public void testOtherUsersFromUsers() throws Exception {
		try {
			// Root user - Change Password from Users
			UserHelper users = new UserHelper();
			users.createUser(path, fileName, sheetName, "Users", 1);
			
			LoginHelper login = new LoginHelper();
			login.login(path, fileName, sheetName, "Login1", 1);
			
			String tcName = "UserChangePassword2";
			ExcelReader excelReader = new ExcelReader();
			HashMap<String, ArrayList<String>> excelData = excelReader.readDataByColumn( path, fileName, sheetName, tcName, 1 );
			String userName = excelData.get("Username").get(0);
			String currentPassword = excelData.get("Current Password").get(0);
			String newPassword = excelData.get("New Password").get(0);
			
			users.changePassword(path, fileName, sheetName, tcName, 1);
			updatePropertyFile(userName, newPassword);
			
			checkLogin(userName, currentPassword, newPassword, false);
			checkLogin(userName, newPassword, "", true);
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	@Test(priority=5, description="Other user change password > reuse old password")
	public void testReuseOldPassword() throws Exception {
		try {
			// Root user - Change Password from Users
			LoginHelper login = new LoginHelper();
			login.loginWithConfigPropertyDetails();
			
			UserHelper users = new UserHelper();
			users.createUser(path, fileName, sheetName, "Users", 1);
			
			login.login(path, fileName, sheetName, "Login2", 1);
			
			String tcName = "UserChangePassword3";
			ExcelReader excelReader = new ExcelReader();
			HashMap<String, ArrayList<String>> excelData = excelReader.readDataByColumn( path, fileName, sheetName, tcName, 1 );
			String currentPassword = excelData.get("Current Password").get(0);
			String newPassword = excelData.get("New Password").get(0);
			String screenTitle = "Change Password";
			
			NavigationHelper.navigateToScreen( "Change Password", screenTitle );
			GenericHelper.waitForElement("ChangePassword_OK", searchScreenWaitSec);
			assertTrue(LabelHelper.isTitlePresent("Change Password"));
			
			TextBoxHelper.type("ChangePassword_CurrentPassword", currentPassword);
			TextBoxHelper.type("ChangePassword_NewPassword", newPassword);
			TextBoxHelper.type("ChangePassword_ConfirmPassword", newPassword);
			
			ButtonHelper.click("ChangePassword_OK");
			GenericHelper.waitForLoadmask(searchScreenWaitSec);
			assertTrue(LabelHelper.isTitlePresent(screenTitle));
			assertTrue(TextBoxHelper.isMandatory("ChangePassword_NewPassword"));
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		} finally {
			if (ButtonHelper.isPresent("ChangePassword_OK")) {
				ButtonHelper.click("CancelButton");
				GenericHelper.waitForLoadmask(searchScreenWaitSec);
			}
		}
	}
}