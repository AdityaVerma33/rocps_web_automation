package com.subex.rocps.automation.helpers.application.tariffs.ratesheetimportrequest;

import java.awt.AWTException;
import java.net.MalformedURLException;
import java.util.Map;

import com.subex.rocps.automation.helpers.application.genericHelpers.PSEntityComboHelper;
import com.subex.rocps.automation.helpers.application.genericHelpers.PSGenericHelper;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;
import com.subex.rocps.automation.utils.ExcelHolder;
import com.subex.rocps.automation.utils.PSStringUtils;

import com.subex.automation.helpers.application.NavigationHelper;
import com.subex.automation.helpers.component.ButtonHelper;
import com.subex.automation.helpers.component.CheckBoxHelper;
import com.subex.automation.helpers.component.ElementHelper;
import com.subex.automation.helpers.component.EntityComboHelper;
import com.subex.automation.helpers.component.FileHelper;
import com.subex.automation.helpers.component.GenericHelper;
import com.subex.automation.helpers.component.GridHelper;
import com.subex.automation.helpers.component.PopupHelper;
import com.subex.automation.helpers.component.SearchGridHelper;
import com.subex.automation.helpers.component.TabHelper;
import com.subex.automation.helpers.component.TextBoxHelper;
import com.subex.automation.helpers.data.ValidationHelper;
import com.subex.automation.helpers.report.Log4jHelper;

public class RateSheetImportRequestDetailImpl extends PSAcceptanceTest
{
	Map<String, String> map = null;
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
	String type;
	String templateName;
	String expirePriorElements;
	String updateFuturePeriods;
	String autoDetectEndRowFlg;;
	String autoDetectEndRowBlankRowNum;
	String regex = new PSStringUtils().regexFirstLevelDelimeter();

	public RateSheetImportRequestDetailImpl( Map<String, String> map ) throws Exception
	{
		this.map = map;
		initializeVariables( map );

	}

	public void newImport() throws Exception
	{
		NavigationHelper.selectPartition( clientPartition );
		GenericHelper.waitForLoadmask();
		assertEquals( NavigationHelper.getScreenTitle(), "New Ratesheet Import Request" );
	}

	/*
	 * This method is is for basic details of rate sheet import request
	 */
	public void basicDetails() throws Exception
	{
		String title = "Tariff Search";
		String effectiveDateVal = date( rateEffectiveDate );
		String completeDate = date( completeExpireDate );
		String originExpireDateVal = date( originCompleteExpireDate );
		String originEffectivedateVal = date( originRateEffectiveDate );
		String[] dataLocationarr = locationInformation.split( regex, -1 );
		/*
		 * If tariff is already attached to template name so no need to give template name in excel and we can go with tariff only
		 */
		if ( !tariff.isEmpty() && templateName.isEmpty() )
			PSEntityComboHelper.selectUsingSearchTextBox( "PSPopUp_emrSvcTffEntSrchId", title, "PSPopUp_emrSvcTffNameTxtId", tariff, "Tariff Name" );
		/*
		 * If tariff is not attached to template name so  need to give template name in excel also ,
		 * so First it will select tariff than attached the template with
		 */
		else
			rateSheetTemplateSelection();
		rsFileupload();
		TextBoxHelper.type( "PS_Detail_RateSheetRequest_rateEffectiveDate_txtID", effectiveDateVal );
		if ( ValidationHelper.isTrue( complete ) )
		{
			CheckBoxHelper.check( "PS_Detail_RateSheetRequest_complete_chkbx" );
			TextBoxHelper.type( "PS_Detail_RateSheetRequest_completeExpireDateDate_txtID", completeDate );
		}
		else if ( ValidationHelper.isTrue( expirePriorElements ) )
			CheckBoxHelper.check( "prirExpirePriorElementFl_InputElement" );
		if ( type.equals( "Origin" ) )
		{
			TextBoxHelper.type( "PS_Detail_RateSheetRequest_originrateEffectiveDate_txtID", originEffectivedateVal );
			/*
			 * This part is not working for origin
			 
			if ( ValidationHelper.isTrue( complete ) )
				TextBoxHelper.type( "PS_Detail_RateSheetRequest_origincompleteExpireDate_txtID", originExpireDateVal );
			*/

		}

		if ( ValidationHelper.isTrue( autoAuthorize ) )
			CheckBoxHelper.check( "PS_Detail_RateSheetRequest_authorize_chkbx" );
		if ( ValidationHelper.isTrue( updateFuturePeriods ) )
			CheckBoxHelper.check( "prirUpdateFutPeriodFl_InputElement" );

		for ( int i = 0; i < dataLocationarr.length; i++ )
		{
			dataLocationInformation( dataLocationarr[i], i + 1 );
			TextBoxHelper.type( "prirNullRowNum", autoDetectEndRowBlankRowNum );
		}
	}

	/*
	 * This method is for RS file upload
	 */
	private void rsFileupload() throws MalformedURLException, AWTException, Exception
	{
		String filePath = automationPath + configProp.getProperty( "ratesheetPath" ) + fileName;
		String fileTypeImageName = configProp.getProperty( "fileTypeUploadImageName" );
		String openButtonImageName = configProp.getProperty( "openButtoneUploadImageName" );

		String path;
		String os = configProp.getOS();
		if ( os.equalsIgnoreCase( "linux" ) )
			path = filePath.replace( "\\", "/" );
		else
			path = filePath;

		ButtonHelper.click( "fileChooserGroupLoad" );
		GenericHelper.waitForLoadmask( detailScreenWaitSec );
		PSGenericHelper.psFileUploadSikuli( "FileUpload_Browse", path, fileTypeImageName, openButtonImageName );
		GenericHelper.waitForLoadmask( detailScreenWaitSec );
		ButtonHelper.click( "FileUpload-upload" );
		GenericHelper.waitForLoadmask( detailScreenWaitSec );

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
	 * This method is for rate sheet template selection
	 */

	public void rateSheetTemplateSelection() throws Exception
	{
		PSEntityComboHelper.selectUsingSearchTextBox( "PSPopUp_emrSvcTffEntSrchId", "Tariff Search", "PSPopUp_emrSvcTffNameTxtId", tariff, "Tariff Name" );
		popupValidation();
		assertEquals( NavigationHelper.getScreenTitle(), "Rate Sheet Import Template Search" );
		SearchGridHelper.gridFilterSearchWithTextBox( "popupWindow", "psitName", templateName, "Template Name" );
		GridHelper.clickRow( "SearchGrid", 1, "Template Name" );
		ButtonHelper.click( "OK_TRT_Button" );
		GenericHelper.waitForLoadmask( detailScreenWaitSec );
	}
	/*
	 * This method is for data location information
	 */

	public void dataLocationInformation( String dataLocationarr, int row ) throws Exception
	{
		String[] dataLocationValueArr = dataLocationarr.split( secondLevelDelimiter );
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
	 * This method is for destinationandOriginDetails grid
	 */
	public void destinationAndOriginDetails() throws Exception
	{
		String[] destinationDetailsArr = destinationDetails.split( regex, -1 );
		String[] originDetailsArr = originDetails.split( regex, -1 );

		if ( ValidationHelper.isNotEmpty( destinationDetails ) )
		{
			TabHelper.gotoTab( "//*[text()='Destination Details']" );
			for ( int i = 0; i < destinationDetailsArr.length; i++ )
			{
				destinationOriginDetailsConfig( "PS_Detail_RateSheetRequest_destination_GridID", destinationDetailsArr[i], i + 1 );
			}
		}
		if ( ValidationHelper.isNotEmpty( originDetails ) )
		{
			TabHelper.gotoTab( "//*[text()='Origin Details']" );
			for ( int i = 0; i < originDetailsArr.length; i++ )
			{
				destinationOriginDetailsConfig( "PS_Detail_RateSheetRequest_origin_GridID", originDetailsArr[i], i + 1 );
			}
		}

	}

	public void destinationOriginDetailsConfig( String gridID, String destinationDetailsArr, int row ) throws Exception
	{
		String[] destinationDetailsAr = destinationDetailsArr.split( secondLevelDelimiter );

		GridHelper.updateGridComboBox( gridID, "PS_Detail_RateSheetRequest_sheet_comboID", row, "Sheet", destinationDetailsAr[0] );
		GridHelper.updateGridTextBox( gridID, "PS_Detail_RateSheetRequest_columnIndex_txtID", row, "Column Index", destinationDetailsAr[1] );
		GridHelper.updateGridTextBox( gridID, "PS_Detail_RateSheetRequest_rowNo_txtID", row, "Row Number", destinationDetailsAr[2] );

	}

	/*
	 * This method is to save ratesheet import request
	 */
	public void saveRateSheetImportRequest() throws Exception
	{

		ButtonHelper.click( "ratesheetImportRequestDetail.save" );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		Log4jHelper.logInfo( "Rate Sheet Import Request is saved successfully for :" + tariff );
		boolean isValuePresent = GridHelper.isValuePresent( "SearchGrid", tariff, "Tariff" );
		assertTrue( isValuePresent, "Ratesheet import request is not saved for this tariff-" + tariff );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
	}

	/*
	 * This method is for popup validation 
	 */
	public void popupValidation() throws Exception
	{
		String popMsg = "Selected Tariff is not associated to any Template. Please select the template";
		assertTrue( PopupHelper.isPresent( "window-scroll-panel" ) );
		String actualMsg = ElementHelper.getText( "//*[@id='window-scroll-panel']//div[@title]" );
		assertEquals( popMsg, actualMsg, "popup texts are not matching" );
		ButtonHelper.click( "ok" );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
	}

	/*
	 * This method is to initialize instance variables
	 */
	public void initializeVariables( Map<String, String> map ) throws Exception
	{
		clientPartition = ExcelHolder.getKey( map, "Partition" );
		tariff = ExcelHolder.getKey( map, "Tariff" );
		rateEffectiveDate = ExcelHolder.getKey( map, "RateEffectiveDate" );
		completeExpireDate = ExcelHolder.getKey( map, "CompleteExpireDate" );
		originRateEffectiveDate = ExcelHolder.getKey( map, "OriginRateEffectiveDate" );
		originCompleteExpireDate = ExcelHolder.getKey( map, "OriginCompleteExpireDate" );
		complete = ExcelHolder.getKey( map, "Complete" );
		autoAuthorize = ExcelHolder.getKey( map, "AutoAuthorize" );
		autoDetectEndRowFlg = ExcelHolder.getKey( map, "AutoDetectEndRowFlg" );
		autoDetectEndRowBlankRowNum = ExcelHolder.getKey( map, "AutoDetectEndRowBlankRowNum" );
		locationInformation = ExcelHolder.getKey( map, "LocationInformation" );
		destinationDetails = ExcelHolder.getKey( map, "Destination Details" );
		originDetails = ExcelHolder.getKey( map, "Origin Details" );
		fileName = ExcelHolder.getKey( map, "FileName" );
		type = ExcelHolder.getKey( map, "Type" );
		templateName = ExcelHolder.getKey( map, "Template Name" );
		expirePriorElements = ExcelHolder.getKey( map, "ExpirePriorElements" );
		updateFuturePeriods = ExcelHolder.getKey( map, "UpdateFuturePeriods" );
	}

}
