package com.subex.rocps.automation.testcases.functionaltesting;

import org.testng.annotations.Test;

import com.subex.rocps.automation.helpers.application.referenceTable.RoamingExprGroup;
import com.subex.rocps.automation.helpers.application.referenceTable.RoamingSerMatchExpresion;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;

import com.subex.automation.helpers.util.FailureHelper;

public class TCRoamingServMatchExpr extends PSAcceptanceTest
{
	String path = System.getProperty( "user.dir" ) + "\\src\\main\\resources\\";

	String workBookName = "FunctionalTestCases.xlsx";
	String sheetName = "RoamingServMatchExpr";

	@Test( priority = 1, enabled = true, description = "'Roaming Service Match Expression'  prerequisite create Roaming Expression Group" ,retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class)
	public void roamingExpGrpPrerequisite() throws Exception
	{
		try
		{
			RoamingExprGroup roamingExprGroup=new RoamingExprGroup( path, workBookName, "RoamingExpGroup", "TestRoamingExpGroupCreation" );
			roamingExprGroup.roamingExpGrpCreation();

		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}
	@Test( priority = 2, enabled = true, description = "'Roaming Service Match Expression'  verify the column Headers Of Search Screen" )
	public void verifyTheColHdrs() throws Exception
	{
		try
		{
			RoamingSerMatchExpresion roamingSerMatchExpresion = new RoamingSerMatchExpresion( path, workBookName, sheetName, "RoamingServMatchExpScreencolVal" );
			roamingSerMatchExpresion.roamSerMatExpColumsValidation();

		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}
	

	@Test( priority = 3, enabled = true, description = "'Roaming Service Match Expression' creation" )
	public void roamingServMatchExprCreation() throws Exception
	{
		try
		{
			RoamingSerMatchExpresion roamingSerMatchExpresion = new RoamingSerMatchExpresion( path, workBookName, sheetName, "TestRoamingServMatchExpCreation" );
			roamingSerMatchExpresion.roamSerMatExpCreation();

		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}
	
	//@Test( priority = 4, enabled = true, description = "'Roaming Service Match Expression' edit SMS Originator of 'SMS - MTC Expression Group'" )
	public void smsMTC_OriginatorEdit() throws Exception
	{
		try
		{
			RoamingSerMatchExpresion roamingSerMatchExpresion = new RoamingSerMatchExpresion( path, workBookName, sheetName, "SMS_MTC_OriginatorEdit" );
			roamingSerMatchExpresion.roamSerMatExpEdit();
			

		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}
	@Test( priority = 5, enabled = true, description = "'Roaming Service Match Expression' edit" )
	public void roamingServMatchExprEdit() throws Exception
	{
		try
		{
			RoamingSerMatchExpresion roamingSerMatchExpresion = new RoamingSerMatchExpresion( path, workBookName, sheetName, "TestRoamingServMatchExpEdit" );
			roamingSerMatchExpresion.roamSerMatExpEdit();
			

		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}
}
