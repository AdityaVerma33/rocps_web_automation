package com.subex.rocps.automation.testcases.functionaltesting;

import org.testng.annotations.Test;

import com.subex.automation.helpers.util.FailureHelper;

import com.subex.rocps.automation.helpers.application.referenceTable.UploadFileType;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;

public class TCUploadFileType extends PSAcceptanceTest
{
	String path = System.getProperty( "user.dir" ) + "\\src\\main\\resources\\";

	String workBookName = "FunctionalTestCases.xlsx";
	String sheetName = "UploadFileType";

	@Test( priority = 1, enabled = true, description = "'Upload File Type'  verify the column Headers Of Search Screen" )
	public void verifyTheColHdrs() throws Exception
	{
		try
		{
			UploadFileType uploadFileType = new UploadFileType( path, workBookName, sheetName, "UploadFileTypeScreencolVal" );
			uploadFileType.uploadFileTypeColumsValidation();

		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

	@Test( priority = 2, enabled = true, description = "'Upload File Type'  creation", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void uploadFileTypeCreation() throws Exception
	{
		try
		{
			UploadFileType uploadFileType = new UploadFileType( path, workBookName, sheetName, "UploadFileTypeCreation" );
			uploadFileType.uploadFileTypeCreation();

		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

}
