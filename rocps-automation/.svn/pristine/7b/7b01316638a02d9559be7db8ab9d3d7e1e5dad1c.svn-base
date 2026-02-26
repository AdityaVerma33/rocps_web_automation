package com.subex.rocps.automation.helpers.application.roaming;

import java.util.ArrayList;
import java.util.Map;

import com.subex.rocps.automation.helpers.application.filters.PSSearchGridHelper;
import com.subex.rocps.automation.helpers.application.genericHelpers.PSActionImpl;
import com.subex.rocps.automation.helpers.application.genericHelpers.PSGenericHelper;
import com.subex.rocps.automation.helpers.application.roaming.nrtrdeFlStatus.NRTRDEFlStatusActionImpl;
import com.subex.rocps.automation.helpers.application.roaming.nrtrdeFlStatus.NRTRDERecordsScreen;
import com.subex.rocps.automation.helpers.application.roaming.nrtrdeReport.NRTRDEFileDeliveryReport;
import com.subex.rocps.automation.helpers.application.roaming.testSIMManagement.TestSIMManagementAction;
import com.subex.rocps.automation.helpers.application.roaming.testSIMManagement.TestSIMManagementDetails;
import com.subex.rocps.automation.helpers.application.system.PSTaskSearchHelper;
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

public class NRTRDEFileStatus extends PSAcceptanceTest
{
	protected ExcelReader excelData = new ExcelReader();
	protected Map<String, ArrayList<String>> nrtrdeFileStatusExcelMap = null;
	protected Map<String, String> nrtrdeFileStatusMap = null;
	protected ExcelHolder excelHolderObj = null;
	protected String path;
	protected String workBookName;
	protected String sheetName;
	protected String testCaseName;
	protected int occurence;
	protected String clientPartition;
	protected String colmHdrs;
	protected int colSize;
	protected int index;
	protected String direction;
	protected String fileNm;
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
	public NRTRDEFileStatus( String path, String workBookName, String sheetName, String testCaseName ) throws Exception
	{

		this.path = path;
		this.workBookName = workBookName;
		this.sheetName = sheetName;
		this.testCaseName = testCaseName;
		nrtrdeFileStatusExcelMap = excelData.readDataByColumn( path, workBookName, sheetName, testCaseName );
		excelHolderObj = new ExcelHolder( nrtrdeFileStatusExcelMap );
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
	public NRTRDEFileStatus( String path, String workBookName, String sheetName, String testCaseName, int occurence ) throws Exception
	{

		this.path = path;
		this.workBookName = workBookName;
		this.sheetName = sheetName;
		this.testCaseName = testCaseName;
		this.occurence = occurence;
		nrtrdeFileStatusExcelMap = excelData.readDataByColumn( path, workBookName, sheetName, testCaseName, occurence );
		excelHolderObj = new ExcelHolder( nrtrdeFileStatusExcelMap );
		colSize = excelHolderObj.totalColumns();
	}

	/*
	 * This method is for initialize variable
	 */
	private void initializeVariable( Map<String, String> map ) throws Exception
	{
		direction = ExcelHolder.getKey( map, "Direction" );
		fileNm = ExcelHolder.getKey( map, "FileName" );
		sender = ExcelHolder.getKey( map, "Sender" );
		recipent = ExcelHolder.getKey( map, "Recipient" );
		clientPartition = ExcelHolder.getKey( map, "Partition" );
	}

	/*
	 * This method is for 'NRTRDE File Status' screen common method
	 */
	private void nrtrdeFileStatusScreen() throws Exception
	{
		NavigationHelper.navigateToScreen( "NRTRDE File Status" );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		nrtrdeFileStatusMap = excelHolderObj.dataMap( index );
		ButtonHelper.click( "ClearButton" );
		psGenericHelper.waitforHeaderElement( "Direction" );
	}

	/*
	 * This method is for 'NRTRDE File Status' screen column validation
	 */
	public void nrtrdeFileStatusColumsValidation() throws Exception
	{
		try
		{
			for ( index = 0; index < colSize; index++ )
			{
				nrtrdeFileStatusScreen();
				colmHdrs = ExcelHolder.getKey( nrtrdeFileStatusMap, "SearchScreenColumns" );
				psGenericHelper.screenColumnValidation( "Direction", colmHdrs, "NRTRDE File Status" );
			}
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}

	}

	// Method: Validate the search results of 'NRTRDE File Status' screen
	public void validateNrtrdeReportSearchResult() throws Exception
	{
		try
		{
			for ( index = 0; index < colSize; index++ )
			{
				nrtrdeFileStatusScreen();
				ButtonHelper.click( "SearchButton" );
				psGenericHelper.waitforHeaderElement( "Direction" );
				GenericHelper.waitForLoadmask( searchScreenWaitSec );
				mapkeys = ExcelHolder.getKey( nrtrdeFileStatusMap, "MapRowKeys" );
				colmHdrs = ExcelHolder.getKey( nrtrdeFileStatusMap, "ColmnHeaders" );
				psGenericHelper.validateSearchResult( colmHdrs, mapkeys, nrtrdeFileStatusMap, "PS_Detail_SearchScreen_ColumnHeaderID", "SearchGrid" );
				Log4jHelper.logInfo( "'NRTRDE File Status' results are validated successfully" );
			}
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}

	}

	/*
	 * This method is for 'NRTRDE File Status'  view record action
	 */
	public void nrtrdeFileStatusViewRecordsAction() throws Exception
	{
		try
		{
			for ( index = 0; index < colSize; index++ )
			{
				nrtrdeFileStatusScreen();
				initializeVariable( nrtrdeFileStatusMap );
				boolean isNRTRDEFilePresent = isNRTRDEFilePresent();
				if ( isNRTRDEFilePresent )
				{
					String testCase = ExcelHolder.getKey( nrtrdeFileStatusMap, "ViewRecordsTestCase" );
					GridHelper.clickRow( "SearchGrid", fileNm, "File Name" );
					NRTRDEFlStatusActionImpl nrFlStatusActionImpl = new NRTRDEFlStatusActionImpl();
					nrFlStatusActionImpl.clickOnViewRecordsAction();
					NRTRDERecordsScreen nrtrdeRecordsScreen = new NRTRDERecordsScreen( path, workBookName, sheetName, testCase );
					nrtrdeRecordsScreen.validateNrtrdeRecordSearchResult( fileNm );
				}
				else

					Log4jHelper.logInfo( "'NRTRDE File Status'' is not avilable with file name:" + fileNm );
			}
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}

	}

	/*
	 * This method is for 'NRTRDE File Status' present or not
	 */
	private boolean isNRTRDEFilePresent() throws Exception
	{
		if ( ValidationHelper.isNotEmpty( direction ) )
			PSSearchGridHelper.gridFilterSearchWithComboBox( "PS_Detail_NrtrdeFlStatus_direc_comboId", direction, "Direction" );
		if ( ValidationHelper.isNotEmpty( sender ) )
			psGenericHelper.isGridTextValuePresent( "PS_Detail_NrtrdeFlStatus_sender_txtId", sender, "Sender" );
		if ( ValidationHelper.isNotEmpty( recipent ) )
			psGenericHelper.isGridTextValuePresent( "PS_Detail_NrtrdeFlStatus_recipient_txtId", recipent, "Recipient" );
		return psGenericHelper.isGridTextValuePresent( "PS_Detail_NrtrdeFlStatus_fileNm_txtId", fileNm, "File Name" );
	}
}
