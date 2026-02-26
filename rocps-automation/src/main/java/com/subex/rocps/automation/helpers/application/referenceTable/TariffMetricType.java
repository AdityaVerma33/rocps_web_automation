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

public class TariffMetricType extends PSAcceptanceTest {

	protected ExcelReader excelData = null;
	protected Map<String, ArrayList<String>> tariffMetricExcel = null;
	protected Map<String, String> tariffMetricMap = null;
	protected ExcelHolder excelHolderObj = null;
	protected String path;
	protected String workBookName;
	protected String sheetName;
	protected String testCaseName;
	protected int paramVal;
	protected int colSize;
	protected String clientPartition;
	protected String name;
	protected String usageName;
	protected String usageDivisorName;
	protected String usageDivisor;
	protected String tableName;
	protected PSGenericHelper genericHelperObj = new PSGenericHelper();

	/*
	 * Constructor for initializing excel Identifying the column size from the
	 * map passed
	 */
	@Test
	public TariffMetricType(String path, String workBookName, String sheetName, String testCaseName) throws Exception {
		this.path = path;
		this.workBookName = workBookName;
		this.sheetName = sheetName;
		this.testCaseName = testCaseName;
		excelData = new ExcelReader();
		tariffMetricExcel = excelData.readDataByColumn(this.path, this.workBookName, this.sheetName, this.testCaseName);
		excelHolderObj = new ExcelHolder(tariffMetricExcel);
		colSize = excelHolderObj.totalColumns();
	}

	/*
	 * Overloaded constructor for reading data from excel if test case name
	 * appears more than once
	 * 
	 * @Param occurance : Test case instance in excel sheet Constructor for
	 * initializing excel Identifying the column size from the map
	 */

	public TariffMetricType(String path, String workBookName, String sheetName, String testCaseName, int occurance)
			throws Exception {
		this.path = path;
		this.workBookName = workBookName;
		this.sheetName = sheetName;
		this.testCaseName = testCaseName;
		excelData = new ExcelReader();
		tariffMetricExcel = excelData.readDataByColumn(this.path, this.workBookName, this.sheetName, this.testCaseName,
				occurance);
		excelHolderObj = new ExcelHolder(tariffMetricExcel);
		colSize = excelHolderObj.totalColumns();
	}

	/*
	 * Configuring the Tariff Metric Type
	 * 
	 */
	public void tariffMetricCreation() throws Exception {
		try {
			NavigationHelper.navigateToScreen("Reference Tables");
			for (paramVal = 0; paramVal < colSize; paramVal++) {

				tariffMetricMap = excelHolderObj.dataMap(paramVal);
				assertEquals(NavigationHelper.getScreenTitle(), "Reference Table Search");
				initializeVariables(tariffMetricMap);
				ComboBoxHelper.select("PS_Detail_serviceTariffType_tableName_comboID", tableName);

				if (!isTariffMetricPresent()) {

					genericHelperObj.clickNewAction(clientPartition);
					GenericHelper.waitForLoadmask();
					newTariffMetric();
					saveTariffMetricType();

					Log4jHelper.logInfo("Tariff Metric type is created successfully with name " + name);
				} else {
					Log4jHelper.logInfo("Tariff MEtric type is available with name " + name);
				}

			}

		} catch (Exception e) {

			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}

	/*
	 * This method is to create new tariff Metric
	 */
	public void newTariffMetric() throws Exception {
		assertEquals(NavigationHelper.getScreenTitle(), "New Tariff Metric type");
		TextBoxHelper.type("PS_Detail_wrapperID", "PS_Detail_RefTable_TariffMetric_name_txtID", name);
		TextBoxHelper.type("PS_Detail_wrapperID", "PS_Detail_RefTable_TariffMetric_usageName_txtID", usageName);
		TextBoxHelper.type("PS_Detail_wrapperID", "PS_Detail_RefTable_TariffMetric_usageDivisorName_txtID",
				usageDivisorName);
		TextBoxHelper.type("PS_Detail_wrapperID", "PS_Detail_RefTable_TariffMetric_usageDivisor_txtID", usageDivisor);
	}

	public void saveTariffMetricType() throws Exception {
		ButtonHelper.click("SaveButton");
		GenericHelper.waitForSave();
		assertEquals(NavigationHelper.getScreenTitle(), "Reference Table Search");
		assertTrue(GridHelper.isValuePresent("Detail_eventDefn_gridID", name, "Name"),
				"Tariff Metrix Type is not configured");
	}

	/*
	 * This method is check if tariffMetric is present already
	 * 
	 */
	private boolean isTariffMetricPresent() throws Exception {
		TextBoxHelper.type("PS_Detail_RefTable_TariffMetric_name_txtID", name);
		ButtonHelper.click("SearchButton");
		return GridHelper.isValuePresent("Detail_eventDefn_gridID", name, "Name");
	}

	/*
	 * this method is to initialize instance Variables
	 */
	public void initializeVariables(Map<String, String> map) throws Exception {
		clientPartition = ExcelHolder.getKey(map, "Partition");
		name = ExcelHolder.getKey(map, "Name");
		tableName = ExcelHolder.getKey(map, "TableName");
		usageName = ExcelHolder.getKey(map, "UsageName");
		usageDivisorName = ExcelHolder.getKey(map, "UsageDivisorName");
		usageDivisor = ExcelHolder.getKey(map, "UsageDivisor");
	}

}
