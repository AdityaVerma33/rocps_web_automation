package com.subex.automation.testcases.regressiontesting.measures_audits.audit_issues;

import java.util.ArrayList;
import java.util.HashMap;

import org.testng.annotations.Test;

import com.subex.automation.helpers.application.NavigationHelper;
import com.subex.automation.helpers.application.ROCHelper;
import com.subex.automation.helpers.application.screens.AuditDefinitionHelper;
import com.subex.automation.helpers.application.screens.ComparisonMeasureHelper;
import com.subex.automation.helpers.application.screens.QueryMeasureHelper;
import com.subex.automation.helpers.component.ButtonHelper;
import com.subex.automation.helpers.component.GenericHelper;
import com.subex.automation.helpers.component.GridHelper;
import com.subex.automation.helpers.component.TabHelper;
import com.subex.automation.helpers.file.ExcelReader;
import com.subex.automation.helpers.util.FailureHelper;

public class test139644 extends testAuditIssues {
	
	final String sheetName = "test139644";
	
	public test139644() throws Exception {
		super();
	}
	
	@Test(priority=1, description="Test Case 1 for Bug 139644")
	public void testCase1() throws Exception {
		try {
			// Validate that user is able to delete any selected columns in summary information Tab(Comparison measure) from Audit screen.
			QueryMeasureHelper queryMeasure = new QueryMeasureHelper();
			queryMeasure.createQueryMeasure(path, fileName, sheetName, "QueryMeasure", 1);
			
			ComparisonMeasureHelper comparisonMeasure = new ComparisonMeasureHelper();
			comparisonMeasure.createComparisonMeasure(path, fileName, sheetName, "ComparisonMeasure", 1);
			
			AuditDefinitionHelper audit = new AuditDefinitionHelper();
			audit.createAuditDefinition(path, fileName, sheetName, "Audits", 1);
			
			ExcelReader excelReader = new ExcelReader();
			HashMap<String, ArrayList<String>> excelData = excelReader.readDataByColumn( path, fileName, sheetName, "Audits", 1 );
			
			String auditName = excelData.get("Name").get(0);
			String measureName = excelData.get("Measure Name").get(0);
			
			int row = GridHelper.getRowNumber("SearchGrid", auditName, "Name");
			NavigationHelper.navigateToEdit("SearchGrid", row, "AuditDefinition_Name");
			
			audit.clickMeasure(measureName);
			NavigationHelper.navigateToAction("Action", "Edit");
			GenericHelper.waitForLoadmask(detailScreenWaitSec);
			
			TabHelper.gotoTab("Summary Information");
			String gridId = "CM_SummaryInfo_Grid";
			int rows = GridHelper.getRowCount(gridId);
			assertEquals(rows, 2);
			ButtonHelper.click("CM_SummaryInfo_Add");
			rows = GridHelper.getRowCount(gridId);
			assertEquals(rows, 3);
			
			GridHelper.clickRow(gridId, 3, "Aggregate");
			assertTrue(ButtonHelper.isPresent("CM_SummaryInfo_Delete"));
			assertTrue(ButtonHelper.isEnabled("CM_SummaryInfo_Delete"));
			ButtonHelper.click("CM_SummaryInfo_Delete");
			rows = GridHelper.getRowCount(gridId);
			assertEquals(rows, 2);
			
			GridHelper.clickRow(gridId, 2, "Aggregate");
			assertTrue(ButtonHelper.isPresent("CM_SummaryInfo_Delete"));
			assertTrue(ButtonHelper.isEnabled("CM_SummaryInfo_Delete"));
			ButtonHelper.click("CM_SummaryInfo_Delete");
			rows = GridHelper.getRowCount(gridId);
			assertEquals(rows, 1);
		} catch (AssertionError e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		} finally {
			if (ButtonHelper.isPresent("AuditDefinition_CM_Cancel")) {
				ButtonHelper.click("AuditDefinition_CM_Cancel");
				GenericHelper.waitForLoadmask(detailScreenWaitSec);
				
				if (ButtonHelper.isPresent("DiscardButton")) {
					ButtonHelper.click("DiscardButton");
					GenericHelper.waitForLoadmask(detailScreenWaitSec);
				}
			}
			
			ROCHelper rocHelper = new ROCHelper();
			rocHelper.handleFailures();
		}
	}
}