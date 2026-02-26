package com.subex.rocps.automation.helpers.application.approvalworkflows;

import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Map;

import com.subex.rocps.automation.helpers.application.approvalworkflows.approvalworkflows.ApprovalWorkFlowActionImpl;
import com.subex.rocps.automation.helpers.application.approvalworkflows.approvalworkflows.ApprovalWorkFlowDetailImpl;
import com.subex.rocps.automation.helpers.application.genericHelpers.PSGenericHelper;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;
import com.subex.rocps.automation.utils.ExcelHolder;
import com.subex.rocps.automation.utils.PSStringUtils;

import com.subex.automation.helpers.application.NavigationHelper;
import com.subex.automation.helpers.component.ButtonHelper;
import com.subex.automation.helpers.component.GenericHelper;
import com.subex.automation.helpers.component.GridHelper;
import com.subex.automation.helpers.component.TextBoxHelper;
import com.subex.automation.helpers.file.ExcelReader;
import com.subex.automation.helpers.report.Log4jHelper;
import com.subex.automation.helpers.util.FailureHelper;

public class ApprovalWorkFlows extends PSAcceptanceTest
{
	protected ExcelReader excelData = null;
	protected Map<String, ArrayList<String>> wrkflowExcel = null;
	protected Map<String, String> wrkflowMap = null;
	protected ExcelHolder excelHolderObj = null;
	protected String path;
	protected String workBookName;
	protected String sheetName;
	protected String testCaseName;
	protected int paramVal;
	protected int colSize;
	protected String clientPartition;
	protected String name;
	protected String approvalName;
	String duration;
	String durationDays;
	String approvalTeam;
	String deafultApprovalUser;
	String notifyEmail;
	String teams;
	String users;
	String allowObjCreator;
	String allowNotification;
	protected PSGenericHelper genericHelperObj = new PSGenericHelper();
	protected String regex = new PSStringUtils().regexFirstLevelDelimeter();
	ApprovalWorkFlowActionImpl workflowActionObj = new ApprovalWorkFlowActionImpl();
	ApprovalWorkFlowDetailImpl workflowDetailObj = new ApprovalWorkFlowDetailImpl();

	/*
	 * Constructor for initializing excel Identifying the column size from the
	 * map passed
	 */
	@Test
	public ApprovalWorkFlows( String path, String workBookName, String sheetName, String testCaseName ) throws Exception
	{
		this.path = path;
		this.workBookName = workBookName;
		this.sheetName = sheetName;
		this.testCaseName = testCaseName;
		excelData = new ExcelReader();
		wrkflowExcel = excelData.readDataByColumn( this.path, this.workBookName, this.sheetName, this.testCaseName );
		excelHolderObj = new ExcelHolder( wrkflowExcel );
		colSize = excelHolderObj.totalColumns();
	}

	/*
	 * Overloaded constructor for reading data from excel if test case name
	 * appears more than once
	 * 
	 * @Param occurance : Test case instance in excel sheet Constructor for
	 * initializing excel Identifying the column size from the map
	 */
	public ApprovalWorkFlows( String path, String workBookName, String sheetName, String testCaseName, int occurance ) throws Exception
	{
		this.path = path;
		this.workBookName = workBookName;
		this.sheetName = sheetName;
		this.testCaseName = testCaseName;
		excelData = new ExcelReader();
		wrkflowExcel = excelData.readDataByColumn( this.path, this.workBookName, this.sheetName, this.testCaseName, occurance );
		excelHolderObj = new ExcelHolder( wrkflowExcel );
		colSize = excelHolderObj.totalColumns();
	}

	/*
	 * Configuring the approvalWorkflow
	 * 
	 */
	public void approvalWorkflowCreation() throws Exception
	{
		try
		{
			NavigationHelper.navigateToScreen( "Approval Workflow" );
			for ( paramVal = 0; paramVal < colSize; paramVal++ )
			{

				wrkflowMap = excelHolderObj.dataMap( paramVal );

				initializeVariables( wrkflowMap );
				boolean isWorkflowPresent = genericHelperObj.isGridTextValuePresent( "pawfWorkflowName", name, "Name" );

				if ( !isWorkflowPresent )
				{
					workflowActionObj.clickNewAction( clientPartition );
					newApprovalWorkflows();
					workflowDetailObj.saveApprovalWorkflow( name );
					Log4jHelper.logInfo( "Approval Workflows is created successfully with name " + name );
				}
				else
					Log4jHelper.logInfo( "Approval Workflows is available " + name );
			}

		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

	/*
	 * This method is to create new Approval Workflows
	 */
	protected void newApprovalWorkflows() throws Exception
	{
		String[] approvalNameArr = approvalName.split( regex, -1 );
		String[] durationArr = duration.split( regex, -1 );
		String[] durationDaysArr = durationDays.split( regex, -1 );
		String[] approvalTeamArr = approvalTeam.split( regex, -1 );
		String[] deafultApprovalUserArr = deafultApprovalUser.split( regex, -1 );
		String[] notifyEmailArr = notifyEmail.split( regex, -1 );
		String[] teamsArr = teams.split( regex, -1 );
		String[] usersArr = users.split( regex, -1 );

		TextBoxHelper.type( "pawfWorkflowName", name );
		for ( int j = 0; j < approvalNameArr.length; j++ )
		{
			int row = GridHelper.getRowNumber( "approvalStagesGrid", approvalNameArr[j], "Approval Stage Name" );
			if(row == 0)
			{
			workflowActionObj.addApprovalStage();
			GenericHelper.waitForLoadmask();
			workflowDetailObj.basicDetails( approvalNameArr[j], durationArr[j], durationDaysArr[j], approvalTeamArr[j], deafultApprovalUserArr[j] );
			workflowDetailObj.notificationDetails( notifyEmailArr[j], teamsArr[j], usersArr[j] );
			ButtonHelper.click( "approvalStageDetail.OK" );
			GenericHelper.waitForLoadmask();
			}else
			{
				assertEquals( GridHelper.getCellValue( "approvalStagesGrid", row, "Approval Stage Name" ), approvalNameArr[j] );
				assertEquals( GridHelper.getCellValue( "approvalStagesGrid", row, "Default Approval User" ), deafultApprovalUserArr[j] );				
			}
		}
		workflowDetailObj.advancedConfigurations( allowObjCreator, allowNotification );
	}

	public void editapprovalWorkflow() throws Exception
	{
		try
		{
			NavigationHelper.navigateToScreen( "Approval Workflow" );
			for ( paramVal = 0; paramVal < colSize; paramVal++ )
			{

				wrkflowMap = excelHolderObj.dataMap( paramVal );

				initializeVariables( wrkflowMap );
				boolean isWorkflowPresent = genericHelperObj.isGridTextValuePresent( "pawfWorkflowName", name, "Name" );

				if ( isWorkflowPresent )
				{
					int row = GridHelper.getRowNumber( "SearchGrid", name, "Name" );
					NavigationHelper.navigateToEdit( "SearchGrid", row );
					assertEquals( NavigationHelper.getScreenTitle(), "Edit Approval Workflow" );
					newApprovalWorkflows();
					workflowDetailObj.saveApprovalWorkflow( name );
					Log4jHelper.logInfo( "Approval Workflows is edited successfully with name " + name );
				}
				else
					Log4jHelper.logInfo( "Approval Workflows is not available" + name );
			}
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}

	}

	public void changeApprovalStatus() throws Exception
	{
		assertTrue( GridHelper.isValuePresent( "SearchGrid", "Draft", "Status" ) );
		GridHelper.clickRow( "SearchGrid", name, "Name" );
		workflowActionObj.changeStatusAction();
		assertTrue( GridHelper.isValuePresent( "SearchGrid", "Accepted", "Status" ) );
		Log4jHelper.logInfo( "Approval Workflows status has been modified from Draft to Accepted" );
	}


	/*
	 * This method is to initialize instance variables
	 */
	protected void initializeVariables( Map<String, String> map ) throws Exception
	{

		clientPartition = ExcelHolder.getKey( map, "Partition" );
		name = ExcelHolder.getKey( map, "Name" );
		approvalName = ExcelHolder.getKey( map, "Approval Name" );
		duration = ExcelHolder.getKey( map, "Duration" );
		durationDays = ExcelHolder.getKey( map, "Duration Days" );
		approvalTeam = ExcelHolder.getKey( map, "Approval Team" );
		deafultApprovalUser = ExcelHolder.getKey( map, "Default ApprovalUser" );
		notifyEmail = ExcelHolder.getKey( map, "Notify Email" );
		teams = ExcelHolder.getKey( map, "Teams" );
		users = ExcelHolder.getKey( map, "Users" );
		allowObjCreator = ExcelHolder.getKey( map, "Allow ObjCreator" );
		allowNotification = ExcelHolder.getKey( map, "Allow Notification" );
	}

	/*
	 * This method is to validate search screen columns
	 */
	public void searchScreenColumnsValidation() throws Exception
	{
		NavigationHelper.navigateToScreen( "Approval Workflow" );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		for ( paramVal = 0; paramVal < colSize; paramVal++ )
		{
			wrkflowMap = excelHolderObj.dataMap( paramVal );
			String searchScreenColumns = ExcelHolder.getKey( wrkflowMap, "SearchScreenColumns" );
			ArrayList<String> excelColumnNames = new ArrayList<String>();
			PSStringUtils strObj = new PSStringUtils();
			String[] searchGridColumnsArr = strObj.stringSplitFirstLevel( searchScreenColumns );
			for ( int col = 0; col < searchGridColumnsArr.length; col++ )
			{
				excelColumnNames.add( searchGridColumnsArr[col] );
			}
			genericHelperObj.totalColumns( excelColumnNames );
		}

	}

	/*
	 * This method is for approvalWorkflow deletion
	 */
	public void approvalWorkflowDelete() throws Exception
	{
		NavigationHelper.navigateToScreen( "Approval Workflow" );
		for ( paramVal = 0; paramVal < colSize; paramVal++ )
		{

			wrkflowMap = excelHolderObj.dataMap( paramVal );
			clientPartition = ExcelHolder.getKey( wrkflowMap, "Partition" );
			name = ExcelHolder.getKey( wrkflowMap, "Name" );

			genericHelperObj.selectPartitionFilter( clientPartition, "Non Deleted Items" );

			boolean isWorkflowPresent = genericHelperObj.isGridTextValuePresent( "pawfWorkflowName", name, "Name" );

			if ( isWorkflowPresent )
			{
				workflowActionObj.clickDeleteAction( name );

				genericHelperObj.selectPartitionFilter( clientPartition, "Deleted Items" );
				GenericHelper.waitForLoadmask();
				assertTrue( GridHelper.isValuePresent( "SearchGrid", name, "Name" ), name );
				Log4jHelper.logInfo( "Approval Workflows is deleted successfully :" + name );

			}
			else
			{
				Log4jHelper.logInfo( "Approval Workflows is not available with :" + name );
			}

		}
	}

	/*
	 * This method is for approvalWorkflow un delete
	 */
	public void approvalWorkflowUnDelete() throws Exception
	{

		NavigationHelper.navigateToScreen( "Approval Workflow" );
		for ( paramVal = 0; paramVal < colSize; paramVal++ )
		{

			wrkflowMap = excelHolderObj.dataMap( paramVal );
			clientPartition = ExcelHolder.getKey( wrkflowMap, "Partition" );
			name = ExcelHolder.getKey( wrkflowMap, "Name" );

			genericHelperObj.selectPartitionFilter( clientPartition, "Deleted Items" );

			boolean isWorkflowPresent = genericHelperObj.isGridTextValuePresent( "pawfWorkflowName", name, "Name" );

			if ( isWorkflowPresent )
			{
				workflowActionObj.clickUnDeleteACtion( name );
				genericHelperObj.selectPartitionFilter( clientPartition, "Non Deleted Items" );
				assertTrue( GridHelper.isValuePresent( "SearchGrid", name, "Name" ), name );
				Log4jHelper.logInfo( "Approval Workflows is un deleted successfully :" + name );

			}
			else
			{
				Log4jHelper.logInfo( "Approval Workflows is not available with :" + name );
			}

		}
	}
}
