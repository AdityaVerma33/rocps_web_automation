package com.subex.rocps.automation.helpers.application.system;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import com.subex.automation.helpers.application.NavigationHelper;
import com.subex.automation.helpers.component.ButtonHelper;
import com.subex.automation.helpers.component.CheckBoxHelper;
import com.subex.automation.helpers.component.ComboBoxHelper;
import com.subex.automation.helpers.component.ElementHelper;
import com.subex.automation.helpers.component.EntityComboHelper;
import com.subex.automation.helpers.component.EntitySearchHelper;
import com.subex.automation.helpers.component.GenericHelper;
import com.subex.automation.helpers.component.GridHelper;
import com.subex.automation.helpers.component.LabelHelper;
import com.subex.automation.helpers.component.PropertyGridHelper;
import com.subex.automation.helpers.component.TextBoxHelper;
import com.subex.automation.helpers.data.ValidationHelper;
import com.subex.automation.helpers.file.ExcelReader;
import com.subex.automation.helpers.report.Log4jHelper;
import com.subex.automation.helpers.util.FailureHelper;

import com.subex.rocps.automation.helpers.application.filters.PSSearchGridHelper;
import com.subex.rocps.automation.helpers.application.genericHelpers.PSGenericHelper;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;
import com.subex.rocps.automation.utils.ExcelHolder;
import com.subex.rocps.automation.utils.PSStringUtils;

public class StreamStagesImpl extends PSAcceptanceTest
{
	ExcelReader excelReader = new ExcelReader();
	PSStringUtils stringobj = new PSStringUtils();
	StreamImpl strImplObj = new StreamImpl();
	PSGenericHelper psGenericHelper = new PSGenericHelper();

	/*
	 * This is for Stream stage type panel
	 */
	public void streamStageType( String streamStageType, String stsName, String stsRetryFlag, String stsRetryAttempts, String stsInterval, String stsLookback ) throws Exception
	{
		ComboBoxHelper.select( "Stream_StreamStage_Type", streamStageType );
		GenericHelper.waitForLoadmask( detailScreenWaitSec );
		TextBoxHelper.type( "Stream_StreamStage_Name", stsName );

		if ( ValidationHelper.isTrue( stsRetryFlag ) )
		{
			CheckBoxHelper.check( "Stream_StreamStage_RestartTask" );
			TextBoxHelper.type( "Stream_StreamStage_RestartAttempts", stsRetryAttempts );
			TextBoxHelper.type( "Stream_StreamStage_RestartInterval", stsInterval );
			TextBoxHelper.type( "Stream_StreamStage_RestartLookback", stsLookback );
		}
	}

	/*
	 * This method is for parse stream stage
	 */

	public void parseStreamStage( String path, String workBookName, String sheetName, String testCaseName, int occurence ) throws Exception
	{

		HashMap<String, ArrayList<String>> map = excelReader.readDataByColumn( path, workBookName, sheetName, testCaseName );

		streamStageType( map.get( "streamStageType" ).get( 0 ), map.get( "stsName" ).get( 0 ), map.get( "stsRetryFlag" ).get( 0 ), map.get( "stsRetryAttempts" ).get( 0 ), map.get( "stsInterval" ).get( 0 ), map.get( "stsLookback" ).get( 0 ) );

		if ( ValidationHelper.isFalse( map.get( "Check For Stale Caches Every File" ).get( 0 ) ) )
			PropertyGridHelper.unCheckCheckBox( "Check For Stale Caches Every File" );
		if ( ValidationHelper.isFalse( "Enable Retry On Failure" ) )
			PropertyGridHelper.unCheckCheckBox( "Enable Retry On Failure(only for 'Ascii' parser)" );
		if ( ValidationHelper.isTrue( map.get( "Log Error With Rejected Record" ).get( 0 ) ) )
			PropertyGridHelper.checkCheckBox( "Log Error With Rejected Record" );
		if ( ValidationHelper.isFalse( map.get( "Log Record Type Distributions" ).get( 0 ) ) )
			PropertyGridHelper.unCheckCheckBox( "Log Record Type Distributions" );

		PropertyGridHelper.typeInDataDir( "Output Directory *", map.get( "Output Directory" ).get( 0 ), ";" );
		PropertyGridHelper.typeInDataDir( "Parser Definition File *", map.get( "Parser Definition File" ).get( 0 ), ";" );

		if ( ValidationHelper.isTrue( map.get( "Skip Records On Error" ).get( 0 ) ) )
			PropertyGridHelper.checkCheckBox( "Skip Records On Error" );

		ButtonHelper.click( "OKButton" );
		GenericHelper.waitForLoadmask( detailScreenWaitSec );

		addParseOutputMap( map.get( "parseOutputMap" ).get( 0 ) );

		ButtonHelper.click( "OKButton" );
		GenericHelper.waitForLoadmask( detailScreenWaitSec );
		assertTrue( LabelHelper.isTitleNotPresent( "New Stream Stage" ), "Stream Stage did not get saved." );
		Log4jHelper.logInfo( "Stream stage is screated successfully for Parse" );

	}

	/*
	 * This method is for dataload stream stage
	 */

	public void dataLoadStreamStage( String path, String workBookName, String sheetName, String testCaseName, int occurence ) throws Exception
	{
		HashMap<String, ArrayList<String>> map = excelReader.readDataByColumn( path, workBookName, sheetName, testCaseName );
		String stsName = map.get( "stsName" ).get( 0 );
		if ( !isStreamStagePresent( stsName ) )
		{
			strImplObj.addStreamStageButton();
			streamStageType( map.get( "streamStageType" ).get( 0 ), map.get( "stsName" ).get( 0 ), map.get( "stsRetryFlag" ).get( 0 ), map.get( "stsRetryAttempts" ).get( 0 ), map.get( "stsInterval" ).get( 0 ), map.get( "stsLookback" ).get( 0 ) );

			if ( ValidationHelper.isTrue( map.get( "Delete Input File" ).get( 0 ) ) )
				PropertyGridHelper.checkCheckBox( "Delete Input File" );
			if ( ValidationHelper.isFalse( map.get( "Enable Retry On Failure" ).get( 0 ) ) )
				PropertyGridHelper.unCheckCheckBox( "Enable Retry On Failure" );
			if ( ValidationHelper.isTrue( map.get( "Log Data Load Output" ).get( 0 ) ) )
				PropertyGridHelper.checkCheckBox( "Log Data Load Output" );

			PropertyGridHelper.typeInTextBox( "Look Back Days To Do Conventional Data Load (In Days) *", map.get( "Look Back Days To Do Conventional Data Load" ).get( 0 ) );
			PropertyGridHelper.typeInTextBox( "Maximum Error Count Per Parse Output File", map.get( "Maximum Error Count Per Parse Output File" ).get( 0 ) );

			if ( ValidationHelper.isTrue( map.get( "Populate DataLoad Statistics" ).get( 0 ) ) )
				PropertyGridHelper.checkCheckBox( "Populate DataLoad Statistics" );
			if ( ValidationHelper.isTrue( map.get( "Truncate Before Load(Valid only for user defined Reference)" ).get( 0 ) ) )
				PropertyGridHelper.checkCheckBox( "Truncate Before Load(Valid only for user defined Referenc..." );

			ButtonHelper.click( "OKButton" );
			GenericHelper.waitForLoadmask( detailScreenWaitSec );
			assertTrue( LabelHelper.isTitleNotPresent( "New Stream Stage" ), "Stream Stage did not get saved." );
			Log4jHelper.logInfo( "Stream stage is screated successfully for Data Load" );
		}
		else
			Log4jHelper.logInfo( "Stream stage '" + stsName + "' is already present for Stream" );
	}

	/*
	 * This method is for match and Rate stream stage
	 */

	public void matchandRateStreamStage( String path, String workBookName, String sheetName, String testCaseName, int occurence ) throws Exception
	{
		HashMap<String, ArrayList<String>> map = excelReader.readDataByColumn( path, workBookName, sheetName, testCaseName );
		streamStageType( map.get( "streamStageType" ).get( 0 ), map.get( "stsName" ).get( 0 ), map.get( "stsRetryFlag" ).get( 0 ), map.get( "stsRetryAttempts" ).get( 0 ), map.get( "stsInterval" ).get( 0 ), map.get( "stsLookback" ).get( 0 ) );

		PropertyGridHelper.selectInComboBox( "Default Error Status *", map.get( "Default Error Status" ).get( 0 ) );
		PropertyGridHelper.selectInComboBox( "Event Error Extra Field Mapping Component *", map.get( "Event Error Extra Field Mapping Component" ).get( 0 ) );
		PropertyGridHelper.typeInTextBox( "Event ID Allocation Size *", map.get( "Event ID Allocation Size" ).get( 0 ) );
		PropertyGridHelper.selectInComboBox( "Event Type *", map.get( "Event Type" ).get( 0 ) );
		PropertyGridHelper.typeInTextBox( "Input Queue Size *", map.get( "Input Queue Size" ).get( 0 ) );
		if ( ValidationHelper.isTrue( map.get( "Perform Suspense Rating" ).get( 0 ) ) )
			PropertyGridHelper.checkCheckBox( "Perform Suspense Rating *" );
		PropertyGridHelper.selectInComboBox( "Post Matching And Rating Component *", map.get( "Post Matching And Rating Component" ).get( 0 ) );
		PropertyGridHelper.selectInComboBox( "Post Processor Component *", map.get( "Post Processor Component" ).get( 0 ) );
		PropertyGridHelper.typeInTextBox( "Post Processor Thread Count *", map.get( "Post Processor Thread Count" ).get( 0 ) );
		PropertyGridHelper.typeInTextBox( "Processed Queue Size *", map.get( "Processed Queue Size" ).get( 0 ) );
		PropertyGridHelper.typeInTextBox( "Processor Thread Count *", map.get( "Processor Thread Count" ).get( 0 ) );

		if ( ValidationHelper.isTrue( map.get( "Refresh Cache" ).get( 0 ) ) )
			PropertyGridHelper.checkCheckBox( "Refresh Cache *" );
		if ( ValidationHelper.isFalse( map.get( "Reject Record When No Pre Rate Found" ).get( 0 ) ) )
			PropertyGridHelper.unCheckCheckBox( "Reject Record When No Pre Rate Found *" );
		if ( ValidationHelper.isFalse( map.get( "Reject Zero Usage Events" ).get( 0 ) ) )
			PropertyGridHelper.unCheckCheckBox( "Reject Zero Usage Events *" );
		PropertyGridHelper.selectInComboBox( "Rerate Reprocess Component *", map.get( "Rerate Reprocess Component" ).get( 0 ) );
		PropertyGridHelper.selectInComboBox( "Suspense Rating Tariff", map.get( "Suspense Rating Tariff" ).get( 0 ) );
		PropertyGridHelper.selectInComboBox( "Suspense Reprocess Component *", map.get( "Suspense Reprocess Component" ).get( 0 ) );
		PropertyGridHelper.typeInDataDir( "Usage Output Directory *", map.get( "Usage Output Directory" ).get( 0 ), ";" );
		PropertyGridHelper.typeInTextBox( "Valid Charges *", map.get( "Valid Charges" ).get( 0 ) );

		ButtonHelper.click( "OKButton" );
		GenericHelper.waitForLoadmask( detailScreenWaitSec );
		assertTrue( LabelHelper.isTitleNotPresent( "New Stream Stage" ), "Stream Stage did not get saved." );
		Log4jHelper.logInfo( "Stream stage is created successfully for Match and Rate" );

	}
	/*
	 * This method is for master task stream stage
	 */

	public void masterTaskStreamStage( String path, String workBookName, String sheetName, String testCaseName, int occurence ) throws Exception
	{
		HashMap<String, ArrayList<String>> map = excelReader.readDataByColumn( path, workBookName, sheetName, testCaseName );

		streamStageType( map.get( "streamStageType" ).get( 0 ), map.get( "stsName" ).get( 0 ), map.get( "stsRetryFlag" ).get( 0 ), map.get( "stsRetryAttempts" ).get( 0 ), map.get( "stsInterval" ).get( 0 ), map.get( "stsLookback" ).get( 0 ) );

		PropertyGridHelper.selectInComboBox( "Master Component *", map.get( "Master Component" ).get( 0 ) );

		ButtonHelper.click( "OKButton" );
		GenericHelper.waitForLoadmask( detailScreenWaitSec );
		assertTrue( LabelHelper.isTitleNotPresent( "New Stream Stage" ), "Stream Stage did not get saved." );
		Log4jHelper.logInfo( "Stream stage is created successfully for Master Task" );
	}

	/*
	 * This method is for aggreagation stream stage
	 */

	public void aggregationStreamStage( String path, String workBookName, String sheetName, String testCaseName, int occarance ) throws Exception
	{
		HashMap<String, ArrayList<String>> map = excelReader.readDataByColumn( path, workBookName, sheetName, testCaseName );
		streamStageType( map.get( "streamStageType" ).get( 0 ), map.get( "stsName" ).get( 0 ), map.get( "stsRetryFlag" ).get( 0 ), map.get( "stsRetryAttempts" ).get( 0 ), map.get( "stsInterval" ).get( 0 ), map.get( "stsLookback" ).get( 0 ) );

		PropertyGridHelper.selectInComboBox( "Aggregation Processor *", map.get( "Aggregation Processor" ).get( 0 ) );
		PropertyGridHelper.typeInTextBox( "Wait time (in seconds) before skipping concurrent aggrega...", map.get( "Wait time" ).get( 0 ) );

		ButtonHelper.click( "OKButton" );
		GenericHelper.waitForLoadmask( detailScreenWaitSec );
		assertTrue( LabelHelper.isTitleNotPresent( "New Stream Stage" ), "Stream Stage did not get saved." );
		Log4jHelper.logInfo( "Stream stage is created successfully for Aggregation" );

	}

	/*
	 * This method is for parseOutput Map in parse stream stage
	 */

	private void addParseOutputMap( String parseOutputMap ) throws Exception
	{

		assertTrue( LabelHelper.isTextPresent( "configureParseOutputMapMsg" ), "Parse Output Map Confirmation popup did not appear." );

		if ( ValidationHelper.isEmpty( parseOutputMap ) )
		{
			ButtonHelper.click( "NoButton" );
			GenericHelper.waitForLoadmask( detailScreenWaitSec );
		}
		else
		{
			ButtonHelper.click( "YesButton" );
			GenericHelper.waitForLoadmask( detailScreenWaitSec );
			assertTrue( LabelHelper.isTitlePresent( "Parse Output Maps" ), "Parse Output Map popup did not appear." );

			String gridId = "Stream_ParseOutput_Grid";

			String[] parseOutputArr = stringobj.stringSplitFirstLevel( parseOutputMap );

			ButtonHelper.click( "Stream_ParseOutput_Add" );
			GenericHelper.waitForLoadmask( detailScreenWaitSec );

			GridHelper.updateGridTextBox( gridId, "Stream_ParseOutput_OutputKey", 1, "Output Key", "Table Instance", parseOutputArr[0] );

			if ( !EntityComboHelper.isPresent( "Stream_ParseOutput_TableInstance" ) )
				GridHelper.clickRow( gridId, 1, "Table Instance" );
			tableInstanceEntitySearch( "Stream_ParseOutput_TableInstance", "Table Instance Search", "TableInst_TableName", parseOutputArr[1], "Table Name" );

			GridHelper.updateGridCheckBox( gridId, "Stream_ParseOutput_DataLoad", 1, "Data Load", parseOutputArr[2] );

		}

	}

	/*
	 * This method is for table instance entity search
	 */

	private void tableInstanceEntitySearch( String iconIdOrXpath, String entityScreenTitle, String txtBoxIdOrXpath, String value, String valueColumnHeader ) throws Exception
	{
		if ( ValidationHelper.isNotEmpty( value ) )
		{
			iconIdOrXpath = GenericHelper.getORProperty( iconIdOrXpath );
			EntityComboHelper.clickEntityIcon( iconIdOrXpath );
			ButtonHelper.click( "ClearButton" );
			ButtonHelper.click( "SearchButton" );
			GenericHelper.waitForLoadmask();
			EntitySearchHelper entitySearch = new EntitySearchHelper();
			entitySearch.selectUsingGridFilterTextBox( entityScreenTitle, txtBoxIdOrXpath, value, valueColumnHeader );

		}
	}

	/*
	 * This method is for credit stream stage
	 */
	public void creditBillingStreamStage( String path, String workBookName, String sheetName, String testCaseName, int occurence ) throws Exception
	{
		HashMap<String, ArrayList<String>> map = excelReader.readDataByColumn( path, workBookName, sheetName, testCaseName );
		String stsName = map.get( "stsName" ).get( 0 );
		if ( !isStreamStagePresent( stsName ) )
		{
			strImplObj.addStreamStageButton();
			streamStageType( map.get( "streamStageType" ).get( 0 ), map.get( "stsName" ).get( 0 ), map.get( "stsRetryFlag" ).get( 0 ), map.get( "stsRetryAttempts" ).get( 0 ), map.get( "stsInterval" ).get( 0 ), map.get( "stsLookback" ).get( 0 ) );

			PropertyGridHelper.selectInComboBox( "Billing Production Path *", map.get( "Billing Production Path" ).get( 0 ) );
			PropertyGridHelper.typeInDataDir( "Credit Output Directory *", map.get( "Credit Output directory" ).get( 0 ) );

			ButtonHelper.click( "OKButton" );
			GenericHelper.waitForLoadmask( detailScreenWaitSec );
			assertTrue( LabelHelper.isTitleNotPresent( "New Stream Stage" ), "Stream Stage did not get saved." );
			Log4jHelper.logInfo( "Stream stage is created successfully for Credit" );
		}
		else
			Log4jHelper.logInfo( "Stream stage '" + stsName + "' is already present for Stream" );
	}
	
	
	/*
	 * This method is for bill stream stage
	 */
	
	public void billStreamStage( String path, String workBookName, String sheetName, String testCaseName, int occurence ) throws Exception
	{
		HashMap<String, ArrayList<String>> map = excelReader.readDataByColumn( path, workBookName, sheetName, testCaseName );
		String stsName = map.get( "stsName" ).get( 0 );
		if ( !isStreamStagePresent( stsName ) )
		{
			strImplObj.addStreamStageButton();
			streamStageType( map.get( "streamStageType" ).get( 0 ), map.get( "stsName" ).get( 0 ), map.get( "stsRetryFlag" ).get( 0 ), map.get( "stsRetryAttempts" ).get( 0 ), map.get( "stsInterval" ).get( 0 ), map.get( "stsLookback" ).get( 0 ) );

			
			
			PropertyGridHelper.typeInDataDir( "Bill Output Directory *", map.get( "Bill Output Directory" ).get( 0 ) );
			PropertyGridHelper.selectInComboBox( "Billing Production Path *", map.get( "Billing Production Path" ).get( 0 ) );
			PropertyGridHelper.typeInDataDir( "Test Output Directory *", map.get( "Test Output Directory" ).get( 0 ) );
			

			ButtonHelper.click( "OKButton" );
			GenericHelper.waitForLoadmask( detailScreenWaitSec );
			assertTrue( LabelHelper.isTitleNotPresent( "New Stream Stage" ), "Stream Stage did not get saved." );
			Log4jHelper.logInfo( "Stream stage is created successfully for Bill" );
		}
		else
			Log4jHelper.logInfo( "Stream stage '" + stsName + "' is already present for Stream" );
	}

	/*
	 * This method is for ratesheet import stream stage
	 */
	public void rateSheetImportStreamStage( String path, String workBookName, String sheetName, String testCaseName, int occurence ) throws Exception
	{
		HashMap<String, ArrayList<String>> map = excelReader.readDataByColumn( path, workBookName, sheetName, testCaseName );
		String stsName = map.get( "stsName" ).get( 0 );
		if ( !isStreamStagePresent( stsName ) )
		{
			strImplObj.addStreamStageButton();
			streamStageType( map.get( "streamStageType" ).get( 0 ), map.get( "stsName" ).get( 0 ), map.get( "stsRetryFlag" ).get( 0 ), map.get( "stsRetryAttempts" ).get( 0 ), map.get( "stsInterval" ).get( 0 ), map.get( "stsLookback" ).get( 0 ) );

			PropertyGridHelper.typeInTextBox( "Block Size *", map.get( "BlockSize" ).get( 0 ) );
			if ( ValidationHelper.isTrue( map.get( "CalculatePricedElements" ).get( 0 ) ) )
				PropertyGridHelper.checkCheckBox( "Calculate Previously Priced Elements *" );
			if ( ValidationHelper.isTrue( map.get( "CalculateRoutingRate" ).get( 0 ) ) )
				PropertyGridHelper.checkCheckBox( "Calculate Routing Rate *" );
			PropertyGridHelper.selectInComboBox( "Default Element Report Group *", map.get( "DefaultElementGrp" ).get( 0 ) );
			if ( ValidationHelper.isFalse( map.get( "ElementUsedforMatching" ).get( 0 ) ) )
				PropertyGridHelper.unCheckCheckBox( "Element Used for Matching *" );
			if ( ValidationHelper.isTrue( map.get( "ElementMatchWhole" ).get( 0 ) ) )
				PropertyGridHelper.checkCheckBox( "ElementMatch Whole *" );
			PropertyGridHelper.typeInDataDir( "Excel Import Rate Sheet Directory *", map.get( "ExcelImportRSDir" ).get( 0 ) );
			PropertyGridHelper.selectInComboBox( "Multiple Column Delimiter *", map.get( "MultipleColumnDelimiter" ).get( 0 ) );
			PropertyGridHelper.typeInTextBox( "Termination PollTime (sec) *", map.get( "TerminationPollTime" ).get( 0 ) );
			PropertyGridHelper.typeInTextBox( "Termination TimeOut (sec) *", map.get( "TerminationTimeOut" ).get( 0 ) );
			ButtonHelper.click( "OKButton" );
			GenericHelper.waitForLoadmask( searchScreenWaitSec );
			assertEquals( NavigationHelper.getScreenTitle(), "Edit Stream", "Stream Stage did not get saved." );
			Log4jHelper.logInfo( "Stream stage is created successfully for Rate sheet" );
		}
		else
			Log4jHelper.logInfo( "Stream stage '" + stsName + "' is already present for Stream" );
	}

	/*
	 * This method is for auto ratesheet Import stream stage
	 */
	public void autoRateSheetImportStreamStage( String path, String workBookName, String sheetName, String testCaseName, int occurence ) throws Exception
	{
		HashMap<String, ArrayList<String>> map = excelReader.readDataByColumn( path, workBookName, sheetName, testCaseName );
		String stsName = map.get( "stsName" ).get( 0 );
		if ( !isStreamStagePresent( stsName ) )
		{
			strImplObj.addStreamStageButton();
			streamStageType( map.get( "streamStageType" ).get( 0 ), map.get( "stsName" ).get( 0 ), map.get( "stsRetryFlag" ).get( 0 ), map.get( "stsRetryAttempts" ).get( 0 ), map.get( "stsInterval" ).get( 0 ), map.get( "stsLookback" ).get( 0 ) );

			PropertyGridHelper.typeInTextBox( "Host", map.get( "Host" ).get( 0 ) );
			PropertyGridHelper.typeInTextBox( "Look Back Day(s) *", map.get( "LookBackDays" ).get( 0 ) );
			PropertyGridHelper.typeInTextBox( "Password", map.get( "Password" ).get( 0 ) );
			PropertyGridHelper.typeInTextBox( "Port *", map.get( "Port" ).get( 0 ) );
			PropertyGridHelper.typeInTextBox( "Protocol", map.get( "Protocol" ).get( 0 ) );
			PropertyGridHelper.typeInTextBox( "SmtpHost", map.get( "SmtpHost" ).get( 0 ) );
			PropertyGridHelper.typeInTextBox( "SmtpPort *", map.get( "SmtpPort" ).get( 0 ) );
			PropertyGridHelper.typeInTextBox( "UserName", map.get( "UserName" ).get( 0 ) );
			ButtonHelper.click( "OKButton" );
			GenericHelper.waitForLoadmask( searchScreenWaitSec );
			assertEquals( NavigationHelper.getScreenTitle(), "Edit Stream", "Stream Stage did not get saved." );
			Log4jHelper.logInfo( "Stream stage is created successfully for Credit" );
		}
		else
			Log4jHelper.logInfo( "Stream stage '" + stsName + "' is already present for Stream" );
	}

	/*
	 * This method is for auto ratesheet pollImport
	 */
	public void autoRateSheetPollImportStreamStage( String path, String workBookName, String sheetName, String testCaseName, int occurence ) throws Exception
	{
		HashMap<String, ArrayList<String>> map = excelReader.readDataByColumn( path, workBookName, sheetName, testCaseName );
		String stsName = map.get( "stsName" ).get( 0 );
		if ( !isStreamStagePresent( stsName ) )
		{
			strImplObj.addStreamStageButton();
			streamStageType( map.get( "streamStageType" ).get( 0 ), map.get( "stsName" ).get( 0 ), map.get( "stsRetryFlag" ).get( 0 ), map.get( "stsRetryAttempts" ).get( 0 ), map.get( "stsInterval" ).get( 0 ), map.get( "stsLookback" ).get( 0 ) );
			GenericHelper.waitForLoadmask( searchScreenWaitSec );
			ButtonHelper.click( "OKButton" );
			GenericHelper.waitForLoadmask( searchScreenWaitSec );
			assertEquals( NavigationHelper.getScreenTitle(), "Edit Stream", "Stream Stage did not get saved." );
			Log4jHelper.logInfo( "Stream stage is created successfully for Credit" );
		}
		else
			Log4jHelper.logInfo( "Stream stage '" + stsName + "' is already present for Stream" );
	}

	/*
	 * This method is for generate report
	 */
	public void generateReportRSStreamStage( String path, String workBookName, String sheetName, String testCaseName, int occurence ) throws Exception
	{
		HashMap<String, ArrayList<String>> map = excelReader.readDataByColumn( path, workBookName, sheetName, testCaseName );
		String stsName = map.get( "stsName" ).get( 0 );
		if ( !isStreamStagePresent( stsName ) )
		{
			strImplObj.addStreamStageButton();
			streamStageType( map.get( "streamStageType" ).get( 0 ), map.get( "stsName" ).get( 0 ), map.get( "stsRetryFlag" ).get( 0 ), map.get( "stsRetryAttempts" ).get( 0 ), map.get( "stsInterval" ).get( 0 ), map.get( "stsLookback" ).get( 0 ) );
			GenericHelper.waitForLoadmask( searchScreenWaitSec );
			PropertyGridHelper.typeInDataDir( "Ratesheet Report Directory *", map.get( "RatesheetReportDir" ).get( 0 ) );
			ButtonHelper.click( "OKButton" );
			GenericHelper.waitForLoadmask( searchScreenWaitSec );
			assertEquals( NavigationHelper.getScreenTitle(), "Edit Stream", "Stream Stage did not get saved." );

			Log4jHelper.logInfo( "Stream stage is created successfully for Credit" );
		}
		else
			Log4jHelper.logInfo( "Stream stage '" + stsName + "' is already present for Stream" );
	}

	/*
	 * This method is for 'RateSheetAuthorizationTask' stream stage
	 */
	public void rateSheetAuthorizeStreamStage( String path, String workBookName, String sheetName, String testCaseName, int occurence ) throws Exception
	{
		HashMap<String, ArrayList<String>> map = excelReader.readDataByColumn( path, workBookName, sheetName, testCaseName );
		String stsName = map.get( "stsName" ).get( 0 );
		if ( !isStreamStagePresent( stsName ) )
		{
			strImplObj.addStreamStageButton();
			streamStageType( map.get( "streamStageType" ).get( 0 ), map.get( "stsName" ).get( 0 ), map.get( "stsRetryFlag" ).get( 0 ), map.get( "stsRetryAttempts" ).get( 0 ), map.get( "stsInterval" ).get( 0 ), map.get( "stsLookback" ).get( 0 ) );
			GenericHelper.waitForLoadmask( searchScreenWaitSec );
			ButtonHelper.click( "OKButton" );
			GenericHelper.waitForLoadmask( searchScreenWaitSec );
			assertEquals( NavigationHelper.getScreenTitle(), "Edit Stream", "Stream Stage did not get saved." );

			Log4jHelper.logInfo( "Stream stage is created successfully for -" + stsName );
		}
		else
			Log4jHelper.logInfo( "Stream stage '" + stsName + "' is already present for Stream" );
	}

	/*
	 * This method is for reAggregation
	 */
	public void reAggregationStreamStage( String path, String workBookName, String sheetName, String testCaseName, int occurence ) throws Exception
	{
		HashMap<String, ArrayList<String>> map = excelReader.readDataByColumn( path, workBookName, sheetName, testCaseName );
		String stsName = map.get( "stsName" ).get( 0 );
		if ( !isStreamStagePresent( stsName ) )
		{
			strImplObj.addStreamStageButton();
			streamStageType( map.get( "streamStageType" ).get( 0 ), map.get( "stsName" ).get( 0 ), map.get( "stsRetryFlag" ).get( 0 ), map.get( "stsRetryAttempts" ).get( 0 ), map.get( "stsInterval" ).get( 0 ), map.get( "stsLookback" ).get( 0 ) );

			PropertyGridHelper.typeInTextBox( "Error Id Allocation Size *", map.get( "ErrorIdAllocationSize" ).get( 0 ) );
			PropertyGridHelper.typeInTextBox( "Thread Count *", map.get( "ThreadCount" ).get( 0 ) );
			PropertyGridHelper.typeInDataDir( "Usage File Output Directory *", map.get( "UsageFileOutputDir" ).get( 0 ) );
			PropertyGridHelper.typeInTextBox( "Wait time (in seconds) before skipping concurrent reaggre...", map.get( "WaitTimeInSecs" ).get( 0 ) );
			ButtonHelper.click( "OKButton" );
			GenericHelper.waitForLoadmask( detailScreenWaitSec );
			assertEquals( NavigationHelper.getScreenTitle(), "Edit Stream", "Stream Stage did not get saved." );
			Log4jHelper.logInfo( "Stream stage is created successfully for Credit" );
		}
		else
			Log4jHelper.logInfo( "Stream stage '" + stsName + "' is already present for Stream" );
	}

	/*
	 * This method is for reAggregation MasterStreamstage
	 */
	public void reAggregationMasterTaskStreamStage( String path, String workBookName, String sheetName, String testCaseName, int occurence ) throws Exception
	{
		HashMap<String, ArrayList<String>> map = excelReader.readDataByColumn( path, workBookName, sheetName, testCaseName );
		String stsName = map.get( "stsName" ).get( 0 );
		if ( !isStreamStagePresent( stsName ) )
		{
			strImplObj.addStreamStageButton();
			streamStageType( map.get( "streamStageType" ).get( 0 ), map.get( "stsName" ).get( 0 ), map.get( "stsRetryFlag" ).get( 0 ), map.get( "stsRetryAttempts" ).get( 0 ), map.get( "stsInterval" ).get( 0 ), map.get( "stsLookback" ).get( 0 ) );

			PropertyGridHelper.selectInComboBox( "Master Component *", map.get( "MasterComponent" ).get( 0 ) );
			ButtonHelper.click( "OKButton" );
			GenericHelper.waitForLoadmask( detailScreenWaitSec );
			assertEquals( NavigationHelper.getScreenTitle(), "Edit Stream", "Stream Stage did not get saved." );
			Log4jHelper.logInfo( "Stream stage is created successfully for Credit" );
		}
		else
			Log4jHelper.logInfo( "Stream stage '" + stsName + "' is already present for Stream" );
	}

	/*
	 * This method is for prepayments
	 */
	public void prepaymentsStreamStage( String path, String workBookName, String sheetName, String testCaseName, int occurence ) throws Exception
	{
		HashMap<String, ArrayList<String>> map = excelReader.readDataByColumn( path, workBookName, sheetName, testCaseName );
		String stsName = map.get( "stsName" ).get( 0 );
		if ( !isStreamStagePresent( stsName ) )
		{
			strImplObj.addStreamStageButton();
			streamStageType( map.get( "streamStageType" ).get( 0 ), map.get( "stsName" ).get( 0 ), map.get( "stsRetryFlag" ).get( 0 ), map.get( "stsRetryAttempts" ).get( 0 ), map.get( "stsInterval" ).get( 0 ), map.get( "stsLookback" ).get( 0 ) );

			PropertyGridHelper.typeInTextBox( "Thread Count *", map.get( "ThreadCount" ).get( 0 ) );

			PropertyGridHelper.typeInTextBox( "Wait time (in seconds) before skipping concurrent prepaym...", map.get( "WaitTimeInSecs" ).get( 0 ) );
			ButtonHelper.click( "WaitTimeBeforeSkipPrepayment_id" );
			ButtonHelper.click( "OKButton" );
			GenericHelper.waitForLoadmask( detailScreenWaitSec );
			assertEquals( NavigationHelper.getScreenTitle(), "Edit Stream", "Stream Stage did not get saved." );
			Log4jHelper.logInfo( "Stream stage is created successfully for voice" );
		}
		else
			Log4jHelper.logInfo( "Stream stage '" + stsName + "' is already present for Stream" );
	}

	/*
	 * This method is for prepaymentsMasterTask
	 */
	public void prepaymentsMasterTaskStreamStage( String path, String workBookName, String sheetName, String testCaseName, int occurence ) throws Exception
	{
		HashMap<String, ArrayList<String>> map = excelReader.readDataByColumn( path, workBookName, sheetName, testCaseName );
		String stsName = map.get( "stsName" ).get( 0 );
		if ( !isStreamStagePresent( stsName ) )
		{
			strImplObj.addStreamStageButton();
			streamStageType( map.get( "streamStageType" ).get( 0 ), map.get( "stsName" ).get( 0 ), map.get( "stsRetryFlag" ).get( 0 ), map.get( "stsRetryAttempts" ).get( 0 ), map.get( "stsInterval" ).get( 0 ), map.get( "stsLookback" ).get( 0 ) );

			PropertyGridHelper.selectInComboBox( "Billing Production Path *", map.get( "BillingProductionPath" ).get( 0 ) );
			PropertyGridHelper.selectInComboBox( "Master Component *", map.get( "MasterComponent" ).get( 0 ) );
			PropertyGridHelper.typeInTextBox( "No Of Profiles Per Task *", map.get( "NoOfProfilesPerTask" ).get( 0 ) );
			ButtonHelper.click( "NoOfProfilesPerTask_id" );
			ButtonHelper.click( "OKButton" );
			GenericHelper.waitForLoadmask( detailScreenWaitSec );
			assertEquals( NavigationHelper.getScreenTitle(), "Edit Stream", "Stream Stage did not get saved." );
			Log4jHelper.logInfo( "Stream stage is created successfully for voice" );
		}
		else
			Log4jHelper.logInfo( "Stream stage '" + stsName + "' is already present for Stream" );
	}

	/*
	 * This method is for carrierinvoice stream stage
	 */
	public void carrierInvoiceStreamStage( String path, String workBookName, String sheetName, String testCaseName, int occurence ) throws Exception
	{
		HashMap<String, ArrayList<String>> map = excelReader.readDataByColumn( path, workBookName, sheetName, testCaseName );
		String stsName = map.get( "stsName" ).get( 0 );
		if ( !isStreamStagePresent( stsName ) )
		{
			strImplObj.addStreamStageButton();
			streamStageType( map.get( "streamStageType" ).get( 0 ), map.get( "stsName" ).get( 0 ), map.get( "stsRetryFlag" ).get( 0 ), map.get( "stsRetryAttempts" ).get( 0 ), map.get( "stsInterval" ).get( 0 ), map.get( "stsLookback" ).get( 0 ) );

			PropertyGridHelper.typeInDataDir( "Carrrier Invoice Import Directory *", map.get( "CarrierInvoiceImportDir" ).get( 0 ) );

			ButtonHelper.click( "OKButton" );
			GenericHelper.waitForLoadmask( detailScreenWaitSec );
			assertEquals( NavigationHelper.getScreenTitle(), "Edit Stream", "Stream Stage did not get saved." );
			Log4jHelper.logInfo( "Stream stage is created successfully for voice" );
		}
		else
			Log4jHelper.logInfo( "Stream stage '" + stsName + "' is already present for Stream" );
	}

	/*
	 * This method is for rerate stream stage
	 */
	public void rerateStreamStage( String path, String workBookName, String sheetName, String testCaseName, int occurence ) throws Exception
	{
		HashMap<String, ArrayList<String>> map = excelReader.readDataByColumn( path, workBookName, sheetName, testCaseName );
		String stsName = map.get( "stsName" ).get( 0 );
		if ( !isStreamStagePresent( stsName ) )
		{
			strImplObj.addStreamStageButton();
			streamStageType( map.get( "streamStageType" ).get( 0 ), map.get( "stsName" ).get( 0 ), map.get( "stsRetryFlag" ).get( 0 ), map.get( "stsRetryAttempts" ).get( 0 ), map.get( "stsInterval" ).get( 0 ), map.get( "stsLookback" ).get( 0 ) );

			PropertyGridHelper.typeInTextBox( "Error Id Allocation Size *", map.get( "ErrorAllocationSize" ).get( 0 ) );
			PropertyGridHelper.typeInTextBox( "Linked Blocking Queue Size", map.get( "LinkedQueueSize" ).get( 0 ) );
			PropertyGridHelper.typeInTextBox( "Thread Count *", map.get( "ThreadCount" ).get( 0 ) );
			PropertyGridHelper.typeInDataDir( "Usage File Output Directory *", map.get( "FileOutputDir" ).get( 0 ) );
			PropertyGridHelper.typeInTextBox( "Wait time (in seconds) before skipping concurrent rerate ...", map.get( "WaitTimeBeforeSkipping" ).get( 0 ) );

			ButtonHelper.click( "OKButton" );
			GenericHelper.waitForLoadmask( detailScreenWaitSec );
			assertEquals( NavigationHelper.getScreenTitle(), "Edit Stream", "Stream Stage did not get saved." );
			Log4jHelper.logInfo( "Stream stage '" + stsName + "'is created successfully for voice" );
		}
		else
			Log4jHelper.logInfo( "Stream stage '" + stsName + "' is already present for voice Stream" );
	}

	/*
	 * This method is for rerate Master stream stage
	 */
	public void rerateMasterStreamStage( String path, String workBookName, String sheetName, String testCaseName, int occurence ) throws Exception
	{
		HashMap<String, ArrayList<String>> map = excelReader.readDataByColumn( path, workBookName, sheetName, testCaseName );
		String stsName = map.get( "stsName" ).get( 0 );
		if ( !isStreamStagePresent( stsName ) )
		{
			strImplObj.addStreamStageButton();
			streamStageType( map.get( "streamStageType" ).get( 0 ), map.get( "stsName" ).get( 0 ), map.get( "stsRetryFlag" ).get( 0 ), map.get( "stsRetryAttempts" ).get( 0 ), map.get( "stsInterval" ).get( 0 ), map.get( "stsLookback" ).get( 0 ) );

			PropertyGridHelper.selectInComboBox( "Master Component *", map.get( "MasterComponent" ).get( 0 ) );
			ButtonHelper.click( "OKButton" );
			GenericHelper.waitForLoadmask( detailScreenWaitSec );
			assertEquals( NavigationHelper.getScreenTitle(), "Edit Stream", "Stream Stage did not get saved." );
			Log4jHelper.logInfo( "Stream stage is created successfully for voice" );
		}
		else
			Log4jHelper.logInfo( "Stream stage '" + stsName + "' is already present for voice Stream" );
	}

	/*
	 * This method is for rerate Dataload stream stage
	 */
	public void rerateDataLoadStreamStage( String path, String workBookName, String sheetName, String testCaseName, int occurence ) throws Exception
	{
		HashMap<String, ArrayList<String>> map = excelReader.readDataByColumn( path, workBookName, sheetName, testCaseName );
		String stsName = map.get( "stsName" ).get( 0 );
		if ( !isStreamStagePresent( stsName ) )
		{
			strImplObj.addStreamStageButton();
			streamStageType( map.get( "streamStageType" ).get( 0 ), map.get( "stsName" ).get( 0 ), map.get( "stsRetryFlag" ).get( 0 ), map.get( "stsRetryAttempts" ).get( 0 ), map.get( "stsInterval" ).get( 0 ), map.get( "stsLookback" ).get( 0 ) );

			if ( ValidationHelper.isTrue( map.get( "DeleteInputFile" ).get( 0 ) ) )
				PropertyGridHelper.checkCheckBox( "Delete Input File" );
			if ( ValidationHelper.isFalse( map.get( "EnableRetry" ).get( 0 ) ) )
				PropertyGridHelper.unCheckCheckBox( "Enable Retry On Failure" );
			if ( ValidationHelper.isTrue( map.get( "LogDataLoadOutput" ).get( 0 ) ) )
				PropertyGridHelper.checkCheckBox( "Log Data Load Output" );
			PropertyGridHelper.typeInTextBox( "Look Back Days To Do Conventional Data Load (In Days) *", map.get( "LookBackDaysDataLoad" ).get( 0 ) );
			PropertyGridHelper.typeInTextBox( "Maximum Error Count Per Parse Output File", map.get( "MaxErrorCount" ).get( 0 ) );
			if ( ValidationHelper.isTrue( map.get( "DataLoadStatistics" ).get( 0 ) ) )
				PropertyGridHelper.checkCheckBox( "Populate DataLoad Statistics" );
			PropertyGridHelper.typeInTextBox( "Thread Count *", map.get( "ThreadCount" ).get( 0 ) );
			if ( ValidationHelper.isTrue( map.get( "TrancateLoad" ).get( 0 ) ) )
				PropertyGridHelper.checkCheckBox( "Truncate Before Load(Valid only for user defined Referenc..." );
			if ( ValidationHelper.isTrue( map.get( "TrancateBeforeDataLoad" ).get( 0 ) ) )
				PropertyGridHelper.checkCheckBox( "Truncate Table Before Data Load (Usage tables) *" );
			ButtonHelper.click( "OKButton" );
			GenericHelper.waitForLoadmask( detailScreenWaitSec );
			assertEquals( NavigationHelper.getScreenTitle(), "Edit Stream", "Stream Stage did not get saved." );
			Log4jHelper.logInfo( "Stream stage is created successfully for voice" );
		}
		else
			Log4jHelper.logInfo( "Stream stage '" + stsName + "' is already present for voice Stream" );
	}

	/*
	 * This method is for bandsRecurringRerate stream stage
	 */
	public void bandsRecurringRerateStreamStage( String path, String workBookName, String sheetName, String testCaseName, int occurence ) throws Exception
	{
		HashMap<String, ArrayList<String>> map = excelReader.readDataByColumn( path, workBookName, sheetName, testCaseName );
		String stsName = map.get( "stsName" ).get( 0 );
		if ( !isStreamStagePresent( stsName ) )
		{
			strImplObj.addStreamStageButton();
			streamStageType( map.get( "streamStageType" ).get( 0 ), map.get( "stsName" ).get( 0 ), map.get( "stsRetryFlag" ).get( 0 ), map.get( "stsRetryAttempts" ).get( 0 ), map.get( "stsInterval" ).get( 0 ), map.get( "stsLookback" ).get( 0 ) );

			PropertyGridHelper.selectInComboBox( "Aggregation Configuration *", map.get( "AggregationConfiguration" ).get( 0 ) );
			PropertyGridHelper.selectInComboBox( "Bands Requiring Rerate Component *", map.get( "BandsRequiringRerateComponent" ).get( 0 ) );
			PropertyGridHelper.typeInTextBox( "Traffic Look Back Days *", map.get( "TrafficLookBackDays" ).get( 0 ) );
			ButtonHelper.click( "OKButton" );
			GenericHelper.waitForLoadmask( detailScreenWaitSec );
			assertEquals( NavigationHelper.getScreenTitle(), "Edit Stream", "Stream Stage did not get saved." );
			Log4jHelper.logInfo( "Stream stage is created successfully for voice" );
		}
		else
			Log4jHelper.logInfo( "Stream stage '" + stsName + "' is already present for Stream" );
	}

	/*
	 * This method is for recon  stream stage
	 */
	public void reconStreamStage( String path, String workBookName, String sheetName, String testCaseName, int occurence ) throws Exception
	{
		HashMap<String, ArrayList<String>> map = excelReader.readDataByColumn( path, workBookName, sheetName, testCaseName );
		String stsName = map.get( "stsName" ).get( 0 );
		if ( !isStreamStagePresent( stsName ) )
		{
			strImplObj.addStreamStageButton();
			streamStageType( map.get( "streamStageType" ).get( 0 ), map.get( "stsName" ).get( 0 ), map.get( "stsRetryFlag" ).get( 0 ), map.get( "stsRetryAttempts" ).get( 0 ), map.get( "stsInterval" ).get( 0 ), map.get( "stsLookback" ).get( 0 ) );

			PropertyGridHelper.selectInComboBox( "Extra Population Component", map.get( "ExtraComponent" ).get( 0 ) );
			PropertyGridHelper.typeInTextBox( "Reconciliation Result ID Allocation Size *", map.get( "ReconResultAllocationSize" ).get( 0 ) );

			ButtonHelper.click( "OKButton" );
			GenericHelper.waitForLoadmask( detailScreenWaitSec );
			assertEquals( NavigationHelper.getScreenTitle(), "Edit Stream", "Stream Stage did not get saved." );
			Log4jHelper.logInfo( "Stream stage is created successfully for voice" );
		}
		else
			Log4jHelper.logInfo( "Stream stage '" + stsName + "' is already present for Stream" );
	}

	//Method: Add stream stages of BulkLoader	
	public void bulkLoaderStreamStage( Map<String, String> bulkLoadStrmMap ) throws Exception
	{

		streamStageType( ExcelHolder.getKey( bulkLoadStrmMap, "streamStageType" ), ExcelHolder.getKey( bulkLoadStrmMap, "stsName" ), ExcelHolder.getKey( bulkLoadStrmMap, "stsRetryFlag" ), ExcelHolder.getKey( bulkLoadStrmMap, "stsRetryAttempts" ), ExcelHolder.getKey( bulkLoadStrmMap, "stsInterval" ), ExcelHolder.getKey( bulkLoadStrmMap, "stsLookback" ) );

		GenericHelper.waitForLoadmask();
		PropertyGridHelper.selectInComboBox( "Bulk Loader Configuration Entity", bulkLoadStrmMap.get( "BulkLoaderConfigurationEntity" ) );
		GenericHelper.waitForLoadmask();
		PropertyGridHelper.typeInDataDir( "Error File Output Directory *", bulkLoadStrmMap.get( "Output Directory" ), ";" );
		GenericHelper.waitForLoadmask();
		if ( ElementHelper.isElementPresent( "//*[@id='property']//div[text()='File Reader Type']" ) )
			PropertyGridHelper.selectInComboBox( "File Reader Type", bulkLoadStrmMap.get( "FileReaderType" ) );
		assertEquals( NavigationHelper.getScreenTitle(), "New Stream Stage" );
		ButtonHelper.click( "streamStageDetail.OK" );
		GenericHelper.waitForLoadmask();
		assertEquals( NavigationHelper.getScreenTitle(), "New Stream" );
	}

	//Method : add dispute claim form stream stages
	public void disputeClaimFormStreamStage( Map<String, String> billStrmMap ) throws Exception
	{

		streamStageType( ExcelHolder.getKey( billStrmMap, "streamStageType" ), ExcelHolder.getKey( billStrmMap, "stsName" ), ExcelHolder.getKey( billStrmMap, "stsRetryFlag" ), ExcelHolder.getKey( billStrmMap, "stsRetryAttempts" ), ExcelHolder.getKey( billStrmMap, "stsInterval" ), ExcelHolder.getKey( billStrmMap, "stsLookback" ) );

		GenericHelper.waitForLoadmask();
		PropertyGridHelper.typeInTextBox( "Cai Dispute Report Logo Image File *", billStrmMap.get( "CaiDisputeReportLogoImgFile" ) );
		PropertyGridHelper.typeInTextBox( "Cai Dispute Report RptDesign File Name *", billStrmMap.get( "CaiDisputeReportRptDegnFile" ) );
		GenericHelper.waitForLoadmask();
		PropertyGridHelper.typeInDataDir( "Logo Directory Path *", billStrmMap.get( "LogoDirectPath" ), ";" );
		PropertyGridHelper.typeInDataDir( "Report Files Directory Path *", billStrmMap.get( "ReportFileDirecPath" ), ";" );

		GenericHelper.waitForLoadmask();
		assertEquals( NavigationHelper.getScreenTitle(), "New Stream Stage" );
		ButtonHelper.click( "streamStageDetail.OK" );
		GenericHelper.waitForLoadmask();
		assertEquals( NavigationHelper.getScreenTitle(), "Edit Stream" );
	}

	//add Auto dispute  and Resolve dispute stream stage
	public void disputeStreamStage( Map<String, String> billStrmMap ) throws Exception
	{

		streamStageType( ExcelHolder.getKey( billStrmMap, "streamStageType" ), ExcelHolder.getKey( billStrmMap, "stsName" ), ExcelHolder.getKey( billStrmMap, "stsRetryFlag" ), ExcelHolder.getKey( billStrmMap, "stsRetryAttempts" ), ExcelHolder.getKey( billStrmMap, "stsInterval" ), ExcelHolder.getKey( billStrmMap, "stsLookback" ) );

		GenericHelper.waitForLoadmask();
		assertEquals( NavigationHelper.getScreenTitle(), "New Stream Stage" );
		ButtonHelper.click( "streamStageDetail.OK" );
		GenericHelper.waitForLoadmask();
		assertEquals( NavigationHelper.getScreenTitle(), "Edit Stream" );
	}

	// Method: Add stream stages of Xdr Template
	public void xdrTemplStreamStage( Map<String, String> xdrTempStrmMap ) throws Exception
	{

		streamStageType( ExcelHolder.getKey( xdrTempStrmMap, "streamStageType" ), ExcelHolder.getKey( xdrTempStrmMap, "stsName" ), ExcelHolder.getKey( xdrTempStrmMap, "stsRetryFlag" ), ExcelHolder.getKey( xdrTempStrmMap, "stsRetryAttempts" ), ExcelHolder.getKey( xdrTempStrmMap, "stsInterval" ), ExcelHolder.getKey( xdrTempStrmMap, "stsLookback" ) );

		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		PropertyGridHelper.selectInComboBox( "XDR Aggregation Processor *", xdrTempStrmMap.get( "AggregationProcessor" ) );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		PropertyGridHelper.typeInTextBox( "Wait time (in seconds) before skipping concurrent aggrega...", xdrTempStrmMap.get( "WaitTimeSkip" ) );
		assertEquals( NavigationHelper.getScreenTitle(), "New Stream Stage" );
		ButtonHelper.click( "streamStageDetail.OK" );
		GenericHelper.waitForLoadmask();
		assertEquals( NavigationHelper.getScreenTitle(), "New Stream" );
	}

	// Method: Add stream stages of 'XDR Aggregation Master Request'
	public void xdrAggMasterReqStreamStg( Map<String, String> xdrTempStrmMap ) throws Exception
	{

		String stsName = ExcelHolder.getKey( xdrTempStrmMap, "stsName" );
		if ( !isStreamStagePresent( stsName ) )
		{
			strImplObj.addStreamStageButton();
			streamStageType( ExcelHolder.getKey( xdrTempStrmMap, "streamStageType" ), ExcelHolder.getKey( xdrTempStrmMap, "stsName" ), ExcelHolder.getKey( xdrTempStrmMap, "stsRetryFlag" ), ExcelHolder.getKey( xdrTempStrmMap, "stsRetryAttempts" ), ExcelHolder.getKey( xdrTempStrmMap, "stsInterval" ), ExcelHolder.getKey( xdrTempStrmMap, "stsLookback" ) );

			GenericHelper.waitForLoadmask( searchScreenWaitSec );
			PropertyGridHelper.selectInComboBox( "Master Component *", xdrTempStrmMap.get( "MasterComponent" ) );
			PropertyGridHelper.selectInComboBox( "XDR Aggregation Processor *", xdrTempStrmMap.get( "XDRAggregationProcessor" ) );
			assertEquals( NavigationHelper.getScreenTitle(), "New Stream Stage" );
			ButtonHelper.click( "streamStageDetail.OK" );
			GenericHelper.waitForLoadmask();
			assertEquals( NavigationHelper.getScreenTitle(), "Edit Stream" );
		}

		else
			Log4jHelper.logInfo( "Stream stage '" + stsName + "' is already present for Stream" );
	}

	// Method: Add stream stages of settlement
	public void settlementStreamStage( Map<String, String> settlementStrmMap ) throws Exception
	{

		String stsName = ExcelHolder.getKey( settlementStrmMap, "stsName" );
		if ( !isStreamStagePresent( stsName ) )
		{
			strImplObj.addStreamStageButton();
			streamStageType( ExcelHolder.getKey( settlementStrmMap, "streamStageType" ), ExcelHolder.getKey( settlementStrmMap, "stsName" ), ExcelHolder.getKey( settlementStrmMap, "stsRetryFlag" ), ExcelHolder.getKey( settlementStrmMap, "stsRetryAttempts" ), ExcelHolder.getKey( settlementStrmMap, "stsInterval" ), ExcelHolder.getKey( settlementStrmMap, "stsLookback" ) );

			GenericHelper.waitForLoadmask( searchScreenWaitSec );
			PropertyGridHelper.typeInDataDir( "Settlements Output Directory *", settlementStrmMap.get( "SettlementOutputDir" ), secondLevelDelimiter );
			GenericHelper.waitForLoadmask( searchScreenWaitSec );
			PropertyGridHelper.typeInDataDir( "Test Settlements Output Directory *", settlementStrmMap.get( "TestSettlementOutputDir" ), secondLevelDelimiter );
			assertEquals( NavigationHelper.getScreenTitle(), "New Stream Stage" );
			ButtonHelper.click( "streamStageDetail.OK" );
			GenericHelper.waitForLoadmask();
			assertEquals( NavigationHelper.getScreenTitle(), "Edit Stream" );
		}

		else
			Log4jHelper.logInfo( "Stream stage '" + stsName + "' is already present for Stream" );
	}

	/*
	 * This method is for deal Validate stream stage
	 */
	public void dealValidateStreamStage( String path, String workBookName, String sheetName, String testCaseName, int occurence ) throws Exception
	{
		HashMap<String, ArrayList<String>> map = excelReader.readDataByColumn( path, workBookName, sheetName, testCaseName );
		String stsName = map.get( "stsName" ).get( 0 );
		if ( !isStreamStagePresent( stsName ) )
		{
			strImplObj.addStreamStageButton();
			streamStageType( map.get( "streamStageType" ).get( 0 ), map.get( "stsName" ).get( 0 ), map.get( "stsRetryFlag" ).get( 0 ), map.get( "stsRetryAttempts" ).get( 0 ), map.get( "stsInterval" ).get( 0 ), map.get( "stsLookback" ).get( 0 ) );

			ButtonHelper.click( "OKButton" );
			GenericHelper.waitForLoadmask( detailScreenWaitSec );
			assertEquals( NavigationHelper.getScreenTitle(), "Edit Stream", "Stream Stage did not get saved." );
			Log4jHelper.logInfo( "Stream stage '" + stsName + "'is created successfully for voice" );
		}
		else
			Log4jHelper.logInfo( "Stream stage '" + stsName + "' is already present for voice Stream" );
	}

	/*
	 * This method is for deal Authorization stream stage
	 */
	public void dealAuthorizationStreamStage( String path, String workBookName, String sheetName, String testCaseName, int occurence ) throws Exception
	{
		HashMap<String, ArrayList<String>> map = excelReader.readDataByColumn( path, workBookName, sheetName, testCaseName );
		String stsName = map.get( "stsName" ).get( 0 );
		if ( !isStreamStagePresent( stsName ) )
		{
			strImplObj.addStreamStageButton();
			streamStageType( map.get( "streamStageType" ).get( 0 ), map.get( "stsName" ).get( 0 ), map.get( "stsRetryFlag" ).get( 0 ), map.get( "stsRetryAttempts" ).get( 0 ), map.get( "stsInterval" ).get( 0 ), map.get( "stsLookback" ).get( 0 ) );

			if ( ValidationHelper.isFalse( map.get( "RateWriteBack" ).get( 0 ) ) )
				PropertyGridHelper.unCheckCheckBox( "Rate Write Back *" );
			PropertyGridHelper.selectInComboBox( "Tariff Rate Write Back *", map.get( "TariffRateWriteBack" ).get( 0 ) );
			ButtonHelper.click( "OKButton" );
			GenericHelper.waitForLoadmask( detailScreenWaitSec );
			assertEquals( NavigationHelper.getScreenTitle(), "Edit Stream", "Stream Stage did not get saved." );
			Log4jHelper.logInfo( "Stream stage '" + stsName + "'is created successfully for voice" );
		}
		else
			Log4jHelper.logInfo( "Stream stage '" + stsName + "' is already present for voice Stream" );
	}

	/*
	 * This method is for deal checkin
	 */
	public void dealCheckIn( String path, String workBookName, String sheetName, String testCaseName, int occurence ) throws Exception
	{
		HashMap<String, ArrayList<String>> map = excelReader.readDataByColumn( path, workBookName, sheetName, testCaseName );
		String stsName = map.get( "stsName" ).get( 0 );
		if ( !isStreamStagePresent( stsName ) )
		{
			strImplObj.addStreamStageButton();
			streamStageType( map.get( "streamStageType" ).get( 0 ), map.get( "stsName" ).get( 0 ), map.get( "stsRetryFlag" ).get( 0 ), map.get( "stsRetryAttempts" ).get( 0 ), map.get( "stsInterval" ).get( 0 ), map.get( "stsLookback" ).get( 0 ) );

			PropertyGridHelper.selectInComboBox( "Deal Check In Component *", map.get( "DealCheckInComponent" ).get( 0 ) );
			ButtonHelper.click( "OKButton" );
			GenericHelper.waitForLoadmask( detailScreenWaitSec );
			assertEquals( NavigationHelper.getScreenTitle(), "Edit Stream", "Stream Stage did not get saved." );
			Log4jHelper.logInfo( "Stream stage '" + stsName + "'is created successfully for voice" );
		}
		else
			Log4jHelper.logInfo( "Stream stage '" + stsName + "' is already present for voice Stream" );
	}

	/*
	 * This method is for deal checkout
	 */
	public void dealCheckOut( String path, String workBookName, String sheetName, String testCaseName, int occurence ) throws Exception
	{
		HashMap<String, ArrayList<String>> map = excelReader.readDataByColumn( path, workBookName, sheetName, testCaseName );
		String stsName = map.get( "stsName" ).get( 0 );
		if ( !isStreamStagePresent( stsName ) )
		{
			strImplObj.addStreamStageButton();
			streamStageType( map.get( "streamStageType" ).get( 0 ), map.get( "stsName" ).get( 0 ), map.get( "stsRetryFlag" ).get( 0 ), map.get( "stsRetryAttempts" ).get( 0 ), map.get( "stsInterval" ).get( 0 ), map.get( "stsLookback" ).get( 0 ) );

			ButtonHelper.click( "OKButton" );
			GenericHelper.waitForLoadmask( detailScreenWaitSec );
			assertEquals( NavigationHelper.getScreenTitle(), "Edit Stream", "Stream Stage did not get saved." );
			Log4jHelper.logInfo( "Stream stage '" + stsName + "'is created successfully for voice" );
		}
		else
			Log4jHelper.logInfo( "Stream stage '" + stsName + "' is already present for voice Stream" );
	}

	/*
	 * This method is for deal AutoRenew
	 */
	public void dealAutoRenew( String path, String workBookName, String sheetName, String testCaseName, int occurence ) throws Exception
	{
		HashMap<String, ArrayList<String>> map = excelReader.readDataByColumn( path, workBookName, sheetName, testCaseName );
		String stsName = map.get( "stsName" ).get( 0 );
		if ( !isStreamStagePresent( stsName ) )
		{
			strImplObj.addStreamStageButton();
			streamStageType( map.get( "streamStageType" ).get( 0 ), map.get( "stsName" ).get( 0 ), map.get( "stsRetryFlag" ).get( 0 ), map.get( "stsRetryAttempts" ).get( 0 ), map.get( "stsInterval" ).get( 0 ), map.get( "stsLookback" ).get( 0 ) );

			PropertyGridHelper.typeInTextBox( "Deal Renew Wait Days *", map.get( "DealRenewWaitDays" ).get( 0 ) );

			ButtonHelper.click( "OKButton" );
			GenericHelper.waitForLoadmask( detailScreenWaitSec );
			assertEquals( NavigationHelper.getScreenTitle(), "Edit Stream", "Stream Stage did not get saved." );
			Log4jHelper.logInfo( "Stream stage '" + stsName + "'is created successfully for voice" );
		}
		else
			Log4jHelper.logInfo( "Stream stage '" + stsName + "' is already present for voice Stream" );
	}

	/*
	 * This method is for bilateral master
	 */
	public void bilteralMasterStreamStage( String path, String workBookName, String sheetName, String testCaseName, int occurence ) throws Exception
	{
		HashMap<String, ArrayList<String>> map = excelReader.readDataByColumn( path, workBookName, sheetName, testCaseName );
		String stsName = map.get( "stsName" ).get( 0 );
		if ( !isStreamStagePresent( stsName ) )
		{
			strImplObj.addStreamStageButton();
			streamStageType( map.get( "streamStageType" ).get( 0 ), map.get( "stsName" ).get( 0 ), map.get( "stsRetryFlag" ).get( 0 ), map.get( "stsRetryAttempts" ).get( 0 ), map.get( "stsInterval" ).get( 0 ), map.get( "stsLookback" ).get( 0 ) );

			PropertyGridHelper.typeInTextBox( "Account Batch Size *", map.get( "AccountBatchSize" ).get( 0 ) );
			PropertyGridHelper.selectInComboBox( "Master Component *", map.get( "MasterComponent" ).get( 0 ) );
			ButtonHelper.click( "OKButton" );
			GenericHelper.waitForLoadmask( detailScreenWaitSec );
			assertEquals( NavigationHelper.getScreenTitle(), "Edit Stream", "Stream Stage did not get saved." );
			Log4jHelper.logInfo( "Stream stage '" + stsName + "'is created successfully for voice" );
		}
		else
			Log4jHelper.logInfo( "Stream stage '" + stsName + "' is already present for voice Stream" );
	}

	/*
	 * This method is for bilateral 
	 */
	public void bilteralStreamStage( String path, String workBookName, String sheetName, String testCaseName, int occurence ) throws Exception
	{
		HashMap<String, ArrayList<String>> map = excelReader.readDataByColumn( path, workBookName, sheetName, testCaseName );
		String stsName = map.get( "stsName" ).get( 0 );
		if ( !isStreamStagePresent( stsName ) )
		{
			strImplObj.addStreamStageButton();
			streamStageType( map.get( "streamStageType" ).get( 0 ), map.get( "stsName" ).get( 0 ), map.get( "stsRetryFlag" ).get( 0 ), map.get( "stsRetryAttempts" ).get( 0 ), map.get( "stsInterval" ).get( 0 ), map.get( "stsLookback" ).get( 0 ) );

			PropertyGridHelper.selectInComboBox( "Bilateral Modelling *", map.get( "BilateralModelling" ).get( 0 ) );
			PropertyGridHelper.typeInDataDir( "Bilateral Output Directory *", map.get( "BilateralOutputDir" ).get( 0 ) );
			PropertyGridHelper.typeInTextBox( "Thread Count *", map.get( "ThreadCount" ).get( 0 ) );

			ButtonHelper.click( "OKButton" );
			GenericHelper.waitForLoadmask( detailScreenWaitSec );
			assertEquals( NavigationHelper.getScreenTitle(), "Edit Stream", "Stream Stage did not get saved." );
			Log4jHelper.logInfo( "Stream stage '" + stsName + "'is created successfully for voice" );
		}
		else
			Log4jHelper.logInfo( "Stream stage '" + stsName + "' is already present for voice Stream" );
	}

	/*
	 * This method is for shortfall and caps 
	 */
	public void shortFallandCapsStreamStage( String path, String workBookName, String sheetName, String testCaseName, int occurence ) throws Exception
	{
		HashMap<String, ArrayList<String>> map = excelReader.readDataByColumn( path, workBookName, sheetName, testCaseName );
		String stsName = map.get( "stsName" ).get( 0 );
		if ( !isStreamStagePresent( stsName ) )
		{
			strImplObj.addStreamStageButton();
			streamStageType( map.get( "streamStageType" ).get( 0 ), map.get( "stsName" ).get( 0 ), map.get( "stsRetryFlag" ).get( 0 ), map.get( "stsRetryAttempts" ).get( 0 ), map.get( "stsInterval" ).get( 0 ), map.get( "stsLookback" ).get( 0 ) );

			PropertyGridHelper.selectInComboBox( "Bilateral Modelling *", map.get( "BilateralModelling" ).get( 0 ) );
			PropertyGridHelper.typeInDataDir( "Bilateral Output Directory *", map.get( "BilateralOutputDir" ).get( 0 ) );
			PropertyGridHelper.typeInTextBox( "Thread Count *", map.get( "ThreadCount" ).get( 0 ) );

			ButtonHelper.click( "OKButton" );
			GenericHelper.waitForLoadmask( detailScreenWaitSec );
			assertEquals( NavigationHelper.getScreenTitle(), "Edit Stream", "Stream Stage did not get saved." );
			Log4jHelper.logInfo( "Stream stage '" + stsName + "'is created successfully for voice" );
		}
		else
			Log4jHelper.logInfo( "Stream stage '" + stsName + "' is already present for voice Stream" );
	}

	/*
	 * This method is for merger streamstage 
	 */
	public void mergerStreamStage( String path, String workBookName, String sheetName, String testCaseName, int occurence ) throws Exception
	{
		HashMap<String, ArrayList<String>> map = excelReader.readDataByColumn( path, workBookName, sheetName, testCaseName );
		String stsName = map.get( "stsName" ).get( 0 );
		if ( !isStreamStagePresent( stsName ) )
		{
			strImplObj.addStreamStageButton();
			streamStageType( map.get( "streamStageType" ).get( 0 ), map.get( "stsName" ).get( 0 ), map.get( "stsRetryFlag" ).get( 0 ), map.get( "stsRetryAttempts" ).get( 0 ), map.get( "stsInterval" ).get( 0 ), map.get( "stsLookback" ).get( 0 ) );

			PropertyGridHelper.selectInComboBox( "Bilaterals Rated Detail Modelling *", map.get( "BilateralModelling" ).get( 0 ) );
			PropertyGridHelper.typeInTextBox( "No.Of Merger Threads *", map.get( "NoOfMergerThreads" ).get( 0 ) );

			ButtonHelper.click( "OKButton" );
			GenericHelper.waitForLoadmask( detailScreenWaitSec );
			assertEquals( NavigationHelper.getScreenTitle(), "Edit Stream", "Stream Stage did not get saved." );
			Log4jHelper.logInfo( "Stream stage '" + stsName + "'is created successfully for voice" );
		}
		else
			Log4jHelper.logInfo( "Stream stage '" + stsName + "' is already present for voice Stream" );
	}

	/*
	 * This method is for rocps dataload task streamstage 
	 */
	public void rocpsDataLoadTaskStreamStage( String path, String workBookName, String sheetName, String testCaseName, int occurence ) throws Exception
	{
		HashMap<String, ArrayList<String>> map = excelReader.readDataByColumn( path, workBookName, sheetName, testCaseName );
		String stsName = map.get( "stsName" ).get( 0 );
		if ( !isStreamStagePresent( stsName ) )
		{
			strImplObj.addStreamStageButton();
			streamStageType( map.get( "streamStageType" ).get( 0 ), map.get( "stsName" ).get( 0 ), map.get( "stsRetryFlag" ).get( 0 ), map.get( "stsRetryAttempts" ).get( 0 ), map.get( "stsInterval" ).get( 0 ), map.get( "stsLookback" ).get( 0 ) );

			if ( ValidationHelper.isTrue( map.get( "DeleteInputFile" ).get( 0 ) ) )
				PropertyGridHelper.clickCheckBox( "Delete Input File", map.get( "DeleteInputFile" ).get( 0 ) );
			if ( ValidationHelper.isFalse( map.get( "EnableRetry" ).get( 0 ) ) )
				PropertyGridHelper.clickCheckBox( "Enable Retry On Failure", map.get( "EnableRetry" ).get( 0 ) );
			if ( ValidationHelper.isTrue( map.get( "LogDataLoadOutput" ).get( 0 ) ) )
				PropertyGridHelper.clickCheckBox( "Log Data Load Output", map.get( "LogDataLoadOutput" ).get( 0 ) );

			PropertyGridHelper.typeInTextBox( "Look Back Days To Do Conventional Data Load (In Days) *", map.get( "LookBackDays" ).get( 0 ) );
			PropertyGridHelper.typeInTextBox( "Maximum Error Count Per Parse Output File", map.get( "MaximumErrorCount" ).get( 0 ) );
			if ( ValidationHelper.isTrue( map.get( "PopulateDataLoadStatistics" ).get( 0 ) ) )
				PropertyGridHelper.clickCheckBox( "Populate DataLoad Statistics", map.get( "PopulateDataLoadStatistics" ).get( 0 ) );
			PropertyGridHelper.typeInTextBox( "Thread Count *", map.get( "ThreadCount" ).get( 0 ) );
			if ( ValidationHelper.isTrue( map.get( "TruncateBeforeLoad" ).get( 0 ) ) )
				PropertyGridHelper.clickCheckBox( "Truncate Before Load(Valid only for user defined Referenc...", map.get( "TruncateBeforeLoad" ).get( 0 ) );
			if ( ValidationHelper.isTrue( map.get( "TruncateTableBeforeDataLoad" ).get( 0 ) ) )
				PropertyGridHelper.clickCheckBox( "Truncate Table Before Data Load (Usage tables) *", map.get( "TruncateTableBeforeDataLoad" ).get( 0 ) );

			PropertyGridHelper.selectInComboBox( "Bilaterals Rated Detail Modelling *", map.get( "BilateralModelling" ).get( 0 ) );
			ButtonHelper.click( "OKButton" );
			GenericHelper.waitForLoadmask( detailScreenWaitSec );
			assertEquals( NavigationHelper.getScreenTitle(), "Edit Stream", "Stream Stage did not get saved." );
			Log4jHelper.logInfo( "Stream stage '" + stsName + "'is created successfully for voice" );
		}
		else
			Log4jHelper.logInfo( "Stream stage '" + stsName + "' is already present for voice Stream" );
	}

	/*
	 * This method is for deal Import stream stage
	 */
	public void dealImportStreamStage( String path, String workBookName, String sheetName, String testCaseName, int occurence ) throws Exception
	{
		HashMap<String, ArrayList<String>> map = excelReader.readDataByColumn( path, workBookName, sheetName, testCaseName );
		String stsName = map.get( "stsName" ).get( 0 );
		if ( !isStreamStagePresent( stsName ) )
		{
			strImplObj.addStreamStageButton();
			streamStageType( map.get( "streamStageType" ).get( 0 ), map.get( "stsName" ).get( 0 ), map.get( "stsRetryFlag" ).get( 0 ), map.get( "stsRetryAttempts" ).get( 0 ), map.get( "stsInterval" ).get( 0 ), map.get( "stsLookback" ).get( 0 ) );

			PropertyGridHelper.typeInDataDir( "Deal Import Directory *", map.get( "DealImportDir" ).get( 0 ) );
			ButtonHelper.click( "OKButton" );
			GenericHelper.waitForLoadmask( detailScreenWaitSec );
			assertEquals( NavigationHelper.getScreenTitle(), "Edit Stream", "Stream Stage did not get saved." );
			Log4jHelper.logInfo( "Stream stage '" + stsName + "'is created successfully for voice" );
		}
		else
			Log4jHelper.logInfo( "Stream stage '" + stsName + "' is already present for voice Stream" );
	}

	/*
	 * This method is for Accrual Overview
	 */
	public void accrualsOverview( String path, String workBookName, String sheetName, String testCaseName, int occurence ) throws Exception
	{
		HashMap<String, ArrayList<String>> map = excelReader.readDataByColumn( path, workBookName, sheetName, testCaseName );
		String stsName = map.get( "stsName" ).get( 0 );
		if ( !isStreamStagePresent( stsName ) )
		{
			strImplObj.addStreamStageButton();
			streamStageType( map.get( "streamStageType" ).get( 0 ), map.get( "stsName" ).get( 0 ), map.get( "stsRetryFlag" ).get( 0 ), map.get( "stsRetryAttempts" ).get( 0 ), map.get( "stsInterval" ).get( 0 ), map.get( "stsLookback" ).get( 0 ) );

			PropertyGridHelper.selectInComboBox( "Accrual Overview Factory Component *", map.get( "AccrualOverviewFactoryComp" ).get( 0 ) );
			PropertyGridHelper.selectInComboBox( "Accrual Overview Modelling *", map.get( "AccrualOverviewModelling" ).get( 0 ) );
			PropertyGridHelper.typeInDataDir( "Accrual Overview Output Directory *", map.get( "AccrualOutputDir" ).get( 0 ) );
			PropertyGridHelper.typeInTextBox( "No. of Parallel Account Processor Count *", map.get( "NoOfParallelCount" ).get( 0 ) );
			PropertyGridHelper.typeInTextBox( "Thread Count Per Account *", map.get( "ThreadCountPerAccount" ).get( 0 ) );
			ButtonHelper.click( "OKButton" );
			GenericHelper.waitForLoadmask( detailScreenWaitSec );
			assertEquals( NavigationHelper.getScreenTitle(), "Edit Stream", "Stream Stage did not get saved." );
			Log4jHelper.logInfo( "Stream stage '" + stsName + "'is created successfully for voice" );
		}
		else
			Log4jHelper.logInfo( "Stream stage '" + stsName + "' is already present for voice Stream" );
	}

	/*
	 * This method is for Accrual Overview Dataload
	 */
	public void accrualsOverviewDataLoad( String path, String workBookName, String sheetName, String testCaseName, int occurence ) throws Exception
	{
		HashMap<String, ArrayList<String>> map = excelReader.readDataByColumn( path, workBookName, sheetName, testCaseName );
		String stsName = map.get( "stsName" ).get( 0 );
		if ( !isStreamStagePresent( stsName ) )
		{
			strImplObj.addStreamStageButton();
			streamStageType( map.get( "streamStageType" ).get( 0 ), map.get( "stsName" ).get( 0 ), map.get( "stsRetryFlag" ).get( 0 ), map.get( "stsRetryAttempts" ).get( 0 ), map.get( "stsInterval" ).get( 0 ), map.get( "stsLookback" ).get( 0 ) );

			if ( ValidationHelper.isTrue( map.get( "DeleteInputFile" ).get( 0 ) ) )
				PropertyGridHelper.clickCheckBox( "Delete Input File", map.get( "DeleteInputFile" ).get( 0 ) );
			if ( ValidationHelper.isFalse( map.get( "EnableRetry" ).get( 0 ) ) )
				PropertyGridHelper.clickCheckBox( "Enable Retry On Failure", map.get( "EnableRetry" ).get( 0 ) );
			if ( ValidationHelper.isTrue( map.get( "LogDataLoadOutput" ).get( 0 ) ) )
				PropertyGridHelper.clickCheckBox( "Log Data Load Output", map.get( "LogDataLoadOutput" ).get( 0 ) );

			PropertyGridHelper.typeInTextBox( "Look Back Days To Do Conventional Data Load (In Days) *", map.get( "LookBackDays" ).get( 0 ) );
			PropertyGridHelper.typeInTextBox( "Maximum Error Count Per Parse Output File", map.get( "MaximumErrorCount" ).get( 0 ) );
			if ( ValidationHelper.isTrue( map.get( "PopulateDataLoadStatistics" ).get( 0 ) ) )
				PropertyGridHelper.clickCheckBox( "Populate DataLoad Statistics", map.get( "PopulateDataLoadStatistics" ).get( 0 ) );
			PropertyGridHelper.typeInTextBox( "Thread Count *", map.get( "ThreadCount" ).get( 0 ) );
			if ( ValidationHelper.isTrue( map.get( "TruncateBeforeLoad" ).get( 0 ) ) )
				PropertyGridHelper.clickCheckBox( "Truncate Before Load(Valid only for user defined Referenc...", map.get( "TruncateBeforeLoad" ).get( 0 ) );
			if ( ValidationHelper.isTrue( map.get( "TruncateTableBeforeDataLoad" ).get( 0 ) ) )
				PropertyGridHelper.clickCheckBox( "Truncate Table Before Data Load (Usage tables) *", map.get( "TruncateTableBeforeDataLoad" ).get( 0 ) );

			ButtonHelper.click( "OKButton" );
			GenericHelper.waitForLoadmask( detailScreenWaitSec );
			assertEquals( NavigationHelper.getScreenTitle(), "Edit Stream", "Stream Stage did not get saved." );
			Log4jHelper.logInfo( "Stream stage '" + stsName + "'is created successfully for voice" );
		}
		else
			Log4jHelper.logInfo( "Stream stage '" + stsName + "' is already present for voice Stream" );
	}

	/*
	 * This method is for Accrual shortfall and caps 
	 */
	public void accrualShortFallandCapsStreamStage( String path, String workBookName, String sheetName, String testCaseName, int occurence ) throws Exception
	{
		HashMap<String, ArrayList<String>> map = excelReader.readDataByColumn( path, workBookName, sheetName, testCaseName );
		String stsName = map.get( "stsName" ).get( 0 );
		if ( !isStreamStagePresent( stsName ) )
		{
			strImplObj.addStreamStageButton();
			streamStageType( map.get( "streamStageType" ).get( 0 ), map.get( "stsName" ).get( 0 ), map.get( "stsRetryFlag" ).get( 0 ), map.get( "stsRetryAttempts" ).get( 0 ), map.get( "stsInterval" ).get( 0 ), map.get( "stsLookback" ).get( 0 ) );

			PropertyGridHelper.selectInComboBox( "Accounting Period Component *", map.get( "AccountingPeriodComp" ).get( 0 ) );
			PropertyGridHelper.selectInComboBox( "Accounting Period Definition *", map.get( "AccountingPeriodDefn" ).get( 0 ) );
			PropertyGridHelper.selectInComboBox( "Accrual Modelling *", map.get( "AccrualModelling" ).get( 0 ) );
			PropertyGridHelper.typeInDataDir( "Accrual Output Directory *", map.get( "AccrualOutputDir" ).get( 0 ) );
			PropertyGridHelper.selectInComboBox( "Accruals Non Usage Component", map.get( "AccrualNonusageComp" ).get( 0 ) );
			PropertyGridHelper.selectInComboBox( "GL Code Definition *", map.get( "GLCodeDefn" ).get( 0 ) );
			PropertyGridHelper.typeInTextBox( "Look Back Days", map.get( "LookBackDays" ).get( 0 ) );
			PropertyGridHelper.typeInTextBox( "Thread Count *", map.get( "ThreadCount" ).get( 0 ) );

			ButtonHelper.click( "OKButton" );
			GenericHelper.waitForLoadmask( detailScreenWaitSec );
			assertEquals( NavigationHelper.getScreenTitle(), "Edit Stream", "Stream Stage did not get saved." );
			Log4jHelper.logInfo( "Stream stage '" + stsName + "'is created successfully for voice" );
		}
		else
			Log4jHelper.logInfo( "Stream stage '" + stsName + "' is already present for voice Stream" );
	}

	/*
	 * This method is for accrualMAster stream stage
	 */

	public void accrualMasterStreamStage( String path, String workBookName, String sheetName, String testCaseName, int occarance ) throws Exception
	{
		HashMap<String, ArrayList<String>> map = excelReader.readDataByColumn( path, workBookName, sheetName, testCaseName );
		String stsName = map.get( "stsName" ).get( 0 );
		if ( !isStreamStagePresent( stsName ) )
		{
			strImplObj.addStreamStageButton();
			streamStageType( map.get( "streamStageType" ).get( 0 ), map.get( "stsName" ).get( 0 ), map.get( "stsRetryFlag" ).get( 0 ), map.get( "stsRetryAttempts" ).get( 0 ), map.get( "stsInterval" ).get( 0 ), map.get( "stsLookback" ).get( 0 ) );

			PropertyGridHelper.typeInTextBox( "Account Batch Size *", map.get( "AccountBatchSize" ).get( 0 ) );
			PropertyGridHelper.selectInComboBox( "Master Component *", map.get( "MasterComponent" ).get( 0 ) );

			ButtonHelper.click( "OKButton" );
			GenericHelper.waitForLoadmask( detailScreenWaitSec );
			assertTrue( LabelHelper.isTitleNotPresent( "New Stream Stage" ), "Stream Stage did not get saved." );
			Log4jHelper.logInfo( "Stream stage is created successfully for Aggregation" );
		}
		else
			Log4jHelper.logInfo( "Stream stage '" + stsName + "' is already present for voice Stream" );
	}

	/*
	 * This method is for accruals Merger stream stage
	 */

	public void accrualMergerStreamStage( String path, String workBookName, String sheetName, String testCaseName, int occarance ) throws Exception
	{
		HashMap<String, ArrayList<String>> map = excelReader.readDataByColumn( path, workBookName, sheetName, testCaseName );
		String stsName = map.get( "stsName" ).get( 0 );
		if ( !isStreamStagePresent( stsName ) )
		{
			strImplObj.addStreamStageButton();
			streamStageType( map.get( "streamStageType" ).get( 0 ), map.get( "stsName" ).get( 0 ), map.get( "stsRetryFlag" ).get( 0 ), map.get( "stsRetryAttempts" ).get( 0 ), map.get( "stsInterval" ).get( 0 ), map.get( "stsLookback" ).get( 0 ) );

			PropertyGridHelper.selectInComboBox( "ARD Modelling *", map.get( "ARDModelling" ).get( 0 ) );
			PropertyGridHelper.selectInComboBox( "Accounting Period Definition *", map.get( "AccountingPeriodDefn" ).get( 0 ) );
			PropertyGridHelper.selectInComboBox( "Accrual Merger Component *", map.get( "AccrualMergerComponent" ).get( 0 ) );
			PropertyGridHelper.typeInDataDir( "Accruals Merger Output Directory *", map.get( "AccrualMergerOutputDir" ).get( 0 ) );
			PropertyGridHelper.typeInTextBox( "Thread Count *", map.get( "ThreadCount" ).get( 0 ) );

			ButtonHelper.click( "OKButton" );
			GenericHelper.waitForLoadmask( detailScreenWaitSec );
			assertTrue( LabelHelper.isTitleNotPresent( "New Stream Stage" ), "Stream Stage did not get saved." );
			Log4jHelper.logInfo( "Stream stage is created successfully for Aggregation" );
		}
		else
			Log4jHelper.logInfo( "Stream stage '" + stsName + "' is already present for voice Stream" );
	}

	/*
	 * This method is for accrualMAster stream stage
	 */

	public void estimationTaskStreamStage( String path, String workBookName, String sheetName, String testCaseName, int occarance ) throws Exception
	{
		HashMap<String, ArrayList<String>> map = excelReader.readDataByColumn( path, workBookName, sheetName, testCaseName );
		String stsName = map.get( "stsName" ).get( 0 );
		if ( !isStreamStagePresent( stsName ) )
		{
			strImplObj.addStreamStageButton();
			streamStageType( map.get( "streamStageType" ).get( 0 ), map.get( "stsName" ).get( 0 ), map.get( "stsRetryFlag" ).get( 0 ), map.get( "stsRetryAttempts" ).get( 0 ), map.get( "stsInterval" ).get( 0 ), map.get( "stsLookback" ).get( 0 ) );

			PropertyGridHelper.typeInDataDir( "Estimation Files Output Directory *", map.get( "EstimationOutputDir" ).get( 0 ) );
			PropertyGridHelper.selectInComboBox( "Estimation Processor *", map.get( "EstimationProcessor" ).get( 0 ) );

			ButtonHelper.click( "OKButton" );
			GenericHelper.waitForLoadmask( detailScreenWaitSec );
			assertTrue( LabelHelper.isTitleNotPresent( "New Stream Stage" ), "Stream Stage did not get saved." );
			Log4jHelper.logInfo( "Stream stage is created successfully for Aggregation" );
		}
		else
			Log4jHelper.logInfo( "Stream stage '" + stsName + "' is already present for voice Stream" );

	}

	// Method: Add stream stages of 'Generic Birt Report Stream'
	public void genBirtRepStreamStage( Map<String, String> genBirtRepStreamMap ) throws Exception
	{

		String stsName = ExcelHolder.getKey( genBirtRepStreamMap, "stsName" );
		if ( !isStreamStagePresent( stsName ) )
		{
			strImplObj.addStreamStageButton();
			streamStageType( ExcelHolder.getKey( genBirtRepStreamMap, "streamStageType" ), ExcelHolder.getKey( genBirtRepStreamMap, "stsName" ), ExcelHolder.getKey( genBirtRepStreamMap, "stsRetryFlag" ), ExcelHolder.getKey( genBirtRepStreamMap, "stsRetryAttempts" ), ExcelHolder.getKey( genBirtRepStreamMap, "stsInterval" ), ExcelHolder.getKey( genBirtRepStreamMap, "stsLookback" ) );
			GenericHelper.waitForLoadmask( searchScreenWaitSec );
			PropertyGridHelper.typeInTextBox( "Machine IP", configProp.getProperty( "machineName" ) );
			GenericHelper.waitForLoadmask( searchScreenWaitSec );
			PropertyGridHelper.typeInDataDir( "Target Directory *", genBirtRepStreamMap.get( "TargetDir" ), secondLevelDelimiter );
			GenericHelper.waitForLoadmask( searchScreenWaitSec );
			PropertyGridHelper.typeInDataDir( "Temporary Directory *", genBirtRepStreamMap.get( "TemporaryDir" ), secondLevelDelimiter );
			assertEquals( NavigationHelper.getScreenTitle(), "New Stream Stage" );
			ButtonHelper.click( "streamStageDetail.OK" );
			GenericHelper.waitForLoadmask();
			assertEquals( NavigationHelper.getScreenTitle(), "Edit Stream" );
		}

		else
			Log4jHelper.logInfo( "Stream stage '" + stsName + "' is already present for Stream" );
	}

	// Method: Add stream stages of 'Audit'
	public void auditStreamStage( Map<String, String> auditStreamMap ) throws Exception
	{

		String stsName = ExcelHolder.getKey( auditStreamMap, "stsName" );
		if ( !isStreamStagePresent( stsName ) )
		{
			strImplObj.addStreamStageButton();
			streamStageType( ExcelHolder.getKey( auditStreamMap, "streamStageType" ), ExcelHolder.getKey( auditStreamMap, "stsName" ), ExcelHolder.getKey( auditStreamMap, "stsRetryFlag" ), ExcelHolder.getKey( auditStreamMap, "stsRetryAttempts" ), ExcelHolder.getKey( auditStreamMap, "stsInterval" ), ExcelHolder.getKey( auditStreamMap, "stsLookback" ) );
			GenericHelper.waitForLoadmask( searchScreenWaitSec );
			PropertyGridHelper.typeInTextBox( "Measure Capability *", auditStreamMap.get( "MeasureCapability" ) );
			GenericHelper.waitForLoadmask( searchScreenWaitSec );
			assertEquals( NavigationHelper.getScreenTitle(), "New Stream Stage" );
			ButtonHelper.click( "StreamStage" );
			GenericHelper.waitForLoadmask( searchScreenWaitSec );
			ButtonHelper.click( "streamStageDetail.OK" );
			GenericHelper.waitForLoadmask();
			assertEquals( NavigationHelper.getScreenTitle(), "Edit Stream" );
		}

		else
			Log4jHelper.logInfo( "Stream stage '" + stsName + "' is already present for Stream" );
	}

	// Method: Add stream stages of 'Manual Measures'
	public void manualMeasuresStreamStage( Map<String, String> manMeasuresStreamMap ) throws Exception
	{

		String stsName = ExcelHolder.getKey( manMeasuresStreamMap, "stsName" );
		if ( !isStreamStagePresent( stsName ) )
		{
			strImplObj.addStreamStageButton();
			streamStageType( ExcelHolder.getKey( manMeasuresStreamMap, "streamStageType" ), ExcelHolder.getKey( manMeasuresStreamMap, "stsName" ), ExcelHolder.getKey( manMeasuresStreamMap, "stsRetryFlag" ), ExcelHolder.getKey( manMeasuresStreamMap, "stsRetryAttempts" ), ExcelHolder.getKey( manMeasuresStreamMap, "stsInterval" ), ExcelHolder.getKey( manMeasuresStreamMap, "stsLookback" ) );
			GenericHelper.waitForLoadmask( searchScreenWaitSec );
			assertEquals( NavigationHelper.getScreenTitle(), "New Stream Stage" );
			ButtonHelper.click( "streamStageDetail.OK" );
			GenericHelper.waitForLoadmask();
			assertEquals( NavigationHelper.getScreenTitle(), "Edit Stream" );
		}

		else
			Log4jHelper.logInfo( "Stream stage '" + stsName + "' is already present for Stream" );
	}

	// Method: Add stream stages of 'Tap In Stream'
	public void tapInStreamStage( Map<String, String> tapInStreamMap ) throws Exception
	{

		String stsName = ExcelHolder.getKey( tapInStreamMap, "stsName" );
		if ( !isStreamStagePresent( stsName ) )
		{
			strImplObj.addStreamStageButton();
			streamStageType( ExcelHolder.getKey( tapInStreamMap, "streamStageType" ), ExcelHolder.getKey( tapInStreamMap, "stsName" ), ExcelHolder.getKey( tapInStreamMap, "stsRetryFlag" ), ExcelHolder.getKey( tapInStreamMap, "stsRetryAttempts" ), ExcelHolder.getKey( tapInStreamMap, "stsInterval" ), ExcelHolder.getKey( tapInStreamMap, "stsLookback" ) );
			GenericHelper.waitForLoadmask( searchScreenWaitSec );
			assertEquals( NavigationHelper.getScreenTitle(), "New Stream Stage" );
			TextBoxHelper.type( "LockRetryCount_id", tapInStreamMap.get( "LockRetryCount" ) );
			PropertyGridHelper.typeInDataDir( "Output Directory *", tapInStreamMap.get( "OutputDirectory" ), secondLevelDelimiter );
			TextBoxHelper.type( "PollTime_id", tapInStreamMap.get( "PollTime" ) );
			PropertyGridHelper.clickCheckBox( "Refresh Cache *", tapInStreamMap.get( "RefreshCache" ) );
			PropertyGridHelper.selectInComboBox( "Roaming Service Identification Component *", tapInStreamMap.get( "RoamingServIdenComponent" ) );
			PropertyGridHelper.selectInComboBox( "TAP Configuration *", tapInStreamMap.get( "TapConfiguration" ) );
			PropertyGridHelper.selectInComboBox( "Tap In Component *", tapInStreamMap.get( "TapInComponent" ) );
			ButtonHelper.click( "OKButton" );
			GenericHelper.waitForLoadmask();
			assertTrue( LabelHelper.isTitleNotPresent( "New Stream Stage" ), "Stream Stage did not get saved." );
		}

		else
			Log4jHelper.logInfo( "Stream stage '" + stsName + "' is already present for Stream" );
	}

	// Method: Add stream stages of 'Tap Out Stream'
	public void tapOutStreamStage( Map<String, String> tapOutStreamMap ) throws Exception
	{

		String stsName = ExcelHolder.getKey( tapOutStreamMap, "stsName" );
		if ( !isStreamStagePresent( stsName ) )
		{
			strImplObj.addStreamStageButton();
			streamStageType( ExcelHolder.getKey( tapOutStreamMap, "streamStageType" ), ExcelHolder.getKey( tapOutStreamMap, "stsName" ), ExcelHolder.getKey( tapOutStreamMap, "stsRetryFlag" ), ExcelHolder.getKey( tapOutStreamMap, "stsRetryAttempts" ), ExcelHolder.getKey( tapOutStreamMap, "stsInterval" ), ExcelHolder.getKey( tapOutStreamMap, "stsLookback" ) );
			GenericHelper.waitForLoadmask( searchScreenWaitSec );
			assertEquals( NavigationHelper.getScreenTitle(), "New Stream Stage" );
			PropertyGridHelper.typeInTextBox( "Event ID Column *", tapOutStreamMap.get( "EventIdColumn" ) );
			PropertyGridHelper.selectInComboBox( "Event Type *", tapOutStreamMap.get( "EventType" ) );
			PropertyGridHelper.typeInTextBox( "Header ID Column *", tapOutStreamMap.get( "HeaderIdColumn" ) );
			PropertyGridHelper.selectInComboBox( "Notification Component *", tapOutStreamMap.get( "NotificationComponent" ) );
			PropertyGridHelper.typeInDataDir( "Output Directory *", tapOutStreamMap.get( "OutputDirectory" ), secondLevelDelimiter );
			PropertyGridHelper.typeInTextBox( "Res Amount Column *", tapOutStreamMap.get( "ResAmountColumn" ) );
			PropertyGridHelper.selectInComboBox( "Roaming Rec Entity Component *", tapOutStreamMap.get( "RoamingRecEntityComponenet" ) );
			PropertyGridHelper.selectInComboBox( "Roaming Service Identification Component *", tapOutStreamMap.get( "RoamingServIdenComponent" ) );
			PropertyGridHelper.typeInTextBox( "Table Inst Column *", tapOutStreamMap.get( "TableInstColumn" ) );
			PropertyGridHelper.selectInComboBox( "Tap Out Component *", tapOutStreamMap.get( "TapOutComponent" ) );
			PropertyGridHelper.typeInTextBox( "Filter Thread Count *", tapOutStreamMap.get( "ThreadCount" ) );
			ButtonHelper.click( "OKButton" );
			GenericHelper.waitForLoadmask();
			assertTrue( LabelHelper.isTitleNotPresent( "New Stream Stage" ), "Stream Stage did not get saved." );
		}

		else
			Log4jHelper.logInfo( "Stream stage '" + stsName + "' is already present for Stream" );
	}

	// Method: Add stream stages of 'Tap Out Master Request'
	public void tapOutMasterRequestStreamStage( Map<String, String> tapOutMasterReqStreamMap ) throws Exception
	{

		String stsName = ExcelHolder.getKey( tapOutMasterReqStreamMap, "stsName" );
		if ( !isStreamStagePresent( stsName ) )
		{
			strImplObj.addStreamStageButton();
			streamStageType( ExcelHolder.getKey( tapOutMasterReqStreamMap, "streamStageType" ), ExcelHolder.getKey( tapOutMasterReqStreamMap, "stsName" ), ExcelHolder.getKey( tapOutMasterReqStreamMap, "stsRetryFlag" ), ExcelHolder.getKey( tapOutMasterReqStreamMap, "stsRetryAttempts" ), ExcelHolder.getKey( tapOutMasterReqStreamMap, "stsInterval" ), ExcelHolder.getKey( tapOutMasterReqStreamMap, "stsLookback" ) );
			GenericHelper.waitForLoadmask( searchScreenWaitSec );
			assertEquals( NavigationHelper.getScreenTitle(), "New Stream Stage" );
			PropertyGridHelper.selectInComboBox( "Master Component *", tapOutMasterReqStreamMap.get( "MasterComponent" ) );
			PropertyGridHelper.selectInComboBox( "Roaming Definition Group *", tapOutMasterReqStreamMap.get( "RoamingDfnGrp" ) );
			ButtonHelper.click( "OKButton" );
			GenericHelper.waitForLoadmask();
			assertTrue( LabelHelper.isTitleNotPresent( "New Stream Stage" ), "Stream Stage did not get saved." );
		}

		else
			Log4jHelper.logInfo( "Stream stage '" + stsName + "' is already present for Stream" );
	}

	// Method: Add stream stages of 'Tap Notification'
	public void tapNotificationStreamStage( Map<String, String> tapNotificationStreamMap ) throws Exception
	{

		String stsName = ExcelHolder.getKey( tapNotificationStreamMap, "stsName" );
		if ( !isStreamStagePresent( stsName ) )
		{
			strImplObj.addStreamStageButton();
			streamStageType( ExcelHolder.getKey( tapNotificationStreamMap, "streamStageType" ), ExcelHolder.getKey( tapNotificationStreamMap, "stsName" ), ExcelHolder.getKey( tapNotificationStreamMap, "stsRetryFlag" ), ExcelHolder.getKey( tapNotificationStreamMap, "stsRetryAttempts" ), ExcelHolder.getKey( tapNotificationStreamMap, "stsInterval" ), ExcelHolder.getKey( tapNotificationStreamMap, "stsLookback" ) );
			GenericHelper.waitForLoadmask( searchScreenWaitSec );
			assertEquals( NavigationHelper.getScreenTitle(), "New Stream Stage" );
			PropertyGridHelper.selectInComboBox( "Notification Component *", tapNotificationStreamMap.get( "NotificationComponent" ) );
			PropertyGridHelper.typeInDataDir( "Output Directory *", tapNotificationStreamMap.get( "OutputDirectory" ), secondLevelDelimiter );
			ButtonHelper.click( "OKButton" );
			GenericHelper.waitForLoadmask();
			assertTrue( LabelHelper.isTitleNotPresent( "New Stream Stage" ), "Stream Stage did not get saved." );
		}

		else
			Log4jHelper.logInfo( "Stream stage '" + stsName + "' is already present for Stream" );
	}

	// Method: Add stream stages of 'Rap Acknowledgement Task Stream'
	public void rapAcknowledgementTaskStreamStage( Map<String, String> rapAcknTaskStreamMap ) throws Exception
	{

		String stsName = ExcelHolder.getKey( rapAcknTaskStreamMap, "stsName" );
		if ( !isStreamStagePresent( stsName ) )
		{
			strImplObj.addStreamStageButton();
			streamStageType( ExcelHolder.getKey( rapAcknTaskStreamMap, "streamStageType" ), ExcelHolder.getKey( rapAcknTaskStreamMap, "stsName" ), ExcelHolder.getKey( rapAcknTaskStreamMap, "stsRetryFlag" ), ExcelHolder.getKey( rapAcknTaskStreamMap, "stsRetryAttempts" ), ExcelHolder.getKey( rapAcknTaskStreamMap, "stsInterval" ), ExcelHolder.getKey( rapAcknTaskStreamMap, "stsLookback" ) );
			GenericHelper.waitForLoadmask( searchScreenWaitSec );
			assertEquals( NavigationHelper.getScreenTitle(), "New Stream Stage" );
			PropertyGridHelper.typeInDataDir( "Output Directory *", rapAcknTaskStreamMap.get( "OutputDirectory" ), secondLevelDelimiter );
			ButtonHelper.click( "OKButton" );
			GenericHelper.waitForLoadmask();
			assertTrue( LabelHelper.isTitleNotPresent( "New Stream Stage" ), "Stream Stage did not get saved." );
		}

		else
			Log4jHelper.logInfo( "Stream stage '" + stsName + "' is already present for Stream" );
	}

	// Method: Add stream stages of 'Rap In Stream '
	public void rapInStreamStage( Map<String, String> rapInStreamMap ) throws Exception
	{

		String stsName = ExcelHolder.getKey( rapInStreamMap, "stsName" );
		if ( !isStreamStagePresent( stsName ) )
		{
			strImplObj.addStreamStageButton();
			streamStageType( ExcelHolder.getKey( rapInStreamMap, "streamStageType" ), ExcelHolder.getKey( rapInStreamMap, "stsName" ), ExcelHolder.getKey( rapInStreamMap, "stsRetryFlag" ), ExcelHolder.getKey( rapInStreamMap, "stsRetryAttempts" ), ExcelHolder.getKey( rapInStreamMap, "stsInterval" ), ExcelHolder.getKey( rapInStreamMap, "stsLookback" ) );
			GenericHelper.waitForLoadmask( searchScreenWaitSec );
			assertEquals( NavigationHelper.getScreenTitle(), "New Stream Stage" );
			PropertyGridHelper.typeInDataDir( "Output Directory *", rapInStreamMap.get( "OutputDirectory" ), secondLevelDelimiter );
			ButtonHelper.click( "OKButton" );
			GenericHelper.waitForLoadmask();
			assertTrue( LabelHelper.isTitleNotPresent( "New Stream Stage" ), "Stream Stage did not get saved." );
		}

		else
			Log4jHelper.logInfo( "Stream stage '" + stsName + "' is already present for Stream" );
	}

	// Method: Add stream stages of 'Rap Out Stream'
	public void rapOutStreamStreamStage( Map<String, String> rapOutStreamMap ) throws Exception
	{

		String stsName = ExcelHolder.getKey( rapOutStreamMap, "stsName" );
		if ( !isStreamStagePresent( stsName ) )
		{
			strImplObj.addStreamStageButton();
			streamStageType( ExcelHolder.getKey( rapOutStreamMap, "streamStageType" ), ExcelHolder.getKey( rapOutStreamMap, "stsName" ), ExcelHolder.getKey( rapOutStreamMap, "stsRetryFlag" ), ExcelHolder.getKey( rapOutStreamMap, "stsRetryAttempts" ), ExcelHolder.getKey( rapOutStreamMap, "stsInterval" ), ExcelHolder.getKey( rapOutStreamMap, "stsLookback" ) );
			GenericHelper.waitForLoadmask( searchScreenWaitSec );
			assertEquals( NavigationHelper.getScreenTitle(), "New Stream Stage" );
			PropertyGridHelper.typeInDataDir( "Output Directory *", rapOutStreamMap.get( "OutputDirectory" ), secondLevelDelimiter );
			ButtonHelper.click( "OKButton" );
			GenericHelper.waitForLoadmask();
			assertTrue( LabelHelper.isTitleNotPresent( "New Stream Stage" ), "Stream Stage did not get saved." );
		}

		else
			Log4jHelper.logInfo( "Stream stage '" + stsName + "' is already present for Stream" );
	}

	// Method: Add stream stages of 'NRTRDE Error Report'
	public void nrtrdeErrorReportStreamtage( Map<String, String> nrtrdeErrRepStreamMap ) throws Exception
	{

		String stsName = ExcelHolder.getKey( nrtrdeErrRepStreamMap, "stsName" );
		if ( !isStreamStagePresent( stsName ) )
		{
			strImplObj.addStreamStageButton();
			streamStageType( ExcelHolder.getKey( nrtrdeErrRepStreamMap, "streamStageType" ), ExcelHolder.getKey( nrtrdeErrRepStreamMap, "stsName" ), ExcelHolder.getKey( nrtrdeErrRepStreamMap, "stsRetryFlag" ), ExcelHolder.getKey( nrtrdeErrRepStreamMap, "stsRetryAttempts" ), ExcelHolder.getKey( nrtrdeErrRepStreamMap, "stsInterval" ), ExcelHolder.getKey( nrtrdeErrRepStreamMap, "stsLookback" ) );
			GenericHelper.waitForLoadmask( searchScreenWaitSec );
			assertEquals( NavigationHelper.getScreenTitle(), "New Stream Stage" );
			PropertyGridHelper.typeInDataDir( "Usage Output Directory *", nrtrdeErrRepStreamMap.get( "UsageOutputDirectory" ), secondLevelDelimiter );
			ButtonHelper.click( "OKButton" );
			GenericHelper.waitForLoadmask();
			assertTrue( LabelHelper.isTitleNotPresent( "New Stream Stage" ), "Stream Stage did not get saved." );
		}

		else
			Log4jHelper.logInfo( "Stream stage '" + stsName + "' is already present for Stream" );
	}

	// Method: Add stream stages of 'NRTRDE File Delivery Report'
	public void nRTRDEFileDeliveryReportStreamStage( Map<String, String> nRTRDEFileDeliRepStreamMap ) throws Exception
	{

		String stsName = ExcelHolder.getKey( nRTRDEFileDeliRepStreamMap, "stsName" );
		if ( !isStreamStagePresent( stsName ) )
		{
			strImplObj.addStreamStageButton();
			streamStageType( ExcelHolder.getKey( nRTRDEFileDeliRepStreamMap, "streamStageType" ), ExcelHolder.getKey( nRTRDEFileDeliRepStreamMap, "stsName" ), ExcelHolder.getKey( nRTRDEFileDeliRepStreamMap, "stsRetryFlag" ), ExcelHolder.getKey( nRTRDEFileDeliRepStreamMap, "stsRetryAttempts" ), ExcelHolder.getKey( nRTRDEFileDeliRepStreamMap, "stsInterval" ), ExcelHolder.getKey( nRTRDEFileDeliRepStreamMap, "stsLookback" ) );
			GenericHelper.waitForLoadmask( searchScreenWaitSec );
			assertEquals( NavigationHelper.getScreenTitle(), "New Stream Stage" );
			PropertyGridHelper.typeInDataDir( "Usage Output Directory *", nRTRDEFileDeliRepStreamMap.get( "UsageOutputDirectory" ), secondLevelDelimiter );
			ButtonHelper.click( "OKButton" );
			GenericHelper.waitForLoadmask();
			assertTrue( LabelHelper.isTitleNotPresent( "New Stream Stage" ), "Stream Stage did not get saved." );
		}

		else
			Log4jHelper.logInfo( "Stream stage '" + stsName + "' is already present for Stream" );
	}

	// Method: Add stream stages of 'NRTRDE File Generator'
	public void nRTRDEFileGeneratorStreamStage( Map<String, String> nRTRDEFileGenStreamMap ) throws Exception
	{

		String stsName = ExcelHolder.getKey( nRTRDEFileGenStreamMap, "stsName" );
		if ( !isStreamStagePresent( stsName ) )
		{
			strImplObj.addStreamStageButton();
			streamStageType( ExcelHolder.getKey( nRTRDEFileGenStreamMap, "streamStageType" ), ExcelHolder.getKey( nRTRDEFileGenStreamMap, "stsName" ), ExcelHolder.getKey( nRTRDEFileGenStreamMap, "stsRetryFlag" ), ExcelHolder.getKey( nRTRDEFileGenStreamMap, "stsRetryAttempts" ), ExcelHolder.getKey( nRTRDEFileGenStreamMap, "stsInterval" ), ExcelHolder.getKey( nRTRDEFileGenStreamMap, "stsLookback" ) );
			GenericHelper.waitForLoadmask( searchScreenWaitSec );
			assertEquals( NavigationHelper.getScreenTitle(), "New Stream Stage" );
			PropertyGridHelper.typeInDataDir( "Output Directory *", nRTRDEFileGenStreamMap.get( "OutputDirectory" ), secondLevelDelimiter );
			ButtonHelper.click( "OKButton" );
			GenericHelper.waitForLoadmask();
			assertTrue( LabelHelper.isTitleNotPresent( "New Stream Stage" ), "Stream Stage did not get saved." );
		}

		else
			Log4jHelper.logInfo( "Stream stage '" + stsName + "' is already present for Stream" );
	}

	// Method: Add stream stages of 'NRTRDE Master Request'
	public void nRTRDEMasterRequestStreamStage( Map<String, String> nRTRDEMasterReqStreamMap ) throws Exception
	{

		String stsName = ExcelHolder.getKey( nRTRDEMasterReqStreamMap, "stsName" );
		if ( !isStreamStagePresent( stsName ) )
		{
			strImplObj.addStreamStageButton();
			streamStageType( ExcelHolder.getKey( nRTRDEMasterReqStreamMap, "streamStageType" ), ExcelHolder.getKey( nRTRDEMasterReqStreamMap, "stsName" ), ExcelHolder.getKey( nRTRDEMasterReqStreamMap, "stsRetryFlag" ), ExcelHolder.getKey( nRTRDEMasterReqStreamMap, "stsRetryAttempts" ), ExcelHolder.getKey( nRTRDEMasterReqStreamMap, "stsInterval" ), ExcelHolder.getKey( nRTRDEMasterReqStreamMap, "stsLookback" ) );
			GenericHelper.waitForLoadmask( searchScreenWaitSec );
			assertEquals( NavigationHelper.getScreenTitle(), "New Stream Stage" );
			PropertyGridHelper.selectInComboBox( "Master Component *", nRTRDEMasterReqStreamMap.get( "MasterComponent" ) );
			ButtonHelper.click( "OKButton" );
			GenericHelper.waitForLoadmask();
			assertTrue( LabelHelper.isTitleNotPresent( "New Stream Stage" ), "Stream Stage did not get saved." );
		}

		else
			Log4jHelper.logInfo( "Stream stage '" + stsName + "' is already present for Stream" );
	}

	// Method: Add stream stages of 'Hur File Generation'
	public void hurFileGenerationStreamStage( Map<String, String> hurFileGenStreamMap ) throws Exception
	{

		String stsName = ExcelHolder.getKey( hurFileGenStreamMap, "stsName" );
		if ( !isStreamStagePresent( stsName ) )
		{
			strImplObj.addStreamStageButton();
			streamStageType( ExcelHolder.getKey( hurFileGenStreamMap, "streamStageType" ), ExcelHolder.getKey( hurFileGenStreamMap, "stsName" ), ExcelHolder.getKey( hurFileGenStreamMap, "stsRetryFlag" ), ExcelHolder.getKey( hurFileGenStreamMap, "stsRetryAttempts" ), ExcelHolder.getKey( hurFileGenStreamMap, "stsInterval" ), ExcelHolder.getKey( hurFileGenStreamMap, "stsLookback" ) );
			GenericHelper.waitForLoadmask( searchScreenWaitSec );
			assertEquals( NavigationHelper.getScreenTitle(), "New Stream Stage" );
			PropertyGridHelper.selectInComboBox( "Aggregation Configuration *", hurFileGenStreamMap.get( "AggregationConfiguration" ) );
			PropertyGridHelper.typeInDataDir( "Output Directory *", hurFileGenStreamMap.get( "OutputDirectory" ), secondLevelDelimiter );
			ButtonHelper.click( "OKButton" );
			GenericHelper.waitForLoadmask();
			assertTrue( LabelHelper.isTitleNotPresent( "New Stream Stage" ), "Stream Stage did not get saved." );
		}

		else
			Log4jHelper.logInfo( "Stream stage '" + stsName + "' is already present for Stream" );
	}

	// Method: Add stream stages of 'Hur Master Request'
	public void hurMasterRequestStreamtage( Map<String, String> hurMasterReqStreamMap ) throws Exception
	{

		String stsName = ExcelHolder.getKey( hurMasterReqStreamMap, "stsName" );
		if ( !isStreamStagePresent( stsName ) )
		{
			strImplObj.addStreamStageButton();
			streamStageType( ExcelHolder.getKey( hurMasterReqStreamMap, "streamStageType" ), ExcelHolder.getKey( hurMasterReqStreamMap, "stsName" ), ExcelHolder.getKey( hurMasterReqStreamMap, "stsRetryFlag" ), ExcelHolder.getKey( hurMasterReqStreamMap, "stsRetryAttempts" ), ExcelHolder.getKey( hurMasterReqStreamMap, "stsInterval" ), ExcelHolder.getKey( hurMasterReqStreamMap, "stsLookback" ) );
			GenericHelper.waitForLoadmask( searchScreenWaitSec );
			assertEquals( NavigationHelper.getScreenTitle(), "New Stream Stage" );
			PropertyGridHelper.selectInComboBox( "Master Component *", hurMasterReqStreamMap.get( "MasterComponent" ) );
			ButtonHelper.click( "OKButton" );
			GenericHelper.waitForLoadmask();
			assertTrue( LabelHelper.isTitleNotPresent( "New Stream Stage" ), "Stream Stage did not get saved." );
		}

		else
			Log4jHelper.logInfo( "Stream stage '" + stsName + "' is already present for Stream" );
	}

	// Method: Add stream stages of 'Email'
	public void emailStreamtage( Map<String, String> map ) throws Exception
	{

		String stsName = ExcelHolder.getKey( map, "stsName" );
		if ( !isStreamStagePresent( stsName ) )
		{
			strImplObj.addStreamStageButton();
			streamStageType( ExcelHolder.getKey( map, "streamStageType" ), ExcelHolder.getKey( map, "stsName" ), ExcelHolder.getKey( map, "stsRetryFlag" ), ExcelHolder.getKey( map, "stsRetryAttempts" ), ExcelHolder.getKey( map, "stsInterval" ), ExcelHolder.getKey( map, "stsLookback" ) );
			GenericHelper.waitForLoadmask( searchScreenWaitSec );
			assertEquals( NavigationHelper.getScreenTitle(), "New Stream Stage" );
			ButtonHelper.click( "OKButton" );
			GenericHelper.waitForLoadmask();
			assertTrue( LabelHelper.isTitleNotPresent( "New Stream Stage" ), "Stream Stage did not get saved." );
		}

		else
			Log4jHelper.logInfo( "Stream stage '" + stsName + "' is already present for Stream" );
	}

	// Method: Add stream stages of 'IMF Exchange Rate File Download'
	public void imfExcRateFlDownloadStreamStage( Map<String, String> map ) throws Exception
	{

		String stsName = ExcelHolder.getKey( map, "stsName" );
		if ( !isStreamStagePresent( stsName ) )
		{
			strImplObj.addStreamStageButton();
			streamStageType( ExcelHolder.getKey( map, "streamStageType" ), ExcelHolder.getKey( map, "stsName" ), ExcelHolder.getKey( map, "stsRetryFlag" ), ExcelHolder.getKey( map, "stsRetryAttempts" ), ExcelHolder.getKey( map, "stsInterval" ), ExcelHolder.getKey( map, "stsLookback" ) );
			GenericHelper.waitForLoadmask( searchScreenWaitSec );
			assertEquals( NavigationHelper.getScreenTitle(), "New Stream Stage" );
			PropertyGridHelper.typeInDataDir( "File Download Directory *", map.get( "FileDownloadDir" ), secondLevelDelimiter );
			PropertyGridHelper.typeInTextBox( "Proxy Server IP", map.get( "ProxyServerIp" ) );
			PropertyGridHelper.typeInTextBox( "Proxy Server Port", map.get( "ProxyServerPort" ) );
			PropertyGridHelper.typeInTextBox( "Retry Count *", map.get( "RetryCount" ) );
			ButtonHelper.click( "OKButton" );
			GenericHelper.waitForLoadmask();
			assertTrue( LabelHelper.isTitleNotPresent( "New Stream Stage" ), "Stream Stage did not get saved." );
		}

		else
			Log4jHelper.logInfo( "Stream stage '" + stsName + "' is already present for Stream" );
	}

	// Method: Add stream stages of 'IMF Exchange Rate Import'
	public void imfExchRateImportStreamStage( Map<String, String> map ) throws Exception
	{

		String stsName = ExcelHolder.getKey( map, "stsName" );
		if ( !isStreamStagePresent( stsName ) )
		{
			strImplObj.addStreamStageButton();
			streamStageType( ExcelHolder.getKey( map, "streamStageType" ), ExcelHolder.getKey( map, "stsName" ), ExcelHolder.getKey( map, "stsRetryFlag" ), ExcelHolder.getKey( map, "stsRetryAttempts" ), ExcelHolder.getKey( map, "stsInterval" ), ExcelHolder.getKey( map, "stsLookback" ) );
			GenericHelper.waitForLoadmask( searchScreenWaitSec );
			assertEquals( NavigationHelper.getScreenTitle(), "New Stream Stage" );
			PropertyGridHelper.selectInComboBox( "Exchange Rate Rounding DP *", map.get( "ExchangeRateRoundingDP" ) );
			PropertyGridHelper.clickCheckBox( "Ignore Last Published Date *", map.get( "IgnoredLastPublishedDtFlg" ) );
			PropertyGridHelper.selectInComboBox( "Import Cross Fx Group *", map.get( "ImportCrossFXGrp" ) );
			PropertyGridHelper.typeInTextBox( "Rate Application Day Of Month *", map.get( "RateApplicationDayOfMonth" ) );
			PropertyGridHelper.typeInTextBox( "Rate Tolerance(+/-) % *", map.get( "RateTolerance" ) );
			PropertyGridHelper.selectInComboBox( "Rounding Mode *", map.get( "RoundingMode" ) );
			ButtonHelper.click( "OKButton" );
			GenericHelper.waitForLoadmask();
			assertTrue( LabelHelper.isTitleNotPresent( "New Stream Stage" ), "Stream Stage did not get saved." );
		}

		else
			Log4jHelper.logInfo( "Stream stage '" + stsName + "' is already present for Stream" );
	}

	// Method: Add stream stages of 'Parse'
	public void parseStreamtage( Map<String, String> tapInParseStreamMap ) throws Exception
	{

		String stsName = ExcelHolder.getKey( tapInParseStreamMap, "stsName" );
		if ( !isStreamStagePresent( stsName ) )
		{
			strImplObj.addStreamStageButton();
			streamStageType( ExcelHolder.getKey( tapInParseStreamMap, "streamStageType" ), ExcelHolder.getKey( tapInParseStreamMap, "stsName" ), ExcelHolder.getKey( tapInParseStreamMap, "stsRetryFlag" ), ExcelHolder.getKey( tapInParseStreamMap, "stsRetryAttempts" ), ExcelHolder.getKey( tapInParseStreamMap, "stsInterval" ), ExcelHolder.getKey( tapInParseStreamMap, "stsLookback" ) );
			if ( ValidationHelper.isFalse( tapInParseStreamMap.get( "Check For Stale Caches Every File" ) ) )
				PropertyGridHelper.unCheckCheckBox( "Check For Stale Caches Every File" );
			if ( ValidationHelper.isFalse( "Enable Retry On Failure" ) )
				PropertyGridHelper.unCheckCheckBox( "Enable Retry On Failure(only for 'Ascii' parser)" );
			if ( ValidationHelper.isTrue( tapInParseStreamMap.get( "Log Error With Rejected Record" ) ) )
				PropertyGridHelper.checkCheckBox( "Log Error With Rejected Record" );
			if ( ValidationHelper.isFalse( tapInParseStreamMap.get( "Log Record Type Distributions" ) ) )
				PropertyGridHelper.unCheckCheckBox( "Log Record Type Distributions" );

			PropertyGridHelper.typeInDataDir( "Output Directory *", tapInParseStreamMap.get( "Output Directory" ), ";" );
			PropertyGridHelper.typeInDataDir( "Parser Definition File *", tapInParseStreamMap.get( "Parser Definition File" ), ";" );

			if ( ValidationHelper.isTrue( tapInParseStreamMap.get( "Skip Records On Error" ) ) )
				PropertyGridHelper.checkCheckBox( "Skip Records On Error" );
			ButtonHelper.click( "OKButton" );
			GenericHelper.waitForLoadmask( detailScreenWaitSec );

			addParseOutputTable( tapInParseStreamMap.get( "OutputKey" ), tapInParseStreamMap.get( "TableInstance" ), tapInParseStreamMap.get( "DataLoad" ) );

			ButtonHelper.click( "OKButton" );
			GenericHelper.waitForLoadmask( detailScreenWaitSec );
			assertTrue( LabelHelper.isTitleNotPresent( "New Stream Stage" ), "Stream Stage did not get saved." );
			Log4jHelper.logInfo( "Stream stage is screated successfully for Parse" );
		}

		else
			Log4jHelper.logInfo( "Stream stage '" + stsName + "' is already present for Stream" );
	}

	/*
	 * This method is for match and Rate stream stage
	 */

	public void matchandRateStreamStage( Map<String, String> map ) throws Exception
	{
		String stsName = ExcelHolder.getKey( map, "stsName" );
		if ( !isStreamStagePresent( stsName ) )
		{

			strImplObj.addStreamStageButton();
			streamStageType( ExcelHolder.getKey( map, "streamStageType" ), ExcelHolder.getKey( map, "stsName" ), ExcelHolder.getKey( map, "stsRetryFlag" ), ExcelHolder.getKey( map, "stsRetryAttempts" ), ExcelHolder.getKey( map, "stsInterval" ), ExcelHolder.getKey( map, "stsLookback" ) );

			PropertyGridHelper.selectInComboBox( "Default Error Status *", map.get( "Default Error Status" ) );
			PropertyGridHelper.selectInComboBox( "Event Error Extra Field Mapping Component *", map.get( "Event Error Extra Field Mapping Component" ) );
			PropertyGridHelper.typeInTextBox( "Event ID Allocation Size *", map.get( "Event ID Allocation Size" ) );
			PropertyGridHelper.selectInComboBox( "Event Type *", map.get( "Event Type" ) );
			PropertyGridHelper.typeInTextBox( "Input Queue Size *", map.get( "Input Queue Size" ) );
			if ( ValidationHelper.isTrue( map.get( "Perform Suspense Rating" ) ) )
				PropertyGridHelper.checkCheckBox( "Perform Suspense Rating *" );
			PropertyGridHelper.selectInComboBox( "Post Matching And Rating Component *", map.get( "Post Matching And Rating Component" ) );
			PropertyGridHelper.selectInComboBox( "Post Processor Component *", map.get( "Post Processor Component" ) );
			PropertyGridHelper.typeInTextBox( "Post Processor Thread Count *", map.get( "Post Processor Thread Count" ) );
			PropertyGridHelper.typeInTextBox( "Processed Queue Size *", map.get( "Processed Queue Size" ) );
			PropertyGridHelper.typeInTextBox( "Processor Thread Count *", map.get( "Processor Thread Count" ) );

			if ( ValidationHelper.isTrue( map.get( "Refresh Cache" ) ) )
				PropertyGridHelper.checkCheckBox( "Refresh Cache *" );
			if ( ValidationHelper.isFalse( map.get( "Reject Record When No Pre Rate Found" ) ) )
				PropertyGridHelper.unCheckCheckBox( "Reject Record When No Pre Rate Found *" );
			if ( ValidationHelper.isFalse( map.get( "Reject Zero Usage Events" ) ) )
				PropertyGridHelper.unCheckCheckBox( "Reject Zero Usage Events *" );
			PropertyGridHelper.selectInComboBox( "Rerate Reprocess Component *", map.get( "Rerate Reprocess Component" ) );

			PropertyGridHelper.selectInComboBox( "Roaming Call Type Group Component *", map.get( "RoamingCallTypeGrpComponent" ) );
			PropertyGridHelper.selectInComboBox( "Roaming Taxation Component *", map.get( "RoamingTaxationComponent" ) );

			PropertyGridHelper.selectInComboBox( "Suspense Rating Tariff", map.get( "Suspense Rating Tariff" ) );
			PropertyGridHelper.selectInComboBox( "Suspense Reprocess Component *", map.get( "Suspense Reprocess Component" ) );
			PropertyGridHelper.typeInDataDir( "Usage Output Directory *", map.get( "Usage Output Directory" ), ";" );
			PropertyGridHelper.typeInTextBox( "Valid Charges *", map.get( "Valid Charges" ) );

			ButtonHelper.click( "OKButton" );
			GenericHelper.waitForLoadmask( detailScreenWaitSec );
			assertTrue( LabelHelper.isTitleNotPresent( "New Stream Stage" ), "Stream Stage did not get saved." );
			Log4jHelper.logInfo( "Stream stage is created successfully for Match and Rate" );
		}

		else
			Log4jHelper.logInfo( "Stream stage '" + stsName + "' is already present for Stream" );

	}
	/*
	 * This method is for dataload stream stage
	 */

	public void dataLoadStreamStage( Map<String, String> map ) throws Exception
	{
		String stsName = ExcelHolder.getKey( map, "stsName" );
		if ( !isStreamStagePresent( stsName ) )
		{
			strImplObj.addStreamStageButton();
			streamStageType( ExcelHolder.getKey( map, "streamStageType" ), ExcelHolder.getKey( map, "stsName" ), ExcelHolder.getKey( map, "stsRetryFlag" ), ExcelHolder.getKey( map, "stsRetryAttempts" ), ExcelHolder.getKey( map, "stsInterval" ), ExcelHolder.getKey( map, "stsLookback" ) );
			if ( ValidationHelper.isTrue( map.get( "Delete Input File" ) ) )
				PropertyGridHelper.checkCheckBox( "Delete Input File" );
			if ( ValidationHelper.isFalse( map.get( "Enable Retry On Failure" ) ) )
				PropertyGridHelper.unCheckCheckBox( "Enable Retry On Failure" );
			if ( ValidationHelper.isTrue( map.get( "Log Data Load Output" ) ) )
				PropertyGridHelper.checkCheckBox( "Log Data Load Output" );

			PropertyGridHelper.typeInTextBox( "Look Back Days To Do Conventional Data Load (In Days) *", map.get( "Look Back Days To Do Conventional Data Load" ) );
			PropertyGridHelper.typeInTextBox( "Maximum Error Count Per Parse Output File", map.get( "Maximum Error Count Per Parse Output File" ) );

			if ( ValidationHelper.isTrue( map.get( "Populate DataLoad Statistics" ) ) )
				PropertyGridHelper.checkCheckBox( "Populate DataLoad Statistics" );
			if ( ValidationHelper.isTrue( map.get( "Truncate Before Load(Valid only for user defined Reference)" ) ) )
				PropertyGridHelper.checkCheckBox( "Truncate Before Load(Valid only for user defined Referenc..." );

			ButtonHelper.click( "OKButton" );
			GenericHelper.waitForLoadmask( detailScreenWaitSec );
			assertTrue( LabelHelper.isTitleNotPresent( "New Stream Stage" ), "Stream Stage did not get saved." );
			Log4jHelper.logInfo( "Stream stage is screated successfully for Data Load" );
		}
		else
			Log4jHelper.logInfo( "Stream stage '" + stsName + "' is already present for Stream" );
	}

	/*
	 * This method is for parseOutput Map in parse stream stage
	 */

	private void addParseOutputTable( String outputKey, String tableInstance, String dataLoad ) throws Exception
	{
		try
		{
			assertTrue( LabelHelper.isTextPresent( "configureParseOutputMapMsg" ), "Parse Output Map Confirmation popup did not appear." );

			if ( ValidationHelper.isEmpty( outputKey ) )
			{
				ButtonHelper.click( "NoButton" );
				GenericHelper.waitForLoadmask( detailScreenWaitSec );
			}
			else
			{
				ButtonHelper.click( "YesButton" );
				GenericHelper.waitForLoadmask( detailScreenWaitSec );
				assertTrue( LabelHelper.isTitlePresent( "Parse Output Maps" ), "Parse Output Map popup did not appear." );

				String gridId = "Stream_ParseOutput_Grid";

				String[] outputKeyArr = stringobj.stringSplitFirstLevel( outputKey );
				String[] tableInstanceArr = stringobj.stringSplitFirstLevel( tableInstance );
				String[] dataLoadArr = stringobj.stringSplitFirstLevel( dataLoad );
				for ( int i = 0; i < outputKeyArr.length; i++ )
				{
					ButtonHelper.click( "Stream_ParseOutput_Add" );
					GenericHelper.waitForLoadmask( detailScreenWaitSec );
					GridHelper.clickRow( gridId, i + 1, "Output Key" );
					GridHelper.updateGridTextBox( gridId, "Stream_ParseOutput_OutputKey", i + 1, "Output Key", "Table Instance", outputKeyArr[i] );
					if ( !EntityComboHelper.isPresent( "Stream_ParseOutput_TableInstance" ) )
						GridHelper.clickRow( gridId, i + 1, "Table Instance" );
					tableInstanceEntitySearch( "Stream_ParseOutput_TableInstance", "TableInst_TableName", tableInstanceArr[i], "Table Name" );
					GridHelper.updateGridCheckBox( gridId, "Stream_ParseOutput_DataLoad", i + 1, "Data Load", dataLoadArr[i] );
				}

			}
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}

	}

	/*This method is Table Instance Search entity search*/
	private void tableInstanceEntitySearch( String iconIdOrXpath, String txtBoxIdOrXpath, String value, String valueColumnHeader ) throws Exception
	{
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		psGenericHelper.waitforEntityElement();
		GenericHelper.waitForLoadmask();
		EntityComboHelper.clickEntityIcon( GenericHelper.getORProperty( iconIdOrXpath ) );
		ButtonHelper.click( "ClearButton" );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		ButtonHelper.click( "SearchButton" );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		psGenericHelper.waitforPopupHeaderElement( "Schema" );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		psGenericHelper.scrollforHeaderElement( "SearchGrid", "Definition Name" );
		psGenericHelper.scrollforHeaderElement( "SearchGrid", valueColumnHeader );
		int row = PSSearchGridHelper.gridFilterSearchWithTextBox( txtBoxIdOrXpath, value, valueColumnHeader );
		boolean isValue = GridHelper.isValuePresent( "Detail_popUpWindowId", "SearchGrid", value, valueColumnHeader );
		assertTrue( isValue, "Table Instance Search with  table name :'" + value + "'  is not found in 'Table Instance Search' popupScreen " );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		GridHelper.clickRow( "Detail_popUpWindowId", "SearchGrid", row, valueColumnHeader );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		ButtonHelper.clickIfEnabled( "Detail_popUpWindowId", "OKButton" );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		ElementHelper.waitForElement( "//*[@id='topLeftPanel']//div[text()='streamStageType']", searchScreenWaitSec );
	}

	//This method is to check is the stream stage is present or not
	public boolean isStreamStagePresent( String streamStage ) throws Exception
	{
		int row = GridHelper.getRowNumber( "StreamStage", streamStage, "Name" );
		if ( row != 0 )
			return true;
		else
			return false;
	}

	// Method: Add stream stages of 'Bulk Entity Export'
	public void bulkEntityExportStreamStage( Map<String, String> map ) throws Exception
	{
		String stsName = ExcelHolder.getKey( map, "stsName" );
		String folder = ExcelHolder.getKey( map, "bulkEntityExportDir" );

		if ( !isStreamStagePresent( stsName ) )
		{
			strImplObj.addStreamStageButton();
			streamStageType( ExcelHolder.getKey( map, "streamStageType" ), ExcelHolder.getKey( map, "stsName" ), ExcelHolder.getKey( map, "stsRetryFlag" ), ExcelHolder.getKey( map, "stsRetryAttempts" ), ExcelHolder.getKey( map, "stsInterval" ), ExcelHolder.getKey( map, "stsLookback" ) );
			if ( ValidationHelper.isNotEmpty( folder ) )
			{
				String filePath = configProp.getDataDirPath() + configProp.getProperty( folder );
				PropertyGridHelper.typeInDataDir( "Bulk Export File Output Directory *", filePath );
			}

			ButtonHelper.click( "OKButton" );
			GenericHelper.waitForLoadmask( detailScreenWaitSec );
			assertTrue( LabelHelper.isTitleNotPresent( "New Stream Stage" ), "Stream Stage did not get saved." );
			Log4jHelper.logInfo( "Stream stage is screated successfully for Data Load" );
		}
		else
			Log4jHelper.logInfo( "Stream stage '" + stsName + "' is already present for Stream" );
	}

	// Method: Add stream stages of 'ROCPS Export All Rows'
	public void rocpsExportAllRowsStreamStage( Map<String, String> map ) throws Exception
	{

		String stsName = ExcelHolder.getKey( map, "stsName" );
		if ( !isStreamStagePresent( stsName ) )
		{
			strImplObj.addStreamStageButton();
			streamStageType( ExcelHolder.getKey( map, "streamStageType" ), ExcelHolder.getKey( map, "stsName" ), ExcelHolder.getKey( map, "stsRetryFlag" ), ExcelHolder.getKey( map, "stsRetryAttempts" ), ExcelHolder.getKey( map, "stsInterval" ), ExcelHolder.getKey( map, "stsLookback" ) );
			GenericHelper.waitForLoadmask( searchScreenWaitSec );
			assertEquals( NavigationHelper.getScreenTitle(), "New Stream Stage" );
			PropertyGridHelper.selectInComboBox( "File Compression Type *", map.get( "FileCompressionType" ) );
			PropertyGridHelper.selectInComboBox( "File Handling *", map.get( "FileHandling" ) );

			PropertyGridHelper.typeInTextBox( "File Handling Value *", map.get( "FileHandlingValue" ) );
			PropertyGridHelper.typeInTextBox( "No Of Processing Threads *", map.get( "NoOfProcessingThreads" ) );
			PropertyGridHelper.typeInDataDir( "Root Directory *", map.get( "RootDirectory" ), ";" );
			PropertyGridHelper.selectInComboBox( "Segment Formatter Component *", map.get( "SegnentFormatterComponenet" ) );
			ButtonHelper.click( "OKButton" );
			GenericHelper.waitForLoadmask();
			assertTrue( LabelHelper.isTitleNotPresent( "New Stream Stage" ), "Stream Stage did not get saved." );
		}

		else
			Log4jHelper.logInfo( "Stream stage '" + stsName + "' is already present for Stream" );
	}

	// Method: Add stream stages of 'UsageBackout Master Request'
	public void usgBackoutMasterReqStreamStage( Map<String, String> map ) throws Exception
	{

		String stsName = ExcelHolder.getKey( map, "stsName" );
		if ( !isStreamStagePresent( stsName ) )
		{
			strImplObj.addStreamStageButton();
			streamStageType( ExcelHolder.getKey( map, "streamStageType" ), ExcelHolder.getKey( map, "stsName" ), ExcelHolder.getKey( map, "stsRetryFlag" ), ExcelHolder.getKey( map, "stsRetryAttempts" ), ExcelHolder.getKey( map, "stsInterval" ), ExcelHolder.getKey( map, "stsLookback" ) );
			GenericHelper.waitForLoadmask( searchScreenWaitSec );
			assertEquals( NavigationHelper.getScreenTitle(), "New Stream Stage" );
			PropertyGridHelper.clickCheckBox( "Delete All File Related Tables *", map.get( "DeleteAllFileRelatedTables" ) );
			PropertyGridHelper.selectInComboBox( "Master Component *", map.get( "MasterComponent" ) );
			ButtonHelper.click( "OKButton" );
			GenericHelper.waitForLoadmask();
			assertTrue( LabelHelper.isTitleNotPresent( "New Stream Stage" ), "Stream Stage did not get saved." );
		}

		else
			Log4jHelper.logInfo( "Stream stage '" + stsName + "' is already present for Stream" );
	}

	// Method: Add stream stages of 'UsageBackout '
	public void usgBackoutStreamStage( Map<String, String> map ) throws Exception
	{

		String stsName = ExcelHolder.getKey( map, "stsName" );
		if ( !isStreamStagePresent( stsName ) )
		{
			strImplObj.addStreamStageButton();
			streamStageType( ExcelHolder.getKey( map, "streamStageType" ), ExcelHolder.getKey( map, "stsName" ), ExcelHolder.getKey( map, "stsRetryFlag" ), ExcelHolder.getKey( map, "stsRetryAttempts" ), ExcelHolder.getKey( map, "stsInterval" ), ExcelHolder.getKey( map, "stsLookback" ) );
			GenericHelper.waitForLoadmask( searchScreenWaitSec );
			assertEquals( NavigationHelper.getScreenTitle(), "New Stream Stage" );
			PropertyGridHelper.typeInTextBox( "Error Id Allocation Size *", map.get( "ErrorIdAllocationSize" ) );
			PropertyGridHelper.typeInTextBox( "Thread Count *", map.get( "ThreadCount" ) );
			PropertyGridHelper.typeInDataDir( "Usage File Output Directory *", map.get( "UsageFileOutputDir" ), secondLevelDelimiter );
			PropertyGridHelper.typeInTextBox( "Wait time (in seconds) before skipping concurrent usage b...", map.get( "WaitTime" ) );
			ButtonHelper.click( "OKButton" );
			GenericHelper.waitForLoadmask();
			assertTrue( LabelHelper.isTitleNotPresent( "New Stream Stage" ), "Stream Stage did not get saved." );
		}

		else
			Log4jHelper.logInfo( "Stream stage '" + stsName + "' is already present for Stream" );
	}

	// Method: Add stream stages of 'Bulk Entity Export'
	public void alertTaskStreamStage( Map<String, String> map ) throws Exception
	{
		String stsName = ExcelHolder.getKey( map, "stsName" );

		if ( !isStreamStagePresent( stsName ) )
		{
			strImplObj.addStreamStageButton();
			streamStageType( ExcelHolder.getKey( map, "streamStageType" ), ExcelHolder.getKey( map, "stsName" ), ExcelHolder.getKey( map, "stsRetryFlag" ), ExcelHolder.getKey( map, "stsRetryAttempts" ), ExcelHolder.getKey( map, "stsInterval" ), ExcelHolder.getKey( map, "stsLookback" ) );

			ButtonHelper.click( "OKButton" );
			GenericHelper.waitForLoadmask( detailScreenWaitSec );
			assertTrue( LabelHelper.isTitleNotPresent( "New Stream Stage" ), "Stream Stage did not get saved." );
			Log4jHelper.logInfo( "Stream stage is screated successfully for Data Load" );
		}
		else
			Log4jHelper.logInfo( "Stream stage '" + stsName + "' is already present for Stream" );
	}

	/*
	 * This method is for dealImport stream stage
	 */
	public void dealImportExcelStreamStage( String path, String workBookName, String sheetName, String testCaseName, int occurence ) throws Exception
	{
		HashMap<String, ArrayList<String>> map = excelReader.readDataByColumn( path, workBookName, sheetName, testCaseName );
		String stsName = map.get( "stsName" ).get( 0 );
		if ( !isStreamStagePresent( stsName ) )
		{
			strImplObj.addStreamStageButton();
			streamStageType( map.get( "streamStageType" ).get( 0 ), map.get( "stsName" ).get( 0 ), map.get( "stsRetryFlag" ).get( 0 ), map.get( "stsRetryAttempts" ).get( 0 ), map.get( "stsInterval" ).get( 0 ), map.get( "stsLookback" ).get( 0 ) );

			PropertyGridHelper.typeInDataDir( "DealImport Template Directory *", map.get( "DealImportTemplateDir" ).get( 0 ) );

			ButtonHelper.click( "OKButton" );
			GenericHelper.waitForLoadmask( detailScreenWaitSec );
			assertEquals( NavigationHelper.getScreenTitle(), "Edit Stream", "Stream Stage did not get saved." );
			Log4jHelper.logInfo( "Stream stage is created successfully for voice" );
		}
		else
			Log4jHelper.logInfo( "Stream stage '" + stsName + "' is already present for Stream" );
	}

	// Method: Add stream stages of 'Update Bill Profile Balance'
	public void updateBillProfBalanceStreamStg( Map<String, String> map ) throws Exception
	{

		String stsName = ExcelHolder.getKey( map, "stsName" );
		if ( !isStreamStagePresent( stsName ) )
		{
			strImplObj.addStreamStageButton();
			streamStageType( ExcelHolder.getKey( map, "streamStageType" ), ExcelHolder.getKey( map, "stsName" ), ExcelHolder.getKey( map, "stsRetryFlag" ), ExcelHolder.getKey( map, "stsRetryAttempts" ), ExcelHolder.getKey( map, "stsInterval" ), ExcelHolder.getKey( map, "stsLookback" ) );
			GenericHelper.waitForLoadmask( searchScreenWaitSec );
			assertEquals( NavigationHelper.getScreenTitle(), "New Stream Stage" );
			ButtonHelper.click( "OKButton" );
			GenericHelper.waitForLoadmask();
			assertTrue( LabelHelper.isTitleNotPresent( "New Stream Stage" ), "Stream Stage did not get saved." );
		}

		else
			Log4jHelper.logInfo( "Stream stage '" + stsName + "' is already present for Stream" );
	}

	/*
	 * This method is for 'BCR Comparison Task' stream stage
	 */

	public void bcrComparisionTask( String path, String workBookName, String sheetName, String testCaseName, int occurence ) throws Exception
	{
		HashMap<String, ArrayList<String>> map = excelReader.readDataByColumn( path, workBookName, sheetName, testCaseName );
		String stsName = map.get( "stsName" ).get( 0 );
		if ( !isStreamStagePresent( stsName ) )
		{
			strImplObj.addStreamStageButton();
			streamStageType( map.get( "streamStageType" ).get( 0 ), map.get( "stsName" ).get( 0 ), map.get( "stsRetryFlag" ).get( 0 ), map.get( "stsRetryAttempts" ).get( 0 ), map.get( "stsInterval" ).get( 0 ), map.get( "stsLookback" ).get( 0 ) );

			PropertyGridHelper.typeInDataDir( "Report Output Directory *", map.get( "ReportOutputDirectory" ).get( 0 ) );
			ButtonHelper.click( "OKButton" );
			GenericHelper.waitForLoadmask( detailScreenWaitSec );
			assertTrue( LabelHelper.isTitleNotPresent( "New Stream Stage" ), "Stream Stage did not get saved." );
			Log4jHelper.logInfo( "Stream stage is screated successfully for " + stsName );
		}
		else
			Log4jHelper.logInfo( "Stream stage '" + stsName + "' is already present for Stream" );
	}
	/*
	 * This method is for 'BCR Delta Task' stream stage
	 */

	public void bcrDeltaTask( String path, String workBookName, String sheetName, String testCaseName, int occurence ) throws Exception
	{
		HashMap<String, ArrayList<String>> map = excelReader.readDataByColumn( path, workBookName, sheetName, testCaseName );
		String stsName = map.get( "stsName" ).get( 0 );
		if ( !isStreamStagePresent( stsName ) )
		{
			strImplObj.addStreamStageButton();
			streamStageType( map.get( "streamStageType" ).get( 0 ), map.get( "stsName" ).get( 0 ), map.get( "stsRetryFlag" ).get( 0 ), map.get( "stsRetryAttempts" ).get( 0 ), map.get( "stsInterval" ).get( 0 ), map.get( "stsLookback" ).get( 0 ) );

			ButtonHelper.click( "OKButton" );
			GenericHelper.waitForLoadmask( detailScreenWaitSec );
			assertTrue( LabelHelper.isTitleNotPresent( "New Stream Stage" ), "Stream Stage did not get saved." );
			Log4jHelper.logInfo( "Stream stage is screated successfully for " + stsName );
		}
		else
			Log4jHelper.logInfo( "Stream stage '" + stsName + "' is already present for Stream" );
	}
	/*
	 * This method is for 'BCR Generation Recurring Task' stream stage
	 */

	public void bCRGenerationRecurringTask( String path, String workBookName, String sheetName, String testCaseName, int occurence ) throws Exception
	{
		HashMap<String, ArrayList<String>> map = excelReader.readDataByColumn( path, workBookName, sheetName, testCaseName );
		String stsName = map.get( "stsName" ).get( 0 );
		if ( !isStreamStagePresent( stsName ) )
		{
			strImplObj.addStreamStageButton();
			streamStageType( map.get( "streamStageType" ).get( 0 ), map.get( "stsName" ).get( 0 ), map.get( "stsRetryFlag" ).get( 0 ), map.get( "stsRetryAttempts" ).get( 0 ), map.get( "stsInterval" ).get( 0 ), map.get( "stsLookback" ).get( 0 ) );
			PropertyGridHelper.typeInTextBox( "Look Forward Date *", map.get( "LookForwardDate" ).get( 0 ) );
			PropertyGridHelper.selectInComboBox( "Product", map.get( "Product" ).get( 0 ) );
			PropertyGridHelper.selectInComboBox( "Switch", map.get( "Switch" ).get( 0 ) );

			ButtonHelper.click( "OKButton" );
			GenericHelper.waitForLoadmask( detailScreenWaitSec );
			assertTrue( LabelHelper.isTitleNotPresent( "New Stream Stage" ), "Stream Stage did not get saved." );
			Log4jHelper.logInfo( "Stream stage is screated successfully for " + stsName );
		}
		else
			Log4jHelper.logInfo( "Stream stage '" + stsName + "' is already present for Stream" );
	}
	/*
	 * This method is for 'BCR Generation  Task' stream stage
	 */

	public void bCRGenerationTask( String path, String workBookName, String sheetName, String testCaseName, int occurence ) throws Exception
	{
		HashMap<String, ArrayList<String>> map = excelReader.readDataByColumn( path, workBookName, sheetName, testCaseName );
		String stsName = map.get( "stsName" ).get( 0 );
		if ( !isStreamStagePresent( stsName ) )
		{
			strImplObj.addStreamStageButton();
			streamStageType( map.get( "streamStageType" ).get( 0 ), map.get( "stsName" ).get( 0 ), map.get( "stsRetryFlag" ).get( 0 ), map.get( "stsRetryAttempts" ).get( 0 ), map.get( "stsInterval" ).get( 0 ), map.get( "stsLookback" ).get( 0 ) );
			PropertyGridHelper.typeInTextBox( "Capacity Tolerance *", map.get( "CapacityTolerance" ).get( 0 ) );
			//PropertyGridHelper.typeInTextBox( "Deal Priority Threshold in Days  *", map.get( "DealPriorityThresholdinDays" ).get( 0 ));
			TextBoxHelper.type( "//div[text()='Deal Priority Threshold in Days  *']/parent::td/parent::tr//input[contains(@class,'TextBox')]", map.get( "DealPriorityThresholdinDays" ).get( 0 ) );
			PropertyGridHelper.typeInTextBox( "Overflow Percent *", map.get( "OverflowPercent" ).get( 0 ) );
			PropertyGridHelper.selectInComboBox( "Traffic Data Lookup *", map.get( "TrafficDataLookup" ).get( 0 ) );

			ButtonHelper.click( "OKButton" );
			GenericHelper.waitForLoadmask( detailScreenWaitSec );
			assertTrue( LabelHelper.isTitleNotPresent( "New Stream Stage" ), "Stream Stage did not get saved." );
			Log4jHelper.logInfo( "Stream stage is screated successfully for " + stsName );
		}
		else
			Log4jHelper.logInfo( "Stream stage '" + stsName + "' is already present for Stream" );
	}
	/*
	 * This method is for 'Deal Billing Rate Calculation Task' stream stage
	 */

	public void dealBillingRateCalculationTask( String path, String workBookName, String sheetName, String testCaseName, int occurence ) throws Exception
	{
		HashMap<String, ArrayList<String>> map = excelReader.readDataByColumn( path, workBookName, sheetName, testCaseName );
		String stsName = map.get( "stsName" ).get( 0 );
		if ( !isStreamStagePresent( stsName ) )
		{
			strImplObj.addStreamStageButton();
			streamStageType( map.get( "streamStageType" ).get( 0 ), map.get( "stsName" ).get( 0 ), map.get( "stsRetryFlag" ).get( 0 ), map.get( "stsRetryAttempts" ).get( 0 ), map.get( "stsInterval" ).get( 0 ), map.get( "stsLookback" ).get( 0 ) );
			PropertyGridHelper.typeInDatePicker( "Calculation Date *", map.get( "CalculationDate" ).get( 0 ) );

			ButtonHelper.click( "OKButton" );
			GenericHelper.waitForLoadmask( detailScreenWaitSec );
			assertTrue( LabelHelper.isTitleNotPresent( "New Stream Stage" ), "Stream Stage did not get saved." );
			Log4jHelper.logInfo( "Stream stage is screated successfully for " + stsName );
		}
		else
			Log4jHelper.logInfo( "Stream stage '" + stsName + "' is already present for Stream" );
	}
	/*
	 * This method is for 'QualityMasterTask   ' stream stage
	 */

	public void QualityMasterTask( String path, String workBookName, String sheetName, String testCaseName, int occurence ) throws Exception
	{
		HashMap<String, ArrayList<String>> map = excelReader.readDataByColumn( path, workBookName, sheetName, testCaseName );
		String stsName = map.get( "stsName" ).get( 0 );
		if ( !isStreamStagePresent( stsName ) )
		{
			strImplObj.addStreamStageButton();
			streamStageType( map.get( "streamStageType" ).get( 0 ), map.get( "stsName" ).get( 0 ), map.get( "stsRetryFlag" ).get( 0 ), map.get( "stsRetryAttempts" ).get( 0 ), map.get( "stsInterval" ).get( 0 ), map.get( "stsLookback" ).get( 0 ) );
			PropertyGridHelper.selectInComboBox( "Master Component *", map.get( "MasterComponent" ).get( 0 ) );

			ButtonHelper.click( "OKButton" );
			GenericHelper.waitForLoadmask( detailScreenWaitSec );
			assertTrue( LabelHelper.isTitleNotPresent( "New Stream Stage" ), "Stream Stage did not get saved." );
			Log4jHelper.logInfo( "Stream stage is screated successfully for " + stsName );
		}
		else
			Log4jHelper.logInfo( "Stream stage '" + stsName + "' is already present for Stream" );
	}
	/*
	 * This method is for 'Quality Results Aggregation Task' stream stage
	 */

	public void qualityResultsAggregationTask( String path, String workBookName, String sheetName, String testCaseName, int occurence ) throws Exception
	{
		HashMap<String, ArrayList<String>> map = excelReader.readDataByColumn( path, workBookName, sheetName, testCaseName );
		String stsName = map.get( "stsName" ).get( 0 );
		if ( !isStreamStagePresent( stsName ) )
		{
			strImplObj.addStreamStageButton();
			streamStageType( map.get( "streamStageType" ).get( 0 ), map.get( "stsName" ).get( 0 ), map.get( "stsRetryFlag" ).get( 0 ), map.get( "stsRetryAttempts" ).get( 0 ), map.get( "stsInterval" ).get( 0 ), map.get( "stsLookback" ).get( 0 ) );
			PropertyGridHelper.typeInTextBox( "Evaluator Thread Count *", map.get( "EvaluatorThreadCount" ).get( 0 ) );
			PropertyGridHelper.typeInTextBox( "Input Data Queue Size *", map.get( "InputDataQueueSize" ).get( 0 ) );
			PropertyGridHelper.selectInComboBox( "Quality Results Aggregation Component *", map.get( "QualityResultsAggregationComponent" ).get( 0 ) );

			ButtonHelper.click( "OKButton" );
			GenericHelper.waitForLoadmask( detailScreenWaitSec );
			assertTrue( LabelHelper.isTitleNotPresent( "New Stream Stage" ), "Stream Stage did not get saved." );
			Log4jHelper.logInfo( "Stream stage is screated successfully for " + stsName );
		}
		else
			Log4jHelper.logInfo( "Stream stage '" + stsName + "' is already present for Stream" );
	}
	/*
	 * This method is for 'Routing Rate Calculation Task' stream stage
	 */

	public void routingRateCalculationTask( String path, String workBookName, String sheetName, String testCaseName, int occurence ) throws Exception
	{
		HashMap<String, ArrayList<String>> map = excelReader.readDataByColumn( path, workBookName, sheetName, testCaseName );
		String stsName = map.get( "stsName" ).get( 0 );
		if ( !isStreamStagePresent( stsName ) )
		{
			strImplObj.addStreamStageButton();
			streamStageType( map.get( "streamStageType" ).get( 0 ), map.get( "stsName" ).get( 0 ), map.get( "stsRetryFlag" ).get( 0 ), map.get( "stsRetryAttempts" ).get( 0 ), map.get( "stsInterval" ).get( 0 ), map.get( "stsLookback" ).get( 0 ) );
			PropertyGridHelper.typeInDatePicker( "Calculation Date", map.get( "CalculationDate" ).get( 0 ) );
			PropertyGridHelper.selectInComboBox( "Operator", map.get( "Operator" ).get( 0 ) );
			PropertyGridHelper.selectInComboBox( "Tariff", map.get( "Tariff" ).get( 0 ) );

			ButtonHelper.click( "OKButton" );
			GenericHelper.waitForLoadmask( detailScreenWaitSec );
			assertTrue( LabelHelper.isTitleNotPresent( "New Stream Stage" ), "Stream Stage did not get saved." );
			Log4jHelper.logInfo( "Stream stage is screated successfully for " + stsName );
		}
		else
			Log4jHelper.logInfo( "Stream stage '" + stsName + "' is already present for Stream" );
	}
	/*
	 * This method is for 'Threshold Evaluation' stream stage
	 */

	public void thresholdEvaluation( String path, String workBookName, String sheetName, String testCaseName, int occurence ) throws Exception
	{
		HashMap<String, ArrayList<String>> map = excelReader.readDataByColumn( path, workBookName, sheetName, testCaseName );
		String stsName = map.get( "stsName" ).get( 0 );
		if ( !isStreamStagePresent( stsName ) )
		{
			strImplObj.addStreamStageButton();
			streamStageType( map.get( "streamStageType" ).get( 0 ), map.get( "stsName" ).get( 0 ), map.get( "stsRetryFlag" ).get( 0 ), map.get( "stsRetryAttempts" ).get( 0 ), map.get( "stsInterval" ).get( 0 ), map.get( "stsLookback" ).get( 0 ) );

			ButtonHelper.click( "OKButton" );
			GenericHelper.waitForLoadmask( detailScreenWaitSec );
			assertTrue( LabelHelper.isTitleNotPresent( "New Stream Stage" ), "Stream Stage did not get saved." );
			Log4jHelper.logInfo( "Stream stage is screated successfully for " + stsName );
		}
		else
			Log4jHelper.logInfo( "Stream stage '" + stsName + "' is already present for Stream" );
	}

	/*
	 * This method is for match and Rate stream stage
	 */

	public void eventAndAggStreamStage( Map<String, String> map ) throws Exception
	{
		String stsName = ExcelHolder.getKey( map, "stsName" );
		if ( !isStreamStagePresent( stsName ) )
		{

			strImplObj.addStreamStageButton();
			streamStageType( ExcelHolder.getKey( map, "streamStageType" ), ExcelHolder.getKey( map, "stsName" ), ExcelHolder.getKey( map, "stsRetryFlag" ), ExcelHolder.getKey( map, "stsRetryAttempts" ), ExcelHolder.getKey( map, "stsInterval" ), ExcelHolder.getKey( map, "stsLookback" ) );

			if ( ValidationHelper.isTrue( map.get( "AcceptBRDandAgreementModelling" ) ) )
				PropertyGridHelper.checkCheckBox( "Accept BRD and Agreement Modelling *" );

			ButtonHelper.click( "OKButton" );
			GenericHelper.waitForLoadmask( detailScreenWaitSec );
			assertTrue( LabelHelper.isTitleNotPresent( "New Stream Stage" ), "Stream Stage did not get saved." );
			Log4jHelper.logInfo( "Stream stage is created successfully for Match and Rate" );
		}

		else
			Log4jHelper.logInfo( "Stream stage '" + stsName + "' is already present for Stream" );

	}
}
