package com.subex.rocps.automation.testcases.functionaltesting;

import com.subex.automation.helpers.util.FailureHelper;
import com.subex.rocps.automation.helpers.application.partnerConfiguration.Agent;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;

public class TCAgent extends PSAcceptanceTest
{

	String path = System.getProperty( "user.dir" ) + "\\src\\main\\resources\\";
	String workBookName = "FunctionalTestCases.xlsx";
	String sheetName = "Agent";

	@org.testng.annotations.Test( priority = 1, description = "Agent creation", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void agentCreation() throws Exception
	{
		try
		{
			Agent accobj = new Agent( path, workBookName, sheetName, "Agent", 1 );
			accobj.agentCreation();
		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 2, description = "Agent with Parent Account creation", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void agentparentCreation() throws Exception
	{
		try
		{
			Agent accobj = new Agent( path, workBookName, sheetName, "ParentAgent", 1 );
			accobj.agentCreation();
		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 3, description = "Agent Column Validation" )
	public void agentcolVal() throws Exception
	{
		try
		{
			Agent accobj = new Agent( path, workBookName, sheetName, "AgentSearchScreencolVal", 1 );
			accobj.searchScreenColumnsValidation();
		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 4, enabled = true, description = "Agent creation1", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void agentCreation1() throws Exception
	{
		try
		{
			Agent accobj = new Agent( path, workBookName, sheetName, "Agent2", 1 );
			accobj.agentCreation();

		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 5, enabled = true, description = "Agent creation2", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void agentCreation2() throws Exception
	{
		try
		{
			Agent accobj = new Agent( path, workBookName, sheetName, "Agent3", 1 );
			accobj.agentCreation();

		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 6, enabled = true, description = "Agent with Parent Account creation1", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void agentparentCreation1() throws Exception
	{
		try
		{
			Agent accobj = new Agent( path, workBookName, sheetName, "ParentAgent1", 1 );
			accobj.agentCreation();
		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 7, description = "Agent deletion" )
	public void agentDelete() throws Exception
	{
		try
		{
			Agent accobj = new Agent( path, workBookName, sheetName, "AgentDelete", 1 );
			accobj.agentDelete();
		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 8, description = "Agent un delete" )
	public void agentUnDelete() throws Exception
	{
		try
		{
			Agent accobj = new Agent( path, workBookName, sheetName, "AgentUnDelete", 1 );
			accobj.agentUnDelete();
		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}

}
