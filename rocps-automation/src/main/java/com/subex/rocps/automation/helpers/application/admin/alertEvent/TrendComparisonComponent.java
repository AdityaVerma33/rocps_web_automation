package com.subex.rocps.automation.helpers.application.admin.alertEvent;

import com.subex.automation.helpers.component.ButtonHelper;
import com.subex.automation.helpers.component.ComboBoxHelper;
import com.subex.automation.helpers.component.GenericHelper;
import com.subex.automation.helpers.component.GridHelper;
import com.subex.automation.helpers.component.MouseHelper;
import com.subex.automation.helpers.component.TextBoxHelper;
import com.subex.automation.helpers.data.ValidationHelper;
import com.subex.rocps.automation.helpers.application.filters.PSSearchGridHelper;
import com.subex.rocps.automation.helpers.application.genericHelpers.PSGenericHelper;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;
import com.subex.rocps.automation.utils.PSStringUtils;

public class TrendComparisonComponent extends PSAcceptanceTest
{
	PSGenericHelper genObj = new PSGenericHelper();
	PSStringUtils strObj = new PSStringUtils();

	private void tableInstanceSearch( String value ) throws Exception
	{
		genObj.waitforPopupHeaderElement( "Schema" );
		ButtonHelper.click( "ClearButton" );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		ButtonHelper.click( "SearchButton" );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		genObj.waitforPopupHeaderElement( "Schema" );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		int row = PSSearchGridHelper.gridFilterSearchWithTextBox( "tinDisplayName", value, "Display Name" );
		boolean isValue = GridHelper.isValuePresent( "Detail_popUpWindowId", "SearchGrid", value, "Display Name" );
		assertTrue( isValue, "Table Instance Search with  display name :'" + value + "'  is not found in 'Table Instance Search' popupScreen " );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		GridHelper.clickRow( "Detail_popUpWindowId", "SearchGrid", row, "Display Name" );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		ButtonHelper.clickIfEnabled( "Detail_popUpWindowId", "OKButton" );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		genObj.waitforPopupHeaderElementToDisappear( "Schema" );
	}

	public void detailsPanelHeader( String tableInstance, String dateTime, String compValue, String compKey, String entity ) throws Exception
	{
		ButtonHelper.click( "trigger-tableInst" );
		tableInstanceSearch( tableInstance );
		ComboBoxHelper.select( "datetimeTableColumn_gwt_uid_", dateTime );
		ComboBoxHelper.select( "comparisonTableColumn_gwt_uid_", compValue );
		if ( !ValidationHelper.isEmpty( compKey ) )
		{
			ComboBoxHelper.select( "btwnEntityTbl_gwt_uid_", compKey );
			if ( !ValidationHelper.isEmpty( entity ) )
				ComboBoxHelper.select( "ofEntityTbl_gwt_uid_", entity );
		}
	}

	public void trendComparisonConfiguration( String currentFunction, String currLookBack, String currentSpan, String currentFrequency, String lastFunction, String lastLookBack, String lastSpan, String lastFrequency ) throws Exception
	{
		ComboBoxHelper.select( "ptccFuncForCurrLast_gwt_uid_", currentFunction );
		TextBoxHelper.type( "ptccCurrLastValue", currLookBack );
		TextBoxHelper.type( "ptccCurrLastSpan", currentSpan );
		ComboBoxHelper.select( "ptccCurrLastFreq_gwt_uid_", currentFrequency );
		ComboBoxHelper.select( "ptccFuncForLast_gwt_uid_", lastFunction );
		TextBoxHelper.type( "ptccLastValue", lastLookBack );
		TextBoxHelper.type( "ptccLastSpan", lastSpan );
		ComboBoxHelper.select( "ptccLastFreq_gwt_uid_", lastFrequency );

	}

	public void thresholdConditionValue( String operator1, String value1, String type1, String operator2, String value2, String type2 ) throws Exception
	{
		ComboBoxHelper.select( "pttvConditionOperator1_gwt_uid_", operator1 );
		TextBoxHelper.type( "pttvValue1", value1 );
		ComboBoxHelper.select( "pttvValueType1_gwt_uid", type1 );
		if ( !ValidationHelper.isEmpty( operator2 ) )
		{
			ComboBoxHelper.select( "pttvConditionOperator2_gwt_uid_", operator2 );
			TextBoxHelper.type( "pttvValue2", value2 );
			ComboBoxHelper.select( "pttvValueType2_gwt_uid", type2 );
		}
	}

	public void groupByDetails( String fields ) throws Exception
	{
		String[] fieldsArr = strObj.stringSplitFirstLevel( fields );
		for ( int i = 0; i < fieldsArr.length; i++ )
		{
			genObj.dualListSelection( "Available fields for group by", fieldsArr[i] );
		}

	}

	public void additionalFilters( String clause, String leftIndent, String column, String operator, String rightIndent ) throws Exception
	{
		String[] clauses = strObj.stringSplitFirstLevel( clause );
		String[] leftIndents = strObj.stringSplitFirstLevel( leftIndent );
		String[] columns = strObj.stringSplitFirstLevel( column );
		String[] operators = strObj.stringSplitFirstLevel( operator );
		String[] rightIndents = strObj.stringSplitFirstLevel( rightIndent );
		for ( int i = 0; i < clauses.length; i++ )
		{
			if ( !ValidationHelper.isEmpty( clauses[i] ) )
			{
				ButtonHelper.click( "toolbar-button-label-queryFilterToolbar.addCondition" );
				GridHelper.clickRow( "gridFilters", i + 1, 1 );
				GridHelper.clickRow( "gridFilters", i + 1, 1 );
				ComboBoxHelper.select( "ptafConditionClauseEditor_gwt-uid-341", clauses[i] );
				GridHelper.clickRow( "gridFilters", i + 1, 2 );
				GridHelper.clickRow( "gridFilters", i + 1, 2 );
				TextBoxHelper.type( "leftIndentEditor", leftIndents[i] );
				GridHelper.clickRow( "gridFilters", i + 1, 3 );
				GridHelper.clickRow( "gridFilters", i + 1, 3 );
				ComboBoxHelper.select( "queryFilterTableColumnCombo", columns[i] );
				GridHelper.clickRow( "gridFilters", i + 1, 4 );
				GridHelper.clickRow( "gridFilters", i + 1, 4 );
				ComboBoxHelper.select( "ptafConditionOperatorEditor_gwt_uid_", operators[i] );
				GridHelper.clickRow( "gridFilters", i + 1, 6 );
				GridHelper.clickRow( "gridFilters", i + 1, 6 );
				TextBoxHelper.type( "rightIndentEditor", rightIndents[i] );

			}
		}

	}

	public void switchToGroupByScreen() throws Exception
	{
		MouseHelper.click( "//div[text()='Group By Configuration']" );
		GenericHelper.waitForLoadmask();
	}

	public void switchToAdditionalScreen() throws Exception
	{
		MouseHelper.click( "//div[text()='Additional Filters']" );
		GenericHelper.waitForLoadmask();
	}
}
