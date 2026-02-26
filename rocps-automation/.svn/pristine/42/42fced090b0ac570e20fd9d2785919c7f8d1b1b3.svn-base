package com.subex.rocps.sprintTestCase.bklg44;

import java.util.ArrayList;
import java.util.Map;

import com.subex.rocps.automation.helpers.application.genericHelpers.PSGenericHelper;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;
import com.subex.rocps.automation.utils.ExcelHolder;

import com.subex.automation.helpers.component.CheckBoxHelper;
import com.subex.automation.helpers.file.ExcelReader;
import com.subex.automation.helpers.util.FailureHelper;

@SuppressWarnings("unused")

public class Dealcaseexex extends PSAcceptanceTest {
	protected ExcelReader excelData = new ExcelReader();
	protected Map<String, ArrayList<String>> dealDataMap = null;
	protected Map<String, String> dealMap = null;
	protected ExcelHolder excelHolderObj = null;
	String path;
	String workBookName;
	String sheetName;
	String testCaseName;
	int colSize;
	int paramVal;
	String fromDate = null;
	String toDate = null;
	String Copy_Rates;
	String Validate_Deal;
	String Deal_Name;
	String band_Name;

	protected PSGenericHelper genHelperObj = new PSGenericHelper();

	@org.testng.annotations.Test(priority = 1)
	public void dealcopyCreation() throws Exception {
		try {
			path = System.getProperty("user.dir") + "\\src\\main\\resources\\";
			workBookName = "TestData.xlsx";
			sheetName = "Dealcopy_sheet3";
			testCaseName = "CopyDealCreation";
			dealDataMap = excelData.readDataByColumn(this.path, this.workBookName, this.sheetName, this.testCaseName);
			excelHolderObj = new ExcelHolder(dealDataMap);
			colSize = excelHolderObj.totalColumns();

			for (paramVal = 0; paramVal < colSize; paramVal++) {
				dealMap = excelHolderObj.dataMap(paramVal);
				Deal_Name = dealMap.get("deal name");
				band_Name = dealMap.get("band name");
				fromDate = dealMap.get("From");
				toDate = dealMap.get("To");
				Copy_Rates = dealMap.get("Copy Rates");
				Validate_Deal = dealMap.get("Validate Deal");

				boolean valid;
				Deal dealObj = new Deal();
				dealObj.dealcopyAction(Deal_Name, band_Name);

				valid = dealObj.checkDateValidation(fromDate, toDate);

				if (valid) {

					boolean copyRatesCheck = Boolean.valueOf(Copy_Rates);
					boolean validateDealCheck = Boolean.valueOf(Validate_Deal);

					if (copyRatesCheck == true && validateDealCheck == false) {
						CheckBoxHelper.uncheck("validateDealFl_InputElement");
						dealObj.dealCopyRatesCreation(fromDate, toDate);

					} else if (copyRatesCheck == false && validateDealCheck == false) {

						CheckBoxHelper.uncheck("Copy_DealRates_Flag");
						boolean isDisabledfl = CheckBoxHelper.isDisabled("validateDealFl_InputElement");
						boolean isUncheckedFl = CheckBoxHelper.isNotChecked("validateDealFl_InputElement");
						assertTrue(isDisabledfl, "Checkbox is not disabled");
						assertTrue(isUncheckedFl, "Checkbox is not unchecked");
						dealObj.dealcopyCreation(fromDate, toDate);

					} else if (copyRatesCheck == true && validateDealCheck == true) {
						dealObj.dealCopyRatesCreation(fromDate, toDate);

					}
				} else {
					dealObj.dealCopyDateValidation(fromDate, toDate);
				}

			}
		} catch (Exception e) {
			FailureHelper.reportFailure(e);
			throw e;
		}
	}
}