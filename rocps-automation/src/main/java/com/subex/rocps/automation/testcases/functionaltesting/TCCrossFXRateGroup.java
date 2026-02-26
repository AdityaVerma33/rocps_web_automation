package com.subex.rocps.automation.testcases.functionaltesting;

import com.subex.automation.helpers.util.FailureHelper;
import com.subex.rocps.automation.helpers.application.referenceTable.CrossFXRateGroup;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;

public class TCCrossFXRateGroup extends PSAcceptanceTest
{

	String path = System.getProperty( "user.dir" ) + "\\src\\main\\resources\\";
	String workBookName = "FunctionalTestCases.xlsx";
	String sheetName = "CrossFXRateGroup";

	@org.testng.annotations.Test( priority = 1, description = "cross FXRateGrp creation", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void crossFXRateGrpCreation() throws Exception
	{
		try
		{
			CrossFXRateGroup crossfxgrpObj = new CrossFXRateGroup( path, workBookName, sheetName, "CrossFxRateGrp", 1 );
			crossfxgrpObj.crossFXRateGrpCreation();
		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 2, description = "cross FXRateGrp deletion" )
	public void crossFXRateGrpDelete() throws Exception
	{
		try
		{
			CrossFXRateGroup crossfxgrpDelObj = new CrossFXRateGroup( path, workBookName, sheetName, "CrossFxRateGrpDelete", 1 );
			crossfxgrpDelObj.crossFXRateGroupDelete();
		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 3, description = "cross FXRateGrp un delete" )
	public void crossFXRateGrpUnDelete() throws Exception
	{
		try
		{
			CrossFXRateGroup crossfxgrpUnDelObj = new CrossFXRateGroup( path, workBookName, sheetName, "CrossFxRateGrpUnDelete", 1 );
			crossfxgrpUnDelObj.crossFXRateGroupUnDelete();
		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 4, description = "cross FXRateGrp search screen col validation" )
	public void crossFXRateGrpColVal() throws Exception
	{
		try
		{
			CrossFXRateGroup crossfxgrpColObj = new CrossFXRateGroup( path, workBookName, sheetName, "CrossFXGrpSearchScreencolVal", 1 );
			crossfxgrpColObj.searchScreenColumnsValidation();
		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 5, description = "cross FXRateGrp edit" )
	public void crossFXRateGrpEdit() throws Exception
	{
		try
		{
			CrossFXRateGroup crossfxgrpColObj = new CrossFXRateGroup( path, workBookName, sheetName, "CrossFxRateGrpEdit", 1 );
			crossfxgrpColObj.crossFXRateGrpEdit();
		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}

}
