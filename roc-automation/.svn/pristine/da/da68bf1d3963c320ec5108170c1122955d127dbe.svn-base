package com.subex.automation.testcases.regressiontesting.measures_audits.audit_issues;

import com.subex.automation.helpers.application.screens.CasePropertyHelper;
import com.subex.automation.helpers.application.screens.CaseTemplateHelper;
import com.subex.automation.helpers.application.screens.WorkflowHelper;
import com.subex.automation.helpers.application.screens.WorkstepHelper;
import com.subex.automation.helpers.selenium.ROCAcceptanceTest;
import com.subex.automation.helpers.util.FailureHelper;

public class testAuditIssues extends ROCAcceptanceTest {
	
	String path = null;
	final String fileName = "AuditIssues_TestData.xlsx";
	
	public testAuditIssues() throws Exception {
		try {
			path = automationPath + "\\src\\main\\resources\\Issues\\";
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public void createCaseTemplate(String sheetName) throws Exception {
		try {
			CasePropertyHelper caseProperty = new CasePropertyHelper();
			caseProperty.createCaseProperty(path, fileName, sheetName, "CaseProperty", 1);
			
			WorkstepHelper workstep = new WorkstepHelper();
			workstep.createWorkstep(path, fileName, sheetName, "Workstep", 1);
			
			WorkflowHelper workflow = new WorkflowHelper();
			workflow.createWorkflow(path, fileName, sheetName, "WorkFlow", 1);
			
			CaseTemplateHelper caseTemplate = new CaseTemplateHelper();
			caseTemplate.createCaseTemplate(path, fileName, sheetName, "CaseTemplate", 1);
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
}