package com.subex.automation.testcases.regressiontesting.measures_audits.audit_issues;

import java.util.ArrayList;
import java.util.HashMap;

import org.testng.annotations.Test;

import com.subex.automation.helpers.application.LoginHelper;
import com.subex.automation.helpers.application.NavigationHelper;
import com.subex.automation.helpers.application.ROCHelper;
import com.subex.automation.helpers.application.screens.AuditDefinitionHelper;
import com.subex.automation.helpers.application.screens.AuditRequestHelper;
import com.subex.automation.helpers.application.screens.QueryMeasureHelper;
import com.subex.automation.helpers.application.screens.RolesHelper;
import com.subex.automation.helpers.application.screens.UserHelper;
import com.subex.automation.helpers.component.ButtonHelper;
import com.subex.automation.helpers.component.GenericHelper;
import com.subex.automation.helpers.component.LabelHelper;
import com.subex.automation.helpers.component.SearchGridHelper;
import com.subex.automation.helpers.file.ExcelReader;
import com.subex.automation.helpers.util.FailureHelper;

public class test162964 extends testAuditIssues {
	
	final String sheetName = "test162964";
	
	public test162964() throws Exception {
		super();
	}
	
	@Test(priority=1, description="Test Case 1 for Bug 162964")
	public void testCase1() throws Exception {
		try {
			// Validate that user is able to create User 'Analyst' without create new measure privileges .
			RolesHelper roles = new RolesHelper();
			roles.createRole(path, fileName, sheetName, "Roles", 1);
			
			UserHelper users = new UserHelper();
			users.createUser(path, fileName, sheetName, "Users", 1);
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	@Test(priority=2, description="Test Case 2 for Bug 162964")
	public void testCase2() throws Exception {
		LoginHelper login = new LoginHelper();
		
		try {
			// Validate that Analyst role is able to access audit_measures request if the role doesn't have create new measure privileges.
			QueryMeasureHelper queryMeasure = new QueryMeasureHelper();
			queryMeasure.createQueryMeasure(path, fileName, sheetName, "QueryMeasure", 1);
			
			AuditDefinitionHelper auditDfn = new AuditDefinitionHelper();
			auditDfn.createAuditDefinition(path, fileName, sheetName, "Audits", 1);
			
			AuditRequestHelper auditRequest = new AuditRequestHelper();
			auditRequest.createAuditRequest(path, fileName, sheetName, "AuditRequest", 1);
			
			ExcelReader excelReader = new ExcelReader();
			HashMap<String, ArrayList<String>> excelData = excelReader.readDataByColumn( path, fileName, sheetName, "Users", 1 );
			String username = excelData.get("Username").get(0);
			String password = excelData.get("Password").get(0);
			login.login(configProp.getApplicationName(), username, password, "welcome1");
			
			excelData = excelReader.readDataByColumn( path, fileName, sheetName, "Audits", 1 );
			String auditName = excelData.get("Name").get(0);
			String measureName = excelData.get("Measure Name").get(0);
			
			NavigationHelper.navigateToScreen("Audit Requests", "Audit Request Search");
			int row = SearchGridHelper.gridFilterAdvancedSearch("AuditRequest_Audit_Filter", auditName, "Audit");
			NavigationHelper.navigateToNewOrEdit(row, "Common", "Audit Request", "AuditRequest_Description");
			auditRequest.clickMeasure(measureName);
			
			NavigationHelper.navigateToAction("Open");
			GenericHelper.waitForLoadmask(detailScreenWaitSec);
			String actualTitle = NavigationHelper.getScreenTitle();
			assertTrue(LabelHelper.isTitlePresent("View Query Measure"),
					"Expected screen title 'View Query Measure'. But found '" + actualTitle + "'.");
			
			ButtonHelper.click("QM_Popup_Cancel");
			GenericHelper.waitForLoadmask(detailScreenWaitSec);
		} catch (AssertionError e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		} finally {
			ROCHelper rocHelper = new ROCHelper();
			rocHelper.handleFailures();
			
			login.loginWithConfigPropertyDetails();
		}
	}
	
	@Test(priority=3, description="Test Case 3 for Bug 162964")
	public void testCase3() throws Exception {
		try {
			// Validate that Admin  role is able to access audit_measures request if the role has create new measure privileges.
			QueryMeasureHelper queryMeasure = new QueryMeasureHelper();
			queryMeasure.createQueryMeasure(path, fileName, sheetName, "QueryMeasure", 1);
			
			AuditDefinitionHelper auditDfn = new AuditDefinitionHelper();
			auditDfn.createAuditDefinition(path, fileName, sheetName, "Audits", 1);
			
			AuditRequestHelper auditRequest = new AuditRequestHelper();
			auditRequest.createAuditRequest(path, fileName, sheetName, "AuditRequest", 1);
			
			ExcelReader excelReader = new ExcelReader();
			HashMap<String, ArrayList<String>> excelData = excelReader.readDataByColumn( path, fileName, sheetName, "Audits", 1 );
			String auditName = excelData.get("Name").get(0);
			String measureName = excelData.get("Measure Name").get(0);
			
			NavigationHelper.navigateToScreen("Audit Requests", "Audit Request Search");
			int row = SearchGridHelper.gridFilterAdvancedSearch("AuditRequest_Audit_Filter", auditName, "Audit");
			NavigationHelper.navigateToNewOrEdit(row, "Common", "Audit Request", "AuditRequest_Description");
			auditRequest.clickMeasure(measureName);
			
			NavigationHelper.navigateToAction("Open");
			GenericHelper.waitForLoadmask(detailScreenWaitSec);
			assertTrue(LabelHelper.isTitlePresent("Edit Query Measure"));
			
			ButtonHelper.click("QM_Popup_Cancel");
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