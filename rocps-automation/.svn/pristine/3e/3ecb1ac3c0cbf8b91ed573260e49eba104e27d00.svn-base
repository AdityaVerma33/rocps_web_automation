package com.subex.rocps.automation.helpers.application.matchandrate.eventmodellingdefinition;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.subex.rocps.automation.helpers.application.genericHelpers.PSGenericHelper;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;
import com.subex.rocps.automation.utils.PSStringUtils;

import com.subex.automation.helpers.application.NavigationHelper;
import com.subex.automation.helpers.component.ButtonHelper;
import com.subex.automation.helpers.component.ComboBoxHelper;
import com.subex.automation.helpers.component.GenericHelper;
import com.subex.automation.helpers.component.GridHelper;
import com.subex.automation.helpers.data.ValidationHelper;
import com.subex.automation.helpers.report.Log4jHelper;

public class ConfigureSearchGridActionDetailImpl extends PSAcceptanceTest
{

	protected Map<String, String> confgSearchGridDetailTabMap;
	protected String tableType;
	protected String tableColumn;
	protected String tableColnHeadrs;
	protected String tableColnChckBx;
	protected String tablColmnflagsArr[];
	protected String tablColnHdrsArr[];
	PSStringUtils psStringUtils = new PSStringUtils();
	PSGenericHelper psGenericHelper = new PSGenericHelper();

	public ConfigureSearchGridActionDetailImpl( Map<String, String> confgSearchGridDetailTabMap ) throws Exception
	{

		this.confgSearchGridDetailTabMap = confgSearchGridDetailTabMap;
		initInstVariables();
	}

	//Method for initializing instance variables
	public void initInstVariables() throws Exception
	{
		tableType = confgSearchGridDetailTabMap.get( "TableType" );
		tableColumn = confgSearchGridDetailTabMap.get( "TbleColumn" );
		tableColnHeadrs = confgSearchGridDetailTabMap.get( "TableColmnHeaders" );
		tableColnChckBx = confgSearchGridDetailTabMap.get( "TableColmnCheckBox" );
		if ( ValidationHelper.isNotEmpty( tableColnChckBx ) )
			tablColmnflagsArr = psStringUtils.stringSplitFirstLevel( tableColnChckBx );
		if ( ValidationHelper.isNotEmpty( tableColnHeadrs ) )
			tablColnHdrsArr = psStringUtils.stringSplitFirstLevel( tableColnHeadrs );
	}

	public void verifyTheColmnHeaderOfConfgSeGrd() throws Exception
	{
		ComboBoxHelper.select( "PS_Detail_EveModDefn_confgsearchGrid_GridWrapper", "PS_Detail_EveModDefn_confgsearchGrid_tableType_ComboId", tableType );
		GenericHelper.waitForLoadmask( 15000 );
		GenericHelper.waitTime( 5, "Wait for Table colmuns value to be appear " );
		ArrayList<String> excelColumnNames = new ArrayList<String>();
		for ( int col = 0; col < tablColnHdrsArr.length; col++ )
		{
			excelColumnNames.add( tablColnHdrsArr[col] );
		}
		totalColumns( excelColumnNames );
		ButtonHelper.click( "PS_Detail_EveModDefn_confgsearchGrid_Cancel_BtnId" );
		GenericHelper.waitForLoadmask();
		if ( NavigationHelper.getScreenTitle().contentEquals( "Confirm" ) )
			ButtonHelper.click( "PS_Detail_EveModDefn_confgsearchGrid_discard_BtnId" );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
	}

	/*
	 * This method is to get the verify the screen total columns
	 */
	public void totalColumns( ArrayList<String> excelColumnNames ) throws Exception
	{
		PSStringUtils psStringObj = new PSStringUtils();
		assertTrue( GridHelper.isPresent( "PS_Detail_EveModDefn_confgsearchGrid_GridId" ) );
		List<String> actualColumnNames = psGenericHelper.getGridColumns( "grid_column_header_undefined_" );
		int index = actualColumnNames.size() - 1;
		String finalVal = actualColumnNames.get( index );
		if ( finalVal.isEmpty() )
			actualColumnNames.remove( index );
		assertEquals( actualColumnNames, excelColumnNames, "Search Screen total columns are not matching" );
		String actualValues = psStringObj.stringformation( actualColumnNames );
		String excelValues = psStringObj.stringformation( excelColumnNames );
		Log4jHelper.logInfo( "Actual Value :" + actualValues );
		Log4jHelper.logInfo( "Excel Value : " + excelValues );
	}

	public void modifyConfgSearchGridTable( String clientPartition, String currscreentitle ) throws Exception
	{
		ComboBoxHelper.select( "PS_Detail_EveModDefn_confgsearchGrid_GridWrapper", "PS_Detail_EveModDefn_confgsearchGrid_tableType_ComboId", tableType );
		GenericHelper.waitTime( 5, "Wait for Table colmuns value to be appear " );
		GridHelper.clickRow( "PS_Detail_EveModDefn_confgsearchGrid_GridId", tableColumn, " Table  Columns" );
		int rowNum = GridHelper.getRowNumber( "PS_Detail_EveModDefn_confgsearchGrid_GridId", tableColumn, " Table  Columns" );
		changeTheTableColumn( rowNum );
		GenericHelper.waitForLoadmask();
	}

	protected void changeTheTableColumn( int rownum ) throws Exception
	{
		for ( int i = 0, j = 1; i < tablColmnflagsArr.length && j < tablColnHdrsArr.length; i++, j++ )
		{
			if ( tablColnHdrsArr[j].equals( "Visible" ) )
				changeTableColmnFlag( i, rownum, j, "PS_Detail_EveModDefn_confgsearchGrid_visible_ChckBox" );
			else if ( tablColnHdrsArr[j].equals( "Searchable" ) )
				changeTableColmnFlag( i, rownum, j, "PS_Detail_EveModDefn_confgsearchGrid_searchable_ChckBox" );
			else if ( tablColnHdrsArr[j].equals( "Value to  Count" ) )
				changeTableColmnFlag( i, rownum, j, "PS_Detail_EveModDefn_confgsearchGrid_valueToCount_ChckBox" );
		}

	}

	protected void changeTableColmnFlag( int tabColFlgIndex, int rowNum, int colmnHeaderIndex, String chckBoxId ) throws Exception
	{

		if ( ValidationHelper.isFalse( tablColmnflagsArr[tabColFlgIndex] ) )
		{
			//System.out.println(tablColnHdrsArr[colmnHeaderIndex]+" here at  "+tablColmnflagsArr[tabColFlgIndex]);
			GridHelper.updateGridCheckBox( "PS_Detail_EveModDefn_confgsearchGrid_GridId", chckBoxId, rowNum, tablColnHdrsArr[colmnHeaderIndex], tablColmnflagsArr[tabColFlgIndex] );
			GenericHelper.waitForLoadmask();
		}
		if ( ValidationHelper.isTrue( tablColmnflagsArr[tabColFlgIndex] ) )
		{
			System.out.println( tablColnHdrsArr[colmnHeaderIndex] + " here at  " + tablColmnflagsArr[tabColFlgIndex] );
			GridHelper.updateGridCheckBox( "PS_Detail_EveModDefn_confgsearchGrid_GridId", chckBoxId, rowNum, tablColnHdrsArr[colmnHeaderIndex], tablColmnflagsArr[tabColFlgIndex] );
			GenericHelper.waitForLoadmask();
			if ( NavigationHelper.getScreenTitle().contentEquals( "Information" ) )
			{
				ButtonHelper.clickIfEnabled( "PS_Detail_EveModDefn_confgsearchGrid_inform_OkBtnId" );
				GenericHelper.waitForLoadmask();
			}
		}
	}

	public void clickConfigureSearchGridSave() throws Exception
	{
		ButtonHelper.click( "PS_Detail_EveModDefn_confgsearchGrid_GridWrapper", "PS_Detail_EveModDefn_confgsearchGrid_Save_BtnId" );
		GenericHelper.waitForLoadmask( detailScreenWaitSec );
	}

}
