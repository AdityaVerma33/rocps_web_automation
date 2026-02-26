package com.subex.rocps.automation.helpers.application.roaming.roamingFlStatus;

import java.util.ArrayList;
import java.util.Map;

import com.subex.rocps.automation.helpers.application.genericHelpers.PSGenericHelper;
import com.subex.rocps.automation.helpers.application.roaming.util.RoamingRecordsUtil;
import com.subex.rocps.automation.utils.ExcelHolder;

import com.subex.automation.helpers.data.ValidationHelper;
import com.subex.automation.helpers.file.ExcelReader;
import com.subex.automation.helpers.report.Log4jHelper;

public class TapError extends RoamingRecordsUtil
{
	protected ExcelReader excelData = new ExcelReader();
	protected Map<String, ArrayList<String>> tapErrorExcelMap = null;
	protected Map<String, String> tapErrorMap = null;
	protected ExcelHolder excelHolderObj = null;
	protected String path;
	protected String workBookName;
	protected String sheetName;
	protected String testCaseName;
	protected int colSize;
	protected int index;
	protected String colmHdrsSearchResult;
	protected String searchScreenColumns;
	protected String context;
	protected String eventDate;
	protected String severity;
	protected String mapkeys;
	PSGenericHelper psGenericHelper = new PSGenericHelper();

	/**Constructor
	 * 
	 * @param path
	 * @param workBookName
	 * @param sheetName
	 * @param testCaseName
	 * @throws Exception 
	 */
	public TapError( String path, String workBookName, String sheetName, String testCaseName ) throws Exception
	{
		this.path = path;
		this.workBookName = workBookName;
		this.sheetName = sheetName;
		this.testCaseName = testCaseName;
		tapErrorExcelMap = excelData.readDataByColumn( path, workBookName, sheetName, testCaseName );
		excelHolderObj = new ExcelHolder( tapErrorExcelMap );
		colSize = excelHolderObj.totalColumns();
	}

	/*
	 * This method is for initialize variable
	 */
	private void initializeVariable( Map<String, String> map ) throws Exception
	{

		mapkeys = ExcelHolder.getKey( map, "MapRowKeys" );
		colmHdrsSearchResult = ExcelHolder.getKey( map, "ColmnHeadersRecordScreen" );
		context = ExcelHolder.getKey( map, "Context" );
		eventDate = ExcelHolder.getKey( map, "EventDate" );
		severity = ExcelHolder.getKey( map, "Severity" );
		searchScreenColumns = ExcelHolder.getKey( map, "SearchScreenColumns" );

	}

	// Method: Validate the search results of 'Tap Error Screen' screen
	public void validateTapErrorSearchResult( String fileName ) throws Exception
	{
		for ( index = 0; index < colSize; index++ )
		{

			tapErrorMap = excelHolderObj.dataMap( index );
			initializeVariable( tapErrorMap );
			super.filterSelection( "PS_Detail_RoamFlStatus_TapErr_context_comboId", context, "PS_Detail_RoamFlStatus_TapErr_eventDt_textId", eventDate, "PS_Detail_RoamFlStatus_TapErr_severity_comboId", severity, "PS_Detail_TapError_ContextHeader_xpath" );
			if ( ValidationHelper.isNotEmpty( searchScreenColumns ) )
				psGenericHelper.screenColumnValidation( "Error Code", searchScreenColumns, "'Tap Error Screen' for file name " + fileName + " of context " + context );
			psGenericHelper.validateSearchResult( colmHdrsSearchResult, mapkeys, tapErrorMap, "PS_Detail_SearchScreen_ColumnHeaderID", "SearchGrid" );
			Log4jHelper.logInfo( "'Tap Error Screen' results are validated successfully for file name -'" + fileName + "' of callType " + context );
		}

	}
}
