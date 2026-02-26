package com.subex.automation.testcases.systemtesting.security;

import java.util.ArrayList;
import java.util.HashMap;

import org.testng.annotations.Test;

import com.subex.automation.helpers.application.ControllerHelper;
import com.subex.automation.helpers.application.ExportHelper;
import com.subex.automation.helpers.application.LoginHelper;
import com.subex.automation.helpers.application.NavigationHelper;
import com.subex.automation.helpers.application.NotesHelper;
import com.subex.automation.helpers.application.screens.EntitiesHelper;
import com.subex.automation.helpers.application.screens.JumpToSearchHelper;
import com.subex.automation.helpers.application.screens.SettingsHelper;
import com.subex.automation.helpers.component.ButtonHelper;
import com.subex.automation.helpers.component.ElementHelper;
import com.subex.automation.helpers.component.GenericHelper;
import com.subex.automation.helpers.component.GridHelper;
import com.subex.automation.helpers.component.PropertyGridHelper;
import com.subex.automation.helpers.component.SearchGridHelper;
import com.subex.automation.helpers.component.TabHelper;
import com.subex.automation.helpers.file.ExcelReader;
import com.subex.automation.helpers.selenium.ROCAcceptanceTest;
import com.subex.automation.helpers.util.FailureHelper;

public class testAdministrator extends ROCAcceptanceTest {
	
	private static String path = null;
	final String fileName = "Security_TestData.xlsx";
	final String sheetName = "Security";
	
	public testAdministrator() throws Exception {
		try {
			path = automationPath + "\\src\\main\\resources\\System_Test_Flows\\";
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	@Test(priority=1, description="Login as Administrator", dependsOnGroups={ "user3" }, groups = { "administrator" })
	public void loginAsAdministrator() throws Exception {
		try {
			LoginHelper login = new LoginHelper();
			login.login(path, fileName, sheetName, "AdministratorLogin", 1);
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	@Test(priority=2, description="Update Settings", dependsOnMethods={ "loginAsAdministrator" }, groups = { "administrator" })
 	public void updateSettings() throws Exception
 	{
		try {
			NavigationHelper.navigateToScreen("Settings", "Edit Settings");
			GenericHelper.waitForElement("Settings_OK", searchScreenWaitSec);
			GenericHelper.waitForLoadmask(searchScreenWaitSec);
			String detailScreenTitle = NavigationHelper.getScreenTitle();
			
			assertTrue(TabHelper.isPresent("User Properties"), "Tab 'User Properties' is not found for 'Administrator'.");
			assertTrue(TabHelper.isPresent("Client Properties"), "Tab 'Client Properties' is not found for 'Administrator'.");
			assertTrue(TabHelper.isPresent("Server Properties"), "Tab 'Server Properties' is not found for 'Administrator'.");
			assertTrue(TabHelper.isPresent("System Properties"), "Tab 'System Properties' is not found for 'Administrator'.");
			
			SettingsHelper settings = new SettingsHelper();
			TabHelper.gotoTab("User Properties");
			String wrapperID = settings.getWrapperID("User Properties");
			PropertyGridHelper.selectInComboBox(wrapperID, "Export File Type *", "CSV (Comma delimited)");
			
			TabHelper.gotoTab("System Properties");
			wrapperID = settings.getWrapperID("System Properties");
			PropertyGridHelper.clickCheckBox(wrapperID, "Display Login Information", "true");
			
			settings.saveSettings(detailScreenTitle);
		} catch (AssertionError e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	@Test(priority=3, description="Verify Export as Administrator", dependsOnMethods={ "updateSettings" }, groups = { "administrator" })
 	public void verifyExport() throws Exception
 	{
		try {
			LoginHelper login = new LoginHelper();
			login.logout();
			login.login(path, fileName, sheetName, "AdministratorLogin", 1);
			
			NavigationHelper.navigateToScreen( "Roles", "Role Search" );
			ExportHelper export = new ExportHelper();
			String exportFileName = export.exportConfiguredRows();
			assertTrue(exportFileName.endsWith("csv"));
		} catch (AssertionError e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	@Test(priority=4, description="Verify Login Information as Administrator", dependsOnMethods={ "updateSettings" }, groups = { "administrator" })
 	public void verifyLoginInfo() throws Exception
 	{
		try {
			ExcelReader excelReader = new ExcelReader();
			HashMap<String, ArrayList<String>> excelData = excelReader.readDataByColumn( path, fileName, sheetName, "AdministratorLogin", 1 );
			String appName = excelData.get("Application Name").get(0);
			String userName = excelData.get("Username").get(0);
			String password = excelData.get("Reset Password").get(0);
			
			LoginHelper login = new LoginHelper();
			login.logout();
			login.setLoginDetails(appName, userName, password);
			login.acceptLoginSuccessfulPopUp(searchScreenWaitSec);
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	@Test(priority=5, description="Verify Reference Table > Country", dependsOnMethods={ "verifyLoginInfo" }, groups = { "administrator" })
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
				assertTrue(NavigationHelper.isActionPresent("New"), "Action 'New' is not found for user 'Administrator'");
				assertTrue(NavigationHelper.isActionPresent("Edit"), "Action 'Edit' is not found for user 'Administrator'");
				assertTrue(NavigationHelper.isActionPresent("Delete"), "Action 'Delete' is not found for user 'Administrator'");
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
	
	@Test(priority=6, description="Verify Reference Table > Currency", dependsOnMethods={ "verifyReferenceTableCountry" }, groups = { "administrator" })
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
				assertTrue(NavigationHelper.isActionPresent("New"), "Action 'New' is not found for user 'Administrator'");
				assertTrue(NavigationHelper.isActionPresent("Edit"), "Action 'Edit' is not found for user 'Administrator'");
				assertTrue(NavigationHelper.isActionPresent("Delete"), "Action 'Delete' is not found for user 'Administrator'");
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
	
	@Test(priority=7, description="Verify Jump To Search", dependsOnMethods={ "verifyReferenceTableCurrency" }, groups = { "administrator" })
 	public void verifyJumpToSearch() throws Exception
 	{
		try {
			JumpToSearchHelper jumpToSearch = new JumpToSearchHelper();
			jumpToSearch.verifyJumpToSearchResult(path, fileName, sheetName, "J2S", 1);
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	@Test(priority=8, description="Enable Notes for Roles as Administrator", dependsOnMethods={ "verifyJumpToSearch" }, groups = { "administrator" })
 	public void enableNotes() throws Exception
 	{
		try {
			EntitiesHelper entities = new EntitiesHelper();
 			entities.editEntity(path, fileName, sheetName, "Entities", 1);
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	@Test(priority=9, description="Add Notes to Roles as Administrator", dependsOnMethods={ "enableNotes" }, groups = { "administrator" })
 	public void addNotes() throws Exception
 	{
		try {
			NavigationHelper.navigateToScreen( "Roles", "Role Search" );
			int row = SearchGridHelper.gridFilterSearchWithTextBox("Roles_RoleName", "Administrator", "Name");
			
			if (row > 0) {
				GridHelper.clickRow("SearchGrid", row, 1);
				NotesHelper notes = new NotesHelper();
				notes.addNotes(path, fileName, sheetName, "Notes", 1);
				
				ButtonHelper.click("SearchButton");
				GenericHelper.waitForLoadmask(searchScreenWaitSec);
				GridHelper.clickRow("SearchGrid", row, 1);
				
				ExcelReader excelReader = new ExcelReader();
				HashMap<String, ArrayList<String>> excelData = excelReader.readDataByColumn( path, fileName, sheetName, "Notes", 1 );
				String noteName = excelData.get("Note Name").get(0);
				NavigationHelper.navigateToAction("Notes");
				assertTrue(NavigationHelper.isActionPresent(noteName));
			}
			else {
				FailureHelper.failTest("Role 'Administrator' is not found for Administrator user.");
			}
		} catch (AssertionError e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	@Test(priority=10, description="Reset Settings", dependsOnMethods={ "enableNotes" }, groups = { "administrator" })
 	public void resetSettings() throws Exception
 	{
		try {
			NavigationHelper.navigateToScreen("Settings", "Edit Settings");
			GenericHelper.waitForElement("Settings_OK", searchScreenWaitSec);
			GenericHelper.waitForLoadmask(searchScreenWaitSec);
			String detailScreenTitle = NavigationHelper.getScreenTitle();
			
			SettingsHelper settings = new SettingsHelper();
			String wrapperID = settings.getWrapperID("User Properties");
			
			TabHelper.gotoTab("System Properties");
			wrapperID = settings.getWrapperID("System Properties");
			PropertyGridHelper.clickCheckBox(wrapperID, "Display Login Information", "false");
			
			settings.saveSettings(detailScreenTitle);
			
			ControllerHelper controller = new ControllerHelper();
			controller.restartTomcat();
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
}