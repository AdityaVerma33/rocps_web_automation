package com.subex.rocps.automation.testcases.functionaltesting;

import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;

import com.subex.automation.helpers.application.screens.BandHelper;
import com.subex.automation.helpers.util.FailureHelper;

public class TCBands extends PSAcceptanceTest
{
	String path = System.getProperty( "user.dir" ) + "\\src\\main\\resources\\";
	String workBookName = "FunctionalTestCases.xlsx";
	String sheetName = "Bands";

	@org.testng.annotations.Test( priority = 1, description = "Band creation for Transit", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void createBandTransit() throws Exception
	{
		try
		{
			BandHelper bandObj = new BandHelper();
			bandObj.createBand( path, workBookName, sheetName, "Bands Transit", 1 );
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 2, description = "Band creation for Incoming", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void createBandIncoming() throws Exception
	{
		try
		{
			BandHelper bandObj = new BandHelper();
			bandObj.createBand( path, workBookName, sheetName, "Bands Incoming", 1 );
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 3, description = "Band creation for FreePhone", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void createBandFreePhone() throws Exception
	{
		try
		{
			BandHelper bandObj = new BandHelper();
			bandObj.createBand( path, workBookName, sheetName, "Bands FreePhone", 1 );
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 4, description = "Band creation for Outgoing", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void createBandOutgoing() throws Exception
	{
		try
		{
			BandHelper bandObj = new BandHelper();
			bandObj.createBand( path, workBookName, sheetName, "Bands Outgoing", 1 );
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 5, description = "Band creation for advance Rating", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void createBandAdvanceRating() throws Exception
	{
		try
		{
			BandHelper bandObj = new BandHelper();
			bandObj.createBand( path, workBookName, sheetName, "Bands AdvanceRating", 1 );
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}
}
