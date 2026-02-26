package com.subex.rocps.automation.testcases.functionaltesting;

import org.testng.annotations.Test;

import com.subex.automation.helpers.application.screens.TariffClassHelper;
import com.subex.automation.helpers.application.screens.TaskControllerHelper;
import com.subex.automation.helpers.util.FailureHelper;
import com.subex.rocps.automation.helpers.application.matchandrate.Operator;
import com.subex.rocps.automation.helpers.application.partnerConfiguration.Account;
import com.subex.rocps.automation.helpers.application.partnerConfiguration.Agent;
import com.subex.rocps.automation.helpers.application.referenceTable.ElementSet;
import com.subex.rocps.automation.helpers.application.referenceTable.TariffType;
import com.subex.rocps.automation.helpers.application.system.PSTaskSearchHelper;
import com.subex.rocps.automation.helpers.application.tariffs.PSTariffHelper;
import com.subex.rocps.automation.helpers.application.tariffs.RateSheetImportRequest;
import com.subex.rocps.automation.helpers.application.tariffs.RateSheetTemplateConfiguration;
import com.subex.rocps.automation.helpers.application.tariffs.RateSheetValidation;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;

public class TCVoiceRORatesheetPrerequisite extends PSAcceptanceTest
{

	String path = System.getProperty( "user.dir" ) + "\\src\\main\\resources\\";
	String workBookName = "FunctionalTestCasesRO.xlsx";
	String sheetName = "Ratesheet";

	@org.testng.annotations.Test( priority = 1, description = "task Controller capabilities", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void taskControllerCapabilities() throws Exception
	{
		try
		{

			TaskControllerHelper taskObj = new TaskControllerHelper();
			taskObj.setTaskControllerCapability( path, workBookName, "RO_Prerequisites", "TCCapability_Rerate", 1 );
			taskObj.setTaskControllerCapability( path, workBookName, "RO_Prerequisites", "TCCapability_Ratesheet", 1 );
			taskObj.setTaskControllerCapability( path, workBookName, "RO_Prerequisites", "TCCapability_RO", 1 );
			taskObj.setTaskControllerCapability( path, workBookName, "RO_Prerequisites", "TCCapability_BcrGenerationRoutingRate", 1 );

		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

	@Test( priority = 2, enabled = true, description = "'Ratesheet VOice RO '  prerequisite" )
	public void creteTariffSystemMDL() throws Exception
	{
		try
		{

			ElementSet eleObj = new ElementSet( path, workBookName, sheetName, "ElementSetCreation_SystemMDL", 1 );
			eleObj.elementSetCreation();
			TariffClassHelper trffClassObj = new TariffClassHelper();
			trffClassObj.createTariffClass( path, workBookName, sheetName, "TariffClass_SystemMDL", 1 );

			PSTariffHelper pstrffObj = new PSTariffHelper();
			pstrffObj.createTariff( path, workBookName, sheetName, "Tariff_SystemMDL", 1 );
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 3, description = "Ratesheet Destination System MDL" , retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void rateSheetTemplateConfigDest() throws Exception
	{

		try
		{
			RateSheetTemplateConfiguration ratesheetObj = new RateSheetTemplateConfiguration( path, workBookName, sheetName, "T1Destination_SystemMdl" );
			ratesheetObj.destinationConfigRateSheetTemplate();
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}

	}

	@Test( priority = 4, enabled = true, description = "'Ratesheet VOice RO '  prerequisite" , retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void creteTariffOp1() throws Exception
	{
		try
		{

			ElementSet eleObj = new ElementSet( path, workBookName, sheetName, "ElementSetCreation_operator", 1 );
			eleObj.elementSetCreation();
			TariffClassHelper trffClassObj = new TariffClassHelper();
			trffClassObj.createTariffClass( path, workBookName, sheetName, "TariffClass_operator1", 1 );

			PSTariffHelper pstrffObj = new PSTariffHelper();
			pstrffObj.createTariff( path, workBookName, sheetName, "Tariff_operator1", 1 );
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 5, description = "Ratesheet Destination System MDL", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class  )
	public void rateSheetTemplateOp1() throws Exception
	{

		try
		{
			RateSheetTemplateConfiguration ratesheetObj = new RateSheetTemplateConfiguration( path, workBookName, sheetName, "T1Destination_operator1" );
			ratesheetObj.destinationConfigRateSheetTemplate();
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}

	}

	@org.testng.annotations.Test( priority = 6, description = "RSImportRequest1-taskStatus and TariffBand Validation-Destination Based" , retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void rsRequest1_destinationBand_SysMDL() throws Exception
	{
		try
		{

			RateSheetImportRequest ratesheetObj = new RateSheetImportRequest( path, workBookName, sheetName, "RSRequest1_Dest_SysMDL" );
			ratesheetObj.rateSheetImportRequest();
			PSTaskSearchHelper tskObj = new PSTaskSearchHelper();
			tskObj.psVerifyTaskStatus( path, workBookName, sheetName, "RSTaskStatus", 1 );

		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 7, description = "RSImportRequest1-taskStatus and TariffBand Validation-Destination Based", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class  )
	public void rsResultAndTariffValidation_destinationBandSysMDL() throws Exception
	{
		try
		{

			RateSheetImportRequest ratesheetObj1 = new RateSheetImportRequest( path, workBookName, sheetName, "RSRequest1_Dest_SysMDL" );
			ratesheetObj1.rateSheetRequestResultsValidation();
			RateSheetValidation ratesheetValObj = new RateSheetValidation( path, workBookName, sheetName, "TariffValidation-SysMDL" );
			ratesheetValObj.tariffBandValidation();

		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 8, description = "RSImportRequest1-taskStatus and TariffBand Validation-Destination Based", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class  )
	public void rsRequest1_destinationBand_op1() throws Exception
	{
		try
		{

			RateSheetImportRequest ratesheetObj = new RateSheetImportRequest( path, workBookName, sheetName, "RSRequest1_Dest_Op1" );
			ratesheetObj.rateSheetImportRequest();
			PSTaskSearchHelper tskObj = new PSTaskSearchHelper();
			tskObj.psVerifyTaskStatus( path, workBookName, sheetName, "RSTaskStatus", 1 );

		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 9, description = "RSImportRequest1-taskStatus and TariffBand Validation-Destination Based" , retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void rsResultAndTariffValidation_destinationBandop1() throws Exception
	{
		try
		{

			RateSheetImportRequest ratesheetObj1 = new RateSheetImportRequest( path, workBookName, sheetName, "RSRequest1_Dest_Op1" );
			ratesheetObj1.rateSheetRequestResultsValidation();
			RateSheetValidation ratesheetValObj = new RateSheetValidation( path, workBookName, sheetName, "TariffValidation-Op1" );
			ratesheetValObj.tariffBandValidation();

		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

}
