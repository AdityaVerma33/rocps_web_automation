package com.subex.automation.testcases.systemtesting.security;
 
import java.util.ArrayList;

import org.testng.annotations.Test;

import com.subex.automation.helpers.application.LoginHelper;
import com.subex.automation.helpers.application.NavigationHelper;
import com.subex.automation.helpers.application.screens.UserHelper;
import com.subex.automation.helpers.component.ComboBoxHelper;
import com.subex.automation.helpers.component.ElementHelper;
import com.subex.automation.helpers.component.GridCheckBoxHelper;
import com.subex.automation.helpers.component.GridHelper;
import com.subex.automation.helpers.component.LabelHelper;
import com.subex.automation.helpers.component.SearchGridHelper;
import com.subex.automation.helpers.component.TabHelper;
import com.subex.automation.helpers.component.TextBoxHelper;
import com.subex.automation.helpers.selenium.ROCAcceptanceTest;
import com.subex.automation.helpers.util.FailureHelper;
 
public class testRootUser extends ROCAcceptanceTest {
	 
	private static String path = null;
	final String fileName = "Security_TestData.xlsx";
	final String sheetName = "Security";
	
	public testRootUser() throws Exception {
		try {
			path = automationPath + "\\src\\main\\resources\\System_Test_Flows\\";
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	@Test(priority=1, description="Update Password of suser4", dependsOnGroups={ "prerequisite" }, groups = { "rootUser" })
 	public void updatePassword() throws Exception
 	{
		try {
	 		LoginHelper login = new LoginHelper();
	 		login.acceptLoginSuccessfulPopUp(searchScreenWaitSec);
	 		
	 		UserHelper users = new UserHelper();
	 		users.changePassword(path, fileName, sheetName, "ChangePassword", 1);
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	@Test(priority=2, description="Update Root user details", dependsOnMethods={ "updatePassword" }, groups = { "rootUser" })
 	public void editRootUser() throws Exception
 	{
		try {
			String userName = "Root";
			NavigationHelper.navigateToScreen( "Users", "User Search" );
			int row = SearchGridHelper.searchWithTextBox("Users_Username", userName, "Name");
			
			if (row > 0) {
				NavigationHelper.navigateToEdit("SearchGrid", row, "Users_NewPassword");
				assertTrue(LabelHelper.isTitlePresent("Edit User"));
				
				assertTrue(TextBoxHelper.isDisabled("Users_Username"));
				assertTrue(TextBoxHelper.isDisabled("Users_NewPassword"));
				assertTrue(TextBoxHelper.isEnabled("Users_FirstName"));
				assertTrue(TextBoxHelper.isEnabled("Users_LastName"));
				assertTrue(TextBoxHelper.isEnabled("Users_PhoneNo"));
				assertTrue(TextBoxHelper.isEnabled("Users_EmailAddress"));
				assertTrue(TextBoxHelper.isEnabled("Users_Description"));
				assertTrue(TextBoxHelper.isEnabled("Users_AllowedMachines"));
				assertTrue(TextBoxHelper.isDisabled("Users_AccountExpiry"));
				assertTrue(TextBoxHelper.isEnabled("Users_ContactAddress"));
				assertTrue(ComboBoxHelper.isEnabled("Users_DiceGroup"));
				assertTrue(ComboBoxHelper.isDisabled("Users_UserPasswordValidation"));
				
				TabHelper.gotoTab("Roles And Partitions");
				ArrayList<String> roles = GridHelper.getColumnValues("Users_RolePrivileges_Grid", 1);
				String[] partitions = {"Common", "P1", "P2"};
				
				for (int i = 0; i < roles.size(); i++) {
					for (int j = 0; j < partitions.length; j++) {
						String checkBoxLocator = or.getProperty("Users_RolePrivileges_CheckBox").replace("partition", partitions[j]);
						
						if (GridCheckBoxHelper.isChecked("Users_RolePrivileges_Grid", checkBoxLocator, (i+1), partitions[j])) {
							GridCheckBoxHelper.uncheck("Users_RolePrivileges_Grid", checkBoxLocator, (i+1), partitions[j]);
							assertTrue(GridCheckBoxHelper.isChecked("Users_RolePrivileges_Grid", checkBoxLocator, (i+1), partitions[j]));
						}
						else {
							GridCheckBoxHelper.check("Users_RolePrivileges_Grid", checkBoxLocator, (i+1), partitions[j]);
							assertFalse(GridCheckBoxHelper.isChecked("Users_RolePrivileges_Grid", checkBoxLocator, (i+1), partitions[j]));
						}
					}
				}
			}
			else {
				FailureHelper.failTest("User '" + userName + "' is not found.");
			}
		} catch (AssertionError e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	@Test(priority=3, description="Delete Root user", dependsOnMethods={ "editRootUser" }, groups = { "rootUser" })
 	public void deleteRootUser() throws Exception
 	{
		try {
			String userName = "Root";
			NavigationHelper.navigateToScreen( "Users", "User Search" );
			int row = SearchGridHelper.searchWithTextBox("Users_Username", userName, "Name");
			
			if (row > 0) {
				GridHelper.clickRow("SearchGrid", row, 1);
				NavigationHelper.navigateToAction("Common Tasks");
				assertFalse(NavigationHelper.isActionPresent("Delete"));
				ElementHelper.pressEscape();
			}
			else {
				FailureHelper.failTest("User '" + userName + "' is not found.");
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