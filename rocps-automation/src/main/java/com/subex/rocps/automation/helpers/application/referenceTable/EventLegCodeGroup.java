package com.subex.rocps.automation.helpers.application.referenceTable;

import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Map;

import com.subex.rocps.automation.helpers.application.genericHelpers.PSGenericHelper;
import com.subex.rocps.automation.helpers.application.referenceTable.eventlegcodegroup.EventLegCodeGroupActionImpl;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;
import com.subex.rocps.automation.utils.ExcelHolder;
import com.subex.rocps.automation.utils.PSStringUtils;

import com.subex.automation.helpers.application.NavigationHelper;
import com.subex.automation.helpers.component.ButtonHelper;
import com.subex.automation.helpers.component.CheckBoxHelper;
import com.subex.automation.helpers.component.GenericHelper;
import com.subex.automation.helpers.component.GridHelper;
import com.subex.automation.helpers.component.SearchGridHelper;
import com.subex.automation.helpers.component.TextBoxHelper;
import com.subex.automation.helpers.data.ValidationHelper;
import com.subex.automation.helpers.file.ExcelReader;
import com.subex.automation.helpers.report.Log4jHelper;
import com.subex.automation.helpers.util.FailureHelper;

public class EventLegCodeGroup extends PSAcceptanceTest
{
	protected ExcelReader excelData = null;
	protected Map<String, ArrayList<String>> legCodeExcel = null;
	protected Map<String, String> legCodeMap = null;
	protected ExcelHolder excelHolderObj = null;
	protected String path;
	protected String workBookName;
	protected String sheetName;
	protected String testCaseName;
	protected int paramVal;
	protected int colSize;
	protected String clientPartition;
	protected String multipleMatching;	
	protected String groupname;
	protected PSGenericHelper genericHelperObj = new PSGenericHelper();
	EventLegCodeGroupActionImpl legcodeActionObj = new EventLegCodeGroupActionImpl();

	/*
	 * Constructor for initializing excel Identifying the column size from the
	 * map passed
	 */
	@Test
	public EventLegCodeGroup(String path, String workBookName, String sheetName, String testCaseName) throws Exception {
		this.path = path;
		this.workBookName = workBookName;
		this.sheetName = sheetName;
		this.testCaseName = testCaseName;
		excelData = new ExcelReader();
		legCodeExcel = excelData.readDataByColumn(this.path, this.workBookName, this.sheetName, this.testCaseName);
		excelHolderObj = new ExcelHolder(legCodeExcel);
		colSize = excelHolderObj.totalColumns();
	}

	/*
	 * Overloaded constructor for reading data from excel if test case name
	 * appears more than once
	 * 
	 * @Param occurance : Test case instance in excel sheet Constructor for
	 * initializing excel Identifying the column size from the map
	 */
	public EventLegCodeGroup(String path, String workBookName, String sheetName, String testCaseName, int occurance)
			throws Exception {
		this.path = path;
		this.workBookName = workBookName;
		this.sheetName = sheetName;
		this.testCaseName = testCaseName;
		excelData = new ExcelReader();
		legCodeExcel = excelData.readDataByColumn(this.path, this.workBookName, this.sheetName, this.testCaseName,
				occurance);
		excelHolderObj = new ExcelHolder(legCodeExcel);
		colSize = excelHolderObj.totalColumns();
	}

	/*
	 * Configuring the event Leg code group
	 * 
	 */
	public void eventLegCodeGroupCreation() throws Exception {
		try {
			NavigationHelper.navigateToReferenceTable( "Event Leg Code Group" );
			for (paramVal = 0; paramVal < colSize; paramVal++) {

				legCodeMap = excelHolderObj.dataMap(paramVal);

				initializeVariables(legCodeMap);
				boolean isLegCodePresent = GridHelper.isValuePresent( "SearchGrid", groupname, "Group Name" );

				if (!isLegCodePresent) {

					legcodeActionObj.clickNewAction(clientPartition);
					GenericHelper.waitForLoadmask();
					newEventLegCodeGroup();

					saveEventLegCodeGroup();
					Log4jHelper.logInfo("Event Leg code Group is created successfully with name " + groupname);
				} else {
					editeventLegCodeGroup();
					Log4jHelper.logInfo("Event Leg code Group is available, modified multiple matching flag for name " + groupname);
				}

			}

		} catch (Exception e) {

			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}

	/*
	 * This method is to create new Traffic Type
	 */
	protected void newEventLegCodeGroup() throws Exception {

		TextBoxHelper.type("PS_Detail_wrapperID", "pelgName", groupname); 
		if(ValidationHelper.isTrue( multipleMatching ))
			CheckBoxHelper.check( "pelgMultipleMatch_InputElement");
	}
	
	public void editeventLegCodeGroup() throws Exception
	{
		GridHelper.clickRow( "SearchGrid", groupname, "Group Name" );
		legcodeActionObj.clickEditAction();
		if (CheckBoxHelper.isNotChecked("pelgMultipleMatch_InputElement")) 			
			CheckBoxHelper.check("pelgMultipleMatch_InputElement");
		ButtonHelper.click("SaveButton");
		GenericHelper.waitForLoadmask();
	}

	public void saveEventLegCodeGroup() throws Exception
	{
		ButtonHelper.click("SaveButton");
		GenericHelper.waitForLoadmask();
	}
	/*
	 * This method is to initialize instance variables
	 */
	protected void initializeVariables(Map<String, String> map) throws Exception {

		clientPartition = ExcelHolder.getKey(map, "Partition");		
		groupname = ExcelHolder.getKey(map, "Group Name");
		multipleMatching = ExcelHolder.getKey(map, "Multiple Matching");
	}
	
	/*
	 * This method is to validate search screen columns
	 */
	public void searchScreenColumnsValidation() throws Exception
	{		
		NavigationHelper.navigateToReferenceTable("Event Leg Code Group");
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		for ( paramVal = 0; paramVal < colSize; paramVal++ )
		{
			legCodeMap = excelHolderObj.dataMap( paramVal );
			String searchScreenColumns = ExcelHolder.getKey( legCodeMap, "SearchScreenColumns" );
			ArrayList<String> excelColumnNames = new ArrayList<String>();
			PSStringUtils strObj = new PSStringUtils();
			String[] searchGridColumnsArr = strObj.stringSplitFirstLevel( searchScreenColumns );
			for ( int col = 0; col < searchGridColumnsArr.length; col++ )
			{
				excelColumnNames.add( searchGridColumnsArr[col] );
			}
			genericHelperObj.totalColumns( excelColumnNames );
		}

	}
	
	/*
	 * This method is for eventLegCodeGroup deletion
	 */
	public void eventLegCodeGroupDelete() throws Exception {
		NavigationHelper.navigateToReferenceTable( "Event Leg Code Group" );
		for (paramVal = 0; paramVal < colSize; paramVal++) {

			legCodeMap = excelHolderObj.dataMap(paramVal);
			clientPartition = ExcelHolder.getKey(legCodeMap, "Partition");		
			groupname = ExcelHolder.getKey(legCodeMap, "Group Name");
			
			genericHelperObj.selectPartitionFilter(clientPartition, "Non Deleted Items");
			
			boolean isLegCodePresent = GridHelper.isValuePresent( "SearchGrid", groupname, "Group Name" );

			if (isLegCodePresent) {
				legcodeActionObj.clickDeleteAction( groupname );
				
				genericHelperObj.selectPartitionFilter(clientPartition, "Deleted Items");
				GenericHelper.waitForLoadmask();
				assertTrue(GridHelper.isValuePresent("SearchGrid", groupname, "Group Name"), groupname);
				Log4jHelper.logInfo("Event Leg Code Group is deleted successfully :" + groupname);

			} else {
				Log4jHelper.logInfo("Event Leg Code Group is not available with :" + groupname);
			}

		}
	}

	/*
	 * This method is for eventLegCodeGroup un delete
	 */
	public void eventLegCodeGroupUnDelete() throws Exception {

		NavigationHelper.navigateToReferenceTable( "Event Leg Code Group" );
		for (paramVal = 0; paramVal < colSize; paramVal++) {

			legCodeMap = excelHolderObj.dataMap(paramVal);
			clientPartition = ExcelHolder.getKey(legCodeMap, "Partition");		
			groupname = ExcelHolder.getKey(legCodeMap, "Group Name");
			genericHelperObj.selectPartitionFilter(clientPartition, "Deleted Items");
			
			boolean isLegCodePresent = GridHelper.isValuePresent( "SearchGrid", groupname, "Group Name" );

			if (isLegCodePresent) {
				legcodeActionObj.clickUnDeleteACtion( groupname );
				genericHelperObj.selectPartitionFilter(clientPartition, "Non Deleted Items");
				assertTrue(GridHelper.isValuePresent("SearchGrid", groupname, "Group Name"), groupname);
				Log4jHelper.logInfo("Event Leg Code Group is un deleted successfully :" + groupname);

			} else {
				Log4jHelper.logInfo("Event Leg Code Group is not available with :" + groupname);
			}

		}
	}
}
