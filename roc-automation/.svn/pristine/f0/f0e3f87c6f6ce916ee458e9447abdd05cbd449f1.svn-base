package com.subex.automation.testcases.regressiontesting.masking;

import java.util.ArrayList;
import java.util.HashMap;

import org.testng.annotations.Test;

import com.subex.automation.helpers.application.LoginHelper;
import com.subex.automation.helpers.application.NavigationHelper;
import com.subex.automation.helpers.application.screens.MaskingConfigurationHelper;
import com.subex.automation.helpers.application.screens.RolesHelper;
import com.subex.automation.helpers.application.screens.UserHelper;
import com.subex.automation.helpers.component.SearchGridHelper;
import com.subex.automation.helpers.file.ExcelReader;
import com.subex.automation.helpers.util.FailureHelper;

public class testUserDelete extends testMasking {

	final String sheetName = "Users";

	public testUserDelete() throws Exception {
		super();
	}
	
	@Test(priority=18, description="Privileged user should be able to search based on masked fields", groups = { "GDPRUserDelete" })
	public void testPrivilegedUserDelete() throws Exception {
		try {
			// Validate that a privileged user is able to search with the masked fields in search screen
			MaskingConfigurationHelper maskingConfig = new MaskingConfigurationHelper();
			maskingConfig.createMaskingConfiguration(path, fileName, sheetName, "MaskingConfiguration", 1);

			UserHelper user = new UserHelper();
			user.createUser(path, fileName, sheetName, "User1", 1);
			
			ExcelReader excelReader = new ExcelReader();
			HashMap<String, ArrayList<String>> excelData = excelReader.readDataByColumn( path, fileName, sheetName, "User1", 1 );
			String userName = excelData.get("Username").get(0);
			
			NavigationHelper.delete("SearchGrid", userName, "Name");
			
			NavigationHelper.undelete("SearchGrid", userName, "Name");
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	@Test(priority=19, description="Unprivileged user should not be able to search based on masked fields", groups = { "GDPRUserDelete" })
	public void testUnprivilegedUserDelete() throws Exception {
		try {
			// Validate that a Unprivileged user is able to search with the masked fields in search screen
			RolesHelper roles = new RolesHelper();
			roles.createRole(path, fileName, sheetName, "Roles", 1);
			
			UserHelper user = new UserHelper();
			user.createUser(path, fileName, sheetName, "Users", 1);
			
			MaskingConfigurationHelper maskingConfig = new MaskingConfigurationHelper();
			maskingConfig.createMaskingConfiguration(path, fileName, sheetName, "MaskingConfiguration", 1);
			
			LoginHelper login = new LoginHelper();
			login.login(path, fileName, sheetName, "UserLogin", 1);
			
			ExcelReader excelReader = new ExcelReader();
			HashMap<String, ArrayList<String>> excelData = excelReader.readDataByColumn( path, fileName, sheetName, "User2", 1 );
			String partition = excelData.get("Partition").get(0);
			String userName = excelData.get("Username").get(0);
			String firstName = excelData.get("First Name").get(0);
			String expectedUserName = userName.substring(0, 1) + "xxxx" + userName.substring(5);
			
			user.navigateToUsers(userName, expectedUserName, partition);
			String detailScreenTitle = NavigationHelper.getScreenTitle();
			user.updateUser(path, fileName, sheetName, "User2", 1);
			user.saveUsers(expectedUserName, detailScreenTitle);
			
			NavigationHelper.navigateToScreen( "Users", "User Search" );
			SearchGridHelper.searchWithTextBox("Users_Username", userName, "Name");
			NavigationHelper.delete("SearchGrid", firstName, "Forename");
			
			NavigationHelper.undelete("SearchGrid", firstName, "Forename");
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
}