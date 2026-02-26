package com.subex.rocps.automation.helpers.application.bills;

import java.util.ArrayList;
import java.util.Map;

import org.testng.annotations.Test;

import com.subex.automation.helpers.application.NavigationHelper;
import com.subex.automation.helpers.component.ButtonHelper;
import com.subex.automation.helpers.component.GenericHelper;
import com.subex.automation.helpers.component.GridHelper;
import com.subex.automation.helpers.component.PopupHelper;
import com.subex.automation.helpers.file.ExcelReader;
import com.subex.automation.helpers.report.Log4jHelper;
import com.subex.automation.helpers.util.FailureHelper;
import com.subex.rocps.automation.helpers.application.bills.billpackage.BillPackageActionImpl;
import com.subex.rocps.automation.helpers.application.bills.billpackage.BillPackageDetailImpl;
import com.subex.rocps.automation.helpers.application.genericHelpers.PSGenericHelper;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;
import com.subex.rocps.automation.utils.ExcelHolder;
import com.subex.rocps.automation.utils.PSStringUtils;

public class BillPackage extends PSAcceptanceTest {

	protected ExcelReader excelData = null;
	protected Map<String, ArrayList<String>> billPackagesExcel = null;
	protected Map<String, String> billPackagesMap = null;
	protected ExcelHolder excelHolderObj = null;
	protected String path;
	protected String workBookName;
	protected String sheetName;
	protected String testCaseName;
	protected int paramVal;
	protected int colSize;
	protected String clientPartition;
	protected String name;
	protected PSGenericHelper genericHelperObj = new PSGenericHelper();
	BillPackageActionImpl billPackageActionObj = new BillPackageActionImpl();

	/*
	 * Constructor for initializing excel Identifying the column size from the map
	 * passed
	 */
	@Test
	public BillPackage(String path, String workBookName, String sheetName, String testCaseName) throws Exception {
		this.path = path;
		this.workBookName = workBookName;
		this.sheetName = sheetName;
		this.testCaseName = testCaseName;
		excelData = new ExcelReader();
		billPackagesExcel = excelData.readDataByColumn(this.path, this.workBookName, this.sheetName, this.testCaseName);
		excelHolderObj = new ExcelHolder(billPackagesExcel);
		colSize = excelHolderObj.totalColumns();
	}

	/*
	 * Overloaded constructor for reading data from excel if test case name appears
	 * more than once
	 * 
	 * @Param occurance : Test case instance in excel sheet Constructor for
	 * initializing excel Identifying the column size from the map
	 */
	public BillPackage(String path, String workBookName, String sheetName, String testCaseName, int occurance)
			throws Exception {
		this.path = path;
		this.workBookName = workBookName;
		this.sheetName = sheetName;
		this.testCaseName = testCaseName;
		excelData = new ExcelReader();
		billPackagesExcel = excelData.readDataByColumn(this.path, this.workBookName, this.sheetName, this.testCaseName,
				occurance);
		excelHolderObj = new ExcelHolder(billPackagesExcel);
		colSize = excelHolderObj.totalColumns();
	}

	/*
	 * Configuring the Bill Package
	 * 
	 * @method : isBillPackagePresent returns false then Bill Package is configured
	 * isBillPackagePresent returns true then Bill Package is not configured
	 */
	public void billPackageCreation() throws Exception {
		try {
			NavigationHelper.navigateToScreen("Bill Packages");

			for (paramVal = 0; paramVal < colSize; paramVal++) {

				billPackagesMap = excelHolderObj.dataMap(paramVal);

				name = ExcelHolder.getKey(billPackagesMap, "Name");
				clientPartition = ExcelHolder.getKey(billPackagesMap, "Partition");
				ButtonHelper.click("ClearButton");
				GenericHelper.waitForLoadmask(searchScreenWaitSec);
				boolean isBillingPackagePresent = genericHelperObj
						.isGridTextValuePresent("PS_Detail_BillPackage_name_txtID", name, "Name");

				if (!isBillingPackagePresent) {
					newBillPackage();
					Log4jHelper.logInfo("Bill package is created successfully with name " + name);
				} else {
					Log4jHelper.logInfo("Bill package is available with name " + name);
				}
			}
		} catch (Exception e) {

			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}

	/* Method is to edit Bill package */

	public void billPackageEdit() throws Exception {
		try {
			NavigationHelper.navigateToScreen("Bill Packages");

			for (paramVal = 0; paramVal < colSize; paramVal++) {

				billPackagesMap = excelHolderObj.dataMap(paramVal);

				name = ExcelHolder.getKey(billPackagesMap, "Name");
				clientPartition = ExcelHolder.getKey(billPackagesMap, "Partition");
				ButtonHelper.click("ClearButton");
				GenericHelper.waitForLoadmask(searchScreenWaitSec);
				boolean isBillingPackagePresent = genericHelperObj
						.isGridTextValuePresent("PS_Detail_BillPackage_name_txtID", name, "Name");

				if (isBillingPackagePresent) {
					int row = GridHelper.getRowNumber("searchGrid", name, "Name");
					GridHelper.clickRow("searchGrid", row, "Name");
					PSGenericHelper.waitForParentActionElementTOBeclickable("Common Tasks");
					NavigationHelper.navigateToEdit("searchGrid", row);
					editBillPackage();
					assertTrue(GridHelper.isValuePresent("Detail_eventDefn_gridID", name, "Name"),
							"Bill package is not configured");
					Log4jHelper.logInfo("Bill package is updated successfully with name " + name);
				} else {
					Log4jHelper.logInfo("Bill package is not available with name " + name);
				}
			}
		} catch (Exception e) {

			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}

	/*
	 * This method is to create new Bill Package
	 */
	protected void newBillPackage() throws Exception {

		BillPackageDetailImpl billPackgaeDetailObj = new BillPackageDetailImpl(billPackagesMap);
		billPackageActionObj.createNewbillPackageConfig(clientPartition);
		billPackgaeDetailObj.billpackageDetailConfig();
		billPackgaeDetailObj.billControlProperties();
		billPackgaeDetailObj.billComponentPropertySelection();
		billPackgaeDetailObj.creditTabConfig();
		genericHelperObj.detailSave("PS_Detail_BillPackage_save_btnID", name, "Name");
	}

	/*
	 * This method is to edit Bill Package
	 */
	protected void editBillPackage() throws Exception {

		BillPackageDetailImpl billPackgaeDetailObj = new BillPackageDetailImpl(billPackagesMap);
		billPackgaeDetailObj.editbillpackageDetail();
		billPackgaeDetailObj.editbillControlProperties();
		billPackgaeDetailObj.billComponentPropertySelection();
		billPackgaeDetailObj.editCreditTabConfig();
		ButtonHelper.click("PS_Detail_BillPackage_save_btnID");
		GenericHelper.waitForLoadmask(searchScreenWaitSec);
		if (PopupHelper.isPresent())
			ButtonHelper.click("YesButton");
		Thread.sleep(1000);
		GenericHelper.waitForLoadmask(searchScreenWaitSec);
	}

	/*
	 * This method is to validate search screen columns
	 */
	public void searchScreenColumnsValidation() throws Exception {
		NavigationHelper.navigateToScreen("Bill Packages");
		GenericHelper.waitForLoadmask(searchScreenWaitSec);
		for (paramVal = 0; paramVal < colSize; paramVal++) {
			billPackagesMap = excelHolderObj.dataMap(paramVal);
			String searchScreenColumns = ExcelHolder.getKey(billPackagesMap, "SearchScreenColumns");
			ArrayList<String> excelColumnNames = new ArrayList<String>();
			PSStringUtils strObj = new PSStringUtils();
			String[] searchGridColumnsArr = strObj.stringSplitFirstLevel(searchScreenColumns);
			for (int col = 0; col < searchGridColumnsArr.length; col++) {
				excelColumnNames.add(searchGridColumnsArr[col]);
			}
			genericHelperObj.totalColumns(excelColumnNames);
		}

	}

	/*
	 * This method is for bill Package deletion
	 */
	public void billPackageDelete() throws Exception {
		NavigationHelper.navigateToScreen("Bill Packages");

		for (paramVal = 0; paramVal < colSize; paramVal++) {

			billPackagesMap = excelHolderObj.dataMap(paramVal);

			name = ExcelHolder.getKey(billPackagesMap, "Name");
			clientPartition = ExcelHolder.getKey(billPackagesMap, "Partition");

			genericHelperObj.selectPartitionFilter(clientPartition, "Non Deleted Items");

			boolean isBillingPackagePresent = genericHelperObj
					.isGridTextValuePresent("PS_Detail_BillPackage_name_txtID", name, "Name");

			if (isBillingPackagePresent) {
				billPackageActionObj.clickDeleteAction(name);
				genericHelperObj.selectPartitionFilter(clientPartition, "Deleted Items");
				assertTrue(GridHelper.isValuePresent("SearchGrid", name, "Name"), name);
				Log4jHelper.logInfo("bill Package is deleted successfully :" + name);

			} else {
				Log4jHelper.logInfo("bill Package is not available with :" + name);
			}

		}
	}

	/*
	 * This method is for bill Package un delete
	 */
	public void billPackageUnDelete() throws Exception {

		NavigationHelper.navigateToScreen("Bill Packages");

		for (paramVal = 0; paramVal < colSize; paramVal++) {

			billPackagesMap = excelHolderObj.dataMap(paramVal);

			name = ExcelHolder.getKey(billPackagesMap, "Name");
			clientPartition = ExcelHolder.getKey(billPackagesMap, "Partition");
			genericHelperObj.selectPartitionFilter(clientPartition, "Deleted Items");

			boolean isBillingPackagePresent = genericHelperObj
					.isGridTextValuePresent("PS_Detail_BillPackage_name_txtID", name, "Name");

			if (isBillingPackagePresent) {
				billPackageActionObj.clickUnDeleteAction(name);
				genericHelperObj.selectPartitionFilter(clientPartition, "Non Deleted Items");
				assertTrue(GridHelper.isValuePresent("SearchGrid", name, "Name"), name);
				Log4jHelper.logInfo("bill Package is un deleted successfully :" + name);

			} else {
				Log4jHelper.logInfo("bill Package is not available with :" + name);
			}

		}
	}
}
