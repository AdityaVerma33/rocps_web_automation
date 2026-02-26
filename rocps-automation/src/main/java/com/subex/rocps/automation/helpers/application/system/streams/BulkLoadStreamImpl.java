package com.subex.rocps.automation.helpers.application.system.streams;

import java.util.ArrayList;
import java.util.HashMap;

import com.subex.rocps.automation.helpers.application.genericHelpers.PSGenericHelper;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;

import com.subex.rocps.automation.utils.ExcelHolder;

import com.subex.automation.helpers.application.NavigationHelper;
import com.subex.automation.helpers.component.GenericHelper;
import com.subex.automation.helpers.component.GridHelper;
import com.subex.automation.helpers.component.SearchGridHelper;
import com.subex.automation.helpers.file.ExcelReader;
import com.subex.automation.helpers.report.Log4jHelper;

public class BulkLoadStreamImpl extends PSAcceptanceTest {

	protected String path;
	protected String workBookName;
	protected String sheetName;
	protected int occurence;
	protected PSGenericHelper psGenericHelper = new PSGenericHelper();

	// Constructor : Initializing the excel without occurrence

	public BulkLoadStreamImpl(String path, String workBookName, String sheetName) {

		this.path = path;
		this.workBookName = workBookName;
		this.sheetName = sheetName;
	}

	// Constructor : Initializing the excel with occurrence
	public BulkLoadStreamImpl(String path, String workBookName, String sheetName, int occurence) {

		this.path = path;
		this.workBookName = workBookName;
		this.sheetName = sheetName;
		this.occurence = occurence;
	}

	/*
	 * public void scheduleFileCollection(String tescase ) throws Exception {
	 * 
	 * ExcelReader excelReader = new ExcelReader(); HashMap<String,
	 * ArrayList<String>> map = excelReader.readDataByColumn(path, workBookName,
	 * sheetName, tescase, occurence); int size =
	 * map.get("FileCollectionName").size(); for (int i = 0; i < size; i++) { String
	 * fcName = map.get("FileCollectionName").get(i); FileCollectionHelper obj = new
	 * FileCollectionHelper(); obj.scheduleFileCollection(fcName); }
	 * 
	 * 
	 * }
	 */

	// Method:Verify the data with Column Grid Filter of Screen

	public void verifyDataWithColumnGridFilterOfScreen(String tescase, String ScreenName, String nametextId,
			String columnHeader) throws Exception {

		ExcelReader excelReader = new ExcelReader();
		HashMap<String, ArrayList<String>> map = excelReader.readDataByColumn(path, workBookName, sheetName, tescase,
				occurence);
		ExcelHolder excelHolderObj = new ExcelHolder(map);
		int colSize = excelHolderObj.totalColumns();

		NavigationHelper.navigateToScreen(ScreenName);
		for (int index = 0; index < colSize; index++) {
			String name = map.get("Name").get(index);
			boolean isdataPresent = psGenericHelper.isGridTextValuePresent(nametextId, name, columnHeader);
			assertTrue(isdataPresent);
			Log4jHelper.logInfo("Grid Value is available with name " + name);

		}

	}

	// Method:Verify the data with Text Box Filter of Screen
	public void verifyDatawithTextBoxFilterOfScreen(String tescase, String ScreenName, String nametextId,
			String columnHeader) throws Exception {

		ExcelReader excelReader = new ExcelReader();
		HashMap<String, ArrayList<String>> map = excelReader.readDataByColumn(path, workBookName, sheetName, tescase,
				occurence);
		ExcelHolder excelHolderObj = new ExcelHolder(map);
		int colSize = excelHolderObj.totalColumns();

		NavigationHelper.navigateToScreen(ScreenName);
		for (int index = 0; index < colSize; index++) {
			String name = map.get("Name").get(index);
			if (columnHeader.contentEquals("Agent")) {
				assertTrue(isAgentPresent(name));
				Log4jHelper.logInfo("SearchBox Value is available with name " + name);
			} else {
				SearchGridHelper.searchWithTextBox(nametextId, name, columnHeader);
				boolean isdataPresent = GridHelper.isValuePresent("Detail_eventDefn_gridID", name, columnHeader);

				assertTrue(isdataPresent);
				Log4jHelper.logInfo("SearchBox Value is available with name " + name);
			}

		}

	}

	public boolean isAgentPresent(String companyName) throws Exception {
		SearchGridHelper.searchWithTextBox("Detail_companyName_txtID", companyName, "Agent");
		int row = GridHelper.getRowCount("SearchGrid");
		boolean rowVal = false;
		if (row > 0) {
			for (int i = 0; i < row; i++) {
				String cellValue = GridHelper.getCellValue("SearchGrid", i + 1, "Agent");
				GenericHelper.waitForLoadmask();
				rowVal = cellValue.contains(companyName);
				return true;
			}
			return rowVal;
		}
		return false;
	}

	// Method:Verify the data with Reference Table Entity of Screen
	public void verifyDatawithReferTableOfScreen(String tescase, String ScreenName, String columnHeader)
			throws Exception {

		ExcelReader excelReader = new ExcelReader();
		HashMap<String, ArrayList<String>> map = excelReader.readDataByColumn(path, workBookName, sheetName, tescase,
				occurence);
		ExcelHolder excelHolderObj = new ExcelHolder(map);
		int colSize = excelHolderObj.totalColumns();

		NavigationHelper.navigateToReferenceTable(ScreenName);
		for (int index = 0; index < colSize; index++) {
			String name = map.get("Name").get(index);

			boolean isdataPresent = GridHelper.isValuePresent("searchGrid", name, columnHeader);

			assertTrue(isdataPresent);
			Log4jHelper.logInfo("Reference Table with the given refereTable Entity-' " + ScreenName
					+ "' is available with name " + name);

		}

	}
}
