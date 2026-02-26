package com.subex.rocps.sprintTestCase.bklg201;

import com.subex.automation.helpers.file.ExcelReader;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;
import com.subex.rocps.automation.utils.ExcelHolder;
import com.subex.automation.helpers.component.CheckBoxHelper;
import com.subex.automation.helpers.component.ElementHelper;
import com.subex.automation.helpers.file.ExcelReader;
import com.subex.automation.helpers.util.FailureHelper;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import com.subex.automation.helpers.application.NavigationHelper;
import com.subex.automation.helpers.component.GenericHelper;
import com.subex.automation.helpers.component.GridHelper;
import com.subex.automation.helpers.component.TextBoxHelper;
import org.openqa.selenium.WebElement;

@SuppressWarnings("unused")


public class imsiManagementExecution2 extends PSAcceptanceTest {
	protected ExcelReader excelData = new ExcelReader();
	protected Map<String, ArrayList<String>> imsisearchDatamap = null;
	protected Map<String, String> imsisearchMap = null;
	protected ExcelHolder excelHolderObj = null;
	String path;
	String workBookName;
	String sheetName;
	String testCaseName;
	int colSize;
	int paramVal;
	public static String imsiSearchColumns;

	public void imsiManagementExecution2() throws Exception {
		try {
			path = System.getProperty("user.dir") + "\\src\\main\\resources\\";
			workBookName = "TestData.xlsx";
			sheetName = "roaming_sheet5";
			testCaseName = "taperrorsearch";
			imsisearchDatamap = excelData.readDataByColumn(this.path, this.workBookName, this.sheetName,
					this.testCaseName);
			excelHolderObj = new ExcelHolder(imsisearchDatamap);
			colSize = excelHolderObj.totalColumns();

			for (paramVal = 0; paramVal < colSize; paramVal++) {
				imsisearchMap = excelHolderObj.dataMap(paramVal);
				
				imsiSearchColumns = imsisearchMap.get("IMSI Search Columns");
				imsiSearch imsiObj = new imsiSearch();
				imsiObj.imsiSearch();
				
			}

		} catch (Exception e) {
			FailureHelper.reportFailure(e);
			throw e;
		}
	}
}