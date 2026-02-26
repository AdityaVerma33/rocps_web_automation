package com.subex.rocps.automation.helpers.application.bills;

import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Map;

import com.subex.rocps.automation.helpers.application.bills.reraterequest.RerateRequestDetailImpl;
import com.subex.rocps.automation.helpers.application.bills.reraterequest.RerateRequestSearchImpl;
import com.subex.rocps.automation.helpers.application.filters.GridFilterSearchHelper;
import com.subex.rocps.automation.helpers.application.filters.PSSearchGridHelper;
import com.subex.rocps.automation.helpers.application.genericHelpers.DataVerificationHelper;
import com.subex.rocps.automation.helpers.application.genericHelpers.PSGenericHelper;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;
import com.subex.rocps.automation.utils.ExcelHolder;
import com.subex.rocps.automation.utils.PSStringUtils;

import com.subex.automation.helpers.application.NavigationHelper;
import com.subex.automation.helpers.component.ButtonHelper;
import com.subex.automation.helpers.component.ComboBoxHelper;
import com.subex.automation.helpers.component.ElementHelper;
import com.subex.automation.helpers.component.GenericHelper;
import com.subex.automation.helpers.component.GridHelper;
import com.subex.automation.helpers.component.PopupHelper;
import com.subex.automation.helpers.component.SearchGridHelper;
import com.subex.automation.helpers.component.TextBoxHelper;
import com.subex.automation.helpers.data.ValidationHelper;
import com.subex.automation.helpers.file.ExcelReader;
import com.subex.automation.helpers.report.Log4jHelper;
import com.subex.automation.helpers.util.FailureHelper;

public class RerateRequest extends PSAcceptanceTest
{
	protected ExcelReader excelData = null;
	protected Map<String, ArrayList<String>> rerateExcel = null;
	protected Map<String, String> rerateMap = null;
	protected ExcelHolder excelHolderObj = null;
	protected String path;
	protected String workBookName;
	protected String sheetName;
	protected String testCaseName;
	protected int paramVal;
	protected int colSize;
	protected String partition;
	protected String rerateType;
	protected String summaryOnly;
	protected String stream;
	protected String considerAdjustment;
	protected String keepSameBillPeriod;
	protected String eventType;
	protected String chargeLegsToReRate;
	protected String aggregationProcessor;
	protected String trafficPeriodFrom;
	protected String trafficPeriodTo;
	protected String billProfile;
	protected String billPeriod;
	protected String tariffs;
	protected String bands;
	protected String entities;
	protected String values;
	protected String schedulingName;
	protected String schedulingDesc;
	protected String colHeaders;
	protected Map<String, ArrayList<String>> excelReaderMapObj = null;
	protected Map<String, String> mapObj = null;
	String regex = new PSStringUtils().regexFirstLevelDelimeter();
	protected PSGenericHelper genericHelperObj = new PSGenericHelper();
	DataVerificationHelper dataVerifyObj = new DataVerificationHelper();

	/*
	 * Constructor for initializing excel Identifying the column size from the
	 * map passed
	 */
	public RerateRequest( String path, String workBookName, String sheetName, String testCaseName ) throws Exception
	{
		this.path = path;
		this.workBookName = workBookName;
		this.sheetName = sheetName;
		this.testCaseName = testCaseName;
		excelData = new ExcelReader();
		rerateExcel = excelData.readDataByColumn( this.path, this.workBookName, this.sheetName, this.testCaseName );
		excelHolderObj = new ExcelHolder( rerateExcel );
		colSize = excelHolderObj.totalColumns();
	}

	/*
	 * Overloaded constructor for reading data from excel if test case name
	 * appears more than once
	 * 
	 * @Param occurance : Test case instance in excel sheet Constructor for
	 * initializing excel Identifying the column size from the map
	 */
	public RerateRequest( String path, String workBookName, String sheetName, String testCaseName, int occurance ) throws Exception
	{
		this.path = path;
		this.workBookName = workBookName;
		this.sheetName = sheetName;
		this.testCaseName = testCaseName;
		excelData = new ExcelReader();
		rerateExcel = excelData.readDataByColumn( this.path, this.workBookName, this.sheetName, this.testCaseName, occurance );
		excelHolderObj = new ExcelHolder( rerateExcel );
		colSize = excelHolderObj.totalColumns();
	}

	/*
	 * Configuring the rerateRequest
	 * 
	 */
	public void rerateRequestCreation() throws Exception
	{
		try
		{
			NavigationHelper.navigateToScreen( "Rerate Request" );
			for ( paramVal = 0; paramVal < colSize; paramVal++ )
			{
				rerateMap = excelHolderObj.dataMap( paramVal );
				initialiseVariables( rerateMap );
				if ( !isReratePresent() )
					createRerateRequest();
			}
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

	public boolean isReratePresent() throws Exception
	{
		PSSearchGridHelper psObj = new PSSearchGridHelper();
		ButtonHelper.click( "ClearButton" );
		GenericHelper.waitForLoadmask();
		String headerElement = "//*[@id='searchGrid']//th//div[contains(text(),'Name')]";
		if ( !ElementHelper.isElementPresent( headerElement ) )
			ElementHelper.waitForElement( headerElement, searchScreenWaitSec );
		//PSSearchGridHelper.gridFilterSearchWithTextBox( "PS_Detail_rerate_name_txtID", name, "Name" );
		SearchGridHelper.gridFilterSearchWithTextBox( "PS_Detail_rerate_name_txtID", schedulingName, "Name" );
		/*if ( !billProfile.isEmpty() )
		{
			ButtonHelper.click( "//div[text()='Advanced Search']" );
			String headerElement1 = "//*[@id='searchGrid']//th//div[contains(text(),'Bill Profile Name')]";
			if ( !ElementHelper.isElementPresent( headerElement1 ) )
				ElementHelper.waitForElement( headerElement1, searchScreenWaitSec );
			SearchGridHelper.gridFilterSearchWithTextBox( "popupWindow", "pbipName", billProfile, "Bill Profile Name" );
		}*/

		GridFilterSearchHelper gridObj = new GridFilterSearchHelper();
		gridObj.calender( "prrqCreatedDttm", "Created Date" );
        genericHelperObj.waitforHeaderElement("Status");
		return GridHelper.isValuePresent( "SearchGrid", schedulingName, "Name" );

	}

	public void createRerateRequest() throws Exception
	{
		int initalrow = GridHelper.getRowCount( "SearchGrid" );
		RerateRequestDetailImpl detailObj = new RerateRequestDetailImpl( rerateMap );
		detailObj.newRerateRequest();
		detailObj.rerateOptions();
		detailObj.reRateFiltersTabConfig();
		detailObj.advancedFiltersTabConfig();
		detailObj.schedulingConfig();
		detailObj.saveRerateRequest();
		ButtonHelper.click( "ClearButton" );
		GenericHelper.waitForLoadmask();
		ButtonHelper.click( "SearchButton" );
		GenericHelper.waitForLoadmask();
		int finalRow = GridHelper.getRowCount( "SearchGrid" );
		if ( finalRow > initalrow )
			Log4jHelper.logInfo( "Rerate request is created successfully" );
		else
			FailureHelper.failTest( "Rerate request is not created successfully" );

	}

	/*
	 * This method is for search screen column validation
	 */
	public void searchScreenColumnsValidation() throws Exception
	{
		NavigationHelper.navigateToScreen( "Rerate Request" );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		for ( paramVal = 0; paramVal < colSize; paramVal++ )
		{
			rerateMap = excelHolderObj.dataMap( paramVal );
			String searchScreenColumns = ExcelHolder.getKey( rerateMap, "SearchScreenColumns" );
			ArrayList<String> excelColumnNames = new ArrayList<String>();
			String[] searchGridColumnsArr = searchScreenColumns.split( regex, -1 );
			for ( int col = 0; col < searchGridColumnsArr.length; col++ )
			{
				excelColumnNames.add( searchGridColumnsArr[col] );
			}
			genericHelperObj.totalColumns( excelColumnNames );
		}

	}

	public void bandElementModification() throws Exception
	{
		NavigationHelper.navigateToScreen( "Bands" );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		for ( paramVal = 0; paramVal < colSize; paramVal++ )
		{
			rerateMap = excelHolderObj.dataMap( paramVal );
			String bandName = ExcelHolder.getKey( rerateMap, "BandName" );
			String elements = ExcelHolder.getKey( rerateMap, "To Element" );
			String[] elementsArr = elements.split( regex, -1 );
			boolean isBandPresent = genericHelperObj.isGridTextValuePresent( "Bands_Name", bandName, "Name" );
			if ( isBandPresent )
			{
				NavigationHelper.navigateToEdit( "SearchGrid", 1 );
				assertEquals( NavigationHelper.getScreenTitle(), "Edit Band" );
				removeElementConnection( bandName, elementsArr );

			}

		}

	}

	private void removeElementConnection( String bandName, String[] toElements ) throws Exception
	{
		try
		{
			if ( ValidationHelper.isNotEmpty( toElements ) )
			{
				int length = toElements.length;

				for ( int i = 0; i < length; i++ )
				{
					TextBoxHelper.type( "Bands_ElementConnection_ToElementName", toElements[i] );
					ButtonHelper.click( "searchButton" );
					GenericHelper.waitForLoadmask( searchScreenWaitSec );
					boolean isPresent = GridHelper.isValuePresent( "Bands_ElementConnection_Grid", toElements[i], "To Element Name" );

					if ( isPresent )
					{
						int row = GridHelper.getRowNumber( "Bands_ElementConnection_Grid", toElements[i] );
						GridHelper.clickRow( "Bands_ElementConnection_Grid", row, "To Element Name" );
						ButtonHelper.click( "elementConnectionsToolBar.delete" );
						GenericHelper.waitForLoadmask( searchScreenWaitSec );
						if ( PopupHelper.isTextPresent( "window-scroll-panel", "Are you sure you want to permanently delete the selected element connection(s)?" ) )
							ButtonHelper.click( "YesButton" );
						GenericHelper.waitForLoadmask( searchScreenWaitSec );
						assertFalse( GridHelper.isValuePresent( "Bands_ElementConnection_Grid", toElements[i], "To Element Name" ) );
						ButtonHelper.click( "SaveButton" );
						GenericHelper.waitForLoadmask( searchScreenWaitSec );
						GenericHelper.waitForElementToDisappear( "SaveButton", detailScreenWaitSec );
						GenericHelper.waitForLoadmask( detailScreenWaitSec );

						ButtonHelper.click( "SearchButton" );
						GenericHelper.waitForLoadmask( searchScreenWaitSec );
						Log4jHelper.logInfo( "Element has been removed from band :" + bandName );

					}
					else
						ButtonHelper.click( "CancelButton" );
					GenericHelper.waitForLoadmask( searchScreenWaitSec );
				}
			}
			else
			{
				FailureHelper.failTest( "Please specify To Elements for Band '" + bandName + "'. Elements are mandatory for Band" );
			}
		}
		catch ( AssertionError e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

	/*
	 * This method is for search screen column validation
	 */
	public void saveASRerateRequest() throws Exception
	{
		NavigationHelper.navigateToScreen( "Rerate Request" );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		for ( paramVal = 0; paramVal < colSize; paramVal++ )
		{
			rerateMap = excelHolderObj.dataMap( paramVal );
			initialiseVariables( rerateMap );
			int initalrow = GridHelper.getRowCount( "SearchGrid" );
			if ( isReratePresent() )
			{

				GridHelper.clickRow( "SearchGrid", 1, "Name" );
				NavigationHelper.navigateToAction( "Common Tasks", "Save As" );
				RerateRequestDetailImpl detailObj = new RerateRequestDetailImpl( rerateMap );
				assertEquals( ComboBoxHelper.getValue( "rrqType_gwt_uid_" ), rerateType );

				assertEquals( ComboBoxHelper.getValue( "stream_gwt_uid_" ), stream );

				detailObj.saveRerateRequest();
				ButtonHelper.click( "ClearButton" );
				GenericHelper.waitForLoadmask();
				ButtonHelper.click( "SearchButton" );
				GenericHelper.waitForLoadmask();
				int finalRow = GridHelper.getRowCount( "SearchGrid" );
				if ( finalRow > initalrow )
					Log4jHelper.logInfo( "Rerate request is created successfully" );
				Log4jHelper.logInfo( "Carrier Invoice Template save as action is performed for " + rerateType );
			}
			else
				Log4jHelper.logInfo( "Carrier Invoice Template " );
		}

	}

	/*
	 * This method is for search screen column validation
	 */
	public void scheduleRerateRequest() throws Exception
	{
		NavigationHelper.navigateToScreen( "Rerate Request" );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		for ( paramVal = 0; paramVal < colSize; paramVal++ )
		{
			rerateMap = excelHolderObj.dataMap( paramVal );
			initialiseVariables( rerateMap );
			String taskmsg = "Rerate Task scheduled for selected Rerate Request";
			if ( isReratePresent() )
			{
				GridHelper.clickRow( "SearchGrid", 1, "Name" );
				assertEquals( GridHelper.getCellValue( "SearchGrid", 1, "Status" ), " Unscheduled" );
				NavigationHelper.navigateToAction( "Rerate Request Actions", "Schedule Request" );

				if ( PopupHelper.isPresent( "window-scroll-panel" ) )
				{
					assertTrue( PopupHelper.isTextPresent( "window-scroll-panel", taskmsg ) );
					ButtonHelper.click( "OKButton" );
					GenericHelper.waitForLoadmask( searchScreenWaitSec );
				}
				//verifyRerateTaskStatus();
			}
		}

	}

	public void verifyRerateTaskStatus() throws Exception
	{
		assertTrue( isReratePresent() );
		GridHelper.clickRow( "SearchGrid", 1, "Name" );
		RerateRequestSearchImpl searchObj = new RerateRequestSearchImpl();
		searchObj.checkTaskStatus();
		assertEquals( GridHelper.getCellValue( "SearchGrid", 1, "Status" ), " Completed" );
		Log4jHelper.logInfo( "Rerate task is successfull" );
	}

	/*
	 * This method is for initialise instance variables 
	 */
	public void initialiseVariables( Map<String, String> map ) throws Exception
	{

		partition = ExcelHolder.getKey( map, "Partition" );
		rerateType = ExcelHolder.getKey( map, "RerateType" );
		schedulingName = ExcelHolder.getKey( map, "SchedulingName" );
		billProfile = ExcelHolder.getKey( map, "BillProfile" );
		stream = ExcelHolder.getKey( map, "Stream" );
	}

}
