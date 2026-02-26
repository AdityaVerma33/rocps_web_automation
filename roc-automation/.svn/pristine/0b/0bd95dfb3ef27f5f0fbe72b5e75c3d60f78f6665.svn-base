package com.subex.automation.testcases.regressiontesting.masking;

import java.util.ArrayList;
import java.util.HashMap;

import org.testng.annotations.Test;

import com.subex.automation.helpers.application.ExportHelper;
import com.subex.automation.helpers.application.LoginHelper;
import com.subex.automation.helpers.application.NavigationHelper;
import com.subex.automation.helpers.application.screens.MaskingConfigurationHelper;
import com.subex.automation.helpers.application.screens.RolesHelper;
import com.subex.automation.helpers.application.screens.SettingsHelper;
import com.subex.automation.helpers.application.screens.UserHelper;
import com.subex.automation.helpers.component.FileHelper;
import com.subex.automation.helpers.component.SearchGridHelper;
import com.subex.automation.helpers.file.ExcelReader;
import com.subex.automation.helpers.util.FailureHelper;

public class testUserExport extends testMasking {

	final String sheetName = "Users";

	public testUserExport() throws Exception {
		super();
	}
	
	@Test(priority=13, description="Privileged user export of masked fields", groups = { "GDPRUserExport" })
	public void testPrivilegedUserExport() throws Exception {
		try {
			// Validate that a privileged user is able to export the masked fields without any masking on data in search screen
			MaskingConfigurationHelper maskingConfig = new MaskingConfigurationHelper();
			maskingConfig.createMaskingConfiguration(path, fileName, sheetName, "MaskingConfiguration", 1);

			UserHelper user = new UserHelper();
			user.createUser(path, fileName, sheetName, "User1", 1);
			
			ExcelReader excelReader = new ExcelReader();
			HashMap<String, ArrayList<String>> excelData = excelReader.readDataByColumn( path, fileName, sheetName, "User1", 1 );
			String userName = excelData.get("Username").get(0);
			String firstName = excelData.get("First Name").get(0);
			String emailAddress = excelData.get("Email Address").get(0);
			String expectedUserName = userName;
			String expectedEmailAddress = emailAddress;
			
			int rowNum = SearchGridHelper.gridFilterSearchWithTextBox("Users_FirstName", firstName, "Forename");
			assertTrue(rowNum > 0);
			ExportHelper export = new ExportHelper();
			String fileName = export.exportSelectedRows("SearchGrid", rowNum, "Name");
			String[] content = FileHelper.readFileContent(automationOS, fileName);
			assertTrue(content[1].contains(expectedUserName), "Expected value '" + expectedUserName + "' is not found in Export file.");
			assertTrue(content[1].contains(expectedEmailAddress), "Expected value '" + expectedEmailAddress + "' is not found in Export file.");
			
			fileName = export.exportConfiguredRows("SearchGrid");
			content = FileHelper.readFileContent(automationOS, fileName);
			assertTrue(content[1].contains(expectedUserName), "Expected value '" + expectedUserName + "' is not found in Export file.");
			assertTrue(content[1].contains(expectedEmailAddress), "Expected value '" + expectedEmailAddress + "' is not found in Export file.");
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	@Test(priority=14, description="Unprivileged user export of masked fields", groups = { "GDPRUserExport" })
	public void testUnprivilegedUserExport() throws Exception {
		try {
			// Validate that a Unprivileged user is able to export the masked fields but view with masking in search screen
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
			String emailAddress = excelData.get("Email Address").get(0);
			String expectedUserName = userName.substring(0, 1) + "xxxx" + userName.substring(5);
			String expectedEmailAddress = "xxxxxx" + emailAddress.substring(6);
			
			user.navigateToUsers(userName, expectedUserName, partition);
			String detailScreenTitle = NavigationHelper.getScreenTitle();
			user.updateUser(path, fileName, sheetName, "User2", 1);
			user.saveUsers(expectedUserName, detailScreenTitle);
			
			NavigationHelper.navigateToScreen( "Users", "User Search" );
			int rowNum = SearchGridHelper.gridFilterSearchWithTextBox("Users_FirstName", firstName, "Forename");
			assertTrue(rowNum > 0);
			ExportHelper export = new ExportHelper();
			String fileName = export.exportSelectedRows("SearchGrid", rowNum, "Name");
			String[] content = FileHelper.readFileContent(automationOS, fileName);
			assertTrue(content[1].contains(expectedUserName), "Expected value '" + expectedUserName + "' is not found in Export file.");
			assertTrue(content[1].contains(expectedEmailAddress), "Expected value '" + expectedEmailAddress + "' is not found in Export file.");
			
			fileName = export.exportConfiguredRows("SearchGrid");
			content = FileHelper.readFileContent(automationOS, fileName);
			assertTrue(content[1].contains(expectedUserName), "Expected value '" + expectedUserName + "' is not found in Export file.");
			assertTrue(content[1].contains(expectedEmailAddress), "Expected value '" + expectedEmailAddress + "' is not found in Export file.");
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	@Test(priority=15, description="Unprivileged user export of masked fields", groups = { "GDPRUserExport" })
	public void testUnprivilegedUserCSVExport() throws Exception {
		try {
			// Validate that a Unprivileged user is able to export as CSV of the masked fields but view with masking in search screen
			LoginHelper login = new LoginHelper();
			login.login(path, fileName, sheetName, "UserLogin", 1);
			
			SettingsHelper settings = new SettingsHelper();
			settings.updateSettings(path, fileName, sheetName, "UpdateSettings", 1);
			
			ExcelReader excelReader = new ExcelReader();
			HashMap<String, ArrayList<String>> excelData = excelReader.readDataByColumn( path, fileName, sheetName, "User2", 1 );
			String partition = excelData.get("Partition").get(0);
			String userName = excelData.get("Username").get(0);
			String firstName = excelData.get("First Name").get(0);
			String emailAddress = excelData.get("Email Address").get(0);
			String expectedUserName = userName.substring(0, 1) + "xxxx" + userName.substring(5);
			String expectedEmailAddress = "xxxxxx" + emailAddress.substring(6);
			
			UserHelper user = new UserHelper();
			user.navigateToUsers(userName, expectedUserName, partition);
			String detailScreenTitle = NavigationHelper.getScreenTitle();
			user.updateUser(path, fileName, sheetName, "User2", 1);
			user.saveUsers(expectedUserName, detailScreenTitle);
			
			NavigationHelper.navigateToScreen( "Users", "User Search" );
			int rowNum = SearchGridHelper.gridFilterSearchWithTextBox("Users_FirstName", firstName, "Forename");
			assertTrue(rowNum > 0);
			ExportHelper export = new ExportHelper();
			String fileName = export.exportSelectedRows("SearchGrid", rowNum, "Name");
			String[] content = FileHelper.readFileContent(automationOS, fileName);
			assertTrue(content[1].contains(expectedUserName), "Expected value '" + expectedUserName + "' is not found in Export file.");
			assertTrue(content[1].contains(expectedEmailAddress), "Expected value '" + expectedEmailAddress + "' is not found in Export file.");
			
			fileName = export.exportConfiguredRows("SearchGrid");
			content = FileHelper.readFileContent(automationOS, fileName);
			assertTrue(content[1].contains(expectedUserName), "Expected value '" + expectedUserName + "' is not found in Export file.");
			assertTrue(content[1].contains(expectedEmailAddress), "Expected value '" + expectedEmailAddress + "' is not found in Export file.");
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
}