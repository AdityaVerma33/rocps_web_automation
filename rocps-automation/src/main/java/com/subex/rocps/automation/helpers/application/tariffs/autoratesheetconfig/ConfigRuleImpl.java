package com.subex.rocps.automation.helpers.application.tariffs.autoratesheetconfig;

import java.util.ArrayList;
import java.util.Map;

import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;
import com.subex.rocps.automation.utils.ExcelHolder;
import com.subex.rocps.automation.utils.PSStringUtils;

import com.subex.automation.helpers.application.NavigationHelper;
import com.subex.automation.helpers.component.ButtonHelper;
import com.subex.automation.helpers.component.CheckBoxHelper;
import com.subex.automation.helpers.component.GenericHelper;
import com.subex.automation.helpers.component.GridHelper;
import com.subex.automation.helpers.component.TabHelper;
import com.subex.automation.helpers.component.TextBoxHelper;
import com.subex.automation.helpers.data.DateHelper;
import com.subex.automation.helpers.data.ValidationHelper;
import com.subex.automation.helpers.file.ExcelReader;

public class ConfigRuleImpl extends PSAcceptanceTest
{
	Map<String, String> map = null;
	protected ExcelReader excelReader = null;
	protected Map<String, ArrayList<String>> excelReaderMapObj = null;
	protected Map<String, String> mapObj = null;
	protected ExcelHolder excelHolderObj = null;
	protected String path;
	protected String workBookName;
	protected String sheetName;
	protected String testCaseName;
	protected int paramVal;
	protected int colSize;
	String dataLocationInformation;
	String clientPartition;
	String tariff;
	String rateEffectiveDate;
	String completeExpireDate;
	String originRateEffectiveDate;
	String originCompleteExpireDate;
	String complete;
	String autoAuthorize;
	String locationInformation;
	String sheet;
	String columnIndex;
	String rowNumber;
	String destinationDetails;
	String originDetails;
	String fileName;
	String expressionName;
	String expression;
	String matchString;
	String matchCondition;
	String expirePriorElements;
	String updateFuturePeriods;
	String[] rateEffectiveDateArr;
	String[] completeArr;
	String[] completeExpireDateArr;
	String[] originRateEffectiveDateArr;
	String[] originCompleteExpireDateArr;
	String[] autoAuthorizeArr;
	String[] locationInformationArr;
	String[] destinationDetailsArr;
	String[] originDetailArr;	
	String[] sheetArr;
	String[] expirePriorElementsArr;
	String[] updateFuturePeriodsArr;
	String configRuleTestCase;
	String autoDetectEndRowBlankRowNum;
	String autoDetectEndRowFlg;
	

	/*public ConfigRuleImpl( Map<String, String> map ) throws Exception
	{
		this.map = map;
		initializeVariables( map );
		initializeArrays();
	}*/

	
	public void excelTestDataInitialize(String path, String workBook, String sheetName, String testCaseName, int row) throws Exception{	
		excelReader =  new ExcelReader();
		excelReaderMapObj = excelReader.readDataByColumn( path, workBook, sheetName, testCaseName );
		excelHolderObj = new ExcelHolder( excelReaderMapObj );
		colSize = excelHolderObj.totalColumns();
		for ( paramVal = 0; paramVal < colSize; paramVal++ )
		{
		map = excelHolderObj.dataMap( paramVal );
		initializeVariables( map );
		initializeArrays();
		configRuleDetail(row);
		}
	}
	
	public void configRuleDetail( int row ) throws Exception
	{
		basicDetails( row, locationInformationArr[row] );
		destinationAndOriginConfig( destinationDetailsArr[row], originDetailArr[row] );
		saveConfigrule();
	}

	/*
	 * This method is for config rule basic details
	 */
	public void basicDetails( int row, String locationInformationArr ) throws Exception
	{
		String[] dataLocationarr = locationInformationArr.split( secondLevelDelimiter );
		String originRateEffectiveDateFinal = null;
		TextBoxHelper.type( "PS_Detail_RateSheetRequest_rateEffectiveDate_txtID", rateEffectiveDateArr[row] );
		if ( ValidationHelper.isNotEmpty( complete ) && ValidationHelper.isNotEmpty( completeArr[row] ) && ValidationHelper.isTrue( completeArr[row] ) )
		{
			CheckBoxHelper.check( "PS_Detail_RateSheetRequest_complete_chkbx" );
			TextBoxHelper.type( "PS_Detail_RateSheetRequest_completeExpireDateDate_txtID", completeExpireDateArr[row] );
		}
		else if ( ValidationHelper.isTrue( expirePriorElements )  && ValidationHelper.isTrue( updateFuturePeriodsArr[row] ) )
			CheckBoxHelper.check( "prirExpirePriorElementFl_InputElement" );
		if ( ValidationHelper.isNotEmpty( originRateEffectiveDate ) && ValidationHelper.isNotEmpty( originRateEffectiveDateArr[row] ) )
			originRateEffectiveDateFinal = date( originRateEffectiveDateArr[row]  );
			TextBoxHelper.type( "PS_Detail_RateSheetRequest_originrateEffectiveDate_txtID", originRateEffectiveDateFinal );
		if ( ValidationHelper.isNotEmpty( originCompleteExpireDate ) && ValidationHelper.isNotEmpty( originCompleteExpireDateArr[row] ) )
			TextBoxHelper.type( "PS_Detail_RateSheetRequest_origincompleteExpireDate_txtID", originCompleteExpireDateArr[row] );
	
		if ( ValidationHelper.isNotEmpty( autoAuthorize ) && ValidationHelper.isTrue( autoAuthorizeArr[row] ) )
			CheckBoxHelper.check( "PS_Detail_RateSheetRequest_authorize_chkbx" );
		
		if ( ValidationHelper.isTrue( updateFuturePeriods ) && ValidationHelper.isTrue( updateFuturePeriodsArr[row] ) )
			CheckBoxHelper.check( "prirUpdateFutPeriodFl_InputElement" );
	
		for ( int i = 0; i < dataLocationarr.length; i++ )
		{
			String sheetVal = sheetArr[row];
			String[] sheetAr = sheetVal.split( secondLevelDelimiter );
			int totalRow = GridHelper.getRowCount( "PS_Detail_RateSheetRequest_dataLocation_GridID" );
			
			if(totalRow != dataLocationarr.length)
				throw new Exception( "Sheet Names and its configurations missing " );
			int rowVal = GridHelper.getRowNumber( "PS_Detail_RateSheetRequest_dataLocation_GridID", sheetAr[i], "Sheet" );
			assertTrue(rowVal>0,"The given sheet name -'"+sheetVal+"' is not found on Grid:");
			dataLocationInformation( dataLocationarr[i], rowVal );
			TextBoxHelper.type( "prirNullRowNum", autoDetectEndRowBlankRowNum );
		}
	}
	
	
	
	/*
	 * This method is for data location information
	 */
	public void dataLocationInformation( String dataLocationarr, int row ) throws Exception
	{
		String regex3rdLevel = new PSStringUtils().regexThirdLevelDelimeter();
		String[] dataLocationValueArr = dataLocationarr.split( regex3rdLevel, -1 );
		GridHelper.updateGridTextBox( "PS_Detail_RateSheetRequest_dataLocation_GridID", "PS_Detail_RateSheetRequest_startRwoNo_txtBx", row, "Start Row Number", dataLocationValueArr[0] );
		if ( ValidationHelper.isFalse( autoDetectEndRowFlg ) )
		{
			CheckBoxHelper.uncheck( "prirAutoDetectFl_InputElement" );
			if ( !CheckBoxHelper.isNotChecked( "prirAutoDetectFl_InputElement" ) )
				CheckBoxHelper.uncheck( "prirAutoDetectFl_InputElement" );
		GridHelper.updateGridTextBox( "PS_Detail_RateSheetRequest_dataLocation_GridID", "PS_Detail_RateSheetRequest_endRowNo_txtBx", row, "End Row Number", dataLocationValueArr[1] );
		}
		else
		{
			if ( !CheckBoxHelper.isChecked( "prirAutoDetectFl_InputElement" ) )
				CheckBoxHelper.check( "prirAutoDetectFl_InputElement" );
		}
	}

	/*
	 * This method is for destination and origin Config
	 */
	public void destinationAndOriginConfig( String destinationDetailsArr, String originDetailsArr ) throws Exception
	{
		String[] destinationDetailsAr = destinationDetailsArr.split( secondLevelDelimiter );
		String[] originDetailsAr = originDetailsArr.split( secondLevelDelimiter );

		if ( !originDetails.isEmpty() && !originDetailsArr.isEmpty() )
		{
			for ( int i = 0; i < originDetailsAr.length; i++ )
			{
				destinationOriginDetailsConfig( "PS_Detail_RateSheetRequest_origin_GridID", originDetailsAr[i], i + 1 );
			}
		}
		if ( !destinationDetails.isEmpty() && !destinationDetailsArr.isEmpty() )
		{
			TabHelper.gotoTab( "PS_Detail_autoRateSheet_destination_TabXpath" );
			for ( int j = 0; j < destinationDetailsAr.length; j++ )
			{
				destinationOriginDetailsConfig( "PS_Detail_RateSheetRequest_destination_GridID", destinationDetailsAr[j], j + 1 );
			}
		}

	}

	public void destinationOriginDetailsConfig( String gridID, String destinationOriginArr, int row ) throws Exception
	{
		String regex3rdLevel = new PSStringUtils().regexThirdLevelDelimeter();
		String[] destinationOriginFinalAr = destinationOriginArr.split( regex3rdLevel, -1 );
		String sheetName = destinationOriginFinalAr[0];
		String columnIndex = destinationOriginFinalAr[1];
		String rowNo = destinationOriginFinalAr[2];

		GridHelper.updateGridComboBox( gridID, "PS_Detail_RateSheetRequest_sheet_comboID", row, "Sheet", sheetName );
		GridHelper.updateGridTextBox( gridID, "PS_Detail_RateSheetRequest_columnIndex_txtID", row, "Column Index", columnIndex );
		GridHelper.updateGridTextBox( gridID, "PS_Detail_RateSheetRequest_rowNo_txtID", row, "Row Number", rowNo );
	}

	/*
	 * This method is to save config rule
	 */
	public void saveConfigrule() throws Exception
	{
		ButtonHelper.click( "PS_Detail_autoRateSheet_configrule_save_btnID" );
		GenericHelper.waitForLoadmask( detailScreenWaitSec );
		assertEquals( NavigationHelper.getScreenTitle(), "New Auto Rate Sheet Config" );
	}
	/*
	 * This method is to remove the hr:min:ss from date
	 */
	private String date( String date )
	{
		String dateVal = null;
		if ( date.contains( "00:00:00" ) )
			dateVal = date.replace( "00:00:00", "" );
		else
			dateVal = date;

		return dateVal;
	}
	/*
	 * This method is to initialize 1st level split array variables
	 */
	public void initializeArrays() throws Exception
	{		
		String regex = new PSStringUtils().regexFirstLevelDelimeter();
		rateEffectiveDateArr = rateEffectiveDate.split( regex, -1 );
		completeArr = complete.split( regex, -1 );
		completeExpireDateArr = completeExpireDate.split( regex, -1 );
		originRateEffectiveDateArr = originRateEffectiveDate.split( regex, -1 );
		originCompleteExpireDateArr = originCompleteExpireDate.split( regex, -1 );
		autoAuthorizeArr = autoAuthorize.split( regex, -1 );
		locationInformationArr = locationInformation.split( regex, -1 );
		destinationDetailsArr = destinationDetails.split( regex, -1 );
		originDetailArr = originDetails.split( regex, -1 );	
		sheetArr = sheet.split( regex, -1 );
		expirePriorElementsArr = expirePriorElements.split( regex, -1 );
		updateFuturePeriodsArr	= updateFuturePeriods.split( regex, -1 );
	}

	/*
	 * This method is to initialize instance variables of config rule
	 */
	public void initializeVariables( Map<String, String> map ) throws Exception
	{
			
		rateEffectiveDate = ExcelHolder.getKey( map, "RateEffectiveDate" );
		completeExpireDate = ExcelHolder.getKey( map, "CompleteExpireDate" );
		originRateEffectiveDate = ExcelHolder.getKey( map, "OriginRateEffectiveDate" );
		originCompleteExpireDate = ExcelHolder.getKey( map, "OriginCompleteExpireDate" );
		complete = ExcelHolder.getKey( map, "Complete" );
		autoAuthorize = ExcelHolder.getKey( map, "AutoAuthorize" );
		locationInformation = ExcelHolder.getKey( map, "LocationInformation" );
		destinationDetails = ExcelHolder.getKey( map, "Destination Details" );
		originDetails = ExcelHolder.getKey( map, "Origin Details" );
		sheet =  ExcelHolder.getKey( map, "SheetName" );
		expirePriorElements = ExcelHolder.getKey( map, "ExpirePriorElements" );
		updateFuturePeriods =  ExcelHolder.getKey( map, "UpdateFuturePeriods" );	
		autoDetectEndRowFlg =  ExcelHolder.getKey( map, "AutoDetectEndRowFlg" );
		autoDetectEndRowBlankRowNum =  ExcelHolder.getKey( map, "autoDetectEndRowBlankRowNum" );

	}
}
