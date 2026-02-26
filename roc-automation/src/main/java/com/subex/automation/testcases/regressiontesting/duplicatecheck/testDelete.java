package com.subex.automation.testcases.regressiontesting.duplicatecheck;

import java.util.ArrayList;
import java.util.HashMap;

import org.testng.annotations.Test;

import com.subex.automation.helpers.application.NavigationHelper;
import com.subex.automation.helpers.application.screens.DuplicateXDRHelper;
import com.subex.automation.helpers.component.ButtonHelper;
import com.subex.automation.helpers.component.ComboBoxHelper;
import com.subex.automation.helpers.component.GenericHelper;
import com.subex.automation.helpers.component.GridHelper;
import com.subex.automation.helpers.file.ExcelReader;
import com.subex.automation.helpers.selenium.ROCAcceptanceTest;
import com.subex.automation.helpers.util.FailureHelper;

public class testDelete extends ROCAcceptanceTest {
	
	static String path = null;
	final String fileName = "DuplicateCheck_TestData.xlsx";
	final String sheetName = "Delete";
	
	public testDelete() throws Exception {
		try {
			path = automationPath + "\\src\\main\\resources\\Regression\\";
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	@Test(priority=1, description="Delete Duplicate XDR Check configuration", groups = { "delete" })
	public void testXDRDelete() throws Exception
	{
		try {
			// Verify if user is able to delete an existing Duplicate XDR Check configuration
			DuplicateXDRHelper duplicateCheck = new DuplicateXDRHelper();
			duplicateCheck.createDuplicateXDR(path, fileName, sheetName, "DuplicateXDRCheck", 1);
			
			ExcelReader excelReader = new ExcelReader();
			HashMap<String, ArrayList<String>> excelData = excelReader.readDataByColumn( path, fileName, sheetName, "DuplicateXDRCheck", 1 );
			String duplicateXDRName = excelData.get( "Name" ).get(0);
			int row = duplicateCheck.navigateToDuplicateXDR(duplicateXDRName);
			
			if (row > 0) {
				NavigationHelper.delete("SearchGrid", duplicateXDRName, "Name");
			}
			else {
				FailureHelper.failTest("Duplicate XDR Check '" + duplicateXDRName + "' is not found.");
			}
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	@Test(priority=2, description="Undelete Duplicate XDR Check configuration", dependsOnMethods = { "testXDRDelete" }, groups = { "delete" })
	public void testXDRUndelete() throws Exception
	{
		try {
			// Verify if user is able to undelete an existing Duplicate XDR Check configuration
			ExcelReader excelReader = new ExcelReader();
			HashMap<String, ArrayList<String>> excelData = excelReader.readDataByColumn( path, fileName, sheetName, "DuplicateXDRCheck", 1 );
			String duplicateXDRName = excelData.get( "Name" ).get(0);
			DuplicateXDRHelper duplicateCheck = new DuplicateXDRHelper();
			duplicateCheck.navigateToDuplicateXDR(duplicateXDRName);
			ComboBoxHelper.select("Include_Combo", "Deleted Items");
			ButtonHelper.click("SearchButton");
			GenericHelper.waitForLoadmask(searchScreenWaitSec);
			int row = GridHelper.getRowNumber("SearchGrid", duplicateXDRName, "Name");
			
			if (row > 0) {
				NavigationHelper.undelete("SearchGrid", duplicateXDRName, "Name");
			}
			else {
				FailureHelper.failTest("Duplicate XDR Check '" + duplicateXDRName + "' is not found.");
			}
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
}