package com.subex.rocps.automation.testcases.functionaltesting;

import com.subex.automation.helpers.application.screens.TaskControllerHelper;
import com.subex.automation.helpers.util.FailureHelper;
import com.subex.rocps.automation.helpers.application.deal.Deal;
import com.subex.rocps.automation.helpers.application.dealImport.DealImport;
import com.subex.rocps.automation.helpers.application.dealImport.DealImportTemplate;
import com.subex.rocps.automation.helpers.application.pdfToExcel.pdfToExcelConversion.PDFToExcelConversion;
import com.subex.rocps.automation.helpers.application.system.PSTaskSearchHelper;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;

public class TCDealImport extends PSAcceptanceTest
{
	PSTaskSearchHelper tskObj = new PSTaskSearchHelper();
	String path = System.getProperty( "user.dir" ) + "\\src\\main\\resources\\";
	String workBookName = "FunctionalTestingDeals.xlsx";
	String sheetName = "DealImport";

	@org.testng.annotations.Test( priority = 1, enabled = true, description = "Task Controller capabilities", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void taskControllerCapabilities() throws Exception
	{
		try
		{

			TaskControllerHelper taskObj = new TaskControllerHelper();
			taskObj.setTaskControllerCapability( path, workBookName, sheetName, "TCCapability", 1 );

		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}
	
	@org.testng.annotations.Test( priority = 2, enabled = true, description = "Column Validation for Deal Import Screen.", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void dealImportColumnValidation() throws Exception
	{

		try
		{

			DealImport dealObj = new DealImport( path, workBookName, sheetName, "ConversionScreencolVal" );
			dealObj.dealImportColumnValidation();
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}
	@org.testng.annotations.Test( priority = 3, enabled = true, description = "Importing a Committed Deal by Deal Import.", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void dealImportCommitted() throws Exception
	{
		try
		{

			DealImport dealObj = new DealImport( path, workBookName, sheetName, "DealImportCommitted" );
			dealObj.dealImportCreation();
			tskObj.psVerifyTaskStatus( path, workBookName, sheetName, "DealImportStatus", 1  );
			dealObj.dealImportValidation();
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}
	@org.testng.annotations.Test( priority = 3, enabled = true, description = "Importing a Non Committed Deal by Deal Import.", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void dealImportNonCommitted() throws Exception
	{
		try
		{

			DealImport dealObj = new DealImport( path, workBookName, sheetName, "DealImportNonCommitted" );
			dealObj.dealImportCreation();
			tskObj.psVerifyTaskStatus( path, workBookName, sheetName, "DealImportStatus", 1  );
			dealObj.dealImportValidation();
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}
	@org.testng.annotations.Test( priority = 4, enabled = true, description = "Importing a Deal with Configuration Error by Deal Import.", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void dealImportErrorConfiguration() throws Exception
	{

		try
		{

			DealImport dealObj = new DealImport( path, workBookName, sheetName, "DealImportErrorConfiguration" );
			dealObj.dealImportCreation();
			
			tskObj.psVerifyTaskStatus( path, workBookName, sheetName, "DealImportStatus", 1  );
			dealObj.dealImportErrorValidation();
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}
	@org.testng.annotations.Test( priority = 5, enabled = true, description = "Importing a Deal with Data Error by Deal Import.", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void dealImportErrorData() throws Exception
	{

		try
		{

			DealImport dealObj = new DealImport( path, workBookName, sheetName, "DealImportErrorData" );
			dealObj.dealImportCreation();
			
			tskObj.psVerifyTaskStatus( path, workBookName, sheetName, "DealImportStatus", 1  );
			dealObj.dealImportErrorValidation();
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}
}
