package com.subex.rocps.automation.testcases.functionaltesting;

import org.testng.annotations.Test;

import com.subex.rocps.automation.helpers.application.roaming.NRTRDEFileStatus;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;

import com.subex.automation.helpers.util.FailureHelper;

public class TCNRTRDEFileStatus extends PSAcceptanceTest
{

	String path = System.getProperty( "user.dir" ) + "\\src\\main\\resources\\";

	String workBookName = "FunctionalTestCases.xlsx";
	String sheetName = "NRTRDEFileStatus";

	@Test( priority = 1, enabled = true, description = "'NRTRDE File Statusn'  verify the column Headers Of Search Screen" )
	public void verifyTheColHdrs() throws Exception
	{
		try
		{
			NRTRDEFileStatus nrtrdeFileStatus = new NRTRDEFileStatus( path, workBookName, sheetName, "NRTRDEFileStatusScreencolVal" );
			nrtrdeFileStatus.nrtrdeFileStatusColumsValidation();
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

	//@Test( priority = 2, enabled = true, description = "'NRTRDE File Statusn'  verify the search results" )
	public void nRTRDEFileStatusSearchResult() throws Exception
	{
		try
		{
			NRTRDEFileStatus nrtrdeFileStatus = new NRTRDEFileStatus( path, workBookName, sheetName, "NRTRDEFileStatusValidateSearchResult" );
			nrtrdeFileStatus.validateNrtrdeReportSearchResult();
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

	//@Test( priority = 3, enabled = true, description = "'NRTRDE File Statusn'  View Records Action" )
	public void nRTRDEFileStatusViewRecordAction() throws Exception
	{
		try
		{
			NRTRDEFileStatus nrtrdeFileStatus = new NRTRDEFileStatus( path, workBookName, sheetName, "TestNRTRDEFileStatusViewRecordAction" );
			nrtrdeFileStatus.nrtrdeFileStatusViewRecordsAction();
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}
}
