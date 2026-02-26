package com.subex.rocps.sprintTestCase.bklg296;

import java.util.ArrayList;
import java.util.Map;

import org.testng.annotations.Test;

import com.subex.automation.helpers.application.NavigationHelper;
import com.subex.automation.helpers.component.ButtonHelper;
import com.subex.automation.helpers.component.GenericHelper;
import com.subex.automation.helpers.component.GridHelper;
import com.subex.automation.helpers.config.OR_Reader;
import com.subex.automation.helpers.data.DateHelper;
import com.subex.automation.helpers.file.ExcelReader;
import com.subex.automation.helpers.report.Log4jHelper;
import com.subex.rocps.automation.helpers.application.genericHelpers.DataVerificationHelper;
import com.subex.rocps.automation.helpers.application.genericHelpers.PSGenericHelper;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;
import com.subex.rocps.automation.utils.ExcelHolder;

public class ImfExchangeRateImportScreen extends PSAcceptanceTest {
	OR_Reader orData = new OR_Reader();
	
	protected PSGenericHelper genericHelperObj = new PSGenericHelper();
	protected ExcelReader excelData = new ExcelReader();
	protected Map<String, ArrayList<String>> imfImportExcelMap= null;
	protected Map<String, String> imfImportMap = null;
	protected ExcelHolder excelHolderObj = null;
	String path;
	String workBookName;
	String sheetName;
	String testCaseName;
	int colSize;
	int paramVal;
	String columnHeader;
	String mapRowKeys;
	String fileName;

	public ImfExchangeRateImportScreen(String path, String workBookName, String sheetName, String testCaseName)
			throws Exception {
		this.path = path;
		this.workBookName = workBookName;
		this.sheetName = sheetName;
		this.testCaseName = testCaseName;
		excelData = new ExcelReader();
		imfImportExcelMap = excelData.readDataByColumn(this.path, this.workBookName, this.sheetName, this.testCaseName);
		excelHolderObj = new ExcelHolder(imfImportExcelMap);
		colSize = excelHolderObj.totalColumns();
	}


	public void verifyPopupGrid() throws Exception {
		String currDate=DateHelper.getCurrentDateTime("ddMMyyyy");
		String fileName="rms_five_"+currDate+".tsv";
		
		GenericHelper.waitForLoadmask();
		imfImportMap=excelHolderObj.dataMap(0);
		initializeVariable(imfImportMap);
		NavigationHelper.navigateToScreen("IMF Exchange Rate Import");
		GenericHelper.waitForLoadmask();
		boolean isFilePresent=genericHelperObj.isGridTextValuePresent("ImfImportScreen_filterText",fileName ,"Downloaded File Name");
		if(isFilePresent) {
		GridHelper.clickRow("ImfImportScreen_Grid", 1,1);
		NavigationHelper.navigateToAction("View", "Exchange Rates");
			
		GenericHelper.waitInSeconds("4");
			DataVerificationHelper verifyData=new DataVerificationHelper();
			verifyData.validateData("grid_column_header_undefined_", imfImportMap,"ImfImportScreen_PopUpGridId",columnHeader,mapRowKeys);
			ButtonHelper.click("imfExchangeRateDetailDetail.cancel");
		
		}
		else {
			Log4jHelper.logInfo("File not Found");
		}
		
		
		
		
	}
	
	public void initializeVariable(Map<String,String> map) throws Exception{
		try {
//			fileName=ExcelHolder.getKey(map, "FileName");
			columnHeader=ExcelHolder.getKey(map, "ColmnHeaders");
			mapRowKeys=ExcelHolder.getKey(map, "MapRowKeys");
			
			
		}
		catch(Exception e) {
			
		}
	}
}