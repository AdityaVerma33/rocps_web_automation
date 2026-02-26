package com.subex.rocps.automation.testcases.functionaltesting;

import com.subex.automation.helpers.application.screens.TaskControllerHelper;
import com.subex.automation.helpers.util.FailureHelper;
import com.subex.rocps.automation.helpers.application.admin.alertEvent.AlertsEvents;
import com.subex.rocps.automation.helpers.application.admin.alertGroup.AlertGroup;
import com.subex.rocps.automation.helpers.application.alerts.AlertValidation;
import com.subex.rocps.automation.helpers.application.deal.Deal;
import com.subex.rocps.automation.helpers.application.partnerConfiguration.Account;
import com.subex.rocps.automation.helpers.application.referenceTable.AlertEventGroup;
import com.subex.rocps.automation.helpers.application.referenceTable.AlertEventStream;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;

public class TCAlertEvent extends PSAcceptanceTest
{

	String path = System.getProperty( "user.dir" ) + "\\src\\main\\resources\\";
	String workBookName = "FunctionalTestCases.xlsx";
	String sheetName = "AlertEvents";

	//@org.testng.annotations.Test( priority = 1, description = "Task controller capabilities" )
	public void TaskCntrollerCapabilities() throws Exception
	{
		{
			try
			{

				TaskControllerHelper taskObj = new TaskControllerHelper();
				taskObj.setTaskControllerCapability( path, workBookName, "AlertStream", "TCCapability", 1 );

			}
			catch ( Exception e )
			{
				FailureHelper.setErrorMessage( e );
				throw e;
			}
		}
	}

	@org.testng.annotations.Test( priority = 2, description = "Configuring Alert Event group in Reference table. ", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void configureAlertEventGroup() throws Exception
	{
		try
		{
			AlertEventGroup grpObj = new AlertEventGroup( path, workBookName, "AlertEventGroup", "AlertGroup_01" );
			grpObj.alertEventGroupConfiguration();
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 3, description = "Configuring Alert Event Stream in Reference Table", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void configureAlertEventStream() throws Exception
	{
		try
		{
			AlertEventStream grpObj = new AlertEventStream( path, workBookName, "AlertEventStream", "AlertStream_01" );
			grpObj.alertEventStreamConfiguration();
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 4, description = "Configuring  Alert Group for Account", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void alertGroupCreationForAccount() throws Exception
	{
		try
		{
			AlertGroup alertObj = new AlertGroup( path, workBookName, "AlertGroup", "AlertGroup1" );
			alertObj.alertGroupCreation();
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 5, description = "Configuring Entity Added Alert Event", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void entityAddedAlertEvent() throws Exception
	{
		try
		{
			AlertsEvents alertObj = new AlertsEvents( path, workBookName, sheetName, "TC_01_EntityAdded" );
			alertObj.alertCreation();
			/*Account accObj = new Account(path, workBookName, sheetName, "TC_01_Account");
			accObj.accountCreation();*/
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 6, description = "Configuring Entity Deleted Alert Event", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void entityDeletedAlertEvent() throws Exception
	{
		try
		{
			AlertsEvents alertObj = new AlertsEvents( path, workBookName, sheetName, "TC_02_EntityDeleted" );
			alertObj.alertCreation();
			Account accObj = new Account( path, workBookName, sheetName, "TC_02_Account" );
			/*accObj.accountCreation();
			accObj.accountDelete();*/
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 7, description = "Configuring Field Change Alert Event", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void fieldChangeAlertEvent() throws Exception
	{
		try
		{
			AlertsEvents alertObj = new AlertsEvents( path, workBookName, sheetName, "TC_03_FieldChange" );
			alertObj.alertCreation();
			//Account accObj = new Account(path, workBookName, sheetName, "TC_03_AccountEdit");
			//accObj.editAccountCreation();

		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 8, description = "Configuring Query Based Alert Event for Account", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void queryBasedAlertEventForAccount() throws Exception
	{
		try
		{
			//AlertValidation valObj = new AlertValidation(path, workBookName, "AlertInstance", "Instance_01");
			//valObj.alertInsatnceValdiation();
			AlertsEvents alertObj = new AlertsEvents( path, workBookName, sheetName, "TC_04_QueryBased_Account" );
			alertObj.alertCreation();
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 9, description = "Configuring Alert Group for Band", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void alertGroupCreationForBand() throws Exception
	{
		try
		{
			AlertGroup alertObj = new AlertGroup( path, workBookName, "AlertGroup", "AlertGroup2" );
			alertObj.alertGroupCreation();
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 10, description = "Configuring Query Based Alert Event for Band", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void queryBasedAlertEventForBand() throws Exception
	{
		try
		{

			AlertsEvents alertObj = new AlertsEvents( path, workBookName, sheetName, "TC_05_QueryBased_Band" );
			alertObj.alertCreation();
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

}
