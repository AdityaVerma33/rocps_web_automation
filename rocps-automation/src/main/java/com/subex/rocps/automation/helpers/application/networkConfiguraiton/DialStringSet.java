package com.subex.rocps.automation.helpers.application.networkConfiguraiton;

import java.util.ArrayList;
import java.util.Map;

import org.testng.annotations.Test;

import com.subex.automation.helpers.application.NavigationHelper;
import com.subex.automation.helpers.component.ButtonHelper;
import com.subex.automation.helpers.component.GenericHelper;
import com.subex.automation.helpers.component.GridHelper;
import com.subex.automation.helpers.component.SearchGridHelper;
import com.subex.automation.helpers.component.TextBoxHelper;
import com.subex.automation.helpers.data.ValidationHelper;
import com.subex.automation.helpers.file.ExcelReader;
import com.subex.automation.helpers.report.Log4jHelper;
import com.subex.rocps.automation.helpers.application.genericHelpers.PSDataComponentHelper;
import com.subex.rocps.automation.helpers.application.genericHelpers.PSGenericHelper;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;
import com.subex.rocps.automation.utils.ExcelHolder;
import com.subex.rocps.automation.utils.PSStringUtils;

public class DialStringSet extends PSAcceptanceTest {

	protected ExcelReader excelData = null;
	protected Map<String, ArrayList<String>> dialMapExcel = null;
	protected Map<String, String> dialMap = null;
	protected ExcelHolder excelHolderObj = null;
	protected String path;
	protected String workBookName;
	protected String sheetName;
	protected String testCaseName;
	protected int paramVal;
	protected int colSize;
	protected String clientPartition;
	protected String name;
	protected String nameDetail;
	protected String digits;
	protected String fromDate;
	protected String toDate;
	protected String regex = new PSStringUtils().regexFirstLevelDelimeter();
	protected PSDataComponentHelper compObj = new PSDataComponentHelper();
	protected PSGenericHelper genHelperObj = new PSGenericHelper();

	/*
	 * Constructor for initializing excel Identifying the column size from the map
	 * passed
	 */
	@Test
	public DialStringSet(String path, String workBookName, String sheetName, String testCaseName) throws Exception {
		this.path = path;
		this.workBookName = workBookName;
		this.sheetName = sheetName;
		this.testCaseName = testCaseName;
		excelData = new ExcelReader();
		dialMapExcel = excelData.readDataByColumn(this.path, this.workBookName, this.sheetName, this.testCaseName);
		excelHolderObj = new ExcelHolder(dialMapExcel);
		colSize = excelHolderObj.totalColumns();
	}

	/*
	 * Overloaded constructor for reading data from excel if test case name appears
	 * more than once
	 * 
	 * @Param occurance : Test case instance in excel sheet Constructor for
	 * initializing excel Identifying the column size from the map
	 */
	public DialStringSet(String path, String workBookName, String sheetName, String testCaseName, int occurance)
			throws Exception {
		this.path = path;
		this.workBookName = workBookName;
		this.sheetName = sheetName;
		this.testCaseName = testCaseName;
		excelData = new ExcelReader();
		dialMapExcel = excelData.readDataByColumn(this.path, this.workBookName, this.sheetName, this.testCaseName,
				occurance);
		excelHolderObj = new ExcelHolder(dialMapExcel);
		colSize = excelHolderObj.totalColumns();
	}

	/*
	 * Configuring the dialStringSet
	 * 
	 * @method : isDialStringSetPresent returns false then DialStringSet is
	 * configured isDialStringSetPresent returns true then eventIdentifierDefinition
	 * is not configured
	 */
	public void dialStringCreation() throws Exception {
		try {
			NavigationHelper.navigateToScreen("Dial String Set");
			for (paramVal = 0; paramVal < colSize; paramVal++) {

				dialMap = excelHolderObj.dataMap(paramVal);

				initializeVariables(dialMap);
				ButtonHelper.click("ClearButton");
				GenericHelper.waitForLoadmask(searchScreenWaitSec);
				if (!isDialStringSetPresent()) {
					clickNewAction();
					newDialStringSet();
					genHelperObj.detailSave("Detail_dialString_save_btnID", name, "Name");
					GenericHelper.waitForLoadmask();
					Log4jHelper.logInfo("Dial String Set is created successfully with name " + name);
				} else
					Log4jHelper.logInfo("Dial String set is available with name " + name);
			}
		} catch (Exception e) {

			throw e;
		}
	}

	/*
	 * This method is to perform double click
	 */

	public void dialStringClickCell(String gridId, int row, String colHeader, String idOrXpath) throws Exception {
		GridHelper.clickRow("dialstringsetGrid", row, colHeader);
		if (!TextBoxHelper.isPresent(idOrXpath))
			GridHelper.clickRow("dialstringsetGrid", row, colHeader);
	}

	/*
	 * This method is to create new DialString Set
	 */

	protected void newDialStringSet() throws Exception {

		String[] nameDetailArr = nameDetail.split(regex, -1);
		String[] digitsArr = digits.split(regex, -1);
		String[] fromDateArr = fromDate.split(regex, -1);
		String[] toDateArr = toDate.split(regex, -1);

		TextBoxHelper.type("Detail_dialString_name_txtID", name);
		for (int j = nameDetailArr.length - 1; j >= 0; j--) {
		    
		    ButtonHelper.click("Detail_dialString_add_btnID");
		    GenericHelper.waitForLoadmask();

		    
		    int newRow = findFirstEmptyRow("dialstringsetGrid", "Name");

		    
		    dialStringClickCell("dialstringsetGrid", newRow, "Name", "Detail_dialString_nameDetail_txtId");
		    TextBoxHelper.type("Detail_nameDetail_wrapperID", "Detail_dialString_nameDetail_txtId", nameDetailArr[j]);

		    dialStringClickCell("dialstringsetGrid", newRow, "Digits", "Detail_dialString_digit_txtID");
		    TextBoxHelper.type("Detail_nameDetail_wrapperID", "Detail_dialString_digit_txtID", digitsArr[j]);

		    dialStringClickCell("dialstringsetGrid", newRow, "From", "Detail_dialString_FromDate_txtID");
		    TextBoxHelper.type("Detail_nameDetail_wrapperID", "Detail_dialString_FromDate_txtID", fromDateArr[j]);

		    if (ValidationHelper.isNotEmpty(toDateArr)) {
		        dialStringClickCell("dialstringsetGrid", newRow, "To", "Detail_dialString_toDate_txtID");
		        compObj.typeInTextBoxOptional("Detail_dialString_toDate_txtID", toDate, toDateArr, j);
		    }
		}


	}
	
	
	
	private int findFirstEmptyRow(String gridId, String columnHeader) throws Exception {
	    int totalRows = GridHelper.getRowCount(gridId); 

	    for (int r = 1; r <= totalRows; r++) { 
	        String cellValue = "";
	        try {
	            cellValue = GridHelper.getCellValue(gridId, r, columnHeader);
	        } catch (Exception e) {
	            
	        }
	        if (cellValue == null || cellValue.trim().isEmpty()) {
	            return r; 
	        }
	    }

	   
	    return totalRows + 1;
	}
	

	public void editDialStringSet() throws Exception {
		try {
			NavigationHelper.navigateToScreen("Dial String Set");
			for (paramVal = 0; paramVal < colSize; paramVal++) {
				dialMap = excelHolderObj.dataMap(paramVal);

				initializeVariables(dialMap);
				ButtonHelper.click("ClearButton");
				GenericHelper.waitForLoadmask(searchScreenWaitSec);
				if (isDialStringSetPresent()) {
					int row = GridHelper.getRowNumber("SearchGrid", name, "Name");
					NavigationHelper.navigateToEdit("SearchGrid", row);
					assertEquals(NavigationHelper.getScreenTitle(), "Edit Dialstring Set");
					editDialStringSetDetail();
					genHelperObj.detailSave("Detail_dialString_save_btnID", name, "Name");
					GenericHelper.waitForLoadmask();
					Log4jHelper.logInfo("Dial String Set is Edite created successfully with name " + name);

				} else {
					Log4jHelper.logInfo("Dial String set is available with name " + name);
				}

			}
		} catch (Exception e) {

			throw e;
		}
	}

	/*
	 * This method is to edit DialString Set
	 */
	protected void editDialStringSetDetail() throws Exception {

		String[] nameDetailArr = nameDetail.split(regex, -1);
		String[] digitsArr = digits.split(regex, -1);
		String[] fromDateArr = fromDate.split(regex, -1);
		String[] toDateArr = toDate.split(regex, -1);

		TextBoxHelper.type("Detail_dialString_name_txtID", name);
		for (int j = 0; j < nameDetailArr.length; j++) {

			TextBoxHelper.type("dialStringName", nameDetailArr[j]);
			ButtonHelper.click("SearchButton");
			GenericHelper.waitForLoadmask(searchScreenWaitSec);

			int row = GridHelper.getRowNumber("dialstringsetGrid", nameDetailArr[j], "Name");
			if (row == 0) {
				ButtonHelper.click("Detail_dialString_add_btnID");
				GenericHelper.waitForLoadmask(searchScreenWaitSec);

				GridHelper.updateGridTextBox("dialstringsetGrid", "Detail_dialString_nameDetail_txtId", j + 1, "Name",
						nameDetailArr[j]);
				GridHelper.updateGridTextBox("dialstringsetGrid", "Detail_dialString_digit_txtID", j + 1, "Digits",
						digitsArr[j]);
				GridHelper.updateGridTextBox("dialstringsetGrid", "Detail_dialString_FromDate_txtID", j + 1, "From",
						fromDateArr[j]);
				if (ValidationHelper.isNotEmpty(toDateArr))
					GridHelper.updateGridTextBox("dialstringsetGrid", "Detail_dialString_toDate_txtID", j + 1,
							"To Date", toDateArr[j]);
			} else {
				assertEquals(GridHelper.getCellValue("dialstringsetGrid", row, "Name"), nameDetailArr[j]);
				if (ValidationHelper.isNotEmpty(digits) && ValidationHelper.isNotEmpty(digitsArr[j]))
					GridHelper.updateGridTextBox("dialstringsetGrid", "Detail_dialString_digit_txtID", row, "Digits",
							digitsArr[j]);
				if (ValidationHelper.isNotEmpty(fromDate) && ValidationHelper.isNotEmpty(fromDateArr[j]))
					GridHelper.updateGridTextBox("dialstringsetGrid", "Detail_dialString_FromDate_txtID", row, "From",
							fromDateArr[j]);
				if (ValidationHelper.isNotEmpty(toDate) && ValidationHelper.isNotEmpty(toDateArr[j]))
					GridHelper.updateGridTextBox("dialstringsetGrid", "Detail_dialString_toDate_txtID", row, "To",
							toDateArr[j]);

			}
		}

	}

	public void clickNewAction() throws Exception {
		genHelperObj.clickNewAction(clientPartition);
		GenericHelper.waitForLoadmask();
	}

	/*
	 * This method is to initialize instance variables
	 */
	protected void initializeVariables(Map<String, String> map) throws Exception {

		clientPartition = ExcelHolder.getKey(map, "Partition");
		name = ExcelHolder.getKey(map, "Name");
		nameDetail = ExcelHolder.getKey(map, "NameDetail");
		digits = ExcelHolder.getKey(map, "Digits");
		fromDate = ExcelHolder.getKey(map, "From");
		toDate = ExcelHolder.getKey(map, "To");

	}

	/*
	 * This method is to check if DialStringset is already present
	 */

	protected boolean isDialStringSetPresent() throws Exception {

		try {
			SearchGridHelper.searchWithTextBox("Detail_dialString_name_txtID", name, "Name");
			return GridHelper.isValuePresent("Detail_eventDefn_gridID", name, "Name");
		} catch (Exception e) {
			throw e;
		}
	}

	/*
	 * This method is to validate search screen columns
	 */
	public void searchScreenColumnsValidation() throws Exception {
		NavigationHelper.navigateToScreen("Dial String Set");
		GenericHelper.waitForLoadmask(searchScreenWaitSec);
		for (paramVal = 0; paramVal < colSize; paramVal++) {
			dialMap = excelHolderObj.dataMap(paramVal);
			String searchScreenColumns = ExcelHolder.getKey(dialMap, "SearchScreenColumns");
			ArrayList<String> excelColumnNames = new ArrayList<String>();
			String[] searchGridColumnsArr = searchScreenColumns.split(regex, -1);
			for (int col = 0; col < searchGridColumnsArr.length; col++) {
				excelColumnNames.add(searchGridColumnsArr[col]);
			}
			genHelperObj.totalColumns(excelColumnNames);
		}

	}

	/*
	 * This method is for Dial string set deletion
	 */
	public void dialStringSetDelete() throws Exception {

		NavigationHelper.navigateToScreen("Dial String Set");
		for (paramVal = 0; paramVal < colSize; paramVal++) {

			dialMap = excelHolderObj.dataMap(paramVal);

			clientPartition = ExcelHolder.getKey(dialMap, "Partition");
			name = ExcelHolder.getKey(dialMap, "Name");

			genHelperObj.collapsableXpath();
			genHelperObj.selectPartitionFilter(clientPartition, "Non Deleted Items");
			GenericHelper.waitForLoadmask();
			if (isDialStringSetPresent()) {
				genHelperObj.clickDeleteOrUnDeleteAction(name, "Name", "Delete");
				GenericHelper.waitForLoadmask();

				genHelperObj.selectPartitionFilter(clientPartition, "Deleted Items");
				assertTrue(GridHelper.isValuePresent("SearchGrid", name, "Name"), name);
				Log4jHelper.logInfo("Dial string set is deleted successfully with name " + name);

			} else {
				Log4jHelper.logInfo("Dial string set is not available with name " + name);
			}

		}
	}

	/*
	 * This method is for dial StringSet un delete
	 */
	public void dialStringSetUnDelete() throws Exception {

		NavigationHelper.navigateToScreen("Dial String Set");
		for (paramVal = 0; paramVal < colSize; paramVal++) {

			dialMap = excelHolderObj.dataMap(paramVal);

			clientPartition = ExcelHolder.getKey(dialMap, "Partition");
			name = ExcelHolder.getKey(dialMap, "Name");

			genHelperObj.selectPartitionFilter(clientPartition, "Deleted Items");
			if (isDialStringSetPresent()) {
				genHelperObj.clickDeleteOrUnDeleteAction(name, "Name", "Undelete");
				GenericHelper.waitForLoadmask();

				genHelperObj.selectPartitionFilter(clientPartition, "Non Deleted Items");
				assertTrue(GridHelper.isValuePresent("SearchGrid", name, "Name"), name);
				Log4jHelper.logInfo("Dial string set is un deleted successfully with name " + name);

			} else {
				Log4jHelper.logInfo("Dial string set is not available with name " + name);
			}

		}
	}
}
