package com.subex.rocps.automation.helpers.application.referenceTable;

import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Map;

import com.subex.rocps.automation.helpers.application.genericHelpers.PSGenericHelper;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;
import com.subex.rocps.automation.utils.ExcelHolder;
import com.subex.rocps.automation.utils.PSStringUtils;

import com.subex.automation.helpers.application.NavigationHelper;
import com.subex.automation.helpers.component.ButtonHelper;
import com.subex.automation.helpers.component.ComboBoxHelper;
import com.subex.automation.helpers.component.ElementHelper;
import com.subex.automation.helpers.component.GenericHelper;
import com.subex.automation.helpers.component.GridHelper;
import com.subex.automation.helpers.component.TextBoxHelper;
import com.subex.automation.helpers.file.ExcelReader;
import com.subex.automation.helpers.report.Log4jHelper;
import com.subex.automation.helpers.util.FailureHelper;

public class DealTrafficTypeOrder extends PSAcceptanceTest
{
	protected ExcelReader excelData = null;
	protected Map<String, ArrayList<String>> dealtrafficExcel = null;
	protected Map<String, String> dealtrafficMap = null;
	protected ExcelHolder excelHolderObj = null;
	protected String path;
	protected String workBookName;
	protected String sheetName;
	protected String testCaseName;
	protected int paramVal;
	protected int colSize;
	protected String clientPartition;
	protected String tableName;
	protected String trafficType;
	protected String orderNumber;	
	protected PSGenericHelper genericHelperObj = new PSGenericHelper();

	/*
	 * Constructor for initializing excel Identifying the column size from the map
	 * passed
	 */
	@Test
	public DealTrafficTypeOrder(String path, String workBookName, String sheetName, String testCaseName) throws Exception {
		this.path = path;
		this.workBookName = workBookName;
		this.sheetName = sheetName;
		this.testCaseName = testCaseName;
		excelData = new ExcelReader();
		dealtrafficExcel = excelData.readDataByColumn(this.path, this.workBookName, this.sheetName, this.testCaseName);
		excelHolderObj = new ExcelHolder(dealtrafficExcel);
		colSize = excelHolderObj.totalColumns();
	}

	/*
	 * Overloaded constructor for reading data from excel if test case name appears
	 * more than once
	 * 
	 * @Param occurance : Test case instance in excel sheet Constructor for
	 * initializing excel Identifying the column size from the map
	 */
	public DealTrafficTypeOrder(String path, String workBookName, String sheetName, String testCaseName, int occurance)
			throws Exception {
		this.path = path;
		this.workBookName = workBookName;
		this.sheetName = sheetName;
		this.testCaseName = testCaseName;
		excelData = new ExcelReader();
		dealtrafficExcel = excelData.readDataByColumn(this.path, this.workBookName, this.sheetName, this.testCaseName,
				occurance);
		excelHolderObj = new ExcelHolder(dealtrafficExcel);
		colSize = excelHolderObj.totalColumns();
	}

	/*
	 * Configuring the deal traffic type order
	 * 
	 */

	public void dealTrafficTypeOrderCreation() throws Exception {
		try {
			NavigationHelper.navigateToReferenceTable("Deal Traffic Type Order");
			for (paramVal = 0; paramVal < colSize; paramVal++) {

				dealtrafficMap = excelHolderObj.dataMap(paramVal);
				initializeVariables(dealtrafficMap);
				//ButtonHelper.click( "ClearButton" );
				GenericHelper.waitForLoadmask(searchScreenWaitSec);
				boolean isTrafficTypePresent = GridHelper.isValuePresent("SearchGrid", trafficType, "Traffic Type");
				if (!isTrafficTypePresent) {
					newDealTrafficTypeOrder();
					GenericHelper.waitForLoadmask( searchScreenWaitSec );
					assertTrue(GridHelper.isValuePresent("SearchGrid", trafficType, "Traffic Type"),
							"Deal Traffic Type Order is not configured");
					Log4jHelper.logInfo("Deal Traffic Type Order is created successfully with name " + trafficType);
				} else {
					Log4jHelper.logInfo("Deal Traffic Type Order is available with name " + trafficType);
				}
			}

		} catch (Exception e) {

			FailureHelper.setErrorMessage(e);
			throw e;
		}

	}

	/*
	 * This method is for new deal traffic type order creation.
	 */

	protected void newDealTrafficTypeOrder() throws Exception {

		createNew();
		assertTrue( ElementHelper.isElementPresent( "//*[text()='Reference Table']" ) );
		TextBoxHelper.type("pdttOrderNo", orderNumber);
		ComboBoxHelper.select("trafficType_traName_gwt_uid_", trafficType);
		GenericHelper.waitForLoadmask( searchScreenWaitSec );	
		ElementHelper.click( "//*[@id='SparkEntitySearch']" );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );		
		saveDealTrafficType();

	}

	/*
	 * This Method is for creating new Deal Traffic Type Order
	 */
	protected void createNew() throws Exception {
		genericHelperObj.clickNewAction(clientPartition);
		GenericHelper.waitForLoadmask();

	}
	/*
	 * This method is to save Deal Traffic Type Order
	 */

	protected void saveDealTrafficType() throws Exception {
		Thread.sleep( 2000 );
		ElementHelper.waitForClickableElement( "//div[@id='SparkEntitySearch.window']//button[@id='ok']", searchScreenWaitSec );		
		//ButtonHelper.click("SparkEntitySearch.window", "OKButton");
		ElementHelper.click( "//div[@id='SparkEntitySearch.window']//button[@id='ok']" );
		GenericHelper.waitForLoadmask(searchScreenWaitSec);
		Thread.sleep( 2000 );
		ButtonHelper.click( "SearchButton" );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		//assertEquals(NavigationHelper.getScreenTitle(), "Reference Table Search");
	}

	/*
	 * This method is to initialize instance variable
	 */

	protected void initializeVariables(Map<String, String> map) throws Exception {
		
		trafficType = ExcelHolder.getKey(map, "TrafficType");
		orderNumber = ExcelHolder.getKey(map, "OrderNumber");
		clientPartition = ExcelHolder.getKey(map, "Partition");
	}

	/*
	 * This method is to validate search screen columns
	 */
	public void searchScreenColumnsValidation() throws Exception
	{		
		NavigationHelper.navigateToReferenceTable("Deal Traffic Type Order");
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		for ( paramVal = 0; paramVal < colSize; paramVal++ )
		{
			dealtrafficMap = excelHolderObj.dataMap( paramVal );
			String searchScreenColumns = ExcelHolder.getKey( dealtrafficMap, "SearchScreenColumns" );
			ArrayList<String> excelColumnNames = new ArrayList<String>();
			PSStringUtils strObj = new PSStringUtils();
			String[] searchGridColumnsArr = strObj.stringSplitFirstLevel( searchScreenColumns );
			for ( int col = 0; col < searchGridColumnsArr.length; col++ )
			{
				excelColumnNames.add( searchGridColumnsArr[col] );
			}
			genericHelperObj.totalColumns( excelColumnNames );
		}

	}
	/*
	 * This method is to delete Deal Traffic Type Order
	 */
	public void dealTrafficTypeDelete() throws Exception {

		NavigationHelper.navigateToReferenceTable("Sales Tax");
		for (paramVal = 0; paramVal < colSize; paramVal++) {

			dealtrafficMap = excelHolderObj.dataMap(paramVal);

			clientPartition = ExcelHolder.getKey(dealtrafficMap, "Partition");
			trafficType = ExcelHolder.getKey(dealtrafficMap, "TrafficType");

			genericHelperObj.selectPartitionFilter(clientPartition, "Non Deleted Items");
			boolean isSalesTaxPresent = GridHelper.isValuePresent("SearchGrid", trafficType, "Traffic Type");
			if (isSalesTaxPresent) {
				genericHelperObj.clickDeleteOrUnDeleteAction(trafficType, "Traffic Type", "Delete");
				GenericHelper.waitForLoadmask();
				genericHelperObj.selectPartitionFilter(clientPartition, "Deleted Items");
				assertTrue(GridHelper.isValuePresent("SearchGrid", trafficType, "Traffic Type"), trafficType);
				Log4jHelper.logInfo("Deal Traffic Type Order is deleted successfully : " + trafficType);

			} else {
				Log4jHelper.logInfo("Deal Traffic Type Order is not available : " + trafficType);
			}

		}
	}

	/*
	 * This method is to un delete Deal Traffic Type Order
	 */
	public void dealTrafficTypeUnDelete() throws Exception {

		NavigationHelper.navigateToReferenceTable("Sales Tax");
		for (paramVal = 0; paramVal < colSize; paramVal++) {

			dealtrafficMap = excelHolderObj.dataMap(paramVal);

			clientPartition = ExcelHolder.getKey(dealtrafficMap, "Partition");
			trafficType = ExcelHolder.getKey(dealtrafficMap, "TrafficType");

			genericHelperObj.selectPartitionFilter(clientPartition, "Deleted Items");
			GenericHelper.waitForLoadmask();
			boolean isSalesTaxPresent = GridHelper.isValuePresent("SearchGrid", trafficType, "Traffic Type");
			if (isSalesTaxPresent) {
				genericHelperObj.clickDeleteOrUnDeleteAction(trafficType, "Traffic Type", "Undelete");
				GenericHelper.waitForLoadmask();
				genericHelperObj.selectPartitionFilter(clientPartition, "Non Deleted Items");
				assertTrue(GridHelper.isValuePresent("SearchGrid", trafficType, "Traffic Type"), trafficType);
				Log4jHelper.logInfo("Deal Traffic Type Order is un deleted successfully : " + trafficType);

			} else {
				Log4jHelper.logInfo("Deal Traffic Type Order is not available : " + trafficType);
			}

		}
	}
}
