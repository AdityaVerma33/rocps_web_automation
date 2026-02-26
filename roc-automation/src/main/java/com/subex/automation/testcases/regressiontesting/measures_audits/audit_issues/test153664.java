package com.subex.automation.testcases.regressiontesting.measures_audits.audit_issues;

import org.testng.annotations.Test;

import com.subex.automation.helpers.application.ConfigureGridHelper;
import com.subex.automation.helpers.application.ControllerHelper;
import com.subex.automation.helpers.application.NavigationHelper;
import com.subex.automation.helpers.application.screens.JumpToResultHelper;
import com.subex.automation.helpers.application.screens.QueryMeasureHelper;
import com.subex.automation.helpers.application.screens.QueryMeasureRequestHelper;
import com.subex.automation.helpers.application.screens.TaskSearchHelper;
import com.subex.automation.helpers.component.ButtonHelper;
import com.subex.automation.helpers.component.GenericHelper;
import com.subex.automation.helpers.component.GridHelper;
import com.subex.automation.helpers.component.LabelHelper;
import com.subex.automation.helpers.report.Log4jHelper;
import com.subex.automation.helpers.util.FailureHelper;

public class test153664 extends testAuditIssues {
	
	final String fileName = "AuditIssues_TestData.xlsx";
	static String sheetName = "test153664";
	
	public test153664() throws Exception {
		super();
	}

	@Test(priority = 1, description = "Test Case 1 for Bug 153664")
	public void testCase1() throws Exception {
		try {
			// validate the user is able to create Query Measure with Aggregate fields. .
			QueryMeasureHelper queryMeasure = new QueryMeasureHelper();
			queryMeasure.createQueryMeasure(path, fileName, sheetName, "QueryMeasure", 1);
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	@Test(priority = 2, description = "Test Case 2 for Bug 153664", dependsOnMethods={"testCase1"})
	public void testCase2() throws Exception {
		try {
			// Validate that user is able to schedule Query Measure Request for measure with Aggregate fields.
			QueryMeasureRequestHelper queryMeasureRequest = new QueryMeasureRequestHelper();
			queryMeasureRequest.createMeasureRequest(path, fileName, sheetName, "MeasureRequest", 1);
			
			// Start Controllers
			ControllerHelper controller = new ControllerHelper();
			controller.startStreamController();
			controller.startTaskController(configProp.getTaskControllerExeFile());
			
			queryMeasureRequest.scheduleMeasureRequest(path, fileName, sheetName, "MeasureRequest", 1);
			Thread.sleep(3000);
			queryMeasureRequest.verifyMeasureRequest(path, fileName, sheetName, "VerifyMeasureRequest", 1);
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	@Test(priority = 3, description = "Test Case 3 for Bug 153664", dependsOnMethods={"testCase2"})
	public void testCase3() throws Exception {
		try {
			// Validate that coloring rule configuration in Measure results drill down summary configure grid is working properly.
			String qmName = "QM-153664";
			QueryMeasureRequestHelper queryMeasureRequest = new QueryMeasureRequestHelper();
			int row = queryMeasureRequest.navigateToMeasureRequest(qmName);

			if (row > 0) {
				GridHelper.clickRow("SearchGrid", row, "Measure");
				NavigationHelper.navigateToAction("Measure Results", "Jump To Results");
				GenericHelper.waitForLoadmask(searchScreenWaitSec);
				assertTrue(LabelHelper.isTitlePresent(qmName + " Results"), "Jump to Results screen did not appear.");
				
				ConfigureGridHelper configureGrid = new ConfigureGridHelper();
				configureGrid.addColorFilter(path, fileName, sheetName, "MeasureConfigureGridAddColor", 1);
			}
			else {
				FailureHelper.failTest("Query Measure Request is not found for Measure '" + qmName + "'.");
			}
		} catch (AssertionError e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	@Test(priority = 4, description = "Test Case 1 for Bug 136899", dependsOnMethods={"testCase2"})
	public void testCase1_136899() throws Exception {
		try {
			// Validate that coloring rule configuration in Measure results drill down summary configure grid is working properly.
			String qmName = "QM-153664";
			QueryMeasureRequestHelper queryMeasureRequest = new QueryMeasureRequestHelper();
			int row = queryMeasureRequest.navigateToMeasureRequest(qmName);
			
			if (row > 0) {
				GridHelper.clickRow("SearchGrid", row, "Measure");
				NavigationHelper.navigateToAction("Measure Results", "Jump To Results");
				GenericHelper.waitForLoadmask(searchScreenWaitSec);
				assertTrue(LabelHelper.isTitlePresent(qmName + " Results"));
				
				NavigationHelper.navigateToAction("Export", "All Rows");
			    GenericHelper.waitForLoadmask(searchScreenWaitSec);
				assertTrue(LabelHelper.isTitlePresent("Information"));
				
				ButtonHelper.click("OKButton");
				GenericHelper.waitForLoadmask(searchScreenWaitSec);
				Log4jHelper.logInfo("Export All Rows scheduled for Measure Request'" + qmName + "'");
				
				TaskSearchHelper taskSearch = new TaskSearchHelper();
				taskSearch.verifyTaskStatus(path, fileName, sheetName, "ExportTask", 1);
				
				String exportDirectory = configProp.getDataDirPath() + "/ExportData";
				String downloadDirectory = configProp.getDownloadDirectory();
				String[] linesValuesToIgnore = {"Exported on:"};
				JumpToResultHelper jumpToResult = new JumpToResultHelper();
				jumpToResult.verifyExportAllRows("mea_req", exportDirectory, downloadDirectory, path + "QM-153664_Export.txt", linesValuesToIgnore);
			}
			else {
				FailureHelper.failTest("Query Measure Request is not found for Measure '" + qmName + "'.");
			}
		} catch (AssertionError e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		} finally {
			ControllerHelper controller = new ControllerHelper();
			controller.stopTaskController("Task Controller");
			controller.stopStreamController();
		}
	}
}