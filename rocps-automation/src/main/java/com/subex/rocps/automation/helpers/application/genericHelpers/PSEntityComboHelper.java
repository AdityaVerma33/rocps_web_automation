package com.subex.rocps.automation.helpers.application.genericHelpers;

import org.openqa.selenium.WebElement;

import com.subex.automation.helpers.component.ButtonHelper;
import com.subex.automation.helpers.component.ElementHelper;
import com.subex.automation.helpers.component.EntityComboHelper;
import com.subex.automation.helpers.component.EntitySearchHelper;
import com.subex.automation.helpers.component.GenericHelper;
import com.subex.automation.helpers.component.GridHelper;
import com.subex.automation.helpers.component.LabelHelper;
import com.subex.automation.helpers.component.MouseHelper;
import com.subex.automation.helpers.component.SearchGridHelper;
import com.subex.automation.helpers.data.ValidationHelper;
import com.subex.automation.helpers.util.FailureHelper;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;

public class PSEntityComboHelper extends PSAcceptanceTest
{
	private static String gridId = "SearchGrid";
	private static String gridWrapper = "popupWindow";
	static PSGenericHelper psGenericHelper = new PSGenericHelper();

	public static void selectUsingGridFilterTextBox( String iconWrapperId, String iconIdOrXpath, String entityScreenTitle, String txtBoxIdOrXpath, String value, String valueColumnHeader ) throws Exception
	{
		try
		{
			if ( ValidationHelper.isNotEmpty( value ) )
			{
				iconWrapperId = GenericHelper.getORProperty( iconWrapperId );
				iconIdOrXpath = GenericHelper.getORProperty( iconIdOrXpath );
				GenericHelper.waitForLoadmask( searchScreenWaitSec );
				EntityComboHelper.clickEntityIcon( iconWrapperId, iconIdOrXpath );
				selectUsingGridFilterTextBox( entityScreenTitle, txtBoxIdOrXpath, value, valueColumnHeader );
			}
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

	public static void selectUsingGridFilterTextBox( String iconIdOrXpath, String entityScreenTitle, String txtBoxIdOrXpath, String value, String valueColumnHeader ) throws Exception
	{
		try
		{
			if ( ValidationHelper.isNotEmpty( value ) )
			{
				iconIdOrXpath = GenericHelper.getORProperty( iconIdOrXpath );
				GenericHelper.waitForLoadmask( searchScreenWaitSec );
				EntityComboHelper.clickEntityIcon( iconIdOrXpath );
				selectUsingGridFilterTextBox( entityScreenTitle, txtBoxIdOrXpath, value, valueColumnHeader );
			}
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

	public static void selectUsingSearchTextBox( String iconIdOrXpath, String entityScreenTitle, String txtBoxIdOrXpath, String value, String valueColumnHeader ) throws Exception
	{
		try
		{
			if ( ValidationHelper.isNotEmpty( value ) )
			{
				iconIdOrXpath = GenericHelper.getORProperty( iconIdOrXpath );
				GenericHelper.waitForLoadmask( searchScreenWaitSec );
				EntityComboHelper.clickEntityIcon( iconIdOrXpath );

				selectUsingSearchTextBox( entityScreenTitle, txtBoxIdOrXpath, value, valueColumnHeader );
			}
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

	public static void selectUsingSearchTextBox( String entityScreenTitle, String txtBoxIdOrXpath, String value, String valueColumnHeader ) throws Exception
	{
		try
		{
			if ( ValidationHelper.isNotEmpty( value ) )
			{
				GenericHelper.waitForLoadmask( searchScreenWaitSec );
				psGenericHelper.waitforPopupHeaderElement( gridWrapper, gridId, "S.No." );
				if ( ButtonHelper.isPresent( "ClearButton" ) )
				{
					ButtonHelper.click( "ClearButton" );
					GenericHelper.waitForLoadmask( searchScreenWaitSec );
				}

				int row = SearchGridHelper.searchWithTextBox( txtBoxIdOrXpath, value, valueColumnHeader );
				selectValue( row, value, valueColumnHeader );
			}
		}
		catch ( AssertionError e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

	public static void selectUsingGridFilterTextBox( String entityScreenTitle, String txtBoxIdOrXpath, String value, String valueColumnHeader ) throws Exception
	{
		try
		{
			if ( ValidationHelper.isNotEmpty( value ) )
			{
				GenericHelper.waitForLoadmask( searchScreenWaitSec );
				psGenericHelper.waitforPopupHeaderElement( gridWrapper, gridId, "S.No." );
				GenericHelper.waitForLoadmask( searchScreenWaitSec );

				if ( ButtonHelper.isPresent( "ClearButton" ) )
				{
					ButtonHelper.click( "ClearButton" );
					GenericHelper.waitForLoadmask( searchScreenWaitSec );
				}

				int row = SearchGridHelper.gridFilterSearchWithTextBox( txtBoxIdOrXpath, value, valueColumnHeader );
				selectValue( row, value, valueColumnHeader );
			}
		}
		catch ( AssertionError e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

	private static void selectValue( int row, String value, String valueColumnHeader ) throws Exception
	{
		try
		{
			if ( row > 0 )
			{
				GenericHelper.waitForLoadmask( searchScreenWaitSec );
				GridHelper.clickRow( gridWrapper, gridId, value, valueColumnHeader );
				GenericHelper.waitForLoadmask( searchScreenWaitSec );
				ButtonHelper.click( "OKButton" );
				GenericHelper.waitForLoadmask( searchScreenWaitSec );
				psGenericHelper.waitforPopupHeaderElementToDisappear( gridWrapper, gridId, "S.No." );
			}
			else
				FailureHelper.failTest( "Row with value '" + value + "' not found in Entity Search screen" );
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

	/**
	 * This method is used to click Entity search icon.
	 * @param iconId - id of entity search icon.
	 * @throws Exception
	 */
	public static void clickEntityIcon( String iconIdOrXpath ) throws Exception
	{
		try
		{
			iconIdOrXpath = GenericHelper.getORProperty( iconIdOrXpath );
			WebElement element = null;
			GenericHelper.waitForLoadmask( searchScreenWaitSec );

			if ( iconIdOrXpath.startsWith( "/" ) )
				element = ElementHelper.getElement( iconIdOrXpath );
			else
				element = ElementHelper.getElement( "trigger-" + iconIdOrXpath );

			if ( element == null )
				element = ElementHelper.getElement( iconIdOrXpath, "EntityCombo_Icon" );

			if ( element != null )
			{
				MouseHelper.click( element );
				GenericHelper.waitForLoadmask( searchScreenWaitSec );
			}
			else
			{
				FailureHelper.failTest( "Entity Icon '" + iconIdOrXpath + "' not found." );
			}
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

}
