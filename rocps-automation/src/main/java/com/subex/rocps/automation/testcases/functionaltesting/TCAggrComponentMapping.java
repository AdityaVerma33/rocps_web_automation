package com.subex.rocps.automation.testcases.functionaltesting;

import com.subex.automation.helpers.util.FailureHelper;
import com.subex.rocps.automation.helpers.application.referenceTable.AggrComponentMapping;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;

public class TCAggrComponentMapping extends PSAcceptanceTest
{

	String path = System.getProperty( "user.dir" ) + "\\src\\main\\resources\\";
	String workBookName = "FunctionalTestCases.xlsx";
	String sheetName = "AggrCompMapping";

	@org.testng.annotations.Test( priority = 1, description = "aggrComponentMapping creation", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void aggrComponentMappingCreation() throws Exception
	{
		try
		{
			AggrComponentMapping aggrObj = new AggrComponentMapping( path, workBookName, sheetName, "AggrCompMapping", 1 );
			aggrObj.aggrComponentMappingCreation();
		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 2, description = "aggrComponentMapping deletion", dependsOnMethods = "aggrComponentMappingCreation" )
	public void aggrComponentMappingDelete() throws Exception
	{
		try
		{
			AggrComponentMapping aggrDelObj = new AggrComponentMapping( path, workBookName, sheetName, "AggrCompMappingDelete", 1 );
			aggrDelObj.aggrComponentMappingDelete();
		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 3, description = "aggrComponentMapping un delete", dependsOnMethods = "aggrComponentMappingDelete" )
	public void aggrComponentMappingUnDelete() throws Exception
	{
		try
		{
			AggrComponentMapping aggrunDelObj = new AggrComponentMapping( path, workBookName, sheetName, "AggrCompMappingUnDelete", 1 );
			aggrunDelObj.aggrComponentMappingUnDelete();
		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 4, description = "aggrComponentMapping search screen col validation" )
	public void aggrComponentMappingColVal() throws Exception
	{
		try
		{
			AggrComponentMapping aggColValObj = new AggrComponentMapping( path, workBookName, sheetName, "AggrMapSearchScreencolVal", 1 );
			aggColValObj.searchScreenColumnsValidation();
		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 5, description = "aggrComponentMapping Creation for Fios", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void aggrComponentMappingCreationFios() throws Exception
	{
		try
		{
			AggrComponentMapping aggrObj = new AggrComponentMapping( path, workBookName, sheetName, "AggrCompMapping Fios", 1 );
			aggrObj.aggrComponentMappingCreation();
		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}

}
