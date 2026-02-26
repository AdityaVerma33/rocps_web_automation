package com.subex.automation.testcases.regressiontesting.measures_audits.audit_issues;

import org.testng.annotations.Test;

import com.subex.automation.helpers.application.ControllerHelper;
import com.subex.automation.helpers.application.NavigationHelper;
import com.subex.automation.helpers.application.ROCHelper;
import com.subex.automation.helpers.application.screens.KPIDefinitionHelper;
import com.subex.automation.helpers.application.screens.KPIResultsHelper;
import com.subex.automation.helpers.application.screens.QueryMeasureHelper;
import com.subex.automation.helpers.application.screens.QueryMeasureRequestHelper;
import com.subex.automation.helpers.component.ButtonHelper;
import com.subex.automation.helpers.component.GenericHelper;
import com.subex.automation.helpers.component.GridHelper;
import com.subex.automation.helpers.component.LabelHelper;
import com.subex.automation.helpers.util.FailureHelper;

public class test152212 extends testAuditIssues {

	final String sheetName = "test152212";

	public test152212() throws Exception {
		super();
	}

	@Test(priority=1, description="Test Case 1 for Bug 152212")
	public void testCase1() throws Exception
	{
		try {
			// Validate that User is able to create Measure.
			QueryMeasureHelper queryMeasure = new QueryMeasureHelper();
			queryMeasure.createQueryMeasure(path, fileName, sheetName, "QueryMeasure", 1);
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}

	@Test(priority=2, description="Test Case 2 for Bug 152212", dependsOnMethods={"testCase1"})
	public void testCase2()throws Exception
	{
		try {
			// Validate that User is able to create KPI Definition.
			KPIDefinitionHelper kpiDefinition = new KPIDefinitionHelper();
			kpiDefinition.createKPIDefinition(path, fileName, sheetName, "KPIDefinition", 1);
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}

	@Test(priority=3, description="Test Case 3 for Bug 152212", dependsOnMethods={"testCase2"})
	public void testCase3()throws Exception {
		ControllerHelper controller = new ControllerHelper();
		
		try {
			// Validate that user is able to schedule measure.
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
	
	@Test(priority=4, description="Test Case 4 for Bug 152212", dependsOnMethods={"testCase3"})
	public void testCase4()throws Exception {
		try {
			// Validate that record count in KPI Definition working properly.
			KPIResultsHelper kpiResult = new KPIResultsHelper();
			kpiResult.verifyKPIResult(path, fileName, sheetName, "VerifyKPIResult", 1);
			int rows = GridHelper.getRowCount("SearchGrid");
			
			if (rows > 0) {
				GridHelper.clickRow("SearchGrid", 1, 1);
				NavigationHelper.navigateToAction("Kpi Result Actions", "View KPI Values");
				GenericHelper.waitForLoadmask(detailScreenWaitSec);
				assertTrue(LabelHelper.isTitlePresent("KPI Value Search"));
				
				ButtonHelper.click("J2S_RecordCount");
				GenericHelper.waitForLoadmask(searchScreenWaitSec);
				GenericHelper.waitForElementToDisappear("J2S_RecordCount_PleaseWait", searchScreenWaitSec);
				GenericHelper.waitForElement("OKButton", searchScreenWaitSec);
				
				assertTrue(LabelHelper.isTextPresent("Record Count35"));
				ButtonHelper.click("OKButton");
				GenericHelper.waitForLoadmask(searchScreenWaitSec);
				
				if (ButtonHelper.isPresent("OKButton")) {
					ButtonHelper.click("OKButton");
					GenericHelper.waitForLoadmask(searchScreenWaitSec);
				}
			}
			else {
				FailureHelper.failTest("No rows found in KPI results screens.");
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