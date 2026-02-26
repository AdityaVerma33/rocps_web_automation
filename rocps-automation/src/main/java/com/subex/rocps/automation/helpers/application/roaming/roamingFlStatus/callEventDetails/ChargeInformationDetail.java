package com.subex.rocps.automation.helpers.application.roaming.roamingFlStatus.callEventDetails;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.subex.automation.helpers.component.GenericHelper;
import com.subex.automation.helpers.component.GridHelper;
import com.subex.automation.helpers.data.ValidationHelper;
import com.subex.automation.helpers.file.ExcelReader;
import com.subex.automation.helpers.report.Log4jHelper;
import com.subex.rocps.automation.helpers.application.genericHelpers.DataVerificationHelper;
import com.subex.rocps.automation.helpers.application.genericHelpers.PSDataComponentHelper;
import com.subex.rocps.automation.helpers.application.genericHelpers.PSGenericHelper;
import com.subex.rocps.automation.helpers.application.roaming.roamingFlStatus.callEventDetails.chargeInformation.ChargeInformationActImpl;
import com.subex.rocps.automation.helpers.application.roaming.util.RoamingRecordsUtil;
import com.subex.rocps.automation.utils.ExcelHolder;
import com.subex.rocps.automation.utils.PSStringUtils;

public class ChargeInformationDetail extends RoamingRecordsUtil
{
	protected ExcelReader excelData = new ExcelReader();
	protected Map<String, ArrayList<String>> chargeInformationExcelMap = null;
	protected Map<String, String> chargeInformationMap = null;
	protected ExcelHolder excelHolderObj = null;
	protected String path;
	protected String workBookName;
	protected String sheetName;
	protected String testCaseName;
	protected int colSize;
	protected int index;
	protected String colmHdrsSearchResult;
	protected String searchScreenColumns;
	protected String chargedItem;
	protected String mapkeys;
	PSGenericHelper psGenericHelper = new PSGenericHelper();
	DataVerificationHelper dataVerHelOb = new DataVerificationHelper();
	PSStringUtils psStringUtils=new PSStringUtils();
	PSDataComponentHelper psDataComponentHelper=new PSDataComponentHelper();

	/**Constructor
	 * @param path
	 * @param workBookName
	 * @param sheetName
	 * @param testCaseName
	 * @throws Exception 
	 */
	public ChargeInformationDetail( String path, String workBookName, String sheetName, String testCaseName ) throws Exception
	{
		this.path = path;
		this.workBookName = workBookName;
		this.sheetName = sheetName;
		this.testCaseName = testCaseName;
		chargeInformationExcelMap = excelData.readDataByColumn( path, workBookName, sheetName, testCaseName );
		excelHolderObj = new ExcelHolder( chargeInformationExcelMap );
		colSize = excelHolderObj.totalColumns();
	}

	/*
	 * This method is for initialize variable
	 */
	private void initializeVariable( Map<String, String> map ) throws Exception
	{

		mapkeys = ExcelHolder.getKey( map, "MapRowKeys" );
		colmHdrsSearchResult = ExcelHolder.getKey( map, "ColmnHeadersRecordScreen" );
		chargedItem = ExcelHolder.getKey( map, "ChargedItem" );
		searchScreenColumns = ExcelHolder.getKey( map, "SearchScreenColumns" );

	}
	// Method: Validate the search results of 'Charged Information' screen
	public void validateChargedInformationSearchResult( String fileName ) throws Exception
	{
		for ( index = 0; index < colSize; index++ )
		{

			chargeInformationMap = excelHolderObj.dataMap( index );
			initializeVariable( chargeInformationMap );
			super.validateFileName( "PS_Detail_RoamingFlStatus_fileNm_txtId", fileName );
			super.filterSelection( "PS_Detail_RoamFlStatus_chargeInfo_chargedItem_comboId", chargedItem, "PS_Detail_RoamingRecordScreen_fileNmHeader_xpath" );
			GenericHelper.waitTime( 10, "Waiting for Roaming Records" );
			if ( ValidationHelper.isNotEmpty( searchScreenColumns ) )
				psGenericHelper.screenColumnValidation( "Charged Item", searchScreenColumns, "'Charged Information' for file name " + fileName + " of charged item " + chargedItem );
			GenericHelper.waitForLoadmask( searchScreenWaitSec );
			dataVerHelOb.validateDataWithoutSorting( GenericHelper.getORProperty( "PS_Detail_SearchScreen_ColumnHeaderID" ), chargeInformationMap, colmHdrsSearchResult, mapkeys, false );
			Log4jHelper.logInfo( "\n'Charge Informtion Screen' results are validated successfully for file name -'" + fileName + "' of chargedItem " + chargedItem );
			String[] rows = psStringUtils.stringSplitFirstLevel( mapkeys );
			String rowValue = chargeInformationMap.get( rows[0] );
			int rowNum = psDataComponentHelper.getRowNumOfGrid( "SearchGrid", rowValue );
			performActionTask( fileName,rowNum );
		}

	}
	/*
	 * This method is for Perform Action task
	 */
	private void performActionTask(  String fileNm,int rowNum) throws Exception
	{
		ChargeInformationActImpl chargeInformationActImpl=new ChargeInformationActImpl();
		List<String> actionNameKeys = chargeInformationActImpl.getKeysOfActionName();
		for ( String actionKey : actionNameKeys )
		{
			String actionNameValue = chargeInformationMap.get( actionKey );
			if ( ValidationHelper.isNotEmpty( actionNameValue ) )
			{
				GridHelper.clickRow( "SearchGrid", rowNum, "Charged Item" );
				chargeInformationActImpl.switchToActionTask( fileNm,chargedItem,actionKey, actionNameValue, path, workBookName, sheetName );

			}
		}
	}

}
