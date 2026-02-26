package com.subex.rocps.sprintTestCase.bklg244;

import org.testng.annotations.Test;

import com.subex.automation.helpers.util.FailureHelper;
import com.subex.rocps.automation.helpers.application.xdrextraction.XdrExtrTempHelper;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;

public class TestXDRExtractionTemp extends PSAcceptanceTest
{
	String path = System.getProperty( "user.dir" ) + "\\src\\main\\resources\\";
	String workBookName = "PSSprintFunctional_ExcelData.xlsx";
	String sheetName = "BKLG244_XDRExtraction";
	
	@Test( priority = 1, enabled = true, description = "XDR Extraction Template creation", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void createXdrExtTempWithoutBill() throws Exception
	{
		try
		{
			XdrExtrTempHelper xdrExtrTempHelper = new XdrExtrTempHelper( path, workBookName, sheetName, "XdrExtCreationWithoutBill" );
			xdrExtrTempHelper.xdrExtTemplCreation();

		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

}
