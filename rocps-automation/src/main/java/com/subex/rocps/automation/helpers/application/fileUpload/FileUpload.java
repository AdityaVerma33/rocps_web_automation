package com.subex.rocps.automation.helpers.application.fileUpload;

import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.Map;

import org.sikuli.script.Key;
import org.sikuli.script.Pattern;
import org.sikuli.script.Screen;

import com.subex.automation.helpers.application.NavigationHelper;
import com.subex.automation.helpers.component.ButtonHelper;
import com.subex.automation.helpers.component.ComboBoxHelper;
import com.subex.automation.helpers.component.ElementHelper;
import com.subex.automation.helpers.component.GenericHelper;
import com.subex.automation.helpers.component.GridHelper;
import com.subex.automation.helpers.component.MouseHelper;
import com.subex.automation.helpers.component.PopupHelper;
import com.subex.automation.helpers.component.TextAreaHelper;
import com.subex.automation.helpers.component.TextBoxHelper;
import com.subex.automation.helpers.file.ExcelReader;
import com.subex.automation.helpers.report.Log4jHelper;
import com.subex.automation.helpers.util.FailureHelper;
import com.subex.rocps.automation.helpers.application.genericHelpers.PSActionImpl;
import com.subex.rocps.automation.helpers.application.genericHelpers.PSGenericHelper;
import com.subex.rocps.automation.helpers.application.monitoring.PSCollectedFileSearchHelper;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;
import com.subex.rocps.automation.utils.ExcelHolder;
import com.subex.rocps.automation.utils.PSStringUtils;

public class FileUpload extends PSAcceptanceTest
{
	protected ExcelReader excelData = new ExcelReader();
	protected Map<String, ArrayList<String>> fileUploadExcelMap = null;
	protected Map<String, String> fileUploadMap = null;
	protected Map<String, String> collectedFlSrchMap = null;
	protected ExcelHolder excelHolderObj = null;
	protected String path;
	protected String workBookName;
	protected String sheetName;
	protected String testCaseName;
	protected int occurence;
	protected int colSize;
	protected String clientPartition;
	protected String category;
	protected String filePath;
	protected String fileSourceFolder;
	protected String fileCollection;
	protected String fileName;
	protected String commentBox;
	protected String colmHdrs;
	protected int index;
	protected PSGenericHelper psGenericHelper = new PSGenericHelper();
	PSStringUtils psStringUtils = new PSStringUtils();
	PSActionImpl actionObj = new PSActionImpl();
	PSGenericHelper genObj = new PSGenericHelper();

	/**Constructor : Initializing the excel without occurrence
	 * @param path
	 * @param workBookName
	 * @param sheetName
	 * @param testCaseName
	 * @throws Exception 
	 */
	public FileUpload( String path, String workBookName, String sheetName, String testCaseName ) throws Exception
	{
		this.path = path;
		this.workBookName = workBookName;
		this.sheetName = sheetName;
		this.testCaseName = testCaseName;
		fileUploadExcelMap = excelData.readDataByColumn( path, workBookName, sheetName, testCaseName );
		excelHolderObj = new ExcelHolder( fileUploadExcelMap );
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
	public FileUpload( String path, String workBookName, String sheetName, String testCaseName, int occurence ) throws Exception
	{
		this.path = path;
		this.workBookName = workBookName;
		this.sheetName = sheetName;
		this.testCaseName = testCaseName;
		this.occurence = occurence;
		fileUploadExcelMap = excelData.readDataByColumn( path, workBookName, sheetName, testCaseName, occurence );
		excelHolderObj = new ExcelHolder( fileUploadExcelMap );
		colSize = excelHolderObj.totalColumns();
	}

	/*
	 * This method is for initialize variable
	 */
	private void initializeVariable( Map<String, String> map ) throws Exception
	{
		category = ExcelHolder.getKey( map, "UploadCategory" );
		filePath = ExcelHolder.getKey( map, "FilePath" );
		fileSourceFolder = ExcelHolder.getKey( map, "FileSourceFolder" );
		fileCollection = ExcelHolder.getKey( map, "FileCollection" );
		fileName = ExcelHolder.getKey( map, "FileName" );
		clientPartition = ExcelHolder.getKey( map, "Partition" );
		commentBox = ExcelHolder.getKey( map, "CommentBox" );
	}

	/*
	 * This method is to configure File Upload
	 */
	private void configureFileUpload() throws Exception
	{
		String path=automationPath+configProp.getProperty( filePath );
		String filePathName = path + fileName;
		
		actionObj.clickOnActionWithPartition( "File Upload", "File Upload", clientPartition );
		ComboBoxHelper.select( "PS_Detail_fileUpload_categoryCombo", category );
		ComboBoxHelper.select( "PS_Detail_fileUpload_fileCollection", fileCollection );
		ButtonHelper.click( "PS_Detail_fileUpload_file" );
		PSGenericHelper.psFileUploadSikuli( "PS_Detail_fileUpload_uploadTrigger", filePathName,"fileTypeFU1.png","openButtonFU1.png" );
		ElementHelper.waitForClickableElement( "PS_Detail_fileUpload_uploadButton", 5 );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		ButtonHelper.click( "PS_Detail_fileUpload_uploadButton" );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		ElementHelper.waitForElementToDisappear( "PS_Detail_fileUpload_uploadButton", 5 );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		TextAreaHelper.type( "PS_Detail_fileUpload_commentBox", commentBox );
	}



	/*
	 * This method is for 'File Upload' screen column validation
	 */
	public void fileUploadColumnValidation() throws Exception
	{
		try
		{
			for ( index = 0; index < colSize; index++ )
			{
				NavigationHelper.navigateToScreen( "File Upload" );
				GenericHelper.waitForLoadmask( searchScreenWaitSec );
				fileUploadMap = excelHolderObj.dataMap( index );
				ButtonHelper.click( "SearchButton" );
				colmHdrs = ExcelHolder.getKey( fileUploadMap, "SearchScreenColumns" );
				psGenericHelper.screenColumnValidation( "File Name", colmHdrs, "Upload File Type" );
			}
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}

	}

	/*
	 * This method is for 'File Upload'  creation
	 */
	public void fileUploadCreation() throws Exception
	{
		try
		{
			for ( index = 0; index < colSize; index++ )
			{
				NavigationHelper.navigateToScreen( "File Upload" );
				GenericHelper.waitForLoadmask( searchScreenWaitSec );
				fileUploadMap = excelHolderObj.dataMap( index );
				initializeVariable( fileUploadMap );
				boolean isuploadFileTypePresent = psGenericHelper.isDataPresent( fileName, "File Name" );
				if ( !isuploadFileTypePresent )
				{

					configureFileUpload();
					GenericHelper.waitForLoadmask( searchScreenWaitSec );
					clickOnSave();
					GenericHelper.waitForLoadmask( searchScreenWaitSec );
					validateFileUploadOnScreen();
					validateFileInFolder();
					Log4jHelper.logInfo( "'File Upload' is successfully created" );

				}
				else

					Log4jHelper.logInfo( "'File Upload' is already avilable " );

			}
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}

	}

	/* This method is used to click on save button*/
	private void clickOnSave() throws Exception
	{
		GenericHelper.waitForLoadmask( searchScreenWaitSec );

		ElementHelper.waitForClickableElement( "PS_Detail_fileUpload_save", 5 );

		ButtonHelper.click( "PS_Detail_fileUpload_save" );
		if ( ElementHelper.isElementPresent( "PS_Detail_fileUpload_save" ) )
			ButtonHelper.click( "PS_Detail_fileUpload_save" );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		psGenericHelper.waitforHeaderElement( "Category" );

	}

	/*
	 *This Method is for validation of File Upload  on Screen.
	 */

	private void validateFileUploadOnScreen() throws Exception
	{
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		boolean isuploadFileTypePresent = psGenericHelper.isDataPresent( fileName, "File Name" );
		assertTrue( isuploadFileTypePresent );
	}

	/*
	 *This Method is for validation of File Upload in DataDir
	 */
	private void validateFileInFolder() throws Exception
	{

		File file = new File( configProp.getDataDirPath() + fileSourceFolder );

		FilenameFilter filter = new FilenameFilter()
		{

			public boolean accept( File f, String name )
			{

				boolean fileStatus = name.equals( fileName );
				return fileStatus;
			}
		};
		File[] files = file.listFiles( filter );
		assertTrue( files.length > 0, " File name contains with name '" + fileName + "' not found in the given directory " + fileSourceFolder );
		for ( int i = 0; i < files.length; i++ )
		{
			Log4jHelper.logInfo( "File name found :- \n " + files[i].getName() );

		}
	}

	/* This method to validate the collected CDR file */
	public void jumpToActionValidation() throws Exception
	{
		for ( index = 0; index < colSize; index++ )
		{
			fileUploadMap = excelHolderObj.dataMap( index );
			initializeVariable( fileUploadMap );
			NavigationHelper.navigateToScreen( "File Upload" );
			GenericHelper.waitForLoadmask( searchScreenWaitSec );
			GridHelper.clickRow( "searchGrid", 1, 1 );
			GridHelper.clickRow( "searchGrid", 1, 1 );
			actionObj.clickOnAction( "Jump To", "Collected File" );
			GenericHelper.waitForLoadmask( searchScreenWaitSec );
			int tryCount = 0;
			int maxWaitCount = 5;
			boolean isCollectedFilepresern = false;
			while ( true )
			{

				isCollectedFilepresern = psGenericHelper.isGridTextValuePresent( "PS_Detail_collectedFl_fileName_textID", fileName, "File Name" );
				if ( isCollectedFilepresern || tryCount == maxWaitCount )
					break;
				ButtonHelper.click( "ClearButton" );
				GenericHelper.waitForLoadmask( searchScreenWaitSec );
				tryCount++;
			}
			assertTrue( isCollectedFilepresern, " This file name " + fileName + " is not collected " + " 'Collected File Search' screen" );
			Log4jHelper.logInfo( " This file name " + fileName + " is  collected successfully on " + " 'Collected File Search' screen" );
		}
	}

}
