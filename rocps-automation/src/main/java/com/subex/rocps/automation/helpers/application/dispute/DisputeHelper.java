package com.subex.rocps.automation.helpers.application.dispute;

import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;

import java.util.ArrayList;
import java.util.Map;

import org.openqa.selenium.By;

import com.subex.automation.helpers.application.NavigationHelper;
import com.subex.automation.helpers.component.ButtonHelper;
import com.subex.automation.helpers.component.CheckBoxHelper;
import com.subex.automation.helpers.component.ComboBoxHelper;
import com.subex.automation.helpers.component.ElementHelper;
import com.subex.automation.helpers.component.GenericHelper;
import com.subex.automation.helpers.component.GridHelper;
import com.subex.automation.helpers.component.MouseHelper;
import com.subex.automation.helpers.component.PopupHelper;
import com.subex.automation.helpers.component.SearchGridHelper;
import com.subex.automation.helpers.component.TextAreaHelper;
import com.subex.automation.helpers.component.TextBoxHelper;
import com.subex.automation.helpers.data.ValidationHelper;
import com.subex.automation.helpers.file.ExcelReader;
import com.subex.automation.helpers.report.Log4jHelper;
import com.subex.automation.helpers.util.FailureHelper;

import com.subex.rocps.automation.helpers.application.dispute.dispute.DisputeActionImpl;
import com.subex.rocps.automation.helpers.application.dispute.dispute.DisputeDetailImpl;
import com.subex.rocps.automation.helpers.application.filters.GridFilterSearchHelper;
import com.subex.rocps.automation.helpers.application.filters.PSSearchGridHelper;
import com.subex.rocps.automation.helpers.application.genericHelpers.PSGenericHelper;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;
import com.subex.rocps.automation.utils.ExcelHolder;

public class DisputeHelper extends PSAcceptanceTest
{

	protected ExcelReader excelData = new ExcelReader();
	protected Map<String, ArrayList<String>> disputeExcelMap = null;
	protected Map<String, String> disputeMap = null;
	protected ExcelHolder excelHolderObj = null;
	protected String path;
	protected String workBookName;
	protected String sheetName;
	protected String testCaseName;
	protected int occurence;
	protected String clientPartition;
	protected String disputeNm;
	protected String disputeCd;
	protected String disputeComponent;
	protected String manualCreaFlg;
	protected String disputeType;
	protected String account;
	protected String billProfile;
	protected String columnVal;
	protected String rejectReason;
	protected String dispRootCause;
	protected String favHomeOpAmnt;
	protected String closureCmnt;
	protected String creditNotes;
	protected int colSize;
	protected int index;
	PSGenericHelper psGenericHelper = new PSGenericHelper();
	protected GridFilterSearchHelper gridFilterSearchHelper = new GridFilterSearchHelper();

	//Constructor : Initializing the excel without occurrence 
	public DisputeHelper( String path, String workBookName, String sheetName, String testCaseName ) throws Exception
	{

		this.path = path;
		this.workBookName = workBookName;
		this.sheetName = sheetName;
		this.testCaseName = testCaseName;
		disputeExcelMap = excelData.readDataByColumn( path, workBookName, sheetName, testCaseName );
		excelHolderObj = new ExcelHolder( disputeExcelMap );
		colSize = excelHolderObj.totalColumns();
	}

	//Constructor : Initializing the excel with occurrence 
	public DisputeHelper( String path, String workBookName, String sheetName, String testCaseName, int occurence ) throws Exception
	{

		this.path = path;
		this.workBookName = workBookName;
		this.sheetName = sheetName;
		this.testCaseName = testCaseName;
		this.occurence = occurence;
		disputeExcelMap = excelData.readDataByColumn( path, workBookName, sheetName, testCaseName, occurence );
		excelHolderObj = new ExcelHolder( disputeExcelMap );
		colSize = excelHolderObj.totalColumns();
	}

	/*
	 * Configuring the  Dispute Type
	 * 
	 */
	public void disputeTypeCreation() throws Exception
	{
		try
		{
			NavigationHelper.navigateToReferenceTable( "Dispute Type" );
			for ( index = 0; index < colSize; index++ )
			{

				disputeMap = excelHolderObj.dataMap( index );
				initializeVariableForDispType( disputeMap );
				GenericHelper.waitForLoadmask( searchScreenWaitSec );
				boolean isDispCompPresent = GridHelper.isValuePresent( "searchGrid", disputeComponent, "Component" );

				if ( !isDispCompPresent )
				{
					psGenericHelper.clickNewAction( clientPartition );
					GenericHelper.waitForLoadmask();
					newDisputeType();
					saveDisputeType();

					Log4jHelper.logInfo( "'Dispute Type' is created successfully with name: " + disputeNm );
				}
				else
				{
					GridHelper.clickRow( "searchGrid", disputeComponent, "Component" );
					GenericHelper.waitForLoadmask();
					NavigationHelper.navigateToAction( "Common Tasks", "Edit" );
					editDisputeType();
					saveDisputeType();
					Log4jHelper.logInfo( "'Dispute Type' is updated  with name " + disputeNm );
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
	 * this method is to create new DisputeType
	 */
	public void newDisputeType() throws Exception
	{
		GenericHelper.waitForLoadmask();
		assertEquals( NavigationHelper.getScreenTitle(), "New Dispute Type" );
		TextBoxHelper.type( "PS_Detail_disputeType_wrapperID", "PS_Detail_disputeType_name_textID", disputeNm );
		TextBoxHelper.type( "PS_Detail_disputeType_wrapperID", "PS_Detail_disputeType_code_textID", disputeCd );
		ComboBoxHelper.select( "PS_Detail_disputeType_component_ComboID", disputeComponent );
		if ( ValidationHelper.isTrue( manualCreaFlg ) )
			CheckBoxHelper.check( "PS_Detail_disputeType_wrapperID", "PS_Detail_disputeType_manualCreFlg_ChckBxID" );

	}

	/*
	 * this method is to Edit  DisputeType
	 */
	public void editDisputeType() throws Exception
	{
		GenericHelper.waitForLoadmask();
		assertEquals( NavigationHelper.getScreenTitle(), "Edit Dispute Type" );
		TextBoxHelper.type( "PS_Detail_disputeType_wrapperID", "PS_Detail_disputeType_name_textID", disputeNm );
		TextBoxHelper.type( "PS_Detail_disputeType_wrapperID", "PS_Detail_disputeType_code_textID", disputeCd );
		ComboBoxHelper.select( "PS_Detail_disputeType_component_ComboID", disputeComponent );
		if ( ValidationHelper.isTrue( manualCreaFlg ) )
			CheckBoxHelper.check( "PS_Detail_disputeType_wrapperID", "PS_Detail_disputeType_manualCreFlg_ChckBxID" );

	}

	/*
	 * this method is to Save  DisputeType
	 */
	public void saveDisputeType() throws Exception
	{
		ButtonHelper.click( "SaveButton" );
		GenericHelper.waitForSave();
		assertEquals( NavigationHelper.getScreenTitle(), "Reference Table Search" );
		assertTrue( GridHelper.isValuePresent( "searchGrid", disputeComponent, "Component" ), "Dispute Type is not configured" );
	}

	/*Method to check the error message with empty data*/

	public void disputeValidation() throws Exception
	{

		try
		{
			NavigationHelper.navigateToScreen( "Dispute" );
			assertEquals( NavigationHelper.getScreenTitle(), "Dispute Search" );
			for ( index = 0; index < colSize; index++ )
			{
				disputeMap = excelHolderObj.dataMap( index );
				clientPartition = ExcelHolder.getKey( disputeMap, "Partition" );
				ButtonHelper.click( "ClearButton" );
				GenericHelper.waitForLoadmask( searchScreenWaitSec );
				DisputeActionImpl disputeActionImpl = new DisputeActionImpl();
				disputeActionImpl.clickNewAction( clientPartition, "Dispute" );
				GenericHelper.waitForLoadmask( searchScreenWaitSec );
				ButtonHelper.click( "PS_Detail_dispute_Save_btnID" );
				String errorText = driver.findElement( By.id( "errorText" ) ).getText();
				assertTrue( errorText.contains( "cannot be empty or invalid." ) );
				GenericHelper.waitForLoadmask( searchScreenWaitSec );
				boolean isSerachScreen = ElementHelper.isElementPresent( "PS_searchPanelId" );
				assertFalse( isSerachScreen, "Dispute should not saved with empty data" );
				Log4jHelper.logInfo( "'Empty dispute creation' assertion is successfully completed" );

			}

		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}

	}

	//Configuring the new Dispute
	public void createDispute() throws Exception
	{

		try
		{
			NavigationHelper.navigateToScreen( "Dispute" );
			assertEquals( NavigationHelper.getScreenTitle(), "Dispute Search" );
			for ( index = 0; index < colSize; index++ )
			{
				disputeMap = excelHolderObj.dataMap( index );
				initializeVariables( disputeMap );
				ButtonHelper.click( "ClearButton" );
				GenericHelper.waitForLoadmask( searchScreenWaitSec );

				if ( !isDisputePresent() )
				{
					DisputeActionImpl disputeActionImpl = new DisputeActionImpl();
					DisputeDetailImpl disputeDetailImpl = new DisputeDetailImpl( disputeMap );
					disputeActionImpl.clickNewAction( clientPartition, "Dispute" );
					disputeDetailImpl.createDisputeConfig();
					disputeDetailImpl.saveDispute( columnVal, disputeType );
					Log4jHelper.logInfo( "Dispute is successfully created with the value-'" + columnVal + "' for this type of Dispute: " + disputeType );

				}
				else
				{
					Log4jHelper.logInfo( "Dispute is availabe with the value-'" + columnVal + "' for this type of Dispute: " + disputeType );
				}
			}
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

	//Method to the edit Dispute
	public void editDispute() throws Exception
	{

		try
		{
			NavigationHelper.navigateToScreen( "Dispute" );
			assertEquals( NavigationHelper.getScreenTitle(), "Dispute Search" );
			for ( index = 0; index < colSize; index++ )
			{
				disputeMap = excelHolderObj.dataMap( index );
				initializeVariables( disputeMap );
				ButtonHelper.click( "ClearButton" );
				GenericHelper.waitForLoadmask( searchScreenWaitSec );
				psGenericHelper.waitforHeaderElement( "Dispute Type" );
				boolean isDisputePresent = isDisputePresent();
				assertTrue( isDisputePresent, "Dispute is not available in the screen with the value: -'" + columnVal + "' for this type of Dispute: " + disputeType );
				String disputeStatus = GridHelper.getCellValue( "searchGrid", 1, "Dispute Status" );
				if ( disputeStatus.contentEquals( "Draft" ) )
				{
					DisputeDetailImpl disputeDetailImpl = new DisputeDetailImpl( disputeMap );
					GridHelper.clickRow( "searchGrid", 1, "Dispute Status" );
					NavigationHelper.navigateToEdit( "searchGrid", 1 );
					disputeDetailImpl.editDisputeConfig();
					disputeDetailImpl.saveDispute( columnVal, disputeType );
					Log4jHelper.logInfo( "Dispute is successfully updated with the value-'" + columnVal + "' for this type of Dispute: " + disputeType );

				}
				else
				{
					Log4jHelper.logInfo( "Dispute is not as expeceted  'Draft' State with the value-'" + columnVal + "' for this type of Dispute: " + disputeType );

				}
			}
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}
	//Method: for Dispute deletion action

	public void disputeDelete() throws Exception
	{
		NavigationHelper.navigateToScreen( "Dispute" );
		assertEquals( NavigationHelper.getScreenTitle(), "Dispute Search" );
		disputeMap = excelHolderObj.dataMap( index );
		initializeVariables( disputeMap );
		ButtonHelper.click( "ClearButton" );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );

		boolean isDisputePresent = isDisputePresent();
		assertTrue( isDisputePresent, "Dispute is not available in the screen with the value: -'" + columnVal + "' for this type of Dispute: " + disputeType );
		String disputeStatus = GridHelper.getCellValue( "searchGrid", 1, "Dispute Status" );

		if ( disputeStatus.contentEquals( "Draft" ) || disputeStatus.contentEquals( "Rejected" ) )
		{

			psGenericHelper.clickDeleteOrUnDeleteAction( disputeStatus, "Dispute Status", "Delete" );
			GenericHelper.waitForLoadmask();
			psGenericHelper.collapsableXpath();

			psGenericHelper.selectPartitionFilter( clientPartition, "Deleted Items" );
			boolean isdisputeDelete = isDisputePresent();
			assertTrue( isdisputeDelete, disputeType + " is not present" );
			Log4jHelper.logInfo( "Dispute is deleted successfully with the value-'" + columnVal + "' for this type of Dispute: " + disputeType );
		}
		else
		{
			Log4jHelper.logInfo( "Dispute is not as expeceted  'Draft' or 'Rejected' State with the value-'" + columnVal + "' for this type of Dispute: " + disputeType );
		}
	}

	//Method: for Dispute Undeletion action
	public void disputeUnDelete() throws Exception
	{

		for ( index = 0; index < colSize; index++ )
		{
			NavigationHelper.navigateToScreen( "Dispute" );
			assertEquals( NavigationHelper.getScreenTitle(), "Dispute Search" );
			disputeMap = excelHolderObj.dataMap( index );
			initializeVariables( disputeMap );
			ButtonHelper.click( "ClearButton" );
			GenericHelper.waitForLoadmask( searchScreenWaitSec );
			psGenericHelper.selectPartitionFilter( clientPartition, "Deleted Items" );
			boolean isDisputePresent = isDisputePresent();
			assertTrue( isDisputePresent, "Dispute is not available in the screen with the value: -'" + columnVal + "' for this type of Dispute: " + disputeType );
			String disputeStatus = GridHelper.getCellValue( "searchGrid", 1, "Dispute Status" );
			psGenericHelper.clickDeleteOrUnDeleteAction( disputeStatus, "Dispute Status", "Undelete" );
			GenericHelper.waitForLoadmask();
			psGenericHelper.selectPartitionFilter( clientPartition, "Non Deleted Items" );
			boolean isDisputeUnDelete = isDisputePresent();
			assertTrue( isDisputeUnDelete, disputeType + " is not present" );
			Log4jHelper.logInfo( "Dispute is un deleted successfully with the value  '" + columnVal + "' for this type of Dispute: " + disputeType );
		}

	}

	//Method: for reject the dispute 
	public void rejectDispute() throws Exception
	{

		try
		{

			for ( index = 0; index < colSize; index++ )
			{
				NavigationHelper.navigateToScreen( "Dispute" );
				assertEquals( NavigationHelper.getScreenTitle(), "Dispute Search" );
				disputeMap = excelHolderObj.dataMap( index );
				initializeVariables( disputeMap );
				rejectReason = ExcelHolder.getKey( disputeMap, "RejectReason" );
				ButtonHelper.click( "ClearButton" );
				GenericHelper.waitForLoadmask( searchScreenWaitSec );

				boolean isDisputePresent = isDisputePresent();
				assertTrue( isDisputePresent, "Dispute is not available in the screen with the value: -'" + columnVal + "' for this type of Dispute: " + disputeType );
				String disputeStatus = GridHelper.getCellValue( "searchGrid", 1, "Dispute Status" );

				if ( disputeStatus.contentEquals( "Draft" ) )
				{
					DisputeActionImpl disputeActionImpl = new DisputeActionImpl();
					GridHelper.clickRow( "searchGrid", 1, "Dispute Status" );
					disputeActionImpl.clickRejectDisputeAction( "Reject Dispute", rejectReason );
					disputeStatus = GridHelper.getCellValue( "searchGrid", 1, "Dispute Status" );
					assertEquals( disputeStatus, "Rejected", "The status is not changed to 'Rejected' from 'Draft' state" );
					Log4jHelper.logInfo( "Dispute is successfully 'Rejected' with the value-'" + columnVal + "' for this type of Dispute: " + disputeType );

				}
				else
				{
					Log4jHelper.logInfo( "Dispute status is not in 'Draft' State of this -'" + columnVal + "' for this type of Dispute: " + disputeType );

				}
			}
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

	//Method: for Validate the dispute with action 
	public void validateDispute() throws Exception
	{

		try
		{

			for ( index = 0; index < colSize; index++ )
			{
				NavigationHelper.navigateToScreen( "Dispute" );
				assertEquals( NavigationHelper.getScreenTitle(), "Dispute Search" );
				disputeMap = excelHolderObj.dataMap( index );
				initializeVariables( disputeMap );
				ButtonHelper.click( "ClearButton" );
				GenericHelper.waitForLoadmask( searchScreenWaitSec );

				boolean isDisputePresent = isDisputePresent();
				assertTrue( isDisputePresent, "Dispute is not available in the screen with the value: -'" + columnVal + "' for this type of Dispute: " + disputeType );
				String disputeStatus = GridHelper.getCellValue( "searchGrid", 1, "Dispute Status" );

				if ( disputeStatus.contentEquals( "Draft" ) )
				{
					DisputeActionImpl disputeActionImpl = new DisputeActionImpl();
					System.out.println( columnVal + " value Status for : validateDispute " + disputeStatus );
					GridHelper.clickRow( "searchGrid", 1, "Dispute Status" );
					disputeActionImpl.clickValidateDisputeAction( "Confirmation" );
					Thread.sleep( 2000 );
					disputeStatus = GridHelper.getCellValue( "searchGrid", 1, "Dispute Status" );
					assertEquals( disputeStatus, "Open", "The status is not changed to 'Open' from 'Draft' state" );
					Log4jHelper.logInfo( "Dispute is successfully 'Validated' with the value-'" + columnVal + "' for this type of Dispute: " + disputeType );

				}
				else
				{
					Log4jHelper.logInfo( "Dispute status is not in 'Draft' State of this -'" + columnVal + "' for this type of Dispute: " + disputeType );
				}
			}
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

	//Method: for Move to Dispute Resolve the dispute

	public void moveToDisputeResolve() throws Exception
	{

		try
		{

			for ( index = 0; index < colSize; index++ )
			{
				NavigationHelper.navigateToScreen( "Dispute" );
				assertEquals( NavigationHelper.getScreenTitle(), "Dispute Search" );
				disputeMap = excelHolderObj.dataMap( index );
				initializeVariables( disputeMap );
				creditNotes = ExcelHolder.getKey( disputeMap, "CreditNoteFlag" );
				dispRootCause = ExcelHolder.getKey( disputeMap, "DisputeRootCause" );
				favHomeOpAmnt = ExcelHolder.getKey( disputeMap, "AmountFavHomeOp" );
				closureCmnt = ExcelHolder.getKey( disputeMap, "ClosureComment" );
				ButtonHelper.click( "ClearButton" );
				GenericHelper.waitForLoadmask( searchScreenWaitSec );

				boolean isDisputePresent = isDisputePresent();
				assertTrue( isDisputePresent, "Dispute is not available in the screen with the value: -'" + columnVal + "' for this type of Dispute: " + disputeType );
				String disputeStatus = GridHelper.getCellValue( "searchGrid", 1, "Dispute Status" );

				if ( disputeStatus.contentEquals( "Open" ) )
				{
					DisputeActionImpl disputeActionImpl = new DisputeActionImpl();
					GridHelper.clickRow( "searchGrid", 1, "Dispute Status" );
					disputeActionImpl.moveToDisputeResolveAction( "Resolve Dispute", dispRootCause, favHomeOpAmnt, closureCmnt );
					assertEquals( NavigationHelper.getScreenTitle(), "Confirm" );
					creditNotesTask();
					disputeStatus = GridHelper.getCellValue( "searchGrid", 1, "Dispute Status" );
					assertEquals( disputeStatus, "Resolved", "The status is not changed to 'Resolved' from 'Open' state" );
					Log4jHelper.logInfo( "Dispute is successfully 'Move to Resolve state' with the value-'" + columnVal + "' for this type of Dispute: " + disputeType );

				}
				else
				{
					Log4jHelper.logInfo( "Dispute status is not in 'Open' State of this -'" + columnVal + "' for this type of Dispute: " + disputeType );
				}
			}
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

	//Method: for Close  the dispute 
	public void closeDispute() throws Exception
	{

		try
		{

			for ( index = 0; index < colSize; index++ )
			{
				NavigationHelper.navigateToScreen( "Dispute" );
				assertEquals( NavigationHelper.getScreenTitle(), "Dispute Search" );
				disputeMap = excelHolderObj.dataMap( index );
				initializeVariables( disputeMap );
				ButtonHelper.click( "ClearButton" );
				GenericHelper.waitForLoadmask( searchScreenWaitSec );

				boolean isDisputePresent = isDisputePresent();
				assertTrue( isDisputePresent, "Dispute is not available in the screen with the value: -'" + columnVal + "' for this type of Dispute: " + disputeType );
				String disputeStatus = GridHelper.getCellValue( "searchGrid", 1, "Dispute Status" );

				if ( disputeStatus.contentEquals( "Resolved" ) )
				{
					DisputeActionImpl disputeActionImpl = new DisputeActionImpl();
					System.out.println( columnVal + " value Status for : closeDispute " + disputeStatus );
					GridHelper.clickRow( "searchGrid", 1, "Dispute Status" );
					disputeActionImpl.closeDisputeAction();
					Log4jHelper.logInfo( "Dispute is successfully 'Closed' with the value-'" + columnVal + "' for this type of Dispute: " + disputeType );

				}
				else
				{
					Log4jHelper.logInfo( "Dispute status is not in 'Resolved' State of this -'" + columnVal + "' for this type of Dispute: " + disputeType );
				}
			}
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

	//Method: for View update history  dispute 
	public void viewUpdateHistoryDispute() throws Exception
	{

		try
		{

			for ( index = 0; index < colSize; index++ )
			{
				NavigationHelper.navigateToScreen( "Dispute" );
				assertEquals( NavigationHelper.getScreenTitle(), "Dispute Search" );
				disputeMap = excelHolderObj.dataMap( index );
				initializeVariables( disputeMap );
				ButtonHelper.click( "ClearButton" );
				GenericHelper.waitForLoadmask( searchScreenWaitSec );

				boolean isDisputePresent = isDisputePresent();
				assertTrue( isDisputePresent, "Dispute is not available in the screen with the value: -'" + columnVal + "' for this type of Dispute: " + disputeType );
				String disputeStatus = GridHelper.getCellValue( "searchGrid", 1, "Dispute Status" );

				DisputeActionImpl disputeActionImpl = new DisputeActionImpl();
				System.out.println( columnVal + " value Status for : viewUpdateHistoryDispute " + disputeStatus );
				GridHelper.clickRow( "searchGrid", 1, "Dispute Status" );
				disputeActionImpl.clickViewUpdtHistoryDispAction( "Dispute History" );
				Log4jHelper.logInfo( "Dispute is successfully viewed 'Dispute History' with the value-'" + columnVal + "' for this type of Dispute: " + disputeType );

			}

		}

		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

	//Method: for CreditNote Screen validation 
	private void creditNotesTask() throws Exception
	{

		ElementHelper.waitForElement( GenericHelper.getORProperty( "PS_Detail_xdrExtTemp_popUP_Xpath" ), searchScreenWaitSec );

		if ( ValidationHelper.isTrue( creditNotes ) )
		{
			GenericHelper.waitForLoadmask( searchScreenWaitSec );
			if ( PopupHelper.isTextPresent( "window-scroll-panel", "Do you want to create a Credit Note for this Dispute?" ) )
				ButtonHelper.click( "YesButton" );
			GenericHelper.waitForLoadmask( searchScreenWaitSec );
			System.out.println( NavigationHelper.getScreenTitle() + "  credit not title" );
			assertEquals( NavigationHelper.getScreenTitle(), "Edit Credit" );
			ButtonHelper.click( "creditDetail.save" );
			GenericHelper.waitForLoadmask( searchScreenWaitSec );

		}
		else
			ButtonHelper.click( "NoButton" );
		ElementHelper.waitForElementToDisappear( GenericHelper.getORProperty( "PS_Detail_xdrExtTemp_popUP_Xpath" ), searchScreenWaitSec );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );

	}

	//Method: for validate the dispute is available in the screen
	protected boolean isDisputePresent() throws Exception
	{
		boolean disputePresent = false;
		try
		{

			if ( disputeType.contains( "Account Component" ) )
			{
				columnVal = account;
				PSSearchGridHelper.gridFilterSearchWithComboBox( "PS_Detail_disputeSearchScr_disputeType_ComboID", "Dispute Account Component", "Dispute Type" );
				GridFilterSearchHelper.gridFilterAdvancedSearch( "PS_Detail_dispute_account_gridColID", account, "Account" );
				disputePresent = GridHelper.isValuePresent( "searchGrid", account, "Account" );
			}
			else if ( disputeType.contains( "Bill Profile Component" ) )
			{
				columnVal = billProfile;
				//gridFilterSearchHelper.billProfileAdvanceFilter("searchGrid", "Bill Profile", billProfile);
				//disputePresent= GridHelper.isValuePresent("searchGrid", "Dispute Bill Profile Component", "Dispute Type");
				PSSearchGridHelper.gridFilterSearchWithComboBox( "PS_Detail_disputeSearchScr_disputeType_ComboID", "Dispute Bill Profile Component", "Dispute Type" );
				GridFilterSearchHelper.gridFilterAdvancedSearch( "PS_Detail_dispute_account_gridColID", account, "Account" );
				disputePresent = GridHelper.isValuePresent( "searchGrid", account, "Account" );

			}
			else if ( disputeType.contains( "Bill Component" ) )
			{
				columnVal = billProfile;
				PSSearchGridHelper.gridFilterSearchWithComboBox( "PS_Detail_disputeSearchScr_disputeType_ComboID", "Dispute Bill Component", "Dispute Type" );
				gridFilterSearchHelper.billProfileAdvanceFilter( "searchGrid", "Bill Profile", billProfile );
				disputePresent = GridHelper.isValuePresent( "searchGrid", "Dispute Bill Component", "Dispute Type" );
			}
			else if ( disputeType.contains( "Carrier Invoice Component" ) )
			{
				columnVal = billProfile;
				PSSearchGridHelper.gridFilterSearchWithComboBox( "PS_Detail_disputeSearchScr_disputeType_ComboID", "Dispute Carrier Invoice Component", "Dispute Type" );
				gridFilterSearchHelper.billProfileAdvanceFilter( "searchGrid", "Bill Profile", billProfile );
				disputePresent = GridHelper.isValuePresent( "searchGrid", "Dispute Carrier Invoice Component", "Dispute Type" );

			}
			else if ( disputeType.contains( "System Dispute Component" ) )
			{
				columnVal = account;
				PSSearchGridHelper.gridFilterSearchWithComboBox( "PS_Detail_disputeSearchScr_disputeType_ComboID", "System Dispute Component", "Dispute Type" );
				GridFilterSearchHelper.gridFilterAdvancedSearch( "PS_Detail_dispute_account_gridColID", account, "Account" );
				disputePresent = GridHelper.isValuePresent( "searchGrid", account, "Account" );
			}
			return disputePresent;

		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

	//Method: for verify the Column headers of the dispute screen

	public void verifyTheColHdrsOfDisputeSrch() throws Exception
	{
		try
		{
			NavigationHelper.navigateToScreen( "Dispute" );
			GenericHelper.waitForLoadmask( detailScreenWaitSec );
			assertEquals( NavigationHelper.getScreenTitle(), "Dispute Search" );
			for ( index = 0; index < colSize; index++ )
			{
				disputeMap = excelHolderObj.dataMap( index );
				DisputeDetailImpl disputeDetailImpl = new DisputeDetailImpl( disputeMap );
				disputeDetailImpl.verifyColmnHeaderOfDispute();
				Log4jHelper.logInfo( "Dispute Columns are validated successfully" );
			}
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

	//Method: for initializing the variables of dispute 
	public void initializeVariables( Map<String, String> map ) throws Exception
	{
		columnVal = null;
		clientPartition = ExcelHolder.getKey( map, "Partition" );
		disputeType = ExcelHolder.getKey( map, "DisputeType" );
		account = ExcelHolder.getKey( map, "Account" );
		billProfile = ExcelHolder.getKey( map, "BillProfile" );

	}

	//Method: for initializing the variables of dispute Type creation
	public void initializeVariableForDispType( Map<String, String> map ) throws Exception
	{
		disputeNm = ExcelHolder.getKey( map, "DisputeName" );
		disputeCd = ExcelHolder.getKey( map, "DisputeCode" );
		disputeComponent = ExcelHolder.getKey( map, "DisputeComponent" );
		manualCreaFlg = ExcelHolder.getKey( map, "ManualCreationFlag" );

	}

}
