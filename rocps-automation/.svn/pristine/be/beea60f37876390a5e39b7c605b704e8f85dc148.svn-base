package com.subex.rocps.automation.helpers.application.matchandrate.eventmodellingdefinition;

import java.util.Map;

import com.subex.automation.helpers.application.NavigationHelper;
import com.subex.automation.helpers.component.ButtonHelper;
import com.subex.automation.helpers.component.CheckBoxHelper;
import com.subex.automation.helpers.component.ElementHelper;
import com.subex.automation.helpers.component.GenericHelper;
import com.subex.automation.helpers.component.GridHelper;
import com.subex.automation.helpers.component.LabelHelper;
import com.subex.automation.helpers.component.PopupHelper;
import com.subex.automation.helpers.component.TextBoxHelper;
import com.subex.automation.helpers.data.ValidationHelper;
import com.subex.automation.helpers.util.FailureHelper;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;

public class EventModellingDefinitionDetailImpl extends PSAcceptanceTest
{
	protected Map<String, String> evenModDefnDetailTabMap;
	protected String eventName;
	protected String outputPrifix;
	protected String errorPrifix;
	protected String surchargerating;
	protected String inputEvent;
	protected String normalEvent;
	protected String ratedEvent;
	protected String ratedEventSeg;
	protected String confgMap;

	public EventModellingDefinitionDetailImpl( Map<String, String> evenModDefnDetailTabMap )
	{
		this.evenModDefnDetailTabMap = evenModDefnDetailTabMap;
		initInstVariables();
	}

	//Method for initializing instance variables
	public void initInstVariables()
	{
		eventName = evenModDefnDetailTabMap.get( "Name" );
		outputPrifix = evenModDefnDetailTabMap.get( "OutputPrefix" );
		surchargerating = evenModDefnDetailTabMap.get( "SurchargeRating" );
		errorPrifix = evenModDefnDetailTabMap.get( "ErrorPrefix" );
		inputEvent = evenModDefnDetailTabMap.get( "InputEvent" );
		normalEvent = evenModDefnDetailTabMap.get( "NormalizationEvent" );
		ratedEvent = evenModDefnDetailTabMap.get( "RatedEvent" );
		ratedEventSeg = evenModDefnDetailTabMap.get( "RatedEventSegment" );
		confgMap = evenModDefnDetailTabMap.get( "ConfigureMapping" );
	}

	//Method to configure EventModellingDefination detail
	public void openEventModellingDefinationDetail() throws Exception
	{
		boolean surchargeratingFlag;
		TextBoxHelper.type( "PS_Detail_EveModDefn_name_txtID", eventName );
		TextBoxHelper.type( "PS_Detail_EveModDefn_outputPrfx_txtID", outputPrifix );
		surchargeratingFlag = ValidationHelper.isTrue( surchargerating );
		if ( surchargeratingFlag )
			CheckBoxHelper.check( "PS_Detail_EveModDefn_surchargeRatng_ChckBox" );
		TextBoxHelper.type( "PS_Detail_EveModDefn_errorPrfx_txtID", errorPrifix );
	}

	// Method of Provide the Table Definition
	public void provideTableDefinationDetail() throws Exception
	{
		inputEventTableDefn();
		normEventTableDefn();
		ratedEventTableDefn();
		ratedEventSegTableDefn();
	}

	protected void inputEventTableDefn() throws Exception
	{

		TableDefinationSearchImpl tableDefnSearchimpl = new TableDefinationSearchImpl();
		GridHelper.clickRow( "PS_Detail_EveModDefn_eventmoddefnDetail_GridId", "Input Event", "Type" );
		int row = GridHelper.getRowNumber( "PS_Detail_EveModDefn_eventmoddefnDetail_GridId", "Input Event", "Type" );
		GridHelper.clickRow( "PS_Detail_EveModDefn_eventmoddefnDetail_GridId", row, " Table Definition" );
		GenericHelper.waitForLoadmask();
		ButtonHelper.click( "PS_Detail_EveModDefn_tableDefnentsearch_BtnId" );
		GenericHelper.waitForLoadmask( detailScreenWaitSec );
		tableDefnSearchimpl.tableDefnEntitySearch( inputEvent );
		tableDefnSearchimpl.clickOkButtonOnTableDefnSearch();
	}

	protected void normEventTableDefn() throws Exception
	{
		try
		{
			if ( ValidationHelper.isNotEmpty( normalEvent ) )
			{
				TableDefinationSearchImpl tableDefnSearchimpl = new TableDefinationSearchImpl();
				int row = GridHelper.getRowNumber( "PS_Detail_EveModDefn_eventmoddefnDetail_GridId", "Normalization Event", "Type" );
				GridHelper.clickRow( "PS_Detail_EveModDefn_eventmoddefnDetail_GridId", row, " Table  Definition" );
				ButtonHelper.click( "PS_Detail_EveModDefn_tableDefnentsearch_BtnId" );
				GenericHelper.waitForLoadmask( detailScreenWaitSec );

				tableDefnSearchimpl.tableDefnEntitySearch( normalEvent );
				tableDefnSearchimpl.clickOkButtonOnTableDefnSearch();
			}
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
		}

	}

	protected void ratedEventTableDefn() throws Exception
	{
		TableDefinationSearchImpl tableDefnSearchimpl = new TableDefinationSearchImpl();
		int row = GridHelper.getRowNumber( "PS_Detail_EveModDefn_eventmoddefnDetail_GridId", "Rated Event", "Type" );
		GridHelper.clickRow( "PS_Detail_EveModDefn_eventmoddefnDetail_GridId", row, " Table  Definition" );
		ButtonHelper.click( "PS_Detail_EveModDefn_tableDefnentsearch_BtnId" );
		GenericHelper.waitForLoadmask( detailScreenWaitSec );
		tableDefnSearchimpl.tableDefnEntitySearch( ratedEvent );
		tableDefnSearchimpl.clickOkButtonOnTableDefnSearch();
	}

	protected void ratedEventSegTableDefn() throws Exception
	{
		TableDefinationSearchImpl tableDefnSearchimpl = new TableDefinationSearchImpl();
		int row = GridHelper.getRowNumber( "PS_Detail_EveModDefn_eventmoddefnDetail_GridId", "Rated Event Segment", "Type" );
		GridHelper.clickRow( "PS_Detail_EveModDefn_eventmoddefnDetail_GridId", row, " Table  Definition" );
		ButtonHelper.click( "PS_Detail_EveModDefn_tableDefnentsearch_BtnId" );
		GenericHelper.waitForLoadmask( detailScreenWaitSec );
		tableDefnSearchimpl.tableDefnEntitySearch( ratedEventSeg );
		tableDefnSearchimpl.clickOkButtonOnTableDefnSearch();
	}

	public void saveEventModellingDefinition( String eventName ) throws Exception
	{
		ButtonHelper.click( "PS_Detail_EveModDefn_Save_BtnID" );// pass in ps_or
		GenericHelper.waitForLoadmask();
		if ( PopupHelper.isPresent("window-scroll-panel") && ValidationHelper.isTrue( confgMap ) )
		{
			Thread.sleep( 3000 );
			ButtonHelper.click( "YesButton" );
			GenericHelper.waitForLoadmask();
			assertEquals( NavigationHelper.getScreenTitle(), "Event Mapping" );
			ButtonHelper.click( "PS_Detail_EveModDefn_Mapping_save__BtnId" );
			GenericHelper.waitForLoadmask();
		}
		if ( PopupHelper.isPresent("window-scroll-panel") && ValidationHelper.isFalse( confgMap ) )
		{
			ButtonHelper.click( "no" );
			GenericHelper.waitForLoadmask( searchScreenWaitSec );
		}
		ElementHelper.isElementPresent( "PS_Detail_EveModDefn_SearchPanelID" );

		boolean isNewAddedEMDefPresent = GridHelper.isValuePresent( "PS_Detail_EveModDefn_GridId", eventName, "Name" );
		assertTrue( isNewAddedEMDefPresent );
	}

}
