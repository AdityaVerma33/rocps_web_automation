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

public class ElementSet extends PSAcceptanceTest {

	protected ExcelReader excelData = null;
	protected Map<String, ArrayList<String>> elementSetExcel = null;
	protected Map<String, String> elementSetMap = null;
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
	public ElementSet(String path, String workBookName, String sheetName, String testCaseName) throws Exception {
		this.path = path;
		this.workBookName = workBookName;
		this.sheetName = sheetName;
		this.testCaseName = testCaseName;
		excelData = new ExcelReader();
		elementSetExcel = excelData.readDataByColumn(this.path, this.workBookName, this.sheetName, this.testCaseName);
		excelHolderObj = new ExcelHolder(elementSetExcel);
		colSize = excelHolderObj.totalColumns();
	}

	/*
	 * Overloaded constructor for reading data from excel if test case name
	 * appears more than once
	 * 
	 * @Param occurance : Test case instance in excel sheet Constructor for
	 * initializing excel Identifying the column size from the map
	 */

	public ElementSet(String path, String workBookName, String sheetName, String testCaseName, int occurance)
			throws Exception {
		this.path = path;
		this.workBookName = workBookName;
		this.sheetName = sheetName;
		this.testCaseName = testCaseName;
		excelData = new ExcelReader();
		elementSetExcel = excelData.readDataByColumn(this.path, this.workBookName, this.sheetName, this.testCaseName,
				occurance);
		excelHolderObj = new ExcelHolder(elementSetExcel);
		colSize = excelHolderObj.totalColumns();
	}

	/*
	 * Configuring the Element Set
	 * 
	 */
	public void elementSetCreation() throws Exception {
		try {
			NavigationHelper.navigateToScreen("Reference Tables");
			for (paramVal = 0; paramVal < colSize; paramVal++) {

				elementSetMap = excelHolderObj.dataMap(paramVal);
				assertEquals(NavigationHelper.getScreenTitle(), "Reference Table Search");
				initializeVariables(elementSetMap);
				ComboBoxHelper.select("PS_Detail_serviceTariffType_tableName_comboID", tableName);

				if (!isElementSetPresent()) {

					genericHelperObj.clickNewAction(clientPartition);
					GenericHelper.waitForLoadmask();
					createElementSet();
					saveElementSet();

					Log4jHelper.logInfo("Element Set is created successfully with name " + name);
				} else {
					Log4jHelper.logInfo("Element Set type is available with name " + name);
				}

			}

		} catch (Exception e) {

			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}

	/*
	 * This method is to create Elementset
	 */
	public void createElementSet() throws Exception {
		assertEquals(NavigationHelper.getScreenTitle(), "New Element Set");
		TextBoxHelper.type("PS_Detail_wrapperID", "PS_Detail_RefTable_ElementSet_name_txtID", name);

	}

	/*
	 * This method is to check if the element set is already present
	 */
	private boolean isElementSetPresent() throws Exception {
		TextBoxHelper.type("PS_Detail_RefTable_ElementSet_name_txtID", name);
		ButtonHelper.click("SearchButton");
		return GridHelper.isValuePresent("Detail_eventDefn_gridID", name, "Element Set Name");
	}

	/*
	 * this method is to save element set
	 */
	private void saveElementSet() throws Exception {
		ButtonHelper.click("SaveButton");
		GenericHelper.waitForSave();
		assertEquals(NavigationHelper.getScreenTitle(), "Reference Table Search");
		assertTrue(GridHelper.isValuePresent("Detail_eventDefn_gridID", name, "Name"), "Element Set is not configured");
	}

	/*
	 * This method is to initialize instance Variables
	 */
	public void initializeVariables(Map<String, String> map) throws Exception {
		clientPartition = ExcelHolder.getKey(map, "Partition");
		name = ExcelHolder.getKey(map, "Name");
		tableName = ExcelHolder.getKey(map, "TableName");
	}
}
