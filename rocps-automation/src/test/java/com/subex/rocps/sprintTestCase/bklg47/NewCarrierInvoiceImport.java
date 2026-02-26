package com.subex.rocps.sprintTestCase.bklg47;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import com.mchange.io.FileUtils;
import com.subex.automation.helpers.application.NavigationHelper;
import com.subex.automation.helpers.component.ButtonHelper;
import com.subex.automation.helpers.component.ElementHelper;
import com.subex.automation.helpers.component.FileHelper;
import com.subex.automation.helpers.component.GenericHelper;
import com.subex.automation.helpers.component.GridHelper;
import com.subex.automation.helpers.component.SearchGridHelper;
import com.subex.automation.helpers.config.OR_Reader;
import com.subex.automation.helpers.file.ExcelReader;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;
import com.subex.rocps.automation.utils.ExcelHolder;

public class NewCarrierInvoiceImport extends PSAcceptanceTest {

	ExcelReader excelData = null;
	Map<String, ArrayList<String>> CIExcelMap = null;
	Map<String, String> ciMap = null;
	ExcelHolder excelHolderObj = null;
	OR_Reader orData = new OR_Reader();
	String path;
	String workBookName;
	String sheetName;
	String testCaseName;
	List<String> columnList = null;

	int colSize;
	public NewCarrierInvoiceImport(String path, String workBookName, String sheetName, String testCaseName)
			throws Exception {
		this.path = path;
		this.workBookName = workBookName;
		this.sheetName = sheetName;
		this.testCaseName = testCaseName;
		excelData = new ExcelReader();
		CIExcelMap = excelData.readDataByColumn(this.path, this.workBookName, this.sheetName, this.testCaseName);
		excelHolderObj = new ExcelHolder(CIExcelMap);
		colSize = excelHolderObj.totalColumns();

	}
		/*This Method is to verify the Status grid is present
		 * 
		 * 
		 */
	public void newImport() throws Exception {

		/*for (int paramVal = 0; paramVal < colSize; paramVal++)
		{
			
			/ciMap = excelHolderObj.dataMap(paramVal);*/
			//int i = 0;
		NavigationHelper.navigateToScreen("Carrier Invoice Import");
			//GenericHelper.waitForLoadmask();
			//i = SearchGridHelper.gridFilterSearchWithComboBox(or.getProperty("Modified_user"), "Root" , "Modified User");
			//System.out.println(i);*/
		NavigationHelper.navigateToAction("Common Tasks", "New");
		ElementHelper.click(ElementHelper.getElement(or.getProperty("File_selection_ID")));
		GenericHelper.waitForLoadmask();
		FileHelper.fileUploadRobot("//Form[@id='scucFilePath']/table/tbody/tr//input)[1]","C:\\10.5.4\\Reconc76_80_01.xlsx");
		ElementHelper.click(ElementHelper.getElement(or.getProperty("Template_selection_id")));
		}
	}
