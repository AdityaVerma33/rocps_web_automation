package com.subex.rocps.automation.testcases.functionaltesting;

import org.testng.annotations.Test;

import com.subex.automation.helpers.application.screens.BandHelper;
import com.subex.automation.helpers.application.screens.ElementCreateHelper;
import com.subex.automation.helpers.application.screens.TariffClassHelper;
import com.subex.automation.helpers.application.screens.TariffHelper;
import com.subex.automation.helpers.util.FailureHelper;
import com.subex.rocps.automation.helpers.application.matchandrate.Operator;
import com.subex.rocps.automation.helpers.application.networkConfiguraiton.Route;
import com.subex.rocps.automation.helpers.application.networkConfiguraiton.RouteGroup;
import com.subex.rocps.automation.helpers.application.networkConfiguraiton.Switch;
import com.subex.rocps.automation.helpers.application.partnerConfiguration.Account;
import com.subex.rocps.automation.helpers.application.partnerConfiguration.Agent;
import com.subex.rocps.automation.helpers.application.partnerConfiguration.BillProfile;
import com.subex.rocps.automation.helpers.application.system.Streams;
import com.subex.rocps.automation.helpers.application.tariffs.PSTariffHelper;
import com.subex.rocps.automation.helpers.application.xdrextraction.XdrExtrTempHelper;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;

public class TCEventUsgRequestPrerequisite extends PSAcceptanceTest
{
	String path = System.getProperty( "user.dir" ) + "\\src\\main\\resources\\";
	String workBookName = "FunctionalTestCases.xlsx";
	String sheetName = "EventUsgPrerequisite";

	@org.testng.annotations.Test( priority = 1, description = "Streams Stage Creation  for 'ROCPS Export All Rows'", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void createStreamStageForRocPSExpAllRorws() throws Exception
	{

		try
		{
			String partition = null;
			Streams streamObj = new Streams();
			streamObj.editStreamConfig( path, workBookName, "EventUsgPrerequisite", "Streams", 1 );
			streamObj.rocpsExpAllRowsStreamStageConfig( path, workBookName, "EventUsgPrerequisite", "ROCPSExportAllRowsStreamStage", 1 );
			streamObj.saveStreamDetail();
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 2, enabled = true, description = "Mnr for Xdr prerequistes", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void XdrPrerequistes1() throws Exception

	{
		try
		{

			Agent agobj = new Agent( path, workBookName, "Agent", "Agent", 1 );
			agobj.agentCreation();

			Account accobj = new Account( path, workBookName, "Account", "Account_Xdr", 1 );
			accobj.accountCreation();

			BillProfile billObj = new BillProfile( path, workBookName, "BillProfile", "BillProfile_Xdr", 1 );
			billObj.billProfileCreation();

		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 3, enabled = true, description = "Mnr for Xdr prerequistes", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void XdrPrerequistes2() throws Exception

	{
		try
		{

			Switch switchObj = new Switch( path, workBookName, "Switch", "Switch" );
			switchObj.configureSwitch();

			Operator ope1Obj = new Operator( path, workBookName, "Operator", "Operator_Xdr", 1 );
			ope1Obj.operatorCreation();

			RouteGroup routeGrpObj = new RouteGroup( path, workBookName, "RouteGrp", "RouteGroup_Xdr" );
			routeGrpObj.routeGrpCreation();

			Route routeColObj = new Route( path, workBookName, "Route", "Route_Xdr" );
			routeColObj.routeCreation();

		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 4, enabled = true, description = "Mnr for Xdr prerequistes", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void XdrPrerequistes3() throws Exception

	{
		try
		{

			ElementCreateHelper eleObj = new ElementCreateHelper();
			eleObj.createElement( path, workBookName, "Elements", "Elements AdvanceRating", 1 );

			BandHelper bandObj = new BandHelper();
			bandObj.createBand( path, workBookName, "Bands", "Bands AdvanceRating", 1 );

			TariffClassHelper trffClassObj = new TariffClassHelper();
			trffClassObj.createTariffClass( path, workBookName, "TariffClass", "TariffClass OutAdvanceRating", 1 );

			PSTariffHelper pstrffObj1 = new PSTariffHelper();
			pstrffObj1.createTariff( path, workBookName, "Tariff", "Tariff Outadvance", 1 );
			pstrffObj1.createFastEntry( path, workBookName, "Tariff", "Tariff OutAdvanceFastEntry", 1 );

		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}

	@Test( priority = 5, enabled = true, description = "XDR Extraction Template creation without Bill", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void createXdrExtTempWithoutBill() throws Exception
	{
		try
		{
			XdrExtrTempHelper xdrExtrTempHelper = new XdrExtrTempHelper( path, workBookName, "XDR Extraction", "XdrExtTempCreationWithoutBill" );
			xdrExtrTempHelper.xdrExtTemplCreation();

		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

	@Test( priority = 6, enabled = true, description = "XDR Extraction Template creation with Bill", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void createXdrExtTempWithBill() throws Exception
	{
		try
		{
			XdrExtrTempHelper xdrExtrTempHelper = new XdrExtrTempHelper( path, workBookName, "XDR Extraction", "XdrExtTempCreationWithBill" );
			xdrExtrTempHelper.xdrExtTemplCreation();

		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

	@Test( priority = 7, enabled = true, description = "XDR Extraction Template change status to Accepted", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void changeStatusToAccepted() throws Exception
	{
		try
		{
			XdrExtrTempHelper xdrExtrTempHelper = new XdrExtrTempHelper( path, workBookName, "XDR Extraction", "XdrExtTemplateChangeStatusToAccepted" );
			xdrExtrTempHelper.changeToAcceptedStatus();

		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

}
