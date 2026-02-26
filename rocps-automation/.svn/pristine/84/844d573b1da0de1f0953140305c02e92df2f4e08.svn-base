package com.subex.rocps.automation.testcases.systemtesting;

import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;

import com.subex.automation.helpers.application.ControllerHelper;
import com.subex.automation.helpers.data.ValidationHelper;
import com.subex.automation.helpers.util.FailureHelper;

public class ControllerStop extends PSAcceptanceTest
{
	@org.testng.annotations.Test( priority = 1, description = "Stop SC and TC ", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void stopTC() throws Exception
	{
		try
		{
			String tcName = configProp.getProperty( "taskControllerName" );
			ControllerHelper scObj = new ControllerHelper();
			scObj.stopTaskController( tcName );

		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 2, description = "Stop SC and TC ", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void stopSC() throws Exception
	{
		try
		{

			ControllerHelper scObj = new ControllerHelper();
			scObj.stopStreamController();

		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

}
