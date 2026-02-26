package com.subex.rocps.automation.helpers.application.bulkentityexport;

import java.io.File;
import java.util.ArrayList;
import java.util.Map;

import com.subex.automation.helpers.application.NavigationHelper;
import com.subex.automation.helpers.component.GenericHelper;
import com.subex.automation.helpers.component.GridHelper;
import com.subex.automation.helpers.data.ValidationHelper;
import com.subex.automation.helpers.file.ExcelReader;
import com.subex.automation.helpers.report.Log4jHelper;
import com.subex.automation.helpers.util.FailureHelper;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;
import com.subex.rocps.automation.utils.ExcelHolder;
import com.subex.rocps.automation.utils.PSFileValidationHelper;

public class BulkEntityExport extends PSAcceptanceTest {

	protected ExcelReader excelData = new ExcelReader();
	protected Map<String, ArrayList<String>> bulkExcelMap = null;
	protected Map<String, ArrayList<String>> excelReaderMapObj = null;

	protected Map<String, String> bulkExpMap = null;
	protected ExcelHolder excelHolderObj = null;
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
	String name;
	String addEntity;
	String addItem;
	String dependentEntities;
	String exportAll;
	String saveAndExport;
	String extension;
	String exportDirectory;
	String filePath;

	/*
	 * Constructor : Initialising the excel
	 */
	public BulkEntityExport(String path, String workBookName, String sheetName, String testCaseName) throws Exception {
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
	public BulkEntityExport(String path, String workBookName, String sheetName, String testCaseName, int occurence)
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
	 * Configuring the Bulk Entity Export
	 * 
	 */
	public void bulkEntityExportCreation() throws Exception {
		try {
			GenericHelper.waitForLoadmask();
			NavigationHelper.navigateToScreen("Bulk Entity Export");
			for (paramVal = 0; paramVal < colSize; paramVal++) {
				bulkExpMap = excelHolderObj.dataMap(paramVal);
				initializeInstanceVariables(bulkExpMap);
				GenericHelper.waitForLoadmask(searchScreenWaitSec);
				if (!isExportPresent()) {
					BulkEntityExportImpl bulkImplObj = new BulkEntityExportImpl(bulkExpMap, addEntity, exportAll,
							addItem, dependentEntities);
					BulkEntityFileHelper bulkFileObj = new BulkEntityFileHelper();
					bulkImplObj.newBulkEntityExport(partition);
					bulkImplObj.configureBulkEntityExport();
					bulkImplObj.saveAction(saveAndExport);
				} else {
					Log4jHelper.logInfo("Export is already available for " + name);
				}
			}

		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}

	/*
	 * RUnning the task and validating data
	 * 
	 */
	public void bulkEntityScheduler() throws Exception {
		try {
			if (isExportPresent() && isTaskCompleted()) {
				Log4jHelper.logInfo("Export Completed");
			} else {
				GenericHelper.waitForLoadmask();
				NavigationHelper.navigateToScreen("Bulk Entity Export");
				for (paramVal = 0; paramVal < colSize; paramVal++) {
					if (isExportPresent())
						bulkExpMap = excelHolderObj.dataMap(paramVal);
					initializeInstanceVariables(bulkExpMap);
					GenericHelper.waitForLoadmask(searchScreenWaitSec);
					BulkEntityExportImpl bulkImplObj = new BulkEntityExportImpl(bulkExpMap, addEntity, exportAll,
							addItem, dependentEntities);
					BulkEntityFileHelper bulkFileObj = new BulkEntityFileHelper();
					PSFileValidationHelper fileObj = new PSFileValidationHelper();
					if (!ValidationHelper.isTrue(saveAndExport)) {
						bulkImplObj.scheduleTask();
					}
				}

			}
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}

	public void bulkEntityMonitoring() throws Exception {
		try {
			if (isExportPresent() && isTaskCompleted()) {
				Log4jHelper.logInfo("Export Completed");
			} else {
				GenericHelper.waitForLoadmask();
				NavigationHelper.navigateToScreen("Bulk Entity Export");
				for (paramVal = 0; paramVal < colSize; paramVal++) {
					if (isExportPresent())
						bulkExpMap = excelHolderObj.dataMap(paramVal);
					initializeInstanceVariables(bulkExpMap);
					GenericHelper.waitForLoadmask(searchScreenWaitSec);
					BulkEntityExportImpl bulkImplObj = new BulkEntityExportImpl(bulkExpMap, addEntity, exportAll,
							addItem, dependentEntities);
					BulkEntityFileHelper bulkFileObj = new BulkEntityFileHelper();
					PSFileValidationHelper fileObj = new PSFileValidationHelper();
					 bulkImplObj.validateNewExport();
					
				}

			}
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}

	public void bulkEntityExportFileValidation() throws Exception {
		try {

			GenericHelper.waitForLoadmask();
			NavigationHelper.navigateToScreen("Bulk Entity Export");
			for (paramVal = 0; paramVal < colSize; paramVal++) {
				BulkEntityExportImpl bulkImplObj = new BulkEntityExportImpl(bulkExpMap, addEntity, exportAll, addItem,
						dependentEntities);
				PSFileValidationHelper fileObj = new PSFileValidationHelper();
				fileObj.validatePartialFileNameInDir(exportDirectory, name, extension);
				fileObj.unzipZipFile(exportDirectory, name);
				bulkImplObj.validateExtractedFiles(exportDirectory, name);
			}

		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}

	}

	public boolean isExportPresent() throws Exception {
		ArrayList<String> list = GridHelper.getColumnValues("searchGrid", "Bulk Export Name");
		if (list.contains(name)) {
			return true;
		} else {
			return false;
		}
	}

	public boolean isTaskCompleted() throws Exception {
		int number = GridHelper.getRowNumber("searchGrid", name);
		ArrayList<String> list = GridHelper.getRowValues("searchGrid", number);
		String str1 = list.get(3).replaceAll("\\s+", "");
		boolean flag = str1.equals("Completed");
		return flag;
	}

	public void initializeInstanceVariables(Map<String, String> bulkMap) throws Exception {
		partition = ExcelHolder.getKey(bulkMap, "Partition");
		name = ExcelHolder.getKey(bulkMap, "Name");
		addEntity = ExcelHolder.getKey(bulkMap, "AddEntity");
		addItem = ExcelHolder.getKey(bulkMap, "AddItem");
		exportAll = ExcelHolder.getKey(bulkMap, "ExportAll");
		dependentEntities = ExcelHolder.getKey(bulkMap, "DependentEntities");
		saveAndExport = ExcelHolder.getKey(bulkMap, "SaveAndExport");
		extension = ExcelHolder.getKey(bulkMap, "Extension");
		exportDirectory = ExcelHolder.getKey(bulkMap, "ExportDirectory");

	}

}
