package com.subex.rocps.sprintTestCase.bklg269;

import java.util.HashMap;


import com.subex.automation.helpers.util.FailureHelper;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;

public class TestCaseExec_bklg269 extends PSAcceptanceTest {


	
	@org.testng.annotations.Test( priority = 1, description = "Event match rule" )
	public void TCRoamingTaxType() throws Exception
	{
		String path = System.getProperty( "user.dir" ) + "\\src\\main\\resources\\";
		String workBookName = "TestData.xlsx";
		String sheetName = "EMR";
		try
		{
			BillProfileComponent rec = new BillProfileComponent( path, workBookName, sheetName, "EMR CI");
			rec.configureEventMatchRule();
		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}

	

}


