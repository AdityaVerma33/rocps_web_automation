package com.subex.rocps.automation.helpers.application.partnerConfiguration;

import java.util.ArrayList;
import java.util.Map;

import org.testng.annotations.Test;

import com.subex.automation.helpers.application.NavigationHelper;
import com.subex.automation.helpers.component.ButtonHelper;
import com.subex.automation.helpers.component.CheckBoxHelper;
import com.subex.automation.helpers.component.ComboBoxHelper;
import com.subex.automation.helpers.component.GenericHelper;
import com.subex.automation.helpers.component.GridHelper;
import com.subex.automation.helpers.component.SearchGridHelper;
import com.subex.automation.helpers.component.TabHelper;
import com.subex.automation.helpers.component.TextBoxHelper;
import com.subex.automation.helpers.data.ValidationHelper;
import com.subex.automation.helpers.file.ExcelReader;
import com.subex.automation.helpers.report.Log4jHelper;
import com.subex.automation.helpers.util.FailureHelper;
import com.subex.rocps.automation.helpers.application.filters.EntityFilterHelper;
import com.subex.rocps.automation.helpers.application.genericHelpers.PSDataComponentHelper;
import com.subex.rocps.automation.helpers.application.genericHelpers.PSGenericHelper;
import com.subex.rocps.automation.helpers.application.partnerConfiguration.agent.AgentActionImpl;
import com.subex.rocps.automation.helpers.application.partnerConfiguration.agent.AgentDetailImpl;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;
import com.subex.rocps.automation.utils.ExcelHolder;
import com.subex.rocps.automation.utils.PSStringUtils;

public class Agent extends PSAcceptanceTest {

	protected ExcelReader excelData = null;
	protected Map<String, ArrayList<String>> agentMapExcel = null;
	protected Map<String, String> agentMap = null;
	protected ExcelHolder excelHolderObj = null;
	protected String path;
	protected String workBookName;
	protected String sheetName;
	protected String testCaseName;
	protected int paramVal;
	protected int colSize;
	protected String clientPartition;
	protected String companyName;
	protected String franchise;
	protected String currency;
	protected String type;
	protected String agentCode;
	protected String title;
	protected String foreName;
	protected String surname;
	protected String companyAddress;
	protected String addressName;
	protected String typeAddress;
	protected String addressedTo;
	protected String salutation;
	protected String company;
	protected String parentCompany;
	protected String agentAddressName;
	protected String agentTypeAddress;
	protected String agentAddressedTo;
	protected String agentSalutation;
	protected String agentCompany;
	protected String agentStreet1;
	protected String agentStreet2;
	protected String agentStreet3;
	protected String agentStreet4;
	protected String agentTown;
	protected String agentCounty;
	protected String agentPostCode;
	protected String agentCountry;
	protected String agentMainAgentAddress;
	protected String contactNameInternet;
	protected String typeInternet;
	protected String addressInternet;
	protected String defaultAddress;
	protected String contactNameDetail;
	protected String typeContact;
	protected String number;
	protected String defaultNumber;
	protected String rootAgent;
	protected String parentAgent;
	protected PSGenericHelper genericHelperObj = new PSGenericHelper();
	protected String regex = new PSStringUtils().regexFirstLevelDelimeter();
	protected PSDataComponentHelper compObj = new PSDataComponentHelper();
	PSStringUtils strObj = new PSStringUtils();

	/*
	 * Constructor for initializing excel Identifying the column size from the map
	 * passed
	 */
	public Agent(String path, String workBookName, String sheetName, String testCaseName) throws Exception {
		this.path = path;
		this.workBookName = workBookName;
		this.sheetName = sheetName;
		this.testCaseName = testCaseName;
		excelData = new ExcelReader();
		agentMapExcel = excelData.readDataByColumn(this.path, this.workBookName, this.sheetName, this.testCaseName);
		excelHolderObj = new ExcelHolder(agentMapExcel);
		colSize = excelHolderObj.totalColumns();
	}

	/*
	 * Overloaded constructor for reading data from excel if test case name appears
	 * more than once
	 * 
	 * @Param occurance : Test case instance in excel sheet Constructor for
	 * initializing excel Identifying the column size from the map
	 */
	public Agent(String path, String workBookName, String sheetName, String testCaseName, int occurance)
			throws Exception {
		this.path = path;
		this.workBookName = workBookName;
		this.sheetName = sheetName;
		this.testCaseName = testCaseName;
		excelData = new ExcelReader();
		agentMapExcel = excelData.readDataByColumn(this.path, this.workBookName, this.sheetName, this.testCaseName,
				occurance);
		excelHolderObj = new ExcelHolder(agentMapExcel);
		colSize = excelHolderObj.totalColumns();
	}

	/*
	 * Configuring the Agent
	 * 
	 * @method : isAgentPresent returns false then Agent is configured
	 * isAgentPresent returns true then Agent is not configured
	 */
	public void agentCreation() throws Exception {
		try {
			NavigationHelper.navigateToScreen("Agent");
			for (paramVal = 0; paramVal < colSize; paramVal++) {

				agentMap = excelHolderObj.dataMap(paramVal);

				initializeVariables(agentMap);
				ButtonHelper.click( "ClearButton" );
				GenericHelper.waitForLoadmask(searchScreenWaitSec);
				createNewAgent();
				
			}

		} catch (Exception e) {

			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}

	public void createNewAgent() throws Exception {
		if (!isAgentPresent()) {

			AgentDetailImpl agentDetailObj = new AgentDetailImpl(agentMap);
			AgentActionImpl agentActionObj = new AgentActionImpl();
			agentActionObj.clicknewAgent(clientPartition);
			agentDetailObj.newAgentBasicDetails();
			agentDetailObj.hierarchy();
			agentDetailObj.addressDetails();
			agentDetailObj.contactInfotab();
			agentActionObj.saveAgent(company+" ("+agentCode+")");
			Log4jHelper.logInfo("Agent is created successfully with name " + companyName);

		} else {
			Log4jHelper.logInfo("Agent set is available with name " + companyName);
		}
	}
	
	/*
	 * This method is to edit agent
	 */
	public void editAgent() throws Exception {
		try {
			NavigationHelper.navigateToScreen("Agent");
			for (paramVal = 0; paramVal < colSize; paramVal++) {

				agentMap = excelHolderObj.dataMap(paramVal);

				initializeVariables(agentMap);
				ButtonHelper.click( "ClearButton" );
				GenericHelper.waitForLoadmask(searchScreenWaitSec);
				if (isAgentPresent()) {
					
					NavigationHelper.navigateToEdit( "SearchGrid", 1 );
					assertEquals( NavigationHelper.getScreenTitle(), "Edit Agent" );
					AgentDetailImpl agentDetailObj = new AgentDetailImpl(agentMap);
					AgentActionImpl agentActionObj = new AgentActionImpl();					
					agentDetailObj.editAgentBasicDetails();
					agentDetailObj.hierarchy();
					agentDetailObj.addressDetails();
					agentDetailObj.contactInfotab();
					agentActionObj.saveAgent(company+" ("+agentCode+")");
					Log4jHelper.logInfo("Agent is edited successfully with name " + companyName);

				} else {
					Log4jHelper.logInfo("Agent set is available with name " + companyName);
				}
				
			}

		} catch (Exception e) {

			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	

	/*
	 * This method is used to initialize instance variables
	 */
	protected void initializeVariables(Map<String, String> map) throws Exception {

		clientPartition = ExcelHolder.getKey(map, "Partition");
		companyName = ExcelHolder.getKey(map, "CompanyName");
		company = ExcelHolder.getKey(map, "Company");
		agentCode = ExcelHolder.getKey(map, "AgentCode");
	}

	public boolean isAgentPresent() throws Exception {
		SearchGridHelper.searchWithTextBox("Detail_companyName_txtID", companyName, "Agent");		
		int row = GridHelper.getRowCount("SearchGrid");
		boolean rowVal = false;
		if (row > 0) {
			for (int i = 0; i < row; i++) {
				String cellValue = GridHelper.getCellValue("SearchGrid", i + 1, "Agent");
				GenericHelper.waitForLoadmask();
				rowVal = cellValue.contains(companyName);				
				return true;
			}
			return rowVal;
		}
		return false;
	}
	
	/*
	 * This method is to validate search screen columns
	 */
	public void searchScreenColumnsValidation() throws Exception
	{		
		NavigationHelper.navigateToScreen( "Agent" );
		for ( paramVal = 0; paramVal < colSize; paramVal++ )
		{
			agentMap = excelHolderObj.dataMap( paramVal );
			String searchScreenColumns = ExcelHolder.getKey( agentMap, "SearchScreenColumns" );
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
	 * This method is to delete Agent
	 */
	public void agentDelete() throws Exception {

		NavigationHelper.navigateToScreen("Agent");

		for (paramVal = 0; paramVal < colSize; paramVal++) {

			agentMap = excelHolderObj.dataMap(paramVal);

			clientPartition = ExcelHolder.getKey(agentMap, "Partition");
			agentCode = ExcelHolder.getKey(agentMap, "AgentCode");
			genericHelperObj.selectPartitionFilter(clientPartition, "Non Deleted Items");
			int isagentcodePresent = SearchGridHelper.gridFilterSearchWithTextBox("pageCode", agentCode, "Agent Code");
			if (isagentcodePresent > 0) {
				genericHelperObj.clickDeleteOrUnDeleteAction(agentCode, "Agent Code", "Delete");
				GenericHelper.waitForLoadmask();
				genericHelperObj.selectPartitionFilter(clientPartition, "Deleted Items");
				assertTrue(GridHelper.isValuePresent("SearchGrid", agentCode, "Agent Code"), agentCode);
				Log4jHelper.logInfo("Agent is deleted successfully : " + agentCode);

			} else {
				Log4jHelper.logInfo("Agent is not available : " + agentCode);
			}

		}
	}

	/*
	 * This method is to un delete Agent
	 */
	public void agentUnDelete() throws Exception {

		NavigationHelper.navigateToScreen("Agent");

		for (paramVal = 0; paramVal < colSize; paramVal++) {

			agentMap = excelHolderObj.dataMap(paramVal);

			clientPartition = ExcelHolder.getKey(agentMap, "Partition");
			agentCode = ExcelHolder.getKey(agentMap, "AgentCode");
			genericHelperObj.selectPartitionFilter(clientPartition, "Deleted Items");

			int isagentcodePresent = SearchGridHelper.gridFilterSearchWithTextBox("pageCode", agentCode, "Agent Code");
			if (isagentcodePresent > 0) {
				genericHelperObj.clickDeleteOrUnDeleteAction(agentCode, "Agent Code", "Undelete");
				GenericHelper.waitForLoadmask();
				genericHelperObj.selectPartitionFilter(clientPartition, "Non Deleted Items");
				assertTrue(GridHelper.isValuePresent("SearchGrid", agentCode, "Agent Code"), agentCode);
				Log4jHelper.logInfo("Agent is un deleted successfully : " + agentCode);

			} else {
				Log4jHelper.logInfo("Agent is not available : " + agentCode);
			}

		}
	}
}
