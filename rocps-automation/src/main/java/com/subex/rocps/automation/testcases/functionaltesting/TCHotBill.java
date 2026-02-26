package com.subex.rocps.automation.testcases.functionaltesting;

import com.subex.rocps.automation.helpers.application.bills.Bills;
import com.subex.rocps.automation.helpers.application.bills.TestBill;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;

import com.subex.automation.helpers.util.FailureHelper;

public class TCHotBill extends PSAcceptanceTest
{
	String path = System.getProperty( "user.dir" ) + "\\src\\main\\resources\\";
	String workBookName = "FunctionalTestCases.xlsx";
	String sheetName = "HotBill";

	@org.testng.annotations.Test( priority = 1, description = "HotBill creation-Delta" )
	public void createBill() throws Exception
	{
		try
		{
	
			Bills billObj = new Bills( path, workBookName, sheetName, "HotBill Delta" );
			billObj.billAction();
	
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}
	
	@org.testng.annotations.Test( priority = 2, description = "HotBill creation-NonDelta" )
	public void createBillNonDelta() throws Exception
	{
		try
		{
	
			Bills billObj = new Bills( path, workBookName, sheetName, "HotBill NonDelta" );
			billObj.billAction();
	
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}
	
	/*	@org.testng.annotations.Test( priority = 3, description = "HotBill TransitOut creation" )
		public void createBillTransitOut() throws Exception
		{
			try
			{
		
				Bills billObj = new Bills( path, workBookName, sheetName, "HotBill TransitOut" );
				billObj.billAction();
		
			}
			catch ( Exception e )
			{
				FailureHelper.setErrorMessage( e );
				throw e;
			}
		}*/
	
	@org.testng.annotations.Test( priority = 4, description = "HotBill screen screen column validation" )
	public void billColVal() throws Exception
	{
		try
		{
	
			Bills billObj = new Bills( path, workBookName, sheetName, "BillSearchScreencolVal" );
			billObj.searchScreenColumnsValidation();
	
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}
	
	@org.testng.annotations.Test( priority = 5, description = "Bill Adjustments Search screen column validation" )
	public void billAdjustmentsColVal() throws Exception
	{
		try
		{
	
			Bills billObj = new Bills( path, workBookName, sheetName, "BillAdjustmentSearchScreencolVal" );
			billObj.billAdjustmentSearchScreenColVal();
	
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}
	
}
