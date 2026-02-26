package com.subex.rocps.sprintTestCase.bklg201;

import com.subex.automation.helpers.file.ExcelReader;
import com.subex.automation.helpers.report.Log4jHelper;
import com.subex.rocps.automation.helpers.application.genericHelpers.PSGenericHelper;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;
import com.subex.rocps.automation.utils.ExcelHolder;
import com.subex.automation.helpers.component.ButtonHelper;
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
import com.subex.automation.helpers.component.MouseHelper;
import com.subex.automation.helpers.component.SearchGridHelper;
import com.subex.automation.helpers.component.TextBoxHelper;
import org.openqa.selenium.WebElement;

@SuppressWarnings("unused")

public class imsiManagementExecution extends PSAcceptanceTest {
	protected ExcelReader excelData = new ExcelReader();
	protected Map<String, ArrayList<String>> imsiDataMap = null;
	protected Map<String, String> imsiMap = null;
	protected ExcelHolder excelHolderObj = null;
	protected PSGenericHelper genHelperObj = new PSGenericHelper();
	String path;
	String workBookName;
	String sheetName;
	String testCaseName;
	int colSize;
	int paramVal;
	private String clientPartition;
	public static String typesFromExcel;
	public static String ValesFromExcel;
	protected String imsiName = "QWERT";
	public static String roamingDefinition;
	public static String imsiSearchColumns;

	@org.testng.annotations.Test(priority = 1)

	public void Imsi() throws Exception {
		try {
			path = System.getProperty("user.dir") + "\\src\\main\\resources\\";
			workBookName = "TestData.xlsx";
			sheetName = "IMSI_sheet6";
			testCaseName = "IMSI_create";
			imsiDataMap = excelData.readDataByColumn(this.path, this.workBookName, this.sheetName, this.testCaseName);
			excelHolderObj = new ExcelHolder(imsiDataMap);
			colSize = excelHolderObj.totalColumns();

			for (paramVal = 0; paramVal < colSize; paramVal++) {
				imsiMap = excelHolderObj.dataMap(paramVal);
				typesFromExcel = imsiMap.get("Type");
				ValesFromExcel = imsiMap.get("Value");
				roamingDefinition = imsiMap.get("Roaming_Definition");
				imsiManagement imsiObj = new imsiManagement();
				NavigationHelper.navigateToScreen("IMSI Management");
				MouseHelper.click("//div[contains(text(),'Advanced Search')]");
				imsiObj.filterStatus(roamingDefinition);
				GenericHelper.waitForLoadmask();
				
				GridHelper.clickRow("Detail_popUpWindowId", "1","MCC");
				ButtonHelper.click("ok");
				boolean isIMSIPresent = GridHelper.isValuePresent("RootPanel", roamingDefinition, "Roaming Definition");

				if (!isIMSIPresent) {
					imsiObj.createNewImsiManagement();
					Log4jHelper.logInfo("IMSI is created successfully with name " + imsiName);

				} else {
					Log4jHelper.logInfo("IMSI is available with name " + imsiName);
				}

			}

		}

		catch (Exception e) {
			FailureHelper.reportFailure(e);
			throw e;
		}
	}
}
