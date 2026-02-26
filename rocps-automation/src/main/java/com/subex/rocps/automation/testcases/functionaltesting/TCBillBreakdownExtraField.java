package com.subex.rocps.automation.testcases.functionaltesting;

import com.subex.automation.helpers.util.FailureHelper;
import com.subex.rocps.automation.helpers.application.bills.BillBreakdownExtraField;

import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;

public class TCBillBreakdownExtraField extends PSAcceptanceTest
{

	String path = System.getProperty( "user.dir" ) + "\\src\\main\\resources\\";
	String workBookName = "FunctionalTestCases.xlsx";
	String sheetName = "BillBreakdownExtraField";

	@org.testng.annotations.Test( priority = 1, description = "bill BreakdownExtraField - Tariff Type creation", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void billBreakdownExtraFieldCreation() throws Exception
	{
		try
		{
			BillBreakdownExtraField billExtraObj = new BillBreakdownExtraField( path, workBookName, sheetName, "BillBreakdownExtraField", 1 );
			billExtraObj.billExtraFieldCreation();
		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 2, description = "bill BreakdownExtraField - SetupAmt creation", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void billBreakdownExtraFieldCreationSetupAmt() throws Exception
	{
		try
		{
			BillBreakdownExtraField billExtraObj = new BillBreakdownExtraField( path, workBookName, sheetName, "BillBreakdownExtraField SetupAmt", 1 );
			billExtraObj.billExtraFieldCreation();
		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 3, description = "bill BreakdownExtraField deletion", dependsOnMethods = "billBreakdownExtraFieldCreation" )
	public void billBreakdownExtraFieldDelete() throws Exception
	{
		try
		{
			BillBreakdownExtraField billExtraDelObj = new BillBreakdownExtraField( path, workBookName, sheetName, "BillBreakdownExtraFieldDelete", 1 );
			billExtraDelObj.billbreakdownExtraFieldDelete();
		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 4, description = "bill BreakdownExtraField un delete", dependsOnMethods = "billBreakdownExtraFieldDelete" )
	public void billBreakdownExtraFieldUnDelete() throws Exception
	{
		try
		{
			BillBreakdownExtraField billExtraUnDelObj = new BillBreakdownExtraField( path, workBookName, sheetName, "BillBreakdownExtraFieldUnDelete", 1 );
			billExtraUnDelObj.billbreakdownExtraFieldUnDelete();
		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 5, description = "bill BreakdownExtraField search screen column validation" )
	public void billBreakdownExtraFieldColVal() throws Exception
	{
		try
		{
			BillBreakdownExtraField billExtraColValObj = new BillBreakdownExtraField( path, workBookName, sheetName, "BillExtraFiledSearchScreencolVal", 1 );
			billExtraColValObj.searchScreenColumnsValidation();
		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 6, description = "bill BreakdownExtraField -Product Definition Argument creation", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void billBreakdownExtraFieldCreationProdDefArg() throws Exception
	{
		try
		{
			BillBreakdownExtraField billExtraObj = new BillBreakdownExtraField( path, workBookName, sheetName, "BillBreakdownExtraField_ProdDefnArg", 1 );
			billExtraObj.billExtraFieldCreation();
		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 7, description = "bill BreakdownExtraField - Product Instance Argument   creation", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void billBreakdownExtraFieldCreationProdInstArg() throws Exception
	{
		try
		{
			BillBreakdownExtraField billExtraObj = new BillBreakdownExtraField( path, workBookName, sheetName, "BillBreakdownExtraField SetupAmt_ProdInstArg", 1 );
			billExtraObj.billExtraFieldCreation();
		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 8, description = "Edit bill BreakdownExtraField " )
	public void editBillBreakdownExtraField() throws Exception
	{
		try
		{
			BillBreakdownExtraField billExtraObj = new BillBreakdownExtraField( path, workBookName, sheetName, "Edit BillBreakdownExtraField", 1 );
			billExtraObj.editBillExtraFieldCreation();
		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}

}
