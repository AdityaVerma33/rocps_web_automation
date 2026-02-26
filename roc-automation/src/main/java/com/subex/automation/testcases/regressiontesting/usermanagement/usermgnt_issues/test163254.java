package com.subex.automation.testcases.regressiontesting.usermanagement.usermgnt_issues;

import org.testng.annotations.Test;

import com.subex.automation.helpers.application.LoginHelper;
import com.subex.automation.helpers.application.NavigationHelper;
import com.subex.automation.helpers.application.screens.UserHelper;
import com.subex.automation.helpers.util.FailureHelper;

public class test163254 extends testUserManagement {
	
	final String sheetName = "test163254";
	
	public test163254() throws Exception {
		super();
	}
	
	@Test(priority=1, description="Test case 1 of Bug 163254")
	public void testCase1() throws Exception {
		try {
			// Validate that creating a user with username with @ character is possbile. Login as that user should work
			UserHelper users = new UserHelper();
			users.createUser(path, fileName, sheetName, "Users1", 1);
			
			LoginHelper login = new LoginHelper();
			login.login( path, fileName, sheetName, "UserLogin1", 1 );
			String[] groupNames = {"Monitoring", "Tariffs", "Admin"};
			String[][] screenNames = {{"Task Search", "Collected Files", "Collected Files Summary", "Alerts", "Case Generation Requests"},
									{"Bands", "Elements", "Routes", "Tariff Classes", "Tariffs"},
									{"Ageing", "Alert Groups", "Audit Trails", "Day Groups", "Entities", "Entity Export", "Entity Groups",
									"Entity Import", "Standard Expressions", "Table Definitions", "Table Instances", "Table Instance Groups"}};
			NavigationHelper.checkScreens(groupNames, screenNames);
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
	
	@Test(priority=2, description="Test case 2 of Bug 163254")
	public void testCase2() throws Exception {
		try {
			// Validate that creating a user with username with underscore character is possbile. Login as that user should work
			UserHelper users = new UserHelper();
			users.createUser(path, fileName, sheetName, "Users2", 1);
			
			LoginHelper login = new LoginHelper();
			login.login( path, fileName, sheetName, "UserLogin2", 1 );
			String[] groupNames = {"Monitoring", "Tariffs", "Admin"};
			String[][] screenNames = {{"Task Search", "Collected Files", "Collected Files Summary", "Alerts", "Case Generation Requests"},
									{"Bands", "Elements", "Routes", "Tariff Classes", "Tariffs"},
									{"Ageing", "Alert Groups", "Audit Trails", "Day Groups", "Entities", "Entity Export", "Entity Groups",
									"Entity Import", "Standard Expressions", "Table Definitions", "Table Instances", "Table Instance Groups"}};
			NavigationHelper.checkScreens(groupNames, screenNames);
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
	
	@Test(priority=3, description="Test case 3 of Bug 163254")
	public void testCase3() throws Exception {
		try {
			// Validate that creating a user with username with # character is possbile. Login as that user should work
			UserHelper users = new UserHelper();
			users.createUser(path, fileName, sheetName, "Users3", 1);
			
			LoginHelper login = new LoginHelper();
			login.login( path, fileName, sheetName, "UserLogin3", 1 );
			String[] groupNames = {"Monitoring", "Tariffs", "Admin"};
			String[][] screenNames = {{"Task Search", "Collected Files", "Collected Files Summary", "Alerts", "Case Generation Requests"},
									{"Bands", "Elements", "Routes", "Tariff Classes", "Tariffs"},
									{"Ageing", "Alert Groups", "Audit Trails", "Day Groups", "Entities", "Entity Export", "Entity Groups",
									"Entity Import", "Standard Expressions", "Table Definitions", "Table Instances", "Table Instance Groups"}};
			NavigationHelper.checkScreens(groupNames, screenNames);
		} catch (AssertionError e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
}