package com.subex.rocps.automation.helpers.application.referenceTable;

import java.util.ArrayList;
import java.util.Map;

import com.subex.automation.helpers.application.NavigationHelper;
import com.subex.automation.helpers.component.ButtonHelper;
import com.subex.automation.helpers.component.GenericHelper;
import com.subex.automation.helpers.component.TextBoxHelper;
import com.subex.automation.helpers.file.ExcelReader;
import com.subex.automation.helpers.report.Log4jHelper;
import com.subex.automation.helpers.util.FailureHelper;
import com.subex.rocps.automation.helpers.application.genericHelpers.PSActionImpl;
import com.subex.rocps.automation.helpers.application.genericHelpers.PSGenericHelper;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;
import com.subex.rocps.automation.utils.ExcelHolder;
import com.subex.rocps.automation.utils.PSStringUtils;

public class UploadFileType extends PSAcceptanceTest
{
	protected ExcelReader excelData = new ExcelReader();
	protected Map<String, ArrayList<String>> uploadFileTypeExcelMap = null;
	protected Map<String, String> uploadFileTypeMap = null;
	protected ExcelHolder excelHolderObj = null;
	protected String path;
	protected String workBookName;
	protected String sheetName;
	protected String testCaseName;
	protected int occurence;
	protected int colSize;
	protected String clientPartition;
	protected String colmHdrs;
	protected String fileExtension;

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
	public UploadFileType( String path, String workBookName, String sheetName, String testCaseName ) throws Exception
	{
		this.path = path;
		this.workBookName = workBookName;
		this.sheetName = sheetName;
		this.testCaseName = testCaseName;
		uploadFileTypeExcelMap = excelData.readDataByColumn( path, workBookName, sheetName, testCaseName );
		excelHolderObj = new ExcelHolder( uploadFileTypeExcelMap );
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
	public UploadFileType( String path, String workBookName, String sheetName, String testCaseName, int occurence ) throws Exception
	{
		this.path = path;
		this.workBookName = workBookName;
		this.sheetName = sheetName;
		this.testCaseName = testCaseName;
		this.occurence = occurence;
		uploadFileTypeExcelMap = excelData.readDataByColumn( path, workBookName, sheetName, testCaseName, occurence );
		excelHolderObj = new ExcelHolder( uploadFileTypeExcelMap );
		colSize = excelHolderObj.totalColumns();
	}

	/*
	 * This method is for initialize variable
	 */
	private void initializeVariable( Map<String, String> map ) throws Exception
	{
		fileExtension = ExcelHolder.getKey( map, "FileExtension" );
		clientPartition = ExcelHolder.getKey( map, "Partition" );
	}

	/*
	 * This method is for 'Upload File Type' screen common method
	 */
	private void uploadFileTypeScreen() throws Exception
	{
		NavigationHelper.navigateToReferenceTable( "Upload File Type" );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		uploadFileTypeMap = excelHolderObj.dataMap( index );
		ButtonHelper.click( "SearchButton" );
		psGenericHelper.waitforHeaderElement( "File Extension" );
	}

	/*
	 * This method is for 'Upload File Type' screen column validation
	 */
	public void uploadFileTypeColumsValidation() throws Exception
	{
		try
		{
			for ( index = 0; index < colSize; index++ )
			{
				uploadFileTypeScreen();
				colmHdrs = ExcelHolder.getKey( uploadFileTypeMap, "SearchScreenColumns" );
				psGenericHelper.screenColumnValidation( "File Extension", colmHdrs, "Upload File Type" );
			}
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}

	}

	/*
	 * This method is for 'Upload File Type'  creation
	 */
	public void uploadFileTypeCreation() throws Exception
	{
		try
		{
			for ( index = 0; index < colSize; index++ )
			{
				uploadFileTypeScreen();
				initializeVariable( uploadFileTypeMap );
				boolean isuploadFileTypePresent = psGenericHelper.isDataPresent( fileExtension, "File Extension" );
				if ( !isuploadFileTypePresent )
				{
					psActionImpl.clickNewAction( clientPartition, "PS_Detail_UploadFileType_detailXpath" );
					configureuploadFileType();
					clickOnSave();
					Log4jHelper.logInfo( "'Upload File Type' is successfully created with  Name:- '" + fileExtension );

				}
				else

					Log4jHelper.logInfo( "'Upload File Type' is already avilable with  name:- '" + fileExtension );
			}
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}

	}

	/* This method is used 
	 * configure Upload File Type
	 */
	private void configureuploadFileType() throws Exception
	{

		TextBoxHelper.type( "PS_Detail_UploadFileType_name_txtId", fileExtension );

	}

	/* This method is used to click on save button*/
	private void clickOnSave() throws Exception
	{
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		psGenericHelper.detailSaveWithRetry( "PS_Detail_UploadFileType_save_BtnId", fileExtension, "File Extension" );
	}
}
