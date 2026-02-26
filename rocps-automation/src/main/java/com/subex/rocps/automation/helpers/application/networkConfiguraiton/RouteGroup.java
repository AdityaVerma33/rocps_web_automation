package com.subex.rocps.automation.helpers.application.networkConfiguraiton;

import java.util.ArrayList;
import java.util.Map;

import org.testng.annotations.Test;

import com.subex.automation.helpers.application.NavigationHelper;
import com.subex.automation.helpers.component.ButtonHelper;
import com.subex.automation.helpers.component.EntityComboHelper;
import com.subex.automation.helpers.component.GenericHelper;
import com.subex.automation.helpers.component.GridHelper;
import com.subex.automation.helpers.component.TextBoxHelper;
import com.subex.automation.helpers.data.ValidationHelper;
import com.subex.automation.helpers.file.ExcelReader;
import com.subex.automation.helpers.report.Log4jHelper;
import com.subex.automation.helpers.util.FailureHelper;
import com.subex.rocps.automation.helpers.application.genericHelpers.DataVerificationHelper;
import com.subex.rocps.automation.helpers.application.genericHelpers.PSActionImpl;
import com.subex.rocps.automation.helpers.application.genericHelpers.PSEntityComboHelper;
import com.subex.rocps.automation.helpers.application.genericHelpers.PSGenericHelper;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;
import com.subex.rocps.automation.utils.ExcelHolder;
import com.subex.rocps.automation.utils.PSStringUtils;

public class RouteGroup extends PSAcceptanceTest
{

	protected ExcelReader excelData = null;
	protected Map<String, ArrayList<String>> routegrpExcel = null;
	protected Map<String, String> routegrpMap = null;
	protected ExcelHolder excelHolderObj = null;
	protected String path;
	protected String workBookName;
	protected String sheetName;
	protected String testCaseName;
	protected int paramVal;
	protected int colSize;
	protected String clientPartition;
	protected String name;
	protected String operator;
	String route;
	protected PSGenericHelper genericHelperObj = new PSGenericHelper();
	PSStringUtils strObj = new PSStringUtils();
	DataVerificationHelper dataObj = new DataVerificationHelper();
	PSActionImpl psActionImpl = new PSActionImpl();

	/*
	 * Constructor for initializing excel Identifying the column size from the map
	 * passed
	 */
	@Test
	public RouteGroup( String path, String workBookName, String sheetName, String testCaseName ) throws Exception
	{
		this.path = path;
		this.workBookName = workBookName;
		this.sheetName = sheetName;
		this.testCaseName = testCaseName;
		excelData = new ExcelReader();
		routegrpExcel = excelData.readDataByColumn( this.path, this.workBookName, this.sheetName, this.testCaseName );
		excelHolderObj = new ExcelHolder( routegrpExcel );
		colSize = excelHolderObj.totalColumns();
	}

	/*
	 * Overloaded constructor for reading data from excel if test case name appears
	 * more than once
	 * 
	 * @Param occurance : Test case instance in excel sheet Constructor for
	 * initializing excel Identifying the column size from the map
	 */
	public RouteGroup( String path, String workBookName, String sheetName, String testCaseName, int occurance ) throws Exception
	{
		this.path = path;
		this.workBookName = workBookName;
		this.sheetName = sheetName;
		this.testCaseName = testCaseName;
		excelData = new ExcelReader();
		routegrpExcel = excelData.readDataByColumn( this.path, this.workBookName, this.sheetName, this.testCaseName, occurance );
		excelHolderObj = new ExcelHolder( routegrpExcel );
		colSize = excelHolderObj.totalColumns();
	}

	/*
	 * Configuring the RouteGroup
	 * 
	 * @method : isRouteGroupPresent returns false then RouteGroup is configured
	 * isRouteGroupPresent returns true then RouteGroup is not configured
	 */
	public void routeGrpCreation() throws Exception
	{
		try
		{
			NavigationHelper.navigateToScreen( "Route Group" );
			for ( paramVal = 0; paramVal < colSize; paramVal++ )
			{

				routegrpMap = excelHolderObj.dataMap( paramVal );

				initializeVariables();
				ButtonHelper.click( "ClearButton" );
				GenericHelper.waitForLoadmask( searchScreenWaitSec );
				boolean isRouteGroupPresent = genericHelperObj.isGridTextValuePresent( "PS_Detail_routeGroup_name_txtId", name, "Name" );

				if ( !isRouteGroupPresent )
				{
					genericHelperObj.clickNewAction( clientPartition );
					GenericHelper.waitForLoadmask();
					newRouteGroup();
					genericHelperObj.detailSave( "PS_Detail_routeGroup_savebtn", name, "Name" );
					Log4jHelper.logInfo( "Route group is created successfully with name " + name );

				}
				else
				{
					Log4jHelper.logInfo( "Route Group is available with name " + name );
				}

			}
		}
		catch ( Exception e )
		{

			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

	/*
	 * This method is to create new RouteGroup
	 */
	protected void newRouteGroup() throws Exception
	{

		TextBoxHelper.type( "PS_Detail_routeGroup_name_txtId", name );
		genericHelperObj.waitforEntityElement();
		PSEntityComboHelper.selectUsingGridFilterTextBox( "PS_Detail_routeGroup_operator_entitybtn", "Operator", "Detail_operator_name_txtId", operator, "Name" );

	}

	/*
	 * This method is to edit route group
	 */
	public void editRouteGrp() throws Exception
	{
		try
		{
			NavigationHelper.navigateToScreen( "Route Group" );
			for ( paramVal = 0; paramVal < colSize; paramVal++ )
			{

				routegrpMap = excelHolderObj.dataMap( paramVal );

				initializeVariables();
				ButtonHelper.click( "ClearButton" );
				GenericHelper.waitForLoadmask( searchScreenWaitSec );
				boolean isRouteGroupPresent = genericHelperObj.isGridTextValuePresent( "PS_Detail_routeGroup_name_txtId", name, "Name" );

				if ( isRouteGroupPresent )
				{
					int row = GridHelper.getRowNumber( "SearchGrid", name, "Name" );
					NavigationHelper.navigateToEdit( "SearchGrid", row );
					GenericHelper.waitForLoadmask();
					editRouteGroupDetail();
					genericHelperObj.detailSave( "PS_Detail_routeGroup_savebtn", name, "Name" );
					Log4jHelper.logInfo( "Route group is created successfully with name " + name );

				}
				else
				{
					Log4jHelper.logInfo( "Route Group is not available with name " + name );
				}

			}
		}
		catch ( Exception e )
		{

			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

	/*
	 * This method is to edit route group detail
	 */
	protected void editRouteGroupDetail() throws Exception
	{

		TextBoxHelper.type( "PS_Detail_routeGroup_name_txtId", name );
		if ( ValidationHelper.isNotEmpty( operator ) )
		{
			genericHelperObj.waitforEntityElement();
			PSEntityComboHelper.selectUsingGridFilterTextBox( "PS_Detail_routeGroup_operator_entitybtn", "Operator", "Detail_operator_name_txtId", operator, "Name" );
		}

	}

	/*
	 * This method is to view Route Action
	 */
	public void viewRoute() throws Exception
	{
		NavigationHelper.navigateToScreen( "Route Group" );
		for ( paramVal = 0; paramVal < colSize; paramVal++ )
		{
			routegrpMap = excelHolderObj.dataMap( paramVal );
			name = ExcelHolder.getKey( routegrpMap, "Name" );
			String mapkeys = ExcelHolder.getKey( routegrpMap, "MapRowKeys" );
			String colmHdrs = ExcelHolder.getKey( routegrpMap, "ColmnHeaders" );
			if ( ValidationHelper.isNotEmpty( mapkeys ) )
			{
				boolean isRouteGroupPresent = genericHelperObj.isGridTextValuePresent( "PS_Detail_routeGroup_name_txtId", name, "Name" );
				String gridId = "routeGrid";
				if ( isRouteGroupPresent )
				{
					GridHelper.clickRow( "SearchGrid", name, "Name" );
					psActionImpl.clickOnAction( "View", "Route" );
					GenericHelper.waitForLoadmask( searchScreenWaitSec );
					genericHelperObj.waitforPopupHeaderElement("window-scroll-panel", gridId, "Route  Name" );
					dataObj.validateDataWithoutSorting( gridId, "grid_column_header_undefined_", routegrpMap, colmHdrs, mapkeys, false );
					ButtonHelper.click( "routeDetail.cancel" );
					genericHelperObj.waitforPopupHeaderElementToDisappear( "window-scroll-panel",gridId, "Route  Name" );
					GenericHelper.waitForLoadmask( searchScreenWaitSec );
				}
			}
		}
	}

	/*
	 * This method is to initialize instance variables
	 */
	protected void initializeVariables() throws Exception
	{

		clientPartition = ExcelHolder.getKey( routegrpMap, "Partition" );
		name = ExcelHolder.getKey( routegrpMap, "Name" );
		operator = ExcelHolder.getKey( routegrpMap, "Operator" );
	}

	/*
	 * This method is to validate search screen columns
	 */
	public void searchScreenColumnsValidation() throws Exception
	{
		NavigationHelper.navigateToScreen( "Route Group" );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		for ( paramVal = 0; paramVal < colSize; paramVal++ )
		{
			routegrpMap = excelHolderObj.dataMap( paramVal );
			String searchScreenColumns = ExcelHolder.getKey( routegrpMap, "SearchScreenColumns" );
			ArrayList<String> excelColumnNames = new ArrayList<String>();
			String[] searchGridColumnsArr = strObj.stringSplitFirstLevel( searchScreenColumns );
			for ( int col = 0; col < searchGridColumnsArr.length; col++ )
			{
				excelColumnNames.add( searchGridColumnsArr[col] );
			}
			genericHelperObj.totalColumns( excelColumnNames );
		}

	}

	/*
	 * This method is for route group deletion
	 */
	public void routeGroupDelete() throws Exception
	{
		NavigationHelper.navigateToScreen( "Route Group" );
		for ( paramVal = 0; paramVal < colSize; paramVal++ )
		{

			routegrpMap = excelHolderObj.dataMap( paramVal );

			clientPartition = ExcelHolder.getKey( routegrpMap, "Partition" );
			name = ExcelHolder.getKey( routegrpMap, "Name" );

			genericHelperObj.selectPartitionFilter( clientPartition, "Non Deleted Items" );
			GenericHelper.waitForLoadmask();
			boolean isRouteGroupPresent = genericHelperObj.isGridTextValuePresent( "PS_Detail_routeGroup_name_txtId", name, "Name" );

			if ( isRouteGroupPresent )
			{
				genericHelperObj.clickDeleteOrUnDeleteAction( name, "Name", "Delete" );
				GenericHelper.waitForLoadmask();
				genericHelperObj.selectPartitionFilter( clientPartition, "Deleted Items" );
				assertTrue( GridHelper.isValuePresent( "SearchGrid", name, "Name" ), name );
				Log4jHelper.logInfo( "RouteGroup is deleted successfully : " + name );

			}
			else
			{
				Log4jHelper.logInfo( "RouteGroup is not available : " + name );
			}

		}
	}

	/*
	 * This method is for route group un delete
	 */
	public void routeGroupUnDelete() throws Exception
	{
		NavigationHelper.navigateToScreen( "Route Group" );
		for ( paramVal = 0; paramVal < colSize; paramVal++ )
		{

			routegrpMap = excelHolderObj.dataMap( paramVal );
			clientPartition = ExcelHolder.getKey( routegrpMap, "Partition" );
			name = ExcelHolder.getKey( routegrpMap, "Name" );

			genericHelperObj.selectPartitionFilter( clientPartition, "Deleted Items" );
			boolean isRouteGroupPresent = genericHelperObj.isGridTextValuePresent( "PS_Detail_routeGroup_name_txtId", name, "Name" );
			if ( isRouteGroupPresent )
			{
				genericHelperObj.clickDeleteOrUnDeleteAction( name, "Name", "Undelete" );
				GenericHelper.waitForLoadmask();
				genericHelperObj.selectPartitionFilter( clientPartition, "Non Deleted Items" );
				assertTrue( GridHelper.isValuePresent( "SearchGrid", name, "Name" ), name );
				Log4jHelper.logInfo( "RouteGroup is un deleted successfully : " + name );

			}
			else
			{
				Log4jHelper.logInfo( "RouteGroup is not available : " + name );
			}

		}
	}

}
