package com.subex.rocps.automation.testcases.functionaltesting;

import org.testng.annotations.Test;

import com.subex.rocps.automation.helpers.application.accruals.GLCodeDefn;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;

import com.subex.automation.helpers.util.FailureHelper;

public class TCGLCodeDefn extends PSAcceptanceTest
{

	String path = System.getProperty( "user.dir" ) + "\\src\\main\\resources\\";

	String workBookName = "FunctionalTestCases.xlsx";
	String sheetName = "GlCodeDefn";

	@Test( priority = 1, enabled = true, description = "'GL Code Definition'  verify the column Headers Of Search Screen" )
	public void verifyTheColHdrs() throws Exception
	{
		try
		{

			GLCodeDefn glCodeDefn = new GLCodeDefn( path, workBookName, sheetName, "GlCodeDefnScreencolVal" );
			glCodeDefn.glCodeDefnColumsValidation();
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

	@Test( priority = 2, enabled = true, description = "'GL Code Definition'  creation" )
	public void createGlCodeDefn() throws Exception
	{
		try
		{

			GLCodeDefn glCodeDefn = new GLCodeDefn( path, workBookName, sheetName, "TestGlCodeDefnCreation" );
			glCodeDefn.glCodeDefnCreation();
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

	@Test( priority = 3, enabled = true, description = "'GL Code Definition'  edit" )
	public void editGlCodeDefn() throws Exception
	{
		try
		{

			GLCodeDefn glCodeDefn = new GLCodeDefn( path, workBookName, sheetName, "TestGlCodeDefnEdit" );
			glCodeDefn.glCodeDefnEdit();
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

}
