package com.subex.rocps.automation.helpers.application.carrierinvoice;

import java.util.ArrayList;
import java.util.Map;

import org.testng.annotations.Test;

import com.subex.automation.helpers.application.NavigationHelper;
import com.subex.automation.helpers.component.ButtonHelper;
import com.subex.automation.helpers.component.CalendarHelper;
import com.subex.automation.helpers.component.CheckBoxHelper;
import com.subex.automation.helpers.component.ComboBoxHelper;
import com.subex.automation.helpers.component.ElementHelper;
import com.subex.automation.helpers.component.GenericHelper;
import com.subex.automation.helpers.component.GridHelper;
import com.subex.automation.helpers.component.PopupHelper;
import com.subex.automation.helpers.component.SearchGridHelper;
import com.subex.automation.helpers.component.TextAreaHelper;
import com.subex.automation.helpers.data.ValidationHelper;
import com.subex.automation.helpers.file.ExcelReader;
import com.subex.automation.helpers.report.Log4jHelper;
import com.subex.automation.helpers.util.FailureHelper;

import com.subex.rocps.automation.helpers.application.carrierinvoice.carrierinvoice.CarrierInvoiceActionImpl;
import com.subex.rocps.automation.helpers.application.carrierinvoice.carrierinvoice.CarrierInvoiceSearchImpl;
import com.subex.rocps.automation.helpers.application.carrierinvoice.carrierinvoicetemplate.CarrierInvoiceDetailImpl;
import com.subex.rocps.automation.helpers.application.carrierinvoice.carrierinvoicetemplate.CarrierInvoiceTabDetailsImpl;
import com.subex.rocps.automation.helpers.application.carrierinvoice.carrierinvoicetemplate.CarrierInvoiceTemplateActionImpl;
import com.subex.rocps.automation.helpers.application.carrierinvoice.invoicereconciliationrequest.InvoiceReconciliationRequestSearchImpl;
import com.subex.rocps.automation.helpers.application.filters.GridFilterSearchHelper;
import com.subex.rocps.automation.helpers.application.genericHelpers.DataVerificationHelper;
import com.subex.rocps.automation.helpers.application.genericHelpers.PSGenericHelper;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;
import com.subex.rocps.automation.utils.ExcelHolder;
import com.subex.rocps.automation.utils.PSStringUtils;

public class CarrierInvoice extends PSAcceptanceTest
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
	protected String account;
	protected String billProfile;
	protected String carrierInvoicePeriod;
	protected String columnHeader;
	protected String results;
	protected String historyColHeader;
	protected String historyResults;
	protected String reconConfig;
	protected String comments;
	protected String markDispute;
	protected String disputeColHeader;
	protected String disputeResults;
	protected String priorCarrierInvoice;
	String regex = new PSStringUtils().regexFirstLevelDelimeter();
	PSStringUtils strObj = new PSStringUtils();
	CarrierInvoiceTemplateActionImpl ciActionImplObj = new CarrierInvoiceTemplateActionImpl();
	protected PSGenericHelper genericHelperObj = new PSGenericHelper();
	DataVerificationHelper dataVerifyObj = new DataVerificationHelper();
	CarrierInvoiceActionImpl actionObj = new CarrierInvoiceActionImpl();

	/*
	 * Constructor for initializing excel Identifying the column size from the map
	 * passed
	 */
	@Test
	public CarrierInvoice( String path, String workBookName, String sheetName, String testCaseName ) throws Exception
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
	 * Overloaded constructor for reading data from excel if test case name appears
	 * more than once
	 * 
	 * @Param occurance : Test case instance in excel sheet Constructor for
	 * initializing excel Identifying the column size from the map
	 */
	public CarrierInvoice( String path, String workBookName, String sheetName, String testCaseName, int occurance ) throws Exception
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
	public void carrierInvoiceSearchConfig() throws Exception
	{
		try
		{
			NavigationHelper.navigateToScreen( "Carrier Invoice" );
			for ( paramVal = 0; paramVal < colSize; paramVal++ )
			{
				citempMap = excelHolderObj.dataMap( paramVal );
				ButtonHelper.click( "ClearButton" );
				GenericHelper.waitForLoadmask( searchScreenWaitSec );
				initialiseVariables( citempMap );
				if ( !carrierInvoiceInitialFilter() )
				{
					CarrierInvoiceDetailImpl detailObj = new CarrierInvoiceDetailImpl( citempMap );
					detailObj.newCarrierInvoice();
					detailObj.carrierInvoiceTemplateSelection();
					detailObj.summaryTabConfig();
					carrierInvoiceTabsConfig( usageTestCase, "Usage" );
					carrierInvoiceTabsConfig( nonUsageTestCase, "Non-Usage" );
					carrierInvoiceTabsConfig( creditNoteTestCase, "Credit Note" );
					detailObj.saveCarrierInvoice();
					ButtonHelper.click( "ClearButton" );
					GenericHelper.waitForLoadmask( searchScreenWaitSec );
					assertTrue( carrierInvoiceFilter() );
					if ( !results.isEmpty() )
						dataVerifyObj.validateData( "grid_column_header_searchGrid_", citempMap, "SearchGrid", "SearchGrid", results );

					Log4jHelper.logInfo( "Carrier Invoice is saved successfully for : " + templateName );
				}
				else
					Log4jHelper.logInfo( "Carrier Invoice is available for template :" + templateName );

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
			ciActionImplObj.switchToTab( tabName );
			CarrierInvoiceTabDetailsImpl tabObj = new CarrierInvoiceTabDetailsImpl( path, workBookName, sheetName, testcase );
			tabObj.usagTabConfiguration();
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

	/*
	 * This method is for Carrier Invoice Validation
	 */
	public void carrierInvoiceValidation() throws Exception
	{
		NavigationHelper.navigateToScreen( "Carrier Invoice" );
		for ( paramVal = 0; paramVal < colSize; paramVal++ )
		{
			citempMap = excelHolderObj.dataMap( paramVal );
			initialiseVariables( citempMap );
			ButtonHelper.click( "ClearButton" );
			GenericHelper.waitForLoadmask( searchScreenWaitSec );
			if ( carrierInvoiceFilter() )
			{
				GridHelper.sortGrid( "SearchGrid", "Created Date Time" );
				GridHelper.sortGrid( "SearchGrid", "Created Date Time" );
				GridHelper.clickRow( "SearchGrid", 1, "Bill Profile" );
				GenericHelper.waitForLoadmask( searchScreenWaitSec );
				if ( !results.isEmpty() )
					dataVerifyObj.validateData( "grid_column_header_searchGrid_", citempMap, "SearchGrid", columnHeader, results );
				NavigationHelper.navigateToAction( "Common Tasks", "Edit" );
				GenericHelper.waitForLoadmask( searchScreenWaitSec );
				assertEquals( NavigationHelper.getScreenTitle(), "Edit Carrier Invoice" );
				CarrierInvoiceDetailImpl detailObj = new CarrierInvoiceDetailImpl( citempMap );
				detailObj.summaryValidation();
				carrierInvoiceTabsValidation( usageTestCase, "Usage" );
				carrierInvoiceTabsValidation( nonUsageTestCase, "Non-Usage" );
				carrierInvoiceTabsValidation( creditNoteTestCase, "Credit Note" );
				ButtonHelper.click( "carrierInvoiceDetail.cancel" );
				GenericHelper.waitForLoadmask( searchScreenWaitSec );
				Log4jHelper.logInfo( "Carrier Invoice is validated successfully :" + templateName );
			}
			else
				Log4jHelper.logInfo( "Carrier Invoice is not available for  :" + billProfile );
		}
	}

	public void carrierInvoiceTabsValidation( String testcase, String tabName ) throws Exception
	{
		if ( ValidationHelper.isNotEmpty( testcase ) )
		{
			ciActionImplObj.switchToTab( tabName );
			CarrierInvoiceTabDetailsImpl tabObj = new CarrierInvoiceTabDetailsImpl( path, workBookName, sheetName, testcase );
			tabObj.usageTabValidation();
		}
	}

	/*
	 * This method is for Carrier Invoice View CI History
	 */
	public void carrierInvoiceViewCIHistory() throws Exception
	{

		NavigationHelper.navigateToScreen( "Carrier Invoice" );
		for ( paramVal = 0; paramVal < colSize; paramVal++ )
		{
			citempMap = excelHolderObj.dataMap( paramVal );
			initialiseVariables( citempMap );
			historyColHeader = citempMap.get( "ColumnHeader" );
			historyResults = citempMap.get( "Results" );
			ButtonHelper.click( "ClearButton" );
			GenericHelper.waitForLoadmask( searchScreenWaitSec );
			if ( carrierInvoiceFilter() )
			{
				GridHelper.clickRow( "SearchGrid", 1, "Bill Profile" );
				GenericHelper.waitForLoadmask( searchScreenWaitSec );
				actionObj.viewCIHistoryAction();
				if ( !historyResults.isEmpty() )
					dataVerifyObj.validateData( "grid_column_header_undefined_", citempMap, "historyGrid", historyColHeader, historyResults );
				GenericHelper.waitForLoadmask( searchScreenWaitSec );
				ButtonHelper.click( "carrierInvoiceHistoryInfoDetail.close" );
				GenericHelper.waitForLoadmask( searchScreenWaitSec );
				Log4jHelper.logInfo( "Carrier Invoice History is validated successfully :" + templateName );
			}
			else
				Log4jHelper.logInfo( "Carrier Invoice is not available for  :" + billProfile );
		}
	}

	public boolean carrierInvoiceInitialFilter() throws Exception
	{
		boolean flag = false;
		priorCarrierInvoice = citempMap.get( "Prior Carrier Invoice" );
		if ( !account.isEmpty() )
			SearchGridHelper.gridFilterAdvancedSearch( "billProfile$account", account, "Account Name" );
		GridFilterSearchHelper gridHelperObj = new GridFilterSearchHelper();
		gridHelperObj.billProfileAdvanceFilter( "SearchGrid", "Bill Profile", billProfile );
		if ( !carrierInvoicePeriod.isEmpty() )
			CalendarHelper.setOnDate( "PS_SearchFilter_BillAction_date_calenderID", carrierInvoicePeriod );
		// SearchGridHelper.searchWithComboBox( "statusCode_gwt_uid_", "Draft", "Status"
		// );
		ButtonHelper.click( "SearchButton" );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		int row = GridHelper.getRowCount( "SearchGrid" );
		if ( row != 0 && ValidationHelper.isNotEmpty( priorCarrierInvoice ) )
			return flag;
		else
		{
			flag = GridHelper.isValuePresent( "SearchGrid", account, "Account Name" );
			return flag;
		}

	}

	public boolean carrierInvoiceFilter() throws Exception
	{
		String headerElement = "//*[@id='searchGrid']//th//div[contains(text(),'Template')]";
		if ( !ElementHelper.isElementPresent( headerElement ) )
			ElementHelper.waitForElement( headerElement, searchScreenWaitSec );
		GridFilterSearchHelper.gridFilterAdvancedSearch( "invoiceTemplate", templateName, "Template" );
		if ( !account.isEmpty() )
			GridFilterSearchHelper.gridFilterAdvancedSearch( "billProfile$account", account, "Account Name" );
		GridFilterSearchHelper gridHelperObj = new GridFilterSearchHelper();
		gridHelperObj.billProfileAdvanceFilter( "SearchGrid", "Bill Profile", billProfile );
		if ( !carrierInvoicePeriod.isEmpty() )
			CalendarHelper.setOnDate( "PS_SearchFilter_BillAction_date_calenderID", carrierInvoicePeriod );
		ButtonHelper.click( "SearchButton" );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );

		boolean flag = GridHelper.isValuePresent( "SearchGrid", account, "Account Name" );

		return flag;

	}

	/*
	 * This method is for Carrier Invoice change status
	 */
	public void carrierInvoiceAuthorize() throws Exception
	{

		NavigationHelper.navigateToScreen( "Carrier Invoice" );
		for ( paramVal = 0; paramVal < colSize; paramVal++ )
		{
			citempMap = excelHolderObj.dataMap( paramVal );
			authorizeVariables( citempMap );
			String msg = "Authorise Carrier Invoice Task is triggered, will Baseline the Reconciliation Request and shall create disputes based on the discrepancies selected.";
			String msg1 = "Authorise Carrier Invoice Task is triggered and shall create disputes based on the discrepancies selected.";
			if ( carrierInvoiceFilter() )
			{
				GridHelper.clickRow( "SearchGrid", 1, "Bill Profile" );
				GenericHelper.waitForLoadmask( searchScreenWaitSec );
				String actualVal = GridHelper.getCellValue( "SearchGrid", 1, "Status" );
				if ( actualVal.contains( "Authorized" ) )
				{
					Log4jHelper.logInfo( "Carrier Invoice status is already Authorized for :" + billProfile );
				}
				else if ( !actualVal.contains( "Reconciled" ) )
				{
					assertTrue( GridHelper.isValuePresent( "SearchGrid", "Draft", "Status" ), "Draft" );
					actionObj.clickAuthorizeAction();
					assertTrue( GridHelper.isValuePresent( "SearchGrid", "Authorized", "Status" ), "Authorized" );
					Log4jHelper.logInfo( "Carrier Invoice status is changed successfully for :" + billProfile );
				}
				else
				{
					assertTrue( GridHelper.isValuePresent( "SearchGrid", "Reconciled", "Status" ), "Reconciled" );
					actionObj.clickAuthorizeAction();
					assertEquals( NavigationHelper.getScreenTitle(), "Authorize" );
					String[] markDisputeArr = strObj.stringSplitFirstLevel( markDispute );
					assertEquals( ComboBoxHelper.getValue( "reconConfiguration_gwt_uid_" ), reconConfig );
					for ( int i = 0; i < markDisputeArr.length; i++ )
					{
						if ( ValidationHelper.isFalse( markDisputeArr[i] ) )
							CheckBoxHelper.uncheck( "grid_check_editor" );
					}
					TextAreaHelper.type( "comment", comments );
					ButtonHelper.click( "authorizeDetail.ok" );
					GenericHelper.waitForLoadmask( searchScreenWaitSec );
					if ( PopupHelper.isTextPresent( "window-scroll-panel", msg ) || PopupHelper.isTextPresent( "window-scroll-panel", msg1 ) )
						ButtonHelper.click( "OKButton" );
					GenericHelper.waitForLoadmask( searchScreenWaitSec );
					ciValidation();
					if ( !results.isEmpty() )
						dataVerifyObj.validateData( "grid_column_header_searchGrid_", citempMap, "SearchGrid", columnHeader, results );
					Log4jHelper.logInfo( "CArrier Invoice has been authorized" );
				}
			}
			else
				Log4jHelper.logInfo( "Carrier Invoice Template is not available with :" + billProfile );
		}

	}

	public void ciValidation() throws Exception
	{
		NavigationHelper.navigateToScreen( "Carrier Invoice" );
		for ( paramVal = 0; paramVal < colSize; paramVal++ )
		{
			citempMap = excelHolderObj.dataMap( paramVal );

			initialiseVariables( citempMap );

			if ( carrierInvoiceFilter() )
			{
				CarrierInvoiceSearchImpl obj = new CarrierInvoiceSearchImpl();
				obj.checkTaskStatus();
				Log4jHelper.logInfo( "carrier Invoice Authorize is completed " );
			}
		}
	}

	/*
	 * This method is for Carrier Invoice change status
	 */
	public void carrierInvoiceReject() throws Exception
	{

		NavigationHelper.navigateToScreen( "Carrier Invoice" );
		for ( paramVal = 0; paramVal < colSize; paramVal++ )
		{
			citempMap = excelHolderObj.dataMap( paramVal );
			templateName = ExcelHolder.getKey( citempMap, "TemplateName" );
			ButtonHelper.click( "ClearButton" );
			GenericHelper.waitForLoadmask( searchScreenWaitSec );
			initialiseVariables( citempMap );
			if ( carrierInvoiceFilter() )
			{
				GridHelper.clickRow( "SearchGrid", 1, "Bill Profile" );
				GenericHelper.waitForLoadmask( searchScreenWaitSec );
				assertTrue( GridHelper.isValuePresent( "SearchGrid", "Draft", "Status" ), "Draft" );
				actionObj.clickRejectAction();
				if ( PopupHelper.isTextPresent( "window-scroll-panel", "Are you sure you want to reject the selected Carrier Invoice/Declaration ?" ) )
				{
					ButtonHelper.click( "YesButton" );
					GenericHelper.waitForLoadmask( searchScreenWaitSec );
				}
				ButtonHelper.click( "SearchButton" );
				GenericHelper.waitForLoadmask( searchScreenWaitSec );
				assertTrue( GridHelper.isValuePresent( "SearchGrid", "Rejected", "Status" ), "Rejected" );
				Log4jHelper.logInfo( "Carrier Invoice status is changed successfully for :" + billProfile );
			}
			else
				Log4jHelper.logInfo( "Carrier Invoice Template is not available with :" + billProfile );
		}
	}

	/*
	 * This method is for Carrier Invoice view comments
	 */
	public void carrierInvoiceViewComment() throws Exception
	{

		NavigationHelper.navigateToScreen( "Carrier Invoice" );
		for ( paramVal = 0; paramVal < colSize; paramVal++ )
		{
			citempMap = excelHolderObj.dataMap( paramVal );
			partition = ExcelHolder.getKey( citempMap, "Partition" );
			templateName = ExcelHolder.getKey( citempMap, "TemplateName" );
			ButtonHelper.click( "ClearButton" );
			GenericHelper.waitForLoadmask( searchScreenWaitSec );
			if ( carrierInvoiceFilter() )
			{
				GridHelper.clickRow( "SearchGrid", 1, "Bill Profile" );
				GenericHelper.waitForLoadmask( searchScreenWaitSec );
				assertTrue( GridHelper.isValuePresent( "SearchGrid", "Draft", "Status" ), "Draft" );
				actionObj.clickRejectAction();

				assertTrue( GridHelper.isValuePresent( "SearchGrid", "Rejected", "Name" ), "Rejected" );
				Log4jHelper.logInfo( "Carrier Invoice status is changed successfully for :" + billProfile );
			}
			else
				Log4jHelper.logInfo( "Carrier Invoice Template is not available with :" + billProfile );
		}

	}

	/*
	 * This method is for Carrier Invoice jump to dispute Action
	 */
	public void carrierInvoiceJumpTo() throws Exception
	{

		NavigationHelper.navigateToScreen( "Carrier Invoice" );
		for ( paramVal = 0; paramVal < colSize; paramVal++ )
		{
			citempMap = excelHolderObj.dataMap( paramVal );
			authorizeVariables( citempMap );
			ButtonHelper.click( "ClearButton" );
			GenericHelper.waitForLoadmask( searchScreenWaitSec );
			if ( carrierInvoiceFilter() )
			{
				GridHelper.clickRow( "SearchGrid", 1, "Bill Profile" );
				GenericHelper.waitForLoadmask( searchScreenWaitSec );
				assertTrue( GridHelper.isValuePresent( "SearchGrid", "UnAuthorized", "Status" ) );
				actionObj.clickJumpToAction();
				if ( !disputeResults.isEmpty() )
					dataVerifyObj.validateData( "grid_column_header_searchGrid_", citempMap, "SearchGrid", disputeColHeader, disputeResults );
				Log4jHelper.logInfo( "Carrier Invoice status is changed successfully for :" + billProfile );
			}
			else
				Log4jHelper.logInfo( "Carrier Invoice Template is not available with :" + billProfile );
		}

	}

	private void initialiseVariables( Map<String, String> map )
	{
		partition = map.get( "Partition" );
		nonUsageTestCase = map.get( "NonUsageTestCase" );
		creditNoteTestCase = map.get( "CreditNoteTestCase" );
		templateName = map.get( "TemplateName" );
		usageTestCase = map.get( "UsageTestCase" );
		// account = map.get( "UsageTestCase" );
		billProfile = map.get( "BillProfile" );
		account = map.get( "Account" );
		carrierInvoicePeriod = map.get( "CarrierInvoicePeriod" );
		columnHeader = map.get( "ColumnHeader" );
		results = map.get( "Results" );
	}

	private void authorizeVariables( Map<String, String> map ) throws Exception
	{

		templateName = ExcelHolder.getKey( map, "TemplateName" );
		billProfile = ExcelHolder.getKey( map, "BillProfile" );
		account = ExcelHolder.getKey( map, "Account" );
		carrierInvoicePeriod = ExcelHolder.getKey( map, "CarrierInvoicePeriod" );
		reconConfig = ExcelHolder.getKey( map, "Recon Config" );
		columnHeader = map.get( "ColumnHeader" );
		results = map.get( "Results" );
		comments = ExcelHolder.getKey( map, "Coments" );
		markDispute = ExcelHolder.getKey( map, "Mark Dispute" );
		disputeColHeader = ExcelHolder.getKey( citempMap, "DisputeColHeader" );
		disputeResults = ExcelHolder.getKey( citempMap, "DisputeResults" );
	}

}
