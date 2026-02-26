package com.subex.rocps.automation.helpers.application.aggregation;

import java.util.ArrayList;
import java.util.Map;

import com.subex.automation.helpers.application.NavigationHelper;
import com.subex.automation.helpers.component.ButtonHelper;
import com.subex.automation.helpers.component.GenericHelper;
import com.subex.automation.helpers.component.GridHelper;
import com.subex.automation.helpers.component.SearchGridHelper;
import com.subex.automation.helpers.component.TabHelper;
import com.subex.automation.helpers.file.ExcelReader;
import com.subex.automation.helpers.report.Log4jHelper;
import com.subex.automation.helpers.util.FailureHelper;
import com.subex.rocps.automation.helpers.application.aggregation.aggregationconfiguration.AggregationActionsImpl;
import com.subex.rocps.automation.helpers.application.aggregation.aggregationconfiguration.AggregationFieldsSelectionDetailImpl;
import com.subex.rocps.automation.helpers.application.aggregation.aggregationconfiguration.AggregationOptionDetailsImpl;
import com.subex.rocps.automation.helpers.application.genericHelpers.PSGenericHelper;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;
import com.subex.rocps.automation.utils.ExcelHolder;
import com.subex.rocps.automation.utils.PSStringUtils;

public class AggregationConfiguration extends PSAcceptanceTest {

	protected ExcelReader excelData;
	protected ExcelHolder excelHolderObj;
	protected Map<String, ArrayList<String>> agcConfigExcelReaderMap;
	protected Map<String, String> agcConfigMap;

	protected String path;
	protected String workBookName;
	protected String sheetName;
	protected String testCaseName;
	protected int paramVal;
	protected int colSize;
	protected int occurence;

	protected String clientPartition;
	protected String name;
	protected String tablePrefix;
	protected PSGenericHelper genericHelperObj = new PSGenericHelper();
	protected AggregationActionsImpl agcActionObj = new AggregationActionsImpl();
	PSStringUtils strObj = new PSStringUtils();
	/*
	 * Constructor for initializing excel Identifying the column size from the map
	 * passed
	 */
	public AggregationConfiguration(String path, String workBookName, String sheetName, String testCaseName)
			throws Exception {
		this.path = path;
		this.workBookName = workBookName;
		this.sheetName = sheetName;
		this.testCaseName = testCaseName;
		excelData = new ExcelReader();
		agcConfigExcelReaderMap = excelData.readDataByColumn(this.path, this.workBookName, this.sheetName,
				this.testCaseName);
		excelHolderObj = new ExcelHolder(agcConfigExcelReaderMap);
		colSize = excelHolderObj.totalColumns();
	}

	/*
	 * Overloaded constructor for reading data from excel if test case name appears
	 * more than once
	 * 
	 * @Param occurance : Test case instance in excel sheet Constructor for
	 * initializing excel Identifying the column size from the map
	 */
	public AggregationConfiguration(String path, String workBookName, String sheetName, String testCaseName,
			int occurence) throws Exception {
		this.path = path;
		this.workBookName = workBookName;
		this.sheetName = sheetName;
		this.testCaseName = testCaseName;
		this.occurence = occurence;
		excelData = new ExcelReader();
		agcConfigExcelReaderMap = excelData.readDataByColumn(this.path, this.workBookName, this.sheetName,
				this.testCaseName);
		excelHolderObj = new ExcelHolder(agcConfigExcelReaderMap);
		colSize = excelHolderObj.totalColumns();
	}

	/*
	 * Method for configuring the new aggregation
	 */
	public void configureAggregation() throws Exception {
		try {

			NavigationHelper.navigateToScreen("Aggregation Configuration");
			GenericHelper.waitForLoadmask();

			for (int index = 0; index < colSize; index++) {

				agcConfigMap = excelHolderObj.dataMap(index);
				intialise();
				ButtonHelper.click( "ClearButton" );
				GenericHelper.waitForLoadmask(searchScreenWaitSec);
				if (!aggregationSearch()) {

					config();
					GenericHelper.waitForLoadmask( searchScreenWaitSec );
					assertTrue(GridHelper.isValuePresent("SearchGrid", name, "Name"));
					Log4jHelper.logInfo("Aggregation configured successfully with name : " + name
							+ " and table prefix : " + tablePrefix);
					assertTrue(GridHelper.isValuePresent("SearchGrid", "Draft", "Status"));
				} else {
					Log4jHelper.logInfo(
							"Aggregation configuration exists : " + name + " and table prefix : " + tablePrefix);
				}
			}

		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
		}

	}

	/*
	 * Method to change the aggregation status
	 */
	public void changeAggregationStatus() throws Exception {
		ButtonHelper.click( "ClearButton" );
		GenericHelper.waitForLoadmask(searchScreenWaitSec);
		boolean changeStatus = genericHelperObj.isGridTextValuePresent("PSDetail_agcName_txtid", name, "Name");
		GenericHelper.waitForLoadmask();
		if (changeStatus) {
			String value = GridHelper.getCellValue( "SearchGrid", 1, "Status" );
			if( value.contains( "Draft" ))
			{
			agcActionObj.setAggregationStatusAccepted(name);
			GenericHelper.waitForLoadmask();
			SearchGridHelper.gridFilterSearchWithTextBox("PSDetail_agcName_txtid", name, "Name");
			GenericHelper.waitForLoadmask();
			assertTrue(GridHelper.isValuePresent("SearchGrid", "Accepted", "Status"));
			GenericHelper.waitForLoadmask();
			}else 
				Log4jHelper.logInfo("Aggregation Configuration status is already changed to accepted" + name);
		} else
			Log4jHelper.logInfo("Aggregation Configuration is not available with name " + name);
	}

	/*
	 * Method for configuring the aggregation
	 */
	private void config() throws Exception {

		AggregationOptionDetailsImpl agcOptDetailsObj = new AggregationOptionDetailsImpl(agcConfigMap);
		agcActionObj.clickNewAction(clientPartition);
		agcOptDetailsObj.aggregationHeaderConfig();
		agcOptDetailsObj.selectAggregationCustomFields();
		agcOptDetailsObj.selectEventTypes();

		AggregationFieldsSelectionDetailImpl agcFieldSelObj = new AggregationFieldsSelectionDetailImpl(agcConfigMap);
		TabHelper.isPresent("//div[text()='Fields Selection']");
		TabHelper.gotoTab("//div[text()='Fields Selection']");
		TabHelper.isPresent("//div[@id='fieldsSelection']");
		agcFieldSelObj.versonAndFrequencySelection();
		agcFieldSelObj.keyValueGridConfig();
		agcOptDetailsObj.aggregationDetailSave();

	}

	private void intialise() throws Exception {

		clientPartition = ExcelHolder.getKey(agcConfigMap, "Partition");
		name = ExcelHolder.getKey(agcConfigMap, "Name");
		tablePrefix = ExcelHolder.getKey(agcConfigMap, "TablePrefix");

	}

	/*
	 * Method for performing aggregation search
	 */
	private boolean aggregationSearch() throws Exception {

		boolean flag = false;
		
		flag = genericHelperObj.isGridTextValuePresent("PSDetail_agcName_txtid", name, "Name");

		if (flag == false) {
			ButtonHelper.click("clear");
			GenericHelper.waitForLoadmask();
			flag = genericHelperObj.isGridTextValuePresent("PSDetail_agctablePrefix_txtId", tablePrefix,
					"Table Prefix");
			GenericHelper.waitForLoadmask();
		}

		return flag;
	}

	/*
	 * Method to discontinue Aggregation Configuration
	 */
	public void discontinueAggregation() throws Exception {
		boolean isaggrPresnet = genericHelperObj.isGridTextValuePresent("PSDetail_agcName_txtid", name, "Name");
		if (isaggrPresnet) {
			agcActionObj.discontinueAggregationConfiguration(name);
			GenericHelper.waitForLoadmask(searchScreenWaitSec);
			assertTrue(GridHelper.isValuePresent("SearchGrid", "Accepted", "Status"));
		} else
			Log4jHelper.logInfo("Aggregation Configuration is not available with name " + name);
	}

	/*
	 * This method is to validate search screen columns
	 */
	public void searchScreenColumnsValidation() throws Exception
	{		
		NavigationHelper.navigateToScreen( "Aggregation Configuration" );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		for ( paramVal = 0; paramVal < colSize; paramVal++ )
		{
			agcConfigMap = excelHolderObj.dataMap( paramVal );
			String searchScreenColumns = ExcelHolder.getKey( agcConfigMap, "SearchScreenColumns" );
			ArrayList<String> excelColumnNames = new ArrayList<String>();
			String[] searchGridColumnsArr =  strObj.stringSplitFirstLevel( searchScreenColumns );
			for ( int col = 0; col < searchGridColumnsArr.length; col++ )
			{
				excelColumnNames.add( searchGridColumnsArr[col] );
			}
			genericHelperObj.totalColumns( excelColumnNames );
		}

	}
	/*
	 * This method is for Aggregation Configuration deletion
	 */
	public void aggregationConfigDelete() throws Exception {

		try
		{
			NavigationHelper.navigateToScreen("Aggregation Configuration");
			GenericHelper.waitForLoadmask();

			for (int index = 0; index < colSize; index++) {

				agcConfigMap = excelHolderObj.dataMap(index);

				clientPartition = ExcelHolder.getKey(agcConfigMap, "Partition");
				name = ExcelHolder.getKey(agcConfigMap, "Name");
				
				genericHelperObj.selectPartitionFilter(clientPartition, "Non Deleted Items");
				boolean isaggregationPresent = genericHelperObj.isGridTextValuePresent("PSDetail_agcName_txtid", name,
						"Name");
				if (isaggregationPresent) {
					agcActionObj.clickDeleteAction(name);
					genericHelperObj.selectPartitionFilter(clientPartition, "Deleted Items");
					assertTrue(GridHelper.isValuePresent("SearchGrid", name, "Name"), name);
					Log4jHelper.logInfo("Aggregation Configuration is deleted successfully : " + name);

				} else
					Log4jHelper.logInfo("Aggregation Configuration is not available : " + name);

			}
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage(e);
		}
	}

	/*
	 * This method is for Aggregation Configuration un delete
	 */
	public void aggregationConfigUnDelete() throws Exception {

		try
		{
			NavigationHelper.navigateToScreen("Aggregation Configuration");
			GenericHelper.waitForLoadmask();

			for (int index = 0; index < colSize; index++) {

				agcConfigMap = excelHolderObj.dataMap(index);

				clientPartition = ExcelHolder.getKey(agcConfigMap, "Partition");
				name = ExcelHolder.getKey(agcConfigMap, "Name");
				
				genericHelperObj.selectPartitionFilter(clientPartition, "Deleted Items");

				boolean isaggregationPresent = genericHelperObj.isGridTextValuePresent("PSDetail_agcName_txtid", name,
						"Name");
				if (isaggregationPresent) {
					GenericHelper.waitForLoadmask( detailScreenWaitSec );
					agcActionObj.clickUnDeleteAction(name);
					genericHelperObj.selectPartitionFilter(clientPartition, "Non Deleted Items");
					assertTrue(GridHelper.isValuePresent("SearchGrid", name, "Name"), name);
					Log4jHelper.logInfo("Aggregation Configuration is un deleted successfully : " + name);

				} else
					Log4jHelper.logInfo("Aggregation Configuration is not available : " + name);

			}
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage(e);
		}
	}

}
