package com.subex.automation.testcases.regressiontesting.duplicatecheck;

import java.util.ArrayList;
import java.util.HashMap;

import org.testng.annotations.Test;

import com.subex.automation.helpers.application.NavigationHelper;
import com.subex.automation.helpers.application.screens.DuplicateXDRHelper;
import com.subex.automation.helpers.component.ComboBoxHelper;
import com.subex.automation.helpers.component.GridCheckBoxHelper;
import com.subex.automation.helpers.file.ExcelReader;
import com.subex.automation.helpers.selenium.ROCAcceptanceTest;
import com.subex.automation.helpers.util.FailureHelper;

public class testEdit extends ROCAcceptanceTest {
	
	static String path = null;
	final String fileName = "DuplicateCheck_TestData.xlsx";
	final String sheetName = "Edit";
	
	public testEdit() throws Exception {
		try {
			path = automationPath + "\\src\\main\\resources\\Regression\\";
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	@Test(priority=1, description="Edit Duplicate XDR Check configuration")
	public void testCase1() throws Exception
	{
		try {
			// Verify if user is able to edit an existing Duplicate XDR Check configuration
			DuplicateXDRHelper duplicateCheck = new DuplicateXDRHelper();
			duplicateCheck.createDuplicateXDR(path, fileName, sheetName, "DuplicateXDRCheck", 1);
			
			ExcelReader excelReader = new ExcelReader();
			HashMap<String, ArrayList<String>> excelData = excelReader.readDataByColumn( path, fileName, sheetName, "DuplicateXDRCheck", 1 );
			String duplicateXDRName = excelData.get( "Name" ).get(0);
			int row = duplicateCheck.navigateToDuplicateXDR(duplicateXDRName);
			
			if (row > 0) {
				String detailScreenTitle = NavigationHelper.navigateToEdit("SearchGrid", row, "DuplicateXDR_Name");
				
				ComboBoxHelper.select( "DuplicateXDR_TagStatusField", "tag_status" );
				GridCheckBoxHelper.uncheck("DuplicateXDR_MatchDuplicates_Grid", "DuplicateXDR_Match_CheckBox", "duration", "Name", "Select");
				GridCheckBoxHelper.check("DuplicateXDR_MatchDuplicates_Grid", "DuplicateXDR_Match_CheckBox", "time_stamp", "Name", "Select");
				
				duplicateCheck.saveDuplicateXDR(duplicateXDRName, detailScreenTitle);
				
				NavigationHelper.navigateToEdit("SearchGrid", row, "DuplicateXDR_Name");
				assertTrue(ComboBoxHelper.isValuePresent("DuplicateXDR_TagStatusField", "tag_status"));
				assertFalse(GridCheckBoxHelper.isChecked("DuplicateXDR_MatchDuplicates_Grid", "DuplicateXDR_Match_CheckBox", "duration", "Name", "Select"));
				assertTrue(GridCheckBoxHelper.isChecked("DuplicateXDR_MatchDuplicates_Grid", "DuplicateXDR_Match_CheckBox", "time_stamp", "Name", "Select"));
			}
			else {
				FailureHelper.failTest("Duplicate XDR Check '" + duplicateXDRName + "' is not found.");
			}
		} catch (AssertionError e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
}