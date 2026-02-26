package com.subex.automation.testcases.regressiontesting.measures_audits.audit_issues;

import org.testng.annotations.Test;

import com.subex.automation.helpers.application.ConfigureGridHelper;
import com.subex.automation.helpers.application.CopyHelper;
import com.subex.automation.helpers.application.ExportHelper;
import com.subex.automation.helpers.component.ButtonHelper;
import com.subex.automation.helpers.component.FileHelper;
import com.subex.automation.helpers.component.GenericHelper;
import com.subex.automation.helpers.component.GridHelper;
import com.subex.automation.helpers.data.ValidationHelper;
import com.subex.automation.helpers.util.FailureHelper;

public class test151878 extends testAuditIssues {
	
	final String sheetName = "test151878";
	
	public test151878() throws Exception {
		super();
	}
	
	@Test(priority=1, description="Test Case 1 for Bug 151878")
	public void testCase1() throws Exception {
		try {
			// Validate that Copy > Selected Cell functionality working on Measure Page Null value configured columns in Configured Grid
			ConfigureGridHelper configureGrid = new ConfigureGridHelper();
			configureGrid.updateConfigureGrid(path, fileName, sheetName, "MeasureConfigureGrid", 1);
			
			ButtonHelper.click("SearchButton");
			GenericHelper.waitForLoadmask(searchScreenWaitSec);
			int row = GridHelper.getRowNumber("SearchGrid", "(None)", "Locked By");
			
			CopyHelper copy = new CopyHelper();
			copy.copySelectedCell("SearchGrid", row, "Locked By");
			String value = copy.getCopyContent();
			assertTrue(value.contains("(None)"), "Expected Default Value '(None)' is not found in Copy popup.");
		} catch (AssertionError e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		} finally {
			if (ButtonHelper.isPresent("Copy_Close")) {
				ButtonHelper.click("Copy_Close");
				GenericHelper.waitForLoadmask(searchScreenWaitSec);
			}
		}
	}
	
	@Test(priority=2, description="Test Case 2 for Bug 151878")
	public void testCase2() throws Exception {
		try {
			// Validate that Copy > Selected Column functionality working on Measure Page Null value configured columns in Configured Grid
			ConfigureGridHelper configureGrid = new ConfigureGridHelper();
			configureGrid.updateConfigureGrid(path, fileName, sheetName, "MeasureConfigureGrid", 1);
			
			ButtonHelper.click("SearchButton");
			GenericHelper.waitForLoadmask(searchScreenWaitSec);
			int row = GridHelper.getRowNumber("SearchGrid", "(None)", "Locked By");
			
			CopyHelper copy = new CopyHelper();
			copy.copySelectedColumn("SearchGrid", row, "Locked By");
			String value = copy.getCopyContent();
			assertTrue(value.contains("(None)"), "Expected Default Value '(None)' is not found in Copy popup.");
		} catch (AssertionError e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		} finally {
			if (ButtonHelper.isPresent("Copy_Close")) {
				ButtonHelper.click("Copy_Close");
				GenericHelper.waitForLoadmask(searchScreenWaitSec);
			}
		}
	}
	
	@Test(priority=3, description="Test Case 3 for Bug 151878")
	public void testCase3() throws Exception {
		try {
			// Validate that Copy > Selected Row functionality working on Measure Page Null value configured columns in Configured Grid
			ConfigureGridHelper configureGrid = new ConfigureGridHelper();
			configureGrid.updateConfigureGrid(path, fileName, sheetName, "MeasureConfigureGrid", 1);
			
			ButtonHelper.click("SearchButton");
			GenericHelper.waitForLoadmask(searchScreenWaitSec);
			int row = GridHelper.getRowNumber("SearchGrid", "(None)", "Locked By");
			
			CopyHelper copy = new CopyHelper();
			copy.copySelectedRow("SearchGrid", row, "Locked By");
			String value = copy.getCopyContent();
			assertTrue(value.contains("(None)"), "Expected Default Value '(None)' is not found in Copy popup.");
		} catch (AssertionError e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		} finally {
			if (ButtonHelper.isPresent("Copy_Close")) {
				ButtonHelper.click("Copy_Close");
				GenericHelper.waitForLoadmask(searchScreenWaitSec);
			}
		}
	}
	
	@Test(priority=4, description="Test Case 4 for Bug 151878")
	public void testCase4() throws Exception {
		try {
			// Validate that Copy > All Rows functionality working on Measure Page Null value configured columns in Configured Grid
			ConfigureGridHelper configureGrid = new ConfigureGridHelper();
			configureGrid.updateConfigureGrid(path, fileName, sheetName, "MeasureConfigureGrid", 1);
			
			ButtonHelper.click("SearchButton");
			GenericHelper.waitForLoadmask(searchScreenWaitSec);
			
			CopyHelper copy = new CopyHelper();
			copy.copyAllRows("SearchGrid");
			String value = copy.getCopyContent();
			assertTrue(value.contains("(None)"), "Expected Default Value '(None)' is not found in Copy popup.");
		} catch (AssertionError e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		} finally {
			if (ButtonHelper.isPresent("Copy_Close")) {
				ButtonHelper.click("Copy_Close");
				GenericHelper.waitForLoadmask(searchScreenWaitSec);
			}
		}
	}
	
	@Test(priority=5, description="Test Case 5 for Bug 151878")
	public void testCase5() throws Exception {
		try {
			// Validate that Export > Selected Rows functionality working on Measure Page Null value configured columns in Configured Grid
			ConfigureGridHelper configureGrid = new ConfigureGridHelper();
			configureGrid.updateConfigureGrid(path, fileName, sheetName, "MeasureConfigureGrid", 1);
			
			ButtonHelper.click("SearchButton");
			GenericHelper.waitForLoadmask(searchScreenWaitSec);
			
			int row = GridHelper.getRowNumber("SearchGrid", "(None)", "Locked By");
			if (row > 0) {
				GridHelper.clickRow("SearchGrid", row, "Locked By");
				ExportHelper export = new ExportHelper();
				String filename = export.exportSelectedRows("SearchGrid", row, "Locked By");
				
				if (filename != null) {
					String[] content = FileHelper.readFileContent(automationOS, filename);
					
					if (ValidationHelper.isNotEmpty(content)) {
						assertTrue(content[1].contains("(None)"), "Expected Default Value '(None)' is not found in Export file.");
					}
					else {
						FailureHelper.failTest("Export file '" + filename + "' is empty.");
					}
				}
				else {
					FailureHelper.failTest("Export did not happen.");
				}
			}
			else {
				FailureHelper.failTest("No row found with Null value.");
			}
		} catch (AssertionError e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	@Test(priority=6, description="Test Case 5 for Bug 151878")
	public void testCase6() throws Exception {
		try {
			// Validate that Copy > All Rows functionality working on Measure Page Null value configured columns in Configured Grid
			ConfigureGridHelper configureGrid = new ConfigureGridHelper();
			configureGrid.updateConfigureGrid(path, fileName, sheetName, "MeasureConfigureGrid", 1);
			
			ButtonHelper.click("SearchButton");
			GenericHelper.waitForLoadmask(searchScreenWaitSec);
			
			int row = GridHelper.getRowNumber("SearchGrid", "(None)", "Locked By");
			if (row > 0) {
				GridHelper.clickRow("SearchGrid", row, "Locked By");
				ExportHelper export = new ExportHelper();
				String filename = export.exportSelectedRows("SearchGrid", row, "Locked By");
				
				if (filename != null) {
					String[] content = FileHelper.readFileContent(automationOS, filename);
					
					if (ValidationHelper.isNotEmpty(content)) {
						assertTrue(content[1].contains("(None)"), "Expected Default Value '(None)' is not found in Export file.");
					}
					else {
						FailureHelper.failTest("Export file '" + filename + "' is empty.");
					}
				}
				else {
					FailureHelper.failTest("Export did not happen.");
				}
			}
			else {
				FailureHelper.failTest("No row found with Null value.");
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