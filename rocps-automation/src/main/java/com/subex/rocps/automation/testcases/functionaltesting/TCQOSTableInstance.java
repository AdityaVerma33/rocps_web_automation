package com.subex.rocps.automation.testcases.functionaltesting;

import org.testng.annotations.Test;

import com.subex.automation.helpers.util.FailureHelper;
import com.subex.rocps.automation.helpers.application.quality.QOSTableInstance;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;

public class TCQOSTableInstance extends PSAcceptanceTest
{

	String path = System.getProperty( "user.dir" ) + "\\src\\main\\resources\\";

	String workBookName = "FunctionalTestCasesRO.xlsx";
	String sheetName = "QOSTableInstance";

	@Test( priority = 1, enabled = true, description = "'QOS TableInstance '  verify the column Headers Of Search Screen" )
	public void verifyTheColHdrs() throws Exception
	{
		try
		{
			QOSTableInstance qosTableInstance = new QOSTableInstance( path, workBookName, sheetName, "QOSTableInstanceScreencolVal" );
			qosTableInstance.qosTableInstanceColumsValidation();

		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}
}
