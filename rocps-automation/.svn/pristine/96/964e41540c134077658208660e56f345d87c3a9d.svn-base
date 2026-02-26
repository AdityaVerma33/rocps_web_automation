package com.subex.rocps.automation.helpers.application.partnerConfiguration.billprofile;

import java.util.Map;

import com.subex.automation.helpers.component.CheckBoxHelper;
import com.subex.automation.helpers.component.ElementHelper;
import com.subex.automation.helpers.component.GenericHelper;
import com.subex.automation.helpers.component.GridHelper;
import com.subex.automation.helpers.component.TabHelper;
import com.subex.automation.helpers.component.TextBoxHelper;
import com.subex.automation.helpers.componentHelpers.CheckBoxElementHelper;
import com.subex.automation.helpers.data.ValidationHelper;
import com.subex.rocps.automation.helpers.application.genericHelpers.PSDataComponentHelper;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;
import com.subex.rocps.automation.utils.PSStringUtils;

public class BillProfileEmailTab extends PSAcceptanceTest
{
	protected Map<String, String> bipEmailMap = null;
	protected String isAutoEmailBill;
	protected String isAutoEmailXDRExtract;
	protected String isAutoEmailCreditNote;
	protected String isAutoEmailSettlement;
	protected String isAutoEmailDispute;
	protected String isSelectEmailInclude;
	protected String isSelectEmailIncludeArr[];
	protected String selectEmailContact;
	protected String selectEmailContactArr[];
	protected String selectEmailAddress;
	protected String selectEmailAddressArr[];
	protected String selectEmailBill;
	protected String selectEmailBillArr[];
	protected String selectEmailXDRExtract;
	protected String selectEmailXDRExtractArr[];
	protected String selectEmailCreditNote;
	protected String selectEmailCreditNoteArr[];
	protected String selectEmailSettlement;
	protected String selectEmailSettlementArr[];
	protected String selectEmailDispute;
	protected String selectEmailDisputeArr[];
	protected String billEmailSubject;
	protected String creditNoteEmailSubject;
	PSStringUtils psStringUtils = new PSStringUtils();
	PSDataComponentHelper psDataComponentHelper = new PSDataComponentHelper();

	/**
	 * Constructor
	 * 
	 * @param bipEmailMap
	 */
	public BillProfileEmailTab( Map<String, String> bipEmailMap ) throws Exception
	{

		this.bipEmailMap = bipEmailMap;
		initializeVariable();
	}

	/*
	 * Method: initializing instance variables
	 */
	protected void initializeVariable() throws Exception
	{
		isAutoEmailBill = bipEmailMap.get( "AutoEmail_Bill_flg" );
		isAutoEmailXDRExtract = bipEmailMap.get( "AutoEmail_XDRExtract_flg" );
		isAutoEmailCreditNote = bipEmailMap.get( "AutoEmail_CreditNote_flg" );
		isAutoEmailSettlement = bipEmailMap.get( "AutoEmail_Settlement_flg" );
		isAutoEmailDispute = bipEmailMap.get( "AutoEmail_Dispute_flg" );
		isSelectEmailInclude = bipEmailMap.get( "SelectEmail_IncludeFlg" );
		selectEmailContact = bipEmailMap.get( "SelectEmail_ContactName" );
		selectEmailAddress = bipEmailMap.get( "SelectEmail_EmailAddress" );

		if ( ValidationHelper.isNotEmpty( selectEmailContact ) )
			selectEmailContactArr = psStringUtils.stringSplitFirstLevel( selectEmailContact );
		if ( ValidationHelper.isNotEmpty( selectEmailAddress ) )
			selectEmailAddressArr = psStringUtils.stringSplitFirstLevel( selectEmailAddress );

		selectEmailBill = bipEmailMap.get( "SelectEmail_Bill" );
		selectEmailXDRExtract = bipEmailMap.get( "SelectEmail_XDRExtract" );
		selectEmailCreditNote = bipEmailMap.get( "SelectEmail_CreditNote" );
		selectEmailSettlement = bipEmailMap.get( "SelectEmail_Settlement" );
		selectEmailDispute = bipEmailMap.get( "SelectEmail_Dispute" );

		if ( ValidationHelper.isNotEmpty( isSelectEmailInclude ) )
			isSelectEmailIncludeArr = psStringUtils.stringSplitFirstLevel( isSelectEmailInclude );
		if ( ValidationHelper.isNotEmpty( selectEmailBill ) )
			selectEmailBillArr = psStringUtils.stringSplitFirstLevel( selectEmailBill );
		if ( ValidationHelper.isNotEmpty( selectEmailXDRExtract ) )
			selectEmailXDRExtractArr = psStringUtils.stringSplitFirstLevel( selectEmailXDRExtract );
		if ( ValidationHelper.isNotEmpty( selectEmailCreditNote ) )
			selectEmailCreditNoteArr = psStringUtils.stringSplitFirstLevel( selectEmailCreditNote );
		if ( ValidationHelper.isNotEmpty( selectEmailSettlement ) )
			selectEmailSettlementArr = psStringUtils.stringSplitFirstLevel( selectEmailSettlement );
		if ( ValidationHelper.isNotEmpty( selectEmailDispute ) )
			selectEmailDisputeArr = psStringUtils.stringSplitFirstLevel( selectEmailDispute );

		billEmailSubject = bipEmailMap.get( "BillEmail_Subject" );
		creditNoteEmailSubject = bipEmailMap.get( "CreditNote_Subject" );
	}

	/*
	 * Method: click on email tab
	 */
	public void billProfileEmailTabConfig() throws Exception
	{
		TabHelper.gotoTab( GenericHelper.getORProperty( "detail_bip_emailTabXpath" ) );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		assertTrue( TabHelper.isSelected( "//ancestor::div[contains(@class,'gwt-TabLayoutPanelTab-selected')]" ), "Email tab is not selected in Bill Profile" );

		autoEmailConfig();
		selectEmailGridConfig();
		billSubject();
		creditNoteSubject();
	}

	/*
	 * Method: click on auto email flag
	 */
	private void autoEmailConfig() throws Exception
	{
		if ( ValidationHelper.isTrue( isAutoEmailBill ) )
			CheckBoxHelper.check( "detail_bip_email_autoEmailBill_ChckbxId" );

		if ( ValidationHelper.isTrue( isAutoEmailXDRExtract ) )
			CheckBoxHelper.check( "detail_bip_email_autoEmailXDRExtract_ChckbxId" );

		if ( ValidationHelper.isTrue( isAutoEmailCreditNote ) )
			CheckBoxHelper.check( "detail_bip_email_autoEmailCreditNote_ChckbxId" );

		if ( ValidationHelper.isTrue( isAutoEmailSettlement ) )
			CheckBoxHelper.check( "detail_bip_email_autoEmailSettlement_ChckbxId" );

		if ( ValidationHelper.isTrue( isAutoEmailDispute ) )
			CheckBoxHelper.check( "detail_bip_email_autoEmailDispute_ChckbxId" );
	}

	/*
	 * Method: configure select email grid
	 */
	private void selectEmailGridConfig() throws Exception
	{
		if ( ValidationHelper.isNotEmpty( selectEmailContact ) )
		{
			String gridId = GenericHelper.getORProperty( "detail_bip_email_selectEmailGridId" );
			for ( int i = 0; i < selectEmailContactArr.length; i++ )
			{
				int row = i + 1;
				String expectedRowValues = selectEmailContactArr[i] + ", " + selectEmailAddressArr[i];
				assertTrue( psDataComponentHelper.isDataPresentInGrid( gridId, expectedRowValues ), "Expected row values is not matched-" + expectedRowValues + " on " + gridId + " for Bill Profile " );
				if ( ValidationHelper.isTrue( isSelectEmailInclude ) )
					GridHelper.updateGridCheckBox( gridId, "detail_bip_email_selectEmailGridInclude_ChckbxId", row, "Include", isSelectEmailIncludeArr[i] );
				if ( ValidationHelper.isNotEmpty( selectEmailBill)  && ValidationHelper.isNotEmpty( selectEmailBillArr[i] ) )
					selectRecipientsEmailCombobox( "detail_bip_email_autoEmailBill_ChckbxId", gridId, "detail_bip_email_selectEmailGrid_Bill_ComboId", row, "Bill", selectEmailBillArr[i] );
				if (ValidationHelper.isNotEmpty( selectEmailXDRExtract)  && ValidationHelper.isNotEmpty( selectEmailXDRExtractArr[i] ) )
					selectRecipientsEmailCombobox( "detail_bip_email_autoEmailXDRExtract_ChckbxId", gridId, "detail_bip_email_selectEmailGrid_XDRExtract_ComboId", row, "XDR Extract", selectEmailXDRExtractArr[i] );
				if (ValidationHelper.isNotEmpty( selectEmailCreditNote)  && ValidationHelper.isNotEmpty( selectEmailCreditNoteArr[i] ) )
					selectRecipientsEmailCombobox( "detail_bip_email_autoEmailCreditNote_ChckbxId", gridId, "detail_bip_email_selectEmailGrid_CreditNote_ComboId", row, "Credit  Note", selectEmailCreditNoteArr[i] );
				if (ValidationHelper.isNotEmpty( selectEmailSettlement)  && ValidationHelper.isNotEmpty( selectEmailSettlementArr[i] ) )
					selectRecipientsEmailCombobox( "detail_bip_email_autoEmailSettlement_ChckbxId", gridId, "detail_bip_email_selectEmailGrid_Settlement_ComboId", row, "Settlement", selectEmailSettlementArr[i] );
				if ( ValidationHelper.isNotEmpty( selectEmailDispute)  && ValidationHelper.isNotEmpty( selectEmailDisputeArr[i] ) )
					selectRecipientsEmailCombobox( "detail_bip_email_autoEmailDispute_ChckbxId", gridId, "detail_bip_email_selectEmailGrid_Dispute_ComboId", row, "Dispute", selectEmailDisputeArr[i] );
			}
		}

	}

	/*
	 * Method: select email recipients
	 */
	private void selectRecipientsEmailCombobox( String autoEmailCheckboxId, String gridId, String comboId, int rowNum, String valueColumnHeader, String value ) throws Exception
	{
		try
		{
			if ( CheckBoxHelper.isChecked( autoEmailCheckboxId ) )
				GridHelper.updateGridComboBox( gridId, comboId, rowNum, valueColumnHeader, value );
		}
		catch ( Exception e )
		{

		}
	}

	/*
	 * Method: configure bill subject
	 */
	private void billSubject() throws Exception
	{
		if ( ValidationHelper.isNotEmpty( billEmailSubject ) )
			TextBoxHelper.type( "detail_bip_email_emailSubject_Bill", billEmailSubject );
	}

	/*
	 * Method: configure credit note subject
	 */
	private void creditNoteSubject() throws Exception
	{
		if ( ValidationHelper.isNotEmpty( creditNoteEmailSubject ) )
			TextBoxHelper.type( "detail_bip_email_emailSubject_CreditNote", creditNoteEmailSubject );
	}

}
