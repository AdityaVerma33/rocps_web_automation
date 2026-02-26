package com.subex.rocps.automation.helpers.application.roaming;

import java.util.ArrayList;
import java.util.Map;

import com.subex.rocps.automation.helpers.application.filters.PSSearchGridHelper;
import com.subex.rocps.automation.helpers.application.genericHelpers.PSActionImpl;
import com.subex.rocps.automation.helpers.application.genericHelpers.PSGenericHelper;
import com.subex.rocps.automation.helpers.application.roaming.tapOutErrExclRecords.TapOutErrExclRecordsActImpl;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;
import com.subex.rocps.automation.utils.ExcelHolder;
import com.subex.rocps.automation.utils.PSStringUtils;

import com.subex.automation.helpers.application.NavigationHelper;
import com.subex.automation.helpers.component.ButtonHelper;
import com.subex.automation.helpers.component.GenericHelper;
import com.subex.automation.helpers.component.GridHelper;
import com.subex.automation.helpers.data.ValidationHelper;
import com.subex.automation.helpers.file.ExcelReader;
import com.subex.automation.helpers.report.Log4jHelper;
import com.subex.automation.helpers.util.FailureHelper;

public class TapOutErrExclRecords extends PSAcceptanceTest
{
	protected ExcelReader excelData = new ExcelReader();
	protected Map<String, ArrayList<String>> tapOutErrExclRecordsExcelMap = null;
	protected Map<String, String> tapOutErrExclRecordsMap = null;
	protected ExcelHolder excelHolderObj = null;
	protected String path;
	protected String workBookName;
	protected String sheetName;
	protected String testCaseName;
	protected int occurence;
	protected String colmHdrs;
	protected int colSize;
	protected int index;
	protected String recipentTadig;
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
	public TapOutErrExclRecords( String path, String workBookName, String sheetName, String testCaseName ) throws Exception
	{

		this.path = path;
		this.workBookName = workBookName;
		this.sheetName = sheetName;
		this.testCaseName = testCaseName;
		tapOutErrExclRecordsExcelMap = excelData.readDataByColumn( path, workBookName, sheetName, testCaseName );
		excelHolderObj = new ExcelHolder( tapOutErrExclRecordsExcelMap );
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
	public TapOutErrExclRecords( String path, String workBookName, String sheetName, String testCaseName, int occurence ) throws Exception
	{
		this.path = path;
		this.workBookName = workBookName;
		this.sheetName = sheetName;
		this.testCaseName = testCaseName;
		this.occurence = occurence;
		tapOutErrExclRecordsExcelMap = excelData.readDataByColumn( path, workBookName, sheetName, testCaseName, occurence );
		excelHolderObj = new ExcelHolder( tapOutErrExclRecordsExcelMap );
		colSize = excelHolderObj.totalColumns();
	}

	/*
	 * This method is for initialize variable
	 */
	private void initializeVariable( Map<String, String> map ) throws Exception
	{
		recipentTadig = ExcelHolder.getKey( map, "RecipientTadig" );

	}

	/*
	 * This method is for 'TAP Out Errored & Excluded Records' screen common method
	 */
	private void tapOutErrExclRecordsScreen() throws Exception
	{
		NavigationHelper.navigateToScreen( "TAP Out Errored & Excluded Records" );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		tapOutErrExclRecordsMap = excelHolderObj.dataMap( index );
		ButtonHelper.click( "ClearButton" );
		psGenericHelper.waitforHeaderElement( "Recipient TADIG" );
	}

	/*
	 * This method is for 'TAP Out Errored & Excluded Records' screen column validation
	 */
	public void tapOutErrExclRecordsColumsValidation() throws Exception
	{
		try
		{
			for ( index = 0; index < colSize; index++ )
			{
				tapOutErrExclRecordsScreen();
				colmHdrs = ExcelHolder.getKey( tapOutErrExclRecordsMap, "SearchScreenColumns" );
				psGenericHelper.screenColumnValidation( "Recipient TADIG", colmHdrs, "TAP Out Errored & Excluded Records" );
			}
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}

	}

	// Method: Validate the search results of 'TAP Out Errored & Excluded Records' screen
	public void validateTapOutErrExclRecordSearchResult() throws Exception
	{
		try
		{
			for ( index = 0; index < colSize; index++ )
			{
				tapOutErrExclRecordsScreen();
				initializeVariable( tapOutErrExclRecordsMap );
				boolean isRecipientTadigPresent = isRecipientTadigPresent();
				assertTrue( isRecipientTadigPresent, "No data found is search screen with recipentTadig: " + recipentTadig );
				psGenericHelper.waitforHeaderElement( "Recipient TADIG" );
				mapkeys = ExcelHolder.getKey( tapOutErrExclRecordsMap, "MapRowKeys" );
				colmHdrs = ExcelHolder.getKey( tapOutErrExclRecordsMap, "ColmnHeaders" );
				psGenericHelper.sortColumnHeaderGrid( "PS_Detail_tapOutErrExclRec_taskDtTime_IconID", "PS_Detail_tapOutErrExclRec_taskDtTime_menuID" );
				GenericHelper.waitForLoadmask( searchScreenWaitSec );
				psGenericHelper.waitforHeaderElement( "Recipient TADIG" );
				psGenericHelper.validateSearchResult( colmHdrs, mapkeys, tapOutErrExclRecordsMap, "PS_Detail_SearchScreen_ColumnHeaderID", "SearchGrid" );
				Log4jHelper.logInfo( "'TAP Out Errored & Excluded Records' results are validated successfully" );

				performActionTask();
			}
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;

		}
	}

	// Method: click On action of 'TAP Out Errored & Excluded Records' screen
	public void performActionTask() throws Exception
	{
		try
		{
			for ( index = 0; index < colSize; index++ )
			{
				String excludedAction = tapOutErrExclRecordsMap.get( "ExcludedRecordsAction" );
				String erroredAction = tapOutErrExclRecordsMap.get( "ErroredRecordsAction" );
				GenericHelper.waitForLoadmask( searchScreenWaitSec );
				GridHelper.clickRow( "SearchGrid", 1, "Recipient TADIG" );
				GenericHelper.waitForLoadmask( searchScreenWaitSec );
			
				if ( ValidationHelper.isNotEmpty( excludedAction ) )
				{
					TapOutErrExclRecordsActImpl tAPOutErrExclRecordsActImpl = new TapOutErrExclRecordsActImpl( path, workBookName, sheetName, excludedAction );
					tAPOutErrExclRecordsActImpl.clickOnAction( "View", "Excluded Records" );
				}
				if ( ValidationHelper.isNotEmpty( erroredAction ) )
				{
					TapOutErrExclRecordsActImpl tAPOutErrExclRecordsActImpl = new TapOutErrExclRecordsActImpl( path, workBookName, sheetName, erroredAction );
					tAPOutErrExclRecordsActImpl.clickOnAction( "View", "Errored Records" );
				}
			}
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

	private boolean isRecipientTadigPresent() throws Exception
	{
		PSSearchGridHelper.gridFilterSearchWithComboBox( "PS_Detail_tapOutErrExclRec_recipientTadig_comboId", recipentTadig, "Recipient TADIG" );
		return GridHelper.isValuePresent( "SearchGrid", recipentTadig, "Recipient TADIG" );
	}
}
