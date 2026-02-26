package com.subex.rocps.automation.helpers.application.deal.deal;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.subex.rocps.automation.helpers.application.genericHelpers.PSGenericHelper;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;
import com.subex.rocps.automation.utils.ExcelHolder;
import com.subex.rocps.automation.utils.PSStringUtils;

import com.subex.automation.helpers.component.ButtonHelper;
import com.subex.automation.helpers.component.CheckBoxHelper;
import com.subex.automation.helpers.component.ComboBoxHelper;
import com.subex.automation.helpers.component.ElementHelper;
import com.subex.automation.helpers.component.GenericHelper;
import com.subex.automation.helpers.component.GridHelper;
import com.subex.automation.helpers.component.TextBoxHelper;
import com.subex.automation.helpers.data.ValidationHelper;
import com.subex.automation.helpers.file.ExcelReader;

public class CapsConfiguration extends PSAcceptanceTest
{
	protected ExcelReader excelReader = null;
	protected Map<String, ArrayList<String>> excelReaderMapObj = null;
	protected Map<String, String> mapObj = null;
	protected ExcelHolder excelHolderObj = null;
	protected String path;
	protected String workBookName;
	protected String sheetName;
	protected String testCaseName;
	protected int paramVal;
	protected int colSize;
	protected Map<String, String> map;
	protected String capName;
	protected String totalVolume;
	protected String tierVolume;
	protected String condition;
	protected String rateType;
	protected String applyTo;
	protected String capPercentage;
	protected String ratePercentage;
	protected String capOn;
	protected String penalizeTotalTraffic;
	protected String disqualifyMinutesOver;
	protected String bandGroup;
	protected String tier;
	protected String include;
	protected String includeColHeader;
	protected String[] includeArr;
	PSStringUtils strObj = new PSStringUtils();
	PSGenericHelper genericObj = new PSGenericHelper();

	public CapsConfiguration( Map<String, String> mapObj ) throws Exception
	{
		this.map = mapObj;
		initialiseVariables( mapObj );
	}

	public void capConfig( String capTestcase ) throws Exception
	{
		if ( capTestcase.contains( "Incoming" ) )
			capDetailConfig( "PS_Detail_Deal_CapIn_GridID" );
		if ( capTestcase.contains( "Outgoing" ) )
			capDetailConfig( "PS_Detail_Deal_CapOut_GridID" );
	}

	/*
	 * This method is for cap detail grid config
	 */
	public void capDetailConfig( String gridID ) throws Exception
	{
		ButtonHelper.click( gridID );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		assertTrue( ElementHelper.isElementPresent( "//div[@id='detailsPanel']" ), "Cap Configuration detail window is not opened" );
		int row = GridHelper.getRowNumber( "capDetailsGrid", capName, "Cap  Name" );
		if ( row == 0 )
		{
			ButtonHelper.click( "capDetailsGridToolbar.Add" );
			GenericHelper.waitForLoadmask( searchScreenWaitSec );
			capDetail();
		}
		else
		{
			GridHelper.clickRow( "capDetailsGrid", row, "Cap  Name" );
			capDetail();
		}
	}

	/*
	 * This method is for capdeatail config
	 */
	public void capDetail() throws Exception
	{
		includeArr = strObj.stringSplitFirstLevel( include );
		TextBoxHelper.type( "PS_Detail_Deal_Cap_nametxtID", capName );
		if ( ValidationHelper.isNotEmpty( bandGroup ) )
			ComboBoxHelper.select( "PS_Detail_Deal_CapBG_comboID", bandGroup );
		if ( ValidationHelper.isFalse( totalVolume ) )
			CheckBoxHelper.uncheck( "PS_Detail_Deal_CapTotal_chckbx" );
		if ( ValidationHelper.isTrue( tierVolume ) )
		{
			CheckBoxHelper.check( "PS_Detail_Deal_CapTierVolume_chckBx" );
			ComboBoxHelper.select( "PS_Detail_Deal_CapTier_comboID", tier );
		}
		ComboBoxHelper.select( "PS_Detail_Deal_CapCondition_comboID", condition );
		ComboBoxHelper.select( "PS_Detail_Deal_CapRateType_comboID", rateType );
		ComboBoxHelper.select( "PS_Detail_Deal_CapApplyTo_comboID", applyTo );
		TextBoxHelper.type( "PS_Detail_Deal_CapPercentage_txtID", capPercentage );
		TextBoxHelper.type( "PS_Detail_Deal_CapRatePercen_txtID", ratePercentage );
		ComboBoxHelper.select( "PS_Detail_Deal_CapOn_comboID", capOn );
		if ( ValidationHelper.isNotEmpty( disqualifyMinutesOver ) && ValidationHelper.isTrue( disqualifyMinutesOver ) )
			CheckBoxHelper.check( "disqualifyMinOverCap_InputElement" );
		if ( ValidationHelper.isNotEmpty( penalizeTotalTraffic ) && ValidationHelper.isTrue( penalizeTotalTraffic ))
			CheckBoxHelper.check( "PS_Detail_Deal_CapPenalize_chckbx" );

		if ( ValidationHelper.isNotEmpty( include ) )
		{
			for ( int i = 0; i < includeArr.length; i++ )
			{
				includeConfig( includeArr[i] );
			}
		}

	}

	/*
	 * This method is for include config
	 */
	public void includeConfigtry( String include ) throws Exception
	{
		String[] includeAr = include.split( secondLevelDelimiter );
		String[] includeColHeaderArr = includeColHeader.split( secondLevelDelimiter );
		for ( String str : includeColHeaderArr )
		{
			int row = GridHelper.getRowNumberContains( "gridPanel", includeAr[0], str );
			GridHelper.updateGridCheckBox( "gridPanel", row, "Include", includeAr[1] );

		}

	}

	/*
	 * This method is for include config
	 */
	public void includeConfig( String include ) throws Exception
	{
		String[] includeAr = include.split( secondLevelDelimiter );
		String[] includeColHeaderArr = strObj.stringSplitFirstLevel( includeColHeader );

		int row = GridHelper.getRowNumberContains( "gridPanel", includeAr[0], includeColHeaderArr[0] );
		for ( int i = 1; i < includeAr.length; i++ )
		{
			if ( !includeAr[i].isEmpty() )
				GridHelper.updateGridCheckBox( "gridPanel", row, includeColHeaderArr[i], includeAr[i] );
		}

	}

	/*
	 * This method is to save cap config
	 */
	public void saveCapConfig() throws Exception
	{
		ButtonHelper.click( "add" );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
	}

	/*
	 * This method is to close cap config
	 */
	public void closeCapConfig() throws Exception
	{
		ButtonHelper.click( "PS_Detail_Deal_Cap_CloseBtn" );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
	}

	/*
	 * This method is to initialise instance varibales
	 */
	public void initialiseVariables( Map<String, String> map ) throws Exception
	{

		capName = ExcelHolder.getKey( map, "CapName" );
		totalVolume = ExcelHolder.getKey( map, "TotalVolume" );
		tierVolume = ExcelHolder.getKey( map, "TierVolume" );
		condition = ExcelHolder.getKey( map, "Condition" );
		rateType = ExcelHolder.getKey( map, "RateType" );
		applyTo = ExcelHolder.getKey( map, "ApplyTo" );
		capPercentage = ExcelHolder.getKey( map, "CapPercentage" );
		ratePercentage = ExcelHolder.getKey( map, "RatePercentage" );
		capOn = ExcelHolder.getKey( map, "CapOn" );
		penalizeTotalTraffic = ExcelHolder.getKey( map, "PenalizeTotalTraffic" );
		bandGroup = ExcelHolder.getKey( map, "BandGroup" );
		tier = ExcelHolder.getKey( map, "Tier" );
		include = ExcelHolder.getKey( map, "Include" );
		includeColHeader = ExcelHolder.getKey( map, "IncludeColHeader" );
		disqualifyMinutesOver = ExcelHolder.getKey( map, "DisqualifyMinutesOver" );
	}
}
