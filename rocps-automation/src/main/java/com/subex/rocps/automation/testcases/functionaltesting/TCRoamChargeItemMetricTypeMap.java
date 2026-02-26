package com.subex.rocps.automation.testcases.functionaltesting;

import org.testng.annotations.Test;

import com.subex.rocps.automation.helpers.application.referenceTable.Currency;
import com.subex.rocps.automation.helpers.application.referenceTable.RoamingChargeItemMetricType;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;

import com.subex.automation.helpers.util.FailureHelper;

public class TCRoamChargeItemMetricTypeMap extends PSAcceptanceTest
{

	String path = System.getProperty( "user.dir" ) + "\\src\\main\\resources\\";

	String workBookName = "FunctionalTestCases.xlsx";
	String sheetName = "RoamingChargeItemMetricMap";

	@Test( priority = 1, enabled = true, description = "'Roaming Charge Item Metric Type Map'  verify the column Headers Of Search Screen" )
	public void verifyTheColHdrs() throws Exception
	{
		try
		{
			RoamingChargeItemMetricType roamChargeItemMetricType = new RoamingChargeItemMetricType( path, workBookName, sheetName, "RoamChgItemMetTypeScreencolVal" );
			roamChargeItemMetricType.roamChgItemMetTypeColumsValidation();

		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}
	@Test( priority = 2, enabled = true, description = "'Roaming Charge Item Metric Type Map'  creation" )
	public void roamingChargeItemMetricMapCreation() throws Exception
	{
		try
		{
			RoamingChargeItemMetricType roamChargeItemMetricType = new RoamingChargeItemMetricType( path, workBookName, sheetName, "TestRoamChgItemMetTypeCreation" );
			roamChargeItemMetricType.roamChgItemMetTypeCreation();

		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}
	@Test( priority = 3, enabled = true, description = "'Roaming Charge Item Metric Type Map'  edit" )
	public void roamingChargeItemMetricMapEdit() throws Exception
	{
		try
		{
			RoamingChargeItemMetricType roamChargeItemMetricType = new RoamingChargeItemMetricType( path, workBookName, sheetName, "TestRoamChgItemMetTypeEdit" );
			roamChargeItemMetricType.roamChgItemMetTypeEdit();

		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}
	
}
