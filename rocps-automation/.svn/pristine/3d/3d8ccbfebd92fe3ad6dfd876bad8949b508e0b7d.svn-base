package com.subex.rocps.automation.helpers.application.referenceTable;

import java.util.ArrayList;
import java.util.Map;

import com.subex.automation.helpers.application.NavigationHelper;
import com.subex.automation.helpers.component.ButtonHelper;
import com.subex.automation.helpers.component.ComboBoxHelper;
import com.subex.automation.helpers.component.GenericHelper;
import com.subex.automation.helpers.component.GridHelper;
import com.subex.automation.helpers.component.TextBoxHelper;
import com.subex.automation.helpers.file.ExcelReader;
import com.subex.automation.helpers.report.Log4jHelper;
import com.subex.automation.helpers.util.FailureHelper;
import com.subex.rocps.automation.helpers.application.genericHelpers.PSActionImpl;
import com.subex.rocps.automation.helpers.application.genericHelpers.PSDataComponentHelper;
import com.subex.rocps.automation.helpers.application.genericHelpers.PSGenericHelper;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;
import com.subex.rocps.automation.utils.ExcelHolder;
import com.subex.rocps.automation.utils.PSStringUtils;

public class CategoryFlCollectMapping extends PSAcceptanceTest
{
	protected ExcelReader excelData = new ExcelReader();
	protected Map<String, ArrayList<String>> categoryFlCollMappingExcelMap = null;
	protected Map<String, String> categoryFlCollMappingMap = null;
	protected ExcelHolder excelHolderObj = null;
	protected String path;
	protected String workBookName;
	protected String sheetName;
	protected String testCaseName;
	protected int occurence;
	protected int colSize;
	protected String clientPartition;
	protected String colmHdrs;
	protected String category;
	protected String fileCollection;

	protected int index;
	protected PSGenericHelper psGenericHelper = new PSGenericHelper();
	PSStringUtils psStringUtils = new PSStringUtils();
	PSActionImpl psActionImpl = new PSActionImpl();
	PSDataComponentHelper psDataComponentHelper = new PSDataComponentHelper();

	/**Constructor : Initializing the excel without occurrence
	 * @param path
	 * @param workBookName
	 * @param sheetName
	 * @param testCaseName
	 * @throws Exception 
	 */
	public CategoryFlCollectMapping( String path, String workBookName, String sheetName, String testCaseName ) throws Exception
	{
		this.path = path;
		this.workBookName = workBookName;
		this.sheetName = sheetName;
		this.testCaseName = testCaseName;
		categoryFlCollMappingExcelMap = excelData.readDataByColumn( path, workBookName, sheetName, testCaseName );
		excelHolderObj = new ExcelHolder( categoryFlCollMappingExcelMap );
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
	public CategoryFlCollectMapping( String path, String workBookName, String sheetName, String testCaseName, int occurence ) throws Exception
	{
		this.path = path;
		this.workBookName = workBookName;
		this.sheetName = sheetName;
		this.testCaseName = testCaseName;
		this.occurence = occurence;
		categoryFlCollMappingExcelMap = excelData.readDataByColumn( path, workBookName, sheetName, testCaseName, occurence );
		excelHolderObj = new ExcelHolder( categoryFlCollMappingExcelMap );
		colSize = excelHolderObj.totalColumns();
	}

	/*
	 * This method is for initialize variable
	 */
	private void initializeVariable( Map<String, String> map ) throws Exception
	{
		category = ExcelHolder.getKey( map, "Category" );
		fileCollection = ExcelHolder.getKey( map, "FileCollection" );
		clientPartition = ExcelHolder.getKey( map, "Partition" );
	}

	/*
	 * This method is for 'Category File Collection Mapping' screen common method
	 */
	private void categoryFlCollMappingScreen() throws Exception
	{
		NavigationHelper.navigateToReferenceTable( "Category File Collection Mapping" );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		categoryFlCollMappingMap = excelHolderObj.dataMap( index );
		ButtonHelper.click( "SearchButton" );
		psGenericHelper.waitforHeaderElement( "Category" );
	}

	/*
	 * This method is for 'Category File Collection Mapping' screen column validation
	 */
	public void categoryFlCollMappingColumsValidation() throws Exception
	{
		try
		{
			for ( index = 0; index < colSize; index++ )
			{
				categoryFlCollMappingScreen();
				colmHdrs = ExcelHolder.getKey( categoryFlCollMappingMap, "SearchScreenColumns" );
				psGenericHelper.screenColumnValidation( "Category", colmHdrs, "Category File Collection Mapping" );
			}
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}

	}

	/*
	 * This method is for 'Category File Collection Mapping'  creation
	 */
	public void categoryFlCollMappingCreation() throws Exception
	{
		try
		{
			for ( index = 0; index < colSize; index++ )
			{
				categoryFlCollMappingScreen();
				initializeVariable( categoryFlCollMappingMap );
				String fileCollectionNm = fileCollection.substring( fileCollection.lastIndexOf( '-' ) + 1 ).trim();
				String combinOfCategFlCollection = category + ", " + fileCollectionNm;
				boolean iscategoryFlCollMappingPresent = psDataComponentHelper.isDataPresentInGrid( "SearchGrid", combinOfCategFlCollection );
				if ( !iscategoryFlCollMappingPresent )
				{
					psActionImpl.clickNewAction( clientPartition, "PS_Detail_CategFlCollecMapping_detailXpath" );
					configurecategoryFlCollMapping();
					clickOnSave( combinOfCategFlCollection );
					Log4jHelper.logInfo( "'Category File Collection Mapping' is successfully created with  combination of:- '" + combinOfCategFlCollection );

				}
				else

					Log4jHelper.logInfo( "'Category File Collection Mapping' is already avilable with  combination of:- '" + combinOfCategFlCollection );
			}
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}

	}

	/* This method is used 
	 * configure Category File Collection Mapping
	 */
	private void configurecategoryFlCollMapping() throws Exception
	{

		ComboBoxHelper.select( "PS_Detail_CategFlCollecMapping_category_comboId", category );
		ComboBoxHelper.select( "PS_Detail_CategFlCollecMapping_fileCollection_comboId", fileCollection );

	}

	/* This method is used to click on save button*/
	private void clickOnSave( String combinOfCategFlCollection ) throws Exception
	{
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		ButtonHelper.click( "PS_Detail_CategFlCollecMapping_save_BtnId" );
		GenericHelper.waitForLoadmask( detailScreenWaitSec );
		ButtonHelper.click( "SearchButton" );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		psGenericHelper.waitforHeaderElement( "Category" );
		boolean iscategoryFlCollMappingPresent = psDataComponentHelper.isDataPresentInGrid( "SearchGrid", combinOfCategFlCollection );
		assertTrue( iscategoryFlCollMappingPresent, "'Category File Collection Mapping' is not found  after save on search screen with  combination of:- '" + combinOfCategFlCollection );
	}
}
