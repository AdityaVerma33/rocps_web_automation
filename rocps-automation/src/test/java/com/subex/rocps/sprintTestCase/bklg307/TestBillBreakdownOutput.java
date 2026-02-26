package com.subex.rocps.sprintTestCase.bklg307;

import org.testng.annotations.Test;

import com.subex.automation.helpers.util.FailureHelper;
import com.subex.rocps.automation.helpers.application.bills.BillBreakdownOutput;
import com.subex.rocps.automation.helpers.application.bills.BillDatasetSearch;
import com.subex.rocps.automation.helpers.application.bills.BillParameterSearch;
import com.subex.rocps.automation.helpers.application.bills.BillReportConfigurationHelper;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;

public class TestBillBreakdownOutput extends PSAcceptanceTest {
	String path = System.getProperty( "user.dir" ) + "\\src\\main\\resources\\";

	String workBookName = "PSSprintFunctional_ExcelData.xlsx";
	String sheetName = "BKLG307_BillBreakdownOutput";
	
	@Test( priority = 1, enabled = true, description = "' Bill Breakdown Output ' prerequisite of Bill Parameter, Bill Dataset, Bill Report Configuration", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void billBreakdownOutput_Prerequisite() throws Exception
	{
		try
		{
			BillParameterSearch billParameterSearch = new BillParameterSearch( path, workBookName, sheetName, "BillParameterCreation" );
			billParameterSearch.billParameterCreation();
			BillDatasetSearch billDatasetSearch = new BillDatasetSearch( path, workBookName, sheetName, "BillDatasetCreation" );
			billDatasetSearch.billDatasetCreation();
			BillReportConfigurationHelper billReportConfigurationHelper = new BillReportConfigurationHelper( path, workBookName, sheetName, "BillReportConfigurationCreation" );
			billReportConfigurationHelper.billReportConfCreation();
			
		}
			
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}
	
	@org.testng.annotations.Test( priority = 2, description = "Bill BreakDownOutput creation based on Component", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void billBreakdownOutputCreationComponent() throws Exception
	{
		try
		{
			BillBreakdownOutput billOutputObj = new BillBreakdownOutput( path, workBookName, sheetName, "BillBreakdownOutput_Component", 1 );
			billOutputObj.billOutputCreation();
		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}
	
	@org.testng.annotations.Test( priority = 3, description = "Bill BreakDownOutput creation based on Configuration", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void billBreakdownOutputCreationConfiguration() throws Exception
	{
		try
		{
			BillBreakdownOutput billOutputObj = new BillBreakdownOutput( path, workBookName, sheetName, "BillBreakdownOutput_GenerationBasedOn_Configuration", 1 );
			billOutputObj.billOutputCreation();
		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}

}
