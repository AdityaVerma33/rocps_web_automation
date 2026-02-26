package com.subex.automation.testcases.regressiontesting.usermanagement;

import java.util.ArrayList;
import java.util.HashMap;

import org.testng.annotations.Test;

import com.subex.automation.helpers.application.LoginHelper;
import com.subex.automation.helpers.application.NavigationHelper;
import com.subex.automation.helpers.application.screens.UserHelper;
import com.subex.automation.helpers.application.screens.UserValidationHelper;
import com.subex.automation.helpers.component.ButtonHelper;
import com.subex.automation.helpers.component.ComboBoxHelper;
import com.subex.automation.helpers.component.GenericHelper;
import com.subex.automation.helpers.component.GridHelper;
import com.subex.automation.helpers.component.LabelHelper;
import com.subex.automation.helpers.component.SearchGridHelper;
import com.subex.automation.helpers.component.TabHelper;
import com.subex.automation.helpers.component.TextBoxHelper;
import com.subex.automation.helpers.file.ExcelReader;
import com.subex.automation.helpers.util.FailureHelper;

public class testUserValidation extends testUserManagement {

	final String sheetName = "UserValidation";
	
	public testUserValidation() throws Exception {
		super();
	}
	
	@Test(priority=1, description="Check User Validation action")
	public void testAction() throws Exception {
		try {
			// Ensure the options available in User Validations screen
			LoginHelper login = new LoginHelper();
			login.loginWithConfigPropertyDetails();
			
			NavigationHelper.navigateToScreen( "Users", "User Search" );
			assertTrue(NavigationHelper.isActionPresent("Special Actions"));
			NavigationHelper.navigateToAction("Special Actions");
			assertTrue(NavigationHelper.isActionPresent("User Validations"));
			NavigationHelper.navigateToAction("Special Actions");
			
			ButtonHelper.click("SearchButton");
			GenericHelper.waitForLoadmask(searchScreenWaitSec);
			GridHelper.clickRow("SearchGrid", 1, 1);
			
			NavigationHelper.navigateToScreen( "Users", "User Search" );
			assertTrue(NavigationHelper.isActionPresent("Special Actions"));
			NavigationHelper.navigateToAction("Special Actions");
			assertTrue(NavigationHelper.isActionPresent("User Validations"));
			NavigationHelper.navigateToAction("User Validations");
			
			assertTrue(LabelHelper.isTitlePresent("User Validation Settings"), "Popup with title 'User Validation Settings' is not found.");
			assertTrue(TabHelper.isPresent("User Validation"), "Tab 'User Validation' is not found.");
			assertTrue(TabHelper.isPresent("Password Validation"), "Tab 'Password Validation' is not found.");
			assertTrue(ComboBoxHelper.isPresent("UserValidation_Component"), "Combobox 'Component' is not found.");
			assertTrue(ButtonHelper.isPresent("UserValidation_Save"), "Button 'Save' is not found.");
			assertTrue(ButtonHelper.isPresent("UserValidation_Cancel"), "Button 'Cancel' is not found.");
			
			ButtonHelper.click("UserValidation_Cancel");
			GenericHelper.waitForLoadmask(searchScreenWaitSec);
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	@Test(priority=2, description="Check User Name Force Alpha Numeric property")
	public void testUsernameForceAlphaNumeric() throws Exception {
		UserValidationHelper userValidation = new UserValidationHelper();
		boolean hasFailed = false;
		
		try {
			// User Validation - User Name Force Alpha Numeric property
			userValidation.updateUserValidation(path, fileName, sheetName, "UserValidationForceAlpha1", 1);
			
			ExcelReader excelReader = new ExcelReader();
			HashMap<String, ArrayList<String>> excelData = excelReader.readDataByColumn( path, fileName, sheetName, "AlphaNumericUser1", 1 );
			String userName = excelData.get("Username").get(0);
			
			NavigationHelper.navigateToScreen( "Users", "User Search" );
			SearchGridHelper.searchWithTextBox("Users_Username", userName, "Name");
			NavigationHelper.navigateToNew("Common", "Users_FirstName");
			String detailScreenTitle = NavigationHelper.getScreenTitle();
			
			UserHelper users = new UserHelper();
			users.updateUser(path, fileName, sheetName, "AlphaNumericUser1", 1);
			
			ButtonHelper.click("SaveButton");
			GenericHelper.waitForLoadmask(detailScreenWaitSec);
			assertTrue(LabelHelper.isTitlePresent(detailScreenTitle), "User save happened without checking Username Force Alpha Numeric.");
			assertTrue(TextBoxHelper.isMandatory("Users_Username"));
			
			String newUserName = userName + "123";
			TextBoxHelper.type("Users_Username", newUserName);
			
			users.saveUsers(newUserName, detailScreenTitle);
			
			userValidation.updateUserValidation(path, fileName, sheetName, "UserValidationForceAlpha2", 1);
			users.createUser(path, fileName, sheetName, "AlphaNumericUser2", 1);
		} catch (AssertionError e) {
			hasFailed = true;
			FailureHelper.setErrorMessage(e);
			throw e;
		} catch (Exception e) {
			hasFailed = true;
			FailureHelper.setErrorMessage(e);
			throw e;
		} finally {
			if (hasFailed)
				userValidation.updateUserValidation(path, fileName, sheetName, "UserValidationForceAlpha2", 1);
		}
	}
	
	@Test(priority=3, description="Check User Name Maximum Length property")
	public void testUsernameMaxLength() throws Exception {
		UserValidationHelper userValidation = new UserValidationHelper();
		LoginHelper login = new LoginHelper();
		boolean hasFailed = false;
		
		try {
			// User Validation - User Name Maximum Length property
			UserHelper users = new UserHelper();
			users.createUser(path, fileName, sheetName, "User1", 1);
			
			userValidation.updateUserValidation(path, fileName, sheetName, "UserValidationMaxLength1", 1);
			
			ExcelReader excelReader = new ExcelReader();
			HashMap<String, ArrayList<String>> excelData = excelReader.readDataByColumn( path, fileName, sheetName, "MaxLengthUser1", 1 );
			String userName = excelData.get("Username").get(0);
			String newUserName = userName.substring(0, 5);
			
			NavigationHelper.navigateToScreen( "Users", "User Search" );
			SearchGridHelper.searchWithTextBox("Users_Username", newUserName, "Name");
			NavigationHelper.navigateToNew("Common", "Users_FirstName");
			String detailScreenTitle = NavigationHelper.getScreenTitle();
			
			users.updateUser(path, fileName, sheetName, "MaxLengthUser1", 1);
			
			ButtonHelper.click("SaveButton");
			GenericHelper.waitForLoadmask(detailScreenWaitSec);
			assertTrue(LabelHelper.isTitlePresent(detailScreenTitle), "User save happened without checking Username Force Alpha Numeric.");
			assertTrue(TextBoxHelper.isMandatory("Users_Username"));
			
			TextBoxHelper.type("Users_Username", newUserName);
			users.saveUsers(newUserName, detailScreenTitle);
			
			users.createUser(path, fileName, sheetName, "MaxLengthUser2", 1);
			
			excelData = excelReader.readDataByColumn( path, fileName, sheetName, "User1", 1 );
			userName = excelData.get("Username").get(0);
			
			NavigationHelper.navigateToScreen( "Users", "User Search" );
			int rowNum = SearchGridHelper.searchWithTextBox("Users_Username", userName, "Name");
			NavigationHelper.navigateToEdit("SearchGrid", rowNum, "Users_FirstName");
			detailScreenTitle = NavigationHelper.getScreenTitle();
			
			ButtonHelper.click("SaveButton");
			GenericHelper.waitForLoadmask(detailScreenWaitSec);
			assertTrue(LabelHelper.isTitlePresent(detailScreenTitle), "User save happened without checking Username Force Alpha Numeric.");
			assertTrue(TextBoxHelper.isMandatory("Users_Username"));
			ButtonHelper.click("CancelButton");
			GenericHelper.waitForLoadmask(detailScreenWaitSec);
			
			if (ButtonHelper.isPresent("YesButton")) {
				ButtonHelper.click("YesButton");
				GenericHelper.waitForLoadmask(detailScreenWaitSec);
			}
			
			login.login(path, fileName, sheetName, "Login1", 1);
			NavigationHelper.navigateToScreen( "Users", "User Search" );
			login.loginWithConfigPropertyDetails();
			
			userValidation.updateUserValidation(path, fileName, sheetName, "UserValidationMaxLength2", 1);
			users.createUser(path, fileName, sheetName, "MaxLengthUser3", 1);
		} catch (AssertionError e) {
			hasFailed = true;
			FailureHelper.setErrorMessage(e);
			throw e;
		} catch (Exception e) {
			hasFailed = true;
			FailureHelper.setErrorMessage(e);
			throw e;
		} finally {
			if (hasFailed) {
				login.loginWithConfigPropertyDetails();
				userValidation.updateUserValidation(path, fileName, sheetName, "UserValidationMaxLength2", 1);
			}
		}
	}
	
	@Test(priority=4, description="Check User Name Minimum Length property")
	public void testUsernameMinLength() throws Exception {
		UserValidationHelper userValidation = new UserValidationHelper();
		LoginHelper login = new LoginHelper();
		boolean hasFailed = false;
		
		try {
			// User Validation - User Name Minimum Length property
			UserHelper users = new UserHelper();
			users.createUser(path, fileName, sheetName, "User2", 1);
			
			userValidation.updateUserValidation(path, fileName, sheetName, "UserValidationMinLength1", 1);
			
			ExcelReader excelReader = new ExcelReader();
			HashMap<String, ArrayList<String>> excelData = excelReader.readDataByColumn( path, fileName, sheetName, "MinLengthUser1", 1 );
			String userName = excelData.get("Username").get(0);
			
			NavigationHelper.navigateToScreen( "Users", "User Search" );
			SearchGridHelper.searchWithTextBox("Users_Username", userName, "Name");
			NavigationHelper.navigateToNew("Common", "Users_FirstName");
			String detailScreenTitle = NavigationHelper.getScreenTitle();
			
			users.updateUser(path, fileName, sheetName, "MinLengthUser1", 1);
			
			ButtonHelper.click("SaveButton");
			GenericHelper.waitForLoadmask(detailScreenWaitSec);
			assertTrue(LabelHelper.isTitlePresent(detailScreenTitle), "User save happened without checking Username Force Alpha Numeric.");
			assertTrue(TextBoxHelper.isMandatory("Users_Username"));
			
			String newUserName = userName + "ghi";
			TextBoxHelper.type("Users_Username", newUserName);
			users.saveUsers(newUserName, detailScreenTitle);
			
			users.createUser(path, fileName, sheetName, "MinLengthUser2", 1);
			
			excelData = excelReader.readDataByColumn( path, fileName, sheetName, "User2", 1 );
			userName = excelData.get("Username").get(0);
			
			NavigationHelper.navigateToScreen( "Users", "User Search" );
			int rowNum = SearchGridHelper.searchWithTextBox("Users_Username", userName, "Name");
			NavigationHelper.navigateToEdit("SearchGrid", rowNum, "Users_FirstName");
			detailScreenTitle = NavigationHelper.getScreenTitle();
			
			ButtonHelper.click("SaveButton");
			GenericHelper.waitForLoadmask(detailScreenWaitSec);
			assertTrue(LabelHelper.isTitlePresent(detailScreenTitle), "User save happened without checking Username Force Alpha Numeric.");
			assertTrue(TextBoxHelper.isMandatory("Users_Username"));
			ButtonHelper.click("CancelButton");
			GenericHelper.waitForLoadmask(detailScreenWaitSec);
			
			if (ButtonHelper.isPresent("YesButton")) {
				ButtonHelper.click("YesButton");
				GenericHelper.waitForLoadmask(detailScreenWaitSec);
			}
			
			login.login(path, fileName, sheetName, "Login2", 1);
			NavigationHelper.navigateToScreen( "Users", "User Search" );
			login.loginWithConfigPropertyDetails();
			
			userValidation.updateUserValidation(path, fileName, sheetName, "UserValidationMinLength2", 1);
			users.createUser(path, fileName, sheetName, "MinLengthUser3", 1);
		} catch (AssertionError e) {
			hasFailed = true;
			FailureHelper.setErrorMessage(e);
			throw e;
		} catch (Exception e) {
			hasFailed = true;
			FailureHelper.setErrorMessage(e);
			throw e;
		} finally {
			if (hasFailed) {
				login.loginWithConfigPropertyDetails();
				userValidation.updateUserValidation(path, fileName, sheetName, "UserValidationMinLength2", 1);
			}
		}
	}
}