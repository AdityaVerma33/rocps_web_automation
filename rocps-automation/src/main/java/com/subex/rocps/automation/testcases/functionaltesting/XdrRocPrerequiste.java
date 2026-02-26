package com.subex.rocps.automation.testcases.functionaltesting;

import com.subex.rocps.automation.helpers.application.referenceTable.AggrComponentMapping;
import com.subex.rocps.automation.helpers.application.system.Streams;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;

import com.subex.automation.helpers.application.screens.FileCollectionHelper;
import com.subex.automation.helpers.application.screens.FileSourceHelper;
import com.subex.automation.helpers.application.screens.RecurringTaskHelper;
import com.subex.automation.helpers.application.screens.TriggerHelper;
import com.subex.automation.helpers.util.FailureHelper;

public class XdrRocPrerequiste extends PSAcceptanceTest
{

	String path = System.getProperty( "user.dir" ) + "\\src\\main\\resources\\";
	String workBookName = "FunctionalTestCases.xlsx";

	/*ROC Prerequiste for Xdr Extraction Template*/

	@org.testng.annotations.Test( priority = 1, description = "Streams creation for Xdr Extraction Template", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void createStreamForXdrTemplate() throws Exception
	{
		String partition = null;
		try
		{

			Streams streamObj = new Streams();
			streamObj.newStreamConfig( path, workBookName, "XDR RocPrerequiste", "Streams", 1 );
			streamObj.xdrStreamNewConfig( path, workBookName, "XDR RocPrerequiste", "XdrTemplStreamStages", 1 );
			streamObj.saveStreamDetail();
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 2, description = "Streams creation for Xdr Extraction Template", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void createXdrAggMasterCompStreamStg() throws Exception
	{
		String partition = null;
		try
		{

			Streams streamObj = new Streams();
			streamObj.editStreamConfig( path, workBookName, "XDR RocPrerequiste", "Streams", 1 );
			streamObj.xdrAggMasterCompStreamStgConfig( path, workBookName, "XDR RocPrerequiste", "XDRAggMasterComponent", 1 );
			streamObj.saveStreamDetail();
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 3, description = "aggrComponentMapping creation" )
	public void aggrComponentMappingCreation() throws Exception
	{
		try
		{
			AggrComponentMapping aggrObj = new AggrComponentMapping( path, workBookName, "XDR RocPrerequiste", "AggrCompMapping", 1 );
			aggrObj.aggrComponentMappingCreation();
		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 4, description = "file source creation", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void fileSource() throws Exception
	{
		try
		{
			FileSourceHelper fileSrcObj = new FileSourceHelper();
			fileSrcObj.createFileSource( path, workBookName, "XDR RocPrerequiste", "FileSource", 1 );

		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 5, description = "file collection creation", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void fileCollection() throws Exception
	{
		try
		{
			FileCollectionHelper fileCollObj = new FileCollectionHelper();
			fileCollObj.createFileCollection( path, workBookName, "XDR RocPrerequiste", "FileCollection", 1 );

		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 6, description = "Trigger creation" )
	public void createTrigger() throws Exception
	{
		try
		{

			TriggerHelper triggerObj = new TriggerHelper();
			triggerObj.createTrigger( path, workBookName, "XDR RocPrerequiste", "Trigger", 1 );

		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 7, description = "Recurring task creation" )
	public void createRecurringTask() throws Exception
	{
		try
		{

			RecurringTaskHelper triggerObj = new RecurringTaskHelper();
			triggerObj.createRecurringTask( path, workBookName, "XDR RocPrerequiste", "RecurringTask", 1 );

		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

}
