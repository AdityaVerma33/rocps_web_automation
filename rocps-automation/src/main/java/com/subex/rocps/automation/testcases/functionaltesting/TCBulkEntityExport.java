package com.subex.rocps.automation.testcases.functionaltesting;

import com.subex.automation.helpers.application.screens.TaskControllerHelper;
import com.subex.automation.helpers.util.FailureHelper;
import com.subex.rocps.automation.helpers.application.bulkentityexport.BulkEntityExport;
import com.subex.rocps.automation.helpers.application.system.PSTaskSearchHelper;
import com.subex.rocps.automation.helpers.application.system.Streams;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;

public class TCBulkEntityExport extends PSAcceptanceTest
{

	String path = System.getProperty( "user.dir" ) + "\\src\\main\\resources\\";
	String workBookName = "FunctionalTestCases.xlsx";
	String sheetName = "BulkEntityExport";

	@org.testng.annotations.Test( priority = 1, enabled = true, description = "Task Controller capabilities", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void taskControllerCapabilities() throws Exception
	{
		try
		{

			TaskControllerHelper taskObj = new TaskControllerHelper();
			taskObj.setTaskControllerCapability( path, workBookName, "BulkEntityExportStream", "TCCapability", 1 );

		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 2, description = "Bulk Entity Creation of Single Entity with Export All using  Save and Export", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void bulkEntitySingleEntExpAllSaveExport() throws Exception
	{
		try
		{
			BulkEntityExport bulkObj = new BulkEntityExport( path, workBookName, sheetName, "SingleEntity_ExportAll_SaveAndExport", 1 );
			bulkObj.bulkEntityExportCreation();
			bulkObj.bulkEntityScheduler();
			PSTaskSearchHelper tskObj = new PSTaskSearchHelper();
			tskObj.psVerifyTaskStatus( path, workBookName, sheetName, "ExportStatus", 1 );
			bulkObj.bulkEntityExportFileValidation();
		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 3, description = "Bulk Entity Creation of Single Entity with Export All using Scheduler", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void bulkEntitySingleEntExpAllSaveSchedule() throws Exception
	{
		try
		{
			BulkEntityExport bulkObj = new BulkEntityExport( path, workBookName, sheetName, "SingleEntity_ExportAll_Schedule", 1 );
			bulkObj.bulkEntityExportCreation();
			bulkObj.bulkEntityScheduler();
			PSTaskSearchHelper tskObj = new PSTaskSearchHelper();
			tskObj.psVerifyTaskStatus( path, workBookName, sheetName, "ExportStatus", 1 );
			bulkObj.bulkEntityExportFileValidation();
		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 4, description = "Bulk Entity Creation of Single Entiiy with Selected Items using Save and Export", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void bulkEntitySingleEntSelectItemSaveExport() throws Exception
	{
		try
		{
			BulkEntityExport bulkObj = new BulkEntityExport( path, workBookName, sheetName, "SingleEntity_SelectItems_SaveAndExport", 1 );
			bulkObj.bulkEntityExportCreation();
			bulkObj.bulkEntityScheduler();
			PSTaskSearchHelper tskObj = new PSTaskSearchHelper();
			tskObj.psVerifyTaskStatus( path, workBookName, sheetName, "ExportStatus", 1 );
			bulkObj.bulkEntityExportFileValidation();

		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 5, description = "Bulk Entity Creation of Single Entity with Slecetd Items using Scheduler", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void bulkEntitySingleEntSelectItemSaveSchedule() throws Exception
	{
		try
		{
			BulkEntityExport bulkObj = new BulkEntityExport( path, workBookName, sheetName, "SingleEntity_SelectItems_Schedule", 1 );
			bulkObj.bulkEntityExportCreation();
			bulkObj.bulkEntityScheduler();
			PSTaskSearchHelper tskObj = new PSTaskSearchHelper();
			tskObj.psVerifyTaskStatus( path, workBookName, sheetName, "ExportStatus", 1 );
			bulkObj.bulkEntityExportFileValidation();

		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 6, description = "Bulk Entity Creation of Multiple Entities with Export all using Save and Export", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void bulkEntityMultiEntExpAllSaveExport() throws Exception
	{
		try
		{
			BulkEntityExport bulkObj = new BulkEntityExport( path, workBookName, sheetName, "MultipleEntities_ExportAll", 1 );
			bulkObj.bulkEntityExportCreation();
			bulkObj.bulkEntityScheduler();
			bulkObj.bulkEntityMonitoring();
			bulkObj.bulkEntityExportFileValidation();

		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 7, description = "Bulk Entity Creation of Multiple Entities with Selected Items using Save and Export", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void bulkEntityMultiEntSelectItemSaveExport() throws Exception
	{
		try
		{
			BulkEntityExport bulkObj = new BulkEntityExport( path, workBookName, sheetName, "Multiple_SelectItems", 1 );
			bulkObj.bulkEntityExportCreation();
			bulkObj.bulkEntityScheduler();
			bulkObj.bulkEntityMonitoring();
			bulkObj.bulkEntityExportFileValidation();

		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 8, description = "Bulk Entity Creation of Multiple Entities with combination of Export All and Selected Items using Save and Export", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void bulkEntityMultiEntComboSaveExport() throws Exception
	{
		try
		{
			BulkEntityExport bulkObj = new BulkEntityExport( path, workBookName, sheetName, "Multiple_Combination_SaveAndExport", 1 );
			bulkObj.bulkEntityExportCreation();
			bulkObj.bulkEntityScheduler();
			bulkObj.bulkEntityMonitoring();
			bulkObj.bulkEntityExportFileValidation();

		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 9, description = "Bulk Entity Creation of Multiple Entities with combination of Export All and Selected Items using Scheduler.", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void bulkEntityMultiEntComboSchedule() throws Exception
	{
		try
		{
			BulkEntityExport bulkObj = new BulkEntityExport( path, workBookName, sheetName, "Multiple_Combination_Schedule", 1 );
			bulkObj.bulkEntityExportCreation();
			bulkObj.bulkEntityScheduler();
			bulkObj.bulkEntityMonitoring();
			bulkObj.bulkEntityExportFileValidation();

		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}

	/*@org.testng.annotations.Test( priority = 10, description = "Bulk Entity Creation of Roaming Combination (TAP IN, TAP OUT etc)", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void bulkEntityRoamingCombination() throws Exception
	{
		try
		{
			BulkEntityExport bulkObj = new BulkEntityExport( path, workBookName, sheetName, "Roaming_Combination", 1 );
			bulkObj.bulkEntityExportCreation();
			bulkObj.bulkEntityScheduler();
			bulkObj.bulkEntityMonitoring();
			bulkObj.bulkEntityExportFileValidation();

		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}*/

	@org.testng.annotations.Test( priority = 11, description = "Bulk Entity Creation of Event Combination (Event leg Code group, Event match Rule etc)", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void bulkEntityEventCombination() throws Exception
	{
		try
		{
			BulkEntityExport bulkObj = new BulkEntityExport( path, workBookName, sheetName, "Event_Combination", 1 );
			bulkObj.bulkEntityExportCreation();
			bulkObj.bulkEntityScheduler();
			bulkObj.bulkEntityMonitoring();
			bulkObj.bulkEntityExportFileValidation();
		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}
}
