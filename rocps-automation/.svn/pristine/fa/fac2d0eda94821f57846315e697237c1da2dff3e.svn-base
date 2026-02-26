package com.subex.rocps.automation.helpers.application.reaggregation;

import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Map;

import com.subex.rocps.automation.helpers.application.bills.creditnotes.CreditNotesActionImpl;
import com.subex.rocps.automation.helpers.application.bills.creditnotes.CreditNotesDetailImpl;
import com.subex.rocps.automation.helpers.application.bills.creditnotes.CreditNotesSearchImpl;
import com.subex.rocps.automation.helpers.application.filters.GridFilterSearchHelper;
import com.subex.rocps.automation.helpers.application.genericHelpers.DataVerificationHelper;
import com.subex.rocps.automation.helpers.application.genericHelpers.PSGenericHelper;
import com.subex.rocps.automation.helpers.application.reaggregation.reaggregationrequest.ReAggregationRequestActionImpl;
import com.subex.rocps.automation.helpers.application.reaggregation.reaggregationrequest.ReAggregationRequestDetailImpl;
import com.subex.rocps.automation.helpers.application.reaggregation.reaggregationrequest.ReAggregationRequestSearchImpl;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;
import com.subex.rocps.automation.utils.ExcelHolder;
import com.subex.rocps.automation.utils.PSStringUtils;

import com.subex.automation.helpers.application.NavigationHelper;
import com.subex.automation.helpers.component.ButtonHelper;
import com.subex.automation.helpers.component.CalendarHelper;
import com.subex.automation.helpers.component.CheckBoxHelper;
import com.subex.automation.helpers.component.ComboBoxHelper;
import com.subex.automation.helpers.component.GenericHelper;
import com.subex.automation.helpers.component.GridHelper;
import com.subex.automation.helpers.component.PopupHelper;
import com.subex.automation.helpers.component.TextAreaHelper;
import com.subex.automation.helpers.component.TextBoxHelper;
import com.subex.automation.helpers.data.ValidationHelper;
import com.subex.automation.helpers.file.ExcelReader;
import com.subex.automation.helpers.report.Log4jHelper;
import com.subex.automation.helpers.util.FailureHelper;

public class ReAggregationRequest extends PSAcceptanceTest
{
	protected ExcelReader excelData = null;
	protected Map<String, ArrayList<String>> reAggrExcel = null;
	protected Map<String, String> reAggrMap = null;
	protected ExcelHolder excelHolderObj = null;
	protected String path;
	protected String workBookName;
	protected String sheetName;
	protected String testCaseName;
	protected int paramVal;
	protected int colSize;
	protected String clientPartition;
	protected String billProfile;
	protected String createdDate;
	String aggregationProcessor;
	String fromDate;
	String toDate;
	String description;
	String billPeriod;
	String include;
	String autoDelete;
	String eventGridColHeaders;
	String eventGridValue;
	String billPeriodGridProfileVal;
	String billPeriodGridPeriodVal;
	String eventType;
	String eventInclude;
	String account;
	protected PSGenericHelper genericHelperObj = new PSGenericHelper();
	protected String regex = new PSStringUtils().regexFirstLevelDelimeter();
	ReAggregationRequestActionImpl reaggActionObj = new ReAggregationRequestActionImpl();
	ReAggregationRequestDetailImpl reaggDetailObj = new ReAggregationRequestDetailImpl();
	DataVerificationHelper dataVerifyObj = new DataVerificationHelper();
	ReAggregationRequestSearchImpl reaggrSearchObj = new ReAggregationRequestSearchImpl();

	/*
	 * Constructor for initializing excel Identifying the column size from the
	 * map passed
	 */
	@Test
	public ReAggregationRequest( String path, String workBookName, String sheetName, String testCaseName ) throws Exception
	{
		this.path = path;
		this.workBookName = workBookName;
		this.sheetName = sheetName;
		this.testCaseName = testCaseName;
		excelData = new ExcelReader();
		reAggrExcel = excelData.readDataByColumn( this.path, this.workBookName, this.sheetName, this.testCaseName );
		excelHolderObj = new ExcelHolder( reAggrExcel );
		colSize = excelHolderObj.totalColumns();
	}

	/*
	 * Overloaded constructor for reading data from excel if test case name
	 * appears more than once
	 * 
	 * @Param occurance : Test case instance in excel sheet Constructor for
	 * initializing excel Identifying the column size from the map
	 */
	public ReAggregationRequest( String path, String workBookName, String sheetName, String testCaseName, int occurance ) throws Exception
	{
		this.path = path;
		this.workBookName = workBookName;
		this.sheetName = sheetName;
		this.testCaseName = testCaseName;
		excelData = new ExcelReader();
		reAggrExcel = excelData.readDataByColumn( this.path, this.workBookName, this.sheetName, this.testCaseName, occurance );
		excelHolderObj = new ExcelHolder( reAggrExcel );
		colSize = excelHolderObj.totalColumns();
	}

	/*
	 * Configuring the Re-Aggregation 
	 * 
	 */
	public void reAggregationRequestCreation() throws Exception
	{
		try
		{
			NavigationHelper.navigateToScreen( "Re-Aggregation Request" );
			for ( paramVal = 0; paramVal < colSize; paramVal++ )
			{
				reAggrMap = excelHolderObj.dataMap( paramVal );

				initializeVariables( reAggrMap );
				boolean isReAggregationPresnet = reaggrSearchObj.reAggregationFilterOperation( createdDate, description );
				if ( !isReAggregationPresnet )
				{
					reaggActionObj.clickNewAction( clientPartition );
					newReAggregationRequest();
				}
				else
					Log4jHelper.logInfo( "Re-Aggregation request is already presnet for " + description );
			}
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

	/*
	 * This method is to create new Re-Aggregation 
	 */
	protected void newReAggregationRequest() throws Exception
	{
		reaggDetailObj.basicDetails( fromDate, toDate, description );
		aggregationProcessor();
		addBillProfileBillPeriod();
		reaggDetailObj.saveReAggregationRequest();
		Log4jHelper.logInfo( "Re-Aggregation Request is created successfully :" + description );

	}

	/*
	 * this method is to select aggregation Processor
	 */
	public void aggregationProcessor() throws Exception
	{
		String[] aggrgProcessorArr = aggregationProcessor.split( regex, -1 );
		String[] includeArr = include.split( regex, -1 );
		String[] autoDeleteArr = autoDelete.split( regex, -1 );
		String[] eventTypeArr = eventType.split( regex, -1 );
		String[] eventIncludeArr = eventInclude.split( regex, -1 );
		String[] eventGridValueArr = eventGridValue.split( regex, -1 );
		for ( int row = 0; row < aggrgProcessorArr.length; row++ )
		{
			reaggDetailObj.selectAggregationProcessor( row, aggrgProcessorArr[row], includeArr[row], autoDeleteArr[row], eventTypeArr[row], eventIncludeArr[row], eventGridColHeaders, eventGridValueArr[row] );

		}
	}

	/*
	 * This method is to add bill  profile and bill period
	 */
	public void addBillProfileBillPeriod() throws Exception
	{
		String[] billProfileArr = billProfile.split( regex, -1 );
		String[] billPeriodArr = billPeriod.split( regex, -1 );
		String[] accountArr = account.split( regex, -1 );

		if ( !billProfile.isEmpty() )
			for ( int i = 0; i < billProfileArr.length; i++ )
			{
				reaggDetailObj.billProfileGridConfig( accountArr[i], billProfileArr[i], billPeriodArr[i], billPeriodGridProfileVal, billPeriodGridPeriodVal );
				reaggDetailObj.billPeriodGridConfig( accountArr[i], billProfileArr[i], billPeriodArr[i], billPeriodGridProfileVal, billPeriodGridPeriodVal );
			}
	}

	/*
	 * this method is to edit the reAggregation request
	 */
	public void editReAggregationRequest() throws Exception
	{
		NavigationHelper.navigateToScreen( "Re-Aggregation Request" );
		for ( paramVal = 0; paramVal < colSize; paramVal++ )
		{
			reAggrMap = excelHolderObj.dataMap( paramVal );

			initializeVariables( reAggrMap );

			boolean isReAggregation = reaggrSearchObj.reAggregationFilterOperation( createdDate, description );
			if ( isReAggregation )
			{
				GridHelper.clickRow( "SearchGrid", description, "Description" );
				reaggActionObj.clickEditAction();
				newReAggregationRequest();
			}
			else
				Log4jHelper.logInfo( "Re-Aggregation request is not available for " + description );
		}

	}

	/*
	 * This method is to schedule reaggreagtion request
	 */
	public void scheduleReAggregationRequest() throws Exception
	{

		NavigationHelper.navigateToScreen( "Re-Aggregation Request" );
		for ( paramVal = 0; paramVal < colSize; paramVal++ )
		{
			reAggrMap = excelHolderObj.dataMap( paramVal );
			createdDate = ExcelHolder.getKey( reAggrMap, "Created Date" );
			description = ExcelHolder.getKey( reAggrMap, "Description" );
			boolean isReAggregation = reaggrSearchObj.reAggregationFilterOperation( createdDate, description );
			if ( isReAggregation )
			{
				reaggDetailObj.scheduleActionDetail( description );
				Log4jHelper.logInfo( "Re-Aggregation request schedule action is successfully for : " + description );
			}
			else
				Log4jHelper.logInfo( "Re-Aggregation request is not available for : " + description );
		}
	}

	/*
	 * This method is to validate search screen columns
	 */
	public void searchScreenColumnsValidation() throws Exception
	{		
		NavigationHelper.navigateToScreen( "Re-Aggregation Request" );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		for ( paramVal = 0; paramVal < colSize; paramVal++ )
		{
			reAggrMap = excelHolderObj.dataMap( paramVal );
			String searchScreenColumns = ExcelHolder.getKey( reAggrMap, "SearchScreenColumns" );
			ArrayList<String> excelColumnNames = new ArrayList<String>();
			PSStringUtils strObj = new PSStringUtils();
			String[] searchGridColumnsArr = strObj.stringSplitFirstLevel( searchScreenColumns );
			for ( int col = 0; col < searchGridColumnsArr.length; col++ )
			{
				excelColumnNames.add( searchGridColumnsArr[col] );
			}
			genericHelperObj.totalColumns( excelColumnNames );
		}

	}
	/*
	 * This method is to initialize instance variables
	 */
	protected void initializeVariables( Map<String, String> map ) throws Exception
	{

		clientPartition = ExcelHolder.getKey( map, "Partition" );
		billProfile = ExcelHolder.getKey( map, "Bill Profile" );
		createdDate = ExcelHolder.getKey( map, "Created Date" );
		aggregationProcessor = ExcelHolder.getKey( map, "Aggregation Processor" );
		fromDate = ExcelHolder.getKey( map, "From Date" );
		toDate = ExcelHolder.getKey( map, "To Date" );
		description = ExcelHolder.getKey( map, "Description" );
		billPeriod = ExcelHolder.getKey( map, "Bill Period" );
		include = ExcelHolder.getKey( map, "Include" );
		autoDelete = ExcelHolder.getKey( map, "Auto Delete" );
		eventGridColHeaders = ExcelHolder.getKey( map, "EventGridColHeaders" );
		eventGridValue = ExcelHolder.getKey( map, "EventGridValue" );
		billPeriodGridProfileVal = ExcelHolder.getKey( map, "BillPeriodGridProfileVal" );
		billPeriodGridPeriodVal = ExcelHolder.getKey( map, "BillPeriodGridPeriodVal" );
		account = ExcelHolder.getKey( map, "Account" );
		eventType = ExcelHolder.getKey( map, "Event Type" );
		eventInclude = ExcelHolder.getKey( map, "Event Include" );
	}
}
