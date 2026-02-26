package com.subex.rocps.automation.testcases.functionaltesting;

import org.testng.annotations.Test;

import com.subex.automation.helpers.util.FailureHelper;
import com.subex.rocps.automation.helpers.application.bills.BillDatasetSearch;
import com.subex.rocps.automation.helpers.application.bills.BillParameterSearch;
import com.subex.rocps.automation.helpers.application.bills.BillReportConfigurationHelper;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;

public class TCBillReportConfiguration extends PSAcceptanceTest
{

	String path = System.getProperty( "user.dir" ) + "\\src\\main\\resources\\";

	String workBookName = "FunctionalTestCases.xlsx";
	String sheetName = "BillReportConfiguration";

	@Test( priority = 1, enabled = true, description = "' Bill Report Configuration ' prerequisite of Bill Parameter, Bill Dataset", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void billReportConfigCreation_Prerequisite() throws Exception
	{
		try
		{
			BillParameterSearch billParameterSearch = new BillParameterSearch( path, workBookName, "BillParameter", "BillParameterCreation_ConstantSoucreType" );
			billParameterSearch.billParameterCreation();
			BillDatasetSearch billDatasetSearch = new BillDatasetSearch( path, workBookName, "BillDataset", "BillDatasetCreation_TableSoucreType" );
			billDatasetSearch.billDatasetCreation();
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

	@Test( priority = 2, enabled = true, description = "' Bill Report Configuration '  verify the column Headers Of Search Screen" )
	public void verifyTheColHdrs() throws Exception
	{
		try
		{
			BillReportConfigurationHelper billReportConfigurationHelper = new BillReportConfigurationHelper( path, workBookName, sheetName, "BillReportConfigurationScreenColumns" );
			billReportConfigurationHelper.billReportConfColumsValidation();
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

	@Test( priority = 3, enabled = true, description = "' Bill Report Configuration ' creation with Single BillPM, BillDs", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void billReportConfigCreation_SingleBillPMBillDS() throws Exception
	{
		try
		{
			BillReportConfigurationHelper billReportConfigurationHelper = new BillReportConfigurationHelper( path, workBookName, sheetName, "BillReportConfigurationCreation_withSingleBillPmBillDS" );
			billReportConfigurationHelper.billReportConfCreation();
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

	@Test( priority = 4, enabled = true, description = "' Bill Report Configuration ' creation with Multiple BillPm, BillDs", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void billReportConfigCreation_MultipleeBillPMBillDS() throws Exception
	{
		try
		{
			BillReportConfigurationHelper billReportConfigurationHelper = new BillReportConfigurationHelper( path, workBookName, sheetName, "BillReportConfigurationCreation_withMultipleBillPmBillDS" );
			billReportConfigurationHelper.billReportConfCreation();
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

	@Test( priority = 5, enabled = true, description = "' Bill Report Configuration ' delete" )
	public void billReportConfigDelete() throws Exception
	{
		try
		{
			BillReportConfigurationHelper billReportConfigurationHelper = new BillReportConfigurationHelper( path, workBookName, sheetName, "BillReportConfigurationDelete" );
			billReportConfigurationHelper.billReportConfigurationDelete();
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

	@Test( priority = 6, enabled = true, description = "' Bill Report Configuration ' Undelete" )
	public void billReportConfigUnDelete() throws Exception
	{
		try
		{
			BillReportConfigurationHelper billReportConfigurationHelper = new BillReportConfigurationHelper( path, workBookName, sheetName, "BillReportConfigurationUnDelete" );
			billReportConfigurationHelper.billReportConfigurationUnDelete();
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}
}
