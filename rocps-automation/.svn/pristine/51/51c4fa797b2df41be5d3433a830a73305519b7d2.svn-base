package com.subex.rocps.automation.helpers.application.settlements;

import java.util.Map;

import com.subex.rocps.automation.helpers.application.filters.AdvanceSearchFiltersHelper;
import com.subex.rocps.automation.helpers.application.filters.GridFilterSearchHelper;
import com.subex.rocps.automation.helpers.application.genericHelpers.PSGenericHelper;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;
import com.subex.rocps.automation.utils.ExcelHolder;

import com.subex.automation.helpers.component.ButtonHelper;
import com.subex.automation.helpers.component.CalendarHelper;
import com.subex.automation.helpers.component.ComboBoxHelper;
import com.subex.automation.helpers.component.ElementHelper;
import com.subex.automation.helpers.component.GenericHelper;
import com.subex.automation.helpers.component.GridHelper;
import com.subex.automation.helpers.component.SearchGridHelper;
import com.subex.automation.helpers.componentHelpers.SearchHelper;
import com.subex.automation.helpers.data.ValidationHelper;

public class SettlmentSearchImpl extends PSAcceptanceTest
{
	protected Map<String, String> settlementsSearchMap = null;
	protected String settlementType;
	protected String latestVersion;
	protected String billProfile;
	protected String account;
	protected String fromDt;
	protected String toDt;
	protected String status;
	AdvanceSearchFiltersHelper adSearchFiltersHelper = new AdvanceSearchFiltersHelper();
	PSGenericHelper psGenericHelper = new PSGenericHelper();
	SearchHelper searchHelper = new SearchHelper();

	/**
	 * Constructor
	 * @param settlementsSearchMap
	 */
	public SettlmentSearchImpl( Map<String, String> settlementsSearchMap )
	{
		this.settlementsSearchMap = settlementsSearchMap;
	}

	/*
	 * This method is for initialize variable
	 */
	protected void initializevariable( Map<String, String> map ) throws Exception
	{
		settlementType = ExcelHolder.getKey( map, "SettlementType" );
		latestVersion = ExcelHolder.getKey( map, "LatestVersion" );
		billProfile = ExcelHolder.getKey( map, "BillProfile" );
		account = ExcelHolder.getKey( map, "Account" );
		fromDt = ExcelHolder.getKey( map, "From" );
		toDt = ExcelHolder.getKey( map, "To" );
		status = ExcelHolder.getKey( map, "Status" );

	}

	/*
	 * This method is for search Settlements filter
	 */
	public void searchSettlementfilter() throws Exception
	{
		assertTrue( isSettlementPresent(), "Settlement bill profile is not found in search screen " );

	}

	/*
	 * This method is for check settlement present in search screen
	 */
	public boolean isSettlementPresent() throws Exception
	{
		initializevariable( settlementsSearchMap );
		psGenericHelper.waitforHeaderElement( "Account" );
		if ( ValidationHelper.isNotEmpty( settlementType ) )
			ComboBoxHelper.select( "PS_Detail_settlements_settlType_comboID", settlementType );
		if ( ValidationHelper.isNotEmpty( latestVersion ) )
			ComboBoxHelper.select( "PS_Detail_settlements_latestVersion_comboID", latestVersion );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		adSearchFiltersHelper.billProfileAdvanceSearch( "Bill Profile", billProfile );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );

		if ( ValidationHelper.isNotEmpty( account ) )
			GridFilterSearchHelper.gridFilterAdvancedSearch( "PS_Detail_settlements_account_textID", account, "Account" );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		searchHelper.clickFilterIcon( GenericHelper.getORProperty( "PS_Detail_settlements_fromDt_iconID" ) );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		CalendarHelper.setOnDate( "options", fromDt + " 00:00:00" );

		searchHelper.clickFilterIcon( GenericHelper.getORProperty( "PS_Detail_settlements_toDtDt_iconID" ) );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		CalendarHelper.setOnDate( "options", toDt + " 00:00:00" );

		if ( ValidationHelper.isNotEmpty( status ) )
			SearchGridHelper.gridFilterSearchWithComboBox( "PS_Detail_settlements_status_comboID", status, "Status" );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		ButtonHelper.click( "SearchButton" );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		return ( GridHelper.getRowCount( "searchGrid" ) >= 1 );

	}

}
