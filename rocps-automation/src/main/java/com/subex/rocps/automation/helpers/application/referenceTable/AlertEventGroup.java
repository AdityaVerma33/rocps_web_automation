package com.subex.rocps.automation.helpers.application.referenceTable;

import java.util.ArrayList;
import java.util.Map;

import com.subex.automation.helpers.application.NavigationHelper;
import com.subex.automation.helpers.component.GenericHelper;
import com.subex.automation.helpers.component.GridHelper;
import com.subex.automation.helpers.data.ValidationHelper;
import com.subex.automation.helpers.file.ExcelReader;
import com.subex.automation.helpers.report.Log4jHelper;
import com.subex.rocps.automation.helpers.application.admin.alertEvent.AlertDetailsImpl;
import com.subex.rocps.automation.helpers.application.admin.alertEvent.AlertReferenceTableImpl;
import com.subex.rocps.automation.helpers.application.admin.alertEvent.EntityAddedDeletedImpl;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;
import com.subex.rocps.automation.utils.ExcelHolder;

public class AlertEventGroup extends PSAcceptanceTest{
	protected ExcelReader excelData = new ExcelReader();
	protected Map<String, ArrayList<String>> bulkExcelMap = null;
	protected Map<String, ArrayList<String>> excelReaderMapObj = null;
	protected AlertDetailsImpl detailsObj = new AlertDetailsImpl();
	protected EntityAddedDeletedImpl entityObj = new EntityAddedDeletedImpl();
	protected Map<String, String> alertMap = null;
	protected ExcelHolder excelHolderObj = null;
	Map<String, ArrayList<String>> configMap = null;
	protected Map<String, String> mapObj = null;
	protected AlertReferenceTableImpl refObj = new AlertReferenceTableImpl();
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
	String group;
	String isActive;
	
	/*
	 * Constructor : Initialising the excel
	 */
	public AlertEventGroup(String path, String workBookName, String sheetName, String testCaseName) throws Exception {
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
	public AlertEventGroup(String path, String workBookName, String sheetName, String testCaseName, int occurence)
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


	public void alertEventGroupConfiguration() throws Exception{
		GenericHelper.waitForLoadmask();
		NavigationHelper.navigateToReferenceTable("Alert Event Group");
		for (paramVal = 0; paramVal < colSize; paramVal++) {
			alertMap = excelHolderObj.dataMap(paramVal);
			GenericHelper.waitForLoadmask(searchScreenWaitSec);	
			initializeVariables(alertMap);
			if(!isGroupPresent()) {
				refObj.configureAlertEventGroup(partition, group, isActive);
				Log4jHelper.logInfo( "Alert Event Group "+group+" created." );}
			else
				Log4jHelper.logInfo("Alert Event Group "+group+ " is already Present");
			
		}
	}
	
	public boolean isGroupPresent() throws Exception{
		return (GridHelper.isValuePresent("searchGrid", group));
	}

	
	public void initializeVariables(Map<String,String> map) throws Exception{
		partition= ExcelHolder.getKey(map, "Partition");
		group= ExcelHolder.getKey(map, "GroupName");
		isActive= ExcelHolder.getKey(map, "IsActive");
	}
	
}
