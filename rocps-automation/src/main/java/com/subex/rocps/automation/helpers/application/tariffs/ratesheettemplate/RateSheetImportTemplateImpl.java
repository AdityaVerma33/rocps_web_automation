package com.subex.rocps.automation.helpers.application.tariffs.ratesheettemplate;

import org.testng.Assert;
import org.sikuli.script.FindFailed;
import org.sikuli.script.Key;
import org.sikuli.script.Pattern;
import org.sikuli.script.Screen;


import java.util.Map;

import com.subex.rocps.automation.helpers.application.deal.deal.DealBandConfiguration;
import com.subex.rocps.automation.helpers.application.genericHelpers.PSGenericHelper;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;
import com.subex.rocps.automation.utils.ExcelHolder;
import com.subex.rocps.automation.utils.PSStringUtils;

import com.subex.automation.helpers.application.NavigationHelper;
import com.subex.automation.helpers.component.ButtonHelper;
import com.subex.automation.helpers.component.ComboBoxHelper;
import com.subex.automation.helpers.component.ElementHelper;
import com.subex.automation.helpers.component.FileHelper;
import com.subex.automation.helpers.component.GenericHelper;
import com.subex.automation.helpers.component.GridHelper;
import com.subex.automation.helpers.component.MouseHelper;
import com.subex.automation.helpers.component.PropertyGridHelper;
import com.subex.automation.helpers.component.TabHelper;
import com.subex.automation.helpers.component.TextAreaHelper;
import com.subex.automation.helpers.component.TextBoxHelper;
import com.subex.automation.helpers.report.Log4jHelper;
import com.subex.automation.helpers.util.FailureHelper;

public class RateSheetImportTemplateImpl extends PSAcceptanceTest
{

	String templateName;
	String templateCode;
	String rateFilePath;
	String description;
	String rateSheetType;
	String importType;
	String dateFormat;
	String tariffs;
	String mapSheet1;
	String mapColumn1;
	String mapSheet2;
	String mapColumn2;
	String configureSheetNames;
	PSStringUtils strUtilObj = new PSStringUtils();
	PSGenericHelper genericObj = new PSGenericHelper();
	

	public RateSheetImportTemplateImpl( Map<String, String> map )
	{

		initialize( map );
	}

	public void initialize( Map<String, String> rstTempDetailMap )
	{

		templateName = rstTempDetailMap.get( "TemplateName" );
		templateCode = rstTempDetailMap.get( "TemplateCode" );
		rateFilePath = rstTempDetailMap.get( "RateFile" );
		description = rstTempDetailMap.get( "Description" );
		rateSheetType = rstTempDetailMap.get( "RateSheetType" );
		dateFormat = rstTempDetailMap.get( "DateDecimal" );
		tariffs = rstTempDetailMap.get( "Tariffs" );
		mapSheet1 = rstTempDetailMap.get( "MappingSheet1" );
		mapColumn1 = rstTempDetailMap.get( "Column1" );
		mapSheet2 = rstTempDetailMap.get( "MappingSheet2" );
		mapColumn2 = rstTempDetailMap.get( "Column2" );
		configureSheetNames = rstTempDetailMap.get( "ConfigureSheetNames" );
		importType = rstTempDetailMap.get( "ImportType" );
	}

	// To Do public void preview()

	public void validateScreenTitle( String titleName ) throws Exception
	{

		NavigationHelper.isTitlePresent( titleName );
	}

	public void naivgateToOriginConfigTab() throws Exception
	{
		TabHelper.gotoTab( "//div[text()='Origin Config']" );
		GenericHelper.waitForLoadmask();
	}

	public void naivgateToDestinationConfigTab() throws Exception
	{
		TabHelper.gotoTab( "//div[text()='Destination...']" );
		GenericHelper.waitForLoadmask();
	}

	public void navigateToTariffsTab() throws Exception
	{
		TabHelper.gotoTab( "//div[text()='Associate T...']" );
		GenericHelper.waitForLoadmask();
	}

	public void navigateToMappingsTab() throws Exception
	{
		TabHelper.gotoTab( "//div[text()='Mapping']" );
		GenericHelper.waitForLoadmask();
	}
	
	/*public void navigateToMappingsSheetTab() throws Exception
	{
		TabHelper.gotoTab( "//div[@id='topPanelContainer']//div[text()='Mapping']" );
		GenericHelper.waitForLoadmask();
	}
	*/
	public void detailTabBasicDetails() throws Exception
	{

		TextBoxHelper.type( or.getProperty( "PS_RSDetailTemplateNameTxtId" ), templateName );
		TextBoxHelper.type( or.getProperty( "PS_RSDetailTemplateCodeTxtId" ), templateCode );

		ButtonHelper.click( "PS_RSDetailTemplateRateFileBtnId" );
		GenericHelper.waitForLoadmask();
		String rateSheetFilePath = automationPath + configProp.getProperty( "ratesheetPath" ) + rateFilePath;
		//GenericHelper.fileUpload( rateSheetFilePath );
		//FileHelper.fileUploadRobot( or.getProperty( "PS_RSDetailfileUploadBtnXpath" ), rateSheetFilePath );
		String fileTypeImageName = configProp.getProperty( "fileTypeUploadImageName" );
		String openButtonImageName = configProp.getProperty( "openButtoneUploadImageName" );
		PSGenericHelper.psFileUploadSikuli("FileUpload_Browse", rateSheetFilePath,fileTypeImageName,openButtonImageName);
		GenericHelper.waitForLoadmask();
		ButtonHelper.click( "FileUpload-upload" );
		GenericHelper.waitForLoadmask();
		TextBoxHelper.type( or.getProperty( "PS_RSDetailTemplateCodeTxtId" ), description );
		ComboBoxHelper.select( "importType_gwt_uid_", importType );
		if(importType.contains( "MSISDN Based" ))
			ComboBoxHelper.select( or.getProperty( "PS_RSDetailRatesheetComboId" ), rateSheetType );
		ComboBoxHelper.select( or.getProperty( "PS_RSDetailDateFormatComboId" ), dateFormat );
		if ( !configureSheetNames.isEmpty() )
		{
			ButtonHelper.click( "PS_RSConfigureSheetNameBtnID" );
			String[] actualVal = TextAreaHelper.getValue( "PS_RsConfigureSheetNamesTxtID" ).split( "\n", -1 );
			String[] excelVal = strUtilObj.stringSplitFirstLevel( configureSheetNames );
			assertEquals( excelVal, actualVal, "sheet names are not matching" );
			ButtonHelper.click( "PS_RSConfigureSheetNameOkBtnID" );
		}

		Log4jHelper.logInfo( "Rate sheet template details tab configured" );
	}

	public void originConfig( String path, String workBook, String sheetName, Map<String, String> map ) throws Exception
	{
		naivgateToOriginConfigTab();
		ConfigurationMapping configMapObj = new ConfigurationMapping( map );
		configMapObj.originOrDestConfigurations(path, workBook, sheetName);
		Log4jHelper.logInfo( "Rate sheet template origin tab configured" );
	}

	public void destinationConfig(String path, String workBook, String sheetName, Map<String, String> map ) throws Exception
	{
		naivgateToDestinationConfigTab();
		ConfigurationMapping configMapObj = new ConfigurationMapping( map );
		configMapObj.originOrDestConfigurations(path, workBook, sheetName);
		Log4jHelper.logInfo( "Rate sheet template destination tab configured" );
	}

	public void tariffsConfig() throws Exception
	{

		String[] tariffArr = null;
		navigateToTariffsTab();
		if ( !tariffs.isEmpty() )
		{
			tariffArr = strUtilObj.stringSplitFirstLevel( tariffs );
			//navigateToTariffsTab();
		
		
		for ( int rowIndex = 0; rowIndex < tariffArr.length; rowIndex++ )
		{
			ButtonHelper.click( or.getProperty( "PS_RSTariffAddBtnId" ) );
			GenericHelper.waitForLoadmask();
			String screenName = NavigationHelper.getScreenTitle();
			assertEquals( screenName, "Tariff Search", " Screen titles are not matching on advance search operation of Tariff" );
			TextBoxHelper.type( "PS_searchTariffTxtId", tariffArr[rowIndex] );
			ButtonHelper.click( "PS_popUpWindowId", "SearchButton" );
			GenericHelper.waitForLoadmask();
			boolean rowValExists = GridHelper.isValuePresent( "PS_popUpWindowId", "SearchGrid", tariffArr[rowIndex], "Tariff Name" );
			assertTrue( rowValExists, "tariff does not exist" + tariffArr[rowIndex] );
			GridHelper.clickRow( "PS_popUpWindowId", "SearchGrid", tariffArr[rowIndex], "Name" );
			ButtonHelper.clickIfEnabled( "OK_Button_ByID" );
			GenericHelper.waitForLoadmask();
			Log4jHelper.logInfo( "Tariff saved successfully : " + tariffArr[rowIndex] );
			Assert.assertTrue( GridHelper.isValuePresent( "tariffGrid", tariffArr[rowIndex], "Tariff" ), tariffArr[rowIndex] + " : tariff not saved in the tariff grid" );
		}
		}
	}

	public void mappingSheets() throws Exception
	{

		String[] mapSheet1Arr = null;
		String[] mapColumn1Arr = null;
		String[] mapSheet2Arr = null;
		String[] mapColumn2Arr = null;

		if ( !mapSheet1.isEmpty() )
		{

			navigateToMappingsTab();
			mapSheet1Arr = strUtilObj.stringSplitFirstLevel( mapSheet1 );
			mapColumn1Arr = strUtilObj.stringSplitFirstLevel( mapColumn1 );
			mapSheet2Arr = strUtilObj.stringSplitFirstLevel( mapSheet2 );
			mapColumn2Arr = strUtilObj.stringSplitFirstLevel( mapColumn2 );

			for ( int rowIndex = 0; rowIndex < mapSheet1Arr.length; rowIndex++ )
			{

				ButtonHelper.click( or.getProperty( "PS_RSMappingSheetAddBtnId" ) );
				GridHelper.updateGridComboBox( or.getProperty( "PS_RSMappingTabGridId" ), or.getProperty( "PS_RSMappingSheet1ComboId" ), rowIndex + 1, "Sheet1", mapSheet1Arr[rowIndex] );
				GridHelper.updateGridTextBox( or.getProperty( "PS_RSMappingTabGridId" ), or.getProperty( "PS_RSMappingSheet1TxtId" ), rowIndex + 1, "Column1", mapColumn1Arr[rowIndex] );
				GridHelper.updateGridComboBox( or.getProperty( "PS_RSMappingTabGridId" ), or.getProperty( "PS_RSMappingSheet2ComboId" ), rowIndex + 1, "Sheet2", mapSheet2Arr[rowIndex] );
				GridHelper.updateGridTextBox( or.getProperty( "PS_RSMappingTabGridId" ), or.getProperty( "PS_RSMappingSheet2TxtId" ), rowIndex + 1, "Column2", mapColumn2Arr[rowIndex] );
				Log4jHelper.logInfo( "Rate sheet template mapping tab configured with sheets " + mapSheet1Arr[rowIndex] + "," + mapColumn1Arr[rowIndex] + "," + mapSheet2Arr[rowIndex] + "," + mapColumn2Arr[rowIndex] );
			}
		}
	}

	public void rateSheetTemplateDetailSave() throws Exception
	{

		ButtonHelper.click( "rateSheetImportTemplateDetail.save" );
		GenericHelper.waitForLoadmask();
	}
	
	
}
