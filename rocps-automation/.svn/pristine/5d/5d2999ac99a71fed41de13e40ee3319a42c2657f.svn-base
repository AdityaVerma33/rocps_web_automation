package com.subex.rocps.automation.helpers.application.products.productBundles;

import org.openqa.selenium.interactions.Actions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import com.subex.rocps.automation.helpers.application.genericHelpers.PSGenericHelper;
import com.subex.rocps.automation.helpers.application.products.ProductBundlesHelper;
import com.subex.rocps.automation.helpers.application.products.productBundles.product.ProductImpl;
import com.subex.rocps.automation.helpers.application.products.productBundles.product.ProductUtil;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;
import com.subex.rocps.automation.utils.ExcelHolder;
import com.subex.rocps.automation.utils.PSStringUtils;

import com.subex.automation.helpers.component.ButtonHelper;
import com.subex.automation.helpers.component.ComboBoxHelper;
import com.subex.automation.helpers.component.ElementHelper;
import com.subex.automation.helpers.component.GenericHelper;
import com.subex.automation.helpers.component.MouseHelper;
import com.subex.automation.helpers.component.PopupHelper;
import com.subex.automation.helpers.component.TextAreaHelper;
import com.subex.automation.helpers.component.TextBoxHelper;
import com.subex.automation.helpers.component.TreeHelper;
import com.subex.automation.helpers.data.ValidationHelper;
import com.subex.automation.helpers.file.ExcelReader;
import com.subex.automation.helpers.report.Log4jHelper;

public class ProductBundleDetailImpl extends PSAcceptanceTest
{
	protected Map<String, String> productBundleDetailsMap = null;
	protected String bundleNm;
	protected String bundleCode;
	protected String bundleProductType;
	protected String bundleFranchise;
	protected String bundleExternalRefer;
	protected String bundleDescription;
	protected String bundle_VersionNm;
	protected String bundle_VersionStatus;
	protected String bundle_VersionValidFrom;
	protected String bundle_VersionValidTo;
	protected String bundle_VersionExternalRefer;
	protected String bundle_VersionExtraArg;
	protected String bundle_VersionDescription;
	protected String bundleCurrency;
	protected String bundleCurrencyArr[];
	protected String bundleCurrencyValue;
	protected String bundleCurrencyValueArr[];
	protected String bundleCountries;
	protected String bundleCountriesArr[];
	protected String bundleCountries_salesTaxGrp;
	protected String bundleCountries_salesTaxGrpArr[];
	protected String bundleBillingGroup;
	protected String bundleBillingGroupArr[];
	PSStringUtils psStringUtils = new PSStringUtils();
	PSGenericHelper psGenericHelperobj = new PSGenericHelper();

	/**Constructor
	 * @param productBundleDetailsMap
	 */
	public ProductBundleDetailImpl( Map<String, String> productBundleDetailsMap )
	{

		this.productBundleDetailsMap = productBundleDetailsMap;
	}

	/*
	 * This method is for initialize variable for Basic Details
	 */
	private void initializeVariableBasicDetails( Map<String, String> map ) throws Exception
	{
		bundleNm = ExcelHolder.getKey( map, "BundleName" );
		bundleCode = ExcelHolder.getKey( map, "BundleCode" );
		bundleProductType = ExcelHolder.getKey( map, "ProductType" );
		bundleFranchise = ExcelHolder.getKey( map, "Franchise" );
		bundleExternalRefer = ExcelHolder.getKey( map, "BundleExternalRef" );
		bundleDescription = ExcelHolder.getKey( map, "BundleDescription" );
	}

	/*
	 * This method is for initialize variable for version Properties
	 */
	private void initializeVariableVersionProperties( Map<String, String> map ) throws Exception
	{
		bundle_VersionNm = ExcelHolder.getKey( map, "BundleVersion_Name" );
		bundle_VersionStatus = ExcelHolder.getKey( map, "BundleVersion_Status" );
		bundle_VersionValidFrom = ExcelHolder.getKey( map, "BundleVersion_ValidFrom" );
		bundle_VersionValidTo = ExcelHolder.getKey( map, "BundleVersion_ValidTo" );
		bundle_VersionExternalRefer = ExcelHolder.getKey( map, "BundleVersion_ExternalRef" );
		bundle_VersionExtraArg = ExcelHolder.getKey( map, "BundleVersion_ExtraArgument" );
		bundle_VersionDescription = ExcelHolder.getKey( map, "BundleVersion_Description" );
	}

	/*
	 * This method is for initialize variable for currency Properties
	 */
	private void initializeVariableCurrencies( Map<String, String> map ) throws Exception
	{
		bundleCurrency = ExcelHolder.getKey( map, "BundleCurrency" );
		bundleCurrencyValue = ExcelHolder.getKey( map, "BundleCurrency_Value" );
		if ( ValidationHelper.isNotEmpty( bundleCurrency ) )
			bundleCurrencyArr = psStringUtils.stringSplitFirstLevel( bundleCurrency );
		if ( ValidationHelper.isNotEmpty( bundleCurrency ) )
			bundleCurrencyValueArr = psStringUtils.stringSplitFirstLevel( bundleCurrencyValue );
	}

	/*
	 * This method is for initialize variable for Countries Properties
	 */
	private void initializeVariableCountries( Map<String, String> map ) throws Exception
	{
		bundleCountries = ExcelHolder.getKey( map, "BundleCountries" );
		bundleCountries_salesTaxGrp = ExcelHolder.getKey( map, "BundleCountries_SalesTaxGrp" );
		if ( ValidationHelper.isNotEmpty( bundleCountries ) )
			bundleCountriesArr = psStringUtils.stringSplitFirstLevel( bundleCountries );
		if ( ValidationHelper.isNotEmpty( bundleCountries_salesTaxGrp ) )
			bundleCountries_salesTaxGrpArr = psStringUtils.stringSplitFirstLevel( bundleCountries_salesTaxGrp );
	}

	/*
	 * This method is for initialize variable for Billing Group Properties
	 */
	private void initializeVariableBillingGrp( Map<String, String> map ) throws Exception
	{
		bundleBillingGroup = ExcelHolder.getKey( map, "BundleBillingGroups" );
		if ( ValidationHelper.isNotEmpty( bundleBillingGroup ) )
			bundleBillingGroupArr = psStringUtils.stringSplitFirstLevel( bundleBillingGroup );
	}

	/*Method to configure Product bundle*/
	public void createProductBundle() throws Exception
	{
		configBasicDetails();
		configVersionProperties();
		configureCurrency();
		configureCountries();
		configureBillingGroup();
		configureProduct();

	}

	/*Method to view Product bundle*/
	public void viewProductBundle() throws Exception
	{
		validateBasicDetails();
		validateVersionProperties();
		validateCurrency();
		validateCountry();
		validateBillingGroup();
		validateProduct();

	}

	/*Method to modify Product bundle*/
	public void modifyProductBundle( String bundleName ) throws Exception
	{
		modifyBasicDetails( bundleName );
		configVersionProperties();
		configureCurrency();
		configureCountries();
		configureBillingGroup();
		configureProduct();
	}

	/*
	 * This method is for Product Bundle creation with validate message for currency, Country, Billing Groups
	 */
	public void createWithValidateMessage() throws Exception
	{
		bundleBillingGroup = ExcelHolder.getKey( productBundleDetailsMap, "BundleBillingGroups" );
		configBasicDetails();
		configVersionProperties();
		ButtonHelper.click( "PS_Detail_bundle_save_BtnId" );
		boolean isCurrencyAlertMszPresent = PopupHelper.isTextPresent( "There are no currencies defined for this bundle definition.There must be atleast one so please add a currency now." );
		assertTrue( isCurrencyAlertMszPresent, "Bundle should not save without currency" );
		ButtonHelper.click( "OKButton" );
		configureCurrency();
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		boolean isCountryAlertMszPresent = PopupHelper.isTextPresent( "There are no countries defined for this bundle definition.There must be atleast one so please add a country now." );
		assertTrue( isCountryAlertMszPresent, "Bundle should not save without Country" );
		ButtonHelper.click( "OKButton" );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		configureCountries();
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		boolean isBillingGrpAlertMszPresent = PopupHelper.isTextPresent( "There are no generic billing groups defined for this bundle definition.Do you wish to add one now?" );
		assertTrue( isBillingGrpAlertMszPresent, "Popup message for billing groups should be present" );

		if ( ValidationHelper.isNotEmpty( bundleBillingGroup ) )
		{
			ButtonHelper.click( "YesButton" );
			configureBillingGroup();
		}
		else
			ButtonHelper.click( "NoButton" );
		configureProduct();

	}

	/*
	 * This method is for configure Basic details for bundle
	 */
	private void configBasicDetails() throws Exception
	{
		initializeVariableBasicDetails( productBundleDetailsMap );
		TextBoxHelper.type( "PS_Detail_bundle_name_textID", bundleNm );
		TextBoxHelper.type( "PS_Detail_bundle_code_textID", bundleCode );
		ComboBoxHelper.select( "PS_Detail_bundle_productType_comboID", bundleProductType );
		if ( ValidationHelper.isNotEmpty( bundleFranchise ) )
			ComboBoxHelper.select( "PS_Detail_bundle_franchise_comboID", bundleFranchise );
		if ( ValidationHelper.isNotEmpty( bundleExternalRefer ) )
			TextBoxHelper.type( "PS_Detail_bundle_externalRefer_textID", bundleExternalRefer );
		if ( ValidationHelper.isNotEmpty( bundleDescription ) )
			TextAreaHelper.type( "PS_Detail_bundle_description_textAreaID", bundleDescription );
	}

	/*
	 * This method is for validate Basic details for bundle
	 */
	private void validateBasicDetails() throws Exception
	{
		initializeVariableBasicDetails( productBundleDetailsMap );
		assertEquals( TextBoxHelper.getValue( "PS_Detail_bundle_name_textID" ), bundleNm, "Bundle Name is not matched" );
		assertEquals( TextBoxHelper.getValue( "PS_Detail_bundle_code_textID" ), bundleCode, "Bundle Name is not matched" );
		assertEquals( ComboBoxHelper.getValue( "PS_Detail_bundle_productType_comboID" ), bundleProductType, "Bundle Product Type is not matched" );
		if ( ValidationHelper.isNotEmpty( bundleFranchise ) )
			assertEquals( ComboBoxHelper.getValue( "PS_Detail_bundle_franchise_comboID" ), bundleFranchise, "Bundle Franchise  is not matched" );
		if ( ValidationHelper.isNotEmpty( bundleExternalRefer ) )
			assertEquals( TextBoxHelper.getValue( "PS_Detail_bundle_externalRefer_textID" ), bundleExternalRefer, "Bundle External Reference is not matched" );
		if ( ValidationHelper.isNotEmpty( bundleDescription ) )
			assertEquals( TextAreaHelper.getValue( "PS_Detail_bundle_description_textAreaID" ), bundleDescription, "Bundle Description is not matched" );
	}

	/*Method to modify Basic details for bundle*/
	public void modifyBasicDetails( String bundleName ) throws Exception
	{
		initializeVariableBasicDetails( productBundleDetailsMap );
		if ( ValidationHelper.isNotEmpty( TextBoxHelper.getValue( "PS_Detail_PBundleDrillDown_bundle_name_textXpath" ) ) )
			assertEquals( TextBoxHelper.getValue( "PS_Detail_PBundleDrillDown_bundle_name_textXpath" ), bundleName, "Bundle Name is not matched" );
		else
			TextBoxHelper.type( "PS_Detail_PBundleDrillDown_bundle_name_textXpath", bundleName );
		if ( ValidationHelper.isNotEmpty( TextBoxHelper.getValue( "PS_Detail_PBundleDrillDown_bundle_code_textXpath" ) ) )
			assertEquals( TextBoxHelper.getValue( "PS_Detail_PBundleDrillDown_bundle_code_textXpath" ), bundleCode, "Bundle Code is not matched" );
		else
			TextBoxHelper.type( "PS_Detail_PBundleDrillDown_bundle_code_textXpath", bundleCode );
		ProductUtil.modifyComboBox( "PS_Detail_PBundleDrillDown_bundle_productType_comboXpath", bundleProductType );
		ProductUtil.modifyComboBox( "PS_Detail_PBundleDrillDown_bundle_franchise_comboXpath", bundleFranchise );
		ProductUtil.modifyTextBox( "PS_Detail_PBundleDrillDown_bundle_externalRefer_textXpath", bundleExternalRefer );
		ProductUtil.modifyTextAreaBox( "PS_Detail_PBundleDrillDown_bundle_description_textAreaXath", bundleDescription );
	}

	/*Method to configure version properties for bundle*/
	private void configVersionProperties() throws Exception
	{
		initializeVariableVersionProperties( productBundleDetailsMap );
		configureVersionNm( bundle_VersionNm );
		ComboBoxHelper.select( "PS_Detail_bundleVersion_status_comboID", bundle_VersionStatus );
		TextBoxHelper.type( "PS_Detail_bundleVersion_from_textID", bundle_VersionValidFrom );
		TextBoxHelper.type( "PS_Detail_bundleVersion_to_textID", bundle_VersionValidTo );
		TextBoxHelper.type( "PS_Detail_bundleVersion_externalRef_textID", bundle_VersionExternalRefer );
		ComboBoxHelper.select( "PS_Detail_bundleVersion_extraArg_comboID", bundle_VersionExtraArg );
		TextAreaHelper.type( "PS_Detail_bundleVersion_descrip_textareaID", bundle_VersionDescription );
	}

	/*Method to modify version properties for bundle*/
	public void modifyVersionProperties() throws Exception
	{
		initializeVariableVersionProperties( productBundleDetailsMap );
		ProductUtil.modifyTextBox( "PS_Detail_PBundleDrillDown_bundleVersion_name_textXpath", bundle_VersionNm );
		ProductUtil.modifyComboBox( "PS_Detail_PBundleDrillDown_bundleVersion_status_comboXpath", bundle_VersionStatus );
        ProductUtil.modifyTextBox( "PS_Detail_PBundleDrillDown_bundleVersion_from_textXpath", bundle_VersionValidFrom );
		ProductUtil.modifyTextBox( "PS_Detail_PBundleDrillDown_bundleVersion_to_textXpath", bundle_VersionValidTo);
		ProductUtil.modifyTextBox( "PS_Detail_PBundleDrillDown_bundleVersion_externalRef_textXpath", bundle_VersionExternalRefer );
		ProductUtil.modifyComboBox( "PS_Detail_PBundleDrillDown_bundleVersion_extraArg_comboXpath", bundle_VersionExtraArg );
		ProductUtil.modifyTextAreaBox( "PS_Detail_PBundleDrillDown_bundleVersion_descrip_textareaXpath", bundle_VersionDescription );
	}
	/*Method to validate version properties for bundle*/
	private void validateVersionProperties() throws Exception
	{
		initializeVariableVersionProperties( productBundleDetailsMap );
		validateVersionNm( bundle_VersionNm );
		assertEquals( ComboBoxHelper.getValue( "PS_Detail_bundleVersion_status_comboID" ), "Accepted", "Version status is not in Accepted state" );
		if ( ValidationHelper.isNotEmpty( bundle_VersionValidFrom ) )
			assertEquals( TextBoxHelper.getValue( "PS_Detail_bundleVersion_from_textID" ), bundle_VersionValidFrom, "Version Valid From Date is not matched" );
		if ( ValidationHelper.isNotEmpty( bundle_VersionValidTo ) )
			assertEquals( TextBoxHelper.getValue( "PS_Detail_bundleVersion_to_textID" ), bundle_VersionValidTo, "Version Valid To Date is not matched" );
		if ( ValidationHelper.isNotEmpty( bundle_VersionExternalRefer ) )
			assertEquals( TextBoxHelper.getValue( "PS_Detail_bundleVersion_externalRef_textID" ), bundle_VersionExternalRefer, "Version External Reference is not matched" );
		if ( ValidationHelper.isNotEmpty( bundle_VersionExtraArg ) )
			assertEquals( ComboBoxHelper.getValue( "PS_Detail_bundleVersion_extraArg_comboID" ), bundle_VersionExtraArg, "Version Extera Argument is not matched" );
		if ( ValidationHelper.isNotEmpty( bundle_VersionDescription ) )
			assertEquals( TextAreaHelper.getValue( "PS_Detail_bundleVersion_descrip_textareaID" ), bundle_VersionDescription, "Version Description  is not matched" );

	}

	/*Method to configure currency properties for bundle*/
	private void configureCurrency() throws Exception
	{
		initializeVariableCurrencies( productBundleDetailsMap );
		for ( int i = 0; i < bundleCurrencyArr.length; i++ )
		{
			boolean isCurrencyPresentInTree = ProductUtil.isChildPresentOnTree( "Currencies", bundleCurrencyArr[i] );
			if ( !isCurrencyPresentInTree )
			{
				if ( !ElementHelper.isElementPresent( GenericHelper.getORProperty( "PS_Detail_bundle_currencyName_xpath" ) ) )
					ProductUtil.clickOnBundleTreeOptions( "Currencies", "Add" );
				ElementHelper.waitForElement( GenericHelper.getORProperty( "PS_Detail_bundle_currencyName_xpath" ), searchScreenWaitSec );
				ComboBoxHelper.select( "PS_Detail_bundle_currencyName_comboID", bundleCurrencyArr[i] );
				if ( ValidationHelper.isNotEmpty( bundleCurrencyValue ) )
					TextBoxHelper.type( "PS_Detail_bundle_currencyValue_textID", bundleCurrencyValueArr[i] );
				ButtonHelper.click( "OKButton" );
				ElementHelper.waitForElementToDisappear( GenericHelper.getORProperty( "PS_Detail_bundle_currencyName_xpath" ), searchScreenWaitSec );
				String actualCurrencyOnTree = ProductUtil.getchildTextOfTree( "Currencies", bundleCurrencyArr[i] );
				assertEquals( actualCurrencyOnTree, bundleCurrencyArr[i], " This Currency is not found on tree :" + bundleCurrencyArr[i] );
			}
			else
				Log4jHelper.logInfo( "The given Currency  is already present on tree: " + bundleCurrencyArr[i] );
		}

	}

	/*Method to validate currency properties for bundle*/
	public void validateCurrency() throws Exception
	{
		initializeVariableCurrencies( productBundleDetailsMap );
		boolean iscurrencyPresenet = false;
		ArrayList<String> listOfChildCurrencies = ProductUtil.getchildTextOfTree( "Currencies" );
		assertEquals( listOfChildCurrencies.size(), bundleCurrencyArr.length, "Currency size should be same for actual and expected " );
		for ( String currency : bundleCurrencyArr )
			iscurrencyPresenet = listOfChildCurrencies.stream().anyMatch( x -> x.equals( currency ) );
		assertTrue( iscurrencyPresenet, "Currency is not matched" );

	}

	/*Method to configure coutries properties for bundle*/
	private void configureCountries() throws Exception //as one method create for all impl
	{
		initializeVariableCountries( productBundleDetailsMap );
		for ( int i = 0; i < bundleCountriesArr.length; i++ )
		{

			boolean isCountryPresentInTree = ProductUtil.isChildPresentOnTree( "Countries", bundleCountriesArr[i] );
			if ( !isCountryPresentInTree )
			{
				if ( !ElementHelper.isElementPresent( GenericHelper.getORProperty( "PS_Detail_bundle_countryName_xpath" ) ) )
					ProductUtil.clickOnBundleTreeOptions( "Countries", "Add" );
				ElementHelper.waitForElement( GenericHelper.getORProperty( "PS_Detail_bundle_countryName_xpath" ), searchScreenWaitSec );
				ComboBoxHelper.select( "PS_Detail_bundle_countryName_comboID", bundleCountriesArr[i] );
				if ( ValidationHelper.isNotEmpty( bundleCountries_salesTaxGrp ) )
					ComboBoxHelper.select( "PS_Detail_bundle_country_salesTaxGrp_comboId", bundleCountries_salesTaxGrpArr[i] );
				ButtonHelper.click( "OKButton" );
				ElementHelper.waitForElementToDisappear( GenericHelper.getORProperty( "PS_Detail_bundle_countryName_xpath" ), searchScreenWaitSec );
				String actualCountryOnTree = ProductUtil.getchildTextOfTree( "Countries", bundleCountriesArr[i] );
				assertEquals( actualCountryOnTree, bundleCountriesArr[i], " This Country is not found on tree :" + bundleCountriesArr[i] );
			}
			else
				Log4jHelper.logInfo( "The given Country  is already present on tree: " + bundleCountriesArr[i] );
		}

	}

	/*Method to validate countries properties for bundle*/
	private void validateCountry() throws Exception
	{
		initializeVariableCountries( productBundleDetailsMap );
		boolean iscountryPresenet = false;
		ArrayList<String> listOfChildCountries = ProductUtil.getchildTextOfTree( "Countries" );
		assertEquals( listOfChildCountries.size(), bundleCountriesArr.length, "Countries size should be same for actual and expected " );
		for ( String country : bundleCountriesArr )
			iscountryPresenet = listOfChildCountries.stream().anyMatch( x -> x.equals( country ) );
		assertTrue( iscountryPresenet, "Country is not matched" );

	}

	/*Method to configure Billing group properties for bundle*/
	private void configureBillingGroup() throws Exception
	{
		initializeVariableBillingGrp( productBundleDetailsMap );
		if ( ValidationHelper.isNotEmpty( bundleBillingGroup ) )
		{
			for ( int i = 0; i < bundleBillingGroupArr.length; i++ )
			{

				boolean isBillingGroupPresentInTree = ProductUtil.isChildPresentOnTree( "Billing Groups", bundleBillingGroupArr[i] );
				if ( !isBillingGroupPresentInTree )
				{
					if ( !ElementHelper.isElementPresent( GenericHelper.getORProperty( "PS_Detail_bundle_billingGrpName_xpath" ) ) )
						ProductUtil.clickOnBundleTreeOptions( "Billing Groups", "Add" );
					ElementHelper.waitForElement( GenericHelper.getORProperty( "PS_Detail_bundle_billingGrpName_xpath" ), searchScreenWaitSec );
					ComboBoxHelper.select( "PS_Detail_bundle_billingGrp_comboId", bundleBillingGroupArr[i] );
					ButtonHelper.click( "OKButton" );
					ElementHelper.waitForElementToDisappear( GenericHelper.getORProperty( "PS_Detail_bundle_billingGrpName_xpath" ), searchScreenWaitSec );
					String actualBillingGrpOnTree = ProductUtil.getchildTextOfTree( "Billing Groups", bundleBillingGroupArr[i] );
					assertEquals( actualBillingGrpOnTree, bundleBillingGroupArr[i], " This Billing Group is not found on tree :" + bundleBillingGroupArr[i] );
				}
				else
					Log4jHelper.logInfo( "The given Billing Group  is already present on tree: " + bundleBillingGroupArr[i] );
			}
		}

	}

	/*Method to validate billing group properties for bundle*/
	private void validateBillingGroup() throws Exception
	{
		initializeVariableBillingGrp( productBundleDetailsMap );
		if ( ValidationHelper.isNotEmpty( bundleBillingGroup ) )
		{
			boolean isBillingGrpPresenet = false;
			ArrayList<String> listOfChildBillingGrp = ProductUtil.getchildTextOfTree( "Billing Groups" );
			assertEquals( listOfChildBillingGrp.size(), bundleBillingGroupArr.length, "Billing Group size should be same for actual and expected " );
			for ( String billingGrp : bundleBillingGroupArr )
				isBillingGrpPresenet = listOfChildBillingGrp.stream().anyMatch( x -> x.equals( billingGrp ) );
			assertTrue( isBillingGrpPresenet, "Billing Group is not matched" );
		}
	}

	/*Method to configure version name  for bundle*/
	private void configureVersionNm( String bundleVersinNm ) throws Exception
	{
		if ( ValidationHelper.isNotEmpty( bundleVersinNm ) )
			TextBoxHelper.type( "PS_Detail_bundleVersion_name_textID", bundleVersinNm );
		else
			assertTrue( TextBoxHelper.getValue( "PS_Detail_bundleVersion_name_textID" ).contains( "Version" ), "Version name is not matched" );
	}

	/*Method to validate version name  for bundle*/
	private void validateVersionNm( String bundleVersinNm ) throws Exception
	{
		if ( ValidationHelper.isNotEmpty( bundleVersinNm ) )
			assertEquals( TextBoxHelper.getValue( "PS_Detail_bundleVersion_name_textID" ), bundleVersinNm );
		else
			assertTrue( TextBoxHelper.getValue( "PS_Detail_bundleVersion_name_textID" ).contains( "Version" ), "Version name is not matched" );
	}

	/*Method to configure product   for bundle*/
	private void configureProduct() throws Exception
	{
		int positionOfProductItem;
		ProductBundlesHelper prodOb=new ProductBundlesHelper();
		String workbookname =prodOb.getWorkbookName();
		String testCasNm = ExcelHolder.getKey( productBundleDetailsMap, "Bundle_Products" );
		String sheetName = ExcelHolder.getKey( productBundleDetailsMap, "SheetName" );
		
		if ( ValidationHelper.isNotEmpty( testCasNm ) )
		{
			List<Map<String, String>> listOfmap = ProductUtil.getTestCaseMap( testCasNm, workbookname, sheetName );
			for ( int i = 0; i < listOfmap.size(); i++ )
			{
				Map<String, String> map = listOfmap.get( i );
				String productName = map.get( "Bundle_ProductName" );
				boolean isProductPresentOnTree = ProductUtil.isChildPresentOnTree( "Products", productName );
				if ( !isProductPresentOnTree )
				{
					int positionOfProduct = i + 1;
					positionOfProductItem = 1;
					ProductUtil.clickOnBundleTreeOptions( "Products", "New" );
					ProductImpl productImpl = new ProductImpl( map, positionOfProductItem );
					productImpl.createProduct( positionOfProduct );
					map = null;
				}
				else
				{

					Log4jHelper.logInfo( "The given product name is already present on tree: " + productName );
					ArrayList<String> listOfExpProductItemNm = ProductUtil.getchildTextOfTree( productName );
					positionOfProductItem = listOfExpProductItemNm.size() + 1;
					ProductUtil.selectChildOfParent( "PS_Detail_bundle_treeName","Products", productName );
					ProductImpl productImpl = new ProductImpl( map, positionOfProductItem );
					productImpl.configureProductItemTestCase();

				}
			}
		}
	}

	/*Method to validate product   for bundle*/
	private void validateProduct() throws Exception
	{
		ProductBundlesHelper prodOb=new ProductBundlesHelper();
		String workbookname =prodOb.getWorkbookName();
		int positionOfProductItem = 1;
		String testCasNm = ExcelHolder.getKey( productBundleDetailsMap, "Bundle_Products" );
		String sheetName = ExcelHolder.getKey( productBundleDetailsMap, "SheetName" );
		if ( ValidationHelper.isNotEmpty( testCasNm ) )
		{
			List<Map<String, String>> listOfmap = ProductUtil.getTestCaseMap( testCasNm, workbookname, sheetName );
			List<String> listOfActualProductNm = new ArrayList<String>();
			for ( int i = 0; i < listOfmap.size(); i++ )
			{
				Map<String, String> map = listOfmap.get( i );
				String productName = map.get( "Bundle_ProductName" );
				listOfActualProductNm.add( productName );
			}
			boolean isProductPresent = false;
			ArrayList<String> listOfExpecProductNm = ProductUtil.getchildTextOfTree( "Products" );
			assertEquals( listOfActualProductNm.size(), listOfExpecProductNm.size(), "Products name size should be same for actual and expected " );
			for ( String product : listOfActualProductNm )
				isProductPresent = listOfExpecProductNm.stream().anyMatch( x -> x.equals( product ) );
			assertTrue( isProductPresent, "Product name is not matched" );
			for ( int i = 0; i < listOfmap.size(); i++ )
			{
				Map<String, String> map = listOfmap.get( i );
				String productName = map.get( "Bundle_ProductName" );
				ProductUtil.selectChildOfParent("PS_Detail_bundle_treeName", "Products", productName );
				ProductImpl productImpl = new ProductImpl( map, positionOfProductItem );
				productImpl.validateProduct();
				map = null;
			}
		}
	}


	/*Method to save bundle*/
	public void saveBundle( String bundleNm ) throws Exception
	{
		psGenericHelperobj.detailSave( "PS_Detail_bundle_save_BtnId", bundleNm, "Name" );

	}

}
