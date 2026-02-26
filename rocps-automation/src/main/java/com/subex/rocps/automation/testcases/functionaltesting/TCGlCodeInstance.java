package com.subex.rocps.automation.testcases.functionaltesting;

import org.testng.annotations.Test;

import com.subex.rocps.automation.helpers.application.accruals.GLCodeDefn;
import com.subex.rocps.automation.helpers.application.accruals.GLCodeInstance;
import com.subex.rocps.automation.helpers.application.partnerConfiguration.Account;
import com.subex.rocps.automation.helpers.application.partnerConfiguration.Agent;
import com.subex.rocps.automation.helpers.application.tariffs.PSTariffHelper;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;

import com.subex.automation.helpers.application.screens.BandHelper;
import com.subex.automation.helpers.application.screens.ElementCreateHelper;
import com.subex.automation.helpers.application.screens.TariffClassHelper;
import com.subex.automation.helpers.application.screens.TariffHelper;
import com.subex.automation.helpers.util.FailureHelper;

public class TCGlCodeInstance extends PSAcceptanceTest
{

	String path = System.getProperty( "user.dir" ) + "\\src\\main\\resources\\";

	String workBookName = "FunctionalTestCases.xlsx";
	String sheetName = "GlCdInstance";

	@Test( priority = 1, enabled = true, description = "'GL Code Instance'  prerequisite for entitiy value", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void glCodeInstPrerequisite() throws Exception
	{
		try
		{
			Agent agobj = new Agent( path, workBookName, "Agent", "Agent", 1 );
			agobj.agentCreation();

			Account accobj = new Account( path, workBookName, "Account", "Account_Dispute", 1 );
			accobj.accountCreation();

			ElementCreateHelper eleObj = new ElementCreateHelper();
			eleObj.createElement( path, workBookName, "Elements", "Elements Incoming", 1 );

			BandHelper bandObj = new BandHelper();
			bandObj.createBand( path, workBookName, "Bands", "Bands Incoming", 1 );

			TariffClassHelper trffClassObj = new TariffClassHelper();
			trffClassObj.createTariffClass( path, workBookName, "TariffClass", "TariffClass Incoming", 1 );

			PSTariffHelper pstrffObj1 = new PSTariffHelper();
			pstrffObj1.createTariff( path, workBookName, "Tariff", "Tariff Incoming", 1 );
			pstrffObj1.createFastEntry( path, workBookName, "Tariff", "Tariff Incoming FastEntry", 1 );
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

	@Test( priority = 2, enabled = true, description = "'GL Code Instance' prerequisite for GL code defn creation", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void glCodeInstPrerequisite2() throws Exception
	{
		try
		{
			GLCodeDefn glCodeDefn = new GLCodeDefn( path, workBookName, "GlCodeDefn", "TestGlCodeDefnCreation" );
			glCodeDefn.glCodeDefnCreation();
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

	@Test( priority = 3, enabled = true, description = "'GL Code Instance'  verify the column Headers Of Search Screen" )
	public void verifyTheColHdrs() throws Exception
	{
		try
		{

			GLCodeInstance glCodeInstance = new GLCodeInstance( path, workBookName, sheetName, "GlCodeInstScreencolVal" );
			glCodeInstance.glCdInstanceColumsValidation();

		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

	@Test( priority = 4, enabled = true, description = "'GL Code Instance' creation", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void createGLCodeInstance() throws Exception
	{
		try
		{

			GLCodeInstance glCodeInstance = new GLCodeInstance( path, workBookName, sheetName, "TestGlCodeInstanceCreation" );
			glCodeInstance.glCodeInstanceCreation();

		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

	@Test( priority = 5, enabled = true, description = "'GL Code Instance' edit" )
	public void editGLCodeInstance() throws Exception
	{
		try
		{

			GLCodeInstance glCodeInstance = new GLCodeInstance( path, workBookName, sheetName, "TestGlCodeInstanceEdit" );
			glCodeInstance.glCodeInstanceEdit();

		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

	@Test( priority = 6, enabled = true, description = "'GL Code Instance'  delete action" )
	public void deleteGlCodeInstance() throws Exception
	{
		try
		{
			GLCodeInstance glCodeInstance = new GLCodeInstance( path, workBookName, sheetName, "GlCodeInstanceDelete" );
			glCodeInstance.glCodeInstDelete();

		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

	@Test( priority = 7, enabled = true, description = "'GL Code Instance'  undelete action" )
	public void unDeleteGlCodeInstance() throws Exception
	{
		try
		{
			GLCodeInstance glCodeInstance = new GLCodeInstance( path, workBookName, sheetName, "GlCodeInstanceUnDelete" );
			glCodeInstance.glCodeInstUnDelete();

		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

}
