package com.subex.rocps.automation.helpers.application.roaming.util;

import com.subex.rocps.automation.helpers.application.genericHelpers.PSGenericHelper;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;
import com.subex.rocps.automation.utils.PSStringUtils;
import com.subex.automation.helpers.component.ButtonHelper;
import com.subex.automation.helpers.component.ComboBoxHelper;
import com.subex.automation.helpers.component.ElementHelper;
import com.subex.automation.helpers.component.GenericHelper;
import com.subex.automation.helpers.component.TextBoxHelper;
import com.subex.automation.helpers.data.ValidationHelper;

abstract public class RoamingRecordsUtil extends PSAcceptanceTest
{
	PSGenericHelper psGenericHelper = new PSGenericHelper();
	PSStringUtils psStringUtils = new PSStringUtils();

	/*
	 * This method is forvalidate file name
	 */
	protected void validateFileName( String textBoxId, String textboxValue ) throws Exception
	{
		if ( ValidationHelper.isNotEmpty( textboxValue ) )
		{
			String fileNmActual = TextBoxHelper.getValue( textBoxId );
			assertEquals( fileNmActual, textboxValue, "File name is not matched" );
		}
	}

	/*
	 * This method is for filter selection
	 */
	protected void filterSelection( String filterComboId, String filterValue, String waitForPageLoadXpath ) throws Exception
	{
		ComboBoxHelper.select( filterComboId, filterValue );
		clickOnSearchButton( waitForPageLoadXpath );
	}

	protected void clickOnSearchButton( String waitForPageLoadXpath ) throws Exception
	{
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		ButtonHelper.clickIfEnabled( "SearchButton" );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		ElementHelper.waitForElement( GenericHelper.getORProperty( waitForPageLoadXpath ), searchScreenWaitSec );
	}

	/*
	 * This method is for filter selection for tap error
	 */
	protected void filterSelection( String contextComboId, String contextValue, String eventDtTextId, String eventDtVal, String severityComboId, String severityVal, String waitForPageLoadXpath ) throws Exception
	{
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		ComboBoxHelper.select( contextComboId, contextValue );
		if ( ValidationHelper.isNotEmpty( eventDtVal ) )
			TextBoxHelper.type( eventDtTextId, eventDtVal );
		if ( ValidationHelper.isNotEmpty( severityVal ) )
			ComboBoxHelper.select( severityComboId, severityVal );
		ButtonHelper.click( "SearchButton" );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		psGenericHelper.waitforHeaderElement( "Error Code" );
		ElementHelper.waitForElement( GenericHelper.getORProperty( waitForPageLoadXpath ), searchScreenWaitSec );
	}
}
