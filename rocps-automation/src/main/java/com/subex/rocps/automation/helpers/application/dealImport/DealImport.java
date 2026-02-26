package com.subex.rocps.automation.helpers.application.dealImport;

import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.Map;

import org.sikuli.script.Key;
import org.sikuli.script.Pattern;
import org.sikuli.script.Screen;

import com.subex.automation.helpers.application.NavigationHelper;
import com.subex.automation.helpers.component.ButtonHelper;
import com.subex.automation.helpers.component.CalendarHelper;
import com.subex.automation.helpers.component.ComboBoxHelper;
import com.subex.automation.helpers.component.ElementHelper;
import com.subex.automation.helpers.component.EntityComboHelper;
import com.subex.automation.helpers.component.GenericHelper;
import com.subex.automation.helpers.component.GridHelper;
import com.subex.automation.helpers.component.MouseHelper;
import com.subex.automation.helpers.component.SearchGridHelper;
import com.subex.automation.helpers.component.TextAreaHelper;
import com.subex.automation.helpers.data.ValidationHelper;
import com.subex.automation.helpers.file.ExcelReader;
import com.subex.automation.helpers.report.Log4jHelper;
import com.subex.automation.helpers.util.FailureHelper;
import com.subex.rocps.automation.helpers.application.deal.Deal;
import com.subex.rocps.automation.helpers.application.filters.GridFilterSearchHelper;
import com.subex.rocps.automation.helpers.application.filters.PSSearchGridHelper;
import com.subex.rocps.automation.helpers.application.genericHelpers.PSActionImpl;
import com.subex.rocps.automation.helpers.application.genericHelpers.PSEntityComboHelper;
import com.subex.rocps.automation.helpers.application.genericHelpers.PSGenericHelper;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;
import com.subex.rocps.automation.utils.ExcelHolder;
import com.subex.rocps.automation.utils.PSStringUtils;

public class DealImport extends PSAcceptanceTest
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
	protected String filePath;
	protected String template;
	protected String fileCollection;
	protected String fileName;
	protected String commentBox;
	protected String colmHdrs;
	protected String contractNo;
	protected int index;
	protected PSGenericHelper psGenericHelper = new PSGenericHelper();
	PSStringUtils psStringUtils = new PSStringUtils();
	PSActionImpl actionObj = new PSActionImpl();
	GridFilterSearchHelper gridHelperObj = new GridFilterSearchHelper();

	/**Constructor : Initializing the excel without occurrence
	 * @param path
	 * @param workBookName
	 * @param sheetName
	 * @param testCaseName
	 * @throws Exception 
	 */
	public DealImport( String path, String workBookName, String sheetName, String testCaseName ) throws Exception
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
	public DealImport( String path, String workBookName, String sheetName, String testCaseName, int occurence ) throws Exception
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
		filePath = ExcelHolder.getKey( map, "FilePath" );
		template = ExcelHolder.getKey( map, "Template" );
		fileName = ExcelHolder.getKey( map, "FileName" );
		clientPartition = ExcelHolder.getKey( map, "Partition" );
		contractNo = ExcelHolder.getKey( map, "ContractNo" );
	}

	/*
	 * This method is to configure Deal Import
	 */
	private void configureDealImport() throws Exception
	{
		String path = automationPath + configProp.getProperty( filePath );
		String filePathName = path + fileName;
		actionObj.clickOnActionWithPartition( "Deal Import Actions", "Import", clientPartition );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		PSEntityComboHelper.selectUsingGridFilterTextBox( "PS_Detail_dealImport_account", "Account", "paccName", account, "Account Name" );
		ButtonHelper.click( "PS_Detail_dealImport_fileTrigger" );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		PSGenericHelper.psFileUploadSikuliWithoutRobot( "PS_Detail_fileUpload_uploadTrigger", filePathName, "fileTypeFU1.png", "openButtonFU1.png" );
		ButtonHelper.click( "PS_Detail_dealImport_uploadButton" );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		ElementHelper.waitForElementToDisappear( "PS_Detail_fileUpload_uploadButton", 5 );
		if ( ValidationHelper.isEmpty( template ) )
		{
			ComboBoxHelper.select( "PS_Detail_dealImport_templateCombo", template );
		}
		ButtonHelper.click( "PS_Detail_dealImport_save" );

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
				NavigationHelper.navigateToScreen( "Deal Import" );
				GenericHelper.waitForLoadmask( searchScreenWaitSec );
				dealImportMap = excelHolderObj.dataMap( index );
				ButtonHelper.click( "SearchButton" );
				colmHdrs = ExcelHolder.getKey( dealImportMap, "SearchScreenColumns" );
				psGenericHelper.screenColumnValidation( "Input File Name", colmHdrs, "Deal Import" );
			}
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}

	}

	/*
	 * This method is for 'Deal Import'  creation
	 */
	public void dealImportCreation() throws Exception
	{
		try
		{
			for ( index = 0; index < colSize; index++ )
			{
				NavigationHelper.navigateToScreen( "Deal Import" );
				GenericHelper.waitForLoadmask( searchScreenWaitSec );
				dealImportMap = excelHolderObj.dataMap( index );
				initializeVariable( dealImportMap );
				boolean isdealImportPresent = psGenericHelper.isDataPresent( fileName, "Input File Name" );
				if ( !isdealImportPresent )
				{

					configureDealImport();
					GenericHelper.waitForLoadmask( searchScreenWaitSec );
					GenericHelper.waitForLoadmask( searchScreenWaitSec );
					Log4jHelper.logInfo( "'Deal Import' is successfully created" );

				}
				else

					Log4jHelper.logInfo( "'Deal Import' is already available " );

			}
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}

	}

	public void dealImportValidation() throws Exception
	{
		NavigationHelper.navigateToScreen( "Deal" );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		ButtonHelper.click( "ClearButton" );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		psGenericHelper.waitforHeaderElement( "Account" );
		SearchGridHelper.gridFilterSearchWithTextBox( "PS_Detail_Deal_deal_txtbxID", account, "Deal Name" );
		SearchGridHelper.gridFilterSearchWithTextBox( "PS_Detail_Deal_contractNo_txtbxID", contractNo, "Contract No" );
		assertTrue( GridHelper.isValuePresent( "SearchGrid", account, "Account" ), "Deal Import Successful" );
	}

	public void dealImportErrorValidation() throws Exception
	{
		NavigationHelper.navigateToScreen( "Deal Import Error" );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		ButtonHelper.click( "ClearButton" );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		psGenericHelper.waitforHeaderElement( "Account" );
		SearchGridHelper.gridFilterSearchWithTextBox( "dealImport$fileTbl$filFilename", fileName, "File Name" );
		assertTrue( GridHelper.isValuePresent( "SearchGrid", fileName, "File Name" ), "Deal Import Successful" );
	}

}