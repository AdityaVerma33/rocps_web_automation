package com.subex.rocps.automation.helpers.application.matchandrate;

import org.testng.Assert;

import java.util.ArrayList;
import java.util.Map;

import com.subex.rocps.automation.helpers.application.genericHelpers.PSGenericHelper;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;
import com.subex.rocps.automation.utils.ExcelHolder;
import com.subex.rocps.automation.utils.PSStringUtils;

import com.subex.automation.helpers.application.NavigationHelper;
import com.subex.automation.helpers.component.ButtonHelper;
import com.subex.automation.helpers.component.GenericHelper;
import com.subex.automation.helpers.component.GridHelper;
import com.subex.automation.helpers.file.ExcelReader;
import com.subex.automation.helpers.report.Log4jHelper;
import com.subex.automation.helpers.util.FailureHelper;

public class PreRatingMatchRule extends PSAcceptanceTest
{
	protected ExcelReader excelReader = null;
	protected Map<String, ArrayList<String>> excelReaderMapObj = null;
	protected Map<String, String> mapObj = null;
	protected ExcelHolder excelHolderObj = null;

	int colSize = 0;
	int occurence = 0;
	String path;
	String workBook;
	String sheetName;
	String testCaseName;

	protected String partition;
	protected String matchRuleName;
	protected String fromDate;
	protected String toDate;
	protected String matchRuleGroup;
	protected String ratingFrom;
	protected String ratingTo;
	protected String tariffFromDate;
	protected String tariffToDate;
	protected String identifierDefn;
	protected String identifierValue;

	protected PSGenericHelper genHelperObj = new PSGenericHelper();

	/*
	 * Constructor for initializing excel and identifying the parameter value colums
	 * in a test case
	 */
	public PreRatingMatchRule( String path, String workBook, String sheetName, String testCaseName ) throws Exception
	{
		this.path = path;
		this.workBook = workBook;
		this.sheetName = sheetName;
		this.testCaseName = testCaseName;
		excelReader = new ExcelReader();
		excelReaderMapObj = excelReader.readDataByColumn( this.path, this.workBook, this.sheetName, this.testCaseName );
		excelHolderObj = new ExcelHolder( excelReaderMapObj );
		colSize = excelHolderObj.totalColumns();

	}

	/*
	 * Overloaded constructor for reading data from excel if test case name appears
	 * more than once
	 * 
	 * @Param occurance : Test case instance in excel sheet Constructor for
	 * initializing excel Identifying the column size from the map
	 */
	public PreRatingMatchRule( String path, String workBook, String sheetName, String testCaseName, int occurence ) throws Exception
	{
		this.path = path;
		this.workBook = workBook;
		this.sheetName = sheetName;
		this.testCaseName = testCaseName;
		this.occurence = occurence;
		excelReader = new ExcelReader();
		excelReaderMapObj = excelReader.readDataByColumn( this.path, this.workBook, this.sheetName, this.testCaseName, this.occurence );
		excelHolderObj = new ExcelHolder( excelReaderMapObj );
		colSize = excelHolderObj.totalColumns();
	}

	public void createPreRatingMatchRule() throws Exception
	{
		try
		{
			for ( int paramVal = 0; paramVal < colSize; paramVal++ )
			{
				mapObj = excelHolderObj.dataMap( paramVal );
				initialiseVariables( mapObj );

				NavigationHelper.navigateToScreen( "Pre Rating Match Rule" );
				String emrSearchColHeader = "Name";
				ButtonHelper.click( "ClearButton" );
				GenericHelper.waitForLoadmask(searchScreenWaitSec);
				boolean isPreRatingMatchRuleExists = genHelperObj.isGridTextValuePresent( "PS_Details_pmrNameTxtId", matchRuleName, emrSearchColHeader );

				if ( !isPreRatingMatchRuleExists )
				{
					PreRatingMatchRuleDetailImpl pmrDetailObj = new PreRatingMatchRuleDetailImpl( mapObj );
					genHelperObj.clickNewAction( partition );
					pmrDetailObj.preRatingMatchRuleBasicDetails();
					pmrDetailObj.addingMatchCriteriaValues();
					pmrDetailObj.tariffTimeLineConfig();
					pmrDetailObj.detailSave();
					String matchRuleColumnHeaderName = "Name";
					Assert.assertTrue( GridHelper.isValuePresent( "SearchGrid", matchRuleName, matchRuleColumnHeaderName ), "pre rating match rule : " + matchRuleName + "is not saved" + testCaseName );
					Log4jHelper.logInfo( "pre rating match rule is saved successfully : " + matchRuleName );
				}
				else				
					Log4jHelper.logInfo( "pre rating match rule exists : " + matchRuleName );				
			}

		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

	private void initialiseVariables( Map<String, String> mapObj ) throws Exception
	{
		Map<String, String> map = mapObj;
		partition = ExcelHolder.getKey( map, "Partition" );
		matchRuleName = ExcelHolder.getKey( map, "Name" );
	}

	public void preRatingMatchRuleDelete() throws Exception
	{

		for ( int paramVal = 0; paramVal < colSize; paramVal++ )
		{
			mapObj = excelHolderObj.dataMap( paramVal );
			initialiseVariables( mapObj );
			NavigationHelper.navigateToScreen( "Pre Rating Match Rule" );
			genHelperObj.selectPartitionFilter( partition, "Non Deleted Items" );
			GenericHelper.waitForLoadmask();
			String emrSearchColHeader = "Name";
			boolean isPreRatingMatchRuleExists = genHelperObj.isGridTextValuePresent( "PS_Details_pmrNameTxtId", matchRuleName, emrSearchColHeader );

			if ( isPreRatingMatchRuleExists )
			{
				genHelperObj.clickDeleteOrUnDeleteAction( matchRuleName, "Name", "Delete" );
				GenericHelper.waitForLoadmask();

				genHelperObj.selectPartitionFilter( partition, "Deleted Items" );
				assertTrue( GridHelper.isValuePresent( "SearchGrid", matchRuleName, "Name" ), matchRuleName );
				Log4jHelper.logInfo( "pre rating match rule is deleted successfully : " + matchRuleName );
			}
			else			
				Log4jHelper.logInfo( "pre rating match rule is not available : " + matchRuleName );		

		}
	}
	
	/*
	 * This method is to validate search screen columns
	 */
	public void searchScreenColumnsValidation() throws Exception
	{		
		NavigationHelper.navigateToScreen( "Pre Rating Match Rule" );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		for ( int paramVal = 0; paramVal < colSize; paramVal++ )
		{
			mapObj = excelHolderObj.dataMap( paramVal );
			String searchScreenColumns = ExcelHolder.getKey( mapObj, "SearchScreenColumns" );
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

	public void preRatingMatchRuleUnDelete() throws Exception
	{

		for ( int paramVal = 0; paramVal < colSize; paramVal++ )
		{
			mapObj = excelHolderObj.dataMap( paramVal );
			initialiseVariables( mapObj );
			NavigationHelper.navigateToScreen( "Pre Rating Match Rule" );
			genHelperObj.selectPartitionFilter( partition, "Deleted Items" );
			GenericHelper.waitForLoadmask();
			String emrSearchColHeader = "Name";
			boolean isPreRatingMatchRuleExists = genHelperObj.isGridTextValuePresent( "PS_Details_pmrNameTxtId", matchRuleName, emrSearchColHeader );

			if ( isPreRatingMatchRuleExists )
			{
				genHelperObj.clickDeleteOrUnDeleteAction( matchRuleName, "Name", "Undelete" );
				GenericHelper.waitForLoadmask();

				genHelperObj.selectPartitionFilter( partition, "Non Deleted Items" );
				assertTrue( GridHelper.isValuePresent( "SearchGrid", matchRuleName, "Name" ), matchRuleName );
				Log4jHelper.logInfo( "pre rating match rule is un deleted successfully : " + matchRuleName );
			}
			else			
				Log4jHelper.logInfo( "pre rating match rule is not available : " + matchRuleName );		

		}
	}
	
	public void editPreRatingMatchRule() throws Exception
	{
		try
		{
			for ( int paramVal = 0; paramVal < colSize; paramVal++ )
			{
				mapObj = excelHolderObj.dataMap( paramVal );
				initialiseVariables( mapObj );

				NavigationHelper.navigateToScreen( "Pre Rating Match Rule" );
				String emrSearchColHeader = "Name";
				ButtonHelper.click( "ClearButton" );
				GenericHelper.waitForLoadmask(searchScreenWaitSec);
				boolean isPreRatingMatchRuleExists = genHelperObj.isGridTextValuePresent( "PS_Details_pmrNameTxtId", matchRuleName, emrSearchColHeader );

				if ( isPreRatingMatchRuleExists )
				{
					PreRatingMatchRuleDetailImpl pmrDetailObj = new PreRatingMatchRuleDetailImpl( mapObj );
					int row = GridHelper.getRowNumber( "SearchGrid", matchRuleName, emrSearchColHeader );
					NavigationHelper.navigateToEdit( "SearchGrid", row );
					GenericHelper.waitForLoadmask();
					assertEquals( NavigationHelper.getScreenTitle(), "Edit Pre-rating Match Rule" );
					pmrDetailObj.editPreRatingMatchRuleBasicDetails();
					pmrDetailObj.editAddingMatchCriteriaValues();
					pmrDetailObj.editTariffTimeLineConfig();
					pmrDetailObj.detailSave();
					String matchRuleColumnHeaderName = "Name";
					Assert.assertTrue( GridHelper.isValuePresent( "SearchGrid", matchRuleName, matchRuleColumnHeaderName ), "pre rating match rule : " + matchRuleName + "is not saved" + testCaseName );
					Log4jHelper.logInfo( "pre rating match rule is edited successfully : " + matchRuleName );
				}
				else
				{
					Log4jHelper.logInfo( "pre rating match rule does not exists : " + matchRuleName );
				}
			}

		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

}
