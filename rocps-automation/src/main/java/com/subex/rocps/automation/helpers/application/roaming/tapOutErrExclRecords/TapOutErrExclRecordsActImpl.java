package com.subex.rocps.automation.helpers.application.roaming.tapOutErrExclRecords;

import java.util.ArrayList;
import java.util.Map;

import com.subex.rocps.automation.helpers.application.genericHelpers.PSActionImpl;
import com.subex.rocps.automation.helpers.application.genericHelpers.PSGenericHelper;
import com.subex.rocps.automation.helpers.application.roaming.RoamingFileStatus;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;
import com.subex.rocps.automation.utils.ExcelHolder;

import com.subex.automation.helpers.component.ButtonHelper;
import com.subex.automation.helpers.component.ElementHelper;
import com.subex.automation.helpers.component.GenericHelper;
import com.subex.automation.helpers.component.GridHelper;
import com.subex.automation.helpers.file.ExcelReader;
import com.subex.automation.helpers.report.Log4jHelper;

public class TapOutErrExclRecordsActImpl extends PSAcceptanceTest
{
	protected ExcelReader excelData = new ExcelReader();
	protected Map<String, ArrayList<String>> tapOutErrExclRecordsScreenExcelMap = null;
	protected Map<String, String> tapOutErrExclRecordsScreenMap = null;
	protected ExcelHolder excelHolderObj = null;
	protected String path;
	protected String workBookName;
	protected String sheetName;
	protected String testCaseName;
	protected int colSize;
	protected int index;
	protected String colmHdrsSearchResult;
	protected String searchScreenColumns;
	protected String callType;
	protected String mapkeys;
	PSGenericHelper psGenericHelper = new PSGenericHelper();
	PSActionImpl psActionImpl = new PSActionImpl();

	/**Constructor
	 * @param path
	 * @param workBookName
	 * @param sheetName
	 * @param testCaseName
	 * @throws Exception 
	 */
	public TapOutErrExclRecordsActImpl( String path, String workBookName, String sheetName, String testCaseName ) throws Exception
	{

		this.path = path;
		this.workBookName = workBookName;
		this.sheetName = sheetName;
		this.testCaseName = testCaseName;
		tapOutErrExclRecordsScreenExcelMap = excelData.readDataByColumn( path, workBookName, sheetName, testCaseName );
		excelHolderObj = new ExcelHolder( tapOutErrExclRecordsScreenExcelMap );
		colSize = excelHolderObj.totalColumns();
	}

	/*Thismethod is for Click on  action
	 *
	 */
	public void clickOnAction( String parentActionNm, String childActionNm ) throws Exception
	{
		tapOutErrExclRecordsScreenMap = excelHolderObj.dataMap( 0 );
		psActionImpl.clickOnAction( parentActionNm, childActionNm, "PS_Detail_tapOutErrExclRec_viewAction_detailXpath" );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		String mapkeys = ExcelHolder.getKey( tapOutErrExclRecordsScreenMap, "MapRowKeys" );
		String colmHdrsRes = ExcelHolder.getKey( tapOutErrExclRecordsScreenMap, "ColmnHeaders" );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		psGenericHelper.validateSearchResult( colmHdrsRes, mapkeys, tapOutErrExclRecordsScreenMap, "PS_Detail_SearchScreen_ColumnHeaderID", "SearchGrid" );
		Log4jHelper.logInfo( childActionNm + " action' results are validated successfully for 'TAP Out Errored & Excluded Records'" );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		ButtonHelper.click( "PS_Detail_tapOutErrExclRec_viewAction_close_Xpath" );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		ElementHelper.waitForElementToDisappear( GenericHelper.getORProperty( "PS_Detail_tapOutErrExclRec_viewAction_close_Xpath" ), searchScreenWaitSec );
		psGenericHelper.waitforHeaderElement( "Recipient TADIG" );

	}
}
