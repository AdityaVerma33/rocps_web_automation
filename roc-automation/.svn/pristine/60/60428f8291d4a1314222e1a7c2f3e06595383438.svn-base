package com.subex.automation.testcases.systemtesting.security;

import org.testng.annotations.Test;

import com.subex.automation.helpers.application.LoginHelper;
import com.subex.automation.helpers.application.NavigationHelper;
import com.subex.automation.helpers.application.screens.TeamHelper;
import com.subex.automation.helpers.component.ButtonHelper;
import com.subex.automation.helpers.component.ComboBoxHelper;
import com.subex.automation.helpers.component.ElementHelper;
import com.subex.automation.helpers.component.GridHelper;
import com.subex.automation.helpers.component.SearchGridHelper;
import com.subex.automation.helpers.component.TextBoxHelper;
import com.subex.automation.helpers.selenium.ROCAcceptanceTest;
import com.subex.automation.helpers.util.FailureHelper;

public class testUser2 extends ROCAcceptanceTest {
	
	private static String path = null;
	final String fileName = "Security_TestData.xlsx";
	final String sheetName = "Security";
	
	public testUser2() throws Exception {
		try {
			path = automationPath + "\\src\\main\\resources\\System_Test_Flows\\";
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	@Test(priority=1, description="Login as suser1", dependsOnGroups={ "user1" }, groups = { "user2" })
	public void loginAsUser2() throws Exception {
		try {
			LoginHelper login = new LoginHelper();
			login.login(path, fileName, sheetName, "User2Login", 1);
			
			String[] screenNames = {"Teams", "Users"};
			NavigationHelper.checkScreens(screenNames);
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	@Test(priority=2, description="Verify Users", dependsOnMethods={ "loginAsUser2" }, groups = { "user2" })
 	public void verifyUsersAsUser2() throws Exception
 	{
		try {
			String[] userNames = {"suser2"};
			NavigationHelper.navigateToScreen( "Users", "User Search" );
			
			for (int i = 0; i < userNames.length; i++) {
				int row = SearchGridHelper.searchWithTextBox("Users_Username", userNames[i], "Name");
				
				if (row > 0) {
					GridHelper.clickRow("SearchGrid", row, 1);
					NavigationHelper.navigateToAction("Common Tasks");
					assertTrue(NavigationHelper.isActionPresent("New"), "Action 'New' is not found for user '" + userNames[i] + "'");
					assertFalse(NavigationHelper.isActionPresent("Delete"), "Action 'Delete' is found for user '" + userNames[i] + "'");
					assertTrue(NavigationHelper.isActionPresent("Edit"), "Action 'Edit' is not found for user '" + userNames[i] + "'");
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
	
	@Test(priority=3, description="Verify Teams", dependsOnMethods={ "loginAsUser2" }, groups = { "user2" })
 	public void verifyTeamsAsUser2() throws Exception
 	{
		try {
			String teamName = "suser2 Team";
			String expectedUser = "suser2";
			
			NavigationHelper.navigateToScreen( "Teams", "Team Search" );
			NavigationHelper.navigateToAction("Common Tasks");
			assertTrue(NavigationHelper.isActionPresent("New"), "Action 'New' is not found for user '" + teamName + "'");
			GridHelper.click("SearchGrid");
			int row = SearchGridHelper.searchWithTextBox("Teams_Name", teamName, "Name");
			
			if (row == 0) {
				if (NavigationHelper.isActionPresent("New"))
					NavigationHelper.navigateToAction("New");
				else
					NavigationHelper.navigateToAction("Common Tasks", "New");
				NavigationHelper.selectPartition("P2");
				String detailScreenTitle = NavigationHelper.getScreenTitle();
				
				TextBoxHelper.type("Popup_Panel", "Teams_Name", teamName);
				ButtonHelper.click("Teams_Users_Add");
				GridHelper.clickRow("Teams_Users_Grid", 1, "User");
				
				if (!ComboBoxHelper.isPresent("Teams_Users_ComboBox"))
					GridHelper.clickRow("Teams_Users_Grid", 1, "User");
				assertTrue(ComboBoxHelper.containsValue("Teams_Users_ComboBox", expectedUser), "Expected User '" + expectedUser + "' is not found.");
				
				ComboBoxHelper.select("Teams_Users_ComboBox", expectedUser);
				GridHelper.clickRow("Teams_Users_Grid", 1, "Supervisor");
				ButtonHelper.click("Teams_Users_SetAsSupervisor");
				
				TeamHelper teams = new TeamHelper();
				teams.saveTeams(teamName, detailScreenTitle);
				row = SearchGridHelper.searchWithTextBox("Teams_Name", teamName, "Name");
				
				GridHelper.clickRow("SearchGrid", row, 1);
				NavigationHelper.navigateToAction("Common Tasks");
				assertTrue(NavigationHelper.isActionPresent("New"), "Action 'New' is not found for user '" + teamName + "'");
				assertTrue(NavigationHelper.isActionPresent("Edit"), "Action 'Edit' is not found for user '" + teamName + "'");
				assertTrue(NavigationHelper.isActionPresent("Delete"), "Action 'Delete' is not found for user '" + teamName + "'");
				assertFalse(NavigationHelper.isActionPresent("View"), "Action 'View' is found for user '" + teamName + "'");
				ElementHelper.pressEscape();
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