package com.subex.rocps.automation.testcases.functionaltesting;

import org.testng.annotations.Test;

import com.subex.rocps.automation.helpers.application.matchandrate.Operator;
import com.subex.rocps.automation.helpers.application.partnerConfiguration.Account;
import com.subex.rocps.automation.helpers.application.partnerConfiguration.Agent;
import com.subex.rocps.automation.helpers.application.system.TaskSchedule;
import com.subex.rocps.automation.helpers.application.system.streams.BulkLoadStreamImpl;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;
import com.subex.automation.helpers.application.screens.TaskControllerHelper;
import com.subex.automation.helpers.application.screens.TaskSearchHelper;
import com.subex.automation.helpers.util.FailureHelper;

public class TCBulkLoaderStream extends PSAcceptanceTest
{
	String path = System.getProperty( "user.dir" ) + "\\src\\main\\resources\\";
	String workBookName = "FunctionalTestCases.xlsx";
	String sheetName = "BulkLoadStream";
	TaskSchedule taskSceduleObj = new TaskSchedule();
	TaskSearchHelper taskSearchHelpObj = new TaskSearchHelper();
	BulkLoadStreamImpl bulkLoadStrmObj = new BulkLoadStreamImpl( path, workBookName, sheetName );

	/*PreRequiste of Bulkloader */

	@org.testng.annotations.Test( priority = 1, enabled = true, description = "  BulkLoader prerequistes", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void BulkLoaderPrerequistes1() throws Exception

	{
		try
		{

			Agent agobj = new Agent( path, workBookName, "Agent", "Agent", 1 );
			agobj.agentCreation();

			Account accobj = new Account( path, workBookName, "Account", "AccountCustomer", 1 );
			accobj.accountCreation();

			Account accobj2 = new Account( path, workBookName, "Account", "AccountVendor", 1 );
			accobj2.accountCreation();

			Operator ope1Obj = new Operator( path, workBookName, "Operator", "OperatorTransit", 1 );
			ope1Obj.operatorCreation();

		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}

	@Test( priority = 2, enabled = true, description = "set Task Controller Capability" , retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void setTaskControllerCapability() throws Exception
	{
		try
		{

			TaskControllerHelper taskControllerHelper = new TaskControllerHelper();
			taskControllerHelper.setTaskControllerCapability( path, workBookName, "BulkLoadStreamPreReq", "TCCapability", 1 );
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}

	}

	@Test( priority = 3, enabled = true, description = "billProfile BulkLoadStream" )
	public void agentBulkLoadStream() throws Exception
	{
		try

		{
			taskSceduleObj.fileCollection( path, workBookName, sheetName, "FileSchedule AgentConf BulkLoadStream_TC001", 1 );
			taskSearchHelpObj.verifyTaskStatus( path, workBookName, sheetName, "AgentConf BulkLoadStreamTaskStatus_TC001", 1 );
			bulkLoadStrmObj.verifyDatawithTextBoxFilterOfScreen( "AgentAssertion_TC001", "Agent", "pageCompanyName", "Agent" );
			bulkLoadStrmObj.verifyDataWithColumnGridFilterOfScreen( "AccountAssertion_TC001", "Account", "paccName", "Account Name" );

		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

	@Test( priority = 4, enabled = true, description = "billProfile BulkLoadStream" )

	public void billProfileBulkLoadStream() throws Exception
	{
		try

		{
			taskSceduleObj.fileCollection( path, workBookName, sheetName, "FileScheduleBillProfile BulkLoadStream_TC1", 1 );
			taskSearchHelpObj.verifyTaskStatus( path, workBookName, sheetName, "BillProfileBulkLoadStreamTaskStatus_TC1", 1 );
			bulkLoadStrmObj.verifyDataWithColumnGridFilterOfScreen( "BillProfileAssertion_TC1", "Bill Profile", "pbipName", "Bill Profile Name" );

		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}
	

	@Test( priority = 5, enabled = true, description = "dialString Set BulkLoadStream" )

	public void dialStringSetBulkLoadStream() throws Exception
	{
		try

		{

			taskSceduleObj.fileCollection( path, workBookName, sheetName, "FileScheduleDialString BulkLoadStream_TC2", 1 );
			taskSearchHelpObj.verifyTaskStatus( path, workBookName, sheetName, "DialStringBulkLoadStreamTaskStatus_TC2", 1 );
			bulkLoadStrmObj.verifyDatawithTextBoxFilterOfScreen( "DialStringSetAssertion_TC2", "Dial String Set", "Detail_dialString_name_txtID", "Name" );
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}
	

	@Test( priority = 6, enabled = true, description = "routeGrpBulkLoadStream" )
	public void routeGrpBulkLoadStream() throws Exception
	{
		try

		{
			taskSceduleObj.fileCollection( path, workBookName, sheetName, "FileScheduleRouteGrp BulkLoadStream_TC3", 1 );
			taskSearchHelpObj.verifyTaskStatus( path, workBookName, sheetName, "RouteGrpBulkLoadStreamTaskStatus_TC3", 1 );
			bulkLoadStrmObj.verifyDataWithColumnGridFilterOfScreen( "RouteGrpAssertion_TC3", "Route Group", "PS_Detail_routeGroup_name_txtId", "Name" );
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

	@Test( priority = 7, enabled = true, description = "evenIdenDefn BulkLoadStream" )
	public void evenIdenDefnBulkLoadStream() throws Exception
	{
		try

		{
			taskSceduleObj.fileCollection( path, workBookName, sheetName, "FileSchedule EventIdenDefn BulkLoadStream_TC4", 1 );
			taskSearchHelpObj.verifyTaskStatus( path, workBookName, sheetName, "EvenIdenDefnBulkLoadStreamTaskStatus_TC4", 1 );
			bulkLoadStrmObj.verifyDataWithColumnGridFilterOfScreen( "EventIdenDefnAssertion_TC4", "Event Identifier Definition", "Detail_eventDefn_eventName_txtId", "Name" );

		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

	@Test( priority = 8, enabled = true, description = "evenIdenValue BulkLoadStream" )
	public void evenIdenValueBulkLoadStream() throws Exception
	{
		try

		{
			taskSceduleObj.fileCollection( path, workBookName, sheetName, "FileSchedule EventIdenVal BulkLoadStream_TC5", 1 );
			taskSearchHelpObj.verifyTaskStatus( path, workBookName, sheetName, "EvenIdenValBulkLoadStreamTaskStatus_TC5", 1 );
			bulkLoadStrmObj.verifyDataWithColumnGridFilterOfScreen( "EventIdenValueAssertion_TC5", "Event Identifier Value", "Detail_eventValue_name_txtId", "Name" );

		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

	@Test( priority = 9, enabled = true, description = "evenIdenValGrp BulkLoadStream" )
	public void evenIdenValGrpBulkLoadStream() throws Exception
	{
		try

		{
			taskSceduleObj.fileCollection( path, workBookName, sheetName, "FileSchedule EventIdenValGrp BulkLoadStream_TC6", 1 );
			taskSearchHelpObj.verifyTaskStatus( path, workBookName, sheetName, "EvenIdenValGrpBulkLoadStreamTaskStatus_TC6", 1 );
			bulkLoadStrmObj.verifyDataWithColumnGridFilterOfScreen( "EventIdenValueGrpAssertion_TC6", "Event Identifier Value Group", "Detail_eventIdentifierValueGrp_txtID", "Name" );

		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

	@Test( priority = 10, enabled = true, description = "evenMatchRuleGrp BulkLoadStream" )
	public void evenMatchRuleGrpBulkLoadStream() throws Exception
	{
		try

		{
			taskSceduleObj.fileCollection( path, workBookName, sheetName, "FileSchedule EventMatchRuleGrp BulkLoadStream_TC7", 1 );
			taskSearchHelpObj.verifyTaskStatus( path, workBookName, sheetName, "EvenMatRuleGrpGrpBulkLoadStreamTaskStatus_TC7", 1 );
			bulkLoadStrmObj.verifyDataWithColumnGridFilterOfScreen( "EveMatchRuleGrpAssertion_TC7", "Event Match Rule Group", "PSDetail_emrName_txtId", "Name" );
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

	@Test( priority = 11, enabled = true, description = "eveLegCdGrpBulkLoadStream" )
	public void eveLegCdGrpBulkLoadStream() throws Exception
	{
		try

		{
			taskSceduleObj.fileCollection( path, workBookName, sheetName, "FileSchedule EventLegCdGrp BulkLoadStream_TC8", 1 );
			taskSearchHelpObj.verifyTaskStatus( path, workBookName, sheetName, "EvenLegCdGrpBulkLoadStreamTaskStatus_TC8", 1 );
			bulkLoadStrmObj.verifyDatawithReferTableOfScreen( "EvenLegCdGrpAssertion_TC8", "Event Leg Code Group", "Group Name" );
			bulkLoadStrmObj.verifyDatawithReferTableOfScreen( "EvenLegCdAssertion_TC8", "Event Leg Code", "Name" );

		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

}
