package com.subex.rocps.automation.testcases.functionaltesting;

import com.subex.rocps.automation.helpers.application.accruals.AccountingPeriods;
import com.subex.rocps.automation.helpers.application.accruals.AccrualsModelling;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;

import com.subex.automation.helpers.util.FailureHelper;

public class TCAccountingPeriod extends PSAcceptanceTest
{
	String path = System.getProperty( "user.dir" ) + "\\src\\main\\resources\\";
	String workBookName = "FunctionalTestCases.xlsx";
	String sheetName = "AccountingPeriod";

	@org.testng.annotations.Test( priority = 1, description = "Accounting period view usage results action",retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void accountingPeriodViewUsageResult() throws Exception
	{
		try
		{
			AccountingPeriods accObj = new AccountingPeriods( path, workBookName, sheetName, "AccountingPeriodViewUSageResults", 1);
			accObj.viewUsageResults();
			
		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	} 
	
	@org.testng.annotations.Test( priority = 2, description = "Accounting period view Reference Table action" )
	public void accountingPeriodViewRefTable() throws Exception
	{
		try
		{
			AccountingPeriods accObj = new AccountingPeriods( path, workBookName, sheetName, "AccountingPeriodViewRefTab", 1);
			accObj.viewReferenceTables();
			
		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	} 

}
