package com.subex.rocps.automation.testcases.functionaltesting;

import com.subex.rocps.automation.helpers.application.partnerConfiguration.Account;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;

import com.subex.automation.helpers.util.FailureHelper;

public class TCAccountAction extends PSAcceptanceTest
{

	String path = System.getProperty( "user.dir" ) + "\\src\\main\\resources\\";
	String workBookName = "FunctionalTestCases.xlsx";
	String sheetName = "Account";
	
	@org.testng.annotations.Test( priority = 1, description = "account - operator creation" )
	public void accountOperatorCreation() throws Exception
	{
		try
		{
			Account accobj = new Account( path, workBookName, sheetName, "AccountOperator", 1 );
			accobj.navigateToOperator();
		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}
	
	@org.testng.annotations.Test( priority = 2, description = "account - View Aggregation Reuslts" )
	public void accountViewaggrResults() throws Exception
	{
		try
		{
			Account accobj = new Account( path, workBookName, sheetName, "AggregationResult IncomingAndTransitIn", 1 );
			accobj.navigateToViewAggregationResults();
		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}
	
	


	/*@org.testng.annotations.Test( priority = 3, description = "account - Termination" )
	public void accountTermination() throws Exception
	{
		try
		{
			Account accobj = new Account( path, workBookName, sheetName, "AccountChangeStatus", 1 );
			accobj.accountChangeStatus();
		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}*/
	
	@org.testng.annotations.Test( priority = 4, description = "account - view Product" )
	public void accountViewProduct() throws Exception
	{
		try
		{
			Account accobj = new Account( path, workBookName, sheetName, "AccountViewProducts", 1 );
			accobj.viewProducts();
			}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}
	
	@org.testng.annotations.Test( priority = 5, description = "account - View EMR" )
	public void accountviewEMR() throws Exception
	{
		try
		{
			Account accobj = new Account( path, workBookName, sheetName, "AccountViewEMR", 1 );
			accobj.viewEventMatchRules();
			}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}
	@org.testng.annotations.Test( priority = 6, description = "account - view Event Usage" )
	public void accountViewEventUsage() throws Exception
	{
		try
		{
			Account accobj = new Account( path, workBookName, sheetName, "AccountViewEventUsage", 1 );
			accobj.viewEventUsage();
		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}
	
	/*@org.testng.annotations.Test( priority = 7, description = "account - view Event Usage" )
	public void accountExport() throws Exception
	{
		try
		{
			Account accobj = new Account( path, workBookName, sheetName, "AccountExport", 1 );
			accobj.exportAccount();
		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}*/
}
