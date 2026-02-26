package com.subex.automation.testcases.systemtesting.audits1;

import org.testng.annotations.Test;

import com.subex.automation.helpers.application.screens.CasePropertyHelper;
import com.subex.automation.helpers.application.screens.CaseTemplateHelper;
import com.subex.automation.helpers.application.screens.TeamHelper;
import com.subex.automation.helpers.application.screens.UserHelper;
import com.subex.automation.helpers.application.screens.WorkflowHelper;
import com.subex.automation.helpers.application.screens.WorkstepHelper;
import com.subex.automation.helpers.selenium.ROCAcceptanceTest;
import com.subex.automation.helpers.util.FailureHelper;

public class testPrerequisite3 extends ROCAcceptanceTest
{
	private static String path = null;
	final String dataLocation = "/src/main/resources/Data/Audit_Flow";
	final String fileName = "AuditFlow1_TestData.xlsx";
	final String sheetName = "AuditFlow1";
	
	public testPrerequisite3() throws Exception {
		try {
			path = automationPath + "\\src\\main\\resources\\System_Test_Flows\\";
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	@Test(priority=1, description="Creation of User for privileges in case template and work step", dependsOnGroups = { "prerequisite2" }, groups = { "prerequisite3" })
	public void createUser() throws Exception
	{
		try {
			UserHelper users = new UserHelper();
			users.createUser(path, fileName, sheetName, "Users", 1);
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	@Test(priority=2, description="Creation of Team for privileges in case template and work step", dependsOnMethods = { "createUser" }, groups = { "prerequisite3" })
	public void createTeam() throws Exception
	{
		try {
			TeamHelper teams = new TeamHelper();
			teams.createTeam(path, fileName, sheetName, "Teams", 1);
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	@Test(priority=3, description="Creation of case properties for linking case template in summarized measure", dependsOnMethods = { "createTeam" }, groups = { "prerequisite3" })
	public void createCaseProperty() throws Exception
	{
		try {
			CasePropertyHelper caseProperty = new CasePropertyHelper();
			caseProperty.createCaseProperty(path, fileName, sheetName, "CaseProperty", 1);
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	@Test(priority=4, description="Creation of work step for case management in measures", dependsOnMethods = { "createCaseProperty" }, groups = { "prerequisite3" })
	public void createWorkstep() throws Exception
	{
		try {
			WorkstepHelper workstep = new WorkstepHelper();
			workstep.createWorkstep(path, fileName, sheetName, "Workstep", 1);
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	@Test(priority=5, description="Creation of work flow for case management in measures", dependsOnMethods = { "createWorkstep" }, groups = { "prerequisite3" })
	public void createWorkflow() throws Exception
	{
		try {
			WorkflowHelper workflow = new WorkflowHelper();
			workflow.createWorkflow(path, fileName, sheetName, "WorkFlow", 1);
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	@Test(priority=6, description="Creation of case template. This template should be linked to the KPI attached to summarized measure", dependsOnMethods = { "createWorkflow" }, groups = { "prerequisite3" })
	public void createCaseTemplate() throws Exception
	{
		try {
			CaseTemplateHelper caseTemplate = new CaseTemplateHelper();
			caseTemplate.createCaseTemplate(path, fileName, sheetName, "CaseTemplate", 1);
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
}