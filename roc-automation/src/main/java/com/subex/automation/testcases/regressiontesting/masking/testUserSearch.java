package com.subex.automation.testcases.regressiontesting.masking;

import java.util.ArrayList;
import java.util.HashMap;

import org.testng.annotations.Test;

import com.subex.automation.helpers.application.LoginHelper;
import com.subex.automation.helpers.application.NavigationHelper;
import com.subex.automation.helpers.application.screens.MaskingConfigurationHelper;
import com.subex.automation.helpers.application.screens.RolesHelper;
import com.subex.automation.helpers.application.screens.UserHelper;
import com.subex.automation.helpers.component.GridHelper;
import com.subex.automation.helpers.component.SearchGridHelper;
import com.subex.automation.helpers.file.ExcelReader;
import com.subex.automation.helpers.util.FailureHelper;

public class testUserSearch extends testMasking {

	final String sheetName = "Users";

	public testUserSearch() throws Exception {
		super();
	}
	
	@Test(priority=11, description="Privileged user should be able to search based on masked fields", groups = { "GDPRUserSearch" })
	public void testPrivilegedUserSearch() throws Exception {
		try {
			// Validate that a privileged user is able to search with the masked fields in search screen
			MaskingConfigurationHelper maskingConfig = new MaskingConfigurationHelper();
			maskingConfig.createMaskingConfiguration(path, fileName, sheetName, "MaskingConfiguration", 1);

			UserHelper user = new UserHelper();
			user.createUser(path, fileName, sheetName, "User1", 1);
			
			ExcelReader excelReader = new ExcelReader();
			HashMap<String, ArrayList<String>> excelData = excelReader.readDataByColumn( path, fileName, sheetName, "User1", 1 );
			String userName = excelData.get("Username").get(0);
			String emailAddress = excelData.get("Email Address").get(0);
			
			int rowNum = SearchGridHelper.searchWithTextBox("Users_Username", userName, "Name");
			assertTrue(rowNum > 0);
			assertTrue(GridHelper.isValuePresent("SearchGrid", rowNum, emailAddress, "Email Address"));
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	@Test(priority=12, description="Unprivileged user should not be able to search based on masked fields", groups = { "GDPRUserSearch" })
	public void testUnprivilegedUserSearch() throws Exception {
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
			String emailAddress = excelData.get("Email Address").get(0);
			String expectedUserName = userName.substring(0, 1) + "xxxx" + userName.substring(5);
			String expectedEmailAddress = "xxxxxx" + emailAddress.substring(6);
			
			user.navigateToUsers(userName, expectedUserName, partition);
			String detailScreenTitle = NavigationHelper.getScreenTitle();
			user.updateUser(path, fileName, sheetName, "User2", 1);
			user.saveUsers(expectedUserName, detailScreenTitle);
			
			NavigationHelper.navigateToScreen( "Users", "User Search" );
			int rowNum = SearchGridHelper.searchWithTextBox("Users_Username", userName, "Name");
			assertTrue(rowNum == 0);
			assertFalse(GridHelper.isValuePresent("SearchGrid", userName, "Name"));
			assertTrue(GridHelper.isValuePresent("SearchGrid", expectedUserName, "Name"));
			assertFalse(GridHelper.isValuePresent("SearchGrid", emailAddress, "Email Address"));
			assertTrue(GridHelper.isValuePresent("SearchGrid", expectedEmailAddress, "Email Address"));
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
}