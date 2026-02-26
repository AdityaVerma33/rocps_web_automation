package com.subex.automation.testcases.systemtesting.filesequencecheck;

import org.testng.annotations.Test;

import com.subex.automation.helpers.application.screens.CaseGroupFieldHelper;
import com.subex.automation.helpers.application.screens.CaseGroupHelper;
import com.subex.automation.helpers.application.screens.CasePropertyHelper;
import com.subex.automation.helpers.application.screens.CaseTemplateHelper;
import com.subex.automation.helpers.application.screens.TeamHelper;
import com.subex.automation.helpers.application.screens.UserHelper;
import com.subex.automation.helpers.application.screens.WorkflowHelper;
import com.subex.automation.helpers.application.screens.WorkstepHelper;
import com.subex.automation.helpers.selenium.ROCAcceptanceTest;
import com.subex.automation.helpers.util.FailureHelper;

public class testPrerequisite3 extends ROCAcceptanceTest {
	
	private static String path = null;
	final String fileName = "FileSequenceCheck_TestData.xlsx";
	final String sheetName = "FileSequenceCheck";
	
	public testPrerequisite3() throws Exception {
		try {
			path = automationPath + "\\src\\main\\resources\\System_Test_Flows\\";
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	@Test(priority=1, description="Create User", dependsOnGroups = { "prerequisite2" }, groups = { "prerequisite3" })
	public void createUser() throws Exception {
		try {
			UserHelper user = new UserHelper();
			user.createUser(path, fileName, sheetName, "Users", 1);
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	@Test(priority=2, description="Create Team", dependsOnMethods = { "createUser" }, groups = { "prerequisite3" })
	public void createTeam() throws Exception {
		try {
			TeamHelper team = new TeamHelper();
			team.createTeam(path, fileName, sheetName, "Teams", 1);
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	@Test(priority=3, description="Create Case Group Fields", dependsOnMethods = { "createTeam" }, groups = { "prerequisite3" })
	public void createCaseGroupField() throws Exception {
		try {
			CaseGroupFieldHelper caseGroupField = new CaseGroupFieldHelper();
			caseGroupField.createCaseGroupField(path, fileName, sheetName, "CaseGroupFields", 1);
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	@Test(priority=4, description="Create Case Group", dependsOnMethods = { "createCaseGroupField" }, groups = { "prerequisite3" })
	public void createCaseGroup() throws Exception {
		try {
			CaseGroupHelper caseGroup = new CaseGroupHelper();
			caseGroup.createCaseGroup(path, fileName, sheetName, "CaseGroup", 1);
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	@Test(priority=5, description="Create Case Properties", dependsOnMethods = { "createCaseGroupField" }, groups = { "prerequisite3" })
	public void createCaseProperty() throws Exception {
		try {
			CasePropertyHelper caseProperty = new CasePropertyHelper();
			caseProperty.createCaseProperty(path, fileName, sheetName, "CaseProperty", 1);
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	@Test(priority=6, description="Create Workstep", dependsOnMethods = { "createCaseProperty" }, groups = { "prerequisite3" })
	public void createWorkstep() throws Exception {
		try {
			WorkstepHelper workStep = new WorkstepHelper();
			workStep.createWorkstep(path, fileName, sheetName, "Workstep", 1);
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	@Test(priority=7, description="Create Workstep", dependsOnMethods = { "createWorkstep" }, groups = { "prerequisite3" })
	public void createWorkflow() throws Exception {
		try {
			WorkflowHelper workFlow = new WorkflowHelper();
			workFlow.createWorkflow(path, fileName, sheetName, "Workflow", 1);
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	@Test(priority=8, description="Create Case Template", dependsOnMethods = { "createWorkflow" }, groups = { "prerequisite3" })
	public void createCaseTemplate() throws Exception {
		try {
			CaseTemplateHelper caseTemplate = new CaseTemplateHelper();
			caseTemplate.createCaseTemplate(path, fileName, sheetName, "CaseTemplate", 1);
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
}