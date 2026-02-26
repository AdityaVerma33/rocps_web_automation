package com.subex.rocps.automation.helpers.application.settlements;

import org.openqa.selenium.By;

import java.util.ArrayList;
import java.util.Map;

import com.subex.rocps.automation.helpers.application.dispute.dispute.DisputeActionImpl;
import com.subex.rocps.automation.helpers.application.genericHelpers.DataVerificationHelper;
import com.subex.rocps.automation.helpers.application.genericHelpers.PSGenericHelper;
import com.subex.rocps.automation.helpers.application.system.PSTaskSearchHelper;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;
import com.subex.rocps.automation.utils.ExcelHolder;
import com.subex.rocps.automation.utils.PSStringUtils;

import com.subex.automation.helpers.application.NavigationHelper;
import com.subex.automation.helpers.application.screens.TaskSearchHelper;
import com.subex.automation.helpers.component.ButtonHelper;
import com.subex.automation.helpers.component.ElementHelper;
import com.subex.automation.helpers.component.GenericHelper;
import com.subex.automation.helpers.component.GridHelper;
import com.subex.automation.helpers.component.LabelHelper;
import com.subex.automation.helpers.component.PopupHelper;
import com.subex.automation.helpers.data.ValidationHelper;
import com.subex.automation.helpers.file.ExcelReader;
import com.subex.automation.helpers.report.Log4jHelper;
import com.subex.automation.helpers.util.FailureHelper;

public class SettlementsHelper extends PSAcceptanceTest
{

	protected ExcelReader excelData = new ExcelReader();
	protected Map<String, ArrayList<String>> settlementsExcelMap = null;
	protected Map<String, String> settlementsMap = null;
	protected ExcelHolder excelHolderObj = null;
	protected String path;
	protected String workBookName;
	protected String sheetName;
	protected String testCaseName;
	protected int occurence;
	protected String clientPartition;
	protected String colmHdrs;
	protected String mapkeys;
	protected int colSize;
	protected int index;
	protected boolean isSettlementPresent;
	PSGenericHelper psGenericHelper = new PSGenericHelper();
	PSStringUtils psStringUtils = new PSStringUtils();
	SettlementsActionImpl settlementsActionOb = new SettlementsActionImpl();

	// Constructor : Initializing the excel without occurrence
	public SettlementsHelper( String path, String workBookName, String sheetName, String testCaseName ) throws Exception
	{

		this.path = path;
		this.workBookName = workBookName;
		this.sheetName = sheetName;
		this.testCaseName = testCaseName;
		settlementsExcelMap = excelData.readDataByColumn( path, workBookName, sheetName, testCaseName );
		excelHolderObj = new ExcelHolder( settlementsExcelMap );
		colSize = excelHolderObj.totalColumns();
	}

	// Constructor : Initializing the excel with occurrence
	public SettlementsHelper( String path, String workBookName, String sheetName, String testCaseName, int occurence ) throws Exception
	{

		this.path = path;
		this.workBookName = workBookName;
		this.sheetName = sheetName;
		this.testCaseName = testCaseName;
		this.occurence = occurence;
		settlementsExcelMap = excelData.readDataByColumn( path, workBookName, sheetName, testCaseName, occurence );
		excelHolderObj = new ExcelHolder( settlementsExcelMap );
		colSize = excelHolderObj.totalColumns();
	}

	/*
	 * This method is for Settlements screen column validation
	 */
	public void settlementsColumsValidation() throws Exception
	{
		try
		{
			for ( index = 0; index < colSize; index++ )
			{
				NavigationHelper.navigateToScreen( "Settlements" );
				GenericHelper.waitForLoadmask( searchScreenWaitSec );
				settlementsMap = excelHolderObj.dataMap( index );
				ButtonHelper.click( "ClearButton" );
				colmHdrs = ExcelHolder.getKey( settlementsMap, "SearchScreenColumns" );
				ArrayList<String> excelColumnNames = new ArrayList<String>();
				String settlementsGridColumnsArr[] = psStringUtils.stringSplitFirstLevel( colmHdrs );
				for ( int col = 0; col < settlementsGridColumnsArr.length; col++ )
				{
					excelColumnNames.add( settlementsGridColumnsArr[col] );
				}
				psGenericHelper.totalColumns( excelColumnNames );
				Log4jHelper.logInfo( "'Settlements screen' Columns are validated successfully" );

			}
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}

	}

	/*
	 * This method is for Settlements search screen results validation
	 */
	public void settlementsSearchResultValidation() throws Exception
	{
		try
		{
			for ( index = 0; index < colSize; index++ )
			{
				NavigationHelper.navigateToScreen( "Settlements" );
				GenericHelper.waitForLoadmask( searchScreenWaitSec );
				settlementsMap = excelHolderObj.dataMap( index );
				clientPartition = ExcelHolder.getKey( settlementsMap, "Partition" );
				ButtonHelper.click( "ClearButton" );
				SettlmentSearchImpl settlmentSearchImpl = new SettlmentSearchImpl( settlementsMap );
				settlmentSearchImpl.searchSettlementfilter();
				Log4jHelper.logInfo( "'Settlements screen search results '  are validated successfully" );

			}
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}

	}

	/*
	 * Common method is for Settlement
	 */
	private void settlementScreen() throws Exception
	{
		NavigationHelper.navigateToScreen( "Settlements" );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		settlementsMap = excelHolderObj.dataMap( index );
		clientPartition = ExcelHolder.getKey( settlementsMap, "Partition" );
		ButtonHelper.click( "ClearButton" );
		SettlmentSearchImpl settlmentSearchImpl = new SettlmentSearchImpl( settlementsMap );
		isSettlementPresent = settlmentSearchImpl.isSettlementPresent();
	}

	/*
	 * This method is for Settlements creation
	 */
	public void createSettlment() throws Exception
	{
		try
		{
			for ( index = 0; index < colSize; index++ )
			{
				settlementScreen();
				if ( !isSettlementPresent )
				{

					settlementsActionOb.clickNewAction( clientPartition, "Settlement" );
					SettlementsDetailsImpl settlementsDetailOb = new SettlementsDetailsImpl( settlementsMap );
					settlementsDetailOb.configSettlment();
					settlementsDetailOb.saveSettlement();
					SettlmentSearchImpl settlmentSearchImpl = new SettlmentSearchImpl( settlementsMap );
					isSettlementPresent = settlmentSearchImpl.isSettlementPresent();
					assertTrue( isSettlementPresent, "Settlement is not found in search screen " );
					Log4jHelper.logInfo( "'Settlements' is successfully created" );
				}
				else
				{
					Log4jHelper.logInfo( "'Settlements is already avilable" );
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
	 * This method is for Settlements Edit
	 */
	public void editSettlement() throws Exception
	{
		try
		{
			for ( index = 0; index < colSize; index++ )
			{
				settlementScreen();

				if ( isSettlementPresent )
				{
					String settlementStatus = getSettlementStatus();
					assertEquals( settlementStatus, "Draft", "Settlement's status should be in draft state and found: " + settlementStatus );
					GenericHelper.waitForLoadmask( searchScreenWaitSec );
					String account = ExcelHolder.getKey( settlementsMap, "Account" );
					SettlementsDetailsImpl settlementsDetailOb = new SettlementsDetailsImpl( settlementsMap );
					int row = GridHelper.getRowNumber( "searchGrid", account, "Account" );
					GridHelper.clickRow( "searchGrid", account, "Account" );
					NavigationHelper.navigateToEdit( "searchGrid", row );
					settlementsDetailOb.editSettlment();
					settlementsDetailOb.saveSettlement();
					SettlmentSearchImpl settlmentSearchImpl = new SettlmentSearchImpl( settlementsMap );
					isSettlementPresent = settlmentSearchImpl.isSettlementPresent();
					assertTrue( isSettlementPresent, "Settlement is not found in search screen " );
					Log4jHelper.logInfo( "'Settlements' is updated successfully " );
				}
				else
				{
					Log4jHelper.logInfo( "Settlements is not avilable in the screen" );
				}

			}
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}

	}

	/*Method to check the error message with empty data*/

	public void settlementValidation() throws Exception
	{

		try
		{
			NavigationHelper.navigateToScreen( "Settlements" );
			assertEquals( NavigationHelper.getScreenTitle(), "Settlements Search" );
			for ( index = 0; index < colSize; index++ )
			{
				settlementsMap = excelHolderObj.dataMap( index );
				clientPartition = ExcelHolder.getKey( settlementsMap, "Partition" );
				ButtonHelper.click( "ClearButton" );
				settlementsActionOb.clickNewAction( clientPartition, "Settlement" );
				GenericHelper.waitForLoadmask( searchScreenWaitSec );
				ButtonHelper.click( "PS_Detail_settlements_save_BtndId" );
				GenericHelper.waitForLoadmask( searchScreenWaitSec );
				String errorText = LabelHelper.getText( "errorText" );
				assertTrue( errorText.contains( "cannot be empty or invalid." ) );
				GenericHelper.waitForLoadmask( searchScreenWaitSec );
				boolean isSchScreenPresent = ElementHelper.isElementPresent( GenericHelper.getORProperty( "PS_searchPanelId" ) );
				assertFalse( isSchScreenPresent, "Settlements should not saved with empty data" );
				Log4jHelper.logInfo( "'Empty Settlements creation' assertion is successfully completed" );

			}

		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}

	}

	/*
	 * This method is for Settlements cancel action
	 */
	public void settlementCancelAction() throws Exception
	{
		try
		{
			for ( index = 0; index < colSize; index++ )
			{
				settlementScreen();

				if ( isSettlementPresent )
				{
					String settlementStatus = getSettlementStatus();
					assertEquals( settlementStatus, "Draft", "Settlement's status should be in draft state and found: " + settlementStatus );
					settlementsActionOb.changeStatusAction( "Cancel" );
					GenericHelper.waitForLoadmask( searchScreenWaitSec );
					ElementHelper.waitForElement( GenericHelper.getORProperty( "PS_Detail_xdrExtTemp_popUP_Xpath" ), searchScreenWaitSec );
					if ( PopupHelper.isTextPresent( "window-scroll-panel", "Are you sure, you want to cancel the selected item ?" ) )
						ButtonHelper.click( "YesButton" );
					ElementHelper.waitForElementToDisappear( GenericHelper.getORProperty( "PS_Detail_xdrExtTemp_popUP_Xpath" ), searchScreenWaitSec );
					settlementStatus = getSettlementStatus();
					assertEquals( settlementStatus, "Cancelled", "Settlement status is not changed to cancel state" );
					Log4jHelper.logInfo( "Settlement's status  is changed to 'cancelled' successfully " );
				}
				else
				{
					Log4jHelper.logInfo( "Settlements is not avilable in the screen" );
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
	 * This method is for Settlements approve action
	 */
	public void settlementApproveAction() throws Exception
	{
		try
		{
			for ( index = 0; index < colSize; index++ )
			{
				settlementScreen();
				if ( isSettlementPresent )
				{
					String taskEvaluation = ExcelHolder.getKey( settlementsMap, "TaskEvalution" );
					String settlementStatus = getSettlementStatus();
					assertEquals( settlementStatus, "Draft", "Settlement's status should be in draft state and found: " + settlementStatus );
					settlementsActionOb.changeStatusAction( "Approve" );
					GenericHelper.waitForLoadmask( searchScreenWaitSec );
					GenericHelper.waitForLoadmask( searchScreenWaitSec );
					ElementHelper.waitForElement( GenericHelper.getORProperty( "PS_Detail_xdrExtTemp_popUP_Xpath" ), searchScreenWaitSec );
					if ( PopupHelper.isTextPresent( "window-scroll-panel", "Task" ) )
						ButtonHelper.click( "OKButton" );
					ElementHelper.waitForElementToDisappear( GenericHelper.getORProperty( "PS_Detail_xdrExtTemp_popUP_Xpath" ), searchScreenWaitSec );
					if ( ValidationHelper.isNotEmpty( taskEvaluation ) )
						settlementTaskStatus( taskEvaluation );
					settlementScreen();
					settlementStatus = getSettlementStatus();
					assertEquals( settlementStatus, "Approved", "Settlement status is not changed to 'Approved' state" );
					Log4jHelper.logInfo( "Settlement's status  is changed to 'Approved' successfully " );
				}
				else
				{
					Log4jHelper.logInfo( "Settlements is not avilable in the screen" );
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
	 * This method is for Settlements approve action Validate Error message
	 */
	public void settlementApproveActValidateMsz() throws Exception
	{
		try
		{
			for ( index = 0; index < colSize; index++ )
			{
				settlementScreen();
				if ( isSettlementPresent )
				{
					String settlementStatus = getSettlementStatus();
					assertEquals( settlementStatus, "Draft", "Settlement's status should be in draft state and found: " + settlementStatus );
					settlementsActionOb.changeStatusAction( "Approve" );
					GenericHelper.waitForLoadmask( searchScreenWaitSec );
					GenericHelper.waitForLoadmask( searchScreenWaitSec );
					ElementHelper.waitForElement( GenericHelper.getORProperty( "PS_Detail_xdrExtTemp_popUP_Xpath" ), searchScreenWaitSec );
					if ( PopupHelper.isTextPresent( "window-scroll-panel", "in this settlement are not in accepted state. Please accept it and try again" ) )
						ButtonHelper.click( "OKButton" );

					Log4jHelper.logInfo( "Settlement's status  is validate with the Error message while changing  to  'Approved' action" );
				}
				else
				{
					Log4jHelper.logInfo( "Settlements is not avilable in the screen" );
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
	 * This method is for Settlements Reversion action
	 */
	public void settlementReversionAction() throws Exception
	{
		try
		{
			for ( index = 0; index < colSize; index++ )
			{
				settlementScreen();
				if ( isSettlementPresent )
				{
					String settlementStatus = getSettlementStatus();
					String previousVersion = getSettlementVersion();
					assertEquals( settlementStatus, "Approved", "Settlement's status should be in Approved state for reversion and found: " + settlementStatus );
					settlementsActionOb.changeStatusAction( "Reversion" );
					GenericHelper.waitForLoadmask( searchScreenWaitSec );
					ElementHelper.waitForElement( GenericHelper.getORProperty( "PS_Detail_xdrExtTemp_popUP_Xpath" ), searchScreenWaitSec );
					if ( PopupHelper.isTextPresent( "window-scroll-panel", "Are you sure you want to reversion the selected item ?" ) )
						ButtonHelper.click( "YesButton" );
					ElementHelper.waitForElementToDisappear( GenericHelper.getORProperty( "PS_Detail_xdrExtTemp_popUP_Xpath" ), searchScreenWaitSec );
					ButtonHelper.click( "SearchButton" );
					GenericHelper.waitForLoadmask( searchScreenWaitSec );
					settlementStatus = getSettlementStatus();
					assertEquals( settlementStatus, "Draft", "Settlement status is not changed to 'Draft' state" );
					assertEquals( getSettlementVersion(), String.valueOf( Integer.valueOf( previousVersion ) + 1 ), "Version is not changed  and found " + getSettlementVersion() );
					Log4jHelper.logInfo( "Settlement's status  is 'Reversioned' successfully " );
				}
				else
				{
					Log4jHelper.logInfo( "Settlements is not avilable in the screen" );
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
	 * This method is for Settlements Settle action
	 */
	public void settlementSettleAction() throws Exception
	{
		try
		{
			for ( index = 0; index < colSize; index++ )
			{
				settlementScreen();
				if ( isSettlementPresent )
				{
					String settlementStatus = getSettlementStatus();
					assertEquals( settlementStatus, "Approved", "Settlement's status should be in Approved state for settle and found: " + settlementStatus );
					settlementsActionOb.changeStatusAction( "Settle" );
					GenericHelper.waitForLoadmask( searchScreenWaitSec );
					ElementHelper.waitForElement( GenericHelper.getORProperty( "PS_Detail_xdrExtTemp_popUP_Xpath" ), searchScreenWaitSec );
					if ( PopupHelper.isTextPresent( "window-scroll-panel", "This would settle the selected item" ) )
						ButtonHelper.click( "YesButton" );
					GenericHelper.waitForLoadmask( searchScreenWaitSec );
					ElementHelper.waitForElement( GenericHelper.getORProperty( "PS_Detail_xdrExtTemp_popUP_Xpath" ), searchScreenWaitSec );
					if ( PopupHelper.isTextPresent( "window-scroll-panel", "Successfully changed the status to Settled" ) )
						ButtonHelper.click( "OKButton" );
					ElementHelper.waitForElementToDisappear( GenericHelper.getORProperty( "PS_Detail_xdrExtTemp_popUP_Xpath" ), searchScreenWaitSec );
					ButtonHelper.click( "SearchButton" );
					settlementStatus = getSettlementStatus();
					assertEquals( settlementStatus, "Settled", "Settlement status is not changed to 'Settled' state" );
					Log4jHelper.logInfo( "Settlement's status  is 'Settled' successfully " );
				}
				else
				{
					Log4jHelper.logInfo( "Settlements is not avilable in the screen" );
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
	 * This method is for Settlements jump to settlement history action
	 */
	public void settlementJumpHistoryAction() throws Exception
	{
		try
		{
			for ( index = 0; index < colSize; index++ )
			{
				settlementScreen();
				if ( isSettlementPresent )
				{
					GridHelper.clickRow( "searchGrid", 1, "Name" );
					settlementsActionOb.jumpToSettHistoryAction();
					GenericHelper.waitForLoadmask( searchScreenWaitSec );
					validateResult( GenericHelper.getORProperty( "PS_Detail_Settlements_HistoryColumnHeaderID" ), "settlementHistoryGrid" );
					ButtonHelper.click( "PS_Detail_settlements_Historyclose_BtndId" );
					GenericHelper.waitForLoadmask( searchScreenWaitSec );
					Log4jHelper.logInfo( "Settlement History's results  are validates successfully " );
				}
				else
				{
					Log4jHelper.logInfo( "Settlements is not avilable in the screen" );
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
	 * This method is for Settlements currency breakdown action
	 */
	public void settlementCurrencyBreakdownAction() throws Exception
	{
		try
		{
			for ( index = 0; index < colSize; index++ )
			{
				settlementScreen();
				if ( isSettlementPresent )
				{
					GridHelper.clickRow( "searchGrid", 1, "Name" );
					settlementsActionOb.settlementsAction( "Settlement Currency Breakdown" );
					GenericHelper.waitForLoadmask( searchScreenWaitSec );
					ElementHelper.waitForElement( GenericHelper.getORProperty( "PS_Detail_xdrExtTemp_popUP_Xpath" ), searchScreenWaitSec );
					validateResult( GenericHelper.getORProperty( "PS_Detail_Settlements_HistoryColumnHeaderID" ), "currencyBreakdownGrid" );
					ButtonHelper.click( "PS_Detail_settlements_CurrenBrkdownclose_BtndId" );
					GenericHelper.waitForLoadmask( searchScreenWaitSec );
					Log4jHelper.logInfo( "'View Settlement Currency Breakdown's results  are validates successfully " );
				}
				else
				{
					Log4jHelper.logInfo( "Settlements is not avilable in the screen" );
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
	 * This method is for create test Settlement action
	 */
	public void createTestSettlementAction() throws Exception
	{
		try
		{
			for ( index = 0; index < colSize; index++ )
			{
				settlementScreen();
				if ( isSettlementPresent )
				{
					String taskEvaluation = ExcelHolder.getKey( settlementsMap, "TaskEvalution" );
					String settlementStatus = getSettlementStatus();
					assertEquals( settlementStatus, "Draft", "Settlement's status should be in draft state and found: " + settlementStatus );
					settlementsActionOb.settlementsAction( "Create Test Settlement" );
					GenericHelper.waitForLoadmask( searchScreenWaitSec );
					ElementHelper.waitForElement( GenericHelper.getORProperty( "PS_Detail_xdrExtTemp_popUP_Xpath" ), searchScreenWaitSec );
					if ( PopupHelper.isTextPresent( "window-scroll-panel", "Are you sure you want to create test settlement request?" ) )
						ButtonHelper.click( "YesButton" );
					GenericHelper.waitForLoadmask( searchScreenWaitSec );
					ElementHelper.waitForElement( GenericHelper.getORProperty( "PS_Detail_xdrExtTemp_popUP_Xpath" ), searchScreenWaitSec );
					if ( PopupHelper.isTextPresent( "window-scroll-panel", "Task successfully scheduled." ) )
						ButtonHelper.click( "OKButton" );
					ElementHelper.waitForElementToDisappear( GenericHelper.getORProperty( "PS_Detail_xdrExtTemp_popUP_Xpath" ), searchScreenWaitSec );
					if ( ValidationHelper.isNotEmpty( taskEvaluation ) )
						settlementTaskStatus( taskEvaluation );

					Log4jHelper.logInfo( "'Create Test Settlement Action's results  are validates successfully " );
				}
				else
				{
					Log4jHelper.logInfo( "Settlements is not avilable in the screen" );
				}

			}
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}

	}

	// Method: Get the status of settlement
	private String getSettlementStatus() throws Exception
	{
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		GenericHelper.waitTime( 3, "" );
		GridHelper.clickRow( "searchGrid", 1, "Name" );
		return GridHelper.getCellValue( "searchGrid", 1, "Status" );
	}

	// Method: Get the version of settlement
	private String getSettlementVersion() throws Exception
	{
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		GenericHelper.waitTime( 3, "" );
		GridHelper.clickRow( "searchGrid", 1, "Name" );
		return GridHelper.getCellValue( "searchGrid", 1, "Version" );
	}

	// Method: Validate the search result with data verification helper
	private void validateResult( String columHeaderId, String gridId ) throws Exception
	{
		colmHdrs = ExcelHolder.getKey( settlementsMap, "ColumnHeaders" );
		mapkeys = ExcelHolder.getKey( settlementsMap, "MapRowkeys" );
		DataVerificationHelper dataVerHelOb = new DataVerificationHelper();
		if ( ValidationHelper.isNotEmpty( mapkeys ) )
			dataVerHelOb.validateDataWithoutSorting( gridId, columHeaderId, settlementsMap, colmHdrs, mapkeys, false );

	}

	// Method: verify the settlement task status in Task search
	private void settlementTaskStatus( String testCasenm ) throws Exception
	{
		String path = System.getProperty( "user.dir" ) + "\\src\\main\\resources\\";
		workBookName = ExcelHolder.getKey( settlementsMap, "WorkBookName" );
		sheetName = ExcelHolder.getKey( settlementsMap, "SheetName" );
		PSTaskSearchHelper psTaskObj = new PSTaskSearchHelper();
		psTaskObj.psVerifyTaskStatus( path, workBookName, sheetName, testCasenm, 1 );

	}

}
