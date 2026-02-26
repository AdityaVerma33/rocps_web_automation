package com.subex.rocps.automation.helpers.application.accruals.accountingperiod;

import java.util.Map;

import com.subex.rocps.automation.helpers.application.filters.AdvanceSearchFiltersHelper;
import com.subex.rocps.automation.helpers.application.genericHelpers.PSGenericHelper;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;
import com.subex.rocps.automation.utils.ExcelHolder;
import com.subex.rocps.automation.utils.PSStringUtils;

import com.subex.automation.helpers.component.ButtonHelper;
import com.subex.automation.helpers.component.CalendarHelper;
import com.subex.automation.helpers.component.ComboBoxHelper;
import com.subex.automation.helpers.component.ElementHelper;
import com.subex.automation.helpers.component.GenericHelper;
import com.subex.automation.helpers.component.TextBoxHelper;

public class AccountingPeriodSearchImpl extends PSAcceptanceTest
{
	protected String account;	
	protected String eventDate;
	protected String billProfile;
	protected String band;
	protected String tariffRateName;
	protected String tariff;
	
	protected String service;
	
	protected String deal;
	protected String contractNo;
	protected String dealTier;
	protected String dealBandGroup;
	protected String category;
	
	protected String colHeader;
	protected String results;
	protected String eventFromDate;
	protected String eventToDate;
	protected Map<String, String> map;
	PSStringUtils strObj = new PSStringUtils();
	PSGenericHelper genericObj = new PSGenericHelper();
	protected AdvanceSearchFiltersHelper advanceFilterObj = new AdvanceSearchFiltersHelper();
	
	public AccountingPeriodSearchImpl( Map<String, String> map ) throws Exception
	{
		this.map = map;
		initialiseVaribles( map );

	}
	public void filterOperations() throws Exception
	{
		
		genericObj.collapsableXpath();

		//CalendarHelper.setOnDate( "PS_searchPanelId", "bimr$evt_dttm", eventDate );
		setCalendar();
		advanceFilterObj.billProfileAdvanceSearch( "Bill Profile", billProfile );
		if ( !band.isEmpty() )
			advanceFilterObj.bandAdvanceSearch( "Band", band );
		if ( !service.isEmpty() )
			advanceFilterObj.serviceAdvanceSearch( "Service", service );
		if ( !tariff.isEmpty() )
			advanceFilterObj.tariffAdvanceSearch( "Tariff", tariff );
		if ( !tariffRateName.isEmpty() )
			ComboBoxHelper.select( "trn_trn_name_gwt_uid_", tariffRateName );

		
		if ( !deal.isEmpty() )
			advanceFilterObj.dealAdvanceSearch( "Deal", account, contractNo );
		if ( !dealTier.isEmpty() )
			ComboBoxHelper.select( "pdlt_pdlt_from_val__pdlt_pdlt_to_val__pdlt_pdlt_direction_gwt_uid_", dealTier );
		if ( !dealBandGroup.isEmpty() )
			ComboBoxHelper.select( "pdbg_pdbg_name__pdbg_pdbg_direction_gwt_uid_", dealBandGroup );
		
		
		
		ButtonHelper.click( "SearchButton" );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
	}
	
	/*
	 * Method for selecting calendar in merger result screen
	 */
	public void setCalendar() throws Exception
	{

		if ( !eventFromDate.isEmpty() && !eventToDate.isEmpty() )
			CalendarHelper.setDate( "PS_searchPanelId", "PS_calendarButtonId", "Between", eventFromDate, eventToDate );
		else if ( !eventFromDate.isEmpty() )
			CalendarHelper.setOnDate( "PS_searchPanelId", "PS_calendarButtonId", eventFromDate );
		else
			throw new RuntimeException( "From date field cannot be left empty" );
	}
	
	public void initialiseVaribles(Map<String, String> map) throws Exception
	{
		account = ExcelHolder.getKey( map, "Account" );
		//eventDate = ExcelHolder.getKey( map, "EventDate" );
		colHeader = ExcelHolder.getKey( map, "ColHeader" );
		results = ExcelHolder.getKey( map, "Results" );		
		billProfile = ExcelHolder.getKey( map, "BillProfile" );
		band = ExcelHolder.getKey( map, "Band" );
		tariffRateName = ExcelHolder.getKey( map, "TariffRateName" );
		tariff = ExcelHolder.getKey( map, "Tariff" );		
		service = ExcelHolder.getKey( map, "Service" );		
		deal = ExcelHolder.getKey( map, "Deal" );
		contractNo = ExcelHolder.getKey( map, "ContractNo" );
		dealTier = ExcelHolder.getKey( map, "DealTier" );
		dealBandGroup = ExcelHolder.getKey( map, "DealBandGroup" );
		//category = ExcelHolder.getKey( map, "Category" );		
		eventFromDate = ExcelHolder.getKey( map, "EventFromDate" );
		eventToDate = ExcelHolder.getKey( map, "EventToDate" );
	}
}
