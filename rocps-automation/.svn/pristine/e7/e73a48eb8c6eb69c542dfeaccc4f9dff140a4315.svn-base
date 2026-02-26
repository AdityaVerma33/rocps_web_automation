package com.subex.rocps.automation.testcases.functionaltesting;

import org.testng.annotations.Test;

import com.subex.automation.helpers.application.screens.BandHelper;
import com.subex.automation.helpers.application.screens.ElementCreateHelper;
import com.subex.automation.helpers.application.screens.TariffClassHelper;
import com.subex.automation.helpers.application.screens.TariffHelper;
import com.subex.automation.helpers.application.screens.TaskControllerHelper;
import com.subex.automation.helpers.application.screens.TaskSearchHelper;
import com.subex.automation.helpers.util.FailureHelper;

import com.subex.rocps.automation.helpers.application.aggregation.AggregationResult;
import com.subex.rocps.automation.helpers.application.bills.Bills;
import com.subex.rocps.automation.helpers.application.eventErrors.EventErrorHelper;
import com.subex.rocps.automation.helpers.application.matchandrate.EventMatchRule;
import com.subex.rocps.automation.helpers.application.matchandrate.EventMatchRuleGroup;
import com.subex.rocps.automation.helpers.application.matchandrate.Operator;
import com.subex.rocps.automation.helpers.application.networkConfiguraiton.Route;
import com.subex.rocps.automation.helpers.application.networkConfiguraiton.RouteGroup;
import com.subex.rocps.automation.helpers.application.networkConfiguraiton.Switch;
import com.subex.rocps.automation.helpers.application.partnerConfiguration.Account;
import com.subex.rocps.automation.helpers.application.partnerConfiguration.Agent;
import com.subex.rocps.automation.helpers.application.partnerConfiguration.BillProfile;
import com.subex.rocps.automation.helpers.application.system.PSTaskSearchHelper;
import com.subex.rocps.automation.helpers.application.system.TaskSchedule;
import com.subex.rocps.automation.helpers.application.tariffs.PSTariffHelper;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;

public class TCEventErrorTest extends PSAcceptanceTest
{
	String path = System.getProperty( "user.dir" ) + "\\src\\main\\resources\\";

	String workBookName = "FunctionalTestCases.xlsx";
	String sheetName = "EventError";

	@org.testng.annotations.Test( priority = 1, enabled = true, description = "task Controller capabilities", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class  )
	public void taskControllerCapabilities() throws Exception
	{
		try
		{

			TaskControllerHelper taskObj = new TaskControllerHelper();
			taskObj.setTaskControllerCapability( path, workBookName, "ROCPreRequisites2", "TCCapability", 1 );

		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 2, enabled = true, description = "Mnr for EventErrorPrerequistes", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void EventErrorPrerequistes1() throws Exception

	{
		try
		{
			Agent agobj = new Agent( path, workBookName, "Agent", "Agent", 1 );
			agobj.agentCreation();

			Account accobj = new Account( path, workBookName, "Account", "Account_Dispute", 1 );
			accobj.accountCreation();

			BillProfile billObj = new BillProfile( path, workBookName, "BillProfile", "BillProfile_EveErr", 1 );
			billObj.billProfileCreation();

		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 3, enabled = true, description = "Mnr for EventErrorPrerequistes", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void EventErrorPrerequistes2() throws Exception

	{
		try
		{

			Switch switchObj = new Switch( path, workBookName, "Switch", "Switch" );
			switchObj.configureSwitch();

			Operator ope1Obj = new Operator( path, workBookName, "Operator", "Operator_Dispute", 1 );
			ope1Obj.operatorCreation();

			RouteGroup routeGrpObj = new RouteGroup( path, workBookName, "RouteGrp", "RouteGroup_EveError" );
			routeGrpObj.routeGrpCreation();

		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 4, enabled = true, description = "Mnr for EventErrorPrerequistes", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void EventErrorPrerequistes3() throws Exception

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

	@org.testng.annotations.Test( priority = 5, enabled = true, description = "Mnr for EventErrorPrerequistes", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void EventErrorPrerequistes4() throws Exception

	{
		try
		{

			EventMatchRuleGroup eventValObj = new EventMatchRuleGroup( path, workBookName, "EventMatchRuleGroup", "EMRG  OutAdvancedRating", 1 );
			eventValObj.configureEventMatchRuleGroup();

			EventMatchRule emrObj = new EventMatchRule( path, workBookName, "EMR", "EMR_EveErrors" );
			emrObj.configureEventMatchRule();

		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 6, enabled = true, description = "EventErrorServerPrerequistes" )
	public void EventErrorServerPrerequistes() throws Exception

	{
		try
		{
			TaskSchedule taskschedule = new TaskSchedule();
			taskschedule.fileCollection( path, workBookName, sheetName, "FileSchedule_EventErr", 1 );

			PSTaskSearchHelper tskObj = new PSTaskSearchHelper();
			tskObj.psVerifyTaskStatus( path, workBookName, sheetName, "MnrTaskStatusEventError", 1 );

		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}

	@Test( priority = 7, enabled = true, description = "Event Error verify the column Headers Of Search Screen" )
	public void verifyTheColHdrs() throws Exception
	{
		try
		{
			EventErrorHelper eventErrObj = new EventErrorHelper( path, workBookName, sheetName, "VerifyColumnHeaders" );
			eventErrObj.verifyTheColHdrsOfEveErrSrch();
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

	@Test( priority = 8, enabled = true, description = "Event Error  Validate Event error search result" )
	public void validateEventErrSearchResult() throws Exception
	{
		try
		{
			EventErrorHelper eventErrObj = new EventErrorHelper( path, workBookName, sheetName, "EventErrorValidateSearchResult" );
			eventErrObj.validateSearchResult();
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 9, enabled = true, description = "Correcting the configuration for EventError", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void EventErrorPrerequistesForCorrectData() throws Exception

	{
		try
		{

			Switch switchObj = new Switch( path, workBookName, "Switch", "SwitchEveError" );
			switchObj.configureSwitch();

			Route routeColObj = new Route( path, workBookName, "Route", "Route_EveErrors" );
			routeColObj.routeCreation();

		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}

	@Test( priority = 10, enabled = true, description = "Event Error reprocess suspense error without correction" )
	public void reprSuspeErrWithoutCorrection() throws Exception
	{
		try
		{
			EventErrorHelper eventErrObj = new EventErrorHelper( path, workBookName, sheetName, "ReprSuspenseError_WithoutCorrection", 1 );
			eventErrObj.reprocessSuspensErr();
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

	@Test( priority = 11, enabled = true, description = "Event Error assign To action" )
	public void assignToAction() throws Exception
	{
		try
		{
			EventErrorHelper eventErrObj = new EventErrorHelper( path, workBookName, sheetName, "EventErrorAssignTo", 1 );
			eventErrObj.assignToAction();
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

	@Test( priority = 12, enabled = true, description = "Event Error change the status action" )
	public void changeStatusAction() throws Exception
	{
		try
		{
			EventErrorHelper eventErrObj = new EventErrorHelper( path, workBookName, sheetName, "EventErrorChangeTheStatus", 1 );
			eventErrObj.changeStatusAction();
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

	@Test( priority = 13, enabled = true, description = "Event Error reprocess suspense error in Diagnostic mode" )
	public void reprocessSuspensesErrInDiagMd() throws Exception
	{
		try
		{
			EventErrorHelper eventErrObj = new EventErrorHelper( path, workBookName, sheetName, "ReprSuspenseErrorInDiagMd" );
			eventErrObj.reprocessSuspensErrinDiagMd();
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

	@Test( priority = 14, enabled = true, description = "Event Error reprocess suspense error with Error message" )
	public void reprSuspensesErrWithErrorMsz() throws Exception
	{
		try
		{
			EventErrorHelper eventErrObj = new EventErrorHelper( path, workBookName, sheetName, "RepSuspenseError_ErrorMsz" );
			eventErrObj.reprocessSuspensErr();
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

	@Test( priority = 15, enabled = true, description = "Event Error reprocess suspense error with error code" )
	public void repSuspensesErrWithErrorCode() throws Exception
	{
		try
		{
			EventErrorHelper eventErrObj = new EventErrorHelper( path, workBookName, sheetName, "RepSuspenseError_ErrorCode", 1 );
			eventErrObj.reprocessSuspensErr();
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

}
