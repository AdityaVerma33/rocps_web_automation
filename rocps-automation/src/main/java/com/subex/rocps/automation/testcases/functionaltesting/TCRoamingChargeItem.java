package com.subex.rocps.automation.testcases.functionaltesting;

import org.testng.annotations.Test;

import com.subex.rocps.automation.helpers.application.referenceTable.Currency;
import com.subex.rocps.automation.helpers.application.referenceTable.RoamingChargeItem;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;

import com.subex.automation.helpers.util.FailureHelper;

public class TCRoamingChargeItem extends PSAcceptanceTest
{

	String path = System.getProperty( "user.dir" ) + "\\src\\main\\resources\\";

	String workBookName = "FunctionalTestCases.xlsx";
	String sheetName = "RoamingChargeItem";

	@Test( priority = 1, enabled = true, description = "'Roaming Charged Item'  verify the column Headers Of Search Screen" )
	public void verifyTheColHdrs() throws Exception
	{
		try
		{
			RoamingChargeItem roamingChargeItem = new RoamingChargeItem( path, workBookName, sheetName, "RoamingChargeItemScreencolVal" );
			roamingChargeItem.roamingChgItemColumsValidation();
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

	@Test( priority = 2, enabled = true, description = "'Roaming Charged Item'  creation ",retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void roamingChargeItemCreation() throws Exception
	{
		try
		{
			RoamingChargeItem roamingChargeItem = new RoamingChargeItem( path, workBookName, sheetName, "TestRoamingChargeItemCreation" );
			roamingChargeItem.roamingChgItemCreation();
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

	@Test( priority = 3, enabled = true, description = "'Roaming Charged Item' edit" ,retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class)
	public void roamingChargeItemEdit() throws Exception
	{
		try
		{
			RoamingChargeItem roamingChargeItem = new RoamingChargeItem( path, workBookName, sheetName, "TestRoamingChargeItemEdit" );
			roamingChargeItem.roamingChgItemEdit();
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}
}
