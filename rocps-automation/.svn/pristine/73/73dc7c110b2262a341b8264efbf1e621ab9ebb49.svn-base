package com.subex.rocps.automation.testcases.functionaltesting;

import com.subex.rocps.automation.helpers.application.approvalworkflows.ApprovalWorkFlows;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;

import com.subex.automation.helpers.application.screens.TeamHelper;
import com.subex.automation.helpers.application.screens.UserHelper;
import com.subex.automation.helpers.util.FailureHelper;

public class TCCreditApprovalWorkflow extends PSAcceptanceTest
{

	String path = System.getProperty( "user.dir" ) + "\\src\\main\\resources\\";
	String workBookName = "FunctionalTestCases.xlsx";
	String sheetName = "CreditApprovalWorkflow";
	
	@org.testng.annotations.Test( priority = 1, description = "Users  creation" )
	public void userCreation() throws Exception
	{
		
		try
		{
			UserHelper useObj = new UserHelper();
			useObj.createUser( path, workBookName, sheetName, "Users", 1 );
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}
	
	@org.testng.annotations.Test( priority = 2, description = "Team  creation" )
	public void teamCreation() throws Exception
	{
		
		try
		{
			TeamHelper teamObj = new TeamHelper();
			teamObj.createTeam( path, workBookName, sheetName, "Teams", 1 );
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}
	
	@org.testng.annotations.Test( priority = 3, description = "Approval WorkFlows  creation" )
	public void approvalWorkflowsCreation() throws Exception
	{
		
		try
		{
			ApprovalWorkFlows wrkflowObj = new ApprovalWorkFlows( path, workBookName, sheetName, "CreditApprovalWorkFlow", 1 );
			wrkflowObj.approvalWorkflowCreation();
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}
	
	@org.testng.annotations.Test( priority = 4, description = "Approval WorkFlows-Multiple approval stage  creation" )
	public void approvalWorkflowsMultipleCreation() throws Exception
	{
		
		try
		{
			ApprovalWorkFlows wrkflowObj = new ApprovalWorkFlows( path, workBookName, sheetName, "Multiple ApprovalStage", 1 );
			wrkflowObj.approvalWorkflowCreation();
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}
	
	@org.testng.annotations.Test( priority = 5, description = "Approval WorkFlows-Multiple Teams  creation" )
	public void approvalWorkflowsMultipleTeams() throws Exception
	{
		
		try
		{
			ApprovalWorkFlows wrkflowObj = new ApprovalWorkFlows( path, workBookName, sheetName, "Multiple teams", 1 );
			wrkflowObj.approvalWorkflowCreation();
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}
	
	
	
	/*	@org.testng.annotations.Test( priority = 6, description = "Approval WorkFlows  Delete" )
		public void approvalWorkflowsDelete() throws Exception
		{
			
			try
			{
				ApprovalWorkFlows wrkflowDelObj = new ApprovalWorkFlows( path, workBookName, sheetName, "ApprovalWorkFlow Delete", 1 );
				wrkflowDelObj.approvalWorkflowDelete();
			}
			catch ( Exception e )
			{
				FailureHelper.setErrorMessage( e );
				throw e;
			}
		}
		
		@org.testng.annotations.Test( priority = 7, description = "Approval WorkFlows Undelete" )
		public void approvalWorkflowsUnDelete() throws Exception
		{
			
			try
			{
				ApprovalWorkFlows wrkflowUnDelObj = new ApprovalWorkFlows( path, workBookName, sheetName, "ApprovalWorkFlow UnDelete", 1 );
				wrkflowUnDelObj.approvalWorkflowUnDelete();
			}
			catch ( Exception e )
			{
				FailureHelper.setErrorMessage( e );
				throw e;
			}
		}*/
	
	@org.testng.annotations.Test( priority = 8, description = "Approval WorkFlows search screen column validation" )
	public void approvalWorkflowsColVal() throws Exception
	{
		
		try
		{
			ApprovalWorkFlows wrkflowColValObj = new ApprovalWorkFlows( path, workBookName, sheetName, "ApprovalWFSearchScreencolVal", 1 );
			wrkflowColValObj.searchScreenColumnsValidation();
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}
	
}
