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
import com.subex.rocps.automation.helpers.application.bills.billbreakdown.BillBreakdownOutputGroupImpl;
import com.subex.rocps.automation.helpers.application.genericHelpers.PSGenericHelper;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;
import com.subex.rocps.automation.utils.ExcelHolder;
import com.subex.rocps.automation.utils.PSStringUtils;

public class BillBreakdownOutputGroup extends PSAcceptanceTest {

	protected ExcelReader excelData = null;
	protected Map<String, ArrayList<String>> billOutputGrpExcel = null;
	protected Map<String, String> billOutputGrpMap = null;
	protected ExcelHolder excelHolderObj = null;
	protected String path;
	protected String workBookName;
	protected String sheetName;
	protected String testCaseName;
	protected int paramVal;
	protected int colSize;
	protected String billOutputGrpName;
	protected String clientPartition;
	protected String billOutputName;
	protected PSGenericHelper genericHelperObj = new PSGenericHelper();
	BillBreakdownOutputGroupImpl billOutputGrpImpObj = new BillBreakdownOutputGroupImpl();

	/*
	 * Constructor for initializing excel Identifying the column size from the map
	 * passed
	 */
	@Test
	public BillBreakdownOutputGroup(String path, String workBookName, String sheetName, String testCaseName)
			throws Exception {
		this.path = path;
		this.workBookName = workBookName;
		this.sheetName = sheetName;
		this.testCaseName = testCaseName;
		excelData = new ExcelReader();
		billOutputGrpExcel = excelData.readDataByColumn(this.path, this.workBookName, this.sheetName,
				this.testCaseName);
		excelHolderObj = new ExcelHolder(billOutputGrpExcel);
		colSize = excelHolderObj.totalColumns();
	}

	/*
	 * Overloaded constructor for reading data from excel if test case name appears
	 * more than once
	 * 
	 * @Param occurance : Test case instance in excel sheet Constructor for
	 * initializing excel Identifying the column size from the map
	 */
	public BillBreakdownOutputGroup(String path, String workBookName, String sheetName, String testCaseName,
			int occurance) throws Exception {
		this.path = path;
		this.workBookName = workBookName;
		this.sheetName = sheetName;
		this.testCaseName = testCaseName;
		excelData = new ExcelReader();
		billOutputGrpExcel = excelData.readDataByColumn(this.path, this.workBookName, this.sheetName, this.testCaseName,
				occurance);
		excelHolderObj = new ExcelHolder(billOutputGrpExcel);
		colSize = excelHolderObj.totalColumns();
	}

	/*
	 * Configuring the Bill Breakdown Output Group
	 * 
	 * @method : isBillOutputGrpPresent returns false then Bill breakdown Output
	 * Group is configured isBillOutputGrpPresent returns true then Bill breakdown
	 * Output Group is not configured
	 */
	public void billOutputGrpCreation() throws Exception {
		try {
			NavigationHelper.navigateToScreen("Bill Breakdown Output Group");
			for (paramVal = 0; paramVal < colSize; paramVal++) {
				billOutputGrpMap = excelHolderObj.dataMap(paramVal);

				initializeVariables(billOutputGrpMap);
				ButtonHelper.click( "ClearButton" );
				GenericHelper.waitForLoadmask(searchScreenWaitSec);
				boolean isBillOutputGrpPresent = genericHelperObj
						.isGridTextValuePresent("PS_Detail_billOutputGrp_name_txtID", billOutputGrpName, "Name");

				if (!isBillOutputGrpPresent) {
					assertEquals(NavigationHelper.getScreenTitle(), "Bill Breakdown Outp...");
					
					newBillbreakdownOutputGroup();					
					Log4jHelper.logInfo(
							"Bill Breakdown Output Group is created successfully with name " + billOutputGrpName);
				} else {
					Log4jHelper.logInfo("Bill Breakdown Output Group is available with name " + billOutputGrpName);
				}
			}
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}

	/*
	 * This method is to add Bill Breakdown Output Group
	 */
	protected void newBillbreakdownOutputGroup() throws Exception {

		billOutputGrpImpObj.createNewbillBreakdownConfig(clientPartition);
		billOutputGrpImpObj.addBillbreakdownOutput(billOutputGrpName, billOutputName);
		billOutputGrpImpObj.billbreakdownConfigSave(billOutputGrpName);
	}
	
	public void editBillOutputGrpCreation() throws Exception {
		try {
			NavigationHelper.navigateToScreen("Bill Breakdown Output Group");
			for (paramVal = 0; paramVal < colSize; paramVal++) {
				billOutputGrpMap = excelHolderObj.dataMap(paramVal);

				initializeVariables(billOutputGrpMap);
				ButtonHelper.click( "ClearButton" );
				GenericHelper.waitForLoadmask(searchScreenWaitSec);
				boolean isBillOutputGrpPresent = genericHelperObj
						.isGridTextValuePresent("PS_Detail_billOutputGrp_name_txtID", billOutputGrpName, "Name");

				if (isBillOutputGrpPresent) {
					int row = GridHelper.getRowNumber( "SearchGrid", billOutputGrpName, "Name" );
					NavigationHelper.navigateToEdit( "SearchGrid", row );
					assertEquals(NavigationHelper.getScreenTitle(), "Edit Bill Breakdown Output Group");
					
					billOutputGrpImpObj.addBillbreakdownOutput(billOutputGrpName, billOutputName);
					billOutputGrpImpObj.billbreakdownConfigSave(billOutputGrpName);					
					Log4jHelper.logInfo(
							"Bill Breakdown Output Group is edited successfully with name " + billOutputGrpName);
				} else {
					Log4jHelper.logInfo("Bill Breakdown Output Group is not available with name " + billOutputGrpName);
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
		billOutputGrpName = ExcelHolder.getKey(map, "BillOutputGroupName");
		billOutputName = ExcelHolder.getKey(map, "BillOutputName");
	}
	
	/*
	 * This method is to validate search screen columns
	 */
	public void searchScreenColumnsValidation() throws Exception
	{		
		NavigationHelper.navigateToScreen( "Bill Breakdown Output Group" );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		for ( paramVal = 0; paramVal < colSize; paramVal++ )
		{
			billOutputGrpMap = excelHolderObj.dataMap( paramVal );
			String searchScreenColumns = ExcelHolder.getKey( billOutputGrpMap, "SearchScreenColumns" );
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
	 * This method is for bill breakdown Output Group deletion
	 */
	public void billbreakdownOutputGrpDelete() throws Exception {
		NavigationHelper.navigateToScreen("Bill Breakdown Output Group");
		for (paramVal = 0; paramVal < colSize; paramVal++) {
			billOutputGrpMap = excelHolderObj.dataMap(paramVal);

			clientPartition = ExcelHolder.getKey(billOutputGrpMap, "Partition");
			billOutputGrpName = ExcelHolder.getKey(billOutputGrpMap, "BillOutputGroupName");

			genericHelperObj.selectPartitionFilter(clientPartition, "Non Deleted Items");

			boolean isBillOutputGrpPresent = genericHelperObj
					.isGridTextValuePresent("PS_Detail_billOutputGrp_name_txtID", billOutputGrpName, "Name");

			if (isBillOutputGrpPresent) {
				billOutputGrpImpObj.clickDeleteAction(billOutputGrpName);
				genericHelperObj.selectPartitionFilter(clientPartition, "Deleted Items");
				assertTrue(GridHelper.isValuePresent("SearchGrid", billOutputGrpName, "Name"), billOutputGrpName);
				Log4jHelper.logInfo("bill breakdown Output Group is deleted successfully :" + billOutputGrpName);

			} else {
				Log4jHelper.logInfo("bill breakdown Output Group is not available with :" + billOutputGrpName);
			}

		}
	}

	/*
	 * This method is for bill breakdown Output Group un delete
	 */
	public void billbreakdownOutputGrpUnDelete() throws Exception {

		NavigationHelper.navigateToScreen("Bill Breakdown Output Group");
		for (paramVal = 0; paramVal < colSize; paramVal++) {
			billOutputGrpMap = excelHolderObj.dataMap(paramVal);

			clientPartition = ExcelHolder.getKey(billOutputGrpMap, "Partition");
			billOutputGrpName = ExcelHolder.getKey(billOutputGrpMap, "BillOutputGroupName");
			genericHelperObj.selectPartitionFilter(clientPartition, "Deleted Items");

			boolean isBillOutputGrpPresent = genericHelperObj
					.isGridTextValuePresent("PS_Detail_billOutputGrp_name_txtID", billOutputGrpName, "Name");

			if (isBillOutputGrpPresent) {
				billOutputGrpImpObj.clickUnDeleteAction(billOutputGrpName);
				genericHelperObj.selectPartitionFilter(clientPartition, "Non Deleted Items");
				assertTrue(GridHelper.isValuePresent("SearchGrid", billOutputGrpName, "Name"), billOutputGrpName);
				Log4jHelper.logInfo("bill breakdown Output Group is un deleted successfully :" + billOutputGrpName);

			} else {
				Log4jHelper.logInfo("bill breakdown Output Group is not available with :" + billOutputGrpName);
			}

		}
	}
}
