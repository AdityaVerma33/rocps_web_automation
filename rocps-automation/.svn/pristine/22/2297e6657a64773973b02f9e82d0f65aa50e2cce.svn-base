package com.subex.rocps.automation.helpers.application.Sales;

import java.util.ArrayList;
import java.util.Map;

import com.subex.automation.helpers.application.NavigationHelper;
import com.subex.automation.helpers.component.ButtonHelper;
import com.subex.automation.helpers.component.GenericHelper;
import com.subex.automation.helpers.file.ExcelReader;
import com.subex.automation.helpers.report.Log4jHelper;
import com.subex.automation.helpers.util.FailureHelper;
import com.subex.rocps.automation.helpers.application.Sales.offerRule.OfferRuleActionImpl;
import com.subex.rocps.automation.helpers.application.Sales.offerRule.OfferRuleDetail;
import com.subex.rocps.automation.helpers.application.genericHelpers.PSActionImpl;
import com.subex.rocps.automation.helpers.application.genericHelpers.PSDataComponentHelper;
import com.subex.rocps.automation.helpers.application.genericHelpers.PSGenericHelper;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;
import com.subex.rocps.automation.utils.ExcelHolder;
import com.subex.rocps.automation.utils.PSStringUtils;

public class OfferRule extends PSAcceptanceTest
{
	protected ExcelReader excelData = new ExcelReader();
	protected Map<String, ArrayList<String>> offerRuleExcelMap = null;
	protected Map<String, String> offerRuleMap = null;
	protected ExcelHolder excelHolderObj = null;
	protected String path;
	protected String workBookName;
	protected String sheetName;
	protected String testCaseName;
	protected int occurence;
	protected String colmHdrs;
	protected int colSize;
	protected int index;
	protected String offerRuleName;
	protected String clientPartition;
	PSGenericHelper psGenericHelper = new PSGenericHelper();
	PSStringUtils psStringUtils = new PSStringUtils();
	PSActionImpl psActionImpl = new PSActionImpl();
	PSDataComponentHelper psDataComponentHelper = new PSDataComponentHelper();
	OfferRuleActionImpl offerRuleActionImpl = new OfferRuleActionImpl();

	/**
	 * Constructor : Initializing the excel without occurrence
	 * 
	 * @param path
	 * @param workBookName
	 * @param sheetName
	 * @param testCaseName
	 * @throws Exception
	 */
	public OfferRule( String path, String workBookName, String sheetName, String testCaseName ) throws Exception
	{

		this.path = path;
		this.workBookName = workBookName;
		this.sheetName = sheetName;
		this.testCaseName = testCaseName;
		offerRuleExcelMap = excelData.readDataByColumn( path, workBookName, sheetName, testCaseName );
		excelHolderObj = new ExcelHolder( offerRuleExcelMap );
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
	public OfferRule( String path, String workBookName, String sheetName, String testCaseName, int occurence ) throws Exception
	{
		this.path = path;
		this.workBookName = workBookName;
		this.sheetName = sheetName;
		this.testCaseName = testCaseName;
		this.occurence = occurence;
		offerRuleExcelMap = excelData.readDataByColumn( path, workBookName, sheetName, testCaseName, occurence );
		excelHolderObj = new ExcelHolder( offerRuleExcelMap );
		colSize = excelHolderObj.totalColumns();
	}

	/*
	 * This method is for initialize variable
	 */
	private void initializeVariable( Map<String, String> map ) throws Exception
	{
		clientPartition = ExcelHolder.getKey( map, "Partition" );
		offerRuleName = ExcelHolder.getKey( map, "Name" );

	}

	/*
	 * This method is for 'Offer Rule' screen common method
	 */
	private void offerRuleScreen() throws Exception
	{
		NavigationHelper.navigateToScreen( "Offer Rule" );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		offerRuleMap = excelHolderObj.dataMap( index );
		ButtonHelper.click( "ClearButton" );
		psGenericHelper.waitforHeaderElement( "Name" );
	}

	/*
	 * This method is for 'Offer Rule' screen column validation
	 */
	public void offerRuleColumsValidation() throws Exception
	{
		try
		{
			for ( index = 0; index < colSize; index++ )
			{
				offerRuleScreen();
				colmHdrs = ExcelHolder.getKey( offerRuleMap, "SearchScreenColumns" );
				psGenericHelper.screenColumnValidation( "Name", colmHdrs, "Offer Rule" );
			}
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}

	}

	/*
	 * This method is for 'Offer Rule' creation
	 */
	public void offerRuleCreation() throws Exception
	{
		try
		{
			for ( index = 0; index < colSize; index++ )
			{
				offerRuleScreen();
				initializeVariable( offerRuleMap );
				boolean isofferRulePresent = psGenericHelper.isGridTextValuePresent( "PS_Detail_offerRule_name_txtId", offerRuleName, "Name" );
				if ( !isofferRulePresent )
				{
					offerRuleActionImpl.clickNewAction( clientPartition );
					OfferRuleDetail offerRuleDetailImpl = new OfferRuleDetail( offerRuleMap );
					offerRuleDetailImpl.configureOfferRule();
					Log4jHelper.logInfo( "Offer Rule is successfuly saved with " + offerRuleName );
				}
				else
					Log4jHelper.logInfo( "Offer Rule is already available with " + offerRuleName );
			}
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}

	}

	// Method: for Offer Rule deletion action
	public void offerRuleDelete() throws Exception
	{
		for ( index = 0; index < colSize; index++ )
		{
			offerRuleScreen();
			initializeVariable( offerRuleMap );
			ButtonHelper.click( "ClearButton" );
			GenericHelper.waitForLoadmask( searchScreenWaitSec );
			boolean isofferRuleNamePresent = psGenericHelper.isGridTextValuePresent( "PS_Detail_offerRule_name_txtId", offerRuleName, "Name" );
			assertTrue( isofferRuleNamePresent, "Offer Rule is not available in the screen with  name: -'" + offerRuleName );
			psGenericHelper.clickDeleteOrUnDeleteAction( offerRuleName, "Name", "Delete" );
			GenericHelper.waitForLoadmask( searchScreenWaitSec );
			psGenericHelper.selectPartitionFilter( clientPartition, "Deleted Items" );
			isofferRuleNamePresent = psGenericHelper.isGridTextValuePresent( "PS_Detail_offerRule_name_txtId", offerRuleName, "Name" );
			assertTrue( isofferRuleNamePresent, offerRuleName + " is not present" );
			Log4jHelper.logInfo( "Offer Rule is deleted successfully with the value-:'" + offerRuleName );
		}

	}

	// Method: for Offer Rule Undeletion action
	public void offerRuleUnDelete() throws Exception
	{
		for ( index = 0; index < colSize; index++ )
		{
			offerRuleScreen();
			initializeVariable( offerRuleMap );
			ButtonHelper.click( "ClearButton" );
			GenericHelper.waitForLoadmask( searchScreenWaitSec );
			psGenericHelper.selectPartitionFilter( clientPartition, "Deleted Items" );
			boolean isofferRuleNamePresent = psGenericHelper.isGridTextValuePresent( "PS_Detail_offerRule_name_txtId", offerRuleName, "Name" );
			assertTrue( isofferRuleNamePresent, "Offer Rule is not available in the screen with  name: -'" + offerRuleName );
			psGenericHelper.clickDeleteOrUnDeleteAction( offerRuleName, "Name", "Undelete" );
			GenericHelper.waitForLoadmask( searchScreenWaitSec );
			psGenericHelper.selectPartitionFilter( clientPartition, "Non Deleted Items" );
			isofferRuleNamePresent = psGenericHelper.isGridTextValuePresent( "PS_Detail_offerRule_name_txtId", offerRuleName, "Name" );
			assertTrue( isofferRuleNamePresent, offerRuleName + " is not present" );
			Log4jHelper.logInfo( "Offer Rule is undeleted successfully with the  value:  '" + offerRuleName );
		}

	}
}
