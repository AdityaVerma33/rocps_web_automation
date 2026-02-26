package com.subex.rocps.automation.helpers.application.carrierinvoice;

import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Map;

import com.subex.rocps.automation.helpers.application.carrierinvoice.systemfieldlist.SystemFieldListDetailImpl;
import com.subex.rocps.automation.helpers.application.filters.PSSearchGridHelper;
import com.subex.rocps.automation.helpers.application.genericHelpers.DataVerificationHelper;
import com.subex.rocps.automation.helpers.application.genericHelpers.PSGenericHelper;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;
import com.subex.rocps.automation.utils.ExcelHolder;
import com.subex.rocps.automation.utils.PSStringUtils;

import com.subex.automation.helpers.application.NavigationHelper;
import com.subex.automation.helpers.component.ButtonHelper;
import com.subex.automation.helpers.component.GenericHelper;
import com.subex.automation.helpers.component.GridHelper;
import com.subex.automation.helpers.component.PopupHelper;
import com.subex.automation.helpers.component.SearchGridHelper;
import com.subex.automation.helpers.file.ExcelReader;
import com.subex.automation.helpers.report.Log4jHelper;
import com.subex.automation.helpers.util.FailureHelper;

public class SystemFieldList extends PSAcceptanceTest {
	protected ExcelReader excelData = null;
	protected Map<String, ArrayList<String>> systemFieldExcel = null;
	protected Map<String, String> systemFieldMap = null;
	protected ExcelHolder excelHolderObj = null;
	protected String path;
	protected String workBookName;
	protected String sheetName;
	protected String testCaseName;
	protected int paramVal;
	protected int colSize;
	protected String clientPartition;
	protected String name;
	protected String rateEffectiveDate;
	protected String completeExpireDate;
	protected String originRateEffectiveDate;
	protected String originCompleteExpireDate;
	protected String complete;
	protected String autoAuthorize;
	protected String locationInformation;
	protected String sheet;
	protected String columnIndex;
	protected String rowNumber;
	protected String destinationDetails;
	protected String originDetails;
	protected String colHeaders;
	protected String results;
	protected String fileName;
	String regex = new PSStringUtils().regexFirstLevelDelimeter();

	protected PSGenericHelper genericHelperObj = new PSGenericHelper();
	DataVerificationHelper dataVerifyObj = new DataVerificationHelper();

	/*
	 * Constructor for initializing excel Identifying the column size from the map
	 * passed
	 */
	@Test
	public SystemFieldList(String path, String workBookName, String sheetName, String testCaseName) throws Exception {
		this.path = path;
		this.workBookName = workBookName;
		this.sheetName = sheetName;
		this.testCaseName = testCaseName;
		excelData = new ExcelReader();
		systemFieldExcel = excelData.readDataByColumn(this.path, this.workBookName, this.sheetName, this.testCaseName);
		excelHolderObj = new ExcelHolder(systemFieldExcel);
		colSize = excelHolderObj.totalColumns();
	}

	/*
	 * Overloaded constructor for reading data from excel if test case name appears
	 * more than once
	 * 
	 * @Param occurance : Test case instance in excel sheet Constructor for
	 * initializing excel Identifying the column size from the map
	 */
	public SystemFieldList(String path, String workBookName, String sheetName, String testCaseName, int occurance)
			throws Exception {
		this.path = path;
		this.workBookName = workBookName;
		this.sheetName = sheetName;
		this.testCaseName = testCaseName;
		excelData = new ExcelReader();
		systemFieldExcel = excelData.readDataByColumn(this.path, this.workBookName, this.sheetName, this.testCaseName,
				occurance);
		excelHolderObj = new ExcelHolder(systemFieldExcel);
		colSize = excelHolderObj.totalColumns();
	}

	/*
	 * Configuring the system field list
	 * 
	 */
	public void configureSystemFieldList() throws Exception {
		try {
			NavigationHelper.navigateToScreen("System Field List");
			for (paramVal = 0; paramVal < colSize; paramVal++) {
				systemFieldMap = excelHolderObj.dataMap(paramVal);
				name = ExcelHolder.getKey(systemFieldMap, "Name");
				clientPartition = ExcelHolder.getKey(systemFieldMap, "Partition");
				ButtonHelper.click("ClearButton");
				GenericHelper.waitForLoadmask(searchScreenWaitSec);
				boolean isSystemFieldPresent = genericHelperObj.isGridTextValuePresent("PS_systemFieldList_Name_txtID",
						name, "Name");
				if (!isSystemFieldPresent) {
					genericHelperObj.clickNewAction(clientPartition);
					SystemFieldListDetailImpl sysDetailObj = new SystemFieldListDetailImpl(systemFieldMap);
					sysDetailObj.basicConfig();
					sysDetailObj.systemFieldMappingGrid();
					sysDetailObj.saveSystemFiledList();
				} else
					Log4jHelper.logInfo("System Field List is available with name :" + name);

			}
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}

	/*
	 * This method is for search screen column validation
	 */
	public void searchScreenColumnsValidation() throws Exception {
		NavigationHelper.navigateToScreen("System Field List");
		GenericHelper.waitForLoadmask(searchScreenWaitSec);
		for (paramVal = 0; paramVal < colSize; paramVal++) {
			systemFieldMap = excelHolderObj.dataMap(paramVal);
			String searchScreenColumns = ExcelHolder.getKey(systemFieldMap, "SearchScreenColumns");
			ArrayList<String> excelColumnNames = new ArrayList<String>();
			String[] searchGridColumnsArr = searchScreenColumns.split(regex, -1);
			for (int col = 0; col < searchGridColumnsArr.length; col++) {
				excelColumnNames.add(searchGridColumnsArr[col]);
			}
			genericHelperObj.totalColumns(excelColumnNames);
		}

	}

	/*
	 * This method is for System Field List chnage status
	 */
	public void systemFieldListChangeStatus() throws Exception {

		NavigationHelper.navigateToScreen("System Field List");
		for (paramVal = 0; paramVal < colSize; paramVal++) {

			systemFieldMap = excelHolderObj.dataMap(paramVal);
			name = ExcelHolder.getKey(systemFieldMap, "Name");
			ButtonHelper.click("ClearButton");
			GenericHelper.waitForLoadmask(searchScreenWaitSec);
			boolean isSystemFieldPresent = genericHelperObj.isGridTextValuePresent("PS_systemFieldList_Name_txtID",
					name, "Name");
			if (isSystemFieldPresent) {
				GridHelper.clickRow("SearchGrid", name, "Name");
				GenericHelper.waitForLoadmask(searchScreenWaitSec);
				String actualVal = GridHelper.getCellValue("SearchGrid", 1, "Status");
				if (actualVal.equalsIgnoreCase("Draft")) {
					// assertTrue( GridHelper.isValuePresent( "SearchGrid", "Draft", "Status" ) );
					NavigationHelper.navigateToAction("Change Status", "Approved");
					GenericHelper.waitForLoadmask(searchScreenWaitSec);
					ButtonHelper.click("ClearButton");
					GenericHelper.waitForLoadmask(searchScreenWaitSec);
					PSSearchGridHelper.gridFilterSearchWithTextBox("psyfName", name, "Name");
					GridHelper.clickRow("SearchGrid", name, "Name");
					assertTrue(GridHelper.isValuePresent("SearchGrid", "Approved", "Status"), "Status is not changed");
					Log4jHelper.logInfo("System Field List status has changed successfully :" + name);
				} else
					Log4jHelper.logInfo("System filed status is in accepted state");
				
			} else
				Log4jHelper.logInfo("System Field List is not available with :" + name);

		}
	}

	/*
	 * This method is for System Field List  delete
	 */
	public void systemFieldListDelete() throws Exception {

		NavigationHelper.navigateToScreen("System Field List");
		for (paramVal = 0; paramVal < colSize; paramVal++) {
			systemFieldMap = excelHolderObj.dataMap(paramVal);

			clientPartition = ExcelHolder.getKey(systemFieldMap, "Partition");
			name = ExcelHolder.getKey(systemFieldMap, "Name");
			genericHelperObj.selectPartitionFilter(clientPartition, "Non Deleted Items");
			boolean isSystemFieldPresent = genericHelperObj.isGridTextValuePresent("PS_systemFieldList_Name_txtID",
					name, "Name");

			if (isSystemFieldPresent) {
				genericHelperObj.clickDeleteOrUnDeleteAction(name, "Name", "Delete");
				genericHelperObj.selectPartitionFilter(clientPartition, "Deleted Items");
				assertTrue(GridHelper.isValuePresent("SearchGrid", name, "Name"), name);
				Log4jHelper.logInfo("System Field List is deleted successfully :" + name);
			} else
				Log4jHelper.logInfo("System Field List is not available with :" + name);
		}
	}

	/*
	 * This method is for billbreakdownConfig un delete
	 */
	public void systemFieldListUnDelete() throws Exception {

		NavigationHelper.navigateToScreen("System Field List");
		for (paramVal = 0; paramVal < colSize; paramVal++) {
			systemFieldMap = excelHolderObj.dataMap(paramVal);
			clientPartition = ExcelHolder.getKey(systemFieldMap, "Partition");
			name = ExcelHolder.getKey(systemFieldMap, "Name");

			genericHelperObj.selectPartitionFilter(clientPartition, "Deleted Items");

			boolean isSystemFieldPresent = genericHelperObj.isGridTextValuePresent("PS_systemFieldList_Name_txtID",
					name, "Name");

			if (isSystemFieldPresent) {
				genericHelperObj.clickDeleteOrUnDeleteAction(name, "Name", "Undelete");
				genericHelperObj.selectPartitionFilter(clientPartition, "Non Deleted Items");
				assertTrue(GridHelper.isValuePresent("SearchGrid", name, "Name"), name);
				Log4jHelper.logInfo("System Field List is un deleted successfully :" + name);
			} else
				Log4jHelper.logInfo("System Field List is not available with :" + name);
		}

	}
	
	public void editSystemFieldList() throws Exception {
		try {
			NavigationHelper.navigateToScreen("System Field List");
			for (paramVal = 0; paramVal < colSize; paramVal++) {
				systemFieldMap = excelHolderObj.dataMap(paramVal);
				name = ExcelHolder.getKey(systemFieldMap, "Name");
				clientPartition = ExcelHolder.getKey(systemFieldMap, "Partition");
				ButtonHelper.click("ClearButton");
				GenericHelper.waitForLoadmask(searchScreenWaitSec);
				boolean isSystemFieldPresent = genericHelperObj.isGridTextValuePresent("PS_systemFieldList_Name_txtID",
						name, "Name");
				if (isSystemFieldPresent) {
					int row = GridHelper.getRowNumber( "SearchGrid", name, "Name" );
					NavigationHelper.navigateToEdit( "SearchGrid", row );
					GenericHelper.waitForLoadmask( searchScreenWaitSec );
					
					SystemFieldListDetailImpl sysDetailObj = new SystemFieldListDetailImpl(systemFieldMap);
					sysDetailObj.editbasicConfig();
					sysDetailObj.editsystemFieldMappingGrid();
					sysDetailObj.saveSystemFiledList();
					Log4jHelper.logInfo("System Field List is edited  successfully with name :" + name);
				} else
					Log4jHelper.logInfo("System Field List is not  available with name :" + name);

			}
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}

}
