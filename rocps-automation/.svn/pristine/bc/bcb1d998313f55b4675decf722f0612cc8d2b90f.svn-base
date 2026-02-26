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

public class TadigCodes extends PSAcceptanceTest
{
	protected ExcelReader excelData = new ExcelReader();
	protected Map<String, ArrayList<String>> tadigCodesExcelMap = null;
	protected Map<String, String> tadigCodesMap = null;
	protected ExcelHolder excelHolderObj = null;
	protected String path;
	protected String workBookName;
	protected String sheetName;
	protected String testCaseName;
	protected int occurence;
	protected int colSize;
	protected String colmHdrs;
	protected String clientPartition;
	protected String tadigCodes;
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
	public TadigCodes( String path, String workBookName, String sheetName, String testCaseName ) throws Exception
	{

		this.path = path;
		this.workBookName = workBookName;
		this.sheetName = sheetName;
		this.testCaseName = testCaseName;
		tadigCodesExcelMap = excelData.readDataByColumn( path, workBookName, sheetName, testCaseName );
		excelHolderObj = new ExcelHolder( tadigCodesExcelMap );
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
	public TadigCodes( String path, String workBookName, String sheetName, String testCaseName, int occurence ) throws Exception
	{

		this.path = path;
		this.workBookName = workBookName;
		this.sheetName = sheetName;
		this.testCaseName = testCaseName;
		this.occurence = occurence;
		tadigCodesExcelMap = excelData.readDataByColumn( path, workBookName, sheetName, testCaseName, occurence );
		excelHolderObj = new ExcelHolder( tadigCodesExcelMap );
		colSize = excelHolderObj.totalColumns();
	}

	/*
	 * This method is for initialize variable
	 */
	private void initializeVariable( Map<String, String> map ) throws Exception
	{
		tadigCodes = ExcelHolder.getKey( map, "TadigCode" );
		clientPartition = ExcelHolder.getKey( map, "Partition" );
	}

	/*
	 * This method is for 'Tadig Codes' screen common method
	 */
	private void tadigCodesScreen() throws Exception
	{
		NavigationHelper.navigateToReferenceTable( "Tadig Codes" );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		tadigCodesMap = excelHolderObj.dataMap( index );
		psGenericHelper.waitforHeaderElement( "Code" );
	}

	/*
	 * This method is for 'Tadig Codes' screen column validation
	 */
	public void tadigCodeColumsValidation() throws Exception
	{
		try
		{
			for ( index = 0; index < colSize; index++ )
			{
				tadigCodesScreen();
				colmHdrs = ExcelHolder.getKey( tadigCodesMap, "SearchScreenColumns" );
				ArrayList<String> excelColumnNames = new ArrayList<String>();
				String tadigCodeGridColumnsArr[] = psStringUtils.stringSplitFirstLevel( colmHdrs );
				for ( String column : tadigCodeGridColumnsArr )
					excelColumnNames.add( column );
				psGenericHelper.totalColumns( excelColumnNames );
				Log4jHelper.logInfo( "'Tadig Codes' Columns are validated successfully" );
			}
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}

	}

	/* This method is used to click on 'New' action in Tadig Codes*/
	private void clickNewAction( String clientPartition ) throws Exception
	{
		ProductUtil.waitForParentActionElementTOBeclickable( "Common Tasks" );
		psGenericHelper.clickNewAction( clientPartition );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		ElementHelper.waitForElement( "PS_Detail_tadigCodes_detail_xpath", searchScreenWaitSec );
	}

	/* This method is used to check tadig code present*/
	private boolean isTadigCodePresent( String tadigCode ) throws Exception
	{
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		ButtonHelper.click( "SearchButton" );
		psGenericHelper.waitforHeaderElement( "Code" );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		int row = GridHelper.getRowCount( "SearchGrid" );
		boolean isTadigCodePresent = false;
		if ( row > 0 )
		{
			for ( int i = 0; i < row; i++ )
			{
				String cellValue = GridHelper.getCellValue( "SearchGrid", i + 1, "Code" );
				GenericHelper.waitForLoadmask();
				isTadigCodePresent = cellValue.contentEquals( tadigCode );
				if ( isTadigCodePresent )
					return true;
			}
			return isTadigCodePresent;
		}
		return false;
	}

	/*
	 * This method is for 'Tadig Codes' report creation
	 */
	public void tadigCodesCreation() throws Exception
	{
		try
		{
			for ( index = 0; index < colSize; index++ )
			{
				tadigCodesScreen();
				initializeVariable( tadigCodesMap );
				boolean isTadigCodePresent = isTadigCodePresent( tadigCodes );
				if ( !isTadigCodePresent )
				{
					initializeVariable( tadigCodesMap );
					clickNewAction( clientPartition );
					configTadigCode();
					Log4jHelper.logInfo( "'Tadig Codes' is successfully created with name-: " + tadigCodes );

				}
				else

					Log4jHelper.logInfo( "'Tadig Codes' is already avilable with name-: " + tadigCodes );
			}
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}

	}

	/*
	 * This method is for 'Tadig Codes' report configuraion
	 */
	private void configTadigCode() throws Exception
	{
		TextBoxHelper.type( "PS_Detail_tadigCodes_code_textID", tadigCodes );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		psGenericHelper.detailSave( "PS_Detail_tadigCodes_code_save_btnId", tadigCodes, "Code" );
	}
}
