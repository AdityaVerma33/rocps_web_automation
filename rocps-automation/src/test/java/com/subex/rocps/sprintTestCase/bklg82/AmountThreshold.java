package com.subex.rocps.sprintTestCase.bklg82;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import com.subex.automation.helpers.application.NavigationHelper;
import com.subex.automation.helpers.component.ButtonHelper;
import com.subex.automation.helpers.component.ComboBoxHelper;
import com.subex.automation.helpers.component.ElementHelper;
import com.subex.automation.helpers.component.GenericHelper;
import com.subex.automation.helpers.component.GridHelper;
import com.subex.automation.helpers.component.SearchGridHelper;
import com.subex.automation.helpers.component.TextBoxHelper;
import com.subex.automation.helpers.config.OR_Reader;
import com.subex.automation.helpers.file.ExcelReader;
import com.subex.automation.helpers.report.Log4jHelper;
import com.subex.automation.helpers.util.FailureHelper;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;
import com.subex.rocps.automation.utils.ExcelHolder;

public class AmountThreshold extends PSAcceptanceTest {

	ExcelReader excelData = null;
	ExcelHolder excelHolderObj = null;
	Map<String, ArrayList<String>> amtThrMap = null;
	Map<String, String> amtMap = null;
	String path;
	String workBookName;
	String sheetName;
	String testCaseName;
	int colSize;
	int paramVal;
	OR_Reader orData = new OR_Reader();

	String amountThresholdName;
	String addCurrency;
	String copyCurrency;
	String currency;
	String effectiveDate;
	String name;
	String operator1;
	String value1;
	String operator2;
	String value2;
	String notifiication;
	String team;
	String currencyData;
	List<String> columnList = null;

	@Test
	public AmountThreshold(String path, String workBookName, String sheetName, String testCaseName) throws Exception {
		this.path = path;
		this.workBookName = workBookName;
		this.sheetName = sheetName;
		this.testCaseName = testCaseName;
		excelData = new ExcelReader();
		amtThrMap = excelData.readDataByColumn(this.path, this.workBookName, this.sheetName, this.testCaseName);
		excelHolderObj = new ExcelHolder(amtThrMap);
		colSize = excelHolderObj.totalColumns();
	}
	
	public void initializeVariables(List<String> columnList) throws Exception {
		amountThresholdName = amtMap.get("AmountThresholdName");
		currency = amtMap.get("Currency");
		effectiveDate = amtMap.get("EffectiveDate");
	}

	public void amountThresholdCreation() throws Exception {

		try {
			for (paramVal = 0; paramVal < colSize; paramVal++) {

				amtMap = excelHolderObj.dataMap(paramVal);

				NavigationHelper.navigateToScreen("Amount Threshold");

				initializeVariables(columnList);
				ThresholdLegends legObj= new ThresholdLegends(amtMap);
				if (!isAmountThresholdPresent(columnList)) {
					NavigationHelper.navigateToAction("Common Tasks", "New");
					GenericHelper.waitForLoadmask();
					newAmountThreshold();
					
					
					legObj.enterData();
					
					

				} 
					GridHelper.clickRow("SearchGrid", 1,1);
					GridHelper.doubleClick("SearchGrid", 1,1);
					
					GenericHelper.waitForLoadmask();
					GridHelper.clickRow(or.getProperty("Currency_Grid"), 1, 7);
					ButtonHelper.click("Notification_Box");
					
					legObj.downloadTemplate();
					GenericHelper.waitInSeconds("20");
					if (legObj.isDownloadSuccesfull()== true)
						Log4jHelper.logInfo("Test Case Passed");
				
				}
			
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}

	}

	protected boolean isAmountThresholdPresent(List<String> columnList) throws Exception {
		try {
			SearchGridHelper.gridFilterSearchWithTextBox(or.getProperty("Detail_amountThresholdName_txtId"),
			 amountThresholdName, "Name");
			
			return GridHelper.isValuePresent(or.getProperty("SearchGrid"), amountThresholdName, "Name")
							 ;
		} catch (Exception e) {
			throw e;
		}
	}

	protected void newAmountThreshold() throws Exception {

		try {
			TextBoxHelper.type(or.getProperty("Detail_amountThresholdName_txtId"), amountThresholdName);
			ComboBoxHelper.select(or.getProperty("Detail_addCurrencyBox"),currency);
			ButtonHelper.click(or.getProperty("Detail_addNewCurrency"));
			TextBoxHelper.type(or.getProperty("Detail_effectiveDate"), effectiveDate);
			ElementHelper.click(or.getProperty("Random_click"));
			
			ButtonHelper.click(or.getProperty("ok_button"));
			
			GenericHelper.waitForLoadmask();

		} catch (Exception e) {

			FailureHelper.setErrorMessage(e);
			throw e;
		}

	}

/*	protected void addCurrencyToXpath(String currency_data) throws Exception {
		try {
			currency = or.getProperty("Detail_currencyData");
			currency=currency.replaceAll("currency_data", currency_data);
			Log4jHelper.logInfo(currency);

		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}*/
}