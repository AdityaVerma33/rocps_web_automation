package com.subex.rocps.automation.helpers.application.Sales;

import java.util.ArrayList;
import java.util.Map;

import com.subex.automation.helpers.application.NavigationHelper;
import com.subex.automation.helpers.component.ButtonHelper;
import com.subex.automation.helpers.component.GenericHelper;
import com.subex.automation.helpers.file.ExcelReader;
import com.subex.automation.helpers.report.Log4jHelper;
import com.subex.automation.helpers.util.FailureHelper;
import com.subex.rocps.automation.helpers.application.Sales.proposalRule.ProposalRuleActionImpl;
import com.subex.rocps.automation.helpers.application.Sales.proposalRule.ProposalRuleDetail;
import com.subex.rocps.automation.helpers.application.genericHelpers.PSActionImpl;
import com.subex.rocps.automation.helpers.application.genericHelpers.PSDataComponentHelper;
import com.subex.rocps.automation.helpers.application.genericHelpers.PSGenericHelper;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;
import com.subex.rocps.automation.utils.ExcelHolder;
import com.subex.rocps.automation.utils.PSStringUtils;

public class ProposalRule extends PSAcceptanceTest
{
	protected ExcelReader excelData = new ExcelReader();
	protected Map<String, ArrayList<String>> proposalRuleExcelMap = null;
	protected Map<String, String> proposalRuleMap = null;
	protected ExcelHolder excelHolderObj = null;
	protected String path;
	protected String workBookName;
	protected String sheetName;
	protected String testCaseName;
	protected int occurence;
	protected String colmHdrs;
	protected int colSize;
	protected int index;
	protected String proposalRuleName;
	protected String clientPartition;
	PSGenericHelper psGenericHelper = new PSGenericHelper();
	PSStringUtils psStringUtils = new PSStringUtils();
	PSActionImpl psActionImpl = new PSActionImpl();
	PSDataComponentHelper psDataComponentHelper = new PSDataComponentHelper();
	ProposalRuleActionImpl proposalRuleActionImpl = new ProposalRuleActionImpl();

	/**
	 * Constructor : Initializing the excel without occurrence
	 * 
	 * @param path
	 * @param workBookName
	 * @param sheetName
	 * @param testCaseName
	 * @throws Exception
	 */
	public ProposalRule( String path, String workBookName, String sheetName, String testCaseName ) throws Exception
	{

		this.path = path;
		this.workBookName = workBookName;
		this.sheetName = sheetName;
		this.testCaseName = testCaseName;
		proposalRuleExcelMap = excelData.readDataByColumn( path, workBookName, sheetName, testCaseName );
		excelHolderObj = new ExcelHolder( proposalRuleExcelMap );
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
	public ProposalRule( String path, String workBookName, String sheetName, String testCaseName, int occurence ) throws Exception
	{
		this.path = path;
		this.workBookName = workBookName;
		this.sheetName = sheetName;
		this.testCaseName = testCaseName;
		this.occurence = occurence;
		proposalRuleExcelMap = excelData.readDataByColumn( path, workBookName, sheetName, testCaseName, occurence );
		excelHolderObj = new ExcelHolder( proposalRuleExcelMap );
		colSize = excelHolderObj.totalColumns();
	}

	/*
	 * This method is for initialize variable
	 */
	private void initializeVariable( Map<String, String> map ) throws Exception
	{
		clientPartition = ExcelHolder.getKey( map, "Partition" );
		proposalRuleName = ExcelHolder.getKey( map, "Name" );

	}

	/*
	 * This method is for 'Proposal Rule' screen common method
	 */
	private void proposalRuleScreen() throws Exception
	{
		NavigationHelper.navigateToScreen( "Proposal Rule" );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		proposalRuleMap = excelHolderObj.dataMap( index );
		ButtonHelper.click( "ClearButton" );
		psGenericHelper.waitforHeaderElement( "Proposal Rule Name" );
	}

	/*
	 * This method is for 'Proposal Rule' screen column validation
	 */
	public void proposalRuleColumsValidation() throws Exception
	{
		try
		{
			for ( index = 0; index < colSize; index++ )
			{
				proposalRuleScreen();
				colmHdrs = ExcelHolder.getKey( proposalRuleMap, "SearchScreenColumns" );
				psGenericHelper.screenColumnValidation( "Proposal Rule Name", colmHdrs, "Proposal Rule" );
			}
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}

	}

	/*
	 * This method is for 'Proposal Rule' creation
	 */
	public void proposalRuleCreation() throws Exception
	{
		try
		{
			for ( index = 0; index < colSize; index++ )
			{
				proposalRuleScreen();
				initializeVariable( proposalRuleMap );
				boolean isproposalRulePresent = psGenericHelper.isGridTextValuePresent( "PS_Detail_proposalRule_name_txtId", proposalRuleName, "Name" );
				if ( !isproposalRulePresent )
				{
					proposalRuleActionImpl.clickNewAction( clientPartition );
					ProposalRuleDetail proposalRuleDetailImpl = new ProposalRuleDetail( proposalRuleMap );
					proposalRuleDetailImpl.configureProposalRule();
					Log4jHelper.logInfo( "Proposal Rule is successfuly saved with " + proposalRuleName );
				}
				else
					Log4jHelper.logInfo( "Proposal Rule is already available with " + proposalRuleName );
			}
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}

	}

	// Method: for Proposal Rule deletion action
	public void proposalRuleDelete() throws Exception
	{
		for ( index = 0; index < colSize; index++ )
		{
			proposalRuleScreen();
			initializeVariable( proposalRuleMap );
			ButtonHelper.click( "ClearButton" );
			GenericHelper.waitForLoadmask( searchScreenWaitSec );
			boolean isproposalRuleNamePresent = psGenericHelper.isGridTextValuePresent( "PS_Detail_proposalRule_name_txtId", proposalRuleName, "Name" );
			assertTrue( isproposalRuleNamePresent, "Proposal Rule is not available in the screen with  name: -'" + proposalRuleName );
			psGenericHelper.clickDeleteOrUnDeleteAction( proposalRuleName, "Name", "Delete" );
			GenericHelper.waitForLoadmask( searchScreenWaitSec );
			psGenericHelper.selectPartitionFilter( clientPartition, "Deleted Items" );
			isproposalRuleNamePresent = psGenericHelper.isGridTextValuePresent( "PS_Detail_proposalRule_name_txtId", proposalRuleName, "Name" );
			assertTrue( isproposalRuleNamePresent, proposalRuleName + " is not present" );
			Log4jHelper.logInfo( "Proposal Rule is deleted successfully with the value-:'" + proposalRuleName );
		}

	}

	// Method: for Proposal Rule Undeletion action
	public void proposalRuleUnDelete() throws Exception
	{
		for ( index = 0; index < colSize; index++ )
		{
			proposalRuleScreen();
			initializeVariable( proposalRuleMap );
			ButtonHelper.click( "ClearButton" );
			GenericHelper.waitForLoadmask( searchScreenWaitSec );
			psGenericHelper.selectPartitionFilter( clientPartition, "Deleted Items" );
			boolean isproposalRuleNamePresent = psGenericHelper.isGridTextValuePresent( "PS_Detail_proposalRule_name_txtId", proposalRuleName, "Name" );
			assertTrue( isproposalRuleNamePresent, "Proposal Rule is not available in the screen with  name: -'" + proposalRuleName );
			psGenericHelper.clickDeleteOrUnDeleteAction( proposalRuleName, "Name", "Undelete" );
			GenericHelper.waitForLoadmask( searchScreenWaitSec );
			psGenericHelper.selectPartitionFilter( clientPartition, "Non Deleted Items" );
			isproposalRuleNamePresent = psGenericHelper.isGridTextValuePresent( "PS_Detail_proposalRule_name_txtId", proposalRuleName, "Name" );
			assertTrue( isproposalRuleNamePresent, proposalRuleName + " is not present" );
			Log4jHelper.logInfo( "Proposal Rule is undeleted successfully with the  value:  '" + proposalRuleName );
		}

	}
}
