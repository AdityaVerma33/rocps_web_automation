package com.subex.automation.testcases.regressiontesting.measures_audits.audit_issues;

import java.util.ArrayList;
import java.util.HashMap;

import org.testng.annotations.Test;

import com.subex.automation.helpers.application.ControllerHelper;
import com.subex.automation.helpers.application.NavigationHelper;
import com.subex.automation.helpers.application.ROCHelper;
import com.subex.automation.helpers.application.screens.AuditDefinitionHelper;
import com.subex.automation.helpers.application.screens.AuditRequestHelper;
import com.subex.automation.helpers.application.screens.JumpToSearchHelper;
import com.subex.automation.helpers.application.screens.QueryMeasureHelper;
import com.subex.automation.helpers.component.ButtonHelper;
import com.subex.automation.helpers.component.GenericHelper;
import com.subex.automation.helpers.component.GridHelper;
import com.subex.automation.helpers.file.ExcelReader;
import com.subex.automation.helpers.util.FailureHelper;

public class test150062 extends testAuditIssues {
	
	final String sheetName = "test150062";
	
	public test150062() throws Exception {
		super();
	}
	
	@Test(priority=1, description="Test Case 1 for Bug 150062")
	public void testCase1() throws Exception {
		try {
			// Validate that user is able to create a QM having previous measure as input and join with reference table.
			QueryMeasureHelper queryMeasure = new QueryMeasureHelper();
			queryMeasure.createQueryMeasure(path, fileName, sheetName, "QueryMeasure", 1);
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	@Test(priority=2, description="Test Case 2 for Bug 150062", dependsOnMethods={"testCase1"})
	public void testCase2() throws Exception {
		ControllerHelper controller = new ControllerHelper();
		
		try {
			// Validate that upon drill down to results , check for reference table option.
			AuditDefinitionHelper auditDfn = new AuditDefinitionHelper();
			auditDfn.createAuditDefinition(path, fileName, sheetName, "Audits", 1);
			
			AuditRequestHelper auditRequest = new AuditRequestHelper();
			auditRequest.createAuditRequest(path, fileName, sheetName, "AuditRequest", 1);
			
			controller.startStreamController();
			controller.startTaskController(configProp.getTaskControllerExeFile());
			controller.startServerService();
			
			auditRequest.scheduleAuditRequest(path, fileName, sheetName, "AuditRequest", 1);
			Thread.sleep(3000);
			
			auditRequest.verifyAuditRequest(path, fileName, sheetName, "VerifyAuditRequest", 1);
			auditRequest.verifyResult(path, fileName, sheetName, "VerifyQMResult", 1);
			
			auditRequest.verifyResultDrillDown(path, fileName, sheetName, "VerifyQMDrillDown", 1);
			
			ExcelReader excelReader = new ExcelReader();
			HashMap<String, ArrayList<String>> excelData = excelReader.readDataByColumn( path, fileName, sheetName, "VerifyQMDrillDown", 1 );
			String measureName = excelData.get("Measure Name").get(0);
			auditRequest.navigateToResults(measureName, false, null, "Results");
			
			GridHelper.clickRow("SearchGrid", "10", "count_tap_id");
			NavigationHelper.navigateToAction("Drill Down", "150062 - QM - 1");
			GenericHelper.waitForLoadmask(searchScreenWaitSec);
			
			if (ButtonHelper.isPresent("YesButton")) {
				ButtonHelper.click("YesButton");
				GenericHelper.waitForLoadmask(searchScreenWaitSec);
			}
			
			GridHelper.clickRow("SearchGrid", 2, "Measure From");
			NavigationHelper.navigateToAction("Drill Down");
			assertTrue(NavigationHelper.isActionPresent("tapin_fingerprint"));
			NavigationHelper.navigateToAction("tapin_fingerprint");
			GenericHelper.waitForLoadmask(searchScreenWaitSec);
			
			if (ButtonHelper.isPresent("YesButton")) {
				ButtonHelper.click("YesButton");
				GenericHelper.waitForLoadmask(searchScreenWaitSec);
			}
			
			JumpToSearchHelper jumpToSearch = new JumpToSearchHelper();
			jumpToSearch.verifyRecordCount("10");
		} catch (AssertionError e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		} finally {
			ROCHelper rocHelper = new ROCHelper();
			rocHelper.handleFailures();
			
			controller.stopServerService();
			controller.stopTaskController("Task Controller");
			controller.stopStreamController();
		}
	}
}