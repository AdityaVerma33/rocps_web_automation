package com.subex.automation.helpers.db;

import org.testng.annotations.Test;

import com.subex.automation.helpers.component.ButtonHelper;
import com.subex.automation.helpers.selenium.ROCAcceptanceTest;
import com.subex.automation.helpers.util.FailureHelper;

public class CreateDbBackUp extends ROCAcceptanceTest {
	@Test
	public  void testCreateDbBackUp() throws Exception {
		try {
			if (!ButtonHelper.isPresent("Logout")) {
				DatabaseHelper dbHelper = new DatabaseHelper();
				dbHelper.createDbBackUp("Reference","freshdb.data");
			}
		}
		catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
}