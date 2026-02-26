package com.subex.rocps.automation.testcases.functionaltesting;

import com.subex.rocps.automation.helpers.application.bills.Bills;
import com.subex.rocps.automation.helpers.application.bills.TestBill;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;

import com.subex.automation.helpers.util.FailureHelper;

public class TCTestBill extends PSAcceptanceTest
{
	String path = System.getProperty( "user.dir" ) + "\\src\\main\\resources\\";
	String workBookName = "FunctionalTestCases.xlsx";
	String sheetName = "TestBill";

	@org.testng.annotations.Test( priority = 1, description = "TestBill creation-NonDelta" )
	public void createTestBillNonDelta() throws Exception
	{
		try
		{
	
			TestBill testBillObj = new TestBill( path, workBookName, sheetName, "TestBill NonDelta" );
		testBillObj.testBillCreation();
	
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}
	
	@org.testng.annotations.Test( priority = 2, description = "TestBill - creation Delta" )
	public void createTestBillDelta() throws Exception
	{
		try
		{
	
			TestBill testBillObj = new TestBill( path, workBookName, sheetName, "TestBill Delta" );
			testBillObj.testBillCreation();
	
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}
	
	/*@org.testng.annotations.Test( priority = 3, description = "TestBill Outgoing creation" )
	public void createTestBillOutgoing() throws Exception
	{
		try
		{
	
			TestBill testBillObj = new TestBill( path, workBookName, sheetName, "TestBill Outgoing" );
			testBillObj.testBillCreation();
	
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}
	*/
	

	@org.testng.annotations.Test( priority = 4, description = "Test Bill Screen screen Column validation" )
	public void testBillColVal() throws Exception
	{
		try
		{
	
			TestBill testBillObj = new TestBill( path, workBookName, sheetName, "TestbillSearchScreencolVal" );
			testBillObj.searchScreenColumnsValidation();
	
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}
}
