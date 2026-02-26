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
import com.subex.rocps.automation.helpers.application.bills.billbreakdown.BillBreakdownInputImpl;
import com.subex.rocps.automation.helpers.application.genericHelpers.PSGenericHelper;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;
import com.subex.rocps.automation.utils.ExcelHolder;
import com.subex.rocps.automation.utils.PSStringUtils;

public class BillBreakDownInput extends PSAcceptanceTest {

	protected ExcelReader excelData = null;
	protected Map<String, ArrayList<String>> billinputExcel = null;
	protected Map<String, String> billinputMap = null;
	protected ExcelHolder excelHolderObj = null;
	protected String path;
	protected String workBookName;
	protected String sheetName;
	protected String testCaseName;
	protected int paramVal;
	protected int colSize;
	protected String billInputName;
	protected String clientPartition;
	protected String billDP;
	protected String billTable;
	protected String billComp;
	protected String billQueryComp;
	protected String billTableNonUsage;
	protected PSGenericHelper genericHelperObj = new PSGenericHelper();
	BillBreakdownInputImpl billInputImplObj = new BillBreakdownInputImpl();

	/*
	 * Constructor for initializing excel Identifying the column size from the map
	 * passed
	 */
	public BillBreakDownInput(String path, String workBookName, String sheetName, String testCaseName)
			throws Exception {
		this.path = path;
		this.workBookName = workBookName;
		this.sheetName = sheetName;
		this.testCaseName = testCaseName;
		excelData = new ExcelReader();
		billinputExcel = excelData.readDataByColumn(this.path, this.workBookName, this.sheetName, this.testCaseName);
		excelHolderObj = new ExcelHolder(billinputExcel);
		colSize = excelHolderObj.totalColumns();
	}

	/*
	 * Overloaded constructor for reading data from excel if test case name appears
	 * more than once
	 * 
	 * @Param occurance : Test case instance in excel sheet Constructor for
	 * initializing excel Identifying the column size from the map
	 */
	public BillBreakDownInput(String path, String workBookName, String sheetName, String testCaseName, int occurance)
			throws Exception {
		this.path = path;
		this.workBookName = workBookName;
		this.sheetName = sheetName;
		this.testCaseName = testCaseName;
		excelData = new ExcelReader();
		billinputExcel = excelData.readDataByColumn(this.path, this.workBookName, this.sheetName, this.testCaseName,
				occurance);
		excelHolderObj = new ExcelHolder(billinputExcel);
		colSize = excelHolderObj.totalColumns();
	}

	/*
	 * Configuring the Bill Breakdown Input
	 * 
	 * @method : isBillInputPresent returns false then Bill Breakdown Input is
	 * configured isBillInputPresent returns true then Bill Breakdown Input is not
	 * configured
	 */
	public void billInputCreation() throws Exception {
		try {
			NavigationHelper.navigateToScreen("Bill Breakdown Input");
			for (paramVal = 0; paramVal < colSize; paramVal++) {

				billinputMap = excelHolderObj.dataMap(paramVal);

				initializeVariables(billinputMap);
				ButtonHelper.click( "ClearButton" );
				GenericHelper.waitForLoadmask(searchScreenWaitSec);
				boolean isBillInputPresent = genericHelperObj.isGridTextValuePresent("PS_Detail_billInput_name_txtID",
						billInputName, "Name");

				if (!isBillInputPresent) {
					assertEquals(NavigationHelper.getScreenTitle(), "Bill Breakdown Inpu...");
					newBillBreakdownInput();
					/*assertTrue(GridHelper.isValuePresent("Detail_eventDefn_gridID", billInputName, "Name"),
							"Bill Breakdown Input is not configured");*/
					Log4jHelper.logInfo("Bill Breakdown Input is created successfully with name " + billInputName);
				} else {
					Log4jHelper.logInfo("Bill Breakdown Input is available with name " + billInputName);
				}
			}
		} catch (Exception e) {

			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}

	/*
	 * This method is to create new Bill Breakdown Input
	 */
	protected void newBillBreakdownInput() throws Exception {

		billInputImplObj.createNewbillBreakdownInput(clientPartition);
		billInputImplObj.detailConfig(billInputName, billDP, billTable, billComp, billQueryComp);
		billInputImplObj.nonusageBillInputConfig(billTableNonUsage);
		billInputImplObj.billBreakdownInputSave( billInputName);

	}

	/*
	 * This method is to initialize instance variables
	 */
	public void initializeVariables(Map<String, String> map) throws Exception {

		clientPartition = ExcelHolder.getKey(map, "Partition");
		billInputName = ExcelHolder.getKey(map, "BillInputName");
		billDP = ExcelHolder.getKey(map, "BillBreakdownDP");
		billTable = ExcelHolder.getKey(map, "BillBreakdownTable");
		billComp = ExcelHolder.getKey(map, "BillComponent");
		billQueryComp = ExcelHolder.getKey(map, "BillQueryComponent");
		billTableNonUsage = ExcelHolder.getKey(map, "BillTableNonUsage");
	}
	
	public void editBillInput() throws Exception {
		try {
			NavigationHelper.navigateToScreen("Bill Breakdown Input");
			for (paramVal = 0; paramVal < colSize; paramVal++) {

				billinputMap = excelHolderObj.dataMap(paramVal);

				initializeVariables(billinputMap);
				ButtonHelper.click( "ClearButton" );
				GenericHelper.waitForLoadmask(searchScreenWaitSec);
				boolean isBillInputPresent = genericHelperObj.isGridTextValuePresent("PS_Detail_billInput_name_txtID",
						billInputName, "Name");

				if (isBillInputPresent) {
					genericHelperObj.waitforHeaderElement( "Name" );
					int row = GridHelper.getRowNumber( "SearchGrid", billInputName, "Name" );
					NavigationHelper.navigateToEdit( "SearchGrid", row);
					assertEquals(NavigationHelper.getScreenTitle(), "Edit Bill Breakdown Input");
					billInputImplObj.ediDetailConfig(billInputName, billDP, billTable, billComp, billQueryComp);
					billInputImplObj.nonusageBillInputConfig(billTableNonUsage);
					billInputImplObj.billBreakdownInputSave( billInputName);					
					Log4jHelper.logInfo("Bill Breakdown Input is edited successfully with name " + billInputName);
				} else {
					Log4jHelper.logInfo("Bill Breakdown Input is available with name " + billInputName);
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
		NavigationHelper.navigateToScreen( "Bill Breakdown Input" );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		for ( paramVal = 0; paramVal < colSize; paramVal++ )
		{
			billinputMap = excelHolderObj.dataMap( paramVal );
			String searchScreenColumns = ExcelHolder.getKey( billinputMap, "SearchScreenColumns" );
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
	 * This method is for bill breakdown Input deletion
	 */
	public void billbreakdownInputDelete() throws Exception {

		NavigationHelper.navigateToScreen("Bill Breakdown Input");
		for (paramVal = 0; paramVal < colSize; paramVal++) {

			billinputMap = excelHolderObj.dataMap(paramVal);

			clientPartition = ExcelHolder.getKey(billinputMap, "Partition");
			billInputName = ExcelHolder.getKey(billinputMap, "BillInputName");

			genericHelperObj.selectPartitionFilter(clientPartition, "Non Deleted Items");

			boolean isBillInputPresent = genericHelperObj.isGridTextValuePresent("PS_Detail_billInput_name_txtID",
					billInputName, "Name");

			if (isBillInputPresent) {
				billInputImplObj.clickDeleteAction(billInputName);
				genericHelperObj.selectPartitionFilter(clientPartition, "Deleted Items");
				assertTrue(GridHelper.isValuePresent("SearchGrid", billInputName, "Name"), billInputName);
				Log4jHelper.logInfo("bill breakdown Input is deleted successfully :" + billInputName);

			} else {
				Log4jHelper.logInfo("bill breakdown Input is not available with :" + billInputName);
			}

		}
	}

	/*
	 * This method is for billbreakdown Input un delete
	 */
	public void billbreakdownInputUnDelete() throws Exception {

		NavigationHelper.navigateToScreen("Bill Breakdown Input");
		for (paramVal = 0; paramVal < colSize; paramVal++) {

			billinputMap = excelHolderObj.dataMap(paramVal);

			clientPartition = ExcelHolder.getKey(billinputMap, "Partition");
			billInputName = ExcelHolder.getKey(billinputMap, "BillInputName");
			genericHelperObj.selectPartitionFilter(clientPartition, "Deleted Items");

			boolean isBillInputPresent = genericHelperObj.isGridTextValuePresent("PS_Detail_billInput_name_txtID",
					billInputName, "Name");

			if (isBillInputPresent) {
				billInputImplObj.clickUnDeleteAction(billInputName);
				genericHelperObj.selectPartitionFilter(clientPartition, "Non Deleted Items");
				assertTrue(GridHelper.isValuePresent("SearchGrid", billInputName, "Name"), billInputName);
				Log4jHelper.logInfo("bill breakdown Input is un deleted successfully :" + billInputName);

			} else {
				Log4jHelper.logInfo("bill breakdown Input is not available with :" + billInputName);
			}

		}
	}

}
