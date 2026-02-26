package com.subex.rocps.automation.helpers.application.products;

import java.util.ArrayList;
import java.util.Map;

import com.subex.rocps.automation.helpers.application.genericHelpers.PSGenericHelper;
import com.subex.rocps.automation.helpers.application.partnerConfiguration.account.AccountActionImpl;
import com.subex.rocps.automation.helpers.application.partnerConfiguration.account.AccountSearchImpl;
import com.subex.rocps.automation.helpers.application.products.productBundles.ProductBundleDetailImpl;
import com.subex.rocps.automation.helpers.application.products.productInstance.ProductInstanceActionImpl;
import com.subex.rocps.automation.helpers.application.products.productInstance.ProductInstanceDetail;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;
import com.subex.rocps.automation.utils.ExcelHolder;
import com.subex.rocps.automation.utils.PSStringUtils;

import com.subex.automation.helpers.application.NavigationHelper;
import com.subex.automation.helpers.component.ButtonHelper;
import com.subex.automation.helpers.component.GenericHelper;
import com.subex.automation.helpers.component.GridHelper;
import com.subex.automation.helpers.data.DateHelper;
import com.subex.automation.helpers.file.ExcelReader;
import com.subex.automation.helpers.report.Log4jHelper;
import com.subex.automation.helpers.util.FailureHelper;

public class ProductInstanceHelper extends PSAcceptanceTest
{

	protected ExcelReader excelData = new ExcelReader();
	protected Map<String, ArrayList<String>> productInstanceExcelMap = null;
	protected Map<String, String> productInstanceMap = null;
	protected ExcelHolder excelHolderObj = null;
	protected String path;
	protected String workBookName;
	protected static String bookName;
	protected String sheetName;
	protected static String sheetNm;
	protected String testCaseName;
	protected int occurence;
	protected String clientPartition;
	protected String colmHdrs;
	protected String accountName;
	protected String billProfile;
	protected String productInstanceNm;
	protected int colSize;
	protected int index;
	PSGenericHelper psGenericHelper = new PSGenericHelper();
	PSStringUtils psStringUtils = new PSStringUtils();
	ProductInstanceActionImpl productInstActImpOb=new ProductInstanceActionImpl();

	public ProductInstanceHelper()
	{
		
	}

	/**Constructor : Initializing the excel without occurrence
	 * @param path
	 * @param workBookName
	 * @param sheetName
	 * @param testCaseName
	 * @throws Exception 
	 */
	public ProductInstanceHelper( String path, String workBookName, String sheetName, String testCaseName ) throws Exception
	{

		this.path = path;
		this.workBookName = workBookName;
		this.sheetName = sheetName;
		this.testCaseName = testCaseName;
		productInstanceExcelMap = excelData.readDataByColumn( path, workBookName, sheetName, testCaseName );
		excelHolderObj = new ExcelHolder( productInstanceExcelMap );
		colSize = excelHolderObj.totalColumns();
	}

	/**Constructor : Initializing the excel with occurrence
	 * @param path
	 * @param workBookName
	 * @param sheetName
	 * @param testCaseName
	 * @param occurence
	 * @throws Exception 
	 */
	public ProductInstanceHelper( String path, String workBookName, String sheetName, String testCaseName, int occurence ) throws Exception
	{

		this.path = path;
		this.workBookName = workBookName;
		this.sheetName = sheetName;
		this.testCaseName = testCaseName;
		this.occurence = occurence;
		productInstanceExcelMap = excelData.readDataByColumn( path, workBookName, sheetName, testCaseName, occurence );
		excelHolderObj = new ExcelHolder( productInstanceExcelMap );
		colSize = excelHolderObj.totalColumns();
	}

	/*
	 * This method is for initialize variable
	 */
	private void initializeVariable( Map<String, String> map ) throws Exception
	{
		productInstanceNm = ExcelHolder.getKey( map, "Name" );
		clientPartition = ExcelHolder.getKey( map, "Partition" );

	}
	/*
	 * This method is for Product Instance screen common method
	 */
	private void productInstanceScreen( Map<String, String> map ) throws Exception
	{
		NavigationHelper.navigateToScreen( "Account" );
		psGenericHelper.waitforHeaderElement( "Account Name" );
		accountName = map.get( "AccountName" );
		billProfile = ExcelHolder.getKey( map, "BillProfile" );
		AccountSearchImpl accSearchObj = new AccountSearchImpl();
		AccountActionImpl accActionObj = new AccountActionImpl();
		boolean isAccountPresent = psGenericHelper.isGridTextValuePresent( "accountName_Detail", accountName, "Account Name" );
		assertTrue( isAccountPresent, "Account is not found with name: -" +accountName);
		GridHelper.clickRow( "SearchGrid", accountName, "Account Name" );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		String billProfileName = accSearchObj.retriveBillProfileValue( billProfile, accountName );
		accActionObj.viewProductAction( billProfileName );
		ButtonHelper.click( "ClearButton" );
		psGenericHelper.waitforHeaderElement( "Product Bundle" );

	}

	/*
	 * This method is for Product Instance screen column validation
	 */
	public void productInstanceColumsValidation() throws Exception
	{
		try
		{
			for ( index = 0; index < colSize; index++ )
			{
				productInstanceMap = excelHolderObj.dataMap( index );
				productInstanceScreen(productInstanceMap);
				colmHdrs = ExcelHolder.getKey( productInstanceMap, "SearchScreenColumns" );
				ArrayList<String> excelColumnNames = new ArrayList<String>();
				String productInstanceGridColumnsArr[] = psStringUtils.stringSplitFirstLevel( colmHdrs );
				for ( String column : productInstanceGridColumnsArr )
					excelColumnNames.add( column );
				psGenericHelper.totalColumns( excelColumnNames );
				Log4jHelper.logInfo( "'Product Instance screen' Columns are validated successfully" );
			}
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}

	}
	
	/*
	 * This method is for Product Instance creation with all mandatory product Items
	 */
	public void createProductInstWithMandatory() throws Exception
	{
		try
		{
			for ( index = 0; index < colSize; index++ )
			{
				productInstanceMap = excelHolderObj.dataMap( index );
				productInstanceScreen(productInstanceMap);
				initializeVariable( productInstanceMap );
				bookName=workBookName;
				sheetNm=sheetName;
				boolean isProductInstancePresent = psGenericHelper.isGridTextValuePresent( "PS_Detail_prodInst_Instancename_textID", productInstanceNm, "Name" );
				if ( !isProductInstancePresent )
				{
					productInstActImpOb.clickNewAction( clientPartition );
					ProductInstanceDetail productInstanceDetailOb=new ProductInstanceDetail( productInstanceMap );
					productInstanceDetailOb.createProductInstWithMandatory();
					ButtonHelper.click( "ClearButton" );
					psGenericHelper.waitforHeaderElement( "Product Bundle" );
					isProductInstancePresent = psGenericHelper.isGridTextValuePresent( "PS_Detail_prodInst_Instancename_textID", productInstanceNm, "Name" );
					assertTrue( isProductInstancePresent, "Product Instance failed to save with name: '"+productInstanceNm );
					Log4jHelper.logInfo( "'Product Instance' is successfully created with name " + productInstanceNm );
				}
				else
				{
					Log4jHelper.logInfo( "'Product Instance is already avilable with name '" + productInstanceNm );
				}

			}
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}

	}
	
	/*
	 * This method is for Product Instance creation without mandatory product Items
	 */
	public void createPrInstanceWithoutMandatory() throws Exception
	{
		try
		{
			for ( index = 0; index < colSize; index++ )
			{
				productInstanceMap = excelHolderObj.dataMap( index );
				productInstanceScreen(productInstanceMap);
				initializeVariable( productInstanceMap );
				bookName=workBookName;
				sheetNm=sheetName;
				boolean isProductInstancePresent = psGenericHelper.isGridTextValuePresent( "PS_Detail_prodInst_Instancename_textID", productInstanceNm, "Name" );
				if ( !isProductInstancePresent )
				{
					productInstActImpOb.clickNewAction( clientPartition );
					ProductInstanceDetail productInstanceDetailOb=new ProductInstanceDetail( productInstanceMap );
					productInstanceDetailOb.createProductInstWithoutMandatory();
					Log4jHelper.logInfo( "'Product Instance' is successfully created with name " + productInstanceNm );
			
				}
				else
				{
					Log4jHelper.logInfo( "'Product Instance is already avilable with name '" + productInstanceNm );
				}

			}
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}

	}
	
	
	public String getWorkbookName()
	{
		return bookName;
	}
	
	public String getSheetName()
	{
		return sheetNm;
	}

}
