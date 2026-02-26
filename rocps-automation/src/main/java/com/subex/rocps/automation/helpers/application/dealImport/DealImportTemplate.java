package com.subex.rocps.automation.helpers.application.dealImport;

import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.Map;

import org.sikuli.script.Key;
import org.sikuli.script.Pattern;
import org.sikuli.script.Screen;

import com.google.common.io.Files;
import com.subex.automation.helpers.application.NavigationHelper;
import com.subex.automation.helpers.component.ButtonHelper;
import com.subex.automation.helpers.component.ComboBoxHelper;
import com.subex.automation.helpers.component.ElementHelper;
import com.subex.automation.helpers.component.EntityComboHelper;
import com.subex.automation.helpers.component.GenericHelper;
import com.subex.automation.helpers.component.MouseHelper;
import com.subex.automation.helpers.data.ValidationHelper;
import com.subex.automation.helpers.file.ExcelReader;
import com.subex.automation.helpers.report.Log4jHelper;
import com.subex.automation.helpers.util.FailureHelper;
import com.subex.rocps.automation.helpers.application.genericHelpers.PSActionImpl;
import com.subex.rocps.automation.helpers.application.genericHelpers.PSGenericHelper;
import com.subex.rocps.automation.helpers.application.system.PSTaskSearchHelper;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;
import com.subex.rocps.automation.utils.ExcelHolder;
import com.subex.rocps.automation.utils.PSFileValidationHelper;
import com.subex.rocps.automation.utils.PSStringUtils;

public class DealImportTemplate extends PSAcceptanceTest
{
	protected ExcelReader excelData = new ExcelReader();
	protected Map<String, ArrayList<String>> dealImportExcelMap = null;
	protected Map<String, String> dealImportMap = null;
	protected Map<String, String> collectedFlSrchMap = null;
	protected ExcelHolder excelHolderObj = null;
	protected String path;
	protected String workBookName;
	protected String sheetName;
	protected String testCaseName;
	protected int occurence;
	protected int colSize;
	protected String clientPartition;
	protected String account;
	protected String fromDate;
	protected String toDate;
	protected String elements;
	protected String bands;
	protected String trafficType;
	protected String colmHdrs;
	protected String backupFolder;
	protected String templateFolder;
	PSTaskSearchHelper tskObj = new PSTaskSearchHelper();
	protected int index;
	protected PSGenericHelper psGenericHelper = new PSGenericHelper();
	PSStringUtils psStringUtils = new PSStringUtils();
	PSActionImpl actionObj = new PSActionImpl();

	/**Constructor : Initializing the excel without occurrence
	 * @param path
	 * @param workBookName
	 * @param sheetName
	 * @param testCaseName
	 * @throws Exception 
	 */
	public DealImportTemplate( String path, String workBookName, String sheetName, String testCaseName ) throws Exception
	{
		this.path = path;
		this.workBookName = workBookName;
		this.sheetName = sheetName;
		this.testCaseName = testCaseName;
		dealImportExcelMap = excelData.readDataByColumn( path, workBookName, sheetName, testCaseName );
		excelHolderObj = new ExcelHolder( dealImportExcelMap );
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
	public DealImportTemplate( String path, String workBookName, String sheetName, String testCaseName, int occurence ) throws Exception
	{
		this.path = path;
		this.workBookName = workBookName;
		this.sheetName = sheetName;
		this.testCaseName = testCaseName;
		this.occurence = occurence;
		dealImportExcelMap = excelData.readDataByColumn( path, workBookName, sheetName, testCaseName, occurence );
		excelHolderObj = new ExcelHolder( dealImportExcelMap );
		colSize = excelHolderObj.totalColumns();
	}

	/*
	 * This method is for initialize variable
	 */
	private void initializeVariable( Map<String, String> map ) throws Exception
	{
		account = ExcelHolder.getKey( map, "Account" );
		fromDate = ExcelHolder.getKey( map, "FromDate" );
		toDate = ExcelHolder.getKey( map, "ToDate" );
		elements = ExcelHolder.getKey( map, "Elements" );
		bands = ExcelHolder.getKey( map, "Bands" );
		trafficType = ExcelHolder.getKey( map, "TrafficType" );
		clientPartition = ExcelHolder.getKey( map, "Partition" );
		templateFolder = ExcelHolder.getKey( map, "TemplateFolder" );
		backupFolder = ExcelHolder.getKey( map, "BackupFolder" );
	}

	/*
	 * This method is for 'Deal Import' screen column validation
	 */
	public void dealImportColumnValidation() throws Exception
	{
		try
		{
			for ( index = 0; index < colSize; index++ )
			{
				NavigationHelper.navigateToScreen( "Deal Import Template" );
				GenericHelper.waitForLoadmask( searchScreenWaitSec );
				dealImportMap = excelHolderObj.dataMap( index );
				ButtonHelper.click( "SearchButton" );
				colmHdrs = ExcelHolder.getKey( dealImportMap, "SearchScreenColumns" );
				psGenericHelper.screenColumnValidation( "Account", colmHdrs, "Deal Import Template" );
			}
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}

	}

	/*
	 * This method is for 'Deal Import Template'  creation
	 */
	public void dealImportTemplateCreation() throws Exception
	{
		try
		{
			for ( index = 0; index < colSize; index++ )
			{
				NavigationHelper.navigateToScreen( "Deal Import Template" );
				GenericHelper.waitForLoadmask( searchScreenWaitSec );
				dealImportMap = excelHolderObj.dataMap( index );
				initializeVariable( dealImportMap );
				moveDealImportTemplateInFolder();
				moveTemplateFileToDataDir();
				NavigationHelper.navigateToNew( clientPartition );
				DealImportTemplateImpl implObj = new DealImportTemplateImpl();
				implObj.headerDetails( account, fromDate, toDate );
				implObj.selectTrafficType( trafficType );
				implObj.selectElements( elements );
				implObj.selectBands( bands );
				implObj.clickOnSave(account,"Account");
				GenericHelper.waitForLoadmask( searchScreenWaitSec );
				GenericHelper.waitForLoadmask( searchScreenWaitSec );
				tskObj.psVerifyTaskStatus(path, workBookName, sheetName, "DealImportTemplateStatus", 1);
				PSFileValidationHelper fileObj = new PSFileValidationHelper();
				fileObj.validatePartialFileNameInDir( templateFolder, account, "xlsx" );
				Log4jHelper.logInfo( "'Deal Import Template' is successfully created" );

			}
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}

	}
	/*
	 * This method is to move existing Deal Import templates to backup folder
	 */
	public void moveDealImportTemplateInFolder() throws Exception{

			File file = new File(configProp.getDataDirPath()+configProp.getProperty( templateFolder ));

			FilenameFilter filter = new FilenameFilter() {

				public boolean accept(File f, String name) {

					boolean fileStatus = (name.endsWith("xlsx") && name.contains(account));
					return fileStatus;
				}
			};
			File[] files = file.listFiles(filter);
			File backup = new File(configProp.getDataDirPath()+configProp.getProperty( backupFolder ));
			if(!backup.exists()){
				backup.mkdir();
				Log4jHelper.logInfo( "Backup Folder Created" );
			}
			for (int i = 0; i < files.length; i++) {
				File tempFile = new File(configProp.getDataDirPath()+configProp.getProperty( backupFolder )+files[i].getName()) ;
				Files.move( files[i], tempFile );
				Log4jHelper.logInfo( "File "+files[i].getName()+" moved." );
		}
	}
	/*
	 * This method is to move Template File to Deal Import Excel Writer Folder
	 */
	public void moveTemplateFileToDataDir() throws Exception{
		File templateFile = new File(automationPath+configProp.getProperty( "dealImport" )+"TemplateFile.xlsx");
		File dataDir = new File(configProp.getDataDirPath()+configProp.getProperty( templateFolder )+"TemplateFile.xlsx");
		if(!dataDir.exists())
			Files.copy( templateFile, dataDir );
	}
}

