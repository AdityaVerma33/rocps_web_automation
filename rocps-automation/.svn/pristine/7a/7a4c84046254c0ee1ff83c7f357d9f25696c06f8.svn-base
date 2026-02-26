package com.subex.rocps.automation.testcases.functionaltesting;

import org.testng.annotations.Test;

import com.subex.automation.helpers.util.FailureHelper;
import com.subex.rocps.automation.helpers.application.bills.BillParameterSearch;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;

public class TCBillParameter extends PSAcceptanceTest
{
	String path = System.getProperty( "user.dir" ) + "\\src\\main\\resources\\";

	String workBookName = "FunctionalTestCases.xlsx";
	String sheetName = "BillParameter";

	@Test( priority = 1, enabled = true, description = "' Bill Parameter'  verify the column Headers Of Search Screen" )
	public void verifyTheColHdrs() throws Exception
	{
		try
		{
			BillParameterSearch billParameterSearch = new BillParameterSearch( path, workBookName, sheetName, "BillParameterScreenColumns" );
			billParameterSearch.billParameterColumsValidation();

		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

	@Test( priority = 2, enabled = true, description = "' Bill Parameter'  creation constant source type" , retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void billParameterCreationConstantSrcType() throws Exception
	{
		try
		{
			BillParameterSearch billParameterSearch = new BillParameterSearch( path, workBookName, sheetName, "BillParameterCreation_ConstantSoucreType" );
			billParameterSearch.billParameterCreation();

		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

	@Test( priority = 3, enabled = true, description = "' Bill Parameter'  creation table source type", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class  )
	public void billParameterCreationTableSrcType() throws Exception
	{
		try
		{
			BillParameterSearch billParameterSearch = new BillParameterSearch( path, workBookName, sheetName, "BillParameterCreation_TableSoucreType" );
			billParameterSearch.billParameterCreation();

		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

	@Test( priority = 4, enabled = true, description = "' Bill Parameter'  delete" )
	public void billParameterDelete() throws Exception
	{
		try
		{
			BillParameterSearch billParameterSearch = new BillParameterSearch( path, workBookName, sheetName, "BillParameterDeletion" );
			billParameterSearch.deleteBillParameter();

		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

	@Test( priority = 5, enabled = true, description = "' Bill Parameter'  undelete" )
	public void billParameterunDelete() throws Exception
	{
		try
		{
			BillParameterSearch billParameterSearch = new BillParameterSearch( path, workBookName, sheetName, "BillParameterUnDeletion" );
			billParameterSearch.unDeleteBillParameter();

		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}
}
