package com.subex.automation.testcases.regressiontesting.masking;

import java.util.ArrayList;
import java.util.HashMap;

import org.testng.annotations.Test;

import com.subex.automation.helpers.application.LoginHelper;
import com.subex.automation.helpers.application.NavigationHelper;
import com.subex.automation.helpers.application.screens.MaskingConfigurationHelper;
import com.subex.automation.helpers.application.screens.RolesHelper;
import com.subex.automation.helpers.application.screens.SettingsHelper;
import com.subex.automation.helpers.application.screens.UserHelper;
import com.subex.automation.helpers.component.ButtonHelper;
import com.subex.automation.helpers.component.GenericHelper;
import com.subex.automation.helpers.component.GridHelper;
import com.subex.automation.helpers.component.SearchGridHelper;
import com.subex.automation.helpers.file.ExcelReader;
import com.subex.automation.helpers.report.Log4jHelper;
import com.subex.automation.helpers.util.FailureHelper;

public class testEnableGDPR extends testMasking {

	final String sheetName = "EnableGDPR";

	public testEnableGDPR() throws Exception {
		super();
	}
	
	private void checkUserSearchResult(String foreName, String expectedUserName, String expectedEmail) throws Exception {
		try {
			NavigationHelper.navigateToScreen( "Users", "User Search" );
			ButtonHelper.click("ClearButton");
			GenericHelper.waitForLoadmask(searchScreenWaitSec);
			
			SearchGridHelper.gridFilterSearchWithTextBox("Users_FirstName", foreName, "Forename");
			assertTrue(GridHelper.isValuePresent("SearchGrid", expectedUserName, "Name"));
			
			int row = GridHelper.getRowNumber("SearchGrid", expectedUserName, "Name");
			String acutalEmail = GridHelper.getCellValue("SearchGrid", row, "Email Address");
			assertEquals(acutalEmail, expectedEmail);
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	@Test(priority=1, description="Validate masking on User Screen when GDPR disabled", groups = { "enableGDPR" })
	public void testGDPRDisable() throws Exception
	{
		try {
			// Validate that masking configuration is not applied on any screen, if "Enable GDPR" is not checked in Settings > System Properties
			Log4jHelper.logInfo("Running Masking Regression");
			RolesHelper roles = new RolesHelper();
			roles.createRole(path, fileName, sheetName, "Roles", 1);
			
			UserHelper user = new UserHelper();
			user.createUser(path, fileName, sheetName, "Users", 1);
			
			MaskingConfigurationHelper maskingConfig = new MaskingConfigurationHelper();
			maskingConfig.createMaskingConfiguration(path, fileName, sheetName, "MaskingConfiguration", 1);
			
			ExcelReader excelReader = new ExcelReader();
			HashMap<String, ArrayList<String>> excelData = excelReader.readDataByColumn( path, fileName, sheetName, "Users", 1 );
			String userName = excelData.get("Username").get(1);
			String foreName = excelData.get("First Name").get(1);
			String emailAddress = excelData.get("Email Address").get(1);
			
			checkUserSearchResult(foreName, userName, emailAddress);
			
			LoginHelper login = new LoginHelper();
			login.login(path, fileName, sheetName, "UserLogin", 1);
			
			checkUserSearchResult(foreName, userName, emailAddress);
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	@Test(priority=2, description="Validate Masking on User Screen when GDPR enabled", groups = { "enableGDPR" })
	public void testGDPREnable() throws Exception
	{
		try {
			// Validate that masking configuration is applied on screens, only if "Enable GDPR" is checked in Settings > System Properties
			LoginHelper login = new LoginHelper();
			login.loginWithConfigPropertyDetails();
			
			SettingsHelper setting = new SettingsHelper();
			setting.updateSettings(path, fileName, sheetName, "GDPRSystemSettings", 1);
			
			RolesHelper roles = new RolesHelper();
			roles.createRole(path, fileName, sheetName, "Roles", 1);
			
			UserHelper user = new UserHelper();
			user.createUser(path, fileName, sheetName, "Users", 1);
			
			MaskingConfigurationHelper maskingConfig = new MaskingConfigurationHelper();
			maskingConfig.createMaskingConfiguration(path, fileName, sheetName, "MaskingConfiguration", 1);
			
			ExcelReader excelReader = new ExcelReader();
			HashMap<String, ArrayList<String>> excelData = excelReader.readDataByColumn( path, fileName, sheetName, "Users", 1 );
			String userName = excelData.get("Username").get(1);
			String foreName = excelData.get("First Name").get(1);
			String emailAddress = excelData.get("Email Address").get(1);
			
			checkUserSearchResult(foreName, userName, emailAddress);
			
			login.login(path, fileName, sheetName, "UserLogin", 1);
			String searchUserName = userName;
			userName = userName.substring(0,1) + "xxxx" + searchUserName.substring(5);
			emailAddress = "xxxxxx" + emailAddress.substring(6);
			checkUserSearchResult(foreName, userName, emailAddress);
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
}