package com.subex.rocps.automation.helpers.application.dispute.dispute;

import java.util.ArrayList;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

import com.subex.automation.helpers.application.NavigationHelper;
import com.subex.automation.helpers.component.ButtonHelper;
import com.subex.automation.helpers.component.CalendarHelper;
import com.subex.automation.helpers.component.CheckBoxHelper;
import com.subex.automation.helpers.component.ComboBoxHelper;
import com.subex.automation.helpers.component.ElementHelper;
import com.subex.automation.helpers.component.EntityComboHelper;
import com.subex.automation.helpers.component.GenericHelper;
import com.subex.automation.helpers.component.GridHelper;
import com.subex.automation.helpers.component.SearchGridHelper;
import com.subex.automation.helpers.component.TextAreaHelper;
import com.subex.automation.helpers.component.TextBoxHelper;
import com.subex.automation.helpers.data.ValidationHelper;
import com.subex.rocps.automation.helpers.application.filters.GridFilterSearchHelper;
import com.subex.rocps.automation.helpers.application.genericHelpers.PSGenericHelper;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;
import com.subex.rocps.automation.utils.ExcelHolder;
import com.subex.rocps.automation.utils.PSStringUtils;

public class DisputeDetailImpl extends PSAcceptanceTest
{

	protected Map<String, String> disputeDetailTabMap;
	protected String columnHeadrs;
	protected String disputeSource;
	protected String disputeType;
	protected String account;
	protected String billProfile;
	protected String date;
	protected String fromDt;
	protected String toDt;
	protected String disputeOwner;
	protected String reasonCd;
	protected String reviewByDt;
	protected String disputeRootCause;
	protected String amt_curr_amt;
	protected String amt_curr_curr;
	protected String shortPay;
	protected String audit_comments;

	protected String account_BillEntiTy;
	protected String billProfile_BillEntity;
	protected String fromDt_BillEntity;
	protected String toDt_BillEntity;
	protected String billRef_BillEntity;

	protected String account_CIEntiTy;
	protected String billProfile_CIEntity;
	protected String fromDt_CIEntity;
	protected String toDt_CIEntity;
	protected String ciRefNo_CIEntity;

	protected PSGenericHelper psGenericHelper = new PSGenericHelper();
	protected GridFilterSearchHelper gridFilterSearchHelper = new GridFilterSearchHelper();
	protected PSStringUtils psStringUtils = new PSStringUtils();

	public DisputeDetailImpl( Map<String, String> disputeDetailTabMap )
	{
		this.disputeDetailTabMap = disputeDetailTabMap;
	}

	//Method for initializing instance variables
	public void initializeVariables( Map<String, String> map ) throws Exception
	{

		disputeSource = ExcelHolder.getKey( map, "DisputeSource" );
		disputeType = ExcelHolder.getKey( map, "DisputeType" );
		account = ExcelHolder.getKey( map, "Account" );
		billProfile = ExcelHolder.getKey( map, "BillProfile" );
		date = ExcelHolder.getKey( map, "Date" );
		fromDt = ExcelHolder.getKey( map, "DisputeFromDate" );
		toDt = ExcelHolder.getKey( map, "DisputeToDate" );
		disputeOwner = ExcelHolder.getKey( map, "DisputeOwner" );
		reasonCd = ExcelHolder.getKey( map, "ReasonCode" );
		reviewByDt = ExcelHolder.getKey( map, "ReviewByDate" );
		disputeRootCause = ExcelHolder.getKey( map, "DisputeRootCause" );
		amt_curr_amt = ExcelHolder.getKey( map, "Amount-Currency amt" );
		amt_curr_curr = ExcelHolder.getKey( map, "Amount-Currency curr" );
		shortPay = ExcelHolder.getKey( map, "ShortPay" );
		audit_comments = disputeDetailTabMap.get( "Audit_Comment" );
		audit_comments = ExcelHolder.getKey( map, "Audit_Comment" );

	}

	//Method: verify the column headers of dispute screen
	public void verifyColmnHeaderOfDispute() throws Exception
	{
		columnHeadrs = ExcelHolder.getKey( disputeDetailTabMap, "ColumnHeaders" );
		ArrayList<String> excelColumnNames = new ArrayList<String>();
		String disputeGridColumnsArr[] = psStringUtils.stringSplitFirstLevel( columnHeadrs );
		for ( int col = 0; col < disputeGridColumnsArr.length; col++ )
		{
			excelColumnNames.add( disputeGridColumnsArr[col] );
		}
		psGenericHelper.totalColumns( excelColumnNames );
	}

	//Method to configure new dispute details 
	public void createDisputeConfig() throws Exception
	{
		initializeVariables( disputeDetailTabMap );
		disputeSourceConfig();
		disputeDetailsConfig();
		disputedAmountConfig();
		auditConfig();

	}

	//Method to  edit dispute details 
	public void editDisputeConfig() throws Exception
	{
		initializeVariables( disputeDetailTabMap );
		editdisputeSource();
		editdisputeDetails();
		editDisputedAmount();
		editauditConfig();

	}

	//Method to edit 'Dispute Source' details information
	private void editdisputeSource() throws Exception
	{
		if ( ValidationHelper.isNotEmpty( disputeSource ) )
			ComboBoxHelper.select( "PS_Detail_dispute_disputeSource_ComboID", disputeSource );
		assertEquals( ComboBoxHelper.getValue( "PS_Detail_dispute_disputeType_ComboID" ), disputeType, "Dispute type is not matched" );
		if ( disputeType.contains( "Account Component" ) )
		{
			assertEquals( EntityComboHelper.getValue( "account" ), account, "Account is not matched in dispute source" );
		}
		else if ( disputeType.contains( "Bill Profile Component" ) )
		{
			assertTrue( EntityComboHelper.getValue( "billProfile" ).contains( billProfile ), "Bill Profile is not matched in dispute source" );
		}
		else if ( disputeType.contains( "Bill Component" ) )
		{
			assertTrue( EntityComboHelper.getValue( "bill" ).contains( billProfile ), "'Bill' is not matched in dispute source" );

		}
		else if ( disputeType.contains( "Carrier Invoice Component" ) )
		{
			assertTrue( EntityComboHelper.getValue( "carrierInvoice" ).contains( billProfile ), "'Carrier Invoice' is not matched in dispute source" );
		}

	}

	//Method to edit 'Dispute Details' details information
	private void editdisputeDetails() throws Exception
	{
		boolean chckTypeOfDispute = ( disputeType.contains( "Account Component" ) || disputeType.contains( "Bill Profile Component" ) );
		String actualLinkRecordTyppe = ComboBoxHelper.getValue( "pdisLinkedRecordType_gwt_uid_" );
		if ( ValidationHelper.isNotEmpty( fromDt ) && chckTypeOfDispute )
			TextBoxHelper.type( "PS_Detail_dispute_fromDt_textID", fromDt );
		if ( ValidationHelper.isNotEmpty( toDt ) && chckTypeOfDispute )
			TextBoxHelper.type( "PS_Detail_dispute_toDt_textID", toDt );
		if ( ValidationHelper.isNotEmpty( disputeOwner ) )
			disputeTypeEntitySearch( "PS_Detail_dispute_dispOwner_popupId", "User Search", "usrSurname", disputeOwner, "Surname" );
		if ( ValidationHelper.isNotEmpty( reviewByDt ) )
			TextBoxHelper.type( "PS_Detail_dispute_reviewByDt_textID", reviewByDt );
		if ( ValidationHelper.isNotEmpty( disputeRootCause ) )
			ComboBoxHelper.select( "PS_Detail_dispute_dispRootCause_ComboID", disputeRootCause );
		if ( disputeType.contains( "Bill Component" ) )
			assertEquals( actualLinkRecordTyppe, "Bill", "Link record Type is not matched to 'Bill'" );
		if ( disputeType.contains( "Carrier Invoice Component" ) )
			assertEquals( actualLinkRecordTyppe, "Carrier Invoice", "Link record Type is not matched to 'Carrier Invoice'" );
		if ( chckTypeOfDispute )
			assertEquals( actualLinkRecordTyppe, "General", "Link record Type is not matched to 'General'" );
		String actualStatusCd = driver.findElement( By.xpath( GenericHelper.getORProperty( "PS_Detail_dispute_statusCd_xpath" ) ) ).getText();
		assertTrue( actualStatusCd.contains( "Draft" ), "Status code is not matched to Draft" );
		String actualCreatedUser = driver.findElement( By.xpath( GenericHelper.getORProperty( "PS_Detail_dispute_createdUser_xpath" ) ) ).getText();
		assertEquals( actualCreatedUser, configProp.getProperty( "applicationUsername" ), "Created user is not matched" );

	}

	//Method to configure 'Dispute Source' details information
	protected void disputeSourceConfig() throws Exception
	{
		ComboBoxHelper.select( "PS_Detail_dispute_disputeSource_ComboID", disputeSource );
		ComboBoxHelper.select( "PS_Detail_dispute_disputeType_ComboID", disputeType );
		if ( disputeType.contains( "Account Component" ) )
		{
			disputeTypeEntitySearch( "PS_Detail_dispute_account_popupId", "Account Search", "accountName_Detail", account, "Account Name" );

		}
		else if ( disputeType.contains( "Bill Profile Component" ) )
		{
			billProfileEntitySearch();
		}
		else if ( disputeType.contains( "Bill Component" ) )
		{
			billEntitySearch();
			billDisputeAssertion();
		}

		else if ( disputeType.contains( "Carrier Invoice Component" ) )
		{
			CIEntitySearch();
			CIDisputeAssertion();
		}

	}

	private void CIDisputeAssertion() throws Exception
	{

		String actualAccount = EntityComboHelper.getValue( "account" );
		assertEquals( actualAccount, account_CIEntiTy );

		String actualBillProfile = EntityComboHelper.getValue( "billProfile" );
		assertEquals( actualBillProfile, billProfile_CIEntity );

		String actualBillPeriod = EntityComboHelper.getValue( "billPeriod" );
		assertTrue( actualBillPeriod.contains( billProfile ) );

		String actualFromDt = TextBoxHelper.getValue( "pdisFromDttm" );
		assertEquals( actualFromDt, fromDt_CIEntity );

		String actualToDt = TextBoxHelper.getValue( "pdisToDttm" );
		assertEquals( actualToDt, toDt_CIEntity );

		String actualLinkRecord = EntityComboHelper.getValue( "linkedRecord" );
		assertEquals( actualLinkRecord, ciRefNo_CIEntity );

		String actualLinkRecordTyppe = ComboBoxHelper.getValue( "pdisLinkedRecordType_gwt_uid_" );
		assertEquals( actualLinkRecordTyppe, "Carrier Invoice" );

	}

	private void CIEntitySearch() throws Exception
	{

		GenericHelper.waitForLoadmask();
		ButtonHelper.click( "PS_Detail_dispute_carrierInvoice_popupId" );
		ElementHelper.waitForElement( GenericHelper.getORProperty( "ps_Detail_entityPopupS_Xpath" ), searchScreenWaitSec );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		//assertEquals( NavigationHelper.getScreenTitle(), "Carrier Invoice Search" );
		gridFilterSearchHelper.billProfileAdvanceFilter( "searchGrid", "Bill Profile", billProfile );

		if ( ValidationHelper.isNotEmpty( date ) )
			setDate( date );
		ButtonHelper.click( "SearchButton" );

		GenericHelper.waitForLoadmask();

		GridHelper.clickRow( "searchGrid", 1, "Bill Profile" );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		account_CIEntiTy = GridHelper.getCellValue( "searchGrid", 1, " Account Name" );
		billProfile_CIEntity = GridHelper.getCellValue( "searchGrid", 1, "Bill Profile" );
		fromDt_CIEntity = GridHelper.getCellValue( "searchGrid", 1, "From" );
		toDt_CIEntity = GridHelper.getCellValue( "searchGrid", 1, "To" );
		ciRefNo_CIEntity = GridHelper.getCellValue( "searchGrid", 1, " Carrier Invoice Ref No." );
		ButtonHelper.click( "Detail_popUpWindowId", "OKButton" );
		ElementHelper.waitForElementToDisappear( GenericHelper.getORProperty( "ps_Detail_entityPopupS_Xpath" ), searchScreenWaitSec );
		GenericHelper.waitForLoadmask();

	}

	//Method: assertion of billDispute
	protected void billDisputeAssertion() throws Exception
	{

		String actualAccount = EntityComboHelper.getValue( "account" );
		assertEquals( actualAccount, account_BillEntiTy );

		String actualBillProfile = EntityComboHelper.getValue( "billProfile" );
		assertEquals( actualBillProfile, billProfile_BillEntity );

		String actualBillPeriod = EntityComboHelper.getValue( "billPeriod" );
		assertTrue( actualBillPeriod.contains( billProfile ) );

		String actualFromDt = TextBoxHelper.getValue( "pdisFromDttm" );
		assertEquals( actualFromDt, fromDt_BillEntity );

		String actualToDt = TextBoxHelper.getValue( "pdisToDttm" );
		assertEquals( actualToDt, toDt_BillEntity );

		String actualLinkRecord = EntityComboHelper.getValue( "linkedRecord" );
		assertEquals( actualLinkRecord, billRef_BillEntity );

		String actualLinkRecordTyppe = ComboBoxHelper.getValue( "pdisLinkedRecordType_gwt_uid_" );
		assertEquals( actualLinkRecordTyppe, "Bill" );

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

	// This method is for disputeType Entity Search
	public void billEntitySearch() throws Exception
	{
		GenericHelper.waitForLoadmask();
		ButtonHelper.click( "PS_Detail_dispute_bill_popupId" );
		ElementHelper.waitForElement( GenericHelper.getORProperty( "ps_Detail_entityPopupS_Xpath" ), searchScreenWaitSec );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		//assertEquals( NavigationHelper.getScreenTitle(), "Bill Search" );
		gridFilterSearchHelper.billProfileAdvanceFilter( "searchGrid", "Bill Profile", billProfile );

		if ( ValidationHelper.isNotEmpty( date ) )
			setDate( date );
		ButtonHelper.click( "SearchButton" );

		GenericHelper.waitForLoadmask();

		GridHelper.clickRow( "searchGrid", 1, "Bill Profile" );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		account_BillEntiTy = GridHelper.getCellValue( "searchGrid", 1, "Account" );
		billProfile_BillEntity = GridHelper.getCellValue( "searchGrid", 1, "Bill Profile" );
		fromDt_BillEntity = GridHelper.getCellValue( "searchGrid", 1, "Bill Period From" );
		toDt_BillEntity = GridHelper.getCellValue( "searchGrid", 1, "Bill Period To" );
		billRef_BillEntity = GridHelper.getCellValue( "searchGrid", 1, "Bill Ref No" );
		ButtonHelper.click( "Detail_popUpWindowId", "OKButton" );
		ElementHelper.waitForElementToDisappear( GenericHelper.getORProperty( "ps_Detail_entityPopupS_Xpath" ), searchScreenWaitSec );
		GenericHelper.waitForLoadmask();
	}

	//Method to configure 'Dispute Details' details information
	protected void disputeDetailsConfig() throws Exception
	{
		boolean chckTypeOfDispute = ( disputeType.contains( "Account Component" ) || disputeType.contains( "Bill Profile Component" ) );
		if ( ValidationHelper.isNotEmpty( fromDt ) && chckTypeOfDispute )
			TextBoxHelper.type( "PS_Detail_dispute_fromDt_textID", fromDt );
		if ( ValidationHelper.isNotEmpty( toDt ) && chckTypeOfDispute )
			TextBoxHelper.type( "PS_Detail_dispute_toDt_textID", toDt );
		disputeTypeEntitySearch( "PS_Detail_dispute_dispOwner_popupId", "User Search", "usrSurname", disputeOwner, "Surname" );
		ComboBoxHelper.select( "PS_Detail_dispute_reasonCd_ComboID", reasonCd );
		TextBoxHelper.type( "PS_Detail_dispute_reviewByDt_textID", reviewByDt );
		if ( ValidationHelper.isNotEmpty( disputeRootCause ) )
			ComboBoxHelper.select( "PS_Detail_dispute_dispRootCause_ComboID", disputeRootCause );
		String actualStatusCd = driver.findElement( By.xpath( GenericHelper.getORProperty( "PS_Detail_dispute_statusCd_xpath" ) ) ).getText();
		assertTrue( actualStatusCd.contains( "Draft" ) );
		String actualCreatedUser = driver.findElement( By.xpath( GenericHelper.getORProperty( "PS_Detail_dispute_createdUser_xpath" ) ) ).getText();
		assertEquals( actualCreatedUser, configProp.getProperty( "applicationUsername" ) );

	}

	//Method to configure 'Disputed Amount' details information
	protected void disputedAmountConfig() throws Exception
	{

		TextBoxHelper.type( "PS_Detail_dispute_amt-Curr_amt_textID", amt_curr_amt );
		ComboBoxHelper.select( "PS_Detail_dispute_amt-Curr_curr_ComboID", amt_curr_curr );
		if ( ValidationHelper.isTrue( shortPay ) )
			CheckBoxHelper.check( "PS_Detail_dispute_shortPay_ChckBoxID" );
	}

	//Method to edit 'Disputed Amount' details information
	protected void editDisputedAmount() throws Exception
	{
		boolean checkDisputeType = disputeType.contains( "Bill Component" );
		if ( ValidationHelper.isNotEmpty( amt_curr_amt ) )
			TextBoxHelper.type( "PS_Detail_dispute_amt-Curr_amt_textID", amt_curr_amt );
		if ( ValidationHelper.isNotEmpty( amt_curr_amt ) )
			ComboBoxHelper.select( "PS_Detail_dispute_amt-Curr_curr_ComboID", amt_curr_curr );
		if ( checkDisputeType && ValidationHelper.isTrue( shortPay ) )
			CheckBoxHelper.check( "PS_Detail_dispute_shortPay_ChckBoxID" );
		if ( checkDisputeType && ValidationHelper.isFalse( shortPay ) )
			CheckBoxHelper.uncheck( "PS_Detail_dispute_shortPay_ChckBoxID" );
	}

	//Method to configure 'Audit' details information
	protected void auditConfig() throws Exception
	{
		TextAreaHelper.type( "PS_Detail_dispute_audit_comments_textID", audit_comments );
	}

	//Method to edit 'Audit' details information
	protected void editauditConfig() throws Exception
	{
		if ( ValidationHelper.isNotEmpty( audit_comments ) )
			TextAreaHelper.type( "PS_Detail_dispute_audit_comments_textID", audit_comments );
	}

	// This method is for disputeType Entity Search
	public void disputeTypeEntitySearch( String disTypeEntPopupId, String popUpScreenName, String gridColId, String colVal, String columnHeader ) throws Exception
	{
		GenericHelper.waitForLoadmask();
		ButtonHelper.click( disTypeEntPopupId );
		ElementHelper.waitForElement( GenericHelper.getORProperty( "ps_Detail_entityPopupS_Xpath" ), searchScreenWaitSec );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		//assertEquals( NavigationHelper.getScreenTitle(), popUpScreenName );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		int row = SearchGridHelper.gridFilterSearchWithTextBox( gridColId, colVal, columnHeader );
		GridHelper.clickRow( "Detail_popUpWindowId", row, columnHeader );
		ButtonHelper.click( "Detail_popUpWindowId", "OKButton" );
		ElementHelper.waitForElementToDisappear( GenericHelper.getORProperty( "ps_Detail_entityPopupS_Xpath" ), searchScreenWaitSec );
		GenericHelper.waitForLoadmask();
	}

	// This method is for bill Profile Entity Search
	public void billProfileEntitySearch() throws Exception
	{
		GenericHelper.waitForLoadmask();
		ButtonHelper.click( "PS_Detail_dispute_billProfile_popupId" );
		ElementHelper.waitForElement( GenericHelper.getORProperty( "ps_Detail_entityPopupS_Xpath" ), searchScreenWaitSec );
		GenericHelper.waitForLoadmask();
		//assertEquals( NavigationHelper.getScreenTitle(), "Bill Profile Search" );
		if ( ValidationHelper.isNotEmpty( account ) )
		{

			SearchGridHelper.gridFilterSearchWithTextBox( "detail_bip_detail_profileName_txtId", billProfile, "Bill Profile Name" );
			gridFilterSearchHelper.accountFilter( "PS_Detail_dispute_account_billProfileSrch_textId", "account", account, "Account" );
		}
		else
			SearchGridHelper.gridFilterSearchWithTextBox( "detail_bip_detail_profileName_txtId", billProfile, "Bill Profile Name" );
		GridHelper.clickRow( "Detail_popUpWindowId", 1, "Bill Profile Name" );
		ButtonHelper.click( "Detail_popUpWindowId", "OKButton" );
		ElementHelper.waitForElementToDisappear( GenericHelper.getORProperty( "ps_Detail_entityPopupS_Xpath" ), searchScreenWaitSec );
		GenericHelper.waitForLoadmask();
	}

	// This method is for save the dispute 
	public void saveDispute( String name, String typeOfDispute ) throws Exception
	{
		GenericHelper.waitForLoadmask();
		ButtonHelper.click( "PS_Detail_dispute_Save_btnID" );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		ElementHelper.isElementPresent( GenericHelper.getORProperty( "PS_searchPanelId" ) );
		//boolean isNewAddedDisputePresent=GridHelper.isValuePresent("PS_searchGridlId", name);
		//assertTrue(isNewAddedDisputePresent," Failed to save this '"+name+"' type of dispute:"+typeOfDispute);
	}
}
