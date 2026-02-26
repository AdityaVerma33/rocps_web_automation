package com.subex.rocps.automation.testcases.functionaltesting;

import com.subex.rocps.automation.helpers.application.accruals.EstimationProcessor;
import com.subex.rocps.automation.helpers.application.system.Streams;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;

import com.subex.automation.helpers.util.FailureHelper;

public class TCEstimationProcessor extends PSAcceptanceTest
{
	String path = System.getProperty( "user.dir" ) + "\\src\\main\\resources\\";
	String workBookName = "FunctionalTestCases.xlsx";
	String sheetName = "EstimationProcessor";
	
	@org.testng.annotations.Test( priority = 1, description = "Estimation Processor Column validation" )
	public void estimationProcessorColVal() throws Exception
	{
		try
		{
			EstimationProcessor estObj = new EstimationProcessor( path, workBookName, sheetName, "EstProcessorColVal" );
			estObj.searchScreenColumnsValidation();

		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}
	
	@org.testng.annotations.Test( priority = 2, description = "Estimation Processor Creation" )
	public void estimationProcessorCreation() throws Exception
	{
		try
		{
			EstimationProcessor estObj = new EstimationProcessor( path, workBookName, sheetName, "EstProcessor" );
			estObj.configureEstimationProcessor();

		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}
	
	@org.testng.annotations.Test( priority = 3, description = "Estimation Processor Edit" )
	public void estimationProcessorEdit() throws Exception
	{
		try
		{
			EstimationProcessor estObj = new EstimationProcessor( path, workBookName, sheetName, "EstProcessorEdit" );
			estObj.estimationProcessorEdit();

		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}
	
	@org.testng.annotations.Test( priority = 4, description = "Estimation Processor Delete" )
	public void estimationProcessorDelete() throws Exception
	{
		try
		{
			EstimationProcessor estObj = new EstimationProcessor( path, workBookName, sheetName, "EstProcessorDelete" );
			estObj.estimationProcessorDelete();
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}
	
	@org.testng.annotations.Test( priority = 5, description = "Estimation Processor UnDelete" )
	public void estimationProcessorUnDelete() throws Exception
	{
		try
		{
			EstimationProcessor estObj = new EstimationProcessor( path, workBookName, sheetName, "EstProcessorUnDelete" );
			estObj.estimationProcessorUnDelete();

		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}
}
