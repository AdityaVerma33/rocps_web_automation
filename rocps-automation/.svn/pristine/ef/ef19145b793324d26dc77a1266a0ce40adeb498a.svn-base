package com.subex.rocps.automation.helpers.application.matchandrate;

import java.util.ArrayList;
import java.util.Map;

import com.subex.automation.helpers.application.NavigationHelper;
import com.subex.automation.helpers.component.ButtonHelper;
import com.subex.automation.helpers.component.GenericHelper;
import com.subex.automation.helpers.component.GridHelper;
import com.subex.automation.helpers.file.ExcelReader;
import com.subex.automation.helpers.report.Log4jHelper;
import com.subex.automation.helpers.util.FailureHelper;
import com.subex.rocps.automation.helpers.application.genericHelpers.PSActionImpl;
import com.subex.rocps.automation.helpers.application.genericHelpers.PSDataComponentHelper;
import com.subex.rocps.automation.helpers.application.genericHelpers.PSGenericHelper;
import com.subex.rocps.automation.helpers.application.matchandrate.surchargeRule.SurchargeRuleActionImpl;
import com.subex.rocps.automation.helpers.application.matchandrate.surchargeRule.SurchargeRuleDetailImpl;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;
import com.subex.rocps.automation.utils.ExcelHolder;
import com.subex.rocps.automation.utils.PSStringUtils;

public class SurchargeRule extends PSAcceptanceTest
{
	protected ExcelReader excelData = new ExcelReader();
	protected Map<String, ArrayList<String>> surchargeRuleExcelMap = null;
	protected Map<String, String> surchargeRuleMap = null;
	protected ExcelHolder excelHolderObj = null;
	protected String path;
	protected String workBookName;
	protected String sheetName;
	protected String testCaseName;
	protected int occurence;
	protected String colmHdrs;
	protected int colSize;
	protected int index;
	protected String surchargeRuleName;
	protected String clientPartition;
	PSGenericHelper psGenericHelper = new PSGenericHelper();
	PSStringUtils psStringUtils = new PSStringUtils();
	PSActionImpl psActionImpl = new PSActionImpl();
	PSDataComponentHelper psDataComponentHelper = new PSDataComponentHelper();
	SurchargeRuleActionImpl surchargeRuleActionImpl = new SurchargeRuleActionImpl();

	/**
	 * Constructor : Initializing the excel without occurrence
	 * 
	 * @param path
	 * @param workBookName
	 * @param sheetName
	 * @param testCaseName
	 * @throws Exception
	 */
	public SurchargeRule( String path, String workBookName, String sheetName, String testCaseName ) throws Exception
	{

		this.path = path;
		this.workBookName = workBookName;
		this.sheetName = sheetName;
		this.testCaseName = testCaseName;
		surchargeRuleExcelMap = excelData.readDataByColumn( path, workBookName, sheetName, testCaseName );
		excelHolderObj = new ExcelHolder( surchargeRuleExcelMap );
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
	public SurchargeRule( String path, String workBookName, String sheetName, String testCaseName, int occurence ) throws Exception
	{
		this.path = path;
		this.workBookName = workBookName;
		this.sheetName = sheetName;
		this.testCaseName = testCaseName;
		this.occurence = occurence;
		surchargeRuleExcelMap = excelData.readDataByColumn( path, workBookName, sheetName, testCaseName, occurence );
		excelHolderObj = new ExcelHolder( surchargeRuleExcelMap );
		colSize = excelHolderObj.totalColumns();
	}

	/*
	 * This method is for initialize variable
	 */
	private void initializeVariable( Map<String, String> map ) throws Exception
	{
		clientPartition = ExcelHolder.getKey( map, "Partition" );
		surchargeRuleName = ExcelHolder.getKey( map, "Name" );

	}

	/*
	 * This method is for 'Surcharge Rule' screen common method
	 */
	private void surchargeRuleScreen() throws Exception
	{
		NavigationHelper.navigateToScreen( "Surcharge Rule" );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		surchargeRuleMap = excelHolderObj.dataMap( index );
		ButtonHelper.click( "ClearButton" );
		psGenericHelper.waitforHeaderElement( "Name" );
	}

	/*
	 * This method is for 'Surcharge Rule' screen column validation
	 */
	public void surchargeRuleColumsValidation() throws Exception
	{
		try
		{
			for ( index = 0; index < colSize; index++ )
			{
				surchargeRuleScreen();
				colmHdrs = ExcelHolder.getKey( surchargeRuleMap, "SearchScreenColumns" );
				psGenericHelper.screenColumnValidation( "Name", colmHdrs, "Surcharge Rule" );
			}
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}

	}

	/*
	 * This method is for 'Surcharge Rule' creation
	 */
	public void surchargeRuleCreation() throws Exception
	{
		try
		{
			for ( index = 0; index < colSize; index++ )
			{
				surchargeRuleScreen();
				initializeVariable( surchargeRuleMap );
				boolean isSurchargeRulePresent = psGenericHelper.isGridTextValuePresent( "PS_Detail_surchargeRule_name_txtId", surchargeRuleName, "Name" );
				if ( !isSurchargeRulePresent )
				{
					surchargeRuleActionImpl.clickNewAction( clientPartition );
					SurchargeRuleDetailImpl surchargeRuleDetailImpl = new SurchargeRuleDetailImpl( surchargeRuleMap );
					surchargeRuleDetailImpl.configureSurchargeRule();
					Log4jHelper.logInfo( "Surcharge Rule is successfuly saved with " + surchargeRuleName );
				}
				else
					Log4jHelper.logInfo( "Surcharge Rule is already available with " + surchargeRuleName );
			}
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}

	}

	// Method: for Surcharge Rule deletion action
	public void surchargeRuleDelete() throws Exception
	{
		for ( index = 0; index < colSize; index++ )
		{
			surchargeRuleScreen();
			initializeVariable( surchargeRuleMap );
			ButtonHelper.click( "ClearButton" );
			GenericHelper.waitForLoadmask( searchScreenWaitSec );
			boolean isSurchargeRuleNamePresent = psGenericHelper.isGridTextValuePresent( "PS_Detail_surchargeRule_name_txtId", surchargeRuleName, "Name" );
			assertTrue( isSurchargeRuleNamePresent, "Surcharge Rule is not available in the screen with  name: -'" + surchargeRuleName );
			psGenericHelper.clickDeleteOrUnDeleteAction( surchargeRuleName, "Name", "Delete" );
			GenericHelper.waitForLoadmask( searchScreenWaitSec );
			psGenericHelper.selectPartitionFilter( clientPartition, "Deleted Items" );
			isSurchargeRuleNamePresent = psGenericHelper.isGridTextValuePresent( "PS_Detail_surchargeRule_name_txtId", surchargeRuleName, "Name" );
			assertTrue( isSurchargeRuleNamePresent, surchargeRuleName + " is not present" );
			Log4jHelper.logInfo( "Surcharge Rule is deleted successfully with the value-:'" + surchargeRuleName );
		}

	}

	// Method: for Surcharge Rule Undeletion action
	public void surchargeRuleUnDelete() throws Exception
	{
		for ( index = 0; index < colSize; index++ )
		{
			surchargeRuleScreen();
			initializeVariable( surchargeRuleMap );
			ButtonHelper.click( "ClearButton" );
			GenericHelper.waitForLoadmask( searchScreenWaitSec );
			psGenericHelper.selectPartitionFilter( clientPartition, "Deleted Items" );
			boolean isSurchargeRuleNamePresent = psGenericHelper.isGridTextValuePresent( "PS_Detail_surchargeRule_name_txtId", surchargeRuleName, "Name" );
			assertTrue( isSurchargeRuleNamePresent, "Surcharge Rule is not available in the screen with  name: -'" + surchargeRuleName );
			psGenericHelper.clickDeleteOrUnDeleteAction( surchargeRuleName, "Name", "Undelete" );
			GenericHelper.waitForLoadmask( searchScreenWaitSec );
			psGenericHelper.selectPartitionFilter( clientPartition, "Non Deleted Items" );
			isSurchargeRuleNamePresent = psGenericHelper.isGridTextValuePresent( "PS_Detail_surchargeRule_name_txtId", surchargeRuleName, "Name" );
			assertTrue( isSurchargeRuleNamePresent, surchargeRuleName + " is not present" );
			Log4jHelper.logInfo( "Surcharge Rule is undeleted successfully with the  value:  '" + surchargeRuleName );
		}

	}

}
