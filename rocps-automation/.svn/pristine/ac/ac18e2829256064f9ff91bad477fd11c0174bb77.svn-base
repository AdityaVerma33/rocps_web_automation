package com.subex.rocps.automation.testcases.functionaltesting;

import com.subex.rocps.automation.helpers.application.networkConfiguraiton.Switch;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;

import com.subex.automation.helpers.util.FailureHelper;

public class TCSwitchAction extends PSAcceptanceTest
{
	String path = System.getProperty( "user.dir" ) + "\\src\\main\\resources\\";
	String workBookName = "FunctionalTestCases.xlsx";
	String sheetName = "Switch";

	@org.testng.annotations.Test( priority = 1, description = "switch Map Tairff" )
	public void switchTariffMapping() throws Exception
	{
		try
		{
			Switch switchColValObj = new Switch( path, workBookName, sheetName, "SwitchMapTariff" );
			switchColValObj.switchTariffMapping();
		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}
}
