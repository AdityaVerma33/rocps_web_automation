package com.subex.rocps.automation.testcases.functionaltesting;

import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;

import com.subex.automation.helpers.application.screens.ElementCreateHelper;
import com.subex.automation.helpers.util.FailureHelper;

public class TCElements extends PSAcceptanceTest
{
	String path = System.getProperty( "user.dir" ) + "\\src\\main\\resources\\";
	String workBookName = "FunctionalTestCases.xlsx";
	String sheetName = "Elements";

	@org.testng.annotations.Test( priority = 1, description = "element creation for Transit", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void createElementTransit() throws Exception
	{
		try
		{
			ElementCreateHelper eleObj = new ElementCreateHelper();
			eleObj.createElement( path, workBookName, sheetName, "Elements Transit", 1 );
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 2, description = "element creation for Incoming", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void createElementIncoming() throws Exception
	{
		try
		{
			ElementCreateHelper eleObj = new ElementCreateHelper();
			eleObj.createElement( path, workBookName, sheetName, "Elements Incoming", 1 );
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 3, description = "element creation for FreePhone", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void createElementFreePhone() throws Exception
	{
		try
		{
			ElementCreateHelper eleObj = new ElementCreateHelper();
			eleObj.createElement( path, workBookName, sheetName, "Elements FreePhone", 1 );
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 4, description = "element creation for Outgoing", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void createElementOutgoing() throws Exception
	{
		try
		{
			ElementCreateHelper eleObj = new ElementCreateHelper();
			eleObj.createElement( path, workBookName, sheetName, "Elements Outgoing", 1 );
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 5, description = "element creation for Advance Rating", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void createElementAdvanceRating() throws Exception
	{
		try
		{
			ElementCreateHelper eleObj = new ElementCreateHelper();
			eleObj.createElement( path, workBookName, sheetName, "Elements AdvanceRating", 1 );
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

}
