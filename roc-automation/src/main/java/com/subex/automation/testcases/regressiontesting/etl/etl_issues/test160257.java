package com.subex.automation.testcases.regressiontesting.etl.etl_issues;

import org.testng.annotations.Test;

import com.subex.automation.helpers.application.NavigationHelper;
import com.subex.automation.helpers.application.ReferenceTableHelper;
import com.subex.automation.helpers.component.ButtonHelper;
import com.subex.automation.helpers.component.GenericHelper;
import com.subex.automation.helpers.component.GridHelper;
import com.subex.automation.helpers.component.LabelHelper;
import com.subex.automation.helpers.component.TextBoxHelper;
import com.subex.automation.helpers.util.FailureHelper;

public class test160257 extends testETLIssues {
	
	public test160257() throws Exception {
		super();
	}
	
	@Test(priority=51, description="Test Case 1 for Bug 160257")
	public void testCase1() throws Exception {
		try {
			// Edit DSL for Data Source Type Oracle DSL name should not disappear from DSL Name Text box.
			ReferenceTableHelper referenceTable = new ReferenceTableHelper();
			String name = "ETL Issues DSL1";
			referenceTable.dataSourceLocation("Common", name, "Oracle", configProp.getDbMachineName(), configProp.getDbInstance(), "");
			
			int row = GridHelper.getRowNumber("SearchGrid", name, "Name");
			NavigationHelper.navigateToEdit("SearchGrid", row, "Detail_Popup");
			assertTrue(LabelHelper.isTitlePresent("Edit Data Source Location"), "DSL Edit popup did not appear.");
			String actualName = TextBoxHelper.getValue("Detail_Popup", "DSL_Name");
			assertEquals(actualName, name, "Expected '" + name + "' but found '" + actualName + "' in Text Box");
			
			ButtonHelper.click("CancelButton");
			GenericHelper.waitForLoadmask(detailScreenWaitSec);
		} catch (AssertionError e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	@Test(priority=52, description="Test Case 2 for Bug 160257")
	public void testCase2() throws Exception {
		try {
			// Edit DSL for Data Source Type PostgreSQL DSL name should not disappear from DSL Name Text box.
			ReferenceTableHelper referenceTable = new ReferenceTableHelper();
			String name = "ETL Issues DSL2";
			referenceTable.dataSourceLocation("Common", name, "PostgreSQL", configProp.getDbMachineName(), configProp.getDbInstance(), "");
			
			int row = GridHelper.getRowNumber("SearchGrid", name, "Name");
			NavigationHelper.navigateToEdit("SearchGrid", row, "Detail_Popup");
			assertTrue(LabelHelper.isTitlePresent("Edit Data Source Location"), "DSL Edit popup did not appear.");
			String actualName = TextBoxHelper.getValue("Detail_Popup", "DSL_Name");
			assertEquals(actualName, name, "Expected '" + name + "' but found '" + actualName + "' in Text Box");
			
			ButtonHelper.click("CancelButton");
			GenericHelper.waitForLoadmask(detailScreenWaitSec);
		} catch (AssertionError e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	@Test(priority=53, description="Test Case 3 for Bug 160257")
	public void testCase3() throws Exception {
		try {
			// Edit DSL for Data Source Type Vertica DSL name should not disappear from DSL Name Text box.
			ReferenceTableHelper referenceTable = new ReferenceTableHelper();
			String name = "ETL Issues DSL2";
			referenceTable.dataSourceLocation("Common", name, "Vertica", configProp.getDbMachineName(), configProp.getDbInstance(), "");
			
			int row = GridHelper.getRowNumber("SearchGrid", name, "Name");
			NavigationHelper.navigateToEdit("SearchGrid", row, "Detail_Popup");
			assertTrue(LabelHelper.isTitlePresent("Edit Data Source Location"), "DSL Edit popup did not appear.");
			String actualName = TextBoxHelper.getValue("Detail_Popup", "DSL_Name");
			assertEquals(actualName, name, "Expected '" + name + "' but found '" + actualName + "' in Text Box");
			
			ButtonHelper.click("CancelButton");
			GenericHelper.waitForLoadmask(detailScreenWaitSec);
		} catch (AssertionError e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	@Test(priority=54, description="Test Case 4 for Bug 160257")
	public void testCase4() throws Exception {
		try {
			// Edit DSL for Data Source Type MySQL DSL name should not disappear from DSL Name Text box.
			ReferenceTableHelper referenceTable = new ReferenceTableHelper();
			String name = "zabbix";
			referenceTable.dataSourceLocation("Common", name, "MySQL", configProp.getDbMachineName(), configProp.getDbInstance(), "");
			
			int row = GridHelper.getRowNumber("SearchGrid", name, "Name");
			NavigationHelper.navigateToEdit("SearchGrid", row, "Detail_Popup");
			assertTrue(LabelHelper.isTitlePresent("Edit Data Source Location"), "DSL Edit popup did not appear.");
			String actualName = TextBoxHelper.getValue("Detail_Popup", "DSL_Name");
			assertEquals(actualName, name, "Expected '" + name + "' but found '" + actualName + "' in Text Box");
			
			ButtonHelper.click("CancelButton");
			GenericHelper.waitForLoadmask(detailScreenWaitSec);
		} catch (AssertionError e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
}