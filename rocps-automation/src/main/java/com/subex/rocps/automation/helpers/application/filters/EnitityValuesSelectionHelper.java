package com.subex.rocps.automation.helpers.application.filters;

import com.subex.rocps.automation.helpers.application.genericHelpers.PSGenericHelper;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;
import com.subex.rocps.automation.utils.PSStringUtils;

import com.subex.automation.helpers.component.ButtonHelper;
import com.subex.automation.helpers.component.ComboBoxHelper;
import com.subex.automation.helpers.component.ElementHelper;
import com.subex.automation.helpers.component.EntityComboHelper;
import com.subex.automation.helpers.component.GenericHelper;
import com.subex.automation.helpers.component.GridHelper;
import com.subex.automation.helpers.component.TextBoxHelper;
import com.subex.automation.helpers.data.ValidationHelper;

public class EnitityValuesSelectionHelper extends PSAcceptanceTest
{
	DataSelectionHelper dataObj = new DataSelectionHelper();
	PSStringUtils psStringUtils = new PSStringUtils();
	PSGenericHelper psGenericHelper = new PSGenericHelper();

	/*
	 * This method is for entity grid config
	 */
	public void entityGridConfig( String entitiesEditorComboId, String entitiesGridId, String entitiesAddBtnId, String entities, String values, String valuesGridId, String valuesAddBtnId, String screenTitle ) throws Exception
	{
		String entitiesArr[] = null;
		String valuesArr[] = null;
		if ( ValidationHelper.isNotEmpty( entities ) )
			entitiesArr = psStringUtils.stringSplitFirstLevel( entities );
		if ( ValidationHelper.isNotEmpty( values ) )
			valuesArr = psStringUtils.stringSplitFirstLevel( values );
		int ent_editorsize = entitiesArr.length;
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		int row = GridHelper.getRowCount( entitiesGridId );
		if ( row > 0 )
			ent_editorsize = ent_editorsize + row;
		for ( int i = row, j = 0; i < ent_editorsize && j < entitiesArr.length; i++, j++ )
		{

			ButtonHelper.click( entitiesAddBtnId );
			GenericHelper.waitForLoadmask( searchScreenWaitSec );
			Thread.sleep( 2000 );
			if ( ValidationHelper.isNotEmpty( entitiesArr[j] ) )
			{
				GridHelper.clickRow( entitiesGridId, i + 1, "Name" );
				GenericHelper.waitForLoadmask( searchScreenWaitSec );
				GenericHelper.waitForLoadmask( searchScreenWaitSec );
				if ( !ComboBoxHelper.isPresent( entitiesEditorComboId ) )
					GridHelper.clickRow( entitiesGridId, i + 1, "Name" );

				ComboBoxHelper.select( entitiesEditorComboId, entitiesArr[j] );
			}

			valueConfig( entitiesGridId, valuesArr[j], valuesGridId, valuesAddBtnId, screenTitle );
		}
	}

	/*
	 * This method is for value config
	 */
	public void valueConfig( String entitiesGridId, String value, String valueGridId, String valueAddBtnId, String screenTitle ) throws Exception
	{
		String[] valueAr = value.split( secondLevelDelimiter );

		for ( int j = 0; j < valueAr.length; j++ )
		{
			ButtonHelper.click( valueAddBtnId );
			GenericHelper.waitForLoadmask( searchScreenWaitSec );
			if ( screenTitle.contains( "Event Usage Request" ) )
				entityValueSelectionWithoutEntIcon( valueGridId, "Value", GenericHelper.getORProperty( "PS_Detail_rerate_entity_txtbxID" ), valueAr[j], j + 1 );
			else if ( screenTitle.contains( "Rerate Request" ) )
				entityValueSelectionWithEntIcon( valueGridId, "Value", valueAr[j], j + 1 );
		}
		GridHelper.click( entitiesGridId );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
	}

	/*
	 * This method is for entity value selection from event usage 
	 */
	public void entityValueSelectionWithoutEntIcon( String gridID, String colHeader, String txtboxID, String value, int row ) throws Exception
	{
		if ( !value.isEmpty() )
		{

			GenericHelper.waitForLoadmask( searchScreenWaitSec );
			if ( ElementHelper.isElementPresent( "//div[contains(text(),'Search')]/parent::div[@class='roc-window-titlebar']" ) )
				entitySelection( gridID, value, colHeader, "Name", row + 1 );
			else
			{
				GenericHelper.waitForLoadmask( searchScreenWaitSec );
				GridHelper.clickRow( gridID, row, "Value" );
				GenericHelper.waitForLoadmask( searchScreenWaitSec );
				if ( textElementPresent( GenericHelper.getORProperty( "PS_Detail_rerate_entity_txtbxID" ) ) )
					entityTextBoxSelection( gridID, txtboxID, colHeader, value, row + 1 );
			}
		}
	}
	/*
	 * This method is for entity value selection from rerate
	 */

	public void entityValueSelectionWithEntIcon( String gridID, String colHeader, String value, int row ) throws Exception
	{
		if ( !value.isEmpty() )
		{
			GenericHelper.waitForLoadmask( searchScreenWaitSec );
			GridHelper.clickRow( gridID, row, "Value" );
			GenericHelper.waitForLoadmask( searchScreenWaitSec );
			if ( !ElementHelper.isElementPresent( "//*[@id='rerateReqEntitySearchComponent']" ) )
			{
				GridHelper.clickRow( gridID, row, "Value" );
				GenericHelper.waitForLoadmask( searchScreenWaitSec );
			}
			if ( ElementHelper.isElementPresent( "//div[contains(@id,'trigger')]" ) )
				entityComboSelection( gridID, "//*[@id='trigger-rerateReqEntitySearchComponent']", value, colHeader, "Name", row );
			else if ( textElementPresent( GenericHelper.getORProperty( "PS_Detail_rerate_entity_txtbxID" ) ) )
				entityTextBoxSelection( gridID, GenericHelper.getORProperty( "PS_Detail_rerate_entity_txtbxID" ), colHeader, value, row );
		}
	}

	/*
	 * This method is for entity value selection from 'GL Code Instance'
	 */

	public void entityValueSelectionGlCdInstance( String gridID, String colHeader, String value, int row ) throws Exception
	{
		if ( !value.isEmpty() )
		{
			GenericHelper.waitForLoadmask( searchScreenWaitSec );
			GridHelper.clickRow( gridID, row, "Value" );
			GenericHelper.waitForLoadmask( searchScreenWaitSec );
			if ( !ElementHelper.isElementPresent( GenericHelper.getORProperty( "PS_Detail_glCodeInst_entityValue_iconXpath" ) ) )
			{
				GridHelper.clickRow( gridID, row, "Value" );
				GenericHelper.waitForLoadmask( searchScreenWaitSec );
			}
			if ( ElementHelper.isElementPresent( GenericHelper.getORProperty( "PS_Detail_glCodeInst_entityValue_iconXpath" ) ) )
				entityComboSelection( gridID, "PS_Detail_glCodeInst_entityValue_iconXpath", value, colHeader, "Name", row );

		}
	}

	/*
	 * This method is to check if textbox is present
	 */
	public boolean textElementPresent( String labelName ) throws Exception
	{
		boolean flag = ElementHelper.isElementPresent( "//input[contains(@id,'" + labelName + "')]" );
		return flag;
	}

	/*
	 * This method is for entity text box selection
	 */
	public void entityTextBoxSelection( String gridID, String textBoxID, String columnName, String value, int row ) throws Exception
	{
		if ( !TextBoxHelper.isPresent( textBoxID ) )
			GridHelper.clickRow( gridID, row, columnName );
		TextBoxHelper.type( textBoxID, value );
		GridHelper.click( gridID );
	}

	/*
	 * This method is used for entity combo selection in rerate screen
	 */
	public void entityComboSelection( String gridID, String entityIconXpath, String value, String entityColHeader, String colHeader, int row ) throws Exception
	{
		if ( ElementHelper.isElementPresent( "PS_Detail_rerate_dateComp_xpath" ) )
		{
			TextBoxHelper.type( "PS_Detail_rerate_date_txtID", value );
			GridHelper.click( gridID );
			GenericHelper.waitForLoadmask( searchScreenWaitSec );
		}
		else
		{
			if ( !ElementHelper.isElementPresent( GenericHelper.getORProperty( entityIconXpath ) ) )
			{
				GridHelper.clickRow( gridID, row, "Value" );
				ElementHelper.waitForElement( entityIconXpath, searchScreenWaitSec );
				GenericHelper.waitForLoadmask( searchScreenWaitSec );
			}
			EntityComboHelper.clickEntityIcon( entityIconXpath );
			GenericHelper.waitForLoadmask( searchScreenWaitSec );
			if ( psGenericHelper.isPopupHeaderElementPresent( "Point to Point" ) )
				dataObj.bandSelection( value );
			else if ( psGenericHelper.isPopupHeaderElementPresent( "Tariff Name" ) )
				dataObj.tariffSelection( value );
			else if ( psGenericHelper.isPopupHeaderElementPresent( "Switch" ) )
				dataObj.routeSelection( value );
			else if ( ElementHelper.isElementPresent( "//div[text()='Stream Search']" ) )
				dataObj.streamSelection( value );
			else if ( ElementHelper.isElementPresent( "//div[text()='Reference Table Search']" ) )
			{
				String actualVal = ComboBoxHelper.getValue( "PS_Detail_referencetable_comboID" );
				if ( actualVal.contains( "Currency" ) )
					dataObj.currencySelection( value );
				if ( actualVal.contains( "TariffRateName" ) )
					dataObj.trnSelection( value );
			}
			else if ( psGenericHelper.isPopupHeaderElementPresent( "Parent Element" ) )
				dataObj.elementSelection( value );
			else if ( psGenericHelper.isPopupHeaderElementPresent( "Event Match Rule" ) )
				dataObj.serviceSelection( value );
			else if ( psGenericHelper.isPopupHeaderElementPresent( "Account Name" ) )
				dataObj.accountSelection( value );
		}
	}

	/*
	 * This method is for entity seection in event usage screen
	 */
	public void entitySelection( String gridID, String value, String entityColHeader, String colHeader, int row ) throws Exception
	{

		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		if ( psGenericHelper.isPopupHeaderElementPresent( "Point to Point" ) )
			dataObj.bandSelection( value );
		else if ( psGenericHelper.isPopupHeaderElementPresent( "Tariff Name" ) )
			dataObj.tariffSelection( value );
		else if ( psGenericHelper.isPopupHeaderElementPresent( "Switch" ) )
			dataObj.routeSelection( value );
		else if ( ElementHelper.isElementPresent( "//div[text()='Stream Search']" ) )
			dataObj.streamSelection( value );
		else if ( ElementHelper.isElementPresent( "//div[text()='Reference Table Search']" ) )
			dataObj.currencySelection( value );
	}

}
