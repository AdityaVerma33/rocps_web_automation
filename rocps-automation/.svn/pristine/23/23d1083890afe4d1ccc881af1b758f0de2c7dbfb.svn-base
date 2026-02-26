package com.subex.rocps.automation.testcases.functionaltesting;

import org.testng.annotations.Test;

import com.subex.rocps.automation.helpers.application.roaming.HURFiles;
import com.subex.rocps.automation.helpers.application.roaming.RoamingConfiguration;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;

import com.subex.automation.helpers.util.FailureHelper;

public class TCHURFiles extends PSAcceptanceTest
{

	String path = System.getProperty( "user.dir" ) + "\\src\\main\\resources\\";

	String workBookName = "FunctionalTestCases.xlsx";
	String sheetName = "HURFiles";

	@Test( priority = 1, enabled = true, description = "'HUR Files'  verify the column Headers Of Search Screen" )
	public void verifyTheColHdrs() throws Exception
	{
		try
		{
			HURFiles hurFiles = new HURFiles( path, workBookName, sheetName, "HURFilesScreencolVal" );
			hurFiles.hurFilesColumsValidation();
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

	//@Test( priority = 2, enabled = true, description = "'HUR Files'  validate search result" )
	public void hURFilesValidateSearchResult() throws Exception
	{
		try
		{
			HURFiles hurFiles = new HURFiles( path, workBookName, sheetName, "HURFilesValidateSearchResult" );
			hurFiles.validateHurFilesSearchResult();
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

	//@Test( priority = 3, enabled = true, description = "'HUR Files'  validate 'Email HUR' action" )
	public void hURFilesEmailHURAction() throws Exception
	{
		try
		{
			HURFiles hurFiles = new HURFiles( path, workBookName, sheetName, "HURFilesEmailHurAction" );
			hurFiles.hurFilesStatusEmailHURAction();
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}
}
