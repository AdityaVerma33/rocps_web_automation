package com.subex.rocps.automation.testcases.functionaltesting;

import com.subex.rocps.automation.helpers.application.referenceTable.DealTrafficTypeOrder;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;

import com.subex.automation.helpers.util.FailureHelper;

public class TCDealTrafficTypeOrder extends PSAcceptanceTest
{
	String path = System.getProperty( "user.dir" ) + "\\src\\main\\resources\\";
	String workBookName = "FunctionalTestingDeals.xlsx";
	String sheetName = "DealTrafficType";

	
	@org.testng.annotations.Test( priority = 1, description = "Deal Traffic Type order",retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void dealTrafficTypeOrderCreation() throws Exception
	{
		try
		{
			DealTrafficTypeOrder dealObj = new DealTrafficTypeOrder( path, workBookName, sheetName, "DealTypeOrder" );
			dealObj.dealTrafficTypeOrderCreation();
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}
}
