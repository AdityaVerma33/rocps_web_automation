package com.subex.rocps.sprintTestCase.bklg16;

import java.util.ArrayList;
import java.util.Map;

import com.subex.automation.helpers.application.NavigationHelper;
import com.subex.automation.helpers.component.ButtonHelper;
import com.subex.automation.helpers.component.CheckBoxHelper;
import com.subex.automation.helpers.component.ComboBoxHelper;
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

public class OOTBReport extends PSAcceptanceTest {
	protected ExcelReader excelData = new ExcelReader();
	protected Map<String, ArrayList<String>> ootbReportsDataMap = null;
	protected Map<String, String> reportMap = null;
	protected ExcelHolder excelHolderObj = null;
	


	int colSize;
	int paramVal;

	protected PSGenericHelper genericHelperObj = new PSGenericHelper();

	int expectedDecimalPlace;
	Map<String, String> dpMap = null;
	
	String clientPartition;
	String fromDate;
	String toDate;
	String report_defnition;
	
	
	

	public OOTBReport() throws Exception {

	}

	public void statisticsReports(String Path, String WorkbookName, String sheetName, String testCaseName) throws Exception {
		try {

			ootbReportsDataMap = excelData.readDataByColumn(Path, WorkbookName, sheetName, testCaseName);

			excelHolderObj = new ExcelHolder(ootbReportsDataMap);
			colSize = excelHolderObj.totalColumns();

			for (paramVal = 0; paramVal < colSize; paramVal++) {
				reportMap = excelHolderObj.dataMap(paramVal);
				report_defnition = reportMap.get("Report Defnition");
				
				fromDate = reportMap.get("FromDate");
				toDate = reportMap.get("ToDate");
				

				NavigationHelper.navigateToScreen("Reports and Extracts");
				GenericHelper.waitForLoadmask();
	            
	            NavigationHelper.navigateToAction("Report and Extract Actions", "Adhoc Request");
				GenericHelper.waitForLoadmask();
				
				ElementHelper.click("//div[@id='trigger-rocpsInterfaceTbl']");
				GenericHelper.waitForLoadmask();
				filterStatus();
				GridHelper.clickRow( "SearchGrid", 1, 1 );
				ButtonHelper.click( "//button[@id='ok']" );
				GenericHelper.waitInSeconds("10");
				TextBoxHelper.type("From Date", fromDate);
				TextBoxHelper.type("To Date", toDate);
				GenericHelper.waitInSeconds("5");
				ButtonHelper.click("//button[@id='adhocRequestDetail.save']");
				GenericHelper.waitForLoadmask();
		     }
		}

		catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}

	public void filterStatus() throws Exception
	{
		ElementHelper.click( "//div[@id='grid_column_header_filtersearchGrid_pifeName']" );
		ComboBoxHelper.select( "//input[@id='pifeName']", report_defnition );
		ButtonHelper.click( "search" );
	}
	public void initializeInstanceVariables() {

		clientPartition = dpMap.get("ClientPartition");

	}
}

