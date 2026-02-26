package com.subex.automation.testcases.regressiontesting.duplicatecheck;

import org.testng.annotations.Test;

import com.subex.automation.helpers.application.screens.DuplicateXDRHelper;
import com.subex.automation.helpers.selenium.ROCAcceptanceTest;
import com.subex.automation.helpers.util.FailureHelper;

public class testNew extends ROCAcceptanceTest {
	
	static String path = null;
	final String fileName = "DuplicateCheck_TestData.xlsx";
	final String sheetName = "New";
	
	public testNew() throws Exception {
		try {
			path = automationPath + "\\src\\main\\resources\\Regression\\";
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	@Test(priority=1, description="Create Duplicate XDR for Reference Table")
	public void createDuplicateXDR() throws Exception
	{
		try {
			// Validate that user is able to create Duplicate XDR Check for a Reference Table.
			DuplicateXDRHelper duplicateCheck = new DuplicateXDRHelper();
			duplicateCheck.createDuplicateXDR(path, fileName, sheetName, "DuplicateXDRCheck", 1);
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
}