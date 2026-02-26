package com.subex.rocps.automation.helpers.application.bills;

import java.util.ArrayList;
import java.util.Map;

import net.bytebuddy.description.type.TypeDescription.Generic;

import org.testng.annotations.Test;

import com.subex.automation.helpers.application.NavigationHelper;
import com.subex.automation.helpers.component.ButtonHelper;
import com.subex.automation.helpers.component.GenericHelper;
import com.subex.automation.helpers.component.GridHelper;
import com.subex.automation.helpers.file.ExcelReader;
import com.subex.automation.helpers.report.Log4jHelper;
import com.subex.automation.helpers.util.FailureHelper;
import com.subex.rocps.automation.helpers.application.bills.billbreakdown.BillBreakdownInputGroupImpl;
import com.subex.rocps.automation.helpers.application.genericHelpers.PSGenericHelper;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;
import com.subex.rocps.automation.utils.ExcelHolder;
import com.subex.rocps.automation.utils.PSStringUtils;

public class BillBreakdownInputGroup extends PSAcceptanceTest {

	protected ExcelReader excelData = null;
	protected Map<String, ArrayList<String>> billinputGrpExcel = null;
	protected Map<String, String> billinputGrpMap = null;
	protected ExcelHolder excelHolderObj = null;
	protected String path;
	protected String workBookName;
	protected String sheetName;
	protected String testCaseName;
	protected int paramVal;
	protected int colSize;
	protected String billInputGrpName;
	protected String clientPartition;
	protected String billInputName;
	protected PSGenericHelper genericHelperObj = new PSGenericHelper();
	BillBreakdownInputGroupImpl bilInputGrpImplObj = new BillBreakdownInputGroupImpl();

	/*
	 * Constructor for initializing excel Identifying the column size from the map
	 * passed
	 */
	@Test
	public BillBreakdownInputGroup(String path, String workBookName, String sheetName, String testCaseName)
			throws Exception {
		this.path = path;
		this.workBookName = workBookName;
		this.sheetName = sheetName;
		this.testCaseName = testCaseName;
		excelData = new ExcelReader();
		billinputGrpExcel = excelData.readDataByColumn(this.path, this.workBookName, this.sheetName, this.testCaseName);
		excelHolderObj = new ExcelHolder(billinputGrpExcel);
		colSize = excelHolderObj.totalColumns();
	}

	/*
	 * Overloaded constructor for reading data from excel if test case name appears
	 * more than once
	 * 
	 * @Param occurance : Test case instance in excel sheet Constructor for
	 * initializing excel Identifying the column size from the map
	 */
	public BillBreakdownInputGroup(String path, String workBookName, String sheetName, String testCaseName,
			int occurance) throws Exception {
		this.path = path;
		this.workBookName = workBookName;
		this.sheetName = sheetName;
		this.testCaseName = testCaseName;
		excelData = new ExcelReader();
		billinputGrpExcel = excelData.readDataByColumn(this.path, this.workBookName, this.sheetName, this.testCaseName,
				occurance);
		excelHolderObj = new ExcelHolder(billinputGrpExcel);
		colSize = excelHolderObj.totalColumns();
	}

	/*
	 * Configuring the Bill Breakdown Input Group
	 * 
	 * @method : isBillInputGrpPresent returns false then Bill breakdown Input Group
	 * is configured isBillInputGrpPresent returns true then Bill breakdown Input
	 * Group is not configured
	 */
	public void billInputGrpCreation() throws Exception {
		try {
			NavigationHelper.navigateToScreen("Bill Breakdown Input Group");
			for (paramVal = 0; paramVal < colSize; paramVal++) {
				billinputGrpMap = excelHolderObj.dataMap(paramVal);

				initializeVariables(billinputGrpMap);
				ButtonHelper.click( "ClearButton" );
				GenericHelper.waitForLoadmask(searchScreenWaitSec);
				boolean isBillInputGrpPresent = genericHelperObj
						.isGridTextValuePresent("PS_Detail_billInputGrp_Name_txtID", billInputGrpName, "Name");
				GenericHelper.waitForLoadmask();

				if (!isBillInputGrpPresent) {
					assertEquals(NavigationHelper.getScreenTitle(),"Bill Breakdown Inpu...");
					newBillbreakdownInputGroup();
					/*assertTrue(GridHelper.isValuePresent("Detail_eventDefn_gridID", billInputGrpName, "Name"),
							"Bill Breakdown Input Group is not configured");*/
					Log4jHelper.logInfo(
							"Bill Breakdown Input Group is created successfully with name " + billInputGrpName);
				} else {
					Log4jHelper.logInfo("Bill Breakdown Input Group is available with name " + billInputGrpName);
				}
			}
		} catch (Exception e) {

			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}

	/*
	 * This method is to add Bill Breakdown Input
	 */
	protected void newBillbreakdownInputGroup() throws Exception {

		bilInputGrpImplObj.createNewbillBreakdownInputGrp(clientPartition);
		bilInputGrpImplObj.addBillbreakdownInput(billInputGrpName, billInputName);
		bilInputGrpImplObj.billbreakdownConfigSave(billInputGrpName);
	}

	/*
	 * This method is to initialize instance variables
	 */
	public void initializeVariables(Map<String, String> map) throws Exception {

		clientPartition = ExcelHolder.getKey(map, "Partition");
		billInputGrpName = ExcelHolder.getKey(map, "BillInputGroupName");
		billInputName = ExcelHolder.getKey(map, "BillInputName");

	}
	
	public void editBillInputGrpCreation() throws Exception {
		try {
			NavigationHelper.navigateToScreen("Bill Breakdown Input Group");
			for (paramVal = 0; paramVal < colSize; paramVal++) {
				billinputGrpMap = excelHolderObj.dataMap(paramVal);

				initializeVariables(billinputGrpMap);
				ButtonHelper.click( "ClearButton" );
				GenericHelper.waitForLoadmask(searchScreenWaitSec);
				boolean isBillInputGrpPresent = genericHelperObj
						.isGridTextValuePresent("PS_Detail_billInputGrp_Name_txtID", billInputGrpName, "Name");
				GenericHelper.waitForLoadmask();

				if (isBillInputGrpPresent) {
					int row = GridHelper.getRowNumber( "SearchGrid", billInputGrpName, "Name" );
					NavigationHelper.navigateToEdit( "SearchGrid", row );
					assertEquals(NavigationHelper.getScreenTitle(),"Edit Bill Breakdown Input Group");
					bilInputGrpImplObj.addBillbreakdownInput(billInputGrpName, billInputName);
					bilInputGrpImplObj.billbreakdownConfigSave(billInputGrpName);					
					Log4jHelper.logInfo("Bill Breakdown Input Group is edit successfully with name " + billInputGrpName);
				} else {
					Log4jHelper.logInfo("Bill Breakdown Input Group is available with name " + billInputGrpName);
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
		NavigationHelper.navigateToScreen( "Bill Breakdown Input Group" );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		for ( paramVal = 0; paramVal < colSize; paramVal++ )
		{
			billinputGrpMap = excelHolderObj.dataMap( paramVal );
			String searchScreenColumns = ExcelHolder.getKey( billinputGrpMap, "SearchScreenColumns" );
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
	 * This method is for bill breakdown Input Group deletion
	 */
	public void billbreakdownInputDelete() throws Exception {

		NavigationHelper.navigateToScreen("Bill Breakdown Input Group");
		for (paramVal = 0; paramVal < colSize; paramVal++) {
			billinputGrpMap = excelHolderObj.dataMap(paramVal);

			clientPartition = ExcelHolder.getKey(billinputGrpMap, "Partition");
			billInputGrpName = ExcelHolder.getKey(billinputGrpMap, "BillInputGroupName");

			genericHelperObj.selectPartitionFilter(clientPartition, "Non Deleted Items");

			boolean isBillInputGrpPresent = genericHelperObj.isGridTextValuePresent("PS_Detail_billInputGrp_Name_txtID",
					billInputGrpName, "Name");

			if (isBillInputGrpPresent) {
				bilInputGrpImplObj.clickDeleteAction(billInputGrpName);
				genericHelperObj.selectPartitionFilter(clientPartition, "Deleted Items");
				assertTrue(GridHelper.isValuePresent("SearchGrid", billInputGrpName, "Name"), billInputGrpName);
				Log4jHelper.logInfo("bill breakdown Input Group is deleted successfully :" + billInputGrpName);

			} else {
				Log4jHelper.logInfo("bill breakdown Input Group is not available with :" + billInputGrpName);
			}

		}
	}

	/*
	 * This method is for bill breakdownInput Group un delete
	 */
	public void billbreakdownInputUnDelete() throws Exception {

		NavigationHelper.navigateToScreen("Bill Breakdown Input Group");
		for (paramVal = 0; paramVal < colSize; paramVal++) {
			billinputGrpMap = excelHolderObj.dataMap(paramVal);

			clientPartition = ExcelHolder.getKey(billinputGrpMap, "Partition");
			billInputGrpName = ExcelHolder.getKey(billinputGrpMap, "BillInputGroupName");
			genericHelperObj.selectPartitionFilter(clientPartition, "Deleted Items");

			boolean isBillInputGrpPresent = genericHelperObj.isGridTextValuePresent("PS_Detail_billInputGrp_Name_txtID",
					billInputGrpName, "Name");

			if (isBillInputGrpPresent) {
				bilInputGrpImplObj.clickUnDeleteAction(billInputGrpName);
				genericHelperObj.selectPartitionFilter(clientPartition, "Non Deleted Items");
				assertTrue(GridHelper.isValuePresent("SearchGrid", billInputGrpName, "Name"), billInputGrpName);
				Log4jHelper.logInfo("bill breakdown Input Group is un deleted successfully :" + billInputGrpName);

			} else {
				Log4jHelper.logInfo("bill breakdown Input Group is not available with :" + billInputGrpName);
			}

		}
	}
}
