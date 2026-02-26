package com.subex.rocps.sprintTestCase.bklg47;

import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;

import com.subex.automation.helpers.util.FailureHelper;

public class TestCaseOfImport extends PSAcceptanceTest  {
	
	@org.testng.annotations.Test(priority=1)
	public void newImport() throws Exception{
		String path = System.getProperty( "user.dir" ) + "\\src\\main\\resources\\";
		String workBookName = "TestData.xlsx";
		
		try 
		{
			NewCarrierInvoiceImport importObj = new NewCarrierInvoiceImport(path, workBookName, "Sheet1", "Account");
			importObj.newImport();
		}
		catch (Exception e) 
		{
			FailureHelper.reportFailure(e);
			throw e;
		}
		
	}
}
