package com.subex.rocps.automation.testcases.functionaltesting;

import com.subex.rocps.automation.helpers.application.accruals.AccountingPeriodDefinition;
import com.subex.rocps.automation.helpers.application.accruals.AccrualsModelling;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;

import com.subex.automation.helpers.util.FailureHelper;

public class TCAccountingPeriodDefinition extends PSAcceptanceTest
{

	String path = System.getProperty( "user.dir" ) + "\\src\\main\\resources\\";
	String workBookName = "FunctionalTestCases.xlsx";
	String sheetName = "AccountingPeriodDefn";

	@org.testng.annotations.Test( priority = 1, description = "Accounting Period Definition creation" )
	public void accountingPeriodDefnCreation() throws Exception
	{
		try
		{
			AccountingPeriodDefinition accobj = new AccountingPeriodDefinition( path, workBookName, sheetName, "APDCreation", 1 );
			accobj.configureAccountingPeriodDefinition();
		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	} 
	
	@org.testng.annotations.Test( priority = 2, description = "Accounting Period Definition Edit" )
	public void accountingPeriodDefnEdit() throws Exception
	{
		try
		{
			AccountingPeriodDefinition accobj = new AccountingPeriodDefinition( path, workBookName, sheetName, "APDEdit", 1 );
			accobj.accountingPeriodDefnEdit();
		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	} 
	
	@org.testng.annotations.Test( priority = 3, description = "Accounting Period Definition delete" )
	public void accountingPeriodDefnDelete() throws Exception
	{
		try
		{
			AccountingPeriodDefinition accobj = new AccountingPeriodDefinition( path, workBookName, sheetName, "APDDelete", 1 );
			accobj.accountingPeriodDefnDelete();
		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	} 
	@org.testng.annotations.Test( priority = 4, description = "Accounting Period Definition undelete" )
	public void accountingPeriodDefnUnDelete() throws Exception
	{
		try
		{
			AccountingPeriodDefinition accobj = new AccountingPeriodDefinition( path, workBookName, sheetName, "APDUnDelete", 1 );
			accobj.accountingPeriodDefnUnDelete();
		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	} 
	@org.testng.annotations.Test( priority = 5, description = "Accounting Period Definition sync action" )
	public void accountingPeriodDefnColVal() throws Exception
	{
		try
		{
			AccountingPeriodDefinition accobj = new AccountingPeriodDefinition( path, workBookName, sheetName, "APDsColVal", 1 );
			accobj.searchScreenColumnsValidation();
		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	} 
	
	@org.testng.annotations.Test( priority = 6, description = "Accounting Period Definition Edit" )
	public void accountingPeriodDefnSync() throws Exception
	{
		try
		{
			AccountingPeriodDefinition accobj = new AccountingPeriodDefinition( path, workBookName, sheetName, "APDSync", 1 );
			accobj.syncAccountingPeriods();
		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	} 
}
