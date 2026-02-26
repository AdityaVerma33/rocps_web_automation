package com.subex.rocps.automation.helpers.application.reaggregation.reaggregationrequest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import com.subex.rocps.automation.helpers.application.filters.GridFilterSearchHelper;
import com.subex.rocps.automation.helpers.application.genericHelpers.DataVerificationHelper;
import com.subex.rocps.automation.helpers.application.genericHelpers.PSGenericHelper;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;
import com.subex.rocps.automation.utils.PSStringUtils;

import com.subex.automation.helpers.application.NavigationHelper;
import com.subex.automation.helpers.component.ButtonHelper;
import com.subex.automation.helpers.component.CheckBoxHelper;
import com.subex.automation.helpers.component.GenericHelper;
import com.subex.automation.helpers.component.GridHelper;
import com.subex.automation.helpers.component.PopupHelper;
import com.subex.automation.helpers.component.SearchGridHelper;
import com.subex.automation.helpers.component.TextBoxHelper;
import com.subex.automation.helpers.data.ValidationHelper;
import com.subex.automation.helpers.report.Log4jHelper;

public class ReAggregationRequestDetailImpl extends PSAcceptanceTest
{
	GridFilterSearchHelper gridHelperObj = new GridFilterSearchHelper();
	ReAggregationRequestActionImpl reaggActionObj = new ReAggregationRequestActionImpl();
	DataVerificationHelper dataVerifyObj = new DataVerificationHelper();
	PSGenericHelper genericHelperObj = new PSGenericHelper();
	PSStringUtils psStringObj = new PSStringUtils();
	ReAggregationRequestSearchImpl reaggrSearchObj = new ReAggregationRequestSearchImpl();

	Map<String, String> billProfileMap = new HashMap<String, String>();

	/*
	 * This method is for basic details
	 */
	public void basicDetails( String fromDate, String toDate, String description ) throws Exception
	{
		TextBoxHelper.type( "PS_Detail_reaggregation_fromDate_TxtID", fromDate );
		TextBoxHelper.type( "PS_Detail_reaggregation_toDate_TxtID", toDate );
		TextBoxHelper.type( "PS_Detail_reaggregation_description_TxtID", description );
	}

	/*
	 * this method is to select aggregation processor detail
	 */
	public void selectAggregationProcessor( int row, String aggrepationProcessor, String include, String autoDelete, String eventType, String eventInclude, String eventGridColHeaders, String eventGridValue ) throws Exception
	{
		row = GridHelper.getRowNumber( "PS_Detail_reaggregation_aggregationprocessor_gridID", aggrepationProcessor, "Aggregation Processors" );
		GridHelper.clickRow( "PS_Detail_reaggregation_aggregationprocessor_gridID", row, "Aggregation Processors" );
		if ( ValidationHelper.isTrue( include ) )
			GridHelper.clickRow( "PS_Detail_reaggregation_aggregationprocessor_gridID", row, "Include" );
		if ( ValidationHelper.isFalse( autoDelete ) )
			GridHelper.clickRow( "PS_Detail_reaggregation_aggregationprocessor_gridID", row, "Auto Delete" );

		String[] eventTypeAr = eventType.split( secondLevelDelimiter );
		String[] eventIncludeAr = eventInclude.split( secondLevelDelimiter );
		for ( int i = 0; i < eventTypeAr.length; i++ )
		{
			GridHelper.clickRow( "PS_Detail_reaggregation_eventtype_gridID", i + 1, "Event Type" );
			eventTypeSetGridcolValue( eventIncludeAr[i], eventGridColHeaders, eventGridValue, i );
		}

	}

	/*
	 * This method is to set eventtypegrid colvalues
	 */
	public void eventTypeSetGridcolValue( String eventIncludeAr, String eventGridColHeaders, String eveGridValue, int row ) throws Exception
	{
		String regex = new PSStringUtils().regexThirdLevelDelimeter();
		String[] eventIncludeFinal = eventIncludeAr.split( regex, -1 );
		String[] eveGridValueArr = eveGridValue.split( secondLevelDelimiter );
		for ( int j = 0; j < eventIncludeFinal.length; j++ )
		{
			if ( ValidationHelper.isFalse( eventIncludeFinal[j] ) )
			{
				if ( CheckBoxHelper.isChecked( "PS_Detail_reaggregation_eventInclude_CheckBx" ) )
					CheckBoxHelper.uncheck( "PS_Detail_reaggregation_eventInclude_CheckBx" );
				assertTrue( CheckBoxHelper.isNotChecked( "PS_Detail_reaggregation_eventInclude_CheckBx" ), "checkbox is checked" );
			}
			else
				assertTrue( CheckBoxHelper.isChecked( "PS_Detail_reaggregation_eventInclude_CheckBx" ), "checkbox is unchecked" );

			if ( !eveGridValue.isEmpty() )
				eventTypeValidation( eventGridColHeaders, eveGridValueArr[row], row + 1 );
		}
	}

	/*
	 * This method is to validate the eventtype
	 */
	public void eventTypeValidation( String eventGridColHeaders, String eventGridValue, int row ) throws Exception
	{
		String regex = new PSStringUtils().regexThirdLevelDelimeter();
		String regex1 = new PSStringUtils().regexFirstLevelDelimeter();
		String[] eventGridValueAr = eventGridValue.split( regex, -1 );
		String[] eventGridColHeadersArr = eventGridColHeaders.split( regex1, -1 );
		ArrayList<String> eventTypeActualVal = new ArrayList<String>();

		String eventActual = GridHelper.getCellValue( "PS_Detail_reaggregation_eventtype_gridID", row, eventGridColHeadersArr[0] );
		String eventIncludeActual = GridHelper.getCellValue( "PS_Detail_reaggregation_eventtype_gridID", row, eventGridColHeadersArr[1] );

		eventTypeActualVal.add( eventActual );
		eventTypeActualVal.add( eventIncludeActual );
		ArrayList<String> excelValList = new ArrayList<String>();
		String eventType = eventGridValueAr[0];
		String include = eventGridValueAr[1];
		excelValList.add( eventType );
		excelValList.add( include );
		String actualValue = psStringObj.stringformation( eventTypeActualVal );
		String expectedValue = psStringObj.stringformation( excelValList );

		Log4jHelper.logInfo( "Event Type Grid Actual value : " + actualValue );
		Log4jHelper.logInfo( "Event Type Grid Expected value : " + expectedValue );
		assertEquals( eventTypeActualVal, excelValList, "Values are not matching" );

	}

	/*
	 * This method is to configire the billprofile grid
	 */
	public void billProfileGridConfig( String accountArr, String billProfileArr, String billPeriodArr, String billPeriodGridProfileVal, String billPeriodVal ) throws Exception
	{
		String[] billProfileAr = billProfileArr.split( secondLevelDelimiter );
		String[] accountAr = accountArr.split( secondLevelDelimiter );

		if ( !billProfileArr.isEmpty() && billPeriodArr.isEmpty() )
		{
			for ( int index = 0; index < billProfileAr.length; index++ )
			{
				reaggActionObj.addBillProfile();
				billProfileGridProfileSelection( accountAr[index], billProfileAr[index] );
				GenericHelper.waitForLoadmask( searchScreenWaitSec );
				String billProfileActual = billProfileMap.get( billProfileAr[index] );
				assertTrue( GridHelper.isValuePresent( "PS_Detail_reaggregation_billProfile_gridID", billProfileActual, "Profile" ), "Bill Profile not fount" );

			}
			if(!billPeriodGridProfileVal.isEmpty())
				billPeriodGridValidation( billPeriodGridProfileVal, billPeriodVal, billProfileMap );
		}
	}
	/*
	 * This method is to config the bill period grid
	 */

	public void billPeriodGridConfig( String accountArr, String billProfileArr, String billPeriodArr, String billPeriodGridProfileVal, String billPeriodVal ) throws Exception
	{
		String[] billProfileAr = billProfileArr.split( secondLevelDelimiter );
		String[] billPeriodAr = billPeriodArr.split( secondLevelDelimiter );
		String[] accountAr = accountArr.split( secondLevelDelimiter );

		if ( !billPeriodArr.isEmpty() && !billProfileArr.isEmpty() )
		{
			for ( int indexVal = 0; indexVal < billPeriodAr.length; indexVal++ )
			{
				billPeriodSelection( accountAr[indexVal], billPeriodAr[indexVal], billProfileAr[indexVal] );
			}
			billPeriodGridValidation( billPeriodGridProfileVal, billPeriodVal, billProfileMap );
		}
	}

	/*
	 * This method is for bill period selection
	 */
	public void billPeriodSelection( String accountAr, String billPeriodAr, String billProfileAr ) throws Exception
	{
		String regex = new PSStringUtils().regexThirdLevelDelimeter();
		String[] billPeriodFinalArr = billPeriodAr.split( regex, -1 );

		if ( ValidationHelper.isNotEmpty( billPeriodFinalArr ) )
		{
			for ( int i = 0; i < billPeriodFinalArr.length; i++ )
			{
				reaggActionObj.addBillPeriod();
				billPeriodSearch( accountAr, billProfileAr, billPeriodFinalArr[i] );
				GridHelper.clickRow( "SearchGrid", 1, 1 );
				String billProfileVal = GridHelper.getCellValue( "SearchGrid", 1, "Bill Profile" );
				GenericHelper.waitForLoadmask(searchScreenWaitSec);
				billProfileMap.put( billProfileAr, billProfileVal );
				ButtonHelper.click( "OKButton" );
				GenericHelper.waitForLoadmask(searchScreenWaitSec);
			}

		}

	}

	/*
	 * this method is for billprofilegrid profile selection
	 */
	public void billProfileGridProfileSelection( String account, String billProfile ) throws Exception
	{
		if ( !account.isEmpty() )
			gridHelperObj.accountFilter( "grid_column_header_filtersearchGrid_account$paccName", "account", account, "Account" );
		SearchGridHelper.gridFilterSearchWithTextBox( "pbipName", billProfile, "Bill Profile Name" );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		String billProfileActual = GridHelper.getCellValue( "SearchGrid", 1, "Bill Profile" );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		billProfileMap.put( billProfile, billProfileActual );
		GridHelper.clickRow( "SearchGrid", 1, 1 );
		ButtonHelper.click( "OKButton" );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
	}

	/*
	 * This method is to get the bill period value
	 */
	private ArrayList<String> getBillPeriodValue( String billPeriodProfileVal, String billPeriodVal, int row ) throws Exception
	{

		ArrayList<String> value = new ArrayList<String>();
		String[] colHeaders =
		{ "Profile", "Bill Period" };

		for ( int col = 0; col < colHeaders.length; col++ )
		{
			String cellValue = GridHelper.getCellValue( "billPeriodGrid", row, colHeaders[col] );
			value.add( cellValue );
		}
		return value;

	}

	/*
	 * thi smethod is to bill period grid validation
	 */
	public void billPeriodGridValidation( String billPeriodGridProfileVal, String billPeriodVal, Map<String, String> map ) throws Exception
	{
		String regex = new PSStringUtils().regexFirstLevelDelimeter();
		String[] billPeriodGridProfileValArr = billPeriodGridProfileVal.split( regex, -1 );
		String[] billPeriodValArr = billPeriodVal.split( regex, -1 );

		int billProfileExcelValCount = billPeriodGridProfileValArr.length;
		int billPeriodExcelValCount = billPeriodValArr.length;

		int rowCount = GridHelper.getRowCount( "billPeriodGrid" );
		if ( billProfileExcelValCount != billPeriodExcelValCount )
			throw new RuntimeException( "Cell values are not matching bill Profile and bill Period" + billProfileExcelValCount );

		if ( rowCount == billProfileExcelValCount )
		{
			for ( int row = 0; row < billPeriodGridProfileValArr.length; row++ )
			{

				ArrayList<String> actualVal = getBillPeriodValue( billPeriodGridProfileValArr[row], billPeriodValArr[row], row + 1 );
				String billProfileActual = actualVal.get( 0 );
				String billPeriodActual = actualVal.get( 1 );
				String billprofileExcel = billPeriodGridProfileValArr[row];
				String billperiodExcel = billPeriodValArr[row];

				ArrayList<String> expectedVal = new ArrayList<String>();
				expectedVal.add( billprofileExcel );
				expectedVal.add( billperiodExcel );
				String billProfileMapVal = map.get( billprofileExcel );
				Log4jHelper.logInfo( "Bill profile Excel value : " + billprofileExcel + "Bill Profile map Value : " + billProfileMapVal );

				assertEquals( billProfileActual, billProfileMapVal, "Bill Profile's are not matching" );
				assertEquals( billPeriodActual, billperiodExcel, "Bill Period values are not matching" );

				String actualValue = psStringObj.stringformation( actualVal );
				String expectedValue = psStringObj.stringformation( expectedVal );
				Log4jHelper.logInfo( "Bill Period Grid Actual value : " + actualValue );
				Log4jHelper.logInfo( "Bill Period Grid Expected value : " + expectedValue );
			}
		}
	}

	/*
	 * Thios method is to save reaggregation result
	 */
	public void saveReAggregationRequest() throws Exception
	{
		ButtonHelper.click( "reaggregationRequestDetail.save" );
		GenericHelper.waitForLoadmask(searchScreenWaitSec);
	}

	/*
	 * This method is to schedule action
	 */
	public void scheduleActionDetail( String description ) throws Exception
	{
		String scheduleMsg = "If there are any partial aggregated files for the selected processors , on scheduling it would result in duplicate data. Do you still wish to proceed?";
		String taskMsg = "Task successfully scheduled.";
		GridHelper.clickRow( "SearchGrid", description, "Description" );
		GenericHelper.waitForLoadmask(searchScreenWaitSec);
		reaggActionObj.scheduleReAggregation();
		assertTrue( isPopupPresnet( "Confirm", scheduleMsg ) );
		ButtonHelper.click( "YesButton" );
		GenericHelper.waitForLoadmask();
		assertTrue( isPopupPresnet( "Information", taskMsg ) );
		ButtonHelper.click( "OK_TRT_Button" );
		GenericHelper.waitForLoadmask(searchScreenWaitSec);

	}

	private boolean isPopupPresnet( String title, String popupMsg ) throws Exception
	{
		assertEquals( NavigationHelper.getScreenTitle(), title );
		return PopupHelper.isTextPresent( "window-scroll-panel", popupMsg );
	}

	public void billPeriodSearch( String account, String billProfileVal, String billPeriodVal ) throws Exception
	{
		if ( !account.isEmpty() )
			gridHelperObj.accountFilter( "grid_column_header_filtersearchGrid_billProfile$account$paccName", "billProfile$account", account, "Account" );
		gridHelperObj.billProfileAdvanceFilter( "searchGrid", "Bill Profile", billProfileVal );
		genericHelperObj.setDate("dummyValidOnDataProperty", billPeriodVal );
		ButtonHelper.click( "SearchButton" );
	}
}
