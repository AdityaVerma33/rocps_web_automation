package com.subex.rocps.automation.helpers.application.deal.deal;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
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
import com.subex.automation.helpers.component.ImageHelper;
import com.subex.automation.helpers.component.MouseHelper;
import com.subex.automation.helpers.component.PopupHelper;
import com.subex.automation.helpers.component.TextBoxHelper;
import com.subex.automation.helpers.data.DateHelper;
import com.subex.automation.helpers.data.ValidationHelper;
import com.subex.automation.helpers.util.FailureHelper;

public class DealRatesImpl extends PSAcceptanceTest
{
	protected String trafficType;
	protected String bandGroup;
	protected String bandName;
	protected String showBlankRates;
	protected String rateType;
	protected String tier;
	protected String currency;
	protected String rateChange;
	protected String rateNames;
	protected String rate;
	protected int colSize;
	protected int paramVal;
	String dealPeriod;
	protected String[] rateNamesArr;
	protected String[] tierArr;
	protected String[] rateArr;
	protected String direction;
	protected Map<String, String> map;
	PSStringUtils strObj = new PSStringUtils();
	PSGenericHelper genericObj = new PSGenericHelper();
	protected ExcelHolder excelHolderObj = null;
	protected Map<String, String> dealMap = null;
	protected Map<String, String> outMap = new HashMap<String, String>();
	protected Map<String, String> inMap = new HashMap<String, String>();

	public DealRatesImpl( Map<String, String> map ) throws Exception
	{
		this.map = map;
	}

	/*
	 * This method is for rate config
	 */

	public void rateConfig( String ViewEditTestCase, int index ) throws Exception
	{
		initializeVaribles( map );
		initializeArray();
		GridHelper.clickRow( "SearchGrid", 1, "Account" );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		if ( index == 0 )
			timeLineEdit( ViewEditTestCase, direction );

		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		viewEditRate( index );
		changeRatesCurrency();
		//	rateValidation();

	}

	public void rateValidation() throws Exception
	{
		int row = GridHelper.getRowNumber( "tierRateGrid", bandName, 2 );
		ArrayList<String> actualVal = GridHelper.getRowValues( "tierRateGrid", 1 );
		String actualRowVal = strObj.stringformation( actualVal );
		System.out.println( "Rate Grid Actual Value :" + actualRowVal );
	}

	/*
	 * This method is to view/edit rate
	 */
	public void viewEditRate( int index ) throws Exception
	{

		ComboBoxHelper.select( "PS_Detail_Deal_rateTrafficType_cmboID", trafficType );
		ComboBoxHelper.select( "PS_Detail_DealRate_bandGrp_comboID", bandGroup );
		if ( PopupHelper.isTextPresent( "Do you want to save the changes?" ) )
			ButtonHelper.click( "YesButton" );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		TextBoxHelper.type( "PS_Detail_DealRate_bandName_txtID", bandName );
		if ( ValidationHelper.isTrue( showBlankRates ) )
			CheckBoxHelper.check( "PS_Detail_Deal_showBlankRates_chckbxID" );
		ButtonHelper.click( "SearchButton" );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );

		Thread.sleep( 2000 );
		if ( index == 0 )
		{
			ElementHelper.waitForElement( "PS_Detail_Deal_changeRatesCurr_Xpath", searchScreenWaitSec );
			ButtonHelper.click( "PS_Detail_Deal_changeRatesCurr_BtnXpath" );
			GenericHelper.waitForLoadmask( searchScreenWaitSec );
		}
	}

	/*
	 * This method is for change rates currency
	 */
	public void changeRatesCurrency() throws Exception
	{

		ComboBoxHelper.select( "rateChange_gwt_uid_", rateChange );
		ComboBoxHelper.select( "PS_Detail_DealRate_Currency_txtID", currency );
		ComboBoxHelper.select( "rateType_gwt_uid_", rateChange );
		for ( int i = 0; i < tierArr.length; i++ )
		{
			ComboBoxHelper.select( "PS_Detail_DealRate_dealTier_comboID", tierArr[i] );
			ratePerTier( rateArr[i] );
			ElementHelper.waitForElement( "PS_Detail_DealRate_apply_BtnXapth", searchScreenWaitSec );
			ButtonHelper.click( "Apply" );
			GenericHelper.waitForLoadmask( searchScreenWaitSec );
		}

	}

	/*
	 * This method is for rate per tier config
	 */
	public void ratePerTier( String ratesArr ) throws Exception
	{
		String[] ratesAr = ratesArr.split( secondLevelDelimiter );
		String rateNameTxtID = getRateNameTxtId( rateNamesArr[0] );
		TextBoxHelper.type( rateNameTxtID, ratesAr[0] );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		if ( rateNamesArr.length > 0 )
		{
			String rateName2TxtID = getRateNameTxtId( rateNamesArr[1] );
			TextBoxHelper.type( rateName2TxtID, ratesAr[1] );
			GenericHelper.waitForLoadmask( searchScreenWaitSec );
		}
	}
	/*
	 * This method is for ratename txtid finding
	 */
	public String getRateNameTxtId( String rateName )
	{

		if ( rateName.equals( "Economy" ) )
			rateName = 1 + rateName;
		else if ( rateName.equals( "Peak" ) )
			rateName = 2 + rateName;
		else if ( rateName.equals( "Weekend" ) )
			rateName = 3 + rateName;
		return rateName;
	}

	/*
	 * This method is for new deal period
	 * PS_Detail_Deal_newOutDealXpath WHEN TWO TIMELINE PRESENT
	 * PS_Detail_Deal_inXpath WHEN ONE TIMELINE PRESENT
	 * 
	 */
	public void newDealPeriod( String newdealPeriod, String direction ) throws Exception
	{

		WebElement inelement = ElementHelper.getElement( or.getProperty( "PS_Detail_Deal_newInDealXpath" ) );
		WebElement outElement = ElementHelper.getElement( or.getProperty( "PS_Detail_Deal_newOutDealXpath" ) );

		if ( ElementHelper.isElementPresent( "//*[@id='timeLineFormContainer']//table/tbody/tr[2]/td[4]//div[@id='Label']" ) ) // If two timeline present in and out
		{

			if ( direction.contains( "In" ) && !ElementHelper.isElementPresent( "PS_Detail_Deal_inXpath" ) )
				clickTimeLine( inelement, "New Deal Period" );
			else if ( direction.contains( "In" ) && ElementHelper.isElementPresent( "PS_Detail_Deal_inXpath" ) )
				newdealPeriodTxtBox( newdealPeriod, direction );

			if ( direction.contains( "Out" ) && !ElementHelper.isElementPresent( "PS_Detail_Deal_outXpath" ) )
				clickTimeLine( outElement, "New Deal Period" );
			else if ( direction.contains( "Out" ) && ElementHelper.isElementPresent( "PS_Detail_Deal_outXpath" ) )
				newdealPeriodTxtBox( newdealPeriod, direction );
		}
		else
		{
			if ( !ElementHelper.isElementPresent( "PS_Detail_Deal_inXpath" ) )
				clickTimeLine( inelement, "New Deal Period" );
			else if ( ElementHelper.isElementPresent( "PS_Detail_Deal_inXpath" ) )
				newdealPeriodTxtBox( newdealPeriod, "In" );
		}

	}

	private void clickTimeLine( WebElement element, String rateNameTC ) throws Exception
	{
		ElementHelper.waitForClickableElement( element, searchScreenWaitSec );
		ElementHelper.click( element );
		String xpath = "//div[text()='" + rateNameTC + "']";
		ElementHelper.waitForElement( xpath, searchScreenWaitSec );
		ButtonHelper.click( xpath );
		GenericHelper.waitForLoadmask();
		ButtonHelper.click( "SearchButton" );
		GenericHelper.waitForLoadmask();
	}

	private void clickTimeLineEdit( WebElement element, String rateNameTC ) throws Exception
	{
		ElementHelper.waitForClickableElement( element, searchScreenWaitSec );
		ElementHelper.click( element );
		if ( !ElementHelper.isElementPresent( "PS_Detail_Deal_timeLineSubMenu_Xpath" ) )
			ElementHelper.click( element );
		if ( !ElementHelper.isElementPresent( "PS_Detail_Deal_timeLineSubMenu_Xpath" ) )
			ElementHelper.click( element );
		String xpath = "//div[text()='" + rateNameTC + "']";
		ElementHelper.waitForElement( xpath, searchScreenWaitSec );
		ButtonHelper.click( xpath );
		GenericHelper.waitForLoadmask();
	}

	private void newdealPeriodTxtBox( String dealPeriod, String direction ) throws Exception
	{
		timeLineEdit( "New Deal Period", direction );
		if ( PopupHelper.isTextPresent( "Rates are not configured for selected deal period" ) )
			FailureHelper.failTest( "Rates are not configured for selected deal period" );
		if ( PopupHelper.isPresent() )
			TextBoxHelper.type( "PS_Detail_DealRate_dealPeriod_txtID", dealPeriod );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
	}

	/*
	 * This method is for time line edit
	 */

	public void timeLineEdit( String rateNameTC, String direction ) throws Exception
	{
		WebElement inelement = ElementHelper.getElement( or.getProperty( "PS_Detail_Deal_inXpath" ) );
		WebElement outElement = ElementHelper.getElement( or.getProperty( "PS_Detail_Deal_outXpath" ) );

		if ( ElementHelper.isElementPresent( "//div[@id='timeLineFormContainer']//table/tbody/tr[2]/td[4]//div[contains(@class,'roc-timeline') and @id='Label']" ) )
		{

			if ( direction.contains( "In" ) )
				clickTimeLineEdit( inelement, rateNameTC );
			if ( direction.contains( "Out" ) )
				clickTimeLineEdit( outElement, rateNameTC );
		}
		else
			clickTimeLineEdit( inelement, rateNameTC );

	}

	/*
	 * Method: Clicking edit action on the timeline
	 */
	/*public void timeLineEdit( String rateNameTC , String dealPeriod) throws Exception
	{
		if ( rateNameTC.contains( "TierRate" ) )
			rateNameTC = "View/Edit Tier Rate";
		if ( rateNameTC.contains( "Short" ) )
			rateNameTC = "View/Edit shortfall rate";
		if ( rateNameTC.contains( "External" ) )
			rateNameTC = "View/Edit external Rate";
	
		List<String> xpaths = new ArrayList<String>();
		String oldTL = "//div[@class='roc-timeline-odd']";
		String newTL = "//div[@class='roc-timeline-even']";
	
		xpaths.add( oldTL );
		xpaths.add( newTL );
		for ( String str : xpaths )
		{
			WebElement element = ElementHelper.getElement( str );
			if ( element != null )
			{
				String datenew = gettoolTipText( str );
				String startdate = datenew.substring( 0, 10 );
				String enddate = datenew.substring( 23, 32 );
				SimpleDateFormat formatter = new SimpleDateFormat( "MM/dd/yyyy" );
				Date startDate = formatter.parse( startdate );
				Date endDate = formatter.parse( enddate );
				Date deDate = formatter.parse( dealPeriod );
				Thread.sleep( 2000 );
				if ( isWithinRange( deDate, startDate, endDate ) )
				{
					element.click();
					break;
				}
			}
	
		}
	
		String xpath = "//div[text()='" + rateNameTC + "']";
		ButtonHelper.click( xpath );
		GenericHelper.waitForLoadmask();	
	}
	
	public String gettoolTipText( String xpath ) throws Exception
	{
		MouseHelper.mouseOver( xpath );
		Thread.sleep(2000 );
		String val = ElementHelper.getAttribute( "//div[@class='roc-tooltip roc-tooltip-popup']", "textContent" );
		return val;
	}
	
	boolean isWithinRange( Date testDate, Date startDate, Date endDate )
	{
		if ( testDate.after( startDate ) && testDate.before( endDate ) )
		{
			System.out.println( "True" );
			return true;
		}
		else
		{
			System.out.println( "false" );
			return false;
		}
	}
	*/

	/*
	 * This method is to initialize array
	 */
	public void initializeArray() throws Exception
	{
		tierArr = strObj.stringSplitFirstLevel( tier );
		rateNamesArr = strObj.stringSplitFirstLevel( rateNames );
		rateArr = strObj.stringSplitFirstLevel( rate );

	}

	/*
	 * This method is to initialize intance variables
	 */
	public void initializeVaribles( Map<String, String> map ) throws Exception
	{
		trafficType = ExcelHolder.getKey( map, "TrafficType" );
		bandGroup = ExcelHolder.getKey( map, "BandGroup" );
		bandName = ExcelHolder.getKey( map, "BandName" );
		showBlankRates = ExcelHolder.getKey( map, "ShowBlankRates" );
		rateType = ExcelHolder.getKey( map, "RateType" );
		tier = ExcelHolder.getKey( map, "Tier" );
		currency = ExcelHolder.getKey( map, "Currency" );
		rateChange = ExcelHolder.getKey( map, "RateChange" );
		rateNames = ExcelHolder.getKey( map, "RateNames" );
		rate = ExcelHolder.getKey( map, "Rate" );
		dealPeriod = ExcelHolder.getKey( map, "DealPeriod" );
		direction = ExcelHolder.getKey( map, "Direction" );

	}
}
