package com.subex.rocps.automation.testcases.functionaltesting;

import org.testng.annotations.Test;

import com.subex.automation.helpers.util.FailureHelper;
import com.subex.rocps.automation.helpers.application.accruals.EstimationProcessor;
import com.subex.rocps.automation.helpers.application.bilateral.BilateralRatedDetailModelling;
import com.subex.rocps.automation.helpers.application.deal.Deal;
import com.subex.rocps.automation.helpers.application.deal.DealSimulation;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;

public class TCDealSimulation extends PSAcceptanceTest
{
	String path = System.getProperty( "user.dir" ) + "\\src\\main\\resources\\";
	String workBookName = "FunctionalTestingDeals.xlsx";
	String sheetName = "DealSimulation";

     @org.testng.annotations.Test( priority = 1, description = "Estimation Processor Creation",retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void estimationProcessorCreation() throws Exception
	{
		try
		{
			EstimationProcessor estObj = new EstimationProcessor( path, workBookName, sheetName, "TestEstProcessorCreation" );
			estObj.configureEstimationProcessor();

		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}
	@Test( priority = 2, enabled = true, description = "''  Bilateral Rated Modelling prerequisite",retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void bilateralRatedModPrerequisite() throws Exception
	{
		try
		{
			BilateralRatedDetailModelling brdObj = new BilateralRatedDetailModelling( path, "FunctionalTestCases.xlsx", "BilateralModelling", "BilateralRated", 1 );
			brdObj.configurebrdModelling();
			BilateralRatedDetailModelling brdObj2 = new BilateralRatedDetailModelling( path, "FunctionalTestCases.xlsx", "BilateralModelling", "BilateralRatedChange status", 1 );
			brdObj2.brdChangeStatus();
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 3, description = "Deal Creation-  prerequisite",retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class  )
	public void dealCreationPrerequisite() throws Exception
	{
		try
		{
			Deal dealObj = new Deal( path, workBookName, sheetName, "DealCreation" );
			dealObj.dealCreation();
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}
	@org.testng.annotations.Test( priority = 4, description = "Deal Simulation screen col validation" )
	public void dealSimuationScreenColVal() throws Exception
	{
		try
		{
			DealSimulation dealSimulation = new DealSimulation( path, workBookName, sheetName, "DealSimulationScreencolVal" );
			dealSimulation.dealSimulationColumsValidation();
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 5, description = "Deal Simulation creation with Deal Simuation Copy Type",retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void dealSimuationCreationCopyType() throws Exception
	{
		try
		{
			DealSimulation dealSimulation = new DealSimulation( path, workBookName, sheetName, "Test01DealSimulation_CreationWithCopyType" );
			dealSimulation.dealSimulationCreation();
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 6, description = "Deal Simulation creation with Deal Simuation New Type Tiered deal type",retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void dealSimuationCreationNewTypeTiered() throws Exception
	{
		try
		{
			DealSimulation dealSimulation = new DealSimulation( path, workBookName, sheetName, "Test01DealSimulation_CreationWithNewTypeTiered" );
			dealSimulation.dealSimulationCreation();
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 7, description = "Deal Simulation creation with Deal Simuation New Type Balanced deal type",retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void dealSimuationCreationNewTypeBalanced() throws Exception
	{
		try
		{
			DealSimulation dealSimulation = new DealSimulation( path, workBookName, sheetName, "Test01DealSimulation_CreationWithNewTypeBalanced" );
			dealSimulation.dealSimulationCreation();
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}
	@org.testng.annotations.Test( priority = 8, description = "Deal Simulation creation with Deal Simuation New Type without any deal ",retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void dealSimuationCreationNewTypeWithoutDeal() throws Exception
	{
		try
		{
			DealSimulation dealSimulation = new DealSimulation( path, workBookName, sheetName, "Test01DealSimulation_CreationWithNewType" );
			dealSimulation.dealSimulationCreation();
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}
	@org.testng.annotations.Test( priority = 9, description = "Deal Simulation delete " )
	public void dealSimuationDelete() throws Exception
	{
		try
		{
			DealSimulation dealSimulation = new DealSimulation( path, workBookName, sheetName, "DealSimuationDelete" );
			dealSimulation.dealSimulationDelete();
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}
	@org.testng.annotations.Test( priority = 10, description = "Deal Simulation UNdelete " )
	public void dealSimuationUnDelete() throws Exception
	{
		try
		{
			DealSimulation dealSimulation = new DealSimulation( path, workBookName, sheetName, "DealSimuationDelete" );
			dealSimulation.dealSimulationUnDelete();
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}
}
