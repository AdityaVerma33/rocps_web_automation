package com.subex.rocps.automation.testcases.functionaltesting;

import com.subex.rocps.automation.helpers.application.matchandrate.CrossFXRate;
import com.subex.rocps.automation.helpers.application.referenceTable.CrossFXRateGroup;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;

import com.subex.automation.helpers.util.FailureHelper;

public class TCCrossFXRate extends PSAcceptanceTest
{

	String path = System.getProperty( "user.dir" ) + "\\src\\main\\resources\\";
	String workBookName = "FunctionalTestCases.xlsx";
	String sheetName = "CrossFXRate";

	@org.testng.annotations.Test( priority = 1, description = "cross FXRateGrp creation", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void crossFXRateGrpCreation() throws Exception
	{
		try
		{
			CrossFXRate crossfxObj = new CrossFXRate( path, workBookName, sheetName, "CrossFxRate", 1 );
			crossfxObj.crossFXRatesCreation();
		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 2, description = "cross FXRateGrp GBP-EUR creation", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void crossFXRateGrpGBPEURCreation() throws Exception
	{
		try
		{
			CrossFXRate crossfx1Obj = new CrossFXRate( path, workBookName, sheetName, "CrossFXRate GBP-EUR", 1 );
			crossfx1Obj.crossFXRatesCreation();
		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 3, description = "CrossFXRate USD-GBP creation", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void crossFXRateGrpUSDGBPCreation() throws Exception
	{
		try
		{
			CrossFXRate crossfx2Obj = new CrossFXRate( path, workBookName, sheetName, "CrossFXRate USD-GBP", 1 );
			crossfx2Obj.crossFXRatesCreation();
		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 4, description = "CrossFXRate search screen column validation" )
	public void crossFXRateGrpColVal() throws Exception
	{
		try
		{
			CrossFXRate crossfx2Obj = new CrossFXRate( path, workBookName, sheetName, "CrossFxRateSearchScreencolVal", 1 );
			crossfx2Obj.searchScreenColumnsValidation();
		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 5, description = "cross FXRateGrp creation1", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void crossFXRateGrpCreation1() throws Exception
	{
		try
		{
			CrossFXRateGroup crossfxgrpObj = new CrossFXRateGroup( path, workBookName, "CrossFXRateGroup", "CrossFxRateGrp02", 1 );
			crossfxgrpObj.crossFXRateGrpCreation();
		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 6, description = "CrossFXRate EUR-USD", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void crossFXRateCreationEURTOUSD() throws Exception
	{
		try
		{
			CrossFXRate crossfxObj = new CrossFXRate( path, workBookName, sheetName, "CrossFXRate EUR-USD", 1 );
			crossfxObj.crossFXRatesCreation();
		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 7, description = "CrossFXRate EUR-GBP", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void crossFXRateCreationEURTOGBP() throws Exception
	{
		try
		{
			CrossFXRate crossfxObj = new CrossFXRate( path, workBookName, sheetName, "CrossFXRate EUR-GBP", 1 );
			crossfxObj.crossFXRatesCreation();
		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 8, description = "CrossFXRate GBP-USD", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void crossFXRateCreationGBPTOUSD() throws Exception
	{
		try
		{
			CrossFXRate crossfxObj = new CrossFXRate( path, workBookName, sheetName, "CrossFXRate GBP-USD", 1 );
			crossfxObj.crossFXRatesCreation();
		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 9, description = "Default CrossFXRate ", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void defaultCrossFXRateCreation() throws Exception
	{
		try
		{
			CrossFXRate crossfxObj = new CrossFXRate( path, workBookName, sheetName, "CrossFXRate CArrierInvoice", 1 );
			crossfxObj.crossFXRatesCreation();
		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 10, description = " CrossFXRate edit " )
	public void defaultCrossFXRateEdit() throws Exception
	{
		try
		{
			CrossFXRate crossfxObj = new CrossFXRate( path, workBookName, sheetName, "CrossFXRate EUR-USDEdit", 1 );
			crossfxObj.crossFXRatesEdit();
		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}
}
