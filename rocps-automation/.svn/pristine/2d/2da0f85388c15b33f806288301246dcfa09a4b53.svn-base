package com.subex.rocps.automation.helpers.application.xdrextraction.XdrExtraTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.subex.automation.helpers.component.ButtonHelper;
import com.subex.automation.helpers.component.CheckBoxHelper;
import com.subex.automation.helpers.component.ComboBoxHelper;
import com.subex.automation.helpers.component.ElementHelper;
import com.subex.automation.helpers.component.GenericHelper;
import com.subex.automation.helpers.component.GridHelper;
import com.subex.automation.helpers.component.LabelHelper;
import com.subex.automation.helpers.component.TextBoxHelper;
import com.subex.automation.helpers.data.DateHelper;
import com.subex.automation.helpers.data.ValidationHelper;
import com.subex.automation.helpers.util.FailureHelper;
import com.subex.rocps.automation.helpers.application.genericHelpers.PSDataComponentHelper;
import com.subex.rocps.automation.helpers.application.genericHelpers.PSGenericHelper;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;
import com.subex.rocps.automation.utils.ExcelHolder;
import com.subex.rocps.automation.utils.PSStringUtils;

public class XdrExtrTempDetailImpl extends PSAcceptanceTest
{

	protected Map<String, String> xdrExTemDetailImplMap;
	protected String colmHdrs;
	protected String colmnHdrsArr[];
	protected String xdrExtTempNm;
	protected String validFromDt;
	protected String validToDt;
	protected String eventType;
	protected String filterComponent;
	protected String showHeaderFlag;
	protected String extractWithBillFlag;
	protected String latestRatedEventFlag;
	protected String inputTable;
	protected String inputTableArr[];
	protected String tableColmnsArr[];
	protected String tableColmnsIncludeFlKeyArr[];
	protected String tableColmnsAggFreqArr[];
	protected String entitiesColmnsArr[];
	protected String entitiesFieldsArr[];
	protected String entitiesFieldsIncludeFlKeyArr[];
	protected String entitiesFieldsAggFreqArr[];
	PSStringUtils psStringUtils = new PSStringUtils();
	PSGenericHelper psGenericHelper = new PSGenericHelper();

	/* Constructor:*/
	public XdrExtrTempDetailImpl( Map<String, String> xdrExTemDetailImplMap ) throws Exception
	{

		this.xdrExTemDetailImplMap = xdrExTemDetailImplMap;
	}

	/* Method: Initialize the variables*/
	private void initializeVariableConfig( Map<String, String> map ) throws Exception
	{

		xdrExtTempNm = ExcelHolder.getKey( map, "TemplateName" );
		validFromDt = ExcelHolder.getKey( map, "ValidFrom" );
		validToDt = ExcelHolder.getKey( map, "ValidTo" );
		eventType = ExcelHolder.getKey( map, "EventType" );
		filterComponent = ExcelHolder.getKey( map, "FilterComponent" );
		showHeaderFlag = ExcelHolder.getKey( map, "IncludeShowHeader" );
		extractWithBillFlag = ExcelHolder.getKey( map, "IncludeExtrWithBill" );
		latestRatedEventFlag = ExcelHolder.getKey( map, "IncludeLatestRatedEvents" );
		inputTable = ExcelHolder.getKey( map, "InputTables" );
		if ( ValidationHelper.isNotEmpty( inputTable ) )
		{

			inputTableArr = psStringUtils.stringSplitFirstLevel( inputTable );
			tableColmnsArr = new String[inputTableArr.length];
			tableColmnsIncludeFlKeyArr = new String[inputTableArr.length];
			tableColmnsAggFreqArr = new String[inputTableArr.length];
			entitiesColmnsArr = new String[inputTableArr.length];
			entitiesFieldsArr = new String[inputTableArr.length];
			entitiesFieldsIncludeFlKeyArr = new String[inputTableArr.length];
			entitiesFieldsAggFreqArr = new String[inputTableArr.length];
			initializeTableColumnEntities( map );
		}

	}

	/* Method: Initialize the variables of Table Columns: and Entities*/
	public void initializeTableColumnEntities( Map<String, String> map ) throws Exception
	{

		for ( int index = 0; index < inputTableArr.length; index++ )
		{

			tableColmnsArr[index] = ExcelHolder.getKey( map, inputTableArr[index] + "_TableColumns" );
			tableColmnsIncludeFlKeyArr[index] = ExcelHolder.getKey( map, inputTableArr[index] + "_TableColumns_IncludeFileKey" );
			tableColmnsAggFreqArr[index] = ExcelHolder.getKey( map, inputTableArr[index] + "_TableColumns_AggFrequency" );
			entitiesColmnsArr[index] = ExcelHolder.getKey( map, inputTableArr[index] + "_EntitiesColmn" );
			entitiesFieldsArr[index] = ExcelHolder.getKey( map, inputTableArr[index] + "_EntitiesField" );
			entitiesFieldsIncludeFlKeyArr[index] = ExcelHolder.getKey( map, inputTableArr[index] + "_EntitiesField_IncludeFileKey" );
			entitiesFieldsAggFreqArr[index] = ExcelHolder.getKey( map, inputTableArr[index] + "_EntitiesField_AggFrequency" );

		}
	}

	/* Method: Verify the column headers of XDR Extraction Template*/
	public void verifyColmnHeaderOfXdrExtrTempl() throws Exception
	{

		colmHdrs = ExcelHolder.getKey( xdrExTemDetailImplMap, "SearchScreenColumns" );
		if ( ValidationHelper.isNotEmpty( colmHdrs ) )
			colmnHdrsArr = psStringUtils.stringSplitFirstLevel( colmHdrs );
		ArrayList<String> excelColumnNames = new ArrayList<String>();
		String evenErrGridColumnsArr[] = psStringUtils.stringSplitFirstLevel( colmHdrs );
		for ( int col = 0; col < evenErrGridColumnsArr.length; col++ )
		{
			excelColumnNames.add( evenErrGridColumnsArr[col] );
		}
		psGenericHelper.totalColumns( excelColumnNames );
	}

	/* Method: Create XDR Extraction Template*/
	private void createXdrExtTempl( String tempNm ) throws Exception
	{
		initializeVariableConfig( xdrExTemDetailImplMap );
		TextBoxHelper.type( "PS_Detail_xdrExtTemp_name_textID", tempNm );
		TextBoxHelper.type( "PS_Detail_xdrExtTemp_ValidFromDt_textID", validFromDt );
		if ( ValidationHelper.isNotEmpty( validToDt ) )
			TextBoxHelper.type( "PS_Detail_xdrExtTemp_ValidToDt_textID", validToDt );
		ComboBoxHelper.select( "PS_Detail_xdrExtTemp_eventType_comboID", eventType );
		if ( ValidationHelper.isNotEmpty( filterComponent ) )
			ComboBoxHelper.select( "PS_Detail_xdrExtTemp_filterComp_comboID", filterComponent );
		if ( ValidationHelper.isTrue( showHeaderFlag ) )
			CheckBoxHelper.check( "PS_Detail_xdrExtTemp_showHeaderFlag_ChkBxID" );
		if ( ValidationHelper.isTrue( extractWithBillFlag ) )
		{
			CheckBoxHelper.check( "PS_Detail_xdrExtTemp_extWithBill_ChkBxID" );
			if ( ValidationHelper.isTrue( latestRatedEventFlag ) )
				CheckBoxHelper.check( "PS_Detail_xdrExtTemp_latestRatedEvent_ChkBxID" );
		}

	}

	// Method: Configure XDR Extraction Template
	public void configXdrExtTempl( String tempNm ) throws Exception
	{
		createXdrExtTempl( tempNm );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		configInputTable();
	}

	// Method: SaveAs option for XDR Extraction Template
	public void saveAsXdrExtTempl( String tempNm ) throws Exception
	{
		createXdrExtTempl( tempNm );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		editInputTable();

	}

	// Method:  Edit for XDR Extraction Template
	public void editXdrExtTempl( String tempNm ) throws Exception
	{
		initializeVariableConfig( xdrExTemDetailImplMap );
		assertEquals( TextBoxHelper.getValue( "PS_Detail_xdrExtTemp_name_textID" ), tempNm, "Template name is not matched" );
		if ( ValidationHelper.isNotEmpty( validFromDt ) )
			TextBoxHelper.type( "PS_Detail_xdrExtTemp_ValidFromDt_textID", validFromDt );
		if ( ValidationHelper.isNotEmpty( validToDt ) )
			TextBoxHelper.type( "PS_Detail_xdrExtTemp_ValidToDt_textID", validToDt );
		if ( ValidationHelper.isNotEmpty( eventType ) )
			ComboBoxHelper.select( "PS_Detail_xdrExtTemp_eventType_comboID", eventType );
		if ( ValidationHelper.isNotEmpty( filterComponent ) )
			ComboBoxHelper.select( "PS_Detail_xdrExtTemp_filterComp_comboID", filterComponent );
		if ( ValidationHelper.isTrue( extractWithBillFlag ) )
			CheckBoxHelper.check( "PS_Detail_xdrExtTemp_extWithBill_ChkBxID" );
		if ( ValidationHelper.isFalse( extractWithBillFlag ) )
			CheckBoxHelper.uncheck( "PS_Detail_xdrExtTemp_extWithBill_ChkBxID" );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		editInputTable();

	}

	// Method: Validate XDR Extraction Template Detail screen
	public void validateXdrDetailScreen() throws Exception
	{
		initializeVariableConfig( xdrExTemDetailImplMap );
		assertEquals( TextBoxHelper.getValue( "PS_Detail_xdrExtTemp_name_textID" ), xdrExtTempNm );
		assertTrue( TextBoxHelper.getValue( "PS_Detail_xdrExtTemp_ValidFromDt_textID" ).contains( validFromDt ), " validFromDt date and to date is not matched" );
		if ( ValidationHelper.isNotEmpty( validToDt ) )
			assertTrue( TextBoxHelper.getValue( "PS_Detail_xdrExtTemp_ValidToDt_textID" ).contains( validToDt ) );
		else
			assertTrue( TextBoxHelper.getValue( "PS_Detail_xdrExtTemp_ValidToDt_textID" ).contains( "01/01/9999" ) );
		assertEquals( ComboBoxHelper.getValue( "PS_Detail_xdrExtTemp_eventType_comboID" ), eventType );
		if ( ValidationHelper.isNotEmpty( filterComponent ) )
			assertEquals( ComboBoxHelper.getValue( "PS_Detail_xdrExtTemp_filterComp_comboID" ), filterComponent );
		if ( ValidationHelper.isTrue( extractWithBillFlag ) )
			CheckBoxHelper.isChecked( "PS_Detail_xdrExtTemp_extWithBill_ChkBxID" );
		else
			CheckBoxHelper.isNotChecked( "PS_Detail_xdrExtTemp_extWithBill_ChkBxID" );
		validateInputTableColumn();
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		ButtonHelper.click( "xdrExtractConfigDetail.cancel" );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );

	}

	// Method: Validate the Input Table:
	private void validateInputTableColumn() throws Exception
	{
		List<String> inputeTblcolumnsList = GridHelper.getColumnValues( "PS_Detail_xdrExtTemp_inputTable_GridID", "Table Name" );
		assertTrue( inputeTblcolumnsList.size() == inputTableArr.length, "Input table is not same as available values of the column List" );
		boolean isInputTablePres = inputeTblcolumnsList.containsAll( Arrays.asList( inputTableArr ) );
		assertTrue( isInputTablePres, "Given record not found in Event error screen" );

		for ( int i = 0; i < inputTableArr.length; i++ )
		{
			GridHelper.clickRow( "PS_Detail_xdrExtTemp_inputTable_GridID", inputTableArr[i], "Table Name" );
			GenericHelper.waitForLoadmask( searchScreenWaitSec );
			validateTableColmns( i );
		}
	}

	// Method: Validate the Table Columns:
	private void validateTableColmns( int index ) throws Exception
	{
		try
		{
			String tcl_displayNmArr[] = null;
			String tcl_displayNmKeyArr[] = null;
			String ent_entityNmArr[] = null;
			String tcl_displNmArrWithFlg[] = null;
			String displayNmGridId = GenericHelper.getORProperty( "PS_Detail_xdrExtTemp_tableColm_GridID" );
			if ( ValidationHelper.isNotEmpty( entitiesFieldsArr[index] ) )
				ent_entityNmArr = psStringUtils.stringSplitFirstLevel( entitiesFieldsArr[index] );
			if ( ValidationHelper.isNotEmpty( tableColmnsArr[index] ) )
			{
				tcl_displayNmArr = psStringUtils.stringSplitFirstLevel( tableColmnsArr[index] );
				for ( int i = 0; i < tcl_displayNmArr.length; i++ )
				{
					tcl_displNmArrWithFlg = psStringUtils.stringSplitSecondLevel( tcl_displayNmArr[i] );
					String displayNm = tcl_displNmArrWithFlg[0];
					String displayNmFlg = tcl_displNmArrWithFlg[1];
					GridHelper.clickRow( "PS_Detail_xdrExtTemp_tableColm_GridID", displayNm, "Display Name" );
					if ( ValidationHelper.isTrue( displayNmFlg ) )
						assertTrue( getCheckBoxText( displayNmGridId, displayNm ).contains( "cellchecked" ) );
					else
						assertTrue( getCheckBoxText( displayNmGridId, displayNm ).contains( "cellunchecked" ) );
				}
			}
			if ( ValidationHelper.isNotEmpty( entitiesColmnsArr[index] ) )
			{
				tcl_displayNmKeyArr = psStringUtils.stringSplitFirstLevel( entitiesColmnsArr[index] );
				for ( int i = 0; i < tcl_displayNmKeyArr.length; i++ )
				{
					GridHelper.clickRow( "PS_Detail_xdrExtTemp_tableColm_GridID", tcl_displayNmKeyArr[i], "Display Name" );
					validateEntitiesField( ent_entityNmArr[i] );

				}
			}
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

	// Method: Validate the Entities:
	private void validateEntitiesField( String entityNames ) throws Exception
	{
		try
		{
			String entityNmArr[] = null;
			String entityNmWithFlgArr[] = null;
			String entityNmGridId = GenericHelper.getORProperty( "PS_Detail_xdrExtTemp_entities_GridID" );
			if ( ValidationHelper.isNotEmpty( entityNames ) )
				entityNmArr = psStringUtils.stringSplitSecondLevel( entityNames );
			for ( int i = 0; i < entityNmArr.length; i++ )
			{
				entityNmWithFlgArr = psStringUtils.stringSplitThirdLevel( entityNmArr[i] );
				String entityNm = entityNmWithFlgArr[0];
				String entityNmFlg = entityNmWithFlgArr[1];
				GridHelper.clickRow( "PS_Detail_xdrExtTemp_entities_GridID", entityNm, "Entity Name" );
				if ( ValidationHelper.isTrue( entityNmFlg ) )
					assertTrue( getCheckBoxText( entityNmGridId, entityNm ).contains( "cellchecked" ) );
				else
					assertTrue( getCheckBoxText( entityNmGridId, entityNm ).contains( "cellunchecked" ) );
			}
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

	// Method: Edit Input Table:
	private void editInputTable() throws Exception
	{
		for ( int i = 0; i < inputTableArr.length; i++ )
		{
			GridHelper.clickRow( "PS_Detail_xdrExtTemp_inputTable_GridID", inputTableArr[i], "Table Name" );
			GenericHelper.waitForLoadmask( searchScreenWaitSec );
			editTableColmns( i );
		}
	}

	// Method:Get the value checkbox with src attribute :
	private String getCheckBoxText( String gridId, String fieldNm ) throws Exception

	{
		String xpath = GenericHelper.getORProperty( "PS_Detail_xdrExtTemp_fieldChckbx_xpath" );
		xpath = xpath.replace( "gridId", gridId );
		xpath = xpath.replace( "Field name", fieldNm );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		String elementTxt = ElementHelper.getAttribute( xpath, "src" );
		return elementTxt;

	}

	// Method: Edit Table Columns:
	private void editTableColmns( int index ) throws Exception
	{
		try
		{
			String tcl_displayNmArr[] = null;
			String tcl_displayNmKeyArr[] = null;
			String ent_entityNmArr[] = null;
			String tcl_displNmArrWithFlg[] = null;
			String displayNmGridId = GenericHelper.getORProperty( "PS_Detail_xdrExtTemp_tableColm_GridID" );
			if ( ValidationHelper.isNotEmpty( entitiesFieldsArr[index] ) )
				ent_entityNmArr = psStringUtils.stringSplitFirstLevel( entitiesFieldsArr[index] );
			if ( ValidationHelper.isNotEmpty( tableColmnsArr[index] ) )
			{
				tcl_displayNmArr = psStringUtils.stringSplitFirstLevel( tableColmnsArr[index] );
				for ( int i = 0; i < tcl_displayNmArr.length; i++ )
				{
					tcl_displNmArrWithFlg = psStringUtils.stringSplitSecondLevel( tcl_displayNmArr[i] );
					String displayNm = tcl_displNmArrWithFlg[0];
					String displayNmFlg = tcl_displNmArrWithFlg[1];
					psGenericHelper.scrollforHeaderElement( "PS_Detail_xdrExtTemp_tableColm_GridID", "Display  Name" );
					GenericHelper.waitForLoadmask( searchScreenWaitSec );
					GenericHelper.waitForLoadmask( searchScreenWaitSec );
					GridHelper.clickRow( "PS_Detail_xdrExtTemp_tableColm_GridID", displayNm, "Display Name" );
					GridHelper.clickRow( "PS_Detail_xdrExtTemp_tableColm_GridID", displayNm, "Display Name" );
					int row = GridHelper.getRowNumber( "PS_Detail_xdrExtTemp_tableColm_GridID", displayNm, "Display Name" );
					if ( ValidationHelper.isTrue( displayNmFlg ) && getCheckBoxText( displayNmGridId, displayNm ).contains( "cellunchecked" ) )
					{
						assertTrue( getCheckBoxText( displayNmGridId, displayNm ).contains( "cellunchecked" ) );
						GridHelper.clickRow( "PS_Detail_xdrExtTemp_tableColm_GridID", row, 2 );
						GenericHelper.waitForLoadmask( searchScreenWaitSec );
						GenericHelper.waitForLoadmask( searchScreenWaitSec );
						GenericHelper.waitForLoadmask( searchScreenWaitSec );
						GenericHelper.waitForLoadmask( searchScreenWaitSec );
						assertTrue( getCheckBoxText( displayNmGridId, displayNm ).contains( "cellchecked" ) );
					}
					if ( ValidationHelper.isFalse( displayNmFlg ) && getCheckBoxText( displayNmGridId, displayNm ).contains( "cellchecked" ) )
					{

						assertTrue( getCheckBoxText( displayNmGridId, displayNm ).contains( "cellchecked" ) );
						GridHelper.clickRow( "PS_Detail_xdrExtTemp_tableColm_GridID", row, 2 );
						GenericHelper.waitForLoadmask( searchScreenWaitSec );
						GenericHelper.waitForLoadmask( searchScreenWaitSec );
						GenericHelper.waitForLoadmask( searchScreenWaitSec );
						assertTrue( getCheckBoxText( displayNmGridId, displayNm ).contains( "cellunchecked" ) );
					}
				}
			}
			if ( ValidationHelper.isNotEmpty( entitiesColmnsArr[index] ) )
			{
				tcl_displayNmKeyArr = psStringUtils.stringSplitFirstLevel( entitiesColmnsArr[index] );
				for ( int i = 0; i < tcl_displayNmKeyArr.length; i++ )
				{
					GridHelper.clickRow( "PS_Detail_xdrExtTemp_tableColm_GridID", tcl_displayNmKeyArr[i], "Display Name" );
					GridHelper.clickRow( "PS_Detail_xdrExtTemp_tableColm_GridID", tcl_displayNmKeyArr[i], "Display Name" );
					editEntitiesField( ent_entityNmArr[i] );

				}
			}
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

	// Method: Edit the Entities:
	private void editEntitiesField( String entityNames ) throws Exception
	{
		try
		{
			String entityNmArr[] = null;
			String entityNmWithFlgArr[] = null;
			String entityNmGridId = GenericHelper.getORProperty( "PS_Detail_xdrExtTemp_entities_GridID" );
			if ( ValidationHelper.isNotEmpty( entityNames ) )
				entityNmArr = psStringUtils.stringSplitSecondLevel( entityNames );
			for ( int i = 0; i < entityNmArr.length; i++ )
			{
				entityNmWithFlgArr = psStringUtils.stringSplitThirdLevel( entityNmArr[i] );
				String entityNm = entityNmWithFlgArr[0];
				String entityNmFlg = entityNmWithFlgArr[1];
				GridHelper.clickRow( "PS_Detail_xdrExtTemp_entities_GridID", entityNm, "Entity Name" );
				int row = GridHelper.getRowNumber( "PS_Detail_xdrExtTemp_entities_GridID", entityNm, "Entity Name" );
				if ( ValidationHelper.isTrue( entityNmFlg ) && getCheckBoxText( entityNmGridId, entityNm ).contains( "cellunchecked" ) )
				{
					assertTrue( getCheckBoxText( entityNmGridId, entityNm ).contains( "cellunchecked" ) );
					GridHelper.clickRow( "PS_Detail_xdrExtTemp_entities_GridID", row, 2 );
					GenericHelper.waitForLoadmask( searchScreenWaitSec );
					assertTrue( getCheckBoxText( entityNmGridId, entityNm ).contains( "cellchecked" ) );
				}
				if ( ValidationHelper.isFalse( entityNmFlg ) && getCheckBoxText( entityNmGridId, entityNm ).contains( "cellchecked" ) )
				{

					assertTrue( getCheckBoxText( entityNmGridId, entityNm ).contains( "cellchecked" ) );
					GridHelper.clickRow( "PS_Detail_xdrExtTemp_entities_GridID", row, 2 );
					GenericHelper.waitForLoadmask( searchScreenWaitSec );
					assertTrue( getCheckBoxText( entityNmGridId, entityNm ).contains( "cellunchecked" ) );
				}

			}
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

	// Method: Configure Input Table:
	private void configInputTable() throws Exception
	{
		for ( int i = 0; i < inputTableArr.length; i++ )
		{
			GridHelper.clickRow( "PS_Detail_xdrExtTemp_inputTable_GridID", inputTableArr[i], "Table Name" );
			GenericHelper.waitForLoadmask( searchScreenWaitSec );
			configTableColmns( i );
		}
	}

	// Method: Configure Table Columns:
	private void configTableColmns( int index ) throws Exception
	{
		try
		{

			String tcl_displayNmKeyArr[] = null;
			String ent_entityNmArr[] = null;
			String ent_entityNmInclFlKeyArr[] = null;
			String ent_entityNmAggFreqArr[] = null;
			if ( ValidationHelper.isNotEmpty( tableColmnsArr[index] ) )
				configTableColumnField( index );
			if ( ValidationHelper.isNotEmpty( entitiesColmnsArr[index] ) )
			{
				tcl_displayNmKeyArr = psStringUtils.stringSplitFirstLevel( entitiesColmnsArr[index] );
				ent_entityNmArr = psStringUtils.stringSplitFirstLevel( entitiesFieldsArr[index] );
				ent_entityNmInclFlKeyArr = psStringUtils.stringSplitFirstLevel( entitiesFieldsIncludeFlKeyArr[index] );
				ent_entityNmAggFreqArr = psStringUtils.stringSplitFirstLevel( entitiesFieldsAggFreqArr[index] );
				for ( int i = 0; i < tcl_displayNmKeyArr.length; i++ )
				{
					psGenericHelper.scrollforHeaderElement( "PS_Detail_xdrExtTemp_tableColm_GridID", "Display  Name" );
					GridHelper.clickRow( "PS_Detail_xdrExtTemp_tableColm_GridID", tcl_displayNmKeyArr[i], "Display Name" );
					GridHelper.clickRow( "PS_Detail_xdrExtTemp_tableColm_GridID", tcl_displayNmKeyArr[i], "Display Name" );
					configEntitiesField( ent_entityNmArr[i], ent_entityNmInclFlKeyArr[i], ent_entityNmAggFreqArr[i] );

				}
			}
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

	private void configTableColumnField( int index ) throws Exception
	{
		String tcl_displayNmArr[] = null;
		String tcl_displayNmInclFlKeyArr[] = null;
		String tcl_displayNmAggFreqArr[] = null;
		tcl_displayNmArr = psStringUtils.stringSplitFirstLevel( tableColmnsArr[index] );
		tcl_displayNmInclFlKeyArr = psStringUtils.stringSplitFirstLevel( tableColmnsIncludeFlKeyArr[index] );
		tcl_displayNmAggFreqArr = psStringUtils.stringSplitFirstLevel( tableColmnsAggFreqArr[index] );
		for ( int i = 0; i < tcl_displayNmArr.length; i++ )
		{
			psGenericHelper.scrollforHeaderElement( "PS_Detail_xdrExtTemp_tableColm_GridID", "Display  Name" );
			GridHelper.clickRow( "PS_Detail_xdrExtTemp_tableColm_GridID", tcl_displayNmArr[i], "Display Name" );
			int row = GridHelper.getRowNumber( "PS_Detail_xdrExtTemp_tableColm_GridID", tcl_displayNmArr[i], "Display Name" );
			GridHelper.clickRow( "PS_Detail_xdrExtTemp_tableColm_GridID", row, "Include" );
			if ( ValidationHelper.isNotEmpty( tcl_displayNmInclFlKeyArr ) && ValidationHelper.isNotEmpty( tcl_displayNmInclFlKeyArr[i] ) )
			{
				GenericHelper.waitForLoadmask( searchScreenWaitSec );
				GridHelper.clickRow( "PS_Detail_xdrExtTemp_tableColm_GridID", tcl_displayNmArr[i], "Display Name" );
				psGenericHelper.scrollforHeaderElement( "PS_Detail_xdrExtTemp_tableColm_GridID", "Include as  File  Key" );
				GridHelper.clickRow( "PS_Detail_xdrExtTemp_tableColm_GridID", row, 3 );
				GridHelper.updateGridCheckBox( "PS_Detail_xdrExtTemp_tableColm_GridID", row, "Include as  File  Key", tcl_displayNmInclFlKeyArr[i] );
			}
			if ( ValidationHelper.isNotEmpty( tcl_displayNmAggFreqArr ) && ValidationHelper.isNotEmpty( tcl_displayNmAggFreqArr[i] ) )
			{
				GridHelper.clickRow( "PS_Detail_xdrExtTemp_tableColm_GridID", row, 4 );
				PSDataComponentHelper.updateGridComboBox( "PS_Detail_xdrExtTemp_tableColm_GridID", "pxakFrequencyEditor_gwt_uid_", row, 4, tcl_displayNmAggFreqArr[i] );
				psGenericHelper.scrollforHeaderElement( "PS_Detail_xdrExtTemp_tableColm_GridID", "Display  Name" );
				GenericHelper.waitForLoadmask( searchScreenWaitSec );

			}
		}

	}

	// Method: Configure the Entities:
	private void configEntitiesField( String entityNm, String entityIncludeFlKey, String entityAggFrequency ) throws Exception
	{
		try
		{
			String entityNmArr[] = null;
			String entityIncludeFlKeyArr[] = null;
			String entityAggFrequencyArr[] = null;
			if ( ValidationHelper.isNotEmpty( entityNm ) )
				entityNmArr = psStringUtils.stringSplitSecondLevel( entityNm );
			if ( ValidationHelper.isNotEmpty( entityIncludeFlKey ) )
				entityIncludeFlKeyArr = psStringUtils.stringSplitSecondLevel( entityIncludeFlKey );
			if ( ValidationHelper.isNotEmpty( entityAggFrequency ) )
				entityAggFrequencyArr = psStringUtils.stringSplitSecondLevel( entityAggFrequency );
			for ( int i = 0; i < entityNmArr.length; i++ )
			{
				GridHelper.clickRow( "PS_Detail_xdrExtTemp_entities_GridID", entityNmArr[i], "Entity Name" );
				int row = GridHelper.getRowNumber( "PS_Detail_xdrExtTemp_entities_GridID", entityNmArr[i], "Entity Name" );
				GridHelper.clickRow( "PS_Detail_xdrExtTemp_entities_GridID", row, "Include" );
				if ( ValidationHelper.isNotEmpty( entityIncludeFlKeyArr ) && ValidationHelper.isNotEmpty( entityIncludeFlKeyArr[i] ) )
					GridHelper.clickRow( "PS_Detail_xdrExtTemp_entities_GridID", row, "Include as  File  Key" );
				if ( ValidationHelper.isNotEmpty( entityAggFrequencyArr ) && ValidationHelper.isNotEmpty( entityAggFrequencyArr[i] ) )
				{
					GridHelper.clickRow( "PS_Detail_xdrExtTemp_entities_GridID", row, 4 );
					PSDataComponentHelper.updateGridComboBox( "PS_Detail_xdrExtTemp_entities_GridID", "pxakFrequencyEntEditor_gwt_uid_", row, 4, entityAggFrequencyArr[i] );
					psGenericHelper.scrollforHeaderElement( "PS_Detail_xdrExtTemp_entities_GridID", "Entity  Name" );

				}
			}
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

	// Method: Save the XDR Extraction Template
	public void saveXdrExtTempl( String tempNm ) throws Exception
	{
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		ButtonHelper.click( "PS_Detail_xdrExtTemp_save_BtnID" );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		ElementHelper.isElementPresent( GenericHelper.getORProperty( "PS_searchPanelId" ) );
		ButtonHelper.click( "ClearButton" );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		ButtonHelper.click( "SearchButton" );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		boolean isNewAddedXdrTempPresent = GridHelper.isValuePresent( "searchGrid", tempNm );
		assertTrue( isNewAddedXdrTempPresent, " Failed to save this template name:- '" + tempNm + "' with the error message:- " + LabelHelper.getText( "PS_Detail_xdrExtTemp_errorTxt_txtID" ) );
	}

}
