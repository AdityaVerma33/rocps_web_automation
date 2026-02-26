package com.subex.rocps.automation.testcases.functionaltesting;

import org.testng.annotations.Test;

import com.subex.automation.helpers.component.FileHelper;
import com.subex.automation.helpers.util.FailureHelper;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;

public class PasswordReset extends PSAcceptanceTest {

	@Test(priority = 1, description = "reset password in property file to welcome")
	public void resetPassword() throws Exception {
		try {
			FileHelper.updatePropertyFile(configFile, "applicationPassword", "welcome");
			Thread.sleep(1000);
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}

}
