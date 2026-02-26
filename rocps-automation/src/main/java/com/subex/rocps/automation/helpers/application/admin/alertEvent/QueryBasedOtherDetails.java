package com.subex.rocps.automation.helpers.application.admin.alertEvent;

import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import com.subex.automation.helpers.application.screens.TableInstanceHelper;
import com.subex.automation.helpers.component.ButtonHelper;
import com.subex.automation.helpers.component.CheckBoxHelper;
import com.subex.automation.helpers.component.ComboBoxHelper;
import com.subex.automation.helpers.component.ElementHelper;
import com.subex.automation.helpers.component.GenericHelper;
import com.subex.automation.helpers.component.GridHelper;
import com.subex.automation.helpers.component.TextBoxHelper;
import com.subex.automation.helpers.data.ValidationHelper;
import com.subex.rocps.automation.helpers.application.filters.DataSelectionHelper;
import com.subex.rocps.automation.helpers.application.filters.PSSearchGridHelper;
import com.subex.rocps.automation.helpers.application.genericHelpers.PSGenericHelper;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;
import com.subex.rocps.automation.utils.ExcelHolder;
import com.subex.rocps.automation.utils.PSStringUtils;

public class QueryBasedOtherDetails extends PSAcceptanceTest
{
	PSGenericHelper genObj = new PSGenericHelper();
	EmailConfigurationImpl emailObj = new EmailConfigurationImpl();
	AlertTextImpl alertTxtObj = new AlertTextImpl();
	AlertEvtOtherDetActImpl actionObj = new AlertEvtOtherDetActImpl();
	protected Map<String, String> map;
	PSStringUtils stringObj = new PSStringUtils();
	DataSelectionHelper dsObj = new DataSelectionHelper();
	String table;
	String column;
	String select;
	String aggregate;
	String alertInstance;
	String alertEmail;
	String columnHeader;
	String clause;
	String leftIndent;
	String aggregate1;
	String aggregate2;
	String column1;
	String column2;
	String operator;
	String rightIndent;
	String joinType;
	String joinColumn1;
	String joinColumn2;
	String joinOperator;
	String joinKey;
	String actions;
	String submitSqlValue;
	String runProgramPath;
	String runValue;
	String component;
	String editFunctionDataType;
	String editFunctionValue;
	String editConstantDataType;
	String editConstantValue;

	public QueryBasedOtherDetails( Map<String, String> map ) throws Exception
	{
		this.map = map;
		initializeVariables( map );
	}

	public void switchToOtherDetailsTab() throws Exception
	{
		ElementHelper.scrollToView( "//div[@id='topPanelContainer']//div[text()='Other Details']", false );
		ElementHelper.click( "//div[@id='topPanelContainer']//div[text()='Other Details']" );
		GenericHelper.waitForLoadmask();
	}

	public void completeOtherDetails() throws Exception
	{
		queryBuilderAddTables( table, column );
		queryBuilderGrid( select, aggregate, alertInstance, alertEmail, columnHeader );
		advanceOption( clause, leftIndent, aggregate1, column1, joinOperator, aggregate2, column2, rightIndent, editFunctionDataType, editFunctionValue, editConstantDataType, editConstantValue );
		joinOption( joinType, joinColumn1, joinColumn2, joinOperator, joinKey );
	}

	public void queryBuilderAddTables( String table, String column ) throws Exception
	{
		
		ButtonHelper.click( "toolbar-button-label-queryToolbar.addTables" );
		String[] tables = stringObj.stringSplitFirstLevel( table );
		String[] columnTables = stringObj.stringSplitFirstLevel( column );
		for ( int i = 0; i < tables.length; i++ )
		{
			String[] columns = stringObj.stringSplitSecondLevel( columnTables[i] );
			ButtonHelper.click( "toolbar-button-label-rawSourceToolBar.addTableInstance" );
			tableInstanceSearch( tables[i] );
			for ( int j = 0; j < columns.length; j++ )
			{
				dualListSelection( columns[j] );
			}
		}
		ButtonHelper.click( "alertTableInstDualListDetail.OK" );

	}

	public void advanceOption( String clause, String leftIndent, String aggregate1, String column1, String operator, String aggregate2, String column2, String rightIndent, String functionDataType, String functionValue, String constatDataType, String constantValue ) throws Exception
	{
		String[] clauses = stringObj.stringSplitFirstLevel( clause );
		String[] leftIndents = stringObj.stringSplitFirstLevel( leftIndent );
		String[] rightIndents = stringObj.stringSplitFirstLevel( rightIndent );
		String[] aggregates1 = stringObj.stringSplitFirstLevel( aggregate1 );
		String[] aggregates2 = stringObj.stringSplitFirstLevel( aggregate2 );
		String[] operators = stringObj.stringSplitFirstLevel( operator );
		String[] columns1 = stringObj.stringSplitFirstLevel( column1 );
		String[] columns2 = stringObj.stringSplitFirstLevel( column2 );
		String[] functionDataTypes = stringObj.stringSplitFirstLevel( functionDataType );
		String[] functionValues = stringObj.stringSplitFirstLevel( functionValue );
		String[] constantDataTypes = stringObj.stringSplitFirstLevel( constatDataType );
		String[] constantValues = stringObj.stringSplitFirstLevel( constantValue );
		GridHelper.clickRow( "queryBuilderGrid", 1, 1 );
		ButtonHelper.click( "toolbar-button-label-queryToolbar.advanceOption" );
		for ( int i = 0; i < clause.length(); i++ )
		{
			ButtonHelper.click( "toolbar-button-label-queryFilterToolbar.addCondition" );
			GridHelper.clickRow( "queryFilterGrid", i + 1, 1 );
			GridHelper.clickRow( "queryFilterGrid", i + 1, 1 );
			ComboBoxHelper.select( "sqwOperatorComboEditor_gwt_uid_", clauses[i] );
			GridHelper.clickRow( "queryFilterGrid", i + 1, 2 );
			GridHelper.clickRow( "queryFilterGrid", i + 1, 2 );
			TextBoxHelper.type( "leftIndentEditor", leftIndents[i] );
			if ( !ValidationHelper.isEmpty( aggregates1[i] ) )
			{
				GridHelper.clickRow( "queryFilterGrid", i + 1, 3 );
				GridHelper.clickRow( "queryFilterGrid", i + 1, 3 );
				ComboBoxHelper.select( "lhsAggregateComboEditor_gwt_uid_", aggregates1[i] );
			}
			GridHelper.clickRow( "queryFilterGrid", i + 1, 4 );
			GridHelper.clickRow( "queryFilterGrid", i + 1, 4 );
			ComboBoxHelper.select( "id_gwt_uid_", columns1[i] );
			expression( functionDataTypes[i], functionValues[i], constantDataTypes[i], constantValues[i] );
			GridHelper.clickRow( "queryFilterGrid", i + 1, 5 );
			GridHelper.clickRow( "queryFilterGrid", i + 1, 5 );
			ComboBoxHelper.select( "operatorComboEditor_gwt_uid_", operators[i] );
			if ( !ValidationHelper.isEmpty( aggregates2[i] ) )
			{
				GridHelper.clickRow( "queryFilterGrid", i + 1, 6 );
				GridHelper.clickRow( "queryFilterGrid", i + 1, 6 );
				ComboBoxHelper.select( "rhsAggregateComboEditor_gwt_uid_", aggregates2[i] );
			}
			GridHelper.clickRow( "queryFilterGrid", i + 1, 7 );
			GridHelper.clickRow( "queryFilterGrid", i + 1, 7 );
			ComboBoxHelper.select( "id_gwt_uid_", columns2[i] );
			GridHelper.clickRow( "queryFilterGrid", i + 1, 8 );
			GridHelper.clickRow( "queryFilterGrid", i + 1, 8 );
			TextBoxHelper.type( "rightIndentEditor", rightIndents[i] );
		}
		ButtonHelper.click( "alertAdvanceOptionsDetail.OK" );

	}

	public void joinOption( String joinType, String joinColumn1, String joinColumn2, String joinOperator, String joinKey ) throws Exception
	{
		String[] joinTypes = stringObj.stringSplitFirstLevel( joinType );
		String[] joinColumns1 = stringObj.stringSplitFirstLevel( joinColumn1 );
		String[] joinColumns2 = stringObj.stringSplitFirstLevel( joinColumn2 );
		String[] joinOperators = stringObj.stringSplitFirstLevel( joinOperator );
		String[] joinKeys = stringObj.stringSplitFirstLevel( joinKey );
		GridHelper.clickRow( "queryBuilderGrid", 1, 1 );
		ButtonHelper.click( "toolbar-button-label-queryToolbar.advanceOption" );
		for ( int i = 0; i < joinTypes.length; i++ )
		{
			if ( !ValidationHelper.isEmpty( joinTypes[i] ) )
			{
				GridHelper.clickRow( "joinFilterGrid", i + 1, 1 );
				GridHelper.clickRow( "joinFilterGrid", i + 1, 1 );
				ComboBoxHelper.select( "joinTypeComboEditor_gwt_uid_", joinTypes[i] );
				GridHelper.clickRow( "joinFilterGrid", i + 1, 3 );
				GridHelper.clickRow( "joinFilterGrid", i + 1, 3 );
				ComboBoxHelper.select( "lhsJoinTableColumnCombo_gwt_uid_", joinColumns1[i] );
				GridHelper.clickRow( "joinFilterGrid", i + 1, 4 );
				GridHelper.clickRow( "joinFilterGrid", i + 1, 4 );
				ComboBoxHelper.select( "joinOperatorComboEditor_gwt_uid_", joinOperators[i] );
				GridHelper.clickRow( "joinFilterGrid", i + 1, 5 );
				GridHelper.clickRow( "joinFilterGrid", i + 1, 5 );
				ComboBoxHelper.select( "rhsJoinTableColumnCombo_gwt_uid_", joinColumns2[i] );
				if ( ValidationHelper.isTrue( joinKeys[i] ) )
				{
					CheckBoxHelper.check( "" );
				}
			}
		}

		ButtonHelper.click( "alertAdvanceOptionsDetail.OK" );

	}

	public void queryBuilderGrid( String select, String aggregate, String alertInstance, String alertEmail, String columnHeader ) throws Exception
	{
		String[] selects = stringObj.stringSplitFirstLevel( select );
		String[] aggregates = stringObj.stringSplitFirstLevel( aggregate );
		String[] alertInstances = stringObj.stringSplitFirstLevel( alertEmail );
		String[] alertEmails = stringObj.stringSplitFirstLevel( alertInstance );
		String[] columnHeaders = stringObj.stringSplitFirstLevel( columnHeader );
		for ( int i = 0; i < selects.length; i++ )
		{
			String[] selectList = stringObj.stringSplitSecondLevel( selects[i] );
			String[] aggregateList = stringObj.stringSplitSecondLevel( aggregates[i] );
			String[] alertInstanceList = stringObj.stringSplitSecondLevel( alertEmails[i] );
			String[] alertEmailList = stringObj.stringSplitSecondLevel( alertInstances[i] );
			String[] columnHeaderList = stringObj.stringSplitSecondLevel( columnHeaders[i] );
			for ( int j = 0; j < selectList.length; j++ )
			{
				if ( ValidationHelper.isTrue( selectList[j] ) )
				{
					GridHelper.clickRow( "queryBuilderGrid", j + 1, 2 );
					String testString = "(//img[@id='selectFlEditor_checked_column_field_image'])[position()=value]".replace( "value", String.valueOf( j + 1 ) );
					checkBoxCheck( testString );
					GridHelper.clickRow( "queryBuilderGrid", j + 1, 3 );
					GridHelper.clickRow( "queryBuilderGrid", j + 1, 3 );
					ComboBoxHelper.select( "sqsAggregateComboEditor_gwt_uid_", aggregateList[j] );
					if ( ValidationHelper.isTrue( alertInstanceList[j] ) )
					{
						GridHelper.clickRow( "queryBuilderGrid", j + 1, 4 );
						testString = "(//img[@id='alertTextEditor_checked_column_field_image'])[position()=value]".replace( "value", String.valueOf( j + 1 ) );
						checkBoxCheck( testString );
					}
					if ( ValidationHelper.isTrue( alertEmailList[j] ) )
					{
						GridHelper.clickRow( "queryBuilderGrid", j + 1, 5 );
						testString = "(//img[@id='alertEmailEditor_checked_column_field_image'])[position()=value]".replace( "value", String.valueOf( j + 1 ) );
						checkBoxCheck( testString );
						GridHelper.clickRow( "queryBuilderGrid", j + 1, 6 );
						GridHelper.clickRow( "queryBuilderGrid", j + 1, 6 );
						TextBoxHelper.type( "emailHeaderEditor", columnHeaderList[j] );
					}

				}
			}
		}
	}

	public void expression( String editFunctionDataType, String editFunctionValue, String editConstantDataType, String editConstantValue ) throws Exception
	{
		if ( !ValidationHelper.isEmpty( editFunctionValue ) )
		{
			ButtonHelper.click( "toolbar-button-label-queryFilterToolbar.expression" );
			ButtonHelper.click( "expressionSubMenu.addEditFunction" );
			GenericHelper.waitForLoadmask();
			GridHelper.clickRow( "grid", 1, 1 );
			GridHelper.clickRow( "grid", 1, 1 );
			ComboBoxHelper.select( "sparkTypes_gwt_uid_", editFunctionDataType );
			GridHelper.clickRow( "grid", 1, 2 );
			GridHelper.clickRow( "grid", 1, 2 );
			TextBoxHelper.type( "datatypeEditor", editFunctionValue );
			ButtonHelper.click( "alertExpressionFilterDetail.ok" );

		}
		if ( !ValidationHelper.isEmpty( editConstantValue ) )
		{
			ButtonHelper.click( "toolbar-button-label-queryFilterToolbar.expression" );
			ButtonHelper.click( "expressionSubMenu.addEditConstant" );
			GenericHelper.waitForLoadmask();
			GridHelper.clickRow( "grid", 1, 1 );
			ComboBoxHelper.select( "sparkTypes_gwt_uid_", editConstantDataType );
			GridHelper.clickRow( "grid", 1, 2 );
			GridHelper.clickRow( "grid", 1, 2 );
			TextBoxHelper.type( "datatypeEditor", editConstantValue );
			ButtonHelper.click( "alertExpressionFilterDetail.ok" );

		}
	}

	public void emailConfiguration( String subject, String header, String footer ) throws Exception
	{
		TextBoxHelper.type( "alertEvent.alertEmailInfo.emailSubString", subject );
		TextBoxHelper.type( "alertEvent.alertEmailInfo.emailHeaderString", header );
		TextBoxHelper.type( "alertEvent.alertEmailInfo.emailFooterString", footer );
	}

	public void checkBoxCheck( String xpath ) throws Exception
	{
		driver.findElement( By.xpath( xpath ) ).click();
	}

	public void dualListSelection( String dualListValue ) throws Exception
	{

		String[] dualListValueArr = new PSStringUtils().stringSplitFirstLevel( dualListValue );
		for ( String str : dualListValueArr )
		{

			if ( dualListValueArr.length >= 1 )
			{

				Actions action = new Actions( driver );
				String locator = "//div[@id='rawSourceTree']//div[text()='inputValues']".replace( "inputValues", str );
				if ( !ElementHelper.isElementPresent( locator ) )
					ElementHelper.waitForElement( locator, searchScreenWaitSec );
				WebElement element = driver.findElement( By.xpath( locator ) );
				ElementHelper.waitForClickableElement( element, searchScreenWaitSec );
				Thread.sleep( 3000 );
				GenericHelper.waitForLoadmask( searchScreenWaitSec );
				action.moveToElement( element ).click().build().perform();
				ButtonHelper.click( "addButton" );
				GenericHelper.waitForLoadmask( searchScreenWaitSec );

			}
		}

	}

	private void tableInstanceSearch( String value ) throws Exception
	{
		genObj.waitforPopupHeaderElement( "Schema" );
		ButtonHelper.click( "ClearButton" );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		ButtonHelper.click( "SearchButton" );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		genObj.waitforPopupHeaderElement( "Schema" );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		int row = PSSearchGridHelper.gridFilterSearchWithTextBox( "tinDisplayName", value, "Display Name" );
		boolean isValue = GridHelper.isValuePresent( "Detail_popUpWindowId", "SearchGrid", value, "Display Name" );
		assertTrue( isValue, "Table Instance Search with  display name :'" + value + "'  is not found in 'Table Instance Search' popupScreen " );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		GridHelper.clickRow( "Detail_popUpWindowId", "SearchGrid", row, "Display Name" );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		ButtonHelper.clickIfEnabled( "Detail_popUpWindowId", "OKButton" );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		genObj.waitforPopupHeaderElementToDisappear( "Schema" );
	}

	public void initializeVariables( Map<String, String> detMap ) throws Exception
	{
		table = ExcelHolder.getKey( detMap, "Table" );
		column = ExcelHolder.getKey( detMap, "Column" );
		select = ExcelHolder.getKey( detMap, "Select" );
		aggregate = ExcelHolder.getKey( detMap, "Aggregate" );
		alertInstance = ExcelHolder.getKey( detMap, "AlertInstance" );
		alertEmail = ExcelHolder.getKey( detMap, "AlertEmail" );
		columnHeader = ExcelHolder.getKey( detMap, "ColumnHeader" );
		clause = ExcelHolder.getKey( detMap, "Clause" );
		leftIndent = ExcelHolder.getKey( detMap, "LeftIndent" );
		aggregate1 = ExcelHolder.getKey( detMap, "Aggregate1" );
		aggregate2 = ExcelHolder.getKey( detMap, "Aggregate2" );
		column1 = ExcelHolder.getKey( detMap, "Column1" );
		column2 = ExcelHolder.getKey( detMap, "Column2" );
		editFunctionDataType = ExcelHolder.getKey( detMap, "FunctionDataType" );
		editFunctionValue = ExcelHolder.getKey( detMap, "FunctionValue" );
		editConstantDataType = ExcelHolder.getKey( detMap, "ConstantDataType" );
		editConstantValue = ExcelHolder.getKey( detMap, "ConstantValue" );
		joinType = ExcelHolder.getKey( detMap, "JoinType" );
		joinColumn1 = ExcelHolder.getKey( detMap, "JoinColumn1" );
		joinColumn2 = ExcelHolder.getKey( detMap, "JoinColumn2" );
		operator = ExcelHolder.getKey( detMap, "Operator" );
		joinOperator = ExcelHolder.getKey( detMap, "JoinOperator" );
		rightIndent = ExcelHolder.getKey( detMap, "RightIndent" );
		joinKey = ExcelHolder.getKey( detMap, "JoinKey" );
		actions = ExcelHolder.getKey( detMap, "Actions" );
		submitSqlValue = ExcelHolder.getKey( detMap, "SubmitSqlValue" );
		runProgramPath = ExcelHolder.getKey( detMap, "RunProgramPath" );
		runValue = ExcelHolder.getKey( detMap, "RunValue" );
		component = ExcelHolder.getKey( detMap, "Component" );

	}

}
