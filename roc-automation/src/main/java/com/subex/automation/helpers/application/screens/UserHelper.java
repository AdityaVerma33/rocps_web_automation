package com.subex.automation.helpers.application.screens;

import java.util.ArrayList;
import java.util.HashMap;

import com.subex.automation.helpers.application.LoginHelper;
import com.subex.automation.helpers.application.NavigationHelper;
import com.subex.automation.helpers.component.ButtonHelper;
import com.subex.automation.helpers.component.ComboBoxHelper;
import com.subex.automation.helpers.component.GenericHelper;
import com.subex.automation.helpers.component.GridCheckBoxHelper;
import com.subex.automation.helpers.component.GridHelper;
import com.subex.automation.helpers.component.LabelHelper;
import com.subex.automation.helpers.component.PopupHelper;
import com.subex.automation.helpers.component.SearchGridHelper;
import com.subex.automation.helpers.component.TabHelper;
import com.subex.automation.helpers.component.TextBoxHelper;
import com.subex.automation.helpers.data.ValidationHelper;
import com.subex.automation.helpers.file.ExcelReader;
import com.subex.automation.helpers.report.Log4jHelper;
import com.subex.automation.helpers.scripts.TestDataHelper;
import com.subex.automation.helpers.selenium.ROCAcceptanceTest;
import com.subex.automation.helpers.util.FailureHelper;

public class UserHelper extends ROCAcceptanceTest {

	public void createUser(String path, String fileName, String sheetName, String testCaseName, int occurance) throws Exception
	{
		try {
			TestDataHelper testData = new TestDataHelper();
			ExcelReader excelReader = new ExcelReader();
			HashMap<String, ArrayList<String>> excelData = excelReader.readDataByColumn( path, fileName, sheetName, testCaseName, occurance );
	
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
				
				createUser(partition, userName, password, firstName, lastName, phoneNo, emailAddress, description, allowedMachines, accountExpiry,
						contactAddress, diceGroup, passwordValidation, role, securityPartition, rocFlow);
			}
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public void createUser(String partition, String userName, String password, String firstName, String lastName, String phoneNo, String emailAddress,
			String description, String allowedMachines, String accountExpiry, String contactAddress, String diceGroup, String passwordValidation,
			String[] role, String[] securityPartition, String[] rocFlow) throws Exception
	{
		try {
			navigateToUsers(userName, partition);
			String detailScreenTitle = NavigationHelper.getScreenTitle();
			GenericHelper.waitForLoadmask(detailScreenWaitSec);
			
			updateUser(userName, password, firstName, lastName, phoneNo, emailAddress, description, allowedMachines, accountExpiry,
					contactAddress, diceGroup, passwordValidation, role, securityPartition, rocFlow);

			saveUsers(userName, detailScreenTitle);
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public void changePassword(String path, String fileName, String sheetName, String testCaseName, int occurance) throws Exception
	{
		try {
			ExcelReader excelReader = new ExcelReader();
			HashMap<String, ArrayList<String>> excelData = excelReader.readDataByColumn( path, fileName, sheetName, testCaseName, occurance );
	
			for(int i = 0; i < excelData.get("Username").size(); i++) 
			{
				String userName = excelData.get("Username").get(i);
				String currentPassword = excelData.get("Current Password").get(i);
				String newPassword = excelData.get("New Password").get(i);
				String confirmPassword = excelData.get("Confirm Password").get(i);
				
				changePassword(userName, currentPassword, newPassword, confirmPassword);
			}
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public void changePassword(String userName, String currentPassword, String newPassword, String confirmPassword) throws Exception
	{
		try {
			NavigationHelper.navigateToScreen( "Users", "User Search" );
			int row = SearchGridHelper.searchWithTextBox("Users_Username", userName, "Name");
			
			if (row > 0) {
				GridHelper.clickRow("SearchGrid", row, 1);
				
				if (NavigationHelper.isActionPresent("User Actions")) {
					NavigationHelper.navigateToAction("User Actions");
					
					if (NavigationHelper.isActionPresent("Change Password")) {
						NavigationHelper.navigateToAction("Change Password");
						GenericHelper.waitForElement("ChangePassword_OK", searchScreenWaitSec);
						assertTrue(LabelHelper.isTitlePresent("Edit Change Password"));
						
						LoginHelper login = new LoginHelper();
						login.updatePassword(userName, currentPassword, newPassword, confirmPassword);
					}
					else {
						FailureHelper.failTest("Action 'Change Password' is not found under 'User Actions' in Users screen.");
					}
				}
				else {
					FailureHelper.failTest("Action 'User Actions' is not found in Users screen.");
				}
			}
			else {
				FailureHelper.failTest("User '" + userName + "' is not found.");
			}
		} catch (AssertionError e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public void updateUser(String path, String fileName, String sheetName, String testCaseName, int occurance) throws Exception
	{
		try {
			TestDataHelper testData = new TestDataHelper();
			ExcelReader excelReader = new ExcelReader();
			HashMap<String, ArrayList<String>> excelData = excelReader.readDataByColumn( path, fileName, sheetName, testCaseName, occurance );
	
			for(int i = 0; i < excelData.get("Username").size(); i++) 
			{
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
				
				updateUser(userName, password, firstName, lastName, phoneNo, emailAddress, description, allowedMachines, accountExpiry,
						contactAddress, diceGroup, passwordValidation, role, securityPartition, rocFlow);
			}
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public void updateUser(String userName, String password, String firstName, String lastName, String phoneNo, String emailAddress,
			String description, String allowedMachines, String accountExpiry, String contactAddress, String diceGroup, String passwordValidation,
			String[] role, String[] securityPartition, String[] rocFlow) throws Exception
	{
		try {
			if (TextBoxHelper.isEnabled("Users_Username"))
				TextBoxHelper.type("Users_Username", userName);
			
			if (TextBoxHelper.isEnabled("Users_NewPassword"))
				TextBoxHelper.type("Users_NewPassword", password);
			
			if (ComboBoxHelper.isEnabled("Users_UserPasswordValidation")) {
				if (ValidationHelper.isEmpty(passwordValidation))
					passwordValidation = "Standard Password Validation";
				ComboBoxHelper.select("Users_UserPasswordValidation", passwordValidation);
			}
			
			TextBoxHelper.type("Users_FirstName", firstName);
			TextBoxHelper.type("Users_LastName", lastName);
			TextBoxHelper.type("Users_PhoneNo", phoneNo);
			TextBoxHelper.type("Users_EmailAddress", emailAddress);
			TextBoxHelper.type("Users_Description", description);
			TextBoxHelper.type("Users_AllowedMachines", allowedMachines);
			TextBoxHelper.type("Users_AccountExpiry", accountExpiry);
			TextBoxHelper.type("Users_ContactAddress", contactAddress);
			ComboBoxHelper.select("Users_DiceGroup", diceGroup);
			
			assignRolePrivileges(role, securityPartition);
			
			assignROCFlows(rocFlow);
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public void disableUser(String path, String fileName, String sheetName, String testCaseName, int occurance) throws Exception
	{
		try {
			ExcelReader excelReader = new ExcelReader();
			HashMap<String, ArrayList<String>> excelData = excelReader.readDataByColumn( path, fileName, sheetName, testCaseName, occurance );
	
			for(int i = 0; i < excelData.get("Username").size(); i++) 
			{
				String userName = excelData.get("Username").get(i);
				
				disableUser(userName);
			}
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public void disableUser(String userName) throws Exception
	{
		try {
			NavigationHelper.navigateToScreen( "Users", "User Search" );
			int row = SearchGridHelper.searchWithTextBox("Users_Username", userName, "Name");
			
			if (row > 0) {
				GridHelper.clickRow("SearchGrid", row, 1);
				
				if (NavigationHelper.isActionPresent("User Actions")) {
					NavigationHelper.navigateToAction("User Actions");
					
					if (NavigationHelper.isActionPresent("Disable User")) {
						NavigationHelper.navigateToAction("Disable User");
						GenericHelper.waitForElement("YesButton", searchScreenWaitSec);
						assertTrue(PopupHelper.isTextPresent("Are you sure you wish to disable the user " + userName + "?"));
						
						ButtonHelper.click("YesButton");
						GenericHelper.waitForLoadmask(searchScreenWaitSec);
						Thread.sleep(1000);
						GenericHelper.waitForLoadmask(searchScreenWaitSec);
						assertTrue(GridHelper.isBooleanValuePresent("SearchGrid", true, row, "Disabled"));
						Log4jHelper.logInfo("User " + userName + " has been disabled.");
					}
					else {
						FailureHelper.failTest("Action 'Disable User' is not found under 'User Actions' in Users screen.");
					}
				}
				else {
					FailureHelper.failTest("Action 'User Actions' is not found in Users screen.");
				}
			}
			else {
				FailureHelper.failTest("User '" + userName + "' is not found.");
			}
		} catch (AssertionError e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public void enableUser(String path, String fileName, String sheetName, String testCaseName, int occurance) throws Exception
	{
		try {
			ExcelReader excelReader = new ExcelReader();
			HashMap<String, ArrayList<String>> excelData = excelReader.readDataByColumn( path, fileName, sheetName, testCaseName, occurance );
	
			for(int i = 0; i < excelData.get("Username").size(); i++) 
			{
				String userName = excelData.get("Username").get(i);
				String currentPassword = excelData.get("Current Password").get(i);
				String newPassword = excelData.get("New Password").get(i);
				String confirmPassword = excelData.get("Confirm Password").get(i);
				
				enableUser(userName, currentPassword, newPassword, confirmPassword);
			}
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public void enableUser(String userName, String currentPassword, String newPassword, String confirmPassword) throws Exception
	{
		try {
			NavigationHelper.navigateToScreen( "Users", "User Search" );
			int row = SearchGridHelper.searchWithTextBox("Users_Username", userName, "Name");
			
			if (row > 0) {
				GridHelper.clickRow("SearchGrid", row, 1);
				
				if (NavigationHelper.isActionPresent("User Actions")) {
					NavigationHelper.navigateToAction("User Actions");
					
					if (NavigationHelper.isActionPresent("Enable User")) {
						NavigationHelper.navigateToAction("Enable User");
						GenericHelper.waitForElement("YesButton", searchScreenWaitSec);
						assertTrue(PopupHelper.isTextPresent("Are you sure you wish to enable the user " + userName + "?"));
						
						ButtonHelper.click("YesButton");
						GenericHelper.waitForLoadmask(searchScreenWaitSec);
						GenericHelper.waitForElement("ChangePassword_OK", searchScreenWaitSec);
						assertTrue(LabelHelper.isTitlePresent("Edit Change Password"));
						
						LoginHelper login = new LoginHelper();
						login.updatePassword(userName, currentPassword, newPassword, confirmPassword);
						
						ButtonHelper.click("SearchButton");
						GenericHelper.waitForLoadmask(searchScreenWaitSec);
						assertTrue(GridHelper.isBooleanValuePresent("SearchGrid", true, row, "Disabled"));
						Log4jHelper.logInfo("User " + userName + " has been enabled.");
					}
					else {
						FailureHelper.failTest("Action 'Enable User' is not found under 'User Actions' in Users screen.");
					}
				}
				else {
					FailureHelper.failTest("Action 'User Actions' is not found in Users screen.");
				}
			}
			else {
				FailureHelper.failTest("User '" + userName + "' is not found.");
			}
		} catch (AssertionError e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public void setAllPrivileges() throws Exception
	{
		try {
			String userName = "Root";
			NavigationHelper.navigateToScreen( "Users", "User Search" );
			int row = SearchGridHelper.searchWithTextBox("Users_Username", userName, "Name");
			
			if (row > 0) {
				GridHelper.clickRow("SearchGrid", row, 1);
				
				if (NavigationHelper.isActionPresent("Special Actions")) {
					NavigationHelper.navigateToAction("Special Actions");
					
					if (NavigationHelper.isActionPresent("Set All Privileges")) {
						NavigationHelper.navigateToAction("Set All Privileges");
						Thread.sleep(1000);
					}
					else {
						FailureHelper.failTest("Action 'Set All Privileges' is not found under 'Special Actions' in Users screen.");
					}
				}
				else {
					FailureHelper.failTest("Action 'Special Actions' is not found in Users screen.");
				}
			}
			else {
				FailureHelper.failTest("User '" + userName + "' is not found.");
			}
		} catch (AssertionError e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public void assignRolePrivileges(String[] role, String[] securityPartition) throws Exception
	{
		try {
			if (ValidationHelper.isNotEmpty(role)) {
				TabHelper.gotoTab("Roles And Partitions");
				GenericHelper.waitForAJAXReady(detailScreenWaitSec);
				
				for(int i = 0; i < role.length; i++) {
					String sPartition = "Common";
					String columnHeader = sPartition;
					
					if(ValidationHelper.isNotEmpty(securityPartition) && securityPartition.length > i && ValidationHelper.isNotEmpty(securityPartition[i]))
						sPartition = securityPartition[i];
					
					if(sPartition.contains(" ")) {
						columnHeader = sPartition;
						sPartition = sPartition.replaceAll(" ", "-");
					}
					
					String checkBoxLocator = or.getProperty("Users_RolePrivileges_CheckBox").replace("partition", sPartition);
					GridCheckBoxHelper.check("Users_RolePrivileges_Grid", checkBoxLocator, role[i], "Role", columnHeader);
				}
			}
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public void assignROCFlows(String[] rocFlow) throws Exception {
		try {
			if (ValidationHelper.isNotEmpty(rocFlow)) {
				TabHelper.gotoTab("ROC Flows");
				Thread.sleep(2000);
				
				for (int i = 0; i < rocFlow.length; i++)
					GridCheckBoxHelper.check("Users_ROCFlows_Grid", "Users_ROCFlows_CheckBox", rocFlow[i], "ROC Flows", "View");
			}
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public int navigateToUsers(String userName) throws Exception {
		try {
			NavigationHelper.navigateToScreen( "Users", "User Search" );
			ButtonHelper.click("ClearButton");
			GenericHelper.waitForLoadmask(searchScreenWaitSec);
			int row = SearchGridHelper.searchWithTextBox("Users_Username", userName, "Name");
			
			return row;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public boolean navigateToUsers(String userName, String partition) throws Exception {
		try {
			int row = navigateToUsers(userName);
			boolean isPresent = NavigationHelper.navigateToNewOrEdit(row, partition, "User", "Users_FirstName");
			
			return isPresent;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public int navigateToUsers(String userName, String expectedUserName, String partition) throws Exception {
		try {
			NavigationHelper.navigateToScreen( "Users", "User Search" );
			ButtonHelper.click("ClearButton");
			GenericHelper.waitForLoadmask(searchScreenWaitSec);
			SearchGridHelper.searchWithTextBox("Users_Username", userName, "Name");
			int row = GridHelper.getRowNumber("SearchGrid", userName, "Name");
			
			if (row == 0)
				row = GridHelper.getRowNumber("SearchGrid", expectedUserName, "Name");
			NavigationHelper.navigateToNewOrEdit(row, partition, "User", "Users_FirstName");

			return row;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public void saveUsers(String userName, String detailScreenTitle) throws Exception {
		try {
			ButtonHelper.click("SaveButton");
			GenericHelper.waitForLoadmask(detailScreenWaitSec);
			
			if (ButtonHelper.isPresent("YesButton")) {
				ButtonHelper.click("YesButton");
				GenericHelper.waitForLoadmask(detailScreenWaitSec);
			}
			
			GenericHelper.waitForAJAXReady(detailScreenWaitSec);
			Thread.sleep(1000);
			
			assertTrue(LabelHelper.isTitleNotPresent(detailScreenTitle), "User save did not happen.");
			ButtonHelper.click("SearchButton");
			GenericHelper.waitForLoadmask(searchScreenWaitSec);
			assertTrue(GridHelper.isValuePresent("SearchGrid", userName, "Name"), "Value '" + userName + "' is not found in grid.");
			Log4jHelper.logInfo("User '" + userName + "' created");
		} catch (AssertionError e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
}