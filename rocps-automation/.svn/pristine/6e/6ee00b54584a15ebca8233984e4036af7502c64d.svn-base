package com.subex.rocps.automation.testcases.functionaltesting;

import com.subex.rocps.automation.helpers.application.carrierinvoice.SystemFieldList;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;

import com.subex.automation.helpers.util.FailureHelper;

public class TCSystemFieldList extends PSAcceptanceTest
{
	String path = System.getProperty( "user.dir" ) + "\\src\\main\\resources\\";
	String workBookName = "FunctionalTestCases.xlsx";
	String sheetName = "SystemFieldList";

	@org.testng.annotations.Test( priority = 1, description = "System Field List Search Screen Column Validation" )
	public void systemFieldListcolumnValidation() throws Exception
	{
		try
		{
			SystemFieldList sysObj = new SystemFieldList( path, workBookName, sheetName, "SystemFieldLine_SearchScreenColumnVal" );
			sysObj.searchScreenColumnsValidation();
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}
	
	@org.testng.annotations.Test( priority = 2, description = "System Field List creation", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void createSystemFieldList() throws Exception
	{
		try
		{
			SystemFieldList sysObj = new SystemFieldList( path, workBookName, sheetName, "SystemFieldLine" );
			sysObj.configureSystemFieldList();
			sysObj.systemFieldListChangeStatus();
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 3, description = "System Field List creation- Structure-Blended" ,retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class)
	public void createSystemFieldListStructureBlended() throws Exception
	{
		try
		{
			SystemFieldList sysObj = new SystemFieldList( path, workBookName, sheetName, "SystemFieldLine_StdStructure_Blended" );
			sysObj.configureSystemFieldList();
			//sysObj.systemFieldListChangeStatus();
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 4, description = "System Field List Edit- Structure-Blended",retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void editSystemFieldListStructureBlended() throws Exception
	{
		try
		{
			SystemFieldList sysObj = new SystemFieldList( path, workBookName, sheetName, "EditSystemFieldLine_StdStructure_Blended" );
			sysObj.editSystemFieldList();
			//sysObj.systemFieldListChangeStatus();
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}
	
	@org.testng.annotations.Test( priority = 5, description = "System Field List Edit- Structure-Blended" ,retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class)
	public void systemFieldListStructureChangeStatus() throws Exception
	{
		try
		{
			SystemFieldList sysObj = new SystemFieldList( path, workBookName, sheetName, "SystemFieldLine_StdStructure_Blended" );			
			sysObj.systemFieldListChangeStatus();
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 6, description = "System Field List creation- Structured", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void createSystemFieldListStructure() throws Exception
	{
		try
		{
			SystemFieldList sysObj = new SystemFieldList( path, workBookName, sheetName, "SystemFieldLine_StdStructure" );
			sysObj.configureSystemFieldList();
			sysObj.systemFieldListChangeStatus();

		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 7, description = "System Field List creation- Delete" )
	public void systemFieldListDelete() throws Exception
	{
		try
		{
			SystemFieldList sysObj = new SystemFieldList( path, workBookName, sheetName, "SystemFieldLine_Delete" );
			sysObj.systemFieldListDelete();
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 8, description = "System Field List creation- UnDelete" )
	public void systemFieldListUnDelete() throws Exception
	{
		try
		{
			SystemFieldList sysObj = new SystemFieldList( path, workBookName, sheetName, "SystemFieldLine_UnDelete" );
			sysObj.systemFieldListUnDelete();
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

}
