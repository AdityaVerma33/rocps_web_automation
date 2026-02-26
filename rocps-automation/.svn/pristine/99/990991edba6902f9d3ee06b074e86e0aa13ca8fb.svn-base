package com.subex.rocps.automation.helpers.application.matchandrate.surchargeRule;

import java.util.Map;

import com.subex.automation.helpers.component.ButtonHelper;
import com.subex.automation.helpers.component.ComboBoxHelper;
import com.subex.automation.helpers.component.ElementHelper;
import com.subex.automation.helpers.component.EntityComboHelper;
import com.subex.automation.helpers.component.GenericHelper;
import com.subex.automation.helpers.component.GridHelper;
import com.subex.automation.helpers.component.TextAreaHelper;
import com.subex.automation.helpers.component.TextBoxHelper;
import com.subex.automation.helpers.data.ValidationHelper;
import com.subex.rocps.automation.helpers.application.filters.DataSelectionHelper;
import com.subex.rocps.automation.helpers.application.genericHelpers.PSDataComponentHelper;
import com.subex.rocps.automation.helpers.application.genericHelpers.PSGenericHelper;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;
import com.subex.rocps.automation.utils.ExcelHolder;
import com.subex.rocps.automation.utils.PSStringUtils;

public class SurchargeRuleDetailImpl extends PSAcceptanceTest
{
	protected Map<String, String> surchargeRuleDetailsMap = null;
	protected String surchargeRuleName;
	protected String eventType;
	protected String description;
	protected String expClause;
	protected String expClauseArr[];
	protected String expLeftBracketFlg;
	protected String expLeftBracketFlgArr[];
	protected String expIdentDefn;
	protected String expIdentDefnArr[];
	protected String expOperator;
	protected String expOperatorArr[];
	protected String expIdentValue;
	protected String expIdentValueArr[];
	protected String expRightBracketFlg;
	protected String expRightBracketFlgArr[];
	protected String expViewExpFlg;
	protected String expViewExpFlgArr[];
	PSStringUtils psStringUtils = new PSStringUtils();
	PSGenericHelper psGenericHelper = new PSGenericHelper();
	DataSelectionHelper dataSelectionHelper = new DataSelectionHelper();
	PSDataComponentHelper psDataComponentHelper = new PSDataComponentHelper();

	/**Constructor
	 * @param surchargeRuleDetailsMap
	 */
	public SurchargeRuleDetailImpl( Map<String, String> surchargeRuleDetailsMap )
	{

		this.surchargeRuleDetailsMap = surchargeRuleDetailsMap;
	}

	/*
	 * This method is for initialize variable
	 */
	private void initializeVariable( Map<String, String> map ) throws Exception
	{
		surchargeRuleName = ExcelHolder.getKey( map, "Name" );
		eventType = ExcelHolder.getKey( map, "EventType" );
		description = ExcelHolder.getKey( map, "Description" );
		expClause = ExcelHolder.getKey( map, "Expression_Clause" );
		expLeftBracketFlg = ExcelHolder.getKey( map, "Expression_LeftBracketFlag" );
		expIdentDefn = ExcelHolder.getKey( map, "Expression_IdentifierDefn" );
		expOperator = ExcelHolder.getKey( map, "Expression_Operator" );
		expIdentValue = ExcelHolder.getKey( map, "Expression_IdentifierValue" );
		expRightBracketFlg = ExcelHolder.getKey( map, "Expression_RightBracketFlag" );
		expViewExpFlg = ExcelHolder.getKey( map, "Expression_ViewExpresionFlg" );

	}

	/*
	 * This method is for configure surcharge rule
	 */
	public void configureSurchargeRule() throws Exception
	{
		initializeVariable( surchargeRuleDetailsMap );
		configureNamePanel();
		configureExpression();
		clickOnSave();
	}


	/*
	 * This method is for configure Name Panel
	 */
	protected void configureNamePanel() throws Exception
	{
		TextBoxHelper.type( "PS_Detail_surchargeRule_name_txtId", surchargeRuleName );
		ComboBoxHelper.select( "PS_Detail_surchargeRule_eventType_comboId", eventType );
		TextBoxHelper.type( "PS_Detail_surchargeRule_description_txtId", description );

	}



	/*
	 * This method is for configure Expression
	 */
	protected void configureExpression() throws Exception
	{
		expClauseArr = psStringUtils.stringSplitFirstLevel( expClause );
		expLeftBracketFlgArr = psStringUtils.stringSplitFirstLevel( expLeftBracketFlg );
		expIdentDefnArr = psStringUtils.stringSplitFirstLevel( expIdentDefn );
		expOperatorArr = psStringUtils.stringSplitFirstLevel( expOperator );
		expIdentValueArr = psStringUtils.stringSplitFirstLevel( expIdentValue );
		expRightBracketFlgArr = psStringUtils.stringSplitFirstLevel( expRightBracketFlg );
		expViewExpFlgArr = psStringUtils.stringSplitFirstLevel( expViewExpFlg );
		String gridId = "PS_Detail_surchargeRule_exp_gridId";
		int rowNum = 0;
		for ( int i = 0; i < expIdentDefnArr.length; i++ )
		{
			rowNum = i + 1;
			ButtonHelper.click( "PS_Detail_surchargeRule_exp_add_btnId" );
			GridHelper.updateGridComboBox( gridId, "PS_Detail_surchargeRule_exp_clause_comboId", rowNum, "Clause", expClauseArr[i] );
			if ( ValidationHelper.isNotEmpty( expLeftBracketFlgArr[i] ) && ValidationHelper.isTrue( expLeftBracketFlgArr[i] ) )
				GridHelper.updateGridTextBox( gridId, "PS_Detail_surchargeRule_exp_leftBracket_textId", rowNum, "(", "(" );
			GridHelper.updateGridComboBox( gridId, "PS_Detail_surchargeRule_exp_identDefn_comboId", rowNum, 3, "Expression", expIdentDefnArr[i] );
			GridHelper.updateGridComboBox( gridId, "PS_Detail_surchargeRule_exp_operator_comboId", rowNum, "Operator", expOperatorArr[i] );
			configEventIdenValGrp( gridId, rowNum, expIdentValueArr[i] );
			if ( ValidationHelper.isNotEmpty( expRightBracketFlgArr[i] ) && ValidationHelper.isTrue( expRightBracketFlgArr[i] ) )
				GridHelper.updateGridTextBox( gridId, "PS_Detail_surchargeRule_exp_rightBracket_textId", rowNum, ")", ")" );
			if ( ValidationHelper.isNotEmpty( expViewExpFlgArr[i] ) && ValidationHelper.isTrue( expViewExpFlgArr[i] ) )
				viewExpression( expIdentDefnArr[i], expOperatorArr[i], expIdentValueArr[i] );
		}

	}

	/*
	 * This method is for configure value Group
	 */
	private void configEventIdenValGrp( String gridId, int rowNum, String valueGrpName ) throws Exception
	{
		GridHelper.clickRow( gridId, rowNum, 5 );
		psGenericHelper.waitforEntityElement();
		EntityComboHelper.clickEntityIcon( "PS_Detail_surchargeRule_exp_identValue_triggerId" );
		if ( psGenericHelper.isPopupHeaderElementPresent( "Operator" ) )
			dataSelectionHelper.routeGrpSelection( valueGrpName );
		else if ( psGenericHelper.isPopupHeaderElementPresent( "Event Identifier Definition" ) )
			dataSelectionHelper.valueGrpSelection( valueGrpName );

	}

	/*
	 * This method is for validate query label in View Expression
	 */
	private void viewExpression( String eventIdenDefn, String operator, String eventIdentValue ) throws Exception
	{
		ButtonHelper.click( "PS_Detail_surchargeRule_exp_viewExp_btnId" );
		ElementHelper.waitForElement( GenericHelper.getORProperty( "PS_Detail_surchargeRule_exp_viewExp_xpath" ), searchScreenWaitSec );
		String viewExpActualQuery = TextAreaHelper.getValue( "PS_Detail_surchargeRule_exp_viewExp_query_testAreaId" );
		String viewExpExpectedQuery = eventIdenDefn + " " + operator.toLowerCase() + " " + eventIdentValue;
		assertTrue( viewExpActualQuery.contains( viewExpExpectedQuery ), "View Expression query is not matched" );
		ButtonHelper.click( "PS_Detail_surchargeRule_exp_viewExp_cancel_btnId" );
		ElementHelper.waitForElementToDisappear( GenericHelper.getORProperty( "PS_Detail_surchargeRule_exp_viewExp_xpath" ), searchScreenWaitSec );
	}

	/*
	 * This method is for click on save
	 */
	private void clickOnSave() throws Exception
	{
		psGenericHelper.detailSave( "PS_Detail_surchargeRule_save_btnId", surchargeRuleName, "Name" );
	}

}
