package com.subex.automation.testcases.regressiontesting.usermanagement.usermgnt_issues;

import java.util.ArrayList;
import java.util.HashMap;

import org.testng.annotations.Test;

import com.subex.automation.helpers.application.NavigationHelper;
import com.subex.automation.helpers.application.screens.RolesHelper;
import com.subex.automation.helpers.component.ButtonHelper;
import com.subex.automation.helpers.component.ComboBoxHelper;
import com.subex.automation.helpers.component.GenericHelper;
import com.subex.automation.helpers.component.GridHelper;
import com.subex.automation.helpers.component.SearchGridHelper;
import com.subex.automation.helpers.file.ExcelReader;
import com.subex.automation.helpers.util.FailureHelper;

public class test149428 extends testUserManagement {
	
	final String sheetName = "test149428";
	
	public test149428() throws Exception {
		super();
	}
	
	@Test(priority=1, description="Test case 1 of Bug 149428", groups = { "deletedRole" })
	public void testCase1() throws Exception {
		try {
			// In Roles search screen, by default deleted roles should not be visible
			RolesHelper roles = new RolesHelper();
			roles.createRole(path, fileName, sheetName, "Roles", 1);
			
			ExcelReader excelReader = new ExcelReader();
			HashMap<String, ArrayList<String>> excelData = excelReader.readDataByColumn( path, fileName, sheetName, "Roles", 1 );
			String roleName = excelData.get("Role Name").get(0);
			
			NavigationHelper.navigateToScreen( "Roles", "Role Search" );
			SearchGridHelper.gridFilterSearchWithTextBox("Roles_RoleName", "Role 149428", "Name");
			NavigationHelper.delete("SearchGrid", roleName, "Name");
			NavigationHelper.navigateToScreen( "Users", "User Search" );
			
			NavigationHelper.navigateToScreen( "Roles", "Role Search" );
			ButtonHelper.click("ClearButton");
			GenericHelper.waitForLoadmask(searchScreenWaitSec);
			ComboBoxHelper.select("Include_Combo", "Non Deleted Items");
			SearchGridHelper.gridFilterSearchWithTextBox("Roles_RoleName", "Role 149428", "Name");
			assertFalse(GridHelper.isValuePresent("SearchGrid", roleName, "Name"));
		} catch (AssertionError e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	@Test(priority=2, description="Test case 2 of Bug 149428", dependsOnMethods = { "testCase1" }, groups = { "deletedRole" })
	public void testCase2() throws Exception {
		try {
			// In Roles search screen, on search based on Include filter as "All", deleted roles should be visible
			ExcelReader excelReader = new ExcelReader();
			HashMap<String, ArrayList<String>> excelData = excelReader.readDataByColumn( path, fileName, sheetName, "Roles", 1 );
			ArrayList<String> roleName = excelData.get("Role Name");
			
			NavigationHelper.navigateToScreen( "Roles", "Role Search" );
			ButtonHelper.click("ClearButton");
			GenericHelper.waitForLoadmask(searchScreenWaitSec);
			ComboBoxHelper.select("Include_Combo", "(All)");
			SearchGridHelper.gridFilterSearchWithTextBox("Roles_RoleName", "Role 149428", "Name");
			
			for (int i = 0; i < roleName.size(); i++)
				assertTrue(GridHelper.isValuePresent("SearchGrid", roleName.get(i), "Name"));
		} catch (AssertionError e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	@Test(priority=3, description="Test case 3 of Bug 149428", dependsOnMethods = { "testCase1" }, groups = { "deletedRole" })
	public void testCase3() throws Exception {
		try {
			// In Roles search screen, on search based on Include filter as "Deleted Items", deleted roles should be visible
			ExcelReader excelReader = new ExcelReader();
			HashMap<String, ArrayList<String>> excelData = excelReader.readDataByColumn( path, fileName, sheetName, "Roles", 1 );
			String roleName1 = excelData.get("Role Name").get(0);
			String roleName2 = excelData.get("Role Name").get(1);
			
			NavigationHelper.navigateToScreen( "Roles", "Role Search" );
			ButtonHelper.click("ClearButton");
			GenericHelper.waitForLoadmask(searchScreenWaitSec);
			ComboBoxHelper.select("Include_Combo", "Deleted Items");
			SearchGridHelper.gridFilterSearchWithTextBox("Roles_RoleName", "Role 149428", "Name");
			assertTrue(GridHelper.isValuePresent("SearchGrid", roleName1, "Name"));
			assertFalse(GridHelper.isValuePresent("SearchGrid", roleName2, "Name"));
		} catch (AssertionError e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
}