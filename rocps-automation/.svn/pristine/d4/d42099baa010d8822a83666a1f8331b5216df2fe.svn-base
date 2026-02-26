package com.subex.rocps.sprintTestCase.bklg16;

import java.util.ArrayList;
import java.util.Map;

import com.subex.automation.helpers.application.NavigationHelper;
import com.subex.automation.helpers.component.ButtonHelper;
import com.subex.automation.helpers.component.ComboBoxHelper;
import com.subex.automation.helpers.component.ElementHelper;
import com.subex.automation.helpers.component.GenericHelper;
import com.subex.automation.helpers.component.GridHelper;
import com.subex.automation.helpers.component.TextBoxHelper;
import com.subex.automation.helpers.file.ExcelReader;
import com.subex.automation.helpers.util.FailureHelper;
import com.subex.rocps.automation.helpers.application.genericHelpers.PSGenericHelper;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;
import com.subex.rocps.automation.utils.ExcelHolder;

public class DisputeReport extends PSAcceptanceTest {
	protected ExcelReader excelData = new ExcelReader();
	protected Map<String, ArrayList<String>> disputeReportsDataMap = null;
	protected Map<String, String> reportMap = null;
	protected ExcelHolder excelHolderObj = null;
	


	int colSize;
	int paramVal;

	protected PSGenericHelper genericHelperObj = new PSGenericHelper();

	int expectedDecimalPlace;
	Map<String, String> dpMap = null;
	
	String clientPartition;
	String disputeFromDate;
	String disputeToDate;
	String report_defnition;
	
	
	

	public DisputeReport() throws Exception {

	}

	public void disputeReport(String Path, String WorkbookName, String sheetName, String testCaseName) throws Exception {
		try {

			disputeReportsDataMap = excelData.readDataByColumn(Path, WorkbookName, sheetName, testCaseName);

			excelHolderObj = new ExcelHolder(disputeReportsDataMap);
			colSize = excelHolderObj.totalColumns();

			for (paramVal = 0; paramVal < colSize; paramVal++) {
				reportMap = excelHolderObj.dataMap(paramVal);
				report_defnition = reportMap.get("Report Defnition");
				
				disputeFromDate = reportMap.get("DisputeFromDate");
				disputeToDate = reportMap.get("DisputeToDate");
				

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
				TextBoxHelper.type("Dispute From Date", disputeFromDate);
				TextBoxHelper.type("Dispute To Date", disputeFromDate);
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


