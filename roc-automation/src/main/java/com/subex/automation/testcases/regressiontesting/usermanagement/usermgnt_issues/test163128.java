package com.subex.automation.testcases.regressiontesting.usermanagement.usermgnt_issues;

import java.util.ArrayList;
import java.util.HashMap;

import org.testng.annotations.Test;

import com.subex.automation.helpers.application.LoginHelper;
import com.subex.automation.helpers.application.NavigationHelper;
import com.subex.automation.helpers.application.screens.UserHelper;
import com.subex.automation.helpers.component.TextBoxHelper;
import com.subex.automation.helpers.file.ExcelReader;
import com.subex.automation.helpers.util.FailureHelper;

public class test163128 extends testUserManagement {
	
	final String sheetName = "test163128";
	
	public test163128() throws Exception {
		super();
	}
	
	@Test(priority=1, description="Test case 1 of Bug 163128")
	public void testCase1() throws Exception {
		try {
			// Validate that creating a user with username without any special characters is possible. Login as that user should work
			UserHelper users = new UserHelper();
			users.createUser(path, fileName, sheetName, "Users1", 1);
			
			LoginHelper login = new LoginHelper();
			ExcelReader excelReader = new ExcelReader();
			HashMap<String, ArrayList<String>> excelData = excelReader.readDataByColumn( path, fileName, sheetName, "UserLogin1", 1 );
	
			for(int i = 0; i < excelData.get("Username").size(); i++) 
			{
				String appName = excelData.get("Application Name").get(i);
				String userName = excelData.get("Username").get(i);
				String password = excelData.get("Password").get(i);
				String resetPassword = excelData.get("Reset Password").get(i);
				
				login.login(appName, userName, password, resetPassword);
				
				if (TextBoxHelper.isPresent("Login_Username_TextBox"))
					login.login(appName, userName, resetPassword, resetPassword);
				
				String[] groupNames = {"Monitoring", "Tariffs", "Admin"};
				String[][] screenNames = {{"Task Search", "Collected Files", "Collected Files Summary", "Alerts", "Case Generation Requests"},
										{"Bands", "Elements", "Routes", "Tariff Classes", "Tariffs"},
										{"Ageing", "Alert Groups", "Audit Trails", "Day Groups", "Entities", "Entity Export", "Entity Groups",
										"Entity Import", "Standard Expressions", "Table Definitions", "Table Instances", "Table Instance Groups"}};
				NavigationHelper.checkScreens(groupNames, screenNames);
			}
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
	
	@Test(priority=2, description="Test case 2 of Bug 163128")
	public void testCase2() throws Exception {
		try {
			// Validate that creating a user with username with hypne character is possbile. Login as that user should work
			UserHelper users = new UserHelper();
			users.createUser(path, fileName, sheetName, "Users2", 1);
			
			LoginHelper login = new LoginHelper();
			ExcelReader excelReader = new ExcelReader();
			HashMap<String, ArrayList<String>> excelData = excelReader.readDataByColumn( path, fileName, sheetName, "UserLogin2", 1 );
	
			for(int i = 0; i < excelData.get("Username").size(); i++) 
			{
				String appName = excelData.get("Application Name").get(i);
				String userName = excelData.get("Username").get(i);
				String password = excelData.get("Password").get(i);
				String resetPassword = excelData.get("Reset Password").get(i);
				
				login.login(appName, userName, password, resetPassword);
				
				if (TextBoxHelper.isPresent("Login_Username_TextBox"))
					login.login(appName, userName, resetPassword, resetPassword);
				
				String[] groupNames = {"Monitoring", "Tariffs", "Admin"};
				String[][] screenNames = {{"Task Search", "Collected Files", "Collected Files Summary", "Alerts", "Case Generation Requests"},
										{"Bands", "Elements", "Routes", "Tariff Classes", "Tariffs"},
										{"Ageing", "Alert Groups", "Audit Trails", "Day Groups", "Entities", "Entity Export", "Entity Groups",
										"Entity Import", "Standard Expressions", "Table Definitions", "Table Instances", "Table Instance Groups"}};
				NavigationHelper.checkScreens(groupNames, screenNames);
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