package com.subex.rocps.automation.helpers.application.roaming.nrtrdeReport;

import java.util.ArrayList;
import java.util.Map;
import java.util.Optional;

import com.subex.rocps.automation.helpers.application.genericHelpers.PSGenericHelper;
import com.subex.rocps.automation.helpers.application.roaming.util.RoamingRecordsUtil;
import com.subex.rocps.automation.utils.ExcelHolder;

import com.subex.automation.helpers.file.ExcelReader;
import com.subex.automation.helpers.report.Log4jHelper;

public class NRTRDEFileDeliveryReport extends RoamingRecordsUtil
{

	protected ExcelReader excelData = new ExcelReader();
	protected Map<String, ArrayList<String>> nrtrdeFileDelRepExcelMap = null;
	protected Map<String, String> nrtrdeFileDelRepMap = null;
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
	public NRTRDEFileDeliveryReport( String path, String workBookName, String sheetName, String testCaseName ) throws Exception
	{
		this.path = path;
		this.workBookName = workBookName;
		this.sheetName = sheetName;
		this.testCaseName = testCaseName;
		nrtrdeFileDelRepExcelMap = excelData.readDataByColumn( path, workBookName, sheetName, testCaseName );
		excelHolderObj = new ExcelHolder( nrtrdeFileDelRepExcelMap );
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

	// Method: Validate the search results of 'NRTRDE File Delivery Report' screen
	public void validateNrtrdeFlDelReportSearchResult( String fileName ) throws Exception
	{
		for ( index = 0; index < colSize; index++ )
		{

			nrtrdeFileDelRepMap = excelHolderObj.dataMap( index );
			initializeVariable( nrtrdeFileDelRepMap );
			super.validateFileName( "PS_Detail_NrtrdeReport_fileNm_txtId", fileName );
			Optional<String> opt = Optional.of( searchScreenColumns );
			boolean isEmpty = opt.filter( x -> x.equals( "" ) ).isPresent();
			if ( !isEmpty )
				psGenericHelper.screenColumnValidation( "File Name", searchScreenColumns, "'NRTRDE File Delivery Report for file name " + fileName + "of Delivery Report" );
			psGenericHelper.validateSearchResult( colmHdrsSearchResult, mapkeys, nrtrdeFileDelRepMap, "PS_Detail_SearchScreen_ColumnHeaderID", "SearchGrid" );
			Log4jHelper.logInfo( "'NRTRDE File Delivery Report' results are validated successfully for file name -'" + fileName + "' of Delivery Report" );
		}

	}

}
