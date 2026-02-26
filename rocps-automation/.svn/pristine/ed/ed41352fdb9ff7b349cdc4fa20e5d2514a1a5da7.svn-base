package com.subex.rocps.automation.testcases.functionaltesting;

import org.testng.annotations.Test;

import com.subex.automation.helpers.util.FailureHelper;
import com.subex.rocps.automation.helpers.application.bills.BillDatasetSearch;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;

public class TCBillDataset extends PSAcceptanceTest
{
	String path = System.getProperty( "user.dir" ) + "\\src\\main\\resources\\";

	String workBookName = "FunctionalTestCases.xlsx";
	String sheetName = "BillDataset";

	@Test( priority = 1, enabled = true, description = "' Bill Dataset'  verify the column Headers Of Search Screen" )
	public void verifyTheColHdrs() throws Exception
	{
		try
		{
			BillDatasetSearch billDatasetSearch = new BillDatasetSearch( path, workBookName, sheetName, "BillDatasetScreenColumns" );
			billDatasetSearch.billDatasetColumsValidation();

		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

	@Test( priority = 2, enabled = true, description = "' Bill Dataset' creation", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class  )
	public void billDatasetCreation() throws Exception
	{
		try
		{
			BillDatasetSearch billDatasetSearch = new BillDatasetSearch( path, workBookName, sheetName, "BillDatasetCreation_TableSoucreType" );
			billDatasetSearch.billDatasetCreation();

		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

	@Test( priority = 3, enabled = true, description = "' Bill Dataset' delete" )
	public void billDatasetDelete() throws Exception
	{
		try
		{
			BillDatasetSearch billDatasetSearch = new BillDatasetSearch( path, workBookName, sheetName, "BillDatasetDeletion" );
			billDatasetSearch.deleteBillDataset();

		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

	@Test( priority = 4, enabled = true, description = "' Bill Dataset' undelete" )
	public void billDatasetunDelete() throws Exception
	{
		try
		{
			BillDatasetSearch billDatasetSearch = new BillDatasetSearch( path, workBookName, sheetName, "BillDatasetUnDeletion" );
			billDatasetSearch.unDeleteBillDataset();

		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}
}
