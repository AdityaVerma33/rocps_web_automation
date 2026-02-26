package com.subex.rocps.automation.helpers.application.matchandrate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.testng.Assert;

import com.subex.automation.helpers.application.NavigationHelper;
import com.subex.automation.helpers.component.ButtonHelper;
import com.subex.automation.helpers.component.CheckBoxHelper;
import com.subex.automation.helpers.component.ComboBoxHelper;
import com.subex.automation.helpers.component.ElementHelper;
import com.subex.automation.helpers.component.EntityComboHelper;
import com.subex.automation.helpers.component.GenericHelper;
import com.subex.automation.helpers.component.GridHelper;
import com.subex.automation.helpers.component.PopupHelper;
import com.subex.automation.helpers.component.TextBoxHelper;
import com.subex.automation.helpers.data.ValidationHelper;
import com.subex.automation.helpers.file.ExcelReader;
import com.subex.automation.helpers.report.Log4jHelper;
import com.subex.automation.helpers.util.FailureHelper;
import com.subex.rocps.automation.helpers.application.filters.EntityFilterHelper;
import com.subex.rocps.automation.helpers.application.genericHelpers.PSEntityComboHelper;
import com.subex.rocps.automation.helpers.application.genericHelpers.PSGenericHelper;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;
import com.subex.rocps.automation.utils.ExcelHolder;
import com.subex.rocps.automation.utils.PSStringUtils;

public class EventMatchRuleGroup extends PSAcceptanceTest
{

	protected ExcelReader excelReaderObj = null;
	protected ExcelHolder excelHolderObj = null;
	protected HashMap<String, ArrayList<String>> emrgExcelMap = null;
	protected Map<String, String> emrgMap = null;

	protected String path;
	protected String workBookName;
	protected String sheetName;
	protected String testCaseName;
	protected int occurence;

	protected int paramVal;
	protected int colSize;

	protected String partition;
	protected String name;
	protected String fromDate;
	protected String toDate;
	protected String eventType;
	protected String preRatingfl;
	protected String legCodes;
	protected String identifierDefn;
	protected String identifierVal;
	protected PSGenericHelper psGenHelpObj = new PSGenericHelper();
	protected String regex = new PSStringUtils().regexFirstLevelDelimeter();

	/*
	 * Constructor : Initialising the excel
	 */
	public EventMatchRuleGroup( String path, String workBookName, String sheetName, String testCaseName ) throws Exception
	{

		this.path = path;
		this.workBookName = workBookName;
		this.sheetName = sheetName;
		this.testCaseName = testCaseName;
		excelReaderObj = new ExcelReader();
		emrgExcelMap = excelReaderObj.readDataByColumn( this.path, this.workBookName, this.sheetName, this.testCaseName );
		excelHolderObj = new ExcelHolder( emrgExcelMap );
		colSize = excelHolderObj.totalColumns();
	}

	/*
	 * Constructor : Initialising the excel
	 * 
	 * @Param : occurence of test case in a sheet
	 */
	public EventMatchRuleGroup( String path, String workBookName, String sheetName, String testCaseName, int occurence ) throws Exception
	{

		this.path = path;
		this.workBookName = workBookName;
		this.sheetName = sheetName;
		this.testCaseName = testCaseName;
		this.occurence = occurence;
		excelReaderObj = new ExcelReader();
		emrgExcelMap = excelReaderObj.readDataByColumn( this.path, this.workBookName, this.sheetName, this.testCaseName, this.occurence );
		excelHolderObj = new ExcelHolder( emrgExcelMap );
		colSize = excelHolderObj.totalColumns();
	}

	/*
	 * Method : To configure new event match rule
	 */
	public void configureEventMatchRuleGroup() throws Exception
	{
		try
		{
			for ( int paramVal = 0; paramVal < colSize; paramVal++ )
			{
				NavigationHelper.navigateToScreen( "Event Match Rule Group" );
				emrgMap = excelHolderObj.dataMap( paramVal );

				intializeVariables( emrgMap );
				ButtonHelper.click( "ClearButton" );
				GenericHelper.waitForLoadmask( searchScreenWaitSec );

				if ( !isMatchRuleGroupExists() )
				{

					psGenHelpObj.clickNewAction( partition );
					ElementHelper.isElementPresent( or.getProperty( "Detail_screenTitleXpath" ) );
					GenericHelper.waitForLoadmask( detailScreenWaitSec );
					TextBoxHelper.type( "PSDetail_emrName_txtId", name );
					TextBoxHelper.type( "PSDetail_fromDate_xpath", fromDate );
					TextBoxHelper.type( "PSDetail_toDate_xpath", toDate );
					//EntityFilterHelper entFiltObj = new EntityFilterHelper();
					//entFiltObj.eventTypeEntitySearch( "PSDetail_eventType_entBtnId", eventType );
					String entityXpath = "//*[@id='eventMatchRuleGroupDetail']//div[contains(@id,'trigger')]";
					if ( !ElementHelper.isElementPresent( entityXpath ) )
						ElementHelper.waitForElement( entityXpath, 120 );
					PSEntityComboHelper.selectUsingGridFilterTextBox( "eventType", "Event Type Search", "PSDetail_popUpEventType_gridColTxtId", eventType, "Name" );
					if ( !preRatingfl.isEmpty() && Boolean.valueOf( preRatingfl ) )
						CheckBoxHelper.check( "PSDetail_preRating_checkBoxId" );

					if ( preRatingfl.isEmpty() || !Boolean.valueOf( preRatingfl ) )
						psGenHelpObj.dualListSelection( legCodes );

					addingCriteriaDfnAndValues();
					psGenHelpObj.detailSave( "PSDetail_emrgDetail_saveBtnId", name, "Name" );
					Log4jHelper.logInfo( "Event Match Rule Group saved sucessfully" );

				}
				else
				{
					psGenHelpObj.waitforHeaderElement( "Name" );
					GridHelper.isValuePresent( "SearchGrid", name, "Name" );
					Log4jHelper.logInfo( "Event Match Rule Group exists" );
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
	 * Method for initialising the instance variable
	 */
	protected void intializeVariables( Map<String, String> map ) throws Exception
	{

		partition = ExcelHolder.getKey( map, "Partition" );
		name = ExcelHolder.getKey( map, "Name" );
		fromDate = ExcelHolder.getKey( map, "FromDate" );
		toDate = ExcelHolder.getKey( map, "ToDate" );
		eventType = ExcelHolder.getKey( map, "EventType" );
		preRatingfl = ExcelHolder.getKey( map, "PreratingFlag" );
		legCodes = ExcelHolder.getKey( map, "LegCodes" );
		identifierDefn = ExcelHolder.getKey( map, "IdentifierDefn" );
		identifierVal = ExcelHolder.getKey( map, "IdentifierVal" );

	}

	/*
	 * Configuring the criteria definition and criteria value. selection of criteria
	 * value is optional
	 */
	protected void addingCriteriaDfnAndValues() throws Exception
	{

		String[] identifierDefnArr = identifierDefn.split( regex, -1 );

		String[] identifierValArr = null;
		if ( !identifierVal.isEmpty() )
			identifierValArr = identifierVal.split( regex, -1 );

		for ( int i = 0; i < identifierDefnArr.length; i++ )
		{
			ButtonHelper.click( "PSDetail_criteriaAdd_btnXpath" );
			if ( i == 0 )
			{
				GridHelper.clickRow( "PSDetail_criteriaGridId", i + 1, 1 );
				GridHelper.clickRow( "PSDetail_criteriaGridId", i + 1, 1 );
			}
			else
			{
				GridHelper.clickRow( "PSDetail_criteriaGridId", i + 1, 1 );
			}

			ComboBoxHelper.select( "PSDetail_identifierDfn_comboId", identifierDefnArr[i] );

			if ( !identifierVal.isEmpty() && !identifierValArr[i].isEmpty() )
			{
				GridHelper.clickRow( "PSDetail_criteriaGridId", i + 1, 2 );
				GridHelper.clickRow( "PSDetail_criteriaGridId", i + 1, 2 );
				ButtonHelper.click( "PSDetail_identifierVal_entId" );
				GenericHelper.waitForLoadmask();

				psGenHelpObj.criteriaValueSelectionWithValueGroups( identifierValArr[i] );
			}

		}
	}

	protected boolean isMatchRuleGroupExists() throws Exception
	{
		return psGenHelpObj.isGridTextValuePresent( "PSDetail_emrName_txtId", name, "Name" );
	}

	/*
	 * Method : Edit event match rule group
	 */
	public void editEventMatchRuleGroup() throws Exception
	{
		try
		{
			NavigationHelper.navigateToScreen( "Event Match Rule Group" );
			for ( int paramVal = 0; paramVal < colSize; paramVal++ )
			{
				emrgMap = excelHolderObj.dataMap( paramVal );
				intializeVariables( emrgMap );
				ButtonHelper.click( "ClearButton" );
				GenericHelper.waitForLoadmask( searchScreenWaitSec );

				if ( isMatchRuleGroupExists() )
				{
					int row = GridHelper.getRowNumber( "SearchGrid", name, "Name" );
					NavigationHelper.navigateToEdit( "SearchGrid", row );
					assertEquals( NavigationHelper.getScreenTitle(), "Edit Event Match Rule Group" );
					GenericHelper.waitForLoadmask( detailScreenWaitSec );
					TextBoxHelper.type( "PSDetail_emrName_txtId", name );
					TextBoxHelper.type( "PSDetail_fromDate_xpath", fromDate );
					if(!toDate.isEmpty())
					{
					ButtonHelper.click( "PSDetail_toDate_xpath" );
					if(PopupHelper.isTextPresent( "window-scroll-panel", "Form and To date have already been used in configuration of match rules. Changing them may have undesired results. Do you still want to change the dates?"))
						ButtonHelper.click( "YesButton" );
					TextBoxHelper.type( "PSDetail_toDate_xpath", toDate );
					}
					assertEquals( EntityComboHelper.getValue( "eventType" ), eventType );
					editCriteriaValues();
					psGenHelpObj.detailSave( "PSDetail_emrgDetail_saveBtnId", name, "Name" );
					Log4jHelper.logInfo( "Event Match Rule Group saved sucessfully" );

				}
				else
				{
					psGenHelpObj.waitforHeaderElement( "Name" );
					GridHelper.isValuePresent( "SearchGrid", name, "Name" );
					Log4jHelper.logInfo( "Event Match Rule Group exists" );
				}

			}
		}
		catch ( Exception e )
		{

			FailureHelper.setErrorMessage( e );
			throw e;
		}

	}

	public void editCriteriaValues() throws Exception
	{
		String[] identifierDefnArr = identifierDefn.split( regex, -1 );
		String[] identifierValArr = identifierVal.split( regex, -1 );

		for ( int i = 0; i < identifierDefnArr.length; i++ )
		{
			int row = GridHelper.getRowNumber( "PSDetail_criteriaGridId", identifierDefnArr[i], 1 );
			assertEquals( GridHelper.getCellValue( "PSDetail_criteriaGridId", row, 1 ), identifierDefnArr[i] );
		}
		if (! identifierVal.isEmpty() )
		{
			for ( int i = 0; i < identifierValArr.length; i++ )
			{
				int row = GridHelper.getRowNumber( "PSDetail_criteriaGridId", identifierValArr[i], 2 );
				assertEquals( GridHelper.getCellValue( "PSDetail_criteriaGridId", row, 2 ), identifierValArr[i] );
			}
		}
	}

	/*
	 * This method is to validate search screen columns
	 */
	public void searchScreenColumnsValidation() throws Exception
	{
		NavigationHelper.navigateToScreen( "Event Match Rule Group" );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		for ( paramVal = 0; paramVal < colSize; paramVal++ )
		{
			emrgMap = excelHolderObj.dataMap( paramVal );
			String searchScreenColumns = ExcelHolder.getKey( emrgMap, "SearchScreenColumns" );
			ArrayList<String> excelColumnNames = new ArrayList<String>();
			String[] searchGridColumnsArr = searchScreenColumns.split( regex, -1 );
			for ( int col = 0; col < searchGridColumnsArr.length; col++ )
			{
				excelColumnNames.add( searchGridColumnsArr[col] );
			}
			psGenHelpObj.totalColumns( excelColumnNames );
		}

	}

	/*
	 * This method is for event Match Rule Group view EMR
	 */
	public void eventMatchRuleGrpViewEMR() throws Exception
	{

		NavigationHelper.navigateToScreen( "Event Match Rule Group" );
		for ( int paramVal = 0; paramVal < colSize; paramVal++ )
		{
			emrgMap = excelHolderObj.dataMap( paramVal );
			name = ExcelHolder.getKey( emrgMap, "Name" );
			String eventMatchRule = ExcelHolder.getKey( emrgMap, "EventMatchRule" );

			if ( isMatchRuleGroupExists() )
			{
				GridHelper.clickRow( "SearchGrid", name, "Name" );
				GenericHelper.waitForLoadmask( searchScreenWaitSec );
				NavigationHelper.navigateToAction( "Event Match Rule", eventMatchRule );
				GenericHelper.waitForLoadmask( searchScreenWaitSec );
				assertEquals( NavigationHelper.getScreenTitle(), "Edit Event Match Rule", "edit event match rule screen is not opened" );
				ButtonHelper.click( "CancelButton" );
				GenericHelper.waitForLoadmask( searchScreenWaitSec );
			}
		}
	}

	/*
	 * This method is to perform reorder action
	 */
	public void emrgreOrderAction() throws Exception
	{
		NavigationHelper.navigateToScreen( "Event Match Rule Group" );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		for ( paramVal = 0; paramVal < colSize; paramVal++ )
		{
			emrgMap = excelHolderObj.dataMap( paramVal );
			name = ExcelHolder.getKey( emrgMap, "Name" );
			String moveUp = ExcelHolder.getKey( emrgMap, "MoveUp" );
			String moveDown = ExcelHolder.getKey( emrgMap, "MoveDown" );
			int row;

			GridHelper.clickRow( "SearchGrid", name, "Name" );
			NavigationHelper.navigateToAction( "Reorder", "Change Match Order" );
			GenericHelper.waitForLoadmask( searchScreenWaitSec );

			if ( ValidationHelper.isNotEmpty( moveUp ) )
			{
				row = GridHelper.getRowNumber( "orderGrid", name, "Event Match Rule Group" );
				int moveRow = GridHelper.getRowNumber( "orderGrid", moveUp, "Event Match Rule Group" );
				int val = row - moveRow;
				int actualRow = moveUpMoveDownConfig( moveUp, "ruleGroupGridToolbar.moveUp", val );
				assertEquals( actualRow, moveRow, "MoveUp is not performed" );

			}
			if ( ValidationHelper.isNotEmpty( moveDown ) )
			{
				row = GridHelper.getRowNumber( "orderGrid", name, "Event Match Rule Group" );
				int moveRow = GridHelper.getRowNumber( "orderGrid", moveDown, "Event Match Rule Group" );
				int val = moveRow - row;
				int actualRow = moveUpMoveDownConfig( moveDown, "ruleGroupGridToolbar.moveDown", val );
				assertEquals( actualRow, moveRow, "MoveUp is not performed" );
			}

			ButtonHelper.click( "matchOrderDetail.save" );
			GenericHelper.waitForLoadmask( searchScreenWaitSec );
		}

	}

	public int moveUpMoveDownConfig( String move, String gridID, int val ) throws Exception
	{
		for ( int i = 0; i < val; i++ )
		{
			GridHelper.clickRow( "orderGrid", name, "Event Match Rule Group" );
			ButtonHelper.click( gridID );
			GenericHelper.waitForLoadmask();

		}
		int actualRow = GridHelper.getRowNumber( "orderGrid", name, "Event Match Rule Group" );
		return actualRow;
	}

	/*
	 * This method is for event Match Rule Group deletion
	 */
	public void eventMatchRuleGrpDelete() throws Exception
	{

		NavigationHelper.navigateToScreen( "Event Match Rule Group" );
		for ( int paramVal = 0; paramVal < colSize; paramVal++ )
		{
			emrgMap = excelHolderObj.dataMap( paramVal );

			partition = ExcelHolder.getKey( emrgMap, "Partition" );
			name = ExcelHolder.getKey( emrgMap, "Name" );

			psGenHelpObj.selectPartitionFilter( partition, "Non Deleted Items" );
			if ( isMatchRuleGroupExists() )
			{
				psGenHelpObj.clickDeleteOrUnDeleteAction( name, "Name", "Delete" );
				GenericHelper.waitForLoadmask();
				psGenHelpObj.selectPartitionFilter( partition, "Deleted Items" );
				assertTrue( GridHelper.isValuePresent( "SearchGrid", name, "Name" ), name );
				Log4jHelper.logInfo( "Event Match Rule Group is deleted successfully : " + name );

			}
			else
			{
				Log4jHelper.logInfo( "Event Match Rule Group is not available : " + name );
			}

		}
	}

	/*
	 * This method is for event Match Rule Group un delete
	 */
	public void eventMatchRuleGrpUnDelete() throws Exception
	{

		NavigationHelper.navigateToScreen( "Event Match Rule Group" );
		for ( int paramVal = 0; paramVal < colSize; paramVal++ )
		{
			emrgMap = excelHolderObj.dataMap( paramVal );

			partition = ExcelHolder.getKey( emrgMap, "Partition" );
			name = ExcelHolder.getKey( emrgMap, "Name" );

			psGenHelpObj.selectPartitionFilter( partition, "Deleted Items" );
			if ( isMatchRuleGroupExists() )
			{
				psGenHelpObj.clickDeleteOrUnDeleteAction( name, "Name", "Undelete" );
				GenericHelper.waitForLoadmask();
				psGenHelpObj.selectPartitionFilter( partition, "Non Deleted Items" );
				assertTrue( GridHelper.isValuePresent( "SearchGrid", name, "Name" ), name );
				Log4jHelper.logInfo( "Event Match Rule Group is un deleted successfully : " + name );

			}
			else
			{
				Log4jHelper.logInfo( "Event Match Rule Group is not available : " + name );
			}

		}
	}
}
