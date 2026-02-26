package com.subex.rocps.sprintTestCase.bklg76;

import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;

import com.subex.automation.helpers.util.FailureHelper;

public class TestProductBundleDrillDown extends PSAcceptanceTest {
	
	@org.testng.annotations.Test()
	public void productBundleDrilldown() throws Exception
	{
		String path = System.getProperty( "user.dir" ) + "\\src\\main\\resources\\";
		//String workBookName = "TestData.xlsx";
		
		try {
			ProductBundleDrillDown preObj = new ProductBundleDrillDown(path);
				preObj.checkRecurring();
				preObj.checkOneOff();
		} catch (Exception e) {
			FailureHelper.reportFailure(e);
			throw e;
		}
		
		
	}
	
	/*@org.testng.annotations.Test(priority=2)
	public void Test() throws Exception
	{
		//URL path = this.getClass().getResource("//src//main//resources");
		String path = System.getProperty( "user.dir" ) + "\\src\\main\\resources\\";
		String workBookName = "TestData.xlsx";
		
		try {
				BillProfile bipObj = new BillProfile(path, workBookName, "Sheet1", "BillProfile");
				bipObj.billProfileCreation();
				
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
		
		
	}
*/
}
