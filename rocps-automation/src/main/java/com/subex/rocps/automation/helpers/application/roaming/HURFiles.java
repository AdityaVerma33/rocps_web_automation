package com.subex.rocps.automation.helpers.application.roaming;

import java.util.ArrayList;
import java.util.Map;

import com.subex.rocps.automation.helpers.application.filters.PSSearchGridHelper;
import com.subex.rocps.automation.helpers.application.genericHelpers.PSActionImpl;
import com.subex.rocps.automation.helpers.application.genericHelpers.PSGenericHelper;
import com.subex.rocps.automation.helpers.application.roaming.hurFiles.HURFilesActionImpl;
import com.subex.rocps.automation.helpers.application.roaming.nrtrdeFlStatus.NRTRDERecordsScreen;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;
import com.subex.rocps.automation.utils.ExcelHolder;
import com.subex.rocps.automation.utils.PSStringUtils;

import com.subex.automation.helpers.application.NavigationHelper;
import com.subex.automation.helpers.component.ButtonHelper;
import com.subex.automation.helpers.component.ElementHelper;
import com.subex.automation.helpers.component.GenericHelper;
import com.subex.automation.helpers.component.GridHelper;
import com.subex.automation.helpers.component.PopupHelper;
import com.subex.automation.helpers.component.TextBoxHelper;
import com.subex.automation.helpers.data.ValidationHelper;
import com.subex.automation.helpers.file.ExcelReader;
import com.subex.automation.helpers.report.Log4jHelper;
import com.subex.automation.helpers.util.FailureHelper;

public class HURFiles extends PSAcceptanceTest
{
	protected ExcelReader excelData = new ExcelReader();
	protected Map<String, ArrayList<String>> hurFilesExcelMap = null;
	protected Map<String, String> hurFilesMap = null;
	protected ExcelHolder excelHolderObj = null;
	protected String path;
	protected String workBookName;
	protected String sheetName;
	protected String testCaseName;
	protected int occurence;
	protected String colmHdrs;
	protected int colSize;
	protected int index;
	protected String fileNm;
	protected String isNotificationFl;
	protected String sender;
	protected String recipent;
	protected String mapkeys;
	PSGenericHelper psGenericHelper = new PSGenericHelper();
	PSStringUtils psStringUtils = new PSStringUtils();
	PSActionImpl psActionImpl = new PSActionImpl();

	/**Constructor : Initializing the excel without occurrence
	 * @param path
	 * @param workBookName
	 * @param sheetName
	 * @param testCaseName
	 * @throws Exception 
	 */
	public HURFiles( String path, String workBookName, String sheetName, String testCaseName ) throws Exception
	{

		this.path = path;
		this.workBookName = workBookName;
		this.sheetName = sheetName;
		this.testCaseName = testCaseName;
		hurFilesExcelMap = excelData.readDataByColumn( path, workBookName, sheetName, testCaseName );
		excelHolderObj = new ExcelHolder( hurFilesExcelMap );
		colSize = excelHolderObj.totalColumns();
	}

	/**Constructor :  Initializing the excel with occurrence
	 * @param path
	 * @param workBookName
	 * @param sheetName
	 * @param testCaseName
	 * @param occurence
	 * @throws Exception 
	 */
	public HURFiles( String path, String workBookName, String sheetName, String testCaseName, int occurence ) throws Exception
	{
		this.path = path;
		this.workBookName = workBookName;
		this.sheetName = sheetName;
		this.testCaseName = testCaseName;
		this.occurence = occurence;
		hurFilesExcelMap = excelData.readDataByColumn( path, workBookName, sheetName, testCaseName, occurence );
		excelHolderObj = new ExcelHolder( hurFilesExcelMap );
		colSize = excelHolderObj.totalColumns();
	}

	/*
	 * This method is for initialize variable
	 */
	private void initializeVariable( Map<String, String> map ) throws Exception
	{
		isNotificationFl = ExcelHolder.getKey( map, "IsNotificationFile" );
		fileNm = ExcelHolder.getKey( map, "FileName" );
		sender = ExcelHolder.getKey( map, "Sender" );
		recipent = ExcelHolder.getKey( map, "Recipient" );
	}

	/*
	 * This method is for 'HUR Files' screen common method
	 */
	private void hurFilesScreen() throws Exception
	{
		NavigationHelper.navigateToScreen( "HUR Files" );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		hurFilesMap = excelHolderObj.dataMap( index );
		ButtonHelper.click( "ClearButton" );
		psGenericHelper.waitforHeaderElement( "File Name" );
	}

	/*
	 * This method is for 'HUR Files' screen column validation
	 */
	public void hurFilesColumsValidation() throws Exception
	{
		try
		{
			for ( index = 0; index < colSize; index++ )
			{
				hurFilesScreen();
				colmHdrs = ExcelHolder.getKey( hurFilesMap, "SearchScreenColumns" );
				psGenericHelper.screenColumnValidation( "File Name", colmHdrs, "HUR Files" );
			}
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}

	}

	// Method: Validate the search results of 'HUR Files' screen
	public void validateHurFilesSearchResult() throws Exception
	{
		try
		{
			for ( index = 0; index < colSize; index++ )
			{
				hurFilesScreen();
				ButtonHelper.click( "SearchButton" );
				psGenericHelper.waitforHeaderElement( "File Name" );
				mapkeys = ExcelHolder.getKey( hurFilesMap, "MapRowKeys" );
				colmHdrs = ExcelHolder.getKey( hurFilesMap, "ColmnHeaders" );
				psGenericHelper.validateSearchResult( colmHdrs, mapkeys, hurFilesMap, "PS_Detail_SearchScreen_ColumnHeaderID", "SearchGrid" );
				Log4jHelper.logInfo( "'HUR Files' results are validated successfully" );
			}
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

	/*
	 * This method is for 'HUR Filess'  'Email HUR' action
	 */
	public void hurFilesStatusEmailHURAction() throws Exception
	{
		try
		{
			for ( index = 0; index < colSize; index++ )
			{
				hurFilesScreen();
				initializeVariable( hurFilesMap );
				boolean isHurFilesPresent = isHurFilesPresent();
				if ( isHurFilesPresent )
				{
					GridHelper.clickRow( "SearchGrid", fileNm, "File Name" );
					HURFilesActionImpl hurFilesActionImpl = new HURFilesActionImpl();
					hurFilesActionImpl.clickOnEmailHurAction();
					Log4jHelper.logInfo( "'HUR Files'' is  succesffuly validated for 'Email HUR' action with file name:" + fileNm );
				}
				else

					Log4jHelper.logInfo( "'HUR Files'' is not avilable with file name:" + fileNm );
			}
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}

	}

	/*
	 * This method is for 'HUR Filess' present or not
	 */
	private boolean isHurFilesPresent() throws Exception
	{
		TextBoxHelper.type( "PS_Detail_HurFiles_fileNm_txtId", fileNm );
		ButtonHelper.click( "SearchButton" );
		psGenericHelper.waitforHeaderElement( "File Name" );
		if ( ValidationHelper.isNotEmpty( isNotificationFl ) )
			PSSearchGridHelper.gridFilterSearchWithComboBox( "PS_Detail_HurFiles_isNotificFl_comboId", isNotificationFl, "Is Notification File" );
		if ( ValidationHelper.isNotEmpty( sender ) )
			psGenericHelper.isGridTextValuePresent( "PS_Detail_HurFiles_sender_txtId", sender, "Sender" );
		if ( ValidationHelper.isNotEmpty( recipent ) )
			psGenericHelper.isGridTextValuePresent( "PS_Detail_HurFiles_recipient_txtId", recipent, "Recepient" );
		return GridHelper.isValuePresent( "SearchGrid", fileNm );
	}
}
