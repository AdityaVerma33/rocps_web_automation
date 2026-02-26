package com.subex.rocps.automation.helpers.application.carrierinvoice;

import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Map;

import com.subex.rocps.automation.helpers.application.carrierinvoice.carrierinvoice.CarrierInvoiceActionImpl;
import com.subex.rocps.automation.helpers.application.carrierinvoice.carrierinvoiceimportsearch.CarrierInvoiceImportDetailImpl;
import com.subex.rocps.automation.helpers.application.carrierinvoice.carrierinvoiceimportsearch.CarrierInvoiceImportSearchImpl;
import com.subex.rocps.automation.helpers.application.genericHelpers.DataVerificationHelper;
import com.subex.rocps.automation.helpers.application.genericHelpers.PSGenericHelper;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;
import com.subex.rocps.automation.utils.ExcelHolder;
import com.subex.rocps.automation.utils.PSStringUtils;

import com.subex.automation.helpers.application.NavigationHelper;
import com.subex.automation.helpers.component.ButtonHelper;
import com.subex.automation.helpers.component.GenericHelper;
import com.subex.automation.helpers.component.GridHelper;
import com.subex.automation.helpers.component.PopupHelper;
import com.subex.automation.helpers.component.TextBoxHelper;
import com.subex.automation.helpers.file.ExcelReader;
import com.subex.automation.helpers.report.Log4jHelper;
import com.subex.automation.helpers.util.FailureHelper;

public class CarrierInvoiceImport extends PSAcceptanceTest
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
	protected String fileName;
	protected String billProfile;
	protected String carrierInvoicePeriod;
	CarrierInvoiceImportSearchImpl searchObj = new CarrierInvoiceImportSearchImpl();
	String regex = new PSStringUtils().regexFirstLevelDelimeter();
	CarrierInvoiceActionImpl ciActionObj = new CarrierInvoiceActionImpl();
	protected PSGenericHelper genericHelperObj = new PSGenericHelper();
	DataVerificationHelper dataVerifyObj = new DataVerificationHelper();

	/*
	 * Constructor for initializing excel Identifying the column size from the
	 * map passed
	 */
	public CarrierInvoiceImport( String path, String workBookName, String sheetName, String testCaseName ) throws Exception
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
	public CarrierInvoiceImport( String path, String workBookName, String sheetName, String testCaseName, int occurance ) throws Exception
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
	 * Configuring the Carrier Invoice import request
	 * 
	 */
	public void carrierInvoiceImportRequest() throws Exception
	{
		try
		{
			NavigationHelper.navigateToScreen( "Carrier Invoice Import" );
			for ( paramVal = 0; paramVal < colSize; paramVal++ )
			{
				citempMap = excelHolderObj.dataMap( paramVal );

				initializeVariables( citempMap );
				newCarrierInvoiceImport();
				
			}
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

	
	public void newCarrierInvoiceImport() throws Exception
	{
		CarrierInvoiceImportDetailImpl ciDetailObj = new CarrierInvoiceImportDetailImpl(citempMap);
		ciDetailObj.newCIImportRequest();
		ciDetailObj.configCarrierInvoiceImport();
		ciDetailObj.saveCarrierInvoiceImportRequest();
	}
	/*
	 * This method is for search screen column validation
	 */
	public void searchScreenColumnsValidation() throws Exception
	{
		NavigationHelper.navigateToScreen( "Carrier Invoice Import" );
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
	
	
	public void requestValidation() throws Exception
	{
		NavigationHelper.navigateToScreen( "Carrier Invoice Import" );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		for ( paramVal = 0; paramVal < colSize; paramVal++ )
		{
			citempMap = excelHolderObj.dataMap( paramVal );
			initializeVariables( citempMap );
			if(searchObj.isCarrierInvoiceRequestPresent(fileName, templateName, carrierInvoicePeriod))
			{
				searchObj.checkTaskStatus();
				Log4jHelper.logInfo( "CI Import request is successfull." );
			}else
				Log4jHelper.logInfo( "Carrier Invoice request is not available" );
		}
	}
	public void scheduleNow() throws Exception
	{
		NavigationHelper.navigateToScreen( "Carrier Invoice Import" );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		for ( paramVal = 0; paramVal < colSize; paramVal++ )
		{
			citempMap = excelHolderObj.dataMap( paramVal );
			initializeVariables( citempMap );
			if(searchObj.isCarrierInvoiceRequestPresent(fileName, templateName, carrierInvoicePeriod))
			{
				GridHelper.clickRow( "SearchGrid", 1, "Template" );
				assertTrue( GridHelper.isValuePresent( "SearchGrid", "Unscheduled" , "Status") );
				NavigationHelper.navigateToAction( "Schedule Now");
				GenericHelper.waitForLoadmask( searchScreenWaitSec );
				assertTrue(PopupHelper.isPresent( "window-scroll-panel"));
				ButtonHelper.click( "OKButton" );
				GenericHelper.waitForLoadmask( searchScreenWaitSec );				
				assertEquals( GridHelper.isValuePresent( "SearchGrid", "Scheduled" , "Status"), "Scheduled" );
			}else
				Log4jHelper.logInfo( "Carrier Invoice request is not available" );
		}
	}
	
	public void viewTemplate() throws Exception
	{
		NavigationHelper.navigateToScreen( "Carrier Invoice Import" );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		for ( paramVal = 0; paramVal < colSize; paramVal++ )
		{
			citempMap = excelHolderObj.dataMap( paramVal );
			initializeVariables( citempMap );
			if(searchObj.isCarrierInvoiceRequestPresent(fileName, templateName, carrierInvoicePeriod))
			{
				GridHelper.clickRow( "SearchGrid", 1, "Template" );				
				NavigationHelper.navigateToAction( "View", "View Template");
				GenericHelper.waitForLoadmask( searchScreenWaitSec );
				assertEquals(NavigationHelper.getScreenTitle(), "View Excel Invoice Template");
				assertEquals( TextBoxHelper.getValue( "pintName" ), templateName );
				assertEquals( TextBoxHelper.getValue( "scucFilePath" ), fileName );
				ButtonHelper.click( "CloseButton" );
				GenericHelper.waitForLoadmask( searchScreenWaitSec );				
				
			}else
				Log4jHelper.logInfo( "Carrier Invoice request is not available" );
		}
	}
	
	
	public void initializeVariables(Map<String, String> map) throws Exception
	{
		fileName = ExcelHolder.getKey( map, "FileName" );
		templateName = ExcelHolder.getKey( map, "TemplateName" );
		billProfile = ExcelHolder.getKey( map, "BillProfile" );
		carrierInvoicePeriod = ExcelHolder.getKey( map, "CarrierInvoicePeriod" );
	}
}
