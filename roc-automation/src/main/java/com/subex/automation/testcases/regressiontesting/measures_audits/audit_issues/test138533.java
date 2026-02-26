package com.subex.automation.testcases.regressiontesting.measures_audits.audit_issues;

import org.testng.annotations.Test;

import com.subex.automation.helpers.application.screens.CaseInstanceHelper;
import com.subex.automation.helpers.application.screens.CasePropertyHelper;
import com.subex.automation.helpers.application.screens.CaseTemplateHelper;
import com.subex.automation.helpers.application.screens.WorkflowHelper;
import com.subex.automation.helpers.application.screens.WorkstepHelper;
import com.subex.automation.helpers.util.FailureHelper;

public class test138533 extends testAuditIssues {
	
	//final String fileName = "AuditIssues_RekhaData.xlsx";
	final String sheetName = "test138533";
	
	public test138533() throws Exception {
		super();
	}
	
	@Test(priority=1, description="Test Case 1 for Bug 138533")
	public void testCase1() throws Exception {
		try {
			// Validate that User is able to configure Case Instance with Case Property added in workstep.
            CasePropertyHelper caseproperty= new CasePropertyHelper();
			caseproperty.createCaseProperty(path, fileName, sheetName, "CaseProperty", 1);
			
			WorkstepHelper workstep = new WorkstepHelper();
			workstep.createWorkstep(path, fileName, sheetName, "Workstep", 1);

			WorkflowHelper workflow = new WorkflowHelper();
			workflow.createWorkflow(path, fileName, sheetName, "Workflow", 1);
			
			CaseTemplateHelper casetemplate = new CaseTemplateHelper();
			casetemplate.createCaseTemplate(path, fileName, sheetName, "CaseTemplate", 1);
			
			CaseInstanceHelper caseinstance = new CaseInstanceHelper();
			caseinstance.createCaseInstance(path, fileName, sheetName, "CaseInstance", 1);
		}
		catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
	}
  }
}