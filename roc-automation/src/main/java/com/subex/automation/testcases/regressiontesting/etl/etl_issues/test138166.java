package com.subex.automation.testcases.regressiontesting.etl.etl_issues;

import org.testng.annotations.Test;

import com.subex.automation.helpers.application.NavigationHelper;
import com.subex.automation.helpers.application.ReferenceTableHelper;
import com.subex.automation.helpers.component.ComboBoxHelper;
import com.subex.automation.helpers.component.FileHelper;
import com.subex.automation.helpers.component.GenericHelper;
import com.subex.automation.helpers.component.GridHelper;
import com.subex.automation.helpers.component.TextBoxHelper;
import com.subex.automation.helpers.db.DatabaseHelper;
import com.subex.automation.helpers.util.FailureHelper;

public class test138166 extends testETLIssues {
	
	final String sheetName = "ASCII";
	
	public test138166() throws Exception {
		super();
	}
	
	@Test(priority=41, description="Test Case 1 for Bug 138166")
	public void testCase1() throws Exception {
		try {
			// All the Reference Table entities should be available under Reference Table drop down
			NavigationHelper.navigateToScreen("Reference Tables", "Reference Table Search");
			GenericHelper.waitForLoadmask(searchScreenWaitSec);
			GenericHelper.waitForAJAXReady(searchScreenWaitSec);
			String[] actualRefTableEntries = ComboBoxHelper.getAllValues("ReferenceTable_DropDownArrow");
			int actualLength = actualRefTableEntries.length-1;
			
			DatabaseHelper databaseHelper = new DatabaseHelper();
			String query = "select rft_name from ref_table where rft_visible_fl = 'Y' order by rft_name";
			String refTableFile = databaseHelper.executeQuery("Reference", query);
			String[] expectedRefTableEntries = FileHelper.readFileContent(automationOS, refTableFile);
			int expectedLength = expectedRefTableEntries.length-1;
			
			if (actualLength == expectedLength) {
				boolean notMatching = false;
				String values = null;
				
				for (int i = 1; i < expectedLength; i++) {
					// Name is manipulated for 'Analytics Distribution' in GUI
					if (expectedRefTableEntries[i].equals("Analytics Distribution"))
						expectedRefTableEntries[i] = "Distribution";
					
					if (!actualRefTableEntries[i].equals(expectedRefTableEntries[i])) {
						notMatching = true;
						values = expectedRefTableEntries[i] + " > " + actualRefTableEntries[i] + "\n";
					}
				}
				
				if (notMatching)
					FailureHelper.failTest("In Reference Table combo box, expected entry is different from actual.\nExpected > Actual\n" + values);
			}
			else {
				FailureHelper.failTest("In Reference Table combo box, expected '" + expectedLength + "' number of entries. But found '" + actualLength + "' entries.");
			}
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		} finally {
			if (GridHelper.isPresent("SearchGrid"))
				GridHelper.click("SearchGrid");
		}
	}
	
	@Test(priority=42, description="Test Case 2 for Bug 138166")
	public void testCase2() throws Exception {
		try {
			// Selecting a entity in Reference Table drop down should load the related entity search screen
			NavigationHelper.navigateToScreen("Reference Tables", "Reference Table Search");
			GenericHelper.waitForLoadmask(searchScreenWaitSec);
			GenericHelper.waitForAJAXReady(searchScreenWaitSec);
			
			ComboBoxHelper.select("ReferenceTable_DropDownArrow", "Data Source Location");
			GenericHelper.waitForLoadmask(searchScreenWaitSec);
			GenericHelper.waitForAJAXReady(searchScreenWaitSec);
			assertTrue(TextBoxHelper.isPresent("DSL_Name"));
		} catch (AssertionError e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	@Test(priority=43, description="Test Case 3 for Bug 138166")
	public void testCase3() throws Exception {
		try {
			// New action should work for Reference Table entity
			ReferenceTableHelper referenceTable = new ReferenceTableHelper();
			referenceTable.dataSourceLocation(path, fileName, sheetName, "DSL", 1);
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	@Test(priority=44, description="Test Case 4 for Bug 138166")
	public void testCase4() throws Exception {
		try {
			// Edit action should work for Reference Table entity
			ReferenceTableHelper referenceTable = new ReferenceTableHelper();
			referenceTable.dataSourceLocation(path, fileName, sheetName, "DSL", 1);
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
}