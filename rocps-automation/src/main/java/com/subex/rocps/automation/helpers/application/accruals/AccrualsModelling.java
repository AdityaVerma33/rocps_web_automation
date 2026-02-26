package com.subex.rocps.automation.helpers.application.accruals;

import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Map;

import com.subex.rocps.automation.helpers.application.accruals.accuralsmodelling.AccrualsModellingDetailImpl;
import com.subex.rocps.automation.helpers.application.bilateral.bilateralratedmodelling.BilateralRatedModellingDetailImpl;
import com.subex.rocps.automation.helpers.application.deal.deal.DealDetailImpl;
import com.subex.rocps.automation.helpers.application.filters.AdvanceSearchFiltersHelper;
import com.subex.rocps.automation.helpers.application.filters.PSSearchGridHelper;
import com.subex.rocps.automation.helpers.application.genericHelpers.DataVerificationHelper;
import com.subex.rocps.automation.helpers.application.genericHelpers.PSGenericHelper;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;
import com.subex.rocps.automation.utils.ExcelHolder;
import com.subex.rocps.automation.utils.PSStringUtils;

import com.subex.automation.helpers.application.NavigationHelper;
import com.subex.automation.helpers.component.ButtonHelper;
import com.subex.automation.helpers.component.CalendarHelper;
import com.subex.automation.helpers.component.ElementHelper;
import com.subex.automation.helpers.component.GenericHelper;
import com.subex.automation.helpers.component.GridHelper;
import com.subex.automation.helpers.component.PopupHelper;
import com.subex.automation.helpers.component.SearchGridHelper;
import com.subex.automation.helpers.file.ExcelReader;
import com.subex.automation.helpers.report.Log4jHelper;
import com.subex.automation.helpers.util.FailureHelper;

public class AccrualsModelling extends PSAcceptanceTest
{
	protected ExcelReader excelData = null;
	protected Map<String, ArrayList<String>> accModExcel = null;
	protected Map<String, String> accModMap = null;
	protected ExcelHolder excelHolderObj = null;
	protected String path;
	protected String workBookName;
	protected String sheetName;
	protected String testCaseName;
	protected int paramVal;
	protected int colSize;
	protected String partition;
	protected String name;
	protected String prefix;
	protected String mergedTablePrefix;
	protected String aggrConfiguration;
	protected String schema;
	protected String billedUsage;
	protected Map<String, ArrayList<String>> excelReaderMapObj = null;
	protected Map<String, String> mapObj = null;
	String regex = new PSStringUtils().regexFirstLevelDelimeter();
	protected PSGenericHelper genericHelperObj = new PSGenericHelper();
	DataVerificationHelper dataVerifyObj = new DataVerificationHelper();

	/*
	 * Constructor for initializing excel Identifying the column size from the
	 * map passed
	 */
	@Test
	public AccrualsModelling( String path, String workBookName, String sheetName, String testCaseName ) throws Exception
	{
		this.path = path;
		this.workBookName = workBookName;
		this.sheetName = sheetName;
		this.testCaseName = testCaseName;
		excelData = new ExcelReader();
		accModExcel = excelData.readDataByColumn( this.path, this.workBookName, this.sheetName, this.testCaseName );
		excelHolderObj = new ExcelHolder( accModExcel );
		colSize = excelHolderObj.totalColumns();
	}

	/*
	 * Overloaded constructor for reading data from excel if test case name
	 * appears more than once
	 * 
	 * @Param occurance : Test case instance in excel sheet Constructor for
	 * initializing excel Identifying the column size from the map
	 */
	public AccrualsModelling( String path, String workBookName, String sheetName, String testCaseName, int occurance ) throws Exception
	{
		this.path = path;
		this.workBookName = workBookName;
		this.sheetName = sheetName;
		this.testCaseName = testCaseName;
		excelData = new ExcelReader();
		accModExcel = excelData.readDataByColumn( this.path, this.workBookName, this.sheetName, this.testCaseName, occurance );
		excelHolderObj = new ExcelHolder( accModExcel );
		colSize = excelHolderObj.totalColumns();
	}

	/*
	 * Configuring the accurals modelling
	 * 
	 */
	public void configureAccuralsModelling() throws Exception
	{
		try
		{
			NavigationHelper.navigateToScreen( "Accruals Modelling" );
			for ( paramVal = 0; paramVal < colSize; paramVal++ )
			{
				accModMap = excelHolderObj.dataMap( paramVal );
				initializeVariables( accModMap );
				boolean accrualsModellingIsPresent = genericHelperObj.isGridTextValuePresent( "PS_Detail_accrualMod_name_txtID", name, "Name" );
				if(!accrualsModellingIsPresent)
					newAccrualsModellingConfig();
				else
					Log4jHelper.logInfo( "Bilateral Rated Modelling is already availablewith name :" +name);			
				
			}
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}
	
	public void newAccrualsModellingConfig() throws Exception
	{
		AccrualsModellingDetailImpl detailObj = new AccrualsModellingDetailImpl(accModMap);
		detailObj.newAccuralsModelling();
		detailObj.basicDetails();
		detailObj.tableInstance();
		
		detailObj.columnDetailsTabConfig();
		detailObj.saveAccrualsModelling();
		
	}	
	
	/*
	 * This method is for search screen column validation
	 */
	public void searchScreenColumnsValidation() throws Exception
	{
		NavigationHelper.navigateToScreen( "Accruals Modelling" );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		for ( paramVal = 0; paramVal < colSize; paramVal++ )
		{
			accModMap = excelHolderObj.dataMap( paramVal );
			String searchScreenColumns = ExcelHolder.getKey( accModMap, "SearchScreenColumns" );
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
	 * This method is for change status  action
	 */
	public void accuralModellingChangeStatus() throws Exception
	{

		NavigationHelper.navigateToScreen( "Accruals Modelling" );
		for ( paramVal = 0; paramVal < colSize; paramVal++ )
		{
			accModMap = excelHolderObj.dataMap( paramVal );
			name = ExcelHolder.getKey( accModMap, "Name" );	
			boolean accrualsModellingIsPresent = genericHelperObj.isGridTextValuePresent( "PS_Detail_accrualMod_name_txtID", name, "Name" );
			if(accrualsModellingIsPresent)
			{
				GridHelper.clickRow( "SearchGrid", name, "Name" );
				String val = GridHelper.getCellValue( "SearchGrid", 1, "Status" );
				if(val.contains( "Draft" ))
				{
				PSGenericHelper.waitForParentActionElementTOBeclickable( "Change Status" );
				NavigationHelper.navigateToAction( "Change Status", "Accept Accruals Modelling" );
				GenericHelper.waitForLoadmask( searchScreenWaitSec );	
				
				if(PopupHelper.isPresent())
					ButtonHelper.click( "YesButton" );
				GenericHelper.waitForLoadmask( searchScreenWaitSec );
				ButtonHelper.click( "SearchButton" );
				GenericHelper.waitForLoadmask( searchScreenWaitSec );
				genericHelperObj.waitforHeaderElement( "Name" );
				GenericHelper.waitForLoadmask( searchScreenWaitSec );
				PSSearchGridHelper.gridFilterSearchWithTextBox( "PS_Detail_accrualMod_name_txtID", name, "Name" );
				GenericHelper.waitForLoadmask( searchScreenWaitSec );
				assertTrue( GridHelper.isValuePresent( "SearchGrid", "Accepted", "Status" ), "Accrual change status is not successfull" );
				Log4jHelper.logInfo( "Accruals status is changed  successfully :" + name );
				}
			}
			else
				Log4jHelper.logInfo( "Accural modelling  is not available with :" + name );
		}
	}
	
	/*
	 * This method is for change status  action
	 */
	public void accuralModellingDiscontinue() throws Exception
	{

		NavigationHelper.navigateToScreen( "Accruals Modelling" );
		for ( paramVal = 0; paramVal < colSize; paramVal++ )
		{
			accModMap = excelHolderObj.dataMap( paramVal );
			name = ExcelHolder.getKey( accModMap, "Name" );	
			boolean accrualsModellingIsPresent = genericHelperObj.isGridTextValuePresent( "PS_Detail_accrualMod_name_txtID", name, "Name" );
			if(accrualsModellingIsPresent)
			{
				GridHelper.clickRow( "SearchGrid", name, "Name" );
				String val = GridHelper.getCellValue( "SearchGrid", 1, "Status" );
				if(val.contains( "Accepted" ))
				{
				PSGenericHelper.waitForParentActionElementTOBeclickable( "Change Status" );
				NavigationHelper.navigateToAction( "Change Status", "Discontinue Accruals Modelling" );
				GenericHelper.waitForLoadmask( searchScreenWaitSec );	
				
				if(PopupHelper.isPresent())
					ButtonHelper.click( "YesButton" );
				GenericHelper.waitForLoadmask( searchScreenWaitSec );
				ButtonHelper.click( "SearchButton" );
				GenericHelper.waitForLoadmask( searchScreenWaitSec );
				assertTrue( accrualsModellingIsPresent );
				assertTrue( GridHelper.isValuePresent( "SearchGrid", "Discontinued", "Status" ), "Accrual change status is not successfull" );
				Log4jHelper.logInfo( "Accruals status is changed  successfully :" + name );
				}
			}
			else
				Log4jHelper.logInfo( "Accural modelling  is not available with :" + name );
		}
	}
	
	/*
	 * This method is for edit accruals Modelling
	 */
	public void accrualsModelingEdit() throws Exception
	{

		NavigationHelper.navigateToScreen( "Accruals Modelling" );
		for ( paramVal = 0; paramVal < colSize; paramVal++ )
		{
			accModMap = excelHolderObj.dataMap( paramVal );
			name = ExcelHolder.getKey( accModMap, "Name" );			
					
			GenericHelper.waitForLoadmask( searchScreenWaitSec );
			boolean accrualsModellingIsPresent = genericHelperObj.isGridTextValuePresent( "PS_Detail_accrualMod_name_txtID", name, "Name" );
			if(accrualsModellingIsPresent)
			{
				int row = GridHelper.getRowNumber( "SearchGrid", name, "Name" );
				NavigationHelper.navigateToEdit( "SearchGrid", row );
				assertTrue( ElementHelper.isElementPresent( "//*[text()='Accrual  Details']" ), "Edit Accrual Modelling Page is not opened" );
				AccrualsModellingDetailImpl detailObj = new AccrualsModellingDetailImpl(accModMap);				
				detailObj.basicDetails();
				detailObj.editTableInstance();				
				detailObj.columnDetailsTabConfig();
				detailObj.saveAccrualsModelling();
				Log4jHelper.logInfo( "Accurals Modelling is Edited successfully :" + name );
			}
			else
				Log4jHelper.logInfo( "Accurals Modelling is not available with :" + name );
		}
	}

	
	/*
	 * This method is to delete Accurals Modelling
	 */
	public void accrualsModellingDelete() throws Exception
	{

		NavigationHelper.navigateToScreen( "Accruals Modelling" );
		for ( paramVal = 0; paramVal < colSize; paramVal++ )
		{
			accModMap = excelHolderObj.dataMap( paramVal );

			partition = ExcelHolder.getKey( accModMap, "Partition" );
			name = ExcelHolder.getKey( accModMap, "Name" );			
			genericHelperObj.selectPartitionFilter( partition, "Non Deleted Items" );
			boolean accrualsModellingIsPresent = genericHelperObj.isGridTextValuePresent( "PS_Detail_accrualMod_name_txtID", name, "Name" );
			if(accrualsModellingIsPresent)
			{
				genericHelperObj.clickDeleteOrUnDeleteAction( name, "Name", "Delete" );
				GenericHelper.waitForLoadmask();
				genericHelperObj.selectPartitionFilter( partition, "Deleted Items" );
				assertTrue( GridHelper.isValuePresent( "SearchGrid", name, "Name" ));
				Log4jHelper.logInfo( "Accurals Modelling is deleted successfully with name " + name );
			}
			else
				Log4jHelper.logInfo( "Accurals Modelling is not available with name " + name );
		}
	}

	/*
	 * This method is to un delete Accurals Modelling
	 */
	public void accrualsModellingUnDelete() throws Exception
	{

		NavigationHelper.navigateToScreen( "Accruals Modelling" );
		for ( paramVal = 0; paramVal < colSize; paramVal++ )
		{
			accModMap = excelHolderObj.dataMap( paramVal );

			partition = ExcelHolder.getKey( accModMap, "Partition" );
			name = ExcelHolder.getKey( accModMap, "Name" );
			genericHelperObj.selectPartitionFilter( partition, "Deleted Items" );
			GenericHelper.waitForLoadmask();
			
			boolean accrualsModellingIsPresent = genericHelperObj.isGridTextValuePresent( "PS_Detail_accrualMod_name_txtID", name, "Name" );
			if(accrualsModellingIsPresent)
			{
				genericHelperObj.clickDeleteOrUnDeleteAction( name, "Name", "Undelete" );
				GenericHelper.waitForLoadmask();
				genericHelperObj.selectPartitionFilter( partition, "Non Deleted Items" );
				assertTrue( GridHelper.isValuePresent( "SearchGrid", name, "Name" ) );
				Log4jHelper.logInfo( "Accurals Modelling is un deleted successfully : " + name );
			}
			else
				Log4jHelper.logInfo( "Accurals Modelling is not available : " + name );
		}
	}
	
	public void initializeVariables(Map<String, String> map) throws Exception
	{
		name = ExcelHolder.getKey( map, "Name" );
		partition = ExcelHolder.getKey( map, "Partition" );
		
	}
}
