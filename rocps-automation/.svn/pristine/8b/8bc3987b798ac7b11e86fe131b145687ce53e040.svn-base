package com.subex.rocps.sprintTestCase.bklg356;

import com.subex.automation.helpers.util.FailureHelper;
import com.subex.rocps.automation.helpers.application.bills.SalesTaxRate;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;

public class TestSalesTaxRate extends PSAcceptanceTest {
	String path = System.getProperty( "user.dir" ) + "\\src\\main\\resources\\";
	String workBookName = "PSSprintFunctional_ExcelData.xlsx";
	String sheetName = "BKLG356_SalesTaxRate";

	@org.testng.annotations.Test( priority = 1, description = "SalesTax Rate Valid On Filter", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void salesTaxRateValidOnFilter() throws Exception
	{
		try
		{
			SalesTaxRate salesTaxObj = new SalesTaxRate( path, workBookName, sheetName, "SalesTaxRate_ValidOnFilter", 1 );
			salesTaxObj.salesTaxRateValidOnFilterVerify();
		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}

}
