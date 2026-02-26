package com.subex.rocps.automation.testcases.functionaltesting;

import com.subex.rocps.automation.helpers.application.carrierinvoice.FieldMapping;
import com.subex.rocps.automation.helpers.application.carrierinvoice.SystemFieldList;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;

import com.subex.automation.helpers.util.FailureHelper;

public class TCFieldMapping extends PSAcceptanceTest
{

	String path = System.getProperty( "user.dir" ) + "\\src\\main\\resources\\";
	String workBookName = "FunctionalTestCases.xlsx";
	String sheetName = "FieldMapping";
	
	@org.testng.annotations.Test( priority = 1, description = "Field Mapping Creation" )
	public void fieldMapping() throws Exception
	{
		try
		{
			FieldMapping fieldMapObj = new FieldMapping( path, workBookName, sheetName, "FieldMapping" );
			fieldMapObj.configureFieldMapping();
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}
}
