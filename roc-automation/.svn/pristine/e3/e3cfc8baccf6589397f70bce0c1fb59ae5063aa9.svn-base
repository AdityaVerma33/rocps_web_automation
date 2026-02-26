package com.subex.automation.testcases.systemtesting.security;

import org.testng.annotations.Test;

import com.subex.automation.helpers.application.ControllerHelper;
import com.subex.automation.helpers.application.LoginHelper;
import com.subex.automation.helpers.application.NavigationHelper;
import com.subex.automation.helpers.application.screens.PasswordValidationHelper;
import com.subex.automation.helpers.application.screens.RolesHelper;
import com.subex.automation.helpers.application.screens.SettingsHelper;
import com.subex.automation.helpers.application.screens.UserHelper;
import com.subex.automation.helpers.component.GenericHelper;
import com.subex.automation.helpers.report.Log4jHelper;
import com.subex.automation.helpers.selenium.ROCAcceptanceTest;
import com.subex.automation.helpers.util.FailureHelper;

public class testPrerequisite extends ROCAcceptanceTest {
	
	private static String path = null;
	final String fileName = "Security_TestData.xlsx";
	final String sheetName = "Security";
	
	public testPrerequisite() throws Exception {
		try {
			path = automationPath + "\\src\\main\\resources\\System_Test_Flows\\";
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	@Test(priority=1, description="Update Settings properties", groups = { "prerequisite" })
	public void updateSettings() throws Exception {
		try {
			Log4jHelper.logInfo("Running Security Flow");
			ControllerHelper controller = new ControllerHelper();
			controller.stopServices();
			
			SettingsHelper settings = new SettingsHelper();
			settings.updateSettings(path, fileName, sheetName, "Settings", 1);
			
			NavigationHelper.navigateToScreen( "Roles", "Role Search" );
			String[] partitionName = {"P1", "P2"};
			GenericHelper.insertPartition(partitionName);
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	@Test(priority=2, description="Restart tomcat", dependsOnMethods={ "updateSettings" }, groups = { "prerequisite" })   
	public void restartTomcat() throws Exception {
		try {
			ControllerHelper controller = new ControllerHelper();
			controller.restartTomcat();
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	@Test(priority=3, description="Create Roles", dependsOnMethods={ "restartTomcat" }, groups = { "prerequisite" })
 	public void createRoles() throws Exception {
		try {
			LoginHelper login = new LoginHelper();
			login.acceptLoginSuccessfulPopUp(searchScreenWaitSec);
			
			SettingsHelper settings = new SettingsHelper();
			settings.saveSettings("Edit Settings");
			
			RolesHelper roles = new RolesHelper();
			roles.createRole(path, fileName, sheetName, "Roles", 1);
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
 	}
	
 	@Test(priority=4, description="Edit Admin role", dependsOnMethods={ "createRoles" }, groups = { "prerequisite" })
 	public void editAdminRole() throws Exception {
 		try {
			RolesHelper roles = new RolesHelper();
			roles.createRole(path, fileName, sheetName, "EditRole", 1);
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
 	}
 	
 	@Test(priority=5, description="Add Password Validation", dependsOnMethods={ "editAdminRole" }, groups = { "prerequisite" })   
	public void addPasswordValidation() throws Exception {
		try {
			PasswordValidationHelper passwordValidation = new PasswordValidationHelper();
			passwordValidation.updatePasswordValidation(path, fileName, sheetName, "PasswordValidation", 1);
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
 	
 	@Test(priority=6, description="Create Users", dependsOnMethods={ "addPasswordValidation" }, groups = { "prerequisite" })
 	public void createUser() throws Exception {
 		try {
			UserHelper users = new UserHelper();
			users.createUser(path, fileName, sheetName, "Users", 1);
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
 	}
}