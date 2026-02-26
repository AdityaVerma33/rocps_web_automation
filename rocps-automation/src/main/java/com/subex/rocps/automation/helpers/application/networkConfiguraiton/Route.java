package com.subex.rocps.automation.helpers.application.networkConfiguraiton;

import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Map;

import com.subex.rocps.automation.helpers.application.genericHelpers.PSGenericHelper;
import com.subex.rocps.automation.helpers.application.networkConfiguraiton.route.RouteDetailImpl;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;
import com.subex.rocps.automation.utils.ExcelHolder;
import com.subex.rocps.automation.utils.PSStringUtils;

import com.subex.automation.helpers.application.NavigationHelper;
import com.subex.automation.helpers.component.ButtonHelper;
import com.subex.automation.helpers.component.GenericHelper;
import com.subex.automation.helpers.component.GridHelper;
import com.subex.automation.helpers.file.ExcelReader;
import com.subex.automation.helpers.report.Log4jHelper;
import com.subex.automation.helpers.util.FailureHelper;

public class Route extends PSAcceptanceTest
{

	ExcelReader excelData = null;
	Map<String, ArrayList<String>> routeExcel = null;
	Map<String, String> routeMap = null;
	ExcelHolder excelHolderObj = null;
	protected String path;
	protected String workBookName;
	protected String sheetName;
	protected String testCaseName;
	protected int paramVal;
	protected int colSize;
	protected String clientPartition;
	protected String name;
	protected String routeGrp;

	protected PSGenericHelper genericHelperObj = new PSGenericHelper();
	PSStringUtils strObj = new PSStringUtils();

	/*
	 * Constructor for initializing excel Identifying the column size from the map
	 * passed
	 */
	@Test
	public Route( String path, String workBookName, String sheetName, String testCaseName ) throws Exception
	{
		this.path = path;
		this.workBookName = workBookName;
		this.sheetName = sheetName;
		this.testCaseName = testCaseName;
		excelData = new ExcelReader();
		routeExcel = excelData.readDataByColumn( this.path, this.workBookName, this.sheetName, this.testCaseName );
		excelHolderObj = new ExcelHolder( routeExcel );
		colSize = excelHolderObj.totalColumns();
	}

	/*
	 * Overloaded constructor for reading data from excel if test case name appears
	 * more than once
	 * 
	 * @Param occurance : Test case instance in excel sheet Constructor for
	 * initializing excel Identifying the column size from the map
	 */
	public Route( String path, String workBookName, String sheetName, String testCaseName, int occurance ) throws Exception
	{
		this.path = path;
		this.workBookName = workBookName;
		this.sheetName = sheetName;
		this.testCaseName = testCaseName;
		excelData = new ExcelReader();
		routeExcel = excelData.readDataByColumn( this.path, this.workBookName, this.sheetName, this.testCaseName, occurance );
		excelHolderObj = new ExcelHolder( routeExcel );
		colSize = excelHolderObj.totalColumns();
	}

	/*
	 * Configuring the Route
	 * 
	 * @method : isRoutePresent returns false then Route is configured
	 * isRoutePresent returns true then Route is not configured
	 */
	public void routeCreation() throws Exception
	{
		try
		{
			NavigationHelper.navigateToScreen( "Route" );
			for ( paramVal = 0; paramVal < colSize; paramVal++ )
			{

				routeMap = excelHolderObj.dataMap( paramVal );
				initializeVariables();
				ButtonHelper.click( "ClearButton" );
				GenericHelper.waitForLoadmask( searchScreenWaitSec );
				boolean isRoutePresent = genericHelperObj.isGridTextValuePresent( "PS_Detail_Route_Name_txtID", name, "Name" );
				if ( !isRoutePresent )
				{
					genericHelperObj.clickNewAction( clientPartition );
					GenericHelper.waitForLoadmask();
					RouteDetailImpl detailObj = new RouteDetailImpl( routeMap );
					detailObj.basicDetailsConfig();
					detailObj.elementAssociation();
					detailObj.routeRuleStringSet();
					detailObj.routingDetailsTab();
					genericHelperObj.detailSave( "PS_Detail_Route_Save_btn", name, "Name" );
					Log4jHelper.logInfo( "Route is created successfully with name " + name );

				}
				else
				{
					Log4jHelper.logInfo( "Route is available with name " + name );
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
	 * This method is to edit route
	 */
	public void editRoute() throws Exception
	{
		try
		{
			NavigationHelper.navigateToScreen( "Route" );
			for ( paramVal = 0; paramVal < colSize; paramVal++ )
			{

				routeMap = excelHolderObj.dataMap( paramVal );
				initializeVariables();
				ButtonHelper.click( "ClearButton" );
				GenericHelper.waitForLoadmask( searchScreenWaitSec );
				boolean isRoutePresent = genericHelperObj.isGridTextValuePresent( "PS_Detail_Route_Name_txtID", name, "Name" );
				if ( isRoutePresent )
				{
					int row = GridHelper.getRowNumber( "SearchGrid", name, "Name" );
					NavigationHelper.navigateToEdit( "SearchGrid", row );
					GenericHelper.waitForLoadmask( searchScreenWaitSec );
					RouteDetailImpl detailObj = new RouteDetailImpl( routeMap );
					detailObj.editBasicDetailsConfig();
					detailObj.elementAssociation();
					detailObj.routeRuleStringSet();
					detailObj.editRoutingDetailsTab();
					genericHelperObj.detailSave( "PS_Detail_Route_Save_btn", name, "Name" );
					Log4jHelper.logInfo( "Route is created successfully with name " + name );
				}
				else
					Log4jHelper.logInfo( "Route is not available with name " + name );

			}
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

	/*
	 * This method is to initialize instance variables
	 */
	protected void initializeVariables() throws Exception
	{

		clientPartition = routeMap.get( "Partition" );
		name = routeMap.get( "Name" );
		routeGrp = routeMap.get( "RouteGroup" );

	}

	/*
	 * This method is to validate search screen columns
	 */
	public void searchScreenColumnsValidation() throws Exception
	{
		NavigationHelper.navigateToScreen( "Route" );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		for ( paramVal = 0; paramVal < colSize; paramVal++ )
		{
			routeMap = excelHolderObj.dataMap( paramVal );
			String searchScreenColumns = ExcelHolder.getKey( routeMap, "SearchScreenColumns" );
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
	public void routeDelete() throws Exception
	{
		NavigationHelper.navigateToScreen( "Route" );
		for ( paramVal = 0; paramVal < colSize; paramVal++ )
		{

			routeMap = excelHolderObj.dataMap( paramVal );

			clientPartition = ExcelHolder.getKey( routeMap, "Partition" );
			name = ExcelHolder.getKey( routeMap, "Name" );

			genericHelperObj.selectPartitionFilter( clientPartition, "Non Deleted Items" );
			GenericHelper.waitForLoadmask();
			boolean isRoutePresent = genericHelperObj.isGridTextValuePresent( "PS_Detail_Route_Name_txtID", name, "Name" );
			if ( isRoutePresent )
			{
				genericHelperObj.clickDeleteOrUnDeleteAction( name, "Name", "Delete" );
				GenericHelper.waitForLoadmask();
				genericHelperObj.selectPartitionFilter( clientPartition, "Deleted Items" );
				assertTrue( GridHelper.isValuePresent( "SearchGrid", name, "Name" ), name );
				Log4jHelper.logInfo( "route '" + name + " ' is deleted successfully." );

			}
			else
			{
				Log4jHelper.logInfo( "route is not available : " + name );
			}
		}
	}

	/*
	 * This method is for route un delete
	 */
	public void routeUnDelete() throws Exception
	{
		NavigationHelper.navigateToScreen( "Route" );
		for ( paramVal = 0; paramVal < colSize; paramVal++ )
		{

			routeMap = excelHolderObj.dataMap( paramVal );
			clientPartition = ExcelHolder.getKey( routeMap, "Partition" );
			name = ExcelHolder.getKey( routeMap, "Name" );

			genericHelperObj.selectPartitionFilter( clientPartition, "Deleted Items" );
			boolean isRoutePresent = genericHelperObj.isGridTextValuePresent( "PS_Detail_Route_Name_txtID", name, "Name" );
			if ( isRoutePresent )
			{
				genericHelperObj.clickDeleteOrUnDeleteAction( name, "Name", "Undelete" );
				GenericHelper.waitForLoadmask();
				genericHelperObj.selectPartitionFilter( clientPartition, "Non Deleted Items" );
				assertTrue( GridHelper.isValuePresent( "SearchGrid", name, "Name" ), name );
				Log4jHelper.logInfo( "route '" + name + " ' is un deleted successfully." );

			}
			else
			{
				Log4jHelper.logInfo( "route is not available : " + name );
			}

		}
	}

	/*
	 * This method is for route Deactivate 
	 */
	public void routeDeActivateAction() throws Exception
	{
		NavigationHelper.navigateToScreen( "Route" );
		for ( paramVal = 0; paramVal < colSize; paramVal++ )
		{

			routeMap = excelHolderObj.dataMap( paramVal );
			clientPartition = ExcelHolder.getKey( routeMap, "Partition" );
			name = ExcelHolder.getKey( routeMap, "Name" );

			boolean isRoutePresent = GridHelper.isValuePresent( "SearchGrid", "Yes", "Active" );
			if ( isRoutePresent )
			{
				GridHelper.clickRow( "SearchGrid", name, "Name" );
				NavigationHelper.navigateToAction( "Route Actions", "Deactivate" );
				GenericHelper.waitForLoadmask();
				assertTrue( GridHelper.isValuePresent( "SearchGrid", "No", "Active" ) );
				Log4jHelper.logInfo( "route '" + name + " ' is Deactivated successfully." );
			}
			else
			{
				Log4jHelper.logInfo( "route is not available : " + name );
			}
		}
	}

	/*
	 * This method is for route Activate
	 */
	public void routeActivateAction() throws Exception
	{
		NavigationHelper.navigateToScreen( "Route" );
		for ( paramVal = 0; paramVal < colSize; paramVal++ )
		{

			routeMap = excelHolderObj.dataMap( paramVal );
			clientPartition = ExcelHolder.getKey( routeMap, "Partition" );
			name = ExcelHolder.getKey( routeMap, "Name" );

			boolean isRoutePresent = GridHelper.isValuePresent( "SearchGrid", "No", "Active" );
			if ( isRoutePresent )
			{
				GridHelper.clickRow( "SearchGrid", name, "Name" );
				NavigationHelper.navigateToAction( "Route Actions", "Activate" );
				GenericHelper.waitForLoadmask();
				assertTrue( GridHelper.isValuePresent( "SearchGrid", "Yes", "Active" ) );
				Log4jHelper.logInfo( "route '" + name + " ' is Activated successfully." );
			}
			else
			{
				Log4jHelper.logInfo( "route is not available : " + name );
			}
		}
	}

}
