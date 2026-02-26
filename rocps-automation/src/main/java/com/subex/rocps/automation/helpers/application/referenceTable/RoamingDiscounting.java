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

public class RoamingDiscounting extends PSAcceptanceTest
{
	protected ExcelReader excelData = new ExcelReader();
	protected Map<String, ArrayList<String>> roamingDiscountingExcelMap = null;
	protected Map<String, String> roamingDiscountingMap = null;
	protected ExcelHolder excelHolderObj = null;
	protected String path;
	protected String workBookName;
	protected String sheetName;
	protected String testCaseName;
	protected int occurence;
	protected int colSize;
	protected String clientPartition;
	protected String colmHdrs;
	protected String name;
	protected String discountType;
	protected String fixedDiscountVal;
	protected String discountRate;
	protected String applicableFor;
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
	public RoamingDiscounting( String path, String workBookName, String sheetName, String testCaseName ) throws Exception
	{
		this.path = path;
		this.workBookName = workBookName;
		this.sheetName = sheetName;
		this.testCaseName = testCaseName;
		roamingDiscountingExcelMap = excelData.readDataByColumn( path, workBookName, sheetName, testCaseName );
		excelHolderObj = new ExcelHolder( roamingDiscountingExcelMap );
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
	public RoamingDiscounting( String path, String workBookName, String sheetName, String testCaseName, int occurence ) throws Exception
	{
		this.path = path;
		this.workBookName = workBookName;
		this.sheetName = sheetName;
		this.testCaseName = testCaseName;
		this.occurence = occurence;
		roamingDiscountingExcelMap = excelData.readDataByColumn( path, workBookName, sheetName, testCaseName, occurence );
		excelHolderObj = new ExcelHolder( roamingDiscountingExcelMap );
		colSize = excelHolderObj.totalColumns();
	}

	/*
	 * This method is for initialize variable
	 */
	private void initializeVariable( Map<String, String> map ) throws Exception
	{
		name = ExcelHolder.getKey( map, "Name" );
		discountType = ExcelHolder.getKey( map, "DiscountType" );
		fixedDiscountVal = ExcelHolder.getKey( map, "FixedDiscountValue" );
		discountRate = ExcelHolder.getKey( map, "DiscountRate" );
		applicableFor = ExcelHolder.getKey( map, "ApplicableFor" );
		clientPartition = ExcelHolder.getKey( map, "Partition" );
	}

	/*
	 * This method is for 'Roaming Discounting' screen common method
	 */
	private void roamingDiscountingScreen() throws Exception
	{
		NavigationHelper.navigateToReferenceTable( "Roaming Discounting" );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		roamingDiscountingMap = excelHolderObj.dataMap( index );
		ButtonHelper.click( "SearchButton" );
		psGenericHelper.waitforHeaderElement( "Discount Type" );
	}

	/*
	 * This method is for 'Roaming Discounting' screen column validation
	 */
	public void roamingDiscountingColumsValidation() throws Exception
	{
		try
		{
			for ( index = 0; index < colSize; index++ )
			{
				roamingDiscountingScreen();
				colmHdrs = ExcelHolder.getKey( roamingDiscountingMap, "SearchScreenColumns" );
				ArrayList<String> excelColumnNames = new ArrayList<String>();
				String roamingDiscountingGridColumnsArr[] = psStringUtils.stringSplitFirstLevel( colmHdrs );
				for ( String column : roamingDiscountingGridColumnsArr )
					excelColumnNames.add( column );
				psGenericHelper.totalColumns( excelColumnNames );
				Log4jHelper.logInfo( "'Roaming Discounting' Columns are validated successfully" );
			}
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}

	}

	/*
	 * This method is for 'Roaming Discounting'  creation
	 */
	public void roamingDiscountingCreation() throws Exception
	{
		try
		{
			for ( index = 0; index < colSize; index++ )
			{
				roamingDiscountingScreen();
				initializeVariable( roamingDiscountingMap );
				boolean isRoamingDiscNamePresent = psGenericHelper.isDataPresent( name, "Name" );
				if ( !isRoamingDiscNamePresent )
				{
					psActionImpl.clickNewAction( clientPartition, "PS_Detail_roamDiscount_detailXpath" );
					configureRoamingDiscounting();
					clickOnSave();
					Log4jHelper.logInfo( "'Roaming Discounting' is successfully created with  Name:- '" + name );

				}
				else

					Log4jHelper.logInfo( "'Roaming Discounting' is already avilable with  name:- '" + name );
			}
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}

	}

	/*
	 * This method is for 'Roaming Discounting'  Edit
	 */
	public void roamingDiscountingEdit() throws Exception
	{
		try
		{
			for ( index = 0; index < colSize; index++ )
			{
				roamingDiscountingScreen();
				initializeVariable( roamingDiscountingMap );
				boolean isRoamingDiscNamePresent = psGenericHelper.isDataPresent( name, "Name" );
				if ( isRoamingDiscNamePresent )
				{
					GridHelper.clickRow( "SearchGrid", name, "Name" );
					GenericHelper.waitForLoadmask( searchScreenWaitSec );
					psActionImpl.clickOnAction( "Common Tasks", "Edit", "PS_Detail_roamDiscount_detailXpath" );
					modifyRoamingDiscounting();
					clickOnSave();
					Log4jHelper.logInfo( "'Roaming Discounting' is successfully updated with  name:- '" + name );

				}
				else

					Log4jHelper.logInfo( "'Roaming Discounting' is not avilable with  name:- '" + name );
			}
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}

	}

	/* This method is used 
	 * configure roaming Discounting
	 */
	private void configureRoamingDiscounting() throws Exception
	{
		TextBoxHelper.type( "PS_Detail_roamDiscount_discountName_txtId", name );
		ComboBoxHelper.select( "PS_Detail_roamDiscount_discountType_comboId", discountType );
		TextBoxHelper.type( "PS_Detail_roamDiscount_fixedDiscVal_txtId", fixedDiscountVal );
		TextBoxHelper.type( "PS_Detail_roamDiscount_discRate_txtId", discountRate );
		ComboBoxHelper.select( "PS_Detail_roamDiscount_applFor_comboId", applicableFor );
	}

	/* This method is used 
	 * modify roaming Discounting
	 */
	private void modifyRoamingDiscounting() throws Exception
	{
		assertEquals( TextBoxHelper.getValue( "PS_Detail_roamDiscount_discountName_txtId" ), name, "Roamging Discounting Name is not matched" );
		ProductUtil.modifyComboBox( "PS_Detail_roamDiscount_discountType_comboId", discountType );
		ProductUtil.modifyTextBox( "PS_Detail_roamDiscount_fixedDiscVal_txtId", fixedDiscountVal );
		ProductUtil.modifyTextBox( "PS_Detail_roamDiscount_discRate_txtId", discountRate );
		ProductUtil.modifyComboBox( "PS_Detail_roamDiscount_applFor_comboId", applicableFor );
	}

	/* This method is used to click on save button*/
	private void clickOnSave() throws Exception
	{
		psGenericHelper.detailSaveWithRetry( "PS_Detail_roamDiscount_save_btnId", discountType, "Discount Type" );
	}

}
