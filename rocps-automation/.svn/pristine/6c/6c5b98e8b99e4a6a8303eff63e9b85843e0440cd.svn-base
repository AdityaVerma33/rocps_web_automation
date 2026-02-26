package com.subex.rocps.automation.helpers.application.referenceTable;

import java.util.ArrayList;
import java.util.Map;

import org.testng.annotations.Test;

import com.subex.automation.helpers.application.NavigationHelper;
import com.subex.automation.helpers.component.ButtonHelper;
import com.subex.automation.helpers.component.ComboBoxHelper;
import com.subex.automation.helpers.component.GenericHelper;
import com.subex.automation.helpers.component.GridHelper;
import com.subex.automation.helpers.component.TextBoxHelper;
import com.subex.automation.helpers.file.ExcelReader;
import com.subex.automation.helpers.report.Log4jHelper;
import com.subex.automation.helpers.util.FailureHelper;
import com.subex.rocps.automation.helpers.application.genericHelpers.PSGenericHelper;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;
import com.subex.rocps.automation.utils.ExcelHolder;

public class TariffRateName extends PSAcceptanceTest {

	protected ExcelReader excelData = null;
	protected Map<String, ArrayList<String>> tariffRateExcel = null;
	protected Map<String, String> tariffRateMap = null;
	protected ExcelHolder excelHolderObj = null;
	protected String path;
	protected String workBookName;
	protected String sheetName;
	protected String testCaseName;
	protected int paramVal;
	protected int colSize;
	protected String clientPartition;
	protected String name;
	protected String code;
	protected String enableTariffType;
	protected String tableName;
	protected PSGenericHelper genericHelperObj = new PSGenericHelper();

	/*
	 * Constructor for initializing excel Identifying the column size from the
	 * map passed
	 */
	@Test
	public TariffRateName(String path, String workBookName, String sheetName, String testCaseName) throws Exception {
		this.path = path;
		this.workBookName = workBookName;
		this.sheetName = sheetName;
		this.testCaseName = testCaseName;
		excelData = new ExcelReader();
		tariffRateExcel = excelData.readDataByColumn(this.path, this.workBookName, this.sheetName, this.testCaseName);
		excelHolderObj = new ExcelHolder(tariffRateExcel);
		colSize = excelHolderObj.totalColumns();
	}

	/*
	 * Overloaded constructor for reading data from excel if test case name
	 * appears more than once
	 * 
	 * @Param occurance : Test case instance in excel sheet Constructor for
	 * initializing excel Identifying the column size from the map
	 */

	public TariffRateName(String path, String workBookName, String sheetName, String testCaseName, int occurance)
			throws Exception {
		this.path = path;
		this.workBookName = workBookName;
		this.sheetName = sheetName;
		this.testCaseName = testCaseName;
		excelData = new ExcelReader();
		tariffRateExcel = excelData.readDataByColumn(this.path, this.workBookName, this.sheetName, this.testCaseName,
				occurance);
		excelHolderObj = new ExcelHolder(tariffRateExcel);
		colSize = excelHolderObj.totalColumns();
	}

	/*
	 * Configuring the TariffRateName
	 * 
	 */
	public void tariffRateNameCreation() throws Exception {
		try {
			NavigationHelper.navigateToScreen("Reference Tables");
			for (paramVal = 0; paramVal < colSize; paramVal++) {

				tariffRateMap = excelHolderObj.dataMap(paramVal);
				assertEquals(NavigationHelper.getScreenTitle(), "Reference Table Search");
				initializeVariables(tariffRateMap);
				ComboBoxHelper.select("PS_Detail_serviceTariffType_tableName_comboID", tableName);
				GenericHelper.waitForLoadmask();
				if (!isTariffRateNamePresent()) {

					genericHelperObj.clickNewAction(clientPartition);
					GenericHelper.waitForLoadmask();
					newtariffRateName();
					saveTariffRateName();
					Log4jHelper.logInfo("Tariff Rate Name is created successfully with name " + name);
				} else {
					Log4jHelper.logInfo("Tariff Rate Name is available with name " + name);
				}

			}

		} catch (Exception e) {

			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}

	public void newtariffRateName() throws Exception {
		assertEquals(NavigationHelper.getScreenTitle(), "New Tariff Rate Name");
		TextBoxHelper.type("PS_Detail_wrapperID", "PS_Detail_RefTable_TariffRateName_name_txtID", name);
	}

	/*
	 * This method is to save tariff rate name
	 */
	public void saveTariffRateName() throws Exception {
		ButtonHelper.click("SaveButton");
		GenericHelper.waitForSave();
		assertEquals(NavigationHelper.getScreenTitle(), "Reference Table Search");
		assertTrue(GridHelper.isValuePresent("Detail_eventDefn_gridID", name, "Name"),
				"Tariff Rate Name is not configured");
	}

	/*
	 * This method is to check if tariff rate name is already present
	 */
	private boolean isTariffRateNamePresent() throws Exception {
		TextBoxHelper.type("PS_Detail_RefTable_TariffRateName_name_txtID", name);
		ButtonHelper.click("SearchButton");
		return GridHelper.isValuePresent("Detail_eventDefn_gridID", name, "Name");
	}

	/*
	 * This method is to initialize instance variables
	 */
	public void initializeVariables(Map<String, String> map) throws Exception {
		clientPartition = ExcelHolder.getKey(map, "Partition");
		name = ExcelHolder.getKey(map, "Name");
		tableName = ExcelHolder.getKey(map, "TableName");
	}
}
