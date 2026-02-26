package com.subex.rocps.automation.testcases.functionaltesting;

import org.testng.annotations.Test;

import com.subex.rocps.automation.helpers.application.referenceTable.Currency;
import com.subex.rocps.automation.helpers.application.reportingAndExtraction.ReportColumnMapping;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;

import com.subex.automation.helpers.util.FailureHelper;

public class TCReportColumnMapping extends PSAcceptanceTest
{

	String path = System.getProperty( "user.dir" ) + "\\src\\main\\resources\\";

	String workBookName = "FunctionalTestCases.xlsx";
	String sheetName = "ReportColumnMapping";

	@Test( priority = 1, enabled = true, description = "'Report Column Mapping'  verify the column Headers Of Search Screen" )
	public void verifyTheColHdrs() throws Exception
	{
		try
		{
			ReportColumnMapping reportColumnMapping = new ReportColumnMapping( path, workBookName, sheetName, "ReportColumnMappingScreencolVal" );
			reportColumnMapping.reportColumnMappingColumsValidation();

		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

	@Test( priority = 2, enabled = true, description = "'Report Column Mapping' creation with 'Usage Detail Report Definition'", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void reportColumnMapping_usgDetail() throws Exception
	{
		try
		{
			ReportColumnMapping reportColumnMapping = new ReportColumnMapping( path, workBookName, sheetName, "ReportColumnMappingCreation_DetailReport" );
			reportColumnMapping.reportColumnMappingCreation();

		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

	@Test( priority = 3, enabled = true, description = "'Report Column Mapping' creation with 'Usage Overview Report Definition'", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void reportColumnMapping_usgOverview() throws Exception
	{
		try
		{
			ReportColumnMapping reportColumnMapping = new ReportColumnMapping( path, workBookName, sheetName, "ReportColumnMappingCreation_OverviewReport" );
			reportColumnMapping.reportColumnMappingCreation();

		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

	@Test( priority = 4, enabled = true, description = "'Report Column Mapping' edit action" )
	public void reportColumnMapping_edit() throws Exception
	{
		try
		{
			ReportColumnMapping reportColumnMapping = new ReportColumnMapping( path, workBookName, sheetName, "ReportColumnMappingEditAction" );
			reportColumnMapping.reportColumnMappingEdit();

		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}
}
