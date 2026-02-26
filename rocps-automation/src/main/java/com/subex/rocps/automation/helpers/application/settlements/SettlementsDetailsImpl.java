package com.subex.rocps.automation.helpers.application.settlements;

import org.apache.commons.collections.map.HashedMap;
import org.openqa.selenium.By;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import com.subex.rocps.automation.helpers.application.filters.DataSelectionHelper;
import com.subex.rocps.automation.helpers.application.genericHelpers.PSGenericHelper;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;
import com.subex.rocps.automation.utils.ExcelHolder;
import com.subex.rocps.automation.utils.PSStringUtils;

import com.subex.automation.helpers.component.ButtonHelper;
import com.subex.automation.helpers.component.ComboBoxHelper;
import com.subex.automation.helpers.component.ElementHelper;
import com.subex.automation.helpers.component.EntityComboHelper;
import com.subex.automation.helpers.component.EntitySearchHelper;
import com.subex.automation.helpers.component.GenericHelper;
import com.subex.automation.helpers.component.GridHelper;
import com.subex.automation.helpers.component.LabelHelper;
import com.subex.automation.helpers.component.MouseHelper;
import com.subex.automation.helpers.component.PopupHelper;
import com.subex.automation.helpers.component.TextAreaHelper;
import com.subex.automation.helpers.component.TextBoxHelper;
import com.subex.automation.helpers.data.DateHelper;
import com.subex.automation.helpers.data.ValidationHelper;
import com.subex.automation.helpers.report.Log4jHelper;
import com.subex.automation.helpers.util.FailureHelper;

public class SettlementsDetailsImpl extends PSAcceptanceTest

{

	protected Map<String, String> settlementsDetailsMap = null;
	protected String billProfile;
	protected String fromDt;
	protected String toDt;
	protected String name;
	protected String addBillFlg;
	protected String addCarrierInvFlg;
	protected String addCreditNoteFlg;
	protected String accountAdd;
	protected String accountAddArr[];
	protected String billProfileAdd;
	protected String billProfileAddArr[];
	protected String fromDtAdd;
	protected String fromDtAddArr[];
	protected String toDtAdd;
	protected String toDtAddArr[];
	protected String amount_creditNt;
	protected String amount_creditNtArr[];
	protected String amnt_SettlementCurrency;
	protected String amnt_HomeCurrency;
	protected Map<String, String> gridKeyValuemap = null;

	DataSelectionHelper dataSelectionHelper = new DataSelectionHelper();
	PSStringUtils psStringUtils = new PSStringUtils();
	PSGenericHelper psGenericHelperobj = new PSGenericHelper();

	/**Constructor
	 * @param settlementsDetailsMap
	 */
	public SettlementsDetailsImpl( Map<String, String> settlementsDetailsMap )
	{

		this.settlementsDetailsMap = settlementsDetailsMap;
	}

	/*
	 * This method is for initialize the variables  Settlements 
	 */
	protected void initializevariable( Map<String, String> map ) throws Exception
	{
		billProfile = ExcelHolder.getKey( map, "BillProfile" );
		fromDt = ExcelHolder.getKey( map, "From" );
		toDt = ExcelHolder.getKey( map, "To" );
		name = ExcelHolder.getKey( map, "Name" );
		addBillFlg = ExcelHolder.getKey( map, "AddOptionBillFlg" );
		addCarrierInvFlg = ExcelHolder.getKey( map, "AddOptionCarrInvFlg" );
		addCreditNoteFlg = ExcelHolder.getKey( map, "AddOptionCreditNtFlg" );
	}

	protected void initializevariableForAddOptions( Map<String, String> map, String accountKey, String BillProfileKey, String fromDtKey, String toDtKey ) throws Exception
	{
		accountAdd = ExcelHolder.getKey( map, accountKey );
		billProfileAdd = ExcelHolder.getKey( map, BillProfileKey );
		fromDtAdd = ExcelHolder.getKey( map, fromDtKey );
		toDtAdd = ExcelHolder.getKey( map, toDtKey );
		accountAddArr = psStringUtils.stringSplitFirstLevel( accountAdd );
		billProfileAddArr = psStringUtils.stringSplitFirstLevel( billProfileAdd );
		fromDtAddArr = psStringUtils.stringSplitFirstLevel( fromDtAdd );
		toDtAddArr = psStringUtils.stringSplitFirstLevel( toDtAdd );
	}

	//Bill grid columns keys
	private List<String> getKeysOfBillGrid()
	{
		List<String> listColumn = new ArrayList<String>();
		listColumn.add( "Bill Profile" );
		listColumn.add( "Bill Period From" );
		listColumn.add( "Bill Period To" );
		listColumn.add( "Bill Amount" );
		listColumn.add( "Bill Profile Currency" );
		listColumn.add( "Bill Ref No" );
		return listColumn;

	}

	//Credit grid columns keys
	private List<String> getKeysOfCreditGrid()
	{
		List<String> listColumn = new ArrayList<String>();
		listColumn.add( "Bill Profile" );
		listColumn.add( "Total Amount" );
		listColumn.add( "Currency" );
		return listColumn;

	}

	//Credit grid columns keys
	private List<String> getKeysOfCIGrid()
	{
		List<String> listColumn = new ArrayList<String>();
		listColumn.add( "Bill Profile" );
		listColumn.add( "From" );
		listColumn.add( "To" );
		listColumn.add( "Currency" );
		listColumn.add( "Total Amount" );
		return listColumn;

	}

	/*
	 * This method is for configuration to Settlements
	 */
	public void configSettlment() throws Exception
	{
		initializevariable( settlementsDetailsMap );
		psGenericHelperobj.waitforEntityElement();
		EntityComboHelper.clickEntityIcon( "PS_Detail_settlements_profile_entityID" );
		dataSelectionHelper.billProfileSelection( billProfile );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		TextBoxHelper.type( "PS_Detail_settlements_fromDt_tableID", fromDt );
		TextBoxHelper.type( "PS_Detail_settlements_toDtDt_tableID", toDt );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		MouseHelper.click( GenericHelper.getORProperty( "PS_Detail_settlements_name_xpath" ) );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		MouseHelper.click( GenericHelper.getORProperty( "PS_Detail_settlements_name_xpath" ) );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		if ( ValidationHelper.isNotEmpty( name ) )
			TextBoxHelper.type( "PS_Detail_settlements_name_textID", name );
		if ( ValidationHelper.isTrue( addBillFlg ) )
			configureAddBil( settlementsDetailsMap );
		if ( ValidationHelper.isTrue( addCarrierInvFlg ) )
			configureAddCI( settlementsDetailsMap );
		if ( ValidationHelper.isTrue( addCreditNoteFlg ) )
			configureAddCreditNote( settlementsDetailsMap );
		validateCurrency( settlementsDetailsMap );
	}

	/*
	 * This method is for configuration to Settlements
	 */
	public void editSettlment() throws Exception
	{
		initializevariable( settlementsDetailsMap );
		assertTrue( EntityComboHelper.getValue( "billProfile" ).contains( billProfile ), " Bill profile is not matched" );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		assertEquals( TextBoxHelper.getValue( "PS_Detail_settlements_fromDt_tableID" ), fromDt, "From Date is not matched" );
		assertEquals( TextBoxHelper.getValue( "PS_Detail_settlements_toDtDt_tableID" ), toDt, "To Date is not matched" );
		validateCurrency( settlementsDetailsMap );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		MouseHelper.click( GenericHelper.getORProperty( "PS_Detail_settlements_name_xpath" ) );
		if ( ValidationHelper.isNotEmpty( name ) )
			TextBoxHelper.type( "PS_Detail_settlements_name_textID", name );
		if ( ValidationHelper.isTrue( addBillFlg ) )
			editAddBil( settlementsDetailsMap );
		if ( ValidationHelper.isTrue( addCarrierInvFlg ) )
			editAddCI( settlementsDetailsMap );
		if ( ValidationHelper.isTrue( addCreditNoteFlg ) )
			editAddCreditNote( settlementsDetailsMap );
	}

	/*
	 * This method is for validate the currency
	 */
	private void validateCurrency( Map<String, String> map ) throws Exception
	{
		amnt_SettlementCurrency = ExcelHolder.getKey( map, "AmountSettlementCurrency" );
		amnt_HomeCurrency = ExcelHolder.getKey( map, "AmountHomeCurrency" );
		if ( ValidationHelper.isNotEmpty( amnt_HomeCurrency ) )
			assertEquals( TextBoxHelper.getValue( "PS_Detail_settlements_HomeCurr_textID" ), amnt_HomeCurrency, " Amount Due to Partner in Settlement Currency is not matched" );
		if ( ValidationHelper.isNotEmpty( amnt_SettlementCurrency ) )
			assertEquals( TextBoxHelper.getValue( "PS_Detail_settlements_settLementCurr_textID" ), amnt_SettlementCurrency, " Amount Due to Partner in  Home Currency is not matched" );
	}

	/*
	 * This method is for configuration to add bill
	 */
	private void configureAddBil( Map<String, String> map ) throws Exception
	{
		initializevariableForAddOptions( map, "Account_Bill", "BillProfile_Bill", "FromDt_Bill", "ToDt_Bill" );
		for ( int i = 0; i < accountAddArr.length; i++ )
			selectAddOptions( "Bill", i );

	}

	/*
	 * This method is for edit add bill
	 */
	private void editAddBil( Map<String, String> map ) throws Exception
	{
		initializevariableForAddOptions( map, "Account_Bill", "BillProfile_Bill", "FromDt_Bill", "ToDt_Bill" );
		for ( int i = 0; i < accountAddArr.length; i++ )
			editAddOptions( "Bill", i );

	}

	/*
	 * This method is for configuration to add CI
	 */
	private void configureAddCI( Map<String, String> map ) throws Exception
	{
		initializevariableForAddOptions( map, "Account_CI", "BillProfile_CI", "FromDt_CI", "ToDt_CI" );
		for ( int i = 0; i < accountAddArr.length; i++ )
			selectAddOptions( "Carrier Invoice", i );

	}

	/*
	 * This method is edit   add CI
	 */
	private void editAddCI( Map<String, String> map ) throws Exception
	{
		initializevariableForAddOptions( map, "Account_CI", "BillProfile_CI", "FromDt_CI", "ToDt_CI" );
		for ( int i = 0; i < accountAddArr.length; i++ )
			editAddOptions( "Carrier Invoice", i );

	}

	/*
	 * This method is for configuration to add credit note
	 */
	private void configureAddCreditNote( Map<String, String> map ) throws Exception
	{
		billProfileAdd = ExcelHolder.getKey( map, "BillProfile_CreditNt" );
		billProfileAddArr = psStringUtils.stringSplitFirstLevel( billProfileAdd );
		amount_creditNt = ExcelHolder.getKey( map, "Amount_CreditNt" );
		amount_creditNtArr = psStringUtils.stringSplitFirstLevel( amount_creditNt );
		for ( int i = 0; i < billProfileAddArr.length; i++ )
			selectAddOptions( "Credit Note", i );

	}

	/*
	 * This method is for edit  add credit note
	 */
	private void editAddCreditNote( Map<String, String> map ) throws Exception
	{
		billProfileAdd = ExcelHolder.getKey( map, "BillProfile_CreditNt" );
		billProfileAddArr = psStringUtils.stringSplitFirstLevel( billProfileAdd );
		amount_creditNt = ExcelHolder.getKey( map, "Amount_CreditNt" );
		amount_creditNtArr = psStringUtils.stringSplitFirstLevel( amount_creditNt );
		for ( int i = 0; i < billProfileAddArr.length; i++ )
			editAddOptions( "Credit Note", i );

	}

	/*
	 * This method is for select the add menu in details screen of Settlements 
	 */
	private void selectAddOptions( String addOption, int index ) throws Exception
	{
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		ElementHelper.waitForClickableElement( GenericHelper.getORProperty( "PS_Detail_settlements_addMenuxpath" ), searchScreenWaitSec );
		int row = GridHelper.getRowCount( "settlementsGrid" );
		MouseHelper.rightClick( "PS_Detail_settlements_addMenuxpath" );
		ElementHelper.waitForElement( GenericHelper.getORProperty( "PS_Detail_settlements_addXpath" ), searchScreenWaitSec );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		MouseHelper.mouseOver( "PS_Detail_settlements_addXpath" );
		String addOptionsXpath = GenericHelper.getORProperty( "PS_Detail_settlements_addMenu_option_xpath" ).replace( "options", addOption );
		MouseHelper.click( addOptionsXpath );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		configAddOption( addOption, index, row );

	}

	/*
	 * This method is for edit select the add menu in details screen of Settlements 
	 */
	private void editAddOptions( String addOption, int index ) throws Exception
	{
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		ElementHelper.waitForClickableElement( GenericHelper.getORProperty( "PS_Detail_settlements_addMenuxpath" ), searchScreenWaitSec );
		if ( isProfilePresentInGrid( addOption, "settlementsGrid", index ) )
			Log4jHelper.logInfo( "The profile '" + billProfileAddArr[index] + "' is already present on settlement grid add option for '" + addOption );
		else
		{
			int row = GridHelper.getRowCount( "settlementsGrid" );
			MouseHelper.rightClick( "PS_Detail_settlements_addMenuxpath" );
			ElementHelper.waitForElement( GenericHelper.getORProperty( "PS_Detail_settlements_addXpath" ), searchScreenWaitSec );
			GenericHelper.waitForLoadmask( searchScreenWaitSec );
			MouseHelper.mouseOver( "PS_Detail_settlements_addXpath" );
			String addOptionsXpath = GenericHelper.getORProperty( "PS_Detail_settlements_addMenu_option_xpath" ).replace( "options", addOption );
			MouseHelper.click( addOptionsXpath );
			GenericHelper.waitForLoadmask( searchScreenWaitSec );
			configAddOption( addOption, index, row );
		}
	}

	/*
	 * This method is for add the Bill/Credit Note/Carrier Invoice profile in details screen of Settlements
	 */
	private void configAddOption( String addOption, int index, int row ) throws Exception
	{
		if ( addOption.contains( "Bill" ) )
		{

			if ( PopupHelper.isPresent() )
				ButtonHelper.click( "OKButton" );
			GenericHelper.waitForLoadmask( searchScreenWaitSec );
			List<String> listColumn = getKeysOfBillGrid();
			gridKeyValuemap = dataSelectionHelper.billsearch( accountAddArr[index], billProfileAddArr[index], fromDtAddArr[index], toDtAddArr[index], "SearchGrid", "grid_column_header_searchGrid_", listColumn );
			GenericHelper.waitForLoadmask( searchScreenWaitSec );
			GenericHelper.waitForLoadmask( searchScreenWaitSec );
			refershAmountBillAssertion( gridKeyValuemap, row );
			Log4jHelper.logInfo( "Bill assertion is completed successfully on settlementGrid for this bill profil:-" + billProfileAddArr[index] );
		}
		if ( addOption.contains( "Credit Note" ) )
		{

			List<String> listColumn = getKeysOfCreditGrid();
			gridKeyValuemap = dataSelectionHelper.creditNoteSearch( billProfileAddArr[index], amount_creditNtArr[index], "ps_detail_settlements_creditNoteReference_iconId", "ps_detail_settlements_creditNoteReference_descSortMenuId", "SearchGrid", "grid_column_header_searchGrid_", listColumn );
			GenericHelper.waitForLoadmask( searchScreenWaitSec );
			refershAmountCreditAssertion( gridKeyValuemap, row );
			Log4jHelper.logInfo( "Credit Note assertion is completed successfully on settlementGrid for this bill profil:-" + billProfileAddArr[index] );
		}
		if ( addOption.contains( "Carrier Invoice" ) )
		{

			if ( PopupHelper.isPresent() )
				ButtonHelper.click( "OKButton" );
			GenericHelper.waitForLoadmask( searchScreenWaitSec );
			List<String> listColumn = getKeysOfCIGrid();
			gridKeyValuemap = dataSelectionHelper.carrierInvoiceSearch( accountAddArr[index], billProfileAddArr[index], fromDtAddArr[index], toDtAddArr[index], "SearchGrid", "grid_column_header_searchGrid_", listColumn );
			GenericHelper.waitForLoadmask( searchScreenWaitSec );
			refershAmountCIAssertion( gridKeyValuemap, row );
			Log4jHelper.logInfo( "Carrier invoice assertion is completed successfully on settlementGrid for this bill profil:-" + billProfileAddArr[index] );
		}
	}

	//Method: assertion of Bill
	protected void refershAmountBillAssertion( Map<String, String> gridKeyValuemap, int row ) throws Exception
	{
		ElementHelper.waitForElement( "//div[@id='settlementsGrid']", searchScreenWaitSec );
		row++;

		String actualProfile = GridHelper.getCellValue( "settlementsGrid", row, "Profile" );
		assertEquals( actualProfile, gridKeyValuemap.get( "Bill Profile" ), "Profile is not matched on settlement grid for Bill" );

		String actualReferenceNo = GridHelper.getCellValue( "settlementsGrid", row, "Reference" );
		assertEquals( actualReferenceNo, gridKeyValuemap.get( "Bill Ref No" ), "Bill Ref No is not matched on settlement grid for Bill" );

		String actualAmount = GridHelper.getCellValue( "settlementsGrid", row, "Amount" );
		assertTrue( gridKeyValuemap.get( "Bill Amount" ).contains( actualAmount ), "Amount is not matched on settlement grid for Bill" );

		String actualCurrency = GridHelper.getCellValue( "settlementsGrid", row, "Currency" );
		assertEquals( actualCurrency, gridKeyValuemap.get( "Bill Profile Currency" ), "Currency  is not matched on settlement grid for Bill" );

		String actualFromDt = GridHelper.getCellValue( "settlementsGrid", row, "From" );
		assertEquals( actualFromDt, gridKeyValuemap.get( "Bill Period From" ), "Bill period from date  is not matched on settlement grid for Bill" );

		String actualToDt = GridHelper.getCellValue( "settlementsGrid", row, "To" );
		assertEquals( actualToDt, gridKeyValuemap.get( "Bill Period To" ), "Bill period to  is not matched on settlement grid  for Bill" );

		String actualType = GridHelper.getCellValue( "settlementsGrid", row, "Type" );
		assertEquals( actualType, "Bill", "Type  is not matched on settlement grid for Bill" );

	}

	//Method: assertion of CI
	protected void refershAmountCIAssertion( Map<String, String> gridKeyValuemap, int row ) throws Exception
	{
		ElementHelper.waitForElement( "//div[@id='settlementsGrid']", searchScreenWaitSec );
		row++;

		String actualProfile = GridHelper.getCellValue( "settlementsGrid", row, "Profile" );
		assertEquals( actualProfile, gridKeyValuemap.get( "Bill Profile" ), "Profile is not matched on settlement grid" );

		String actualAmount = GridHelper.getCellValue( "settlementsGrid", row, "Amount" );
		assertTrue( gridKeyValuemap.get( "Total Amount" ).contains( actualAmount ), "Amount is not matched on settlement grid" );

		String actualCurrency = GridHelper.getCellValue( "settlementsGrid", row, "Currency" );
		assertEquals( actualCurrency, gridKeyValuemap.get( "Currency" ), "Currency  is not matched on settlement grid" );

		String actualFromDt = GridHelper.getCellValue( "settlementsGrid", row, "From" );
		assertEquals( actualFromDt, gridKeyValuemap.get( "From" ), "Bill period from date  is not matched on settlement grid" );

		String actualToDt = GridHelper.getCellValue( "settlementsGrid", row, "To" );
		assertEquals( actualToDt, gridKeyValuemap.get( "To" ), "Bill period to  is not matched on settlement grid" );

		String actualType = GridHelper.getCellValue( "settlementsGrid", row, "Type" );
		assertEquals( actualType, "Carrier Invoice", "Type  is not matched on settlement grid" );

	}

	//Method: assertion of 
	protected void refershAmountCreditAssertion( Map<String, String> gridKeyValuemap, int row ) throws Exception
	{
		ElementHelper.waitForElement( "//div[@id='settlementsGrid']", searchScreenWaitSec );
		row++;

		String actualProfile = GridHelper.getCellValue( "settlementsGrid", row, "Profile" );
		assertEquals( actualProfile, gridKeyValuemap.get( "Bill Profile" ), "Profile  is not matched on settlement grid for credit note" );

		String actualAmount = GridHelper.getCellValue( "settlementsGrid", row, "Amount" );
		assertTrue( gridKeyValuemap.get( "Total Amount" ).contains( actualAmount ), "Amount  is not matched on settlement grid for credit note" );

		String actualCurrency = GridHelper.getCellValue( "settlementsGrid", row, "Currency" );
		assertEquals( actualCurrency, gridKeyValuemap.get( "Currency" ), "Currency  is not matched on settlement grid for credit note" );

		String actualType = GridHelper.getCellValue( "settlementsGrid", row, "Type" );
		assertEquals( actualType, "Credit Note", "Type  is not matched on settlement grid for credit note" );
	}

	private Map<String, String> getExpectedData( String addOption, int index )
	{
		Map<String, String> hmap = new HashMap<String, String>();
		hmap.put( "Bill Profile", billProfileAddArr[index] );
		if ( addOption.contains( "Bill" ) || addOption.contains( "Carrier Invoice" ) )
		{
			hmap.put( "From", fromDtAddArr[index] );
			hmap.put( "To", toDtAddArr[index] );
		}
		if ( addOption.contains( "Bill" ) )
			hmap.put( "Type", "Bill" );
		else if ( addOption.contains( "Credit Note" ) )
		{
			hmap.put( "Type", "Credit Note" );
			hmap.put( "Amount", amount_creditNtArr[index] );
		}
		else if ( addOption.contains( "Carrier Invoice" ) )
			hmap.put( "Type", "Carrier Invoice" );

		return hmap;
	}

	/*
	 * This Method is to find profile is present in the settlement grid
	 */
	private boolean isProfilePresentInGrid( String addOption, String gridId, int index ) throws Exception
	{

		try
		{
			int totalRow = GridHelper.getRowCount( gridId );
			for ( int i = 0; i < totalRow; i++ )
			{
				if ( isDataMatch( gridId, i + 1, addOption, index ) )
					return true;
			}
			return false;
		}
		catch ( Exception e )
		{

			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

	/*
	 * This method is to check  data is present in settlement grid 
	 */
	private boolean isDataMatch( String gridId, int row, String addOption, int index ) throws Exception
	{
		boolean checkConditions = false;
		Map<String, String> getExpectedValue = null;
		getExpectedValue = getExpectedData( addOption, index );
		String expectedtype = getExpectedValue.get( "Type" );
		String expectedBillprofile = getExpectedValue.get( "Bill Profile" );

		String type = GridHelper.getCellValue( gridId, row, "Type" );
		String profile = GridHelper.getCellValue( gridId, row, "Profile" );

		if ( expectedtype.contentEquals( "Bill" ) || expectedtype.contentEquals( "Carrier Invoice" ) )
		{
			String fromDt = GridHelper.getCellValue( gridId, row, "From" );
			String toDt = GridHelper.getCellValue( gridId, row, "To" );
			String expectedfromDate = getExpectedValue.get( "From" );
			String expectedtoDt = getExpectedValue.get( "To" );
			checkConditions = ( type.equals( expectedtype ) && profile.contains( expectedBillprofile ) && expectedfromDate.contains( fromDt ) && expectedtoDt.contains( toDt ) );
		}
		else
		{
			String amount = GridHelper.getCellValue( gridId, row, "Amount" );
			String expectwedAmount = getExpectedValue.get( "Amount" );
			checkConditions = ( type.equals( expectedtype ) && profile.contains( expectedBillprofile ) && amount.contentEquals( expectwedAmount ) );
		}
		return checkConditions;
	}

	/*
	 * This method is for save the Settlement
	 */
	public void saveSettlement() throws Exception
	{
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		ButtonHelper.click( "PS_Detail_settlements_save_BtndId" );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		boolean isSchScreenPresent = ElementHelper.isElementPresent( GenericHelper.getORProperty( "PS_searchPanelId" ) );
		if ( !isSchScreenPresent )
			ElementHelper.waitForElement( "//div[@id='searchPanel']", searchScreenWaitSec );
		assertTrue( isSchScreenPresent, "Failed to save settlement-- " + LabelHelper.getText( "errorText" ) );
	}

}
