package com.subex.rocps.sprintTestCase.bklg59;

import com.subex.automation.helpers.util.FailureHelper;
import com.subex.rocps.automation.helpers.application.partnerConfiguration.Account;
import com.subex.rocps.automation.helpers.application.partnerConfiguration.BillProfile;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;

public class TestBillProfileEmail extends PSAcceptanceTest
{
	String path = System.getProperty( "user.dir" ) + "\\src\\main\\resources\\";
	String workBookName = "PSSprintFunctional_ExcelData.xlsx";
	String sheetName = "BKLG59_BillProfileEmail";


	@org.testng.annotations.Test( priority = 1, description = "bill profile- Email", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void billProfileCreation() throws Exception
	{
		try
		{
			BillProfile billObj = new BillProfile( path, workBookName, sheetName, "BillProfile_Email", 1 );
			billObj.billProfileCreation();
		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}

}
