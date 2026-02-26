package com.subex.rocps.automation.testcases.functionaltesting;

import com.subex.rocps.automation.helpers.application.carrierinvoice.CarrierInvoice;
import com.subex.rocps.automation.helpers.application.matchandrate.EventIdentiferDefinition;
import com.subex.rocps.automation.helpers.application.matchandrate.EventMatchRule;
import com.subex.rocps.automation.helpers.application.matchandrate.EventMatchRuleGroup;
import com.subex.rocps.automation.helpers.application.matchandrate.Operator;
import com.subex.rocps.automation.helpers.application.networkConfiguraiton.Route;
import com.subex.rocps.automation.helpers.application.networkConfiguraiton.RouteGroup;
import com.subex.rocps.automation.helpers.application.networkConfiguraiton.Switch;
import com.subex.rocps.automation.helpers.application.partnerConfiguration.Account;
import com.subex.rocps.automation.helpers.application.partnerConfiguration.Agent;
import com.subex.rocps.automation.helpers.application.partnerConfiguration.BillProfile;
import com.subex.rocps.automation.helpers.application.system.Streams;
import com.subex.rocps.automation.helpers.application.tariffs.PSTariffHelper;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;

import com.subex.automation.helpers.application.screens.BandHelper;
import com.subex.automation.helpers.application.screens.ElementCreateHelper;
import com.subex.automation.helpers.application.screens.RecurringTaskHelper;
import com.subex.automation.helpers.application.screens.TariffClassHelper;
import com.subex.automation.helpers.application.screens.TariffHelper;
import com.subex.automation.helpers.application.screens.TriggerHelper;
import com.subex.automation.helpers.util.FailureHelper;

public class TCAccrualPrerequisites extends PSAcceptanceTest
{
	String path = System.getProperty( "user.dir" ) + "\\src\\main\\resources\\";
	String workBookName = "FunctionalTestCases.xlsx";
	String sheetName = "AccrualPrerequisites";
	

	
	@org.testng.annotations.Test( priority = 1, description = "Mnr for Carrier Invoice", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void ciPrerequisite1() throws Exception
	{
		try
		{
			Account accobj = new Account( path, workBookName, sheetName, "AccountAccrual", 1 );
			accobj.accountCreation();
			
			
			BillProfile billObj = new BillProfile( path, workBookName, sheetName, "BillProfileAccrual", 1 );
			billObj.billProfileCreation();
			/*	
				CarrierInvoice ciObj = new CarrierInvoice( path, workBookName, sheetName, "CI_ManualTmpl1_Accrual", 1 );
				ciObj.carrierInvoiceSearchConfig();*/
		
			Operator ope4Obj = new Operator( path, workBookName, "Operator", "Operator AllFields", 1 );
			ope4Obj.operatorCreation();
			
			Switch switchObj = new Switch( path, workBookName, "Switch", "Switch" );
			switchObj.configureSwitch();			
			
			RouteGroup routeGrpObj = new RouteGroup( path, workBookName, sheetName, "RouteGroup Accrual" );
			routeGrpObj.routeGrpCreation();
			
			Route routeColObj = new Route( path, workBookName, sheetName, "Routes Accrual" );
			routeColObj.routeCreation();
			
			

		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}
	
		
		
		@org.testng.annotations.Test( priority = 3, description = "tariff class creation" , retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class)
		public void ciTariffClass() throws Exception
		{
			try
			{			
				TariffClassHelper trffClassObj = new TariffClassHelper();
				trffClassObj.createTariffClass( path, workBookName, sheetName, "TariffClass Outgoing", 1 );
			}
			catch ( Exception e )
			{
				FailureHelper.reportFailure( e );
				throw e;
			}
		}
	
	@org.testng.annotations.Test( priority = 4, description = "tariff class creation" , retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class)
	public void ciEMRCreation() throws Exception
	{
		try
		{			
			PSTariffHelper pstrffObj1 = new PSTariffHelper();
			pstrffObj1.createTariff( path, workBookName, sheetName, "Tariff OutgoingAccrual", 1 );
			pstrffObj1.createFastEntry( path, workBookName, sheetName, "Tariff Outgoing FastEntry1", 1 );
			
			
			pstrffObj1.createTariff( path, workBookName, sheetName, "Tariff OutgoingDeal2", 1 );
			pstrffObj1.createFastEntry( path, workBookName, sheetName, "Tariff Outgoing2 FastEntry", 1 );

			EventMatchRuleGroup eventValObj = new EventMatchRuleGroup( path, workBookName, "EventMatchRuleGroup", "EMRG-CI", 1 );
			eventValObj.configureEventMatchRuleGroup();
			
			EventMatchRule emrObj = new EventMatchRule( path, workBookName, sheetName, "EMR AccrualOut" );
			emrObj.configureEventMatchRule();
		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}
	
	/*@org.testng.annotations.Test( priority = 5, description = "edit Voice stream- Accrual" )
	public void editVoicestream() throws Exception
	{
		try
		{
			Streams streamObj = new Streams();
			streamObj.editStreamConfig( path, workBookName, "AccrualPrerequisites", "VoiceStreamsAccruals", 1 );
			streamObj.accrualStreamConfig( path, workBookName, "AccrualPrerequisites", testCaseName, 1 );
			streamObj.saveStreamDetail();
	
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}
	@org.testng.annotations.Test( priority = 6, description = "Trigger creation" )
	public void createTrigger() throws Exception
	{
		try
		{
	
			TriggerHelper triggerObj = new TriggerHelper();
			triggerObj.createTrigger( path, workBookName, sheetName, "Trigger", 1 );
	
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}
	
	@org.testng.annotations.Test( priority = 7, description = "Recurring task creation" )
	public void createRecurringTask() throws Exception
	{
		try
		{
	
			RecurringTaskHelper triggerObj = new RecurringTaskHelper();
			triggerObj.createRecurringTask( path, workBookName, sheetName, "RecurringTask", 1 );
	
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}*/

}
