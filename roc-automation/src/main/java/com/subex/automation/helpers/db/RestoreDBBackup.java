package com.subex.automation.helpers.db;

import org.testng.annotations.Test;

import com.subex.automation.helpers.selenium.ROCAcceptanceTest;
import com.subex.automation.helpers.util.FailureHelper;

public class RestoreDBBackup extends ROCAcceptanceTest {
	@Test
	public void DBRestore() throws Exception {
		try {
			DatabaseHelper dbHelper = new DatabaseHelper();
			dbHelper.restoreDbBackUp("Reference", "freshdb.data");
		}
		catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
}