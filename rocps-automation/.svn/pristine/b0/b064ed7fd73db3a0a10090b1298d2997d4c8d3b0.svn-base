package com.subex.rocps.automation.helpers.application.bilateral.bilateralratedmodelling;

import java.util.Map;

import com.subex.rocps.automation.helpers.application.filters.DataSelectionHelper;
import com.subex.rocps.automation.helpers.application.genericHelpers.PSDataComponentHelper;
import com.subex.rocps.automation.helpers.application.genericHelpers.PSGenericHelper;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;
import com.subex.rocps.automation.utils.ExcelHolder;
import com.subex.rocps.automation.utils.PSStringUtils;

import com.subex.automation.helpers.application.NavigationHelper;
import com.subex.automation.helpers.component.ButtonHelper;
import com.subex.automation.helpers.component.CheckBoxHelper;
import com.subex.automation.helpers.component.ComboBoxHelper;
import com.subex.automation.helpers.component.ElementHelper;
import com.subex.automation.helpers.component.GenericHelper;
import com.subex.automation.helpers.component.GridHelper;
import com.subex.automation.helpers.component.TextBoxHelper;
import com.subex.automation.helpers.data.ValidationHelper;

public class BilateralRatedModellingDetailImpl extends PSAcceptanceTest
{
	protected String partition;
	protected String name;
	protected String prefix;
	protected String mergedTablePrefix;
	protected String aggregationConfiguration;
	protected String schema;
	protected String billedUsage;
	protected String fieldName;
	protected String displayName;
	protected String dataType;
	protected String aggregationField;
	protected String key;
	protected String visible;
	protected String searchable;
	protected String skipAtParent;
	protected String prorate;
	protected String subTotal;
	protected String totalField;
	protected String fromCurrency;
	protected String toCurrency;
	String[] fieldNameArr;
	String[] displayNameArr;
	String[] dataTypeArr;
	String[] aggregationFieldArr;
	String[] keyArr;
	String[] visibleArr;
	String[] searchableArr;
	String[] skipAtParentArr;
	String[] prorateArr;
	String[] subTotalArr;
	String[] totalFieldArr;
	String[] fromCurrencyArr;
	String[] toCurrencyArr;
	PSStringUtils stringObj = new PSStringUtils();
	DataSelectionHelper dataObj = new DataSelectionHelper();
	PSDataComponentHelper compObj =  new PSDataComponentHelper();
	protected Map<String, String> map;

	public BilateralRatedModellingDetailImpl( Map<String, String> map ) throws Exception
	{
		this.map = map;
		initialiseVariables( map );
		initalizeArray();
	}
	/*
	 * This method is for new Bilateral Modelli
	 */
	public void newBilateralModelling() throws Exception
	{
		PSGenericHelper genericObj = new PSGenericHelper();
		genericObj.clickNewAction( partition );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		assertEquals( NavigationHelper.getScreenTitle(), "New Bilateral Rated Details Modelling" );

	}
	
	public void basicDetails() throws Exception
	{
		TextBoxHelper.type( "pbmdName", name );
		TextBoxHelper.type( "pbmdPrefix", prefix );
		TextBoxHelper.type( "pmrgdPrefix", mergedTablePrefix );
		ComboBoxHelper.select( "aggregationConfig_gwt_uid_", aggregationConfiguration );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		ComboBoxHelper.select( "busageTableColumn_gwt_uid_", billedUsage );
	}
	
	public void brdModellingGridConfig() throws Exception
	{
		
		for (int row = 0;row<fieldNameArr.length;row++)
		{
			ElementHelper.scrollToView( "//div[@id='grid_column_header_undefined_pbmcName']", false );
			int rowNo = GridHelper.getRowNumber( "brdModellingColGrid", fieldNameArr[row], "Field Name" );
			int rowCount = GridHelper.getRowCount( "brdModellingColGrid" );
			if(rowNo !=0 )
			{
				brdModellingDetailGridConfig( rowNo, row );
			}else 
			{
				ButtonHelper.click( "brdModlingColToolbar.add" );
				GenericHelper.waitForLoadmask( searchScreenWaitSec );				
				GridHelper.updateGridTextBox( "brdModellingColGrid", "fieldNameEditor", rowCount+1, "Field Name", fieldNameArr[row] );
				GridHelper.updateGridComboBox( "brdModellingColGrid", "dataTypeEditor_gwt_uid_", rowCount+1, "Data Type", dataTypeArr[row] );
				brdModellingDetailGridConfig( rowCount+1, row );
			}
		}
		
	}
	
	public void brdModellingDetailGridConfig(int rowNo, int row) throws Exception
	{		
		
		GridHelper.updateGridTextBox( "brdModellingColGrid", "displayNameEditor", rowNo, "Display Name", displayNameArr[row] );

		GridHelper.updateGridComboBox( "brdModellingColGrid", "AggTclEditor_gwt_uid_", rowNo, "Aggregation Field", aggregationFieldArr[row] );
		if(!key.isEmpty() && !ValidationHelper.isEmpty( keyArr[row] ) &&ValidationHelper.isTrue( keyArr[row] ) && !getCheckBoxText(rowNo) )
			GridHelper.updateGridCheckBox( "brdModellingColGrid", rowNo, "Key", keyArr[row] );
		if(!visible.isEmpty() && !ValidationHelper.isEmpty( visibleArr[row] ) && ValidationHelper.isTrue( visibleArr[row] ) && !getCheckBoxText(rowNo))
			GridHelper.updateGridCheckBox( "brdModellingColGrid", rowNo, "Visible", visibleArr[row] );
		if(!searchable.isEmpty() && !ValidationHelper.isEmpty( searchableArr[row] ) && ValidationHelper.isTrue( searchableArr[row] ))
			GridHelper.updateGridCheckBox( "brdModellingColGrid", rowNo, "Searchable", searchableArr[row] );
		if(!skipAtParent.isEmpty() && !ValidationHelper.isEmpty( skipAtParentArr[row] ) && ValidationHelper.isTrue( skipAtParentArr[row] ))
			GridHelper.updateGridCheckBox( "brdModellingColGrid", rowNo, "Skip At Paren", skipAtParentArr[row] );
		if(!prorate.isEmpty()  && !ValidationHelper.isEmpty( prorateArr[row] ) && ValidationHelper.isTrue( prorateArr[row] ))
			GridHelper.updateGridCheckBox( "brdModellingColGrid", rowNo, "Prorate", prorateArr[row] );
		if(!subTotal.isEmpty() && !ValidationHelper.isEmpty( subTotalArr[row] ) && ValidationHelper.isTrue( subTotalArr[row] ))
		{
			GridHelper.updateGridCheckBox( "brdModellingColGrid", rowNo, "Sub Total", subTotalArr[row] );	
			if(!totalField.isEmpty() && ValidationHelper.isNotEmpty( totalFieldArr[row] ))
			{
			ElementHelper.scrollToView( "//div[@id='grid_column_header_undefined_fromBrdModellingCol']", false );
			GenericHelper.waitForLoadmask();
			GridHelper.updateGridComboBox( "brdModellingColGrid", "TotalcolumnEditor_gwt_uid_", rowNo, "Total  Field", totalFieldArr[row] );
			ElementHelper.scrollToView( "//div[@id='grid_column_header_undefined_fromBrdModellingCol']", false );
			GenericHelper.waitForLoadmask();
			GridHelper.updateGridComboBox( "brdModellingColGrid", "FromcurrencyEditor_gwt_uid_", rowNo, "From Currency", fromCurrencyArr[row] );
			ElementHelper.scrollToView( "//div[@id='grid_column_header_undefined_toBrdModellingCol']", false );
			GenericHelper.waitForLoadmask();
			GridHelper.updateGridComboBox( "brdModellingColGrid", "TocurrencyEditor_gwt_uid_", rowNo, "To Currency", toCurrencyArr[row] );	
			}
		}
	}
	
	public boolean getCheckBoxText( int row) throws Exception
    {	
		String xpath = GenericHelper.getORProperty( "//div[@id='brdModellingColGrid']//table//tbody/tr[" + row + "]/td[5]//div[@id='grid_check_editor']//img" );    
       
        String elementTxt = ElementHelper.getAttribute( xpath, "src" );        
        if(elementTxt.contains( "cellunchecked" ))
        	return false;
        else return true;
    }
	/*
	 * This method is to save Bilateral Modelling
	 */

	public void saveBilateralModelling() throws Exception
	{
		ButtonHelper.click( "brdModellingDetail.save" );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		GenericHelper.waitForElementToDisappear( "brdModellingDetail.save", detailScreenWaitSec );
		GenericHelper.waitForLoadmask( detailScreenWaitSec );

		ButtonHelper.click( "SearchButton" );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		assertTrue( GridHelper.isValuePresent( "SearchGrid", name, "Name" ) );
	}

	/*
	 * This method is to initialize array
	 */
	public void initalizeArray() throws Exception
	{
		fieldNameArr = stringObj.stringSplitFirstLevel( fieldName );
		displayNameArr = stringObj.stringSplitFirstLevel( displayName );
		dataTypeArr = stringObj.stringSplitFirstLevel( dataType );
		aggregationFieldArr = stringObj.stringSplitFirstLevel( aggregationField );
		keyArr = stringObj.stringSplitFirstLevel( key );
		visibleArr = stringObj.stringSplitFirstLevel( visible );
		searchableArr = stringObj.stringSplitFirstLevel( searchable );
		skipAtParentArr = stringObj.stringSplitFirstLevel( skipAtParent );
		prorateArr = stringObj.stringSplitFirstLevel( prorate );
		subTotalArr = stringObj.stringSplitFirstLevel( subTotal );		
		totalFieldArr = stringObj.stringSplitFirstLevel( totalField );
		fromCurrencyArr = stringObj.stringSplitFirstLevel( fromCurrency );
		toCurrencyArr = stringObj.stringSplitFirstLevel( toCurrency );

	}

	/*
	 * This method is to initialize instance variables
	 */
	public void initialiseVariables( Map<String, String> map ) throws Exception
	{
		partition = ExcelHolder.getKey( map, "Partition" );
		name = ExcelHolder.getKey( map, "Name" );
		prefix = ExcelHolder.getKey( map, "Prefix" );
		mergedTablePrefix = ExcelHolder.getKey( map, "MergedTablePrefix" );
		aggregationConfiguration = ExcelHolder.getKey( map, "AggregationConfiguration" );
		schema = ExcelHolder.getKey( map, "Schema" );
		billedUsage = ExcelHolder.getKey( map, "BilledUsage" );
		fieldName = ExcelHolder.getKey( map, "FieldName" );
		displayName = ExcelHolder.getKey( map, "DisplayName" );
		dataType = ExcelHolder.getKey( map, "DataType" );
		aggregationField = ExcelHolder.getKey( map, "AggregationField" );
		key = ExcelHolder.getKey( map, "Key" );
		visible = ExcelHolder.getKey( map, "Visible" );
		searchable = ExcelHolder.getKey( map, "Searchable" );
		skipAtParent = ExcelHolder.getKey( map, "SkipAtParent" );
		prorate = ExcelHolder.getKey( map, "Prorate" );
		subTotal = ExcelHolder.getKey( map, "SubTotal" );
		totalField = ExcelHolder.getKey( map, "TotalField" );
		fromCurrency = ExcelHolder.getKey( map, "FromCurrency" );
		toCurrency = ExcelHolder.getKey( map, "ToCurrency" );

	}
	
	
}
