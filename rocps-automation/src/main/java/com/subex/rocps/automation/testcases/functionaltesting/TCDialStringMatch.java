package com.subex.rocps.automation.testcases.functionaltesting;

import com.subex.automation.helpers.util.FailureHelper;
import com.subex.rocps.automation.helpers.application.networkConfiguraiton.DialStringSet;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;

public class TCDialStringMatch extends PSAcceptanceTest
{

	String path = System.getProperty( "user.dir" ) + "\\src\\main\\resources\\";
	String workBookName = "FunctionalTestCases.xlsx";
	String sheetName = "DialStringSet";

	@org.testng.annotations.Test( priority = 1, description = "dial String set creation", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void dialStringSetCreation() throws Exception
	{
		try
		{
			DialStringSet dialObj = new DialStringSet( path, workBookName, sheetName, "DialStringSet", 1 );
			dialObj.dialStringCreation();
		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 2, description = "dial String set with all mandatory fields creation", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void dialStringSetAllFieldsCreation() throws Exception
	{
		try
		{
			DialStringSet dialObj = new DialStringSet( path, workBookName, sheetName, "DialStringSet Allfields", 1 );
			dialObj.dialStringCreation();
		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 3, description = "dial String set creation1", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void dialStringSetCreation1() throws Exception
	{
		try
		{
			DialStringSet dialObj = new DialStringSet( path, workBookName, sheetName, "DialStringSet1", 1 );
			dialObj.dialStringCreation();
		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 4, description = "dial String set creation2", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void dialStringSetCreation2() throws Exception
	{
		try
		{
			DialStringSet dialObj = new DialStringSet( path, workBookName, sheetName, "DialStringSet2", 1 );
			dialObj.dialStringCreation();
		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 5, description = "dial String set creation3", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void dialStringSetCreation3() throws Exception
	{
		try
		{
			DialStringSet dialObj = new DialStringSet( path, workBookName, sheetName, "DialStringSet3", 1 );
			dialObj.dialStringCreation();
		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 6, description = "dial String set deletion" )
	public void dialStringSetDelete() throws Exception
	{
		try
		{
			DialStringSet dialDelObj = new DialStringSet( path, workBookName, sheetName, "DialStringSetDelete", 1 );
			dialDelObj.dialStringSetDelete();
		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 7, description = "dial String set un delete" )
	public void dialStringSetUnDelete() throws Exception
	{
		try
		{
			DialStringSet dialUnDelObj = new DialStringSet( path, workBookName, sheetName, "DialStringSetUnDelete", 1 );
			dialUnDelObj.dialStringSetUnDelete();
		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 8, description = "dial String set column validation" )
	public void dialStringSetColVal() throws Exception
	{
		try
		{
			DialStringSet dialColValObj = new DialStringSet( path, workBookName, sheetName, "DialSetSearchScreencolVal", 1 );
			dialColValObj.searchScreenColumnsValidation();
		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 9, description = "edit  dial String set" )
	public void editdialStringSetCreation3() throws Exception
	{
		try
		{
			DialStringSet dialObj = new DialStringSet( path, workBookName, sheetName, "EditDialStringSet3", 1 );
			dialObj.editDialStringSet();
		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}
}
