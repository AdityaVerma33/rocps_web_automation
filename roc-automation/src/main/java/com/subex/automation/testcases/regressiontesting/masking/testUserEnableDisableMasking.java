package com.subex.automation.testcases.regressiontesting.masking;

import java.util.ArrayList;
import java.util.HashMap;

import org.testng.annotations.Test;

import com.subex.automation.helpers.application.LoginHelper;
import com.subex.automation.helpers.application.NavigationHelper;
import com.subex.automation.helpers.application.screens.MaskingConfigurationHelper;
import com.subex.automation.helpers.application.screens.UserHelper;
import com.subex.automation.helpers.component.GridHelper;
import com.subex.automation.helpers.component.SearchGridHelper;
import com.subex.automation.helpers.file.ExcelReader;
import com.subex.automation.helpers.util.FailureHelper;

public class testUserEnableDisableMasking extends testMasking {
	
	final String sheetName = "Users";

	public testUserEnableDisableMasking() throws Exception {
		super();
	}
	
	@Test(priority=20, description="Test enabling and disabling masking of a column", groups = { "GDPREnableDisable" })
	public void testEnableDisableColumn() throws Exception {
		try {
			// Enable and Disable the columns in Masking Config Screen system tbl and check .It Should work as expected.
			MaskingConfigurationHelper maskingConfig = new MaskingConfigurationHelper();
			maskingConfig.createMaskingConfiguration(path, fileName, sheetName, "MaskingConfigEdit", 1);
			
			LoginHelper login = new LoginHelper();
			login.login(path, fileName, sheetName, "UserLogin", 1);
			
			UserHelper user = new UserHelper();
			user.createUser(path, fileName, sheetName, "User2", 1);
			
			ExcelReader excelReader = new ExcelReader();
			HashMap<String, ArrayList<String>> excelData = excelReader.readDataByColumn( path, fileName, sheetName, "User2", 1 );
			String userName = excelData.get("Username").get(0);
			String emailAddress = excelData.get("Email Address").get(0);
			String phoneNo = excelData.get("Phone No").get(0);
			String expectedEmailAddress = "xxxxxx" + emailAddress.substring(6);
			String expectedPhoneNo = phoneNo.substring(0, 2) + "xxxxxx" + phoneNo.substring(8);
			
			NavigationHelper.navigateToScreen( "Users", "User Search" );
			int rowNum = SearchGridHelper.searchWithTextBox("Users_Username", userName, "Name");
			assertTrue(rowNum > 0);
			assertTrue(GridHelper.isValuePresent("SearchGrid", userName, "Name"));
			assertFalse(GridHelper.isValuePresent("SearchGrid", emailAddress, "Email Address"));
			assertTrue(GridHelper.isValuePresent("SearchGrid", expectedEmailAddress, "Email Address"));
			assertFalse(GridHelper.isValuePresent("SearchGrid", phoneNo, "Cell Number"));
			assertTrue(GridHelper.isValuePresent("SearchGrid", expectedPhoneNo, "Cell Number"));
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
}