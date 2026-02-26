package com.subex.rocps.sprintTestCase.bklg33;

import java.util.ArrayList;
import java.util.Map;

import com.subex.automation.helpers.application.NavigationHelper;
import com.subex.automation.helpers.component.ButtonHelper;
import com.subex.automation.helpers.component.CheckBoxHelper;
import com.subex.automation.helpers.component.ElementHelper;
import com.subex.automation.helpers.component.FileHelper;
import com.subex.automation.helpers.component.GenericHelper;
import com.subex.automation.helpers.component.GridHelper;
import com.subex.automation.helpers.component.TextBoxHelper;
import com.subex.automation.helpers.data.ValidationHelper;
import com.subex.automation.helpers.file.ExcelReader;
import com.subex.automation.helpers.util.FailureHelper;

import com.subex.rocps.automation.helpers.application.genericHelpers.PSGenericHelper;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;
import com.subex.rocps.automation.utils.ExcelHolder;

public class ratesheetImportFuturePeriodUpdate extends PSAcceptanceTest {
	protected ExcelReader excelData = new ExcelReader();
	protected Map<String, ArrayList<String>> ratesheetDataMap = null;
	protected Map<String, String> rateMap = null;
	protected ExcelHolder excelHolderObj = null;

	int colSize;
	int paramVal;

	protected PSGenericHelper genericHelperObj = new PSGenericHelper();

	int expectedDecimalPlace;
	Map<String, String> dpMap = null;
	String clientPartition;
	String fromDate;
	String toDate;
	String tariff_name;
	String file_name;
	String complete;
	String expirePriorElements;
	String updateAllFuturePeriods;

	public ratesheetImportFuturePeriodUpdate() throws Exception {

	}

	public void rateSheetFuturePeriodUpdate(String Path, String WorkbookName, String sheetName, String testCaseName) throws Exception {
		try {

			ratesheetDataMap = excelData.readDataByColumn(Path, WorkbookName, sheetName, testCaseName);

			excelHolderObj = new ExcelHolder(ratesheetDataMap);
			colSize = excelHolderObj.totalColumns();

			for (paramVal = 0; paramVal < colSize; paramVal++) {
				rateMap = excelHolderObj.dataMap(paramVal);
				tariff_name = rateMap.get("Tariff");
				file_name = rateMap.get("File");
				fromDate = rateMap.get("complete expire date");
				toDate = rateMap.get("origin complete expire date");
				complete = rateMap.get("complete ratesheet");
				expirePriorElements = rateMap.get("expire prior elements");
				updateAllFuturePeriods = rateMap.get("Update All Future Periods");

				NavigationHelper.navigateToScreen("Rate Sheet Import Request");
				GenericHelper.waitForLoadmask();
				NavigationHelper.navigateToAction("Import", "Import Rate Sheet");
				GenericHelper.waitForLoadmask();

				ElementHelper.click(or.getProperty("PS_RateSheet_Import_Detail_Tariff"));
				GenericHelper.waitForLoadmask();
				TextBoxHelper.type(or.getProperty("PS_RateSheet_Import_Detail_Tariff_Name"), tariff_name);
				ElementHelper.click("//button[@id='search']");
				GenericHelper.waitForLoadmask();

				GridHelper.clickRow("popupWindow", "searchGrid", 1, "Tariff Name");

				ButtonHelper.click("//input[@id='tffName']/ancestor::div[@id='popupWindow']//button[@id='ok']");
				GenericHelper.waitForLoadmask();
				GenericHelper.waitInSeconds("2");

				ElementHelper.click("//button[@id='fileChooserGroupLoad']");

				FileHelper.fileUploadRobot("//*[@id='File Upload']//div", file_name);
				GenericHelper.waitInSeconds("5");
				ButtonHelper.click("//button[@id='FileUpload-upload']");

				assertTrue(CheckBoxHelper.isEnabled("//input[@id='prirCompleteSheet_InputElement']"));
				assertTrue(CheckBoxHelper.isEnabled("//input[@id='prirExpirePriorElementFl_InputElement']"));

				assertTrue(CheckBoxHelper.isNotChecked("//input[@id='prirCompleteSheet_InputElement']"));
				assertTrue(CheckBoxHelper.isEnabled("//input[@id='prirUpdateFutPeriodFl_InputElement']"));
				assertTrue(CheckBoxHelper.isNotChecked("//input[@id='prirUpdateFutPeriodFl_InputElement']"));
				if (ValidationHelper.isTrue(complete)) {
					CheckBoxHelper.check("//input[@id='prirCompleteSheet_InputElement']");
					GenericHelper.waitInSeconds("5");
					
					assertTrue(CheckBoxHelper.isNotChecked("//input[@id='prirExpirePriorElementFl_InputElement']"));

					assertTrue(CheckBoxHelper.isDisabled("//input[@id='prirExpirePriorElementFl_InputElement']"));

					CheckBoxHelper.check("//input[@id='prirUpdateFutPeriodFl_InputElement']");
					GenericHelper.waitInSeconds("5");
				}
				TextBoxHelper.type(
						"//table[@id='prirExpireDttm']//input[@class='gwt-DateBox roc-field roc-trigger-field roc-datefield']",
						fromDate);

				TextBoxHelper.type(
						"//table[@id='prirOrgExpireDttm']//input[@class='gwt-DateBox roc-field roc-trigger-field roc-datefield']",
						toDate);

				if (ValidationHelper.isTrue(expirePriorElements)) {
					CheckBoxHelper.check("//input[@id='prirExpirePriorElementFl_InputElement']");
					assertTrue(TextBoxHelper.isDisabled("prirExpireDttm"));
					assertTrue(TextBoxHelper.isDisabled("prirOrgExpireDttm"));
					CheckBoxHelper.check("//input[@id='prirUpdateFutPeriodFl_InputElement']");
					GenericHelper.waitInSeconds("5");

				}

				GenericHelper.waitInSeconds("5");

				GridHelper.clickRow("dataLocationInfoGrid", 1, " Start  Row  Number");

				GridHelper.updateGridTextBox("dataLocationInfoGrid", "Ratesheet_import_start", 1, 2,
						"Start  Row  Number", "2");

				GridHelper.updateGridTextBox("dataLocationInfoGrid", "Ratesheet_import_end", 1, 3, "Start  Row  Number",
						"4");

				GridHelper.updateGridTextBox("dataLocationInfoGrid", "Ratesheet_import_start", 2, 2,
						"Start  Row  Number", "2");

				GridHelper.updateGridTextBox("dataLocationInfoGrid", "Ratesheet_import_end", 2, 3, "Start  Row  Number",
						"4");

				GridHelper.updateGridTextBox("dataLocationInfoGrid", "Ratesheet_import_start", 3, 2,
						"Start  Row  Number", "2");

				GridHelper.updateGridTextBox("dataLocationInfoGrid", "Ratesheet_import_end", 3, 3, "Start  Row  Number",
						"4");
				ButtonHelper.click("//button[@id='ratesheetImportRequestDetail.save']");
				GenericHelper.waitForLoadmask();

			}
		}

		catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}

	public void initializeInstanceVariables() {

		clientPartition = dpMap.get("ClientPartition");

	}
}
