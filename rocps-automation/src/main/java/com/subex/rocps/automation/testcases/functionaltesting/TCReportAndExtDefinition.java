package com.subex.rocps.automation.testcases.functionaltesting;

import org.testng.annotations.Test;

import com.subex.rocps.automation.helpers.application.referenceTable.ExtractFileLocation;
import com.subex.rocps.automation.helpers.application.reportingAndExtraction.ReportAndExtDefinition;
import com.subex.rocps.automation.helpers.application.reportingAndExtraction.ReportColumnMapping;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;

import com.subex.automation.helpers.application.screens.AuditDefinitionHelper;
import com.subex.automation.helpers.application.screens.ComparisonMeasureHelper;
import com.subex.automation.helpers.application.screens.QueryMeasureHelper;
import com.subex.automation.helpers.util.FailureHelper;

public class TCReportAndExtDefinition extends PSAcceptanceTest
{
	String path = System.getProperty( "user.dir" ) + "\\src\\main\\resources\\";

	String workBookName = "FunctionalTestCases.xlsx";
	String sheetName = "ReportAndExtDefn";

	@Test( priority = 1, enabled = true, description = "'Report and Extract Definition'   measures,audit, and extract file creation prerequisite", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void repAndExtDefnPrerequisite() throws Exception
	{
		try
		{
			QueryMeasureHelper queryMeasure = new QueryMeasureHelper();
			queryMeasure.createQueryMeasure( path, workBookName, sheetName, "QueryMeasure", 1 );

			AuditDefinitionHelper audit = new AuditDefinitionHelper();
			audit.createAuditDefinition( path, workBookName, sheetName, "Audits", 1 );

			ExtractFileLocation extractFileLocation = new ExtractFileLocation( path, workBookName, sheetName, "ExtractFlLocationCreation" );
			extractFileLocation.extractFlLocationCreation();

		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

	@Test( priority = 2, enabled = true, description = "'Report and Extract Definition'   Report Column Mapping creation prerequisite", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void repAndExtDefnPrerequisite2() throws Exception
	{
		try
		{

			ReportColumnMapping reportColumnMapping = new ReportColumnMapping( path, workBookName, "ReportColumnMapping", "ReportColumnMappingCreation_DetailReport" );
			reportColumnMapping.reportColumnMappingCreation();

			ReportColumnMapping reportColumnMappingOver = new ReportColumnMapping( path, workBookName, "ReportColumnMapping", "ReportColumnMappingCreation_OverviewReport" );
			reportColumnMappingOver.reportColumnMappingCreation();

		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

	@Test( priority = 3, enabled = true, description = "'Report and Extract Definition'  verify the column Headers Of Search Screen" )
	public void verifyTheColHdrs() throws Exception
	{
		try
		{

			ReportAndExtDefinition reportAndExtDefinition = new ReportAndExtDefinition( path, workBookName, sheetName, "ReportExtDefnScreencolVal" );
			reportAndExtDefinition.reportAndExtDefnColumsValidation();
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

	@Test( priority = 4, enabled = true, description = "'Report and Extract Definition'  Data Warehouse extraction report creation without parameters", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void creationDWSReportWithoutParamters() throws Exception
	{
		try
		{

			ReportAndExtDefinition reportAndExtDefinition = new ReportAndExtDefinition( path, workBookName, sheetName, "DWSExtRepCreationWithoutParameters" );
			reportAndExtDefinition.reportAndExtReportCreation();
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

	@Test( priority = 5, enabled = true, description = "'Report and Extract Definition'  Data Warehouse extraction report creation with parameters", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void creationDWSReportWithParamters() throws Exception
	{
		try
		{

			ReportAndExtDefinition reportAndExtDefinition = new ReportAndExtDefinition( path, workBookName, sheetName, "DWSExtRepCreationWithParameters" );
			reportAndExtDefinition.reportAndExtReportCreation();
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

	@Test( priority = 6, enabled = true, description = "'Report and Extract Definition'  Data Warehouse extraction report edit" )
	public void editDataWarehouseReport() throws Exception
	{
		try
		{

			ReportAndExtDefinition reportAndExtDefinition = new ReportAndExtDefinition( path, workBookName, sheetName, "DWSExtRepEdit" );
			reportAndExtDefinition.reportAndExtReportEdit();
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

	@Test( priority = 7, enabled = true, description = "'Report and Extract Definition'  delete action" )
	public void deleteRepExtDefinition() throws Exception
	{
		try
		{

			ReportAndExtDefinition reportAndExtDefinition = new ReportAndExtDefinition( path, workBookName, sheetName, "RepExtDefnDelete" );
			reportAndExtDefinition.reportAndExtDefnDelete();
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

	@Test( priority = 8, enabled = true, description = "'Report and Extract Definition'  undelete action" )
	public void unDeleteRepExtDefinition() throws Exception
	{
		try
		{

			ReportAndExtDefinition reportAndExtDefinition = new ReportAndExtDefinition( path, workBookName, sheetName, "RepExtDefnUnDelete" );
			reportAndExtDefinition.reportAndExtDefnUnDelete();
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

	@Test( priority = 9, enabled = true, description = "'Report and Extract Definition' - 'Generic Usage Report'creation with 'Accounts Payable Ageing Report Component'", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void creationGenUsgReportWithAccountPayble() throws Exception
	{
		try
		{

			ReportAndExtDefinition reportAndExtDefinition = new ReportAndExtDefinition( path, workBookName, sheetName, "GenUsgReportCreation_AccountPayable" );
			reportAndExtDefinition.reportAndExtReportCreation();
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

	@Test( priority = 10, enabled = true, description = "'Report and Extract Definition' - 'Generic Usage Report'creation with 'Account Receivables Ageing Report Component'", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void creationGenUsgReportWithAccountReceivables() throws Exception
	{
		try
		{

			ReportAndExtDefinition reportAndExtDefinition = new ReportAndExtDefinition( path, workBookName, sheetName, "GenUsgReportCreation_AccountReceivables" );
			reportAndExtDefinition.reportAndExtReportCreation();
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

	@Test( priority = 11, enabled = true, description = "'Report and Extract Definition' - 'Generic Usage Report'creation with 'Usage Revenue Cost Detail Report Component'", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void creationGenUsgReportWithUsgRevCostDetail() throws Exception
	{
		try
		{

			ReportAndExtDefinition reportAndExtDefinition = new ReportAndExtDefinition( path, workBookName, sheetName, "GenUsgReportCreation_UsgRevCostDetail_Rep" );
			reportAndExtDefinition.reportAndExtReportCreation();
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

	@Test( priority = 12, enabled = true, description = "'Report and Extract Definition' - 'Generic Usage Report'creation with 'Usage Revenue Cost Overview Report Component'", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void creationGenUsgReportWithUsgRevCostOverview() throws Exception
	{
		try
		{

			ReportAndExtDefinition reportAndExtDefinition = new ReportAndExtDefinition( path, workBookName, sheetName, "GenUsgReportCreation_UsgRevCostOverview_Rep" );
			reportAndExtDefinition.reportAndExtReportCreation();
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

	@Test( priority = 13, enabled = true, description = "'Report and Extract Definition' - 'Generic Usage Report'creation with 'Usage Revenue Cost Overview Report Component' with email", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void creationGenUsgRepWithUsgRevCostOverEmail() throws Exception
	{
		try
		{

			ReportAndExtDefinition reportAndExtDefinition = new ReportAndExtDefinition( path, workBookName, sheetName, "GenUsgReportCreation_UsgRevCostOverview_RepWithEmail" );
			reportAndExtDefinition.reportAndExtReportCreation();
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

	@Test( priority = 14, enabled = true, description = "'Report and Extract Definition' - 'DWS with EMail'", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void creationDWSWithEmail() throws Exception
	{
		try
		{

			ReportAndExtDefinition reportAndExtDefinition = new ReportAndExtDefinition( path, workBookName, sheetName, "DWSExtRepCreationWithParametersWithEmail" );
			reportAndExtDefinition.reportAndExtReportCreation();
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

	//@Test( priority = 15, enabled = true, description = "'Report and Extract Definition' - 'Birt Report'creation with 'Event Statistics Report Component'", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void creationBirtReportWithEventStatistics() throws Exception
	{
		try
		{

			ReportAndExtDefinition reportAndExtDefinition = new ReportAndExtDefinition( path, workBookName, sheetName, "BirtReport_EventStatastics_Rep1" );
			reportAndExtDefinition.reportAndExtReportCreation();
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

}
