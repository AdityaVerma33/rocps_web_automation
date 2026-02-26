package com.subex.rocps.automation.helpers.application.tariffs.ratesheettemplate;

import org.testng.Assert;

import java.util.HashMap;
import java.util.Map;

import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;
import com.subex.rocps.automation.utils.PSStringUtils;

import com.subex.automation.helpers.application.NavigationHelper;
import com.subex.automation.helpers.component.ButtonHelper;
import com.subex.automation.helpers.component.CheckBoxHelper;
import com.subex.automation.helpers.component.ComboBoxHelper;
import com.subex.automation.helpers.component.ElementHelper;
import com.subex.automation.helpers.component.GenericHelper;
import com.subex.automation.helpers.component.GridHelper;
import com.subex.automation.helpers.component.TextBoxHelper;
import com.subex.automation.helpers.data.ValidationHelper;

public class AdvanceRatingImpl extends PSAcceptanceTest
{
	String flatRateMatchString;
	String rejectDuplicate;
	String duplicateRateAction;
	String duplicateEffectiveDateAction;
	String ruleDefintionRuleType;
	String ruleDefinitionSearchString;
	String ruleDefinitionSheetName;
	String ruleDefinitionColumnName;
	String ruleDefinitionCLIRule;
	String spcecialCharParserType;
	String spcecialCharParserTypeCharacters;
	
	Map<String,String> advConfMap = new HashMap<String,String>();
	PSStringUtils strUtilObj = new PSStringUtils();
	public AdvanceRatingImpl(Map<String, String> map) throws Exception {
		
		this.advConfMap = map;
		initialize(  this.advConfMap );
	}
	

	private void initialize( Map<String, String> map ) throws Exception
	{
		flatRateMatchString =	map.get("FlatRateMatchString");
		rejectDuplicate =	map.get("RejectDuplicate");
		duplicateRateAction =	map.get("DuplicateRateAction");
		duplicateEffectiveDateAction =	map.get("DuplicateEffectiveDateAction");
		ruleDefintionRuleType =	map.get("RuleDefintionRuleType");
		ruleDefinitionSearchString =	map.get("RuleDefinitionSearchString");
		ruleDefinitionSheetName =	map.get("RuleDefinitionSheetName");
		ruleDefinitionColumnName =	map.get("RuleDefinitionColumnName");
		ruleDefinitionCLIRule =	map.get("RuleDefinitionCLIRule");
		spcecialCharParserType =	map.get("SpcecialCharParserType");
		spcecialCharParserTypeCharacters =	map.get("SpcecialCharParserTypeCharacters");
	
	}
	
	public void advanceRating() throws Exception {
		boolean isflatRateMatchString = Boolean.valueOf( !flatRateMatchString.isEmpty() );
		boolean isrejectDuplicate = Boolean.valueOf( !rejectDuplicate.isEmpty() );
		boolean isruleDefintionRuleType = Boolean.valueOf( !ruleDefintionRuleType.isEmpty() );
		boolean isspcecialCharParserType = Boolean.valueOf( !spcecialCharParserType.isEmpty());
		
		if(isflatRateMatchString || isrejectDuplicate ||isruleDefintionRuleType || isspcecialCharParserType) {
			ButtonHelper.click("advanceConfiguration");
			GenericHelper.waitForLoadmask();
			validateScreenTitle();
			
			if(isflatRateMatchString || isrejectDuplicate)
				elementMatchingAndRecordConfig();
			
			if(isruleDefintionRuleType)
				ruleDefinitions();
			
			if(isspcecialCharParserType)
				specialCharacterDefinitions();
			
			ButtonHelper.click( "ratesheetAdvConfigDetail.oK" );
			GenericHelper.waitForLoadmask();
		}

	}
	
	
	protected void elementMatchingAndRecordConfig() throws Exception {
		
		TextBoxHelper.type(or.getProperty( "PS_RSAdvRatingFlatRateMatchingTxtId" ),flatRateMatchString);
		if(!rejectDuplicate.isEmpty() && ValidationHelper.isFalse( rejectDuplicate )) {
			CheckBoxHelper.uncheck( "pracIsRejectAll_InputElement" );
			ComboBoxHelper.select("PS_RSAdvRatingDupRateAComboId" , duplicateRateAction );			
			ComboBoxHelper.select( "PS_RSAdvRatingDupEffectiveDateComboId", duplicateEffectiveDateAction );
		}
	}
	
	protected void ruleDefinitions() throws Exception {
		
		String[] ruleDefintionRuleTypeArr = strUtilObj.stringSplitFirstLevel( ruleDefintionRuleType );
		String[] ruleDefinitionSearchStringArr = strUtilObj.stringSplitFirstLevel( ruleDefinitionSearchString );
		String[] ruleDefinitionSheetNameArr = strUtilObj.stringSplitFirstLevel( ruleDefinitionSheetName );
		String[] ruleDefinitionColumnNameArr = strUtilObj.stringSplitFirstLevel( ruleDefinitionColumnName );
		String[] ruleDefinitionCLIRuleArr = strUtilObj.stringSplitFirstLevel( ruleDefinitionCLIRule );
		
		for(int rowIndex=0; rowIndex < ruleDefintionRuleTypeArr.length; rowIndex++) {
			ElementHelper.scrollToView( "//div[@id='grid_column_header_undefined_ratesheetTemRuleType']", false );
			
			ButtonHelper.click( or.getProperty( "PS_RSRuleDefnAddBtnXpath" ) );
			GridHelper.updateGridComboBox( or.getProperty( "PS_RSAdvRulDfnGridId" ), or.getProperty( "PS_RSAdvRulDfnRuleTypeComboId" ), rowIndex+1, "Rule Type", ruleDefintionRuleTypeArr[rowIndex] );
			GridHelper.updateGridTextBox( or.getProperty( "PS_RSAdvRulDfnGridId" ), or.getProperty( "PS_RSAdvRulDfnSrchStringTxtId" ), rowIndex+1, "Search String", ruleDefinitionSearchStringArr[rowIndex] );
			GridHelper.updateGridComboBox( or.getProperty( "PS_RSAdvRulDfnGridId" ), or.getProperty( "PS_RSAdvRulDfnWrkSheetComboId" ), rowIndex+1, "Sheet Name", ruleDefinitionSheetNameArr[rowIndex] );
			GridHelper.updateGridTextBox( or.getProperty( "PS_RSAdvRulDfnGridId" ), or.getProperty( "PS_RSAdvRulDfnColNameTxtId" ), rowIndex+1, "Column Name", ruleDefinitionColumnNameArr[rowIndex] );
			if(!ruleDefinitionCLIRuleArr[rowIndex].isEmpty()) {
			ElementHelper.scrollToView( "//div[@id='grid_column_header_undefined_cliRules']", false );
			GridHelper.updateGridComboBox( or.getProperty( "PS_RSAdvRulDfnGridId" ), or.getProperty( "PS_RSAdvRulDfnCliRuleComboId" ), rowIndex+1, "C L I  Rule", ruleDefinitionCLIRuleArr[rowIndex] );
			}
		}
    }
	
	
	protected void specialCharacterDefinitions() throws Exception {
		
		String[] spcecialCharParserTypeArr = strUtilObj.stringSplitFirstLevel( spcecialCharParserType);
		String[] spcecialCharParserTypeCharactersArr = strUtilObj.stringSplitFirstLevel( spcecialCharParserTypeCharacters );
		
		for(int rowIndex = 0; rowIndex < spcecialCharParserTypeArr.length; rowIndex++) {
			
			int rowNum = GridHelper.getRowNumber( or.getProperty( "PS_RSSpecialCharDfnGridId" ), spcecialCharParserTypeArr[rowIndex] , "Parser Type");
			
			GridHelper.updateGridComboBox(  or.getProperty( "PS_RSSpecialCharDfnGridId" ), or.getProperty( "PS_RSSpecialCharDfnCharComboId" ), rowNum, "Character", spcecialCharParserTypeCharactersArr[rowIndex] );
			
		}
	}
	
	protected void validateScreenTitle() throws Exception {
		
		String title = NavigationHelper.getScreenTitle();
		Assert.assertTrue(title.equals( "Ratesheet Advance Configuration" ));
	}
}
