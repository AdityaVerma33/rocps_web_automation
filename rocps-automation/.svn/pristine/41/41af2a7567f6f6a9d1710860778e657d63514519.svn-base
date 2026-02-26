package com.subex.rocps.automation.helpers.application.carrierinvoice.carrierinvoicetemplate;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

import java.util.Map;

import com.subex.rocps.automation.helpers.application.filters.GridFilterSearchHelper;
import com.subex.rocps.automation.helpers.application.filters.PSSearchGridHelper;
import com.subex.rocps.automation.helpers.application.genericHelpers.PSEntityComboHelper;
import com.subex.rocps.automation.helpers.application.genericHelpers.PSGenericHelper;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;
import com.subex.rocps.automation.utils.ExcelHolder;
import com.subex.rocps.automation.utils.PSStringUtils;

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
import com.subex.automation.helpers.component.SearchGridHelper;
import com.subex.automation.helpers.component.TextAreaHelper;
import com.subex.automation.helpers.component.TextBoxHelper;
import com.subex.automation.helpers.data.DateHelper;
import com.subex.automation.helpers.data.ValidationHelper;
import com.subex.automation.helpers.report.Log4jHelper;
import com.subex.automation.helpers.util.FailureHelper;

public class CarrierInvoiceDetailImpl extends PSAcceptanceTest
{
	protected String partition;
	protected String templateType;
	protected String templateName;
	protected String isDelta;
	protected String billProfile;
	protected String fromDate;
	protected String toDate;
	protected String priorCarrierInvoice;
	protected String invoiceNo;
	protected String invoiceDate;
	protected String dueDate;
	protected String receivedDate;
	protected String reference;
	protected String invoiceVersion;
	protected String description;
	protected String billProfileCurrency;
	protected String homeCurrency;
	protected String fxGroup;
	protected String fxRule;
	protected String usageTotal;
	protected String nonUsageTotal;
	protected String creditNoteTotal;
	protected String netCharges;
	protected String calculatedTotalCharges;
	protected String difference;
	protected String tax;
	protected String totalAmount;
	protected String carrierInvoicePeriod;
	protected String carrierInvoiceStatus;
	PSGenericHelper genericObj = new PSGenericHelper();
	PSStringUtils strObj = new PSStringUtils();
	protected Map<String, String> map;
	CarrierInvoiceTemplateActionImpl ciActionObj = new CarrierInvoiceTemplateActionImpl();

	public CarrierInvoiceDetailImpl( Map<String, String> map ) throws Exception
	{
		this.map = map;
		initialiseVariables( map );
	}

	public void newCarrierInvoice() throws Exception
	{
		PSGenericHelper genericObj = new PSGenericHelper();
		genericObj.clickNewAction( partition );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		//assertEquals( NavigationHelper.getScreenTitle(), "Carrier Invoice Search" );

	}

	public void carrierInvoiceTemplateSelection() throws Exception
	{
		PSEntityComboHelper.selectUsingGridFilterTextBox( "invoiceTemplate", "Carrier Invoice Template Search", "pintName", templateName, "Name" );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		Thread.sleep( 1000 );
		ButtonHelper.click( "OKButton" );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		assertEquals( NavigationHelper.getScreenTitle(), "New Carrier Invoice" );

	}

	public void summaryTabConfig() throws Exception
	{
		billProfileDetails();
		invoicDetails();
		currencyDetails();
		chargeDetails();
		ButtonHelper.click( "nextButton" );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
	}

	public void billProfileDetails() throws Exception
	{
		if ( ValidationHelper.isTrue( isDelta ) )
			CheckBoxHelper.check( "pcaiIsDelta_InputElement" );
		//EntityComboHelper.selectUsingGridFilterTextBox( "billProfile", "Bill Profile Search", "pbipName", billProfile, "Bill Profile Name" );
		billProfileSelection();
		TextBoxHelper.type( "//table[@id='pcaiFromDttm']/tbody/tr/td[1]/input", date( fromDate ) );
		TextBoxHelper.type( "//table[@id='pcaiToDttm']/tbody/tr/td[1]/input", date( toDate ) );
		ButtonHelper.click( "billProfilePanel" );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		ButtonHelper.click( "pcaiDesc" );
		if ( PopupHelper.isPresent() )
		{
			PopupHelper.isTextPresent( "window-scroll-panel", "Cross fx rate not found for bill profile for the Source currency(s) British Pound and Target currency US Dollar" );
			ButtonHelper.click( "OKButton" );
			GenericHelper.waitForLoadmask( searchScreenWaitSec );
		}

		if ( ValidationHelper.isNotEmpty( priorCarrierInvoice ) )
		{
			EntityComboHelper.clickEntityIcon( "priorCarrierInvoice" );
			GridFilterSearchHelper gridHelperObj = new GridFilterSearchHelper();
			gridHelperObj.billProfileAdvanceFilter( "SearchGrid", "Bill Profile", priorCarrierInvoice );
			if ( !carrierInvoicePeriod.isEmpty() )
				genericObj.setDate( "dummyValidOnDataProperty", carrierInvoicePeriod );
			if ( !carrierInvoiceStatus.isEmpty() )
				SearchGridHelper.gridFilterSearchWithComboBox( "statusCode_gwt_uid_", carrierInvoiceStatus, "Status" );
			GridHelper.clickRow( "SearchGrid", 1, "Bill Profile" );
			ButtonHelper.click( "OKButton" );
			GenericHelper.waitForLoadmask( searchScreenWaitSec );

		}
	}
	
	
	public void billProfileSelection() throws Exception
	{
		EntityComboHelper.clickEntityIcon( "billProfile" );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		genericObj.waitforPopupHeaderElement( "Bill Profile Name" );		
		SearchGridHelper.gridFilterSearchWithTextBox(  "popupWindow", "pbipName", billProfile, "Bill Profile Name" );
		Thread.sleep( 2000 );
		genericObj.waitforPopupHeaderElement( "Bill Profile Name" );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		GridHelper.clickRow("popupWindow", "SearchGrid", billProfile, "Bill Profile Name" );
		GridHelper.clickRow("popupWindow", "SearchGrid", billProfile, "Bill Profile Name" );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		ButtonHelper.click( "OKButton" );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
	}

	/*
	 * This method is to remove the hr:min:ss from date
	 */
	private String date( String date )
	{
		String dateVal = null;
		if ( date.contains( "00:00:00" ) )
			dateVal = date.replace( "00:00:00", "" );
		else
			dateVal = date;

		return dateVal;
	}

	public void invoicDetails() throws Exception
	{
		TextBoxHelper.type( "pcaiInvoiceNo", invoiceNo );
		TextBoxHelper.type( "pcaiInvoiceDttm", invoiceDate );
		TextBoxHelper.type( "pcaiInvoiceDueDttm", dueDate );
		TextBoxHelper.type( "pcaiReceivedDttm", receivedDate );
		TextBoxHelper.type( "pcaiReference", reference );
		TextBoxHelper.type( "versionLabel", invoiceVersion );
		TextAreaHelper.type( "pcaiDesc", description );
	}

	public void currencyDetails() throws Exception
	{
		assertEquals( ComboBoxHelper.getValue( "billProfileCurrency_gwt_uid_" ), billProfileCurrency );
		assertEquals( ComboBoxHelper.getValue( "homeCurrency_gwt_uid_" ), homeCurrency );
		assertEquals( ComboBoxHelper.getValue( "crossFxRateGroup_gwt_uid_" ), fxGroup );
		assertEquals( ComboBoxHelper.getValue( "component_gwt_uid_" ), fxRule );

	}

	public void chargeDetails() throws Exception
	{
		TextBoxHelper.type( "pcaiTotalAmount", netCharges );
		TextBoxHelper.type( "pcaiTax", tax );
	}

	public void saveCarrierInvoice() throws Exception
	{
		ButtonHelper.click( "carrierInvoiceDetail.save" );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		if ( PopupHelper.isTextPresent( "window-scroll-panel", "Net Charges or Total Amount is null. Do you want to copy Calculated Total Charges to Net Charges and Total Amount = Net Charges+Tax" ) )
		{
			ButtonHelper.click( "YesButton" );
			GenericHelper.waitForLoadmask( searchScreenWaitSec );
		}

		if ( PopupHelper.isTextPresent( "window-scroll-panel", "Click OK to Replace selected Prior Carrier Invoice" ) || PopupHelper.isTextPresent( "window-scroll-panel", "Click OK to Reject selected Prior Carrier Invoice" ) )
		{
			ButtonHelper.click( "OKButton" );
			GenericHelper.waitForLoadmask( searchScreenWaitSec );
		}
		Thread.sleep( 2000 );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		GenericHelper.waitForElementToDisappear( "carrierInvoiceDetail.save", searchScreenWaitSec );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
	}

	public void summaryValidation() throws Exception
	{
		String billprofileUI = EntityComboHelper.getValue( "billProfile" );
		if ( billprofileUI.contains( billProfile ) )
			Log4jHelper.logInfo( "Bill profile is validated." );
		else
			FailureHelper.failTest( "Bill profiles are not matching" );
		//assertEquals(TextBoxHelper.getValue( "//table[@id='pcaiFromDttm']/tbody/tr/td[1]/input" ), fromDate);
		//assertEquals(TextBoxHelper.getValue( "//table[@id='pcaiToDttm']/tbody/tr/td[1]/input" ), toDate);
		currencyDetails();
	}

	public void initialiseVariables( Map<String, String> map ) throws Exception
	{
		partition = ExcelHolder.getKey( map, "Partition" );
		carrierInvoicePeriod = ExcelHolder.getKey( map, "CarrierInvoicePeriod" );
		carrierInvoiceStatus = ExcelHolder.getKey( map, "PriorCarrierInvoiceStatus" );
		templateName = ExcelHolder.getKey( map, "TemplateName" );
		isDelta = ExcelHolder.getKey( map, "IsDelta" );
		billProfile = ExcelHolder.getKey( map, "BillProfile" );
		fromDate = ExcelHolder.getKey( map, "FromDate" );
		toDate = ExcelHolder.getKey( map, "ToDate" );
		priorCarrierInvoice = ExcelHolder.getKey( map, "PriorCarrierInvoice" );
		invoiceNo = ExcelHolder.getKey( map, "InvoiceNo" );
		invoiceDate = ExcelHolder.getKey( map, "InvoiceDate" );
		dueDate = ExcelHolder.getKey( map, "DueDate" );
		receivedDate = ExcelHolder.getKey( map, "ReceivedDate" );
		reference = ExcelHolder.getKey( map, "Reference" );
		invoiceVersion = ExcelHolder.getKey( map, "InvoiceVersion" );
		description = ExcelHolder.getKey( map, "Description" );
		billProfileCurrency = ExcelHolder.getKey( map, "BillProfileCurrency" );
		homeCurrency = ExcelHolder.getKey( map, "HomeCurrency" );
		fxGroup = ExcelHolder.getKey( map, "FxGroup" );
		fxRule = ExcelHolder.getKey( map, "FxRule" );
		usageTotal = ExcelHolder.getKey( map, "UsageTotal" );
		nonUsageTotal = ExcelHolder.getKey( map, "NonUsageTotal" );
		creditNoteTotal = ExcelHolder.getKey( map, "CreditNoteTotal" );
		netCharges = ExcelHolder.getKey( map, "NetCharges" );
		calculatedTotalCharges = ExcelHolder.getKey( map, "CalculatedTotalCharges" );
		difference = ExcelHolder.getKey( map, "Difference" );
		tax = ExcelHolder.getKey( map, "Tax" );
		totalAmount = ExcelHolder.getKey( map, "TotalAmount" );
	}
}
