package com.subex.rocps.automation.helpers.application.reaggregation;

import java.util.ArrayList;
import java.util.Map;

import com.subex.automation.helpers.application.NavigationHelper;
import com.subex.automation.helpers.component.ButtonHelper;
import com.subex.automation.helpers.component.ElementHelper;
import com.subex.automation.helpers.component.GenericHelper;
import com.subex.automation.helpers.component.GridHelper;
import com.subex.automation.helpers.component.PopupHelper;
import com.subex.automation.helpers.file.ExcelReader;
import com.subex.automation.helpers.report.Log4jHelper;
import com.subex.automation.helpers.util.FailureHelper;
import com.subex.rocps.automation.helpers.application.genericHelpers.PSActionImpl;
import com.subex.rocps.automation.helpers.application.genericHelpers.PSDataComponentHelper;
import com.subex.rocps.automation.helpers.application.genericHelpers.PSGenericHelper;
import com.subex.rocps.automation.helpers.application.products.provStatusGrp.ProvisionStatusGrpDetailImpl;
import com.subex.rocps.automation.helpers.application.reaggregation.usgBackoutReq.UsageBackoutReqActImpl;
import com.subex.rocps.automation.helpers.application.reaggregation.usgBackoutReq.UsgBackoutReqDetailImpl;
import com.subex.rocps.automation.helpers.application.system.PSTaskSearchHelper;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;
import com.subex.rocps.automation.utils.ExcelHolder;
import com.subex.rocps.automation.utils.PSStringUtils;

public class UsageBackoutRequest extends PSAcceptanceTest
{
	protected ExcelReader excelData = new ExcelReader();
	protected Map<String, ArrayList<String>> usageBackoutReqExcelMap = null;
	protected Map<String, String> usageBackoutReqMap = null;
	protected ExcelHolder excelHolderObj = null;
	protected String path;
	protected String workBookName;
	protected String sheetName;
	protected String testCaseName;
	protected int occurence;
	protected String colmHdrs;
	protected int colSize;
	protected int index;
	protected String description;
	protected String clientPartition;
	PSGenericHelper psGenericHelper = new PSGenericHelper();
	PSStringUtils psStringUtils = new PSStringUtils();
	PSActionImpl psActionImpl = new PSActionImpl();
	PSDataComponentHelper psDataComponentHelper = new PSDataComponentHelper();
	UsageBackoutReqActImpl usageBackoutReqActImpl = new UsageBackoutReqActImpl();

	/**
	 * Constructor : Initializing the excel without occurrence
	 * 
	 * @param path
	 * @param workBookName
	 * @param sheetName
	 * @param testCaseName
	 * @throws Exception
	 */
	public UsageBackoutRequest( String path, String workBookName, String sheetName, String testCaseName ) throws Exception
	{

		this.path = path;
		this.workBookName = workBookName;
		this.sheetName = sheetName;
		this.testCaseName = testCaseName;
		usageBackoutReqExcelMap = excelData.readDataByColumn( path, workBookName, sheetName, testCaseName );
		excelHolderObj = new ExcelHolder( usageBackoutReqExcelMap );
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
	public UsageBackoutRequest( String path, String workBookName, String sheetName, String testCaseName, int occurence ) throws Exception
	{
		this.path = path;
		this.workBookName = workBookName;
		this.sheetName = sheetName;
		this.testCaseName = testCaseName;
		this.occurence = occurence;
		usageBackoutReqExcelMap = excelData.readDataByColumn( path, workBookName, sheetName, testCaseName, occurence );
		excelHolderObj = new ExcelHolder( usageBackoutReqExcelMap );
		colSize = excelHolderObj.totalColumns();
	}

	/*
	 * This method is for initialize variable
	 */
	private void initializeVariable( Map<String, String> map ) throws Exception
	{
		clientPartition = ExcelHolder.getKey( map, "Partition" );
		description = ExcelHolder.getKey( map, "Description" );

	}

	/*
	 * This method is for 'Usage Backout Request' screen common method
	 */
	private void usageBackoutReqScreen() throws Exception
	{
		NavigationHelper.navigateToScreen( "Usage Backout Request" );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		usageBackoutReqMap = excelHolderObj.dataMap( index );
		ButtonHelper.click( "SearchButton" );
		psGenericHelper.waitforHeaderElement( "File Collection" );
	}

	/*
	 * This method is for 'Usage Backout Request' screen column validation
	 */
	public void usageBackoutReqColumsValidation() throws Exception
	{
		try
		{
			for ( index = 0; index < colSize; index++ )
			{
				usageBackoutReqScreen();
				colmHdrs = ExcelHolder.getKey( usageBackoutReqMap, "SearchScreenColumns" );
				psGenericHelper.screenColumnValidation( "File Collection", colmHdrs, "Usage Backout Request" );
			}
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}

	}

	/*
	 * This method is for 'Usage Backout Request' creation
	 */
	public void usgBackoutReqCreation() throws Exception
	{
		try
		{
			for ( index = 0; index < colSize; index++ )
			{
				usageBackoutReqScreen();
				initializeVariable( usageBackoutReqMap );
				boolean isUsgBackReqPresent = psGenericHelper.isDataPresent( description, "Description" );
				if ( !isUsgBackReqPresent )
				{
					usageBackoutReqActImpl.clickNewAction( clientPartition );
					UsgBackoutReqDetailImpl usBackoutReqDetailImpl = new UsgBackoutReqDetailImpl( usageBackoutReqMap );
					usBackoutReqDetailImpl.configUsgBackoutRequest();
					Log4jHelper.logInfo( "'Usage Backout Request' is successfully created with ' description '" + description );
				}
				else
					Log4jHelper.logInfo( "'Usage Backout Request' is already avilable with ' description'" + description );

			}
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}

	}

	/*
	 * This method is for 'Usage Backout Request' scheule
	 */
	public void usgBackoutReqSchedule() throws Exception
	{
		try
		{
			for ( index = 0; index < colSize; index++ )
			{
				usageBackoutReqScreen();
				initializeVariable( usageBackoutReqMap );
				boolean isUsgBackReqPresent = psGenericHelper.isDataPresent( description, "Description" );
				if ( !isUsgBackReqPresent )
				{
					usageBackoutReqActImpl.clickNewAction( clientPartition );
					UsgBackoutReqDetailImpl usBackoutReqDetailImpl = new UsgBackoutReqDetailImpl( usageBackoutReqMap );
					usBackoutReqDetailImpl.configUsgBackoutRequest();
					scheduleRequestAction( description );
					int row = getDescriptionRow( description );
					verifyTaskStatus();
					psDataComponentHelper.waitForTaskCompletion( row, "Status" );
					Log4jHelper.logInfo( "'Usage Backout Request' is successfully schedule with ' description '" + description );
				}
				else
				{
					Log4jHelper.logInfo( "'Usage Backout Request' is availabe with the description name:- " + description );
					int row = getDescriptionRow( description );
					String currentTaskStatus = GridHelper.getCellValue( "SearchGrid", row, "Status" ).trim();
					if ( currentTaskStatus.equals( "Unscheduled" ) )
					{
						scheduleRequestAction( description );
						row = getDescriptionRow( description );
						verifyTaskStatus();
						psDataComponentHelper.waitForTaskCompletion( row, "Status" );
					}
					else
						Log4jHelper.logInfo( "'Usage Backout Request' is already in '" + currentTaskStatus + "' status with the description name:- " + description );

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
	 * This method is for 'Usage Backout Request' View
	 */
	public void usgBackoutReqViewAction() throws Exception
	{
		try
		{
			for ( index = 0; index < colSize; index++ )
			{
				usageBackoutReqScreen();
				initializeVariable( usageBackoutReqMap );
				boolean isUsgBackReqPresent = psGenericHelper.isDataPresent( description, "Description" );
				if ( isUsgBackReqPresent )
				{
					int row = getDescriptionRow( description );
					String currentTaskStatus = GridHelper.getCellValue( "SearchGrid", row, "Status" ).trim();
					assertTrue( !currentTaskStatus.equals( "Unscheduled" ), " Task status should not  be in Unscheduled for view action" );
					GridHelper.clickRow( "SearchGrid", description, "Description" );
					usageBackoutReqActImpl.clickOnAction( "Common Tasks", "View" );
					UsgBackoutReqDetailImpl usBackoutReqDetailImpl = new UsgBackoutReqDetailImpl( usageBackoutReqMap );
					usBackoutReqDetailImpl.viewUsgBackoutRequest();
					Log4jHelper.logInfo( "'Usage Backout Request' is successfully validated for View Action with' description ' " + description );
				}
				else
					Log4jHelper.logInfo( "'Usage Backout Request' is not avilable with ' description'" + description );

			}
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

	/*
	 * This method is for 'Usage Backout Request' View
	 */
	public void usgBackoutReqViewLogValidateAction() throws Exception
	{
		try
		{
			for ( index = 0; index < colSize; index++ )
			{
				usageBackoutReqScreen();
				initializeVariable( usageBackoutReqMap );
				boolean isUsgBackReqPresent = psGenericHelper.isDataPresent( description, "Description" );
				if ( isUsgBackReqPresent )
				{
					int row = getDescriptionRow( description );
					String currentTaskStatus = GridHelper.getCellValue( "SearchGrid", row, "Status" ).trim();
					assertTrue( !currentTaskStatus.equals( "Unscheduled" ), " Task status should not  be in Unscheduled for view action" );
					GridHelper.clickRow( "SearchGrid", description, "Description" );
					PSGenericHelper.waitForParentActionElementTOBeclickable( "Actions" );
					psGenericHelper.validateActionText( "Actions", "View Log" );
					Log4jHelper.logInfo( "'Usage Backout Request' is successfully validated for 'View Log' Action with' description ' " + description );
				}
				else
					Log4jHelper.logInfo( "'Usage Backout Request' is not avilable with ' description'" + description );

			}
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

	/*
	 * This method is for 'Usage Backout Request' edit
	 */
	public void usgBackoutReqEdit() throws Exception
	{
		try
		{
			for ( index = 0; index < colSize; index++ )
			{
				usageBackoutReqScreen();
				initializeVariable( usageBackoutReqMap );
				boolean isUsgBackReqPresent = psGenericHelper.isDataPresent( description, "Description" );
				if ( isUsgBackReqPresent )
				{
					GridHelper.clickRow( "SearchGrid", description, "Description" );
					usageBackoutReqActImpl.clickOnAction( "Common Tasks", "Edit" );
					UsgBackoutReqDetailImpl usBackoutReqDetailImpl = new UsgBackoutReqDetailImpl( usageBackoutReqMap );
					usBackoutReqDetailImpl.modifyUsgBackoutRequest();
					Log4jHelper.logInfo( "'Usage Backout Request' is successfully updated with ' description '" + description );
				}
				else
					Log4jHelper.logInfo( "'Usage Backout Request' is not avilable with ' description'" + description );

			}
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}

	}

	// Method to schedule Request action
	protected void scheduleRequestAction( String description ) throws Exception
	{
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		GridHelper.clickRow( "searchGrid", getDescriptionRow( description ), "Description" );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		usageBackoutReqActImpl.clickOnScheduleAction( "Actions", "Schedule Request" );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		PopupHelper.waitForPopup( searchScreenWaitSec );
		assertTrue( PopupHelper.isTextPresent( "Task successfully scheduled." ), "Popup message is not matched" );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		ButtonHelper.click( "OKButton" );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		ButtonHelper.click( "SearchButton" );
	}

	// Method: verify the  task status in Task search
	private void verifyTaskStatus() throws Exception
	{
		PSTaskSearchHelper psTaskSearchHelper = new PSTaskSearchHelper();
		psTaskSearchHelper.psVerifyTaskStatus( path, workBookName, sheetName, "UsgBackoutReqTaskStatus", 1 );

	}

	// Method to get row value of Usage Backout Request
	protected int getDescriptionRow( String description ) throws Exception
	{

		int row = GridHelper.getRowCount( "searchGrid" );
		boolean rowVal = false;
		if ( row > 0 )
		{
			for ( int i = 0; i < row; i++ )
			{
				String cellValue = GridHelper.getCellValue( "searchGrid", i + 1, "Description" );
				GenericHelper.waitForLoadmask( searchScreenWaitSec );
				rowVal = cellValue.equals( description );
				if ( rowVal )
					return i + 1;
			}
			return 0;
		}
		return 0;
	}
}
