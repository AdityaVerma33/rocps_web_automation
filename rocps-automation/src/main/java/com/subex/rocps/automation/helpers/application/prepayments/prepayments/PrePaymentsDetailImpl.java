package com.subex.rocps.automation.helpers.application.prepayments.prepayments;

import com.subex.rocps.automation.helpers.application.genericHelpers.PSEntityComboHelper;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;

import com.subex.automation.helpers.component.ButtonHelper;
import com.subex.automation.helpers.component.ComboBoxHelper;
import com.subex.automation.helpers.component.ElementHelper;
import com.subex.automation.helpers.component.EntityComboHelper;
import com.subex.automation.helpers.component.GenericHelper;
import com.subex.automation.helpers.component.GridHelper;
import com.subex.automation.helpers.component.PopupHelper;
import com.subex.automation.helpers.component.TextAreaHelper;
import com.subex.automation.helpers.component.TextBoxHelper;
import com.subex.automation.helpers.data.ValidationHelper;
import com.subex.automation.helpers.report.Log4jHelper;
import com.subex.automation.helpers.util.FailureHelper;

public class PrePaymentsDetailImpl extends PSAcceptanceTest
{

	/*
	 * This method is for payment configuration
	 */
	public void prepaymentConfiguration( String transactionReference, String transactionDate, String billProfile, String currency, String amount, String paymentMethod, String comments ) throws Exception
	{
		TextBoxHelper.type( "PS_Detail_prepayments_transactionRef_txtID", transactionReference );
		TextBoxHelper.type( "PS_Detail_prepayments_transactiondate_txtID", transactionDate );
		PSEntityComboHelper.selectUsingGridFilterTextBox( "Detail_popUpWindowId", "PS_Details_testBill_billPrf_popUp", "Bill Profile Search", "PS_Detail_prepayments_billProfile_txtID", billProfile, "Bill Profile Name" );
		
		TextBoxHelper.type( "PS_Detail_prepayments_amount_txtID", amount ); //effective amt assertion
		ComboBoxHelper.select( "PS_Detail_creditNote_currency_comboId", currency );
		String effectiveAmount = TextBoxHelper.getValue( "pprmEffectiveAmt" );
		if(effectiveAmount.contains(amount))
			Log4jHelper.logInfo("Amount and Effective amounts are matching");
		else
			FailureHelper.failTest("Amount and effective amounts are not matching");
			
		ComboBoxHelper.select( "PS_Detail_prepayments_paymentMethod_txtID", paymentMethod );

		TextAreaHelper.type( "PS_Detail_prepayments_comments_txtID", comments );
	}

	/*
	 * This method is to save prepayments
	 */
	public void savePrePayments(String transactionRef) throws Exception
	{
		ButtonHelper.click( "PS_Detail_prepayments_save_btnID" );
		GenericHelper.waitForLoadmask();
		if ( PopupHelper.isPresent( "PS_popup_billAction_InformationID" ) )
			assertTrue( isPopUpValidation() , "Popup texts are not matching");
		GenericHelper.waitForLoadmask();
		GridHelper.isValuePresent( "SearchGrid", transactionRef, "Transaction Reference" );
	}
	
	/*
	 * This method is for edit payment configuration
	 */
	public void editPrepaymentConfiguration( String transactionReference, String transactionDate, String billProfile, String currency, String amount, String paymentMethod, String comments ) throws Exception
	{
		assertEquals(TextBoxHelper.getValue( "PS_Detail_prepayments_transactionRef_txtID"), transactionReference );
		if(ValidationHelper.isNotEmpty( transactionDate ))
			TextBoxHelper.type( "PS_Detail_prepayments_transactiondate_txtID", transactionDate );
		
		String billProfileActual = EntityComboHelper.getValue( "billProfile" );
		if(!billProfileActual.contains( billProfile ))
			FailureHelper.failTest( "Bill Profile values are not matching" );
		
		if(ValidationHelper.isNotEmpty( currency ))
			ComboBoxHelper.select( "PS_Detail_creditNote_currency_comboId", currency );
		if(ValidationHelper.isNotEmpty( amount ))
		{
			TextBoxHelper.type( "PS_Detail_prepayments_amount_txtID", amount ); //effective amt assertion
		String effectiveAmount = TextBoxHelper.getValue( "pprmEffectiveAmt" );
		if(effectiveAmount.contains(amount))
			Log4jHelper.logInfo("Amount and Effective amounts are matching");
		else
			FailureHelper.failTest("Amount and effective amounts are not matching");
		}
		if(ValidationHelper.isNotEmpty( paymentMethod ))	
			ComboBoxHelper.select( "PS_Detail_prepayments_paymentMethod_txtID", paymentMethod );
		if(ValidationHelper.isNotEmpty( comments ))
			TextAreaHelper.type( "PS_Detail_prepayments_comments_txtID", comments );
	}

	/*
	 * this method is for popupValidation
	 */

	public boolean isPopUpValidation() throws Exception
	{
		String currencyMsg = "Pre-Payment Currency does not match the Bill Profile Currency. Do you wish to continue?";
		String changeStatusMsg = "Are you sure you want to Approve the Pre-Payment?";
		String popMsg = ElementHelper.getText( "//div[@id='window-scroll-panel']" );
		boolean flag = false;
		if ( popMsg.equals( currencyMsg ) )
		{
			flag = true;
			assertEquals( popMsg, currencyMsg, "popup texts are not matching" );
			Log4jHelper.logInfo( "Actual value : " + popMsg );
			Log4jHelper.logInfo( "expected value : " + currencyMsg );
			ButtonHelper.click( "YesButton" );
			GenericHelper.waitForLoadmask();
		}
		else if ( popMsg.equals( changeStatusMsg ) )
		{
			flag = true;
			assertEquals( popMsg, changeStatusMsg, "popup texts are not matching" );
			Log4jHelper.logInfo( "Actual value : " + popMsg );
			Log4jHelper.logInfo( "expected value : " + changeStatusMsg );
			ButtonHelper.click( "YesButton" );
			GenericHelper.waitForLoadmask();
		}
		return flag;

	}

}
