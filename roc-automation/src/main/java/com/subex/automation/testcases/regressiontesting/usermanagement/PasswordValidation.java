package com.subex.automation.testcases.regressiontesting.usermanagement;

import java.util.ArrayList;
import java.util.HashMap;

import com.subex.automation.helpers.application.LoginHelper;
import com.subex.automation.helpers.application.NavigationHelper;
import com.subex.automation.helpers.component.ButtonHelper;
import com.subex.automation.helpers.component.GenericHelper;
import com.subex.automation.helpers.component.LabelHelper;
import com.subex.automation.helpers.component.PopupHelper;
import com.subex.automation.helpers.component.TextBoxHelper;
import com.subex.automation.helpers.file.ExcelReader;
import com.subex.automation.helpers.util.FailureHelper;

public class PasswordValidation extends testUserManagement {

	static String applicationName = null;
	static String userName = null;
	static String password = null;
	static String resetPassword = null;
	
	public PasswordValidation() throws Exception {
		super();
	}
	
	protected void setParameters(String sheetName, String testCaseName) throws Exception {
		try {
			ExcelReader excelReader = new ExcelReader();
			HashMap<String, ArrayList<String>> excelData = excelReader.readDataByColumn( path, fileName, sheetName, testCaseName, 1 );
			applicationName = excelData.get("Application Name").get(0);
			userName = excelData.get("Username").get(0);
			password = excelData.get("Password").get(0);
			resetPassword = excelData.get("Reset Password").get(0);
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	protected void consecutiveFailedAttempts(String sheetName, String testCaseName, int maxFailedLoginAttempts) throws Exception {
		try {
			setParameters(sheetName, testCaseName);
			
			LoginHelper login = new LoginHelper();
			String wrongPassword = password + "1";
			
			for (int i = 1; i <= maxFailedLoginAttempts; i++) {
				login.login(applicationName, userName, wrongPassword, resetPassword);
				String errorMsg = login.getLoginScreenError();
				
				if (i < maxFailedLoginAttempts)
					assertTrue(errorMsg.contains("Invalid Credentials"));
				else
					assertTrue(errorMsg.contains("Failed login - User disabled due to 3 consecutive failed login attempts"));
			}
			
			login.login(applicationName, userName, password, resetPassword);
			String errorMsg = login.getLoginScreenError();
			assertTrue(errorMsg.contains("User disabled. Contact system administrator"));
		} catch (AssertionError e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	protected void nonconsecutiveFailedAttempts(String sheetName, String testCaseName, int maxFailedLoginAttempts) throws Exception {
		try {
			setParameters(sheetName, testCaseName);
			
			LoginHelper login = new LoginHelper();
			String wrongPassword = password + "1";
			
			for (int i = 1; i <= maxFailedLoginAttempts; i++) {
				login.login(applicationName, userName, password, resetPassword);
				NavigationHelper.navigateToScreen( "Users", "User Search" );
				login.logout();
				
				login.login(applicationName, userName, wrongPassword, resetPassword);
				String errorMsg = login.getLoginScreenError();
				assertTrue(errorMsg.contains("Invalid Credentials"));
			}
			
			login.login(applicationName, userName, password, resetPassword);
			NavigationHelper.navigateToScreen( "Users", "User Search" );
		} catch (AssertionError e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	protected void checkPassword(String sheetName, String testCaseName, String newPassword) throws Exception {
		try {
			setParameters(sheetName, testCaseName);
			
			LoginHelper login = new LoginHelper();
			login.logout();
			login.setLoginDetails(applicationName, userName, password);
			login.acceptLoginSuccessfulPopUp(searchScreenWaitSec);
			
			if(PopupHelper.isTextPresent("Your password has expired. You must reset your password before continuing.")) {
				ButtonHelper.click("OKButton");
				GenericHelper.waitForElement("ChangePassword_CurrentPassword", searchScreenWaitSec);
				assertTrue(LabelHelper.isTitlePresent("Change Password"));
				
				TextBoxHelper.type("ChangePassword_CurrentPassword", password);
				TextBoxHelper.type("ChangePassword_NewPassword", resetPassword);
				TextBoxHelper.type("ChangePassword_ConfirmPassword", resetPassword);
				
				ButtonHelper.click("ChangePassword_OK");
				GenericHelper.waitForLoadmask(searchScreenWaitSec);
				assertTrue(LabelHelper.isTitlePresent("Change Password"));
				assertTrue(TextBoxHelper.isMandatory("ChangePassword_NewPassword"), "New Password option is not highlighted.");
				
				TextBoxHelper.type("ChangePassword_NewPassword", newPassword);
				TextBoxHelper.type("ChangePassword_ConfirmPassword", newPassword);
				login.saveChangePassword(userName);
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