package com.subex.rocps.automation.helpers.application.products.productInstance;

import java.util.List;
import java.util.Map;

import com.subex.rocps.automation.helpers.application.genericHelpers.PSGenericHelper;
import com.subex.rocps.automation.helpers.application.products.ProductInstanceHelper;
import com.subex.rocps.automation.helpers.application.products.productBundles.product.ProductUtil;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;
import com.subex.rocps.automation.utils.ExcelHolder;

import com.subex.automation.helpers.component.ButtonHelper;
import com.subex.automation.helpers.component.ElementHelper;
import com.subex.automation.helpers.component.GenericHelper;
import com.subex.automation.helpers.component.GridHelper;
import com.subex.automation.helpers.component.LabelHelper;
import com.subex.automation.helpers.component.SearchGridHelper;
import com.subex.automation.helpers.data.ValidationHelper;
import com.subex.automation.helpers.report.Log4jHelper;
import com.subex.automation.helpers.util.FailureHelper;

public class ProductInstanceDetail extends PSAcceptanceTest
{
	protected Map<String, String> productInstDetailMap = null;
	protected String productBundleNm;
	protected String productBundleVersion;
	protected String productBundleVersionName;
	protected String bundleNmWithVersionNm;
	protected String workbookname;
	protected String sheetName;
	ProductInstItemImpl productInstItemImpl;
	ProductInstItemMemberImpl prodInstItemMemberObj;

	PSGenericHelper psGenericHelper = new PSGenericHelper();

	/**Constructor
	 * @param productInstItemImplMap
	 */
	public ProductInstanceDetail( Map<String, String> productInstDetailMap )
	{

		this.productInstDetailMap = productInstDetailMap;
	}

	/*Method is to initiailize variable for Product Bundle */
	private void initializeVariableOfBundle( Map<String, String> map ) throws Exception
	{
		productBundleNm = ExcelHolder.getKey( map, "ProductBundleName" );
		productBundleVersion = ExcelHolder.getKey( map, "ProductBundleVersion" );
	}

	/*Method is to initiailize variable for workbook name and sheetname*/
	public void initializeBookNmSheetNm()
	{
		ProductInstanceHelper prodInsHelperOb = new ProductInstanceHelper();
		workbookname = prodInsHelperOb.getWorkbookName();
		sheetName = prodInsHelperOb.getSheetName();
	}

	/*Method is to creation of product instance with all mandatory options */
	public void createProductInstWithMandatory() throws Exception
	{
		productBundleFilter();
		configProdInstWithMandatory();
		createProdtInstItemWithMandatory();

	}

	/*Method is to creation of product instance without the mandatory options */
	public void createProductInstWithoutMandatory() throws Exception
	{
		productBundleFilter();
		configProdInstWithMandatory();
		createProdtInstItemWithMandatory();
		verifyProductInstanceCreated();
		selectBundleOnTree();
		createPrInstItemWithoutMandatory( bundleNmWithVersionNm );

	}

	/*Method is to Configure of product instance with all mandatory options */
	private void configProdInstWithMandatory() throws Exception
	{
		waitForInstancePage( "Product Instance" );
		ProductInstanceImpl productInstanceImpl = new ProductInstanceImpl( productInstDetailMap );
		productInstanceImpl.configureProductInstance();
		clickOnButton();
		checkForErrorMessage();

	}

	/*Method is to configuration of product instance item with all mandatory options */
	private void configProdInstItemWithMandatory( Map<String, String> map ) throws Exception
	{
		waitForInstancePage( "Product Item" );
		productInstItemImpl = new ProductInstItemImpl( map );
		productInstItemImpl.configureProductInstItem();
		clickOnButton();
		checkForErrorMessage();

	}

	/*Method is to configuration of Recurring instance item member with all mandatory options */
	private void configureRecurringItemWithMandatory( Map<String, String> map ) throws Exception
	{
		waitForInstancePage( "Recurring Item" );
		RecurringItemInstImpl recurringItemInstImpl = new RecurringItemInstImpl( map );
		recurringItemInstImpl.configureRecurringInstItem();
		clickOnButton();
		checkForErrorMessage();
	}

	/*Method is to configuration of One Off instance item member with all mandatory options */
	private void configureOneOffItemWithMandatory( Map<String, String> map ) throws Exception
	{
		waitForInstancePage( "One Off Item" );
		OneOffItemInstImpl oneOffItemInstImpl = new OneOffItemInstImpl( map );
		oneOffItemInstImpl.configureOneOffInstItem();
		clickOnButton();
		checkForErrorMessage();
	}

	/*Method is to creation of product instance Item with all mandatory options */
	private void createProdtInstItemWithMandatory() throws Exception
	{
		initializeBookNmSheetNm();
		String testCasNm = ExcelHolder.getKey( productInstDetailMap, "InstanceItemTestCase" );

		if ( isInstancPagePresent( "Product Item" ) && ValidationHelper.isNotEmpty( testCasNm ) )
		{
			List<Map<String, String>> listOfmap = ProductUtil.getTestCaseMap( testCasNm, workbookname, sheetName );
			for ( int i = 0; i < listOfmap.size(); i++ )
			{
				Map<String, String> map = listOfmap.get( i );
				if ( isInstancPagePresent( "Product Item" ) )
				{
					configProdInstItemWithMandatory( map );
					createPrInstItemMemberWithMandatory( map );
				}

			}
		}
	}

	/*Method is to creation of product instance Item without mandatory options */
	private void createPrInstItemWithoutMandatory( String bundleNmWithVersionNm ) throws Exception
	{
		initializeBookNmSheetNm();
		String testCasNm = ExcelHolder.getKey( productInstDetailMap, "InstanceItemTestCase" );

		if ( ValidationHelper.isNotEmpty( testCasNm ) )
		{
			List<Map<String, String>> listOfInstanceItemmap = ProductUtil.getTestCaseMap( testCasNm, workbookname, sheetName );
			for ( int i = 0; i < listOfInstanceItemmap.size(); i++ )
			{
				Map<String, String> map = listOfInstanceItemmap.get( i );
				String productName = ExcelHolder.getKey( map, "ProductName" );
				productInstItemImpl = new ProductInstItemImpl( map );
				productInstItemImpl.createPrInstItemWithoutMandatory( bundleNmWithVersionNm );
				ProductInstItemMemberImpl.selectProductOnTree( bundleNmWithVersionNm, productName );
				createPrInstItemMemberWithoutMandatory( map );
				selectBundleOnTree( bundleNmWithVersionNm );
			}
		}
	}

	/*Method is to creation of product instance Item member with all mandatory options */
	private void createPrInstItemMemberWithMandatory( Map<String, String> map ) throws Exception
	{
		String oneOffItem = ExcelHolder.getKey( map, "OneOffItem" );
		String recurringItem = ExcelHolder.getKey( map, "RecurringItem" );
		initializeBookNmSheetNm();
		if ( isInstancPagePresent( "Recurring Item" ) && ValidationHelper.isNotEmpty( recurringItem ) )
			createRecItemMemberWithMandatory( recurringItem );
		if ( isInstancPagePresent( "One Off Item" ) && ValidationHelper.isNotEmpty( oneOffItem ) )
			createOneOfftemMemberWithMandatory( oneOffItem );
	}

	/*Method is to creation of product instance Item Member without mandatory options */
	private void createPrInstItemMemberWithoutMandatory( Map<String, String> map ) throws Exception
	{
		String oneOffItem = ExcelHolder.getKey( map, "OneOffItem" );
		String recurringItem = ExcelHolder.getKey( map, "RecurringItem" );
		initializeBookNmSheetNm();
		if ( ValidationHelper.isNotEmpty( recurringItem ) )
			createRecItemMemberWithoutMandatory( recurringItem );
		if ( ValidationHelper.isNotEmpty( oneOffItem ) )
			createOneOfftemMemberWithoutMandatory( oneOffItem );
	}

	/*Method is to creation of Recurring instance item member with all mandatory options */
	private void createRecItemMemberWithMandatory( String testcaseNm ) throws Exception
	{

		List<Map<String, String>> listOfmap = ProductUtil.getTestCaseMap( testcaseNm, workbookname, sheetName );
		for ( int i = 0; i < listOfmap.size(); i++ )
		{
			Map<String, String> map = listOfmap.get( i );
			if ( isInstancPagePresent( "Recurring Item" ) )
				configureRecurringItemWithMandatory( map );
		}
	}

	/*Method is to creation of Recurring instance item member without mandatory options */
	private void createRecItemMemberWithoutMandatory( String testcaseNm ) throws Exception
	{

		List<Map<String, String>> listOfmap = ProductUtil.getTestCaseMap( testcaseNm, workbookname, sheetName );
		for ( int i = 0; i < listOfmap.size(); i++ )
		{
			Map<String, String> map = listOfmap.get( i );
			prodInstItemMemberObj = new ProductInstItemMemberImpl( map );
			prodInstItemMemberObj.createRecInstItemMemberWithoutMandatory();
		}
	}

	/*Method is to creation of One Off instance item member with all mandatory options */
	private void createOneOfftemMemberWithMandatory( String testcaseNm ) throws Exception
	{
		List<Map<String, String>> listOfmap = ProductUtil.getTestCaseMap( testcaseNm, workbookname, sheetName );
		for ( int i = 0; i < listOfmap.size(); i++ )
		{
			Map<String, String> map = listOfmap.get( i );
			if ( isInstancPagePresent( "One Off Item" ) )
				configureOneOffItemWithMandatory( map );
		}
	}

	/*Method is to creation of One Off instance item member without mandatory options */
	private void createOneOfftemMemberWithoutMandatory( String testcaseNm ) throws Exception
	{
		List<Map<String, String>> listOfmap = ProductUtil.getTestCaseMap( testcaseNm, workbookname, sheetName );
		for ( int i = 0; i < listOfmap.size(); i++ )
		{
			Map<String, String> map = listOfmap.get( i );
			prodInstItemMemberObj = new ProductInstItemMemberImpl( map );
			prodInstItemMemberObj.createOneOffInstItemMemberWithoutMandatory();
		}
	}

	/*Method is to filter the product Bundle*/
	private void productBundleFilter() throws Exception
	{

		psGenericHelper.waitforPopupHeaderElement( "Code" );
		initializeVariableOfBundle( productInstDetailMap );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		SearchGridHelper.gridFilterSearchWithTextBox( "PS_Detail_prodInst_BundleName_textID", productBundleNm, "Name" );
		boolean isProductBundlePresent = GridHelper.isValuePresent( "Detail_popUpWindowId", "SearchGrid", productBundleNm, "Name" );
		assertTrue( isProductBundlePresent, "Product Bundle with name :'" + productBundleNm + "'  is not found in 'Product Bundle Search' popupScreen " );
		int row = getRowOfProductBundle( productBundleVersion );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		GridHelper.clickRow( "Detail_popUpWindowId", "SearchGrid", row, "Code" );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		ButtonHelper.clickIfEnabled( "Detail_popUpWindowId", "OKButton" );

	}

	/*Method to get row of product bundle*/
	private int getRowOfProductBundle( String version ) throws Exception
	{

		int row = 0;

		if ( GridHelper.getRowCount( "Detail_popUpWindowId", "SearchGrid" ) > 1 && ValidationHelper.isNotEmpty( version ) )
			row = GridHelper.getRowNumber( "Detail_popUpWindowId", "SearchGrid", version, "Version" );
		else
			row = GridHelper.getRowNumber( "Detail_popUpWindowId", "SearchGrid", productBundleNm, "Name" );
		assertTrue( row <= GridHelper.getRowCount( "Detail_popUpWindowId", "SearchGrid" ), "This row is not found in Grid :" + row );
		return row;

	}

	/*Method is to wait for instance page*/
	public static void waitForInstancePage( String pageNm ) throws Exception
	{
		String xpath = "//div[@id='formTitle' and text()='PageName']";
		xpath = xpath.replace( "PageName", pageNm );
		ElementHelper.waitForElement( xpath, searchScreenWaitSec );
	}

	/*Method is to wait for popup page*/
	public static void waitForPopupPage( String popupFieldNm ) throws Exception
	{
		String xpath = "//div[@id='window-scroll-panel']//div[starts-with(text(),'PopUpFieldNm')]";
		xpath = xpath.replace( "PopUpFieldNm", popupFieldNm );
		ElementHelper.waitForElement( xpath, searchScreenWaitSec );

	}

	/*Method is to check Instance page present*/
	public boolean isInstancPagePresent( String pageNm ) throws Exception
	{
		String xpath = "//div[@id='formTitle' and text()='PageName']";
		xpath = xpath.replace( "PageName", pageNm );
		return ElementHelper.isElementPresent( xpath );
	}

	/*Method is to check error message*/
	public void checkForErrorMessage() throws Exception
	{
		if ( ElementHelper.isElementPresent( GenericHelper.getORProperty( "PS_Detail_prodInst_errorText_xpath" ) ) )
			FailureHelper.failTest( "The error text found: '" + LabelHelper.getText( "PS_Detail_prodInst_errorText_xpath" ) );
	}

	/*Method is to click on Button*/
	public static void clickOnButton() throws Exception
	{
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		String okButtonXpath = GenericHelper.getORProperty( "PS_Detail_prodInst_OK_btnXpath" );
		if ( ElementHelper.isElementPresent( okButtonXpath ) )
			ButtonHelper.clickIfEnabled( "PS_Detail_prodInst_Instance_gridWrapperID", "PS_Detail_prodInst_OK_btnID" );
		else
			ButtonHelper.clickIfEnabled( "PS_Detail_prodInst_Instance_gridWrapperID", "PS_Detail_prodInst_Next_btnID" );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
	}

	/*Method is to verify product instance created*/
	private void verifyProductInstanceCreated() throws Exception
	{
		String productInstanceNm = ExcelHolder.getKey( productInstDetailMap, "Name" );
		ButtonHelper.click( "ClearButton" );
		psGenericHelper.waitforHeaderElement( "Product Bundle" );
		boolean isProductInstancePresent = psGenericHelper.isGridTextValuePresent( "PS_Detail_prodInst_Instancename_textID", productInstanceNm, "Name" );
		assertTrue( isProductInstancePresent, "Product Instance failed to save with name: '" + productInstanceNm );

	}

	/*Method is to select bundle on tree*/
	private void selectBundleOnTree() throws Exception
	{

		GridHelper.clickRow( "SearchGrid", 1, "Name" );
		productBundleVersionName = GridHelper.getCellValue( "SearchGrid", 1, "Version" );
		bundleNmWithVersionNm = productBundleNm + " - " + productBundleVersionName;
		ProductUtil.selectChildOfParent( "PS_Detail_instance_treeName", "All Bundles", bundleNmWithVersionNm );
		psGenericHelper.waitforHeaderElement( "Product Definition" );
	}

	/*Method is to select bundle on tree with bundleVersionName argument*/
	private void selectBundleOnTree( String bundleNmWithVersionNm ) throws Exception
	{
		ProductUtil.selectChildOfParent( "PS_Detail_instance_treeName", "All Bundles", bundleNmWithVersionNm );
		psGenericHelper.waitforHeaderElement( "Product Definition" );
	}

}
