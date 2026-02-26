package com.subex.rocps.automation.helpers.application.bills.creditnotes;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.subex.rocps.automation.helpers.application.filters.GridFilterSearchHelper;
import com.subex.rocps.automation.helpers.application.genericHelpers.PSGenericHelper;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;

import com.subex.automation.helpers.component.ButtonHelper;
import com.subex.automation.helpers.component.CalendarHelper;
import com.subex.automation.helpers.component.CheckBoxHelper;
import com.subex.automation.helpers.component.ComboBoxHelper;
import com.subex.automation.helpers.component.ElementHelper;
import com.subex.automation.helpers.component.GenericHelper;
import com.subex.automation.helpers.component.GridHelper;
import com.subex.automation.helpers.component.SearchGridHelper;
import com.subex.automation.helpers.component.TextBoxHelper;
import com.subex.automation.helpers.componentHelpers.SearchHelper;
import com.subex.automation.helpers.data.ValidationHelper;
import com.subex.automation.helpers.db.DBHelper;
import com.subex.automation.helpers.report.Log4jHelper;
import com.subex.automation.helpers.util.FailureHelper;

public class CreditNotesSearchImpl extends PSAcceptanceTest
{
	GridFilterSearchHelper gridHelperObj = new GridFilterSearchHelper();

	/*
	 * This method is to check if credit notes is already present
	 */
	public boolean creditNotesFilterSelection( String billProfile, String searchGridCurrency, String creditAmount, String includeBill ) throws Exception
	{
		boolean flag = false;
		ButtonHelper.click( "ClearButton" );
		gridHelperObj.billProfileAdvanceFilter( "SearchGrid", "Bill Profile", billProfile );

		String currencyActual = retriveCurrencyValue( searchGridCurrency );
		SearchGridHelper.gridFilterSearchWithComboBox( "PS_Detail_creditNote_currency_comboId", currencyActual, "Currency" );
		//statusFilter( "PS_Detail_creditNote_status_gridFilterID", "PS_Detail_creditNote_status_txtbxID", "Draft", "Status" );	
		calender( "PS_Detail_creditNote_calender_gridFilterID" );
		if ( !includeBill.isEmpty() )
			SearchGridHelper.gridFilterSearchWithComboBox( "pcrdIncludeInNextBilFl_gwt_uid_", includeBill, "Included in Bill" );
		//if ( !creditAmount.isEmpty() )
		//	TextBoxHelper.type( "amountPanel", "dummyCrdTxnAmt", creditAmount );
		ButtonHelper.click( "SearchButton" );
		int row = GridHelper.getRowCount( "searchGrid" );
		if ( row > 0 )
		{
			flag = true;
		}
		return flag;

	}

	/*
	 * This method is to check if bill profile is present
	 */
	public boolean billperiodSearch( String billPeriod, String billProfile ) throws Exception
	{
		if ( ValidationHelper.isNotEmpty( billPeriod ) )
			setDate( billPeriod );

		ButtonHelper.click( "SearchButton" );
		GenericHelper.waitForLoadmask();
		latestVersionCheck();
		int row = GridHelper.getRowNumberContains( "SearchGrid", billProfile, "Bill Profile" );
		if ( row > 0 )
			return true;
		else
			return false;
	}

	/*
	 * This method is to validate  the row count before creation and after creation
	 */
	public boolean rowscount( int initalRow ) throws Exception
	{
		boolean flag = false;
		int row = GridHelper.getRowCount( "SearchGrid" );
		int finalRow = row - 1;
		if ( initalRow == finalRow )
		{
			flag = true;
			Log4jHelper.logInfo( "Credit Notes is saved successfuly" );
		}
		return flag;
	}

	public static void setDate( String date ) throws Exception

	{
		String xpath = "//table[@id='dummyValidOnDataProperty']//div[@id='options']/img";

		String type = "On";

		ElementHelper.click( xpath );
		ElementHelper.click( "//div[@id='" + type + "']" );
		driver.findElement( By.xpath( "//input[@id='-fromDateLabel']" ) ).clear();
		driver.findElement( By.xpath( "//input[@id='-fromDateLabel']" ) ).sendKeys( date, Keys.ENTER );
	}

	/*
	 * This method is for latest Version Check
	 */
	public void latestVersionCheck() throws Exception
	{
		String version = ComboBoxHelper.getValue( "PS_Detail_creditNote_version_comboID" );
		if ( !version.contains( "All" ) )
		{
			ComboBoxHelper.select( "PS_Detail_creditNote_version_comboID", "Latest Versions" );
			ButtonHelper.click( "SearchButton" );
		}
	}

	/*
	 * This method is for status Filter search
	 */
	public boolean statusFilter( String filterIconID, String txtBoxId, String value, String columnHeader ) throws Exception
	{
		boolean flag = false;
		SearchHelper searchHelper = new SearchHelper();
		searchHelper.clickFilterIcon( filterIconID );
		TextBoxHelper.type( "Grid_Filter_Panel", txtBoxId, value );
		searchHelper.clickSearch();

		int row = GridHelper.getRowCount( "SearchGrid" );
		if ( row > 0 )
		{
			flag = true;
		}
		return flag;
	}

	/*
	 * This method is for calender Filter search
	 */
	public void calender( String calenderFilterIconID ) throws Exception
	{
		SearchHelper searchHelper = new SearchHelper();
		ElementHelper.scrollToView( "//div[@id='grid_column_header_searchGrid_pcrdTxnDttm']", false );
        GenericHelper.waitForLoadmask();
		searchHelper.clickFilterIcon( calenderFilterIconID );
		CalendarHelper.setToday( "PS_Detail_creditNote_calenderID" );
		ButtonHelper.click( "SearchButton" );

	}

	/*
	 * this method is to retrieve currency value from ref table
	 */
	public String retriveCurrencyValue( String currency ) throws Exception
	{
		DBHelper dbHelper = null;
		ResultSet value;
		String currencyActual = null;

		try
		{
			dbHelper = DBHelper.connectToReferenceDB( false );

			if ( dbHelper != null )
			{
				String query = "select CUR_ISO_CD from CURRENCY where CUR_NAME = ?";
				PreparedStatement stmt = dbHelper.dbConnection.prepareStatement( query );
				stmt.setString( 1, currency );
				ResultSet rs = stmt.executeQuery();

				while ( rs.next() )
				{
					currencyActual = rs.getString( 1 );
				}
			}

			return currencyActual;
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
		finally
		{
			if ( dbHelper != null )
				dbHelper.closeConnection();
		}
	}
}
