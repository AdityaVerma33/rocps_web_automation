package com.subex.rocps.automation.testcases.functionaltesting;

import com.subex.automation.helpers.application.screens.StreamControllerHelper;
import com.subex.automation.helpers.util.FailureHelper;

import com.subex.rocps.automation.helpers.application.aggregation.AggregationProcessor;
import com.subex.rocps.automation.helpers.application.system.Streams;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;

public class TCStreams extends PSAcceptanceTest
{

	String path = System.getProperty( "user.dir" ) + "\\src\\main\\resources\\";
	String workBookName = "FunctionalTestCases.xlsx";
	String sheetName = "Streams";

	@org.testng.annotations.Test( priority = 1, description = "Streams creation", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void createStream() throws Exception
	{
		try
		{

			Streams streamObj = new Streams();
			if ( !streamObj.newStreamConfig( path, workBookName, sheetName, "Streams", 1 ) )
			{
				streamObj.voiceStreamNewConfig( path, workBookName, sheetName, testCaseName, 1 );
				streamObj.saveStreamDetail();
			}

		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 2, description = "Streams creation for Fios", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void createFiosStream() throws Exception
	{
		try
		{

			Streams streamObj = new Streams();
			if ( !streamObj.newStreamConfig( path, workBookName, "FiosStream", "FiosStreams", 1 ) )
			{
				streamObj.voiceStreamNewConfig( path, workBookName, "FiosStream", testCaseName, 1 );
				streamObj.saveStreamDetail();
			}

		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

	/*
	 * @org.testng.annotations.Test( priority = 2, description = "Stream deletion",
	 * dependsOnMethods = "createStream" ) public void streamDelete() throws
	 * Exception { try { Streams streamObj = new Streams(); streamObj.streamDelete(
	 * path, workBookName, sheetName, "StreamDelete", 1 ); } catch ( Exception e ) {
	 * FailureHelper.reportFailure( e ); throw e; } }
	 * 
	 * @org.testng.annotations.Test( priority = 3, description = "Stream un delete",
	 * dependsOnMethods = "streamDelete" ) public void streamUnDelete() throws
	 * Exception { try { Streams streamObj = new Streams();
	 * streamObj.streamUnDelete( path, workBookName, sheetName, "StreamUnDelete", 1
	 * ); } catch ( Exception e ) { FailureHelper.reportFailure( e ); throw e; } }
	 */
}
