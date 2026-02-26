package com.subex.automation.testcases.regressiontesting.measures_audits.audit_issues;

import java.util.ArrayList;
import java.util.HashMap;

import org.testng.annotations.Test;

import com.subex.automation.helpers.application.MeasureHelper;
import com.subex.automation.helpers.application.NavigationHelper;
import com.subex.automation.helpers.application.screens.QueryMeasureHelper;
import com.subex.automation.helpers.component.GridHelper;
import com.subex.automation.helpers.component.TabHelper;
import com.subex.automation.helpers.component.TextBoxHelper;
import com.subex.automation.helpers.file.ExcelReader;
import com.subex.automation.helpers.util.FailureHelper;

public class test145029 extends testAuditIssues {
	
	final String sheetName = "test145029";
	
	public test145029() throws Exception {
		super();
	}
	
	@Test(priority=1, description="Test Case 1 for Bug 145029")
	public void testCase1() throws Exception {
		try {
			// Validate that user is able to save the QM without getting syntax error after delete the brackets in filters.
			QueryMeasureHelper queryMeasure = new QueryMeasureHelper();
			queryMeasure.createQueryMeasure(path, fileName, sheetName, "QueryMeasure", 1);
			
			ExcelReader excelReader = new ExcelReader();
			HashMap<String, ArrayList<String>> excelData = excelReader.readDataByColumn( path, fileName, sheetName, "QueryMeasure", 1 );
			String measureName = excelData.get("Name").get(0);
			int row = GridHelper.getRowNumber("SearchGrid", measureName, "Name");
			
			if (row > 0) {
				NavigationHelper.navigateToEdit("SearchGrid", row, "QM_Name");
				
				String gridId = "QueryFilter_Expression_GridWrapper";
				TabHelper.gotoTab("QM_Workflow_Panel", "Filters");
				GridHelper.clickRow(gridId, 1, "(");
				if(!TextBoxHelper.isPresent("QueryFilter_Expression_LeftIndent"))
					GridHelper.clickRow(gridId, 1, "(");
				TextBoxHelper.clear("QueryFilter_Expression_LeftIndent");
				GridHelper.clickRow(gridId, 1, "Clause");
				
				GridHelper.clickRow(gridId, 1, ")");
				if(!TextBoxHelper.isPresent("QueryFilter_Expression_RightIndent"))
					GridHelper.clickRow(gridId, 1, ")");
				TextBoxHelper.clear("QueryFilter_Expression_RightIndent");
				GridHelper.clickRow(gridId, 1, "Clause");
				
				MeasureHelper measure = new MeasureHelper();
				measure.saveMeasure("Query Measure", measureName, "Edit Query Measure");
			}
			else {
				FailureHelper.failTest("Query Measure '" + measureName + "' did not get created.");
			}
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
}