package com.subex.automation.testcases.systemtesting.security;

import org.testng.annotations.Test;

import com.subex.automation.helpers.application.ExportHelper;
import com.subex.automation.helpers.application.LoginHelper;
import com.subex.automation.helpers.application.NavigationHelper;
import com.subex.automation.helpers.component.ElementHelper;
import com.subex.automation.helpers.component.GridHelper;
import com.subex.automation.helpers.component.SearchGridHelper;
import com.subex.automation.helpers.selenium.ROCAcceptanceTest;
import com.subex.automation.helpers.util.FailureHelper;

public class testUser1 extends ROCAcceptanceTest {
	
	private static String path = null;
	final String fileName = "Security_TestData.xlsx";
	final String sheetName = "Security";
	
	public testUser1() throws Exception {
		try {
			path = automationPath + "\\src\\main\\resources\\System_Test_Flows\\";
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	@Test(priority=1, description="Login as suser1", dependsOnGroups={ "rootUser" }, groups = { "user1" })
	public void loginAsUser1() throws Exception {
		try {
			LoginHelper login = new LoginHelper();
			login.login(path, fileName, sheetName, "User1Login", 1);
			
			String[] screenNames = {"Roles", "Users"};
			NavigationHelper.checkScreens(screenNames);
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	@Test(priority=2, description="Verify users", dependsOnMethods={ "loginAsUser1" }, groups = { "user1" })
 	public void verifyUsersAsUser1() throws Exception
 	{
		try {
			String[] userNames = {"Administrator", "suser1", "suser3", "suser4"};
			NavigationHelper.navigateToScreen( "Users", "User Search" );
			
			for (int i = 0; i < userNames.length; i++) {
				int row = SearchGridHelper.searchWithTextBox("Users_Username", userNames[i], "Name");
				
				if (row > 0) {
					GridHelper.clickRow("SearchGrid", row, 1);
					NavigationHelper.navigateToAction("Common Tasks");
					assertTrue(NavigationHelper.isActionPresent("New"), "Action 'New' is not found for user '" + userNames[i] + "'");
					assertTrue(NavigationHelper.isActionPresent("Delete"), "Action 'Delete' is not found for user '" + userNames[i] + "'");
					assertFalse(NavigationHelper.isActionPresent("Edit"), "Action 'Edit' is found for user '" + userNames[i] + "'");
					assertFalse(NavigationHelper.isActionPresent("View"), "Action 'View' is found for user '" + userNames[i] + "'");
					ElementHelper.pressEscape();
				}
				else {
					FailureHelper.failTest("User '" + userNames[i] + "' is not found.");
				}
			}
		} catch (AssertionError e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	@Test(priority=3, description="Verify Export as suser1", dependsOnMethods={ "loginAsUser1" }, groups = { "administrator" })
 	public void verifyExportAsUser1() throws Exception
 	{
		try {
			NavigationHelper.navigateToScreen( "Roles", "Role Search" );
			ExportHelper export = new ExportHelper();
			String exportFileName = export.exportConfiguredRows();
			assertTrue(exportFileName.endsWith("txt"));
		} catch (AssertionError e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
}