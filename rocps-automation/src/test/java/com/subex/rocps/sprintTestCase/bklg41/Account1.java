package com.subex.rocps.sprintTestCase.bklg41;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

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
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;
import com.subex.rocps.automation.utils.ExcelHolder;

public class Account1 extends PSAcceptanceTest {

	// private static final int ACCOUNT_NAME_COLUMN_NUM = i++;
	ExcelReader excelData = null;
	Map<String, ArrayList<String>> accExcelMap = null;
	Map<String, String> accMap = null;
	ExcelHolder excelHolderObj = null;
	OR_Reader orData = new OR_Reader();
	String path;
	String workBookName;
	String sheetName;
	String testCaseName;
	List<String> columnList = null;
	String errorMessage;
	String accountName;
	String customerType;
	int colSize;
	int paramVal;
	public Account1(String path, String workBookName, String sheetName, String testCaseName) throws Exception {
		this.path = path;
		this.workBookName = workBookName;
		this.sheetName = sheetName;
		this.testCaseName = testCaseName;
		excelData = new ExcelReader();
		accExcelMap = excelData.readDataByColumn(this.path, this.workBookName, this.sheetName, this.testCaseName);
		excelHolderObj = new ExcelHolder(accExcelMap);
		colSize = excelHolderObj.totalColumns();
	}

	/*
	 * Configuring the account
	 * 
	 * @method : isAccountPresent returns false then account is configured
	 * isAccountPresent returns true then account is not configured
	 */
	public void accountCreation() throws Exception {
		try {

			NavigationHelper.navigateToScreen("Account");

			for (paramVal = 0; paramVal < colSize; paramVal++) {

				accMap = excelHolderObj.dataMap(paramVal);

				initializeInstanceVariables(columnList);

				if (!isAccountPresent(columnList)) {
					// NavigationHelper.navigateToNew("Common");

					NavigationHelper.navigateToAction("Common Tasks", "New");
					GenericHelper.waitForLoadmask();

					// Partition Box

					openAccountDetail();
					
					AccountDetailsTab accDetailTabObj = new AccountDetailsTab(accMap);
					AccountTest accContInfoObj = new AccountTest(accMap);
					accDetailTabObj.detailTabConfig();
					GenericHelper.waitInSeconds("5");
					accContInfoObj.contactInformation();
					if (accContInfoObj.returnFlagValue() != true) {
						ButtonHelper.click(or.getProperty("Detail_accountDetailSaveId"));

						assertTrue(GridHelper.isValuePresent("SearchGrid", accountName, "Account Name"));
						Log4jHelper.logInfo("Account is created successfully with name " + accountName);
					}
					else
						Log4jHelper.logInfo("Test Case Passed");

				} else {
					Log4jHelper.logInfo("Account is available with name " + accountName);
				}

			}

		} catch (Exception e) {

			throw e;
		}
	}

	// this method is used for initializing the instance variables
	public void initializeInstanceVariables(List<String> columnList) throws Exception {

		accountName = accMap.get("AccountName");
		customerType = accMap.get("CustomerType");
		

	}

	/*
	 * Account name verification in account search screen
	 */
	protected boolean isAccountPresent(List<String> columnList) throws Exception {
		try {
			SearchGridHelper.gridFilterSearchWithTextBox(or.getProperty("accountName_Detail"), accountName,
					"Account Name");
			return GridHelper.isValuePresent(or.getProperty("SearchGrid"), accountName, "Account Name");
		} catch (Exception e) {
			throw e;
		}
	}

	/*
	 * Method to configure account detail
	 */
	protected void openAccountDetail() throws Exception {
		try {
			ComboBoxHelper.select(or.getProperty("accountType_Detail"), customerType);
			TextBoxHelper.type(or.getProperty("accountName_Detail"), accountName);

		} catch (Exception e) {
			throw e;
		}

	}

	protected void checkPartition() throws Exception {

		WebElement temp = ElementHelper.getElement(or.getProperty("partitionBox"));
		ElementHelper.click(temp);
		temp = ElementHelper.getElement(or.getProperty("common_Partition"));
		ElementHelper.click(temp);
		// ElementHelper.click(temp);
		ButtonHelper.click(or.getProperty("partition_Block"), or.getProperty("ok_button"));
		ElementHelper.waitForElement(or.getProperty("new_Account"), 10);
	}

}
