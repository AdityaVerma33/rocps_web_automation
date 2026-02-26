package com.subex.automation.testcases.regressiontesting.measures_audits.audit_issues;

import java.util.ArrayList;
import java.util.HashMap;

import org.testng.annotations.Test;

import com.subex.automation.helpers.application.MeasureHelper;
import com.subex.automation.helpers.application.NavigationHelper;
import com.subex.automation.helpers.application.screens.AuditDefinitionHelper;
import com.subex.automation.helpers.application.screens.KPIDefinitionHelper;
import com.subex.automation.helpers.application.screens.QueryMeasureHelper;
import com.subex.automation.helpers.application.screens.TeamHelper;
import com.subex.automation.helpers.application.screens.UserHelper;
import com.subex.automation.helpers.component.ButtonHelper;
import com.subex.automation.helpers.component.GenericHelper;
import com.subex.automation.helpers.component.LabelHelper;
import com.subex.automation.helpers.component.SearchGridHelper;
import com.subex.automation.helpers.component.TextBoxHelper;
import com.subex.automation.helpers.file.ExcelReader;
import com.subex.automation.helpers.scripts.TestDataHelper;
import com.subex.automation.helpers.util.FailureHelper;

public class test163040 extends testAuditIssues {
	
	final String sheetName = "test163040";
	
	public test163040() throws Exception {
		super();
	}
	
	@Test(priority=1, description="Test Case 1 for Bug 163040")
	public void testCase1() throws Exception {
		try {
			// Validate that User is able to create Measure with group by fields and an aggregated fields.
			UserHelper users = new UserHelper();
			users.createUser(path, fileName, sheetName, "Users", 1);
			
			TeamHelper teams = new TeamHelper();
			teams.createTeam(path, fileName, sheetName, "Teams", 1);
			
			createCaseTemplate(sheetName);
			
			QueryMeasureHelper queryMeasure = new QueryMeasureHelper();
			queryMeasure.createQueryMeasure(path, fileName, sheetName, "QueryMeasure", 1);
			
			KPIDefinitionHelper kpiDefinition = new KPIDefinitionHelper();
			kpiDefinition.createKPIDefinition(path, fileName, sheetName, "KPIDefinition", 1);
			
			AuditDefinitionHelper auditDfn = new AuditDefinitionHelper();
			auditDfn.createAuditDefinition(path, fileName, sheetName, "Audits", 1);
			
			ExcelReader excelReader = new ExcelReader();
			HashMap<String, ArrayList<String>> excelData = excelReader.readDataByColumn( path, fileName, sheetName, "Audits", 1 );
			String auditName = excelData.get("Name").get(0);
			String measureName = excelData.get("Measure Name").get(0);
			excelData = excelReader.readDataByColumn( path, fileName, sheetName, "KPIDefinition", 1 );
			String kpiName = excelData.get("Name").get(0);
			
			NavigationHelper.navigateToScreen("Audits", "Audit Search");
			int row = SearchGridHelper.gridFilterSearchWithTextBox("AuditDefinition_Name", auditName, "Name");
			NavigationHelper.navigateToNewOrEdit(row, "Common", "Audit", "AuditDefinition_Name");
			
			auditDfn.clickMeasure(measureName);
			NavigationHelper.navigateToAction("KPI", kpiName);
			GenericHelper.waitForLoadmask(detailScreenWaitSec);
			assertTrue(LabelHelper.isTitlePresent("Edit KPI Definition"));
			
			TextBoxHelper.clear("KPIDefinition_CaseTemplate");
			ButtonHelper.click("KPIDefinition_Save");
			GenericHelper.waitForLoadmask(detailScreenWaitSec);
			
			ButtonHelper.click("SaveButton");
			GenericHelper.waitForLoadmask(detailScreenWaitSec);
			assertTrue(LabelHelper.isTitleNotPresent("Edit Audit"));
			
			NavigationHelper.navigateToNewOrEdit(row, "Common", "Audit", "AuditDefinition_Name");
			auditDfn.clickMeasure(measureName);
			NavigationHelper.navigateToAction("KPI", kpiName);
			GenericHelper.waitForLoadmask(detailScreenWaitSec);
			assertTrue(LabelHelper.isTitlePresent("Edit KPI Definition"));
			assertEquals(TextBoxHelper.getValue("KPIDefinition_CaseTemplate"), "");
			
			ButtonHelper.click("KPIDefinition_Cancel");
			GenericHelper.waitForLoadmask(detailScreenWaitSec);
		} catch (AssertionError e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	@Test(priority=2, description="Test Case 2 for Bug 163040", dependsOnMethods = { "testCase1" })
	public void testCase2() throws Exception {
		try {
			// Validate that User is able to create Measure with group by fields and an aggregated fields.
			TestDataHelper testData = new TestDataHelper();
			ExcelReader excelReader = new ExcelReader();
			HashMap<String, ArrayList<String>> excelData = excelReader.readDataByColumn( path, fileName, sheetName, "Audits", 1 );
			String auditName = excelData.get("Name").get(0);
			String measureName = excelData.get("Measure Name").get(0);
			
			excelData = excelReader.readDataByColumn( path, fileName, sheetName, "KPIDefinition", 1 );
			String kpiName = excelData.get("Name").get(0);
			String caseTemplateName = excelData.get("Case Template Name").get(0);
			String[][] casePropertyMapping = testData.getStringValue(excelData.get("Case Property Mapping").get(0), firstLevelDelimiter, secondLevelDelimiter);
			
			NavigationHelper.navigateToScreen("Audits", "Audit Search");
			int row = SearchGridHelper.gridFilterSearchWithTextBox("AuditDefinition_Name", auditName, "Name");
			if (row > 0) {
				NavigationHelper.navigateToEdit("SearchGrid", row, "AuditDefinition_Name");
				
				AuditDefinitionHelper auditDfn = new AuditDefinitionHelper();
				auditDfn.clickMeasure(measureName);
				NavigationHelper.navigateToAction("KPI", kpiName);
				GenericHelper.waitForLoadmask(detailScreenWaitSec);
				assertTrue(LabelHelper.isTitlePresent("Edit KPI Definition"));
				
				MeasureHelper measure = new MeasureHelper();
				measure.linkCaseTemplate("KPIDefinition_CaseTemplate", caseTemplateName, casePropertyMapping);
				
				ButtonHelper.click("KPIDefinition_Save");
				GenericHelper.waitForLoadmask(detailScreenWaitSec);
				
				ButtonHelper.click("SaveButton");
				GenericHelper.waitForLoadmask(detailScreenWaitSec);
				assertTrue(LabelHelper.isTitleNotPresent("Edit Audit"));
				
				NavigationHelper.navigateToNewOrEdit(row, "Common", "Audit", "AuditDefinition_Name");
				auditDfn.clickMeasure(measureName);
				NavigationHelper.navigateToAction("KPI", kpiName);
				GenericHelper.waitForLoadmask(detailScreenWaitSec);
				assertTrue(LabelHelper.isTitlePresent("Edit KPI Definition"));
				assertEquals(TextBoxHelper.getValue("KPIDefinition_CaseTemplate"), caseTemplateName);
				
				ButtonHelper.click("KPIDefinition_Cancel");
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