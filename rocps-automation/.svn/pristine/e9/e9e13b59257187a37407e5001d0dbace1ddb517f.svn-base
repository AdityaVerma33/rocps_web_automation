package com.subex.rocps.automation.helpers.application.exchangeRates;

import java.util.ArrayList;
import java.util.Map;

import com.subex.rocps.automation.helpers.application.exchangeRates.imfExcRateImport.ImfExchRateImportActImpl;
import com.subex.rocps.automation.helpers.application.genericHelpers.PSActionImpl;
import com.subex.rocps.automation.helpers.application.genericHelpers.PSGenericHelper;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;
import com.subex.rocps.automation.utils.ExcelHolder;
import com.subex.rocps.automation.utils.PSStringUtils;

import com.subex.automation.helpers.application.NavigationHelper;
import com.subex.automation.helpers.component.ButtonHelper;
import com.subex.automation.helpers.component.GenericHelper;
import com.subex.automation.helpers.component.GridHelper;
import com.subex.automation.helpers.data.DateHelper;
import com.subex.automation.helpers.file.ExcelReader;
import com.subex.automation.helpers.report.Log4jHelper;
import com.subex.automation.helpers.util.FailureHelper;

public class ImfExchangeRateImport extends PSAcceptanceTest {
	protected ExcelReader excelData = new ExcelReader();
	protected Map<String, ArrayList<String>> imfExchRateImportExcelMap = null;
	protected Map<String, String> imfExchRateImportMap = null;
	protected ExcelHolder excelHolderObj = null;
	protected String path;
	protected String workBookName;
	protected String sheetName;
	protected String testCaseName;
	protected int occurence;
	protected int colSize;
	protected String clientPartition;
	protected String colmHdrs;

	protected int index;
	protected PSGenericHelper psGenericHelper = new PSGenericHelper();
	PSStringUtils psStringUtils = new PSStringUtils();
	PSActionImpl psActionImpl = new PSActionImpl();

	/**
	 * Constructor : Initializing the excel without occurrence
	 * 
	 * @param path
	 * @param workBookName
	 * @param sheetName
	 * @param testCaseName
	 * @throws Exception
	 */
	public ImfExchangeRateImport(String path, String workBookName, String sheetName, String testCaseName)
			throws Exception {
		this.path = path;
		this.workBookName = workBookName;
		this.sheetName = sheetName;
		this.testCaseName = testCaseName;
		imfExchRateImportExcelMap = excelData.readDataByColumn(path, workBookName, sheetName, testCaseName);
		excelHolderObj = new ExcelHolder(imfExchRateImportExcelMap);
		colSize = excelHolderObj.totalColumns();
	}

	/**
	 * Constructor : Initializing the excel with occurrence
	 * 
	 * @param path
	 * @param workBookName
	 * @param sheetName
	 * @param testCaseName
	 * @param occurence
	 * @throws Exception
	 */
	public ImfExchangeRateImport(String path, String workBookName, String sheetName, String testCaseName, int occurence)
			throws Exception {
		this.path = path;
		this.workBookName = workBookName;
		this.sheetName = sheetName;
		this.testCaseName = testCaseName;
		this.occurence = occurence;
		imfExchRateImportExcelMap = excelData.readDataByColumn(path, workBookName, sheetName, testCaseName, occurence);
		excelHolderObj = new ExcelHolder(imfExchRateImportExcelMap);
		colSize = excelHolderObj.totalColumns();
	}

	/*
	 * This method is for initialize variable
	 */
	private void initializeVariable(Map<String, String> map) throws Exception {

		clientPartition = ExcelHolder.getKey(map, "Partition");
	}

	/*
	 * This method is for 'IMF Exchange Rate Import' screen common method
	 */
	private void imfExchRateImportScreen() throws Exception {
		NavigationHelper.navigateToScreen("IMF Exchange Rate Import");
		GenericHelper.waitForLoadmask(searchScreenWaitSec);
		imfExchRateImportMap = excelHolderObj.dataMap(index);
		ButtonHelper.click("ClearButton");
		psGenericHelper.waitforHeaderElement("Task Status");
	}

	/*
	 * This method is for 'IMF Exchange Rate Import' screen column validation
	 */
	public void imfExchRateImportColumsValidation() throws Exception {
		try {
			for (index = 0; index < colSize; index++) {
				imfExchRateImportScreen();
				colmHdrs = ExcelHolder.getKey(imfExchRateImportMap, "SearchScreenColumns");
				psGenericHelper.screenColumnValidation("Task Status", colmHdrs, "IMF Exchange Rate Import");
			}
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}

	}

	// Method: Validate the search results of 'IMF Exchange Rate Import' screen
	public void validateImfExchRateImpSearchResult() throws Exception {
		try {
			for (index = 0; index < colSize; index++) {
				String currDate = DateHelper.getCurrentDateTime("ddMMyyyy");
				String importedFileName = "rms_five_" + currDate + ".tsv";
				imfExchRateImportScreen();
				ButtonHelper.click("SearchButton");
				psGenericHelper.waitforHeaderElement("Task Status");
				boolean isImfExchRateImpPresent = isImfImportedFlPresent(importedFileName);
				assertTrue(isImfExchRateImpPresent, "Imf Exchange Rate Import data is not found in search screen with '"+importedFileName);
				GenericHelper.waitForLoadmask(searchScreenWaitSec);
				GridHelper.clickRow("SearchGrid", importedFileName, "Imported File Name");
				GenericHelper.waitForLoadmask(searchScreenWaitSec);
				ImfExchRateImportActImpl imExchRateImportActImpl = new ImfExchRateImportActImpl();
				imExchRateImportActImpl.clickOnExchangeRatesAction(imfExchRateImportMap);

			}
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}

	/*
	 * This method is used to check Imported file name is present or not
	 */
	private boolean isImfImportedFlPresent(String importedFileName) throws Exception {

		return psGenericHelper.isGridTextValuePresent("PS_Detail_imfExchRateImp_impFlName_textId", importedFileName,
				"Imported File Name");

	}
}
