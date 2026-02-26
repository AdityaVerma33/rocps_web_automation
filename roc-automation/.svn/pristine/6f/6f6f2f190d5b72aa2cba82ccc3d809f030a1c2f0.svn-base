package com.subex.automation.testcases.regressiontesting.measures_audits.audit_issues;

import java.util.ArrayList;
import java.util.HashMap;

import org.testng.annotations.Test;

import com.subex.automation.helpers.application.NavigationHelper;
import com.subex.automation.helpers.application.screens.AuditDefinitionHelper;
import com.subex.automation.helpers.application.screens.DataMatchMeasureHelper;
import com.subex.automation.helpers.application.screens.QueryMeasureHelper;
import com.subex.automation.helpers.component.ButtonHelper;
import com.subex.automation.helpers.component.GenericHelper;
import com.subex.automation.helpers.component.LabelHelper;
import com.subex.automation.helpers.component.SearchGridHelper;
import com.subex.automation.helpers.file.ExcelReader;
import com.subex.automation.helpers.util.FailureHelper;

public class test163037 extends testAuditIssues {
	
	final String sheetName = "test163037";
	
	public test163037() throws Exception {
		super();
	}
	
	@Test(priority=1, description="Test Case 1 for Bug 163040")
	public void testCase1() throws Exception {
		try {
			// Validate that User is able to create Measure with group by fields and an aggregated fields.
			QueryMeasureHelper queryMeasure = new QueryMeasureHelper();
			queryMeasure.createQueryMeasure(path, fileName, sheetName, "QueryMeasure", 1);
			
			DataMatchMeasureHelper dataMatchMeasure = new DataMatchMeasureHelper();
			dataMatchMeasure.createDataMatchMeasure(path, fileName, sheetName, "DataMatchMeasure", 1);
			
			AuditDefinitionHelper auditDfn = new AuditDefinitionHelper();
			auditDfn.createAuditDefinition(path, fileName, sheetName, "Audits", 1);
			
			ExcelReader excelReader = new ExcelReader();
			HashMap<String, ArrayList<String>> excelData = excelReader.readDataByColumn( path, fileName, sheetName, "Audits", 1 );
			String auditName = excelData.get("Name").get(0);
			
			NavigationHelper.navigateToScreen("Audits", "Audit Search");
			int row = SearchGridHelper.gridFilterSearchWithTextBox("AuditDefinition_Name", auditName, "Name");
			NavigationHelper.navigateToNewOrEdit(row, "Common", "Audit", "AuditDefinition_Name");
			ButtonHelper.isDisabled("AuditDefinition_Action");
			
			String[] measureName = {"1", "2"};
			for (int i = 0; i < measureName.length; i++) {
				auditDfn.clickMeasure(measureName[i]);
				ButtonHelper.isEnabled("AuditDefinition_Action");
				NavigationHelper.navigateToAction("Action");
				ButtonHelper.isEnabled("AuditDefinition_Remove");
			}
			
			ButtonHelper.click("CancelButton");
			GenericHelper.waitForLoadmask(detailScreenWaitSec);
			assertTrue(LabelHelper.isTitleNotPresent("Edit Audit"));
		} catch (AssertionError e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
}