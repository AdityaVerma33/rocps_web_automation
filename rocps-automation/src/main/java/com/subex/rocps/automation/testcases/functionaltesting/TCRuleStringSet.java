package com.subex.rocps.automation.testcases.functionaltesting;

import com.subex.rocps.automation.helpers.application.networkConfiguraiton.RuleStringSet;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;

import com.subex.automation.helpers.util.FailureHelper;

public class TCRuleStringSet extends PSAcceptanceTest
{
	String path = System.getProperty( "user.dir" ) + "\\src\\main\\resources\\";
	String workBookName = "FunctionalTestCases.xlsx";
	String sheetName = "RuleStringSet";

	@org.testng.annotations.Test( priority = 1, description = "Rule stringset   creation", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void ruleStringSetCreation() throws Exception
	{
		try
		{
			RuleStringSet ruleObj = new RuleStringSet( path, workBookName, sheetName, "RuleStringSet", 1 );
			ruleObj.ruleStringCreation();
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 2, description = "Rule stringset - Multiple  creation", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void ruleStringSetMultipleCreation() throws Exception
	{
		try
		{
			RuleStringSet ruleMultiObj = new RuleStringSet( path, workBookName, sheetName, "RuleStringSet Multiple", 1 );
			ruleMultiObj.ruleStringCreation();
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 3, description = "Rule stringset Delete" )
	public void ruleStringSetDelete() throws Exception
	{
		try
		{
			RuleStringSet ruledelObj = new RuleStringSet( path, workBookName, sheetName, "RuleStringSet Delete", 1 );
			ruledelObj.ruleStringSetDelete();
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 4, description = "Rule stringset UnDelete" )
	public void ruleStringSetUnDelete() throws Exception
	{
		try
		{
			RuleStringSet ruleUnDelObj = new RuleStringSet( path, workBookName, sheetName, "RuleStringSet UnDelete", 1 );
			ruleUnDelObj.ruleStringSetUnDelete();
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 5, description = "Rule stringset search screen column validation" )
	public void ruleStringSetColVal() throws Exception
	{
		try
		{
			RuleStringSet ruleColValObj = new RuleStringSet( path, workBookName, sheetName, "RuleStrSetSearchScreencolVal", 1 );
			ruleColValObj.searchScreenColumnsValidation();
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}
}
