package com.subex.automation.testcases.regressiontesting.usermanagement;

import java.util.ArrayList;
import java.util.HashMap;

import org.testng.annotations.Test;

import com.subex.automation.helpers.application.LoginHelper;
import com.subex.automation.helpers.application.NavigationHelper;
import com.subex.automation.helpers.application.screens.RolesHelper;
import com.subex.automation.helpers.application.screens.UserHelper;
import com.subex.automation.helpers.component.GenericHelper;
import com.subex.automation.helpers.component.GridCheckBoxHelper;
import com.subex.automation.helpers.component.TabHelper;
import com.subex.automation.helpers.file.ExcelReader;
import com.subex.automation.helpers.scripts.TestDataHelper;
import com.subex.automation.helpers.util.FailureHelper;

public class testUserEdit extends testUserManagement {

	final String sheetName = "EditUser";
	
	public testUserEdit() throws Exception {
		super();
	}
	
	@Test(priority=1, description="User edit of Role", groups = { "createUser" })
	public void testUserRoleEdit() throws Exception {
		try {
			// Edit User and provide more module privileges
			RolesHelper roles = new RolesHelper();
			roles.createRole(path, fileName, sheetName, "Roles", 1);
			
			UserHelper users = new UserHelper();
			users.createUser(path, fileName, sheetName, "User1", 1);
			
			LoginHelper login = new LoginHelper();
			login.login(path, fileName, sheetName, "User1Login", 1);
			
			String[] expectedGroups1 = {"Admin", "ROC View"};
			String[][] expectedScreens1 = {{"Table Definitions", "Table Instances", "Table Instance Groups", "Ageing"},
										{"Visualizer"}};
			NavigationHelper.checkScreens(expectedGroups1, expectedScreens1);
			
			login.loginWithConfigPropertyDetails();
			roles.createRole(path, fileName, sheetName, "EditRole", 1);
			login.login(path, fileName, sheetName, "User1Login", 1);
			
			String[] expectedGroups2 = {"Admin", "ROC View", "Tariffs"};
			String[][] expectedScreens2 = {{"Table Definitions", "Table Instances", "Table Instance Groups", "Ageing"},
										{"Visualizer"}, {"Elements", "Bands"}};
			NavigationHelper.checkScreens(expectedGroups2, expectedScreens2);
			
			login.loginWithConfigPropertyDetails();
			users.createUser(path, fileName, sheetName, "EditUser1", 1);
			login.login(path, fileName, sheetName, "User1Login", 1);
			
			String[] expectedGroups3 = {"Admin", "ROC View", "Tariffs", "System"};
			String[][] expectedScreens3 = {{"Table Definitions", "Table Instances", "Table Instance Groups", "Ageing"},
										{"Visualizer"}, {"Elements", "Bands"},
										{"Cache Strategies", "Controllers", "Duplicate XDR Check", "File Collections", "File Sequence Check",
											"File Sources", "LDC Correlators", "Machines", "Pre Aggregation", "Recurring Tasks", "Streams",
											"String Rule Sets", "File Transfers"}};
			NavigationHelper.checkScreens(expectedGroups3, expectedScreens3);
			
			String[] fsPartition = {"Common"};
			verifyFileSource("CreateUser", fsPartition);
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
	
	@Test(priority=2, description="User edit of partition", dependsOnMethods = { "testUserRoleEdit" }, groups = { "createUser" })
	public void testUserPartitionEdit() throws Exception {
		try {
			// Edit User and change Partition
			TestDataHelper testData = new TestDataHelper();
			ExcelReader excelReader = new ExcelReader();
			HashMap<String, ArrayList<String>> excelData = excelReader.readDataByColumn( path, fileName, sheetName, "EditUser1", 1 );
			String partition = excelData.get("Partition").get(0);
			String userName = excelData.get("Username").get(0);
			String[] role = testData.getStringValue(excelData.get("Roles").get(0), firstLevelDelimiter);
			String[] securityPartition = {"Reg P1"};
			
			UserHelper users = new UserHelper();
			users.navigateToUsers(userName, partition);
			String detailScreenTitle = NavigationHelper.getScreenTitle();
			GenericHelper.waitForLoadmask(detailScreenWaitSec);
			
			users.assignRolePrivileges(role, securityPartition);
			users.saveUsers(userName, detailScreenTitle);
			
			LoginHelper login = new LoginHelper();
			login.login(path, fileName, sheetName, "User1Login", 1);
			String[] fsPartition1 = {"Common", "Reg P1"};
			verifyFileSource("CreateUser", fsPartition1);
			
			login.loginWithConfigPropertyDetails();
			users.navigateToUsers(userName, partition);
			GenericHelper.waitForLoadmask(detailScreenWaitSec);
			
			TabHelper.gotoTab("Roles And Partitions");
			String checkBoxLocator = or.getProperty("Users_RolePrivileges_CheckBox").replace("partition", "Common");
			GridCheckBoxHelper.uncheck("Users_RolePrivileges_Grid", checkBoxLocator, role[0], "Role", "Common");
			
			users.saveUsers(userName, detailScreenTitle);
			
			login.login(path, fileName, sheetName, "User1Login", 1);
			String[] fsPartition2 = {"Reg P1"};
			verifyFileSource("CreateUser", fsPartition2);
		} catch (AssertionError e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
}