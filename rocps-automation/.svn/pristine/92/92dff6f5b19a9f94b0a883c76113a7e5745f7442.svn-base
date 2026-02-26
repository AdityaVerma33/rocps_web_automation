package com.subex.rocps.automation.helpers.application.referenceTable;

import java.util.ArrayList;
import java.util.Map;

import com.subex.rocps.automation.helpers.application.genericHelpers.PSActionImpl;
import com.subex.rocps.automation.helpers.application.genericHelpers.PSGenericHelper;
import com.subex.rocps.automation.helpers.application.products.productBundles.product.ProductUtil;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;
import com.subex.rocps.automation.utils.ExcelHolder;
import com.subex.rocps.automation.utils.PSStringUtils;

import com.subex.automation.helpers.application.NavigationHelper;
import com.subex.automation.helpers.component.ButtonHelper;
import com.subex.automation.helpers.component.ComboBoxHelper;
import com.subex.automation.helpers.component.GenericHelper;
import com.subex.automation.helpers.component.GridHelper;
import com.subex.automation.helpers.component.TextBoxHelper;
import com.subex.automation.helpers.file.ExcelReader;
import com.subex.automation.helpers.report.Log4jHelper;
import com.subex.automation.helpers.util.FailureHelper;

public class Currency extends PSAcceptanceTest
{
	protected ExcelReader excelData = new ExcelReader();
	protected Map<String, ArrayList<String>> CurrencyExcelMap = null;
	protected Map<String, String> CurrencyMap = null;
	protected ExcelHolder excelHolderObj = null;
	protected String path;
	protected String workBookName;
	protected String sheetName;
	protected String testCaseName;
	protected int occurence;
	protected int colSize;
	protected String clientPartition;
	protected String colmHdrs;
	protected String isoCode;
	protected String name;
	protected String culture;
	protected String symbol;
	protected int index;
	protected PSGenericHelper psGenericHelper = new PSGenericHelper();
	PSStringUtils psStringUtils = new PSStringUtils();
	PSActionImpl psActionImpl = new PSActionImpl();

	/**Constructor : Initializing the excel without occurrence
	 * @param path
	 * @param workBookName
	 * @param sheetName
	 * @param testCaseName
	 * @throws Exception 
	 */
	public Currency( String path, String workBookName, String sheetName, String testCaseName ) throws Exception
	{
		this.path = path;
		this.workBookName = workBookName;
		this.sheetName = sheetName;
		this.testCaseName = testCaseName;
		CurrencyExcelMap = excelData.readDataByColumn( path, workBookName, sheetName, testCaseName );
		excelHolderObj = new ExcelHolder( CurrencyExcelMap );
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
	public Currency( String path, String workBookName, String sheetName, String testCaseName, int occurence ) throws Exception
	{
		this.path = path;
		this.workBookName = workBookName;
		this.sheetName = sheetName;
		this.testCaseName = testCaseName;
		this.occurence = occurence;
		CurrencyExcelMap = excelData.readDataByColumn( path, workBookName, sheetName, testCaseName, occurence );
		excelHolderObj = new ExcelHolder( CurrencyExcelMap );
		colSize = excelHolderObj.totalColumns();
	}

	/*
	 * This method is for initialize variable
	 */
	private void initializeVariable( Map<String, String> map ) throws Exception
	{
		isoCode = ExcelHolder.getKey( map, "ISOCode" );
		name = ExcelHolder.getKey( map, "Name" );
		culture = ExcelHolder.getKey( map, "Culture" );
		symbol = ExcelHolder.getKey( map, "Symbol" );
		clientPartition = ExcelHolder.getKey( map, "Partition" );
	}

	/*
	 * This method is for 'Currency' screen common method
	 */
	private void currencyScreen() throws Exception
	{
		NavigationHelper.navigateToReferenceTable( "Currency" );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		CurrencyMap = excelHolderObj.dataMap( index );
		ButtonHelper.click( "SearchButton" );
		psGenericHelper.waitforHeaderElement( "Name" );
	}

	/*
	 * This method is for 'Currency' screen column validation
	 */
	public void currencyColumsValidation() throws Exception
	{
		try
		{
			for ( index = 0; index < colSize; index++ )
			{
				currencyScreen();
				colmHdrs = ExcelHolder.getKey( CurrencyMap, "SearchScreenColumns" );
				psGenericHelper.screenColumnValidation( "Name", colmHdrs, "Currency" );
			}
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}

	}

	/*
	 * This method is for 'Currency'  creation
	 */
	public void currencyCreation() throws Exception
	{
		try
		{
			for ( index = 0; index < colSize; index++ )
			{
				currencyScreen();
				initializeVariable( CurrencyMap );
				boolean iscurrencyPresent = isCurrencyPresent( name, "Name" );
				if ( !iscurrencyPresent )
				{
					psActionImpl.clickNewAction( clientPartition, "PS_Detail_currency_detailXpath" );
					configureCurrency();
					clickOnSave();
					Log4jHelper.logInfo( "'Currency' is successfully created with  Name:- '" + name );

				}
				else

					Log4jHelper.logInfo( "'Currency' is already avilable with  name:- '" + name );
			}
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}

	}

	private boolean isCurrencyPresent( String currency, String columnHeader ) throws Exception
	{
		TextBoxHelper.type( "curName", currency );
		ButtonHelper.click( "SearchButton" );
		psGenericHelper.waitforHeaderElement( "Name" );
		return GridHelper.isValuePresent( "SearchGrid", currency, columnHeader ) || GridHelper.getRowCount( "SearchGrid" )>0;
	}

	/*
	 * This method is for 'Currency'  Edit
	 */
	public void currencyEdit() throws Exception
	{
		try
		{
			for ( index = 0; index < colSize; index++ )
			{
				currencyScreen();
				initializeVariable( CurrencyMap );
				boolean iscurrencyPresent = isCurrencyPresent( name, "Name" );
				if ( iscurrencyPresent )
				{
					GridHelper.clickRow( "SearchGrid", name, "Name" );
					GenericHelper.waitForLoadmask( searchScreenWaitSec );
					psActionImpl.clickOnAction( "Common Tasks", "Edit", "PS_Detail_currency_detailXpath" );
					modifyCurrency();
					clickOnSave();
					Log4jHelper.logInfo( "'Currency' is successfully updated with  name:- '" + name );

				}
				else

					Log4jHelper.logInfo( "'Currency' is not avilable with  name:- '" + name );
			}
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}

	}

	/* This method is used 
	 * configure  Currency
	 */
	private void configureCurrency() throws Exception
	{

		TextBoxHelper.type( "PS_Detail_Currency_isoCode_txtId", isoCode );
		TextBoxHelper.type( "PS_Detail_Currency_name_txtId", name );
		TextBoxHelper.type( "PS_Detail_Currency_culture_txtId", culture );
		TextBoxHelper.type( "PS_Detail_Currency_symbol_txtId", symbol );

	}

	/* This method is used 
	 * modify  Currency
	 */
	private void modifyCurrency() throws Exception
	{
		assertEquals( TextBoxHelper.getValue( "PS_Detail_Currency_name_txtId" ), name, "Currency Name is not matched" );
		ProductUtil.modifyTextBox( "PS_Detail_Currency_isoCode_txtId", isoCode );
		ProductUtil.modifyTextBox( "PS_Detail_Currency_culture_txtId", culture );
		ProductUtil.modifyTextBox( "PS_Detail_Currency_symbol_txtId", symbol );

	}

	/* This method is used to click on save button*/
	private void clickOnSave() throws Exception
	{
		psGenericHelper.detailSave( "PS_Detail_Currency_save_btnId", name, "Name" );
	}

}
