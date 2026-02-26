package com.subex.automation.testcases.systemtesting.security;

import org.testng.annotations.Test;

import com.subex.automation.helpers.application.LoginHelper;
import com.subex.automation.helpers.application.NavigationHelper;
import com.subex.automation.helpers.application.screens.TeamHelper;
import com.subex.automation.helpers.component.ButtonHelper;
import com.subex.automation.helpers.component.ComboBoxHelper;
import com.subex.automation.helpers.component.ElementHelper;
import com.subex.automation.helpers.component.GenericHelper;
import com.subex.automation.helpers.component.GridHelper;
import com.subex.automation.helpers.component.SearchGridHelper;
import com.subex.automation.helpers.component.TextBoxHelper;
import com.subex.automation.helpers.selenium.ROCAcceptanceTest;
import com.subex.automation.helpers.util.FailureHelper;

public class testUser3 extends ROCAcceptanceTest {
	
	private static String path = null;
	final String fileName = "Security_TestData.xlsx";
	final String sheetName = "Security";
	
	public testUser3() throws Exception {
		try {
			path = automationPath + "\\src\\main\\resources\\System_Test_Flows\\";
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	@Test(priority=1, description="Login as suser1", dependsOnGroups={ "user2" }, groups = { "user3" })
	public void loginAsUser3() throws Exception {
		try {
			LoginHelper login = new LoginHelper();
			login.login(path, fileName, sheetName, "User3Login", 1);
			
			String[] screenNames = {"Roles", "Teams", "Users"};
			NavigationHelper.checkScreens(screenNames);
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	@Test(priority=2, description="Verify User1", dependsOnMethods={ "loginAsUser3" }, groups = { "user3" })
 	public void verifyUser1AsUser3() throws Exception
 	{
		try {
			String userName = "suser1";
			NavigationHelper.navigateToScreen( "Users", "User Search" );
			int row = SearchGridHelper.searchWithTextBox("Users_Username", userName, "Name");
			
			if (row > 0) {
				GridHelper.clickRow("SearchGrid", row, 1);
				NavigationHelper.navigateToAction("Common Tasks");
				assertTrue(NavigationHelper.isActionPresent("New"), "Action 'New' is not found for user '" + userName + "'");
				assertTrue(NavigationHelper.isActionPresent("Delete"), "Action 'Delete' is not found for user '" + userName + "'");
				assertFalse(NavigationHelper.isActionPresent("Edit"), "Action 'Edit' is found for user '" + userName + "'");
				assertFalse(NavigationHelper.isActionPresent("View"), "Action 'View' is found for user '" + userName + "'");
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
	
	@Test(priority=3, description="Verify User2", dependsOnMethods={ "loginAsUser3" }, groups = { "user3" })
 	public void verifyUser2AsUser3() throws Exception
 	{
		try {
			String userName = "suser2";
			NavigationHelper.navigateToScreen( "Users", "User Search" );
			int row = SearchGridHelper.searchWithTextBox("Users_Username", userName, "Name");
			
			if (row > 0) {
				GridHelper.clickRow("SearchGrid", row, 1);
				NavigationHelper.navigateToAction("Common Tasks");
				assertTrue(NavigationHelper.isActionPresent("New"), "Action 'New' is not found for user '" + userName + "'");
				assertTrue(NavigationHelper.isActionPresent("Delete"), "Action 'Delete' is not found for user '" + userName + "'");
				assertFalse(NavigationHelper.isActionPresent("Edit"), "Action 'Edit' is found for user '" + userName + "'");
				assertFalse(NavigationHelper.isActionPresent("View"), "Action 'View' is found for user '" + userName + "'");
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
	
	@Test(priority=4, description="Verify Teams", dependsOnMethods={ "loginAsUser3" }, groups = { "user3" })
 	public void verifyTeamsAsUser3() throws Exception
 	{
		try {
			String teamName = "suser3 Team";
			String[] expectedUsers = {"Administrator", "suser1", "suser2", "suser3", "suser4"};
			
			NavigationHelper.navigateToScreen( "Teams", "Team Search" );
			NavigationHelper.navigateToAction("Common Tasks");
			assertTrue(NavigationHelper.isActionPresent("New"));
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
				for (int i = 0; i < expectedUsers.length; i++)
					assertTrue(ComboBoxHelper.containsValue("Teams_Users_ComboBox", expectedUsers[i]), "Expected User '" + expectedUsers[i] + "' is not found.");
				
				ComboBoxHelper.select("Teams_Users_ComboBox", expectedUsers[0]);
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
	
	@Test(priority=5, description="Verify Reference Table > Country", dependsOnMethods={ "loginAsUser3" }, groups = { "user3" })
 	public void verifyReferenceTableCountry() throws Exception
 	{
		try {
			NavigationHelper.navigateToReferenceTable("Country");
			ButtonHelper.click("SearchButton");
			GenericHelper.waitForLoadmask(searchScreenWaitSec);
			int rows = GridHelper.getRowCount("SearchGrid");
			
			if (rows > 0) {
				GridHelper.clickRow("SearchGrid", 1, 1);
				NavigationHelper.navigateToAction("Common Tasks");
				assertTrue(NavigationHelper.isActionPresent("New"), "Action 'New' is not found for user 'suser3'");
				assertTrue(NavigationHelper.isActionPresent("Edit"), "Action 'Edit' is not found for user 'suser3'");
				assertTrue(NavigationHelper.isActionPresent("Delete"), "Action 'Delete' is not found for user 'suser3'");
				ElementHelper.pressEscape();
				GridHelper.click("SearchGrid");
			}
			else {
				FailureHelper.failTest("No rows found in Reference Table > Country");
			}
		} catch (AssertionError e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	@Test(priority=6, description="Verify Reference Table > Currency", dependsOnMethods={ "loginAsUser3" }, groups = { "user3" })
 	public void verifyReferenceTableCurrency() throws Exception
 	{
		try {
			NavigationHelper.navigateToReferenceTable("Currency");
			ButtonHelper.click("SearchButton");
			GenericHelper.waitForLoadmask(searchScreenWaitSec);
			int rows = GridHelper.getRowCount("SearchGrid");
			
			if (rows > 0) {
				GridHelper.clickRow("SearchGrid", 1, 1);
				NavigationHelper.navigateToAction("Common Tasks");
				assertFalse(NavigationHelper.isActionPresent("New"), "Action 'New' is found for user 'suser3'");
				assertTrue(NavigationHelper.isActionPresent("Edit"), "Action 'Edit' is not found for user 'suser3'");
				assertFalse(NavigationHelper.isActionPresent("Delete"), "Action 'Delete' is found for user 'suser3'");
				ElementHelper.pressEscape();
				GridHelper.click("SearchGrid");
			}
			else {
				FailureHelper.failTest("No rows found in Reference Table > Currency");
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