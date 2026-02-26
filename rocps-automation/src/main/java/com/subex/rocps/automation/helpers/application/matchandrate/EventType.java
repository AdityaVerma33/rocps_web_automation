package com.subex.rocps.automation.helpers.application.matchandrate;

import java.util.ArrayList;
import java.util.Map;

import org.testng.annotations.Test;

import com.subex.automation.helpers.application.NavigationHelper;
import com.subex.automation.helpers.component.ButtonHelper;
import com.subex.automation.helpers.component.CheckBoxHelper;
import com.subex.automation.helpers.component.ComboBoxHelper;
import com.subex.automation.helpers.component.GenericHelper;
import com.subex.automation.helpers.component.GridHelper;
import com.subex.automation.helpers.component.TextBoxHelper;
import com.subex.automation.helpers.data.ValidationHelper;
import com.subex.automation.helpers.file.ExcelReader;
import com.subex.automation.helpers.report.Log4jHelper;
import com.subex.automation.helpers.util.FailureHelper;
import com.subex.rocps.automation.helpers.application.genericHelpers.PSGenericHelper;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;
import com.subex.rocps.automation.utils.ExcelHolder;
import com.subex.rocps.automation.utils.PSStringUtils;

public class EventType extends PSAcceptanceTest {

	ExcelReader excelData = null;
	Map<String, ArrayList<String>> eventTypeExcel = null;
	Map<String, String> eventMap = null;
	ExcelHolder excelHolderObj = null;
	protected String path;
	protected String workBookName;
	protected String sheetName;
	protected String testCaseName;
	protected int paramVal;
	protected int colSize;
	protected String clientPartition;
	protected String name;
	protected String eventModelling;
	protected String eventLegGrp;
	protected String tariffType;
	protected String eventProcessor;
	protected String multipleServicePerLeg;
	protected String revenueAmount;
	protected String revenueCurrency;
	protected String ratingFrom;
	protected String ratingTo;
	protected String serviceTypeGrp;
	protected PSGenericHelper genericHelperObj = new PSGenericHelper();
	PSStringUtils strObj = new PSStringUtils();
	/*
	 * Constructor for initializing excel Identifying the column size from the map
	 * passed
	 */
	@Test
	public EventType(String path, String workBookName, String sheetName, String testCaseName) throws Exception {
		this.path = path;
		this.workBookName = workBookName;
		this.sheetName = sheetName;
		this.testCaseName = testCaseName;
		excelData = new ExcelReader();
		eventTypeExcel = excelData.readDataByColumn(this.path, this.workBookName, this.sheetName, this.testCaseName);
		excelHolderObj = new ExcelHolder(eventTypeExcel);
		colSize = excelHolderObj.totalColumns();
	}

	/*
	 * Overloaded constructor for reading data from excel if test case name appears
	 * more than once
	 * 
	 * @Param occurance : Test case instance in excel sheet Constructor for
	 * initializing excel Identifying the column size from the map
	 */
	public EventType(String path, String workBookName, String sheetName, String testCaseName, int occurance)
			throws Exception {
		this.path = path;
		this.workBookName = workBookName;
		this.sheetName = sheetName;
		this.testCaseName = testCaseName;
		excelData = new ExcelReader();
		eventTypeExcel = excelData.readDataByColumn(this.path, this.workBookName, this.sheetName, this.testCaseName,
				occurance);
		excelHolderObj = new ExcelHolder(eventTypeExcel);
		colSize = excelHolderObj.totalColumns();
	}

	/*
	 * Configuring the Event Type
	 * 
	 * @method : isEventTypePresent returns false then EventType is configured
	 * isEventTypePresent returns true then EventType is not configured
	 */
	public void eventTypeCreation() throws Exception {
		try {
			NavigationHelper.navigateToScreen("Event Type");
			for (paramVal = 0; paramVal < colSize; paramVal++) {

				eventMap = excelHolderObj.dataMap(paramVal);			

				initializeVariables();
				ButtonHelper.click( "ClearButton" );
				GenericHelper.waitForLoadmask(searchScreenWaitSec);
				genericHelperObj.waitforHeaderElement( "Name" );
				boolean isEventTypePresent = genericHelperObj.isGridTextValuePresent("PS_Detail_EventType_Name_txtID",
						name, "Name");
				GenericHelper.waitForLoadmask();

				if (!isEventTypePresent) {
					genericHelperObj.clickNewAction(clientPartition);
					GenericHelper.waitForLoadmask();
					eventTypebasicConfig();
					ratingConfig();

					ButtonHelper.click("PS_Detail_EventType_saveBtn");
					GenericHelper.waitForSave(searchScreenWaitSec);
					assertTrue(GridHelper.isValuePresent("Detail_eventDefn_gridID", name, "Name"),
							"Event Type is not configured");
					Log4jHelper.logInfo("Event Type is created successfully with name " + name);

				} else {
					editEventtype();
					Log4jHelper
							.logInfo("EventType is available with name, modified multiple service leg flag for" + name);
				}

			}
		} catch (Exception e) {

			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}

	public void editEventtype() throws Exception {
		GridHelper.clickRow("SearchGrid", name, "Name");
		GenericHelper.waitForSave(searchScreenWaitSec);
		NavigationHelper.navigateToAction("Common Tasks", "Edit");
		GenericHelper.waitForLoadmask();
		if (CheckBoxHelper.isNotChecked("PS_Detail_multipleServiceLeg_chkbx")) {
			// if (ValidationHelper.isTrue(multipleServicePerLeg)) {
			CheckBoxHelper.check("PS_Detail_multipleServiceLeg_chkbx");
		}
		ButtonHelper.click("PS_Detail_EventType_saveBtn");
		GenericHelper.waitForSave(searchScreenWaitSec);
	}

	/*
	 * This method is for event type basic details config
	 */
	protected void eventTypebasicConfig() throws Exception {
		TextBoxHelper.type("PS_Detail_EventType_Name_txtID", name);
		ComboBoxHelper.select("PS_Detail_eventModelling_comboID", eventModelling);
		ComboBoxHelper.select("PS_Detail_eventLegGrp_comboID", eventLegGrp);
		ComboBoxHelper.select("PS_Detail_tariffType_comboID", tariffType);
		ComboBoxHelper.select("PS_Detail_eventProcessor_comboId", eventProcessor);

		if (ValidationHelper.isTrue(multipleServicePerLeg)) {
			CheckBoxHelper.check("PS_Detail_multipleServiceLeg_chkbx");
		}
	}

	/*
	 * This method is for rating tab config
	 */
	protected void ratingConfig() throws Exception {
		ComboBoxHelper.select("PS_Detail_revenueAmount_comboID", revenueAmount);
		ComboBoxHelper.select("PS_Detail_revenueCurrency_comboID", revenueCurrency);
		ComboBoxHelper.select("PS_Detail_ratingFrom_ComboID", ratingFrom);
		ComboBoxHelper.select("PS_Detail_ratingTo_comboID", ratingTo);

		PSGenericHelper genericHelperObj = new PSGenericHelper();
		genericHelperObj.dualListSelection(serviceTypeGrp);

	}

	/*
	 * This method is to initialize instance variables
	 */
	protected void initializeVariables() throws Exception {

		clientPartition = eventMap.get("Partition");
		name = eventMap.get("Name");
		eventModelling = eventMap.get("EventModelling");
		eventLegGrp = eventMap.get("EventLegGrp");
		tariffType = eventMap.get("TariffType");
		eventProcessor = eventMap.get("EventProcessor");
		multipleServicePerLeg = eventMap.get("MultipleServicePerLeg");
		revenueAmount = eventMap.get("RevenueAmount");
		revenueCurrency = eventMap.get("RevenueCurrency");
		ratingFrom = eventMap.get("RatingFrom");
		ratingTo = eventMap.get("RatingTo");
		serviceTypeGrp = eventMap.get("ServiceTypeGrp");

	}
	
	/*
	 * This method is to validate search screen columns
	 */
	public void searchScreenColumnsValidation() throws Exception
	{		
		NavigationHelper.navigateToScreen( "Event Type" );
		for ( paramVal = 0; paramVal < colSize; paramVal++ )
		{
			eventMap = excelHolderObj.dataMap( paramVal );
			String searchScreenColumns = ExcelHolder.getKey( eventMap, "SearchScreenColumns" );
			//GridHelper.isPresent( gridId )
			ArrayList<String> excelColumnNames = new ArrayList<String>();
			String[] searchGridColumnsArr = strObj.stringSplitFirstLevel( searchScreenColumns );
			for ( int col = 0; col < searchGridColumnsArr.length; col++ )
			{
				excelColumnNames.add( searchGridColumnsArr[col] );
			}
			genericHelperObj.totalColumns( excelColumnNames );
		}

	}

	/*
	 * This method is for event type deletion
	 */
	public void eventTypeDelete() throws Exception {

		try
		{
			NavigationHelper.navigateToScreen("Event Type");
			for (paramVal = 0; paramVal < colSize; paramVal++) {

				eventMap = excelHolderObj.dataMap(paramVal);

				clientPartition = eventMap.get("Partition");
				name = eventMap.get("Name");

				genericHelperObj.selectPartitionFilter(clientPartition, "Non Deleted Items");
				boolean isEventTypePresent = genericHelperObj.isGridTextValuePresent("PS_Detail_EventType_Name_txtID", name,
						"Name");
				if (isEventTypePresent) {
					genericHelperObj.clickDeleteOrUnDeleteAction(name, "Name", "Delete");
					GenericHelper.waitForLoadmask();
					genericHelperObj.selectPartitionFilter(clientPartition, "Deleted Items");
					assertTrue(GridHelper.isValuePresent("SearchGrid", name, "Name"), "Event type is not deleted");
					Log4jHelper.logInfo("Event Type is deleted successfully with name " + name);

				} else {
					Log4jHelper.logInfo("Event Type is not available with name " + name);
				}

			}
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}

	/*
	 * This method is for event type un delete
	 */
	public void eventTypeUnDelete() throws Exception {

		try
		{
			NavigationHelper.navigateToScreen("Event Type");
			for (paramVal = 0; paramVal < colSize; paramVal++) {

				eventMap = excelHolderObj.dataMap(paramVal);

				clientPartition = eventMap.get("Partition");
				name = eventMap.get("Name");

				genericHelperObj.selectPartitionFilter(clientPartition, "Deleted Items");
				GenericHelper.waitForLoadmask();
				boolean isEventTypePresent = genericHelperObj.isGridTextValuePresent("PS_Detail_EventType_Name_txtID", name,
						"Name");
				GenericHelper.waitForLoadmask();
				if (isEventTypePresent) {
					genericHelperObj.clickDeleteOrUnDeleteAction(name, "Name", "Undelete");
					GenericHelper.waitForLoadmask();
					genericHelperObj.selectPartitionFilter(clientPartition, "Non Deleted Items");
					assertTrue(GridHelper.isValuePresent("SearchGrid", name, "Name"), "Event type is not deleted");
					Log4jHelper.logInfo("Event Type is un deleted successfully with name " + name);

				} else {
					Log4jHelper.logInfo("Event Type is not available with name " + name);
				}
			}
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}

}
