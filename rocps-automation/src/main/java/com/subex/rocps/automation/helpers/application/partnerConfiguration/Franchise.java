package com.subex.rocps.automation.helpers.application.partnerConfiguration;

import java.util.ArrayList;
import java.util.Map;

import org.testng.annotations.Test;

import com.subex.automation.helpers.application.NavigationHelper;
import com.subex.automation.helpers.component.ButtonHelper;
import com.subex.automation.helpers.component.GenericHelper;
import com.subex.automation.helpers.component.GridHelper;
import com.subex.automation.helpers.component.TextBoxHelper;
import com.subex.automation.helpers.file.ExcelReader;
import com.subex.automation.helpers.report.Log4jHelper;
import com.subex.automation.helpers.util.FailureHelper;
import com.subex.rocps.automation.helpers.application.genericHelpers.PSGenericHelper;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;
import com.subex.rocps.automation.utils.ExcelHolder;

public class Franchise extends PSAcceptanceTest {
	protected ExcelReader excelData = null;
	protected Map<String, ArrayList<String>> franchiseExcel = null;
	protected Map<String, String> franchiseMap = null;
	protected ExcelHolder excelHolderObj = null;
	protected String path;
	protected String workBookName;
	protected String sheetName;
	protected String testCaseName;
	protected int paramVal;
	protected int colSize;
	protected String clientPartition;
	protected String name;
	protected PSGenericHelper genericHelperObj = new PSGenericHelper();

	/*
	 * Constructor for initializing excel Identifying the column size from the map
	 * passed
	 */
	public Franchise(String path, String workBookName, String sheetName, String testCaseName) throws Exception {
		this.path = path;
		this.workBookName = workBookName;
		this.sheetName = sheetName;
		this.testCaseName = testCaseName;
		excelData = new ExcelReader();
		franchiseExcel = excelData.readDataByColumn(this.path, this.workBookName, this.sheetName, this.testCaseName);
		excelHolderObj = new ExcelHolder(franchiseExcel);
		colSize = excelHolderObj.totalColumns();
	}

	/*
	 * Overloaded constructor for reading data from excel if test case name appears
	 * more than once
	 * 
	 * @Param occurance : Test case instance in excel sheet Constructor for
	 * initializing excel Identifying the column size from the map
	 */

	public Franchise(String path, String workBookName, String sheetName, String testCaseName, int occurance)
			throws Exception {
		this.path = path;
		this.workBookName = workBookName;
		this.sheetName = sheetName;
		this.testCaseName = testCaseName;
		excelData = new ExcelReader();
		franchiseExcel = excelData.readDataByColumn(this.path, this.workBookName, this.sheetName, this.testCaseName,
				occurance);
		excelHolderObj = new ExcelHolder(franchiseExcel);
		colSize = excelHolderObj.totalColumns();
	}

	/*
	 * Configuring the new franchise
	 * 
	 */
	public void franchiseCreation() throws Exception {
		try {
			NavigationHelper.navigateToReferenceTable("Franchise");
			for (paramVal = 0; paramVal < colSize; paramVal++) {

				franchiseMap = excelHolderObj.dataMap(paramVal);
				initializeVariables(franchiseMap);
				GenericHelper.waitForLoadmask(searchScreenWaitSec);
				boolean isFranchisePresent = GridHelper.isValuePresent("searchGrid", name, "Code");

				if (!isFranchisePresent) {
					genericHelperObj.clickNewAction(clientPartition);
					GenericHelper.waitForLoadmask();
					newFranchise();
					saveFranchise();

					Log4jHelper.logInfo("Franchise is created successfully with name " + name);
				} else {
					Log4jHelper.logInfo("Franchise is already available with name " + name);
				}
			}
		} catch (Exception e) {

			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}

	/*
	 * this method is to create new Franchise
	 */
	public void newFranchise() throws Exception {
		NavigationHelper.selectPartition(clientPartition);
		assertEquals(NavigationHelper.getScreenTitle(), "New Franchise");
		GenericHelper.waitForLoadmask();
		System.out.println("The CODE FOR FRANCHISE IS "+name);
		TextBoxHelper.type("PS_Detail_wrapperID", "PS_Detail_RefTable_Franchise_name_txtID", name);
	}

	public void saveFranchise() throws Exception {
		ButtonHelper.click("SaveButton");
		GenericHelper.waitForSave();
		assertEquals(NavigationHelper.getScreenTitle(), "Reference Table Search");
		assertTrue(GridHelper.isValuePresent("Detail_eventDefn_gridID", name, "Code"), "Franchise is not configured");
	}

	/*
	 * This method is to initializeVariables
	 */
	public void initializeVariables(Map<String, String> map) throws Exception {
		clientPartition = ExcelHolder.getKey(map, "Partition");
		name = ExcelHolder.getKey(map, "Name");
	}

}
