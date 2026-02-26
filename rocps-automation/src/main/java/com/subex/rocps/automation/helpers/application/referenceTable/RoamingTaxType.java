package com.subex.rocps.automation.helpers.application.referenceTable;

import java.util.ArrayList;
import java.util.Map;

import com.subex.rocps.automation.helpers.application.genericHelpers.PSGenericHelper;
import com.subex.rocps.automation.helpers.application.products.productBundles.product.ProductUtil;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;
import com.subex.rocps.automation.utils.ExcelHolder;
import com.subex.rocps.automation.utils.PSStringUtils;

import com.subex.automation.helpers.application.NavigationHelper;
import com.subex.automation.helpers.component.ButtonHelper;
import com.subex.automation.helpers.component.ElementHelper;
import com.subex.automation.helpers.component.GenericHelper;
import com.subex.automation.helpers.component.GridHelper;
import com.subex.automation.helpers.component.TextBoxHelper;
import com.subex.automation.helpers.file.ExcelReader;
import com.subex.automation.helpers.report.Log4jHelper;
import com.subex.automation.helpers.util.FailureHelper;

public class RoamingTaxType extends PSAcceptanceTest
{

	protected ExcelReader excelData = new ExcelReader();
	protected Map<String, ArrayList<String>> roamingTaxTypeExcelMap = null;
	protected Map<String, String> roamingTaxTypeMap = null;
	protected ExcelHolder excelHolderObj = null;
	protected String path;
	protected String workBookName;
	protected String sheetName;
	protected String testCaseName;
	protected int occurence;
	protected int colSize;
	protected String clientPartition;
	protected String colmHdrs;
	protected String value;
	protected String description;
	protected String type;
	protected int index;
	protected PSGenericHelper psGenericHelper = new PSGenericHelper();
	PSStringUtils psStringUtils = new PSStringUtils();

	/**Constructor : Initializing the excel without occurrence
	 * @param path
	 * @param workBookName
	 * @param sheetName
	 * @param testCaseName
	 * @throws Exception 
	 */
	public RoamingTaxType( String path, String workBookName, String sheetName, String testCaseName ) throws Exception
	{

		this.path = path;
		this.workBookName = workBookName;
		this.sheetName = sheetName;
		this.testCaseName = testCaseName;
		roamingTaxTypeExcelMap = excelData.readDataByColumn( path, workBookName, sheetName, testCaseName );
		excelHolderObj = new ExcelHolder( roamingTaxTypeExcelMap );
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
	public RoamingTaxType( String path, String workBookName, String sheetName, String testCaseName, int occurence ) throws Exception
	{
		this.path = path;
		this.workBookName = workBookName;
		this.sheetName = sheetName;
		this.testCaseName = testCaseName;
		this.occurence = occurence;
		roamingTaxTypeExcelMap = excelData.readDataByColumn( path, workBookName, sheetName, testCaseName, occurence );
		excelHolderObj = new ExcelHolder( roamingTaxTypeExcelMap );
		colSize = excelHolderObj.totalColumns();
	}

	/*
	 * This method is for initialize variable
	 */
	private void initializeVariable( Map<String, String> map ) throws Exception
	{
		value = ExcelHolder.getKey( map, "Value" );
		description = ExcelHolder.getKey( map, "Description" );
		type = ExcelHolder.getKey( map, "Type" );
		clientPartition = ExcelHolder.getKey( map, "Partition" );
	}

	/*
	 * This method is for 'Roaming Tax Type' screen common method
	 */
	private void roamingTaxTypeScreen() throws Exception
	{
		NavigationHelper.navigateToReferenceTable( "Roaming Tax Type" );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		roamingTaxTypeMap = excelHolderObj.dataMap( index );
		ButtonHelper.click( "SearchButton" );
		psGenericHelper.waitforHeaderElement( "Value" );
	}

	/*
	 * This method is for 'Roaming Tax Type' screen column validation
	 */
	public void roamingTaxTypeColumsValidation() throws Exception
	{
		try
		{
			for ( index = 0; index < colSize; index++ )
			{
				roamingTaxTypeScreen();
				colmHdrs = ExcelHolder.getKey( roamingTaxTypeMap, "SearchScreenColumns" );
				ArrayList<String> excelColumnNames = new ArrayList<String>();
				String roamingTaxTypeGridColumnsArr[] = psStringUtils.stringSplitFirstLevel( colmHdrs );
				for ( String column : roamingTaxTypeGridColumnsArr )
					excelColumnNames.add( column );
				psGenericHelper.totalColumns( excelColumnNames );
				Log4jHelper.logInfo( "'Roaming Tax Type' Columns are validated successfully" );
			}
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}

	}

	/*
	 * This method is for 'Roaming Tax Type'  creation
	 */
	public void roamingTaxTypeCreation() throws Exception
	{
		try
		{
			for ( index = 0; index < colSize; index++ )
			{
				roamingTaxTypeScreen();
				initializeVariable( roamingTaxTypeMap );
				boolean isValuePresent = isDataPresent( value, "Value" );
				boolean isTypePresent = isDataPresent( type, "Type" );
				if ( !isValuePresent && !isTypePresent )
				{
					clickNewAction( clientPartition );
					configureRoamingTaxTypeType();
					clickOnSave();
					Log4jHelper.logInfo( "'Roaming Tax Type' is successfully created with  type:- '" + type + " and value: " + value );

				}
				else if ( isValuePresent )
					Log4jHelper.logInfo( "'Roaming Tax Type' is already avilable with  value:- '" + value );
				else if ( isTypePresent )
					Log4jHelper.logInfo( "'Roaming Tax Type' is already avilable with  type:- '" + type );
			}
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}

	}

	/*
	 * This method is for 'Roaming Tax Type'  edit
	 */
	public void roamingTaxTypeEdit() throws Exception
	{
		try
		{
			for ( index = 0; index < colSize; index++ )
			{
				roamingTaxTypeScreen();
				initializeVariable( roamingTaxTypeMap );
				boolean isValuePresent = isDataPresent( value, "Value" );
				if ( isValuePresent )
				{
					GridHelper.clickRow( "SearchGrid", value, "Value" );
					GenericHelper.waitForLoadmask( searchScreenWaitSec );
					clickOnAction( "Common Tasks", "Edit" );
					modifyRoamingTaxTypeType();
					clickOnSave();
					Log4jHelper.logInfo( "'Roaming Tax Type' is successfully updated with  type:- '" + type );

				}
				else
					Log4jHelper.logInfo( "'Roaming Tax Type' is not avilable with  value:- '" + value );
			}
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}

	}

	/* This method is used  to check data present*/
	public boolean isDataPresent( String txtValue, String txtColumnHeader ) throws Exception
	{
		int row = GridHelper.getRowCount( "SearchGrid" );
		boolean rowVal = false;
		if ( row > 0 )
		{
			for ( int i = 0; i < row; i++ )
			{
				String cellValue = GridHelper.getCellValue( "SearchGrid", i + 1, txtColumnHeader );
				GenericHelper.waitForLoadmask();
				rowVal = cellValue.contains( txtValue );
				if ( rowVal )
					return true;
			}
			return rowVal;
		}
		return false;
	}

	/* This method is used 
	 * configure Roaming Tax Type
	 */
	private void configureRoamingTaxTypeType() throws Exception
	{
		TextBoxHelper.type( "PS_Detail_roamTaxType_value_txtId", value );
		TextBoxHelper.type( "PS_Detail_roamTaxType_type_txtId", type );
		TextBoxHelper.type( "PS_Detail_roamTaxType_description_txtId", description );
	}

	/* This method is used 
	 * modify Roaming Tax Type
	 */
	private void modifyRoamingTaxTypeType() throws Exception
	{
		assertEquals( TextBoxHelper.getValue( "PS_Detail_roamTaxType_value_txtId" ), value, "Value is not matched" );
		assertEquals( TextBoxHelper.getValue( "PS_Detail_roamTaxType_type_txtId" ), type, "Type is not matched" );
		ProductUtil.modifyTextBox( "PS_Detail_roamTaxType_description_txtId", description );
	}

	/* This method is used to click on save button*/
	private void clickOnSave() throws Exception
	{
		psGenericHelper.detailSave( "PS_Detail_roamTaxType_save_btnId", value, "Value" );
	}

	/* This method is used to click on 'New' action */
	private void clickNewAction( String clientPartition ) throws Exception
	{
		ProductUtil.waitForParentActionElementTOBeclickable( "Common Tasks" );
		psGenericHelper.clickNewAction( clientPartition );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		ElementHelper.waitForElement( "PS_Detail_roamTaxType_detailXpath", searchScreenWaitSec );
	}

	/* This method is used to click on  action'*/
	public void clickOnAction( String parentActionNm, String childActionNm ) throws Exception
	{
		PSGenericHelper.waitForParentActionElementTOBeclickable( parentActionNm );
		psGenericHelper.validateActionText( parentActionNm, childActionNm );
		NavigationHelper.navigateToAction( parentActionNm, childActionNm );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		ElementHelper.waitForElement( GenericHelper.getORProperty( "PS_Detail_roamTaxType_detailXpath" ), searchScreenWaitSec );
	}
}
