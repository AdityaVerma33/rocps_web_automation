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
import com.subex.automation.helpers.component.TextBoxHelper;
import com.subex.automation.helpers.data.ValidationHelper;
import com.subex.automation.helpers.file.ExcelReader;
import com.subex.automation.helpers.report.Log4jHelper;
import com.subex.automation.helpers.util.FailureHelper;

public class AccountCategory extends PSAcceptanceTest
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
	protected String name;	
	
	protected PSGenericHelper genericHelperObj = new PSGenericHelper();
	EventLegCodeGroupActionImpl legcodeActionObj = new EventLegCodeGroupActionImpl();

	/*
	 * Constructor for initializing excel Identifying the column size from the
	 * map passed
	 */
	@Test
	public AccountCategory(String path, String workBookName, String sheetName, String testCaseName) throws Exception {
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
	public AccountCategory(String path, String workBookName, String sheetName, String testCaseName, int occurance)
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
	 * Configuring the account Category
	 * 
	 */
	public void accountCategoryCreation() throws Exception {
		try {
			NavigationHelper.navigateToReferenceTable( "Account Category" );
			for (paramVal = 0; paramVal < colSize; paramVal++) {

				legCodeMap = excelHolderObj.dataMap(paramVal);

				initializeVariables(legCodeMap);
				boolean isAccountCategoryPresent = GridHelper.isValuePresent( "SearchGrid", name, "Name" );

				if (!isAccountCategoryPresent) {

					legcodeActionObj.clickNewAction(clientPartition);
					GenericHelper.waitForLoadmask();
					newAccountCategory();

					saveEventLegCodeGroup();
					Log4jHelper.logInfo("Account Category is created successfully with name " + name);
				} 

			}

		} catch (Exception e) {

			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}

	/*
	 * This method is to create new account Category
	 */
	protected void newAccountCategory() throws Exception {

		TextBoxHelper.type("SparkEntitySearch.window", "paclName", name); 
	
	}
	
	
	public void saveEventLegCodeGroup() throws Exception
	{
		//ButtonHelper.click("SaveButton");
		genericHelperObj.detailSave( "SaveButton", name, "Name" );
		GenericHelper.waitForLoadmask();
	}
	/*
	 * This method is to initialize instance variables
	 */
	protected void initializeVariables(Map<String, String> map) throws Exception {

		clientPartition = ExcelHolder.getKey(map, "Partition");		
		name = ExcelHolder.getKey(map, "Name");
		
	}
	
	/*
	 * This method is to validate search screen columns
	 */
	public void searchScreenColumnsValidation() throws Exception
	{		
		NavigationHelper.navigateToReferenceTable("Account Category");
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
	 * This method is for Account Category deletion
	 */
	public void accountCategoryDelete() throws Exception {
		NavigationHelper.navigateToReferenceTable( "Account Category" );
		for (paramVal = 0; paramVal < colSize; paramVal++) {

			legCodeMap = excelHolderObj.dataMap(paramVal);
			clientPartition = ExcelHolder.getKey(legCodeMap, "Partition");		
			name = ExcelHolder.getKey(legCodeMap, "Name");
			
			genericHelperObj.selectPartitionFilter(clientPartition, "Non Deleted Items");
			
			boolean isAccountCategoryPresent = GridHelper.isValuePresent( "SearchGrid", name, "Name" );

			if (isAccountCategoryPresent) {
				legcodeActionObj.clickDeleteAction( name );
				
				genericHelperObj.selectPartitionFilter(clientPartition, "Deleted Items");
				GenericHelper.waitForLoadmask();
				assertTrue(GridHelper.isValuePresent("SearchGrid", name, "Name"), name);
				Log4jHelper.logInfo("Account Category is deleted successfully :" + name);

			} else {
				Log4jHelper.logInfo("Account Category is not available with :" + name);
			}

		}
	}

	/*
	 * This method is for Account Category un delete
	 */
	public void accountCategoryUnDelete() throws Exception {

		NavigationHelper.navigateToReferenceTable( "Account Category" );
		for (paramVal = 0; paramVal < colSize; paramVal++) {

			legCodeMap = excelHolderObj.dataMap(paramVal);
			clientPartition = ExcelHolder.getKey(legCodeMap, "Partition");		
			name = ExcelHolder.getKey(legCodeMap, "Name");
			genericHelperObj.selectPartitionFilter(clientPartition, "Deleted Items");
			
			boolean isAccountCategoryPresent = GridHelper.isValuePresent( "SearchGrid", name, "Name" );

			if (isAccountCategoryPresent) {
				legcodeActionObj.clickUnDeleteACtion( name );
				genericHelperObj.selectPartitionFilter(clientPartition, "Non Deleted Items");
				assertTrue(GridHelper.isValuePresent("SearchGrid", name, "Name"), name);
				Log4jHelper.logInfo("Account Category is un deleted successfully :" + name);

			} else {
				Log4jHelper.logInfo("Account Category is not available with :" + name);
			}

		}
	}
}
