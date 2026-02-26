package com.subex.rocps.automation.testcases.functionaltesting;

import org.testng.annotations.Test;

import com.subex.automation.helpers.util.FailureHelper;
import com.subex.rocps.automation.helpers.application.matchandrate.EventIdentiferDefinition;
import com.subex.rocps.automation.helpers.application.matchandrate.EventIdentifierValueGroup;
import com.subex.rocps.automation.helpers.application.matchandrate.SurchargeRule;
import com.subex.rocps.automation.helpers.application.networkConfiguraiton.DialStringSet;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;

public class TCSurchargeRule extends PSAcceptanceTest
{
	String path = System.getProperty( "user.dir" ) + "\\src\\main\\resources\\";

	String workBookName = "FunctionalTestCases.xlsx";
	String sheetName = "SurchargeRule";

	@org.testng.annotations.Test( priority = 1, description = "event identifier defn-Different Event type creation", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void eventIdentifierDefnValueGrpPrerequisite() throws Exception
	{
		try
		{
			EventIdentiferDefinition eventObj = new EventIdentiferDefinition( path, workBookName, "EventDefn", "EvenDefn EventType", 1 );
			eventObj.eventCreation();
			DialStringSet dialObj = new DialStringSet( path, workBookName, "DialStringSet", "DialStringSet", 1 );
			dialObj.dialStringCreation();
			EventIdentifierValueGroup valueGrpObj = new EventIdentifierValueGroup( path, workBookName, "EventIdentiferValueGroup", "EventValueGrp", 1 );
			valueGrpObj.eventValueGrpCreation();
		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}

	@Test( priority = 2, enabled = true, description = "'Surcharge Rule'  verify the column Headers Of Search Screen" )
	public void verifyTheColHdrs() throws Exception
	{
		try
		{
			SurchargeRule surchargeRule = new SurchargeRule( path, workBookName, sheetName, "SurchargeRuleScreenColumns" );
			surchargeRule.surchargeRuleColumsValidation();

		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

	@Test( priority = 3, enabled = true, description = "'Surcharge Rule'  creation", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void surchargeRuleCreation() throws Exception
	{
		try
		{
			SurchargeRule surchargeRule = new SurchargeRule( path, workBookName, sheetName, "SurchargeRuleCreation" );
			surchargeRule.surchargeRuleCreation();

		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

	@Test( priority = 4, enabled = true, description = "'Surcharge Rule'  creation with View Expression Query validate", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void surchargeRuleCreationWithViewExpQuery() throws Exception
	{
		try
		{
			SurchargeRule surchargeRule = new SurchargeRule( path, workBookName, sheetName, "SurchargeRuleCreationWithViewExpressionQueryValidate" );
			surchargeRule.surchargeRuleCreation();

		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

	@Test( priority = 5, enabled = true, description = "'Surcharge Rule'  Delete action" )
	public void surchargeRuleDelete() throws Exception
	{
		try
		{
			SurchargeRule surchargeRule = new SurchargeRule( path, workBookName, sheetName, "SurchargeRuleDelete" );
			surchargeRule.surchargeRuleDelete();

		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

	@Test( priority = 6, enabled = true, description = "'Surcharge Rule'  Undelete action" )
	public void surchargeRuleUnDelete() throws Exception
	{
		try
		{
			SurchargeRule surchargeRule = new SurchargeRule( path, workBookName, sheetName, "SurchargeRuleUnDelete" );
			surchargeRule.surchargeRuleUnDelete();

		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}
}
