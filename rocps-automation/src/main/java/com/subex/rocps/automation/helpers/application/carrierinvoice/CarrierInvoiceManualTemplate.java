package com.subex.rocps.automation.helpers.application.carrierinvoice;

import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Map;

import com.subex.rocps.automation.helpers.application.carrierinvoice.carrierinvoice.CarrierInvoiceActionImpl;
import com.subex.rocps.automation.helpers.application.carrierinvoice.carrierinvoicetemplate.CarrierInvoiceTemplateActionImpl;
import com.subex.rocps.automation.helpers.application.carrierinvoice.carrierinvoicetemplate.ManualCITemplateTabDetailsImpl;
import com.subex.rocps.automation.helpers.application.genericHelpers.DataVerificationHelper;
import com.subex.rocps.automation.helpers.application.genericHelpers.PSGenericHelper;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;
import com.subex.rocps.automation.utils.ExcelHolder;
import com.subex.rocps.automation.utils.PSStringUtils;

import com.subex.automation.helpers.application.NavigationHelper;
import com.subex.automation.helpers.component.ButtonHelper;
import com.subex.automation.helpers.component.GenericHelper;
import com.subex.automation.helpers.data.ValidationHelper;
import com.subex.automation.helpers.file.ExcelReader;
import com.subex.automation.helpers.report.Log4jHelper;
import com.subex.automation.helpers.util.FailureHelper;

public class CarrierInvoiceManualTemplate extends PSAcceptanceTest
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
	protected String colHeaders;
	protected String results;

	String regex = new PSStringUtils().regexFirstLevelDelimeter();
	CarrierInvoiceTemplateActionImpl ciActionObj = new CarrierInvoiceTemplateActionImpl();
	protected PSGenericHelper genericHelperObj = new PSGenericHelper();
	DataVerificationHelper dataVerifyObj = new DataVerificationHelper();

	/*
	 * Constructor for initializing excel Identifying the column size from the
	 * map passed
	 */
	@Test
	public CarrierInvoiceManualTemplate( String path, String workBookName, String sheetName, String testCaseName ) throws Exception
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
	public CarrierInvoiceManualTemplate( String path, String workBookName, String sheetName, String testCaseName, int occurance ) throws Exception
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
	public void ciManualTemplateConfig() throws Exception
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
					ManualCITemplateTabDetailsImpl usgObj = new ManualCITemplateTabDetailsImpl( path, workBookName, sheetName, testCaseName );
					usgObj.newCarrierInvoiceManualTemplate(partition, templateType, templateName);
					manualCarrierInvoiceTabsConfig(usageTestCase, "Usage");
					manualCarrierInvoiceTabsConfig( nonUsageTestCase, "Non-Usage" );
					manualCarrierInvoiceTabsConfig( creditNoteTestCase, "Credit Note" );
					usgObj.saveCarrierInvoiceTemplate(templateName);
					if(ValidationHelper.isNotEmpty( results ))
						dataVerifyObj.validateData( "grid_column_header_searchGrid_", colHeaders, "SearchGrid", results );
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
	
	public void manualCarrierInvoiceTabsConfig(String testcase, String tabName) throws Exception
	{
		if(ValidationHelper.isNotEmpty(  testcase))
		{
		ciActionObj.switchToTab( tabName );
		ManualCITemplateTabDetailsImpl usgObj = new ManualCITemplateTabDetailsImpl( path, workBookName, sheetName, testcase );
		usgObj.usagTabConfiguration(tabName);
		}
	}
	
	private void initialiseVariables( Map<String, String> map )
	{	
		templateType = map.get( "TemplateType" );
		nonUsageTestCase = map.get("NonUsageTestCase");
		creditNoteTestCase = map.get( "CreditNoteTestCase" );
		templateName = map.get("TemplateName");
		usageTestCase = map.get( "UsageTestCase" );
		colHeaders = map.get("ColHeaders");
		results = map.get( "Results" );
		partition = map.get( "Partition" );
	}
}
