package com.subex.rocps.automation.helpers.application.products.productBundles.product;

import java.util.Map;

import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;
import com.subex.rocps.automation.utils.ExcelHolder;
import com.subex.rocps.automation.utils.PSStringUtils;

import com.subex.automation.helpers.component.ButtonHelper;
import com.subex.automation.helpers.component.CheckBoxHelper;
import com.subex.automation.helpers.component.ComboBoxHelper;
import com.subex.automation.helpers.component.ElementHelper;
import com.subex.automation.helpers.component.GenericHelper;
import com.subex.automation.helpers.component.GridHelper;
import com.subex.automation.helpers.component.TextBoxHelper;
import com.subex.automation.helpers.data.ValidationHelper;

public class OneOffImpl extends PSAcceptanceTest implements ProductItem
{

	protected Map<String, String> oneOffItemMap = null;
	protected String name;
	protected String type;
	protected String reason;
	protected String statusGrp;
	protected String extraArgument;
	protected String externalRef;
	protected String allowOverrideAmntFlg;
	protected String optionalFlg;
	protected String mandatoryFlg;
	protected String specifyFlg;
	protected String productOptionsMinNo;
	protected String productOptionsMaxNo;
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
	 * @param chargeOneOffItemMap
	 */
	public OneOffImpl( Map<String, String> oneOffItemMap )
	{
		this.oneOffItemMap = oneOffItemMap;
	}

	/*
	 * This method is for initialize variable for Basic Details
	 */
	private void initializeVariableBasicDetails( Map<String, String> map ) throws Exception
	{
		name = ExcelHolder.getKey( map, "Name" );
		type = ExcelHolder.getKey( map, "Type" );
		reason = ExcelHolder.getKey( map, "Reason" );
		statusGrp = ExcelHolder.getKey( map, "StatusGroup" );
		extraArgument = ExcelHolder.getKey( map, "ExtraArgument" );
		externalRef = ExcelHolder.getKey( map, "ExternalRefer" );
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

	/*Override method to validate Product Item*/
	@Override
	public void validateProductItem() throws Exception
	{

		vlidateBasicDetails();
		validateOptionsPanel();
		validateAmountsPanel();
		validateBillingGrpPanel();
		validateSalesTaxPanel();
	}

	/*Override method to configure Product Item*/
	@Override
	public void configureProductItem() throws Exception
	{
		configureBasicDetails();
		configureOptionsPanel();
		configureAmountsPanel();
		configureBillingGrpPanel();
		configureSalesTaxPanel();
	}

	/* method to configure basic details of Product Item*/
	private void configureBasicDetails() throws Exception
	{
		initializeVariableBasicDetails( oneOffItemMap );
		ElementHelper.waitForElement( GenericHelper.getORProperty( "PS_Detail_productItem_OneOff_reasonXpath" ), searchScreenWaitSec );
		TextBoxHelper.type( "PS_Detail_productItem_OneOff_Name_txtId", name );
		if ( ValidationHelper.isNotEmpty( type ) )
			ComboBoxHelper.select( "PS_Detail_productItem_OneOff_Type_comboId", type );
		ComboBoxHelper.select( "PS_Detail_productItem_OneOff_Reason_comboId", reason );
		if ( ValidationHelper.isNotEmpty( statusGrp ) )
			ComboBoxHelper.select( "PS_Detail_productItem_OneOff_StatusGrp_comboId", statusGrp );
		if ( ValidationHelper.isNotEmpty( extraArgument ) )
			ComboBoxHelper.select( "PS_Detail_productItem_OneOff_ExtraArg_comboId", extraArgument );
		if ( ValidationHelper.isNotEmpty( externalRef ) )
			TextBoxHelper.type( "PS_Detail_productItem_OneOff_ExternalRef_txtId", externalRef );
		ProductUtil.configAllowAmountCheckBox( allowOverrideAmntFlg );
	}

	/* method to validate basic details of Product Item*/
	private void vlidateBasicDetails() throws Exception
	{
		initializeVariableBasicDetails( oneOffItemMap );
		ElementHelper.waitForElement( GenericHelper.getORProperty( "PS_Detail_productItem_OneOff_reasonXpath" ), searchScreenWaitSec );
		assertEquals( TextBoxHelper.getValue( "PS_Detail_productItem_OneOff_Name_txtId" ), name, "One off Product item Name is not matched" );
		if ( ValidationHelper.isNotEmpty( type ) )
			assertEquals( ComboBoxHelper.getValue( "PS_Detail_productItem_OneOff_Type_comboId" ), type, "One off Product item Type is not matched" );
		if ( ValidationHelper.isNotEmpty( reason ) )
			assertEquals( ComboBoxHelper.getValue( "PS_Detail_productItem_OneOff_Reason_comboId" ), reason, "One off Product item Reason is not matched" );

		if ( ValidationHelper.isNotEmpty( statusGrp ) )
			assertEquals( ComboBoxHelper.getValue( "PS_Detail_productItem_OneOff_StatusGrp_comboId" ), statusGrp, "One off Product item StatusGroup is not matched" );

		if ( ValidationHelper.isNotEmpty( extraArgument ) )
			assertEquals( ComboBoxHelper.getValue( "PS_Detail_productItem_OneOff_ExtraArg_comboId" ), extraArgument, "One off Product item Extra Argument is not matched" );

		if ( ValidationHelper.isNotEmpty( externalRef ) )
			assertEquals( TextBoxHelper.getValue( "PS_Detail_productItem_OneOff_ExternalRef_txtId" ), externalRef, "One off Product item External Ref is not matched" );

		ProductUtil.validateAllowAmountCheckBox( allowOverrideAmntFlg );
	}

	/* method to configure options panel of Product Item*/
	private void configureOptionsPanel() throws Exception
	{
		initializeVariableOptionsPanel( oneOffItemMap );
		ProductUtil.selectOptionsOnPanel( optionalFlg, mandatoryFlg, specifyFlg, productOptionsMinNo, productOptionsMaxNo );
	}

	/* method to validate options panel of Product Item*/
	private void validateOptionsPanel() throws Exception
	{
		initializeVariableOptionsPanel( oneOffItemMap );
		ProductUtil.validateOptionsOnPanel( optionalFlg, mandatoryFlg, specifyFlg, productOptionsMinNo, productOptionsMaxNo );
	}

	/* method to configure amounts panel of Product Item*/
	private void configureAmountsPanel() throws Exception
	{
		initializeVariableAmountDetails( oneOffItemMap );
		ProductUtil.configureAmountsPanel( amount_currencyArr, amount_fromDtArr, amount_amt, amount_amtArr, amount_MinAmnt, amount_MinAmntArr, amount_MaxAmnt, amount_MaxAmntArr );
	}

	/* method to validate amounts panel of Product Item*/
	private void validateAmountsPanel() throws Exception
	{
		initializeVariableAmountDetails( oneOffItemMap );
		ProductUtil.validateAmountsPanel( "PS_Detail_productItem_OneOff_amountsGridId", amount_currencyArr, amount_fromDtArr, amount_amt, amount_amtArr, amount_MinAmnt, amount_MinAmntArr, amount_MaxAmnt, amount_MaxAmntArr );
	}

	/* method to configure billing group panel of Product Item*/
	private void configureBillingGrpPanel() throws Exception
	{
		initializeVariableBillingGrpsDetails( oneOffItemMap );
		ProductUtil.configureBillingGrpPanel( billingGrpCode, billingGrpCodeArr, billingGrpDefault, billingGrpDefaultArr );
	}

	/* method to validate billing group panel of Product Item*/
	private void validateBillingGrpPanel() throws Exception
	{
		initializeVariableBillingGrpsDetails( oneOffItemMap );
		ProductUtil.validateBillingGrpPanel( billingGrpCode, billingGrpCodeArr, billingGrpDefault, billingGrpDefaultArr );
	}

	/* method to configure sales tax  panel of Product Item*/
	private void configureSalesTaxPanel() throws Exception
	{
		initializeVariableSalesTaxDetails( oneOffItemMap );
		ProductUtil.configureSalesTaxPanel( salesTax_CountryArr, salesTax_Grp, salesTax_GrpArr, salesTax_Default, salesTax_DefaultArr );
	}

	/* method to validate sales tax  panel of Product Item*/
	private void validateSalesTaxPanel() throws Exception
	{
		initializeVariableSalesTaxDetails( oneOffItemMap );
		ProductUtil.validateSalesTaxPanel( salesTax_CountryArr, salesTax_Grp, salesTax_GrpArr, salesTax_Default, salesTax_DefaultArr );
	}

}
