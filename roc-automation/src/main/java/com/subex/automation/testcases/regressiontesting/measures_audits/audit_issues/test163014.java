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
import com.subex.automation.helpers.component.GenericHelper;
import com.subex.automation.helpers.component.LabelHelper;
import com.subex.automation.helpers.component.SearchGridHelper;
import com.subex.automation.helpers.file.ExcelReader;
import com.subex.automation.helpers.util.FailureHelper;

public class test163014 extends testAuditIssues {

	final String sheetName = "test163014";

	public test163014() throws Exception {
		super();
	}

	@Test(priority=1, description="Test case 1 of 163014")
	public void testCase1()throws Exception
	{
		try {
			// Validate that User is able to create KPI Definition
			QueryMeasureHelper queryMeasure = new QueryMeasureHelper();
			queryMeasure.createQueryMeasure(path, fileName, sheetName, "QueryMeasure", 1);

			KPIDefinitionHelper kpiDefinition = new KPIDefinitionHelper();
			kpiDefinition.createKPIDefinition(path, fileName, sheetName, "KPIDefinition", 1);

			AuditDefinitionHelper auditDfn = new AuditDefinitionHelper();
			auditDfn.createAuditDefinition(path, fileName, sheetName, "Audits", 1);
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}

	@Test(priority=2, description="Test case 2 of 163014")
	public void testCase2() throws Exception
	{
		try {
			// Validate that user is able to create a user without giving edit Privilege for edit KPI.
			RolesHelper roles = new RolesHelper();
			roles.createRole(path, fileName, sheetName, "Roles", 1);
			
			UserHelper users = new UserHelper();
			users.createUser(path, fileName, sheetName, "Users", 1);
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	@Test(priority=3, description="Test Case 3 for Bug 163014")
	public void testCase3() throws Exception {
		LoginHelper login = new LoginHelper();
		
		try {
			// Validate that able to login with created user.
			ExcelReader excelReader = new ExcelReader();
			HashMap<String, ArrayList<String>> excelData = excelReader.readDataByColumn( path, fileName, sheetName, "Users", 1 );
			String username = excelData.get("Username").get(0);
			String password = excelData.get("Password").get(0);
			login.login(configProp.getApplicationName(), username, password, "welcome1");
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	@Test(priority=4, description="Test Case 4 for Bug 163014", dependsOnMethods = { "testCase1" })
	public void testCase4() throws Exception {
		try {
			ExcelReader excelReader = new ExcelReader();
			HashMap<String, ArrayList<String>> excelData = excelReader.readDataByColumn( path, fileName, sheetName, "Audits", 1 );
			String auditName = excelData.get("Name").get(0);
			String measureName = excelData.get("Measure Name").get(0);
			excelData = excelReader.readDataByColumn( path, fileName, sheetName, "KPIDefinition", 1 );
			String kpiName = excelData.get("Name").get(0);

			NavigationHelper.navigateToScreen("Audits", "Audit Search");
			int row = SearchGridHelper.gridFilterSearchWithTextBox("AuditDefinition_Name", auditName, "Name");
			
			if (row > 0) {
				NavigationHelper.navigateToEdit("SearchGrid", row, "AuditDefinition_Name");
				assertTrue(LabelHelper.isTitlePresent("Edit Audit"));
	
				AuditDefinitionHelper auditDfn = new AuditDefinitionHelper();
				auditDfn.clickMeasure(measureName);
	
				NavigationHelper.navigateToAction("KPI", "New KPI");
				GenericHelper.waitForLoadmask(detailScreenWaitSec);
				assertTrue(LabelHelper.isTitlePresent("Warning"));
				ButtonHelper.click("OKButton");
				GenericHelper.waitForLoadmask(detailScreenWaitSec);
				
				NavigationHelper.navigateToAction("KPI", kpiName);
				GenericHelper.waitForLoadmask(detailScreenWaitSec);
				assertTrue(LabelHelper.isTitlePresent("Warning"));
				ButtonHelper.click("OKButton");
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