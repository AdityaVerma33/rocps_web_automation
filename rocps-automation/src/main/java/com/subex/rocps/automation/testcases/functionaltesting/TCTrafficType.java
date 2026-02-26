package com.subex.rocps.automation.testcases.functionaltesting;

import com.subex.rocps.automation.helpers.application.referenceTable.TrafficType;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;

import com.subex.automation.helpers.util.FailureHelper;

public class TCTrafficType extends PSAcceptanceTest
{

	String path = System.getProperty( "user.dir" ) + "\\src\\main\\resources\\";
	String workBookName = "FunctionalTestCases.xlsx";
	String sheetName = "TrafficType";

	@org.testng.annotations.Test( priority = 1, description = "TrafficType creation", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void createtariffType() throws Exception
	{
		try
		{
			TrafficType trafficObj = new TrafficType( path, workBookName, sheetName, "TrafficType" );
			trafficObj.trafficTypeCreation();

		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 2, description = "TrafficType delete" )
	public void deleteTariffType() throws Exception
	{
		try
		{
			TrafficType trafficObj = new TrafficType( path, workBookName, sheetName, "TrafficTypeDelete" );
			trafficObj.trafficTypeDelete();

		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 3, description = "TrafficType Un Delete" )
	public void undeleteTariffType() throws Exception
	{
		try
		{
			TrafficType trafficObj = new TrafficType( path, workBookName, sheetName, "TrafficTypeUnDelete" );
			trafficObj.trafficTypeUnDelete();

		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 4, description = "TrafficType search screen column validation" )
	public void tariffTypeColVal() throws Exception
	{
		try
		{
			TrafficType trafficObj = new TrafficType( path, workBookName, sheetName, "TrafficypeSearchScreencolVal" );
			trafficObj.searchScreenColumnsValidation();

		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 5, description = "TrafficType creation1 SCC", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void createtariffType1() throws Exception
	{
		try
		{
			TrafficType trafficObj = new TrafficType( path, workBookName, sheetName, "TrafficType1" );
			trafficObj.trafficTypeCreation();

		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 6, description = "TrafficType creation2 GSS", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void createtariffType2() throws Exception
	{
		try
		{
			TrafficType trafficObj = new TrafficType( path, workBookName, sheetName, "TrafficType2" );
			trafficObj.trafficTypeCreation();

		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 7, description = "TrafficType creation3 LWW", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void createtariffType3() throws Exception
	{
		try
		{
			TrafficType trafficObj = new TrafficType( path, workBookName, sheetName, "TrafficType3" );
			trafficObj.trafficTypeCreation();

		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 8, description = "TrafficType edit" )
	public void tariffTypeEdit() throws Exception
	{
		try
		{
			TrafficType trafficObj = new TrafficType( path, workBookName, sheetName, "TrafficTypeEdit" );
			trafficObj.trafficTypeEdit();

		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

}
