package com.subex.rocps.automation.testcases.functionaltesting;

import com.subex.automation.helpers.util.FailureHelper;
import com.subex.rocps.automation.helpers.application.matchandrate.EventModellingInstance;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;

public class TCEventModellingInst extends PSAcceptanceTest
{

	String path = System.getProperty( "user.dir" ) + "\\src\\main\\resources\\";
	String workBookName = "FunctionalTestCases.xlsx";
	String sheetName = "EventModellingInst";
	
	@org.testng.annotations.Test( priority = 1, description = "event Modelling instance Update script",retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void eventModellingInstanceUpdate() throws Exception
	{
		try
		{
			EventModellingInstance eventundelObj = new EventModellingInstance( path, workBookName, sheetName, "EventModellingInst", 1 );
			eventundelObj.eventModellingInstance();
		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}
	

	
	@org.testng.annotations.Test( priority = 2, description = "event Modelling instance column validation" )
	public void eventModellingInstanceColVal() throws Exception
	{
		try
		{
			EventModellingInstance eventColValObj = new EventModellingInstance( path, workBookName, sheetName, "EventInstSearchScreencolVal", 1 );
			eventColValObj.searchScreenColumnsValidation();
			
		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority=3,description = "Event Modelling Instance Validated create New Inst Button",retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class)
	public void checkCrtInst() throws Exception
	{
		try {
			EventModellingInstance eventMInstHelOb =new EventModellingInstance(path, workBookName, sheetName, "EventModInstChckCrtNewInst");
		    eventMInstHelOb.checkTheCrtNwInstEnableOrNot();
		}
		catch(Exception e)
		{
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	

	@org.testng.annotations.Test( priority=4, description = "EventModellingInstance creation with Normalize event ",retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class)
	public void createEventModellingInstWithNormalize() throws Exception
	{
		try {
			EventModellingInstance eventMInstHelOb =new EventModellingInstance(path, workBookName, sheetName, "EventModInstWithNormalizationEvent");
		    eventMInstHelOb.eventModInstCreation();
		}
		catch(Exception e)
		{
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	/*@org.testng.annotations.Test( priority=7, description = "EventModellingInstance creation without Normalize event ")
	public void createEventModellingInstWithoutNormEvent() throws Exception
	{
		try {
			EventModellingInstance eventMInstHelOb =new EventModellingInstance(path, workBookName, sheetName, "EventModInstWithoutNormalizationEvent");
		    eventMInstHelOb.eventModInstCreation();
		}
		catch(Exception e)
		{
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}*/
	
	@org.testng.annotations.Test( priority = 5, description = "event Modelling instancea deletion" )
	public void eventModellingInstanceDelete() throws Exception
	{
		try
		{
			EventModellingInstance eventdelObj = new EventModellingInstance( path, workBookName, sheetName, "EventModellingInstDelete", 1 );
			eventdelObj.eventModellingInstDelete();
		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}
	
	@org.testng.annotations.Test( priority = 6, description = "event Modelling instance un delete")
	public void eventModellingInstanceUnDelete() throws Exception
	{
		try
		{
			EventModellingInstance eventundelObj = new EventModellingInstance( path, workBookName, sheetName, "EventModellingInstUnDelete", 1 );
			eventundelObj.eventModellingInstUnDelete();
		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}

}
