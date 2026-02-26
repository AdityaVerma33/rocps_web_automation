package com.subex.rocps.automation.testcases.functionaltesting;

import com.subex.automation.helpers.util.FailureHelper;
import com.subex.rocps.automation.helpers.application.referenceTable.ServiceTariffType;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;

public class TCServiceTariffType extends PSAcceptanceTest
{

	String path = System.getProperty( "user.dir" ) + "\\src\\main\\resources\\";
	String workBookName = "FunctionalTestCases.xlsx";
	String sheetName = "ServiceTariffType";

	@org.testng.annotations.Test( priority = 1, description = "service Tariff type creation", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void serviceTariffTypeCreation() throws Exception
	{
		try
		{
			ServiceTariffType trfObj = new ServiceTariffType( path, workBookName, sheetName, "serviceTariffType", 1 );
			trfObj.serviceTariffTypeCreation();
		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 2, description = "service Tariff type deletion" )
	public void serviceTariffTypeDelete() throws Exception
	{
		try
		{
			ServiceTariffType trfDelObj = new ServiceTariffType( path, workBookName, sheetName, "ServiceTariffTypeDelete", 1 );
			trfDelObj.serviceTariffTypeDelete();
		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 3, description = "service Tariff type un delete" )
	public void serviceTariffTypeUnDelete() throws Exception
	{
		try
		{
			ServiceTariffType trfUnDelObj = new ServiceTariffType( path, workBookName, sheetName, "ServiceTariffTypeUnDelete", 1 );
			trfUnDelObj.serviceTariffTypeUnDelete();
		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 4, description = "service Tariff type search screen column validation" )
	public void serviceTariffTypeColVal() throws Exception
	{
		try
		{
			ServiceTariffType trfColValObj = new ServiceTariffType( path, workBookName, sheetName, "ServiceTariffSearchScreencolVal", 1 );
			trfColValObj.searchScreenColumnsValidation();
		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 5, description = "service Tariff type creation1 SCC", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void serviceTariffTypeCreation1() throws Exception
	{
		try
		{
			ServiceTariffType trfObj = new ServiceTariffType( path, workBookName, sheetName, "ServiceTariffType1", 1 );
			trfObj.serviceTariffTypeCreation();
		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 6, description = "service Tariff type creation2 GSS", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void serviceTariffTypeCreation2() throws Exception
	{
		try
		{
			ServiceTariffType trfObj = new ServiceTariffType( path, workBookName, sheetName, "ServiceTariffType2", 1 );
			trfObj.serviceTariffTypeCreation();
		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 7, description = "service Tariff type edit" )
	public void serviceTariffTypeEdit() throws Exception
	{
		try
		{
			ServiceTariffType trfObj = new ServiceTariffType( path, workBookName, sheetName, "ServiceTariffTypeEdit", 1 );
			trfObj.serviceTariffTypeEdit();
		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}
}
