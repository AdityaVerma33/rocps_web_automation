package com.subex.rocps.automation.testcases.functionaltesting;

import org.testng.annotations.Test;

import com.subex.rocps.automation.helpers.application.roaming.RoamingAgreementConfig;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;

import com.subex.automation.helpers.util.FailureHelper;

public class TCRoamingAgreemConfig extends PSAcceptanceTest
{
	String path = System.getProperty( "user.dir" ) + "\\src\\main\\resources\\";

	String workBookName = "FunctionalTestCases.xlsx";
	String sheetName = "RoamingAgreemConfig";

	@Test( priority = 1, enabled = true, description = "'Roaming Agreement Configuration'  verify the column Headers Of Search Screen" )
	public void verifyTheColHdrs() throws Exception
	{
		try
		{

		RoamingAgreementConfig roamingAgreementConfig=new RoamingAgreementConfig( path, workBookName, sheetName, "RoamingAgreeConfigScreencolVal" );
		roamingAgreementConfig.roamingAgreeConfigColumsValidation();
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}
	

	@Test( priority = 2, enabled = true, description = "'Roaming Agreement Configuration'  creation with HOME Agreement" ,retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void roamingAgreemConfigCreationHomeAgreement() throws Exception
	{
		try
		{

		RoamingAgreementConfig roamingAgreementConfig=new RoamingAgreementConfig( path, workBookName, sheetName, "TestRoamingAgreeConfigCreationHome" );
		roamingAgreementConfig.roamingAgreemConfigCreation();
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}
	
	@Test( priority = 3, enabled = true, description = "'Roaming Agreement Configuration'  creation with Direct agreement",retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class  )
	public void roamingAgreemConfigCreationDrectAgreement() throws Exception
	{
		try
		{

		RoamingAgreementConfig roamingAgreementConfig=new RoamingAgreementConfig( path, workBookName, sheetName, "TestRoamingAgreeConfigCreationDirect" );
		roamingAgreementConfig.roamingAgreemConfigCreation();
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}
	

}
