package com.subex.rocps.automation.helpers.application.carrierinvoice;

import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Map;

import com.subex.rocps.automation.helpers.application.carrierinvoice.carrierinvoice.CarrierInvoiceActionImpl;
import com.subex.rocps.automation.helpers.application.carrierinvoice.invoicereconciliationrequest.InvoiceReconciliationRequestDetailImpl;
import com.subex.rocps.automation.helpers.application.carrierinvoice.invoicereconconfig.InvoiceReconConfigActionImpl;
import com.subex.rocps.automation.helpers.application.carrierinvoice.invoicereconconfig.InvoiceReconConfigDetailImpl;
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

public class InvoiceReconConfig extends PSAcceptanceTest
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

	protected String name;
	protected String invoiceTemplate;
	protected String reconsilationComponent;
	protected String automationComponent;
	protected String invoiceSelection;
	protected String invoiceStep;
	protected String autoBaseLine;
	protected String stepName;
	Map<String, String> configuratonMap = null;
	protected Map<String, ArrayList<String>> excelReaderMapObj = null;
	protected Map<String, String> mapObj = null;
	String regex = new PSStringUtils().regexFirstLevelDelimeter();
	CarrierInvoiceActionImpl ciActionObj = new CarrierInvoiceActionImpl();
	protected PSGenericHelper genericHelperObj = new PSGenericHelper();
	DataVerificationHelper dataVerifyObj = new DataVerificationHelper();
	InvoiceReconConfigActionImpl actionObj = new InvoiceReconConfigActionImpl();

	/*
	 * Constructor for initializing excel Identifying the column size from the
	 * map passed
	 */
	@Test
	public InvoiceReconConfig( String path, String workBookName, String sheetName, String testCaseName ) throws Exception
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
	public InvoiceReconConfig( String path, String workBookName, String sheetName, String testCaseName, int occurance ) throws Exception
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
	 * Configuring the invoice Recon Config
	 * 
	 */
	public void invoiceReconConfigCreation() throws Exception
	{
		try
		{
			NavigationHelper.navigateToScreen( "Invoice Recon Config" );
			GenericHelper.waitForLoadmask( searchScreenWaitSec );
			for ( paramVal = 0; paramVal < colSize; paramVal++ )
			{
				ciReconMap = excelHolderObj.dataMap( paramVal );

				initializeVaribles( ciReconMap );
				ButtonHelper.click( "ClearButton" );
				boolean isInvoiceReconPresent = genericHelperObj.isGridTextValuePresent( "pireName", name, "Name" );
				if ( !isInvoiceReconPresent )
					newReconConfig();
				else
					Log4jHelper.logInfo( "Auto Invoice Congiguration is already available" );
			}
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

	public void newReconConfig() throws Exception
	{
		InvoiceReconConfigDetailImpl detailObj = new InvoiceReconConfigDetailImpl();
		actionObj.clickNewAction( partition );
		detailObj.invoiceReconBasicConfig( name, invoiceTemplate, reconsilationComponent, automationComponent, invoiceSelection, invoiceStep, autoBaseLine );
		detailObj.invoiceReconDetailConfig( this.path, this.workBookName, this.sheetName, stepName,ciReconMap );
		detailObj.saveInvoiceReconConfig( name );

	}

	/* This method is for Invoice Recon Config View Output Tables
	 */
	public void invoiceReconConfigViewOutputTables() throws Exception
	{

		NavigationHelper.navigateToScreen( "Invoice Recon Config" );
		for ( paramVal = 0; paramVal < colSize; paramVal++ )
		{
			ciReconMap = excelHolderObj.dataMap( paramVal );

			partition = ExcelHolder.getKey( ciReconMap, "Partition" );
			name = ExcelHolder.getKey( ciReconMap, "Name" );
			String tableInst = ExcelHolder.getKey( ciReconMap, "Table Instance" );
			boolean isReconConfigPresent = genericHelperObj.isGridTextValuePresent( "pireName", name, "Name" );
			if ( isReconConfigPresent )
			{
				InvoiceReconConfigActionImpl actionObj = new InvoiceReconConfigActionImpl();
				actionObj.viewOutputTablesAction( name );
				String val = GridHelper.getCellValue( "tablesGrid", 1 ,"Table Instances");
				assertEquals( val , tableInst );
				ButtonHelper.click( "outputTablesDetail.cancel" );
				GenericHelper.waitForLoadmask( searchScreenWaitSec );
				Log4jHelper.logInfo( "Invoice Recon Config is deleted successfully :" + name );
			}
			else
				Log4jHelper.logInfo( "Invoice Recon Config is not available with :" + name );
		}
	}
	
	/* This method is for Invoice Recon Config create Recon Request
	 */
	public void invoiceReconConfigRequest() throws Exception
	{

		NavigationHelper.navigateToScreen( "Invoice Recon Config" );
		for ( paramVal = 0; paramVal < colSize; paramVal++ )
		{
			ciReconMap = excelHolderObj.dataMap( paramVal );

			partition = ExcelHolder.getKey( ciReconMap, "Partition" );
			name = ExcelHolder.getKey( ciReconMap, "Name" );
			boolean isReconConfigPresent = genericHelperObj.isGridTextValuePresent( "pireName", name, "Name" );
			if ( isReconConfigPresent )
			{
				
				actionObj.invoiceConfigRequestAction(name);
				InvoiceReconciliationRequestDetailImpl detailObj = new InvoiceReconciliationRequestDetailImpl( ciReconMap );				
				detailObj.reconRequestDetailConfig();
				detailObj.reconSteps();
				detailObj.saveInvoiceReconRequest();				
				Log4jHelper.logInfo( "Invoice Recon Config is deleted successfully :" + name );
			}
			else
				Log4jHelper.logInfo( "Invoice Recon Config is not available with :" + name );
		}
	}
	
	/* This method is for Invoice Recon Config Reorder Action
	 * 
	 * 	 */
	public void invoiceReconConfigReorder() throws Exception
	{

		NavigationHelper.navigateToScreen( "Invoice Recon Config" );
		for ( paramVal = 0; paramVal < colSize; paramVal++ )
		{
			ciReconMap = excelHolderObj.dataMap( paramVal );

			partition = ExcelHolder.getKey( ciReconMap, "Partition" );
			name = ExcelHolder.getKey( ciReconMap, "Name" );
			String moveUp = ExcelHolder.getKey( ciReconMap, "MoveUp" );
			String moveDown = ExcelHolder.getKey( ciReconMap, "MoveDown" );
			int row;
			boolean isReconConfigPresent = genericHelperObj.isGridTextValuePresent( "pireName", name, "Name" );	

			if ( isReconConfigPresent )
			{
				actionObj.reorderAction(name);	
				if ( ValidationHelper.isNotEmpty( moveUp ) )
				{
					row = GridHelper.getRowNumber( "ReconReorderGrid", name, "Recon Config" );
					int moveRow = GridHelper.getRowNumber( "ReconReorderGrid", moveUp, "Recon Config" );
					int val = row - moveRow;
					int actualRow = moveUpMoveDownConfig( moveUp, "reconReorderGridToolbar.moveUp", val );
					assertEquals( actualRow, moveRow, "MoveUp is not performed" );

				}
				if ( ValidationHelper.isNotEmpty( moveDown ) )
				{
					row = GridHelper.getRowNumber( "ReconReorderGrid", name, "Recon Config" );
					int moveRow = GridHelper.getRowNumber( "ReconReorderGrid", moveDown, "Recon Config" );
					int val = moveRow - row;
					int actualRow = moveUpMoveDownConfig( moveDown, "reconReorderGridToolbar.moveDown", val );
					assertEquals( actualRow, moveRow, "MoveUp is not performed" );
				}				
			}
			else
				Log4jHelper.logInfo( "Invoice Recon Config is not available with :" + name );
		}
	}
	
	public int moveUpMoveDownConfig( String move, String gridID, int val ) throws Exception
	{
		for ( int i = 0; i < val; i++ )
		{
			GridHelper.clickRow( "orderGrid", name, "Event Match Rule Group" );
			ButtonHelper.click( gridID );
			GenericHelper.waitForLoadmask();

		}
		int actualRow = GridHelper.getRowNumber( "orderGrid", name, "Event Match Rule Group" );
		return actualRow;
	}


	/* This method is for Invoice Recon Config delete
	 */
	public void invoiceReconConfigDelete() throws Exception
	{

		NavigationHelper.navigateToScreen( "Invoice Recon Config" );
		for ( paramVal = 0; paramVal < colSize; paramVal++ )
		{
			ciReconMap = excelHolderObj.dataMap( paramVal );

			partition = ExcelHolder.getKey( ciReconMap, "Partition" );
			name = ExcelHolder.getKey( ciReconMap, "Name" );
			genericHelperObj.selectPartitionFilter( partition, "Non Deleted Items" );
			boolean isReconConfigPresent = genericHelperObj.isGridTextValuePresent( "pireName", name, "Name" );
			;

			if ( isReconConfigPresent )
			{
				genericHelperObj.clickDeleteOrUnDeleteAction( name, "Name", "Delete" );
				genericHelperObj.selectPartitionFilter( partition, "Deleted Items" );
				assertTrue( GridHelper.isValuePresent( "SearchGrid", name, "Name" ), name );
				Log4jHelper.logInfo( "Invoice Recon Config is deleted successfully :" + name );
			}
			else
				Log4jHelper.logInfo( "Invoice Recon Config is not available with :" + name );
		}
	}

	/*
	 * This method is for Invoice Recon Config un delete
	 */
	public void invoiceReconConfigUnDelete() throws Exception
	{

		NavigationHelper.navigateToScreen( "Invoice Recon Config" );
		for ( paramVal = 0; paramVal < colSize; paramVal++ )
		{
			ciReconMap = excelHolderObj.dataMap( paramVal );

			partition = ExcelHolder.getKey( ciReconMap, "Partition" );
			name = ExcelHolder.getKey( ciReconMap, "Name" );
			genericHelperObj.selectPartitionFilter( partition, "Deleted Items" );

			boolean isReconConfigPresent = genericHelperObj.isGridTextValuePresent( "pireName", name, "Name" );
			;

			if ( isReconConfigPresent )
			{
				genericHelperObj.clickDeleteOrUnDeleteAction( name, "Name", "Undelete" );
				genericHelperObj.selectPartitionFilter( partition, "Non Deleted Items" );
				assertTrue( GridHelper.isValuePresent( "SearchGrid", name, "Name" ), name );
				Log4jHelper.logInfo( "Invoice Recon Config is un deleted successfully :" + name );
			}
			else
				Log4jHelper.logInfo( "Invoice Recon Config is not available with :" + name );
		}

	}

	/*
	 * This method is for search screen column validation
	 */
	public void searchScreenColumnsValidation() throws Exception
	{
		NavigationHelper.navigateToScreen( "Invoice Recon Config" );
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

	public void initializeVaribles( Map<String, String> map ) throws Exception
	{
		name = ExcelHolder.getKey( map, "Name" );
		invoiceTemplate = ExcelHolder.getKey( map, "InvoiceTemplate" );
		reconsilationComponent = ExcelHolder.getKey( map, "ReconciliationComponent" );
		automationComponent = ExcelHolder.getKey( map, "AutomationComponent" );
		invoiceSelection = ExcelHolder.getKey( map, "InvoiceSelection" );
		invoiceStep = ExcelHolder.getKey( map, "InvoiceStep" );
		autoBaseLine = ExcelHolder.getKey( map, "AutoBaseLine" );
		stepName = ExcelHolder.getKey( map, "StepNameTestCase" );
	}

}
