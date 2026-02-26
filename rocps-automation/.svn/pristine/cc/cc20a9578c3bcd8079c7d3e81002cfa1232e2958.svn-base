package com.subex.rocps.automation.helpers.application.reportingAndExtraction.reportsAndExt;

import com.subex.rocps.automation.helpers.application.filters.DataSelectionHelper;
import com.subex.rocps.automation.helpers.application.genericHelpers.PSGenericHelper;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;
import com.subex.rocps.automation.utils.PSStringUtils;

import com.subex.automation.helpers.component.ComboBoxHelper;
import com.subex.automation.helpers.component.ElementHelper;
import com.subex.automation.helpers.component.EntityComboHelper;
import com.subex.automation.helpers.component.GenericHelper;
import com.subex.automation.helpers.component.TextBoxHelper;
import com.subex.automation.helpers.data.ValidationHelper;

public class ParamtersSelection extends PSAcceptanceTest
{
	PSGenericHelper psGenericHelper = new PSGenericHelper();
	DataSelectionHelper dataSelectionHelper = new DataSelectionHelper();
	PSStringUtils psStringUtils = new PSStringUtils();

	/*
	 * This method is for configure parameters
	 */
	public void configureParameters( String parameterLabelNm, String paramFieldValue ) throws Exception
	{

		if ( isComboBoxPresentInUI( parameterLabelNm ) )
		{
			String comboId = parameterLabelNm.replace( " ", "_" );
			comboId = comboId + "_gwt_uid_";
			ComboBoxHelper.select( comboId, paramFieldValue );
		}
		else if ( isEntityPresentInUI( parameterLabelNm ) )
		{
			String entityIconXpath = "//div[@class='roc-trigger roc-search-trigger' and @id='trigger-" + parameterLabelNm + "']";
			entitySearch( entityIconXpath, paramFieldValue );
		}
		else if ( isTextBoxPresentInUI( parameterLabelNm ) )
			TextBoxHelper.type( parameterLabelNm, paramFieldValue );
	}

	/*
	 * This method is for configure entity search
	 */
	private void entitySearch( String entityIconId, String value ) throws Exception
	{
		if ( ValidationHelper.isNotEmpty( value ) )
		{
			psGenericHelper.waitforEntityElement();
			EntityComboHelper.clickEntityIcon( entityIconId );
			GenericHelper.waitForLoadmask( searchScreenWaitSec );
			if ( psGenericHelper.isPopupHeaderElementPresent( "Point to Point" ) )
				dataSelectionHelper.bandSelection( value );
			else if ( psGenericHelper.isPopupHeaderElementPresent( "Tariff Name" ) )
				dataSelectionHelper.tariffSelection( value );
			else if ( psGenericHelper.isPopupHeaderElementPresent( "Switch" ) )
				dataSelectionHelper.routeSelection( value );
			else if ( ElementHelper.isElementPresent( "//div[text()='Stream Search']" ) )
				dataSelectionHelper.streamSelection( value );
			else if ( psGenericHelper.isPopupHeaderElementPresent( "Parent Element" ) )
				dataSelectionHelper.elementSelection( value );
			else if ( psGenericHelper.isPopupHeaderElementPresent( "Account Name" ) )
				dataSelectionHelper.accountSelection( value );
		}
	}

	/*
	 * This method is for is text present in UI
	 */
	private boolean isTextBoxPresentInUI( String parameterLabelNm ) throws Exception
	{
		return TextBoxHelper.isPresent( parameterLabelNm );
	}

	/*
	 * This method is for is Combobox present in UI
	 */
	private boolean isComboBoxPresentInUI( String parameterLabelNm ) throws Exception
	{
		String comboId = parameterLabelNm.replace( " ", "_" );
		comboId = comboId + "_gwt_uid_";
		return ComboBoxHelper.isPresent( comboId );
	}

	/*
	 * This method is for is Entity present in UI
	 */
	private boolean isEntityPresentInUI( String parameterLabelNm ) throws Exception
	{

		String entityIconXpath = "//div[@class='roc-trigger roc-search-trigger' and @id='trigger-" + parameterLabelNm + "']";
		return EntityComboHelper.isPresent( entityIconXpath );
	}

}
