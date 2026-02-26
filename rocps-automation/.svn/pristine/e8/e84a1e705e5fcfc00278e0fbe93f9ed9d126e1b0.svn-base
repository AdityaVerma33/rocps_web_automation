package com.subex.rocps.automation.helpers.application.filters;

import org.openqa.selenium.WebElement;

import com.subex.rocps.automation.helpers.application.genericHelpers.PSGenericHelper;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;

import com.subex.automation.helpers.component.ComboBoxHelper;
import com.subex.automation.helpers.component.ElementHelper;
import com.subex.automation.helpers.component.GenericHelper;
import com.subex.automation.helpers.component.GridHelper;
import com.subex.automation.helpers.component.MouseHelper;
import com.subex.automation.helpers.component.SearchGridHelper;
import com.subex.automation.helpers.component.TextBoxHelper;
import com.subex.automation.helpers.componentHelpers.GridElementHelper;
import com.subex.automation.helpers.componentHelpers.SearchHelper;
import com.subex.automation.helpers.componentHelpers.TextBoxElementHelper;
import com.subex.automation.helpers.data.ValidationHelper;
import com.subex.automation.helpers.util.FailureHelper;

public class PSSearchGridHelper extends PSAcceptanceTest
{
	/*
	 * This method is for search gridfilter textbox
	 */
	public static int gridFilterSearchWithTextBox( String txtBoxIdOrXpath, String value, String columnHeader ) throws Exception
	{
		try
		{
			int row = 0;
			PSGenericHelper genericObj = new PSGenericHelper();
			SearchHelper searchHelper = new SearchHelper();
			if ( ValidationHelper.isNotEmpty( value ) )
			{
				boolean isWrapperPresent = ElementHelper.isElementPresent( "popupWindow" );

				if ( isWrapperPresent )
				{
					genericObj.waitforPopupHeaderElement( columnHeader );
					clickFilterIcon( "popupWindow", "SearchGrid", columnHeader );
				}
				else
				{
					genericObj.waitforHeaderElement( columnHeader );
					clickFilterIcon( "SearchGrid", columnHeader );
				}
				TextBoxHelper.type( "Grid_Filter_Panel", txtBoxIdOrXpath, value );
				GenericHelper.waitForLoadmask( searchScreenWaitSec );
				searchHelper.clickSearch();
				if ( isWrapperPresent )
					row = GridHelper.getRowNumber( "popupWindow", "SearchGrid", value, columnHeader );
				else
					row = GridHelper.getRowNumber( "SearchGrid", value, columnHeader );
			}

			return row;
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

	/*
	 * This method is for search gridfilter combobox
	 */
	public static int gridFilterSearchWithComboBox( String comboIdOrXpath, String value, String columnHeader ) throws Exception
	{
		try
		{
			int row = 0;
			PSGenericHelper genericObj = new PSGenericHelper();
			if ( ValidationHelper.isNotEmpty( value ) )
			{
				SearchHelper searchHelper = new SearchHelper();
				boolean isWrapperPresent = ElementHelper.isElementPresent( "popupWindow" );

				if ( isWrapperPresent )
				{
					genericObj.waitforPopupHeaderElement( columnHeader );
					searchHelper.clickFilterIcon( "popupWindow", "SearchGrid", columnHeader );
				}
				else
				{
					genericObj.scrollforHeaderElement( columnHeader );
					genericObj.waitforHeaderElement( columnHeader );
					clickFilterIcon( "SearchGrid", columnHeader );
				}
				ComboBoxHelper.select( "Grid_Filter_Panel", comboIdOrXpath, value );
				searchHelper.clickSearch();

				if ( isWrapperPresent )
					row = GridHelper.getRowNumber( "popupWindow", "SearchGrid", value, columnHeader );
				else
					row = GridHelper.getRowNumber( "SearchGrid", value, columnHeader );
			}

			return row;
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

	/*
	 * This method is to click filter icon
	 */
	private static void clickFilterIcon( String gridId, String columnHeader ) throws Exception
	{
		try
		{
			gridId = GenericHelper.getORProperty( gridId );
			WebElement headerElement = GridElementHelper.getHeaderElement( gridId, columnHeader );

			clickFilterIcon( headerElement, gridId, columnHeader );
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}
	public static void clickFilterIcon(String gridWrapper, String gridId, String columnHeader) throws Exception {
		try {
			gridWrapper = GenericHelper.getORProperty(gridWrapper);
			gridId = GenericHelper.getORProperty(gridId);
			GenericHelper.waitForAJAXReady(searchScreenWaitSec);
			WebElement headerElement = GridElementHelper.getHeaderElement(gridWrapper, gridId, columnHeader);
			
			clickFilterIcon(headerElement, gridId, columnHeader);
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}

	private static void clickFilterIcon( WebElement headerElement, String gridId, String columnHeader ) throws Exception
	{
		try
		{

			if ( headerElement != null )
			{
				String filterLocator = or.getProperty( "Grid_Filter_Icon" );
				WebElement filterElement = ElementHelper.getElement( headerElement, filterLocator );

				if ( filterElement != null )
				{
					//ElementHelper.scrollToView( filterElement, true );
					if ( !ElementHelper.isClickable( filterElement ) )
						ElementHelper.waitForClickableElement( filterElement, searchScreenWaitSec );
					MouseHelper.click( filterElement );
					GenericHelper.waitForLoadmask( searchScreenWaitSec );
					for ( int index = 0; index < 4; index++ )
					{
						try
						{
							if ( !ElementHelper.isElementPresent( or.getProperty( "Grid_Filter_Panel" ) ) )
							{
								headerElement = GridElementHelper.getHeaderElement( gridId, columnHeader );
								filterElement = ElementHelper.getElement( headerElement, filterLocator );

								if ( !ElementHelper.isClickable( filterElement ) )
									ElementHelper.waitForClickableElement( filterElement, searchScreenWaitSec );
								MouseHelper.click( filterElement );
								GenericHelper.waitForLoadmask( searchScreenWaitSec );
							}
							else
								break;
						}
						catch ( Exception e )
						{
							e.printStackTrace();
						}
					}
				}
				else
					FailureHelper.failTest( "Filter icon not found for column '" + columnHeader + "' in grid '" + gridId + "'" );
			}
			else
				FailureHelper.failTest( "Expected column '" + columnHeader + "' not found in grid '" + gridId + "'" );

		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

	public static boolean isValuePresent( String gridId, String value, String columnHeader ) throws Exception
	{
		try
		{
			gridId = GenericHelper.getORProperty( gridId );
			int col = GridElementHelper.getColumn( gridId, columnHeader );
			int row = GridElementHelper.getRow( gridId, value, col );

			if ( row == 0 )
				return false;
			else
				return true;
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

	public static boolean psisValuePresent( String gridId, String value, String columnHeader ) throws Exception
	{
		boolean flag = false;

		for ( int index = 0; index < 4; index++ )
		{
			try
			{
				flag = GridHelper.isValuePresent( "", value, "Name" );
				break;
			}
			catch ( Exception e )
			{
				e.printStackTrace();
			}
			if ( index > 4 )
				FailureHelper.failTest( "Value is not present in search screen" );

		}
		return flag;
	}
	public static int searchWithTextBox(String txtBoxIdOrXpath, String value, String columnHeader) throws Exception {
		try {
			PSGenericHelper genericObj = new PSGenericHelper();
			int row = 0;
			String gridWrapper="popupWindow";
			String gridId="SearchGrid";
			if (ValidationHelper.isNotEmpty(value)) {
				SearchHelper searchHelper = new SearchHelper();
				boolean isWrapperPresent = ElementHelper.isElementPresent(gridWrapper);
				
				if (isWrapperPresent) {
					TextBoxHelper.type(gridWrapper, txtBoxIdOrXpath, value);
					searchHelper.clickSearch();
					genericObj.waitforPopupHeaderElement( columnHeader );
					row = GridHelper.getRowNumber(gridWrapper, gridId, value, columnHeader);
				}
				else {
					TextBoxHelper.type(txtBoxIdOrXpath, value);
					searchHelper.clickSearch();
					genericObj.waitforHeaderElement( columnHeader );
					row = GridHelper.getRowNumber(gridId, value, columnHeader);
				}
			}
			
			return row;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	

}
