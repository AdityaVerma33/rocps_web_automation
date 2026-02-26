package com.subex.rocps.automation.testcases.functionaltesting;

import org.testng.annotations.Test;

import com.subex.rocps.automation.helpers.application.bilateral.BilateralRatedDetailModelling;
import com.subex.rocps.automation.helpers.application.reportingAndExtraction.ReportModelling;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;

import com.subex.automation.helpers.util.FailureHelper;

public class TCReportModelling extends PSAcceptanceTest
{
	String path = System.getProperty( "user.dir" ) + "\\src\\main\\resources\\";

	String workBookName = "FunctionalTestCases.xlsx";
	String sheetName = "ReportModelling";
	@Test( priority = 1, enabled = true, description = "'Report Modelling'  Bilateral Rated Modelling prerequisite",retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void bilateralRatedModPrerequisite() throws Exception
	{
		try
		{
			BilateralRatedDetailModelling brdObj = new BilateralRatedDetailModelling( path, workBookName, "BilateralModelling", "BilateralRated", 1 );
			brdObj.configurebrdModelling();
			BilateralRatedDetailModelling brdObj2 = new BilateralRatedDetailModelling( path, workBookName, "BilateralModelling", "BilateralRatedChange status", 1 );
			brdObj2.brdChangeStatus();
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}
	
	@Test( priority = 2, enabled = true, description = "'Report Modelling'  verify the column Headers Of Search Screen" )
	public void verifyTheColHdrs() throws Exception
	{
		try
		{
			ReportModelling reportModelling = new ReportModelling( path, workBookName, sheetName, "ReportModellingScreencolVal" );
			reportModelling.reportModellingColumsValidation();
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}
	@Test( priority = 3, enabled = true, description = "'Report Modelling'  creation with aggregation" ,retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class)
	public void reportModellingCreationWithAggregation() throws Exception
	{
		try
		{
			ReportModelling reportModelling = new ReportModelling( path, workBookName, sheetName, "ReportModellingCreationAggregationConfig" );
			reportModelling.reportModellingCreation();
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}
	@Test( priority = 4, enabled = true, description = "'Report Modelling'  creation with Bilateral Modelling",retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void reportModellingCreationWithBilateralMod() throws Exception
	{
		try
		{
			ReportModelling reportModelling = new ReportModelling( path, workBookName, sheetName, "ReportModellingCreationBrdModelling" );
			reportModelling.reportModellingCreation();
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}
	@Test( priority = 5, enabled = true, description = "'Report Modelling'  edit",retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void reportModellingEdit() throws Exception
	{
		try
		{
			ReportModelling reportModelling = new ReportModelling( path, workBookName, sheetName, "ReportModellingEdit" );
			reportModelling.reportModellingEdit();
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}
	@Test( priority = 6, enabled = true, description = "'Report Modelling'  changing the status to Accepted",retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void reportModellingChangingStatus() throws Exception
	{
		try
		{
			ReportModelling reportModelling = new ReportModelling( path, workBookName, sheetName, "ReportModelling_ChangeStatus" );
			reportModelling.reportModellingAccepted();
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}
	@Test( priority = 7, enabled = true, description = "'Report Modelling'  changing the status to Discontinue",retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void reportModellingDiscontinue() throws Exception
	{
		try
		{
			ReportModelling reportModelling = new ReportModelling( path, workBookName, sheetName, "ReportModelling_Discontinue" );
			reportModelling.reportModellingDicountinue();
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}
	@Test( priority = 8, enabled = true, description = "'Report Modelling'  delete action" )
	public void reportModellingDelete() throws Exception
	{
		try
		{
			ReportModelling reportModelling = new ReportModelling( path, workBookName, sheetName, "ReportModelling_Delete" );
			reportModelling.reportModellingDelete();
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}
	@Test( priority = 9, enabled = true, description = "'Report Modelling' Undelete action" )
	public void reportModellingUnDelete() throws Exception
	{
		try
		{
			ReportModelling reportModelling = new ReportModelling( path, workBookName, sheetName, "ReportModelling_UnDelete" );
			reportModelling.reportModellingUnDelete();
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}
}
