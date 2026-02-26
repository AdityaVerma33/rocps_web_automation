package com.subex.rocps.automation.helpers.application.alerts;

import java.util.ArrayList;
import java.util.Map;

import com.subex.automation.helpers.application.NavigationHelper;
import com.subex.automation.helpers.application.screens.AlertsHelper;
import com.subex.automation.helpers.component.GenericHelper;
import com.subex.automation.helpers.file.ExcelReader;
import com.subex.automation.helpers.util.FailureHelper;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;
import com.subex.rocps.automation.utils.ExcelHolder;

public class AlertValidation extends PSAcceptanceTest {
	protected ExcelReader excelData = new ExcelReader();
	protected Map<String, ArrayList<String>> bulkExcelMap = null;
	protected Map<String, ArrayList<String>> excelReaderMapObj = null;
	protected ExcelHolder excelHolderObj = null;
	protected Map<String, String> alertMap = null;
	AlertsHelper alertObj=new AlertsHelper();
	String path;
	String workBookName;
	String sheetName;
	String testCaseName;
	int occurence;
	String columnHeader;
	String results;
	int colSize;
	int paramVal;
	String partition;
	/*
	 * Constructor : Initialising the excel
	 */
	public AlertValidation(String path, String workBookName, String sheetName, String testCaseName) throws Exception {
		this.path = path;
		this.workBookName = workBookName;
		this.sheetName = sheetName;
		this.testCaseName = testCaseName;
		bulkExcelMap = excelData.readDataByColumn(this.path, this.workBookName, this.sheetName, this.testCaseName);
		excelHolderObj = new ExcelHolder(bulkExcelMap);
		colSize = excelHolderObj.totalColumns();
	}

	/*
	 * Constructor : Initialising the excel
	 * 
	 * @Param : occurence of test case in a sheet
	 */
	public AlertValidation(String path, String workBookName, String sheetName, String testCaseName, int occurence)
			throws Exception {
		this.path = path;
		this.workBookName = workBookName;
		this.sheetName = sheetName;
		this.testCaseName = testCaseName;
		this.occurence = occurence;
		bulkExcelMap = excelData.readDataByColumn(this.path, this.workBookName, this.sheetName, this.testCaseName,
				this.occurence);
		excelHolderObj = new ExcelHolder(bulkExcelMap);
		colSize = excelHolderObj.totalColumns();
	}
	
	/*
	 * Configuring the Alert
	 * 
	 */
	public void alertInsatnceValdiation() throws Exception {
		try {
			GenericHelper.waitForLoadmask();
			for (paramVal = 0; paramVal < colSize; paramVal++) {
				alertMap = excelHolderObj.dataMap(paramVal);
				GenericHelper.waitForLoadmask(searchScreenWaitSec);
				alertObj.verifyAlertInstance(path, workBookName, sheetName, testCaseName, occurence);
				

		} }catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}	
}
