package com.subex.rocps.automation.helpers.application.products;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.subex.rocps.automation.helpers.application.filters.AdvanceSearchFiltersHelper;
import com.subex.rocps.automation.helpers.application.filters.GridFilterSearchHelper;
import com.subex.rocps.automation.helpers.application.filters.PSSearchGridHelper;
import com.subex.rocps.automation.helpers.application.genericHelpers.PSGenericHelper;
import com.subex.rocps.automation.helpers.application.products.productBundleDrillDown.PBundleDrillDownActionImpl;
import com.subex.rocps.automation.helpers.application.products.productBundles.ProductBundleDetailImpl;
import com.subex.rocps.automation.helpers.application.products.productBundles.product.ProductUtil;
import com.subex.rocps.automation.helpers.application.products.productInstance.OneOffItemInstImpl;
import com.subex.rocps.automation.helpers.application.products.productInstance.ProductInstItemImpl;
import com.subex.rocps.automation.helpers.application.products.productInstance.ProductInstanceImpl;
import com.subex.rocps.automation.helpers.application.products.productInstance.RecurringItemInstImpl;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;
import com.subex.rocps.automation.utils.ExcelHolder;
import com.subex.rocps.automation.utils.PSStringUtils;

import com.subex.automation.helpers.application.NavigationHelper;
import com.subex.automation.helpers.component.ButtonHelper;
import com.subex.automation.helpers.component.ComboBoxHelper;
import com.subex.automation.helpers.component.ElementHelper;
import com.subex.automation.helpers.component.GenericHelper;
import com.subex.automation.helpers.component.GridHelper;
import com.subex.automation.helpers.component.SearchGridHelper;
import com.subex.automation.helpers.componentHelpers.SearchHelper;
import com.subex.automation.helpers.data.ValidationHelper;
import com.subex.automation.helpers.file.ExcelReader;
import com.subex.automation.helpers.report.Log4jHelper;
import com.subex.automation.helpers.util.FailureHelper;

public class ProductBundleDrillDownHelper extends PSAcceptanceTest
{

	protected ExcelReader excelData = new ExcelReader();
	protected Map<String, ArrayList<String>> productBundleDrillDownExcelMap = null;
	protected Map<String, String> productBundleDrillDownMap = null;
	protected ExcelHolder excelHolderObj = null;
	protected String path;
	protected String workBookName;
	protected String sheetName;
	protected String testCaseName;
	protected int occurence;
	protected String clientPartition;
	protected String colmHdrs;
	protected int colSize;
	protected int index;
	protected String productScreen;
	PSGenericHelper psGenericHelper = new PSGenericHelper();
	PSStringUtils psStringUtils = new PSStringUtils();
	AdvanceSearchFiltersHelper advanceSearchHelpOb = new AdvanceSearchFiltersHelper();
	GridFilterSearchHelper gridFilterSearchHelper = new GridFilterSearchHelper();

	/**Constructor : Initializing the excel without occurrence
	 * @param path
	 * @param workBookName
	 * @param sheetName
	 * @param testCaseName
	 * @throws Exception 
	 */
	public ProductBundleDrillDownHelper( String path, String workBookName, String sheetName, String testCaseName ) throws Exception
	{

		this.path = path;
		this.workBookName = workBookName;
		this.sheetName = sheetName;
		this.testCaseName = testCaseName;
		productBundleDrillDownExcelMap = excelData.readDataByColumn( path, workBookName, sheetName, testCaseName );
		excelHolderObj = new ExcelHolder( productBundleDrillDownExcelMap );
		colSize = excelHolderObj.totalColumns();
	}

	/** Constructor : Initializing the excel with occurrence
	 * @param path
	 * @param workBookName
	 * @param sheetName
	 * @param testCaseName
	 * @param occurence
	 * @throws Exception 
	 */
	public ProductBundleDrillDownHelper( String path, String workBookName, String sheetName, String testCaseName, int occurence ) throws Exception
	{

		this.path = path;
		this.workBookName = workBookName;
		this.sheetName = sheetName;
		this.testCaseName = testCaseName;
		this.occurence = occurence;
		productBundleDrillDownExcelMap = excelData.readDataByColumn( path, workBookName, sheetName, testCaseName, occurence );
		excelHolderObj = new ExcelHolder( productBundleDrillDownExcelMap );
		colSize = excelHolderObj.totalColumns();
	}

	/*
	 * This method is for Product Bundle  Drill Down screen common method
	 */
	private void productBundleDrillDownScreen() throws Exception
	{
		NavigationHelper.navigateToScreen( "Product Bundle Drill-Down" );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		productBundleDrillDownMap = excelHolderObj.dataMap( index );
		ButtonHelper.click( "ClearButton" );
	}

	/*
	 * This method is for Product Bundle Drill-Down screen column validation
	 */
	public void productBundleDrillDownColumsValidation() throws Exception
	{
		try
		{
			for ( index = 0; index < colSize; index++ )
			{
				productBundleDrillDownScreen();
				productScreen = ExcelHolder.getKey( productBundleDrillDownMap, "ProductScreen" );
				productScreenFilter( productScreen );
				colmHdrs = ExcelHolder.getKey( productBundleDrillDownMap, "SearchScreenColumns" );
				ArrayList<String> excelColumnNames = new ArrayList<String>();
				String bundleDrillDownGridColumnsArr[] = psStringUtils.stringSplitFirstLevel( colmHdrs );
				for ( String column : bundleDrillDownGridColumnsArr )
					excelColumnNames.add( column );
				psGenericHelper.totalColumns( excelColumnNames );
				Log4jHelper.logInfo( "'Product Bundle Drill-Down screen' Columns are validated successfully for this product screen:-:- '" + productScreen + "'\n" );
			}
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}

	}

	/*
	 * This method is for Product Bundle Drill-Down Edit Product Bundle
	 */
	public void productBundleEdit() throws Exception
	{
		try
		{
			for ( index = 0; index < colSize; index++ )
			{
				productBundleDrillDownScreen();
				String testCaseNm = ExcelHolder.getKey( productBundleDrillDownMap, "EditScreenTestCase" );
				productScreen = ExcelHolder.getKey( productBundleDrillDownMap, "ProductScreen" );
				productScreenFilter( productScreen );
				modifyProductBundle( testCaseNm );
			}
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}

	}

	/*
	 * This method is for Product Bundle Drill-Down Edit Product Instance
	 */
	public void productInstanceEdit() throws Exception
	{
		try
		{
			for ( index = 0; index < colSize; index++ )
			{
				productBundleDrillDownScreen();
				String testCaseNm = ExcelHolder.getKey( productBundleDrillDownMap, "EditScreenTestCase" );
				productScreen = ExcelHolder.getKey( productBundleDrillDownMap, "ProductScreen" );
				productScreenFilter( productScreen );
				modifyProductInstance( testCaseNm );
			}
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}

	}

	/*
	 * This method is for Product Bundle Drill-Down Edit Product Instance Item
	 */
	public void productInstanceItemEdit() throws Exception
	{
		try
		{
			for ( index = 0; index < colSize; index++ )
			{
				productBundleDrillDownScreen();
				String testCaseNm = ExcelHolder.getKey( productBundleDrillDownMap, "EditScreenTestCase" );
				productScreen = ExcelHolder.getKey( productBundleDrillDownMap, "ProductScreen" );
				productScreenFilter( productScreen );
				modifyProductInstItem( testCaseNm );
			}
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}

	}

	/*
	 * This method is for Product Bundle Drill-Down Edit 'One Off Item Instance'
	 */
	public void oneOffItemInstanceEdit() throws Exception
	{
		try
		{
			for ( index = 0; index < colSize; index++ )
			{
				productBundleDrillDownScreen();
				String testCaseNm = ExcelHolder.getKey( productBundleDrillDownMap, "EditScreenTestCase" );
				productScreen = ExcelHolder.getKey( productBundleDrillDownMap, "ProductScreen" );
				productScreenFilter( productScreen );
				modifyOneOffItemOnstance( testCaseNm );
			}
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}

	}

	/*
	 * This method is for Product Bundle Drill-Down Edit 'One Off Item Instance'
	 */
	public void recurringItemInstanceEdit() throws Exception
	{
		try
		{
			for ( index = 0; index < colSize; index++ )
			{
				productBundleDrillDownScreen();
				String testCaseNm = ExcelHolder.getKey( productBundleDrillDownMap, "EditScreenTestCase" );
				productScreen = ExcelHolder.getKey( productBundleDrillDownMap, "ProductScreen" );
				productScreenFilter( productScreen );
				modifyRecurringItemOnstance( testCaseNm );
			}
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}

	}

	/*
	 * This method is for Product Bundle Drill-Down Edit 'Product Instance Item Member'
	 */
	public void productInstItemMemberEdit() throws Exception
	{
		try
		{
			for ( index = 0; index < colSize; index++ )
			{
				productBundleDrillDownScreen();
				String testCaseNm = ExcelHolder.getKey( productBundleDrillDownMap, "EditScreenTestCase" );
				productScreen = ExcelHolder.getKey( productBundleDrillDownMap, "ProductScreen" );
				productScreenFilter( productScreen );
				modifyProductInstItemMember( testCaseNm );
			}
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}

	}

	/*
	 * This method is for Product Bundle Drill-Down 'Product screen' filter
	 */
	private static void productScreenFilter( String productScreen ) throws Exception
	{
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		ComboBoxHelper.select( "PS_Detail_PBundleDrillDown_productScreen_comboId", productScreen );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		ButtonHelper.click( "SearchButton" );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
	}

	/*
	 * This method is for modify Product bundle
	 */
	private void modifyProductBundle( String testCaseNm ) throws Exception
	{
		psGenericHelper.waitforHeaderElement( "Product Type" );
		List<Map<String, String>> listOfmap = ProductUtil.getTestCaseMap( testCaseNm, workBookName, sheetName );
		for ( int i = 0; i < listOfmap.size(); i++ )
		{
			Map<String, String> map = listOfmap.get( i );
			String producttype = ExcelHolder.getKey( map, "ProductType" );
			String productBundleNm = ExcelHolder.getKey( map, "BundleName" );
			if ( isproductBundlePresent( producttype, productBundleNm ) )
			{
				PBundleDrillDownActionImpl pBundleDrillDownActionImpl = new PBundleDrillDownActionImpl();
				ProductBundleDetailImpl productBundleDetailImpl = new ProductBundleDetailImpl( map );
				int row = GridHelper.getRowNumber( "SearchGrid", productBundleNm, "Name" );
				GenericHelper.waitForLoadmask( searchScreenWaitSec );
				GridHelper.clickRow( "SearchGrid", row, "Name" );
				GenericHelper.waitForLoadmask( searchScreenWaitSec );
				pBundleDrillDownActionImpl.clickOnAction( "Common Tasks", "Edit", "Status" );
				productBundleDetailImpl.modifyBasicDetails( productBundleNm );
				productBundleDetailImpl.modifyVersionProperties();
				ButtonHelper.clickIfEnabled( "OKButton" );
				waitForPopupFieldNameToDisappear( "Status" );
				Log4jHelper.logInfo( "'Product Bundle' is successfully updated with name " + productBundleNm );
			}
			else
				Log4jHelper.logInfo( "Product Bundle name is not found: " + productBundleNm );

		}

	}

	/*
	 * This method is for modify Product Instance
	 */
	private void modifyProductInstance( String testCaseNm ) throws Exception
	{
		psGenericHelper.waitforHeaderElement( "Product Bundle" );
		List<Map<String, String>> listOfmap = ProductUtil.getTestCaseMap( testCaseNm, workBookName, sheetName );
		for ( int i = 0; i < listOfmap.size(); i++ )
		{
			Map<String, String> map = listOfmap.get( i );
			String account = ExcelHolder.getKey( map, "AccountName" );
			String billProfile = ExcelHolder.getKey( map, "BillProfile" );
			String productBundleNm = ExcelHolder.getKey( map, "ProductBundleName" );
			String productBundleVersion = ExcelHolder.getKey( map, "ProductBundleVersion" );
			String productInstance = ExcelHolder.getKey( map, "Name" );

			if ( isproductInstancePresent( account, billProfile, productBundleNm, productBundleVersion, productInstance ) )
			{
				PBundleDrillDownActionImpl pBundleDrillDownActionImpl = new PBundleDrillDownActionImpl();
				ProductInstanceImpl productInstanceImpl = new ProductInstanceImpl( map );
				int row = GridHelper.getRowNumber( "SearchGrid", productInstance, "Name" );
				GenericHelper.waitForLoadmask( searchScreenWaitSec );
				GridHelper.clickRow( "SearchGrid", row, "Name" );
				GenericHelper.waitForLoadmask( searchScreenWaitSec );
				pBundleDrillDownActionImpl.clickOnAction( "Common Tasks", "Edit", "Country" );
				productInstanceImpl.modifyProductInstance();
				ButtonHelper.clickIfEnabled( "OKButton" );
				waitForPopupFieldNameToDisappear( "Country" );
				Log4jHelper.logInfo( "'Product Insance' is successfully updated with name " + productInstance );
			}
			else
				Log4jHelper.logInfo( "Product Insance name is not found: " + productInstance );

		}

	}

	/*
	 * This method is for modify Product Instance Item
	 */
	private void modifyProductInstItem( String testCaseNm ) throws Exception
	{
		psGenericHelper.waitforHeaderElement( "Product Definition" );
		List<Map<String, String>> listOfmap = ProductUtil.getTestCaseMap( testCaseNm, workBookName, sheetName );
		for ( int i = 0; i < listOfmap.size(); i++ )
		{
			Map<String, String> map = listOfmap.get( i );
			String productInstance = ExcelHolder.getKey( map, "ProductInstanceName" );
			String productInstanceItem = ExcelHolder.getKey( map, "Name" );

			if ( isproductInstItemPresent( productInstance, productInstanceItem ) )
			{
				PBundleDrillDownActionImpl pBundleDrillDownActionImpl = new PBundleDrillDownActionImpl();
				ProductInstItemImpl productInstanceImpl = new ProductInstItemImpl( map );
				int row = GridHelper.getRowNumber( "SearchGrid", productInstanceItem, "Name" );
				GenericHelper.waitForLoadmask( searchScreenWaitSec );
				GridHelper.clickRow( "SearchGrid", row, "Name" );
				GenericHelper.waitForLoadmask( searchScreenWaitSec );
				pBundleDrillDownActionImpl.clickOnAction( "Common Tasks", "Edit", "Order  Date" );
				productInstanceImpl.modifyProductInstItem();
				ButtonHelper.clickIfEnabled( "OKButton" );
				waitForPopupFieldNameToDisappear( "Order  Date" );
				Log4jHelper.logInfo( "'Product Insance Item' is successfully updated with name " + productInstanceItem );
			}
			else
				Log4jHelper.logInfo( "Product Insance Item name is not found: " + productInstanceItem );

		}

	}

	/*
	 * This method is for modify One Off Instance Item
	 */
	private void modifyOneOffItemOnstance( String testCaseNm ) throws Exception
	{
		psGenericHelper.waitforHeaderElement( "Type" );
		List<Map<String, String>> listOfmap = ProductUtil.getTestCaseMap( testCaseNm, workBookName, sheetName );
		for ( int i = 0; i < listOfmap.size(); i++ )
		{
			Map<String, String> map = listOfmap.get( i );
			String account = ExcelHolder.getKey( map, "AccountName" );
			String billProfile = ExcelHolder.getKey( map, "BillProfile" );
			String oneOffItemInstance = ExcelHolder.getKey( map, "Name" );

			if ( isOneOffItemInstPresent( account, billProfile, oneOffItemInstance ) )
			{
				PBundleDrillDownActionImpl pBundleDrillDownActionImpl = new PBundleDrillDownActionImpl();
				OneOffItemInstImpl oneOffItemInstImpl = new OneOffItemInstImpl( map );
				int row = GridHelper.getRowNumber( "SearchGrid", oneOffItemInstance, "Name" );
				GenericHelper.waitForLoadmask( searchScreenWaitSec );
				GridHelper.clickRow( "SearchGrid", row, "Name" );
				GenericHelper.waitForLoadmask( searchScreenWaitSec );
				pBundleDrillDownActionImpl.clickOnAction( "Common Tasks", "Edit", "Reason" );
				oneOffItemInstImpl.modifyOneOffInstItem();
				ButtonHelper.clickIfEnabled( "OKButton" );
				waitForPopupFieldNameToDisappear( "Reason" );
				Log4jHelper.logInfo( "'One Off Item Instance' is successfully updated with name " + oneOffItemInstance );
			}
			else
				Log4jHelper.logInfo( "'One Off Item Instance' name is not found: " + oneOffItemInstance );

		}

	}

	/*
	 * This method is for modify Recurring Instance Item
	 */
	private void modifyRecurringItemOnstance( String testCaseNm ) throws Exception
	{
		psGenericHelper.waitforHeaderElement( "Type" );
		List<Map<String, String>> listOfmap = ProductUtil.getTestCaseMap( testCaseNm, workBookName, sheetName );
		for ( int i = 0; i < listOfmap.size(); i++ )
		{
			Map<String, String> map = listOfmap.get( i );
			String account = ExcelHolder.getKey( map, "AccountName" );
			String billProfile = ExcelHolder.getKey( map, "BillProfile" );
			String recurringItemInstance = ExcelHolder.getKey( map, "Name" );

			if ( isRecurringItemInstPresent( account, billProfile, recurringItemInstance ) )
			{
				PBundleDrillDownActionImpl pBundleDrillDownActionImpl = new PBundleDrillDownActionImpl();
				RecurringItemInstImpl recurringItemInstImpl = new RecurringItemInstImpl( map );
				int row = GridHelper.getRowNumber( "SearchGrid", recurringItemInstance, "Name" );
				GenericHelper.waitForLoadmask( searchScreenWaitSec );
				GridHelper.clickRow( "SearchGrid", row, "Name" );
				GenericHelper.waitForLoadmask( searchScreenWaitSec );
				pBundleDrillDownActionImpl.clickOnAction( "Common Tasks", "Edit", "Cycle(s) in  Advance" );
				recurringItemInstImpl.modifyRecurringInstItem();
				ButtonHelper.clickIfEnabled( "OKButton" );
				waitForPopupFieldNameToDisappear( "Cycle(s) in  Advance" );
				Log4jHelper.logInfo( "'Recurring Item Instance' is successfully updated with name " + recurringItemInstance );
			}
			else
				Log4jHelper.logInfo( "'Recurring Item Instance' name is not found: " + recurringItemInstance );

		}

	}

	/*
	 * This method is for modify Product Instance Item member
	 */
	private void modifyProductInstItemMember( String testcaseNm ) throws Exception
	{
		if ( ValidationHelper.isNotEmpty( testcaseNm ) )
		{
			String testCasnmArr[] = psStringUtils.stringSplitFirstLevel( testcaseNm );
			for ( String testCase : testCasnmArr )
			{
				if ( testCase.contains( "Recurring" ) )
					modifyRecurringItemOnstance( testCase );
				else if ( testCase.contains( "OneOff" ) )
					modifyOneOffItemOnstance( testCase );
				else
					Log4jHelper.logInfo( "The given testcasenm is invalid " + testCase );
			}
		}
	}

	/*
	 * This method is for check Product Bundle  present
	 */
	private boolean isproductBundlePresent( String productType, String bundleName ) throws Exception
	{
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		PSSearchGridHelper.gridFilterSearchWithComboBox( "productType_gwt_uid_", productType, "Product Type" );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		return psGenericHelper.isGridTextValuePresent( "PS_Detail_PBundleDrillDown_bundle_name_textXpath", bundleName, "Name" );

	}

	/*
	 * This method is for check Product Instance  present
	 */
	private boolean isproductInstancePresent( String account, String billProfile, String productBundleNm, String productBundleVersion, String productInstanceNm ) throws Exception
	{
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		advanceSearchHelpOb.accountAdvanceSearch( "Account", account );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		psGenericHelper.waitforHeaderElement( "Product Bundle" );
		advanceSearchHelpOb.billProfileAdvanceSearch( "Bill Profile", billProfile );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		psGenericHelper.waitforHeaderElement( "Product Bundle" );
		ButtonHelper.click( "SearchButton" );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		productBundleAdvanceSearchFilter( "SearchGrid", "Product Bundle", productBundleNm, productBundleVersion );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		return psGenericHelper.isGridTextValuePresent( "PS_Detail_prodInst_Instancename_textID", productInstanceNm, "Name" );

	}

	/*
	 * This method is for check Product Instance Item  present
	 */
	private boolean isproductInstItemPresent( String productInstance, String productInstIem ) throws Exception
	{
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		advanceSearchHelpOb.productInstanceAdvanceSearch( "Product Instance", productInstance );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		psGenericHelper.waitforHeaderElement( "Product Definition" );
		ButtonHelper.click( "SearchButton" );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		psGenericHelper.waitforHeaderElement( "Product Definition" );
		return psGenericHelper.isGridTextValuePresent( "PS_Detail_prodInst_InstItemName_textID", productInstIem, "Name" );

	}

	/*
	 * This method is for check OneOff Item Instance  present
	 */
	private boolean isOneOffItemInstPresent( String account, String billProfile, String oneOffItemInstance ) throws Exception
	{
		productInstItemMemberFilter( account, billProfile );
		return psGenericHelper.isGridTextValuePresent( "PS_Detail_PBundleDrillDown_prodItemInstMember_name_textId", oneOffItemInstance, "Name" );
	}

	/*
	 * This method is for check Recurring Item Instance  present
	 */
	private boolean isRecurringItemInstPresent( String account, String billProfile, String recurringItemInstance ) throws Exception
	{
		productInstItemMemberFilter( account, billProfile );
		return psGenericHelper.isGridTextValuePresent( "PS_Detail_PBundleDrillDown_prodItemInstMember_name_textId", recurringItemInstance, "Name" );
	}

	/*
	 * This method is for product instance item member filter
	 */
	private void productInstItemMemberFilter( String account, String billProfile ) throws Exception
	{
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		GridFilterSearchHelper.gridFilterAdvancedSearch( "billProfile$account", account, "Account" );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		psGenericHelper.waitforHeaderElement( "Type" );
		gridFilterSearchHelper.billProfileAdvanceFilter( "searchGrid", "Bill Profile", billProfile );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		psGenericHelper.waitforHeaderElement( "Type" );
		ButtonHelper.click( "SearchButton" );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		psGenericHelper.waitforHeaderElement( "Type" );

	}

	/*
	 * This method is for wait for popup filed name 
	 */
	public static void waitForPopupFieldName( String popupFieldName ) throws Exception
	{
		String xpath = "//*[@id='window-scroll-panel']//div[text()='" + popupFieldName + "']";
		ElementHelper.waitForElement( xpath, searchScreenWaitSec );
	}

	/*
	 * This method is for wait for popup filed name  to disappear
	 */
	public static void waitForPopupFieldNameToDisappear( String popupFieldName ) throws Exception
	{
		String xpath = "//*[@id='window-scroll-panel']//div[text()='" + popupFieldName + "']";
		ElementHelper.waitForElementToDisappear( xpath, searchScreenWaitSec );
	}

	/*
	 * This method is for product bundle advance search filter 
	 */
	private void productBundleAdvanceSearchFilter( String gridId, String filterHeaderName, String productBundleNm, String productBundleVersion ) throws Exception
	{
		SearchHelper searchHelper = new SearchHelper();
		String advanceSearchBtn = GenericHelper.getORProperty( "PS_suggestionFilterAdvanceTextXpath" ).replace( "filterTxtId", "productDfnVersion" );
		String bundleSearchBtnLocator = GenericHelper.getORProperty( "PS_suggestionFilterSearchBtnXpath" ).replace( "filterTxtId", "productDfnVersion" );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		searchHelper.clickFilterIcon( gridId, filterHeaderName );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		ElementHelper.click( advanceSearchBtn );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		Thread.sleep( 1000 );
		psGenericHelper.waitforPopupHeaderElement( "Code" );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		SearchGridHelper.gridFilterSearchWithTextBox( "PS_Detail_prodInst_BundleName_textID", productBundleNm, "Name" );
		boolean isProductBundlePresent = GridHelper.isValuePresent( "Detail_popUpWindowId", "SearchGrid", productBundleNm, "Name" );
		assertTrue( isProductBundlePresent, "Product Bundle with name :'" + productBundleNm + "'  is not found in 'Product Bundle Search' popupScreen " );
		int row = getRowOfProductBundle( productBundleNm, productBundleVersion );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		GridHelper.clickRow( "Detail_popUpWindowId", "SearchGrid", row, "Code" );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		ButtonHelper.clickIfEnabled( "Detail_popUpWindowId", "OKButton" );
		GenericHelper.waitForLoadmask();
		ElementHelper.waitForElement( bundleSearchBtnLocator, searchScreenWaitSec );
		ButtonHelper.click( bundleSearchBtnLocator );
		GenericHelper.waitForLoadmask();

	}

	/*Method to get row of product bundle*/
	private int getRowOfProductBundle( String productBundleNm, String version ) throws Exception
	{

		int row = 0;
		if ( GridHelper.getRowCount( "Detail_popUpWindowId", "SearchGrid" ) > 1 && ValidationHelper.isNotEmpty( version ) )
			row = GridHelper.getRowNumber( "Detail_popUpWindowId", "SearchGrid", version, "Version" );
		else
			row = GridHelper.getRowNumber( "Detail_popUpWindowId", "SearchGrid", productBundleNm, "Name" );
		assertTrue( row <= GridHelper.getRowCount( "Detail_popUpWindowId", "SearchGrid" ), "This row is not found in Grid :" + row );
		return row;

	}

}
