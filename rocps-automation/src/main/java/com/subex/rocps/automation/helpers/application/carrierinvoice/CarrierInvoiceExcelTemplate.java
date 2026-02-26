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
import com.subex.automation.helpers.component.ComboBoxHelper;
import com.subex.automation.helpers.component.GenericHelper;
import com.subex.automation.helpers.component.GridHelper;
import com.subex.automation.helpers.component.TextBoxHelper;
import com.subex.automation.helpers.data.ValidationHelper;
import com.subex.automation.helpers.file.ExcelReader;
import com.subex.automation.helpers.report.Log4jHelper;
import com.subex.automation.helpers.util.FailureHelper;

public class CarrierInvoiceExcelTemplate extends PSAcceptanceTest
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
	public CarrierInvoiceExcelTemplate( String path, String workBookName, String sheetName, String testCaseName ) throws Exception
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
	public CarrierInvoiceExcelTemplate( String path, String workBookName, String sheetName, String testCaseName, int occurance ) throws Exception
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
				boolean isCarrierInvoicePresent = genericHelperObj.isGridTextValuePresent( "PS_CITemplate_Name_txtID", templateName, "Name" );
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
					if ( ValidationHelper.isNotEmpty( results ) )
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

	public void carrierInvoiceTabsConfig( String testcase, String tabName ) throws Exception
	{
		if ( ValidationHelper.isNotEmpty( testcase ) )
		{
			ciActionObj.switchToTab( tabName );
			UsageTabDetailImpl usgObj = new UsageTabDetailImpl( path, workBookName, sheetName, testcase );
			usgObj.usagTabConfiguration( tabName );
		}
	}

	public void carrireInvoiceTemplateSaveAs() throws Exception
	{
		NavigationHelper.navigateToScreen( "Carrier Invoice Template" );
		for ( paramVal = 0; paramVal < colSize; paramVal++ )
		{
			citempMap = excelHolderObj.dataMap( paramVal );
			templateName = ExcelHolder.getKey( citempMap, "TemplateName" );
			String ciFileName = ExcelHolder.getKey( citempMap, "CIFileName" );
			String dateFormat = ExcelHolder.getKey( citempMap, "DateFormat" );
			boolean isCarrierInvoiceTempPresent = genericHelperObj.isGridTextValuePresent( "PS_CITemplate_Name_txtID", templateName, "Name" );

			if ( isCarrierInvoiceTempPresent )
			{
				GridHelper.clickRow( "SearchGrid", templateName, "Name" );
				ciActionObj.navigateToSaveAsAction();
				CarrierInvoiceTemplateDetaiImpl detailObj = new CarrierInvoiceTemplateDetaiImpl( citempMap );
				TextBoxHelper.type( "PS_CITemplate_Name_txtID", templateName );
				assertEquals( TextBoxHelper.getValue( "scucFilePath" ), ciFileName );
				assertEquals( ComboBoxHelper.getValue( "PS_CITemplate_dateFormat_comboID" ), dateFormat );
				detailObj.saveCarrierInvoiceTemplate();

				Log4jHelper.logInfo( "Carrier Invoice Template save as action is performed for " + templateName );
			}
			else
				Log4jHelper.logInfo( "Carrier Invoice Template " );
		}

	}

	public void approveCarrireInvoiceTemplate() throws Exception
	{
		NavigationHelper.navigateToScreen( "Carrier Invoice Template" );
		for ( paramVal = 0; paramVal < colSize; paramVal++ )
		{
			citempMap = excelHolderObj.dataMap( paramVal );
			templateName = ExcelHolder.getKey( citempMap, "TemplateName" );
			ButtonHelper.click( "ClearButton" );
			GenericHelper.waitForLoadmask( searchScreenWaitSec );
			boolean isCarrierInvoiceTempPresent = genericHelperObj.isGridTextValuePresent( "PS_CITemplate_Name_txtID", templateName, "Name" );

			if ( isCarrierInvoiceTempPresent )
			{
				GridHelper.clickRow( "SearchGrid", templateName, "Name" );
				GenericHelper.waitForLoadmask( searchScreenWaitSec );
				String actualVal =  GridHelper.getCellValue( "SearchGrid", 1, "Status" );
				GenericHelper.waitForLoadmask( searchScreenWaitSec );
				if(actualVal.contains( "Draft" ))
				{
				ciActionObj.navigateToApproveAction();
				ButtonHelper.click( "ClearButton" );
				assertTrue( genericHelperObj.isGridTextValuePresent( "PS_CITemplate_Name_txtID", templateName, "Name" ) );
				GenericHelper.waitForLoadmask( searchScreenWaitSec );
				assertEquals( GridHelper.getCellValue( "SearchGrid", 1, "Status" ), "Approved" );
				Log4jHelper.logInfo( "Carrier Invoice Template status is changed to approved" );
				}
				else
					assertEquals( GridHelper.getCellValue( "SearchGrid", 1, "Status" ), "Approved" );
			}
		}

	}

	public void discontinueCarrireInvoiceTemplate() throws Exception
	{
		NavigationHelper.navigateToScreen( "Carrier Invoice Template" );
		for ( paramVal = 0; paramVal < colSize; paramVal++ )
		{
			citempMap = excelHolderObj.dataMap( paramVal );
			templateName = ExcelHolder.getKey( citempMap, "TemplateName" );
			ButtonHelper.click( "ClearButton" );
			GenericHelper.waitForLoadmask( searchScreenWaitSec );
			boolean isCarrierInvoiceTempPresent = genericHelperObj.isGridTextValuePresent( "PS_CITemplate_Name_txtID", templateName, "Name" );

			if ( isCarrierInvoiceTempPresent )
			{
				GridHelper.clickRow( "SearchGrid", templateName, "Name" );
				assertEquals( GridHelper.getCellValue( "SearchGrid", 1, "Status" ), "Approved" );
				ciActionObj.navigateToDiscontinueAction();
				assertTrue( genericHelperObj.isGridTextValuePresent( "PS_CITemplate_Name_txtID", templateName, "Name" ) );
				assertEquals( GridHelper.getCellValue( "SearchGrid", 1, "Status" ), "Discontinued" );
				Log4jHelper.logInfo( "Carrier Invoice Template status is changed to discontnued" );
			}
		}

	}

	public void carrireInvoiceTemplateViewOutputTables() throws Exception
	{
		NavigationHelper.navigateToScreen( "Carrier Invoice Template" );
		for ( paramVal = 0; paramVal < colSize; paramVal++ )
		{
			citempMap = excelHolderObj.dataMap( paramVal );
			templateName = ExcelHolder.getKey( citempMap, "TemplateName" );
			String tableInstances = ExcelHolder.getKey( citempMap, "TableInstances" );
			ButtonHelper.click( "ClearButton" );
			GenericHelper.waitForLoadmask( searchScreenWaitSec );
			boolean isCarrierInvoiceTempPresent = genericHelperObj.isGridTextValuePresent( "PS_CITemplate_Name_txtID", templateName, "Name" );

			if ( isCarrierInvoiceTempPresent )
			{
				GridHelper.clickRow( "SearchGrid", templateName, "Name" );
				ciActionObj.navigateToViewOutputTablesAction();
				assertEquals( GridHelper.getCellValue( "tablesGrid", 1, "Table Instances" ), tableInstances );
				ButtonHelper.click( "outputTablesDetail.cancel" );
				GenericHelper.waitForLoadmask( searchScreenWaitSec );
				Log4jHelper.logInfo( "Carrier Invoice Template status is changed to discontnued" );
			}
		}

	}

	public void carrireInvoiceTemplateMoveToDraft() throws Exception
	{
		NavigationHelper.navigateToScreen( "Carrier Invoice Template" );
		for ( paramVal = 0; paramVal < colSize; paramVal++ )
		{
			citempMap = excelHolderObj.dataMap( paramVal );
			templateName = ExcelHolder.getKey( citempMap, "TemplateName" );
			ButtonHelper.click( "ClearButton" );
			GenericHelper.waitForLoadmask( searchScreenWaitSec );
			boolean isCarrierInvoiceTempPresent = genericHelperObj.isGridTextValuePresent( "PS_CITemplate_Name_txtID", templateName, "Name" );

			if ( isCarrierInvoiceTempPresent )
			{
				GridHelper.clickRow( "SearchGrid", templateName, "Name" );
				assertEquals( GridHelper.getCellValue( "SearchGrid", 1, "Status" ), "Approved" );
				ciActionObj.navigateToMoveToDraftAction();
				assertTrue( genericHelperObj.isGridTextValuePresent( "PS_CITemplate_Name_txtID", templateName, "Name" ) );
				assertEquals( GridHelper.getCellValue( "SearchGrid", 1, "Status" ), "Draft" );
				Log4jHelper.logInfo( "Carrier Invoice Template status is Moved back to Draft" );
			}
		}

	}

	/*
	 * This method is for search screen column validation
	 */
	public void searchScreenColumnsValidation() throws Exception
	{
		NavigationHelper.navigateToScreen( "Carrier Invoice Template" );
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

	public void jumpToCarrierInvoice() throws Exception
	{
		NavigationHelper.navigateToScreen( "Carrier Invoice Template" );
		for ( paramVal = 0; paramVal < colSize; paramVal++ )
		{
			citempMap = excelHolderObj.dataMap( paramVal );
			templateName = ExcelHolder.getKey( citempMap, "TemplateName" );
			ButtonHelper.click( "ClearButton" );
			GenericHelper.waitForLoadmask( searchScreenWaitSec );
			boolean isCarrierInvoiceTempPresent = genericHelperObj.isGridTextValuePresent( "PS_CITemplate_Name_txtID", templateName, "Name" );

			if ( isCarrierInvoiceTempPresent )
			{
				GridHelper.clickRow( "SearchGrid", templateName, "Name" );
				assertEquals( GridHelper.getCellValue( "SearchGrid", 1, "Status" ), "Approved" );
				NavigationHelper.navigateToAction( "Jump to", "Carrier Invoice" );
				GenericHelper.waitForLoadmask( searchScreenWaitSec );
				assertEquals( NavigationHelper.getScreenTitle(), "Carrier Invoice Search" );
				Log4jHelper.logInfo( "Carrier Invoice Template status is Moved back to Draft" );
			}
		}

	}

	public void jumpToCarrierInvoiceImport() throws Exception
	{
		NavigationHelper.navigateToScreen( "Carrier Invoice Template" );
		for ( paramVal = 0; paramVal < colSize; paramVal++ )
		{
			citempMap = excelHolderObj.dataMap( paramVal );
			templateName = ExcelHolder.getKey( citempMap, "TemplateName" );
			ButtonHelper.click( "ClearButton" );
			GenericHelper.waitForLoadmask( searchScreenWaitSec );
			boolean isCarrierInvoiceTempPresent = genericHelperObj.isGridTextValuePresent( "PS_CITemplate_Name_txtID", templateName, "Name" );

			if ( isCarrierInvoiceTempPresent )
			{
				GridHelper.clickRow( "SearchGrid", templateName, "Name" );
				assertEquals( GridHelper.getCellValue( "SearchGrid", 1, "Status" ), "Approved" );
				NavigationHelper.navigateToAction( "Jump to", "Carrier Invoice Import" );
				GenericHelper.waitForLoadmask( searchScreenWaitSec );
				assertEquals( NavigationHelper.getScreenTitle(), "Carrier Invoice Import" );
				Log4jHelper.logInfo( "Carrier Invoice Template status is Moved back to Draft" );
			}
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
			boolean isCarrierInvoiceTempPresent = genericHelperObj.isGridTextValuePresent( "PS_CITemplate_Name_txtID", templateName, "Name" );

			if ( isCarrierInvoiceTempPresent )
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

			boolean isCarrierInvoiceTempPresent = genericHelperObj.isGridTextValuePresent( "PS_CITemplate_Name_txtID", templateName, "Name" );

			if ( isCarrierInvoiceTempPresent )
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
		nonUsageTestCase = map.get( "NonUsageTabTestCase" );
		creditNoteTestCase = map.get( "CreditNoteTabTestCase" );
		templateName = map.get( "TemplateName" );
		usageTestCase = map.get( "UsageTabTestCase" );
		colHeaders = map.get( "ColHeaders" );
		results = map.get( "Results" );
	}

}
