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
import com.subex.rocps.automation.helpers.application.bills.billbreakdown.BillBreakdownOutputImpl;
import com.subex.rocps.automation.helpers.application.genericHelpers.PSGenericHelper;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;
import com.subex.rocps.automation.utils.ExcelHolder;
import com.subex.rocps.automation.utils.PSStringUtils;

public class BillBreakdownOutput extends PSAcceptanceTest {

	protected ExcelReader excelData = null;
	protected Map<String, ArrayList<String>> billoutputExcel = null;
	protected Map<String, String> billoutputMap = null;
	protected ExcelHolder excelHolderObj = null;
	protected String path;
	protected String workBookName;
	protected String sheetName;
	protected String testCaseName;
	protected int paramVal;
	protected int colSize;
	protected String billOutputName;
	protected PSGenericHelper genericHelperObj = new PSGenericHelper();

	/*
	 * Constructor for initializing excel Identifying the column size from the map
	 * passed
	 */
	@Test
	public BillBreakdownOutput(String path, String workBookName, String sheetName, String testCaseName)
			throws Exception {
		this.path = path;
		this.workBookName = workBookName;
		this.sheetName = sheetName;
		this.testCaseName = testCaseName;
		excelData = new ExcelReader();
		billoutputExcel = excelData.readDataByColumn(this.path, this.workBookName, this.sheetName, this.testCaseName);
		excelHolderObj = new ExcelHolder(billoutputExcel);
		colSize = excelHolderObj.totalColumns();
	}

	/*
	 * Overloaded constructor for reading data from excel if test case name appears
	 * more than once
	 * 
	 * @Param occurance : Test case instance in excel sheet Constructor for
	 * initializing excel Identifying the column size from the map
	 */
	public BillBreakdownOutput(String path, String workBookName, String sheetName, String testCaseName, int occurance)
			throws Exception {
		this.path = path;
		this.workBookName = workBookName;
		this.sheetName = sheetName;
		this.testCaseName = testCaseName;
		excelData = new ExcelReader();
		billoutputExcel = excelData.readDataByColumn(this.path, this.workBookName, this.sheetName, this.testCaseName,
				occurance);
		excelHolderObj = new ExcelHolder(billoutputExcel);
		colSize = excelHolderObj.totalColumns();
	}

	/*
	 * Configuring the Bill Breakdown Output
	 * 
	 * @method : isBillOutputPresent returns false then Bill Breakdown Output is
	 * configured isBillOutputPresent returns true then Bill Breakdown Output is not
	 * configured
	 */
	public void billOutputCreation() throws Exception {
		try {
			NavigationHelper.navigateToScreen("Bill Breakdown Output");
			for (paramVal = 0; paramVal < colSize; paramVal++) {

				billoutputMap = excelHolderObj.dataMap(paramVal);

				billOutputName = ExcelHolder.getKey(billoutputMap, "BillOutputName");
				ButtonHelper.click( "ClearButton" );
				GenericHelper.waitForLoadmask(searchScreenWaitSec);
				boolean isBillOutputPresent = genericHelperObj.isGridTextValuePresent("PS_Detail_billOutput_name_txtID",
						billOutputName, "Name");

				if (!isBillOutputPresent) {					
					
					assertEquals(NavigationHelper.getScreenTitle(), "Bill Breakdown Outp...");
					newbillBreakdownOutput();
					GenericHelper.waitForLoadmask();					
					Log4jHelper.logInfo("Bill Breakdown Output is created successfully with name " + billOutputName);
				} else {
					Log4jHelper.logInfo("Bill Breakdown Output is available with name " + billOutputName);
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
	protected void newbillBreakdownOutput() throws Exception {

		BillBreakdownOutputImpl billOutputImpl = new BillBreakdownOutputImpl(billoutputMap);
		billOutputImpl.createNewbillBreakdownConfig();
		billOutputImpl.detailconfig();
		billOutputImpl.billbreakdownConfigSave();
	}
	
	public void editBillBreakdownOutput() throws Exception {
		try {
			NavigationHelper.navigateToScreen("Bill Breakdown Output");
			for (paramVal = 0; paramVal < colSize; paramVal++) {

				billoutputMap = excelHolderObj.dataMap(paramVal);

				billOutputName = ExcelHolder.getKey(billoutputMap, "BillOutputName");
				ButtonHelper.click( "ClearButton" );
				GenericHelper.waitForLoadmask(searchScreenWaitSec);
				boolean isBillOutputPresent = genericHelperObj.isGridTextValuePresent("PS_Detail_billOutput_name_txtID",
						billOutputName, "Name");

				if (isBillOutputPresent) {					
					int row = GridHelper.getRowNumber( "SearchGrid", billOutputName , "Name");
					NavigationHelper.navigateToEdit( "SearchGrid", row );
					assertEquals(NavigationHelper.getScreenTitle(), "Edit Bill Breakdown Output");
					BillBreakdownOutputImpl billOutputImpl = new BillBreakdownOutputImpl(billoutputMap);
					billOutputImpl.editDetailconfig();
					billOutputImpl.billbreakdownConfigSave();
					GenericHelper.waitForLoadmask();					
					Log4jHelper.logInfo("Bill Breakdown Output is edited successfully with name " + billOutputName);
				} else {
					Log4jHelper.logInfo("Bill Breakdown Output is available with name " + billOutputName);
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
		NavigationHelper.navigateToScreen( "Bill Breakdown Output" );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		for ( paramVal = 0; paramVal < colSize; paramVal++ )
		{
			billoutputMap = excelHolderObj.dataMap( paramVal );
			String searchScreenColumns = ExcelHolder.getKey( billoutputMap, "SearchScreenColumns" );
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
	 * This method is for bill breakdown Output deletion
	 */
	public void billbreakdownOutputDelete() throws Exception {

		NavigationHelper.navigateToScreen("Bill Breakdown Output");
		for (paramVal = 0; paramVal < colSize; paramVal++) {

			billoutputMap = excelHolderObj.dataMap(paramVal);

			String clientPartition = ExcelHolder.getKey(billoutputMap, "Partition");
			billOutputName = ExcelHolder.getKey(billoutputMap, "BillOutputName");

			genericHelperObj.selectPartitionFilter(clientPartition, "Non Deleted Items");

			boolean isBillOutputPresent = genericHelperObj.isGridTextValuePresent("PS_Detail_billOutput_name_txtID",
					billOutputName, "Name");

			if (isBillOutputPresent) {
				//BillBreakdownOutputImpl billOutputImpl = new BillBreakdownOutputImpl(billoutputMap);
				//billOutputImpl.clickDeleteAction(billOutputName);
				genericHelperObj.clickDeleteOrUnDeleteAction(billOutputName, "Name", "Delete");
				GenericHelper.waitForLoadmask();
				genericHelperObj.selectPartitionFilter(clientPartition, "Deleted Items");
				assertTrue(GridHelper.isValuePresent("SearchGrid", billOutputName, "Name"), billOutputName);
				Log4jHelper.logInfo("bill breakdown Output is deleted successfully :" + billOutputName);

			} else
				Log4jHelper.logInfo("bill breakdown Output is not available with :" + billOutputName);
		}
	}

	/*
	 * This method is for billbreakdown Output un delete
	 */
	public void billbreakdownOutputUnDelete() throws Exception {

		NavigationHelper.navigateToScreen("Bill Breakdown Output");
		for (paramVal = 0; paramVal < colSize; paramVal++) {

			billoutputMap = excelHolderObj.dataMap(paramVal);
			String clientPartition = ExcelHolder.getKey(billoutputMap, "Partition");
			billOutputName = ExcelHolder.getKey(billoutputMap, "BillOutputName");
			genericHelperObj.selectPartitionFilter(clientPartition, "Deleted Items");

			boolean isBillOutputPresent = genericHelperObj.isGridTextValuePresent("PS_Detail_billOutput_name_txtID",
					billOutputName, "Name");

			if (isBillOutputPresent) {
				//BillBreakdownOutputImpl billOutputImpl = new BillBreakdownOutputImpl(billoutputMap);
				//billOutputImpl.clickUnDeleteAction(billOutputName);
				genericHelperObj.clickDeleteOrUnDeleteAction(billOutputName, "Name", "Undelete");
				GenericHelper.waitForLoadmask();
				genericHelperObj.selectPartitionFilter(clientPartition, "Non Deleted Items");
				assertTrue(GridHelper.isValuePresent("SearchGrid", billOutputName, "Name"), billOutputName);
				Log4jHelper.logInfo("bill breakdown Output is un deleted successfully :" + billOutputName);

			} else
				Log4jHelper.logInfo("bill breakdown Output is not available with :" + billOutputName);
		}
	}
}
