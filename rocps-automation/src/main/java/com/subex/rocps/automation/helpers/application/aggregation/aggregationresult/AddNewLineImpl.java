package com.subex.rocps.automation.helpers.application.aggregation.aggregationresult;

import static org.junit.Assert.assertEquals;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

import java.util.Map;

import com.subex.rocps.automation.helpers.application.genericHelpers.PSEntityComboHelper;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;
import com.subex.rocps.automation.utils.ExcelHolder;

import com.subex.automation.helpers.application.NavigationHelper;
import com.subex.automation.helpers.component.ButtonHelper;
import com.subex.automation.helpers.component.CalendarHelper;
import com.subex.automation.helpers.component.CheckBoxHelper;
import com.subex.automation.helpers.component.ComboBoxHelper;
import com.subex.automation.helpers.component.ElementHelper;
import com.subex.automation.helpers.component.EntityComboHelper;
import com.subex.automation.helpers.component.GenericHelper;
import com.subex.automation.helpers.component.GridHelper;
import com.subex.automation.helpers.component.PopupHelper;
import com.subex.automation.helpers.component.TextAreaHelper;
import com.subex.automation.helpers.component.TextBoxHelper;
import com.subex.automation.helpers.data.ValidationHelper;

public class AddNewLineImpl extends PSAcceptanceTest
{

	protected Map<String, String> dataMap;
	protected String aggregationName;
	protected String eventFromDate;
	protected String eventToDate;
	protected String billProfile;
	protected String stream;
	protected String billPeriodDate;
	protected String service;
	protected String tariff;
	protected String band;
	protected String tariffRateName;
	protected String isRatable;
	protected String status;
	protected String itemCode;
	String ratedRevenueCurrency;
	String eventCount;
	String ratedRevenueAmount;
	String usage;
	String billedUsage;
	String currency;
	String rate;
	String transactionAmt;
	String setupAmt;
	String modifieddate;
	String createddate;
	String compute;
	String eventDate;
	String internalComments;
	String externalComments;
	String override;
	String positive;

	public AddNewLineImpl( Map<String, String> agcConfigMap ) throws Exception
	{

		this.dataMap = agcConfigMap;
		initialise();
	}

	/*
	 * Method for initialising the variables
	 */
	private void initialise() throws Exception
	{

		aggregationName = ExcelHolder.getKey( dataMap, "AggregationName" );

		billProfile = ExcelHolder.getKey( dataMap, "BillProfile" );
		stream = ExcelHolder.getKey( dataMap, "Stream" );
		billPeriodDate = ExcelHolder.getKey( dataMap, "BillPeriod" );
		service = ExcelHolder.getKey( dataMap, "Service" );
		tariff = ExcelHolder.getKey( dataMap, "Tariff" );
		band = ExcelHolder.getKey( dataMap, "Band" );
		tariffRateName = ExcelHolder.getKey( dataMap, "TariffRateName" );
		isRatable = ExcelHolder.getKey( dataMap, "IsRatable" );

		//itemCode = ExcelHolder.getKey(dataMap, "ItemCode");
		ratedRevenueCurrency = ExcelHolder.getKey( dataMap, "RatedRevenueCurrency" );
		eventCount = ExcelHolder.getKey( dataMap, "EventCount" );
		ratedRevenueAmount = ExcelHolder.getKey( dataMap, "RatedRevenueAmount" );
		usage = ExcelHolder.getKey( dataMap, "Usage" );
		billedUsage = ExcelHolder.getKey( dataMap, "BilledUsage" );
		currency = ExcelHolder.getKey( dataMap, "Currency" );
		rate = ExcelHolder.getKey( dataMap, "Rate" );
		transactionAmt = ExcelHolder.getKey( dataMap, "TransactionAmt" );
		setupAmt = ExcelHolder.getKey( dataMap, "SetupAmt" );
		modifieddate = ExcelHolder.getKey( dataMap, "ModifiedDate" );
		createddate = ExcelHolder.getKey( dataMap, "CreatedDate" );
		compute = ExcelHolder.getKey( dataMap, "Compute" );
		eventDate = ExcelHolder.getKey( dataMap, "Event Date" );
		internalComments = ExcelHolder.getKey( dataMap, "InternalComments" );
		externalComments = ExcelHolder.getKey( dataMap, "ExternalComments" );
		override = ExcelHolder.getKey( dataMap, "Override" );
		positive = ExcelHolder.getKey( dataMap, "Positive" );
	}

	public void newlineConfig() throws Exception
	{

		TextBoxHelper.type( "//table[@id='evt_dttm']/tbody/tr/td[1]/input", eventDate );

		EntityComboHelper.select( "stm_id", "Stream Search", stream, "Name" );
		//EntityComboHelper.select( "pbip_id", "BillProfile Search", billProfile, "Bill Profile Name" );
		PSEntityComboHelper.selectUsingGridFilterTextBox( "bnd_id", "Band Search", "bndName", band, "Name" );
		ComboBoxHelper.select( "Add Newline", "Tariff_Rate_Name_gwt_uid_", tariffRateName );

		EntityComboHelper.selectUsingSearchTextBox( "tariff", "Tariff Search", "tffName", tariff, "Tariff Name" );

		billPeriodSearch();
		if ( ValidationHelper.isFalse( isRatable ) )
			CheckBoxHelper.uncheck( "rev_ratable_fl_InputElement" );

		PSEntityComboHelper.selectUsingGridFilterTextBox( "service", "Service Search", "psvcName", service, "Name" );
		ComboBoxHelper.select( "Currency_gwt_uid_716_chzn", ratedRevenueCurrency );
		TextBoxHelper.type( "rev_count", eventCount );

		TextBoxHelper.type( "res_usage", usage );
		TextBoxHelper.type( "res_billed_usage", billedUsage );
		ButtonHelper.click( "//*[text()='Fetch Rate and Currency']" );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		if ( ValidationHelper.isTrue( compute ) )
		{
			ButtonHelper.click( "Compute" );
			assertEquals( TextBoxHelper.getValue( "voic_txn_amt" ), transactionAmt );
			assertEquals( TextBoxHelper.getValue( "rev_revenue_amount" ), ratedRevenueAmount );
		}
		else
			assertEquals( TextBoxHelper.getValue( "voic_rate" ), rate );
		assertEquals( ComboBoxHelper.getValue( "Currency_gwt_uid_" ), currency );
		assertEquals( TextBoxHelper.getValue( "voic_setup_amt" ), setupAmt );

		if ( ValidationHelper.isTrue( override ) )
		{
			CheckBoxHelper.check( "Override Rate_InputElement" );
			TextBoxHelper.type( "voic_rate", rate );
			ComboBoxHelper.select( "Currency_gwt_uid_", currency );
			TextBoxHelper.type( "voic_setup_amt", setupAmt );
		}

		if ( ValidationHelper.isNotEmpty( internalComments ) )
			TextAreaHelper.type( "InternalCommentTextArea", internalComments );
		if ( ValidationHelper.isNotEmpty( externalComments ) )
			TextAreaHelper.type( "ExternalCommentTextArea", externalComments );
		TextBoxHelper.type( "voic_txn_amt", transactionAmt );
		ButtonHelper.click( "ok" );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		PopupHelper.isTextPresent( "window-scroll-panel", "Row successfully inserted." );
		ButtonHelper.click( "OKButton" );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
	}

	public void addAjustments() throws Exception
	{
		billPeriodSearch();
		TextBoxHelper.type( "rev_count", eventCount );

		TextBoxHelper.type( "res_usage", usage );
		TextBoxHelper.type( "res_billed_usage", billedUsage );

		if ( ValidationHelper.isTrue( compute ) )
		{
			GenericHelper.waitForLoadmask( detailScreenWaitSec );
			ElementHelper.click( "//*[@id='voic_setup_amt']" );
			Thread.sleep( 1000 );			
			ElementHelper.waitForElement( "//*[contains(text(),'Compute')]", searchScreenWaitSec );
			ButtonHelper.isEnabled("Compute");
			ButtonHelper.click( "Compute" );
			GenericHelper.waitForLoadmask( detailScreenWaitSec );
			//assertEquals( TextBoxHelper.getValue( "rev_revenue_amount" ), ratedRevenueAmount );
		}
		else if ( ValidationHelper.isTrue( override ) )
			TextBoxHelper.type( "voic_rate", rate );
		TextBoxHelper.type( "voic_txn_amt", transactionAmt );

		if ( ValidationHelper.isNotEmpty( internalComments ) )
			TextAreaHelper.type( "InternalCommentTextArea", internalComments );
		if ( ValidationHelper.isFalse( positive ) )
			CheckBoxHelper.check( "Negative_InputElement" );
		if ( ValidationHelper.isNotEmpty( externalComments ) )
			TextAreaHelper.type( "ExternalCommentTextArea", externalComments );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );

		Thread.sleep( 2000 );		
		ButtonHelper.click( "OKButton" );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		if ( PopupHelper.isPresent() )			
		{
			ElementHelper.waitForElement( "//*[contains(text(),'OK')]", searchScreenWaitSec );
			ButtonHelper.click( "OKButton" );
			GenericHelper.waitForLoadmask( searchScreenWaitSec );
		}
	}

	public void billPeriodSearch() throws Exception
	{

		ButtonHelper.click( "trigger-pbpd_id" );
		assertEquals( NavigationHelper.getScreenTitle(), "BillPeriod Search" );
		setDate( billPeriodDate );
		ButtonHelper.click( "SearchButton" );
		GridHelper.clickRow( "popupWindow", "SearchGrid", 1, 1 );

		ButtonHelper.click( "OKButton" );
		GenericHelper.waitForLoadmask( detailScreenWaitSec );
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
}
