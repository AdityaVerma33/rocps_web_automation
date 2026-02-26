package com.subex.rocps.automation.testcases.functionaltesting;

import com.subex.rocps.automation.helpers.application.matchandrate.EventExtraRatingField;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;

import com.subex.automation.helpers.util.FailureHelper;

public class TCEventExtraRatingField extends PSAcceptanceTest
{
	String path = System.getProperty( "user.dir" ) + "\\src\\main\\resources\\";
	String workBookName = "FunctionalTestCases.xlsx";
	String sheetName = "EventExtraRatingField";

	@org.testng.annotations.Test( priority = 1, description = "Event Extra Rating Field   creation", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void eventRatingFieldCreation() throws Exception
	{
		try
		{
			EventExtraRatingField eveObj = new EventExtraRatingField( path, workBookName, sheetName, "ExtraRatingField", 1 );
			eveObj.eventRatingCreation();
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 2, description = "Edit Event Extra Rating Field  " )
	public void editEventRatingField() throws Exception
	{
		try
		{
			EventExtraRatingField eveObj = new EventExtraRatingField( path, workBookName, sheetName, "EditExtraRatingField", 1 );
			eveObj.editEventRatingCreation();
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 3, description = "Event Extra Rating Field Delete" )
	public void eventRatingFieldDelete() throws Exception
	{
		try
		{
			EventExtraRatingField evedelObj = new EventExtraRatingField( path, workBookName, sheetName, "ExtraRatingField Delete", 1 );
			evedelObj.eventRatingDelete();
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 4, description = "Event Extra Rating Field UnDelete" )
	public void eventRatingFieldUnDelete() throws Exception
	{
		try
		{
			EventExtraRatingField eveUnDelObj = new EventExtraRatingField( path, workBookName, sheetName, "ExtraRatingField UnDelete", 1 );
			eveUnDelObj.eventRatingUnDelete();
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 5, description = "Event Extra Rating Field Search Screen Column validation" )
	public void eventRatingFieldColVal() throws Exception
	{
		try
		{
			EventExtraRatingField eveObj = new EventExtraRatingField( path, workBookName, sheetName, "ExtraRatingSearchScreencolVal", 1 );
			eveObj.searchScreenColumnsValidation();
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

}
