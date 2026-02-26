package com.subex.rocps.automation.helpers.application.tariffs;

import org.testng.Assert;

import java.util.ArrayList;
import java.util.Map;

import com.subex.rocps.automation.helpers.application.genericHelpers.PSGenericHelper;
import com.subex.rocps.automation.helpers.application.tariffs.ratesheettemplate.ConfigurationMapping;
import com.subex.rocps.automation.helpers.application.tariffs.ratesheettemplate.RateSheetImportTemplateImpl;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;
import com.subex.rocps.automation.utils.ExcelHolder;
import com.subex.rocps.automation.utils.PSStringUtils;

import com.subex.automation.helpers.application.NavigationHelper;
import com.subex.automation.helpers.component.ButtonHelper;
import com.subex.automation.helpers.component.GenericHelper;
import com.subex.automation.helpers.component.GridHelper;
import com.subex.automation.helpers.file.ExcelReader;
import com.subex.automation.helpers.report.Log4jHelper;
import com.subex.automation.helpers.util.FailureHelper;

public class RateSheetTemplateConfiguration extends PSAcceptanceTest
{
	protected ExcelReader excelReader = null;
	protected Map<String, ArrayList<String>> excelReaderMapObj = null;
	protected Map<String, String> mapObj = null;
	Map<String,String> configuratonMap = null;
	protected ExcelHolder excelHolderObj = null;

	int colSize = 0;
	int occurence = 0;
	String path;
	String workBook;
	String sheetName;
	String testCaseName;
	
	String partition;
	String templateName;
	String originConfigTestCase;
	String destinationConfigTestCase;
	protected PSGenericHelper genHelperObj = new PSGenericHelper();
	RateSheetImportTemplateImpl rstImpTmpObj = null;
	
	
	public RateSheetTemplateConfiguration( String path, String workBook, String sheetName, String testCaseName ) throws Exception
	{
		this.path = path;
		this.workBook = workBook;
		this.sheetName = sheetName;
		this.testCaseName = testCaseName;
		excelReader = new ExcelReader();
		excelReaderMapObj = excelReader.readDataByColumn( this.path, this.workBook, this.sheetName, this.testCaseName );
		excelHolderObj = new ExcelHolder( excelReaderMapObj );
		colSize = excelHolderObj.totalColumns();

	}
	
	public void originConfigRateSheetTemplate() throws Exception {
		try
		{
			for ( int paramVal = 0; paramVal < colSize; paramVal++ )
			{
				mapObj = excelHolderObj.dataMap( paramVal );
				initialiseVariables( mapObj );
				
				NavigationHelper.navigateToScreen( "Rate Sheet Import Template" );
				String rstSrchTemplateColHeader = "Template Name";
				boolean isRateSheetTempExists = genHelperObj.isGridTextValuePresent( "PS_RSDetailTemplateNameTxtId", templateName, rstSrchTemplateColHeader );
				if(!isRateSheetTempExists) {
					genHelperObj.clickNewAction( partition );
					rstImpTmpObj = new RateSheetImportTemplateImpl(mapObj);
					rstImpTmpObj.detailTabBasicDetails();
					configuratonMap = excelTestDataInitialize(this.path, this.workBook, this.sheetName,originConfigTestCase);
					rstImpTmpObj.originConfig( this.path, this.workBook, this.sheetName,configuratonMap );
					configuratonMap = null;					
					configuratonMap = excelTestDataInitialize(this.path, this.workBook, this.sheetName,destinationConfigTestCase);
					rstImpTmpObj.destinationConfig(this.path, this.workBook, this.sheetName, configuratonMap );
					rstImpTmpObj.tariffsConfig();
					rstImpTmpObj.mappingSheets();
					rstImpTmpObj.rateSheetTemplateDetailSave();
					Assert.assertTrue(GridHelper.isValuePresent("SearchGrid", templateName, rstSrchTemplateColHeader),
							"Rate Sheet Template : " + templateName + "is not saved" + testCaseName);
					Log4jHelper.logInfo( "rate sheet template is saved successfully : " + templateName );
				}
				else
					Log4jHelper.logInfo( "rate sheet template exists : " + templateName );
			}
			
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}
	
	
	public void destinationConfigRateSheetTemplate() throws Exception {
		try
		{
			for ( int paramVal = 0; paramVal < colSize; paramVal++ )
			{
				mapObj = excelHolderObj.dataMap( paramVal );
				initialiseVariables( mapObj );
				
				NavigationHelper.navigateToScreen( "Rate Sheet Import Template" );
				String rstSrchTemplateColHeader = "Template Name";
				ButtonHelper.click( "ClearButton" );
				GenericHelper.waitForLoadmask( searchScreenWaitSec );
				boolean isRateSheetTempExists = genHelperObj.isGridTextValuePresent( "PS_RSDetailTemplateNameTxtId", templateName, rstSrchTemplateColHeader );
				if(!isRateSheetTempExists) {
					genHelperObj.clickNewAction( partition );				
					rstImpTmpObj = new RateSheetImportTemplateImpl(mapObj);
					rstImpTmpObj.detailTabBasicDetails();					
					configuratonMap = excelTestDataInitialize(this.path, this.workBook, this.sheetName,destinationConfigTestCase);
					rstImpTmpObj.destinationConfig( this.path, this.workBook, this.sheetName,configuratonMap );					
					rstImpTmpObj.tariffsConfig();
					rstImpTmpObj.mappingSheets();
					rstImpTmpObj.rateSheetTemplateDetailSave();
					Assert.assertTrue(GridHelper.isValuePresent("SearchGrid", templateName, rstSrchTemplateColHeader),
							"Event match rule : " + templateName + "is not saved" + testCaseName);
					Log4jHelper.logInfo( "rate sheet template is saved successfully : " + templateName );
				}
				else
					Log4jHelper.logInfo( "rate sheet template exists : " + templateName );
			}
			
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

	private void initialiseVariables( Map<String, String> map )
	{
		partition = map.get("Partition");
		templateName = map.get( "TemplateName" );
		originConfigTestCase = map.get("OriginConfig");
		destinationConfigTestCase = map.get("DestinationConfig");
	}
	
	/*
	 * This method is to validate search screen columns
	 */
	public void searchScreenColumnsValidation() throws Exception
	{		
		NavigationHelper.navigateToScreen( "Rate Sheet Import Template" );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		for ( int paramVal = 0; paramVal < colSize; paramVal++ )
		{
			mapObj = excelHolderObj.dataMap( paramVal );
			String searchScreenColumns = ExcelHolder.getKey( mapObj, "SearchScreenColumns" );
			ArrayList<String> excelColumnNames = new ArrayList<String>();
			PSStringUtils strObj = new PSStringUtils();
			String[] searchGridColumnsArr = strObj.stringSplitFirstLevel( searchScreenColumns );
			for ( int col = 0; col < searchGridColumnsArr.length; col++ )
			{
				excelColumnNames.add( searchGridColumnsArr[col] );
			}
			genHelperObj.totalColumns( excelColumnNames );
		}

	}
	
	private Map<String,String> excelTestDataInitialize(String path, String workBook, String sheetName, String testCaseName) throws Exception{
		this.path = path;
		this.workBook = workBook;
		this.sheetName = sheetName;
		this.testCaseName = testCaseName;
		
		excelReaderMapObj = excelReader.readDataByColumn( this.path, this.workBook, this.sheetName, this.testCaseName );
		excelHolderObj = new ExcelHolder( excelReaderMapObj );
		return mapObj = excelHolderObj.dataMap( 0 );
	}
}
