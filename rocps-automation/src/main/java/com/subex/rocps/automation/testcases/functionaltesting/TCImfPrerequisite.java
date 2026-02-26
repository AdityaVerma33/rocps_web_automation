package com.subex.rocps.automation.testcases.functionaltesting;

import org.testng.annotations.Test;

import com.subex.rocps.automation.helpers.application.matchandrate.CrossFXRate;
import com.subex.rocps.automation.helpers.application.referenceTable.CrossFXRateGroup;
import com.subex.rocps.automation.helpers.application.system.Streams;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;

import com.subex.automation.helpers.application.screens.FileCollectionHelper;
import com.subex.automation.helpers.application.screens.FileSourceHelper;
import com.subex.automation.helpers.application.screens.RecurringTaskHelper;
import com.subex.automation.helpers.util.FailureHelper;

public class TCImfPrerequisite extends PSAcceptanceTest
{
	String path = System.getProperty( "user.dir" ) + "\\src\\main\\resources\\";
	String workBookName = "FunctionalTestCases.xlsx";
	String sheetName = "IMFPrerequisite";

	@Test( priority = 1, description = "CrossFXRate_Group Creation for IMF", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void crossFXRateGrpCreationIMF() throws Exception
	{
		try
		{
			CrossFXRateGroup crossfxgrpObj = new CrossFXRateGroup( path, workBookName, "CrossFXRateGroup", "CrossFxRateGrp_IMF", 1 );
			crossfxgrpObj.crossFXRateGrpCreation();

		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 2, description = "Streams creation for IMF", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void createStreamForIMF() throws Exception
	{
		try
		{

			Streams streamObj = new Streams();
			if ( !streamObj.newStreamConfig( path, workBookName, sheetName, "Streams", 1 ) )
			{
				streamObj.imfExcRateFlDownloadStreamStageConfig( path, workBookName, sheetName, "IMFExchangeRateFileDownload", 1 );
				streamObj.imfExchRateImportStreamStageConfig( path, workBookName, sheetName, "IMFExchangeRateImport", 1 );
				streamObj.saveStreamDetail();
			}
			else
			{
				streamObj.editStreamConfig( path, workBookName, sheetName, "Streams", 1 );
				streamObj.imfExcRateFlDownloadStreamStageConfig( path, workBookName, sheetName, "IMFExchangeRateFileDownload", 1 );
				streamObj.imfExchRateImportStreamStageConfig( path, workBookName, sheetName, "IMFExchangeRateImport", 1 );
				streamObj.saveStreamDetail();
			}

		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 3, description = "Recurring task creation 'IMF Exchange Rate File Download' ",retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class  )
	public void createRecurringTaskIMF() throws Exception
	{
		try
		{

			RecurringTaskHelper triggerObj = new RecurringTaskHelper();
			triggerObj.createRecurringTask( path, workBookName, sheetName, "ImfFileDownloadRecurringTask", 1 );

		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 4, description = "file source creation 'IMF exchange rate import' ", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void fileSourceIMF() throws Exception
	{
		try
		{
			FileSourceHelper fileSrcObj = new FileSourceHelper();
			fileSrcObj.createFileSource( path, workBookName, sheetName, "FileSource_ImfImport", 1 );

		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 5, description = "file collection creation 'IMF exchange rate import' ", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void fileCollectionIMF() throws Exception
	{
		try
		{
			FileCollectionHelper fileCollObj = new FileCollectionHelper();
			fileCollObj.createFileCollection( path, workBookName, sheetName, "FileCollection_ImfImport", 1 );

		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

}
