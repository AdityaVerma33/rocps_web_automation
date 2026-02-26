package com.subex.automation.testcases.regressiontesting.usermanagement.usermgnt_issues;

import java.util.ArrayList;
import java.util.HashMap;

import org.testng.annotations.Test;

import com.subex.automation.helpers.application.NavigationHelper;
import com.subex.automation.helpers.application.screens.RolesHelper;
import com.subex.automation.helpers.component.GridHelper;
import com.subex.automation.helpers.component.SearchGridHelper;
import com.subex.automation.helpers.file.ExcelReader;
import com.subex.automation.helpers.util.FailureHelper;

public class test145551 extends testUserManagement {
	
	final String sheetName = "test145551";
	
	public test145551() throws Exception {
		super();
	}
	
	@Test(priority=1, description="Test case 1 of Bug 145551")
	public void testCase1() throws Exception {
		try {
			// Verify if search based on Name in Roles Screen for exact match works fine
			RolesHelper roles = new RolesHelper();
			roles.createRole(path, fileName, sheetName, "Roles", 1);
			
			ExcelReader excelReader = new ExcelReader();
			HashMap<String, ArrayList<String>> excelData = excelReader.readDataByColumn( path, fileName, sheetName, "Roles", 1 );
			ArrayList<String> roleName = excelData.get("Role Name");
			
			NavigationHelper.navigateToScreen( "Roles", "Role Search" );
			String searchString = "Role 145551";
			SearchGridHelper.gridFilterSearchWithTextBox("Roles_RoleName", searchString, "Name");
			int rows = GridHelper.getRowCount("SearchGrid");
			assertTrue(rows > 0);
			
			for (int i = 0; i < roleName.size(); i++) {
				String name = roleName.get(i);
				
				if (name.startsWith(searchString))
					assertTrue(GridHelper.isValuePresent("SearchGrid", name, "Name"));
				else
					assertFalse(GridHelper.isValuePresent("SearchGrid", name, "Name"));
			}
		} catch (AssertionError e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	@Test(priority=2, description="Test case 2 of Bug 145551")
	public void testCase2() throws Exception {
		try {
			// Verify if search based on Name in Roles Screen for like match works fine
			RolesHelper roles = new RolesHelper();
			roles.createRole(path, fileName, sheetName, "Roles", 1);
			
			ExcelReader excelReader = new ExcelReader();
			HashMap<String, ArrayList<String>> excelData = excelReader.readDataByColumn( path, fileName, sheetName, "Roles", 1 );
			ArrayList<String> roleName = excelData.get("Role Name");
			
			NavigationHelper.navigateToScreen( "Roles", "Role Search" );
			String searchString = "%test%";
			SearchGridHelper.gridFilterSearchWithTextBox("Roles_RoleName", searchString, "Name");
			int rows = GridHelper.getRowCount("SearchGrid");
			assertTrue(rows > 0);
			
			for (int i = 0; i < roleName.size(); i++) {
				String name = roleName.get(i);
				
				if (name.contains("test"))
					assertTrue(GridHelper.isValuePresent("SearchGrid", name, "Name"));
				else
					assertFalse(GridHelper.isValuePresent("SearchGrid", name, "Name"));
			}
		} catch (AssertionError e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	@Test(priority=3, description="Test case 3 of Bug 145551")
	public void testCase3() throws Exception {
		try {
			// Verify if search based on Name in Roles Screen for starts with match works fine
			RolesHelper roles = new RolesHelper();
			roles.createRole(path, fileName, sheetName, "Roles", 1);
			
			ExcelReader excelReader = new ExcelReader();
			HashMap<String, ArrayList<String>> excelData = excelReader.readDataByColumn( path, fileName, sheetName, "Roles", 1 );
			ArrayList<String> roleName = excelData.get("Role Name");
			
			NavigationHelper.navigateToScreen( "Roles", "Role Search" );
			String searchString = "Role";
			SearchGridHelper.gridFilterSearchWithTextBox("Roles_RoleName", searchString, "Name");
			int rows = GridHelper.getRowCount("SearchGrid");
			assertTrue(rows > 0);
			
			for (int i = 0; i < roleName.size(); i++) {
				String name = roleName.get(i);
				
				if (name.startsWith(searchString))
					assertTrue(GridHelper.isValuePresent("SearchGrid", name, "Name"));
				else
					assertFalse(GridHelper.isValuePresent("SearchGrid", name, "Name"));
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