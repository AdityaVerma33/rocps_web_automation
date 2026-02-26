package com.subex.rocps.automation.testcases.functionaltesting;

import org.testng.annotations.Test;

import com.subex.automation.helpers.application.screens.TariffClassHelper;
import com.subex.automation.helpers.util.FailureHelper;
import com.subex.rocps.automation.helpers.application.aggregation.AggregationResult;
import com.subex.rocps.automation.helpers.application.bilateral.MergerResults;
import com.subex.rocps.automation.helpers.application.deal.Deal;
import com.subex.rocps.automation.helpers.application.deal.DealRate;
import com.subex.rocps.automation.helpers.application.matchandrate.EventMatchRule;
import com.subex.rocps.automation.helpers.application.matchandrate.EventMatchRuleGroup;
import com.subex.rocps.automation.helpers.application.matchandrate.Operator;
import com.subex.rocps.automation.helpers.application.networkConfiguraiton.Route;
import com.subex.rocps.automation.helpers.application.networkConfiguraiton.RouteGroup;
import com.subex.rocps.automation.helpers.application.networkConfiguraiton.Switch;
import com.subex.rocps.automation.helpers.application.partnerConfiguration.Account;
import com.subex.rocps.automation.helpers.application.partnerConfiguration.Agent;
import com.subex.rocps.automation.helpers.application.partnerConfiguration.BillProfile;
import com.subex.rocps.automation.helpers.application.referenceTable.ElementSet;
import com.subex.rocps.automation.helpers.application.referenceTable.SystemTariffMapping;
import com.subex.rocps.automation.helpers.application.referenceTable.TariffType;
import com.subex.rocps.automation.helpers.application.system.PSTaskSearchHelper;
import com.subex.rocps.automation.helpers.application.system.TaskSchedule;
import com.subex.rocps.automation.helpers.application.tariffs.PSTariffHelper;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;

public class TCVoiceROClientPrerequisite extends PSAcceptanceTest
{
	String path = System.getProperty( "user.dir" ) + "\\src\\main\\resources\\";
	String workBookName = "FunctionalTestCasesRO.xlsx";
	String sheetName = "ROClientPrerequisite";
	TaskSchedule taskObj = new TaskSchedule();
	PSTaskSearchHelper tskObj = new PSTaskSearchHelper();
	@org.testng.annotations.Test( priority = 1, description = "Account Operator creation System MDL", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class  )
	public void accountOpCreation() throws Exception
	{

		try
		{
			Agent agobj = new Agent( path, workBookName, "ROClientPrerequisite", "Agent", 1 );
			agobj.agentCreation();

			Account accobjMDL = new Account( path, workBookName, "ROClientPrerequisite", "Account_sysMDL", 1 );
			accobjMDL.accountCreation();
			Operator ope1ObjMDL = new Operator( path, workBookName, "ROClientPrerequisite", "Operator_SysMDL", 1 );
			ope1ObjMDL.operatorCreation();

			Account accobj = new Account( path, workBookName, "ROClientPrerequisite", "Account_Op1", 1 );
			accobj.accountCreation();
			Operator ope1Obj = new Operator( path, workBookName, "ROClientPrerequisite", "Operator_Op1", 1 );
			ope1Obj.operatorCreation();
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}

	}

	@Test( priority = 2, enabled = true, description = "'Ratesheet VOice RO '  prerequisite", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class  )
	public void createTariffSystemMDL() throws Exception
	{
		try
		{

			ElementSet eleObj = new ElementSet( path, workBookName, sheetName, "ElementSetCreation_SystemMDL", 1 );
			eleObj.elementSetCreation();
			TariffClassHelper trffClassObj = new TariffClassHelper();
			trffClassObj.createTariffClass( path, workBookName, sheetName, "TariffClass_SystemMDL", 1 );

			PSTariffHelper pstrffObj = new PSTariffHelper();
			pstrffObj.createTariff( path, workBookName, sheetName, "Tariff_SystemMDL", 1 );
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

	@Test( priority = 3, enabled = true, description = "'Ratesheet VOice RO '  prerequisite", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class  )
	public void creteTariffOp1() throws Exception
	{
		try
		{

			ElementSet eleObj = new ElementSet( path, workBookName, sheetName, "ElementSetCreation_operator", 1 );
			eleObj.elementSetCreation();
			TariffClassHelper trffClassObj = new TariffClassHelper();
			trffClassObj.createTariffClass( path, workBookName, sheetName, "TariffClass_operator1", 1 );

			PSTariffHelper pstrffObj = new PSTariffHelper();
			pstrffObj.createTariff( path, workBookName, sheetName, "Tariff_operator1", 1 );
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

	@Test( priority = 4, enabled = true, description = "'Ratesheet VOice RO '  prerequisite", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class  )
	public void routeCreationSysMDL() throws Exception
	{
		try
		{

			Switch switchObj = new Switch( path, workBookName, sheetName, "Switch_SysMDL" );
			switchObj.configureSwitch();


			RouteGroup routeGrpObj = new RouteGroup( path, workBookName, sheetName, "RouteGroup_SysMDL" );
			routeGrpObj.routeGrpCreation();

			Route routeColObj = new Route( path, workBookName, sheetName, "RouteSysMDL" );
			routeColObj.routeCreation();

		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}
	@Test( priority = 5, enabled = true, description = "'Ratesheet VOice RO '  prerequisite" , retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void routeCreationOP1() throws Exception
	{
		try
		{

			Switch switchObj = new Switch( path, workBookName, sheetName, "Switch_OP1" );
			switchObj.configureSwitch();


			RouteGroup routeGrpObj = new RouteGroup( path, workBookName, sheetName, "RouteGroup_OP1" );
			routeGrpObj.routeGrpCreation();

			Route routeColObj = new Route( path, workBookName, sheetName, "Route_OP1" );
			routeColObj.routeCreation();

		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

	@Test( priority = 6, enabled = true, description = "'Ratesheet VOice RO '  prerequisite", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class  )
	public void createSystemTariffMappingMDL() throws Exception
	{
		try
		{

			SystemTariffMapping systemTariffMapping = new SystemTariffMapping( path, workBookName, sheetName, "SystemTariffMappingCreation" );
			systemTariffMapping.systemTariffMappingCreation();
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

	@Test( priority = 7, enabled = true, description = "'Ratesheet VOice RO '  prerequisite", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class  )
	public void operatorTariffMapping() throws Exception
	{
		try
		{

			Operator operator = new Operator( path, workBookName, sheetName, "Operator_TariffMapping" );
			operator.operatorTariffMapping();
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}
	@org.testng.annotations.Test( priority = 8, description = "Account , Bill Profile and Route for deal", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void dealPrerequisite1() throws Exception
	{
		try
		{
			Account accobj = new Account( path, workBookName, "DealPrerequisite", "AccountDeal", 1 );
			accobj.accountCreation();

			
			BillProfile billObj = new BillProfile( path, workBookName, "DealPrerequisite", "BillProfileDeal", 1 );
			billObj.billProfileCreation();

			RouteGroup routeGrpObj = new RouteGroup( path, workBookName, "DealPrerequisite", "RouteGroup Deal" );
			routeGrpObj.routeGrpCreation();

			Route routeColObj = new Route( path, workBookName, "DealPrerequisite", "Routes Deals" );
			routeColObj.routeCreation();

		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 9, description = "EMR for deal", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void dealPrerequisite2() throws Exception
	{
		try
		{
			

			TariffClassHelper trffClassObj = new TariffClassHelper();
			trffClassObj.createTariffClass( path, workBookName, "DealPrerequisite", "TariffClass Outgoing", 1 );

			PSTariffHelper pstrff2Obj1 = new PSTariffHelper();
			pstrff2Obj1.createTariff( path, workBookName, "DealPrerequisite", "Tariff OutgoingDeal1", 1 );
			pstrff2Obj1.createFastEntry( path, workBookName, "DealPrerequisite", "Tariff Outgoing FastEntry1", 1 );


			EventMatchRuleGroup eventValObj = new EventMatchRuleGroup( path, workBookName, "DealPrerequisite", "EMRG-CI", 1 );
			eventValObj.configureEventMatchRuleGroup();

			EventMatchRule emrObj = new EventMatchRule( path, workBookName, "DealPrerequisite", "EMR DealOut" );
			emrObj.configureEventMatchRule();
		
		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}
	@org.testng.annotations.Test( priority = 10, description = "Deal Creation- Tiered outgoing" ,retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class)
	public void dealCreationTierOut() throws Exception
	{
		try
		{


			Deal dealObj = new Deal( path, workBookName, "DealPrerequisite", "Test01DealOut" );
			dealObj.dealCreation();
			
			DealRate dealrateObj = new DealRate( path, workBookName, "DealPrerequisite", "Test01DealOut_Rate" );
			dealrateObj.dealRateConfig();
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 11, description = "Deal change status Actions- committed deal - outgoing" )
	public void dealAuthorizeNonCommitedDeal() throws Exception
	{
		try
		{
			Deal dealObj = new Deal( path, workBookName, "DealPrerequisite", "Test01DealOut_Status" );
			dealObj.dealValidate();

			Deal deal1Obj = new Deal( path, workBookName, "DealPrerequisite", "Test01DealOut_Status" );
			deal1Obj.dealCheckIn();

			PSTaskSearchHelper tskObj = new PSTaskSearchHelper();
			tskObj.psVerifyTaskStatus( path, workBookName, "DealPrerequisite", "DealCheckinStatus", 1 );

			Deal deal2Obj = new Deal( path, workBookName, "DealPrerequisite", "Test01DealOut_Status" );
			deal2Obj.dealAuthorize();

		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}
	@org.testng.annotations.Test( priority = 12, description = "Mnr for deal committed deal - outgoing" )
	public void dealNonCommittedOutMatchandRate() throws Exception
	{
		try
		{
			TaskSchedule taskObj = new TaskSchedule();
			taskObj.fileCollection( path, workBookName, "DealPrerequisite", "FileSchedule NonCommittedDealOut", 1 );

			PSTaskSearchHelper tskObj = new PSTaskSearchHelper();
			tskObj.psVerifyTaskStatus( path, "FunctionalTestCases.xlsx", "AggregationResults", "MnrTaskStatus", 1 );

		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 13, description = "scheduling recuring task for NonCommitted deal -Outgoing" )
	public void dealNonCommittedOutScheduleRecurringTask() throws Exception
	{
		try
		{
			TaskSchedule taskObj = new TaskSchedule();
			PSTaskSearchHelper tskObj = new PSTaskSearchHelper();

			taskObj.scheduleRecurringTask( path, "FunctionalTestCases.xlsx", "AggregationResults", "RecurringTask", 1 );

			tskObj.psVerifyTaskStatus( path, "FunctionalTestCases.xlsx", "AggregationResults", "AggrTaskStatus", 1 );

			AggregationResult aggrResObj = new AggregationResult( path, workBookName, "DealPrerequisite", "AggregationResult NonCommittedOut" );
			aggrResObj.isAggregationResultPresent();

		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 14, description = "Scheduling bilater master request and validation of brd results and merger results" )
	public void bilateralResultsNonCommittedOut() throws Exception
	{
		try
		{

			taskObj.scheduleRecurringTask( path, workBookName, "DealPrerequisite", "RecurringTask", 1 );

			tskObj.psVerifyTaskStatus( path, workBookName, "DealPrerequisite", "brdTaskStatus", 1 );

			MergerResults mergerObj = new MergerResults( path, workBookName, "DealPrerequisite", "MergerResults-NonCommittedDealOut" );
			//mergerObj.viewMergerResults();

		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}
	@Test( priority = 15, enabled = true, description = "' VOice RO '  deal", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class  )
	public void operatorTariffMappingForDeal() throws Exception
	{
		try
		{
			Operator ope1Obj = new Operator( path, workBookName, "ROClientPrerequisite", "Operator_Op1_Deal", 1 );
			ope1Obj.operatorCreation();

			Operator operator = new Operator( path, workBookName, sheetName, "Operator_TariffMapping_Deal" );
			operator.operatorTariffMapping();
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}
}
