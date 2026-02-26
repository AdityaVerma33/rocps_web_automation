package com.subex.rocps.automation.testcases.functionaltesting;

import com.subex.rocps.automation.helpers.application.admin.Entities;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;

import com.subex.automation.helpers.util.FailureHelper;

public class TCEntities extends PSAcceptanceTest
{

	String path = System.getProperty( "user.dir" ) + "\\src\\main\\resources\\";
	String workBookName = "FunctionalTestCases.xlsx";
	String sheetName = "Entities";

	@org.testng.annotations.Test( priority = 1, description = "Acount entity edit", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void acccountEdit() throws Exception
	{
		try
		{
			Entities accobj = new Entities( path, workBookName, sheetName, "EntitiesAccount", 1 );
			accobj.editEntities();

		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 2, description = "billprofie entity edit", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void billProfileEdit() throws Exception
	{
		try
		{

			Entities acc1obj = new Entities( path, workBookName, sheetName, "Entities BillProfile", 1 );
			acc1obj.editEntities();
		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}
}
