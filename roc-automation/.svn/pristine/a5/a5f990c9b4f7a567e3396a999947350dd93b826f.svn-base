package com.subex.automation.testcases.regressiontesting.usermanagement.usermgnt_issues;

import java.util.ArrayList;
import java.util.HashMap;

import org.testng.annotations.Test;

import com.subex.automation.helpers.application.NavigationHelper;
import com.subex.automation.helpers.application.screens.TeamHelper;
import com.subex.automation.helpers.application.screens.UserHelper;
import com.subex.automation.helpers.component.ButtonHelper;
import com.subex.automation.helpers.component.GenericHelper;
import com.subex.automation.helpers.component.GridHelper;
import com.subex.automation.helpers.component.SearchGridHelper;
import com.subex.automation.helpers.file.ExcelReader;
import com.subex.automation.helpers.util.FailureHelper;

public class test147926 extends testUserManagement {
	
	final String sheetName = "test147926";
	
	public test147926() throws Exception {
		super();
	}
	
	@Test(priority=1, description="Test case 1 of Bug 147926")
	public void testCase1() throws Exception {
		try {
			// In Teams screen, verify if Pagination Record Count is proper while creating a Team and clicking on Save
			UserHelper users = new UserHelper();
			users.createUser(path, fileName, sheetName, "Users", 1);
			
			TeamHelper teams = new TeamHelper();
			teams.createTeam(path, fileName, sheetName, "Teams", 1);
			
			String recordCount = SearchGridHelper.getTotalRecordsFetched();
			assertTrue(recordCount.equals("1"));
		} catch (AssertionError e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	@Test(priority=2, description="Test case 2 of Bug 147926")
	public void testCase2() throws Exception {
		try {
			// In Teams screen, verify if Pagination Record Count is proper while editing a Team and clicking on Save
			UserHelper users = new UserHelper();
			users.createUser(path, fileName, sheetName, "Users", 1);
			
			TeamHelper teams = new TeamHelper();
			teams.createTeam(path, fileName, sheetName, "Teams", 1);
			teams.createTeam(path, fileName, sheetName, "Teams", 2);
			
			String recordCount = SearchGridHelper.getTotalRecordsFetched();
			assertTrue(recordCount.equals("1"));
		} catch (AssertionError e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	@Test(priority=3, description="Test case 3 of Bug 147926")
	public void testCase3() throws Exception {
		try {
			// In Teams screen, verify if Pagination Record Count is proper while clicking on Search button multiple times
			UserHelper users = new UserHelper();
			users.createUser(path, fileName, sheetName, "Users", 1);
			
			TeamHelper teams = new TeamHelper();
			teams.createTeam(path, fileName, sheetName, "Teams", 1);
			
			ExcelReader excelReader = new ExcelReader();
			HashMap<String, ArrayList<String>> excelData = excelReader.readDataByColumn( path, fileName, sheetName, "Teams", 1 );
			String teamName = excelData.get("Name").get(0);
			NavigationHelper.navigateToScreen( "Teams", "Team Search" );
			SearchGridHelper.searchWithTextBox("Teams_Name", teamName, "Name");
			int rows = GridHelper.getRowCount("SearchGrid");
			
			int recordCount = Integer.parseInt(SearchGridHelper.getTotalRecordsFetched());
			assertEquals(rows, recordCount);
			
			for (int i = 0; i < 2; i++) {
				ButtonHelper.click("SearchButton");
				GenericHelper.waitForLoadmask(searchScreenWaitSec);
				recordCount = Integer.parseInt(SearchGridHelper.getTotalRecordsFetched());
				assertEquals(rows, recordCount);
			}
		} catch (AssertionError e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	@Test(priority=4, description="Test case 4 of Bug 147926")
	public void testCase4() throws Exception {
		try {
			// In Teams screen, verify if Pagination Record Count is proper while changing pagination and clicking Search
			UserHelper users = new UserHelper();
			users.createUser(path, fileName, sheetName, "Users", 1);
			
			TeamHelper teams = new TeamHelper();
			teams.createTeam(path, fileName, sheetName, "Teams", 1);
			
			ExcelReader excelReader = new ExcelReader();
			HashMap<String, ArrayList<String>> excelData = excelReader.readDataByColumn( path, fileName, sheetName, "Teams", 1 );
			String teamName = excelData.get("Name").get(0);
			NavigationHelper.navigateToScreen( "Teams", "Team Search" );
			SearchGridHelper.searchWithTextBox("Teams_Name", teamName, "Name");
			int rows = GridHelper.getRowCount("SearchGrid");
			
			int recordCount = Integer.parseInt(SearchGridHelper.getTotalRecordsFetched());
			assertEquals(rows, recordCount);
			
			SearchGridHelper.setPagination("1000 per page");
			ButtonHelper.click("SearchButton");
			GenericHelper.waitForLoadmask(searchScreenWaitSec);
			recordCount = Integer.parseInt(SearchGridHelper.getTotalRecordsFetched());
			assertEquals(rows, recordCount);
			
			SearchGridHelper.setPagination("50 per page");
			ButtonHelper.click("SearchButton");
			GenericHelper.waitForLoadmask(searchScreenWaitSec);
			recordCount = Integer.parseInt(SearchGridHelper.getTotalRecordsFetched());
			assertEquals(rows, recordCount);
		} catch (AssertionError e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
}