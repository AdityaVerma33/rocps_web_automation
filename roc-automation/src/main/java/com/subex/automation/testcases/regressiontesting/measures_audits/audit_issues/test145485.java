package com.subex.automation.testcases.regressiontesting.measures_audits.audit_issues;

import java.util.ArrayList;
import java.util.HashMap;

import org.testng.annotations.Test;

import com.subex.automation.helpers.application.NavigationHelper;
import com.subex.automation.helpers.application.screens.AuditDefinitionHelper;
import com.subex.automation.helpers.application.screens.AuditRequestHelper;
import com.subex.automation.helpers.application.screens.QueryMeasureHelper;
import com.subex.automation.helpers.component.ButtonHelper;
import com.subex.automation.helpers.component.GenericHelper;
import com.subex.automation.helpers.component.GridHelper;
import com.subex.automation.helpers.component.LabelHelper;
import com.subex.automation.helpers.component.SearchGridHelper;
import com.subex.automation.helpers.file.ExcelReader;
import com.subex.automation.helpers.util.FailureHelper;

public class test145485 extends testAuditIssues {

	final String sheetName = "test145485";

	public test145485() throws Exception {
		super();
	}

	@Test(priority=1, description="Test Case 1 for Bug 145485")
	public void testCase1() throws Exception {
		try {
			// Validate that Audit Request audit name is getting displayed beside measure after clicking on Search or Clear.
			QueryMeasureHelper queryMeasure = new QueryMeasureHelper();
			queryMeasure.createQueryMeasure(path, fileName, sheetName, "QueryMeasure", 1);
			
			AuditDefinitionHelper auditDfn = new AuditDefinitionHelper();
			auditDfn.createAuditDefinition(path, fileName, sheetName, "Audits", 1);
			
			AuditRequestHelper auditRequest = new AuditRequestHelper();
			auditRequest.createAuditRequest(path, fileName, sheetName, "AuditRequest", 1);
			
			ExcelReader excelReader = new ExcelReader();
			HashMap<String, ArrayList<String>> excelData = excelReader.readDataByColumn( path, fileName, sheetName, "AuditRequest", 1 );
			String auditName = excelData.get("Audit Name").get(0);

			NavigationHelper.navigateToScreen("Audit Requests", "Audit Request Search");
			int row = SearchGridHelper.gridFilterAdvancedSearch("AuditRequest_Audit_Filter", auditName, "Audit");
			GridHelper.clickRow("SearchGrid", row, "Audit");
			String actualLabel = LabelHelper.getText("AuditRequest_Measures_Label");
			assertEquals(actualLabel, "Measure Requests( " + auditName + " ) :");
			
			ButtonHelper.click("ClearButton");
			GenericHelper.waitForLoadmask(searchScreenWaitSec);
			actualLabel = LabelHelper.getText("AuditRequest_Measures_Label");
			assertEquals(actualLabel, "Measure Requests:");
			
			ButtonHelper.click("SearchButton");
			GenericHelper.waitForLoadmask(searchScreenWaitSec);
			actualLabel = LabelHelper.getText("AuditRequest_Measures_Label");
			assertEquals(actualLabel, "Measure Requests:");
		} catch (AssertionError e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
}