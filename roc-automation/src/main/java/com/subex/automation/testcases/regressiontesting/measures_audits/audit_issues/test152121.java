package com.subex.automation.testcases.regressiontesting.measures_audits.audit_issues;

import org.testng.annotations.Test;

import com.subex.automation.helpers.application.ControllerHelper;
import com.subex.automation.helpers.application.ROCHelper;
import com.subex.automation.helpers.application.screens.KPIDefinitionHelper;
import com.subex.automation.helpers.application.screens.QueryMeasureHelper;
import com.subex.automation.helpers.application.screens.QueryMeasureRequestHelper;
import com.subex.automation.helpers.util.FailureHelper;

public class test152121 extends testAuditIssues {

	final String sheetName = "test152121";

	public test152121() throws Exception {
		super();
	}

	@Test(priority=1, description="Test Case 1 for Bug 152121")
	public void testCase1() throws Exception {
		try {
			// Validate that User is able to create Measure with group by fields and an aggregated fields.
			QueryMeasureHelper queryMeasure = new QueryMeasureHelper();
			queryMeasure.createQueryMeasure(path, fileName, sheetName, "QueryMeasure", 1);
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}

	@Test(priority=2, description="Test Case 2 for Bug 152121", dependsOnMethods={"testCase1"})
	public void testCase2() throws Exception {
		try {
			// Validate that User is able to create KPI  on aggregated field with "apply kpi at group level".
			KPIDefinitionHelper kpiDefinition = new KPIDefinitionHelper();
			kpiDefinition.createKPIDefinition(path, fileName, sheetName, "KPIDefinition", 1);
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}

	@Test(priority=3, description="Test Case 3 for Bug 152121", dependsOnMethods={"testCase2"})
	public void testCase3()throws Exception {
		ControllerHelper controller = new ControllerHelper();
		
		try {
			// Validate that user is able to Request and Schedule Measure.
			QueryMeasureRequestHelper queryMeasureRequest = new QueryMeasureRequestHelper();
			queryMeasureRequest.createMeasureRequest(path, fileName, sheetName, "MeasureRequest", 1);

			controller.startStreamController();
			controller.startTaskController(configProp.getTaskControllerExeFile());

			queryMeasureRequest.scheduleMeasureRequest(path, fileName, sheetName, "MeasureRequest", 1);
			queryMeasureRequest.verifyMeasureRequest(path, fileName, sheetName, "VerifyMeasureRequest", 1);
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
	
	@Test(priority=4, description="Test Case 4 for Bug 152121", dependsOnMethods={"testCase3"})
	public void testCase4()throws Exception {
		try {
			// Validate that View KPI Values throws null pointer exception.
			QueryMeasureRequestHelper queryMeasureRequest = new QueryMeasureRequestHelper();
			queryMeasureRequest.verifyKPIResult(path, fileName, sheetName, "VerifyKPIResult", 1);
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		} 
	}
}