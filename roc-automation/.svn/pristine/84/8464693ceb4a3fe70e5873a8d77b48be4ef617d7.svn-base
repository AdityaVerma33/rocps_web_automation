package com.subex.automation.testcases.regressiontesting.usermanagement.usermgnt_issues;

import java.util.ArrayList;
import java.util.HashMap;

import org.testng.annotations.Test;

import com.subex.automation.helpers.application.NavigationHelper;
import com.subex.automation.helpers.application.screens.UserHelper;
import com.subex.automation.helpers.component.ButtonHelper;
import com.subex.automation.helpers.component.GenericHelper;
import com.subex.automation.helpers.component.GridHelper;
import com.subex.automation.helpers.component.SearchGridHelper;
import com.subex.automation.helpers.file.ExcelReader;
import com.subex.automation.helpers.report.Log4jHelper;
import com.subex.automation.helpers.util.FailureHelper;

public class test140388 extends testUserManagement {
	
	final String sheetName = "test140388";
	
	public test140388() throws Exception {
		super();
	}
	
	@Test(priority=1, description="Test case 1 of Bug 140388")
	public void testCase1() throws Exception {
		try {
			// Verify if search based on Forename in Users Screen for exact match works fine
			Log4jHelper.logInfo("Running User Management Issues Test Cases");
			UserHelper users = new UserHelper();
			users.createUser(path, fileName, sheetName, "Users", 1);
			
			ExcelReader excelReader = new ExcelReader();
			HashMap<String, ArrayList<String>> excelData = excelReader.readDataByColumn( path, fileName, sheetName, "Users", 1 );
			ArrayList<String> firstName = excelData.get("First Name");
			
			NavigationHelper.navigateToScreen( "Users", "User Search" );
			ButtonHelper.click("ClearButton");
			GenericHelper.waitForLoadmask(searchScreenWaitSec);
			String searchString = "user1";
			SearchGridHelper.gridFilterSearchWithTextBox("Users_FirstName", searchString, "Forename");
			int rows = GridHelper.getRowCount("SearchGrid");
			assertTrue(rows > 0);
			
			for (int i = 0; i < firstName.size(); i++) {
				String name = firstName.get(i);
				
				if (name.startsWith(searchString))
					assertTrue(GridHelper.isValuePresent("SearchGrid", name, "Forename"));
				else
					assertFalse(GridHelper.isValuePresent("SearchGrid", name, "Forename"));
			}
		} catch (AssertionError e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	@Test(priority=2, description="Test case 2 of Bug 140388")
	public void testCase2() throws Exception {
		try {
			// Verify if search based on Forename in Users Screen for like match works fine
			UserHelper users = new UserHelper();
			users.createUser(path, fileName, sheetName, "Users", 1);
			
			ExcelReader excelReader = new ExcelReader();
			HashMap<String, ArrayList<String>> excelData = excelReader.readDataByColumn( path, fileName, sheetName, "Users", 1 );
			ArrayList<String> firstName = excelData.get("First Name");
			
			NavigationHelper.navigateToScreen( "Users", "User Search" );
			ButtonHelper.click("ClearButton");
			GenericHelper.waitForLoadmask(searchScreenWaitSec);
			String searchString = "%user%";
			SearchGridHelper.gridFilterSearchWithTextBox("Users_FirstName", searchString, "Forename");
			int rows = GridHelper.getRowCount("SearchGrid");
			assertTrue(rows > 0);
			
			for (int i = 0; i < firstName.size(); i++) {
				String name = firstName.get(i);
				
				if (name.contains("user"))
					assertTrue(GridHelper.isValuePresent("SearchGrid", name, "Forename"));
				else
					assertFalse(GridHelper.isValuePresent("SearchGrid", name, "Forename"));
			}
		} catch (AssertionError e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	@Test(priority=3, description="Test case 3 of Bug 140388")
	public void testCase3() throws Exception {
		try {
			// Verify if search based on Forename in Users Screen for starts with match works fine
			UserHelper users = new UserHelper();
			users.createUser(path, fileName, sheetName, "Users", 1);
			
			ExcelReader excelReader = new ExcelReader();
			HashMap<String, ArrayList<String>> excelData = excelReader.readDataByColumn( path, fileName, sheetName, "Users", 1 );
			ArrayList<String> firstName = excelData.get("First Name");
			
			NavigationHelper.navigateToScreen( "Users", "User Search" );
			ButtonHelper.click("ClearButton");
			GenericHelper.waitForLoadmask(searchScreenWaitSec);
			String searchString = "user";
			SearchGridHelper.gridFilterSearchWithTextBox("Users_FirstName", searchString, "Forename");
			int rows = GridHelper.getRowCount("SearchGrid");
			assertTrue(rows > 0);
			
			for (int i = 0; i < firstName.size(); i++) {
				String name = firstName.get(i);
				
				if (name.startsWith(searchString))
					assertTrue(GridHelper.isValuePresent("SearchGrid", name, "Forename"));
				else
					assertFalse(GridHelper.isValuePresent("SearchGrid", name, "Forename"));
			}
		} catch (AssertionError e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	@Test(priority=4, description="Test case 4 of Bug 140388")
	public void testCase4() throws Exception {
		try {
			// Verify if search based on Surname in Users Screen for exact match works fine
			UserHelper users = new UserHelper();
			users.createUser(path, fileName, sheetName, "Users", 1);
			
			ExcelReader excelReader = new ExcelReader();
			HashMap<String, ArrayList<String>> excelData = excelReader.readDataByColumn( path, fileName, sheetName, "Users", 1 );
			ArrayList<String> lastName = excelData.get("Last Name");
			
			NavigationHelper.navigateToScreen( "Users", "User Search" );
			ButtonHelper.click("ClearButton");
			GenericHelper.waitForLoadmask(searchScreenWaitSec);
			String searchString = "user1";
			SearchGridHelper.gridFilterSearchWithTextBox("Users_LastName", searchString, "Surname");
			int rows = GridHelper.getRowCount("SearchGrid");
			assertTrue(rows > 0);
			
			for (int i = 0; i < lastName.size(); i++) {
				String name = lastName.get(i);
				
				if (name.startsWith(searchString))
					assertTrue(GridHelper.isValuePresent("SearchGrid", name, "Surname"));
				else
					assertFalse(GridHelper.isValuePresent("SearchGrid", name, "Surname"));
			}
		} catch (AssertionError e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	@Test(priority=5, description="Test case 5 of Bug 140388")
	public void testCase5() throws Exception {
		try {
			// Verify if search based on Surname in Users Screen for like match works fine
			UserHelper users = new UserHelper();
			users.createUser(path, fileName, sheetName, "Users", 1);
			
			ExcelReader excelReader = new ExcelReader();
			HashMap<String, ArrayList<String>> excelData = excelReader.readDataByColumn( path, fileName, sheetName, "Users", 1 );
			ArrayList<String> lastName = excelData.get("Last Name");
			
			NavigationHelper.navigateToScreen( "Users", "User Search" );
			ButtonHelper.click("ClearButton");
			GenericHelper.waitForLoadmask(searchScreenWaitSec);
			String searchString = "%user%";
			SearchGridHelper.gridFilterSearchWithTextBox("Users_LastName", searchString, "Surname");
			int rows = GridHelper.getRowCount("SearchGrid");
			assertTrue(rows > 0);
			
			for (int i = 0; i < lastName.size(); i++) {
				String name = lastName.get(i);
				
				if (name.contains("user"))
					assertTrue(GridHelper.isValuePresent("SearchGrid", name, "Surname"));
				else
					assertFalse(GridHelper.isValuePresent("SearchGrid", name, "Surname"));
			}
		} catch (AssertionError e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	@Test(priority=6, description="Test case 6 of Bug 140388")
	public void testCase6() throws Exception {
		try {
			// Verify if search based on Surname in Users Screen for starts with match works fine
			UserHelper users = new UserHelper();
			users.createUser(path, fileName, sheetName, "Users", 1);
			
			ExcelReader excelReader = new ExcelReader();
			HashMap<String, ArrayList<String>> excelData = excelReader.readDataByColumn( path, fileName, sheetName, "Users", 1 );
			ArrayList<String> lastName = excelData.get("Last Name");
			
			NavigationHelper.navigateToScreen( "Users", "User Search" );
			ButtonHelper.click("ClearButton");
			GenericHelper.waitForLoadmask(searchScreenWaitSec);
			String searchString = "user";
			SearchGridHelper.gridFilterSearchWithTextBox("Users_LastName", searchString, "Surname");
			int rows = GridHelper.getRowCount("SearchGrid");
			assertTrue(rows > 0);
			
			for (int i = 0; i < lastName.size(); i++) {
				String name = lastName.get(i);
				
				if (name.startsWith(searchString))
					assertTrue(GridHelper.isValuePresent("SearchGrid", name, "Surname"));
				else
					assertFalse(GridHelper.isValuePresent("SearchGrid", name, "Surname"));
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