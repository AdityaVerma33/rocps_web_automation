package com.subex.rocps.automation.helpers.application.bills;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.subex.rocps.automation.helpers.application.approvalworkflows.approvalworkflows.ApprovalWorkFlowActionImpl;
import com.subex.rocps.automation.helpers.application.approvalworkflows.approvalworkflows.ApprovalWorkFlowDetailImpl;
import com.subex.rocps.automation.helpers.application.bills.creditnotes.CreditNotesActionImpl;
import com.subex.rocps.automation.helpers.application.bills.creditnotes.CreditNotesDetailImpl;
import com.subex.rocps.automation.helpers.application.bills.creditnotes.CreditNotesSearchImpl;
import com.subex.rocps.automation.helpers.application.filters.GridFilterSearchHelper;
import com.subex.rocps.automation.helpers.application.genericHelpers.DataVerificationHelper;
import com.subex.rocps.automation.helpers.application.genericHelpers.PSGenericHelper;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;
import com.subex.rocps.automation.utils.ExcelHolder;
import com.subex.rocps.automation.utils.PSStringUtils;

import com.subex.automation.helpers.application.NavigationHelper;
import com.subex.automation.helpers.component.ButtonHelper;
import com.subex.automation.helpers.component.CheckBoxHelper;
import com.subex.automation.helpers.component.ComboBoxHelper;
import com.subex.automation.helpers.component.EntityComboHelper;
import com.subex.automation.helpers.component.GenericHelper;
import com.subex.automation.helpers.component.GridHelper;
import com.subex.automation.helpers.component.PopupHelper;
import com.subex.automation.helpers.component.TextAreaHelper;
import com.subex.automation.helpers.component.TextBoxHelper;
import com.subex.automation.helpers.data.ValidationHelper;
import com.subex.automation.helpers.file.ExcelReader;
import com.subex.automation.helpers.report.Log4jHelper;
import com.subex.automation.helpers.util.FailureHelper;

public class CreditNotes extends PSAcceptanceTest
{
	protected ExcelReader excelData = null;
	protected Map<String, ArrayList<String>> creditExcel = null;
	protected Map<String, String> creditMap = null;
	protected ExcelHolder excelHolderObj = null;
	protected String path;
	protected String workBookName;
	protected String sheetName;
	protected String testCaseName;
	protected int paramVal;
	protected int colSize;
	protected String clientPartition;
	protected String billProfile;
	protected String creditDate;
	String currency;
	String creditReason;
	String remarks;
	String reference;
	String creditDue;
	String includeNextBill;
	String applySalesTax;
	String creditDesc;
	String creditNetAmt;
	String salesTaxGrp;
	String salesTaxAmt;
	String totalAmt;
	String overrideSalesTax;
	String billbreakdownConfig;
	String billCreditLineItemValue;
	String billCreditLineItemColHeader;
	String description;
	String billPeriod;
	String expectedCurrSalesTaxValue;
	String columnHeaders;
	String results;
	String billLinked;
	String creditHistoryResults;
	String creditHistoryColHeader;
	String cancelReason;
	protected PSGenericHelper genericHelperObj = new PSGenericHelper();
	protected String regex = new PSStringUtils().regexFirstLevelDelimeter();
	CreditNotesActionImpl creditActionObj = new CreditNotesActionImpl();
	CreditNotesDetailImpl creditDetailObj = new CreditNotesDetailImpl();
	CreditNotesSearchImpl creditSearchObj = new CreditNotesSearchImpl();
	GridFilterSearchHelper gridHelperObj = new GridFilterSearchHelper();
	DataVerificationHelper dataVerifyObj = new DataVerificationHelper();

	/*
	 * Constructor for initializing excel Identifying the column size from the
	 * map passed
	 */
	@Test
	public CreditNotes( String path, String workBookName, String sheetName, String testCaseName ) throws Exception
	{
		this.path = path;
		this.workBookName = workBookName;
		this.sheetName = sheetName;
		this.testCaseName = testCaseName;
		excelData = new ExcelReader();
		creditExcel = excelData.readDataByColumn( this.path, this.workBookName, this.sheetName, this.testCaseName );
		excelHolderObj = new ExcelHolder( creditExcel );
		colSize = excelHolderObj.totalColumns();
	}

	/*
	 * Overloaded constructor for reading data from excel if test case name
	 * appears more than once
	 * 
	 * @Param occurance : Test case instance in excel sheet Constructor for
	 * initializing excel Identifying the column size from the map
	 */
	public CreditNotes( String path, String workBookName, String sheetName, String testCaseName, int occurance ) throws Exception
	{
		this.path = path;
		this.workBookName = workBookName;
		this.sheetName = sheetName;
		this.testCaseName = testCaseName;
		excelData = new ExcelReader();
		creditExcel = excelData.readDataByColumn( this.path, this.workBookName, this.sheetName, this.testCaseName, occurance );
		excelHolderObj = new ExcelHolder( creditExcel );
		colSize = excelHolderObj.totalColumns();
	}

	/*
	 * Configuring the Credit Notes
	 * 
	 */
	public void creditNotesCreation() throws Exception
	{
		try
		{
			NavigationHelper.navigateToScreen( "Credit Notes" );
			for ( paramVal = 0; paramVal < colSize; paramVal++ )
			{
				creditMap = excelHolderObj.dataMap( paramVal );

				initializeVariables( creditMap );
				ButtonHelper.click( "ClearButton" );
				gridHelperObj.billProfileAdvanceFilter( "SearchGrid", "Bill Profile", billProfile );
				newCreditNotes();
			}
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

	/*
	 * This method is to create new Credit Notes
	 */
	protected void newCreditNotes() throws Exception
	{
		int initalrowCount = GridHelper.getRowCount( "searchGrid" );
		creditActionObj.clickNewAction( clientPartition );
		creditDetailObj.basicDetails( billProfile, creditDate, currency, creditReason, remarks, creditDue, applySalesTax, includeNextBill );
		if ( ValidationHelper.isNotEmpty( billLinked ) && ValidationHelper.isTrue( billLinked ) )

			billLinkedCreditNote();
		else
			creditLineItemStandalone();

		creditDetailObj.saveCreditNotes();
		Log4jHelper.logInfo( "Credit Notes is created successfully for " + billProfile );

		creditNoteValidate( initalrowCount, creditNetAmt );
		creditDetailObj.creditHistory( creditHistoryColHeader, creditHistoryResults );
		Log4jHelper.logInfo( "Credit Notes is Validated successfully for " + billProfile );
	}

	/*
	 * This method is for standlaone credit Note
	 */
	public void creditLineItemStandalone() throws Exception
	{
		String[] creditDescArr = creditDesc.split( regex, -1 );
		String[] creditNetAmtArr = creditNetAmt.split( regex, -1 );
		String[] salesTaxGrpArr = salesTaxGrp.split( regex, -1 );
		String[] salesTaxAmtArr = salesTaxAmt.split( regex, -1 );
		String[] overrideSalesTaxArr = overrideSalesTax.split( regex, -1 );

		for ( int j = 0; j < creditDescArr.length; j++ )
		{
			creditActionObj.addCreditLineItem();
			creditDetailObj.creditLineItemStandaloneDetail( creditDescArr[j], creditNetAmtArr[j], salesTaxGrpArr[j], salesTaxAmtArr[j], overrideSalesTaxArr[j], applySalesTax, salesTaxAmt );
			ButtonHelper.click( "PS_Detail_creditNote_creditlineitem_wrapperID", "PS_Detail_creditNote_creditLineItem_okButton" );
			GenericHelper.waitForLoadmask();			
			creditDetailObj.creditLineItemValidate( j + 1 );
		}
	}

	/*
	 * This method is for bill linked credit Note
	 */

	public void billLinkedCreditNote() throws Exception
	{
		String[] billPeriodArr = billPeriod.split( regex, -1 );
		String[] billbreakdownConfigArr = billbreakdownConfig.split( regex, -1 );
		String[] billCreditLineItemValueArr = billCreditLineItemValue.split( regex, -1 );
		String[] descArr = description.split( regex, -1 );
		String[] billCreditLineItemColHeaderArr = billCreditLineItemColHeader.split( regex, -1 );

		for ( int row = 0; row < billPeriodArr.length; row++ )
		{
			creditActionObj.addBillLinkedToCredit();
			creditDetailObj.billLinkedToCreditNotesDetail( billProfile, billPeriodArr[row], row + 1 );
			verifySalesTaxCurrency();
			creditDetailObj.creditLineItemsDetail( billbreakdownConfigArr[row], billCreditLineItemValueArr[row], descArr[row], row, billCreditLineItemColHeaderArr[row] );
		}
	}

	/*
	 * This method is to verify sales tax and currency
	 */
	public void verifySalesTaxCurrency() throws Exception
	{
		String currencyExpected = ComboBoxHelper.getValue( "PS_Detail_creditNote_currency_comboId" );
		String salesTaxExpected = String.valueOf( CheckBoxHelper.isChecked( "PS_Detail_creditNote_salesTax_checkbxID" ) );
		String[] expectedValueArr = expectedCurrSalesTaxValue.split( regex, -1 );

		assertEquals( expectedValueArr[0], currencyExpected );
		assertEquals( expectedValueArr[1], salesTaxExpected );
		Log4jHelper.logInfo( "Currency Excel value :'" + expectedValueArr[0] + "' Expected Value :'" + currencyExpected + "' " );
		Log4jHelper.logInfo( "Sales Tax Excel value: '" + expectedValueArr[1] + "' Expected Value :'" + salesTaxExpected + "' " );
	}

	/*
	 * This method is for credit note final validation
	 */
	public void creditNoteValidate( int initalrowCount, String creditAmt ) throws Exception
	{
		String[] expectedValueArr = expectedCurrSalesTaxValue.split( regex, -1 );
		String searchGridCurrency = expectedValueArr[0];
		assertTrue( creditSearchObj.rowscount( initalrowCount ) );
		if ( ValidationHelper.isNotEmpty( columnHeaders ) && ValidationHelper.isNotEmpty( results ) )
		{
			assertTrue( creditSearchObj.creditNotesFilterSelection( billProfile, searchGridCurrency, creditAmt, includeNextBill ) );
			dataVerifyObj.validateData( "grid_column_header_searchGrid_", columnHeaders, "searchGrid", results );
		}
	}

	/*
	 * This method is to change creditnote status
	 */
	public void changeCreditNoteStatus() throws Exception
	{
		NavigationHelper.navigateToScreen( "Credit Notes" );
		for ( paramVal = 0; paramVal < colSize; paramVal++ )
		{
			creditMap = excelHolderObj.dataMap( paramVal );
			initializeVariables( creditMap );
			String[] expectedValueArr = expectedCurrSalesTaxValue.split( regex, -1 );
			String searchGridCurrency = expectedValueArr[0];
			boolean isCreditNotesPresent = creditSearchObj.creditNotesFilterSelection( billProfile, searchGridCurrency, creditNetAmt, includeNextBill );
			int row = GridHelper.getRowCount( "SearchGrid" );
			if ( isCreditNotesPresent )
			{
				assertTrue( creditSearchObj.statusFilter( "PS_Detail_creditNote_status_gridFilterID", "PS_Detail_creditNote_status_txtbxID", "Draft", "Status" ) );
				creditActionObj.clickApproveCreditNoteAction( row );
				assertTrue( PopupHelper.isTextPresent( "Are you sure you wish to approve the selected credit note(s)" ), "popup didt not appear" );
				ButtonHelper.click( "YesButton" );
				GenericHelper.waitForLoadmask();
				assertEquals( NavigationHelper.getScreenTitle(), "Information" );
				ButtonHelper.click( "OK_TRT_Button" );
				assertTrue( creditSearchObj.creditNotesFilterSelection( billProfile, currency, creditNetAmt, includeNextBill ) );
				assertTrue( creditSearchObj.statusFilter( "PS_Detail_creditNote_status_gridFilterID", "PS_Detail_creditNote_status_txtbxID", "Approved", "Status" ) );
				Log4jHelper.logInfo( "Credit Notes change status is successfull " + billProfile );
			}
			else
				Log4jHelper.logInfo( "Credit Notes is not available for name " + billProfile );
		}
	}

	/*
	 * This method is to cancel credit note
	 */
	public void cancelCreditNote() throws Exception
	{
		NavigationHelper.navigateToScreen( "Credit Notes" );
		for ( paramVal = 0; paramVal < colSize; paramVal++ )
		{
			creditMap = excelHolderObj.dataMap( paramVal );
			initializeVariables( creditMap );
			String cancelPopup = "Are you sure you wish to cancel the selected credit note(s)?";
			String[] expectedValueArr = expectedCurrSalesTaxValue.split( regex, -1 );
			String searchGridCurrency = expectedValueArr[0];
			boolean isCreditNotesPresent = creditSearchObj.creditNotesFilterSelection( billProfile, searchGridCurrency, creditNetAmt, includeNextBill );
			int row = GridHelper.getRowCount( "SearchGrid" );
			if ( isCreditNotesPresent )
			{
				creditActionObj.clickCancelCreditNoteAction( row );
				GenericHelper.waitForLoadmask();
				assertTrue( PopupHelper.isPresent( "window-scroll-panel" ) );
				assertTrue( PopupHelper.isTextPresent( "window-scroll-panel", cancelPopup ) );
				TextAreaHelper.type( "cancelReason", cancelReason );
				ButtonHelper.click( "cancelCreditDetail.ok" );
				GenericHelper.waitForLoadmask();
				assertTrue( creditSearchObj.creditNotesFilterSelection( billProfile, currency, creditNetAmt, includeNextBill ) );
				assertTrue( creditSearchObj.statusFilter( "PS_Detail_creditNote_status_gridFilterID", "PS_Detail_creditNote_status_txtbxID", "Cancelled", "Status" ) );
			}
			else
				Log4jHelper.logInfo( "Credit Notes is not available for name " + billProfile );
		}
	}

	/*
	 * This method is to edit credit note Line Item
	 */
	public void editCreditNoteLineItem() throws Exception
	{
		NavigationHelper.navigateToScreen( "Credit Notes" );
		for ( paramVal = 0; paramVal < colSize; paramVal++ )
		{
			creditMap = excelHolderObj.dataMap( paramVal );

			initializeVariables( creditMap );

			String[] expectedValueArr = expectedCurrSalesTaxValue.split( regex, -1 );
			String searchGridCurrency = expectedValueArr[0];
			boolean isCreditNotesPresent = creditSearchObj.creditNotesFilterSelection( billProfile, searchGridCurrency, creditNetAmt, includeNextBill );
			if ( isCreditNotesPresent )
			{
				int row = GridHelper.getRowCount( "SearchGrid" );
				creditActionObj.clickEditAction( row );
				String[] creditNetAmtArr = creditNetAmt.split( regex, -1 );
				String[] salesTaxAmtArr = salesTaxAmt.split( regex, -1 );
				String[] overrideSalesTaxArr = overrideSalesTax.split( regex, -1 );
				String[] creditDescArr = creditDesc.split( regex, -1 );
				String[] salesTaxGrpArr = salesTaxGrp.split( regex, -1 );

				for ( int i = 0; i < creditDescArr.length; i++ )
				{
					creditDetailObj.editCreditLineItemDetail( i + 1, creditNetAmtArr[i], salesTaxAmtArr[i], overrideSalesTaxArr[i], billLinked, applySalesTax, salesTaxGrpArr[i] );
					creditDetailObj.creditLineItemValidate( i + 1 );
				}
				creditDetailObj.saveCreditNotes();
				Log4jHelper.logInfo( "Credit Notes is edited successfully for " + billProfile );
			}
			else
				Log4jHelper.logInfo( "Credit Notes is not available successfully for bill profile:" + billProfile );
		}
	}

	/*
	 * This method is to validate search screen columns
	 */
	public void searchScreenColumnsValidation() throws Exception
	{		
		NavigationHelper.navigateToScreen( "Credit Notes" );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		for ( paramVal = 0; paramVal < colSize; paramVal++ )
		{
			creditMap = excelHolderObj.dataMap( paramVal );
			String searchScreenColumns = ExcelHolder.getKey( creditMap, "SearchScreenColumns" );
			ArrayList<String> excelColumnNames = new ArrayList<String>();
			PSStringUtils strObj = new PSStringUtils();
			String[] searchGridColumnsArr = strObj.stringSplitFirstLevel( searchScreenColumns );
			for ( int col = 0; col < searchGridColumnsArr.length; col++ )
			{
				excelColumnNames.add( searchGridColumnsArr[col] );
			}
			genericHelperObj.totalColumns( excelColumnNames );
		}

	}
	/*
	 * This method is to initialize instance variables
	 */
	protected void initializeVariables( Map<String, String> map ) throws Exception
	{

		clientPartition = ExcelHolder.getKey( map, "Partition" );
		billProfile = ExcelHolder.getKey( map, "Bill Profile" );
		creditDate = ExcelHolder.getKey( map, "Credit Date" );
		currency = ExcelHolder.getKey( map, "Currency" );
		creditReason = ExcelHolder.getKey( map, "Credit Reason" );
		remarks = ExcelHolder.getKey( map, "Remarks" );
		reference = ExcelHolder.getKey( map, "Reference" );
		creditDue = ExcelHolder.getKey( map, "Credit Due" );
		includeNextBill = ExcelHolder.getKey( map, "Include NextBill" );
		applySalesTax = ExcelHolder.getKey( map, "Apply SalesTax" );
		creditDesc = ExcelHolder.getKey( map, "Credit Desc" );
		creditNetAmt = ExcelHolder.getKey( map, "CreditNetAmt" );
		salesTaxGrp = ExcelHolder.getKey( map, "Sales TaxGrp" );
		salesTaxAmt = ExcelHolder.getKey( map, "Sales TaxAmt" );
		totalAmt = ExcelHolder.getKey( map, "TotalAmt" );
		overrideSalesTax = ExcelHolder.getKey( map, "Override SalesTax" );
		billbreakdownConfig = ExcelHolder.getKey( map, "Bill BreakdownConfig" );
		billCreditLineItemValue = ExcelHolder.getKey( map, "BillCreditLineItemValue" );
		billCreditLineItemColHeader = ExcelHolder.getKey( map, "BillCreditLineItemColHeader" );
		description = ExcelHolder.getKey( map, "Description" );
		billPeriod = ExcelHolder.getKey( map, "Bill Period" );
		expectedCurrSalesTaxValue = ExcelHolder.getKey( map, "ExpectedCurrencySalesTax" );
		columnHeaders = ExcelHolder.getKey( map, "ColumnHeaders" );
		results = ExcelHolder.getKey( map, "CreditResults" );
		billLinked = ExcelHolder.getKey( map, "Bill Linked" );
		creditHistoryResults = ExcelHolder.getKey( map, "Credit HistoryResults" );
		creditHistoryColHeader = ExcelHolder.getKey( map, "Credit HistoryColHeader" );
		cancelReason = ExcelHolder.getKey( creditMap, "Cancel Reason" );
	}

}
