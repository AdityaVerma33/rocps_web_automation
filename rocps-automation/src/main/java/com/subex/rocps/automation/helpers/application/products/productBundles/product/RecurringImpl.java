package com.subex.rocps.automation.helpers.application.products.productBundles.product;

import java.util.Map;

import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;
import com.subex.rocps.automation.utils.ExcelHolder;
import com.subex.rocps.automation.utils.PSStringUtils;

import com.subex.automation.helpers.component.CheckBoxHelper;
import com.subex.automation.helpers.component.ComboBoxHelper;
import com.subex.automation.helpers.component.ElementHelper;
import com.subex.automation.helpers.component.GenericHelper;
import com.subex.automation.helpers.component.RadioHelper;
import com.subex.automation.helpers.component.TextBoxHelper;
import com.subex.automation.helpers.data.ValidationHelper;

public class RecurringImpl extends PSAcceptanceTest implements ProductItem
{

	protected Map<String, String> recurringItemMap = null;
	protected String name;
	protected String type;
	protected String applied;
	protected String statusGroup;
	protected String farInAdvance;
	protected String extraArg;
	protected String externalRefer;
	protected String prorateChargeTermFlg;
	protected String allowOverrideAmntFlg;
	protected String optionalFlg;
	protected String mandatoryFlg;
	protected String specifyFlg;
	protected String productOptionsMinNo;
	protected String productOptionsMaxNo;
	protected String alignWithFrequency;
	protected String frequency;
	protected String aniversaryAlignBasedOn;
	protected String alignUsingTheFollowing;
	protected String dayOfMonthWeek;
	protected String alignmentDate;
	protected String amount_fromDt;
	protected String amount_fromDtArr[];
	protected String amount_currency;
	protected String amount_currencyArr[];
	protected String amount_amt;
	protected String amount_amtArr[];
	protected String amount_MinAmnt;
	protected String amount_MinAmntArr[];
	protected String amount_MaxAmnt;
	protected String amount_MaxAmntArr[];
	protected String billingGrpCode;
	protected String billingGrpCodeArr[];
	protected String billingGrpDefault;
	protected String billingGrpDefaultArr[];
	protected String salesTax_Country;
	protected String salesTax_CountryArr[];
	protected String salesTax_Grp;
	protected String salesTax_GrpArr[];
	protected String salesTax_Default;
	protected String salesTax_DefaultArr[];
	PSStringUtils psStringUtils = new PSStringUtils();

	/**Constructor
	 * @param recurringItemMap
	 */
	public RecurringImpl( Map<String, String> recurringItemMap )
	{
		this.recurringItemMap = recurringItemMap;
	}

	/*Override method to configure Product Item*/
	@Override
	public void configureProductItem() throws Exception
	{
		configureBasicDetails();
		configureOptionsPanel();
		configureFrequencyPanel();
		configureAmountsPanel();
		configureBillingGrpPanel();
		configureSalesTaxPanel();
	}

	/*Override method to validate Product Item*/
	@Override
	public void validateProductItem() throws Exception
	{
		validateBasicDetails();
		validateOptionsPanel();
		validateFrequencyPanel();
		validateAmountsPanel();
		validateBillingGrpPanel();
		validateSalesTaxPanel();

	}

	/*
	 * This method is for initialize variable for Basic Details
	 */
	private void initializeVariableBasicDetails( Map<String, String> map ) throws Exception
	{
		name = ExcelHolder.getKey( map, "Name" );
		type = ExcelHolder.getKey( map, "Type" );
		applied = ExcelHolder.getKey( map, "Applied" );
		statusGroup = ExcelHolder.getKey( map, "StatusGroup" );
		farInAdvance = ExcelHolder.getKey( map, "FarInAdvance" );
		extraArg = ExcelHolder.getKey( map, "ExtraArgument" );
		externalRefer = ExcelHolder.getKey( map, "ExternalRefer" );
		prorateChargeTermFlg = ExcelHolder.getKey( map, "ProrateChargeTerm" );
		allowOverrideAmntFlg = ExcelHolder.getKey( map, "AllowOverrideOfAmt" );
	}

	/*
	 * This method is for initialize variable for Options panel
	 */
	private void initializeVariableOptionsPanel( Map<String, String> map ) throws Exception
	{
		optionalFlg = ExcelHolder.getKey( map, "Optional" );
		mandatoryFlg = ExcelHolder.getKey( map, "Mandatory" );
		specifyFlg = ExcelHolder.getKey( map, "Specify" );
		productOptionsMinNo = ExcelHolder.getKey( map, "OptionsMinNo" );
		productOptionsMaxNo = ExcelHolder.getKey( map, "OptionsMaxNo" );
	}

	/*
	 * This method is for initialize variable for Frequency Details
	 */
	private void initializeVariableFrequencyDetails( Map<String, String> map ) throws Exception
	{
		alignWithFrequency = ExcelHolder.getKey( map, "AlignWithFrequency" );
		frequency = ExcelHolder.getKey( map, "Frequency" );
		aniversaryAlignBasedOn = ExcelHolder.getKey( map, "AniversaryAlignBasedOn" );
		alignUsingTheFollowing = ExcelHolder.getKey( map, "AlignUsingTheFollowing" );
		dayOfMonthWeek = ExcelHolder.getKey( map, "DayOfMonthWeek" );
		alignmentDate = ExcelHolder.getKey( map, "AlignmentDate" );

	}

	/*
	 * This method is for initialize variable for Amounts Panel
	 */
	private void initializeVariableAmountDetails( Map<String, String> map ) throws Exception
	{
		amount_fromDt = ExcelHolder.getKey( map, "Amounts_FromDt" );
		amount_currency = ExcelHolder.getKey( map, "Amounts_Currency" );
		amount_amt = ExcelHolder.getKey( map, "Amounts_Amt" );
		amount_MinAmnt = ExcelHolder.getKey( map, "Amounts_MinAmt" );
		amount_MaxAmnt = ExcelHolder.getKey( map, "Amounts_MaxAmt" );

		if ( ValidationHelper.isNotEmpty( amount_fromDt ) )
			amount_fromDtArr = psStringUtils.stringSplitFirstLevel( amount_fromDt );
		if ( ValidationHelper.isNotEmpty( amount_currency ) )
			amount_currencyArr = psStringUtils.stringSplitFirstLevel( amount_currency );
		if ( ValidationHelper.isNotEmpty( amount_amt ) )
			amount_amtArr = psStringUtils.stringSplitFirstLevel( amount_amt );
		if ( ValidationHelper.isNotEmpty( amount_MinAmnt ) )
			amount_MinAmntArr = psStringUtils.stringSplitFirstLevel( amount_MinAmnt );
		if ( ValidationHelper.isNotEmpty( amount_MaxAmnt ) )
			amount_MaxAmntArr = psStringUtils.stringSplitFirstLevel( amount_MaxAmnt );

	}

	/*
	 * This method is for initialize variable for SalesTax Panel
	 */
	private void initializeVariableSalesTaxDetails( Map<String, String> map ) throws Exception
	{
		salesTax_Country = ExcelHolder.getKey( map, "SalesTaxes_Country" );
		salesTax_Grp = ExcelHolder.getKey( map, "SalesTaxes_SalesTaxGrp" );
		salesTax_Default = ExcelHolder.getKey( map, "SalesTaxes_Default" );
		if ( ValidationHelper.isNotEmpty( salesTax_Country ) )
			salesTax_CountryArr = psStringUtils.stringSplitFirstLevel( salesTax_Country );
		if ( ValidationHelper.isNotEmpty( salesTax_Grp ) )
			salesTax_GrpArr = psStringUtils.stringSplitFirstLevel( salesTax_Grp );
		if ( ValidationHelper.isNotEmpty( salesTax_Default ) )
			salesTax_DefaultArr = psStringUtils.stringSplitFirstLevel( salesTax_Default );
	}

	/*
	 * This method is for initialize variable for Billing Groups Panel
	 */
	private void initializeVariableBillingGrpsDetails( Map<String, String> map ) throws Exception
	{

		billingGrpCode = ExcelHolder.getKey( map, "BillingGroupsCode" );
		billingGrpDefault = ExcelHolder.getKey( map, "BillingGroups_Default" );
		if ( ValidationHelper.isNotEmpty( billingGrpCode ) )
			billingGrpCodeArr = psStringUtils.stringSplitFirstLevel( billingGrpCode );
		if ( ValidationHelper.isNotEmpty( billingGrpDefault ) )
			billingGrpDefaultArr = psStringUtils.stringSplitFirstLevel( billingGrpDefault );
	}

	/* method to configure basic details of Product Item*/
	private void configureBasicDetails() throws Exception
	{
		initializeVariableBasicDetails( recurringItemMap );
		ElementHelper.waitForElement( GenericHelper.getORProperty( "PS_Detail_productItem_Recurring_FrequencyLabel_xpath" ), searchScreenWaitSec );

		TextBoxHelper.type( "PS_Detail_productItem_Recurring_name_txtId", name );
		ComboBoxHelper.select( "PS_Detail_productItem_Recurring_type_comboId", type );
		ComboBoxHelper.select( "PS_Detail_productItem_Recurring_applied_comboId", applied );
		if ( ValidationHelper.isNotEmpty( statusGroup ) )
			ComboBoxHelper.select( "PS_Detail_productItem_Recurring_statusGrp_comboId", statusGroup );
		if ( applied.contentEquals( "In Advance" ) )
			TextBoxHelper.type( "PS_Detail_productItem_Recurring_farInAdvance_txtId", farInAdvance );
		if ( ValidationHelper.isNotEmpty( extraArg ) )
			ComboBoxHelper.select( "PS_Detail_productItem_Recurring_extraArg_comboId", extraArg );
		if ( ValidationHelper.isNotEmpty( externalRefer ) )
			TextBoxHelper.type( "PS_Detail_productItem_Recurring_externalRefer_txtId", externalRefer );
		if ( ValidationHelper.isTrue( prorateChargeTermFlg ) )
			CheckBoxHelper.check( "PS_Detail_productItem_Recurring_prorateChgTerm_chckBxId" );
		ProductUtil.configAllowAmountCheckBox( allowOverrideAmntFlg );
	}

	/* method to validate basic details of Product Item*/
	private void validateBasicDetails() throws Exception
	{
		initializeVariableBasicDetails( recurringItemMap );
		ElementHelper.waitForElement( GenericHelper.getORProperty( "PS_Detail_productItem_Recurring_FrequencyLabel_xpath" ), searchScreenWaitSec );
		assertEquals( TextBoxHelper.getValue( "PS_Detail_productItem_Recurring_name_txtId" ), name, "Recurring product item Name is not matched" );
		assertEquals( ComboBoxHelper.getValue( "PS_Detail_productItem_Recurring_type_comboId" ), type, "Recurring product item Type is not matched" );
		assertEquals( ComboBoxHelper.getValue( "PS_Detail_productItem_Recurring_applied_comboId" ), applied, "Recurring product item Applied is not matched" );
		if ( ValidationHelper.isNotEmpty( statusGroup ) )
			assertEquals( ComboBoxHelper.getValue( "PS_Detail_productItem_Recurring_statusGrp_comboId" ), statusGroup, "Recurring product item Status Group is not matched" );
		if ( applied.contentEquals( "In Advance" ) )
			assertEquals( TextBoxHelper.getValue( "PS_Detail_productItem_Recurring_farInAdvance_txtId" ), farInAdvance, "Recurring product item Far In Advance is not matched" );
		if ( ValidationHelper.isNotEmpty( extraArg ) )
			assertEquals( ComboBoxHelper.getValue( "PS_Detail_productItem_Recurring_extraArg_comboId" ), extraArg, "Recurring product item Extra Argument is not matched" );
		if ( ValidationHelper.isNotEmpty( externalRefer ) )
			assertEquals( TextBoxHelper.getValue( "PS_Detail_productItem_Recurring_externalRefer_txtId" ), externalRefer, "Recurring product item External Reference is not matched" );
		if ( ValidationHelper.isTrue( prorateChargeTermFlg ) )
			CheckBoxHelper.isChecked( "PS_Detail_productItem_Recurring_prorateChgTerm_chckBxId" );
		else
			CheckBoxHelper.isNotChecked( "PS_Detail_productItem_Recurring_prorateChgTerm_chckBxId" );
		ProductUtil.configAllowAmountCheckBox( allowOverrideAmntFlg );
	}

	/* method to configure options panel of Product Item*/
	private void configureOptionsPanel() throws Exception
	{
		initializeVariableOptionsPanel( recurringItemMap );
		ProductUtil.selectOptionsOnPanel( optionalFlg, mandatoryFlg, specifyFlg, productOptionsMinNo, productOptionsMaxNo );
	}

	/* method to validate options panel of Product Item*/
	private void validateOptionsPanel() throws Exception
	{
		initializeVariableOptionsPanel( recurringItemMap );
		ProductUtil.validateOptionsOnPanel( optionalFlg, mandatoryFlg, specifyFlg, productOptionsMinNo, productOptionsMaxNo );
	}

	/* method to configure Frequency panel of Product Item*/
	private void configureFrequencyPanel() throws Exception
	{
		initializeVariableFrequencyDetails( recurringItemMap );
		ComboBoxHelper.select( "PS_Detail_productItem_Recurring_Frequency_comboId", frequency );
		if ( ValidationHelper.isTrue( aniversaryAlignBasedOn ) )
		{
			RadioHelper.click( "PS_Detail_productItem_Recurring_anivAlignBased_radioBtnId" );
			if ( ValidationHelper.isTrue( alignWithFrequency ) )
				CheckBoxHelper.check( "PS_Detail_productItem_Recurring_alignWithFreq_chckBxId" );
		}
		else
		{
			RadioHelper.click( "PS_Detail_productItem_Recurring_alignUsingThe_radioBtnId" );
			ComboBoxHelper.select( "PS_Detail_productItem_Recurring_dayOfMonth_comboId", dayOfMonthWeek );
			String valArr[] = ComboBoxHelper.getAllValues( "PS_Detail_productItem_Recurring_alignmentDate_comboId" );
			alignmentDate = valArr[1];
			ComboBoxHelper.select( "PS_Detail_productItem_Recurring_alignmentDate_comboId", alignmentDate );
		}

	}

	/* method to validate frequency panel details of Product Item*/
	private void validateFrequencyPanel() throws Exception
	{
		initializeVariableFrequencyDetails( recurringItemMap );
		assertEquals( ComboBoxHelper.getValue( "PS_Detail_productItem_Recurring_Frequency_comboId" ), frequency, "Recurring Product Item Frequency is not matched" );
		if ( ValidationHelper.isTrue( aniversaryAlignBasedOn ) )
		{
			RadioHelper.isChecked( "PS_Detail_productItem_Recurring_anivAlignBased_radioBtnId" );
			if ( ValidationHelper.isTrue( alignWithFrequency ) )
				CheckBoxHelper.isChecked( "PS_Detail_productItem_Recurring_alignWithFreq_chckBxId" );
			else
				CheckBoxHelper.isNotChecked( "PS_Detail_productItem_Recurring_alignWithFreq_chckBxId" );
		}
		else
		{
			RadioHelper.isChecked( "PS_Detail_productItem_Recurring_alignUsingThe_radioBtnId" );
			assertEquals( ComboBoxHelper.getValue( "PS_Detail_productItem_Recurring_dayOfMonth_comboId" ), dayOfMonthWeek, "Recurring Product Item Day Of Month/Week is not matched" );
		}

	}

	/* method to configure amounts panel of Product Item*/
	private void configureAmountsPanel() throws Exception
	{
		initializeVariableAmountDetails( recurringItemMap );
		ProductUtil.configureAmountsPanel( amount_currencyArr, amount_fromDtArr, amount_amt, amount_amtArr, amount_MinAmnt, amount_MinAmntArr, amount_MaxAmnt, amount_MaxAmntArr );
	}

	/* method to validate amounts panel of Product Item*/
	private void validateAmountsPanel() throws Exception
	{
		initializeVariableAmountDetails( recurringItemMap );
		ProductUtil.validateAmountsPanel( "PS_Detail_productItem_Recurring_amountsGridId", amount_currencyArr, amount_fromDtArr, amount_amt, amount_amtArr, amount_MinAmnt, amount_MinAmntArr, amount_MaxAmnt, amount_MaxAmntArr );
	}

	/* method to configure billing group panel of Product Item*/
	private void configureBillingGrpPanel() throws Exception
	{
		initializeVariableBillingGrpsDetails( recurringItemMap );
		ProductUtil.configureBillingGrpPanel( billingGrpCode, billingGrpCodeArr, billingGrpDefault, billingGrpDefaultArr );
	}

	/* method to validate billing group panel of Product Item*/
	private void validateBillingGrpPanel() throws Exception
	{
		initializeVariableBillingGrpsDetails( recurringItemMap );
		ProductUtil.validateBillingGrpPanel( billingGrpCode, billingGrpCodeArr, billingGrpDefault, billingGrpDefaultArr );
	}

	/* method to configure sales tax  panel of Product Item*/
	private void configureSalesTaxPanel() throws Exception
	{
		initializeVariableSalesTaxDetails( recurringItemMap );
		ProductUtil.configureSalesTaxPanel( salesTax_CountryArr, salesTax_Grp, salesTax_GrpArr, salesTax_Default, salesTax_DefaultArr );
	}

	/* method to validate sales tax  panel of Product Item*/
	private void validateSalesTaxPanel() throws Exception
	{
		initializeVariableSalesTaxDetails( recurringItemMap );
		ProductUtil.validateSalesTaxPanel( salesTax_CountryArr, salesTax_Grp, salesTax_GrpArr, salesTax_Default, salesTax_DefaultArr );
	}
}
