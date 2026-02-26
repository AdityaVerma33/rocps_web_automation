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
import com.subex.rocps.automation.helpers.application.genericHelpers.PSGenericHelper;
import com.subex.rocps.automation.helpers.application.products.productBundles.product.ProductUtil;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;
import com.subex.rocps.automation.utils.ExcelHolder;
import com.subex.rocps.automation.utils.PSStringUtils;

public class FileUploadCategory extends PSAcceptanceTest
{
	protected ExcelReader excelData = new ExcelReader();
	protected Map<String, ArrayList<String>> fileUploadCategoryExcelMap = null;
	protected Map<String, String> fileUploadCategoryMap = null;
	protected ExcelHolder excelHolderObj = null;
	protected String path;
	protected String workBookName;
	protected String sheetName;
	protected String testCaseName;
	protected int occurence;
	protected int colSize;
	protected String clientPartition;
	protected String colmHdrs;
	protected String categoryName;

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
	public FileUploadCategory( String path, String workBookName, String sheetName, String testCaseName ) throws Exception
	{
		this.path = path;
		this.workBookName = workBookName;
		this.sheetName = sheetName;
		this.testCaseName = testCaseName;
		fileUploadCategoryExcelMap = excelData.readDataByColumn( path, workBookName, sheetName, testCaseName );
		excelHolderObj = new ExcelHolder( fileUploadCategoryExcelMap );
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
	public FileUploadCategory( String path, String workBookName, String sheetName, String testCaseName, int occurence ) throws Exception
	{
		this.path = path;
		this.workBookName = workBookName;
		this.sheetName = sheetName;
		this.testCaseName = testCaseName;
		this.occurence = occurence;
		fileUploadCategoryExcelMap = excelData.readDataByColumn( path, workBookName, sheetName, testCaseName, occurence );
		excelHolderObj = new ExcelHolder( fileUploadCategoryExcelMap );
		colSize = excelHolderObj.totalColumns();
	}

	/*
	 * This method is for initialize variable
	 */
	private void initializeVariable( Map<String, String> map ) throws Exception
	{
		categoryName = ExcelHolder.getKey( map, "CategoryName" );
		clientPartition = ExcelHolder.getKey( map, "Partition" );
	}

	/*
	 * This method is for 'File Upload Category' screen common method
	 */
	private void fileUploadCategoryScreen() throws Exception
	{
		NavigationHelper.navigateToReferenceTable( "File Upload Category" );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		fileUploadCategoryMap = excelHolderObj.dataMap( index );
		ButtonHelper.click( "SearchButton" );
		psGenericHelper.waitforHeaderElement( "Category" );
	}

	/*
	 * This method is for 'File Upload Category' screen column validation
	 */
	public void fileUploadCategoryColumsValidation() throws Exception
	{
		try
		{
			for ( index = 0; index < colSize; index++ )
			{
				fileUploadCategoryScreen();
				colmHdrs = ExcelHolder.getKey( fileUploadCategoryMap, "SearchScreenColumns" );
				psGenericHelper.screenColumnValidation( "Category", colmHdrs, "File Upload Category" );
			}
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}

	}

	/*
	 * This method is for 'File Upload Category'  creation
	 */
	public void fileUploadCategoryCreation() throws Exception
	{
		try
		{
			for ( index = 0; index < colSize; index++ )
			{
				fileUploadCategoryScreen();
				initializeVariable( fileUploadCategoryMap );
				boolean isfileUploadCategoryPresent = psGenericHelper.isDataPresent( categoryName, "Category" );
				if ( !isfileUploadCategoryPresent )
				{
					psActionImpl.clickNewAction( clientPartition, "PS_Detail_fileUploadCategory_detailXpath" );
					configurefileUploadCategory();
					clickOnSave();
					Log4jHelper.logInfo( "'File Upload Category' is successfully created with  Name:- '" + categoryName );

				}
				else

					Log4jHelper.logInfo( "'File Upload Category' is already avilable with  name:- '" + categoryName );
			}
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}

	}

	/* This method is used 
	 * configure File Upload Category
	 */
	private void configurefileUploadCategory() throws Exception
	{

		TextBoxHelper.type( "PS_Detail_fileUploadCategory_name_txtId", categoryName );

	}

	/* This method is used to click on save button*/
	private void clickOnSave() throws Exception
	{
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		psGenericHelper.detailSaveWithRetry( "PS_Detail_fileUploadCategory_save_BtnId", categoryName, "Category" );
	}
}
