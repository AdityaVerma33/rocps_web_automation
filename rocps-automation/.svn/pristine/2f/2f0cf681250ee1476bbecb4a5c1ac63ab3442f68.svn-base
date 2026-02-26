package com.subex.rocps.automation.helpers.application.eventErrors.eventError;

import java.util.ArrayList;
import java.util.Arrays;

import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import com.subex.automation.helpers.application.NavigationHelper;
import com.subex.automation.helpers.application.screens.TaskSearchHelper;
import com.subex.automation.helpers.component.ButtonHelper;
import com.subex.automation.helpers.component.ComboBoxHelper;
import com.subex.automation.helpers.component.ElementHelper;
import com.subex.automation.helpers.component.GenericHelper;
import com.subex.automation.helpers.component.GridHelper;
import com.subex.automation.helpers.component.LabelHelper;
import com.subex.automation.helpers.component.MouseHelper;
import com.subex.automation.helpers.component.PopupHelper;
import com.subex.automation.helpers.component.SearchGridHelper;
import com.subex.automation.helpers.component.TextBoxHelper;
import com.subex.automation.helpers.componentHelpers.GridElementHelper;
import com.subex.automation.helpers.componentHelpers.SearchHelper;
import com.subex.automation.helpers.data.ValidationHelper;
import com.subex.automation.helpers.report.Log4jHelper;
import com.subex.automation.helpers.util.FailureHelper;

import com.subex.rocps.automation.helpers.application.genericHelpers.DataVerificationHelper;
import com.subex.rocps.automation.helpers.application.genericHelpers.PSGenericHelper;
import com.subex.rocps.automation.helpers.application.system.PSTaskSearchHelper;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;
import com.subex.rocps.automation.utils.ExcelHolder;
import com.subex.rocps.automation.utils.PSStringUtils;

import java.awt.Desktop.Action;
import java.awt.event.KeyEvent;

public class EventErrorSearchImpl extends PSAcceptanceTest
{

	protected Map<String, String> eveErrSrchImplMap;
	protected String fromDate;
	protected String toDate;
	protected String streamStage;
	protected String status;
	protected String errorType;
	protected String assignedTo;
	protected String groupBy;
	protected String errorCode;
	protected String colmHdrs;
	protected String mapkeys;
	protected String errorCodesArr[];
	protected String columnName;
	protected String rowValues;
	protected String rowValuesArr[];
	protected String taskEvaluation;
	protected String workbookName;
	protected String sheetName;
	PSStringUtils psStringUtils = new PSStringUtils();
	PSGenericHelper psGenericHelper = new PSGenericHelper();

	// Constructor:
	public EventErrorSearchImpl( Map<String, String> eveErrSrchImplMap ) throws Exception
	{
		this.eveErrSrchImplMap = eveErrSrchImplMap;
		initializeVariable( eveErrSrchImplMap );
	}

	// Method: Initialize the variables
	public void initializeVariable( Map<String, String> map ) throws Exception
	{
		fromDate = ExcelHolder.getKey( map, "FromDate" );
		toDate = ExcelHolder.getKey( map, "ToDate" );
		streamStage = ExcelHolder.getKey( map, "StreamStage" );
		status = ExcelHolder.getKey( map, "Status" );
		errorType = ExcelHolder.getKey( map, "ErrorType" );
		assignedTo = ExcelHolder.getKey( map, "AssignedTo" );
		groupBy = ExcelHolder.getKey( map, "GroupBy" );
		errorCode = ExcelHolder.getKey( map, "ErrorCode" );
		colmHdrs = ExcelHolder.getKey( map, "ColmnHeaders" );
	}

	// Method: Initialize the variables for Column Rows 
	public void initializeVariableForColRows( Map<String, String> map ) throws Exception

	{
		mapkeys = ExcelHolder.getKey( map, "MapRowKeys" );
		columnName = ExcelHolder.getKey( map, "ColumnName" );
		rowValues = ExcelHolder.getKey( map, "RowValues" );
		if ( ValidationHelper.isNotEmpty( rowValues ) )
			rowValuesArr = psStringUtils.stringSplitFirstLevel( rowValues );
	}

	// Method: Filter criteria of Event error search
	public void eveErrFilterSearch() throws Exception
	{
		TextBoxHelper.type( "PS_Detail_EveErr_frmDt_ID", fromDate );
		TextBoxHelper.type( "PS_Detail_EveErr_toDt_ID", toDate );
		ComboBoxHelper.select( "PS_Detail_EveErr_streamStg_ComboID", streamStage );
		if ( ValidationHelper.isNotEmpty( status ) )
			ComboBoxHelper.select( "PS_Detail_EveErr_status_ComboID", status );
		if ( ValidationHelper.isNotEmpty( errorType ) )
			ComboBoxHelper.select( "PS_Detail_EveErr_errorType_ComboID", errorType );
		if ( ValidationHelper.isNotEmpty( assignedTo ) )
			ComboBoxHelper.select( "PS_Detail_EveErr_assignedTo_ComboID", assignedTo );
		if ( ValidationHelper.isNotEmpty( groupBy ) )
			ComboBoxHelper.select( "PS_Detail_EveErr_grpBy_ComboID", groupBy );
		if ( ValidationHelper.isNotEmpty( errorCode ) )
			ComboBoxHelper.select( "PS_Detail_EveErr_errorCd_ComboID", errorCode );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		ButtonHelper.click( "PS_Detail_EveErr_search_BtnID" );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		sortColumnHeaderGrid( "PS_Detail_EveErr_errorCd_IconID", "PS_Detail_EveErr_errorCdSort_menuID" );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
	}

	protected void sortColumnHeaderGrid( String iconId, String ascendingMenuId ) throws Exception
	{
		SearchHelper searchHelper = new SearchHelper();
		searchHelper.clickFilterIcon( GenericHelper.getORProperty( iconId ) );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		ElementHelper.waitForClickableElement( GenericHelper.getORProperty( ascendingMenuId ), searchScreenWaitSec );

		Actions action = new Actions( driver );
		action.moveToElement( ElementHelper.getElement( GenericHelper.getORProperty( ascendingMenuId ) ) ).click().perform();

	}

	// Method: Verify the column headers of Event Error
	public void verifyColmnHeaderOfEveErr() throws Exception
	{
		ArrayList<String> excelColumnNames = new ArrayList<String>();
		String evenErrGridColumnsArr[] = psStringUtils.stringSplitFirstLevel( colmHdrs );
		for ( int col = 0; col < evenErrGridColumnsArr.length; col++ )
		{
			excelColumnNames.add( evenErrGridColumnsArr[col] );
		}
		psGenericHelper.totalColumns( excelColumnNames );

	}

	// Method: Validate the search result of Event error
	public void validateSearchResult() throws Exception
	{

		mapkeys = ExcelHolder.getKey( eveErrSrchImplMap, "MapRowKeys" );
		DataVerificationHelper dataVerHelOb = new DataVerificationHelper();
		if ( ValidationHelper.isNotEmpty( mapkeys ) )
			dataVerHelOb.validateDataWithoutSorting( "PS_Detail_EveErr_GridID", GenericHelper.getORProperty( "PS_Detail_EveErr_ColumnHeaderID" ), eveErrSrchImplMap, colmHdrs, mapkeys, false );
		else
		{
			GenericHelper.waitForLoadmask( searchScreenWaitSec );
			int row = GridHelper.getRowCount( "searchGrid" );
			assertEquals( row, 0 );
		}
	}

	// Method: Select rows in grid of Event error
	protected void selectRowInSearchGrid() throws Exception
	{
		List<String> columnsList = GridHelper.getColumnValues( "PS_Detail_EveErr_GridID", columnName );
		assertTrue( columnsList.size() >= rowValuesArr.length, "Row values size exceeded with the available values of the column List" );
		boolean isValuePrese = columnsList.containsAll( Arrays.asList( rowValuesArr ) );
		assertTrue( isValuePrese, "Given record not found in Event error screen" );
		if ( rowValuesArr.length == 1 )
			GridHelper.clickRow( "PS_Detail_EveErr_GridID", rowValues, columnName );
		else
			selectMultiRows( columnsList, rowValuesArr );
		GenericHelper.waitTime( 3, "" );

	}

	// Method: Change the status of Event error
	public void changeStatusAction( String changeStatus ) throws Exception
	{
		initializeVariableForColRows( eveErrSrchImplMap );
		selectRowInSearchGrid();
		EventErrorActionImpl evErrAcOb = new EventErrorActionImpl();
		evErrAcOb.changeStatus( "Error Status Search", changeStatus );

	}

	// Method: Assign to user
	public void assignToAction( String assignTo ) throws Exception
	{
		initializeVariableForColRows( eveErrSrchImplMap );
		selectRowInSearchGrid();
		EventErrorActionImpl evErrAcOb = new EventErrorActionImpl();
		evErrAcOb.assignToAction( "User Search", assignTo );

	}

	// Method: Reprocess the suspense error
	public void reprocessSuspenseError() throws Exception
	{

		initializeVariableForColRows( eveErrSrchImplMap );
		taskEvaluation = ExcelHolder.getKey( eveErrSrchImplMap, "TaskEvalution" );
		selectRowInSearchGrid();
		EventErrorActionImpl evErrAcOb = new EventErrorActionImpl();
		evErrAcOb.clickReprocessSuspErrAction( "Confirm" );
		handleConfirmationPopup();
		String actualInfo = driver.findElement( By.id( GenericHelper.getORProperty( "PS_Detail_EveErr_expecInfo_Msz" ) ) ).getText();
		assertTrue( actualInfo.contains( GenericHelper.getORProperty( "PS_Detail_EveErr_expecInfo_ReprSusErr" ) ) );
		GenericHelper.waitForLoadmask();
		ButtonHelper.click( "PS_Detail_EveErr_expecInfo_BtnId" );
		ElementHelper.waitForElementToDisappear( GenericHelper.getORProperty( "PS_Detail_xdrExtTemp_popUP_Xpath" ), searchScreenWaitSec );
		List<String> afterReproccolumnsList = GridHelper.getColumnValues( "PS_Detail_EveErr_GridID", columnName );
		boolean isAvailable = chckAfterReproErrDataIsAvaOrNot( afterReproccolumnsList, rowValuesArr );
		assertFalse( isAvailable, "Unsuccessfull! After reprocess suspense error data is still available in search screen." );

		if ( ValidationHelper.isNotEmpty( taskEvaluation ) )
			verifyTaskStatus();
		else
			FailureHelper.failTest( "Test case for MNR task status is not found" );

	}

	// Method: Reprocess the suspense error
	public void reprocessRateError() throws Exception
	{

		initializeVariableForColRows( eveErrSrchImplMap );
		taskEvaluation = ExcelHolder.getKey( eveErrSrchImplMap, "TaskEvalution" );
		selectRowInSearchGrid();
		EventErrorActionImpl evErrAcOb = new EventErrorActionImpl();
		evErrAcOb.clickReprocessRateErrAction( "Confirm" );
		handleConfirmationPopup();
		String actualInfo = driver.findElement( By.id( GenericHelper.getORProperty( "PS_Detail_EveErr_expecInfo_Msz" ) ) ).getText();
		assertTrue( actualInfo.contains( GenericHelper.getORProperty( "PS_Detail_EveErr_expecInfo_ReprRateErr" ) ), "Information text is not matched found " + actualInfo + " and expected  " + GenericHelper.getORProperty( "PS_Detail_EveErr_expecInfo_ReprRateErr" ) );
		GenericHelper.waitForLoadmask();
		ButtonHelper.click( "PS_Detail_EveErr_expecInfo_BtnId" );
		ElementHelper.waitForElementToDisappear( GenericHelper.getORProperty( "PS_Detail_xdrExtTemp_popUP_Xpath" ), searchScreenWaitSec );
		List<String> afterReproccolumnsList = GridHelper.getColumnValues( "PS_Detail_EveErr_GridID", columnName );
		boolean isAvailable = chckAfterReproErrDataIsAvaOrNot( afterReproccolumnsList, rowValuesArr );
		assertFalse( isAvailable, "Unsuccessfull! After reprocess Rate error data is still available in search screen." );

		if ( ValidationHelper.isNotEmpty( taskEvaluation ) )
			verifyTaskStatus();
		else
			FailureHelper.failTest( "Test case for MNR task status is not found" );

	}

	// Method: verify the  task status in Task search
	private void verifyTaskStatus() throws Exception
	{
		String path = System.getProperty( "user.dir" ) + "\\src\\main\\resources\\";
		workbookName = ExcelHolder.getKey( eveErrSrchImplMap, "WorkBookName" );
		sheetName = ExcelHolder.getKey( eveErrSrchImplMap, "SheetName" );
		PSTaskSearchHelper taskSearchHelper = new PSTaskSearchHelper();
		String tescase = taskEvaluation;
		taskSearchHelper.psVerifyTaskStatus( path, workbookName, sheetName, tescase, 1 );

	}

	public boolean chckAfterReproErrDataIsAvaOrNot( List<String> guiColNamesList, String[] errorCdValue ) throws Exception
	{
		GenericHelper.waitForLoadmask();
		// ButtonHelper.click("PS_Detail_EveErr_search_BtnID");
		// GenericHelper.waitForLoadmask( searchScreenWaitSec );
		boolean flag = false;
		for ( int rowIndex = 0; rowIndex < errorCdValue.length; rowIndex++ )
		{
			if ( guiColNamesList.contains( errorCdValue[rowIndex] ) )
			{
				flag = true;
				break;
			}
		}
		return flag;
	}

	// Method: Select multiple rows in event error
	public void selectMultiRows( List<String> guiColNamesList, String[] rowValue ) throws Exception
	{

		List<WebElement> listEl = new ArrayList<WebElement>();

		for ( int rowIndex = 0; rowIndex < rowValue.length; rowIndex++ )
		{
			int col = GridHelper.getColumnNumber( "PS_Detail_EveErr_GridID", columnName );
			int row = GridHelper.getRowNumber( "PS_Detail_EveErr_GridID", rowValue[rowIndex], columnName );
			WebElement element = GridElementHelper.getCellElement( GenericHelper.getORProperty( "PS_Detail_EveErr_GridID" ), row, col );
			if ( element != null )
				listEl.add( element );
			else
			{
				FailureHelper.failTest( "Element is " + element );
			}
		}
		Actions actions = new Actions( driver );
		for ( int rowIndex = 0; rowIndex < rowValue.length; rowIndex++ )
		{
			ElementHelper.scrollToView( listEl.get( rowIndex ), true );
			if ( rowIndex == rowValue.length - 1 )
				actions.keyUp( listEl.get( rowIndex ), Keys.CONTROL ).build().perform();
			else
				actions.keyDown( listEl.get( rowIndex ), Keys.CONTROL );
		}

	}

	// Method: Reprocess the suspense error in diagnostic mode
	public void reprocessSuspenseErrorInDiagMd() throws Exception
	{
		initializeVariableForColRows( eveErrSrchImplMap );
		taskEvaluation = ExcelHolder.getKey( eveErrSrchImplMap, "TaskEvalution" );
		List<String> columnsList = GridHelper.getColumnValues( "PS_Detail_EveErr_GridID", columnName );
		boolean isPresent = columnsList.contains( rowValues );
		assertTrue( isPresent, "Given record not found in Event error screen" );
		Log4jHelper.logInfo( rowValues + " is available in the columnList" );
		GridHelper.clickRow( "PS_Detail_EveErr_GridID", rowValues, columnName );
		EventErrorActionImpl evErrAcOb = new EventErrorActionImpl();
		evErrAcOb.clickReprocessSuspErrInDiagMdAction( "Confirm" );
		handleConfirmationPopup();
		String actualInfo = driver.findElement( By.id( GenericHelper.getORProperty( "PS_Detail_EveErr_expecInfo_Msz" ) ) ).getText();
		assertTrue( actualInfo.contains( GenericHelper.getORProperty( "PS_Detail_EveErr_expecInfo_ReprSusErInDiagMd" ) ) );

		// String taskId=actualInfo.replaceAll("[^0-9]", "");
		// System.out.println("Text "+actualInfo+" and taskid "+taskId);
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		ButtonHelper.click( "PS_Detail_EveErr_expecInfo_BtnId" );
		ElementHelper.waitForElementToDisappear( GenericHelper.getORProperty( "PS_Detail_xdrExtTemp_popUP_Xpath" ), searchScreenWaitSec );

		if ( ValidationHelper.isNotEmpty( taskEvaluation ) )
			verifyTaskStatus();
		else
			FailureHelper.failTest( "Test case for MNR task status is not found" );

	}

	private void handleConfirmationPopup() throws Exception
	{
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		if ( NavigationHelper.isTitlePresent( "Confirm" ) )
		{
			ElementHelper.waitForElement( "//*[@id='window-scroll-panel']", searchScreenWaitSec );
			ButtonHelper.click( "PS_Detail_EveErr_expecInfo_BtnId" );
			GenericHelper.waitForLoadmask( searchScreenWaitSec );
		}
	}

}
