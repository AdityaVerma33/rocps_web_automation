package com.subex.rocps.automation.testcases.functionaltesting;

import org.testng.annotations.Test;

import com.subex.rocps.automation.helpers.application.partnerConfiguration.Account;
import com.subex.rocps.automation.helpers.application.partnerConfiguration.Agent;
import com.subex.rocps.automation.helpers.application.referenceTable.ExtractFileLocation;
import com.subex.rocps.automation.helpers.application.reportingAndExtraction.ReportAndExtDefinition;
import com.subex.rocps.automation.helpers.application.reportingAndExtraction.ReportAndExtScheduler;
import com.subex.rocps.automation.helpers.application.reportingAndExtraction.ReportColumnMapping;
import com.subex.rocps.automation.helpers.application.reportingAndExtraction.ReportsAndExtracts;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;

import com.subex.automation.helpers.application.screens.AuditDefinitionHelper;
import com.subex.automation.helpers.application.screens.QueryMeasureHelper;
import com.subex.automation.helpers.application.screens.TaskControllerHelper;
import com.subex.automation.helpers.util.FailureHelper;

public class TCReportAndExtScheduler extends PSAcceptanceTest
{

	String path = System.getProperty( "user.dir" ) + "\\src\\main\\resources\\";

	String workBookName = "FunctionalTestCases.xlsx";
	String sheetName = "ReportAndExtScheduler";

	@org.testng.annotations.Test( priority = 1, enabled = true, description = "task Controller capabilities" )
	public void taskControllerCapabilities() throws Exception
	{
		try
		{

			TaskControllerHelper taskObj = new TaskControllerHelper();
			taskObj.setTaskControllerCapability( path, workBookName, "ReportsAndExtracts", "TCCapability", 1 );

		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

	@Test( priority = 2, enabled = true, description = "'Reports and Extracts'   measures,audit, and extract file creation prerequisite", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void repAndExtractsPrerequisite1() throws Exception
	{
		try
		{
			QueryMeasureHelper queryMeasure = new QueryMeasureHelper();
			queryMeasure.createQueryMeasure( path, workBookName, "ReportAndExtDefn", "QueryMeasure", 1 );

			AuditDefinitionHelper audit = new AuditDefinitionHelper();
			audit.createAuditDefinition( path, workBookName, "ReportAndExtDefn", "Audits", 1 );

			ExtractFileLocation extractFileLocation = new ExtractFileLocation( path, workBookName, "ReportAndExtDefn", "ExtractFlLocationCreation" );
			extractFileLocation.extractFlLocationCreation();


		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

	@Test( priority = 3, enabled = true, description = "'Reports and Extracts'   Report Column Mapping creation prerequisite", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void repAndExtractsPrerequisite2() throws Exception
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

	@Test( priority = 4, enabled = true, description = "'Reports and Extracts'  Data Warehouse extract report ext definition creation prerequisite", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void repAndExtractsPrerequisite3() throws Exception
	{
		try
		{

			ReportAndExtDefinition reportAndExtDefinition = new ReportAndExtDefinition( path, workBookName, "ReportAndExtDefn", "DWSExtRepCreationWithoutParameters" );
			reportAndExtDefinition.reportAndExtReportCreation();

			ReportAndExtDefinition reportAndExtDefinition2 = new ReportAndExtDefinition( path, workBookName, "ReportAndExtDefn", "DWSExtRepCreationWithParameters" );
			reportAndExtDefinition2.reportAndExtReportCreation();


		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

	@Test( priority = 5, enabled = true, description = "'Reports and Extracts' Generic Usage report ext definition creation prerequisite", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void repAndExtractsPrerequisite4() throws Exception
	{
		try
		{


			ReportAndExtDefinition reportAndExtDefinition3 = new ReportAndExtDefinition( path, workBookName, "ReportAndExtDefn", "GenUsgReportCreation_AccountPayable" );
			reportAndExtDefinition3.reportAndExtReportCreation();

			ReportAndExtDefinition reportAndExtDefinition4 = new ReportAndExtDefinition( path, workBookName, "ReportAndExtDefn", "GenUsgReportCreation_AccountReceivables" );
			reportAndExtDefinition4.reportAndExtReportCreation();

			ReportAndExtDefinition reportAndExtDefinition5 = new ReportAndExtDefinition( path, workBookName, "ReportAndExtDefn", "GenUsgReportCreation_UsgRevCostDetail_Rep" );
			reportAndExtDefinition5.reportAndExtReportCreation();

			ReportAndExtDefinition reportAndExtDefinition6 = new ReportAndExtDefinition( path, workBookName, "ReportAndExtDefn", "GenUsgReportCreation_UsgRevCostOverview_Rep" );
			reportAndExtDefinition6.reportAndExtReportCreation();


		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

	@Test( priority = 6, enabled = true, description = "'Reports and Extracts'   Account creation prerequisite", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void repAndExtractsPrerequisite5() throws Exception
	{
		try
		{

			Agent agobj = new Agent( path, workBookName, "Agent", "Agent", 1 );
			agobj.agentCreation();

			Account accobj = new Account( path, workBookName, "Account", "AccountCustomer", 1 );
			accobj.accountCreation();

		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}


	@Test( priority = 7, enabled = true, description = "'Report and Extract Scheduler'  verify the column Headers Of Search Screen" )
	public void verifyTheColHdrs() throws Exception
	{
		try
		{

			ReportAndExtScheduler reportAndExtScheduler = new ReportAndExtScheduler( path, workBookName, sheetName, "ReportExtScheduleScreencolVal" );
			reportAndExtScheduler.repAndExtScheduleColumsValidation();
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

	@Test( priority = 8, enabled = true, description = "'Report and Extract Scheduler'  creation 'Data Warehouse extract Report'", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void createRepAndExtScheduler_DWS() throws Exception
	{
		try
		{

			ReportAndExtScheduler reportAndExtScheduler = new ReportAndExtScheduler( path, workBookName, sheetName, "TestCreationRepAndExtScheduler_DWS" );
			reportAndExtScheduler.reportAndExtScheduleCreation();
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

	@Test( priority = 9, enabled = true, description = "'Report and Extract Scheduler'  edit", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void editRepAndExtScheduler() throws Exception
	{
		try
		{

			ReportAndExtScheduler reportAndExtScheduler = new ReportAndExtScheduler( path, workBookName, sheetName, "TestEditRepAndExtScheduler" );
			reportAndExtScheduler.reportAndExtScheduleEdit();
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

	@Test( priority = 10, enabled = true, description = "'Report and Extract Scheduler'  Schedule for 'Data Warehouse extract Report'" )
	public void scheduleRepAndExtScheduler_DWS() throws Exception
	{
		try
		{

			ReportAndExtScheduler reportAndExtScheduler = new ReportAndExtScheduler( path, workBookName, sheetName, "TestScheduleRepAndExtScheduler_DWS" );
			reportAndExtScheduler.scheduleRepAndExtSchudler();
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

	@Test( priority = 11, enabled = true, description = "'Report and Extract Scheduler'  file valiation FOR 'Data Warehouse extract Report'" )
	public void repAndExtSchedulerFlValidation_DWS() throws Exception
	{
		try
		{

			ReportAndExtScheduler reportAndExtScheduler = new ReportAndExtScheduler( path, workBookName, sheetName, "ReportExtractsScheduleFlValidate_DWS" );
			reportAndExtScheduler.reportExtLocFileValidation();
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

	@Test( priority = 12, enabled = true, description = "'Report and Extract Scheduler'  creation for Generic Usage Report'", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void createRepAndExtScheduler_GenUsgReport() throws Exception
	{
		try
		{

			ReportAndExtScheduler reportAndExtScheduler = new ReportAndExtScheduler( path, workBookName, sheetName, "TestCreationRepAndExtScheduler_GenUsgReport" );
			reportAndExtScheduler.reportAndExtScheduleCreation();
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

	@Test( priority = 13, enabled = true, description = "'Report and Extract Scheduler'  Schedule for 'Generic Usage Report'" )
	public void scheduleRepAndExtScheduler_GenUsgReport() throws Exception
	{
		try
		{

			ReportAndExtScheduler reportAndExtScheduler = new ReportAndExtScheduler( path, workBookName, sheetName, "TestScheduleRepAndExtScheduler_GenUsgReport" );
			reportAndExtScheduler.scheduleRepAndExtSchudler();
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

	@Test( priority = 14, enabled = true, description = "'Report and Extract Scheduler'  file valiation FOR 'Generic Usage Report" )
	public void repAndExtSchedulerFlValidation_GenUsgReport() throws Exception
	{
		try
		{

			ReportAndExtScheduler reportAndExtScheduler = new ReportAndExtScheduler( path, workBookName, sheetName, "RepExtAdhocFlValidate_GenUsgRep" );
			reportAndExtScheduler.reportExtFileValidationWithFileNm();
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

}
