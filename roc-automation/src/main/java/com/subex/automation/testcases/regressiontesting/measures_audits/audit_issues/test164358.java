package com.subex.automation.testcases.regressiontesting.measures_audits.audit_issues;

import java.util.ArrayList;
import java.util.HashMap;

import org.testng.annotations.Test;

import com.subex.automation.helpers.application.NavigationHelper;
import com.subex.automation.helpers.application.screens.AuditDefinitionHelper;
import com.subex.automation.helpers.application.screens.QueryMeasureHelper;
import com.subex.automation.helpers.component.ButtonHelper;
import com.subex.automation.helpers.component.GenericHelper;
import com.subex.automation.helpers.component.GridHelper;
import com.subex.automation.helpers.component.LabelHelper;
import com.subex.automation.helpers.component.SearchGridHelper;
import com.subex.automation.helpers.component.TextBoxHelper;
import com.subex.automation.helpers.file.ExcelReader;
import com.subex.automation.helpers.util.FailureHelper;

public class test164358 extends testAuditIssues {

	final String sheetName = "test164358";

	public test164358() throws Exception {
		super();
	}

	@Test(priority=1, description="Test Case 1 for Bug 164358")
	public void testCase1() throws Exception
	{
		try {
			// Validate that User is able to create Audit.
			QueryMeasureHelper queryMeasure = new QueryMeasureHelper();
			queryMeasure.createQueryMeasure(path, fileName, sheetName, "QueryMeasure", 1);
			
			AuditDefinitionHelper audits = new AuditDefinitionHelper();
			audits.createAuditDefinition(path, fileName, sheetName, "Audits", 1);
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}

	@Test(priority=2, description="Test Case 2 for Bug 164358")
	public void testCase2() throws Exception {
		try {
			// Validate that upon making changes when user clicks on cancel Tab> check for popup Cancel Action
			ExcelReader excelReader = new ExcelReader();
			HashMap<String, ArrayList<String>> excelData = excelReader.readDataByColumn( path, fileName, sheetName, "Audits", 1 );
			String auditName = excelData.get("Name").get(0);
			String measureName = excelData.get("Measure Name").get(0);
			String description = excelData.get("Description").get(0);

			NavigationHelper.navigateToScreen("Audits", "Audit Search");
			int row = SearchGridHelper.gridFilterSearchWithTextBox("AuditDefinition_Name", auditName, "Name");
			
			if (row > 0) {
				NavigationHelper.navigateToEdit("SearchGrid", row, "AuditDefinition_Name");
				assertTrue(LabelHelper.isTitlePresent("Edit Audit"));
				
				AuditDefinitionHelper auditDfn = new AuditDefinitionHelper();
				auditDfn.clickMeasure(measureName);
				TextBoxHelper.type("AuditDefinition_Description", description + "_updated");			
				ButtonHelper.click("CancelButton");
				GenericHelper.waitForLoadmask(detailScreenWaitSec);
				assertTrue(ButtonHelper.isPresent("DiscardButton"));
				
				ButtonHelper.click("DiscardButton");
				GenericHelper.waitForLoadmask(detailScreenWaitSec);
				assertTrue(LabelHelper.isTitlePresent("Audit Search"));
				
				ButtonHelper.click("SearchButton");
				GenericHelper.waitForLoadmask(searchScreenWaitSec);
				assertTrue(GridHelper.isValuePresent("SearchGrid", description, "Description"));
			}
			else {
				FailureHelper.failTest("Audit '" + auditName + "' is not found.");
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