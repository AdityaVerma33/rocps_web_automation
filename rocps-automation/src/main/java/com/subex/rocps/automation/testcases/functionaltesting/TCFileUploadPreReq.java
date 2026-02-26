package com.subex.rocps.automation.testcases.functionaltesting;

import org.testng.annotations.Test;

import com.subex.automation.helpers.application.screens.FileCollectionHelper;
import com.subex.automation.helpers.application.screens.FileSourceHelper;
import com.subex.automation.helpers.util.FailureHelper;
import com.subex.rocps.automation.helpers.application.fileUpload.FileUpload;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;

public class TCFileUploadPreReq extends PSAcceptanceTest
{
	String path = System.getProperty( "user.dir" ) + "\\src\\main\\resources\\";
	String workBookName = "FunctionalTestCases.xlsx";
	String sheetName = "FileUploadPreRequisites";

	@Test( priority = 1, enabled = true, description = "'File Upload - Creating File Source and File Collection", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void preRequisites() throws Exception
	{
		try
		{

			FileSourceHelper fileSrcObj = new FileSourceHelper();
			fileSrcObj.createFileSource( path, workBookName, sheetName, "FileUploadFileSource", 1 );
			FileCollectionHelper fileCollObj = new FileCollectionHelper();
			fileCollObj.createFileCollection( path, workBookName, sheetName, "FileUploadFileCollection", 1 );
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}
}
