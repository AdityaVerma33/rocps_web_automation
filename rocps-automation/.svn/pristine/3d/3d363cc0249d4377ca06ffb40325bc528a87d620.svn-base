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

public class RoamingTaxIndicator extends PSAcceptanceTest
{

	protected ExcelReader excelData = new ExcelReader();
	protected Map<String, ArrayList<String>> roamingTaxIndicExcelMap = null;
	protected Map<String, String> roamingTaxIndicMap = null;
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
	public RoamingTaxIndicator( String path, String workBookName, String sheetName, String testCaseName ) throws Exception
	{

		this.path = path;
		this.workBookName = workBookName;
		this.sheetName = sheetName;
		this.testCaseName = testCaseName;
		roamingTaxIndicExcelMap = excelData.readDataByColumn( path, workBookName, sheetName, testCaseName );
		excelHolderObj = new ExcelHolder( roamingTaxIndicExcelMap );
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
	public RoamingTaxIndicator( String path, String workBookName, String sheetName, String testCaseName, int occurence ) throws Exception
	{

		this.path = path;
		this.workBookName = workBookName;
		this.sheetName = sheetName;
		this.testCaseName = testCaseName;
		this.occurence = occurence;
		roamingTaxIndicExcelMap = excelData.readDataByColumn( path, workBookName, sheetName, testCaseName, occurence );
		excelHolderObj = new ExcelHolder( roamingTaxIndicExcelMap );
		colSize = excelHolderObj.totalColumns();
	}

	/*
	 * This method is for initialize variable
	 */
	private void initializeVariable( Map<String, String> map ) throws Exception
	{
		value = ExcelHolder.getKey( map, "Value" );
		description = ExcelHolder.getKey( map, "Description" );
		clientPartition = ExcelHolder.getKey( map, "Partition" );
	}

	/*
	 * This method is for 'Roaming Tax Indicator' screen common method
	 */
	private void roamingTaxIndicatorScreen() throws Exception
	{
		NavigationHelper.navigateToReferenceTable( "Roaming Tax Indicator" );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		roamingTaxIndicMap = excelHolderObj.dataMap( index );
		ButtonHelper.click( "SearchButton" );
		psGenericHelper.waitforHeaderElement( "Value" );
	}

	/*
	 * This method is for 'Roaming Tax Indicator' screen column validation
	 */
	public void roamingTaxIndicatorColumsValidation() throws Exception
	{
		try
		{
			for ( index = 0; index < colSize; index++ )
			{
				roamingTaxIndicatorScreen();
				colmHdrs = ExcelHolder.getKey( roamingTaxIndicMap, "SearchScreenColumns" );
				ArrayList<String> excelColumnNames = new ArrayList<String>();
				String roamingTaxIndicatorGridColumnsArr[] = psStringUtils.stringSplitFirstLevel( colmHdrs );
				for ( String column : roamingTaxIndicatorGridColumnsArr )
					excelColumnNames.add( column );
				psGenericHelper.totalColumns( excelColumnNames );
				Log4jHelper.logInfo( "'Roaming Tax Indicator' Columns are validated successfully" );
			}
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}

	}

	/*
	 * This method is for 'Roaming Tax Indicator'  creation
	 */
	public void RoamingTaxIndicatorCreation() throws Exception
	{
		try
		{
			for ( index = 0; index < colSize; index++ )
			{
				roamingTaxIndicatorScreen();
				initializeVariable( roamingTaxIndicMap );
				boolean isValuePresent = isDataPresent( value, "Value" );
				if ( !isValuePresent )
				{
					clickNewAction( clientPartition );
					configureRoamingTaxIndicator();
					clickOnSave();
					Log4jHelper.logInfo( "'Roaming Tax Indicator' is successfully created with  value:- '" + value );

				}
				else

					Log4jHelper.logInfo( "'Roaming Tax Indicator' is already avilable with  value:- '" + value );
			}
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}

	}

	/*
	 * This method is for 'Roaming Tax Indicator'  edit
	 */
	public void RoamingTaxIndicatorEdit() throws Exception
	{
		try
		{
			for ( index = 0; index < colSize; index++ )
			{
				roamingTaxIndicatorScreen();
				initializeVariable( roamingTaxIndicMap );
				boolean isValuePresent = isDataPresent( value, "Value" );
				if ( isValuePresent )
				{
					GridHelper.clickRow( "SearchGrid", value, "Value" );
					GenericHelper.waitForLoadmask( searchScreenWaitSec );
					clickOnAction( "Common Tasks", "Edit" );
					modifyRoamingTaxIndicator();
					clickOnSave();
					Log4jHelper.logInfo( "'Roaming Tax Indicator' is successfully updated with  value:- '" + value );

				}
				else

					Log4jHelper.logInfo( "'Roaming Tax Indicator' is not avilable with  value:- '" + value );
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
	 * configure RoamingTaxIndicator
	 */
	private void configureRoamingTaxIndicator() throws Exception
	{
		TextBoxHelper.type( "PS_Detail_roamTaxIndic_value_txtId", value );
		TextBoxHelper.type( "PS_Detail_roamTaxIndic_description_txtId", description );
	}

	/* This method is used  modify
	 * RoamingTaxIndicator
	 */
	private void modifyRoamingTaxIndicator() throws Exception
	{
		assertEquals( TextBoxHelper.getValue( "PS_Detail_roamTaxIndic_value_txtId" ), value, "Value is not matched" );
		ProductUtil.modifyTextBox( "PS_Detail_roamTaxIndic_description_txtId", description );
	}

	/* This method is used to click on save button*/
	private void clickOnSave() throws Exception
	{
		psGenericHelper.detailSave( "PS_Detail_roamTaxIndic_save_btnId", value, "Value" );
	}

	/* This method is used to click on 'New' action */
	private void clickNewAction( String clientPartition ) throws Exception
	{
		ProductUtil.waitForParentActionElementTOBeclickable( "Common Tasks" );
		psGenericHelper.clickNewAction( clientPartition );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		ElementHelper.waitForElement( "PS_Detail_roamTaxIndic_detailXpath", searchScreenWaitSec );
	}

	/* This method is used to click on  action'*/
	public void clickOnAction( String parentActionNm, String childActionNm ) throws Exception
	{
		PSGenericHelper.waitForParentActionElementTOBeclickable( parentActionNm );
		psGenericHelper.validateActionText( parentActionNm, childActionNm );
		NavigationHelper.navigateToAction( parentActionNm, childActionNm );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		ElementHelper.waitForElement( GenericHelper.getORProperty( "PS_Detail_roamTaxIndic_detailXpath" ), searchScreenWaitSec );
	}
}
