package com.subex.rocps.automation.testcases.functionaltesting;

import com.subex.rocps.automation.helpers.application.bilateral.MergerResults;
import com.subex.rocps.automation.helpers.application.referenceTable.DealTrafficTypeOrder;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;

import com.subex.automation.helpers.util.FailureHelper;

public class TCMergerResults extends PSAcceptanceTest
{
	String path = System.getProperty( "user.dir" ) + "\\src\\main\\resources\\";
	String workBookName = "FunctionalTestingDeals.xlsx";
	String sheetName = "DealTrafficType";

	
	@org.testng.annotations.Test( priority = 1, description = "Merger Result screen column validation" )
	public void mergerResultColVal() throws Exception
	{
		try
		{
			MergerResults dealObj = new MergerResults( path, workBookName, sheetName, "DealTypeOrder" );
			dealObj.searchScreenColumnsValidation();
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}
	
	@org.testng.annotations.Test( priority = 2, description = "View Merger Results" )
	public void viewMergerResult() throws Exception
	{
		try
		{
			MergerResults dealObj = new MergerResults( path, workBookName, sheetName, "DealTypeOrder" );
			dealObj.viewMergerResults();
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}
}
