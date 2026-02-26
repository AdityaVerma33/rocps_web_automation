package com.subex.rocps.automation.testcases.functionaltesting;

import org.testng.annotations.Test;

import com.subex.rocps.automation.helpers.application.bills.Bills;
import com.subex.rocps.automation.helpers.application.referenceTable.ExtractFileLocation;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;

import com.subex.automation.helpers.util.FailureHelper;

public class TCExtractFlLocation extends PSAcceptanceTest
{
	String path = System.getProperty( "user.dir" ) + "\\src\\main\\resources\\";

	String workBookName = "FunctionalTestCases.xlsx";
	String sheetName = "ExtractFileLocation";

	@Test( priority = 1, enabled = true, description = "ExtractFileLocation creation" )
	public void createExtractFlLocation() throws Exception
	{
		try
		{

			ExtractFileLocation extractFileLocation=new ExtractFileLocation( path, workBookName, sheetName, "ExtractFlLocationCreation" );
			extractFileLocation.extractFlLocationCreation();
			

		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}

	}
}
