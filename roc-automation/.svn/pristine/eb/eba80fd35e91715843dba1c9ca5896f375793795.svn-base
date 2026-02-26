package com.subex.automation.testcases.regressiontesting.masking;

import org.testng.annotations.Test;

import com.subex.automation.helpers.application.screens.MaskingConfigurationHelper;
import com.subex.automation.helpers.util.FailureHelper;

public class testEdit extends testMasking {
	
	final String sheetName = "Edit";
	
	public testEdit() throws Exception {
		super();
	}
	
	@Test(priority=22, description="Edit a masking configuration", groups = { "GDPREdit" })
	public void testCase1() throws Exception
	{
		try {
			// Validate that a user is able to edit a masking configuration already saved and modify as required
			MaskingConfigurationHelper masking = new MaskingConfigurationHelper();
			masking.createMaskingConfiguration(path, fileName, sheetName, "Masking", 1);
			masking.createMaskingConfiguration(path, fileName, sheetName, "Masking", 2);
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
}