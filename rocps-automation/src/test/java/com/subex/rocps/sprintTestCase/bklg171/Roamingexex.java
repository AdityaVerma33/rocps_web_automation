package com.subex.rocps.sprintTestCase.bklg171;

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

public class Roamingexex extends PSAcceptanceTest {
	protected ExcelReader excelData = new ExcelReader();
	protected Map<String, ArrayList<String>> roamingDataMap = null;
	protected Map<String, String> roamingMap = null;
	protected ExcelHolder excelHolderObj = null;
	String path;
	String workBookName;
	String sheetName;
	String testCaseName;
	int colSize;
	int paramVal;
	String fileName;
	String callType;
	public static String tapColumns;
	public static String RoamingRecordColumns;

	@org.testng.annotations.Test(priority = 1)
	public void roaming() throws Exception {
		try {
			path = System.getProperty("user.dir") + "\\src\\main\\resources\\";
			workBookName = "TestData.xlsx";
			sheetName = "roaming_sheet5";
			testCaseName = "taperrorsearch";
			roamingDataMap = excelData.readDataByColumn(this.path, this.workBookName, this.sheetName,
					this.testCaseName);
			excelHolderObj = new ExcelHolder(roamingDataMap);
			colSize = excelHolderObj.totalColumns();

			for (paramVal = 0; paramVal < colSize; paramVal++) {
				roamingMap = excelHolderObj.dataMap(paramVal);
				fileName = roamingMap.get("file Name");
				tapColumns = roamingMap.get("tap Columns");
				RoamingRecordColumns = roamingMap.get("roaming Columns");
				callType = roamingMap.get("call Type");
				roaming roamObj = new roaming();
				roamObj.roamingFileStatustoTAPError(fileName);
				roamObj.roamingFileStatustoRoamingRecords(fileName, callType);
			}

		} catch (Exception e) {
			FailureHelper.reportFailure(e);
			throw e;
		}
	}
}
