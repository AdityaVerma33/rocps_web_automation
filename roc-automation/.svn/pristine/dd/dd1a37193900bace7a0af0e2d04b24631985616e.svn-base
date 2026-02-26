package com.subex.automation.testcases.regressiontesting.masking;

import org.testng.annotations.Test;

import com.subex.automation.helpers.application.LoginHelper;
import com.subex.automation.helpers.application.screens.JumpToSearchHelper;
import com.subex.automation.helpers.application.screens.MaskingConfigurationHelper;
import com.subex.automation.helpers.application.screens.RolesHelper;
import com.subex.automation.helpers.application.screens.UserHelper;
import com.subex.automation.helpers.util.FailureHelper;

public class testMaskingAll extends testMasking {
	
	final String sheetName = "MaskAll";
	
	public testMaskingAll() throws Exception {
		super();
	}
	
	@Test(priority=23, description="Test masking of all fields", groups = { "GDPRMaskAll" })
	public void testMasking() throws Exception
	{
		try {
			// Validate that all the values should be masked when numeric datatypes are considered
			MaskingConfigurationHelper masking = new MaskingConfigurationHelper();
			masking.createMaskingConfiguration(path, fileName, sheetName, "Masking", 1);
			
			RolesHelper roles = new RolesHelper();
			roles.createRole(path, fileName, sheetName, "Roles", 1);
			
			UserHelper user = new UserHelper();
			user.createUser(path, fileName, sheetName, "Users", 1);
			
			LoginHelper login = new LoginHelper();
			login.login(path, fileName, sheetName, "UserLogin", 1);
			
			JumpToSearchHelper jumpToSearch = new JumpToSearchHelper();
			jumpToSearch.verifyJumpToSearchResult(path, fileName, sheetName, "J2S", 1);
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
}