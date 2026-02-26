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
import com.subex.automation.helpers.component.SearchGridHelper;
import com.subex.automation.helpers.component.TextBoxHelper;
import com.subex.automation.helpers.file.ExcelReader;
import com.subex.automation.helpers.report.Log4jHelper;
import com.subex.automation.helpers.util.FailureHelper;

public class CallTypeLevel1 extends PSAcceptanceTest
{

	protected ExcelReader excelData = new ExcelReader();
	protected Map<String, ArrayList<String>> callTypeLevel1ExcelMap = null;
	protected Map<String, String> callTypeLevel1Map = null;
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
	public CallTypeLevel1( String path, String workBookName, String sheetName, String testCaseName ) throws Exception
	{

		this.path = path;
		this.workBookName = workBookName;
		this.sheetName = sheetName;
		this.testCaseName = testCaseName;
		callTypeLevel1ExcelMap = excelData.readDataByColumn( path, workBookName, sheetName, testCaseName );
		excelHolderObj = new ExcelHolder( callTypeLevel1ExcelMap );
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
	public CallTypeLevel1( String path, String workBookName, String sheetName, String testCaseName, int occurence ) throws Exception
	{

		this.path = path;
		this.workBookName = workBookName;
		this.sheetName = sheetName;
		this.testCaseName = testCaseName;
		this.occurence = occurence;
		callTypeLevel1ExcelMap = excelData.readDataByColumn( path, workBookName, sheetName, testCaseName, occurence );
		excelHolderObj = new ExcelHolder( callTypeLevel1ExcelMap );
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
	 * This method is for 'Call Type Level 1' screen common method
	 */
	private void callTypeLevel1Screen() throws Exception
	{
		NavigationHelper.navigateToReferenceTable( "Call Type Level 1" );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		callTypeLevel1Map = excelHolderObj.dataMap( index );
		ButtonHelper.click( "SearchButton" );
		psGenericHelper.waitforHeaderElement( "Value" );
	}

	/*
	 * This method is for 'Call Type Level 1' screen column validation
	 */
	public void callTypeLevel1ColumsValidation() throws Exception
	{
		try
		{
			for ( index = 0; index < colSize; index++ )
			{
				callTypeLevel1Screen();
				colmHdrs = ExcelHolder.getKey( callTypeLevel1Map, "SearchScreenColumns" );
				ArrayList<String> excelColumnNames = new ArrayList<String>();
				String callTypeLevel1GridColumnsArr[] = psStringUtils.stringSplitFirstLevel( colmHdrs );
				for ( String column : callTypeLevel1GridColumnsArr )
					excelColumnNames.add( column );
				psGenericHelper.totalColumns( excelColumnNames );
				Log4jHelper.logInfo( "'Call Type Level 1' Columns are validated successfully" );
			}
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}

	}

	/*
	 * This method is for 'Call Type Level 1'  creation
	 */
	public void callTypeLevel1Creation() throws Exception
	{
		try
		{
			for ( index = 0; index < colSize; index++ )
			{
				callTypeLevel1Screen();
				initializeVariable( callTypeLevel1Map );
				boolean isValuePresent = isDataPresent( value, "Value" );
				if ( !isValuePresent )
				{
					clickNewAction( clientPartition );
					configureCallTypeLevel1();
					clickOnSave();
					Log4jHelper.logInfo( "'Call Type Level 1' is successfully created with  value:- '" + value );

				}
				else

					Log4jHelper.logInfo( "'Call Type Level 1' is already avilable with  value:- '" + value );
			}
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}

	}

	/*
	 * This method is for 'Call Type Level 1'  edit
	 */
	public void callTypeLevel1Edit() throws Exception
	{
		try
		{
			for ( index = 0; index < colSize; index++ )
			{
				callTypeLevel1Screen();
				initializeVariable( callTypeLevel1Map );
				boolean isValuePresent = isDataPresent( value, "Value" );
				if ( isValuePresent )
				{
					GridHelper.clickRow( "SearchGrid", value, "Value" );
					GenericHelper.waitForLoadmask( searchScreenWaitSec );
					clickOnAction( "Common Tasks", "Edit" );
					modifyCallTypeLevel1();
					clickOnSave();
					Log4jHelper.logInfo( "'Call Type Level 1' is successfully updated with  value:- '" + value );

				}
				else

					Log4jHelper.logInfo( "'Call Type Level 1' is not avilable with  value:- '" + value );
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
	 * configure Call type level1
	 */
	private void configureCallTypeLevel1() throws Exception
	{
		TextBoxHelper.type( "PS_Detail_callTypeLevel1_value_txtId", value );
		TextBoxHelper.type( "PS_Detail_callTypeLevel1_description_txtId", description );
	}

	/* This method is used 
	 * modify Call type level1
	 */
	private void modifyCallTypeLevel1() throws Exception
	{
		assertEquals( TextBoxHelper.getValue( "PS_Detail_callTypeLevel1_value_txtId" ), value, "Value is not matched" );
		ProductUtil.modifyTextBox( "PS_Detail_callTypeLevel1_description_txtId", description );
	}

	/* This method is used to click on save button*/
	private void clickOnSave() throws Exception
	{
		psGenericHelper.detailSaveWithRetry( "PS_Detail_callTypeLevel1_save_btnId", value, "Value" );
	}

	/* This method is used to click on 'New' action */
	private void clickNewAction( String clientPartition ) throws Exception
	{
		ProductUtil.waitForParentActionElementTOBeclickable( "Common Tasks" );
		psGenericHelper.clickNewAction( clientPartition );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		ElementHelper.waitForElement( "PS_Detail_callTypeLevel1_detailXpath", searchScreenWaitSec );
	}

	/* This method is used to click on  action'*/
	public void clickOnAction( String parentActionNm, String childActionNm ) throws Exception
	{
		PSGenericHelper.waitForParentActionElementTOBeclickable( parentActionNm );
		psGenericHelper.validateActionText( parentActionNm, childActionNm );
		NavigationHelper.navigateToAction( parentActionNm, childActionNm );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		ElementHelper.waitForElement( GenericHelper.getORProperty( "PS_Detail_callTypeLevel1_detailXpath" ), searchScreenWaitSec );
	}
}
