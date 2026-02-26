package com.subex.automation.testcases.regressiontesting.duplicatecheck;

import java.util.ArrayList;

import org.testng.annotations.Test;

import com.subex.automation.helpers.application.CopyHelper;
import com.subex.automation.helpers.application.NavigationHelper;
import com.subex.automation.helpers.component.ButtonHelper;
import com.subex.automation.helpers.component.GenericHelper;
import com.subex.automation.helpers.component.GridHelper;
import com.subex.automation.helpers.component.SearchGridHelper;
import com.subex.automation.helpers.selenium.ROCAcceptanceTest;
import com.subex.automation.helpers.util.FailureHelper;

public class testCopy extends ROCAcceptanceTest {
	
	private ArrayList<String> getColumnValues(String searchName) throws Exception
	{
		try {
			NavigationHelper.navigateToScreen( "Duplicate XDR Check", "Duplicate XDR Check Search" );
			SearchGridHelper.gridFilterSearchWithTextBox("DuplicateXDR_Name", searchName, "Name");
			ButtonHelper.click("SearchButton");
			GenericHelper.waitForLoadmask(searchScreenWaitSec);
			
			ArrayList<String> name = GridHelper.getColumnValues("SearchGrid", "Name");
			return name;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	@Test(priority=1, description="Copy Duplicate XDR Check")
	public void testCase1() throws Exception
	{
		try {
			// Verify if user is able to perform Search screen Copy action in Duplicate XDR Check screen
			ArrayList<String> name = getColumnValues("%1%");
			int rowNum = GridHelper.getRowNumber("SearchGrid", name.get(0), "Name");
			
			CopyHelper copy = new CopyHelper();
			copy.copySelectedCell("SearchGrid", rowNum, "Name");
			String value = copy.getCopyContent();
			assertTrue(value.contains("Name\n" + name.get(0)), "Expected value '" + name.get(0) + "' is not found in Copy popup.");
			copy.closeCopyPopup();
			
			String expectedValue = "Name\n" + name.get(0);
			for (int i = 1; i < name.size(); i++)
				expectedValue = expectedValue + "	\n" + name.get(i);
			copy.copySelectedColumn("SearchGrid", rowNum, "Name");
			value = copy.getCopyContent();
			assertTrue(value.contains(expectedValue), "Expected value '" + expectedValue + "' is not found in Copy popup.");
			copy.closeCopyPopup();
			
			ArrayList<String> values = GridHelper.getRowValues("SearchGrid", rowNum);
			expectedValue = values.get(0);
			for (int i = 1; i < values.size(); i++)
				expectedValue = expectedValue + "	" + values.get(i);
			copy.copySelectedRow("SearchGrid", rowNum, "Name");
			value = copy.getCopyContent();
			assertTrue(value.contains(expectedValue), "Expected value '" + expectedValue + "' is not found in Copy popup.");
			copy.closeCopyPopup();
			
			copy.copyAllRows("SearchGrid");
			value = copy.getCopyContent();
			assertTrue(value.contains(expectedValue), "Expected value '" + expectedValue + "' is not found in Copy popup.");
			copy.closeCopyPopup();
		} catch (AssertionError e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		} finally {
			CopyHelper copy = new CopyHelper();
			copy.closeCopyPopup();
		}
	}
}