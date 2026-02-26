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

public class ImfCurrency extends PSAcceptanceTest
{
	protected ExcelReader excelData = new ExcelReader();
	protected Map<String, ArrayList<String>> imfCurrencyExcelMap = null;
	protected Map<String, String> imfCurrencyMap = null;
	protected ExcelHolder excelHolderObj = null;
	protected String path;
	protected String workBookName;
	protected String sheetName;
	protected String testCaseName;
	protected int occurence;
	protected int colSize;
	protected String clientPartition;
	protected String colmHdrs;
	protected String currency;
	protected String imfCurrencyName;

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
	public ImfCurrency( String path, String workBookName, String sheetName, String testCaseName ) throws Exception
	{
		this.path = path;
		this.workBookName = workBookName;
		this.sheetName = sheetName;
		this.testCaseName = testCaseName;
		imfCurrencyExcelMap = excelData.readDataByColumn( path, workBookName, sheetName, testCaseName );
		excelHolderObj = new ExcelHolder( imfCurrencyExcelMap );
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
	public ImfCurrency( String path, String workBookName, String sheetName, String testCaseName, int occurence ) throws Exception
	{
		this.path = path;
		this.workBookName = workBookName;
		this.sheetName = sheetName;
		this.testCaseName = testCaseName;
		this.occurence = occurence;
		imfCurrencyExcelMap = excelData.readDataByColumn( path, workBookName, sheetName, testCaseName, occurence );
		excelHolderObj = new ExcelHolder( imfCurrencyExcelMap );
		colSize = excelHolderObj.totalColumns();
	}

	/*
	 * This method is for initialize variable
	 */
	private void initializeVariable( Map<String, String> map ) throws Exception
	{
		currency = ExcelHolder.getKey( map, "Currency" );
		imfCurrencyName = ExcelHolder.getKey( map, "IMFCurrencyName" );
		clientPartition = ExcelHolder.getKey( map, "Partition" );
	}

	/*
	 * This method is for 'IMF Currency' screen common method
	 */
	private void imfCurrencyScreen() throws Exception
	{
		NavigationHelper.navigateToReferenceTable( "IMF Currency" );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		imfCurrencyMap = excelHolderObj.dataMap( index );
		ButtonHelper.click( "SearchButton" );
		psGenericHelper.waitforHeaderElement( "IMF Currency Name" );
	}

	/*
	 * This method is for 'IMF Currency' screen column validation
	 */
	public void imfCurrencyColumsValidation() throws Exception
	{
		try
		{
			for ( index = 0; index < colSize; index++ )
			{
				imfCurrencyScreen();
				colmHdrs = ExcelHolder.getKey( imfCurrencyMap, "SearchScreenColumns" );
				psGenericHelper.screenColumnValidation( "IMF Currency Name", colmHdrs, "IMF Currency" );
			}
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}

	}

	/*
	 * This method is for 'IMF Currency'  creation
	 */
	public void imfCurrencyCreation() throws Exception
	{
		try
		{
			for ( index = 0; index < colSize; index++ )
			{
				imfCurrencyScreen();
				initializeVariable( imfCurrencyMap );
				boolean isImfCurrencyPresent = psGenericHelper.isDataPresent( imfCurrencyName, "IMF Currency Name" );
				if ( !isImfCurrencyPresent )
				{
					psActionImpl.clickNewAction( clientPartition, "PS_Detail_imfCurrency_detailXpath" );
					configureImfCurrency();
					clickOnSave();
					Log4jHelper.logInfo( "'IMF Currency' is successfully created with  Name:- '" + imfCurrencyName );

				}
				else

					Log4jHelper.logInfo( "'IMF Currency' is already avilable with  name:- '" + imfCurrencyName );
			}
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}

	}

	/*
	 * This method is for 'IMF Currency'  Edit
	 */
	public void imfCurrencyEdit() throws Exception
	{
		try
		{
			for ( index = 0; index < colSize; index++ )
			{
				imfCurrencyScreen();
				initializeVariable( imfCurrencyMap );
				boolean isImfCurrencyPresent = psGenericHelper.isDataPresent( imfCurrencyName, "IMF Currency Name" );
				if ( isImfCurrencyPresent )
				{
					GridHelper.clickRow( "SearchGrid", imfCurrencyName, "IMF Currency Name" );
					GenericHelper.waitForLoadmask( searchScreenWaitSec );
					psActionImpl.clickOnAction( "Common Tasks", "Edit", "PS_Detail_imfCurrency_detailXpath" );
					modifyImfCurrency();
					clickOnSave();
					Log4jHelper.logInfo( "'IMF Currency' is successfully updated with  name:- '" + imfCurrencyName );

				}
				else

					Log4jHelper.logInfo( "'IMF Currency' is not avilable with  name:- '" + imfCurrencyName );
			}
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}

	}

	/* This method is used 
	 * configure imf Currency
	 */
	private void configureImfCurrency() throws Exception
	{

		ComboBoxHelper.select( "PS_Detail_imfCurrency_currency_comboId", currency );
		TextBoxHelper.type( "PS_Detail_imfCurrency_imfCurrencyName_txtId", imfCurrencyName );

	}

	/* This method is used 
	 * modify imf Currency
	 */
	private void modifyImfCurrency() throws Exception
	{
		assertEquals( TextBoxHelper.getValue( "PS_Detail_imfCurrency_imfCurrencyName_txtId" ), imfCurrencyName, "IMF Currency Name is not matched" );
		ProductUtil.modifyComboBox( "PS_Detail_imfCurrency_currency_comboId", currency );

	}

	/* This method is used to click on save button*/
	private void clickOnSave() throws Exception
	{
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		psGenericHelper.detailSaveWithRetry( "PS_Detail_imfCurrency_save_btnId", imfCurrencyName, "IMF Currency Name" );
	}

}
