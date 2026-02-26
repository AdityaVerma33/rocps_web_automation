package com.subex.rocps.automation.testcases.functionaltesting;

import org.testng.annotations.Test;
import com.subex.automation.helpers.util.FailureHelper;
import com.subex.rocps.automation.helpers.application.products.ProvisionStatusGroupHelper;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;

public class TCProvisionStatusGroup extends PSAcceptanceTest
{
	String path = System.getProperty( "user.dir" ) + "\\src\\main\\resources\\";

	String workBookName = "FunctionalTestCases.xlsx";
	String sheetName = "ProvisionStatusGroup";

	@Test( priority = 1, enabled = true, description = "Provision Status Group verify the column Headers Of Search Screen" )
	public void verifyTheColHdrs() throws Exception
	{
		try
		{
			ProvisionStatusGroupHelper provisionStatusGroupOb = new ProvisionStatusGroupHelper( path, workBookName, sheetName, "ProvisionStatusGroupScreencolVal" );
			provisionStatusGroupOb.provisionStatusGrpColumsValidation();
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

	@Test( priority = 2, enabled = true, description = "Provision Status Group creation" )
	public void provisionStatusGrpCreation() throws Exception
	{
		try
		{
			ProvisionStatusGroupHelper provisionStatusGroupOb = new ProvisionStatusGroupHelper( path, workBookName, sheetName, "ProvisionStatusGroupCreation" );
			provisionStatusGroupOb.provisionStatusGrpCreation();
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

	@Test( priority = 3, enabled = true, description = "Provision Status Group Edit" )
	public void provisionStatusGrpEdit() throws Exception
	{
		try
		{
			ProvisionStatusGroupHelper provisionStatusGroupOb = new ProvisionStatusGroupHelper( path, workBookName, sheetName, "ProvisionStatusGroupEdit" );
			provisionStatusGroupOb.provisionStatusGrpEdit();
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

	@Test( priority = 4, enabled = true, description = "Provision Status Group Delete" )
	public void provisionStatusGrpDelete() throws Exception
	{
		try
		{
			ProvisionStatusGroupHelper provisionStatusGroupOb = new ProvisionStatusGroupHelper( path, workBookName, sheetName, "ProvisionStatusGrpDelete" );
			provisionStatusGroupOb.provisionStatusGrpDelete();
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

	@Test( priority = 5, enabled = true, description = "Provision Status Group UnDelete" )
	public void provisionStatusGrpUnDelete() throws Exception
	{
		try
		{
			ProvisionStatusGroupHelper provisionStatusGroupOb = new ProvisionStatusGroupHelper( path, workBookName, sheetName, "ProvisionStatusGrpUnDelete" );
			provisionStatusGroupOb.provisionStatusGrpUnDelete();
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}
}
