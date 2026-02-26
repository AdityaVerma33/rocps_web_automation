package com.subex.rocps.automation.helpers.application.tariffs.ratesheettemplate;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.subex.rocps.automation.helpers.application.deal.deal.DealBandConfiguration;
import com.subex.rocps.automation.helpers.application.genericHelpers.PSGenericHelper;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;
import com.subex.rocps.automation.utils.ExcelHolder;
import com.subex.rocps.automation.utils.PSStringUtils;

import com.subex.automation.helpers.application.NavigationHelper;
import com.subex.automation.helpers.component.ButtonHelper;
import com.subex.automation.helpers.component.CheckBoxHelper;
import com.subex.automation.helpers.component.ComboBoxHelper;
import com.subex.automation.helpers.component.ElementHelper;
import com.subex.automation.helpers.component.GenericHelper;
import com.subex.automation.helpers.component.GridHelper;
import com.subex.automation.helpers.component.PropertyGridHelper;
import com.subex.automation.helpers.component.TabHelper;
import com.subex.automation.helpers.component.TextBoxHelper;
import com.subex.automation.helpers.data.ValidationHelper;
import com.subex.automation.helpers.file.ExcelReader;
import com.subex.automation.helpers.report.Log4jHelper;

public class ConfigurationMapping extends PSAcceptanceTest
{
	protected ExcelReader excelReader = null;
	protected Map<String, ArrayList<String>> excelReaderMapObj = null;
	protected Map<String, String> mapObj = null;
	protected ExcelHolder excelHolderObj = null;
	protected int paramVal;
	protected int colSize;
	String dstDetailsFieldName;
	String dstDetailsType;
	String dstDetailsSheetName;
	String dstDetailsColumn;
	String dstDetailsDefault;
	String rateChangeKeys;
	String rateDecimalSeperator;
	String includeTimeOfDay;
	String dayGroupName;
	String dayGrpInclFl;
	String dayGrpCol;
	String dayGrpWorkSheet;
	String dstTypeLookUpMapping;
	String dstTypeMapping;
	String dstColumnMapping;
	String currLookUpMapping;
	String currMapping;
	String trnInclFlMapping;
	String trnMapping;
	String trnRateColumnMapping;
	String trnSetUpAmountMapping;
	String trnRateNameKeyMapping;
	String advanceRating;
	String flatRateMatchString;
	String rejectDuplicate;
	String duplicateRateAction;
	String duplicateEffectiveDateAction;
	String ruleDefintionRuleType;
	String ruleDefinitionSearchString;
	String ruleDefinitionSheetName;
	String ruleDefinitionColumnName;
	String spcecialCharParserType;
	String spcecialCharParserTypeCharacters;
	String expirationStrategy;
	String columnIndex;
	String rowNumber;
	String sheetName;
	String expirationStrategyTC;

	
	Map<String,String> configMap = new HashMap<String, String>();
	PSStringUtils strUtilObj = new PSStringUtils();
	PSGenericHelper genericObj = new PSGenericHelper();
	public ConfigurationMapping(Map<String,String> map) {
		this.configMap = map;
		initialize(map);
	}
	
	private void initialize( Map<String, String> map )
	{
		dstDetailsFieldName = 	map.get("DestinationDetailsField");
		dstDetailsType =	map.get("DestinationDetailsType");
		dstDetailsSheetName =	map.get("DestinationDetailsShetName");
		dstDetailsColumn =	map.get("DestinationDetailsColumn");
		dstDetailsDefault =	map.get("DestinationDetailsDefault");
		rateChangeKeys =	map.get("RateChangeKeys");
		rateDecimalSeperator =	map.get("RateDecimalSeperator");
		includeTimeOfDay =	map.get("IncludeTimeOfDay");
		dayGroupName =	map.get("DayGroupName");
		dayGrpInclFl =	map.get("DayGroupIncludeFl");
		dayGrpCol =	map.get("DayGroupColumn");
		dayGrpWorkSheet = map.get("DayGroupWorkSheet");
		advanceRating =	map.get("AdvanceRating");
		flatRateMatchString =	map.get("FlatRateMatchString");
		rejectDuplicate =	map.get("RejectDuplicate");
		duplicateRateAction =	map.get("DuplicateRateAction");
		duplicateEffectiveDateAction =	map.get("DuplicateEffectiveDateAction");
		ruleDefintionRuleType =	map.get("RuleDefintionRuleType");
		ruleDefinitionSearchString =	map.get("RuleDefinitionSearchString");
		ruleDefinitionSheetName =	map.get("RuleDefinitionSheetName");
		ruleDefinitionColumnName =	map.get("RuleDefinitionColumnName");
		spcecialCharParserType =	map.get("SpcecialCharParserType");
		spcecialCharParserTypeCharacters =	map.get("SpcecialCharParserTypeCharacters");
		dstTypeLookUpMapping = map.get( "DestinationTypeLookUp" );
		dstTypeMapping = map.get( "DestinationType" );
		dstColumnMapping = map.get( "DestinationColumn" );
		currLookUpMapping = map.get( "CurrencyLookUp" );
		trnMapping = map.get( "TRN" );
		currMapping = map.get( "Currency" );
		trnInclFlMapping  = map.get( "TRN-IncludeFl" );		
		trnRateColumnMapping  = map.get( "TRN-RateColumn" );
		trnSetUpAmountMapping = map.get( "TRN-SetUpAmount" );
		trnRateNameKeyMapping  = map.get( "TRN-RateNameKey" );
		
		expirationStrategyTC =  map.get( "ExpirationStrategyTC" );
	
	}
	
	public void originOrDestConfigurations(String path, String workBook, String sheetName) throws Exception { 
		naivgateToDestinationDetailsGridTab();
		destinationDetailGridConfig();
		rateChangePanelConfig();
		if(!expirationStrategyTC.isEmpty())
			expirationStrategyTestCase(path, workBook, sheetName, expirationStrategyTC);
		AdvanceRatingImpl advRatingObj = new AdvanceRatingImpl( this.configMap );
		advRatingObj.advanceRating();
		
		
			includeTimeOfDayGridConfig();
		
		mappingTab();
			
	}
	
	protected void mappingTab() throws Exception {
		
		boolean isdstTypeLookUpMapping = Boolean.valueOf( !dstTypeLookUpMapping.isEmpty() );
		boolean isdstColumnMapping = Boolean.valueOf(!dstColumnMapping.isEmpty());
		boolean iscurrLookUpMapping = Boolean.valueOf(!currLookUpMapping.isEmpty());
		boolean istrnMapping = Boolean.valueOf(!trnMapping.isEmpty());
		
		if(isdstTypeLookUpMapping || isdstColumnMapping || iscurrLookUpMapping || istrnMapping) {
			naivgateToMappingTab();
		
			if(isdstColumnMapping)
				dstColumnMappingConfig();
			
			if(isdstTypeLookUpMapping)
			dstTypeLookUpMappingConfig();
		
		
		if(iscurrLookUpMapping)
			currencyLookUpMapping();
		
		if(istrnMapping)
			trnConfigLookUpMapping();
		}
	}
	
	protected void naivgateToMappingTab() throws Exception {
		
		
		TabHelper.gotoTab( "//div[@id='rateSheetImportInfoDetail']//div[text()='Mapping']" );
		GenericHelper.waitForLoadmask();
	}
	
	protected void naivgateToDestinationDetailsGridTab() throws Exception {
		TabHelper.gotoTab( "//div[@id='rateSheetImportInfoDetail']//div[text()='Destination Details']" );
		GenericHelper.waitForLoadmask();
	}
	
	protected void destinationDetailGridConfig() throws Exception {
		String[] dstDetailsFieldNameArr= strUtilObj.stringSplitFirstLevel( dstDetailsFieldName );
		String[] dstDetailsTypeArr= strUtilObj.stringSplitFirstLevel( dstDetailsType );
		String[] dstDetailsSheetNameArr= strUtilObj.stringSplitFirstLevel( dstDetailsSheetName );
		String[] dstDetailsColumnArr= strUtilObj.stringSplitFirstLevel( dstDetailsColumn );
		String[] dstDetailsDefaultArr= strUtilObj.stringSplitFirstLevel( dstDetailsDefault );
		
		System.out.println( dstDetailsFieldNameArr+" length "+dstDetailsFieldNameArr.length );
		System.out.println( dstDetailsTypeArr+" length "+dstDetailsTypeArr.length );
		System.out.println( dstDetailsSheetNameArr+" length "+dstDetailsSheetNameArr.length );
		System.out.println( dstDetailsColumnArr+" length "+dstDetailsColumnArr.length );
		System.out.println( dstDetailsDefaultArr+" length "+dstDetailsDefaultArr.length );
		
		for(int rowIndex =0 ; rowIndex < dstDetailsFieldNameArr.length; rowIndex++) {
			
			GridHelper.clickRow( or.getProperty( "PS_RSDestinationDetailGridId" ), dstDetailsFieldNameArr[rowIndex] , "Field");
			int rowNum = GridHelper.getRowNumber( or.getProperty( "PS_RSDestinationDetailGridId" ), dstDetailsFieldNameArr[rowIndex] );
			if(!dstDetailsType.isEmpty())
				GridHelper.updateGridComboBox( or.getProperty( "PS_RSDestinationDetailGridId" ), or.getProperty( "PS_RSDestDetailTypeGridComboId" ), rowNum, "Type", dstDetailsTypeArr[rowIndex] );
			
			if(!dstDetailsSheetName.isEmpty())
				GridHelper.updateGridComboBox( or.getProperty( "PS_RSDestinationDetailGridId" ), or.getProperty( "PS_RSDestDetailSheetNameGridComboId" ), rowNum, "Sheet Name", dstDetailsSheetNameArr[rowIndex] );
			
			if(!dstDetailsColumn.isEmpty())
				GridHelper.updateGridTextBox( or.getProperty( "PS_RSDestinationDetailGridId" ), or.getProperty( "PS_RSDestDetailColumnGridTextId" ), rowNum, "Column", dstDetailsColumnArr[rowIndex] );
			
			if(!dstDetailsDefault.isEmpty())
				GridHelper.updateGridComboBox( or.getProperty( "PS_RSDestinationDetailGridId" ), or.getProperty( "PS_RSDestDetailDefaultGridComboId" ), rowNum, "Default", dstDetailsDefaultArr[rowIndex] );
		}
	}
	
	protected void rateChangePanelConfig() throws Exception {
		TextBoxHelper.type(or.getProperty( "PS_RSDestRateChangeKeysTxtId" ),rateChangeKeys);
		ComboBoxHelper.select( or.getProperty( "PS_RSDestRateDecimalSeperatorComboId" ), rateDecimalSeperator );	
	}
	
	
	protected void advanceRating() throws Exception {
		boolean isflatRateMatchString = Boolean.valueOf( flatRateMatchString.isEmpty() );
		boolean isrejectDuplicate = Boolean.valueOf( rejectDuplicate.isEmpty() );
		boolean isruleDefintionRuleType = Boolean.valueOf( ruleDefintionRuleType.isEmpty() );
		boolean isspcecialCharParserType = Boolean.valueOf( spcecialCharParserType.isEmpty());
		AdvanceRatingImpl advRatingObj= null;
		
		if(isflatRateMatchString || isrejectDuplicate ||isruleDefintionRuleType || isspcecialCharParserType) {
			
			ButtonHelper.click("advanceConfiguration");
			GenericHelper.waitForLoadmask();
			advRatingObj = new AdvanceRatingImpl( this.configMap );
			
			if(isflatRateMatchString)
				advRatingObj.validateScreenTitle();
			
			if(isrejectDuplicate)
				advRatingObj.elementMatchingAndRecordConfig();
			
			if(isruleDefintionRuleType)
				advRatingObj.ruleDefinitions();
			
			if(isspcecialCharParserType)
				advRatingObj.specialCharacterDefinitions();
			
			ButtonHelper.click( "ratesheetAdvConfigDetail.oK" );
			GenericHelper.waitForLoadmask();
			Log4jHelper.logInfo( "advance rating is configured" );
		}
	}
	
	protected void includeTimeOfDayGridConfig() throws Exception {
		
		if(ValidationHelper.isTrue( includeTimeOfDay )) 			
			CheckBoxHelper.check( or.getProperty( "PS_RSDestInclDayGrpCheckBoxId" ) );
			
		if(!dayGroupName.isEmpty())
		{
			String[] dayGrpInclArr  = strUtilObj.stringSplitFirstLevel( dayGrpInclFl);
			String[] dayGrpColArr  = strUtilObj.stringSplitFirstLevel(dayGrpCol);
			String[] dayGrpNameArr  = strUtilObj.stringSplitFirstLevel(dayGroupName);
			String[] dayGrpWorkSheetArr = strUtilObj.stringSplitFirstLevel(dayGrpWorkSheet);
			if((dayGrpInclArr.length != dayGrpNameArr.length) && (dayGrpNameArr.length != dayGrpColArr.length))
				throw new RuntimeException( "column size not matching across daygroupInclFlag, dayGrpColFlag, dayGrpNameFl" );
				
			for(int i =0 ; i < dayGrpNameArr.length; i++) {			
				
				GridHelper.rightClick( or.getProperty( "PS_RSDetailDayGrpGridId" ), dayGrpNameArr[i], "Day  Group  Name" );
				ButtonHelper.click("dayGrpGridContextMenu.Edit");
				GenericHelper.waitForLoadmask();
				dayGroupDetails(dayGrpInclArr[i], dayGrpColArr[i], dayGrpNameArr[i], dayGrpWorkSheetArr[i] );
				Log4jHelper.logInfo( "Day group info saved successfully for TRN : " +  dayGrpWorkSheetArr[i]);
			}
		
		}
	}
	
	protected void dayGroupDetails(String dayGrpInclFl, String dayGrpCol, String dayGrpName, String worksheet) throws Exception {
		CheckBoxHelper.check( or.getProperty( "PS_RSDayGrpDetailInclFlagId" ) );
		TextBoxHelper.type( or.getProperty( "PS_RSDayGrpDetailColIndexTxtId" ), dayGrpCol );
		ComboBoxHelper.select( or.getProperty( "PS_RSDayGrpDetailWorkSheetComboId" ), worksheet );
		ButtonHelper.click( "rateSheetDgpimpLookupDetail.ok" );
		GenericHelper.waitForLoadmask();
		
	}
	
	protected void dstTypeLookUpMappingConfig() throws Exception {
		
		String[] dstTypeLookUpMappingArr = strUtilObj.stringSplitFirstLevel( dstTypeLookUpMapping );
		String[] dstTypeMappingArr = strUtilObj.stringSplitFirstLevel( dstTypeMapping );
		for(int rowIndex=0; rowIndex <dstTypeLookUpMappingArr.length; rowIndex++)
		{
			ButtonHelper.click( or.getProperty("PS_RSMappingDstTypeLookUpAddBtnId") );
			GridHelper.updateGridTextBox( or.getProperty( "PS_RSMappingDstTypeGridId" ), or.getProperty( "PS_RSMappingDstTypeTxtId" ), rowIndex+1, "Destination  Type  Lookup", dstTypeLookUpMappingArr[rowIndex] );
			GridHelper.updateGridComboBox(  or.getProperty( "PS_RSMappingDstTypeGridId" ), or.getProperty( "PS_RSMappingDstTypeComboId" ), rowIndex+1, "Destination Type", dstTypeMappingArr[rowIndex] );
			Log4jHelper.logInfo( dstTypeLookUpMappingArr[rowIndex] + " configured in destination type lookup grid" );
		}
	}
	
	protected void dstColumnMappingConfig() throws Exception{
		
		String[] dstColumnMappingArr = strUtilObj.stringSplitFirstLevel( dstColumnMapping );
		for(int rowIndex=0; rowIndex <dstColumnMappingArr.length; rowIndex++)
		{
			ButtonHelper.click( or.getProperty("PS_RSMappingDstColAddBtnId") );
			GridHelper.updateGridTextBox( or.getProperty( "PS_RSMappingDstGridId" ), or.getProperty( "PS_RSMappingDstColGridTxtId" ), rowIndex+1, "Column", dstColumnMappingArr[rowIndex] );
			Log4jHelper.logInfo( "destination column : "+ dstColumnMappingArr[rowIndex] + " configured in destination column mapping grid" );
		}
	}
	
	
	protected void currencyLookUpMapping() throws Exception {
		String[] currLookUpMappingArr = strUtilObj.stringSplitFirstLevel( currLookUpMapping );
		String[] currMappingArr = strUtilObj.stringSplitFirstLevel( currMapping );
		
		for(int rowIndex=0; rowIndex< currLookUpMappingArr.length; rowIndex++) {
			ButtonHelper.click( or.getProperty( "PS_RSMappingGridAddBtnXpath" ) );
			GridHelper.updateGridTextBox( or.getProperty( "PS_RSMappingCurGridId" ), or.getProperty( "PS_RSMappingCurrLookUpTxtId" ), rowIndex+1, " Currency Lookup", currLookUpMappingArr[rowIndex] );
			GridHelper.updateGridComboBox( or.getProperty( "PS_RSMappingCurGridId" ), or.getProperty( "PS_RSMappingCurrGridComboId" ), rowIndex+1, "Currency", currMappingArr[rowIndex] );
			Log4jHelper.logInfo( currLookUpMappingArr[rowIndex] + " configured in currency lookup grid" );
		}
	}
	
	protected void trnConfigLookUpMapping() throws Exception {
		
		String[] trnInclFlMappingArr = strUtilObj.stringSplitFirstLevel( trnInclFlMapping );
		String[] trnMappingArr = strUtilObj.stringSplitFirstLevel( trnMapping );
		String[] trnRateColumnMappingArr = strUtilObj.stringSplitFirstLevel( trnRateColumnMapping );
		String[] trnSetUpAmountMappingArr = strUtilObj.stringSplitFirstLevel( trnSetUpAmountMapping );
		String[] trnRateNameKeyMappingArr = strUtilObj.stringSplitFirstLevel( trnRateNameKeyMapping );
		
		for(int rowIndex=0; rowIndex< trnInclFlMappingArr.length; rowIndex++) {
			if(trnMappingArr[rowIndex].contains( "Weekend" ))
			{
			String locator = "//*[@id='trnGrid']//*[text()='Weekend']";			
			ElementHelper.scrollOneLevelDown( locator, 1 );
			}
				
			GridHelper.rightClick( or.getProperty( "PS_RSMappingTrnGridId" ), trnMappingArr[rowIndex], "Tariff Rate Name" );			
			GenericHelper.waitForLoadmask();
			ButtonHelper.click( or.getProperty( "PS_RSTRNMappingRightClickEditBtnId" ) );
			GenericHelper.waitForLoadmask();
			CheckBoxHelper.check( or.getProperty( "PS_RSTRNMappingInclCheckBoxId" ) );
			//TextBoxHelper.type("pitnIndex", trnRateColumnMappingArr[rowIndex] , "Rate Column");
			//TextBoxHelper.type( "pistIndex", trnSetUpAmountMappingArr[rowIndex] , "Setup Amount Column");
			TextBoxHelper.type(or.getProperty( "PS_RSTRNMappingDetailRateNameKeyTxtId" ), trnRateNameKeyMappingArr[rowIndex]);
			ButtonHelper.click( "trnImportDetail.ok" );
			GenericHelper.waitForLoadmask();
			
			Log4jHelper.logInfo( trnMappingArr[rowIndex] + " configured in tariff rate name lookup grid" );
			
		}
		
	}
	
	
	public void expirationStrategy(Map<String, String> map) throws Exception
	{
		initializeStrategy( map );
		
			ButtonHelper.click( "completeExpiryConfig" );
			Thread.sleep( 2000 );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		assertTrue( ElementHelper.isElementPresent( "//div[contains(text(),'Expiration  Strategy')]") , "Expiration Strategy popup is not opened");
		ComboBoxHelper.select( "pedcStrategyCmpId_gwt_uid_" , expirationStrategy);
		
		if(expirationStrategy.contains( "Input From Cell" ))
		{
			//GridHelper.updateGridTextBox( "property", "id", 1, "VAlue", value );
			PropertyGridHelper.typeInTextBox( "Column Index *", columnIndex );
			PropertyGridHelper.typeInTextBox( "Row Number *", rowNumber );
			PropertyGridHelper.typeInTextBox( "Sheet Name *", sheetName );
			
		}
		ButtonHelper.click( "completeRateSheetExpiryConfDetail.oK" );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		
	}
	
	private void expirationStrategyTestCase( String path, String workBookName, String sheetName, String testCaseName ) throws Exception
	{
		excelReader =  new ExcelReader();
		excelReaderMapObj = excelReader.readDataByColumn( path, workBookName, sheetName, testCaseName );
		excelHolderObj = new ExcelHolder( excelReaderMapObj );
		mapObj = excelHolderObj.dataMap( 0 );
		expirationStrategy(mapObj);
	}
	
	public void initializeStrategy(Map<String, String> map)
	{
		expirationStrategy  = map.get( "ExpirationStrategy" );
		columnIndex  = map.get( "ColumnIndex" );
		rowNumber  = map.get( "RowNumber" );
		sheetName  = map.get( "SheetName" );
	}
}
