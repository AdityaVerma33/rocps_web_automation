package com.subex.automation.testcases.regressiontesting.measures_audits.audit_issues;

import java.util.ArrayList;
import java.util.HashMap;

import org.testng.annotations.Test;

import com.subex.automation.helpers.application.NavigationHelper;
import com.subex.automation.helpers.application.screens.AuditDefinitionHelper;
import com.subex.automation.helpers.application.screens.QueryMeasureHelper;
import com.subex.automation.helpers.component.ButtonHelper;
import com.subex.automation.helpers.component.EntitySearchHelper;
import com.subex.automation.helpers.component.GenericHelper;
import com.subex.automation.helpers.component.LabelHelper;
import com.subex.automation.helpers.component.MouseHelper;
import com.subex.automation.helpers.component.SearchGridHelper;
import com.subex.automation.helpers.file.ExcelReader;
import com.subex.automation.helpers.scripts.TestDataHelper;
import com.subex.automation.helpers.util.FailureHelper;

public class test152213 extends testAuditIssues {

	final String sheetName = "test152213";

	public test152213() throws Exception {
		super();
	}

	@Test(priority=1, description="Test Case 1 for Bug 152213")
	public void testCase1()throws Exception
	{
		try {
			// Validate that user is able to create Audit.
			QueryMeasureHelper queryMeasure = new QueryMeasureHelper();
			queryMeasure.createQueryMeasure(path, fileName, sheetName, "QueryMeasure", 1);
			
			AuditDefinitionHelper audits = new AuditDefinitionHelper();
			audits.createAuditDefinition(path, fileName, sheetName, "Audits", 1);
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}

	@Test(priority=2, description="Test Case 2 for Bug 152213")
	public void testCase2() throws Exception {
		try {
//			Validate that user is able to remove a measure and then add the same measure back to that audit then save.
			TestDataHelper testData = new TestDataHelper();
			ExcelReader excelReader = new ExcelReader();
			HashMap<String, ArrayList<String>> excelData = excelReader.readDataByColumn( path, fileName, sheetName, "Audits", 1 );
			String auditName = excelData.get("Name").get(0);
			String[] measureName = testData.getStringValue(excelData.get("Measure Name").get(0), firstLevelDelimiter);

			NavigationHelper.navigateToScreen("Audits", "Audit Search");
			int row = SearchGridHelper.gridFilterSearchWithTextBox("AuditDefinition_Name", auditName, "Name");
			
			if (row > 0) {
				String detailScreenTitle = NavigationHelper.navigateToEdit("SearchGrid", row, "AuditDefinition_Name");
				assertTrue(LabelHelper.isTitlePresent("Edit Audit"));
				
				AuditDefinitionHelper auditDfn = new AuditDefinitionHelper();
				auditDfn.clickMeasure(measureName[0]);
				NavigationHelper.navigateToAction("Action", "Remove");
				GenericHelper.waitForLoadmask(detailScreenWaitSec);
				assertTrue(LabelHelper.isTitlePresent("Confirm"));
				ButtonHelper.click("YesButton");
				GenericHelper.waitForLoadmask(detailScreenWaitSec);
	
				EntitySearchHelper entitySearch = new EntitySearchHelper();
				MouseHelper.click("AuditDefinition_Add");
				GenericHelper.waitForLoadmask(detailScreenWaitSec);
				entitySearch.selectUsingGridFilterTextBox("Measure Search", "Measure_Name", measureName[0], "Name");
				GenericHelper.waitForLoadmask(detailScreenWaitSec);
				auditDfn.clickMeasure(measureName[0]);
				
				auditDfn.saveAuditDefinition(auditName, detailScreenTitle);
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