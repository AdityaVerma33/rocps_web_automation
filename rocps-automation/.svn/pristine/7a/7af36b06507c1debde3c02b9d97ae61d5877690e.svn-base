package com.subex.rocps.automation.helpers.application.matchandrate;

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

public class EventNormalization extends PSAcceptanceTest {

	protected ExcelReader excelData = null;
	protected Map<String, ArrayList<String>> eventNorExcel = null;
	protected Map<String, String> eventNorMap = null;
	protected ExcelHolder excelHolderObj = null;
	protected String path;
	protected String workBookName;
	protected String sheetName;
	protected String testCaseName;
	protected int paramVal;
	protected int colSize;
	protected String clientPartition;
	protected String Select;

	protected String name;
	protected String component;
	protected String eventType;
	protected PSGenericHelper genericHelperObj = new PSGenericHelper();
	PSStringUtils strObj = new PSStringUtils();

	/*
	 * Constructor for initializing excel Identifying the column size from the map
	 * passed
	 */
	@Test
	public EventNormalization(String path, String workBookName, String sheetName, String testCaseName)
			throws Exception {
		this.path = path;
		this.workBookName = workBookName;
		this.sheetName = sheetName;
		this.testCaseName = testCaseName;
		excelData = new ExcelReader();
		eventNorExcel = excelData.readDataByColumn(this.path, this.workBookName, this.sheetName, this.testCaseName);
		excelHolderObj = new ExcelHolder(eventNorExcel);
		colSize = excelHolderObj.totalColumns();
	}

	/*
	 * Overloaded constructor for reading data from excel if test case name appears
	 * more than once
	 * 
	 * @Param occurance : Test case instance in excel sheet Constructor for
	 * initializing excel Identifying the column size from the map
	 */
	public EventNormalization(String path, String workBookName, String sheetName, String testCaseName, int occurance)
			throws Exception {
		this.path = path;
		this.workBookName = workBookName;
		this.sheetName = sheetName;
		this.testCaseName = testCaseName;
		excelData = new ExcelReader();
		eventNorExcel = excelData.readDataByColumn(this.path, this.workBookName, this.sheetName, this.testCaseName,
				occurance);
		excelHolderObj = new ExcelHolder(eventNorExcel);
		colSize = excelHolderObj.totalColumns();
	}

	/*
	 * Configuring the EventNormalization
	 * 
	 */
	public void eventNormalizationCreation() throws Exception {
		try {
			NavigationHelper.navigateToScreen("Event Normalization");
			for (paramVal = 0; paramVal < colSize; paramVal++) {

				eventNorMap = excelHolderObj.dataMap(paramVal);

				initializeVariables();
				ButtonHelper.click( "ClearButton" );
				GenericHelper.waitForLoadmask(searchScreenWaitSec);
				boolean isEventNor = genericHelperObj.isGridTextValuePresent("PS_Detail_eventNormalization_name_txtID",
						name, "Name");
				if (!isEventNor) {
					genericHelperObj.clickNewAction(clientPartition);
					GenericHelper.waitForLoadmask();
					neweventNormalization();

					ButtonHelper.click("PS_Detail_ok_buttonID");
					GenericHelper.waitForSave(searchScreenWaitSec);
					assertTrue(GridHelper.isValuePresent("Detail_eventDefn_gridID", name, "Name"),
							"Event normalization is not configured");
					Log4jHelper.logInfo("Event Normalization is created successfully with name " + name);
				} else {
					Log4jHelper.logInfo("Event Normalization is available with name " + name);
				}

			}

		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	/*
	 * This method is to create new Event Normalization
	 */

	protected void neweventNormalization() throws Exception {

		try {
			TextBoxHelper.type("PS_Detail_eventNormalization_name_txtID", name);

			ComboBoxHelper.select("PS_Detail_eventNormalization_componet_comboID", component);

			// ComboBoxHelper.select("PS_Detail_eventNormalization_eventType_comboID",
			// eventType);
			ComboBoxHelper.select("eventType_gwt_uid_", eventType);
			propertiesConfig();
		} catch (Exception e) {

			throw e;
		}

	}

	/*
	 * This method is for properties config
	 */
	protected void propertiesConfig() throws Exception {
		EventNormalizationComponent eventCompObj = new EventNormalizationComponent(eventNorMap);
		if (component.contentEquals("Assign Component"))
			eventCompObj.assignComponent();
		else if (component.contentEquals("Route Lookup Component"))
			eventCompObj.routeLookUpCompoent();
		else if (component.contentEquals("Rule StringSet Component"))
			eventCompObj.ruleStringSetComponent();
		else if (component.contentEquals("PreRating Component"))
			eventCompObj.preratingComponent();
		else if (component.contentEquals("Currency Lookup Component"))
			eventCompObj.currencyLookupComponent();
	}

	/*
	 * This method is for initializing instance variables
	 */

	protected void initializeVariables() throws Exception {

		clientPartition = eventNorMap.get("Partition");
		name = eventNorMap.get("Name");
		component = eventNorMap.get("Component");
		eventType = eventNorMap.get("EventType");
	}
	
	public void editEventNormalization() throws Exception {
		try {
			NavigationHelper.navigateToScreen("Event Normalization");
			for (paramVal = 0; paramVal < colSize; paramVal++) {

				eventNorMap = excelHolderObj.dataMap(paramVal);

				initializeVariables();
				ButtonHelper.click( "ClearButton" );
				GenericHelper.waitForLoadmask(searchScreenWaitSec);
				boolean isEventNor = genericHelperObj.isGridTextValuePresent("PS_Detail_eventNormalization_name_txtID",
						name, "Name");
				if (isEventNor) {
					int row = GridHelper.getRowNumber( "SearchGrid", name, "Name" );
					NavigationHelper.navigateToEdit( "SearchGrid", row );
					GenericHelper.waitForLoadmask();
					assertEquals( NavigationHelper.getScreenTitle(), "Edit Event Normalization" );				
					
					TextBoxHelper.type("PS_Detail_eventNormalization_name_txtID", name);
					assertEquals(ComboBoxHelper.getValue("PS_Detail_eventNormalization_componet_comboID"), component);
					assertEquals(ComboBoxHelper.getValue("eventType_gwt_uid_"), eventType);
					propertiesConfig();

					ButtonHelper.click("PS_Detail_ok_buttonID");
					GenericHelper.waitForSave(searchScreenWaitSec);
					assertTrue(GridHelper.isValuePresent("Detail_eventDefn_gridID", name, "Name"),
							"Event normalization is not configured");
					Log4jHelper.logInfo("Event Normalization is edited successfully with name " + name);
				} else {
					Log4jHelper.logInfo("Event Normalization is not available with name " + name);
				}

			}

		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	/*
	 * This method is to validate search screen columns
	 */
	public void searchScreenColumnsValidation() throws Exception
	{		
		NavigationHelper.navigateToScreen( "Event Normalization" );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		for ( paramVal = 0; paramVal < colSize; paramVal++ )
		{
			eventNorMap = excelHolderObj.dataMap( paramVal );
			String searchScreenColumns = ExcelHolder.getKey( eventNorMap, "SearchScreenColumns" );
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
	 * This method is for event normalization deletion
	 */
	public void eventNormalizationDelete() throws Exception {

		NavigationHelper.navigateToScreen("Event Normalization");
		for (paramVal = 0; paramVal < colSize; paramVal++) {

			eventNorMap = excelHolderObj.dataMap(paramVal);
			clientPartition = eventNorMap.get("Partition");
			name = eventNorMap.get("Name");

			genericHelperObj.selectPartitionFilter(clientPartition, "Non Deleted Items");
			GenericHelper.waitForLoadmask();

			boolean isEventNor = genericHelperObj.isGridTextValuePresent("PS_Detail_eventNormalization_name_txtID",
					name, "Name");
			if (isEventNor) {

				genericHelperObj.clickDeleteOrUnDeleteAction(name, "Name", "Delete");
				GenericHelper.waitForLoadmask();

				genericHelperObj.selectPartitionFilter(clientPartition, "Deleted Items");
				assertTrue(GridHelper.isValuePresent("SearchGrid", name, "Name"), name);
				Log4jHelper.logInfo("Event normalization is deleted successfully : " + name);

			} else {
				Log4jHelper.logInfo("Event normalization is not available : " + name);
			}

		}
	}

	/*
	 * This method is for event normalization un delete
	 */
	public void eventNormalizationUnDelete() throws Exception {

		NavigationHelper.navigateToScreen("Event Normalization");
		for (paramVal = 0; paramVal < colSize; paramVal++) {

			eventNorMap = excelHolderObj.dataMap(paramVal);
			clientPartition = eventNorMap.get("Partition");
			name = eventNorMap.get("Name");

			genericHelperObj.selectPartitionFilter(clientPartition, "Deleted Items");

			boolean isEventNor = genericHelperObj.isGridTextValuePresent("PS_Detail_eventNormalization_name_txtID",
					name, "Name");
			if (isEventNor) {

				genericHelperObj.clickDeleteOrUnDeleteAction(name, "Name", "Undelete");
				GenericHelper.waitForLoadmask();

				genericHelperObj.selectPartitionFilter(clientPartition, "Non Deleted Items");
				assertTrue(GridHelper.isValuePresent("SearchGrid", name, "Name"), name);
				Log4jHelper.logInfo("Event normalization is un deleted successfully : " + name);

			} else {
				Log4jHelper.logInfo("Event normalization is not available : " + name);
			}

		}
	}

}
