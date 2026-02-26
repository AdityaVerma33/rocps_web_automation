package com.subex.rocps.automation.helpers.application.bills;

import java.util.ArrayList;
import java.util.Map;

import org.testng.annotations.Test;

import com.subex.automation.helpers.application.NavigationHelper;
import com.subex.automation.helpers.component.ButtonHelper;
import com.subex.automation.helpers.component.GenericHelper;
import com.subex.automation.helpers.component.GridHelper;
import com.subex.automation.helpers.file.ExcelReader;
import com.subex.automation.helpers.report.Log4jHelper;
import com.subex.automation.helpers.util.FailureHelper;
import com.subex.rocps.automation.helpers.application.bills.billbreakdown.BillBreakdownExtraFieldImpl;
import com.subex.rocps.automation.helpers.application.genericHelpers.PSGenericHelper;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;
import com.subex.rocps.automation.utils.ExcelHolder;
import com.subex.rocps.automation.utils.PSStringUtils;

public class BillBreakdownExtraField extends PSAcceptanceTest {

	protected ExcelReader excelData = null;
	protected Map<String, ArrayList<String>> billExtraExcel = null;
	protected Map<String, String> billExtraMap = null;
	protected ExcelHolder excelHolderObj = null;
	protected String path;
	protected String workBookName;
	protected String sheetName;
	protected String testCaseName;
	protected int paramVal;
	protected int colSize;
	protected String clientPartition;
	protected String component;
	protected String displayName;
	protected String name;
	protected PSGenericHelper genericHelperObj = new PSGenericHelper();
	BillBreakdownExtraFieldImpl billextraObj = new BillBreakdownExtraFieldImpl();

	/*
	 * Constructor for initializing excel Identifying the column size from the map
	 * passed
	 */
	@Test
	public BillBreakdownExtraField(String path, String workBookName, String sheetName, String testCaseName)
			throws Exception {
		this.path = path;
		this.workBookName = workBookName;
		this.sheetName = sheetName;
		this.testCaseName = testCaseName;
		excelData = new ExcelReader();
		billExtraExcel = excelData.readDataByColumn(this.path, this.workBookName, this.sheetName, this.testCaseName);
		excelHolderObj = new ExcelHolder(billExtraExcel);
		colSize = excelHolderObj.totalColumns();
	}

	/*
	 * Overloaded constructor for reading data from excel if test case name appears
	 * more than once
	 * 
	 * @Param occurance : Test case instance in excel sheet Constructor for
	 * initializing excel Identifying the column size from the map
	 */
	public BillBreakdownExtraField(String path, String workBookName, String sheetName, String testCaseName,
			int occurance) throws Exception {
		this.path = path;
		this.workBookName = workBookName;
		this.sheetName = sheetName;
		this.testCaseName = testCaseName;
		excelData = new ExcelReader();
		billExtraExcel = excelData.readDataByColumn(this.path, this.workBookName, this.sheetName, this.testCaseName,
				occurance);
		excelHolderObj = new ExcelHolder(billExtraExcel);
		colSize = excelHolderObj.totalColumns();
	}

	/*
	 * Configuring the Bill Breakdown Extra Field
	 * 
	 * @method : isBillExtraFieldPresent returns false then Bill breakdown Extra
	 * field is configured isBillExtraFieldPresent returns true then Bill breakdown
	 * Extra field is not configured
	 */
	public void billExtraFieldCreation() throws Exception {
		try {
			NavigationHelper.navigateToScreen("Bill Breakdown Extra Field");
			for (paramVal = 0; paramVal < colSize; paramVal++) {
				billExtraMap = excelHolderObj.dataMap(paramVal);

				initializeVariables(billExtraMap);
				ButtonHelper.click( "ClearButton" );
				GenericHelper.waitForLoadmask(searchScreenWaitSec);
				boolean isBillExtraFieldPresent = genericHelperObj.isGridTextValuePresent("_grid_header_filter",
						displayName, "Display Name");

				if (!isBillExtraFieldPresent) {
					assertEquals(NavigationHelper.getScreenTitle(), "Bill Breakdown Extr...");
					newbillBreakdownExtraField();					
					Log4jHelper.logInfo("Bill Breakdown Extra Filed is created successfully with name " + displayName);
				} else {
					Log4jHelper.logInfo("Bill Breakdown Extra Field is available with name " + displayName);

				}
			}
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}

	/*
	 * This method is to initialize instance variables
	 */
	public void initializeVariables(Map<String, String> map) throws Exception {

		clientPartition = ExcelHolder.getKey(map, "Partition");
		component = ExcelHolder.getKey(map, "Component");
		name = ExcelHolder.getKey(map, "Name");
		displayName = ExcelHolder.getKey(map, "DisplayName");
	}

	/*
	 * This method is to create new Bill Breakdown ExtraField
	 */
	protected void newbillBreakdownExtraField() throws Exception {

		billextraObj.createNewbillBreaskdownConfig(clientPartition);
		billextraObj.addBillbreakdownExtraField(component, displayName);
		billextraObj.saveBillBreakdownExtraField(displayName);
	}
	
	/*
	 * This method is to edit bill breakdown extra field 
	 */
	public void editBillExtraFieldCreation() throws Exception {
		try {
			NavigationHelper.navigateToScreen("Bill Breakdown Extra Field");
			for (paramVal = 0; paramVal < colSize; paramVal++) {
				billExtraMap = excelHolderObj.dataMap(paramVal);

				initializeVariables(billExtraMap);
				ButtonHelper.click( "ClearButton" );
				GenericHelper.waitForLoadmask(searchScreenWaitSec);
				boolean isBillExtraFieldPresent = genericHelperObj.isGridTextValuePresent("_grid_header_filter",
						displayName, "Display Name");

				if (isBillExtraFieldPresent) {
					int row = GridHelper.getRowNumber( "SearchGrid", displayName, "Display Name" );
					NavigationHelper.navigateToEdit( "SearchGrid", row );
					assertEquals( NavigationHelper.getScreenTitle(), "Edit Bill Breakdown Extra Field" );
					billextraObj.editBillbreakdownExtraField(component, displayName,name);
					genericHelperObj.detailSave( "PS_Detail_billBreakdownExtraFiled_savebtn", displayName, "Display Name" );					
					Log4jHelper.logInfo("Bill Breakdown Extra Filed is created successfully with name " + displayName);
				} else {
					Log4jHelper.logInfo("Bill Breakdown Extra Field is not available with name " + displayName);

				}
			}
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	/*
	 * This method is to validate search screen columns
	 */
	public void searchScreenColumnsValidation() throws Exception
	{		
		NavigationHelper.navigateToScreen( "Bill Breakdown Extra Field" );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		for ( paramVal = 0; paramVal < colSize; paramVal++ )
		{
			billExtraMap = excelHolderObj.dataMap( paramVal );
			String searchScreenColumns = ExcelHolder.getKey( billExtraMap, "SearchScreenColumns" );
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
	 * This method is for bill breakdown Extra field deletion
	 */
	public void billbreakdownExtraFieldDelete() throws Exception {

		NavigationHelper.navigateToScreen("Bill Breakdown Extra Field");
		for (paramVal = 0; paramVal < colSize; paramVal++) {
			billExtraMap = excelHolderObj.dataMap(paramVal);

			displayName = ExcelHolder.getKey(billExtraMap, "DisplayName");
			clientPartition = ExcelHolder.getKey(billExtraMap, "Partition");

			genericHelperObj.selectPartitionFilter(clientPartition, "Non Deleted Items");

			boolean isBillExtraFieldPresent = genericHelperObj.isGridTextValuePresent("_grid_header_filter",
					displayName, "Display Name");

			if (isBillExtraFieldPresent) {
				billextraObj.clickDeleteAction(displayName);
				genericHelperObj.selectPartitionFilter(clientPartition, "Deleted Items");
				assertTrue(GridHelper.isValuePresent("SearchGrid", displayName, "Display Name"), displayName);
				Log4jHelper.logInfo("bill breakdown Extra filed is deleted successfully :" + displayName);

			} else {
				Log4jHelper.logInfo("bill breakdown Extra filed is not available with :" + displayName);
			}

		}
	}

	/*
	 * This method is for billbreakdownExtraField un delete
	 */
	public void billbreakdownExtraFieldUnDelete() throws Exception {

		NavigationHelper.navigateToScreen("Bill Breakdown Extra Field");
		for (paramVal = 0; paramVal < colSize; paramVal++) {
			billExtraMap = excelHolderObj.dataMap(paramVal);

			displayName = ExcelHolder.getKey(billExtraMap, "DisplayName");
			clientPartition = ExcelHolder.getKey(billExtraMap, "Partition");
			genericHelperObj.selectPartitionFilter(clientPartition, "Deleted Items");

			boolean isBillExtraFieldPresent = genericHelperObj.isGridTextValuePresent("_grid_header_filter",
					displayName, "Display Name");

			if (isBillExtraFieldPresent) {
				billextraObj.clickUnDeleteAction(displayName);
				genericHelperObj.selectPartitionFilter(clientPartition, "Non Deleted Items");
				assertTrue(GridHelper.isValuePresent("SearchGrid", displayName, "Display Name"), displayName);
				Log4jHelper.logInfo("bill breakdown Extra filed is un deleted successfully :" + displayName);

			} else {
				Log4jHelper.logInfo("bill breakdown Extra filed is not available with :" + displayName);
			}

		}
	}
}
