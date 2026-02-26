package com.subex.rocps.automation.helpers.application.bilateral;

import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Map;

import com.subex.rocps.automation.helpers.application.aggregation.aggregationresult.AggregationResultImpl;
import com.subex.rocps.automation.helpers.application.bilateral.bilateralratedmodelling.BilateralRatedModellingDetailImpl;
import com.subex.rocps.automation.helpers.application.filters.AdvanceSearchFiltersHelper;
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
import com.subex.automation.helpers.data.ValidationHelper;
import com.subex.automation.helpers.file.ExcelReader;
import com.subex.automation.helpers.report.Log4jHelper;
import com.subex.automation.helpers.util.FailureHelper;

public class BilateralRatedDetailModelling extends PSAcceptanceTest
{
	protected ExcelReader excelData = null;
	protected Map<String, ArrayList<String>> bilateralExcel = null;
	protected Map<String, String> bilateralMap = null;
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
	public BilateralRatedDetailModelling( String path, String workBookName, String sheetName, String testCaseName ) throws Exception
	{
		this.path = path;
		this.workBookName = workBookName;
		this.sheetName = sheetName;
		this.testCaseName = testCaseName;
		excelData = new ExcelReader();
		bilateralExcel = excelData.readDataByColumn( this.path, this.workBookName, this.sheetName, this.testCaseName );
		excelHolderObj = new ExcelHolder( bilateralExcel );
		colSize = excelHolderObj.totalColumns();
	}

	/*
	 * Overloaded constructor for reading data from excel if test case name
	 * appears more than once
	 * 
	 * @Param occurance : Test case instance in excel sheet Constructor for
	 * initializing excel Identifying the column size from the map
	 */
	public BilateralRatedDetailModelling( String path, String workBookName, String sheetName, String testCaseName, int occurance ) throws Exception
	{
		this.path = path;
		this.workBookName = workBookName;
		this.sheetName = sheetName;
		this.testCaseName = testCaseName;
		excelData = new ExcelReader();
		bilateralExcel = excelData.readDataByColumn( this.path, this.workBookName, this.sheetName, this.testCaseName, occurance );
		excelHolderObj = new ExcelHolder( bilateralExcel );
		colSize = excelHolderObj.totalColumns();
	}

	/*
	 * Configuring the Bands requiring rerate
	 * 
	 */
	public void configurebrdModelling() throws Exception
	{
		try
		{
			NavigationHelper.navigateToScreen( "Bilateral Rated Details Modelling" );
			for ( paramVal = 0; paramVal < colSize; paramVal++ )
			{
				bilateralMap = excelHolderObj.dataMap( paramVal );
				initializeVariables( bilateralMap );
				if(!bilateralModellingisPresent())
					newBilateralModellingConfig();
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
	
	public void newBilateralModellingConfig() throws Exception
	{
		BilateralRatedModellingDetailImpl detailObj = new BilateralRatedModellingDetailImpl( bilateralMap );
		detailObj.newBilateralModelling();
		detailObj.basicDetails();
		detailObj.brdModellingGridConfig();
		detailObj.saveBilateralModelling();
	}
	
	public boolean bilateralModellingisPresent() throws Exception
	{
		String headerElement = "//*[@id='searchGrid']//th//div[contains(text(),'Name')]";
		if ( !ElementHelper.isElementPresent( headerElement ) )
			ElementHelper.waitForElement( headerElement, searchScreenWaitSec );
		SearchGridHelper.gridFilterSearchWithTextBox( "pbmdName", name, "Name" );
		return GridHelper.isValuePresent( "SearchGrid", name, "Name" );
	}
	
	/*
	 * This method is for Bilateral Rated Modelling change status
	 */
	public void brdChangeStatus() throws Exception {

		NavigationHelper.navigateToScreen("Bilateral Rated Details Modelling");
		for (paramVal = 0; paramVal < colSize; paramVal++) {

			bilateralMap = excelHolderObj.dataMap(paramVal);
			name = ExcelHolder.getKey(bilateralMap, "Name");
			ButtonHelper.click("ClearButton");
			GenericHelper.waitForLoadmask(searchScreenWaitSec);
			
			if (bilateralModellingisPresent()) {
				GridHelper.clickRow("SearchGrid", name, "Name");
				GenericHelper.waitForLoadmask(searchScreenWaitSec);
				String actualVal = GridHelper.getCellValue("SearchGrid", 1, "Status");
				if (actualVal.equalsIgnoreCase("Draft")) {
					NavigationHelper.navigateToAction("Change Status", "Accept Bilateral Rated Modelling");
					GenericHelper.waitForLoadmask(searchScreenWaitSec);
					if(PopupHelper.isTextPresent( "window-scroll-panel", "The bilateral rated modelling cannot be edited after accepted. Are you sure you wish to change the bilateral rated modelling status to accepted?" ))
					{
					ButtonHelper.click( "YesButton" );
					GenericHelper.waitForLoadmask(searchScreenWaitSec);
					}
					ButtonHelper.click("ClearButton");
					GenericHelper.waitForLoadmask(searchScreenWaitSec);
					String headerElement = "//*[@id='searchGrid']//th//div[contains(text(),'Name')]";
					if ( !ElementHelper.isElementPresent( headerElement ) )
						ElementHelper.waitForElement( headerElement, searchScreenWaitSec );
					SearchGridHelper.gridFilterSearchWithTextBox( "pbmdName", name, "Name" );
					ButtonHelper.click("SearchButton");
					GenericHelper.waitForLoadmask(searchScreenWaitSec);
				
					assertTrue(GridHelper.isValuePresent("SearchGrid", "Accepted", "Status"), "Status is not changed");
					Log4jHelper.logInfo("Bilateral Rated Modelling status has changed successfully :" + name);
				} else
					Log4jHelper.logInfo("Bilateral Rated Modelling status is in accepted state");
				
			} else
				Log4jHelper.logInfo("Bilateral Rated Modelling is not available with :" + name);

		}
	}
	

	/*
	 * This method is for Bilateral Rated Modelling discontinue
	 */
	public void brdDiscontinue() throws Exception {

		NavigationHelper.navigateToScreen("Bilateral Rated Details Modelling");
		for (paramVal = 0; paramVal < colSize; paramVal++) {

			bilateralMap = excelHolderObj.dataMap(paramVal);
			name = ExcelHolder.getKey(bilateralMap, "Name");
			ButtonHelper.click("ClearButton");
			GenericHelper.waitForLoadmask(searchScreenWaitSec);
			
			if (bilateralModellingisPresent()) {
				GridHelper.clickRow("SearchGrid", name, "Name");
				GenericHelper.waitForLoadmask(searchScreenWaitSec);
				String actualVal = GridHelper.getCellValue("SearchGrid", 1, "Status");
				if (actualVal.equalsIgnoreCase("Accepted")) {
					NavigationHelper.navigateToAction("Change Status", "Discontinue Bilateral Rated Modelling");
					GenericHelper.waitForLoadmask(searchScreenWaitSec);
					if(PopupHelper.isTextPresent( "window-scroll-panel", "This change cannot be reverted back. Are you sure you wish to change the bilateral rated detail modelling status to discontinued?" ))
					{
					ButtonHelper.click( "YesButton" );
					GenericHelper.waitForLoadmask(searchScreenWaitSec);
					}
					ButtonHelper.click("ClearButton");
					GenericHelper.waitForLoadmask(searchScreenWaitSec);
					String headerElement = "//*[@id='searchGrid']//th//div[contains(text(),'Name')]";
					if ( !ElementHelper.isElementPresent( headerElement ) )
						ElementHelper.waitForElement( headerElement, searchScreenWaitSec );
					SearchGridHelper.gridFilterSearchWithTextBox( "pbmdName", name, "Name" );
					ButtonHelper.click("SearchButton");
					GenericHelper.waitForLoadmask(searchScreenWaitSec);				
					assertTrue(GridHelper.isValuePresent("SearchGrid", "Discontinued", "Status"), "Status is not changed");
					Log4jHelper.logInfo("Bilateral Rated Modelling status has changed successfully :" + name);
				} else
					Log4jHelper.logInfo("Bilateral Rated Modelling status is not in accepted state");
				
			} else
				Log4jHelper.logInfo("Bilateral Rated Modelling is not available with :" + name);

		}
	}
	
	/*
	 * This method is for Bilateral Rated Modelling view results
	 */
	public void brdViewResults() throws Exception {

		NavigationHelper.navigateToScreen("Bilateral Rated Details Modelling");
		for (paramVal = 0; paramVal < colSize; paramVal++) {

			bilateralMap = excelHolderObj.dataMap(paramVal);
			name = ExcelHolder.getKey(bilateralMap, "Name");
			String billProfile = ExcelHolder.getKey(bilateralMap, "BillProfile");
			String eventFromDate = ExcelHolder.getKey(bilateralMap, "EventDate");
			String eventToDate = ExcelHolder.getKey(bilateralMap, "EventToDate");
			String colHeader = ExcelHolder.getKey(bilateralMap, "ColHeader");
			String results = ExcelHolder.getKey(bilateralMap, "Results");
			AdvanceSearchFiltersHelper advsearchObj = new AdvanceSearchFiltersHelper();
			ButtonHelper.click("ClearButton");
			GenericHelper.waitForLoadmask(searchScreenWaitSec);
			
			if (bilateralModellingisPresent()) {
				GridHelper.clickRow("SearchGrid", name, "Name");
				GenericHelper.waitForLoadmask(searchScreenWaitSec);
				PSGenericHelper.waitForParentActionElementTOBeclickable( "Merger Actions" );
					NavigationHelper.navigateToAction("Merger Actions", "View Results");
					GenericHelper.waitForLoadmask(searchScreenWaitSec);
					Thread.sleep( 2000 );
					//assertEquals( NavigationHelper.getScreenTitle(), "Merger Results" );
					advsearchObj.billProfileAdvanceSearch( "Bill Profile", billProfile );
					//genericHelperObj.setDate( "bimr$evt_dttm", eventDate );
					CalendarHelper.setOnDate( "PS_searchPanelId", "bimr$evt_dttm", eventFromDate );					
					GenericHelper.waitForLoadmask( searchScreenWaitSec );
					ButtonHelper.click( "SearchButton" );
					GenericHelper.waitForLoadmask( searchScreenWaitSec );
					if(!results.isEmpty())
						dataVerifyObj.validateData( "grid_column_header_searchGrid_", bilateralMap, "SearchGrid", colHeader, results );
					Log4jHelper.logInfo("Bilateral Rated Modelling results has been validated :" + name);
				} 
			else
				Log4jHelper.logInfo("Bilateral Rated Modelling is not available with :" + name);

		}
	}
	
	/*
	 * This method is for search screen column validation
	 */
	public void searchScreenColumnsValidation() throws Exception
	{
		NavigationHelper.navigateToScreen( "Bilateral Rated Details Modelling" );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		for ( paramVal = 0; paramVal < colSize; paramVal++ )
		{
			bilateralMap = excelHolderObj.dataMap( paramVal );
			String searchScreenColumns = ExcelHolder.getKey( bilateralMap, "SearchScreenColumns" );
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
	 * This method is to delete Bilateral Rated Modelling
	 */
	public void brdDelete() throws Exception
	{

		NavigationHelper.navigateToScreen( "Bilateral Rated Details Modelling" );
		for ( paramVal = 0; paramVal < colSize; paramVal++ )
		{
			bilateralMap = excelHolderObj.dataMap( paramVal );

			partition = ExcelHolder.getKey( bilateralMap, "Partition" );
			name = ExcelHolder.getKey( bilateralMap, "Name" );
			
			
			genericHelperObj.selectPartitionFilter( partition, "Non Deleted Items" );
			
			if ( bilateralModellingisPresent() )
			{
				genericHelperObj.clickDeleteOrUnDeleteAction( name, "Name", "Delete" );
				GenericHelper.waitForLoadmask();
				genericHelperObj.selectPartitionFilter( partition, "Deleted Items" );
				assertTrue( GridHelper.isValuePresent( "SearchGrid", name, "Name" ));
				Log4jHelper.logInfo( "Bilateral Rated Modelling is deleted successfully with name " + name );
			}
			else
				Log4jHelper.logInfo( "Bilateral Rated Modelling is not available with name " + name );
		}
	}

	/*
	 * This method is to un delete Bilateral Rated Modelling
	 */
	public void brdUnDelete() throws Exception
	{

		NavigationHelper.navigateToScreen( "Bilateral Rated Details Modelling" );
		for ( paramVal = 0; paramVal < colSize; paramVal++ )
		{
			bilateralMap = excelHolderObj.dataMap( paramVal );

			partition = ExcelHolder.getKey( bilateralMap, "Partition" );
			name = ExcelHolder.getKey( bilateralMap, "Name" );
			genericHelperObj.selectPartitionFilter( partition, "Deleted Items" );
			GenericHelper.waitForLoadmask();
			
			if ( bilateralModellingisPresent() )
			{
				genericHelperObj.clickDeleteOrUnDeleteAction( name, "Name", "Undelete" );
				GenericHelper.waitForLoadmask();
				genericHelperObj.selectPartitionFilter( partition, "Non Deleted Items" );
				assertTrue( GridHelper.isValuePresent( "SearchGrid", name, "Name" ) );
				Log4jHelper.logInfo( "Bilateral Rated Modelling is un deleted successfully : " + name );
			}
			else
				Log4jHelper.logInfo( "Bilateral Rated Modelling is not available : " + name );
		}
	}
	
	public void initializeVariables(Map<String, String> map) throws Exception
	{
		name = ExcelHolder.getKey( map, "Name" );
		partition = ExcelHolder.getKey( map, "Partition" );
		
	}
}
