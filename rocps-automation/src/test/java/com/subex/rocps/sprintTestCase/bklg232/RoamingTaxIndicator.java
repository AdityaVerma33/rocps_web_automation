package com.subex.rocps.sprintTestCase.bklg232;

import java.util.ArrayList;
import java.util.Map;

import org.testng.annotations.Test;

import com.subex.automation.helpers.application.NavigationHelper;
import com.subex.automation.helpers.component.ButtonHelper;
import com.subex.automation.helpers.component.ComboBoxHelper;
import com.subex.automation.helpers.component.GenericHelper;
import com.subex.automation.helpers.component.GridHelper;
import com.subex.automation.helpers.component.TextBoxHelper;
import com.subex.automation.helpers.file.ExcelReader;
import com.subex.automation.helpers.report.Log4jHelper;
import com.subex.automation.helpers.util.FailureHelper;
import com.subex.rocps.automation.helpers.application.genericHelpers.PSGenericHelper;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;
import com.subex.rocps.automation.utils.ExcelHolder;
import com.subex.rocps.automation.utils.PSStringUtils;


public class RoamingTaxIndicator extends PSAcceptanceTest {
	protected PSGenericHelper genericHelperObj = new PSGenericHelper();
	protected ExcelReader excelData = null;
	protected Map<String, ArrayList<String>> referenceTableMap = null;
	protected Map<String, String> roamMap = null;
	protected ExcelHolder excelHolderObj = null;
	protected String path;
	protected String workBookName;
	protected String sheetName;
	protected String testCaseName;
	protected int paramVal;
	protected int colSize;

	protected String clientPartition;
	protected String Value;
	protected String desc;

	/*
	 * Constructor for initializing excel Identifying the column size from the map
	 * passed
	 */
	@Test
	public RoamingTaxIndicator(String path, String workBookName, String sheetName, String testCaseName) throws Exception {
		this.path = path;
		this.workBookName = workBookName;
		this.sheetName = sheetName;
		this.testCaseName = testCaseName;
		excelData = new ExcelReader();
		referenceTableMap = excelData.readDataByColumn(this.path, this.workBookName, this.sheetName, this.testCaseName);
		excelHolderObj = new ExcelHolder(referenceTableMap);
		colSize = excelHolderObj.totalColumns();
	}


	public void roamingTaxIndicatorCreation() throws Exception {
		try {
			NavigationHelper.navigateToReferenceTable("Roaming Tax Indicator");

			for ( paramVal = 0;paramVal < colSize; paramVal++ ) 
			{ 
				roamMap = excelHolderObj.dataMap( paramVal);
				initializeVariables(roamMap);
				GenericHelper.waitForLoadmask( searchScreenWaitSec );
				newRoamingTaxIndicator();
				GenericHelper.waitForLoadmask( searchScreenWaitSec );
			}

		} catch (Exception e) {

			FailureHelper.setErrorMessage(e);
			throw e;
		}

	}
	protected void newRoamingTaxIndicator() throws Exception {
		GenericHelper.waitForLoadmask();
		createNew();
		assertEquals(NavigationHelper.getScreenTitle(), "New Roaming Tax Indicator");
		GenericHelper.waitForLoadmask();
		TextBoxHelper.type(or.getProperty("Roaming_tax_indicator_value"), Value);
		TextBoxHelper.type(or.getProperty("Roaming_tax_indicator_desc"), desc);
		saveRoamingTaxIndicator();

	}


	protected void createNew() throws Exception {
		genericHelperObj.clickNewAction(clientPartition);
		GenericHelper.waitForLoadmask();


	}

	protected void saveRoamingTaxIndicator() throws Exception {
		ButtonHelper.click(or.getProperty("Roaming_tax_indicator_save"));
		GenericHelper.waitForLoadmask();
		assertEquals(NavigationHelper.getScreenTitle(), "Reference Table Search");
	}

	/*
	 * This method is to initialize instance variable
	 */

	protected void initializeVariables(Map<String, String> Map) throws Exception {


		Value=Map.get("Value");
		clientPartition=Map.get("Partition");
		desc=Map.get("Description");
	}







}





