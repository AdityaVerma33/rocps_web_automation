package com.subex.automation.testcases.regressiontesting.duplicatecheck;

import java.util.ArrayList;

import org.testng.annotations.Test;

import com.subex.automation.helpers.application.ExportHelper;
import com.subex.automation.helpers.application.NavigationHelper;
import com.subex.automation.helpers.application.screens.SettingsHelper;
import com.subex.automation.helpers.component.ButtonHelper;
import com.subex.automation.helpers.component.FileHelper;
import com.subex.automation.helpers.component.GenericHelper;
import com.subex.automation.helpers.component.GridHelper;
import com.subex.automation.helpers.component.SearchGridHelper;
import com.subex.automation.helpers.selenium.ROCAcceptanceTest;
import com.subex.automation.helpers.util.FailureHelper;

public class testExport extends ROCAcceptanceTest {
	
	static String path = null;
	final String fileName = "DuplicateCheck_TestData.xlsx";
	final String sheetName = "Export";
	
	public testExport() throws Exception {
		try {
			path = automationPath + "\\src\\main\\resources\\Regression\\";
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	private ArrayList<String> getRowValues(String searchName) throws Exception
	{
		try {
			NavigationHelper.navigateToScreen( "Duplicate XDR Check", "Duplicate XDR Check Search" );
			SearchGridHelper.gridFilterSearchWithTextBox("DuplicateXDR_Name", searchName, "Name");
			ButtonHelper.click("SearchButton");
			GenericHelper.waitForLoadmask(searchScreenWaitSec);
			
			ArrayList<String> values = GridHelper.getRowValues("SearchGrid", 1);
			return values;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	@Test(priority=1, description="Export Duplicate XDR Check")
	public void testCase1() throws Exception
	{
		try {
			// Verify if user is able to perform Search screen Export action in Duplicate XDR Check screen
			SettingsHelper settings = new SettingsHelper();
			settings.updateSettings(path, fileName, sheetName, "UpdateSettings", 1);
			
			ArrayList<String> values = getRowValues("%1%");
			String expectedValue = values.get(0);
			for (int i = 1; i < values.size()-1; i++)
				expectedValue = expectedValue + "	" + values.get(i);
			int rowNum = GridHelper.getRowNumber("SearchGrid", values.get(0), "Name");
			
			ExportHelper export = new ExportHelper();
			String fileName = export.exportSelectedRows("SearchGrid", rowNum, "Name");
			String[] content = FileHelper.readFileContent(automationOS, fileName);
			assertTrue(content[1].contains(expectedValue), "Expected value '" + expectedValue + "' is not found in Export file.");
			
			fileName = export.exportConfiguredRows("SearchGrid");
			content = FileHelper.readFileContent(automationOS, fileName);
			assertTrue(content[1].contains(expectedValue), "Expected value '" + expectedValue + "' is not found in Export file.");
		} catch (AssertionError e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
}