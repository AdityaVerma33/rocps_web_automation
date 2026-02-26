package com.subex.rocps.automation.helpers.application.Sales;

import java.util.ArrayList;
import java.util.Map;

import com.subex.automation.helpers.application.NavigationHelper;
import com.subex.automation.helpers.component.ButtonHelper;
import com.subex.automation.helpers.component.GenericHelper;
import com.subex.automation.helpers.file.ExcelReader;
import com.subex.automation.helpers.report.Log4jHelper;
import com.subex.automation.helpers.util.FailureHelper;
import com.subex.rocps.automation.helpers.application.Sales.markupRule.MarkupRuleActionImpl;
import com.subex.rocps.automation.helpers.application.Sales.markupRule.MarkupRuleDetail;
import com.subex.rocps.automation.helpers.application.genericHelpers.PSActionImpl;
import com.subex.rocps.automation.helpers.application.genericHelpers.PSDataComponentHelper;
import com.subex.rocps.automation.helpers.application.genericHelpers.PSGenericHelper;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;
import com.subex.rocps.automation.utils.ExcelHolder;
import com.subex.rocps.automation.utils.PSStringUtils;

public class MarkupRule extends PSAcceptanceTest
{
	protected ExcelReader excelData = new ExcelReader();
	protected Map<String, ArrayList<String>> markupRuleExcelMap = null;
	protected Map<String, String> markupRuleMap = null;
	protected ExcelHolder excelHolderObj = null;
	protected String path;
	protected String workBookName;
	protected String sheetName;
	protected String testCaseName;
	protected int occurence;
	protected String colmHdrs;
	protected int colSize;
	protected int index;
	protected String markupRuleName;
	protected String clientPartition;
	PSGenericHelper psGenericHelper = new PSGenericHelper();
	PSStringUtils psStringUtils = new PSStringUtils();
	PSActionImpl psActionImpl = new PSActionImpl();
	PSDataComponentHelper psDataComponentHelper = new PSDataComponentHelper();
	MarkupRuleActionImpl markupRuleActionImpl = new MarkupRuleActionImpl();

	/**
	 * Constructor : Initializing the excel without occurrence
	 * 
	 * @param path
	 * @param workBookName
	 * @param sheetName
	 * @param testCaseName
	 * @throws Exception
	 */
	public MarkupRule( String path, String workBookName, String sheetName, String testCaseName ) throws Exception
	{

		this.path = path;
		this.workBookName = workBookName;
		this.sheetName = sheetName;
		this.testCaseName = testCaseName;
		markupRuleExcelMap = excelData.readDataByColumn( path, workBookName, sheetName, testCaseName );
		excelHolderObj = new ExcelHolder( markupRuleExcelMap );
		colSize = excelHolderObj.totalColumns();
	}

	/**
	 * Constructor : Initializing the excel with occurrence
	 * 
	 * @param path
	 * @param workBookName
	 * @param sheetName
	 * @param testCaseName
	 * @param occurence
	 * @throws Exception
	 */
	public MarkupRule( String path, String workBookName, String sheetName, String testCaseName, int occurence ) throws Exception
	{
		this.path = path;
		this.workBookName = workBookName;
		this.sheetName = sheetName;
		this.testCaseName = testCaseName;
		this.occurence = occurence;
		markupRuleExcelMap = excelData.readDataByColumn( path, workBookName, sheetName, testCaseName, occurence );
		excelHolderObj = new ExcelHolder( markupRuleExcelMap );
		colSize = excelHolderObj.totalColumns();
	}

	/*
	 * This method is for initialize variable
	 */
	private void initializeVariable( Map<String, String> map ) throws Exception
	{
		clientPartition = ExcelHolder.getKey( map, "Partition" );
		markupRuleName = ExcelHolder.getKey( map, "Name" );

	}

	/*
	 * This method is for 'Markup Rule' screen common method
	 */
	private void markupRuleScreen() throws Exception
	{
		NavigationHelper.navigateToScreen( "Markup Rule" );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		markupRuleMap = excelHolderObj.dataMap( index );
		ButtonHelper.click( "ClearButton" );
		psGenericHelper.waitforHeaderElement( "Name" );
	}

	/*
	 * This method is for 'Markup Rule' screen column validation
	 */
	public void markupRuleColumsValidation() throws Exception
	{
		try
		{
			for ( index = 0; index < colSize; index++ )
			{
				markupRuleScreen();
				colmHdrs = ExcelHolder.getKey( markupRuleMap, "SearchScreenColumns" );
				psGenericHelper.screenColumnValidation( "Name", colmHdrs, "Markup Rule" );
			}
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}

	}

	/*
	 * This method is for 'Markup Rule' creation
	 */
	public void markupRuleCreation() throws Exception
	{
		try
		{
			for ( index = 0; index < colSize; index++ )
			{
				markupRuleScreen();
				initializeVariable( markupRuleMap );
				boolean ismarkupRulePresent = psGenericHelper.isGridTextValuePresent( "PS_Detail_markupRule_name_txtId", markupRuleName, "Name" );
				if ( !ismarkupRulePresent )
				{
					markupRuleActionImpl.clickNewAction( clientPartition );
					MarkupRuleDetail markupRuleDetailImpl = new MarkupRuleDetail( markupRuleMap );
					markupRuleDetailImpl.configureMarkupRule();
					Log4jHelper.logInfo( "Markup Rule is successfuly saved with " + markupRuleName );
				}
				else
					Log4jHelper.logInfo( "Markup Rule is already available with " + markupRuleName );
			}
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}

	}

	// Method: for Markup Rule deletion action
	public void markupRuleDelete() throws Exception
	{
		for ( index = 0; index < colSize; index++ )
		{
			markupRuleScreen();
			initializeVariable( markupRuleMap );
			ButtonHelper.click( "ClearButton" );
			GenericHelper.waitForLoadmask( searchScreenWaitSec );
			boolean ismarkupRuleNamePresent = psGenericHelper.isGridTextValuePresent( "PS_Detail_markupRule_name_txtId", markupRuleName, "Name" );
			assertTrue( ismarkupRuleNamePresent, "Markup Rule is not available in the screen with  name: -'" + markupRuleName );
			psGenericHelper.clickDeleteOrUnDeleteAction( markupRuleName, "Name", "Delete" );
			GenericHelper.waitForLoadmask( searchScreenWaitSec );
			psGenericHelper.selectPartitionFilter( clientPartition, "Deleted Items" );
			ismarkupRuleNamePresent = psGenericHelper.isGridTextValuePresent( "PS_Detail_markupRule_name_txtId", markupRuleName, "Name" );
			assertTrue( ismarkupRuleNamePresent, markupRuleName + " is not present" );
			Log4jHelper.logInfo( "Markup Rule is deleted successfully with the value-:'" + markupRuleName );
		}

	}

	// Method: for Markup Rule Undeletion action
	public void markupRuleUnDelete() throws Exception
	{
		for ( index = 0; index < colSize; index++ )
		{
			markupRuleScreen();
			initializeVariable( markupRuleMap );
			ButtonHelper.click( "ClearButton" );
			GenericHelper.waitForLoadmask( searchScreenWaitSec );
			psGenericHelper.selectPartitionFilter( clientPartition, "Deleted Items" );
			boolean ismarkupRuleNamePresent = psGenericHelper.isGridTextValuePresent( "PS_Detail_markupRule_name_txtId", markupRuleName, "Name" );
			assertTrue( ismarkupRuleNamePresent, "Markup Rule is not available in the screen with  name: -'" + markupRuleName );
			psGenericHelper.clickDeleteOrUnDeleteAction( markupRuleName, "Name", "Undelete" );
			GenericHelper.waitForLoadmask( searchScreenWaitSec );
			psGenericHelper.selectPartitionFilter( clientPartition, "Non Deleted Items" );
			ismarkupRuleNamePresent = psGenericHelper.isGridTextValuePresent( "PS_Detail_markupRule_name_txtId", markupRuleName, "Name" );
			assertTrue( ismarkupRuleNamePresent, markupRuleName + " is not present" );
			Log4jHelper.logInfo( "Markup Rule is undeleted successfully with the  value:  '" + markupRuleName );
		}

	}

}
