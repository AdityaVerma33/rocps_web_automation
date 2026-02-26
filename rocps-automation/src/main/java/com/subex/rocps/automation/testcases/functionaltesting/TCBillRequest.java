package com.subex.rocps.automation.testcases.functionaltesting;

import com.subex.rocps.automation.helpers.application.bills.BillRequest;
import com.subex.rocps.automation.helpers.application.bills.Bills;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;

import com.subex.automation.helpers.util.FailureHelper;

public class TCBillRequest extends PSAcceptanceTest
{
	String path = System.getProperty( "user.dir" ) + "\\src\\main\\resources\\";
	String workBookName = "FunctionalTestCases.xlsx";
	String sheetName = "Bill Request";

	/*@org.testng.annotations.Test( priority = 1, description = "TestBill Request - without billperiod creation" )
	public void createBillRequest() throws Exception
	{
		try
		{
	
			BillRequest billObj = new BillRequest( path, workBookName, sheetName, "TestBill Request" );
			billObj.billRequestCreation();
	
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}
	
	@org.testng.annotations.Test( priority = 2, description = "TestBill Request - with bill period creation" )
	public void createTestBillRequest() throws Exception
	{
		try
		{
	
			BillRequest billObj = new BillRequest( path, workBookName, sheetName, "TestBill withBillPeriod" );
			billObj.billRequestCreation();
	
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}
	
	@org.testng.annotations.Test( priority = 3, description = "TestBill Request creation" )
	public void createHotBillRequest() throws Exception
	{
		try
		{
	
			BillRequest billObj = new BillRequest( path, workBookName, sheetName, "HotBill Request" );
			billObj.billRequestCreation();
	
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}*/
	
	@org.testng.annotations.Test( priority = 3, description = "Bill REquest search screen column validation" )
	public void billrequest() throws Exception
	{
		try
		{
	
			BillRequest billObj = new BillRequest( path, workBookName, sheetName, "BillRequestSearchScreencolVal" );
			billObj.searchScreenColumnsValidation();
	
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}
}
