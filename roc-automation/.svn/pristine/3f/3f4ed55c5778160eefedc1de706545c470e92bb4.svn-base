package com.subex.automation.testcases.regressiontesting.measures_audits;

import org.testng.annotations.Test;

import com.subex.automation.helpers.application.screens.SQLMeasureHelper;
import com.subex.automation.helpers.application.screens.SQLMeasureRequestHelper;
import com.subex.automation.helpers.selenium.ROCAcceptanceTest;
import com.subex.automation.helpers.util.FailureHelper;

public class Audits_TC_17 extends ROCAcceptanceTest {
	
	String path = null;
	final String fileName = "AuditRegression_TestData.xlsx";
	final String sheetName = "Audits_TC_17";
	
	public Audits_TC_17() throws Exception {
		try {
			path = automationPath + "\\src\\main\\resources\\Regression\\";
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	@Test(priority = 1, description = "Test Case 17")
	public void testCase17() throws Exception { 
		try {
			 //sql measure with reporting table
			
          	SQLMeasureHelper sqlmeasure=new SQLMeasureHelper();
			sqlmeasure.createSQLMeasure(path,fileName,sheetName,"SqlMeasure" ,1);
			
			SQLMeasureRequestHelper sqlMeasureRequest = new SQLMeasureRequestHelper();
			sqlMeasureRequest.createMeasureRequest(path, fileName, sheetName, "SqlMeasureRequest", 1);
			sqlMeasureRequest.scheduleMeasureRequest(path, fileName, sheetName, "SqlMeasureRequest", 1);

		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
}