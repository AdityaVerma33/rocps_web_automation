package com.subex.rocps.automation.testcases.functionaltesting;

import com.subex.rocps.automation.helpers.application.matchandrate.EventMatchRuleGroup;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;

import com.subex.automation.helpers.util.FailureHelper;

public class TCEventMatchRuleGroupAction extends PSAcceptanceTest
{
	String path = System.getProperty( "user.dir" ) + "\\src\\main\\resources\\";
	String workBookName = "FunctionalTestCases.xlsx";
	String sheetName = "EventMatchRuleGroup";

	@org.testng.annotations.Test( priority = 1, description = "event MatchRuleGroup view EMR " )
	public void eventIdentifierRuleGroupViewEMR() throws Exception
	{
		try
		{
			EventMatchRuleGroup eventValObj = new EventMatchRuleGroup( path, workBookName, sheetName, "EMRG ViewEMR", 1 );
			eventValObj.eventMatchRuleGrpViewEMR();
		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 2, description = "event MatchRuleGroup view EMR " )
	public void eventIdentifierRuleGroupReorder() throws Exception
	{
		try
		{
			EventMatchRuleGroup eventValObj = new EventMatchRuleGroup( path, workBookName, sheetName, "Reorder", 1 );
			eventValObj.emrgreOrderAction();
		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}
}
