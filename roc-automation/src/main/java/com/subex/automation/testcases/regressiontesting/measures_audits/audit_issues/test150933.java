package com.subex.automation.testcases.regressiontesting.measures_audits.audit_issues;

import org.testng.annotations.Test;

import com.subex.automation.helpers.application.NavigationHelper;
import com.subex.automation.helpers.application.screens.ComparisonMeasureHelper;
import com.subex.automation.helpers.application.screens.QueryMeasureHelper;
import com.subex.automation.helpers.report.Log4jHelper;
import com.subex.automation.helpers.util.FailureHelper;

public class test150933 extends testAuditIssues {
	
	final String sheetName = "test150933";
	
	public test150933() throws Exception {
		super();
	}

	@Test(priority=1, description="Test Case 1 for Bug 150933")
	public void testCase1() throws Exception {
		try {
			// User should be able to see and navigate to Measure Screens through Quick Links.
			Log4jHelper.logInfo("Running Measures and Audits Issues Test Cases");
			String[] groupName = {"Reconciliation"};
			String[][] screenNames = {{"Measures", "Measure Requests", "KPI Definitions", "KPI Results"}};
			String[][] screenTitles = {{"Measure Search", "Measure Request Search", "KPI Definition Search", "KPI Result Search"}};
			
			NavigationHelper.setAsQuickLink(groupName, screenNames);
			for (int i = 0; i < groupName.length; i++) {
				for (int j = 0; j < screenNames[i].length; j++)
					NavigationHelper.navigateThroughQuickLinks(screenNames[i][j], screenTitles[i][j]);
			}
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	@Test(priority=2, description="Test Case 2 for Bug 150933")
	public void testCase2() throws Exception {
		try {
			// User should be able to see and navigate to Measure Screens through Quick Links.
			String[] groupName = {"Reconciliation"};
			String[][] screenNames = {{"Audits", "Audit Requests", "Audit Schedule"}};
			String[][] screenTitles = {{"Audit Search", "Audit Request Search", "Audit Schedule Search"}};
			
			NavigationHelper.setAsQuickLink(groupName, screenNames);
			for (int i = 0; i < groupName.length; i++) {
				for (int j = 0; j < screenNames[i].length; j++)
					NavigationHelper.navigateThroughQuickLinks(screenNames[i][j], screenTitles[i][j]);
			}
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	@Test(priority=3, description="Test Case 3 for Bug 150933")
	public void testCase3() throws Exception {
		try {
			// User should be able to see and navigate to Measure Screens through Group.
			Log4jHelper.logInfo("Running Measures and Audits Issues Test Cases");
			String[] groupName = {"Reconciliation"};
			String[][] screenNames = {{"Measures", "Measure Requests", "KPI Definitions", "KPI Results"}};
			String[][] screenTitles = {{"Measure Search", "Measure Request Search", "KPI Definition Search", "KPI Result Search"}};
			
			for (int i = 0; i < groupName.length; i++) {
				for (int j = 0; j < screenNames[i].length; j++)
					NavigationHelper.navigateThroughGroup(groupName[i], screenNames[i][j], screenTitles[i][j]);
			}
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	@Test(priority=4, description="Test Case 4 for Bug 150933")
	public void testCase4() throws Exception {
		try {
			// User should be able to see and navigate to Measure Screens through Group.
			String[] groupName = {"Reconciliation"};
			String[][] screenNames = {{"Audits", "Audit Requests", "Audit Schedule"}};
			String[][] screenTitles = {{"Audit Search", "Audit Request Search", "Audit Schedule Search"}};
			
			for (int i = 0; i < groupName.length; i++) {
				for (int j = 0; j < screenNames[i].length; j++)
					NavigationHelper.navigateThroughGroup(groupName[i], screenNames[i][j], screenTitles[i][j]);
			}
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	@Test(priority=5, description="Test Case 5 for Bug 150933")
	public void testCase5() throws Exception {
		try {
			// Validate that user is able to interlink the measure.
			QueryMeasureHelper queryMeasure = new QueryMeasureHelper();
			queryMeasure.createQueryMeasure(path, fileName, sheetName, "QueryMeasure", 1);
			
			ComparisonMeasureHelper comparisonMeasure = new ComparisonMeasureHelper();
			comparisonMeasure.createComparisonMeasure(path, fileName, sheetName, "ComparisonMeasure", 1);
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	@Test(priority=6, description="Test Case 6 for Bug 150933")
	public void testCase6() throws Exception {
		try {
			// Validate that user is able to add in 3 measures which are interlinked.
			QueryMeasureHelper queryMeasure = new QueryMeasureHelper();
			queryMeasure.createQueryMeasure(path, fileName, sheetName, "QueryMeasure", 1);
			
			ComparisonMeasureHelper comparisonMeasure = new ComparisonMeasureHelper();
			comparisonMeasure.createComparisonMeasure(path, fileName, sheetName, "ComparisonMeasure", 1);
			
			queryMeasure.createQueryMeasure(path, fileName, sheetName, "QueryMeasure", 2);
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
}