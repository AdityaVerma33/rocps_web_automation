package com.subex.rocps.automation.testcases.functionaltesting;

import org.testng.annotations.Test;

import com.subex.rocps.automation.helpers.application.partnerConfiguration.Account;
import com.subex.rocps.automation.helpers.application.partnerConfiguration.Agent;
import com.subex.rocps.automation.helpers.application.referenceTable.ExtractFileLocation;
import com.subex.rocps.automation.helpers.application.reportingAndExtraction.ReportAndExtDefinition;
import com.subex.rocps.automation.helpers.application.reportingAndExtraction.ReportColumnMapping;
import com.subex.rocps.automation.helpers.application.reportingAndExtraction.ReportsAndExtracts;
import com.subex.rocps.automation.helpers.application.system.Streams;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;

import com.subex.automation.helpers.application.screens.AuditDefinitionHelper;
import com.subex.automation.helpers.application.screens.QueryMeasureHelper;
import com.subex.automation.helpers.application.screens.TaskControllerHelper;
import com.subex.automation.helpers.util.FailureHelper;

public class TCReportAndExtracts extends PSAcceptanceTest
{
	String path = System.getProperty( "user.dir" ) + "\\src\\main\\resources\\";

	String workBookName = "FunctionalTestCases.xlsx";
	String sheetName = "ReportsAndExtracts";

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

	@Test( priority = 7, enabled = true, description = "'Reports and Extracts'  verify the column Headers Of Search Screen" )
	public void verifyTheColHdrs() throws Exception
	{
		try
		{

			ReportsAndExtracts reportsAndExtracts = new ReportsAndExtracts( path, workBookName, sheetName, "ReportExtScreencolVal" );
			reportsAndExtracts.reportAndExtColumsValidation();
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

	@Test( priority = 8, enabled = true, description = "'Report and Extracts'  creation without parameters for 'Data Warehouse Extract Report'" )
	public void creationRepAndExtWithoutParam_DWS() throws Exception
	{
		try
		{

			ReportsAndExtracts reportsAndExtracts = new ReportsAndExtracts( path, workBookName, sheetName, "RepAndExtCreationWithoutParam_DWS" );
			reportsAndExtracts.reportAndExtCreation();
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

	@Test( priority = 9, enabled = true, description = "'Report and Extracts'  creation with parameters for 'Data Warehouse Extract Report'" )
	public void creationRepAndExtWitParam_DWS() throws Exception
	{
		try
		{

			ReportsAndExtracts reportsAndExtracts = new ReportsAndExtracts( path, workBookName, sheetName, "RepAndExtCreationWithParam_DWS" );
			reportsAndExtracts.reportAndExtCreation();
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

	@Test( priority = 10, enabled = true, description = "'Report and Extracts'  Validate adhoc file in 'Extract File Location' without parameter for 'Data Warehouse Extract Report'" )
	public void validateRepAndExtFileAdhoc_WithoutParameter() throws Exception
	{
		try
		{

			ReportsAndExtracts reportsAndExtracts = new ReportsAndExtracts( path, workBookName, sheetName, "RepExtAdhocFlValidate_WithoutParam_DWS" );
			reportsAndExtracts.reportExtLocFileValidation();
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

	@Test( priority = 11, enabled = true, description = "'Report and Extracts'  Validate adhoc file in 'Extract File Location' with parameter for 'Data Warehouse Extract Report'" )
	public void validateRepAndExtFileAdhoc_WithParameter() throws Exception
	{
		try
		{

			ReportsAndExtracts reportsAndExtracts = new ReportsAndExtracts( path, workBookName, sheetName, "RepExtAdhocFlValidate_WithParam_DWS" );
			reportsAndExtracts.reportExtLocFileValidation();
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}

	}

	@Test( priority = 12, enabled = true, description = "'Report and Extracts'  creation for 'Generic Usage Report'creation with 'Accounts Payable Ageing Report Component" )
	public void creationGenUsgReportWithAccountPayble() throws Exception
	{
		try
		{

			ReportsAndExtracts reportsAndExtracts = new ReportsAndExtracts( path, workBookName, sheetName, "RepAndExtCreation_GenUsgRep_AccountPayable" );
			reportsAndExtracts.reportAndExtCreation();
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

	@Test( priority = 13, enabled = true, description = "'Report and Extracts'  Validate adhoc file for 'Generic Usage Report'creation with 'Accounts Payable Ageing Report Component" )
	public void validateGenUsgReportWithAccountPayble() throws Exception
	{
		try
		{

			ReportsAndExtracts reportsAndExtracts = new ReportsAndExtracts( path, workBookName, sheetName, "RepExtAdhocFlValidate_GenUsgRep_AccountPayable" );
			reportsAndExtracts.reportExtFileValidationWithFileNm();
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}

	}

	@Test( priority = 14, enabled = true, description = "'Report and Extracts'  creation for 'Generic Usage Report'creation with 'Accounts Receivables Ageing Report Component" )
	public void creationGenUsgReportWithAccountReceivables() throws Exception
	{
		try
		{

			ReportsAndExtracts reportsAndExtracts = new ReportsAndExtracts( path, workBookName, sheetName, "RepAndExtCreation_GenUsgRep_AccountReceivables" );
			reportsAndExtracts.reportAndExtCreation();
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

	@Test( priority = 15, enabled = true, description = "'Report and Extracts'  Validate adhoc file for 'Generic Usage Report'creation with 'Accounts Receivables Ageing Report Component" )
	public void validateGenUsgReportWithAccountReceivables() throws Exception
	{
		try
		{

			ReportsAndExtracts reportsAndExtracts = new ReportsAndExtracts( path, workBookName, sheetName, "RepExtAdhocFlValidate_GenUsgRep_AccountReceivables" );
			reportsAndExtracts.reportExtFileValidationWithFileNm();
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}

	}

	@Test( priority = 16, enabled = true, description = "'Report and Extracts'  creation for 'Generic Usage Report'creation with 'Usage Revenue Cost Detail Report Component" )
	public void creationGenUsgReportWithUsgDetail() throws Exception
	{
		try
		{

			ReportsAndExtracts reportsAndExtracts = new ReportsAndExtracts( path, workBookName, sheetName, "RepAndExtCreation_GenUsgRep_UsgDetail" );
			reportsAndExtracts.reportAndExtCreation();
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

	@Test( priority = 17, enabled = true, description = "'Report and Extracts'  Validate adhoc file for 'Generic Usage Report'creation with 'Usage Revenue Cost Detail Report Component" )
	public void validateGenUsgReportWithUsgDetail() throws Exception
	{
		try
		{

			ReportsAndExtracts reportsAndExtracts = new ReportsAndExtracts( path, workBookName, sheetName, "RepExtAdhocFlValidate_GenUsgRep_UsgDetail" );
			reportsAndExtracts.reportExtFileValidationWithFileNm();
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}

	}

	@Test( priority = 18, enabled = true, description = "'Report and Extracts'  creation for 'Generic Usage Report'creation with 'Usage Revenue Cost Overview Report Component" )
	public void creationGenUsgReportWithUsgOverview() throws Exception
	{
		try
		{

			ReportsAndExtracts reportsAndExtracts = new ReportsAndExtracts( path, workBookName, sheetName, "RepAndExtCreation_GenUsgRep_UsgOverview" );
			reportsAndExtracts.reportAndExtCreation();
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

	@Test( priority = 19, enabled = true, description = "'Report and Extracts'  Validate adhoc file for 'Generic Usage Report'creation with 'Usage Revenue Cost Overview Report Component" )
	public void validateGenUsgReportWithUsgOverview() throws Exception
	{
		try
		{

			ReportsAndExtracts reportsAndExtracts = new ReportsAndExtracts( path, workBookName, sheetName, "RepExtAdhocFlValidate_GenUsgRep_UsgOverview" );
			reportsAndExtracts.reportExtFileValidationWithFileNm();
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}

	}
}
