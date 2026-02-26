package com.subex.rocps.automation.helpers.application.networkConfiguraiton;

import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Map;

import com.subex.rocps.automation.helpers.application.genericHelpers.PSDataComponentHelper;
import com.subex.rocps.automation.helpers.application.genericHelpers.PSGenericHelper;
import com.subex.rocps.automation.helpers.application.networkConfiguraiton.rulestringset.RuleStringSetActionImpl;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;
import com.subex.rocps.automation.utils.ExcelHolder;
import com.subex.rocps.automation.utils.PSStringUtils;

import com.subex.automation.helpers.application.NavigationHelper;
import com.subex.automation.helpers.component.ButtonHelper;
import com.subex.automation.helpers.component.GenericHelper;
import com.subex.automation.helpers.component.GridHelper;
import com.subex.automation.helpers.component.SearchGridHelper;
import com.subex.automation.helpers.component.TextBoxHelper;
import com.subex.automation.helpers.data.ValidationHelper;
import com.subex.automation.helpers.file.ExcelReader;
import com.subex.automation.helpers.report.Log4jHelper;

public class RuleStringSet extends PSAcceptanceTest
{

	protected ExcelReader excelData = null;
	protected Map<String, ArrayList<String>> ruleStringExcel = null;
	protected Map<String, String> ruleStringMap = null;
	protected ExcelHolder excelHolderObj = null;
	protected String path;
	protected String workBookName;
	protected String sheetName;
	protected String testCaseName;
	protected int paramVal;
	protected int colSize;
	protected String clientPartition;
	protected String name;
	protected String match;
	protected String remove;
	protected String add;	
	protected String regex = new PSStringUtils().regexFirstLevelDelimeter();
	protected PSDataComponentHelper compObj = new PSDataComponentHelper();
	protected PSGenericHelper genHelperObj = new PSGenericHelper();
	RuleStringSetActionImpl ruleStringActionObj = new RuleStringSetActionImpl();

	/*
	 * Constructor for initializing excel Identifying the column size from the map
	 * passed
	 */
	@Test
	public RuleStringSet(String path, String workBookName, String sheetName, String testCaseName) throws Exception {
		this.path = path;
		this.workBookName = workBookName;
		this.sheetName = sheetName;
		this.testCaseName = testCaseName;
		excelData = new ExcelReader();
		ruleStringExcel = excelData.readDataByColumn(this.path, this.workBookName, this.sheetName, this.testCaseName);
		excelHolderObj = new ExcelHolder(ruleStringExcel);
		colSize = excelHolderObj.totalColumns();
	}

	/*
	 * Overloaded constructor for reading data from excel if test case name appears
	 * more than once
	 * 
	 * @Param occurance : Test case instance in excel sheet Constructor for
	 * initializing excel Identifying the column size from the map
	 */
	public RuleStringSet(String path, String workBookName, String sheetName, String testCaseName, int occurance)
			throws Exception {
		this.path = path;
		this.workBookName = workBookName;
		this.sheetName = sheetName;
		this.testCaseName = testCaseName;
		excelData = new ExcelReader();
		ruleStringExcel = excelData.readDataByColumn(this.path, this.workBookName, this.sheetName, this.testCaseName,
				occurance);
		excelHolderObj = new ExcelHolder(ruleStringExcel);
		colSize = excelHolderObj.totalColumns();
	}

	/*
	 * Configuring the Rule String Set
	 * 
	 * @method : isRuleStringSetPresent returns false then RuleStringSet is
	 * configured isRuleStringSetPresent returns true then RuleStrinset
	 * is not configured
	 */
	public void ruleStringCreation() throws Exception {
		try {
			NavigationHelper.navigateToScreen("Rule String Set");
			for (paramVal = 0; paramVal < colSize; paramVal++) {

				ruleStringMap = excelHolderObj.dataMap(paramVal);

				initializeVariables(ruleStringMap);

				if (!isRuleStringSetPresent()) {
					ruleStringActionObj.clickNewAction( clientPartition );
					newRuleStringSet();	
					saveRuleStringSet();
					Log4jHelper.logInfo("Rule String Set is created successfully with name " + name);

				} else {
					Log4jHelper.logInfo("Rule String set is available with name " + name);
				}

			}
		} catch (Exception e) {

			throw e;
		}
	}

	
	/*
	 * This method is to create new RuleString Set
	 */
	protected void newRuleStringSet() throws Exception {

		String[] matchArr = match.split(regex, -1);
		String[] removeArr = remove.split(regex, -1);
		String[] addArr = add.split( regex, -1 );
		
		TextBoxHelper.type( "prstName", name );
		
		for (int j = 0; j < matchArr.length; j++) {
			ruleStringActionObj.addRuleString();					
			TextBoxHelper.type("ruleStringDetail.window", "prlsMatchDigits", matchArr[j]);			
			TextBoxHelper.type("ruleStringDetail.window", "prlsRemoveDigits", removeArr[j]);			
			TextBoxHelper.type("ruleStringDetail.window", "prlsAddDigits", addArr[j]);	
			ButtonHelper.click("ruleStringDetail.window", "ruleStringDetail.Ok");	

		}

	}
	
	/*
	 * this method is to save rule string set
	 */
	public void saveRuleStringSet() throws Exception
	{
		ButtonHelper.click("ruleStringSetDetail.save");
		GenericHelper.waitForSave();
		assertTrue(GridHelper.isValuePresent("Detail_eventDefn_gridID", name, "Name"),
					"RuleString set is not configured");
		
	}

	
	/*
	 * This method is to initialize instance variables
	 */
	protected void initializeVariables(Map<String, String> map) throws Exception {

		clientPartition = ExcelHolder.getKey(map, "Partition");
		name = ExcelHolder.getKey(map, "Name");
		match = ExcelHolder.getKey(map, "Match");
		remove = ExcelHolder.getKey(map, "Remove");
		add = ExcelHolder.getKey(map, "Add");

	}

	/*
	 * This method is to check if RuleStringset is already present
	 */

	protected boolean isRuleStringSetPresent() throws Exception {

		try {
			SearchGridHelper.searchWithTextBox("prstName", name, "Name");
			return GridHelper.isValuePresent("Detail_eventDefn_gridID", name, "Name");
		} catch (Exception e) {
			throw e;
		}
	}
	
	/*
	 * This method is to validate search screen columns
	 */
	public void searchScreenColumnsValidation() throws Exception
	{		
		NavigationHelper.navigateToScreen( "Rule String Set" );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		for ( paramVal = 0; paramVal < colSize; paramVal++ )
		{
			ruleStringMap = excelHolderObj.dataMap( paramVal );
			String searchScreenColumns = ExcelHolder.getKey( ruleStringMap, "SearchScreenColumns" );
			ArrayList<String> excelColumnNames = new ArrayList<String>();
			PSStringUtils strObj = new PSStringUtils();
			String[] searchGridColumnsArr = strObj.stringSplitFirstLevel( searchScreenColumns );
			for ( int col = 0; col < searchGridColumnsArr.length; col++ )
			{
				excelColumnNames.add( searchGridColumnsArr[col] );
			}
			genHelperObj.totalColumns( excelColumnNames );
		}
	
	}

	/*
	 * This method is for rule string set deletion
	 */
	public void ruleStringSetDelete() throws Exception {

		NavigationHelper.navigateToScreen("Rule String Set");
		for (paramVal = 0; paramVal < colSize; paramVal++) {

			ruleStringMap = excelHolderObj.dataMap(paramVal);

			clientPartition = ExcelHolder.getKey(ruleStringMap, "Partition");
			name = ExcelHolder.getKey(ruleStringMap, "Name");

			
			genHelperObj.selectPartitionFilter(clientPartition, "Non Deleted Items");
			GenericHelper.waitForLoadmask();
			if (isRuleStringSetPresent()) {
				ruleStringActionObj.clickDeleteAction( name );
				genHelperObj.selectPartitionFilter(clientPartition, "Deleted Items");
				assertTrue(GridHelper.isValuePresent("SearchGrid", name, "Name"), name);
				Log4jHelper.logInfo("Rule string set is deleted successfully with name " + name);

			} else {
				Log4jHelper.logInfo("Rule string set is not available with name " + name);
			}

		}
	}

	/*
	 * This method is for rule StringSet un delete
	 */
	public void ruleStringSetUnDelete() throws Exception {

		NavigationHelper.navigateToScreen("Rule String Set");
		for (paramVal = 0; paramVal < colSize; paramVal++) {

			ruleStringMap = excelHolderObj.dataMap(paramVal);

			clientPartition = ExcelHolder.getKey(ruleStringMap, "Partition");
			name = ExcelHolder.getKey(ruleStringMap, "Name");

			genHelperObj.selectPartitionFilter(clientPartition, "Deleted Items");
			if (isRuleStringSetPresent()) {
				
				ruleStringActionObj.clickUnDeleteACtion( name );
				genHelperObj.selectPartitionFilter(clientPartition, "Non Deleted Items");
				assertTrue(GridHelper.isValuePresent("SearchGrid", name, "Name"), name);
				Log4jHelper.logInfo("Rule string set is un deleted successfully with name " + name);

			} else {
				Log4jHelper.logInfo("Rule string set is not available with name " + name);
			}

		}
	}
}
