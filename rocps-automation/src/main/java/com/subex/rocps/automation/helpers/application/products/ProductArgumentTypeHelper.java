package com.subex.rocps.automation.helpers.application.products;

import java.util.ArrayList;
import java.util.Map;

import com.subex.rocps.automation.helpers.application.genericHelpers.PSGenericHelper;
import com.subex.rocps.automation.helpers.application.products.productArgType.ProductArgTypeActionImpl;
import com.subex.rocps.automation.helpers.application.products.productArgType.ProductArgTypeDetailHelper;
import com.subex.rocps.automation.helpers.application.products.productBundles.ProductBundleDetailImpl;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;
import com.subex.rocps.automation.utils.ExcelHolder;
import com.subex.rocps.automation.utils.PSStringUtils;

import com.subex.automation.helpers.application.NavigationHelper;
import com.subex.automation.helpers.component.ButtonHelper;
import com.subex.automation.helpers.component.GenericHelper;
import com.subex.automation.helpers.component.GridHelper;
import com.subex.automation.helpers.file.ExcelReader;
import com.subex.automation.helpers.report.Log4jHelper;
import com.subex.automation.helpers.util.FailureHelper;

public class ProductArgumentTypeHelper extends PSAcceptanceTest
{
	protected ExcelReader excelData = new ExcelReader();
	protected Map<String, ArrayList<String>> productArgTypeExcelMap = null;
	protected Map<String, String> productArgTypeMap = null;
	protected ExcelHolder excelHolderObj = null;
	protected String path;
	protected String workBookName;
	protected String sheetName;
	protected String testCaseName;
	protected int occurence;
	protected String clientPartition;
	protected String productArgTypeName;
	protected String colmHdrs;
	protected int colSize;
	protected int index;
	PSGenericHelper psGenericHelper = new PSGenericHelper();
	PSStringUtils psStringUtils = new PSStringUtils();
	ProductArgTypeActionImpl productArgTypeActionObj = new ProductArgTypeActionImpl();

	/**Constructor : Initializing the excel without occurrence
	 * @param path
	 * @param workBookName
	 * @param sheetName
	 * @param testCaseName
	 * @throws Exception 
	 */
	public ProductArgumentTypeHelper( String path, String workBookName, String sheetName, String testCaseName ) throws Exception
	{

		this.path = path;
		this.workBookName = workBookName;
		this.sheetName = sheetName;
		this.testCaseName = testCaseName;
		productArgTypeExcelMap = excelData.readDataByColumn( path, workBookName, sheetName, testCaseName );
		excelHolderObj = new ExcelHolder( productArgTypeExcelMap );
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
	public ProductArgumentTypeHelper( String path, String workBookName, String sheetName, String testCaseName, int occurence ) throws Exception
	{

		this.path = path;
		this.workBookName = workBookName;
		this.sheetName = sheetName;
		this.testCaseName = testCaseName;
		this.occurence = occurence;
		productArgTypeExcelMap = excelData.readDataByColumn( path, workBookName, sheetName, testCaseName, occurence );
		excelHolderObj = new ExcelHolder( productArgTypeExcelMap );
		colSize = excelHolderObj.totalColumns();
	}

	/*
	 * This method is for initialize variable
	 */
	private void initializeVariable( Map<String, String> map ) throws Exception
	{
		productArgTypeName = ExcelHolder.getKey( map, "Name" );
		clientPartition = ExcelHolder.getKey( map, "Partition" );

	}

	/*
	 * This method is for Product Argument type screen common method
	 */
	private void productArgTypeScreen() throws Exception
	{
		NavigationHelper.navigateToScreen( "Product Argument Type" );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		productArgTypeMap = excelHolderObj.dataMap( index );
		ButtonHelper.click( "ClearButton" );
		psGenericHelper.waitforHeaderElement( "Type" );
	}

	/*
	 * This method is for Product Argument Type screen column validation
	 */
	public void productArgumentTypeColumnValidation() throws Exception
	{
		try
		{
			for ( index = 0; index < colSize; index++ )
			{
				productArgTypeScreen();
				colmHdrs = ExcelHolder.getKey( productArgTypeMap, "SearchScreenColumns" );
				ArrayList<String> excelColumnNames = new ArrayList<String>();
				String productArgumentTypeGridColumnsArr[] = psStringUtils.stringSplitFirstLevel( colmHdrs );
				for ( String column : productArgumentTypeGridColumnsArr )
					excelColumnNames.add( column );
				psGenericHelper.totalColumns( excelColumnNames );
				Log4jHelper.logInfo( "'Product Argument Type screen' Columns are validated successfully" );
			}
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}

	}

	/*
	 * This method is for Product Argument Type creation
	 */
	public void createProductArgType() throws Exception
	{
		try
		{
			for ( index = 0; index < colSize; index++ )
			{
				productArgTypeScreen();
				initializeVariable( productArgTypeMap );
				boolean isProductArgTypePresent = psGenericHelper.isGridTextValuePresent( "PS_Detail_prodArgType_name_textID", productArgTypeName, "Name" );
				if ( !isProductArgTypePresent )
				{
					productArgTypeActionObj.clickNewAction( clientPartition );
					ProductArgTypeDetailHelper productArgTypeDetailOb = new ProductArgTypeDetailHelper( productArgTypeMap );
					productArgTypeDetailOb.createProductArgType();
					productArgTypeDetailOb.saveProductArgType( productArgTypeName );
					Log4jHelper.logInfo( "'Product Argument Type' is successfully created with name " + productArgTypeName );
				}
				else
				{
					Log4jHelper.logInfo( "'Product Argument Type is already avilable with name '" + productArgTypeName );
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
	 * This method is for Product Argument Type 'Edit ' Action
	 */
	public void editProdutArgTypeAction() throws Exception
	{
		try
		{
			for ( index = 0; index < colSize; index++ )
			{
				productArgTypeScreen();
				initializeVariable( productArgTypeMap );
				boolean isProductArgTypePresent = psGenericHelper.isGridTextValuePresent( "PS_Detail_prodArgType_name_textID", productArgTypeName, "Name" );

				if ( isProductArgTypePresent )
				{
					int row = GridHelper.getRowNumber( "SearchGrid", productArgTypeName, "Name" );
					GridHelper.clickRow( "SearchGrid", row, "Name" );
					productArgTypeActionObj.clickOnAction( "Common Tasks", "Edit" );
					ProductArgTypeDetailHelper productArgTypeDetailOb = new ProductArgTypeDetailHelper( productArgTypeMap );
					productArgTypeDetailOb.modifyProductArgType();
					productArgTypeDetailOb.saveProductArgType( productArgTypeName );
					Log4jHelper.logInfo( "'Product Argument Type' is successfully updated with name " + productArgTypeName );
				}
				else
				{
					Log4jHelper.logInfo( "'Product Argument Type is not avilable with name '" + productArgTypeName );
				}

			}
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}

	}

	// Method: for Product Argument Type deletion action
	public void producArgumentTypeDelete() throws Exception
	{
		productArgTypeScreen();
		initializeVariable( productArgTypeMap );
		ButtonHelper.click( "ClearButton" );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		boolean isProductArgTypePresent = psGenericHelper.isGridTextValuePresent( "PS_Detail_prodArgType_name_textID", productArgTypeName, "Name" );
		assertTrue( isProductArgTypePresent, "Product Argument Type is not available in the screen with  name: -'" + productArgTypeName );
		psGenericHelper.clickDeleteOrUnDeleteAction( productArgTypeName, "Name", "Delete" );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		psGenericHelper.selectPartitionFilter( clientPartition, "Deleted Items" );
		isProductArgTypePresent = psGenericHelper.isGridTextValuePresent( "PS_Detail_prodArgType_name_textID", productArgTypeName, "Name" );
		assertTrue( isProductArgTypePresent, productArgTypeName + " is not present" );
		Log4jHelper.logInfo( "Product Argument Type is deleted successfully with the value-:'" + productArgTypeName );

	}

	// Method: for  Product Argument Type Undeletion action
	public void productArgumentTypeUnDelete() throws Exception
	{

		productArgTypeScreen();
		initializeVariable( productArgTypeMap );
		ButtonHelper.click( "ClearButton" );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		psGenericHelper.selectPartitionFilter( clientPartition, "Deleted Items" );
		boolean isProductArgTypePresent = psGenericHelper.isGridTextValuePresent( "PS_Detail_prodArgType_name_textID", productArgTypeName, "Name" );
		assertTrue( isProductArgTypePresent, "Product Argument Type is not available in the screen with  name: -'" + productArgTypeName );
		psGenericHelper.clickDeleteOrUnDeleteAction( productArgTypeName, "Name", "Undelete" );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		psGenericHelper.selectPartitionFilter( clientPartition, "Non Deleted Items" );
		isProductArgTypePresent = psGenericHelper.isGridTextValuePresent( "PS_Detail_prodArgType_name_textID", productArgTypeName, "Name" );
		assertTrue( isProductArgTypePresent, productArgTypeName + " is not present" );
		Log4jHelper.logInfo( "Product Argument Type is undeleted successfully with the value:  '" + productArgTypeName );

	}

}
