package com.subex.rocps.automation.testcases.functionaltesting;

import org.testng.annotations.Test;

import com.subex.automation.helpers.util.FailureHelper;
import com.subex.rocps.automation.helpers.application.referenceTable.CategoryFlCollectMapping;
import com.subex.rocps.automation.helpers.application.referenceTable.FileUploadCategory;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;

public class TCCategoryFlCollMapping extends PSAcceptanceTest
{
	String path = System.getProperty( "user.dir" ) + "\\src\\main\\resources\\";

	String workBookName = "FunctionalTestCases.xlsx";
	String sheetName = "CategoryFileCollectionMapping";

	@Test( priority = 1, enabled = true, description = "'Category File Collection Mapping'  prerequiste creation of file upload category", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void fileUploadCategoryPerequsite() throws Exception
	{
		try
		{
			FileUploadCategory fileUploadCategory = new FileUploadCategory( path, workBookName, "FileUploadCategory", "FileUploadCategoryCreation" );
			fileUploadCategory.fileUploadCategoryCreation();

		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

	@Test( priority = 2, enabled = true, description = "'Category File Collection Mapping'  verify the column Headers Of Search Screen" )
	public void verifyTheColHdrs() throws Exception
	{
		try
		{
			CategoryFlCollectMapping categoryFlCollectMapping = new CategoryFlCollectMapping( path, workBookName, sheetName, "CategoryFileCollectionMappingScreencolVal" );
			categoryFlCollectMapping.categoryFlCollMappingColumsValidation();

		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

	@Test( priority = 3, enabled = true, description = "'Category File Collection Mapping'  creation", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void categoryFileCollectionMappingCreation() throws Exception
	{
		try
		{
			CategoryFlCollectMapping categoryFlCollectMapping = new CategoryFlCollectMapping( path, workBookName, sheetName, "CategoryFileCollectionMappingCreation" );
			categoryFlCollectMapping.categoryFlCollMappingCreation();

		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

}
