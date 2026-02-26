package com.subex.rocps.automation.helpers.application.roaming.roamingFlStatus.callEventDetails;

import java.util.ArrayList;
import java.util.Map;

import com.subex.automation.helpers.component.ButtonHelper;
import com.subex.automation.helpers.component.GenericHelper;
import com.subex.automation.helpers.data.ValidationHelper;
import com.subex.automation.helpers.file.ExcelReader;
import com.subex.automation.helpers.report.Log4jHelper;
import com.subex.rocps.automation.helpers.application.genericHelpers.DataVerificationHelper;
import com.subex.rocps.automation.helpers.application.genericHelpers.PSGenericHelper;
import com.subex.rocps.automation.helpers.application.roaming.util.RoamingRecordsUtil;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;
import com.subex.rocps.automation.utils.ExcelHolder;

public class RecordEntitiesDetail extends RoamingRecordsUtil
{
	protected ExcelReader excelData = new ExcelReader();
	protected Map<String, ArrayList<String>> recordEntitiesExcelMap = null;
	protected Map<String, String> recordEntitiesMap = null;
	protected ExcelHolder excelHolderObj = null;
	protected String path;
	protected String workBookName;
	protected String sheetName;
	protected String testCaseName;
	protected int colSize;
	protected int index;
	protected String colmHdrsSearchResult;
	protected String mapkeys;
	PSGenericHelper psGenericHelper = new PSGenericHelper();
	DataVerificationHelper dataVerHelOb = new DataVerificationHelper();

	/**Constructor
	 * @param path
	 * @param workBookName
	 * @param sheetName
	 * @param testCaseName
	 * @throws Exception 
	 */
	public RecordEntitiesDetail( String path, String workBookName, String sheetName, String testCaseName ) throws Exception
	{
		this.path = path;
		this.workBookName = workBookName;
		this.sheetName = sheetName;
		this.testCaseName = testCaseName;
		recordEntitiesExcelMap = excelData.readDataByColumn( path, workBookName, sheetName, testCaseName );
		excelHolderObj = new ExcelHolder( recordEntitiesExcelMap );
		colSize = excelHolderObj.totalColumns();
	}

	/*
	 * This method is for initialize variable
	 */
	private void initializeVariable( Map<String, String> map ) throws Exception
	{

		mapkeys = ExcelHolder.getKey( map, "MapRowKeys" );
		colmHdrsSearchResult = ExcelHolder.getKey( map, "ColmnHeadersRecordScreen" );
	}
	
	// Method: Validate the search results of 'Record  Entities Search' screen
	public void validateRecordEntitiesSearchResult( String fileName ) throws Exception
	{
		for ( index = 0; index < colSize; index++ )
		{

			recordEntitiesMap = excelHolderObj.dataMap( index );
			initializeVariable( recordEntitiesMap );
			dataVerHelOb.validateDataWithoutSorting( "recordEntityGrid", "grid_column_header_undefined_", recordEntitiesMap, colmHdrsSearchResult, mapkeys, false );
			Log4jHelper.logInfo( "\n'Record Entities Popup Screen' results are validated successfully for file name -'" + fileName  );
		}

	}
}
