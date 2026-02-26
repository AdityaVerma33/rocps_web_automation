package com.subex.rocps.automation.helpers.application.partnerConfiguration.eventUsg;

import java.util.ArrayList;
import java.util.Map;

import com.subex.automation.helpers.application.NavigationHelper;
import com.subex.automation.helpers.component.ButtonHelper;
import com.subex.automation.helpers.component.ComboBoxHelper;
import com.subex.automation.helpers.component.GenericHelper;
import com.subex.automation.helpers.component.PopupHelper;
import com.subex.automation.helpers.component.TextBoxHelper;
import com.subex.automation.helpers.data.ValidationHelper;
import com.subex.automation.helpers.report.Log4jHelper;
import com.subex.rocps.automation.helpers.application.filters.AdvanceSearchFiltersHelper;
import com.subex.rocps.automation.helpers.application.genericHelpers.PSGenericHelper;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;
import com.subex.rocps.automation.utils.ExcelHolder;
import com.subex.rocps.automation.utils.PSStringUtils;

public class EventUsgSeacrhImpl extends PSAcceptanceTest
{

	protected Map<String, String> eventUsgSrchImplMap;
	protected String fromDate;
	protected String toDate;
	protected String streamStage;
	protected String eventType;
	protected String billProfile;
	protected String trend;
	protected String fromHour;
	protected String toHour;
	protected String searchScreencolmHdrs;
	protected String colmHdrsSearchResult;
	protected String mapkeys;
	PSStringUtils psStringUtils = new PSStringUtils();
	PSGenericHelper psGenericHelper = new PSGenericHelper();
	AdvanceSearchFiltersHelper advanceSearchHelpOb = new AdvanceSearchFiltersHelper();

	// Constructor:
	public EventUsgSeacrhImpl( Map<String, String> eventUsgSrchImplMap ) throws Exception
	{

		this.eventUsgSrchImplMap = eventUsgSrchImplMap;
	}

	// Method: Initialize the variables
	public void initializeVariable( Map<String, String> map ) throws Exception
	{

		fromDate = ExcelHolder.getKey( map, "FromDate" );
		toDate = ExcelHolder.getKey( map, "ToDate" );
		streamStage = ExcelHolder.getKey( map, "StreamStage" );
		eventType = ExcelHolder.getKey( map, "EventType" );
		billProfile = ExcelHolder.getKey( map, "BillProfile" );
		trend = ExcelHolder.getKey( map, "Trend" );
		fromHour = ExcelHolder.getKey( map, "FromHour" );
		toHour = ExcelHolder.getKey( map, "ToHour" );

	}

	// Method event usage filter
	public void eventUsgFilter() throws Exception
	{
		initializeVariable( eventUsgSrchImplMap );
		TextBoxHelper.type( "PS_Detail_eventUsg_fromDt_textID", fromDate );
		TextBoxHelper.type( "PS_Detail_eventUsg_toDt_textID", toDate );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		ComboBoxHelper.select( "PS_Detail_eventUsg_streamStg_ComboID", streamStage );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		assertEquals( ComboBoxHelper.getValue( "PS_Detail_eventUsg_eventType_ComboID" ), eventType );
		advanceSearchHelpOb.billProfileAdvanceSearch( "Bill Profile", billProfile );
		ComboBoxHelper.select( "PS_Detail_eventUsg_trend_ComboID", trend );
		if ( trend.equals( "Hourly" ) )
		{
			ComboBoxHelper.select( "PS_Detail_eventUsg_fromHour_ComboID", fromHour );
			ComboBoxHelper.select( "PS_Detail_eventUsg_toHour_ComboID", toHour );
		}
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		ButtonHelper.click( "SearchButton" );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		PopupHelper.waitForPopup( searchScreenWaitSec );
		assertTrue( PopupHelper.isTextPresent( "Loading will be slow as filters are not specific, Do you want to continue?" ), "Popup message is not matched" );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		ButtonHelper.click( "Yes" );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		psGenericHelper.waitforHeaderElement( "A Number" );

	}

	// Method: Verify the column headers of Event Usage
	public void verifyColmnHeaderOfEveErr() throws Exception
	{
		searchScreencolmHdrs = ExcelHolder.getKey( eventUsgSrchImplMap, "SearchScreenColumns" );
		psGenericHelper.screenColumnValidation( "A Number", searchScreencolmHdrs, "Event Usage Search" );

	}

	// Method: : Validate the search results of 'Event Usage' screen
	public void validateSearchResultEventUsg() throws Exception
	{
		psGenericHelper.waitforHeaderElement( "A Number" );
		mapkeys = ExcelHolder.getKey( eventUsgSrchImplMap, "MapRowKeys" );
		colmHdrsSearchResult = ExcelHolder.getKey( eventUsgSrchImplMap, "ColmnHeaders" );
		psGenericHelper.sortColumnHeaderGrid( "PS_Detail_eventUsg_Amount_IconID", "PS_Detail_eventUsg_Amount_DescmenuID" );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		psGenericHelper.waitforHeaderElement( "A Number" );
		psGenericHelper.validateSearchResult( colmHdrsSearchResult, mapkeys, eventUsgSrchImplMap, "PS_Detail_SearchScreen_ColumnHeaderID", "SearchGrid" );
		Log4jHelper.logInfo( "'Event Usage Search' results are validated successfully" );

	}

	public void viewTotalAction() throws Exception
	{
		ButtonHelper.click( "PS_Detail_eventUsg_viewTotal_action_BtnId" );
		psGenericHelper.waitforPopupHeaderElement( "PS_Detail_eventUsg_viewTotal_popupWindowId", "PS_Detail_eventUsg_viewTotal_gridId", "Charge" );
		String mapkeys = ExcelHolder.getKey( eventUsgSrchImplMap, "ViewTotalsMapRowKeys" );
		String colmHdrsSearchResult = ExcelHolder.getKey( eventUsgSrchImplMap, "ViewTotalsColmnHeaders" );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		psGenericHelper.validateSearchResult( colmHdrsSearchResult, mapkeys, eventUsgSrchImplMap, "grid_column_header_totalsGrid_", "PS_Detail_eventUsg_viewTotal_gridId" );
		Log4jHelper.logInfo( "'View Totals' results are validated successfully" );
		ButtonHelper.click( "CloseButton" );
		psGenericHelper.waitforPopupHeaderElementToDisappear( "PS_Detail_eventUsg_viewTotal_popupWindowId", "PS_Detail_eventUsg_viewTotal_gridId", "Charge" );
	}

}
