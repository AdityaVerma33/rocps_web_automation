package com.subex.rocps.automation.helpers.application.products.productBundles.product;

import net.bytebuddy.build.ToStringPlugin.Exclude;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.subex.rocps.automation.helpers.application.products.ProductBundlesHelper;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;
import com.subex.rocps.automation.utils.ExcelHolder;
import com.subex.rocps.automation.utils.PSStringUtils;

import com.subex.automation.helpers.component.ComboBoxHelper;
import com.subex.automation.helpers.component.ElementHelper;
import com.subex.automation.helpers.component.GenericHelper;
import com.subex.automation.helpers.component.MouseHelper;
import com.subex.automation.helpers.component.TextAreaHelper;
import com.subex.automation.helpers.component.TextBoxHelper;
import com.subex.automation.helpers.data.ValidationHelper;
import com.subex.automation.helpers.file.ExcelReader;
import com.subex.automation.helpers.report.Log4jHelper;

public class ProductImpl extends PSAcceptanceTest
{
	protected Map<String, String> productDetailsMap = null;
	protected String productName;
	protected String productCode;
	protected String productCategory;
	protected String productExtraArg;
	protected String productExternalRefer;
	protected String productDescr;
	protected String optionalFlg;
	protected String mandatoryFlg;
	protected String specifyFlg;
	protected String productOptionsMinNo;
	protected String productOptionsMaxNo;
	protected String productItems;
	protected String productItemsArr[];
	protected int positionOfItem = 0;
	PSStringUtils psStringUtils = new PSStringUtils();

	/**Constructor
	 * @param productDetailsMap
	 */
	public ProductImpl( Map<String, String> productDetailsMap, int positionOfItem )
	{
		this.positionOfItem = positionOfItem;
		this.productDetailsMap = productDetailsMap;
	}

	/*
	 * This method is for initialize variable for Basic Details
	 */
	private void initializeVariableBasicDetails( Map<String, String> map ) throws Exception
	{
		productName = ExcelHolder.getKey( map, "Bundle_ProductName" );
		productCode = ExcelHolder.getKey( map, "Bundle_ProductCode" );
		productCategory = ExcelHolder.getKey( map, "Bundle_ProductCategory" );
		productExtraArg = ExcelHolder.getKey( map, "Bundle_ProductExtraArg" );
		productExternalRefer = ExcelHolder.getKey( map, "Bundle_ProductExternalRefer" );
		productDescr = ExcelHolder.getKey( map, "Bundle_ProductDesc" );
	}

	/*
	 * This method is for initialize variable for Options panel
	 */
	private void initializeVariableOptionsPanel( Map<String, String> map ) throws Exception
	{
		optionalFlg = ExcelHolder.getKey( map, "Optional" );
		mandatoryFlg = ExcelHolder.getKey( map, "Mandatory" );
		specifyFlg = ExcelHolder.getKey( map, "Specify" );
		productOptionsMinNo = ExcelHolder.getKey( map, "Bundle_ProductOptionsMinNo" );
		productOptionsMaxNo = ExcelHolder.getKey( map, "Bundle_ProductOptionsMaxNo" );
	}

	/*Method to configure product   for bundle*/
	public void createProduct( int positionOfProduct ) throws Exception
	{
		configureBasicDetais();
		configureOptionsPanel();
		ProductUtil.clickOnChildOfParentInTree( "Products", String.valueOf( positionOfProduct ) );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		String actualProductNmOnTree = ProductUtil.getchildTextOfTree( "Products", productName );
		assertEquals( actualProductNmOnTree, productName, " This Product is not found on tree :" + productName );
		configureProductItemTestCase();

	}

	/*Method to validate product   for bundle*/
	public void validateProduct() throws Exception
	{
		validateBasicDetais();
		validateOptionsPanel();
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		String actualProductNmOnTree = ProductUtil.getchildTextOfTree( "Products", productName );
		assertEquals( actualProductNmOnTree, productName, " This Product is not found on tree :" + productName );
		validateProductItemTestCase();

	}

	/*Method to configure basic details of product   for bundle*/
	private void configureBasicDetais() throws Exception
	{
		initializeVariableBasicDetails( productDetailsMap );
		ElementHelper.waitForElement( "PS_Detail_bundleProduct_deatails_basicDetailXpath", searchScreenWaitSec );
		TextBoxHelper.type( "PS_Detail_bundleProduct_name_textId", productName );
		TextBoxHelper.type( "PS_Detail_bundleProduct_code_textId", productCode );
		ComboBoxHelper.select( "PS_Detail_bundleProduct_category_comboId", productCategory );
		if ( ValidationHelper.isNotEmpty( productExtraArg ) )
			ComboBoxHelper.select( "PS_Detail_bundleProduct_extraArgu_comboId", productExtraArg );
		if ( ValidationHelper.isNotEmpty( productExternalRefer ) )
			TextBoxHelper.type( "PS_Detail_bundleProduct_externalRef_textId", productExternalRefer );
		if ( ValidationHelper.isNotEmpty( productDescr ) )
			TextAreaHelper.type( "PS_Detail_bundleProduct_descr_textAreaId", productDescr );

	}

	/*Method to validate basic details of product   for bundle*/
	private void validateBasicDetais() throws Exception
	{
		initializeVariableBasicDetails( productDetailsMap );
		ElementHelper.waitForElement( "PS_Detail_bundleProduct_deatails_basicDetailXpath", searchScreenWaitSec );
		assertEquals( TextBoxHelper.getValue( "PS_Detail_bundleProduct_name_textId" ), productName, "Product name is not matched" );
		assertEquals( TextBoxHelper.getValue( "PS_Detail_bundleProduct_code_textId" ), productCode, "Product code is not matched" );
		assertEquals( ComboBoxHelper.getValue( "PS_Detail_bundleProduct_category_comboId" ), productCategory, "Product category is not matched" );
		if ( ValidationHelper.isNotEmpty( productExtraArg ) )
			assertEquals( ComboBoxHelper.getValue( "PS_Detail_bundleProduct_extraArgu_comboId" ), productExtraArg, "Product Extra Argument is not matched" );
		if ( ValidationHelper.isNotEmpty( productExternalRefer ) )
			assertEquals( TextBoxHelper.getValue( "PS_Detail_bundleProduct_externalRef_textId" ), productExternalRefer, "Product External Reference is not matched" );
		if ( ValidationHelper.isNotEmpty( productDescr ) )
			assertEquals( TextAreaHelper.getValue( "PS_Detail_bundleProduct_descr_textAreaId" ), productDescr, "Product  Description is not matched" );

	}

	/*Method to configure options panel of product   for bundle*/
	private void configureOptionsPanel() throws Exception
	{
		initializeVariableOptionsPanel( productDetailsMap );
		ProductUtil.selectOptionsOnPanel( optionalFlg, mandatoryFlg, specifyFlg, productOptionsMinNo, productOptionsMaxNo );
	}

	/*Method to validate options panel of product   for bundle*/
	private void validateOptionsPanel() throws Exception
	{
		initializeVariableOptionsPanel( productDetailsMap );
		ProductUtil.validateOptionsOnPanel( optionalFlg, mandatoryFlg, specifyFlg, productOptionsMinNo, productOptionsMaxNo );
	}

	/*Method to configure product Item test case of product   for bundle*/
	public void configureProductItemTestCase() throws Exception
	{
		productItems = ExcelHolder.getKey( productDetailsMap, "ProductItems" );
		productName = ExcelHolder.getKey( productDetailsMap, "Bundle_ProductName" );

		if ( ValidationHelper.isNotEmpty( productItems ) )
		{
			productItemsArr = psStringUtils.stringSplitFirstLevel( productItems );
			for ( int i = 0; i < productItemsArr.length; i++ )
			{
				String productItemType = productItemsArr[i];
				String prodcutItemTestCaseNm = ExcelHolder.getKey( productDetailsMap, productItemsArr[i] );
				configureProductItem( prodcutItemTestCaseNm, productItemType );

			}
		}
	}

	/*Method to validate product Item test case of product   for bundle*/
	private void validateProductItemTestCase() throws Exception
	{
		productItems = ExcelHolder.getKey( productDetailsMap, "ProductItems" );

		if ( ValidationHelper.isNotEmpty( productItems ) )
		{
			productItemsArr = psStringUtils.stringSplitFirstLevel( productItems );
			for ( int i = 0; i < productItemsArr.length; i++ )
			{
				String productItemType = productItemsArr[i];
				String prodcutItemTestCaseNm = ExcelHolder.getKey( productDetailsMap, productItemsArr[i] );
				validateProductItem( prodcutItemTestCaseNm, productItemType );

			}
		}
	}

	/*Method to configure product Item  of product   for bundle*/
	private void configureProductItem( String testcaseNm, String productItemType ) throws Exception
	{

		String sheetName = ExcelHolder.getKey( productDetailsMap, "SheetName" );
		ProductBundlesHelper prodOb=new ProductBundlesHelper();
		String workbookname =prodOb.getWorkbookName();
		List<Map<String, String>> listOfmap = ProductUtil.getTestCaseMap( testcaseNm, workbookname, sheetName );
		for ( int i = 0; i < listOfmap.size(); i++ )
		{
			Map<String, String> map = listOfmap.get( i );
			String productItemName = map.get( "Name" );
			boolean isProductItemPresentOnTree = ProductUtil.isChildPresentOnTree( productName, productItemName );
			if ( !isProductItemPresentOnTree )
			{
				clickOnProductItemOptions( productName, productItemType );
				ProductItemFactory productItemFactory = new ProductItemFactory( map );
				ProductItem productItem = productItemFactory.getTypeOfItem( productItemType );
				productItem.configureProductItem();
				ProductUtil.clickOnChildOfParentInTree( productName, String.valueOf( positionOfItem ) );
				String actualProductItemOnTree = ProductUtil.getchildTextOfTree( productName, map.get( "Name" ) );
				assertEquals( actualProductItemOnTree, map.get( "Name" ), " This Product Item is not found on tree :" + map.get( "Name" ) );
				positionOfItem++;
				map = null;
			}
			else
			{
				Log4jHelper.logInfo( "The given Product Item Name is already present on tree :" + productItemName );
				ProductUtil.selectChildOfParent("PS_Detail_bundle_treeName", productName, productItemName );
			}

		}
	}

	/*Method to configure product Item  of product   for bundle*/
	private void validateProductItem( String testcaseNm, String productItemType ) throws Exception
	{

		String sheetName = ExcelHolder.getKey( productDetailsMap, "SheetName" );
		ProductBundlesHelper prodOb=new ProductBundlesHelper();
		String workbookname =prodOb.getWorkbookName();
		List<Map<String, String>> listOfmap = ProductUtil.getTestCaseMap( testcaseNm, workbookname, sheetName );

		List<String> listOfActualProductItemNm = new ArrayList<String>();
		for ( int i = 0; i < listOfmap.size(); i++ )
		{
			Map<String, String> map = listOfmap.get( i );
			String productItemName = map.get( "Name" );
			listOfActualProductItemNm.add( productItemName );
		}
		boolean isProductItemPresent = false;
		ArrayList<String> listOfExpProductItemNm = ProductUtil.getchildTextOfTree( productName );
		for ( String productItem : listOfActualProductItemNm )
			isProductItemPresent = listOfExpProductItemNm.stream().anyMatch( x -> x.equals( productItem ) );
		assertTrue( isProductItemPresent, "Product Item name is not matched" );

		for ( int i = 0; i < listOfmap.size(); i++ )
		{

			Map<String, String> map = listOfmap.get( i );
			String productItemName = map.get( "Name" );
			ProductItemFactory productItemFactory = new ProductItemFactory( map );
			ProductItem productItem = productItemFactory.getTypeOfItem( productItemType );
			ProductUtil.selectChildOfParent("PS_Detail_bundle_treeName", productName, productItemName );
			productItem.validateProductItem();
			String actualProductItemOnTree = ProductUtil.getchildTextOfTree( productName, map.get( "Name" ) );
			assertEquals( actualProductItemOnTree, map.get( "Name" ), " This Product Item is not found on tree :" + map.get( "Name" ) );
			map = null;
		}
	}

	//Method to click on product Item Options
	public static void clickOnProductItemOptions( String productNm, String ItemType ) throws Exception
	{
		String productItemText[] = getItemTypeText( ItemType );
		String expandXpath = "//div[@id='cell-list-panel']//div[text()='Expand All']";
		ElementHelper.scrollToView( expandXpath, false );
		ElementHelper.waitForClickableElement( expandXpath, searchScreenWaitSec );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		MouseHelper.click( expandXpath );
		String menuXpath = "//div[starts-with(@id,'productBundleTree') and (text()=' ParentMenuName')]";
		menuXpath = menuXpath.replace( "ParentMenuName", productNm );
		ElementHelper.waitForClickableElement( menuXpath, searchScreenWaitSec );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		MouseHelper.rightClick( menuXpath );

		String subMenuxpath = "//div[starts-with(@id,'productBundleTreeMenu') ]//div[contains(text(),'SubMenuName')]";
		subMenuxpath = subMenuxpath.replace( "SubMenuName", "New" );
		ElementHelper.waitForClickableElement( subMenuxpath, searchScreenWaitSec );
		WebElement subMenuElem = ElementHelper.getElement( subMenuxpath );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );

		String productItemTypeXpath = "//*[@id='ProductItemTypeMenu']//table//div[starts-with(text(),'ItemTypeStartText') and contains(text(),'ItemTypeProperties')]";
		productItemTypeXpath = productItemTypeXpath.replace( "ItemTypeStartText", productItemText[0] );
		productItemTypeXpath = productItemTypeXpath.replace( "ItemTypeProperties", productItemText[1] );

		Actions action = new Actions( driver );
		action.moveToElement( subMenuElem ).perform();
		ElementHelper.waitForClickableElement( productItemTypeXpath, searchScreenWaitSec );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		WebElement productItemElem = ElementHelper.getElement( productItemTypeXpath );
		MouseHelper.click( productItemElem );

	}

	//Methid to get product item type of product
	private static String[] getItemTypeText( String productItemType )
	{
		String productItemText[] = new String[2];
		if ( productItemType.equalsIgnoreCase( "CHARGEONEOFF" ) )
		{
			productItemText[0] = "Charge";
			productItemText[1] = "One Off";
		}
		else if ( productItemType.equalsIgnoreCase( "CreditOneOff" ) )
		{
			productItemText[0] = "Credit";
			productItemText[1] = "One Off";
		}
		else if ( productItemType.equalsIgnoreCase( "CHARGERECURRING" ) )
		{
			productItemText[0] = "Charge";
			productItemText[1] = "Recurring";
		}
		else if ( productItemType.equalsIgnoreCase( "CREDITRECURRING" ) )
		{
			productItemText[0] = "Credit";
			productItemText[1] = "Recurring";
		}
		return productItemText;
	}
}
