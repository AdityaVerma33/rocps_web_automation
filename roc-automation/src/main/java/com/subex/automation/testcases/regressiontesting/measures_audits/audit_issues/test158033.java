package com.subex.automation.testcases.regressiontesting.measures_audits.audit_issues;

import org.testng.annotations.Test;

import com.subex.automation.helpers.application.NavigationHelper;
import com.subex.automation.helpers.application.screens.ComparisonMeasureHelper;
import com.subex.automation.helpers.application.screens.ComparisonMeasureRequestHelper;
import com.subex.automation.helpers.application.screens.QueryMeasureHelper;
import com.subex.automation.helpers.application.screens.QueryMeasureRequestHelper;
import com.subex.automation.helpers.application.screens.SettingsHelper;
import com.subex.automation.helpers.component.GridHelper;
import com.subex.automation.helpers.util.FailureHelper;

public class test158033 extends testAuditIssues {
	
	final String sheetName = "test158033";
	
	public test158033() throws Exception {
		super();
	}
	
	@Test(priority = 1, description = "Test Case 1 for Bug 158033")
	public void testCase1() throws Exception { 
		try {
			 //Validate that On summary grid date format can be changed and saved successfully (Comparison Measure).
			QueryMeasureHelper queryMeasure = new QueryMeasureHelper();
			queryMeasure.createQueryMeasure(path, fileName, sheetName, "QueryMeasure", 1);
			
			QueryMeasureRequestHelper queryMeasureRequest = new QueryMeasureRequestHelper();
			queryMeasureRequest.createMeasureRequest(path, fileName, sheetName, "MeasureRequest", 1);
			queryMeasureRequest.scheduleMeasureRequest(path, fileName, sheetName, "MeasureRequest", 1);
			
			ComparisonMeasureHelper comparisonMeasure = new ComparisonMeasureHelper();
			comparisonMeasure.createComparisonMeasure(path, fileName, sheetName, "ComparisonMeasure", 1);
			
			ComparisonMeasureRequestHelper comparisonMeasureRequest = new ComparisonMeasureRequestHelper();
			comparisonMeasureRequest.createMeasureRequest(path, fileName, sheetName, "ComparisonMeasureRequest", 1);
	        comparisonMeasureRequest.scheduleMeasureRequest(path, fileName, sheetName, "ComparisonMeasureRequest", 1);
	        
			int row = comparisonMeasureRequest.navigateToMeasureRequest("CM - 158033");
            if (row > 0)
		    GridHelper.clickRow("SearchGrid", row, "Measure");
			NavigationHelper.navigateToAction("Measure Results", "Jump To Summary");
			
            SettingsHelper updateSettings= new SettingsHelper();
			updateSettings.updateSettings(path ,fileName, sheetName, "UpdateUserProperties", 1);
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
}