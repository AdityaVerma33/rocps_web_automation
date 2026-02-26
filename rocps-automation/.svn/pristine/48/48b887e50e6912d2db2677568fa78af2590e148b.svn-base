package com.subex.rocps.automation.helpers.application.tariffs;

import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Map;

import com.subex.rocps.automation.helpers.application.filters.AdvanceSearchFiltersHelper;
import com.subex.rocps.automation.helpers.application.filters.PSSearchGridHelper;
import com.subex.rocps.automation.helpers.application.genericHelpers.DataVerificationHelper;
import com.subex.rocps.automation.helpers.application.genericHelpers.PSGenericHelper;
import com.subex.rocps.automation.helpers.application.tariffs.autoratesheetconfig.AutoRateSheetConfigDetailImpl;
import com.subex.rocps.automation.helpers.application.tariffs.ratesheetimportrequest.RateSheetImportRequestDetailImpl;
import com.subex.rocps.automation.helpers.application.tariffs.ratesheetimportrequest.RateSheetImportRequestSearchImpl;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;
import com.subex.rocps.automation.utils.ExcelHolder;
import com.subex.rocps.automation.utils.PSStringUtils;

import com.subex.automation.helpers.application.NavigationHelper;
import com.subex.automation.helpers.component.ButtonHelper;
import com.subex.automation.helpers.component.EntityComboHelper;
import com.subex.automation.helpers.component.GenericHelper;
import com.subex.automation.helpers.component.GridHelper;
import com.subex.automation.helpers.component.PopupHelper;
import com.subex.automation.helpers.component.SearchGridHelper;
import com.subex.automation.helpers.component.TextBoxHelper;
import com.subex.automation.helpers.data.ValidationHelper;
import com.subex.automation.helpers.file.ExcelReader;
import com.subex.automation.helpers.report.Log4jHelper;
import com.subex.automation.helpers.util.FailureHelper;

public class AutoRateSheetConfig extends PSAcceptanceTest
{
	protected ExcelReader excelData = null;
	protected Map<String, ArrayList<String>> autoratesheetExcel = null;
	protected Map<String, String> autoratesheetMap = null;
	protected ExcelHolder excelHolderObj = null;
	protected String path;
	protected String workBookName;
	protected String sheetName;
	protected String testCaseName;
	protected int paramVal;
	protected int colSize;
	protected String clientPartition;
	protected String tariff;
	String name;
	String rateEffectiveDate;
	String completeExpireDate;
	String originRateEffectiveDate;
	String originCompleteExpireDate;
	String complete;
	String autoAuthorize;
	String locationInformation;
	String destinationDetails;
	String originDetails;
	String colHeaders;
	String results;
	String emailId;
	String emailSubject;
	String maxsize;
	String attachment;
	String outputdir;
	String expressionName;
	String expression;
	String matchString;
	String matchCondition;
	String configRuleTestCase;
	String pollConfigRuleTestCase;
	String regex = new PSStringUtils().regexFirstLevelDelimeter();
	String templateName;

	protected PSGenericHelper genericHelperObj = new PSGenericHelper();
	DataVerificationHelper dataVerifyObj = new DataVerificationHelper();

	/*
	 * Constructor for initializing excel Identifying the column size from the
	 * map passed
	 */
	@Test
	public AutoRateSheetConfig( String path, String workBookName, String sheetName, String testCaseName ) throws Exception
	{
		this.path = path;
		this.workBookName = workBookName;
		this.sheetName = sheetName;
		this.testCaseName = testCaseName;
		excelData = new ExcelReader();
		autoratesheetExcel = excelData.readDataByColumn( this.path, this.workBookName, this.sheetName, this.testCaseName );
		excelHolderObj = new ExcelHolder( autoratesheetExcel );
		colSize = excelHolderObj.totalColumns();
	}

	/*
	 * Overloaded constructor for reading data from excel if test case name
	 * appears more than once
	 * 
	 * @Param occurance : Test case instance in excel sheet Constructor for
	 * initializing excel Identifying the column size from the map
	 */
	public AutoRateSheetConfig( String path, String workBookName, String sheetName, String testCaseName, int occurance ) throws Exception
	{
		this.path = path;
		this.workBookName = workBookName;
		this.sheetName = sheetName;
		this.testCaseName = testCaseName;
		excelData = new ExcelReader();
		autoratesheetExcel = excelData.readDataByColumn( this.path, this.workBookName, this.sheetName, this.testCaseName, occurance );
		excelHolderObj = new ExcelHolder( autoratesheetExcel );
		colSize = excelHolderObj.totalColumns();
	}

	/*
	 * Configuring the Rate sheet Import Request
	 * 
	 */
	public void autoRateSheetConfiguration() throws Exception
	{
		try
		{
			NavigationHelper.navigateToScreen( "Auto Rate Sheet Config" );
			for ( paramVal = 0; paramVal < colSize; paramVal++ )
			{
				autoratesheetMap = excelHolderObj.dataMap( paramVal );
				initalizeVariables();
				if ( !isAutoRatesheetConfigPresnet() )
					newAutoRatesheetConfig();
				else
				{
					if ( ValidationHelper.isNotEmpty( results ) )
					{
						assertTrue( isAutoRatesheetConfigPresnet() );
						dataVerifyObj.validateData( "grid_column_header_searchGrid_", colHeaders, "SearchGrid", results );
					}
					Log4jHelper.logInfo( "Auto Rate sheet is already available for :" + tariff );
				}
			}
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

	public void newAutoRatesheetConfig() throws Exception
	{
		AutoRateSheetConfigDetailImpl autoRateDetailObj = new AutoRateSheetConfigDetailImpl();

		genericHelperObj.clickNewAction( clientPartition );
		autoRateDetailObj.newAutoRateSheetConfig( name, tariff );
		if ( ValidationHelper.isNotEmpty( configRuleTestCase ) )
			autoRateDetailObj.emailDetails( this.path, this.workBookName, this.sheetName, configRuleTestCase, emailId, emailSubject, maxsize, attachment, outputdir, autoratesheetMap, rateEffectiveDate, templateName );
		if ( ValidationHelper.isNotEmpty( expressionName ) && ValidationHelper.isNotEmpty( pollConfigRuleTestCase ) )
			autoRateDetailObj.pollDirectory( this.path, this.workBookName, this.sheetName, pollConfigRuleTestCase, expressionName, expression, matchString, matchCondition, autoratesheetMap, templateName );
		autoRateDetailObj.saveAutoRateSheetConfig( tariff );
		if ( ValidationHelper.isNotEmpty( results ) )
		{
			assertTrue( isAutoRatesheetConfigPresnet() );
			dataVerifyObj.validateData( "grid_column_header_searchGrid_", colHeaders, "SearchGrid", results );
		}

	}

	public boolean isAutoRatesheetConfigPresnet() throws Exception
	{

		/*ButtonHelper.click( "SearchButton" );
		ButtonHelper.click( "ClearButton" );*/
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		AdvanceSearchFiltersHelper filterObj = new AdvanceSearchFiltersHelper();
		//filterObj.tariffAdvanceSearchGridFilter( "grid_column_header_filtersearchGrid_tariff$tffName", tariff );
		filterObj.tariffAdvanceSearchGridFilter( "searchGrid", tariff );
		//SearchGridHelper.gridFilterAdvancedSearch( "tariff", tariff, "Tariff" );
		return GridHelper.isValuePresent( "SearchGrid", tariff, "Tariff" );
	}

	/*
	 * This method is for screen screen column validation
	 */

	public void searchScreenColumnsValidation() throws Exception
	{
		NavigationHelper.navigateToScreen( "Auto Rate Sheet Config" );
		for ( paramVal = 0; paramVal < colSize; paramVal++ )
		{
			autoratesheetMap = excelHolderObj.dataMap( paramVal );
			String searchScreenColumns = ExcelHolder.getKey( autoratesheetMap, "SearchScreenColumns" );
			ArrayList<String> excelColumnNames = new ArrayList<String>();
			String[] searchGridColumnsArr = searchScreenColumns.split( regex, -1 );
			for ( int col = 0; col < searchGridColumnsArr.length; col++ )
			{
				excelColumnNames.add( searchGridColumnsArr[col] );
			}
			genericHelperObj.totalColumns( excelColumnNames );
		}

	}

	/*
	 * This method is to delete autoRatesheetconfig
	 */
	public void autoRatesheetconfigEdit() throws Exception
	{
		AutoRateSheetConfigDetailImpl autoRateDetailObj = new AutoRateSheetConfigDetailImpl();

		NavigationHelper.navigateToScreen( "Auto Rate Sheet Config" );
		for ( paramVal = 0; paramVal < colSize; paramVal++ )
		{
			autoratesheetMap = excelHolderObj.dataMap( paramVal );
			tariff = ExcelHolder.getKey( autoratesheetMap, "Tariff" );

			if ( isAutoRatesheetConfigPresnet() )
			{
				GridHelper.clickRow( "SearchGrid", name, "Tariff" );
				autoRateDetailObj.editAutoRateSheetConfig( tariff );
				autoRateDetailObj.saveAutoRateSheetConfig( tariff );
				Log4jHelper.logInfo( "AutoRatesheet Config is deleted successfully with name " + name );
			}
			else
				Log4jHelper.logInfo( "AutoRatesheet Config is not available with name " + name );
		}
	}

	/*
	 * This method is to delete autoRatesheetconfig
	 */
	public void autoRatesheetconfigDelete() throws Exception
	{

		NavigationHelper.navigateToScreen( "Auto Rate Sheet Config" );
		for ( paramVal = 0; paramVal < colSize; paramVal++ )
		{
			autoratesheetMap = excelHolderObj.dataMap( paramVal );

			clientPartition = ExcelHolder.getKey( autoratesheetMap, "Partition" );
			name = ExcelHolder.getKey( autoratesheetMap, "Name" );
			genericHelperObj.selectPartitionFilter( clientPartition, "Non Deleted Items" );
			int row = PSSearchGridHelper.gridFilterSearchWithTextBox( "prscName", name, "Name" );
			if ( row > 0 )
			{
				genericHelperObj.clickDeleteOrUnDeleteAction( name, "Name", "Delete" );
				GenericHelper.waitForLoadmask();
				genericHelperObj.selectPartitionFilter( clientPartition, "Deleted Items" );
				assertTrue( GridHelper.isValuePresent( "SearchGrid", name, "Name" ) );
				Log4jHelper.logInfo( "AutoRatesheet Config is deleted successfully with name " + name );
			}
			else
				Log4jHelper.logInfo( "AutoRatesheet Config is not available with name " + name );
		}
	}

	/*
	 * This method is to un delete autoRatesheetconfig
	 */
	public void autoRatesheetconfigUnDelete() throws Exception
	{

		NavigationHelper.navigateToScreen( "Auto Rate Sheet Config" );
		for ( paramVal = 0; paramVal < colSize; paramVal++ )
		{
			autoratesheetMap = excelHolderObj.dataMap( paramVal );

			clientPartition = ExcelHolder.getKey( autoratesheetMap, "Partition" );
			name = ExcelHolder.getKey( autoratesheetMap, "Name" );
			genericHelperObj.selectPartitionFilter( clientPartition, "Deleted Items" );
			GenericHelper.waitForLoadmask();

			int row = PSSearchGridHelper.gridFilterSearchWithTextBox( "prscName", name, "Name" );
			if ( row > 0 )
			{
				genericHelperObj.clickDeleteOrUnDeleteAction( name, "Name", "Undelete" );
				GenericHelper.waitForLoadmask();
				genericHelperObj.selectPartitionFilter( clientPartition, "Non Deleted Items" );
				assertTrue( GridHelper.isValuePresent( "SearchGrid", name, "Name" ) );
				ButtonHelper.click( "ClearButton" );
				GenericHelper.waitForLoadmask();
				ButtonHelper.click( "SearchButton" );
				
				Log4jHelper.logInfo( "AutoRatesheet Config is un deleted successfully : " + name );
			}
			else
				Log4jHelper.logInfo( "AutoRatesheet Config is not available : " + name );
		}
	}

	public void initalizeVariables() throws Exception
	{
		clientPartition = ExcelHolder.getKey( autoratesheetMap, "Partition" );
		name = ExcelHolder.getKey( autoratesheetMap, "Name" );
		tariff = ExcelHolder.getKey( autoratesheetMap, "Tariff" );
		emailId = ExcelHolder.getKey( autoratesheetMap, "EmailId" );
		emailSubject = ExcelHolder.getKey( autoratesheetMap, "EmailSubject" );
		maxsize = ExcelHolder.getKey( autoratesheetMap, "Maxsize" );
		attachment = ExcelHolder.getKey( autoratesheetMap, "Attachment" );
		outputdir = ExcelHolder.getKey( autoratesheetMap, "Outputdir" );
		rateEffectiveDate = ExcelHolder.getKey( autoratesheetMap, "RateEffectiveDate" );
		expressionName = ExcelHolder.getKey( autoratesheetMap, "ExpressionName" );
		expression = ExcelHolder.getKey( autoratesheetMap, "Expression" );
		matchString = ExcelHolder.getKey( autoratesheetMap, "MatchString" );
		matchCondition = ExcelHolder.getKey( autoratesheetMap, "MatchCondition" );
		colHeaders = ExcelHolder.getKey( autoratesheetMap, "ColHeaders" );
		results = ExcelHolder.getKey( autoratesheetMap, "Results" );
		templateName = ExcelHolder.getKey( autoratesheetMap, "TemplateName" );
		configRuleTestCase = ExcelHolder.getKey( autoratesheetMap, "ConfigRuleTestCase" );
		pollConfigRuleTestCase = ExcelHolder.getKey( autoratesheetMap, "PollConfigRuleTestCase" );

	}

}
