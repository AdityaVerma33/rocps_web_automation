package com.subex.automation.testcases.regressiontesting.measures_audits.audit_issues;

import java.util.ArrayList;
import java.util.HashMap;

import org.testng.annotations.Test;

import com.subex.automation.helpers.application.LoginHelper;
import com.subex.automation.helpers.application.NavigationHelper;
import com.subex.automation.helpers.application.screens.AuditDefinitionHelper;
import com.subex.automation.helpers.application.screens.KPIDefinitionHelper;
import com.subex.automation.helpers.application.screens.QueryMeasureHelper;
import com.subex.automation.helpers.application.screens.RolesHelper;
import com.subex.automation.helpers.application.screens.UserHelper;
import com.subex.automation.helpers.component.ButtonHelper;
import com.subex.automation.helpers.component.CheckBoxHelper;
import com.subex.automation.helpers.component.GenericHelper;
import com.subex.automation.helpers.component.LabelHelper;
import com.subex.automation.helpers.component.SearchGridHelper;
import com.subex.automation.helpers.file.ExcelReader;
import com.subex.automation.helpers.util.FailureHelper;

public class test163000 extends testAuditIssues {
	
	final String sheetName = "test163000";
	
	public test163000() throws Exception {
		super();
	}
	
	@Test(priority=1, description="Test Case 1 for Bug 163000")
	public void testCase1() throws Exception {
		try {
			// Validate that Edit KPI Definition is coming as “KPI Definition”, when privileged user try to edit the KPI from Audit screen.
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
			
			NavigationHelper.navigateToScreen("Audits", "Audit Search");
			int row = SearchGridHelper.gridFilterSearchWithTextBox("AuditDefinition_Name", auditName, "Name");
			NavigationHelper.navigateToNewOrEdit(row, "Common", "Audit", "AuditDefinition_Name");
			auditDfn.clickMeasure(measureName);
			
			excelData = excelReader.readDataByColumn( path, fileName, sheetName, "KPIDefinition", 1 );
			String kpiName = excelData.get("Name").get(0);
			NavigationHelper.navigateToAction("KPI", kpiName);
			GenericHelper.waitForLoadmask(detailScreenWaitSec);
			assertTrue(LabelHelper.isTitlePresent("Edit KPI Definition"));
			
			CheckBoxHelper.check("KPIDefinition_GenerateNonviolatedRows");
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
			assertTrue(CheckBoxHelper.isChecked("KPIDefinition_GenerateNonviolatedRows"));
			
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
	
	@Test(priority=2, description="Test Case 2 for Bug 163000")
	public void testCase2() throws Exception {
		LoginHelper login = new LoginHelper();
		
		try {
			// Validate that Edit KPI Definition is coming as “KPI Definition”, when privileged user try to edit the KPI from Audit screen.
			RolesHelper roles = new RolesHelper();
			roles.createRole(path, fileName, sheetName, "Roles", 1);
			
			UserHelper users = new UserHelper();
			users.createUser(path, fileName, sheetName, "Users", 1);
			
			QueryMeasureHelper queryMeasure = new QueryMeasureHelper();
			queryMeasure.createQueryMeasure(path, fileName, sheetName, "QueryMeasure", 1);
			
			KPIDefinitionHelper kpiDefinition = new KPIDefinitionHelper();
			kpiDefinition.createKPIDefinition(path, fileName, sheetName, "KPIDefinition", 1);
			
			AuditDefinitionHelper auditDfn = new AuditDefinitionHelper();
			auditDfn.createAuditDefinition(path, fileName, sheetName, "Audits", 1);
			
			ExcelReader excelReader = new ExcelReader();
			HashMap<String, ArrayList<String>> excelData = excelReader.readDataByColumn( path, fileName, sheetName, "Users", 1 );
			String username = excelData.get("Username").get(0);
			String password = excelData.get("Password").get(0);
			login.login(configProp.getApplicationName(), username, password, "welcome1");
			
			excelData = excelReader.readDataByColumn( path, fileName, sheetName, "Audits", 1 );
			String auditName = excelData.get("Name").get(0);
			String measureName = excelData.get("Measure Name").get(0);
			
			NavigationHelper.navigateToScreen("Audits", "Audit Search");
			int row = SearchGridHelper.gridFilterSearchWithTextBox("AuditDefinition_Name", auditName, "Name");
			NavigationHelper.navigateToNewOrEdit(row, "Common", "Audit", "AuditDefinition_Name");
			auditDfn.clickMeasure(measureName);
			
			excelData = excelReader.readDataByColumn( path, fileName, sheetName, "KPIDefinition", 1 );
			String kpiName = excelData.get("Name").get(0);
			NavigationHelper.navigateToAction("KPI", kpiName);
			GenericHelper.waitForLoadmask(detailScreenWaitSec);
			assertTrue(LabelHelper.isTitlePresent("View KPI Definition"));
			
			CheckBoxHelper.uncheck("KPIDefinition_GenerateNonviolatedRows");
			assertTrue(CheckBoxHelper.isChecked("KPIDefinition_GenerateNonviolatedRows"));
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
}