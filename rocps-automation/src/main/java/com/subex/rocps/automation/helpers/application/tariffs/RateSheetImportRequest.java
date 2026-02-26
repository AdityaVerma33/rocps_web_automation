package com.subex.rocps.automation.helpers.application.tariffs;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import com.subex.rocps.automation.helpers.application.genericHelpers.DataVerificationHelper;
import com.subex.rocps.automation.helpers.application.genericHelpers.PSActionImpl;
import com.subex.rocps.automation.helpers.application.genericHelpers.PSGenericHelper;
import com.subex.rocps.automation.helpers.application.prepayments.prepayments.PrePaymentsDetailImpl;
import com.subex.rocps.automation.helpers.application.prepayments.prepayments.PrePaymentsSearchImpl;
import com.subex.rocps.automation.helpers.application.tariffs.ratesheetimportrequest.RateSheetImportRequestDetailImpl;
import com.subex.rocps.automation.helpers.application.tariffs.ratesheetimportrequest.RateSheetImportRequestSearchImpl;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;
import com.subex.rocps.automation.utils.ExcelHolder;
import com.subex.rocps.automation.utils.FileExport;
import com.subex.rocps.automation.utils.PSFileHelper;
import com.subex.rocps.automation.utils.PSStringUtils;

import com.subex.automation.helpers.application.NavigationHelper;
import com.subex.automation.helpers.component.ButtonHelper;
import com.subex.automation.helpers.component.FileHelper;
import com.subex.automation.helpers.component.GenericHelper;
import com.subex.automation.helpers.component.GridHelper;
import com.subex.automation.helpers.component.PopupHelper;
import com.subex.automation.helpers.component.TabHelper;
import com.subex.automation.helpers.component.TextAreaHelper;
import com.subex.automation.helpers.component.TextBoxHelper;
import com.subex.automation.helpers.data.ValidationHelper;
import com.subex.automation.helpers.file.ExcelReader;
import com.subex.automation.helpers.report.Log4jHelper;
import com.subex.automation.helpers.selenium.AcceptanceTest;
import com.subex.automation.helpers.util.FailureHelper;
import com.subex.automation.helpers.util.WindowsHelper;

public class RateSheetImportRequest extends PSAcceptanceTest
{

	protected ExcelReader excelData = null;
	protected Map<String, ArrayList<String>> ratesheetReqExcel = null;
	protected Map<String, String> ratesheetReqMap = null;
	protected ExcelHolder excelHolderObj = null;
	protected String path;
	protected String workBookName;
	protected String sheetName;
	protected String testCaseName;
	protected int paramVal;
	protected int colSize;
	protected String clientPartition;
	protected String tariff;
	protected String rateEffectiveDate;
	protected String completeExpireDate;
	protected String originRateEffectiveDate;
	protected String originCompleteExpireDate;
	protected String complete;
	protected String autoAuthorize;
	protected String locationInformation;
	protected String sheet;
	protected String columnIndex;
	protected String rowNumber;
	protected String destinationDetails;
	protected String originDetails;
	protected String colHeaders;
	protected String results;
	protected String fileName;
	String regex = new PSStringUtils().regexFirstLevelDelimeter();

	protected PSGenericHelper genericHelperObj = new PSGenericHelper();
	DataVerificationHelper dataVerifyObj = new DataVerificationHelper();
	PSActionImpl psActionImpl = new PSActionImpl();

	/*
	 * Constructor for initializing excel Identifying the column size from the
	 * map passed
	 */
	public RateSheetImportRequest( String path, String workBookName, String sheetName, String testCaseName ) throws Exception
	{
		this.path = path;
		this.workBookName = workBookName;
		this.sheetName = sheetName;
		this.testCaseName = testCaseName;
		excelData = new ExcelReader();
		ratesheetReqExcel = excelData.readDataByColumn( this.path, this.workBookName, this.sheetName, this.testCaseName );
		excelHolderObj = new ExcelHolder( ratesheetReqExcel );
		colSize = excelHolderObj.totalColumns();
	}

	/*
	 * Overloaded constructor for reading data from excel if test case name
	 * appears more than once
	 * 
	 * @Param occurance : Test case instance in excel sheet Constructor for
	 * initializing excel Identifying the column size from the map
	 */
	public RateSheetImportRequest( String path, String workBookName, String sheetName, String testCaseName, int occurance ) throws Exception
	{
		this.path = path;
		this.workBookName = workBookName;
		this.sheetName = sheetName;
		this.testCaseName = testCaseName;
		excelData = new ExcelReader();
		ratesheetReqExcel = excelData.readDataByColumn( this.path, this.workBookName, this.sheetName, this.testCaseName, occurance );
		excelHolderObj = new ExcelHolder( ratesheetReqExcel );
		colSize = excelHolderObj.totalColumns();
	}

	/*
	 * This method is for 'RateSheet Import  Request' screen common method
	 */
	private void rateSheetImportScreen() throws Exception
	{
		NavigationHelper.navigateToScreen( "Rate sheet Import Request" );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		ratesheetReqMap = excelHolderObj.dataMap( paramVal );
		ButtonHelper.click( "ClearButton" );
		genericHelperObj.waitforHeaderElement( "Tariff" );
	}

	/*
	 * Configuring the Rate sheet Import Request
	 *
	 */
	public void rateSheetImportRequest() throws Exception
	{
		try
		{
			for ( paramVal = 0; paramVal < colSize; paramVal++ )
			{
				rateSheetImportScreen();
				psActionImpl.clickOnAction( "Import", "Import Rate Sheet" );
				GenericHelper.waitForLoadmask();
				newRatesheetImportRequest();

			}
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

	public void newRatesheetImportRequest() throws Exception
	{

		RateSheetImportRequestDetailImpl ratesheetDetailObj = new RateSheetImportRequestDetailImpl( ratesheetReqMap );
		ratesheetDetailObj.newImport();
		ratesheetDetailObj.basicDetails();
		ratesheetDetailObj.destinationAndOriginDetails();
		ratesheetDetailObj.saveRateSheetImportRequest();

	}

	/*
	 * This method si for rate sheet request validation
	 */
	public void rateSheetRequestResultsValidation() throws Exception
	{
		for ( paramVal = 0; paramVal < colSize; paramVal++ )
		{
			rateSheetImportScreen();
			initalizeVariables( ratesheetReqMap );
			RateSheetImportRequestSearchImpl rateSheetSearchObj = new RateSheetImportRequestSearchImpl();
			assertTrue( rateSheetSearchObj.filterOperation( tariff, fileName ) );
			dataVerifyObj.validateData( "grid_column_header_searchGrid_", ratesheetReqMap, "SearchGrid", colHeaders, results );
			Log4jHelper.logInfo( "Rate sheet import request is validated successfully" );
		}
	}

	/*
	 * This method is for authorize import request
	 */

	public void authorizeImport() throws Exception
	{
		for ( paramVal = 0; paramVal < colSize; paramVal++ )
		{
			rateSheetImportScreen();
			initalizeVariables( ratesheetReqMap );
			RateSheetImportRequestSearchImpl rateSheetSearchObj = new RateSheetImportRequestSearchImpl();
			if ( ValidationHelper.isFalse( autoAuthorize ) )
			{
				//ButtonHelper.click( "ClearButton " );
				assertTrue( rateSheetSearchObj.filterOperation( tariff, fileName ), "The given Tariff-'" + tariff + "'  and file name-'" + fileName + "' is not found " );
				GridHelper.clickRow( "SearchGrid", tariff, "Tariff" );
				assertTrue( GridHelper.isValuePresent( "SearchGrid", "PendingAuthorization", "Authorization Status" ), " The given 'PendingAuthorization'task status is not found" );
				GenericHelper.waitForLoadmask( detailScreenWaitSec );
				psActionImpl.clickOnAction( "Other Actions", "Authorize Import" );
				GenericHelper.waitForLoadmask( detailScreenWaitSec );
				assertTrue( PopupHelper.isTextPresent( "window-scroll-panel", "Rate Sheet Authorization Task created successfully" ) );
				ButtonHelper.click( "OKButton" );
				GenericHelper.waitForLoadmask( searchScreenWaitSec );
			}
		}
	}

	/*
	 * This method is for authorize import request
	 */

	public void RejectImport() throws Exception
	{
		NavigationHelper.navigateToScreen( "Rate sheet Import Request" );
		for ( paramVal = 0; paramVal < colSize; paramVal++ )
		{
			ratesheetReqMap = excelHolderObj.dataMap( paramVal );
			tariff = ExcelHolder.getKey( ratesheetReqMap, "Tariff" );
			fileName = ExcelHolder.getKey( ratesheetReqMap, "FileName" );
			String rejectReason = ExcelHolder.getKey( ratesheetReqMap, "RejectReason" );
			//initalizeVariables( ratesheetReqMap );
			ButtonHelper.click( "ClearButton" );
			RateSheetImportRequestSearchImpl rateSheetSearchObj = new RateSheetImportRequestSearchImpl();
			assertTrue( rateSheetSearchObj.filterOperation( tariff, fileName )," Tariff is not avialable" );
			GridHelper.clickRow( "SearchGrid", tariff, "Tariff" );
			assertTrue( GridHelper.isValuePresent( "SearchGrid", "PendingAuthorization", "Authorization Status" )," 'Authorization Status' is not matched with 'Authorization Status'" );
			GenericHelper.waitForLoadmask( detailScreenWaitSec );
			NavigationHelper.navigateToAction( "Other Actions", "Reject Import" );
			GenericHelper.waitForLoadmask( detailScreenWaitSec );
			assertEquals( NavigationHelper.getScreenTitle(), "Ratesheet Rejection Description" );
			TextAreaHelper.type( "prrdReason", rejectReason );
			ButtonHelper.click( "OKButton" );
			GenericHelper.waitForLoadmask( searchScreenWaitSec );
			assertTrue( GridHelper.isValuePresent( "SearchGrid", "Rejected", "Authorization Status" )," 'Authorization Status' is not rejected'" );
			
		}
	}

	/*
	 * This method is to view template
	 */

	public void ViewTemplate() throws Exception
	{
		String title = NavigationHelper.getScreenTitle();
		//if(title.equals( "null" ) || !title.equals("Rate Sheet Import Search"))
		NavigationHelper.navigateToScreen( "Rate sheet Import Request" );
		for ( paramVal = 0; paramVal < colSize; paramVal++ )
		{
			ratesheetReqMap = excelHolderObj.dataMap( paramVal );

			tariff = ExcelHolder.getKey( ratesheetReqMap, "Tariff" );
			fileName = ExcelHolder.getKey( ratesheetReqMap, "FileName" );
			String templateName = ExcelHolder.getKey( ratesheetReqMap, "Template Name" );
			String templateCode = ExcelHolder.getKey( ratesheetReqMap, "Template Code" );
			RateSheetImportRequestSearchImpl rateSheetSearchObj = new RateSheetImportRequestSearchImpl();
			ButtonHelper.click( "ClearButton" );
			assertTrue( rateSheetSearchObj.filterOperation( tariff, fileName ) );
			GridHelper.clickRow( "SearchGrid", tariff, "Tariff" );
			GenericHelper.waitForLoadmask( detailScreenWaitSec );
			NavigationHelper.navigateToAction( "View", "View Template" );
			GenericHelper.waitForLoadmask( detailScreenWaitSec );
			assertEquals( NavigationHelper.getScreenTitle(), "View Rate Sheet Import Template" );
			String actualTemplateName = TextBoxHelper.getValue( "psitName" );
			assertEquals( actualTemplateName, templateName, "RateSheet Template Names are not matching" );
			String actualTemplateCode = TextBoxHelper.getValue( "psitCode" );
			assertEquals( actualTemplateCode, templateCode, "RateSheet Template Names are not matching" );

			TabHelper.gotoTab( "//*[text()='Destination...']" );
			GenericHelper.waitForLoadmask( searchScreenWaitSec );
			ButtonHelper.click( "CloseButton" );
			GenericHelper.waitForLoadmask( searchScreenWaitSec );
		}
	}

	/*
	 * This method is to view statistics
	 */

	public void ViewStatistics() throws Exception
	{
		NavigationHelper.navigateToScreen( "Rate sheet Import Request" );
		for ( paramVal = 0; paramVal < colSize; paramVal++ )
		{
			ratesheetReqMap = excelHolderObj.dataMap( paramVal );

			//initalizeVariables( ratesheetReqMap );
			tariff = ExcelHolder.getKey( ratesheetReqMap, "Tariff" );
			fileName = ExcelHolder.getKey( ratesheetReqMap, "FileName" );
			RateSheetImportRequestSearchImpl rateSheetSearchObj = new RateSheetImportRequestSearchImpl();
			ButtonHelper.click( "ClearButton" );
			assertTrue( rateSheetSearchObj.filterOperation( tariff, fileName ) );
			GridHelper.clickRow( "SearchGrid", tariff, "Tariff" );
			GenericHelper.waitForLoadmask( detailScreenWaitSec );
			NavigationHelper.navigateToAction( "View", "View Statistics" );
			GenericHelper.waitForLoadmask( detailScreenWaitSec );
			assertEquals( NavigationHelper.getScreenTitle(), "View Import Statistics" );
			ButtonHelper.click( "CloseButton" );
			GenericHelper.waitForLoadmask( searchScreenWaitSec );
		}
	}

	/*
	 * This method is to view statistics
	 */

	public void ViewErrors() throws Exception
	{
		NavigationHelper.navigateToScreen( "Rate sheet Import Request" );
		for ( paramVal = 0; paramVal < colSize; paramVal++ )
		{
			ratesheetReqMap = excelHolderObj.dataMap( paramVal );

			//initalizeVariables( ratesheetReqMap );
			tariff = ExcelHolder.getKey( ratesheetReqMap, "Tariff" );
			fileName = ExcelHolder.getKey( ratesheetReqMap, "FileName" );
			RateSheetImportRequestSearchImpl rateSheetSearchObj = new RateSheetImportRequestSearchImpl();
			ButtonHelper.click( "ClearButton" );
			assertTrue( rateSheetSearchObj.filterOperation( tariff, fileName ) );
			GridHelper.clickRow( "SearchGrid", tariff, "Tariff" );
			GenericHelper.waitForLoadmask( detailScreenWaitSec );
			NavigationHelper.navigateToAction( "View", "View Errors" );
			GenericHelper.waitForLoadmask( detailScreenWaitSec );
			assertEquals( NavigationHelper.getScreenTitle(), "Import Error Search" );
			ButtonHelper.click( "CancelButton" );
			GenericHelper.waitForLoadmask( searchScreenWaitSec );
		}
	}

	/*
	 * This method is for generate report
	 */

	public void generateReport() throws Exception
	{
		NavigationHelper.navigateToScreen( "Rate sheet Import Request" );
		for ( paramVal = 0; paramVal < colSize; paramVal++ )
		{
			ratesheetReqMap = excelHolderObj.dataMap( paramVal );
			//initalizeVariables( ratesheetReqMap );
			tariff = ExcelHolder.getKey( ratesheetReqMap, "Tariff" );
			fileName = ExcelHolder.getKey( ratesheetReqMap, "FileName" );
			RateSheetImportRequestSearchImpl rateSheetSearchObj = new RateSheetImportRequestSearchImpl();

			assertTrue( rateSheetSearchObj.filterOperation( tariff, fileName ) );
			GridHelper.clickRow( "SearchGrid", tariff, "Tariff" );
			assertTrue( GridHelper.isValuePresent( "SearchGrid", "PendingAuthorization", "Authorization Status" ) );
			GenericHelper.waitForLoadmask( detailScreenWaitSec );
			genericHelperObj.waitForParentActionElementTOBeclickable( "Comparison Report" );
			NavigationHelper.navigateToAction( "Comparison Report", "Generate Report" );
			GenericHelper.waitForLoadmask( detailScreenWaitSec );
			if ( PopupHelper.isPresent() )
				ButtonHelper.click( "OKButton" );
			GenericHelper.waitForLoadmask( searchScreenWaitSec );
			Log4jHelper.logInfo( "Generate Report task has been completed" );
		}
	}

	/*
	 * This method is for download report
	 */

	public void downloadReport() throws Exception
	{
		NavigationHelper.navigateToScreen( "Rate sheet Import Request" );
		for ( paramVal = 0; paramVal < colSize; paramVal++ )
		{
			ratesheetReqMap = excelHolderObj.dataMap( paramVal );
			tariff = ExcelHolder.getKey( ratesheetReqMap, "Tariff" );
			fileName = ExcelHolder.getKey( ratesheetReqMap, "FileName" );
			RateSheetImportRequestSearchImpl rateSheetSearchObj = new RateSheetImportRequestSearchImpl();

			assertTrue( rateSheetSearchObj.filterOperation( tariff, fileName ) );
			GridHelper.clickRow( "SearchGrid", tariff, "Tariff" );
			assertTrue( GridHelper.isValuePresent( "SearchGrid", "PendingAuthorization", "Authorization Status" ) );
			GenericHelper.waitForLoadmask( detailScreenWaitSec );
			PSGenericHelper.waitForParentActionElementTOBeclickable( "Comparison Report" );
			NavigationHelper.navigateToAction( "Comparison Report", "Download Report" );
			GenericHelper.waitForLoadmask( detailScreenWaitSec );
			String fileNAme = PSFileHelper.fileDownloadSikuli();

			GenericHelper.waitForLoadmask( searchScreenWaitSec );
			Log4jHelper.logInfo( "Report has been doownloaded successfully with file name-" + fileNAme );
		}
	}

	/*
	 * This method is for view errors
	 */

	public void viewErrors() throws Exception
	{
		NavigationHelper.navigateToScreen( "Rate sheet Import Request" );
		for ( paramVal = 0; paramVal < colSize; paramVal++ )
		{
			ratesheetReqMap = excelHolderObj.dataMap( paramVal );
			initalizeVariables( ratesheetReqMap );
			RateSheetImportRequestSearchImpl rateSheetSearchObj = new RateSheetImportRequestSearchImpl();

			assertTrue( rateSheetSearchObj.filterOperation( tariff, fileName ) );
			GridHelper.clickRow( "SearchGrid", tariff, "Tariff" );
			assertTrue( GridHelper.isValuePresent( "SearchGrid", "Failed", "Authorization Status" ) );
			GenericHelper.waitForLoadmask( detailScreenWaitSec );
			PSGenericHelper.waitForParentActionElementTOBeclickable( "View" );
			NavigationHelper.navigateToAction( "View", "View Errors" );
			GenericHelper.waitForLoadmask( detailScreenWaitSec );
			dataVerifyObj.validateData( "grid_column_header_searchGrid_", ratesheetReqMap, "SearchGrid", colHeaders, results );
			GenericHelper.waitForLoadmask( searchScreenWaitSec );
			ButtonHelper.click( "CancelButton" );

			GenericHelper.waitForLoadmask( searchScreenWaitSec );
			Log4jHelper.logInfo( "Report has been doownloaded successfully" );
		}
	}

	/*
	 * This method is for search screen column validation
	 */
	public void searchScreenColumnsValidation() throws Exception
	{
		NavigationHelper.navigateToScreen( "Rate sheet Import Request" );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		for ( paramVal = 0; paramVal < colSize; paramVal++ )
		{
			ratesheetReqMap = excelHolderObj.dataMap( paramVal );
			String searchScreenColumns = ExcelHolder.getKey( ratesheetReqMap, "SearchScreenColumns" );
			ArrayList<String> excelColumnNames = new ArrayList<String>();
			String[] searchGridColumnsArr = searchScreenColumns.split( regex, -1 );
			for ( int col = 0; col < searchGridColumnsArr.length; col++ )
			{
				excelColumnNames.add( searchGridColumnsArr[col] );
			}
			genericHelperObj.totalColumns( excelColumnNames );
		}

	}

	/*
	 * This method is to iniatialize variables
	 */
	public void initalizeVariables( Map<String, String> map ) throws Exception
	{
		colHeaders = ExcelHolder.getKey( map, "ColHeaders" );
		results = ExcelHolder.getKey( map, "Results" );
		tariff = ExcelHolder.getKey( map, "Tariff" );
		fileName = ExcelHolder.getKey( map, "FileName" );
		autoAuthorize = ExcelHolder.getKey( map, "AutoAuthorize" );
	}

}
