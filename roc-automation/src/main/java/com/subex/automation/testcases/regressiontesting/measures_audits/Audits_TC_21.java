package com.subex.automation.testcases.regressiontesting.measures_audits;

import org.testng.annotations.Test;

import com.subex.automation.helpers.application.screens.ComparisonMeasureHelper;
import com.subex.automation.helpers.application.screens.QueryMeasureHelper;
import com.subex.automation.helpers.selenium.ROCAcceptanceTest;
import com.subex.automation.helpers.util.FailureHelper;

public class Audits_TC_21 extends ROCAcceptanceTest {
	
	String path = null;
	final String fileName = "AuditRegression_TestData.xlsx";
	final String sheetName = "Audits_TC_21";
	
	public Audits_TC_21() throws Exception {
		try {
			path = automationPath + "\\src\\main\\resources\\Regression\\";
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	@Test(priority = 1, description = "Test Case 21")
	public void testCase1() throws Exception { 
		try {
			 //Validate that user is able to create CM measure with Summary Information
			QueryMeasureHelper queryMeasure = new QueryMeasureHelper();
			queryMeasure.createQueryMeasure(path, fileName, sheetName, "QueryMeasure", 1);
			
			ComparisonMeasureHelper comparisonMeasure = new ComparisonMeasureHelper();
			comparisonMeasure.createComparisonMeasure(path, fileName, sheetName, "ComparisonMeasure", 1);
			
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
}