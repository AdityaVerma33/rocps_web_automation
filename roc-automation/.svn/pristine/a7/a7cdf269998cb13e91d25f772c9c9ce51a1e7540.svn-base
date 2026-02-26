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

public class testUserMaskingDelete extends testMasking {

	final String sheetName = "Users";

	public testUserMaskingDelete() throws Exception {
		super();
	}
	
	@Test(priority=21, description="Test delete of masking configuration", groups = { "GDPRDelete" })
	public void testMaskingConfigDelete() throws Exception {
		try {
			// Delete a masking configuration on system tbl and then validate detail and search screens
			ExcelReader excelReader = new ExcelReader();
			HashMap<String, ArrayList<String>> excelData = excelReader.readDataByColumn( path, fileName, sheetName, "MaskingConfigEdit", 1 );
			String name = excelData.get("Name").get(0);
			
			MaskingConfigurationHelper maskingConfig = new MaskingConfigurationHelper();
			int row = maskingConfig.navigateToMaskingConfiguration(name);
			
			if (row > 0) {
				NavigationHelper.delete("SearchGrid", name, "Name");
				
				excelData = excelReader.readDataByColumn( path, fileName, sheetName, "User2", 1 );
				String userName = excelData.get("Username").get(0);
				String emailAddress = excelData.get("Email Address").get(0);
				String phoneNo = excelData.get("Phone No").get(0);
				
				NavigationHelper.navigateToScreen( "Users", "User Search" );
				int rowNum = SearchGridHelper.searchWithTextBox("Users_Username", userName, "Name");
				assertTrue(rowNum > 0);
				assertTrue(GridHelper.isValuePresent("SearchGrid", userName, "Name"));
				assertTrue(GridHelper.isValuePresent("SearchGrid", emailAddress, "Email Address"));
				assertTrue(GridHelper.isValuePresent("SearchGrid", phoneNo, "Cell Number"));
				
				LoginHelper login = new LoginHelper();
				login.login(path, fileName, sheetName, "UserLogin", 1);
				
				UserHelper user = new UserHelper();
				user.createUser(path, fileName, sheetName, "User2", 1);
				
				NavigationHelper.navigateToScreen( "Users", "User Search" );
				rowNum = SearchGridHelper.searchWithTextBox("Users_Username", userName, "Name");
				assertTrue(rowNum > 0);
				assertTrue(GridHelper.isValuePresent("SearchGrid", userName, "Name"));
				assertTrue(GridHelper.isValuePresent("SearchGrid", emailAddress, "Email Address"));
				assertTrue(GridHelper.isValuePresent("SearchGrid", phoneNo, "Cell Number"));
			}
			else {
				FailureHelper.failTest("Masking Configuration '" + name + "' is not found.");
			}
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
}