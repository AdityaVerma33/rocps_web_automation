package com.subex.rocps.sprintTestCase.bklg91;


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
	import com.subex.automation.helpers.util.FailureHelper;
	import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;
	import com.subex.rocps.automation.utils.ExcelHolder;

	public class CarrierInvoiceSearchGrid extends PSAcceptanceTest {

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

		@Test
		public CarrierInvoiceSearchGrid(String path, String workBookName, String sheetName, String testCaseName)
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
			/*This Method is to verify the grid functionality
			 * 
			 * 
			 */
		public void newImport() throws Exception {
			
			try{

			NavigationHelper.navigateToScreen("Carrier Invoice");
			GenericHelper.waitForLoadmask();
			SearchGridHelper.gridFilterSearchWithComboBox(or.getProperty("Modified_user"), "Root" , "Modified User");
			SearchGridHelper.gridFilterSearchWithComboBox(or.getProperty("Status"), "Draft" , "Status");
			SearchGridHelper.gridFilterSearchWithComboBox(or.getProperty("Task_Status"), "Completed" , "Task Status");
			} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
			}
			
			
			
		}
	}

