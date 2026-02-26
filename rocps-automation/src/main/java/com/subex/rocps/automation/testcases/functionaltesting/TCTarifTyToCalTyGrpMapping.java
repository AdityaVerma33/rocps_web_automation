package com.subex.rocps.automation.testcases.functionaltesting;

import org.testng.annotations.Test;

import com.subex.rocps.automation.helpers.application.referenceTable.CallTypeGroup;
import com.subex.rocps.automation.helpers.application.referenceTable.TariffTypToCalTypGrpMapping;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;

import com.subex.automation.helpers.util.FailureHelper;

public class TCTarifTyToCalTyGrpMapping extends PSAcceptanceTest
{

	String path = System.getProperty( "user.dir" ) + "\\src\\main\\resources\\";

	String workBookName = "FunctionalTestCases.xlsx";
	String sheetName = "TariffTypeToCallTypeGrpMapping";

	@Test( priority = 1, enabled = true, description = "'Tariff Type to Call Type Group Mapping'  prerequiste of Call Type Group creation",retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void callTypeGrpPrerequisite() throws Exception
	{
		try
		{
			CallTypeGroup callTypeGroup = new CallTypeGroup( path, workBookName, "CallTypeGroup", "TestCallTypeGroupCreation" );
			callTypeGroup.callTypeGrpCreation();

		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}
	@Test( priority = 2, enabled = true, description = "'Tariff Type to Call Type Group Mapping'  verify the column Headers Of Search Screen" )
	public void verifyTheColHdrs() throws Exception
	{
		try
		{
			TariffTypToCalTypGrpMapping tariffTypToCalTypGrp=new TariffTypToCalTypGrpMapping( path, workBookName, sheetName, "TariffTypeToCallTypeGrpMappingScreencolVal" );
			tariffTypToCalTypGrp.tariffTyToCalTyGrpColumsValidation();

		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}
	
	@Test( priority = 3, enabled = true, description = "'Tariff Type to Call Type Group Mapping' creation" )
	public void tariffTypeToCallTypeGrpMappingCreation() throws Exception
	{
		try
		{
			TariffTypToCalTypGrpMapping tariffTypToCalTypGrp=new TariffTypToCalTypGrpMapping( path, workBookName, sheetName, "TestTariffTypeToCallTypeGrpCreation" );
			tariffTypToCalTypGrp.tariffTyToCallTyGrpCreation();

		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}
	@Test( priority = 4, enabled = true, description = "'Tariff Type to Call Type Group Mapping' edit" )
	public void tariffTypeToCallTypeGrpMappingEdit() throws Exception
	{
		try
		{
			TariffTypToCalTypGrpMapping tariffTypToCalTypGrp=new TariffTypToCalTypGrpMapping( path, workBookName, sheetName, "TestTariffTypeToCallTypeGrpEdit" );
			tariffTypToCalTypGrp.tariffTyToCallTyGrpEdit();

		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}
}
