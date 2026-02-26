package com.subex.automation.testcases.regressiontesting.usermanagement;

import java.util.ArrayList;
import java.util.HashMap;

import org.testng.annotations.Test;

import com.subex.automation.helpers.application.LoginHelper;
import com.subex.automation.helpers.application.NavigationHelper;
import com.subex.automation.helpers.application.screens.CasePropertyHelper;
import com.subex.automation.helpers.application.screens.DayGroupHelper;
import com.subex.automation.helpers.application.screens.FileSourceHelper;
import com.subex.automation.helpers.application.screens.RolesHelper;
import com.subex.automation.helpers.application.screens.UserHelper;
import com.subex.automation.helpers.component.ButtonHelper;
import com.subex.automation.helpers.component.ElementHelper;
import com.subex.automation.helpers.component.GenericHelper;
import com.subex.automation.helpers.component.GridHelper;
import com.subex.automation.helpers.component.LabelHelper;
import com.subex.automation.helpers.component.SearchGridHelper;
import com.subex.automation.helpers.component.TabHelper;
import com.subex.automation.helpers.component.TextBoxHelper;
import com.subex.automation.helpers.file.ExcelReader;
import com.subex.automation.helpers.scripts.TestDataHelper;
import com.subex.automation.helpers.util.FailureHelper;

public class testUserCreation extends testUserManagement {

	final String sheetName = "CreateUser";
	
	public testUserCreation() throws Exception {
		super();
	}
	
	@Test(priority=1, description="User with single role", groups = { "createUser" })
	public void testSingleRoleUser() throws Exception {
		try {
			// Create user with single role
			UserHelper users = new UserHelper();
			users.createUser(path, fileName, sheetName, "SingleRoleUser", 1);
			
			verifyUserLogin(sheetName, "SingleRoleUserLogin", 1, 0);
			
			LoginHelper login = new LoginHelper();
			login.login(path, fileName, sheetName, "SingleRoleLogin", 1);
			
			verifyUserLogin(sheetName, "SingleRoleUserLogin", 2, 0);
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
	
	@Test(priority=2, description="User with multiple roles", groups = { "createUser" })
	public void testMultipleRoleUser() throws Exception {
		try {
			// Create user with multiple roles
			RolesHelper roles = new RolesHelper();
			roles.createRole(path, fileName, sheetName, "MultiRoleRoles", 1);
			
			UserHelper users = new UserHelper();
			users.createUser(path, fileName, sheetName, "MultiRoleUser", 1);
			
			LoginHelper login = new LoginHelper();
			login.login(path, fileName, sheetName, "MultiRoleLogin", 1);
			
			String[] groupName = {"Admin", "System"};
			String[][] screenName = {{"Table Definitions", "Table Instances", "Table Instance Groups", "Ageing"},
									{"File Sources", "File Collections", "Recurring Tasks"}};
			NavigationHelper.checkScreens(groupName, screenName);
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
	
	@Test(priority=3, description="User without role", groups = { "createUser" })
	public void testNoRoleUser() throws Exception {
		try {
			// Create user without role
			UserHelper users = new UserHelper();
			users.createUser(path, fileName, sheetName, "NoRoleUser", 1);
			
			ExcelReader excelReader = new ExcelReader();
			HashMap<String, ArrayList<String>> excelData = excelReader.readDataByColumn( path, fileName, sheetName, "NoRoleLogin", 1 );
			String appName = excelData.get("Application Name").get(0);
			String userName = excelData.get("Username").get(0);
			String password = excelData.get("Password").get(0);
			String resetPassword = excelData.get("Reset Password").get(0);
			
			LoginHelper login = new LoginHelper();
			login.login(appName, userName, password, resetPassword);
			String errorMsg = login.getLoginScreenError();
			assertTrue(errorMsg.equals(userName + " does not have any role privilege"));
			
			login.loginWithConfigPropertyDetails();
			verifyUserLogin(sheetName, "NoRoleUserLogin", 2, 0);
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
	
	@Test(priority=4, description="Username uniqueness", groups = { "createUser" })
	public void testUsernameUniqueness() throws Exception {
		try {
			// Create user with existing username
			UserHelper users = new UserHelper();
			users.createUser(path, fileName, sheetName, "NoRoleUser", 1);
			
			ExcelReader excelReader = new ExcelReader();
			HashMap<String, ArrayList<String>> excelData = excelReader.readDataByColumn( path, fileName, sheetName, "NoRoleUser", 2 );
			String partition = excelData.get("Partition").get(0);
			String userName = excelData.get("Username").get(0);
			
			users.navigateToUsers(userName);
			NavigationHelper.navigateToNew(partition, "Users_FirstName");
			String detailScreenTitle = NavigationHelper.getScreenTitle();
			GenericHelper.waitForLoadmask(detailScreenWaitSec);
			users.updateUser(path, fileName, sheetName, "NoRoleUser", 2);
			
			ButtonHelper.click("SaveButton");
			GenericHelper.waitForLoadmask(detailScreenWaitSec);
			assertTrue(LabelHelper.isTitlePresent(detailScreenTitle));
			assertTrue(TextBoxHelper.isMandatory("Users_Username"));
		} catch (AssertionError e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	@Test(priority=5, description="User with Common partition", groups = { "createUser" })
	public void testUserWithCommonPartition() throws Exception {
		try {
			// User creation with Common partition
			String[] partitionName = {"Reg P1", "Reg P2", "Reg P3"};
			GenericHelper.insertPartition(partitionName);
			
			UserHelper users = new UserHelper();
			users.setAllPrivileges();
			
			FileSourceHelper fileSource = new FileSourceHelper();
			fileSource.createFileSource(path, fileName, sheetName, "FileSource", 1);
			
			users.createUser(path, fileName, sheetName, "UserWithCommon", 1);
			
			LoginHelper login = new LoginHelper();
			login.login(path, fileName, sheetName, "CommonUserLogin", 1);
			
			NavigationHelper.navigateToScreen("File Sources", "File Source Search");
			ExcelReader excelReader = new ExcelReader();
			HashMap<String, ArrayList<String>> excelData = excelReader.readDataByColumn( path, fileName, sheetName, "FileSource", 1 );
			String userPartition = "Common";
			
			for(int i = 0; i < excelData.get("Name").size(); i++)
			{
				String partition = excelData.get("Partition").get(i);
				String name = excelData.get("Name").get(i);
				
				int row = SearchGridHelper.gridFilterSearchWithTextBox("FileSource_Name", name, "Name");
				if (partition.equals(userPartition))
					assertTrue(row >= 1);
				else
					assertTrue(row == 0);
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
	
	@Test(priority=6, description="User with other partition", dependsOnMethods = { "testUserWithCommonPartition" }, groups = { "createUser" })
	public void testUserWithOtherPartition() throws Exception {
		try {
			// User creation with partition other than Common
			UserHelper users = new UserHelper();
			users.createUser(path, fileName, sheetName, "UserWithP1", 1);
			
			LoginHelper login = new LoginHelper();
			login.login(path, fileName, sheetName, "P1UserLogin", 1);
			
			NavigationHelper.navigateToScreen("File Sources", "File Source Search");
			ExcelReader excelReader = new ExcelReader();
			HashMap<String, ArrayList<String>> excelData = excelReader.readDataByColumn( path, fileName, sheetName, "FileSource", 1 );
			String userPartition1 = "Reg P1";
			String userPartition2 = "Reg P2";
			
			for(int i = 0; i < excelData.get("Name").size(); i++)
			{
				String partition = excelData.get("Partition").get(i);
				String name = excelData.get("Name").get(i);
				
				int row = SearchGridHelper.gridFilterSearchWithTextBox("FileSource_Name", name, "Name");
				if (partition.equals(userPartition1) || partition.equals(userPartition2))
					assertTrue(row >= 1);
				else
					assertTrue(row == 0);
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
	
	private void verifyDayGroup() throws Exception {
		try {
			ExcelReader excelReader = new ExcelReader();
			HashMap<String, ArrayList<String>> excelData = excelReader.readDataByColumn( path, fileName, sheetName, "DayGroup", 1 );
			String adminPartition = "Common";
			NavigationHelper.navigateToScreen("Day Groups", "Day Group Search");
			
			for(int i = 0; i < excelData.get("Name").size(); i++)
			{
				String partition = excelData.get("Partition").get(i);
				String name = excelData.get("Name").get(i);
				
				int row = SearchGridHelper.gridFilterSearchWithTextBox("DayGroup_Name", name, "Name");
				if (partition.equals(adminPartition))
					assertTrue(row >= 1);
				else
					assertTrue(row == 0);
			}
		} catch (AssertionError e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	private void verifyCaseProperty() throws Exception {
		try {
			ExcelReader excelReader = new ExcelReader();
			HashMap<String, ArrayList<String>> excelData = excelReader.readDataByColumn( path, fileName, sheetName, "CaseProperty", 1 );
			String cmPartition = "Reg P1";
			NavigationHelper.navigateToScreen("Case Properties", "Case Property Search");
			
			for(int i = 0; i < excelData.get("Name").size(); i++)
			{
				String partition = excelData.get("Partition").get(i);
				String name = excelData.get("Name").get(i);
				
				int row = SearchGridHelper.gridFilterSearchWithTextBox("CaseProperty_NameFilter", name, "Name");
				if (partition.equals(cmPartition))
					assertTrue(row >= 1);
				else
					assertTrue(row == 0);
			}
		} catch (AssertionError e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	@Test(priority=7, description="User with Common and other partition", dependsOnMethods = { "testUserWithCommonPartition" }, groups = { "createUser" })
	public void testUserWithCommonAndOtherPartition() throws Exception {
		try {
			// User creation with Common and other Partitions
			CasePropertyHelper caseProperty = new CasePropertyHelper();
			caseProperty.createCaseProperty(path, fileName, sheetName, "CaseProperty", 1);
			
			DayGroupHelper dayGroup = new DayGroupHelper();
			dayGroup.createDayGroup(path, fileName, sheetName, "DayGroup", 1);
			
			RolesHelper roles = new RolesHelper();
			roles.createRole(path, fileName, sheetName, "Roles", 1);
			
			UserHelper users = new UserHelper();
			users.createUser(path, fileName, sheetName, "UserWithCommon&P1", 1);
			
			LoginHelper login = new LoginHelper();
			login.login(path, fileName, sheetName, "Common&P1UserLogin", 1);
			
			verifyDayGroup();
			
			verifyCaseProperty();
			
			String[] fsPartition = {"Reg P2"};
			verifyFileSource(sheetName, fsPartition);
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
	
	@Test(priority=8, description="User case sensitivity", groups = { "createUser" })
	public void testUserCaseSensitivity() throws Exception {
		try {
			// Case insensitive username
			UserHelper users = new UserHelper();
			users.createUser(path, fileName, sheetName, "UserCaseInsensitive", 1);
			
			TestDataHelper testData = new TestDataHelper();
			ExcelReader excelReader = new ExcelReader();
			HashMap<String, ArrayList<String>> excelData = excelReader.readDataByColumn( path, fileName, sheetName, "UserCaseInsensitive1", 1 );
			
			for(int i = 0; i < excelData.get("Username").size(); i++) 
			{
				String partition = excelData.get("Partition").get(i);
				String userName = excelData.get("Username").get(i);
				String password = excelData.get("Password").get(i);
				String firstName = excelData.get("First Name").get(i);
				String lastName = excelData.get("Last Name").get(i);
				String phoneNo = excelData.get("Phone No").get(i);
				String emailAddress = excelData.get("Email Address").get(i);
				String description = excelData.get("Description").get(i);
				String allowedMachines = excelData.get("Allowed Machines").get(i);
				String accountExpiry = excelData.get("Account Expiry").get(i);
				String contactAddress = excelData.get("Contact Address").get(i);
				String diceGroup = excelData.get("DICE Group").get(i);
				String passwordValidation = excelData.get("User Password Validation").get(i);
				String[] role = testData.getStringValue(excelData.get("Roles").get(i), firstLevelDelimiter);
				String[] securityPartition = testData.getStringValue(excelData.get("Security Partition").get(i), firstLevelDelimiter);
				String[] rocFlow = testData.getStringValue(excelData.get("ROC Flows").get(i), firstLevelDelimiter);
				
				users.navigateToUsers(userName, partition);
				String detailScreenTitle = NavigationHelper.getScreenTitle();
				GenericHelper.waitForLoadmask(detailScreenWaitSec);
				
				users.updateUser(userName, password, firstName, lastName, phoneNo, emailAddress, description, allowedMachines, accountExpiry,
						contactAddress, diceGroup, passwordValidation, role, securityPartition, rocFlow);
				
				ButtonHelper.click("SaveButton");
				GenericHelper.waitForLoadmask(detailScreenWaitSec);
				assertTrue(LabelHelper.isTitlePresent(detailScreenTitle), "User save happened with the same username.");
			}
		} catch (AssertionError e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	@Test(priority=9, description="User with deleted role", groups = { "createUser" })
	public void testUserWithRoleDelete() throws Exception {
		try {
			// Validate user login when User's role is deleted
			RolesHelper roles = new RolesHelper();
			roles.createRole(path, fileName, sheetName, "MultiRoleRoles", 1);
			
			UserHelper users = new UserHelper();
			users.createUser(path, fileName, sheetName, "MultiRoleUser", 1);
			
			LoginHelper login = new LoginHelper();
			login.login(path, fileName, sheetName, "MultiRoleLogin", 1);
			
			String[] groupName1 = {"Admin", "System"};
			String[][] screenName1 = {{"Table Definitions", "Table Instances", "Table Instance Groups", "Ageing"},
									{"File Sources", "File Collections", "Recurring Tasks"}};
			NavigationHelper.checkScreens(groupName1, screenName1);
			
			login.loginWithConfigPropertyDetails();
			NavigationHelper.navigateToScreen( "Roles", "Role Search" );
			String roleName = "Reg Role - 2";
			SearchGridHelper.gridFilterSearchWithTextBox("Roles_RoleName", roleName, "Name");
			NavigationHelper.delete("SearchGrid", roleName, "Name");
			
			ExcelReader excelReader = new ExcelReader();
			HashMap<String, ArrayList<String>> excelData = excelReader.readDataByColumn( path, fileName, sheetName, "MultiRoleUser", 1 );
			String partition = excelData.get("Partition").get(0);
			String userName = excelData.get("Username").get(0);
			
			users.navigateToUsers(userName, partition);
			TabHelper.gotoTab("Roles And Partitions");
			assertFalse(GridHelper.isValuePresent("Roles_Privileges_Group_Grid", roleName, "Role"));
			
			login.login(path, fileName, sheetName, "MultiRoleLogin", 1);
			
			verifyUserLogin(sheetName, "RoleDeleteUserLogin", 1, 0);
			
			String[] groupName2 = {"Admin"};
			String[][] screenName2 = {{"Table Definitions", "Table Instances", "Table Instance Groups", "Ageing"}};
			NavigationHelper.checkScreens(groupName2, screenName2);
			
			ButtonHelper.click("NavigationMenu");
			Thread.sleep(100);
			String groupLocator = or.getProperty("MegaMenu_GroupNames").replace("groupName", "System");
			assertFalse(ElementHelper.isElementPresent(groupLocator));
		} catch (AssertionError e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
}