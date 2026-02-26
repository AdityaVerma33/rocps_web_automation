package com.subex.rocps.automation.testcases.functionaltesting;

import com.subex.rocps.automation.helpers.application.matchandrate.EventExtraRatingField;
import com.subex.rocps.automation.helpers.application.referenceTable.EventLegCodeGroup;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;

import com.subex.automation.helpers.util.FailureHelper;

public class TCEventLegCodeGroup extends PSAcceptanceTest
{
	String path = System.getProperty( "user.dir" ) + "\\src\\main\\resources\\";
	String workBookName = "FunctionalTestCases.xlsx";
	String sheetName = "EventLegCodeGroup";

	@org.testng.annotations.Test( priority = 1, description = "Event Leg Code Group creation", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void eventLegCodeGroupCreation() throws Exception
	{
		try
		{
			EventLegCodeGroup eveObj = new EventLegCodeGroup( path, workBookName, sheetName, "LegCodeGrop", 1 );
			eveObj.eventLegCodeGroupCreation();
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 2, description = "Edit- Event Leg Code Group", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void editEventLegCodeGroup() throws Exception
	{
		try
		{
			EventLegCodeGroup eveObj = new EventLegCodeGroup( path, workBookName, sheetName, "EditLegCodeGrop", 1 );
			eveObj.eventLegCodeGroupCreation();
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 3, description = "Event Leg Code Group Delete" )
	public void eventLegCodeGroupDelete() throws Exception
	{
		try
		{
			EventLegCodeGroup evedelObj = new EventLegCodeGroup( path, workBookName, sheetName, "LegCodeGrop Delete", 1 );
			evedelObj.eventLegCodeGroupDelete();
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 4, description = "Event Leg Code Group UnDelete" )
	public void eventLegCodeGroupUnDelete() throws Exception
	{
		try
		{
			EventLegCodeGroup eveUnDelObj = new EventLegCodeGroup( path, workBookName, sheetName, "LegCodeGrop UnDelete", 1 );
			eveUnDelObj.eventLegCodeGroupUnDelete();
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 5, description = "Event Leg Code Group search screen column validation" )
	public void eventLegCodeGroupColVal() throws Exception
	{
		try
		{
			EventLegCodeGroup eveColValObj = new EventLegCodeGroup( path, workBookName, sheetName, "LegCodeGrpSearchScreencolVal", 1 );
			eveColValObj.searchScreenColumnsValidation();
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}
}
