package com.subex.rocps.automation.helpers.application.carrierinvoice;

import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Map;

import com.subex.rocps.automation.helpers.application.carrierinvoice.carrierinvoice.CarrierInvoiceActionImpl;
import com.subex.rocps.automation.helpers.application.carrierinvoice.carrierinvoicetemplate.CarrierInvoiceTemplateActionImpl;
import com.subex.rocps.automation.helpers.application.carrierinvoice.carrierinvoicetemplate.CarrierInvoiceTemplateDetaiImpl;
import com.subex.rocps.automation.helpers.application.carrierinvoice.carrierinvoicetemplate.SummaryDetailImpl;
import com.subex.rocps.automation.helpers.application.carrierinvoice.carrierinvoicetemplate.UsageTabDetailImpl;
import com.subex.rocps.automation.helpers.application.genericHelpers.DataVerificationHelper;
import com.subex.rocps.automation.helpers.application.genericHelpers.PSGenericHelper;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;
import com.subex.rocps.automation.utils.ExcelHolder;
import com.subex.rocps.automation.utils.PSStringUtils;

import com.subex.automation.helpers.application.NavigationHelper;
import com.subex.automation.helpers.component.ButtonHelper;
import com.subex.automation.helpers.component.GenericHelper;
import com.subex.automation.helpers.component.GridHelper;
import com.subex.automation.helpers.data.ValidationHelper;
import com.subex.automation.helpers.file.ExcelReader;
import com.subex.automation.helpers.report.Log4jHelper;
import com.subex.automation.helpers.util.FailureHelper;

public class CarrierInvoiceTemplate extends PSAcceptanceTest
{
	protected ExcelReader excelData = null;
	protected Map<String, ArrayList<String>> citempExcel = null;
	protected Map<String, String> citempMap = null;
	protected ExcelHolder excelHolderObj = null;
	protected String path;
	protected String workBookName;
	protected String sheetName;
	protected String testCaseName;
	protected int paramVal;
	protected int colSize;
	protected String partition;
	protected String templateName;
	protected String templateType;
	protected String usageTestCase;
	protected String nonUsageTestCase;
	protected String creditNoteTestCase;

	String regex = new PSStringUtils().regexFirstLevelDelimeter();
	CarrierInvoiceTemplateActionImpl ciActionObj = new CarrierInvoiceTemplateActionImpl();
	protected PSGenericHelper genericHelperObj = new PSGenericHelper();
	DataVerificationHelper dataVerifyObj = new DataVerificationHelper();

	/*
	 * Constructor for initializing excel Identifying the column size from the
	 * map passed
	 */
	@Test
	public CarrierInvoiceTemplate( String path, String workBookName, String sheetName, String testCaseName ) throws Exception
	{
		this.path = path;
		this.workBookName = workBookName;
		this.sheetName = sheetName;
		this.testCaseName = testCaseName;
		excelData = new ExcelReader();
		citempExcel = excelData.readDataByColumn( this.path, this.workBookName, this.sheetName, this.testCaseName );
		excelHolderObj = new ExcelHolder( citempExcel );
		colSize = excelHolderObj.totalColumns();
	}

	/*
	 * Overloaded constructor for reading data from excel if test case name
	 * appears more than once
	 * 
	 * @Param occurance : Test case instance in excel sheet Constructor for
	 * initializing excel Identifying the column size from the map
	 */
	public CarrierInvoiceTemplate( String path, String workBookName, String sheetName, String testCaseName, int occurance ) throws Exception
	{
		this.path = path;
		this.workBookName = workBookName;
		this.sheetName = sheetName;
		this.testCaseName = testCaseName;
		excelData = new ExcelReader();
		citempExcel = excelData.readDataByColumn( this.path, this.workBookName, this.sheetName, this.testCaseName, occurance );
		excelHolderObj = new ExcelHolder( citempExcel );
		colSize = excelHolderObj.totalColumns();
	}

	/*
	 * Configuring the Carrier Invoice Excel Template
	 * 
	 */
	public void carrierInvoiceExcelTemplateConfig() throws Exception
	{
		try
		{
			NavigationHelper.navigateToScreen( "Carrier Invoice Template" );
			for ( paramVal = 0; paramVal < colSize; paramVal++ )
			{
				citempMap = excelHolderObj.dataMap( paramVal );
				
				ButtonHelper.click( "ClearButton" );
				GenericHelper.waitForLoadmask( searchScreenWaitSec );
				initialiseVariables( citempMap );
				boolean isCarrierInvoicePresent = genericHelperObj.isGridTextValuePresent( "PS_CITemplate_Name_txtID",templateName , "Name" );
				if ( !isCarrierInvoicePresent )
				{
					CarrierInvoiceTemplateDetaiImpl detailObj = new CarrierInvoiceTemplateDetaiImpl( citempMap );
					detailObj.newCarrierInvoiceExcelTemplate();
					detailObj.basicDetailsConfig();
					detailObj.rulesConfig();
					SummaryDetailImpl summaryObj = new SummaryDetailImpl( citempMap );
					summaryObj.summaryTabConfig();				
					carrierInvoiceTabsConfig( usageTestCase, "Usage" );
					carrierInvoiceTabsConfig( nonUsageTestCase, "Non-Usage" );
					carrierInvoiceTabsConfig( creditNoteTestCase, "Credit Note" );
					detailObj.saveCarrierInvoiceTemplate();
				}
				else
					Log4jHelper.logInfo( "FieldMapping is available with name :" + templateName );

			}
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}
	
	public void carrierInvoiceTabsConfig(String testcase, String tabName) throws Exception
	{
		if(ValidationHelper.isNotEmpty(  testcase))
		{
			ciActionObj.switchToTab( tabName );
		UsageTabDetailImpl usgObj = new UsageTabDetailImpl( path, workBookName, sheetName, testcase );
		usgObj.usagTabConfiguration(tabName);
		}
	}

	/*
	 * This method is for search screen column validation
	 */
	public void searchScreenColumnsValidation() throws Exception
	{
		NavigationHelper.navigateToScreen( "Carrier Invoice" );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		for ( paramVal = 0; paramVal < colSize; paramVal++ )
		{
			citempMap = excelHolderObj.dataMap( paramVal );
			String searchScreenColumns = ExcelHolder.getKey( citempMap, "SearchScreenColumns" );
			ArrayList<String> excelColumnNames = new ArrayList<String>();
			String[] searchGridColumnsArr = searchScreenColumns.split( regex, -1 );
			for ( int col = 0; col < searchGridColumnsArr.length; col++ )
			{
				excelColumnNames.add( searchGridColumnsArr[col] );
			}
			genericHelperObj.totalColumns( excelColumnNames );
		}

	}
	/* This method is for Carrier Invoice Template un delete
	 */
	public void carrierInvoiceTempDelete() throws Exception
	{

		NavigationHelper.navigateToScreen( "Carrier Invoice Template" );
		for ( paramVal = 0; paramVal < colSize; paramVal++ )
		{
			citempMap = excelHolderObj.dataMap( paramVal );

			partition = ExcelHolder.getKey( citempMap, "Partition" );
			templateName = ExcelHolder.getKey( citempMap, "TemplateName" );
			genericHelperObj.selectPartitionFilter( partition, "Non Deleted Items" );
			boolean isCarrierInvoicePresent = genericHelperObj.isGridTextValuePresent( "PS_CITemplate_Name_txtID", templateName, "Name" );

			if ( isCarrierInvoicePresent )
			{
				genericHelperObj.clickDeleteOrUnDeleteAction( templateName, "Name", "Delete" );
				genericHelperObj.selectPartitionFilter( partition, "Deleted Items" );
				assertTrue( GridHelper.isValuePresent( "SearchGrid", templateName, "Name" ), templateName );
				Log4jHelper.logInfo( "Carrier Invoice Template is deleted successfully :" + templateName );
			}
			else
				Log4jHelper.logInfo( "Carrier Invoice Template is not available with :" + templateName );
		}
	}

	/*
	 * This method is for Carrier Invoice Template un delete
	 */
	public void carrierInvoiceTemplateUnDelete() throws Exception
	{

		NavigationHelper.navigateToScreen( "Carrier Invoice Template" );
		for ( paramVal = 0; paramVal < colSize; paramVal++ )
		{
			citempMap = excelHolderObj.dataMap( paramVal );
			partition = ExcelHolder.getKey( citempMap, "Partition" );
			templateName = ExcelHolder.getKey( citempMap, "TemplateName" );
			genericHelperObj.selectPartitionFilter( partition, "Deleted Items" );

			boolean isCarrierInvoicePresent = genericHelperObj.isGridTextValuePresent( "PS_CITemplate_Name_txtID", templateName, "Name" );

			if ( isCarrierInvoicePresent )
			{
				genericHelperObj.clickDeleteOrUnDeleteAction( templateName, "Name", "Undelete" );
				genericHelperObj.selectPartitionFilter( partition, "Non Deleted Items" );
				assertTrue( GridHelper.isValuePresent( "SearchGrid", templateName, "Name" ), templateName );
				Log4jHelper.logInfo( "Carrier Invoice Template is un deleted successfully :" + templateName );
			}
			else
				Log4jHelper.logInfo( "Carrier Invoice Template is not available with :" + templateName );
		}

	}
	private void initialiseVariables( Map<String, String> map )
	{	
		nonUsageTestCase = map.get("NonUsageTestCase");
		creditNoteTestCase = map.get( "CreditNoteTestCase" );
		templateName = map.get("TemplateName");
		usageTestCase = map.get( "UsageTestCase" );
	}
	
	
}
