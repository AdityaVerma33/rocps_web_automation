package com.subex.rocps.automation.helpers.application.bills.creditnotes;

import org.testng.Assert;

import java.util.ArrayList;
import java.util.List;

import com.subex.rocps.automation.helpers.application.filters.GridFilterSearchHelper;
import com.subex.rocps.automation.helpers.application.genericHelpers.DataVerificationHelper;
import com.subex.rocps.automation.helpers.application.genericHelpers.PSEntityComboHelper;
import com.subex.rocps.automation.helpers.application.genericHelpers.PSGenericHelper;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;
import com.subex.rocps.automation.utils.PSStringUtils;

import com.subex.automation.helpers.application.NavigationHelper;
import com.subex.automation.helpers.component.ButtonHelper;
import com.subex.automation.helpers.component.CheckBoxHelper;
import com.subex.automation.helpers.component.ComboBoxHelper;
import com.subex.automation.helpers.component.ElementHelper;
import com.subex.automation.helpers.component.EntityComboHelper;
import com.subex.automation.helpers.component.GenericHelper;
import com.subex.automation.helpers.component.GridHelper;
import com.subex.automation.helpers.component.PopupHelper;
import com.subex.automation.helpers.component.TextAreaHelper;
import com.subex.automation.helpers.component.TextBoxHelper;
import com.subex.automation.helpers.data.DateHelper;
import com.subex.automation.helpers.data.ValidationHelper;
import com.subex.automation.helpers.report.Log4jHelper;

public class CreditNotesDetailImpl extends PSAcceptanceTest
{
	CreditNotesActionImpl creditActionObj = new CreditNotesActionImpl();
	GridFilterSearchHelper gridSearchObj = new GridFilterSearchHelper();
	PSGenericHelper genericHelperObj = new PSGenericHelper();
	CreditNotesSearchImpl creditSearchObj = new CreditNotesSearchImpl();
	DataVerificationHelper dataVerifyObj = new DataVerificationHelper();
	PSStringUtils psStringObj = new PSStringUtils();

	/*
	 * This method is for credit notes basic details
	 */
	public void basicDetails( String billProfile, String creditDate, String currency, String creditReason, String remarks, String creditDue, String applySalesTax, String includeBill ) throws Exception
	{
		PSEntityComboHelper.selectUsingGridFilterTextBox( "billProfile", "Bill Profile Search", "pbipName", billProfile, "Bill Profile Name" );
		String date = DateHelper.getCurrentDate();
		TextBoxHelper.type( "PS_Detail_creditNote_calenderID", date );
		ComboBoxHelper.select( "PS_Detail_creditNote_currency_comboId", currency );
		ComboBoxHelper.select( "PS_Detail_creditNote_creditReasonTxtID", creditReason );
		TextBoxHelper.type( "PS_Detail_creditNote_remarks_txtID", remarks );

		if ( !ValidationHelper.isTrue( includeBill ) )
		{
			CheckBoxHelper.uncheck( "PS_Detail_creditNote_includeBill_chkbx" );
			TextBoxHelper.type( "PS_Detail_creditNote_creditDue_txtbxID", creditDue );
		}
		if ( ValidationHelper.isFalse( applySalesTax ) )
			CheckBoxHelper.uncheck( "PS_Detail_creditNote_salesTax_checkbxID" );
	}

	/*
	 * This method is for credit note standalone detail
	 */
	public void creditLineItemStandaloneDetail( String creditDescArr, String creditNetAmtArr, String salesTaxGrpArr, String salesTaxAmtArr, String overrideSalesTaxArr, String applySalesTax, String salesTaxAmt ) throws Exception
	{

		TextBoxHelper.type( "PS_Detail_creditNote_creditlineitem_wrapperID", "PS_Detail_creditNote_creditDesc_txtID", creditDescArr );

		TextBoxHelper.type( "PS_Detail_creditNote_creditlineitem_wrapperID", "PS_Detail_creditNote_creditNetAmt_txtID", creditNetAmtArr );

		if ( applySalesTax.contentEquals( "TRUE" ) && !applySalesTax.isEmpty() )
		{
			ComboBoxHelper.select( "PS_Detail_creditNote_creditlineitem_wrapperID", "PS_Detail_creditNote_salesTaxGrp_comboID", salesTaxGrpArr );
			if ( overrideSalesTaxArr.contentEquals( "TRUE" ) )
				CheckBoxHelper.check( "PS_Detail_creditNote_overrideSalesTax_chckbox" );

			if ( !salesTaxAmtArr.isEmpty() )
				TextBoxHelper.type( "PS_Detail_creditNote_creditlineitem_wrapperID", "PS_Detail_creditNote_salesTaxAmt_txtID", salesTaxAmtArr );
		}

	}

	/*
	 * This method is for bill linked credit notes detail 
	 */
	public void billLinkedToCreditNotesDetail( String billProfile, String billPeriod, int row ) throws Exception
	{
		String[] billColHeaders =
		{ "Bill Ref No", "Balance Amount", "Bill Amount", "Due Date" };
		String[] billCreditColHeaders =
		{ "Bill  Reference", "Balance  Amount", "Bill  Amount", "Bill  Date" };

		assertTrue( creditSearchObj.billperiodSearch( billPeriod, billProfile),"Bill not found" );
		GridHelper.clickRow( "SearchGrid", 1, 1 );
		ArrayList<String> billInfo = dataVerification( "searchGrid", billColHeaders, "grid_column_header_searchGrid_", 1 );
		ButtonHelper.click( "OK_TRT_Button" );
		GenericHelper.waitForLoadmask();
		isPopupPresent();
		isPopupPresent();
		ArrayList<String> bilCreditInfo = dataVerification( "billGrid", billCreditColHeaders, "grid_column_header_undefined_", row );
		String actualValue = psStringObj.stringformation( bilCreditInfo );
		String expectedValue = psStringObj.stringformation( billInfo );
		Log4jHelper.logInfo( "Bill Information Actual value : " + actualValue );
		Log4jHelper.logInfo( "Credit Bill Linked grid info Expected value : " + expectedValue );
		Assert.assertEquals( actualValue, expectedValue, "Values are not matching " );
	}

	/*
	 * This method is for data verification
	 */
	public ArrayList<String> dataVerification( String gridID, String[] colHeaders, String colHeaderSection, int row ) throws Exception
	{

		ArrayList<String> value = new ArrayList<String>();
		List<String> UIColumns = genericHelperObj.getGridColumns( colHeaderSection, gridID );
		String[] dateArr = null;
		for ( int i = 0; i < colHeaders.length; i++ )
		{
			int colNum = UIColumns.indexOf( colHeaders[i] );

			String cellValue = GridHelper.getCellValue( gridID, row, colNum + 1 );
			if ( cellValue.contains( "00:00:00" ) )
			{
				dateArr = cellValue.split( " ", -1 );
				cellValue = dateArr[0];
			}
			value.add( cellValue );
		}
		return value;
	}

	/*
	 * This method is for creditlineIteam detail section
	 */
	public void creditLineItemsDetail( String billBreakdownConfigArr, String billCreditLineItemValueArr, String descTextArr, int row, String billCreditLineItemColHeaderArr ) throws Exception
	{
		String[] billbreakdownConfigAr = billBreakdownConfigArr.split( secondLevelDelimiter );
		String[] billCreditLineItemValueAr = billCreditLineItemValueArr.split( secondLevelDelimiter );
		String[] descTextAr = descTextArr.split( secondLevelDelimiter );
		String[] billCreditLineItemColHeaderAr = billCreditLineItemColHeaderArr.split( secondLevelDelimiter );

		String billRef = GridHelper.getCellValue( "billGrid", row + 1, "Bill  Reference" ).concat( " :" );
		String currency = ComboBoxHelper.getValue( "currency_gwt_uid_" );
		String billRefInfo = "Bill:".concat( billRef + currency ).trim();
		for ( int i = 0; i < billCreditLineItemValueAr.length; i++ )
		{
			creditActionObj.addCreditLineItem();
			//assertEquals( NavigationHelper.getScreenTitle(), "Add Credit Line Items From Bill(s)" );
			assertTrue(ElementHelper.isElementPresent( "//div[contains(text(),'Bill  Breakdown  Config')]" ), "Add Credit Line Items From Bill(s) screen is not opened");
			
			ComboBoxHelper.select( "PS_Detail_creditNote_billref_comboID", billRefInfo );
			if ( !billBreakdownConfigArr.isEmpty() )
				ComboBoxHelper.select( "PS_Detail_creditNote_billbreakdownConfig_comboID", billbreakdownConfigAr[i] );
			ElementHelper.waitForElement( "//*[@id='billLineItemDetail.window']//div[contains(text(),'Bill  Breakdown  Config')]", searchScreenWaitSec );
			Thread.sleep( 1000 );
			GridHelper.clickRow( "PS_Detail_creditNote_billLineItem_GridID", billCreditLineItemValueAr[i], billCreditLineItemColHeaderAr[i] );
			GenericHelper.waitForLoadmask();
			ButtonHelper.click( "PS_Detail_creditNote_billLineItem_OkbuttonID" );
			GenericHelper.waitForLoadmask();

			description( descTextAr[i] );
			creditLineItemValidate( i + 1 );

		}

	}

	/*
	 * This method is to edit credit Line Item Detail
	 */
	public void editCreditLineItemDetail( int row, String creditNetAmtAr, String salesTaxAmtAr, String overrideSalesTaxAmtAr, String billLinked, String applySalesTax, String salesTaxGrpArr ) throws Exception
	{

		String applySalesTaxExpected = String.valueOf( CheckBoxHelper.isChecked( "PS_Detail_creditNote_salesTax_checkbxID" ) );
		GridHelper.clickRow( "creditGrid", row, "Description" );
		ButtonHelper.click( "creditGridToolbar.Edit" );
		GenericHelper.waitForLoadmask();
		if ( !creditNetAmtAr.isEmpty() )
			TextBoxHelper.type( "PS_Detail_creditNote_creditlineitem_wrapperID", "PS_Detail_creditNote_creditNetAmt_txtID", creditNetAmtAr );
		if ( ValidationHelper.isTrue( applySalesTaxExpected ) )
		{
			if ( overrideSalesTaxAmtAr.contentEquals( "TRUE" ) )
				CheckBoxHelper.check( "PS_Detail_creditNote_overrideSalesTax_chckbox" );
			if ( !salesTaxAmtAr.isEmpty() )
				TextBoxHelper.type( "PS_Detail_creditNote_creditlineitem_wrapperID", "PS_Detail_creditNote_salesTaxAmt_txtID", salesTaxAmtAr );

			if ( ValidationHelper.isFalse( billLinked ) )
				ComboBoxHelper.select( "PS_Detail_creditNote_creditlineitem_wrapperID", "PS_Detail_creditNote_salesTaxGrp_comboID", salesTaxGrpArr );
		}

		ButtonHelper.click( "PS_Detail_creditNote_creditlineitem_wrapperID", "PS_Detail_creditNote_creditLineItem_okButton" );
		GenericHelper.waitForLoadmask();

	}

	/*
	 * This method is for credit line item data validation
	 */

	public void creditLineItemValidate( int row ) throws Exception
	{

		String[] colHeaders =
		{ "Description", "Net  Amount", "Sales  Tax  Amount", "Credit  Amount" };
		ArrayList<String> creditDetail = dataVerification( "creditGrid", colHeaders, "grid_column_header_undefined_", row );

		String actualValue = psStringObj.stringformation( creditDetail );
		Log4jHelper.logInfo( "Credit Line Item Actual value : " + actualValue );

	}

	/*
	 * This method is for description popup
	 */
	public void description( String descTextAr ) throws Exception
	{
		if ( PopupHelper.isPresent( "PS_popup_billAction_InformationID" ) )
		{
			assertEquals( NavigationHelper.getScreenTitle(), "Description" );
			TextAreaHelper.type( "PS_Detail_creditNote_billLineItem_desc_txtID", descTextAr );
			ButtonHelper.click( "PS_Detail_creditNote_desc_OkButton" );
			GenericHelper.waitForLoadmask();
		}
	}

	/*
	 * This method is to save credit notes
	 */
	public void saveCreditNotes() throws Exception
	{
		ButtonHelper.click( "creditDetail.save" );
		GenericHelper.waitForLoadmask();
	}

	/*
	 * This method is for credit history
	 */
	public void creditHistory( String creditHistoryColHeaders, String creditHistoryResults ) throws Exception
	{
		int row = GridHelper.getRowCount( "SearchGrid" );
		creditActionObj.creditHistoryAction( row );
		assertEquals( NavigationHelper.getScreenTitle(), "Credit History" );
		if ( ValidationHelper.isNotEmpty( creditHistoryResults ) )
			dataVerifyObj.validateData( "grid_column_header_undefined_", creditHistoryColHeaders, "creditHistoryGrid", creditHistoryResults );

		ButtonHelper.click( "creditHistoryDetail.Close" );
		GenericHelper.waitForLoadmask();
	}

	/*
	 * This method is for Confirmation Popup message check
	 */
	private void isPopupPresent() throws Exception
	{

		boolean flag = false;

		if ( PopupHelper.isPresent( "PS_popup_billAction_InformationID" ) )
		{
			String salesLineItemMsg = "Credit note Apply sales tax at line item value is not matching with the selected bill(s) apply sales tax at line item value. Do you want to change?";
			String currencymsg = "Credit note currency is not matching with the selected bill(s) currency. Do you want to change?";
			assertEquals( NavigationHelper.getScreenTitle(), "Confirm" );
			String pop = ElementHelper.getText( "//div[@id='window-scroll-panel']//div[@title]" );

			if ( pop.equals( salesLineItemMsg ) )
			{
				flag = true;
				Log4jHelper.logInfo( "Actual value : " + pop );
				Log4jHelper.logInfo( "expected value : " + salesLineItemMsg );
				ButtonHelper.click( "OK_TRT_Button" );
				GenericHelper.waitForLoadmask();

			}
			else if ( pop.equals( currencymsg ) )
			{
				flag = true;
				Log4jHelper.logInfo( "Actual value : " + pop );
				Log4jHelper.logInfo( "expected value : " + currencymsg );
				ButtonHelper.click( "OK_TRT_Button" );
				GenericHelper.waitForLoadmask();
			}
			else

			{
				Log4jHelper.logInfo( "expected value : " + salesLineItemMsg );
				Log4jHelper.logInfo( "expected value : " + currencymsg );

				assertFalse( flag, "pop texts are not matching : " + pop );
			}
		}
	}
}
