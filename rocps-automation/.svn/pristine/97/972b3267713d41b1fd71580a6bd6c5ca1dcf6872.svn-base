package com.subex.rocps.automation.testcases.functionaltesting;

import com.subex.rocps.automation.helpers.application.aggregation.AggregationResult;
import com.subex.rocps.automation.helpers.application.bills.Bills;
import com.subex.rocps.automation.helpers.application.bills.RerateRequest;
import com.subex.rocps.automation.helpers.application.matchandrate.EventIdentifierValue;
import com.subex.rocps.automation.helpers.application.matchandrate.EventMatchRule;
import com.subex.rocps.automation.helpers.application.matchandrate.EventMatchRuleGroup;
import com.subex.rocps.automation.helpers.application.matchandrate.Operator;
import com.subex.rocps.automation.helpers.application.monitoring.PSCollectedFileSearchHelper;
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

import com.subex.automation.helpers.application.screens.BandHelper;
import com.subex.automation.helpers.application.screens.ElementCreateHelper;
import com.subex.automation.helpers.application.screens.TariffClassHelper;
import com.subex.automation.helpers.application.screens.TariffHelper;
import com.subex.automation.helpers.application.screens.TaskControllerHelper;
import com.subex.automation.helpers.application.screens.TaskSearchHelper;
import com.subex.automation.helpers.util.FailureHelper;

public class TCRerateServerCases extends PSAcceptanceTest
{
	String path = System.getProperty( "user.dir" ) + "\\src\\main\\resources\\";
	String workBookName = "FunctionalTestCases.xlsx";
	String sheetName = "RerateRequest";

	@org.testng.annotations.Test( priority = 1, description = "task Controller capabilities", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void taskControllerCapabilities() throws Exception
	{
		try
		{

			TaskControllerHelper taskObj = new TaskControllerHelper();
			taskObj.setTaskControllerCapability( path, workBookName, "RerateServer", "TCCapability", 1 );

		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 2,invocationCount = 2, description = "Account, Bill Profile for Rerate", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void reratePrerequisite1() throws Exception
	{
		try
		{
			Agent agobj = new Agent( path, workBookName, "Agent", "Agent", 1 );
			agobj.agentCreation();

			Account accobj = new Account( path, workBookName, "Account", "AccountRerate", 1 );
			accobj.accountCreation();

			BillProfile billObj = new BillProfile( path, workBookName, "RerateServer", "BillProfile Rerate", 1 );
			billObj.billProfileCreation();


		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}
	@org.testng.annotations.Test( priority = 3,invocationCount = 2, description = "Route for Rerate", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void reratePrerequisite2() throws Exception
	{
		try
		{

			Operator ope4Obj = new Operator( path, workBookName, "Operator", "Operator HomeCarrier", 1 );
			ope4Obj.operatorCreation();

			Switch switchObj = new Switch( path, workBookName, "Switch", "Switch HomeCarrier" );
			switchObj.configureSwitch();

			RouteGroup routeGrpObj = new RouteGroup( path, workBookName, "RouteGrp", "RouteGroup HomeCarrier" );
			routeGrpObj.routeGrpCreation();

			Route routeColObj = new Route( path, workBookName, "Route", "Route HomeCarrier" );
			routeColObj.routeCreation();

		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}
	@org.testng.annotations.Test( priority = 4,invocationCount = 2, description = "Mnr for Rerate Element, Band,Tariff, Evrnt iden Value", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void reratePrerequisite3() throws Exception
	{
		try
		{


			ElementCreateHelper eleObj = new ElementCreateHelper();
			eleObj.createElement( path, workBookName, "RerateServer", "Elements Rerate1st level", 1 );

			ElementCreateHelper ele1Obj = new ElementCreateHelper();
			ele1Obj.createElement( path, workBookName, "RerateServer", "Elements Rerate3rdLevel", 1 );

			BandHelper bandObj = new BandHelper();
			bandObj.createBand( path, workBookName, "RerateServer", "Bands 1st level", 1 );

			TariffClassHelper trffClassObj = new TariffClassHelper();
			trffClassObj.createTariffClass( path, workBookName, "RerateServer", "TariffClass Rerate 1st level", 1 );

			PSTariffHelper pstrffObj1 = new PSTariffHelper();
			pstrffObj1.createTariff( path, workBookName, "RerateServer", "Tariff Rerate", 1 );
			pstrffObj1.createFastEntry( path, workBookName, "RerateServer", "TariffRerate FastEntry", 1 );

			EventIdentifierValue eventValObj = new EventIdentifierValue( path, workBookName, "EventIdentifierValue", "EventIdentifierValue rerate", 1 );
            eventValObj.eventIdentifierValue();
            EventIdentifierValue eventValObj2 = new EventIdentifierValue( path, workBookName, "EventIdentifierValue", "EventIdentifierValue diffDefn", 1 );
            eventValObj2.eventIdentifierValue();
            EventIdentifierValue eventValObj3 = new EventIdentifierValue( path, workBookName, "EventIdentifierValue", "EventIdentifierValue AllFileds", 1 );
            eventValObj3.eventIdentifierValue();

		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}
	@org.testng.annotations.Test( priority = 5,invocationCount = 2, description = "Mnr for Rerate", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void reratePrerequisite4() throws Exception
	{
		try
		{


			EventMatchRuleGroup emrgObj = new EventMatchRuleGroup( path, workBookName, "EventMatchRuleGroup", "EMRG Rerate", 1 );
			emrgObj.configureEventMatchRuleGroup();

			EventMatchRule emrObj = new EventMatchRule( path, workBookName, "RerateServer", "EMR Rerate" );
			emrObj.configureEventMatchRule();
		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 6, description = "Mnr and Aggregation for rerate" )
	public void mnrAndAggregation() throws Exception
	{
		try
		{
			TaskSchedule taskObj = new TaskSchedule();
			taskObj.fileCollection( path, workBookName, "RerateServer", "FileSchedule rerate", 1 );
			
			PSCollectedFileSearchHelper psCollectedFileObj1=new PSCollectedFileSearchHelper(path, workBookName, "RerateServer", "CollectedFileSearchRerate_rerate");
			psCollectedFileObj1.validationOfCollectedFile();
			
			TaskSchedule task1Obj = new TaskSchedule();
			task1Obj.fileCollection( path, workBookName, "RerateServer", "FileSchedule rerate2", 1 );
			PSCollectedFileSearchHelper psCollectedFileObj2=new PSCollectedFileSearchHelper(path, workBookName, "RerateServer", "CollectedFileSearchRerate_rerate2");
			psCollectedFileObj2.validationOfCollectedFile();
			
			TaskSchedule task2Obj = new TaskSchedule();
			task2Obj.fileCollection( path, workBookName, "RerateServer", "FileSchedule rerate3", 1 );
			
			PSCollectedFileSearchHelper psCollectedFileObj3=new PSCollectedFileSearchHelper(path, workBookName, "RerateServer", "CollectedFileSearchRerate_rerate3");
			psCollectedFileObj3.validationOfCollectedFile();
	
			TaskSearchHelper tskObj = new TaskSearchHelper();
			tskObj.verifyTaskStatus( path, workBookName, "AggregationResults", "MnrTaskStatus", 1 );
			
	
		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}
	
	@org.testng.annotations.Test( priority = 7, description = "Mnr and Aggregation for rerate" )
	public void aggreagtionRecurringTask() throws Exception
	{
		try
		{
			TaskSchedule taskObj = new TaskSchedule();	
	
			TaskSearchHelper tskObj = new TaskSearchHelper();
			
	
			taskObj.scheduleRecurringTask( path, workBookName, "AggregationResults", "RecurringTask", 1 );
	
			tskObj.verifyTaskStatus( path, workBookName, "AggregationResults", "AggrTaskStatus", 1 );
	
			AggregationResult aggrResObj = new AggregationResult( path, workBookName, "Rerate1stlevel", "AggregationResult" );
			aggrResObj.isAggregationResultPresent();
	
		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}
	
	
	@org.testng.annotations.Test( priority = 8, description = "rerate only - 1st level with adjustments" )
	public void rerateOnly1stLevelwithAdjustemnets() throws Exception
	{
		try
		{
			AggregationResult aggrRes1Obj = new AggregationResult( path, workBookName, "Rerate1stlevel", "Aggradjustments- rerate1stlevel" );
			aggrRes1Obj.addAdjustments();
	
			PSTariffHelper trffObj = new PSTariffHelper();
			trffObj.createFastEntry( path, workBookName, "Rerate1stlevel", "TariffRerate FastEntry-afteradj", 1 );
	
			RerateRequest rerateObj = new RerateRequest( path, workBookName, "Rerate1stlevel", "ReRate 1ndLevel-Server-afteradj", 1 );
			rerateObj.rerateRequestCreation();
			rerateObj.scheduleRerateRequest();
	
			PSTaskSearchHelper taskobj = new PSTaskSearchHelper();
			taskobj.psVerifyTaskStatus( path, workBookName, sheetName, "RerateTaskStatus", 1 );
	
			AggregationResult aggrResObj = new AggregationResult( path, workBookName, "Rerate1stlevel", "AggregationResult After1strerateadj" );
			aggrResObj.viewAggregationResult();
	
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}
	
	@org.testng.annotations.Test( priority = 9, description = "rerate only - 1st level summary " )
	public void rerateOnly1stLevelSummary() throws Exception
	{
		try
		{
			PSTariffHelper trffObj = new PSTariffHelper();
			trffObj.createFastEntry( path, workBookName, "Rerate1stlevel", "TariffRerate FastEntry-summary", 1 );
	
			RerateRequest rerateObj = new RerateRequest( path, workBookName, "Rerate1stlevel", "ReRate 1ndLevel-Server-summary", 1 );
			rerateObj.rerateRequestCreation();
			rerateObj.scheduleRerateRequest();
	
			PSTaskSearchHelper taskobj = new PSTaskSearchHelper();
			taskobj.psVerifyTaskStatus( path, workBookName, sheetName, "RerateTaskStatus", 1 );
	
			AggregationResult aggrResObj = new AggregationResult( path, workBookName, "Rerate1stlevel", "AggregationResult After1streratsummary" );
			aggrResObj.viewAggregationResult();
	
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}
	
	@org.testng.annotations.Test( priority = 10, description = "rerate only - 1st level - Rerate only " )
	public void rerateOnly1stLevel() throws Exception
	{
		try
		{
			PSTariffHelper trffObj = new PSTariffHelper();
			trffObj.createFastEntry( path, workBookName, "Rerate1stlevel", "TariffRerate FastEntry-RerateOnly", 1 );
	
			RerateRequest rerateObj = new RerateRequest( path, workBookName, "Rerate1stlevel", "ReRate 1ndLevel-Server-RerateOnly", 1 );
			rerateObj.rerateRequestCreation();
			rerateObj.scheduleRerateRequest();
	
			PSTaskSearchHelper taskobj = new PSTaskSearchHelper();
			taskobj.psVerifyTaskStatus( path, workBookName, sheetName, "RerateTaskStatus", 1 );
	
			AggregationResult aggrResObj = new AggregationResult( path, workBookName, "Rerate1stlevel", "AggregationResult RerateOnly" );
			aggrResObj.viewAggregationResult();
	
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}
	
	@org.testng.annotations.Test( priority = 11, description = "rerate only - 1st level - with bill 4th level rerate" )
	public void rerateOnly1stLevelWithBill() throws Exception
	{
		try
		{
	
			Bills billObj = new Bills( path, workBookName, "Rerate1stlevel", "HotBill Rerate" );
			billObj.billAction();
	
			PSTariffHelper trff1Obj = new PSTariffHelper();
			trff1Obj.createFastEntry( path, workBookName, "Rerate1stlevel", "TariffRerate-RerateOnlyafterbill", 1 );
	
			RerateRequest rerate1Obj = new RerateRequest( path, workBookName, "Rerate1stlevel", "ReRate 1ndLevel-afterbill", 1 );
			rerate1Obj.rerateRequestCreation();
			rerate1Obj.scheduleRerateRequest();
	
			PSTaskSearchHelper task1obj = new PSTaskSearchHelper();
			task1obj.psVerifyTaskStatus( path, workBookName, sheetName, "RerateTaskStatus", 1 );
	
			TaskSchedule taskObj = new TaskSchedule();
			taskObj.scheduleRecurringTask( path, workBookName, "AggregationResults", "RecurringTask", 1 );
	
			PSTaskSearchHelper pstaskobj = new PSTaskSearchHelper();
			pstaskobj.psVerifyTaskStatus( path, workBookName, "AggregationResults", "AggrTaskStatus", 1 );
	
			AggregationResult aggrRes1Obj = new AggregationResult( path, workBookName, "Rerate1stlevel", "AggregationResult -Afterbillratechange" );
			aggrRes1Obj.viewAggregationResult();
	
			Bills bill1Obj = new Bills( path, workBookName, "Rerate1stlevel", "HotBill Regenrate" );
			bill1Obj.billAction();
	
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}
	
	@org.testng.annotations.Test( priority = 12, description = "rerate only - 2nd level" )
	public void rerateOnly2ndLevel() throws Exception
	{
		try
		{
	
			RerateRequest bandObj = new RerateRequest( path, workBookName, sheetName, "Bands Remove", 1 );
			bandObj.bandElementModification();
	
			BandHelper band1Obj = new BandHelper();
			band1Obj.createBand( path, workBookName, sheetName, "Bands Rerate", 1 );
	
			RerateRequest rerateObj = new RerateRequest( path, workBookName, sheetName, "ReRate 2ndLevelServer", 1 );
			rerateObj.rerateRequestCreation();
			rerateObj.scheduleRerateRequest();
	
			PSTaskSearchHelper taskobj = new PSTaskSearchHelper();
			taskobj.psVerifyTaskStatus( path, workBookName, sheetName, "RerateTaskStatus", 1 );
	
			TaskSchedule taskObj = new TaskSchedule();
			taskObj.scheduleRecurringTask( path, workBookName, "AggregationResults", "RecurringTask", 1 );
	
			PSTaskSearchHelper pstaskobj = new PSTaskSearchHelper();
			pstaskobj.psVerifyTaskStatus( path, workBookName, "AggregationResults", "AggrTaskStatus", 1 );
	
			AggregationResult aggrResObj = new AggregationResult( path, workBookName, "Rerate2ndlevel", "AggregationResult -After2nd level" );
			aggrResObj.viewAggregationResult();
	
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}
	
	@org.testng.annotations.Test( priority = 13, description = "rerate only - 3st level" )
	public void fullyrematchrate3rdLevel() throws Exception
	{
		try
		{
			EventMatchRule emrObj = new EventMatchRule( path, workBookName, "Rerate3rdlevel", "EditEMR Rerate" );
			emrObj.eventMatchRuleEdit();
	
			EventMatchRule emr1Obj = new EventMatchRule( path, workBookName, "Rerate3rdlevel", "EMR Rerate2" );
			emr1Obj.configureEventMatchRule();
	
			RerateRequest rerateObj = new RerateRequest( path, workBookName, "Rerate3rdlevel", "3rdlevelReRate-Server", 1 );
			rerateObj.rerateRequestCreation();
			rerateObj.scheduleRerateRequest();
	
			PSTaskSearchHelper taskobj = new PSTaskSearchHelper();
			taskobj.psVerifyTaskStatus( path, workBookName, sheetName, "RerateTaskStatus", 1 );
	
			TaskSchedule taskObj = new TaskSchedule();
			taskObj.scheduleRecurringTask( path, workBookName, "AggregationResults", "RecurringTask", 1 );
	
			PSTaskSearchHelper task1obj = new PSTaskSearchHelper();
			task1obj.psVerifyTaskStatus( path, workBookName, "AggregationResults", "AggrTaskStatus", 1 );
	
			AggregationResult aggrResObj = new AggregationResult( path, workBookName, "Rerate3rdlevel", "AggregationResult -3rdLevel-feb" );
			aggrResObj.viewAggregationResult();
	
			AggregationResult aggrRes1Obj = new AggregationResult( path, workBookName, "Rerate3rdlevel", "AggregationResult -3nd level-Mar" );
			aggrRes1Obj.viewAggregationResult();
	
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}
	
	@org.testng.annotations.Test( priority = 14, description = "rerate only - 3st level-service expiry adding entity value" )
	public void fullyrematchrate3rdLevelServiceExpiry() throws Exception
	{
		try
		{
	
			EventMatchRule emr2Obj = new EventMatchRule( path, workBookName, "Rerate3rdlevel", "EMR Rerate2 -serviceexpiry" );
			emr2Obj.eventMatchRuleEdit();
	
			RerateRequest rerateObj = new RerateRequest( path, workBookName, "Rerate3rdlevel", "3rdlevelReRate-serviceexpiry", 1 );
			rerateObj.rerateRequestCreation();
			rerateObj.scheduleRerateRequest();
	
			PSTaskSearchHelper taskobj = new PSTaskSearchHelper();
			taskobj.psVerifyTaskStatus( path, workBookName, sheetName, "RerateTaskStatus", 1 );
	
			TaskSchedule taskObj = new TaskSchedule();
			taskObj.scheduleRecurringTask( path, workBookName, "AggregationResults", "RecurringTask", 1 );
	
			PSTaskSearchHelper pstaskobj = new PSTaskSearchHelper();
			pstaskobj.psVerifyTaskStatus( path, workBookName, "AggregationResults", "AggrTaskStatus", 1 );
	
			AggregationResult aggrRes1Obj = new AggregationResult( path, workBookName, "Rerate3rdlevel", "AggregationResult -serviceexpiry" );
			aggrRes1Obj.viewAggregationResult();
	
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}
	
	@org.testng.annotations.Test( priority = 15, description = "rerate - 3st level-same bill period checked" )
	public void fullyrematchratesamebillPeriodChecked() throws Exception
	{
		try
		{
	
			Bills billObj = new Bills( path, workBookName, "Rerate3rdlevel", "HotBill Rerate-sameBillPeriodChecked" );
			billObj.billAction();
	
			PSTariffHelper trffObj = new PSTariffHelper();
			trffObj.createFastEntry( path, workBookName, "Rerate3rdlevel", "TariffRerate-samebillPeriodChecked", 1 );
	
			RerateRequest rerateObj = new RerateRequest( path, workBookName, "Rerate3rdlevel", "ReRaterdLevel-samebillPeriodChecked", 1 );
			rerateObj.rerateRequestCreation();
			rerateObj.scheduleRerateRequest();
	
			PSTaskSearchHelper taskobj = new PSTaskSearchHelper();
			taskobj.psVerifyTaskStatus( path, workBookName, sheetName, "RerateTaskStatus", 1 );
	
			TaskSchedule taskObj = new TaskSchedule();
			taskObj.scheduleRecurringTask( path, workBookName, "AggregationResults", "RecurringTask", 1 );
	
			PSTaskSearchHelper task1obj = new PSTaskSearchHelper();
			task1obj.psVerifyTaskStatus( path, workBookName, "AggregationResults", "AggrTaskStatus", 1 );
	
			AggregationResult aggrResObj = new AggregationResult( path, workBookName, "Rerate3rdlevel", "AggregationResult_samebillPeriodChecked_EMR3" );
			aggrResObj.viewAggregationResult();
	
			AggregationResult aggrRes1Obj = new AggregationResult( path, workBookName, "Rerate3rdlevel", "AggregationResult_samebillPeriodChecked_EMR4" );
			aggrRes1Obj.viewAggregationResult();
	
			Bills bill1Obj = new Bills( path, workBookName, "Rerate3rdlevel", "HotBill Regenrate-sameBillPeriodChecked" );
			bill1Obj.billAction();
	
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}
	
	@org.testng.annotations.Test( priority = 16, description = "rerate - 3st level-same bill period unchecked" )
	public void fullyrematchratesamebillPeriodunChecked() throws Exception
	{
		try
		{
	
			Bills billObj = new Bills( path, workBookName, "Rerate3rdlevel", "HotBill Rerate-sameBillPeriodUnChecked" );
			billObj.billAction();
	
			PSTariffHelper trffObj = new PSTariffHelper();
			trffObj.createFastEntry( path, workBookName, "Rerate3rdlevel", "TariffRerate-samebillPeriodUnChecked", 1 );
	
			RerateRequest rerateObj = new RerateRequest( path, workBookName, "Rerate3rdlevel", "ReRaterdLevel-samebillPeriodUnChecked", 1 );
			rerateObj.rerateRequestCreation();
			rerateObj.scheduleRerateRequest();
	
			PSTaskSearchHelper taskobj = new PSTaskSearchHelper();
			taskobj.psVerifyTaskStatus( path, workBookName, sheetName, "RerateTaskStatus", 1 );
	
			TaskSchedule taskObj = new TaskSchedule();
			taskObj.scheduleRecurringTask( path, workBookName, "AggregationResults", "RecurringTask", 1 );
	
			PSTaskSearchHelper pstaskobj = new PSTaskSearchHelper();
			pstaskobj.psVerifyTaskStatus( path, workBookName, "AggregationResults", "AggrTaskStatus", 1 );
	
			AggregationResult aggrResObj = new AggregationResult( path, workBookName, "Rerate3rdlevel", "AggregationResult_samebillPeriodUnChecked_EMR3" );
			aggrResObj.viewAggregationResult();
	
			AggregationResult aggrRes1Obj = new AggregationResult( path, workBookName, "Rerate3rdlevel", "AggregationResult_samebillPeriodUnChecked_EMR4" );
			aggrRes1Obj.viewAggregationResult();
	
			Bills bill1Obj = new Bills( path, workBookName, "Rerate3rdlevel", "HotBill Regenrate-sameBillPeriodUnChecked" );
			bill1Obj.billAction();
	
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}
}
