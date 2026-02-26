package com.subex.automation.testcases.regressiontesting.usermanagement.usermgnt_issues;

import com.subex.automation.helpers.selenium.ROCAcceptanceTest;
import com.subex.automation.helpers.util.FailureHelper;

public class testUserManagement extends ROCAcceptanceTest {
	
	String path = null;
	final String fileName = "UserManagement_TestData.xlsx";
	
	public testUserManagement() throws Exception {
		try {
			path = automationPath + "\\src\\main\\resources\\Issues\\";
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
}
