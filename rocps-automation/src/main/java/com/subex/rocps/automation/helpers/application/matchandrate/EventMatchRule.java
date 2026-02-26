package com.subex.rocps.automation.helpers.application.matchandrate;

import java.util.ArrayList;
import java.util.Map;
import java.util.Properties;

import com.subex.automation.helpers.application.NavigationHelper;
import com.subex.automation.helpers.component.ButtonHelper;
import com.subex.automation.helpers.component.CheckBoxHelper;
import com.subex.automation.helpers.component.ComboBoxHelper;
import com.subex.automation.helpers.component.EntityComboHelper;
import com.subex.automation.helpers.component.GenericHelper;
import com.subex.automation.helpers.component.GridHelper;
import com.subex.automation.helpers.component.SearchGridHelper;
import com.subex.automation.helpers.component.TabHelper;
import com.subex.automation.helpers.component.TextBoxHelper;
import com.subex.automation.helpers.data.ValidationHelper;
import com.subex.automation.helpers.file.ExcelReader;
import com.subex.automation.helpers.report.Log4jHelper;
import com.subex.automation.helpers.util.FailureHelper;
import com.subex.rocps.automation.helpers.application.customexception.ScreenTitleException;
import com.subex.rocps.automation.helpers.application.customexception.StringLengthException;
import com.subex.rocps.automation.helpers.application.genericHelpers.PSEntityComboHelper;
import com.subex.rocps.automation.helpers.application.genericHelpers.PSGenericHelper;
import com.subex.rocps.automation.helpers.application.matchandrate.Emr.EMRStrategyPattern;
import com.subex.rocps.automation.helpers.application.matchandrate.Emr.SurchargeRuleServices;
import com.subex.rocps.automation.helpers.application.matchandrate.mnrInterfaces.MatchRuleService;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;
import com.subex.rocps.automation.utils.ExcelHolder;
import com.subex.rocps.automation.utils.PSStringUtils;

public class EventMatchRule extends PSAcceptanceTest
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
	protected String eventMatchRuleGroup;
	protected String externalReference;
	protected String overRideRevenueFl;
	protected String amount;
	protected String currency;
	protected String identifierDefn;
	protected String identifierVal;
	protected MatchRuleService service = null;
	protected String surchargeServiceFlg;
	protected PSGenericHelper genHelperObj = new PSGenericHelper();
	PSStringUtils strObj = new PSStringUtils();

	/*
	 * Constructor for initializing excel and identifying the parameter value colums
	 * in a test case
	 */
	public EventMatchRule( String path, String workBook, String sheetName, String testCaseName ) throws Exception
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
	public EventMatchRule( String path, String workBook, String sheetName, String testCaseName, int occurence ) throws Exception
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

	/*
	 * Setter method to pass the MatchRuleService Object
	 */
	public void setService( MatchRuleService service )
	{
		this.service = service;
	}

	/*
	 * Method : Creating the new event match rule
	 */
	public void configureEventMatchRule() throws Exception
	{

		try
		{

			for ( int paramVal = 0; paramVal < colSize; paramVal++ )
			{
				mapObj = excelHolderObj.dataMap( paramVal );
				initialiseVariables( mapObj );

				NavigationHelper.navigateToScreen( "Event Match Rule" );
				String emrSearchColHeader = "Name";
				ButtonHelper.click( "ClearButton" );
				GenericHelper.waitForLoadmask( searchScreenWaitSec );
				boolean isEventMatchRuleExists = genHelperObj.isGridTextValuePresent( "PS_EvtMatchRuleName_TxtId", matchRuleName, emrSearchColHeader );

				if ( !isEventMatchRuleExists )
				{
					genHelperObj.clickNewAction( partition );
					matchRuleDetailConfig();
					String matchRuleColumnHeaderName = "Name";
					assertTrue( GridHelper.isValuePresent( "SearchGrid", matchRuleName, matchRuleColumnHeaderName ), "Event match rule : " + matchRuleName + "is not saved" + testCaseName );
					Log4jHelper.logInfo( "Event match rule is saved successfully : " + matchRuleName );
				}
				else
				{
					Log4jHelper.logInfo( "Event match rule exists : " + matchRuleName );
				}
			}

		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}

	}

	/*
	 * Method for configuring event match rule detail
	 */
	protected void matchRuleDetailConfig() throws Exception
	{

		try
		{
			NavigationHelper.isTitlePresent( "New Event Match Rule" );
		}
		catch ( Exception e )
		{
			throw new ScreenTitleException( "Screen title not found" );
		}

		details();
		addingMatchCriteriaValues();
		addingServices();
		ButtonHelper.click( "PSDetail_emrSaveBtnId" );
		GenericHelper.waitForLoadmask();
		GenericHelper.waitForElementToDisappear( "PSDetail_emrSaveBtnId", searchScreenWaitSec );

	}

	/*
	 * Method : for selecting the service
	 */
	private void addingServices() throws Exception
	{
		surchargeServiceFlg = mapObj.get( "SurchargeServicesFlag" );
		EMRStrategyPattern emrStrategyPattern;
		if ( ValidationHelper.isNotEmpty( surchargeServiceFlg ) && ValidationHelper.isTrue( surchargeServiceFlg ) )
		{
			SurchargeRuleServices surchargeRuleServices = new SurchargeRuleServices();
			emrStrategyPattern = new EMRStrategyPattern( surchargeRuleServices, mapObj );
		}
		else
		{
			EventMatchRuleService emrSerObj = new EventMatchRuleService();
			emrStrategyPattern = new EMRStrategyPattern( emrSerObj, mapObj );
		}
		emrStrategyPattern.addServiceConfiguration();
	}

	/*
	 * Method for configuring the criteria values
	 */
	private void addingMatchCriteriaValues() throws Exception
	{
		PSStringUtils strUtilObj = new PSStringUtils();
		String[] ideDfnArr = strUtilObj.stringSplitFirstLevel( identifierDefn );
		String[] ideValArr = strUtilObj.stringSplitFirstLevel( identifierVal );

		if ( ideDfnArr.length != ideValArr.length )
		{
			throw new StringLengthException( "Identifier definitions and identifier values are not matching" );
		}

		for ( int i = 0; i < ideDfnArr.length; i++ )
		{
			genHelperObj.clickPropertyValueColumn( ideDfnArr[i] );
			ButtonHelper.click( "trigger-identifierValueEditor" );
			GenericHelper.waitForLoadmask();
			genHelperObj.criteriaValueSelectionWithValueGroups( ideValArr[i] );
		}

	}

	/*
	 * Method : for configuring the event match rule detail header
	 */
	private void details() throws Exception
	{

		TabHelper.gotoTab( "//div[text()='Detail']" );
		TextBoxHelper.type( "PS_EvtMatchRuleName_TxtId", matchRuleName );
		String fromDateLocatorXpath = GenericHelper.getORProperty( "TextBox_DatePicker" ).replace( "idOrName", or.getProperty( "PSDetail_emrFromDateId" ) );
		String toDateLocatorXpath = GenericHelper.getORProperty( "TextBox_DatePicker" ).replace( "idOrName", or.getProperty( "PSDetail_emtToDateId" ) );
		TextBoxHelper.type( fromDateLocatorXpath, fromDate );
		TextBoxHelper.type( toDateLocatorXpath, toDate );
		String mrgColName = "Name";
		genHelperObj.waitforEntityElement();
		PSEntityComboHelper.selectUsingGridFilterTextBox( "PSDetail_emrgEntityId", "Event Match Rule Group Search", "PSDetail_emrName_txtId", eventMatchRuleGroup, mrgColName );
		TextBoxHelper.type( "PSDetail_emrExternalRefernce", externalReference );
		if ( ValidationHelper.isTrue( overRideRevenueFl ) )
		{
			CheckBoxHelper.check( "PSDetail_emrOverrideRev_checkBoxId" );
			TextBoxHelper.isEnabled( "PSDetail_emrAmount_txtId" );
			TextBoxHelper.type( "PSDetail_emrAmount_txtId", amount );
			ComboBoxHelper.isEnabled( "PSDetail_emrCurrency_comboId" );
			ComboBoxHelper.select( "PSDetail_emrCurrency_comboId", currency );
		}
	}

	protected void initialiseVariables( Map<String, String> map ) throws Exception
	{
		


		partition = ExcelHolder.getKey( map, "Partition" );
		matchRuleName = ExcelHolder.getKey( map, "Name" );
		fromDate = ExcelHolder.getKey( map, "FromDate" );
		toDate = ExcelHolder.getKey( map, "ToDate" );
		eventMatchRuleGroup = ExcelHolder.getKey( map, "EventMatchRuleGroup" );
		externalReference = ExcelHolder.getKey( map, "ExternalReference" );
		overRideRevenueFl = ExcelHolder.getKey( map, "OverrideRevenueFl" );
		amount = ExcelHolder.getKey( map, "Amount" );
		currency = ExcelHolder.getKey( map, "Currency" );
		identifierDefn = ExcelHolder.getKey( map, "IdentifierDefinition" );
		identifierVal = ExcelHolder.getKey( map, "IdentifierValue" );
	}

	/*
	 * This method is to validate search screen columns
	 */
	public void searchScreenColumnsValidation() throws Exception
	{
		NavigationHelper.navigateToScreen( "Event Match Rule" );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		for ( int paramVal = 0; paramVal < colSize; paramVal++ )
		{
			mapObj = excelHolderObj.dataMap( paramVal );
			String searchScreenColumns = ExcelHolder.getKey( mapObj, "SearchScreenColumns" );
			ArrayList<String> excelColumnNames = new ArrayList<String>();
			String[] searchGridColumnsArr = strObj.stringSplitFirstLevel( searchScreenColumns );
			for ( int col = 0; col < searchGridColumnsArr.length; col++ )
			{
				excelColumnNames.add( searchGridColumnsArr[col] );
			}
			genHelperObj.totalColumns( excelColumnNames );
		}

	}

	/*
	 * This method is for event match rule Edit
	 */
	public void eventMatchRuleEdit() throws Exception
	{

		for ( int paramVal = 0; paramVal < colSize; paramVal++ )
		{
			mapObj = excelHolderObj.dataMap( paramVal );

			NavigationHelper.navigateToScreen( "Event Match Rule" );
			partition = ExcelHolder.getKey( mapObj, "Partition" );
			matchRuleName = ExcelHolder.getKey( mapObj, "Name" );

			initialiseVariables( mapObj );
			boolean isEventMatchRuleExists = genHelperObj.isGridTextValuePresent( "PS_EvtMatchRuleName_TxtId", matchRuleName, "Name" );

			if ( isEventMatchRuleExists )
			{
				GridHelper.clickRow( "SearchGrid", matchRuleName, "Name" );
				NavigationHelper.navigateToAction( "Common Tasks", "Edit" );
				assertEquals( NavigationHelper.getScreenTitle(), "Edit Event Match Rule" );

				TabHelper.gotoTab( "//div[text()='Detail']" );
				assertEquals( TextBoxHelper.getValue( "PS_EvtMatchRuleName_TxtId" ), matchRuleName );
				String fromDateLocatorXpath = GenericHelper.getORProperty( "TextBox_DatePicker" ).replace( "idOrName", or.getProperty( "PSDetail_emrFromDateId" ) );
				String toDateLocatorXpath = GenericHelper.getORProperty( "TextBox_DatePicker" ).replace( "idOrName", or.getProperty( "PSDetail_emtToDateId" ) );
				if ( ValidationHelper.isNotEmpty( fromDate ) )
					TextBoxHelper.type( fromDateLocatorXpath, fromDate );
				if ( ValidationHelper.isNotEmpty( toDate ) )
					TextBoxHelper.type( toDateLocatorXpath, toDate );
				editMatchCriteriaValues();
				EventMatchRuleService emrSerObj = new EventMatchRuleService();
				emrSerObj.editServices( mapObj );
				genHelperObj.detailSave( "PSDetail_emrSaveBtnId", matchRuleName, "Name" );
				Log4jHelper.logInfo( "Event match rule is Edited successfully : " + matchRuleName );

			}
			else
			{
				Log4jHelper.logInfo( "Event match rule is not available : " + matchRuleName );
			}

		}
	}

	private void editMatchCriteriaValues() throws Exception
	{
		PSStringUtils strUtilObj = new PSStringUtils();
		String[] ideDfnArr = strUtilObj.stringSplitFirstLevel( identifierDefn );
		String[] ideValArr = strUtilObj.stringSplitFirstLevel( identifierVal );

		if ( ideDfnArr.length != ideValArr.length )
		{
			throw new StringLengthException( "Identifier definitions and identifier values are not matching" );
		}

		for ( int i = 0; i < ideDfnArr.length; i++ )
		{
			int row = GridHelper.getRowNumber( "eventRuleMatchCriteriaGrid", ideValArr[i], "Identifier Value" );
			if ( row == 0 )
			{
				genHelperObj.clickPropertyValueColumn( ideDfnArr[i] );
				ButtonHelper.click( "trigger-identifierValueEditor" );
				GenericHelper.waitForLoadmask();
				genHelperObj.criteriaValueSelectionWithValueGroups( ideValArr[i] );
			}
			else
			{
				assertEquals( GridHelper.getCellValue( "eventRuleMatchCriteriaGrid", row, "Identifier Value" ), ideValArr[i] );
			}
		}

	}

	/*
	 * This method is for event match rule deletion
	 */
	public void eventMatchRuleDelete() throws Exception
	{

		for ( int paramVal = 0; paramVal < colSize; paramVal++ )
		{
			mapObj = excelHolderObj.dataMap( paramVal );

			NavigationHelper.navigateToScreen( "Event Match Rule" );
			partition = ExcelHolder.getKey( mapObj, "Partition" );
			matchRuleName = ExcelHolder.getKey( mapObj, "Name" );

			genHelperObj.collapsableXpath();
			genHelperObj.selectPartitionFilter( partition, "Non Deleted Items" );
			GenericHelper.waitForLoadmask();
			boolean isEventMatchRuleExists = genHelperObj.isGridTextValuePresent( "PS_EvtMatchRuleName_TxtId", matchRuleName, "Name" );

			if ( isEventMatchRuleExists )
			{

				genHelperObj.clickDeleteOrUnDeleteAction( matchRuleName, "Name", "Delete" );
				GenericHelper.waitForLoadmask();

				genHelperObj.selectPartitionFilter( partition, "Deleted Items" );
				assertTrue( GridHelper.isValuePresent( "SearchGrid", matchRuleName, "Name" ), matchRuleName );
				Log4jHelper.logInfo( "Event match rule is deleted successfully : " + matchRuleName );

			}
			else
			{
				Log4jHelper.logInfo( "Event match rule is not available : " + matchRuleName );
			}

		}
	}

	/*
	 * This method is for event match rule un delete
	 */
	public void eventMatchRuleUnDelete() throws Exception
	{

		for ( int paramVal = 0; paramVal < colSize; paramVal++ )
		{
			mapObj = excelHolderObj.dataMap( paramVal );

			NavigationHelper.navigateToScreen( "Event Match Rule" );
			partition = ExcelHolder.getKey( mapObj, "Partition" );
			matchRuleName = ExcelHolder.getKey( mapObj, "Name" );

			genHelperObj.selectPartitionFilter( partition, "Deleted Items" );
			boolean isEventMatchRuleExists = genHelperObj.isGridTextValuePresent( "PS_EvtMatchRuleName_TxtId", matchRuleName, "Name" );

			if ( isEventMatchRuleExists )
			{

				genHelperObj.clickDeleteOrUnDeleteAction( matchRuleName, "Name", "Undelete" );
				GenericHelper.waitForLoadmask();

				genHelperObj.selectPartitionFilter( partition, "Non Deleted Items" );
				assertTrue( GridHelper.isValuePresent( "SearchGrid", matchRuleName, "Name" ), matchRuleName );
				Log4jHelper.logInfo( "Event match rule is un deleted successfully : " + matchRuleName );

			}
			else
			{
				Log4jHelper.logInfo( "Event match rule is not available : " + matchRuleName );
			}

		}
	}

}
