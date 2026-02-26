package com.subex.rocps.automation.helpers.application.deal.deal;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import com.subex.rocps.automation.helpers.application.genericHelpers.PSGenericHelper;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;
import com.subex.rocps.automation.utils.ExcelHolder;
import com.subex.rocps.automation.utils.PSStringUtils;

import com.subex.automation.helpers.application.screens.ElementCreateHelper;
import com.subex.automation.helpers.component.ButtonHelper;
import com.subex.automation.helpers.component.ElementHelper;
import com.subex.automation.helpers.component.GenericHelper;
import com.subex.automation.helpers.component.GridHelper;
import com.subex.automation.helpers.component.SearchGridHelper;
import com.subex.automation.helpers.component.TabHelper;
import com.subex.automation.helpers.component.TextBoxHelper;
import com.subex.automation.helpers.component.TreeHelper;
import com.subex.automation.helpers.componentHelpers.SearchHelper;
import com.subex.automation.helpers.data.ValidationHelper;

public class DealBandConfiguration extends PSAcceptanceTest
{
	protected String trafficType;
	protected String bandGroup;
	protected String band;
	protected String include;
	protected String tiersPerBandGroup;
	protected String gracePeriod;
	protected String bandFrom;
	protected String bandTo;
	protected String fromMinutes;
	protected String toMinutes;
	protected String commitment;
	protected String bestEffort;
	protected String baseTier;
	protected String backToNthTier;
	protected int colSize;
	protected String paramVal;
	protected String[] bandGroupArr;
	protected String[] bandArr;
	protected String[] includeArr;
	protected String[] gracePeriodArr;
	protected String[] bandFromArr;
	protected String[] bandToArr;
	protected String[] fromMinutesArr;
	protected String[] toMinutesArr;
	protected String[] commitmentArr;
	protected String[] bestEffortArr;
	protected String[] baseTierArr;
	protected String[] backToNthTierArr;
	protected Map<String, String> map;
	PSStringUtils strObj = new PSStringUtils();
	PSGenericHelper genericObj = new PSGenericHelper();
	protected ExcelHolder excelHolderObj = null;
	protected Map<String, String> dealMap = null;
	protected Map<String, String> outMap = new HashMap<String, String>();
	protected Map<String, String> inMap = new HashMap<String, String>();

	public DealBandConfiguration( Map<String, String> map ) throws Exception
	{
		this.map = map;
		initializeVaribles( map );
		initializeArray();
		orPropertiesMap();
	}

	public void bandConfig( String incomingOutgoing ) throws Exception
	{
		if ( incomingOutgoing.contains( "Incoming" ) )
		{
			TabHelper.gotoTab( "//*[text()='Incoming']" );
			bandConfiguration( inMap );
		}
		if ( incomingOutgoing.contains( "Outgoing" ) )
		{
			TabHelper.gotoTab( "//*[text()='Outgoing']" );
			bandConfiguration( outMap );
		}
	}

	/*
	 * This method is for band configuration
	 */
	public void bandConfiguration( Map<String, String> map ) throws Exception
	{
		addTrafficType( map.get( "TrafficType" ), map.get( "TreeGridID" ), map.get( "TreeWrapper" ) );
		//	Arrays.stream( bandGroupArr ).forEach( bandGroup -> {

		//	int i = Arrays.asList( bandGroupArr ).indexOf( bandGroup );
		for ( int i = 0; i < bandGroupArr.length; i++ )
		{
			String xpath = "//*[@id='" + map.get( "TreeGridID" ) + "']//div[contains(text(),'" + bandGroupArr[i] + "')]";
			if ( !ElementHelper.isElementPresent( xpath ) )
			{
				TreeHelper.rightClick( map.get( "TreeGridID" ), trafficType );
				GenericHelper.waitForLoadmask( searchScreenWaitSec );
				TreeHelper.clickChild( map.get( "TreeGridID" ), trafficType, "Add Band Group" );
				GenericHelper.waitForLoadmask( searchScreenWaitSec );
				TextBoxHelper.type( "pdbgName", bandGroupArr[i] );
				ButtonHelper.click( "OKButton" );
				GenericHelper.waitForLoadmask( searchScreenWaitSec );

				TreeHelper.clickChild( map.get( "TreeGridID" ), trafficType, bandGroupArr[i] );
				addBands( map, bandArr[i] );
				if ( !include.isEmpty() || !gracePeriod.isEmpty() || !bandFrom.isEmpty() || !bandTo.isEmpty() )
					bandGridConfig( map, bandArr[i], includeArr[i], gracePeriodArr[i], bandFromArr[i], bandToArr[i] );
			}
			else
			{
				TreeHelper.clickChild( map.get( "TreeGridID" ), trafficType, bandGroupArr[i] );
				addBands( map, bandArr[i] );
				if ( !include.isEmpty() || !gracePeriod.isEmpty() || !bandFrom.isEmpty() || !bandTo.isEmpty() )
					bandGridConfig( map, bandArr[i], includeArr[i], gracePeriodArr[i], bandFromArr[i], bandToArr[i] );
			}

		}
	}

	/*
	 * This method is for add traffic type	
	 */
	private void addTrafficType( String trafficTypeProp, String treeGridID, String wrapperid ) throws Exception
	{
		String xpath = "//*[@id='" + wrapperid + "']//*[@id='" + treeGridID + "']//*[contains(text(),'" + trafficType + "')]";
		if ( !ElementHelper.isElementPresent( xpath ) )
		{
			ButtonHelper.click( trafficTypeProp );
			GenericHelper.waitForLoadmask( searchScreenWaitSec );
			genericObj.waitforPopupHeaderElement( "Code" );
			GridHelper.clickRow( "SearchGrid", trafficType, "Name" );
			GenericHelper.waitForLoadmask( searchScreenWaitSec );
			ButtonHelper.click( "OKButton" );
			GenericHelper.waitForLoadmask( searchScreenWaitSec );
		}

	}

	/*
	 * This method is to add bands
	 */
	private void addBands( Map<String, String> map, String bandArr ) throws Exception
	{
		String[] bandAr = bandArr.split( secondLevelDelimiter );
		for ( String band : bandAr )
		{
			int rowNo = GridHelper.getRowNumber( map.get( "BandGrid" ), band, "Band" );
			if ( rowNo == 0 )
			{
				ButtonHelper.click( map.get( "AddBand" ) );
				GenericHelper.waitForLoadmask( searchScreenWaitSec );
				genericObj.waitforPopupHeaderElement( "Point to Point" );
				GenericHelper.waitForLoadmask( searchScreenWaitSec );
				bandSelection( band );
			}
		}
	}

	/*
	 * This method is for band detail grid config
	 */
	public void bandGridConfig( Map<String, String> map, String bandArr, String includeArr, String gracePeriodArr, String fromBandArr, String toBandArr ) throws Exception
	{
		String[] bandAr = bandArr.split( secondLevelDelimiter );
		String[] includeAr = includeArr.split( secondLevelDelimiter );
		String[] gracePeriodAr = gracePeriodArr.split( secondLevelDelimiter );
		String[] fromBandAr = fromBandArr.split( secondLevelDelimiter );
		String[] toBandAr = toBandArr.split( secondLevelDelimiter );

		for ( String band : bandAr )
		{
			int i = Arrays.asList( bandAr ).indexOf( band );
			int row = GridHelper.getRowNumber( map.get( "BandGrid" ), band, "Band" );
			if ( ValidationHelper.isNotEmpty( includeArr ) && ValidationHelper.isNotEmpty( includeAr[i] ) )
				GridHelper.updateGridCheckBox( map.get( "BandGrid" ), row, "Include", includeAr[i] );
			if ( ValidationHelper.isNotEmpty( gracePeriodArr ) && ValidationHelper.isNotEmpty( gracePeriodAr[i] ) )
				GridHelper.updateGridCheckBox( map.get( "BandGrid" ), row, "Grace Period", gracePeriodAr[i] );
			if ( ValidationHelper.isNotEmpty( fromBandArr ) && ValidationHelper.isNotEmpty( fromBandAr[i] ) )
				GridHelper.updateGridTextBox( map.get( "BandGrid" ), map.get( "From" ), row, "From", fromBandAr[i] );
			if ( ValidationHelper.isNotEmpty( toBandArr ) && ValidationHelper.isNotEmpty( toBandAr[i] ) )
				GridHelper.updateGridTextBox( map.get( "BandGrid" ), map.get( "To" ), row, "To", toBandAr[i] );
		}
	}

	/*
	 * This method is for band selection
	 */
	public void bandSelection( String band ) throws Exception
	{
		ButtonHelper.click( "ClearButton" );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		SearchHelper searchHelper = new SearchHelper();
		searchHelper.clickFilterIcon( "SearchGrid", "Name" );
		TextBoxHelper.type( "Grid_Filter_Panel", "bndName", band );
		ButtonHelper.click( "//*[@id='_grid_header_filter']//*[text()='Search']" );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		GridHelper.clickRow( "SearchGrid", band, "Name" );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		ButtonHelper.clickIfEnabled( "Detail_popUpWindowId", "OK_Button_ByID" );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );

	}

	public void orPropertiesMap()
	{
		outMap.put( "TrafficType", "outDealTreeToolBar.addTrafficType" );
		outMap.put( "TreeGridID", "outDealTree" );
		outMap.put( "AddBand", "outgoingDealGridToolBar.addBand" );
		outMap.put( "BandGroup", "pdbgName" );
		outMap.put( "From", "outBndFromDttmEditor" );
		outMap.put( "To", "outBndToDttmEditor" );
		outMap.put( "BandGrid", "outDealGrid" );
		outMap.put( "TreeWrapper", "OutgoingTreeWrapperPanel" );

		inMap.put( "TrafficType", "dealTreeToolBar.addTrafficType" );
		inMap.put( "TreeGridID", "inDealTree" );
		inMap.put( "AddBand", "dealGridToolBar.addBand" );
		inMap.put( "BandGroup", "pdbgName" );
		inMap.put( "From", "bndFromDttmEditor" );
		inMap.put( "To", "bndToDttmEditor" );
		inMap.put( "BandGrid", "inDealGrid" );
		inMap.put( "TreeWrapper", "treeWrapperPanel" );
	}

	/*
	 * This method is to initialize array variables
	 */
	public void initializeArray() throws Exception
	{
		bandGroupArr = strObj.stringSplitFirstLevel( bandGroup );
		bandArr = strObj.stringSplitFirstLevel( band );
		includeArr = strObj.stringSplitFirstLevel( include );
		gracePeriodArr = strObj.stringSplitFirstLevel( gracePeriod );
		bandFromArr = strObj.stringSplitFirstLevel( bandFrom );
		bandToArr = strObj.stringSplitFirstLevel( bandTo );
	}

	/*
	 * This method is to initalize instance varibales
	 */
	public void initializeVaribles( Map<String, String> map ) throws Exception
	{
		trafficType = ExcelHolder.getKey( map, "TrafficType" );
		bandGroup = ExcelHolder.getKey( map, "BandGroup" );
		band = ExcelHolder.getKey( map, "Band" );
		include = ExcelHolder.getKey( map, "Include" );
		gracePeriod = ExcelHolder.getKey( map, "GracePeriod" );
		bandFrom = ExcelHolder.getKey( map, "BandFrom" );
		bandTo = ExcelHolder.getKey( map, "BandTo" );

	}

}
