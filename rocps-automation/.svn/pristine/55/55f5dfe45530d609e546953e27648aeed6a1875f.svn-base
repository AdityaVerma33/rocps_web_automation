package com.subex.rocps.automation.testcases.functionaltesting;

import org.testng.annotations.Test;

import com.subex.rocps.automation.helpers.application.referenceTable.RoamingDiscounting;
import com.subex.rocps.automation.helpers.application.roaming.TapOutErrExclRecords;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;

import com.subex.automation.helpers.util.FailureHelper;

public class TCRoamTapOutErrExclRecords extends PSAcceptanceTest
{

	String path = System.getProperty( "user.dir" ) + "\\src\\main\\resources\\";

	String workBookName = "FunctionalTestCases.xlsx";
	String sheetName = "TapOutErrExclRecords";

	@Test( priority = 1, enabled = true, description = "'TAP Out Errored & Excluded Records'  verify the column Headers Of Search Screen" )
	public void verifyTheColHdrs() throws Exception
	{
		try
		{
			TapOutErrExclRecords tapOutErrExclRecords = new TapOutErrExclRecords( path, workBookName, sheetName, "TapOutErrExclRecordsScreencolVal" );
			tapOutErrExclRecords.tapOutErrExclRecordsColumsValidation();

		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

	//@Test( priority = 2, enabled = true, description = "'TAP Out Errored & Excluded Records'  verify the search result of Search screen" )
	public void validateSearchResultOfTapOutErrExclScreen() throws Exception
	{
		try
		{
			TapOutErrExclRecords tapOutErrExclRecords = new TapOutErrExclRecords( path, workBookName, sheetName, "TapOutErrExclRecordsValidateSearchResult" );
			tapOutErrExclRecords.validateTapOutErrExclRecordSearchResult();

		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

	//@Test( priority = 3, enabled = true, description = "'TAP Out Errored & Excluded Records'  verify the search result of 'excluded Records' action" )
	public void validateSearchResultOfExclRecordsAction() throws Exception
	{
		try
		{
			TapOutErrExclRecords tapOutErrExclRecords = new TapOutErrExclRecords( path, workBookName, sheetName, "TapOutErrExclValidateExclRecordsSearchResult" );
			tapOutErrExclRecords.validateTapOutErrExclRecordSearchResult();

		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

	//@Test( priority = 4, enabled = true, description = "'TAP Out Errored & Excluded Records'  verify the search result of 'Errored Records' action" )
	public void validateSearchResultOfErrRecordsAction() throws Exception
	{
		try
		{
			TapOutErrExclRecords tapOutErrExclRecords = new TapOutErrExclRecords( path, workBookName, sheetName, "TapOutErrExclValidateErrRecordsSearchResult" );
			tapOutErrExclRecords.validateTapOutErrExclRecordSearchResult();

		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}
}
