package com.subex.rocps.automation.testcases.functionaltesting;

import org.testng.annotations.Test;

import com.subex.rocps.automation.helpers.application.bills.BillingGroupCode;
import com.subex.rocps.automation.helpers.application.bills.Bills;
import com.subex.rocps.automation.helpers.application.partnerConfiguration.Account;
import com.subex.rocps.automation.helpers.application.partnerConfiguration.Agent;
import com.subex.rocps.automation.helpers.application.partnerConfiguration.BillProfile;
import com.subex.rocps.automation.helpers.application.products.ProductArgumentTypeHelper;
import com.subex.rocps.automation.helpers.application.products.ProductBundlesHelper;
import com.subex.rocps.automation.helpers.application.products.ProductInstanceHelper;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;

import com.subex.automation.helpers.util.FailureHelper;

public class TCProductInstanceServer extends PSAcceptanceTest
{

	String path = System.getProperty( "user.dir" ) + "\\src\\main\\resources\\";

	String workBookName = "FunctionalTestCases.xlsx";
	String sheetName = "ProductInstanceServer";

	@Test( priority = 1, enabled = true, description = "Bill creation with Credit Product items mandatory" )
	public void createBillWithCreditItems() throws Exception
	{
		try
		{

			Bills billObjec = new Bills( path, workBookName, sheetName, "HotBillCreate_Statement_WithCreditItems_Mandatory" );
			billObjec.billAction();

		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}

	}

	@Test( priority = 2, enabled = true, description = "Bill creation with Charge Product items mandatory" )
	public void createBillWithChargeItems() throws Exception
	{
		try
		{

			Bills billObjec = new Bills( path, workBookName, sheetName, "HotBillCreate_InvoiceWithChargeItemsMandatory" );
			billObjec.billAction();
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}

	}

	@Test( priority = 3, enabled = true, description = "Bill creation with Credit, Charge Product items with optional" )
	public void createBillCreditChargeItems_Optional() throws Exception
	{
		try
		{

			Bills billObjec = new Bills( path, workBookName, sheetName, "HotBillCreate_InvoiceCreditChargeItems_Optional" );
			billObjec.billAction();

		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}

	}

	@Test( priority = 4, enabled = true, description = "Bill creation with Charge,Credit Product items with specify" )
	public void createBillWithChargeCreditItems_Specify() throws Exception
	{
		try
		{

			Bills billObjec = new Bills( path, workBookName, sheetName, "HotBillCreate_InvoiceWithChargeCreditItems_Specify" );
			billObjec.billAction();

		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

}
