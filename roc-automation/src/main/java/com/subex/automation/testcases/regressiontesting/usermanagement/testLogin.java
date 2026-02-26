package com.subex.automation.testcases.regressiontesting.usermanagement;

import org.testng.annotations.Test;

import com.subex.automation.helpers.application.LoginHelper;
import com.subex.automation.helpers.application.screens.UserHelper;
import com.subex.automation.helpers.util.FailureHelper;

public class testLogin extends PasswordValidation {

	final String sheetName = "Login";
	
	public testLogin() throws Exception {
		super();
	}
	
	@Test(priority=1, description="Login with wrong username")
	public void testWrongUserLogin() throws Exception {
		try {
			// Login with wrong username
			LoginHelper login = new LoginHelper();
			login.login(configProp.getApplicationName(), "dummy123", "welcome", "welcome1");
			String errorMsg = login.getLoginScreenError();
			assertTrue(errorMsg.contains("Invalid Credentials"));
			
			login.loginWithConfigPropertyDetails();
			verifyUserLogin(sheetName, "WrongUserUserLogin", 1, 1);
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
	
	@Test(priority=2, description="Login with wrong password")
	public void testWrongPasswordLogin() throws Exception {
		try {
			// Login with wrong password
			UserHelper users = new UserHelper();
			users.createUser(path, fileName, sheetName, "Users1", 1);
			
			LoginHelper login = new LoginHelper();
			login.login(path, fileName, sheetName, "Login1", 1);
			
			String errorMsg = login.getLoginScreenError();
			assertTrue(errorMsg.contains("Invalid Credentials"));
			
			login.loginWithConfigPropertyDetails();
			verifyUserLogin(sheetName, "WrongPassUserLogin", 1, 1);
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
	
	@Test(priority=3, description="Login with 3 times wrong password")
	public void testDisbleAfterFailedLoginAttempts() throws Exception {
		LoginHelper login = new LoginHelper();
		boolean hasFailed = false;
		
		try {
			// Login with 3 times wrong password
			UserHelper users = new UserHelper();
			users.createUser(path, fileName, sheetName, "Users2", 1);
			
			consecutiveFailedAttempts(sheetName, "Login2", 3);
			
			login.loginWithConfigPropertyDetails();
			verifyUserLogin(sheetName, "UserLogin1", 1, 2);
			verifyUserLogin(sheetName, "UserLogin2", 1, 1);
		} catch (Exception e) {
			hasFailed = true;
			FailureHelper.setErrorMessage(e);
			throw e;
		} finally {
			if (hasFailed)
				login.loginWithConfigPropertyDetails();
		}
	}
}