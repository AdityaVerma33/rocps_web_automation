package com.subex.automation.testcases.regressiontesting.usermanagement;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;

import org.testng.annotations.Test;

import com.subex.automation.helpers.application.LoginHelper;
import com.subex.automation.helpers.application.NavigationHelper;
import com.subex.automation.helpers.application.ROCHelper;
import com.subex.automation.helpers.application.screens.PasswordValidationHelper;
import com.subex.automation.helpers.application.screens.UserHelper;
import com.subex.automation.helpers.application.screens.UserValidationHelper;
import com.subex.automation.helpers.component.ButtonHelper;
import com.subex.automation.helpers.component.GenericHelper;
import com.subex.automation.helpers.component.GridHelper;
import com.subex.automation.helpers.component.LabelHelper;
import com.subex.automation.helpers.file.ExcelReader;
import com.subex.automation.helpers.scripts.ExecuteScript;
import com.subex.automation.helpers.util.FailureHelper;

public class testPasswordValidation extends PasswordValidation {
	
	final String sheetName = "PasswordValidation";
	
	public testPasswordValidation() throws Exception {
		super();
	}
	
	@Test(priority=1, description="Check Disable After Failed Login Attempts property")
	public void testDisbleAfterFailedLoginAttempts() throws Exception {
		ResultSet rs = null;
		LoginHelper login = new LoginHelper();
		boolean hasFailed = false;
		
		try {
			// Password Validation dialog – Disable After Failed Login Attempts property
			UserHelper users = new UserHelper();
			users.createUser(path, fileName, sheetName, "DisablePassUsers1", 1);
			
			String query = "select pri_value from property_inst where pig_id = (select pig_id from user_password_validation where upv_name like 'Standard Password Validation')"
					+ " and prd_id in (select prd_id from property_dfn where prd_name like 'Disable After Failed Login Attempts')";
			rs = ExecuteScript.exeQuery(query);
			rs.next();
			int maxFailedLoginAttempts = rs.getInt("pri_value");
			rs.close();
			
			consecutiveFailedAttempts(sheetName, "DisablePassLogin1", maxFailedLoginAttempts);
			
			login.loginWithConfigPropertyDetails();
			verifyUserLogin(sheetName, "DisablePassUserLogin11", 1, 2);
			verifyUserLogin(sheetName, "DisablePassUserLogin12", 1, 1);
			
			nonconsecutiveFailedAttempts(sheetName, "DisablePassLogin2", maxFailedLoginAttempts);
			
			login.loginWithConfigPropertyDetails();
			verifyUserLogin(sheetName, "DisablePassUserLogin21", 1, 3);
			verifyUserLogin(sheetName, "DisablePassUserLogin22", 1, 3);
		} catch (Exception e) {
			hasFailed = true;
			FailureHelper.setErrorMessage(e);
			throw e;
		} finally {
			if (rs != null)
				rs.close();
			
			if (hasFailed)
				login.loginWithConfigPropertyDetails();
		}
	}
	
	@Test(priority=3, description="Check Password Default property")
	public void testDefaultPassword() throws Exception {
		LoginHelper login = new LoginHelper();
		
		try {
			// Password Validation dialog – Password Default property
			PasswordValidationHelper passwordValidation = new PasswordValidationHelper();
			passwordValidation.updatePasswordValidation(path, fileName, sheetName, "DefaultPassPasswordValidation", 1);
			
			UserHelper users = new UserHelper();
			users.createUser(path, fileName, sheetName, "DefaultPassUsers1", 1);
			
			login.login(path, fileName, sheetName, "DefaultPassLogin1", 1);
			NavigationHelper.navigateToScreen( "Users", "User Search" );
			
			login.login(path, fileName, sheetName, "DefaultPassLogin2", 1);
			NavigationHelper.navigateToScreen( "Users", "User Search" );
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		} finally {
			login.loginWithConfigPropertyDetails();
		}
	}
	
	@Test(priority=4, description="Check Password Force Alpha Numeric property")
	public void testAplhaNumericPassword() throws Exception {
		LoginHelper login = new LoginHelper();
		
		try {
			// Password Validation dialog – Password Force Alpha Numeric property
			PasswordValidationHelper passwordValidation = new PasswordValidationHelper();
			passwordValidation.updatePasswordValidation(path, fileName, sheetName, "AlphaNumPassPasswordValidation", 1);
			
			UserHelper users = new UserHelper();
			users.createUser(path, fileName, sheetName, "AlphaNumPassUsers1", 1);
			
			login.login(path, fileName, sheetName, "AlphaNumPassLogin1", 1);
			NavigationHelper.navigateToScreen( "Users", "User Search" );
			
			String newPassword = resetPassword + "1234";
			checkPassword(sheetName, "AlphaNumPassLogin2", newPassword);
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		} finally {
			login.loginWithConfigPropertyDetails();
		}
	}
	
	@Test(priority=5, description="Check Password Force Strong property")
	public void testStrongPassword() throws Exception {
		LoginHelper login = new LoginHelper();
		
		try {
			// Password Validation dialog – Password Force Strong property
			PasswordValidationHelper passwordValidation = new PasswordValidationHelper();
			passwordValidation.updatePasswordValidation(path, fileName, sheetName, "StrongPassPasswordValidation", 1);
			
			UserHelper users = new UserHelper();
			users.createUser(path, fileName, sheetName, "StrongPassUsers1", 1);
			
			String newPassword = password + "4";
			checkPassword(sheetName, "StrongPassLogin1", newPassword);
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		} finally {
			ROCHelper rocHelper = new ROCHelper();
			rocHelper.handleFailures();
			
			login.loginWithConfigPropertyDetails();
		}
	}
	
	@Test(priority=6, description="Check Password Minimum Length property")
	public void testMinimumLengthPassword() throws Exception {
		LoginHelper login = new LoginHelper();
		
		try {
			// Password Validation dialog – Password Minimum Length property
			PasswordValidationHelper passwordValidation = new PasswordValidationHelper();
			passwordValidation.updatePasswordValidation(path, fileName, sheetName, "MinLengthPassPasswordValidation", 1);
			
			UserHelper users = new UserHelper();
			users.createUser(path, fileName, sheetName, "MinLengthPassUsers1", 1);
			
			String newPassword = password + "12";
			checkPassword(sheetName, "MinLengthPassLogin1", newPassword);
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		} finally {
			ROCHelper rocHelper = new ROCHelper();
			rocHelper.handleFailures();
			
			login.loginWithConfigPropertyDetails();
		}
	}
	
	@Test(priority=7, description="Check Password Validation delete")
	public void testPVDelete() throws Exception {
		try {
			// Password Validation tab – Delete action
			PasswordValidationHelper passwordValidation = new PasswordValidationHelper();
			passwordValidation.updatePasswordValidation(path, fileName, sheetName, "DeletePasswordValidation", 1);
			
			UserHelper users = new UserHelper();
			users.createUser(path, fileName, sheetName, "DeletePassUsers", 1);
			
			ExcelReader excelReader = new ExcelReader();
			HashMap<String, ArrayList<String>> excelData = excelReader.readDataByColumn( path, fileName, sheetName, "DeletePasswordValidation", 1 );
			ArrayList<String> name = excelData.get("Name");
			String detailScreenTitle = passwordValidation.navigateToPasswordValidation();
			
			for (int i = 0; i < name.size(); i++) {
				int rowNum = GridHelper.getRowNumber("PasswordValidation_Grid", name.get(i), "Password  Validation");
				GridHelper.clickRow("PasswordValidation_Grid", rowNum, 1);
				ButtonHelper.click("PasswordValidation_Delete");
				GenericHelper.waitForLoadmask(detailScreenWaitSec);
				
				if (i == 0) {
					assertTrue(ButtonHelper.isPresent("OKButton"));
					assertTrue(LabelHelper.isTextPresent("Unable to delete User Password Validation " + name.get(i) + " as it is assigned to 1 user."));
				
					ButtonHelper.click("OKButton");
					GenericHelper.waitForLoadmask(detailScreenWaitSec);
					rowNum = GridHelper.getRowNumber("PasswordValidation_Grid", name.get(1), "Password  Validation");
					assertTrue(rowNum > 1, "Password Validation got deleted eventhough it is used.");
				}
				else {
					assertTrue(ButtonHelper.isPresent("YesButton"));
					ButtonHelper.click("YesButton");
					GenericHelper.waitForLoadmask(detailScreenWaitSec);
					rowNum = GridHelper.getRowNumber("PasswordValidation_Grid", name.get(1), "Password  Validation");
					assertTrue(rowNum == 0, "Password Validation did not get deleted.");
				}
			}
			
			UserValidationHelper userValidation = new UserValidationHelper();
			userValidation.saveUserValidation(detailScreenTitle);
		} catch (AssertionError e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
}