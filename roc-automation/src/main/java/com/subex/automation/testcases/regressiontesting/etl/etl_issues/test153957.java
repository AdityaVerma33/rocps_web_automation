package com.subex.automation.testcases.regressiontesting.etl.etl_issues;

import org.testng.annotations.Test;

import com.subex.automation.helpers.application.NavigationHelper;
import com.subex.automation.helpers.component.ComboBoxHelper;
import com.subex.automation.helpers.component.GenericHelper;
import com.subex.automation.helpers.component.TextBoxHelper;
import com.subex.automation.helpers.report.Log4jHelper;
import com.subex.automation.helpers.util.FailureHelper;

public class test153957 extends testETLIssues {
	
	public test153957() throws Exception {
		super();
	}

	@Test(priority=31, description="Test Case 1 for Bug 153957")
	public void testCase1() throws Exception {
		try {
			// On navigating to Reference table, screen should not hang
			Log4jHelper.logInfo("Running ETL Issues Test Cases");
			NavigationHelper.navigateToScreen("Reference Tables", "Reference Table Search");
			GenericHelper.waitForLoadmask(searchScreenWaitSec);
			GenericHelper.waitForAJAXReady(searchScreenWaitSec);
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	@Test(priority=32, description="Test Case 2 for Bug 153957")
	public void testCase2() throws Exception {
		try {
			// Selecting a entity in Reference Table drop down should load the related entity search screen
			NavigationHelper.navigateToScreen("Reference Tables", "Reference Table Search");
			GenericHelper.waitForLoadmask(searchScreenWaitSec);
			GenericHelper.waitForAJAXReady(searchScreenWaitSec);
			
			ComboBoxHelper.select("ReferenceTable_DropDownArrow", "Data Source Connection");
			GenericHelper.waitForLoadmask(searchScreenWaitSec);
			GenericHelper.waitForAJAXReady(searchScreenWaitSec);
			assertTrue(TextBoxHelper.isPresent("DSC_Name"));
		} catch (AssertionError e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
}