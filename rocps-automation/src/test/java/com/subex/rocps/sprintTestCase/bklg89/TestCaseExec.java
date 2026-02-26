package com.subex.rocps.sprintTestCase.bklg89;

import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;

import com.subex.automation.helpers.util.FailureHelper;

public class TestCaseExec extends PSAcceptanceTest
{

	@org.testng.annotations.Test( priority = 1 )
	public void ciImportCreation() throws Exception
	{
		try
		{
			CarrierInvoiceImport ciObj = new CarrierInvoiceImport();
			ciObj.CIImportCreation();
		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}

	}

}
