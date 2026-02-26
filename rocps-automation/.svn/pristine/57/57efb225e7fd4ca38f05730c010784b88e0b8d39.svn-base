package com.subex.rocps.sprintTestCase.bklg77;

import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;

import com.subex.automation.helpers.util.FailureHelper;

public class TestCaseExec2 extends PSAcceptanceTest {
	
	@org.testng.annotations.Test()
	public void prePaid() throws Exception
	{
		String path = System.getProperty( "user.dir" ) + "\\src\\main\\resources\\";
		//String workBookName = "TestData.xlsx";
		
		try {
				PrePaidBalance preObj = new PrePaidBalance(path);
				preObj.prepaidBalance();
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
