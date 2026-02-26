package com.subex.rocps.automation.testcases.functionaltesting;

import org.testng.annotations.Test;

import com.subex.automation.helpers.util.FailureHelper;
import com.subex.rocps.automation.helpers.application.fileUpload.FileUpload;
import com.subex.rocps.automation.helpers.application.monitoring.PSCollectedFileSearchHelper;
import com.subex.rocps.automation.helpers.application.referenceTable.FileUploadCategory;
import com.subex.rocps.automation.helpers.application.system.TaskSchedule;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;

public class TCFileUpload extends PSAcceptanceTest
{

	String path = System.getProperty( "user.dir" ) + "\\src\\main\\resources\\";

	String workBookName = "FunctionalTestCases.xlsx";
	String sheetName = "FileUpload";
	
	@Test( priority = 1, enabled = true, description = "'File Upload '  verify the column Headers Of Search Screen" )
	public void verifyTheColHdrs() throws Exception
	{
		try
		{
			FileUpload fileUpload = new FileUpload( path, workBookName, sheetName, "FileUploadScreencolVal" );
			fileUpload.fileUploadColumnValidation();
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

	


	@Test( priority = 2, enabled = true, description = "'File Upload '  creation", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void fileUploadCreation() throws Exception
	{
		try
		{
			FileUpload fileUpload = new FileUpload( path, workBookName, sheetName, "FileUploadCreation" );
			fileUpload.fileUploadCreation();
			TaskSchedule taskObj = new TaskSchedule();
			taskObj.scheduleFileCollection( path, workBookName, sheetName, "FileCollectionSchedule", 1 );
			PSCollectedFileSearchHelper psCollectedFileObj1=new PSCollectedFileSearchHelper(path, workBookName, sheetName, "CollectedFileSearch");
			psCollectedFileObj1.validationOfCollectedFile();
			

		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}
	
	@Test( priority = 3, enabled = true, description = "'File Upload '  creation", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void fileUploadCreationWithJumpTo() throws Exception
	{
		try
		{
			FileUpload fileUpload = new FileUpload( path, workBookName, sheetName, "FileUploadCreationWithJumpTo" );
			fileUpload.fileUploadCreation();
			TaskSchedule taskObj = new TaskSchedule();
			taskObj.scheduleFileCollection( path, workBookName, sheetName, "FileCollectionSchedule_2", 1 );
			fileUpload.jumpToActionValidation();
			

		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}
}
