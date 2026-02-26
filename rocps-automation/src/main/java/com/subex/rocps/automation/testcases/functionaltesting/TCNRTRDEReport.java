package com.subex.rocps.automation.testcases.functionaltesting;

import org.testng.annotations.Test;

import com.subex.rocps.automation.helpers.application.roaming.NRTRDEReport;
import com.subex.rocps.automation.helpers.application.roaming.RoamingConfiguration;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;

import com.subex.automation.helpers.util.FailureHelper;

public class TCNRTRDEReport extends PSAcceptanceTest
{

	String path = System.getProperty( "user.dir" ) + "\\src\\main\\resources\\";

	String workBookName = "FunctionalTestCases.xlsx";
	String sheetName = "NRTRDEReport";

	@Test( priority = 1, enabled = true, description = "'NRTRDE Report'  verify the column Headers Of Search Screen" )
	public void verifyTheColHdrs() throws Exception
	{
		try
		{
			NRTRDEReport nrtrdeReport = new NRTRDEReport( path, workBookName, sheetName, "NRTRDEReportScreencolVal" );
			nrtrdeReport.nrtrdeReportColumsValidation();
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

	//@Test( priority = 2, enabled = true, description = "'NRTRDE Report'  validate Search result" )
	public void nRTRDEReportValidateSearchResult() throws Exception
	{
		try
		{
			NRTRDEReport nrtrdeReport = new NRTRDEReport( path, workBookName, sheetName, "NRTRDEReportValidateSearchResult" );
			nrtrdeReport.validateNrtrdeReportSearchResult();
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

	//@Test( priority = 3, enabled = true, description = "'NRTRDE Report'  view report action" )
	public void nRTRDEReportViewReportAction() throws Exception
	{
		try
		{
			NRTRDEReport nrtrdeReport = new NRTRDEReport( path, workBookName, sheetName, "NRTRDEReportViewReportAction" );
			nrtrdeReport.nrtrdeReportViewReportAction();
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}
}
