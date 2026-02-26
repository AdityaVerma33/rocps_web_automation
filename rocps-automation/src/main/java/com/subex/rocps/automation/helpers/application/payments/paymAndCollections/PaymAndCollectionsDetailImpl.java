package com.subex.rocps.automation.helpers.application.payments.paymAndCollections;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.xmlgraphics.ps.PSGenerator;

import com.subex.automation.helpers.component.ButtonHelper;
import com.subex.automation.helpers.component.CheckBoxHelper;
import com.subex.automation.helpers.component.ComboBoxHelper;
import com.subex.automation.helpers.component.ElementHelper;
import com.subex.automation.helpers.component.EntityComboHelper;
import com.subex.automation.helpers.component.GenericHelper;
import com.subex.automation.helpers.component.GridHelper;
import com.subex.automation.helpers.component.TextBoxHelper;
import com.subex.automation.helpers.data.DateHelper;
import com.subex.automation.helpers.data.ValidationHelper;
import com.subex.automation.helpers.report.Log4jHelper;
import com.subex.rocps.automation.helpers.application.filters.DataSelectionHelper;
import com.subex.rocps.automation.helpers.application.genericHelpers.PSDataComponentHelper;
import com.subex.rocps.automation.helpers.application.genericHelpers.PSGenericHelper;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;
import com.subex.rocps.automation.utils.ExcelHolder;
import com.subex.rocps.automation.utils.PSStringUtils;

public class PaymAndCollectionsDetailImpl extends PSAcceptanceTest
{
	protected Map<String, String> paymAndCollDetailMap = null;
	protected String transacionReference;
	protected String transactionDate;
	protected String currency;
	protected String amount;
	protected String effectiveAmount;
	protected String autoAuthorizeFlg;
	protected String paymentCollectionBillProfile;
	protected String paymentAgainst;
	protected String billCi_Acount;
	protected String billCi_BillProfile;
	protected String billCi_FromDt;
	protected String billCi_FromDtArr[];
	protected String billCi_ToDt;
	protected String billCi_ToDtArr[];
	protected String billCi_AllocationAmount;
	protected String billCi_AllocationAmountArr[];
	Map<String, String> billGridKeyValueMap = null;
	Map<String, String> ciGridKeyValueMap = null;
	DataSelectionHelper dataSelectionHelper = new DataSelectionHelper();
	PSStringUtils psStringUtils = new PSStringUtils();
	PSGenericHelper psGenericHelper = new PSGenericHelper();
	PSDataComponentHelper psDataComponentHelper = new PSDataComponentHelper();

	/**Constructor
	 * @param paymAndCollDetailMap
	 * @throws Exception 
	 */
	public PaymAndCollectionsDetailImpl( Map<String, String> paymAndCollDetailMap ) throws Exception
	{
		this.paymAndCollDetailMap = paymAndCollDetailMap;
		initializeVariable( paymAndCollDetailMap );
		initializeArrays();
	}

	/*
	 * This method is for initialize variable
	 */
	private void initializeVariable( Map<String, String> map ) throws Exception
	{
		transacionReference = ExcelHolder.getKey( map, "TransacionReference" );
		transactionDate = ExcelHolder.getKey( map, "TransacionDate" );
		currency = ExcelHolder.getKey( map, "Currency" );
		amount = ExcelHolder.getKey( map, "Amount" );
		autoAuthorizeFlg = ExcelHolder.getKey( map, "AutoAutorize" );
		paymentCollectionBillProfile = ExcelHolder.getKey( map, "PaymentCollectionBillProfile" );
		paymentAgainst = ExcelHolder.getKey( map, "PaymentAgainst" );
		billCi_Acount = ExcelHolder.getKey( map, "BillCI_Accont" );
		billCi_BillProfile = ExcelHolder.getKey( map, "BillCI_BillProfile" );
		billCi_FromDt = ExcelHolder.getKey( map, "BillCI_FromDate" );
		billCi_ToDt = ExcelHolder.getKey( map, "BillCI_ToDate" );
		billCi_AllocationAmount = ExcelHolder.getKey( map, "BillCI_AllocationAmount" );
	}

	/*
	 * This method is for initialize Arrays
	 */
	private void initializeArrays() throws Exception
	{
		billCi_FromDtArr = psStringUtils.stringSplitFirstLevel( billCi_FromDt );
		billCi_ToDtArr = psStringUtils.stringSplitFirstLevel( billCi_ToDt );
		billCi_AllocationAmountArr = psStringUtils.stringSplitFirstLevel( billCi_AllocationAmount );

	}

	public void createPaymentCollections() throws Exception
	{
		cofigureBasicPanel();
		configDetailsPanel();
		configurePaymentGrid();
		psGenericHelper.detailSave( "PS_Detail_paymAndCollect_save_BtnId", transacionReference, "Transaction Reference" );

	}

	public void modifyPaymentCollections() throws Exception
	{
		modifyBasicPanel();
		configurePaymentGrid();
		psGenericHelper.detailSave( "PS_Detail_paymAndCollect_save_BtnId", transacionReference, "Transaction Reference" );
	}

	protected void cofigureBasicPanel() throws Exception
	{

		if ( ValidationHelper.isEmpty( transactionDate ) )
			transactionDate = DateHelper.getCurrentDate( "MM/dd/yyyy" ) + " 00:00:00";
		TextBoxHelper.type( "PS_Detail_paymAndCollect_transactRef_txtId", transacionReference );
		TextBoxHelper.type( "PS_Detail_paymAndCollect_transactDt_txtId", transactionDate );
		ComboBoxHelper.select( "PS_Detail_paymAndCollect_currency_comboId", currency );
		TextBoxHelper.type( "PS_Detail_paymAndCollect_amount_txtId", amount );
		if ( ValidationHelper.isTrue( autoAuthorizeFlg ) )
			CheckBoxHelper.check( "PS_Detail_paymAndCollect_autoAuth_chckBxId" );
	}
	protected void modifyBasicPanel() throws Exception
	{
		if ( ValidationHelper.isEmpty( transactionDate ) )
			transactionDate = DateHelper.getCurrentDate( "MM/dd/yyyy" ) + " 00:00:00";
		assertEquals( TextBoxHelper.getValue( "PS_Detail_paymAndCollect_transactRef_txtId" ), transacionReference ,"Transaction Reference is not matched");
		psDataComponentHelper.modifyTextBox( "PS_Detail_paymAndCollect_transactDt_txtId", transactionDate );
		assertEquals( ComboBoxHelper.getValue( "PS_Detail_paymAndCollect_currency_comboId" ), currency ,"Currency  is not matched");
		assertEquals( TextBoxHelper.getValue( "PS_Detail_paymAndCollect_amount_txtId" ), amount+".0000" ," Amount is not matched");
		if ( ValidationHelper.isTrue( autoAuthorizeFlg ) )
			CheckBoxHelper.check( "PS_Detail_paymAndCollect_autoAuth_chckBxId" );
	}

	protected void configDetailsPanel() throws Exception
	{
		ButtonHelper.click( "paymentDetails" );

		psGenericHelper.waitforEntityElement();
		EntityComboHelper.clickEntityIcon( "PS_Detail_paymAndCollect_billProfile_triggerId" );
		dataSelectionHelper.billProfileSelection( paymentCollectionBillProfile );
		ComboBoxHelper.select( "PS_Detail_paymAndCollect_paymeAgainst_comboId", paymentAgainst );

	}

	protected void configurePaymentGrid() throws Exception
	{
		if ( paymentAgainst.contentEquals( "Bills" ) )
			billSearch();
		else
			carrierInvoiceSearch();
	}

	// Method bill entity search
	public void billSearch() throws Exception
	{
		String gridId = GenericHelper.getORProperty( "PS_Detail_paymAndCollect_payment_gridId" );
		for ( int i = 0; i < billCi_FromDtArr.length; i++ )
		{
			String fromDt = billCi_FromDtArr[i].replace( " 00:00:00", "" );
			String toDt = billCi_ToDtArr[i].replace( " 23:59:59", "" );
			String expectedPeriod = fromDt + " - " + toDt;

			boolean isDataPresentInGrid = psDataComponentHelper.isDataPresentInGrid( gridId, expectedPeriod );
			if ( !isDataPresentInGrid )
			{
				GenericHelper.waitForLoadmask( searchScreenWaitSec );
				ButtonHelper.click( "PS_Detail_paymAndCollect_paymentGrid_addBtnId" );
				List<String> listColumn = getKeysOfBillGrid();
				billGridKeyValueMap = dataSelectionHelper.billsearch( billCi_Acount, billCi_BillProfile, billCi_FromDtArr[i], billCi_ToDtArr[i], "SearchGrid", "grid_column_header_searchGrid_", listColumn );
				GenericHelper.waitForLoadmask( searchScreenWaitSec );
				GridHelper.clickRow( gridId, i + 1, "Reference  No" );
				GridHelper.updateGridTextBox( gridId, "PS_Detail_paymAndCollect_paymentGri_allocationAmt_txtId", i + 1, "Allocation  Amount", billCi_AllocationAmountArr[i] );
			}
			else
			{
				Log4jHelper.logInfo( "The given period - " + expectedPeriod + " is already present in " + gridId );
				GenericHelper.waitForLoadmask( searchScreenWaitSec );
				GridHelper.clickRow( gridId, i + 1, "Reference  No" );
				GridHelper.updateGridTextBox( gridId, "PS_Detail_paymAndCollect_paymentGri_allocationAmt_txtId", i + 1, "Allocation  Amount", billCi_AllocationAmountArr[i] );
			}
		}
	}

	public void carrierInvoiceSearch() throws Exception
	{
		String gridId = GenericHelper.getORProperty( "PS_Detail_paymAndCollect_payment_gridId" );
		for ( int i = 0; i < billCi_FromDtArr.length; i++ )
		{
			String fromDt = billCi_FromDtArr[i].replace( " 00:00:00", "" );
			String toDt = billCi_ToDtArr[i].replace( " 23:59:59", "" );
			String expectedPeriod = fromDt + " - " + toDt;

			boolean isDataPresentInGrid = psDataComponentHelper.isDataPresentInGrid( gridId, expectedPeriod );
			if ( !isDataPresentInGrid )
			{
				GenericHelper.waitForLoadmask( searchScreenWaitSec );
				ButtonHelper.click( "PS_Detail_paymAndCollect_paymentGrid_addBtnId" );
				List<String> listColumn = getKeysOfCIGrid();
				billGridKeyValueMap = dataSelectionHelper.carrierInvoiceSearch( billCi_Acount, billCi_BillProfile, billCi_FromDtArr[i], billCi_ToDtArr[i], "SearchGrid", "grid_column_header_searchGrid_", listColumn );
				GenericHelper.waitForLoadmask( searchScreenWaitSec );
				GridHelper.clickRow( gridId, i + 1, "Reference  No" );
				GridHelper.updateGridTextBox( gridId, "PS_Detail_paymAndCollect_paymentGri_allocationAmt_txtId", i + 1, "Allocation  Amount", billCi_AllocationAmountArr[i] );

			}
			else
			{
				Log4jHelper.logInfo( "The given period - " + expectedPeriod + " is already present in " + gridId );
				GenericHelper.waitForLoadmask( searchScreenWaitSec );
				GridHelper.clickRow( gridId, i + 1, "Reference  No" );
				GridHelper.updateGridTextBox( gridId, "PS_Detail_paymAndCollect_paymentGri_allocationAmt_txtId", i + 1, "Allocation  Amount", billCi_AllocationAmountArr[i] );
			}
		}
	}

	// Bill grid columns keys
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

	// CI grid columns keys
	private List<String> getKeysOfCIGrid()
	{
		List<String> listColumn = new ArrayList<String>();
		listColumn.add( "Bill Profile" );
		listColumn.add( "From" );
		listColumn.add( "To" );
		listColumn.add( "Total Amount" );
		listColumn.add( "Currency" );
		return listColumn;

	}

}
