package com.subex.rocps.automation.testcases.functionaltesting;

import org.testng.annotations.Test;

import com.subex.automation.helpers.util.FailureHelper;
import com.subex.rocps.automation.helpers.application.deal.Deal;
import com.subex.rocps.automation.helpers.application.system.PSTaskSearchHelper;
import com.subex.rocps.automation.helpers.application.vendorRateManagement.RoutingRateReport;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;

public class TCRoutingRateReport extends PSAcceptanceTest
{
	String path = System.getProperty( "user.dir" ) + "\\src\\main\\resources\\";

	String workBookName = "FunctionalTestCasesRO.xlsx";
	String sheetName = "RoutingRateReport";

	@Test( priority = 1, enabled = true, description = "' Routing Rate Report'  verify the column Headers Of Search Screen" )
	public void verifyTheColHdrs() throws Exception
	{
		try
		{
			RoutingRateReport routingRateReport = new RoutingRateReport( path, workBookName, sheetName, "RoutingRateReportScreencolVal" );
			routingRateReport.routingRateReportColumsValidation();

		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}
	
	@Test( priority = 2, enabled = true, description = "' Routing Rate Report'  calculation Routing Rate" )
	public void routingRateReportCalculation() throws Exception
	{
		try
		{
			RoutingRateReport routingRateReport = new RoutingRateReport( path, workBookName, sheetName, "RoutingRateReportCalculation" );
			routingRateReport.routingRateReportCalculation();
			PSTaskSearchHelper psTaskobj = new PSTaskSearchHelper();
			psTaskobj.psVerifyTaskStatus( path, workBookName, sheetName, "RoutingRateCalculationTaskStatus", 1 );


		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}
	@Test( priority = 3, enabled = true, description = "' Routing Rate Report'  validate search result" )
	public void routingRateRepValidateSearchResult() throws Exception
	{
		try
		{
			RoutingRateReport routingRateReport = new RoutingRateReport( path, workBookName, sheetName, "RoutingRateReportCalculationSearchResult" );
			routingRateReport.routingRateReportSearchResult();


		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}
	@Test( priority = 4, enabled = true, description = "' Routing Rate Report'  PenaltyRemoved" )
	public void routingRateRepPenaltyRemoved() throws Exception
	{
		try
		{
			RoutingRateReport routingRateReport = new RoutingRateReport( path, workBookName, sheetName, "RoutingRateReportPenaltyRemoved" );
			routingRateReport.routingRateReportSearchResult();


		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}
	@Test( priority = 5, enabled = true, description = "' Routing Rate Report'  PenaltyAdded" )
	public void routingRateRepPenaltyAdded() throws Exception
	{
		try
		{
			RoutingRateReport routingRateReport = new RoutingRateReport( path, workBookName, sheetName, "RoutingRateReportPenaltyAdded" );
			routingRateReport.routingRateReportSearchResult();


		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}
	
}
