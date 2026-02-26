package com.subex.rocps.automation.testcases.functionaltesting;

import org.testng.annotations.Test;

import com.subex.automation.helpers.util.FailureHelper;
import com.subex.rocps.automation.helpers.application.referenceTable.FileUploadCategory;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;

public class TCFileUploadCategory extends PSAcceptanceTest
{

	String path = System.getProperty( "user.dir" ) + "\\src\\main\\resources\\";

	String workBookName = "FunctionalTestCases.xlsx";
	String sheetName = "FileUploadCategory";

	@Test( priority = 1, enabled = true, description = "'File Upload Category'  verify the column Headers Of Search Screen" )
	public void verifyTheColHdrs() throws Exception
	{
		try
		{
			FileUploadCategory fileUploadCategory = new FileUploadCategory( path, workBookName, sheetName, "FileUploadCategoryScreencolVal" );
			fileUploadCategory.fileUploadCategoryColumsValidation();

		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

	@Test( priority = 2, enabled = true, description = "'File Upload Category'  creation", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void fileUploadCategoryCreation() throws Exception
	{
		try
		{
			FileUploadCategory fileUploadCategory = new FileUploadCategory( path, workBookName, sheetName, "FileUploadCategoryCreation" );
			fileUploadCategory.fileUploadCategoryCreation();

		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}
}
