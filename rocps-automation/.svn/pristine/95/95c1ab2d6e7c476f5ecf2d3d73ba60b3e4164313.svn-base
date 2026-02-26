package com.subex.rocps.automation.testcases.functionaltesting;

import org.testng.annotations.Test;

import com.subex.automation.helpers.application.screens.FileCollectionHelper;
import com.subex.automation.helpers.application.screens.FileSourceHelper;
import com.subex.automation.helpers.application.screens.TaskControllerHelper;
import com.subex.automation.helpers.util.FailureHelper;

import com.subex.rocps.automation.helpers.application.system.Streams;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;

public class TCBulkLoadPreRequists extends PSAcceptanceTest
{

	String path = System.getProperty( "user.dir" ) + "\\src\\main\\resources\\";
	String workBookName = "FunctionalTestCases.xlsx";
	String sheetName = "BulkLoadStreamPreReq";

	@Test( priority = 1, enabled = true, description = "Bulk loader Streams creation", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void createBulkLoaderStream() throws Exception
	{
		try
		{

			Streams streamObj = new Streams();
			streamObj.newStreamConfig( path, workBookName, sheetName, "BulkLoaderStreams", 1 );
			streamObj.bulkLoaderStreamNewConfig( path, workBookName, sheetName, "BulkLoaderStreamStage", 3 );
			streamObj.saveStreamDetail();
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

	@Test( priority = 2, enabled = true, description = "Bulk loader file source creation", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void bulkLoaderFileSource() throws Exception
	{
		try
		{
			FileSourceHelper fileSrcObj = new FileSourceHelper();
			fileSrcObj.createFileSource( path, workBookName, sheetName, "BulkLoaderFileSource", 1 );

		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

	@Test( priority = 3, enabled = true, description = "Bulk loader file collection creation", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void bulkLoaderFileCollection() throws Exception
	{
		try
		{
			FileCollectionHelper fileCollObj = new FileCollectionHelper();
			fileCollObj.createFileCollection( path, workBookName, sheetName, "BulkLoaderFileCollection", 1 );

		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}

	}

}
