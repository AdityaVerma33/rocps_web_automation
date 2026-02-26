package com.subex.automation.testcases.regressiontesting.measures_audits.audit_issues;

import java.util.ArrayList;
import java.util.HashMap;

import org.testng.annotations.Test;

import com.subex.automation.helpers.application.ControllerHelper;
import com.subex.automation.helpers.application.CopyHelper;
import com.subex.automation.helpers.application.NavigationHelper;
import com.subex.automation.helpers.application.ROCHelper;
import com.subex.automation.helpers.application.screens.AuditDefinitionHelper;
import com.subex.automation.helpers.application.screens.AuditRequestHelper;
import com.subex.automation.helpers.application.screens.DataMatchMeasureHelper;
import com.subex.automation.helpers.component.GenericHelper;
import com.subex.automation.helpers.component.GridHelper;
import com.subex.automation.helpers.file.ExcelReader;
import com.subex.automation.helpers.util.FailureHelper;

public class test162503 extends testAuditIssues {

	final String sheetName = "test162503";

	public test162503() throws Exception {
		super();
	}

	@Test(priority=1, description="Test Case 1 for Bug 162503")
	public void testCase1()throws Exception
	{
		try {
			// Validate that user is able to create DM measure.
			DataMatchMeasureHelper dataMatchMeasure = new DataMatchMeasureHelper();
			dataMatchMeasure.createDataMatchMeasure(path, fileName, sheetName, "DataMatchMeasure", 1);
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}

	@Test(priority=2, description="Test Case 2 for Bug 162503", dependsOnMethods={"testCase1"})
	public void testCase2() throws Exception {
		ControllerHelper controller = new ControllerHelper();
		
		try {
			// Validate that user is able to add DM measure in Audit.
			AuditDefinitionHelper auditDfn = new AuditDefinitionHelper();
			auditDfn.createAuditDefinition(path, fileName, sheetName, "Audits", 1);
			
			AuditRequestHelper auditRequest = new AuditRequestHelper();
			auditRequest.createAuditRequest(path, fileName, sheetName, "AuditRequest", 1);

			// Start Controllers
			controller.startStreamController();
			controller.startTaskController(configProp.getTaskControllerExeFile());
			
			auditRequest.scheduleAuditRequest(path, fileName, sheetName, "AuditRequest", 1);
			auditRequest.verifyAuditRequest(path, fileName, sheetName, "VerifyAuditRequest", 1);
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		} finally {
			ROCHelper rocHelper = new ROCHelper();
			rocHelper.handleFailures();
			
			controller.stopTaskController("Task Controller");
			controller.stopStreamController();
		}
	}
	
	@Test(priority=3, description="Test Case 3 for Bug 162503", dependsOnMethods={"testCase2"})
	public void testCase3() throws Exception {
		try {
			// Validate that user is able to copy Data Match measures jump to search results , first one row.
			AuditRequestHelper auditRequest = new AuditRequestHelper();
			auditRequest.verifyResult(path, fileName, sheetName, "VerifyDMResult", 1);
			
			ExcelReader excelReader = new ExcelReader();
			HashMap<String, ArrayList<String>> excelData = excelReader.readDataByColumn( path, fileName, sheetName, "VerifyDMResult", 1 );
			String measureName = excelData.get("Measure Name").get(0);
			
			auditRequest.clickMeasure(measureName);
			NavigationHelper.navigateToAction("Results");
			GenericHelper.waitForLoadmask(detailScreenWaitSec);
			GridHelper.clickRow("SearchGrid", 1, 1);
			
			CopyHelper copy = new CopyHelper();
			copy.copySelectedCell("SearchGrid", 1, 1);
			String value = copy.getCopyContent();
			assertTrue(value.contains("43"));
		} catch (AssertionError e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
}