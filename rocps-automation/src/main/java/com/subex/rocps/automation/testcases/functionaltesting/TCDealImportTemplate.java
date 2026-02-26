package com.subex.rocps.automation.testcases.functionaltesting;

import com.subex.automation.helpers.application.screens.TaskControllerHelper;
import com.subex.automation.helpers.util.FailureHelper;
import com.subex.rocps.automation.helpers.application.dealImport.DealImport;
import com.subex.rocps.automation.helpers.application.dealImport.DealImportTemplate;
import com.subex.rocps.automation.helpers.application.system.PSTaskSearchHelper;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;

public class TCDealImportTemplate extends PSAcceptanceTest
{

	String path = System.getProperty( "user.dir" ) + "\\src\\main\\resources\\";
	String workBookName = "FunctionalTestingDeals.xlsx";
	String sheetName = "DealImportTemplate";

	@org.testng.annotations.Test( priority = 1, enabled = true, description = "Task Controller capabilities", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void taskControllerCapabilities() throws Exception
	{
		try
		{

			TaskControllerHelper taskObj = new TaskControllerHelper();
			taskObj.setTaskControllerCapability( path, workBookName, "DealImportTemplate", "TCCapability", 1 );

		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 2, enabled = true, description = "Column Validation for Deal Import Template Screen.", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void dealImportTemplateColumnValidation() throws Exception
	{
		
		try
		{

			DealImportTemplate templateObj = new DealImportTemplate(path, workBookName, sheetName, "DelImportTemplateScreencolVal");
			templateObj.dealImportColumnValidation();
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}
	@org.testng.annotations.Test( priority = 3, enabled = true, description = "Configuring a Deal Import Template.", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void dealImportTemplateConfiguration() throws Exception
	{
		try
		{

			DealImportTemplate dealObj = new DealImportTemplate( path, workBookName, sheetName, "DealImportTemplateConfiguration" );
			dealObj.dealImportTemplateCreation();
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}
}
