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
import com.subex.automation.helpers.component.GenericHelper;
import com.subex.automation.helpers.component.GridHelper;
import com.subex.automation.helpers.component.TextBoxHelper;
import com.subex.automation.helpers.file.ExcelReader;
import com.subex.automation.helpers.report.Log4jHelper;
import com.subex.automation.helpers.util.FailureHelper;

public class RoamingChargeItem extends PSAcceptanceTest
{

	protected ExcelReader excelData = new ExcelReader();
	protected Map<String, ArrayList<String>> roamingChgItemExcelMap = null;
	protected Map<String, String> roamingChgItemMap = null;
	protected ExcelHolder excelHolderObj = null;
	protected String path;
	protected String workBookName;
	protected String sheetName;
	protected String testCaseName;
	protected int occurence;
	protected int colSize;
	protected String clientPartition;
	protected String colmHdrs;
	protected String code;
	protected String value;
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
	public RoamingChargeItem( String path, String workBookName, String sheetName, String testCaseName ) throws Exception
	{
		this.path = path;
		this.workBookName = workBookName;
		this.sheetName = sheetName;
		this.testCaseName = testCaseName;
		roamingChgItemExcelMap = excelData.readDataByColumn( path, workBookName, sheetName, testCaseName );
		excelHolderObj = new ExcelHolder( roamingChgItemExcelMap );
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
	public RoamingChargeItem( String path, String workBookName, String sheetName, String testCaseName, int occurence ) throws Exception
	{
		this.path = path;
		this.workBookName = workBookName;
		this.sheetName = sheetName;
		this.testCaseName = testCaseName;
		this.occurence = occurence;
		roamingChgItemExcelMap = excelData.readDataByColumn( path, workBookName, sheetName, testCaseName, occurence );
		excelHolderObj = new ExcelHolder( roamingChgItemExcelMap );
		colSize = excelHolderObj.totalColumns();
	}

	/*
	 * This method is for initialize variable
	 */
	private void initializeVariable( Map<String, String> map ) throws Exception
	{
		code = ExcelHolder.getKey( map, "Code" );
		value = ExcelHolder.getKey( map, "Value" );
		clientPartition = ExcelHolder.getKey( map, "Partition" );
	}

	/*
	 * This method is for 'Roaming Charged Item' screen common method
	 */
	private void roamingChgItemScreen() throws Exception
	{
		NavigationHelper.navigateToReferenceTable( "Roaming Charged Item" );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		roamingChgItemMap = excelHolderObj.dataMap( index );
		ButtonHelper.click( "SearchButton" );
		psGenericHelper.waitforHeaderElement( "Value" );
	}

	/*
	 * This method is for 'Roaming Charged Item' screen column validation
	 */
	public void roamingChgItemColumsValidation() throws Exception
	{
		try
		{
			for ( index = 0; index < colSize; index++ )
			{
				roamingChgItemScreen();
				colmHdrs = ExcelHolder.getKey( roamingChgItemMap, "SearchScreenColumns" );
				psGenericHelper.screenColumnValidation( "Value", colmHdrs, "Roaming Charged Item" );
			}
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}

	}

	/*
	 * This method is for 'Roaming Charged Item'  creation
	 */
	public void roamingChgItemCreation() throws Exception
	{
		try
		{
			for ( index = 0; index < colSize; index++ )
			{
				roamingChgItemScreen();
				initializeVariable( roamingChgItemMap );
				boolean isroamingChgItemPresent = psGenericHelper.isDataPresent( value, "Value" );
				if ( !isroamingChgItemPresent )
				{
					psActionImpl.clickNewAction( clientPartition, "PS_Detail_roamChgItem_detailXpath" );
					configureroamingChgItem();
					clickOnSave();
					Log4jHelper.logInfo( "'Roaming Charged Item' is successfully created with  Value:- '" + value );

				}
				else

					Log4jHelper.logInfo( "'Roaming Charged Item' is already avilable with  Value:- '" + value );
			}
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}

	}

	/*
	 * This method is for 'Roaming Charged Item'  Edit
	 */
	public void roamingChgItemEdit() throws Exception
	{
		try
		{
			for ( index = 0; index < colSize; index++ )
			{
				roamingChgItemScreen();
				initializeVariable( roamingChgItemMap );
				boolean isroamingChgItemPresent = psGenericHelper.isDataPresent( value, "Value" );
				if ( isroamingChgItemPresent )
				{
					GridHelper.clickRow( "SearchGrid", value, "Value" );
					GenericHelper.waitForLoadmask( searchScreenWaitSec );
					psActionImpl.clickOnAction( "Common Tasks", "Edit", "PS_Detail_roamChgItem_detailXpath" );
					modifyroamingChgItem();
					clickOnSave();
					Log4jHelper.logInfo( "'Roaming Charged Item' is successfully updated with  Value:- '" + value );

				}
				else

					Log4jHelper.logInfo( "'Roaming Charged Item' is not avilable with  Value:- '" + value );
			}
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}

	}

	/* This method is used 
	 * configure  Roaming Charged Item
	 */
	private void configureroamingChgItem() throws Exception
	{

		TextBoxHelper.type( "PS_Detail_roamChgItem_code_txtId", code );
		TextBoxHelper.type( "PS_Detail_roamChgItem_value_txtId", value );

	}

	/* This method is used 
	 * modify  Roaming Charged Item
	 */
	private void modifyroamingChgItem() throws Exception
	{
		assertEquals( TextBoxHelper.getValue( "PS_Detail_roamChgItem_value_txtId" ), value, "Roaming Charged Item Value is not matched" );
		ProductUtil.modifyTextBox( "PS_Detail_roamChgItem_code_txtId", code );

	}

	/* This method is used to click on save button*/
	private void clickOnSave() throws Exception
	{
		psGenericHelper.detailSave( "PS_Detail_roamChgItem_save_btnId", value, "Value" );
	}
}
