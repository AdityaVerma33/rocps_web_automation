package com.subex.automation.testcases.regressiontesting.masking;

import java.util.ArrayList;
import java.util.HashMap;

import org.testng.annotations.Test;

import com.subex.automation.helpers.application.LoginHelper;
import com.subex.automation.helpers.application.NavigationHelper;
import com.subex.automation.helpers.application.screens.MaskingConfigurationHelper;
import com.subex.automation.helpers.application.screens.RolesHelper;
import com.subex.automation.helpers.application.screens.UserHelper;
import com.subex.automation.helpers.component.ButtonHelper;
import com.subex.automation.helpers.component.GenericHelper;
import com.subex.automation.helpers.component.GridHelper;
import com.subex.automation.helpers.component.LabelHelper;
import com.subex.automation.helpers.component.SearchGridHelper;
import com.subex.automation.helpers.component.TextBoxHelper;
import com.subex.automation.helpers.file.ExcelReader;
import com.subex.automation.helpers.util.FailureHelper;

public class testUserEdit extends testMasking {

	final String sheetName = "Users";

	public testUserEdit() throws Exception {
		super();
	}
	
	@Test(priority=9, description="Privileged user should be able to edit masked fields", groups = { "GDPRUserEdit" })
	public void testPrivilegedUserEdit() throws Exception {
		try {
			// Validate that a privileged user is able to edit and save configurations on masked fields along with modifying the masked fields
			MaskingConfigurationHelper maskingConfig = new MaskingConfigurationHelper();
			maskingConfig.createMaskingConfiguration(path, fileName, sheetName, "MaskingConfiguration", 1);

			UserHelper user = new UserHelper();
			user.createUser(path, fileName, sheetName, "User1", 1);
			
			ExcelReader excelReader = new ExcelReader();
			HashMap<String, ArrayList<String>> excelData = excelReader.readDataByColumn( path, fileName, sheetName, "User1", 1 );
			String userName = excelData.get("Username").get(0);
			
			int rowNum = GridHelper.getRowNumber("SearchGrid", userName, "Name");
			GridHelper.clickRow("SearchGrid", rowNum, 1);
			String detailScreenTitle = NavigationHelper.navigateToEdit("SearchGrid", rowNum, "Users_Username");
			
			user.updateUser(path, fileName, sheetName, "EditUser1", 1);
			user.saveUsers(userName, detailScreenTitle);
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	@Test(priority=10, description="Unprivileged user should not be able to edit masked fields", groups = { "GDPRUserEdit" })
	public void testUnprivilegedUserEdit() throws Exception {
		try {
			// Validate that a Unprivileged user is unable to edit and save configurations on masked fields without modifying the masked fields
			LoginHelper login = new LoginHelper();
			login.loginWithConfigPropertyDetails();
			
			RolesHelper roles = new RolesHelper();
			roles.createRole(path, fileName, sheetName, "Roles", 1);
			
			UserHelper user = new UserHelper();
			user.createUser(path, fileName, sheetName, "Users", 1);
			
			MaskingConfigurationHelper maskingConfig = new MaskingConfigurationHelper();
			maskingConfig.createMaskingConfiguration(path, fileName, sheetName, "MaskingConfiguration", 1);
			
			login.login(path, fileName, sheetName, "UserLogin", 1);
			
			ExcelReader excelReader = new ExcelReader();
			HashMap<String, ArrayList<String>> excelData = excelReader.readDataByColumn( path, fileName, sheetName, "User2", 1 );
			String partition = excelData.get("Partition").get(0);
			String userName = excelData.get("Username").get(0);
			String expectedUserName = userName.substring(0, 1) + "xxxx" + userName.substring(5);
			String firstName = excelData.get("First Name").get(0);
			String lastName = excelData.get("Last Name").get(0);
			String emailAddress = excelData.get("Email Address").get(0);
			
			user.navigateToUsers(userName, expectedUserName, partition);
			String detailScreenTitle = NavigationHelper.getScreenTitle();
			user.updateUser(path, fileName, sheetName, "User2", 1);
			user.saveUsers(expectedUserName, detailScreenTitle);
			
			NavigationHelper.navigateToScreen( "Users", "User Search" );
			SearchGridHelper.gridFilterSearchWithTextBox("Users_FirstName", firstName, "Forename");
			int rowNum = GridHelper.getRowNumber("SearchGrid", lastName, "Surname");
			GridHelper.clickRow("SearchGrid", rowNum, 1);
			detailScreenTitle = NavigationHelper.navigateToEdit("SearchGrid", rowNum, "Users_EmailAddress");
			TextBoxHelper.type("Users_EmailAddress", "1_" + emailAddress);
			ButtonHelper.click("SaveButton");
			GenericHelper.waitForLoadmask(detailScreenWaitSec);
			assertTrue(LabelHelper.isTitleNotPresent(detailScreenTitle), "User save did not happen.");
			
			GridHelper.clickRow("SearchGrid", rowNum, 1);
			detailScreenTitle = NavigationHelper.navigateToEdit("SearchGrid", rowNum, "Users_EmailAddress");
			assertTrue(TextBoxHelper.isValuePresent("Users_EmailAddress", "xxxxxx@subex.com"));
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
}