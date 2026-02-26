package com.subex.rocps.automation.testcases.functionaltesting;

import com.subex.rocps.automation.helpers.application.amountthreshold.AmountThreshold;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;

import com.subex.automation.helpers.application.screens.UserHelper;
import com.subex.automation.helpers.util.FailureHelper;

public class TCAmountThreshold extends PSAcceptanceTest
{
	String path = System.getProperty( "user.dir" ) + "\\src\\main\\resources\\";
	String workBookName = "FunctionalTestCases.xlsx";
	String sheetName = "AmountThreshold";
	
	@org.testng.annotations.Test( priority = 1, description = "AmountThreshold  creation" )
	public void amountThresholdSingleTabCreation() throws Exception
	{
		
		try
		{
			AmountThreshold amtObj = new AmountThreshold(path, workBookName, sheetName, "AmtThreshold", 1);
			amtObj.amountThresholdSingleCurrencyCreation();
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}
	
	@org.testng.annotations.Test( priority = 2, description = "AmountThreshold  - Multi Currency creation" )
	public void amountThresholdMultiTabCreation() throws Exception
	{
		
		try
		{
			AmountThreshold amtObj = new AmountThreshold(path, workBookName, sheetName, "AmtThreshold multipleCurrency", 1);
			amtObj.amountThresholdMultiCurrencyCreation();
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}
	
	
	@org.testng.annotations.Test( priority = 3, description = "AmountThreshold  - Copy currency creation" )
	public void amountThresholdCreation() throws Exception
	{
		
		try
		{
			AmountThreshold amtObj = new AmountThreshold(path, workBookName, sheetName, "AmtThreshold copyCurrency", 1);
			amtObj.amountThresholdCopyCurrencyCreation();
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	} 
	
	@org.testng.annotations.Test( priority = 4, description = "AmountThreshold  - Deletion" )
	public void amountThresholdDelete() throws Exception
	{
		
		try
		{
			AmountThreshold amtObj = new AmountThreshold(path, workBookName, sheetName, "AmtThreshold Delete", 1);
			amtObj.amountThresholdDelete();
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}
	
	@org.testng.annotations.Test( priority = 5, description = "AmountThreshold  - Un Deletion" )
	public void amountThresholdUnDelete() throws Exception
	{
		
		try
		{
			AmountThreshold amtObj = new AmountThreshold(path, workBookName, sheetName, "AmtThreshold UnDelete", 1);
			amtObj.amountThresholdUnDelete();
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}
}
