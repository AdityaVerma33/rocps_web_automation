package com.subex.rocps.automation.helpers.application.partnerConfiguration.billprofile;

import org.openqa.selenium.WebElement;

import java.util.Map;

import com.subex.rocps.automation.helpers.application.genericHelpers.PSDataComponentHelper;
import com.subex.rocps.automation.helpers.application.genericHelpers.PSGenericHelper;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;
import com.subex.rocps.automation.utils.PSStringUtils;

import com.subex.automation.helpers.component.ButtonHelper;
import com.subex.automation.helpers.component.CheckBoxHelper;
import com.subex.automation.helpers.component.ComboBoxHelper;
import com.subex.automation.helpers.component.ElementHelper;
import com.subex.automation.helpers.component.GenericHelper;
import com.subex.automation.helpers.component.TabHelper;
import com.subex.automation.helpers.component.TextBoxHelper;
import com.subex.automation.helpers.data.ValidationHelper;

public class BillProfileDetailsTab extends PSAcceptanceTest
{

	protected String profileName;
	protected String language;
	protected String billPackage;
	protected String balanceThreshold;
	protected String balanceCurrency;
	protected String crossFxGroup;
	protected String allocationStrategy;
	protected String externalReference;
	protected String companyVatNo;
	protected String defaultPaymentTerm;
	protected String paymentMethod;
	protected String billingCycle;
	protected String firstPeriodDate;
	protected String consolidatedBillPeriodFl;
	protected String exemptFromSalesTaxFl;
	protected String prePaidFl;
	protected String paymentThreshold;
	protected String validFrom;
	protected String currency;
	protected String billDp;
	protected String fxGroup;
	protected String fxRule;
	protected String creditLimit;
	protected String minimumAmount;
	protected String addressName;
	protected Map<String, String> bipDetailTabMap;
	protected PSDataComponentHelper compObj = new PSDataComponentHelper();

	/*
	 * Constructor initialising map and paramVal
	 */
	public BillProfileDetailsTab( Map<String, String> bipMap )
	{

		this.bipDetailTabMap = bipMap;

		intializeInstVariables();
	}

	/*
	 * Method: initialising instance variables
	 */
	protected void intializeInstVariables()
	{

		profileName = bipDetailTabMap.get( "ProfileName" );
		firstPeriodDate = bipDetailTabMap.get( "FirstPeriodDate" );
		balanceThreshold = bipDetailTabMap.get( "BalanceThreshold" );
		balanceCurrency = bipDetailTabMap.get( "BalanceCurrency" );
		crossFxGroup = bipDetailTabMap.get( "CrossFxGroup" );
		allocationStrategy = bipDetailTabMap.get( "AllocationStrategy" );

		language = bipDetailTabMap.get( "Language" );
		externalReference = bipDetailTabMap.get( "ExternalReference" );
		companyVatNo = bipDetailTabMap.get( "CompanyVatNo" );
		defaultPaymentTerm = bipDetailTabMap.get( "DefaultPaymentTerm" );
		paymentMethod = bipDetailTabMap.get( "PaymentMethod" );
		billingCycle = bipDetailTabMap.get( "BillingCycle" );
		consolidatedBillPeriodFl = bipDetailTabMap.get( "ConsolidatedBillPeriodFl" );
		exemptFromSalesTaxFl = bipDetailTabMap.get( "ExemptFromSalesTaxFl" );
		prePaidFl = bipDetailTabMap.get( "PrePaidFl" );
		paymentThreshold = bipDetailTabMap.get( "PaymentThreshold" );

		validFrom = bipDetailTabMap.get( "ValidFrom" );
		currency = bipDetailTabMap.get( "Currency" );
		billDp = bipDetailTabMap.get( "BillDp" );
		fxGroup = bipDetailTabMap.get( "FxGroup" );
		fxRule = bipDetailTabMap.get( "FxRule" );
		creditLimit = bipDetailTabMap.get( "CreditLimit" );
		minimumAmount = bipDetailTabMap.get( "MinimumAmount" );
		addressName = bipDetailTabMap.get( "AddressName" );

	}

	/*
	 * Method for configuring bill profile detailTab
	 */
	public void detailTabConfig() throws Exception
	{

		TabHelper.gotoTab( "detail_bip_detail_tabXpath" );

		billingDetailsconfig();

		if ( !validFrom.isEmpty() )
			billProfileCurrencyPeriodConfig();

		deliveryAddressConfig();

	}

	/*
	 * Method for edit bill profile detailTab
	 */
	public void editdetailTabConfig() throws Exception
	{

		TabHelper.gotoTab( "detail_bip_detail_tabXpath" );

		editbillingDetailsconfig();

		if ( !validFrom.isEmpty() )
			billProfileCurrencyPeriodConfig();

		editdeliveryAddressConfig();

	}

	/*
	 * Method : Configuring billing details panel
	 */
	protected void billingDetailsconfig() throws Exception
	{

		ComboBoxHelper.select( "detail_bip_detail_language_comboId", language );

		TextBoxHelper.type( "detail_bip_detail_extRef_txtId", externalReference );

		TextBoxHelper.type( "detail_bip_firstPeriodDate_txtXpath", firstPeriodDate );

		//TextBoxHelper.type("detail_bip_detail_profileName_txtId", profileName);

		TextBoxHelper.type( "detail_bip_detail_compVatNo_txtId", companyVatNo );

		ComboBoxHelper.select( "detail_bip_detail_paymentTerm_comboId", defaultPaymentTerm );

		ComboBoxHelper.select( "detail_bip_detail_paymentThreshold_comboId", paymentMethod );

		ComboBoxHelper.select( "detail_bip_billingCycle_comboId", billingCycle );

		// Calendar

		if ( Boolean.valueOf( consolidatedBillPeriodFl ) )
			CheckBoxHelper.check( "detail_bip_consFirstBillPeriod_checkId" );

		if ( Boolean.valueOf( exemptFromSalesTaxFl ) )
			CheckBoxHelper.check( "detail_bip_exemptFromSalesTax_checkId" );

		if ( Boolean.valueOf( prePaidFl ) )
			CheckBoxHelper.check( "detail_bip_prePaid_checkId" );

		TextBoxHelper.type( "detail_bip_detail_profileName_txtId", profileName );

		if ( Boolean.valueOf( prePaidFl ) && ComboBoxHelper.isEnabled( "detail_bip_prePaymentThreshold_comboId" ) )
			ComboBoxHelper.select( "detail_bip_prePaymentThreshold_comboId", paymentThreshold );

	}

	/*
	 * Method : edit billing details panel
	 */
	protected void editbillingDetailsconfig() throws Exception
	{

		if ( ValidationHelper.isNotEmpty( language ) )
			ComboBoxHelper.select( "detail_bip_detail_language_comboId", language );
		if ( ValidationHelper.isNotEmpty( externalReference ) )
			TextBoxHelper.type( "detail_bip_detail_extRef_txtId", externalReference );

		assertEquals( TextBoxHelper.getValue( "detail_bip_detail_profileName_txtId" ), profileName, "Profile name is not matched" );
		assertEquals( TextBoxHelper.getValue( "detail_bip_firstPeriodDate_txtXpath" ), firstPeriodDate, "First Period Date is not matched" );

		if ( ValidationHelper.isNotEmpty( companyVatNo ) )
			TextBoxHelper.type( "detail_bip_detail_compVatNo_txtId", companyVatNo );

		if ( ValidationHelper.isNotEmpty( defaultPaymentTerm ) )
			ComboBoxHelper.select( "detail_bip_detail_paymentTerm_comboId", defaultPaymentTerm );
		if ( ValidationHelper.isNotEmpty( paymentMethod ) )
			ComboBoxHelper.select( "detail_bip_detail_paymentThreshold_comboId", paymentMethod );
		if ( ValidationHelper.isNotEmpty( billingCycle ) )
			ComboBoxHelper.select( "detail_bip_billingCycle_comboId", billingCycle );

		// Calendar

	}

	/*
	 * Method for configuring single or multiple timelines within bill profile
	 */
	protected void billProfileCurrencyPeriodConfig() throws Exception
	{

		String splitRegex = new PSStringUtils().regexFirstLevelDelimeter();
		String[] validFromArr = validFrom.split( splitRegex, -1 );
		String[] currencyArr = currency.split( splitRegex, -1 );
		String[] billDpArr = billDp.split( splitRegex, -1 );
		String[] fxGrpArr = fxGroup.split( splitRegex, -1 );
		String[] fxRuleArr = fxRule.split( splitRegex, -1 );
		String[] creditLimitArr = creditLimit.split( splitRegex, -1 );
		String[] minAmtArr = minimumAmount.split( splitRegex, -1 );
		PSGenericHelper genHelperObj = new PSGenericHelper();

		for ( int i = 0; i < validFromArr.length; i++ )
		{
			WebElement element = ElementHelper.getElement( "detail_bip_timeLineDisplayed_xpath" );

			boolean timeLineFlag = false;

			if ( validFromArr[i].equals( firstPeriodDate ) )
				genHelperObj.timeLineEdit();
			else
			{
				genHelperObj.timeLineNew();
				timeLineFlag = true;
			}

			billProfileCurrencyDetails( validFromArr[i], currencyArr[i], billDpArr[i], fxGrpArr[i], fxRuleArr[i], creditLimitArr[i], minAmtArr[i], timeLineFlag );

		}

	}

	/*
	 * Method for configuring billProfileCurrencyDetails
	 */
	protected void billProfileCurrencyDetails( String validFromArrVal, String currencyArrVal, String billDpArrVal, String fxGrpArrVal, String fxRuleArrVal, String creditLimitArrVal, String minAmtArrVal, boolean timeLineFlag ) throws Exception
	{

		GenericHelper.waitForLoadmask();
		if ( Boolean.valueOf( timeLineFlag ) )
			TextBoxHelper.type( "detail_bip_validFromDate_txtXpath", validFromArrVal );

		if ( !currencyArrVal.isEmpty() )
			ComboBoxHelper.select( "detail_bip_billCurrDetail_popUpId", "detail_bip_currecnyTimeLine_comboId", currencyArrVal );

		if ( !billDpArrVal.isEmpty() )
			ComboBoxHelper.select( "detail_bip_billCurrDetail_popUpId", "detail_bip_billCurrBillDp_comboId", billDpArrVal );

		if ( !fxGrpArrVal.isEmpty() )
			ComboBoxHelper.select( "detail_bip_billCurrDetail_popUpId", "detail_bip_billFxGroup_comboId", fxGrpArrVal );

		if ( !fxRuleArrVal.isEmpty() )
			ComboBoxHelper.select( "detail_bip_billCurrDetail_popUpId", "detail_bip_billFxRule_comboId", fxRuleArrVal );

		if ( !creditLimitArrVal.isEmpty() )
			TextBoxHelper.type( "detail_bip_creditLimit_txtId", creditLimitArrVal );

		if ( !minAmtArrVal.isEmpty() )
			TextBoxHelper.type( "detail_bip_minAmount_txtId", minAmtArrVal );

		ButtonHelper.clickIfEnabled( "detail_bip_billCurrDetail_popUpId", "detail_bip_timeLineSave_btnId" );
		GenericHelper.waitForLoadmask();
	}

	/*
	 * Method for configuring delivery address
	 */
	protected void deliveryAddressConfig() throws Exception
	{

		ComboBoxHelper.select( "detail_bip_addressName_comboId", addressName );

	}

	/*
	 * Method for configuring delivery address
	 */
	protected void editdeliveryAddressConfig() throws Exception
	{

		if ( ValidationHelper.isNotEmpty( addressName ) )
			ComboBoxHelper.select( "detail_bip_addressName_comboId", addressName );

	}

}
