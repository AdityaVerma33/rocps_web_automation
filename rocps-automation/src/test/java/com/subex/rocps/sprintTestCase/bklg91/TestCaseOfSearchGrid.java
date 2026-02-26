package com.subex.rocps.sprintTestCase.bklg91;

import com.subex.automation.helpers.util.FailureHelper;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;
public class TestCaseOfSearchGrid extends PSAcceptanceTest  {
	
	@org.testng.annotations.Test(priority=1)
	public void newImport() throws Exception{
		String path = System.getProperty( "user.dir" ) + "\\src\\main\\resources\\";
		String workBookName = "TestData.xlsx";
		
		try 
		{
			CarrierInvoiceSearchGrid importObj = new CarrierInvoiceSearchGrid(path, workBookName, "Sheet1", "Account");
			importObj.newImport();
		}
		catch (Exception e) 
		{
			FailureHelper.reportFailure(e);
			throw e;
		}
		
	}
}