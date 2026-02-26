package com.subex.rocps.automation.testcases.functionaltesting;

import com.subex.rocps.automation.helpers.application.referenceTable.ElementSet;
import com.subex.rocps.automation.helpers.application.system.PSTaskSearchHelper;
import com.subex.rocps.automation.helpers.application.tariffs.PSTariffHelper;
import com.subex.rocps.automation.helpers.application.tariffs.RateSheetImportRequest;
import com.subex.rocps.automation.helpers.application.tariffs.RateSheetTemplateConfiguration;
import com.subex.rocps.automation.helpers.application.tariffs.RateSheetValidation;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;

import com.subex.automation.helpers.application.screens.TariffClassHelper;
import com.subex.automation.helpers.application.screens.TariffHelper;
import com.subex.automation.helpers.application.screens.TaskControllerHelper;
import com.subex.automation.helpers.application.screens.TaskSearchHelper;
import com.subex.automation.helpers.util.FailureHelper;

public class TCRatesheetServerCases extends PSAcceptanceTest
{
	String path = System.getProperty( "user.dir" ) + "\\src\\main\\resources\\";
	String workBookName = "FunctionalTestCases.xlsx";
	String sheetName = "RatesheetServerSideDest";

	@org.testng.annotations.Test( priority = 1, enabled = true, description = "task Controller capabilities", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void taskControllerCapabilities() throws Exception
	{
		try
		{

			TaskControllerHelper taskObj = new TaskControllerHelper();
			taskObj.setTaskControllerCapability( path, workBookName, "RateSheet", "TCCapability", 1 );

		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 2, description = "Tariff creation for Transit" ,retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class)
	public void createTariffTransit() throws Exception
	{
		try
		{

			ElementSet eleObj = new ElementSet( path, workBookName, "RatesheetServerSideDest", "TariffClassExp ElementSet", 1 );
			eleObj.elementSetCreation();
			TariffClassHelper trffClassObj = new TariffClassHelper();
			trffClassObj.createTariffClass( path, workBookName, sheetName, "TariffClass RS", 1 );

			PSTariffHelper pstrffObj = new PSTariffHelper();
			pstrffObj.createTariff( path, workBookName, sheetName, "TariffRS", 1 );

		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 3, description = "AutoRateSheet config" )
	public void rateSheetTemplateConfigDest() throws Exception
	{

		try
		{
			RateSheetTemplateConfiguration ratesheetObj = new RateSheetTemplateConfiguration( path, workBookName, sheetName, "T1_Destination" );
			ratesheetObj.destinationConfigRateSheetTemplate();
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}

	}

	@org.testng.annotations.Test( priority = 4, description = "AutoRateSheet config" )
	public void rateSheetTemplateConfigOrigin() throws Exception
	{

		try
		{
			RateSheetTemplateConfiguration ratesheetObj = new RateSheetTemplateConfiguration( path, workBookName, sheetName, "T2_Origin" );
			ratesheetObj.originConfigRateSheetTemplate();
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}

	}

	@org.testng.annotations.Test( priority = 5, description = "RSImportRequest1-taskStatus and TariffBand Validation-Destination Based" )
	public void rsRequest1_destinationBand() throws Exception
	{
		try
		{

			RateSheetImportRequest ratesheetObj = new RateSheetImportRequest( path, workBookName, sheetName, "RSRequest1_Dest_completeUncheck" );
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

	@org.testng.annotations.Test( priority = 6, description = "RSImportRequest1-taskStatus and TariffBand Validation-Destination Based" )
	public void rsAuthorize1_destinationBand() throws Exception
	{
		try
		{

			RateSheetImportRequest rsAuthorizeObj = new RateSheetImportRequest( path, workBookName, sheetName, "RSRequest1_Dest_completeUncheck" );
			rsAuthorizeObj.authorizeImport();
			PSTaskSearchHelper tskObj = new PSTaskSearchHelper();
			tskObj.psVerifyTaskStatus( path, workBookName, sheetName, "RSTaskStatus_AuthorizationTask", 1 );

		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 7, description = "RSImportRequest1-taskStatus and TariffBand Validation-Destination Based" )
	public void rsResultAndTariffValidation_destinationBand() throws Exception
	{
		try
		{

			RateSheetImportRequest ratesheetObj1 = new RateSheetImportRequest( path, workBookName, sheetName, "RSRequest1_Dest_completeUncheck" );
			ratesheetObj1.rateSheetRequestResultsValidation();
			RateSheetValidation ratesheetValObj = new RateSheetValidation( path, workBookName, sheetName, "TariffValidation-Req1" );
			ratesheetValObj.tariffBandValidation();

		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 8, description = "RSImportRequest2-taskStatus and TariffBand Validation-DestinationBased" )
	public void rsRequest2_DestinationBased() throws Exception
	{
		try
		{
			RateSheetImportRequest ratesheetObj = new RateSheetImportRequest( path, workBookName, sheetName, "RSRequest2_Dest_completeCheck" );
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

	@org.testng.annotations.Test( priority = 9, description = "RSImportRequest2-taskStatus and TariffBand Validation-DestinationBased" )
	public void rsesult2AndTariffValidation_destinationBased() throws Exception
	{
		try
		{

			RateSheetImportRequest ratesheetObj1 = new RateSheetImportRequest( path, workBookName, sheetName, "RSRequest2_Dest_completeCheck" );
			ratesheetObj1.rateSheetRequestResultsValidation();
			RateSheetValidation ratesheetValObj = new RateSheetValidation( path, workBookName, sheetName, "TariffValidation-Req2" );
			ratesheetValObj.tariffBandValidation();
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 10, description = "RSImportRequest3-taskStatus and TariffBand Validation-DestinationBased" )
	public void rsRequest3_DestinationBased() throws Exception
	{
		try
		{

			RateSheetImportRequest ratesheetObj = new RateSheetImportRequest( path, workBookName, sheetName, "RSRequest3_Dest_completeUncheck" );
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

	@org.testng.annotations.Test( priority = 11, description = "RSImportRequest3-taskStatus and TariffBand Validation-DestinationBased" )
	public void rs3ResultAndTariffValidation_DestinationBased() throws Exception
	{
		try
		{

			RateSheetImportRequest ratesheetObj1 = new RateSheetImportRequest( path, workBookName, sheetName, "RSRequest3_Dest_completeUncheck" );
			ratesheetObj1.rateSheetRequestResultsValidation();
			RateSheetValidation ratesheetValObj = new RateSheetValidation( path, workBookName, sheetName, "TariffValidation-Req3" );
			ratesheetValObj.tariffBandValidation();
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 12, description = "RSImportRequest4-taskStatus and TariffBand Validation-OriginBased" )
	public void rsRequest4_OriginBased() throws Exception
	{
		try
		{
			RateSheetImportRequest ratesheetObj = new RateSheetImportRequest( path, workBookName, "RatesheetServerSideOrigin", "RSRequest1_Origin_completeUncheck" );
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

	@org.testng.annotations.Test( priority = 13, description = "RSImportRequest4-taskStatus and TariffBand Validation-OriginBased" )
	public void rsResult4AndTariffValidation_OriginBased() throws Exception
	{
		try
		{

			RateSheetImportRequest ratesheetObj1 = new RateSheetImportRequest( path, workBookName, "RatesheetServerSideOrigin", "RSRequest1_Origin_completeUncheck" );
			ratesheetObj1.rateSheetRequestResultsValidation();
			RateSheetValidation ratesheetValObj = new RateSheetValidation( path, workBookName, "RatesheetServerSideOrigin", "TariffValidation-Req4" );
			ratesheetValObj.tariffBandValidation();
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 14, description = "RSImportRequest5-taskStatus and TariffBand Validation-OriginBased" )
	public void rsRequest5_OriginBased() throws Exception
	{
		try
		{
			RateSheetImportRequest ratesheetObj = new RateSheetImportRequest( path, workBookName, "RatesheetServerSideOrigin", "RSRequest2_Origin_completeCheck" );
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

	@org.testng.annotations.Test( priority = 15, description = "RSImportRequest5-taskStatus and TariffBand Validation-OriginBased" )
	public void rsResult5AndTariffValidation_OriginBased() throws Exception
	{
		try
		{

			RateSheetImportRequest ratesheetObj1 = new RateSheetImportRequest( path, workBookName, "RatesheetServerSideOrigin", "RSRequest2_Origin_completeCheck" );
			ratesheetObj1.rateSheetRequestResultsValidation();
			RateSheetValidation ratesheetValObj = new RateSheetValidation( path, workBookName, "RatesheetServerSideOrigin", "TariffValidation-Req5" );
			ratesheetValObj.tariffBandValidation();
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 16, description = "RSImportRequest6-taskStatus and TariffBand Validation-OriginBased" )
	public void rsRequest6OriginBased() throws Exception
	{
		try
		{
			RateSheetImportRequest ratesheetObj = new RateSheetImportRequest( path, workBookName, "RatesheetServerSideOrigin", "RSRequest6_Origin_completeUnCheck" );
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

	@org.testng.annotations.Test( priority = 17, description = "RSImportRequest6-taskStatus and TariffBand Validation-OriginBased" )
	public void rsResult6AndTariffValidation_OriginBased() throws Exception
	{
		try
		{

			RateSheetImportRequest ratesheetObj1 = new RateSheetImportRequest( path, workBookName, "RatesheetServerSideOrigin", "RSRequest6_Origin_completeUnCheck" );
			ratesheetObj1.rateSheetRequestResultsValidation();
			RateSheetValidation ratesheetValObj = new RateSheetValidation( path, workBookName, "RatesheetServerSideOrigin", "TariffValidation-Req6" );
			ratesheetValObj.tariffBandValidation();
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 18, description = "RSImportRequest2-taskStatus and TariffBand Validation-DestinationBased _updateFuturePeriod and ExpireElements" )
	public void rsReq7AndResult_DestBased_updateFuturePeriod() throws Exception
	{
		try
		{
			RateSheetImportRequest ratesheetObj = new RateSheetImportRequest( path, workBookName, sheetName, "RSRequest7_Dest_CompleteUncheck" );
			ratesheetObj.rateSheetImportRequest();
			PSTaskSearchHelper tskObj = new PSTaskSearchHelper();
			tskObj.psVerifyTaskStatus( path, workBookName, sheetName, "RSTaskStatus", 1 );
			RateSheetImportRequest ratesheetObj1 = new RateSheetImportRequest( path, workBookName, sheetName, "RSRequest7_Dest_CompleteUncheck" );
			ratesheetObj1.rateSheetRequestResultsValidation();

		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 19, description = "RSImportRequest2-taskStatus and TariffBand Validation-DestinationBased _updateFuturePeriod and ExpireElements" )
	public void rsReq8AndResult_DestBased_updateFuturePeriod() throws Exception
	{
		try
		{

			RateSheetImportRequest ratesheetObj2 = new RateSheetImportRequest( path, workBookName, sheetName, "RSRequest8_Dest_expireElements" );
			ratesheetObj2.rateSheetImportRequest();
			PSTaskSearchHelper tskObj2 = new PSTaskSearchHelper();
			tskObj2.psVerifyTaskStatus( path, workBookName, sheetName, "RSTaskStatus", 1 );
			RateSheetImportRequest ratesheetObj3 = new RateSheetImportRequest( path, workBookName, sheetName, "RSRequest8_Dest_expireElements" );
			ratesheetObj3.rateSheetRequestResultsValidation();

		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 20, description = "RSImportRequest2-taskStatus and TariffBand Validation-DestinationBased _updateFuturePeriod and ExpireElements" )
	public void rsReq9AndResult_DestBased_updateFuturePeriod() throws Exception
	{
		try
		{

			RateSheetImportRequest ratesheetObj4 = new RateSheetImportRequest( path, workBookName, sheetName, "RSRequest9_Dest_updatefuture" );
			ratesheetObj4.rateSheetImportRequest();
			PSTaskSearchHelper tskObj3 = new PSTaskSearchHelper();
			tskObj3.psVerifyTaskStatus( path, workBookName, sheetName, "RSTaskStatus", 1 );
			RateSheetImportRequest ratesheetObj5 = new RateSheetImportRequest( path, workBookName, sheetName, "RSRequest9_Dest_updatefuture" );
			ratesheetObj5.rateSheetRequestResultsValidation();

		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 21, description = "RSImportRequest2-taskStatus and TariffBand Validation-DestinationBased _ExpirePriorElements" )
	public void rsTariffValidation_DestBased_updateFuturePeriod() throws Exception
	{
		try
		{

			RateSheetValidation ratesheetValObj = new RateSheetValidation( path, workBookName, sheetName, "TariffValidation-Jan" );
			ratesheetValObj.tariffBandValidation();
			RateSheetValidation ratesheetValObj1 = new RateSheetValidation( path, workBookName, sheetName, "TariffValidation-Feb" );
			ratesheetValObj1.tariffBandValidation();
			RateSheetValidation ratesheetValObj2 = new RateSheetValidation( path, workBookName, sheetName, "TariffValidation-Mar" );
			ratesheetValObj2.tariffBandValidation();
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 22, description = "Tariff creation for Transit" )
	public void createTariffTransit_Expiry() throws Exception
	{
		try
		{

			ElementSet eleObj = new ElementSet( path, workBookName, "RatesheetServerSideDest", "TariffClassExp ElementSet", 1 );
			eleObj.elementSetCreation();
			TariffClassHelper trffClassObj = new TariffClassHelper();
			trffClassObj.createTariffClass( path, workBookName, sheetName, "TariffClassExp RS", 1 );

			PSTariffHelper pstrffObj = new PSTariffHelper();
			pstrffObj.createTariff( path, workBookName, sheetName, "TariffExpRS", 1 );

		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 23, description = "RateSheet tempalte config- Expiration Strategy", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void rsTemplateExpirationStrategy() throws Exception
	{

		try
		{
			RateSheetTemplateConfiguration ratesheetObj = new RateSheetTemplateConfiguration( path, workBookName, sheetName, "T1_DestinationTestEXP" );
			ratesheetObj.destinationConfigRateSheetTemplate();

		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			;
			throw e;
		}

	}

	@org.testng.annotations.Test( priority = 24, description = "RS Import request for Expiration startegy" )
	public void rsRequestExpriry1Validation() throws Exception
	{
		try
		{

			RateSheetImportRequest ratesheetObj = new RateSheetImportRequest( path, workBookName, sheetName, "RateSheetImportRequestEXP1" );
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

	@org.testng.annotations.Test( priority = 25, description = "RS Import request for Expiration startegy" )
	public void rsRequestExpriry1TariffValidation() throws Exception
	{
		try
		{

			RateSheetValidation ratesheetValObj = new RateSheetValidation( path, workBookName, sheetName, "TariffValidation-Exp1" );
			ratesheetValObj.tariffBandValidation();

		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 26, description = "RS Import request for Expiration startegy" )
	public void rsRequestExpriry2Validation() throws Exception
	{
		try
		{

			RateSheetImportRequest ratesheetObj3 = new RateSheetImportRequest( path, workBookName, sheetName, "RateSheetImportRequestEXP2" );
			ratesheetObj3.rateSheetImportRequest();
			PSTaskSearchHelper tskObj2 = new PSTaskSearchHelper();
			tskObj2.psVerifyTaskStatus( path, workBookName, sheetName, "RSTaskStatus", 1 );

		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 27, description = "RS Import request for Expiration startegy" )
	public void rsRequestExpriry2TariffValidation() throws Exception
	{
		try
		{

			RateSheetValidation ratesheetValObj1 = new RateSheetValidation( path, workBookName, sheetName, "TariffValidation-Exp2" );
			ratesheetValObj1.tariffBandValidation();

		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 28, description = "RateSheet tempalte config- Expiration Strategy", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void rsTemplateExpirationStrategyMin() throws Exception
	{

		try
		{
			RateSheetTemplateConfiguration ratesheetObj = new RateSheetTemplateConfiguration( path, workBookName, sheetName, "T1_DestinationEXPMin" );
			ratesheetObj.destinationConfigRateSheetTemplate();

		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			;
			throw e;
		}

	}

	@org.testng.annotations.Test( priority = 29, description = "RS Import request for Expiration startegy - Minimum" )
	public void rsRequestExpriryValidationMin1() throws Exception
	{
		try
		{

			RateSheetImportRequest ratesheetObj = new RateSheetImportRequest( path, workBookName, sheetName, "RateSheetImportRequestEXPMin1" );
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

	@org.testng.annotations.Test( priority = 30, description = "RS Import request for Expiration startegy - Minimum" )
	public void rsRequestExpriryMin1_tariffBandValidation() throws Exception
	{
		try
		{

			RateSheetValidation ratesheetValObj = new RateSheetValidation( path, workBookName, sheetName, "TariffValidation-ExpMin1" );
			ratesheetValObj.tariffBandValidation();

		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 31, description = "RS Import request for Expiration startegy - Minimum" )
	public void rsRequestExpriryValidationMin2() throws Exception
	{
		try
		{

			RateSheetImportRequest ratesheetObj3 = new RateSheetImportRequest( path, workBookName, sheetName, "RateSheetImportRequestEXPMin2" );
			ratesheetObj3.rateSheetImportRequest();
			PSTaskSearchHelper tskObj2 = new PSTaskSearchHelper();
			tskObj2.psVerifyTaskStatus( path, workBookName, sheetName, "RSTaskStatus", 1 );

		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 32, description = "RS Import request for Expiration startegy - Minimum" )
	public void rsRequestExpriryMin2_tariffBandValidation() throws Exception
	{
		try
		{

			RateSheetValidation ratesheetValObj1 = new RateSheetValidation( path, workBookName, sheetName, "TariffValidation-ExpMin2" );
			ratesheetValObj1.tariffBandValidation();

		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 33, description = "RateSheet tempalte config- Expiration Strategy Maximum", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void rsTemplateExpirationStrategyMax() throws Exception
	{

		try
		{
			RateSheetTemplateConfiguration ratesheetObj = new RateSheetTemplateConfiguration( path, workBookName, sheetName, "T1_DestinationEXPMax" );
			ratesheetObj.destinationConfigRateSheetTemplate();

		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			;
			throw e;
		}

	}

	@org.testng.annotations.Test( priority = 34, description = "RS Import request for Expiration startegy- Maximum" )
	public void rsRequestExpriryValidationMax1() throws Exception
	{
		try
		{

			RateSheetImportRequest ratesheetObj = new RateSheetImportRequest( path, workBookName, sheetName, "RateSheetImportRequestEXPMax1" );
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

	@org.testng.annotations.Test( priority = 35, description = "RS Import request for Expiration startegy- Maximum" )
	public void rsRequestExpriryMax1_tariffBandValidation() throws Exception
	{
		try
		{

			RateSheetValidation ratesheetValObj = new RateSheetValidation( path, workBookName, sheetName, "TariffValidation-ExpMax1" );
			ratesheetValObj.tariffBandValidation();

		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 36, description = "RS Import request for Expiration startegy- Maximum" )
	public void rsRequestExpriryValidationMax2() throws Exception
	{
		try
		{

			RateSheetImportRequest ratesheetObj3 = new RateSheetImportRequest( path, workBookName, sheetName, "RateSheetImportRequestEXPMax2" );
			ratesheetObj3.rateSheetImportRequest();
			PSTaskSearchHelper tskObj2 = new PSTaskSearchHelper();
			tskObj2.psVerifyTaskStatus( path, workBookName, sheetName, "RSTaskStatus", 1 );

		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 37, description = "RS Import request for Expiration startegy- Maximum" )
	public void rsRequestExprirytariffBandValidationMax2() throws Exception
	{
		try
		{

			RateSheetValidation ratesheetValObj1 = new RateSheetValidation( path, workBookName, sheetName, "TariffValidation-ExpMax2" );
			ratesheetValObj1.tariffBandValidation();

		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}
}
