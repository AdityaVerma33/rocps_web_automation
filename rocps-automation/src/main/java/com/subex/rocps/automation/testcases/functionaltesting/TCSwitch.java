package com.subex.rocps.automation.testcases.functionaltesting;

import com.subex.automation.helpers.application.screens.ElementCreateHelper;
import com.subex.automation.helpers.util.FailureHelper;
import com.subex.rocps.automation.helpers.application.networkConfiguraiton.Switch;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;

public class TCSwitch extends PSAcceptanceTest
{
	String path = System.getProperty( "user.dir" ) + "\\src\\main\\resources\\";
	String workBookName = "FunctionalTestCases.xlsx";
	String sheetName = "Switch";

	@org.testng.annotations.Test( priority = 1, description = "element creation for non matching", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void createElementNonMatching() throws Exception
	{
		try
		{
			ElementCreateHelper eleObj = new ElementCreateHelper();
			eleObj.createElement( path, workBookName, "Elements", "Elements NonMatching", 1 );
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 2, description = "switch creation", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void switchCreation() throws Exception
	{
		try
		{
			Switch switchObj = new Switch( path, workBookName, sheetName, "Switch" );
			switchObj.configureSwitch();
		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 3, description = "switch additional creation", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void switchAdditionalCreation() throws Exception
	{
		try
		{
			Switch switchObj = new Switch( path, workBookName, sheetName, "SwitchAdditional" );
			switchObj.configureSwitch();
		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 4, description = "switch - HomeCarriercreation", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void switchHCCreation() throws Exception
	{
		try
		{
			Switch switchObj = new Switch( path, workBookName, sheetName, "Switch HomeCarrier" );
			switchObj.configureSwitch();
		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 5, description = "switch deletion" )
	public void switchDelete() throws Exception
	{
		try
		{
			Switch switchDelObj = new Switch( path, workBookName, sheetName, "SwitchDelete" );
			switchDelObj.switchDelete();
		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 6, description = "switch un delete" )
	public void switchUnDelete() throws Exception
	{
		try
		{
			Switch switchUnDelObj = new Switch( path, workBookName, sheetName, "SwitchUnDelete" );
			switchUnDelObj.switchUnDelete();
		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 7, description = "switch search Screen col validation" )
	public void switchColVal() throws Exception
	{
		try
		{
			Switch switchColValObj = new Switch( path, workBookName, sheetName, "SwitchSearchScreencolVal" );
			switchColValObj.searchScreenColumnsValidation();
		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 8, description = "switch Element Association", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void switchElementSet() throws Exception
	{
		try
		{
			Switch switchColValObj = new Switch( path, workBookName, sheetName, "SwitchElementsAssociation" );
			switchColValObj.configureSwitch();
		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 9, description = "switch RuleStringSet", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void switchRuleStringSet() throws Exception
	{
		try
		{
			Switch switchColValObj = new Switch( path, workBookName, sheetName, "SwitchRuleStringSet" );
			switchColValObj.configureSwitch();
		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 10, description = "switch RuleStringSet" )
	public void editswitchRuleStringSet() throws Exception
	{
		try
		{
			Switch switchColValObj = new Switch( path, workBookName, sheetName, "EditSwitchRuleStringSet" );
			switchColValObj.editSwitch();
		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}
	/*
		@org.testng.annotations.Test( priority = 11, description = "switch Map Tairff" )
	
		public void switchTariffMapping() throws Exception
		{
			try
			{
				Switch switchColValObj = new Switch( path, workBookName, sheetName, "SwitchMapTariff" );
				switchColValObj.switchTariffMapping();
			}
			catch ( Exception e )
			{
				FailureHelper.reportFailure( e );
				throw e;
			}
		}
		*/
}
