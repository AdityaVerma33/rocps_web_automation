package com.subex.rocps.automation.helpers.application.roaming.nrtrdeReport;

import java.util.ArrayList;
import java.util.Map;
import java.util.Optional;

import com.subex.rocps.automation.helpers.application.genericHelpers.PSGenericHelper;
import com.subex.rocps.automation.helpers.application.roaming.util.RoamingRecordsUtil;
import com.subex.rocps.automation.utils.ExcelHolder;

import com.subex.automation.helpers.file.ExcelReader;
import com.subex.automation.helpers.report.Log4jHelper;

public class NRTRDEErrorReport extends RoamingRecordsUtil
{

	protected ExcelReader excelData = new ExcelReader();
	protected Map<String, ArrayList<String>> nrtrdeErrorReportExcelMap = null;
	protected Map<String, String> nrtrdeErrorReportMap = null;
	protected ExcelHolder excelHolderObj = null;
	protected String path;
	protected String workBookName;
	protected String sheetName;
	protected String testCaseName;
	protected int colSize;
	protected int index;
	protected String colmHdrsSearchResult;
	protected String searchScreenColumns;
	protected String mapkeys;
	PSGenericHelper psGenericHelper = new PSGenericHelper();

	/**Constructor
	 * @param path
	 * @param workBookName
	 * @param sheetName
	 * @param testCaseName
	 * @throws Exception 
	 */
	public NRTRDEErrorReport( String path, String workBookName, String sheetName, String testCaseName ) throws Exception
	{

		this.path = path;
		this.workBookName = workBookName;
		this.sheetName = sheetName;
		this.testCaseName = testCaseName;
		nrtrdeErrorReportExcelMap = excelData.readDataByColumn( path, workBookName, sheetName, testCaseName );
		excelHolderObj = new ExcelHolder( nrtrdeErrorReportExcelMap );
		colSize = excelHolderObj.totalColumns();
	}

	/*
	 * This method is for initialize variable
	 */
	private void initializeVariable( Map<String, String> map ) throws Exception
	{

		mapkeys = ExcelHolder.getKey( map, "MapRowKeys" );
		colmHdrsSearchResult = ExcelHolder.getKey( map, "ColmnHeadersRecordScreen" );
		searchScreenColumns = ExcelHolder.getKey( map, "SearchScreenColumns" );

	}

	// Method: Validate the search results of 'NRTRDE Error Report' screen
	public void validateNrtrdeErrReportSearchResult( String fileName ) throws Exception
	{
		for ( index = 0; index < colSize; index++ )
		{

			nrtrdeErrorReportMap = excelHolderObj.dataMap( index );
			initializeVariable( nrtrdeErrorReportMap );
			super.validateFileName( "PS_Detail_NrtrdeReport_fileNm_txtId", fileName );
			Optional<String> opt = Optional.of( searchScreenColumns );
			boolean isEmpty = opt.filter( x -> x.equals( "" ) ).isPresent();
			if ( !isEmpty )
				psGenericHelper.screenColumnValidation( "File Name", searchScreenColumns, "'NRTRDE Error Report' for file name " + fileName + " of Errror Report " );
			psGenericHelper.validateSearchResult( colmHdrsSearchResult, mapkeys, nrtrdeErrorReportMap, "PS_Detail_SearchScreen_ColumnHeaderID", "SearchGrid" );
			Log4jHelper.logInfo( "'NRTRDE Error Report' results are validated successfully for file name -'" + fileName + "'  of Errror Report  " );
		}

	}
}
