package com.subex.automation.testcases.regressiontesting.measures_audits.audit_issues;

import org.testng.annotations.Test;

import com.subex.automation.helpers.application.ControllerHelper;
import com.subex.automation.helpers.application.screens.QueryMeasureHelper;
import com.subex.automation.helpers.application.screens.QueryMeasureRequestHelper;
import com.subex.automation.helpers.component.GridHelper;
import com.subex.automation.helpers.util.FailureHelper;

public class test162501 extends testAuditIssues {
	
	final String sheetName = "test162501";
	
	public test162501() throws Exception {
		super();
	}
	
	@Test(priority=1, description="Test Case 1 for Bug 162501")
	public void testCase1() throws Exception {
		ControllerHelper controller = new ControllerHelper();
		
		try {
			// Validate that when date changed in manual measure doesn't create a new measure request.
			QueryMeasureHelper queryMeasure = new QueryMeasureHelper();
			queryMeasure.createQueryMeasure(path, fileName, sheetName, "QueryMeasure", 1);
			
			QueryMeasureRequestHelper queryMeasureRequest = new QueryMeasureRequestHelper();
			queryMeasureRequest.createMeasureRequest(path, fileName, sheetName, "MeasureRequest", 1);
			
			controller.startStreamController();
			controller.startTaskController(configProp.getTaskControllerExeFile());
			
			queryMeasureRequest.scheduleMeasureRequest(path, fileName, sheetName, "MeasureRequest", 1);
			queryMeasureRequest.verifyMeasureRequest(path, fileName, sheetName, "VerifyMeasureRequest", 1);
			
			queryMeasure.createQueryMeasure(path, fileName, sheetName, "QueryMeasure", 2);
			int rows = GridHelper.getRowCount("SearchGrid");
			assertTrue(rows == 1);
			
			queryMeasureRequest.scheduleMeasureRequest(path, fileName, sheetName, "MeasureRequest", 1);
			rows = GridHelper.getRowCount("SearchGrid");
			assertTrue(rows == 1);
		} catch (AssertionError e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		} finally {
			controller.stopTaskController("Task Controller");
			controller.stopStreamController();
		}
	}
}