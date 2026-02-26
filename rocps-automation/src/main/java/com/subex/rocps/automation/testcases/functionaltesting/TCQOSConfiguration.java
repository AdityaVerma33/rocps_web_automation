package com.subex.rocps.automation.testcases.functionaltesting;

import org.testng.annotations.Test;

import com.subex.automation.helpers.util.FailureHelper;
import com.subex.rocps.automation.helpers.application.quality.QOSConfiguration;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;

public class TCQOSConfiguration extends PSAcceptanceTest
{

	String path = System.getProperty( "user.dir" ) + "\\src\\main\\resources\\";

	String workBookName = "FunctionalTestCasesRO.xlsx";
	String sheetName = "QOSConfiguration";

	@Test( priority = 1, enabled = true, description = "' QOS Configuration'  verify the column Headers Of Search Screen" )
	public void verifyTheColHdrs() throws Exception
	{
		try
		{

			QOSConfiguration qosConfiguration = new QOSConfiguration( path, workBookName, sheetName, "QOSConfigurationScreencolVal" );
			qosConfiguration.qosConfigurationColumsValidation();

		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}
}
