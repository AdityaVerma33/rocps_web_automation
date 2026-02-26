package com.subex.automation.testcases.regressiontesting.usermanagement.usermgnt_issues;

import java.util.ArrayList;
import java.util.HashMap;

import org.testng.annotations.Test;

import com.subex.automation.helpers.application.LoginHelper;
import com.subex.automation.helpers.application.NavigationHelper;
import com.subex.automation.helpers.application.screens.RolesHelper;
import com.subex.automation.helpers.application.screens.UserHelper;
import com.subex.automation.helpers.component.ButtonHelper;
import com.subex.automation.helpers.component.GenericHelper;
import com.subex.automation.helpers.component.GridHelper;
import com.subex.automation.helpers.component.PopupHelper;
import com.subex.automation.helpers.component.SearchGridHelper;
import com.subex.automation.helpers.component.TabHelper;
import com.subex.automation.helpers.file.ExcelReader;
import com.subex.automation.helpers.util.FailureHelper;

public class test147534 extends testUserManagement {
	
	final String sheetName = "test147534";
	
	public test147534() throws Exception {
		super();
	}
	
	@Test(priority=1, description="Test case 1 of Bug 147534", groups = { "deleteRole" })
	public void testCase1() throws Exception {
		try {
			// If a role is deleted, verify if while creating a User that role does not appear in Role Privileges tab
			RolesHelper roles = new RolesHelper();
			roles.createRole(path, fileName, sheetName, "Roles", 1);
			
			ExcelReader excelReader = new ExcelReader();
			HashMap<String, ArrayList<String>> excelData = excelReader.readDataByColumn( path, fileName, sheetName, "Roles", 1 );
			String roleName = excelData.get("Role Name").get(0);
			
			NavigationHelper.navigateToScreen( "Roles", "Role Search" );
			NavigationHelper.delete("SearchGrid", roleName, "Name");
			NavigationHelper.navigateToScreen( "Users", "User Search" );
			NavigationHelper.navigateToNew("Common", "Users_FirstName");
			TabHelper.gotoTab("Roles And Partitions");
			assertFalse(GridHelper.isValuePresent("Users_RolePrivileges_Grid", roleName, "Role"));
		} catch (AssertionError e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	@Test(priority=2, description="Test case 2 of Bug 147534", dependsOnMethods = { "testCase1" }, groups = { "deleteRole" })
	public void testCase2() throws Exception {
		try {
			// If a role is deleted, verify if User with only that role does not see any screen on login
			ExcelReader excelReader = new ExcelReader();
			HashMap<String, ArrayList<String>> excelData = excelReader.readDataByColumn( path, fileName, sheetName, "Roles", 1 );
			String roleName = excelData.get("Role Name").get(0);
			
			NavigationHelper.navigateToScreen( "Roles", "Role Search" );
			NavigationHelper.undelete("SearchGrid", roleName, "Name");
			
			UserHelper users = new UserHelper();
			users.createUser(path, fileName, sheetName, "Users", 1);
			
			NavigationHelper.navigateToScreen( "Roles", "Role Search" );
			NavigationHelper.delete("SearchGrid", roleName, "Name");
			
			LoginHelper login = new LoginHelper();
			login.login(path, fileName, sheetName, "UserLogin", 1);
			GenericHelper.waitForElement("OKButton", searchScreenWaitSec);
			assertTrue(PopupHelper.isTextPresent("You do not have sufficient role privileges. Please contact system administrator."));
			ButtonHelper.click("OKButton");
			GenericHelper.waitForLoadmask(searchScreenWaitSec);
			GenericHelper.waitForElement("Login_Username_TextBox", searchScreenWaitSec);
		} catch (AssertionError e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		} finally {
			LoginHelper login = new LoginHelper();
			login.loginWithConfigPropertyDetails();
		}
	}
	
	@Test(priority=3, description="Test case 3 of Bug 147534", dependsOnMethods = { "testCase2" }, groups = { "deleteRole" })
	public void testCase3() throws Exception {
		try {
			// If a role is deleted, verify if User with that role and some other role does not see screens of deleted role
			// (if the screens are specific to that role). Screens common in both the role should be visible
			ExcelReader excelReader = new ExcelReader();
			HashMap<String, ArrayList<String>> excelData = excelReader.readDataByColumn( path, fileName, sheetName, "Roles", 1 );
			String roleName = excelData.get("Role Name").get(0);
			
			NavigationHelper.navigateToScreen( "Roles", "Role Search" );
			NavigationHelper.undelete("SearchGrid", roleName, "Name");
			
			RolesHelper roles = new RolesHelper();
			roles.createRole(path, fileName, sheetName, "Roles", 2);
			
			UserHelper users = new UserHelper();
			users.createUser(path, fileName, sheetName, "Users", 2);
			
			NavigationHelper.navigateToScreen( "Roles", "Role Search" );
			SearchGridHelper.gridFilterSearchWithTextBox("Roles_RoleName", roleName, "Name");
			NavigationHelper.delete("SearchGrid", roleName, "Name");
			
			LoginHelper login = new LoginHelper();
			login.login(path, fileName, sheetName, "UserLogin", 2);
			
			String[] groupNames = {"Monitoring", "Tariffs"};
			String[][] screenNames = {{"Task Search", "Collected Files", "Collected Files Summary", "Alerts", "Case Generation Requests"},
									{"Bands", "Elements", "Routes", "Tariff Classes", "Tariffs"}};
			NavigationHelper.checkScreens(groupNames, screenNames);
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		} finally {
			LoginHelper login = new LoginHelper();
			login.loginWithConfigPropertyDetails();
		}
	}
	
	@Test(priority=4, description="Test case 4 of Bug 147534", dependsOnMethods = { "testCase3" }, groups = { "deleteRole" })
	public void testCase4() throws Exception {
		try {
			// If a role is undeleted, verify if while creating a User that role appears in Role Privileges tab
			ExcelReader excelReader = new ExcelReader();
			HashMap<String, ArrayList<String>> excelData = excelReader.readDataByColumn( path, fileName, sheetName, "Roles", 1 );
			String roleName = excelData.get("Role Name").get(0);
			
			NavigationHelper.navigateToScreen( "Roles", "Role Search" );
			NavigationHelper.undelete("SearchGrid", roleName, "Name");
			NavigationHelper.navigateToScreen( "Users", "User Search" );
			NavigationHelper.navigateToNew("Common", "Users_FirstName");
			TabHelper.gotoTab("Roles And Partitions");
			assertTrue(GridHelper.isValuePresent("Users_RolePrivileges_Grid", roleName, "Role"));
		} catch (AssertionError e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	@Test(priority=5, description="Test case 5 of Bug 147534", dependsOnMethods = { "testCase4" }, groups = { "deleteRole" })
	public void testCase5() throws Exception {
		try {
			// If a role is undeleted, verify if User with only that role is able to see all the screens of that role on login
			LoginHelper login = new LoginHelper();
			login.login(path, fileName, sheetName, "UserLogin", 1);
			assertFalse(PopupHelper.isTextPresent("You do not have sufficient role privileges. Please contact system administrator."));
			
			String[] groupNames = {"Admin"};
			String[][] screenNames = {{"Ageing", "Alert Groups", "Audit Trails", "Day Groups", "Entities", "Entity Export", "Entity Groups",
									"Entity Import", "Standard Expressions", "Table Definitions", "Table Instances", "Table Instance Groups"}};
			NavigationHelper.checkScreens(groupNames, screenNames);
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	@Test(priority=6, description="Test case 6 of Bug 147534", dependsOnMethods = { "testCase5" }, groups = { "deleteRole" })
	public void testCase6() throws Exception {
		try {
			// If a role is undeleted, verify if User with that role and some other role is able to see screens of both the roles
			LoginHelper login = new LoginHelper();
			login.login(path, fileName, sheetName, "UserLogin", 2);
			
			String[] groupNames = {"Monitoring", "Tariffs", "Admin"};
			String[][] screenNames = {{"Task Search", "Collected Files", "Collected Files Summary", "Alerts", "Case Generation Requests"},
									{"Bands", "Elements", "Routes", "Tariff Classes", "Tariffs"},
									{"Ageing", "Alert Groups", "Audit Trails", "Day Groups", "Entities", "Entity Export", "Entity Groups",
									"Entity Import", "Standard Expressions", "Table Definitions", "Table Instances", "Table Instance Groups"}};
			NavigationHelper.checkScreens(groupNames, screenNames);
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
}