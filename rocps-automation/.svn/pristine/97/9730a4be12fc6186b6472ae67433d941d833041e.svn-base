package com.subex.rocps.automation.helpers.application.carrierinvoice;

import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Map;

import com.subex.rocps.automation.helpers.application.carrierinvoice.invoicereconciliationrequest.InvoiceReconciliationRequestDetailImpl;
import com.subex.rocps.automation.helpers.application.carrierinvoice.invoicereconciliationrequest.InvoiceReconciliationRequestSearchImpl;
import com.subex.rocps.automation.helpers.application.carrierinvoice.invoicereconciliationrequest.InvoiceReconciliationReuestActionImpl;
import com.subex.rocps.automation.helpers.application.filters.GridFilterSearchHelper;
import com.subex.rocps.automation.helpers.application.genericHelpers.DataVerificationHelper;
import com.subex.rocps.automation.helpers.application.genericHelpers.PSGenericHelper;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;
import com.subex.rocps.automation.utils.ExcelHolder;
import com.subex.rocps.automation.utils.PSStringUtils;

import com.subex.automation.helpers.application.NavigationHelper;
import com.subex.automation.helpers.component.ButtonHelper;
import com.subex.automation.helpers.component.CalendarHelper;
import com.subex.automation.helpers.component.CheckBoxHelper;
import com.subex.automation.helpers.component.ComboBoxHelper;
import com.subex.automation.helpers.component.EntityComboHelper;
import com.subex.automation.helpers.component.GenericHelper;
import com.subex.automation.helpers.component.GridHelper;
import com.subex.automation.helpers.component.LabelHelper;
import com.subex.automation.helpers.component.PopupHelper;
import com.subex.automation.helpers.component.SearchGridHelper;
import com.subex.automation.helpers.component.TextBoxHelper;
import com.subex.automation.helpers.componentHelpers.SearchHelper;
import com.subex.automation.helpers.data.ValidationHelper;
import com.subex.automation.helpers.file.ExcelReader;
import com.subex.automation.helpers.report.Log4jHelper;
import com.subex.automation.helpers.util.FailureHelper;

public class InvoiceReconciliationRequest extends PSAcceptanceTest
{

	protected ExcelReader excelData = null;
	protected Map<String, ArrayList<String>> ciReconExcel = null;
	protected Map<String, String> ciReconMap = null;
	protected ExcelHolder excelHolderObj = null;
	protected String path;
	protected String workBookName;
	protected String sheetName;
	protected String testCaseName;
	protected int paramVal;
	protected int colSize;
	protected String partition;

	protected String invoiceReconConfig;
	protected String billProfile;
	protected String carrierInvoiceTemplate;
	protected String colHeader;
	protected String results;
	protected String overBilled;
	protected String underBilled;
	protected String equallyBilled;
	protected String showSystemFields;
	protected String invoiceReconStep;
	protected Map<String, ArrayList<String>> excelReaderMapObj = null;
	protected Map<String, String> mapObj = null;
	String regex = new PSStringUtils().regexFirstLevelDelimeter();
	InvoiceReconciliationReuestActionImpl actionObj = new InvoiceReconciliationReuestActionImpl();
	protected PSGenericHelper genericHelperObj = new PSGenericHelper();
	DataVerificationHelper dataVerifyObj = new DataVerificationHelper();

	/*
	 * Constructor for initializing excel Identifying the column size from the
	 * map passed
	 */
	@Test
	public InvoiceReconciliationRequest( String path, String workBookName, String sheetName, String testCaseName ) throws Exception
	{
		this.path = path;
		this.workBookName = workBookName;
		this.sheetName = sheetName;
		this.testCaseName = testCaseName;
		excelData = new ExcelReader();
		ciReconExcel = excelData.readDataByColumn( this.path, this.workBookName, this.sheetName, this.testCaseName );
		excelHolderObj = new ExcelHolder( ciReconExcel );
		colSize = excelHolderObj.totalColumns();
	}

	/*
	 * Overloaded constructor for reading data from excel if test case name
	 * appears more than once
	 * 
	 * @Param occurance : Test case instance in excel sheet Constructor for
	 * initializing excel Identifying the column size from the map
	 */
	public InvoiceReconciliationRequest( String path, String workBookName, String sheetName, String testCaseName, int occurance ) throws Exception
	{
		this.path = path;
		this.workBookName = workBookName;
		this.sheetName = sheetName;
		this.testCaseName = testCaseName;
		excelData = new ExcelReader();
		ciReconExcel = excelData.readDataByColumn( this.path, this.workBookName, this.sheetName, this.testCaseName, occurance );
		excelHolderObj = new ExcelHolder( ciReconExcel );
		colSize = excelHolderObj.totalColumns();
	}

	/*
	 * Configuring the InvoiceReconciliationRequest
	 * 
	 */
	public void invoiceReconciliationRequestCreation() throws Exception
	{
		try
		{
			NavigationHelper.navigateToScreen( "Invoice Reconciliation Request" );
			for ( paramVal = 0; paramVal < colSize; paramVal++ )
			{
				ciReconMap = excelHolderObj.dataMap( paramVal );
				initialiseVariables( ciReconMap );
				//if ( !invoiceReconFilter() )
					newReconReqConfig();
				//else
					//Log4jHelper.logInfo( "Carrier Invoice and Invoice Recon Config already exits" );
			}
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

	public void newReconReqConfig() throws Exception
	{
		InvoiceReconciliationRequestDetailImpl detailObj = new InvoiceReconciliationRequestDetailImpl( ciReconMap );		
		actionObj.clickNewAction( partition );
		detailObj.reconRequestDetailConfig();
		detailObj.reconSteps();
		detailObj.saveInvoiceReconRequest();
		assertTrue( invoiceReconFilter(), "Invoice Recon Request is not created" );
		dataVerifyObj.validateData( "grid_column_header_searchGrid_", colHeader, "SearchGrid", results );
		Log4jHelper.logInfo( "Invoice Recon Request is created" );
	}

	public boolean invoiceReconFilter() throws Exception
	{
		ButtonHelper.click( "ClearButton" );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		SearchGridHelper.gridFilterAdvancedSearch( "invoiceReconciliation", invoiceReconConfig, "Invoice Reconciliation" );
		GridFilterSearchHelper gridHelperObj = new GridFilterSearchHelper();
		gridHelperObj.billProfileAdvanceFilter( "SearchGrid", "Bill Profile", billProfile );
		//	SearchGridHelper.gridFilterAdvancedSearch( "carrierInvoice", carrierInvoice, "Carrier Invoice" );
		calender( "grid_column_header_filtersearchGrid_pirrCreateDttm" );
		return GridHelper.isValuePresent( "SearchGrid", invoiceReconConfig, "Invoice Reconciliation" );
	}

	public void calender( String filterIconID ) throws Exception
	{
		SearchHelper searchHelper = new SearchHelper();
		searchHelper.clickFilterIcon( filterIconID );
		CalendarHelper.setToday( "pirrCreateDttm" );
		ButtonHelper.click( "SearchButton" );
		GridHelper.sortGrid( "searchGrid", "Created Date" );
		GridHelper.sortGrid( "searchGrid", "Created Date" );
	}

	/* This method is for Invoice Recon Request Schedule Now
	 */
	public void invoiceReconConfigScheduleNow() throws Exception
	{
		NavigationHelper.navigateToScreen( "Invoice Reconciliation Request" );
		for ( paramVal = 0; paramVal < colSize; paramVal++ )
		{
			ciReconMap = excelHolderObj.dataMap( paramVal );
			carrierInvoiceTemplate = ExcelHolder.getKey( ciReconMap, "CITemplate" );
			invoiceReconConfig = ExcelHolder.getKey( ciReconMap, "InvoiceReconConfig" );
			billProfile = ExcelHolder.getKey( ciReconMap, "BillProfile" );
			String msg = "Reconciliation task triggered successfully with Task id";
			String msgRecon = "Carrier Invoice/Declaration with a higher version exists for one or more of the selected Reconciliation Requests, do you wish to continue?";
			if ( invoiceReconFilter() )
			{
				GridHelper.clickRow( "SearchGrid", invoiceReconConfig, "Invoice Reconciliation" );
				//assertEquals( GridHelper.getCellValue( "SearchGrid", 1, 5), "Unscheduled" );
				assertTrue( GridHelper.isValuePresent( "SearchGrid", "Unscheduled", "Status" ));
				actionObj.clickScheduleNowAction();
				if ( PopupHelper.isPresent( "window-scroll-panel" ) )
				{
					String actualVal = LabelHelper.getText( "window-scroll-panel" );
					if(actualVal.contains( msgRecon ))
						ButtonHelper.click( "YesButton" );
					String actualTaskVal = LabelHelper.getText( "window-scroll-panel" );
					if ( actualTaskVal.contains( msg ) )
						ButtonHelper.click( "OKButton" );
					
					GenericHelper.waitForLoadmask( searchScreenWaitSec );
				}
				Log4jHelper.logInfo( "Invoice Recon request has been scheduled for :" + invoiceReconConfig );
			}
			else
				Log4jHelper.logInfo( "Invoice Recon Config is not available with :" + invoiceReconConfig );
		}
	}

	/* This method is for Invoice Recon Request Schedule Now
	 */
	public void invoiceReconConfigReschedule() throws Exception
	{

		NavigationHelper.navigateToScreen( "Invoice Reconciliation Request" );
		for ( paramVal = 0; paramVal < colSize; paramVal++ )
		{
			ciReconMap = excelHolderObj.dataMap( paramVal );

			carrierInvoiceTemplate = ExcelHolder.getKey( ciReconMap, "CITemplate" );
			invoiceReconConfig = ExcelHolder.getKey( ciReconMap, "InvoiceReconConfig" );
			billProfile = ExcelHolder.getKey( ciReconMap, "BillProfile" );
			String msg = "Reconciliation task triggered successfully with Task id";
			if ( invoiceReconFilter() )
			{
				GridHelper.clickRow( "SearchGrid", invoiceReconConfig, "Invoice Reconciliation" );
				assertEquals( GridHelper.getCellValue( "SearchGrid", 1, "Recon Status" ), "Reconciled" );
				actionObj.clickRescheduleAction();
				if ( PopupHelper.isPresent( "window-scroll-panel" ) )				
				{
					String actualVal = LabelHelper.getText( "window-scroll-panel" );
					if ( actualVal.contains( msg ) )
						ButtonHelper.click( "OKButton" );
					GenericHelper.waitForLoadmask( searchScreenWaitSec );
					}
				Log4jHelper.logInfo( "Invoice Recon Request has been rescheduled :" + invoiceReconConfig );
			}
			else
				Log4jHelper.logInfo( "Invoice Recon Request is not available with :" + invoiceReconConfig );
		}
	}
	
	/* This method is for Invoice Recon Request - Baseline -Action
	 */
	public void invoiceReconConfigBaseLine() throws Exception
	{
		NavigationHelper.navigateToScreen( "Invoice Reconciliation Request" );
		for ( paramVal = 0; paramVal < colSize; paramVal++ )
		{
			ciReconMap = excelHolderObj.dataMap( paramVal );

			carrierInvoiceTemplate = ExcelHolder.getKey( ciReconMap, "CITemplate" );
			invoiceReconConfig = ExcelHolder.getKey( ciReconMap, "InvoiceReconConfig" );
			billProfile = ExcelHolder.getKey( ciReconMap, "BillProfile" );
			
			if ( invoiceReconFilter() )
			{
				GridHelper.clickRow( "SearchGrid", invoiceReconConfig, "Invoice Reconciliation" );
				assertEquals( GridHelper.getCellValue( "SearchGrid", 1, "Recon Status" ), "Reconciled" );
				actionObj.clickBaselineAction();
				ButtonHelper.click( "ClearButton" );
				assertTrue( invoiceReconFilter() );
				assertEquals( GridHelper.getCellValue( "SearchGrid", 1, "Recon Status" ), "Baselined" );
				Log4jHelper.logInfo( "Invoice Recon Request has been Baselined for:" + invoiceReconConfig );
			}
			else
				Log4jHelper.logInfo( "Invoice Recon Request is not available with :" + invoiceReconConfig );
		}
	}
	/*
	 * This method is for recon Validation
	 */

	public void reconValidation() throws Exception
	{
		NavigationHelper.navigateToScreen( "Invoice Reconciliation Request" );
		for ( paramVal = 0; paramVal < colSize; paramVal++ )
		{
			ciReconMap = excelHolderObj.dataMap( paramVal );

			initialiseVariables( ciReconMap );

			if ( invoiceReconFilter() )
			{
				InvoiceReconciliationRequestSearchImpl searchObj = new InvoiceReconciliationRequestSearchImpl();
				searchObj.checkTaskStatus();
				Log4jHelper.logInfo( "Invoice Recon Request is completed " );
			}
		}
	}

	/* This method is for Invoice Recon Request -view Reconciliation Action
	 */
	public void invoiceReconConfigViewRecon() throws Exception
	{

		NavigationHelper.navigateToScreen( "Invoice Reconciliation Request" );
		for ( paramVal = 0; paramVal < colSize; paramVal++ )
		{
			ciReconMap = excelHolderObj.dataMap( paramVal );

			carrierInvoiceTemplate = ExcelHolder.getKey( ciReconMap, "CITemplate" );
			invoiceReconConfig = ExcelHolder.getKey( ciReconMap, "InvoiceReconConfig" );
			billProfile = ExcelHolder.getKey( ciReconMap, "BillProfile" );
			if ( invoiceReconFilter() )
			{
				GridHelper.clickRow( "SearchGrid", invoiceReconConfig, "Invoice Reconciliation" );
				actionObj.viewReconResults();
				assertEquals( TextBoxHelper.getValue( "pireName" ), invoiceReconConfig );
				assertEquals( EntityComboHelper.getValue( "invoiceTemplate" ), carrierInvoiceTemplate );
				ButtonHelper.click( "invoiceReconciliationDetail.cancel" );
				GenericHelper.waitForLoadmask( searchScreenWaitSec );
			}
			else
				Log4jHelper.logInfo( "Invoice Recon Config is not available with :" + invoiceReconConfig );
		}
	}

	/* This method is for Invoice Recon Request -view Results
	 */
	public void invoiceReconConfigViewResults() throws Exception
	{
		NavigationHelper.navigateToScreen( "Invoice Reconciliation Request" );
		for ( paramVal = 0; paramVal < colSize; paramVal++ )
		{
			ciReconMap = excelHolderObj.dataMap( paramVal );		
			viewResultVariables( ciReconMap );
			if ( invoiceReconFilter() )
			{
				GridHelper.clickRow( "SearchGrid", invoiceReconConfig, "Invoice Reconciliation" );
				actionObj.viewResult();
				assertEquals( ComboBoxHelper.getValue( "invoiceReconciliation_gwt_uid_" ), invoiceReconConfig );
				ComboBoxHelper.select( "invoiceReconStep_gwt_uid_", invoiceReconStep );	 
				if ( ValidationHelper.isFalse( overBilled ) )
					CheckBoxHelper.uncheck( "Over Billed_InputElement" );
				if ( ValidationHelper.isFalse( underBilled ) )
					CheckBoxHelper.uncheck( "Under Billed_InputElement" );

				if ( ValidationHelper.isTrue( equallyBilled ) )
					CheckBoxHelper.check( "Equally Billed_InputElement" );
				if ( ValidationHelper.isTrue( showSystemFields ) )
					CheckBoxHelper.check( "Show System Fields_InputElement" );

				ButtonHelper.click( "SearchButton" );
				GenericHelper.waitForLoadmask( searchScreenWaitSec );
				//dataVerifyObj.validateData( "grid_column_header_searchGrid_", ciReconMap, "SearchGrid", colHeader, results , false);	
				dataVerifyObj.validateDataInResultScreen( "grid_column_header_searchGrid_", ciReconMap, colHeader, results , true );
				Log4jHelper.logInfo( "Invoice Recon Results has been validated successfully" );
			}
			else
				Log4jHelper.logInfo( "Invoice Recon Config is not available with :" + invoiceReconConfig );
		}
	}

	/*
	 * This method is for search screen column validation
	 */
	public void searchScreenColumnsValidation() throws Exception
	{
		NavigationHelper.navigateToScreen( "Invoice Reconciliation Request" );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		for ( paramVal = 0; paramVal < colSize; paramVal++ )
		{
			ciReconMap = excelHolderObj.dataMap( paramVal );
			String searchScreenColumns = ExcelHolder.getKey( ciReconMap, "SearchScreenColumns" );
			ArrayList<String> excelColumnNames = new ArrayList<String>();
			String[] searchGridColumnsArr = searchScreenColumns.split( regex, -1 );
			for ( int col = 0; col < searchGridColumnsArr.length; col++ )
			{
				excelColumnNames.add( searchGridColumnsArr[col] );
			}
			genericHelperObj.totalColumns( excelColumnNames );
		}

	}

	public void initialiseVariables( Map<String, String> map ) throws Exception
	{

		billProfile = ExcelHolder.getKey( map, "BillProfile" );
		invoiceReconConfig = ExcelHolder.getKey( map, "InvoiceReconConfig" );
		carrierInvoiceTemplate = ExcelHolder.getKey( map, "CITemplate" );
		colHeader = ExcelHolder.getKey( map, "ColHeader" );
		results = ExcelHolder.getKey( map, "Results" );
	}

	public void viewResultVariables( Map<String, String> map ) throws Exception
	{
		carrierInvoiceTemplate = ExcelHolder.getKey( map, "CITemplate" );
		invoiceReconConfig = ExcelHolder.getKey( map, "InvoiceReconConfig" );
		invoiceReconStep = ExcelHolder.getKey( map, "InvoiceReconStep" );
		billProfile = ExcelHolder.getKey( map, "BillProfile" );
		overBilled = ExcelHolder.getKey( map, "Over Billed" );
		underBilled = ExcelHolder.getKey( map, "Under Billed" );
		equallyBilled = ExcelHolder.getKey( map, "Equally Billed" );
		showSystemFields = ExcelHolder.getKey( map, "Show System Fields" );
		colHeader = ExcelHolder.getKey( map, "ColHeader" );
		results = ExcelHolder.getKey( map, "Results" );
	}
}
