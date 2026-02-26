package com.subex.automation.testcases.regressiontesting.measures_audits.audit_issues;

import java.util.ArrayList;
import java.util.HashMap;

import org.testng.annotations.Test;

import com.subex.automation.helpers.application.NavigationHelper;
import com.subex.automation.helpers.application.screens.AuditDefinitionHelper;
import com.subex.automation.helpers.application.screens.QueryMeasureHelper;
import com.subex.automation.helpers.component.ButtonHelper;
import com.subex.automation.helpers.component.ComboBoxHelper;
import com.subex.automation.helpers.component.GenericHelper;
import com.subex.automation.helpers.component.SearchGridHelper;
import com.subex.automation.helpers.component.TabHelper;
import com.subex.automation.helpers.component.TextBoxHelper;
import com.subex.automation.helpers.file.ExcelReader;
import com.subex.automation.helpers.util.FailureHelper;

public class test140033 extends testAuditIssues {

	final String sheetName = "test140033";

	public test140033() throws Exception {
		super();
	}

	@Test(priority=1, description="Test Case 1 for Bug 140033")
	public void testCase1() throws Exception {
		try {
			// Validate that User is able to create Audit.
			QueryMeasureHelper queryMeasure = new QueryMeasureHelper();
			queryMeasure.createQueryMeasure(path, fileName, sheetName, "QueryMeasure", 1);

			AuditDefinitionHelper auditDfn = new AuditDefinitionHelper();
			auditDfn.createAuditDefinition(path, fileName, sheetName, "Audits", 1);
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	@Test(priority=2, description="Test Case 2 for Bug 140033")
	public void testCase2() throws Exception {
		try {
			// Validate that modifications made in measure is working properly.
			ExcelReader excelReader = new ExcelReader();
			HashMap<String, ArrayList<String>> excelData = excelReader.readDataByColumn( path, fileName, sheetName, "Audits", 2 );
			String auditName = excelData.get("Name").get(0);
			String measureName = excelData.get("Measure Name").get(0);
			
			NavigationHelper.navigateToScreen("Audits", "Audit Search");
			int row = SearchGridHelper.gridFilterSearchWithTextBox("AuditDefinition_Name", auditName, "Name");
			
			if (row > 0) {
				NavigationHelper.navigateToEdit("SearchGrid", row, "AuditDefinition_Name");
	
				AuditDefinitionHelper auditDfn = new AuditDefinitionHelper();
				auditDfn.clickMeasure(measureName);
				NavigationHelper.navigateToAction("Action", "Edit");
				
				TabHelper.gotoTab("QM_Workflow_Panel", "Columns");
				ComboBoxHelper.select("QM_DatetimeColumn", "tap.tap_start_dttm*");
				ComboBoxHelper.select("QM_Frequency", "Daily");
				TextBoxHelper.type("QM_FrequencyDate", "06/20/2020 00:00:00");
				
				ButtonHelper.click("QM_Popup_Save");
				GenericHelper.waitForLoadmask(detailScreenWaitSec);
				
				ButtonHelper.click("SaveButton");
				GenericHelper.waitForLoadmask(detailScreenWaitSec);
				
				NavigationHelper.navigateToEdit("SearchGrid", row, "AuditDefinition_Name");
	
				auditDfn.clickMeasure(measureName);
				NavigationHelper.navigateToAction("Action", "Edit");
				
				TabHelper.gotoTab("QM_Workflow_Panel", "Columns");
				assertEquals(ComboBoxHelper.getValue("QM_DatetimeColumn"), "tap.tap_start_dttm*");
				assertEquals(ComboBoxHelper.getValue("QM_Frequency"), "Daily");
				assertEquals(TextBoxHelper.getValue("QM_FrequencyDate"), "06/20/2020 00:00:00");
				
				ButtonHelper.click("QM_Popup_Cancel");
				GenericHelper.waitForLoadmask(detailScreenWaitSec);
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