package com.subex.rocps.automation.helpers.application.monitoring;

import java.util.ArrayList;
import java.util.Map;

import com.subex.automation.helpers.application.NavigationHelper;
import com.subex.automation.helpers.component.ButtonHelper;
import com.subex.automation.helpers.component.CalendarHelper;
import com.subex.automation.helpers.component.ComboBoxHelper;
import com.subex.automation.helpers.component.GenericHelper;
import com.subex.automation.helpers.component.GridHelper;
import com.subex.automation.helpers.component.SearchGridHelper;
import com.subex.automation.helpers.data.DateHelper;
import com.subex.automation.helpers.data.ValidationHelper;
import com.subex.automation.helpers.file.ExcelReader;
import com.subex.automation.helpers.report.Log4jHelper;
import com.subex.automation.helpers.util.FailureHelper;

import com.subex.rocps.automation.helpers.application.filters.DataSelectionHelper;
import com.subex.rocps.automation.helpers.application.genericHelpers.PSGenericHelper;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;
import com.subex.rocps.automation.utils.ExcelHolder;
import com.subex.rocps.automation.utils.PSStringUtils;

public class PSCollectedFileSearchHelper extends PSAcceptanceTest
{

	protected ExcelReader excelData = new ExcelReader();
	protected Map<String, ArrayList<String>> collectedFlSrchExcelMap = null;
	protected Map<String, String> collectedFlSrchMap = null;
	protected ExcelHolder excelHolderObj = null;
	protected String path;
	protected String workBookName;
	protected String sheetName;
	protected String testCaseName;
	protected int occurence;
	protected String clientPartition;
	protected String fileSource;
	protected String token;
	protected String polledDt;
	protected String fileCollection;
	protected String status;
	protected String fileName;

	protected int colSize;
	protected int index;
	PSGenericHelper psGenericHelper = new PSGenericHelper();

	// Constructor : Initializing the excel without occurrence
	public PSCollectedFileSearchHelper( String path, String workBookName, String sheetName, String testCaseName ) throws Exception
	{

		this.path = path;
		this.workBookName = workBookName;
		this.sheetName = sheetName;
		this.testCaseName = testCaseName;
		collectedFlSrchExcelMap = excelData.readDataByColumn( path, workBookName, sheetName, testCaseName );
		excelHolderObj = new ExcelHolder( collectedFlSrchExcelMap );
		colSize = excelHolderObj.totalColumns();
	}

	// Constructor : Initializing the excel with occurrence
	public PSCollectedFileSearchHelper( String path, String workBookName, String sheetName, String testCaseName, int occurence ) throws Exception
	{

		this.path = path;
		this.workBookName = workBookName;
		this.sheetName = sheetName;
		this.testCaseName = testCaseName;
		this.occurence = occurence;
		collectedFlSrchExcelMap = excelData.readDataByColumn( path, workBookName, sheetName, testCaseName, occurence );
		excelHolderObj = new ExcelHolder( collectedFlSrchExcelMap );
		colSize = excelHolderObj.totalColumns();
	}

	private void initializeVariableFileSearch( Map<String, String> map ) throws Exception
	{
		clientPartition = ExcelHolder.getKey( collectedFlSrchMap, "Partition" );
		fileSource = ExcelHolder.getKey( collectedFlSrchMap, "FileSource" );
		token = ExcelHolder.getKey( collectedFlSrchMap, "Token" );
		polledDt = ExcelHolder.getKey( collectedFlSrchMap, "PolledDate" );
		fileCollection = ExcelHolder.getKey( collectedFlSrchMap, "FileCollection" );
		status = ExcelHolder.getKey( collectedFlSrchMap, "Status" );
		fileName = ExcelHolder.getKey( collectedFlSrchMap, "FileName" );

	}

	// Method: Common method to navigate to the 'Collected File Search' screen
	private void collectedFlSrchScreen() throws Exception
	{
		NavigationHelper.navigateToScreen( "Collected Files" );
		GenericHelper.waitForLoadmask( detailScreenWaitSec );
		assertEquals( NavigationHelper.getScreenTitle(), "Collected File Search" );

	}

	/*
	 * This method is to validate search screen columns
	 */
	public void searchScreenColumnsValidation() throws Exception
	{
		collectedFlSrchScreen();
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		for ( index = 0; index < colSize; index++ )
		{
			collectedFlSrchMap = excelHolderObj.dataMap( index );
			String searchScreenColumns = ExcelHolder.getKey( collectedFlSrchMap, "SearchScreenColumns" );
			ArrayList<String> excelColumnNames = new ArrayList<String>();
			PSStringUtils strObj = new PSStringUtils();
			String[] searchGridColumnsArr = strObj.stringSplitFirstLevel( searchScreenColumns );
			for ( int col = 0; col < searchGridColumnsArr.length; col++ )
			{
				excelColumnNames.add( searchGridColumnsArr[col] );
			}
			psGenericHelper.totalColumns( excelColumnNames );

			Log4jHelper.logInfo( "'Collected File Search' Columns are validated successfully" );

		}

	}

	/* This method to validate the collected CDR file */
	public void validationOfCollectedFile() throws Exception
	{
		for ( index = 0; index < colSize; index++ )
		{
			collectedFlSrchScreen();
			GenericHelper.waitForLoadmask( searchScreenWaitSec );
			collectedFlSrchMap = excelHolderObj.dataMap( index );
			initializeVariableFileSearch( collectedFlSrchMap );
			ButtonHelper.click( "ClearButton" );
			GenericHelper.waitForLoadmask( searchScreenWaitSec );
			int tryCount = 0;
			int maxWaitCount = 5;
			boolean isCollectedFilepresern = false;
			while ( true )
			{
				collectedFileSrchFilter();
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

	/* This method to validate the deleted collected CDR file */
	public void validateOfNotPresentCollectedFile() throws Exception
	{
		for ( index = 0; index < colSize; index++ )
		{
			collectedFlSrchScreen();
			GenericHelper.waitForLoadmask( searchScreenWaitSec );
			collectedFlSrchMap = excelHolderObj.dataMap( index );
			initializeVariableFileSearch( collectedFlSrchMap );
			ButtonHelper.click( "ClearButton" );
			GenericHelper.waitForLoadmask( searchScreenWaitSec );
			boolean isCollectedFilepresern = false;
			collectedFileSrchFilter();
			isCollectedFilepresern = psGenericHelper.isGridTextValuePresent( "PS_Detail_collectedFl_fileName_textID", fileName, "File Name" );
			assertFalse( isCollectedFilepresern, " This file name " + fileName + " should be deleted from " + " 'Collected File Search' screen" );
			Log4jHelper.logInfo( " This file name " + fileName + " is  deleted successfully on " + " 'Collected File Search' screen" );

		}

	}

	/* This method to validate the collected CDR file */
	public void validationOfCollectedFileWithoutFileNm() throws Exception
	{
		for ( index = 0; index < colSize; index++ )
		{
			collectedFlSrchScreen();
			GenericHelper.waitForLoadmask( searchScreenWaitSec );
			collectedFlSrchMap = excelHolderObj.dataMap( index );
			initializeVariableFileSearch( collectedFlSrchMap );
			ButtonHelper.click( "ClearButton" );
			GenericHelper.waitForLoadmask( searchScreenWaitSec );
			int tryCount = 0;
			int maxWaitCount = 5;
			boolean isCollectedFilepresern = false;
			while ( true )
			{
				collectedFileSrchFilter();
				isCollectedFilepresern = GridHelper.getRowCount( "SearchGrid" ) > 0;
				if ( isCollectedFilepresern || tryCount == maxWaitCount )
					break;
				ButtonHelper.click( "ClearButton" );
				GenericHelper.waitForLoadmask( searchScreenWaitSec );
				tryCount++;
			}
			fileName = GridHelper.getCellValue( "SearchGrid", 1, "File Name" );
			assertTrue( isCollectedFilepresern, " This file name " + fileName + " is not collected " + " 'Collected File Search' screen" );
			Log4jHelper.logInfo( " This file name " + fileName + " is  collected successfully on " + " 'Collected File Search' screen" );

		}

	}

	// This method to filter in 'Collected File Search' screen
	private void collectedFileSrchFilter() throws Exception
	{
		String currDt = DateHelper.getCurrentDateTime( "MM/dd/yyyy" );
		if ( ValidationHelper.isEmpty( polledDt ) )
			polledDt = currDt + " 00:00:00";
		Log4jHelper.logInfo( "polled dt " + polledDt );
		ComboBoxHelper.select( "PS_Detail_collectedFl_fileSrc_comboID", fileSource );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		ComboBoxHelper.select( "PS_Detail_collectedFl_token_comboID", token );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		// DataSelectionHelper.setOnDate(GenericHelper.getORProperty("PS_Detail_collectedFl_polledDt_tableID"),
		// polledDt);
		CalendarHelper.setOnDate( "options", polledDt );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		SearchGridHelper.gridFilterSearchWithComboBox( "PS_Detail_collectedFl_fileCollection_comboID", fileCollection, "File Collection" );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		SearchGridHelper.gridFilterSearchWithComboBox( "PS_Detail_collectedFl_status_comboID", status, "Status" );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );

	}

}
