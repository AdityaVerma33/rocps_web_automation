package com.subex.rocps.automation.testcases.functionaltesting;

import com.subex.rocps.automation.helpers.application.aggregation.AggregationResult;
import com.subex.rocps.automation.helpers.application.bills.Bills;
import com.subex.rocps.automation.helpers.application.matchandrate.EventMatchRule;
import com.subex.rocps.automation.helpers.application.matchandrate.EventMatchRuleGroup;
import com.subex.rocps.automation.helpers.application.networkConfiguraiton.Route;
import com.subex.rocps.automation.helpers.application.networkConfiguraiton.RouteGroup;
import com.subex.rocps.automation.helpers.application.partnerConfiguration.BillProfile;
import com.subex.rocps.automation.helpers.application.system.PSTaskSearchHelper;
import com.subex.rocps.automation.helpers.application.system.TaskSchedule;
import com.subex.rocps.automation.helpers.application.tariffs.PSTariffHelper;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;

import com.subex.automation.helpers.application.screens.TariffHelper;
import com.subex.automation.helpers.application.screens.TaskSearchHelper;
import com.subex.automation.helpers.util.FailureHelper;

public class TCBillAction extends PSAcceptanceTest
{
	String path = System.getProperty( "user.dir" ) + "\\src\\main\\resources\\";
	String workBookName = "FunctionalTestCases.xlsx";
	String sheetName = "BillActions";

	@org.testng.annotations.Test( priority = 1, description = "Mnr for bills" , retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class)
	public void matchAndRate() throws Exception
	{
		try
		{
			BillProfile billObj = new BillProfile( path, workBookName, "BillProfile", "BillProfileBills", 1 );
			billObj.billProfileCreation();

			RouteGroup routeGrpObj = new RouteGroup( path, workBookName, "RouteGrp", "RouteGroupBills" );
			routeGrpObj.routeGrpCreation();

			Route routeColObj = new Route( path, workBookName, "Route", "Routes Bills" );
			routeColObj.routeCreation();

			PSTariffHelper pstrffObj1 = new PSTariffHelper();
			pstrffObj1.createTariff( path, workBookName, "Tariff", "Tariff Bills", 1 );
			pstrffObj1.createFastEntry( path, workBookName, "Tariff", "Tariff BillsFastEntry", 1 );

			EventMatchRuleGroup eventValObj = new EventMatchRuleGroup( path, workBookName, "EventMatchRuleGroup", "EMRG-Bills", 1 );
			eventValObj.configureEventMatchRuleGroup();

			EventMatchRule emrObj = new EventMatchRule( path, workBookName, "EMR", "EMR Bills" );
			emrObj.configureEventMatchRule();

		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 2, description = "Mnr and Aggregation for bills" )
	public void mnrAndAggregation() throws Exception
	{
		try
		{
			TaskSchedule taskObj = new TaskSchedule();
			taskObj.fileCollection( path, workBookName, sheetName, "FileSchedule Bill", 1 );

			PSTaskSearchHelper tskObj = new PSTaskSearchHelper();
			tskObj.psVerifyTaskStatus( path, workBookName, "AggregationResults", "MnrTaskStatus", 1 );			

		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}
	
	@org.testng.annotations.Test( priority = 3, description = "Mnr and Aggregation for bills" )
	public void recurringTask() throws Exception
	{
		try
		{
			TaskSchedule taskObj = new TaskSchedule();	
			PSTaskSearchHelper tskObj = new PSTaskSearchHelper();

			taskObj.scheduleRecurringTask( path, workBookName, "AggregationResults", "RecurringTask", 1 );

			tskObj.psVerifyTaskStatus( path, workBookName, "AggregationResults", "AggrTaskStatus", 1 );

			AggregationResult aggrResObj = new AggregationResult( path, workBookName, sheetName, "AggregationResult Bills" );
			aggrResObj.viewAggregationResult();

		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 4, description = "HotBill creation-NonDelta_regenerate" )
	public void createBillNonDelta() throws Exception
	{
		try
		{

			Bills billObj = new Bills( path, workBookName, sheetName, "NonDelta Bill-Reg" );
			billObj.billAction();

			AggregationResult aggrResObj = new AggregationResult( path, workBookName, sheetName, "Aggradjustments-NonDeltaRegenrate" );
			aggrResObj.addAdjustments();

			Bills bill1Obj = new Bills( path, workBookName, sheetName, "HotBill RegenerateNonDelta" );
			bill1Obj.billAction();
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 5, description = "HotBill creation-Delta_regenerate" )
	public void createBillDelta() throws Exception
	{
		try
		{

			Bills billObj = new Bills( path, workBookName, sheetName, "DeltaBill Reg" );
			billObj.billAction();

			AggregationResult aggrResObj = new AggregationResult( path, workBookName, sheetName, "Aggradjustments-DeltaRegenrate" );
			aggrResObj.addAdjustments();

			Bills bill1Obj = new Bills( path, workBookName, sheetName, "HotBill RegenerateDelta" );
			bill1Obj.billAction();
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 6, description = "HotBill creation-NonDelta_reversion" )
	public void createBillNonDeltaReversion() throws Exception
	{
		try
		{

			Bills billObj = new Bills( path, workBookName, sheetName, "NonDelta Bill-Reversion" );
			billObj.billAction();

			AggregationResult aggrResObj = new AggregationResult( path, workBookName, sheetName, "Aggradjustments-NonDeltaReversion" );
			aggrResObj.addAdjustments();

			Bills bill1Obj = new Bills( path, workBookName, sheetName, "HotBill ReversionNonDelta" );
			bill1Obj.billAction();
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 7, description = "HotBill creation-Delta_reversion" )
	public void createBillDeltaReversion() throws Exception
	{
		try
		{

			Bills billObj = new Bills( path, workBookName, sheetName, "Delta Bill-Reversion" );
			billObj.billAction();

			AggregationResult aggrResObj = new AggregationResult( path, workBookName, sheetName, "Aggradjustments-DeltaReversion" );
			aggrResObj.addAdjustments();

			Bills bill1Obj = new Bills( path, workBookName, sheetName, "HotBill ReversionDelta" );
			bill1Obj.billAction();
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}
}
