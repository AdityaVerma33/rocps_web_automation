package com.subex.rocps.automation.helpers.application.tariffs.autoratesheetconfig;

import java.util.Map;

import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;
import com.subex.rocps.automation.utils.PSStringUtils;

import com.subex.automation.helpers.application.NavigationHelper;
import com.subex.automation.helpers.component.ButtonHelper;
import com.subex.automation.helpers.component.ElementHelper;
import com.subex.automation.helpers.component.EntityComboHelper;
import com.subex.automation.helpers.component.GenericHelper;
import com.subex.automation.helpers.component.GridHelper;
import com.subex.automation.helpers.component.PopupHelper;
import com.subex.automation.helpers.component.SearchGridHelper;
import com.subex.automation.helpers.component.TabHelper;
import com.subex.automation.helpers.component.TextBoxHelper;
import com.subex.automation.helpers.data.ValidationHelper;
import com.subex.automation.helpers.report.Log4jHelper;

public class AutoRateSheetConfigDetailImpl extends PSAcceptanceTest
{

	/*
	 * this method is for basic details of auto rate sheet config
	 */
	public void newAutoRateSheetConfig( String name, String tariff ) throws Exception
	{
		String title = "Tariff Search";
		TextBoxHelper.type( "PS_Detail_autoRateSheet_name_txtID", name );
		EntityComboHelper.selectUsingSearchTextBox( "PSPopUp_emrSvcTffEntSrchId", title, "PSPopUp_emrSvcTffNameTxtId", tariff, "Tariff Name" );

	}
	
	
	/*
	 * this method is for emailDetails
	 */
	public void emailDetails( String path, String workBook, String sheetName, String testCaseName, String emailId, String emailSubject, String maxsize, String attachment, String outputdir, Map<String, String> map, String rateEffective, String templateName ) throws Exception
	{
		String regex = new PSStringUtils().regexFirstLevelDelimeter();
		String[] emailIdArr = emailId.split( regex, -1 );
		String[] emailsubjectArr = emailSubject.split( regex, -1 );
		String[] maxSizeArr = maxsize.split( regex, -1 );
		String[] attachmentArr = attachment.split( regex, -1 );
		String[] outputdirArr = outputdir.split( regex, -1 );
		String[] rateEffectiveArr = rateEffective.split( regex, -1 );
		for ( int row = 0; row < rateEffectiveArr.length; row++ )
		{
			ButtonHelper.click( "PS_Detail_autoRateSheet_email_addBtn" );
			GenericHelper.waitForLoadmask( detailScreenWaitSec );
			if ( ValidationHelper.isNotEmpty( emailId ) && ValidationHelper.isNotEmpty( emailIdArr[row] ) )
				GridHelper.updateGridTextBox( "PS_Detail_autoRateSheet_emailInfo_gridID", "PS_Detail_autoRateSheet_emailID_txtID", row + 1, "Email Id", emailIdArr[row] );
			if ( ValidationHelper.isNotEmpty( emailSubject ) && ValidationHelper.isNotEmpty( emailsubjectArr[row] ) )
				GridHelper.updateGridTextBox( "PS_Detail_autoRateSheet_emailInfo_gridID", "PS_Detail_autoRateSheet_emailSubject_txtID", row + 1, "Email Subject", emailsubjectArr[row] );
			if ( ValidationHelper.isNotEmpty( maxsize ) && ValidationHelper.isNotEmpty( maxSizeArr[row] ) )
				GridHelper.updateGridTextBox( "PS_Detail_autoRateSheet_emailInfo_gridID", "PS_Detail_autoRateSheet_maxsize_txtID", row + 1, "Max  Attachment  Size( Kb)", maxSizeArr[row] );
			if ( ValidationHelper.isNotEmpty( attachment ) && ValidationHelper.isNotEmpty( attachmentArr[row] ) )
				GridHelper.updateGridTextBox( "PS_Detail_autoRateSheet_emailInfo_gridID", "PS_Detail_autoRateSheet_attachment_txtID", row + 1, "Attachment  Name", attachmentArr[row] );
			if ( ValidationHelper.isNotEmpty( outputdir ) && ValidationHelper.isNotEmpty( outputdirArr[row] ) )
				GridHelper.updateGridTextBox( "PS_Detail_autoRateSheet_emailInfo_gridID", "PS_Detail_autoRateSheet_outputdir_txtID", row + 1, "Output  Directory", outputdirArr[row] );

			ButtonHelper.click( "PS_Detail_autoRateSheet_configRule_XPath" );
			GenericHelper.waitForLoadmask( detailScreenWaitSec );
			if(!templateName.isEmpty())
			{
				popupValidation();
				assertEquals( NavigationHelper.getScreenTitle(), "Rate Sheet Import Template Search" );
				SearchGridHelper.gridFilterSearchWithTextBox( "popupWindow", "psitName", templateName, "Template Name" );
				GridHelper.clickRow( "SearchGrid", 1, "Template Name" );
				ButtonHelper.click( "OK_TRT_Button" );
				GenericHelper.waitForLoadmask( detailScreenWaitSec );
			}
			assertEquals( NavigationHelper.getScreenTitle(), "Config Rule" );
			ConfigRuleImpl configruleObj = new ConfigRuleImpl();
			configruleObj.excelTestDataInitialize( path, workBook, sheetName, testCaseName, row );
			//configruleObj.configRuleDetail( row );

		}
	}

	/*
	 * This method is for poll Directory
	 */
	public void pollDirectory( String path, String workBook, String sheetName, String testCaseName, String expressionName, String expression, String matchString, String matchCondition ,Map<String, String> map,String templateName) throws Exception

	{
		String regex = new PSStringUtils().regexFirstLevelDelimeter();
		String[] expressionNameArr = expressionName.split( regex, -1 );
		String[] expressionArr = expression.split( regex, -1 );
		String[] matchStringArr = matchString.split( regex, -1 );
		String[] matchConditionArr = matchCondition.split( regex, -1 );

		if ( !expressionName.isEmpty() && !expression.isEmpty() )
		{
			TabHelper.gotoTab( "//*[text()='Poll Directory']" );
			for ( int i = 0; i < expressionNameArr.length; i++ )
			{
				ButtonHelper.click( "PS_Detail_autoRateSheet_pollDir_addbtn" );
				GenericHelper.waitForLoadmask();
				assertEquals( NavigationHelper.getScreenTitle(), "Auto RateSheet Import Form Poll Dir" );
				TextBoxHelper.type( "PS_Detail_autoRateSheet_pollDir_expName_txtID", expressionNameArr[i] );
				
				pollDirectoryMatchconfig( matchStringArr[i], matchConditionArr[i] );
				assertEquals( TextBoxHelper.getValue( "PS_Detail_autoRateSheet_pollDir_exp_txtID" ), expressionArr[i] );
				ButtonHelper.click( "OKButton" );
				GenericHelper.waitForLoadmask( detailScreenWaitSec );
				
				ButtonHelper.click( "PS_Detail_autoRateSheet_configRule_XPath" );
				GenericHelper.waitForLoadmask( detailScreenWaitSec );
				if(!templateName.isEmpty())
				{
					popupValidation();
					assertEquals( NavigationHelper.getScreenTitle(), "Rate Sheet Import Template Search" );
					SearchGridHelper.gridFilterSearchWithTextBox( "popupWindow", "psitName", templateName, "Template Name" );
					GridHelper.clickRow( "SearchGrid", 1, "Template Name" );
					ButtonHelper.click( "OK_TRT_Button" );
					GenericHelper.waitForLoadmask( detailScreenWaitSec );
				}
				ConfigRuleImpl configObj = new ConfigRuleImpl();
				configObj.excelTestDataInitialize( path, workBook, sheetName, testCaseName, i );
				
			}
		}
	}

	/*
	 * This method is for poll directory match string
	 */
	private void pollDirectoryMatchconfig( String matchStringArr, String matchConditionArr ) throws Exception
	{
		String[] matchStringAr = matchStringArr.split( secondLevelDelimiter );
		String[] matchConditionAr = matchConditionArr.split( secondLevelDelimiter );

		for ( int j = 0; j < matchStringAr.length; j++ )
		{
			ButtonHelper.click( "gridToolbar.Add" );
			GridHelper.updateGridTextBox( "autoRateSheetExpCondGrid", "PS_Detail_autoRateSheet_pollDir_matchString_txtID", j+1, "Match String", matchStringAr[j] );
			GridHelper.updateGridTextBox( "autoRateSheetExpCondGrid", "PS_Detail_autoRateSheet_pollDir_matchCond_txtID", j+1, "Match Condition", matchConditionAr[j] );
		}
	}

	/*
	 * This method is to save Auto rate sheet config
	 */
	public void saveAutoRateSheetConfig( String tariff ) throws Exception
	{
		ButtonHelper.clickIfEnabled( "PS_Detail_autoRateSheet_save_btnID" );
		GenericHelper.waitForLoadmask( detailScreenWaitSec );
		assertTrue( GridHelper.isValuePresent( "SearchGrid", tariff, "Tariff" ) );
		Log4jHelper.logInfo( "Auto Rate sheet Config is saved successfully for " + tariff );
	}
	
	public void editAutoRateSheetConfig(String tariff) throws Exception
	{
		NavigationHelper.navigateToAction( "Common Tasks", "Edit" );
		assertEquals( NavigationHelper.getScreenTitle(), "Edit Auto Rate Sheet Config" );
		EntityComboHelper.selectUsingSearchTextBox( "PSPopUp_emrSvcTffEntSrchId", "Tariff Search", "PSPopUp_emrSvcTffNameTxtId", tariff, "Tariff Name" );
		if(PopupHelper.isPresent( "window-scroll-panel" ))
		{
		assertTrue( PopupHelper.isTextPresent( "window-scroll-panel", "Are you sure you wish to change the Tariff? This tariff is not associated with previous RateSheet Template. Once changed all the configurations related to config rule will be reset." ) );
		ButtonHelper.click( "OKButton" );
		}
	}
	
	
	/*
	 * This method is for popup validation 
	 */
	public void popupValidation() throws Exception
	{
		String popMsg = "Selected Tariff is not associated to any Template. Please select the template";
		assertTrue( PopupHelper.isPresent( "window-scroll-panel" ) );
		String actualMsg = ElementHelper.getText( "//*[@id='window-scroll-panel']//div[@title]" );
		assertEquals( popMsg, actualMsg, "popup texts are not matching" );
		ButtonHelper.click( "ok" );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
	}
}
