package com.subex.automation.testcases.regressiontesting.measures_audits.audit_issues;

import java.util.ArrayList;
import java.util.HashMap;

import org.testng.annotations.Test;

import com.subex.automation.helpers.application.NavigationHelper;
import com.subex.automation.helpers.application.screens.AuditDefinitionHelper;
import com.subex.automation.helpers.application.screens.EntityImportHelper;
import com.subex.automation.helpers.component.SearchGridHelper;
import com.subex.automation.helpers.file.ExcelReader;
import com.subex.automation.helpers.report.Log4jHelper;
import com.subex.automation.helpers.util.FailureHelper;

public class test149503 extends testAuditIssues {

	final String sheetName = "test149503";

	public test149503() throws Exception {
		super();
	}

	@Test(priority=1, description="Test Case 1 for Bug 149503")
	public void testCase1() throws Exception {
		try {
			// Validate that user is able to create a Audit with 52 measures.
			EntityImportHelper entityImport = new EntityImportHelper();
			entityImport.createEntityImport(path, fileName, sheetName, "EntityImport", 1);
			
			AuditDefinitionHelper auditDfn = new AuditDefinitionHelper();
			auditDfn.createAuditDefinition(path, fileName, sheetName, "Audits", 1);
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}

	@Test(priority=2, description="Test Case 2 for Bug 149503", dependsOnMethods={"testCase1"})
	public void testCase2() throws Exception {
		try {
			// Validate that user is able to edit a Audit with 52 measures.
			ExcelReader excelReader = new ExcelReader();
			HashMap<String, ArrayList<String>> excelData = excelReader.readDataByColumn( path, fileName, sheetName, "UpdateAudit", 1 );
			String auditName = excelData.get("Name").get(0);
			
			NavigationHelper.navigateToScreen("Audits", "Audit Search");
			int row = SearchGridHelper.gridFilterSearchWithTextBox("AuditDefinition_Name", auditName, "Name");

			if (row > 0 ) {
				String detailScreenTitle = NavigationHelper.navigateToEdit("SearchGrid", row, "AuditDefinition_Name");
				
				AuditDefinitionHelper auditDfn = new AuditDefinitionHelper();
				auditDfn.updateAuditDefinition(path, fileName, sheetName, "UpdateAudit", 1);
				
				auditDfn.saveAuditDefinition(auditName, detailScreenTitle);
				Log4jHelper.logWarning("Audit '" + auditName + "' is updated." );
			}
			else {
				FailureHelper.failTest("Audit '" + auditName + "' is not found.");
			}
			
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
}