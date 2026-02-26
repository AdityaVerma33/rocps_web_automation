package com.subex.rocps.automation.testcases.functionaltesting;

import com.subex.rocps.automation.helpers.application.matchandrate.PreRatingMatchRule;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;

import com.subex.automation.helpers.util.FailureHelper;

public class TCPrerating extends PSAcceptanceTest
{
	String path = System.getProperty( "user.dir" ) + "\\src\\main\\resources\\";
	String workBookName = "FunctionalTestCases.xlsx";
	String sheetName = "Prerating";

	@org.testng.annotations.Test( priority = 1, description = "Prerating  creation", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void preratingCreation() throws Exception
	{

		try
		{
			PreRatingMatchRule pmrObj = new PreRatingMatchRule( path, workBookName, sheetName, "PreRatingRuleCreate" );
			pmrObj.createPreRatingMatchRule();
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			;
			throw e;
		}

	}

	@org.testng.annotations.Test( priority = 2, description = "Users  creation", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void preratingCreationMultipleTariffs() throws Exception
	{

		try
		{
			PreRatingMatchRule pmrObj = new PreRatingMatchRule( path, workBookName, sheetName, "PreRatingRuleCreateMultipleTariffs" );
			pmrObj.createPreRatingMatchRule();
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			;
			throw e;
		}

	}

	@org.testng.annotations.Test( priority = 3, description = "Prerating  Edit", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void editprerating() throws Exception
	{

		try
		{
			PreRatingMatchRule pmrObj = new PreRatingMatchRule( path, workBookName, sheetName, "PreRatingRuleEdit" );
			pmrObj.editPreRatingMatchRule();
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			;
			throw e;
		}

	}

	@org.testng.annotations.Test( priority = 4, description = "Prerating Delete" )
	public void preratingDelete() throws Exception
	{

		try
		{
			PreRatingMatchRule pmrObj = new PreRatingMatchRule( path, workBookName, sheetName, "PreRatingRuleDelete" );
			pmrObj.preRatingMatchRuleDelete();
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			;
			throw e;
		}

	}

	@org.testng.annotations.Test( priority = 5, description = "Prerating undelete" )
	public void preratingUnDelete() throws Exception
	{

		try
		{
			PreRatingMatchRule pmrObj = new PreRatingMatchRule( path, workBookName, sheetName, "PreRatingRuleUndelete" );
			pmrObj.preRatingMatchRuleUnDelete();
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			;
			throw e;
		}

	}

	@org.testng.annotations.Test( priority = 6, description = "Screen screen column validation" )
	public void preratingColVal() throws Exception
	{

		try
		{
			PreRatingMatchRule pmrObj = new PreRatingMatchRule( path, workBookName, sheetName, "PreratingSearchScreencolVal" );
			pmrObj.searchScreenColumnsValidation();
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			;
			throw e;
		}

	}

}
