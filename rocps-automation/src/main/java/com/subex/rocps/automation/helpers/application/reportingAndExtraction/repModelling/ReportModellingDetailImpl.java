package com.subex.rocps.automation.helpers.application.reportingAndExtraction.repModelling;

import java.util.Map;

import com.subex.automation.helpers.component.ComboBoxHelper;
import com.subex.automation.helpers.component.GenericHelper;
import com.subex.automation.helpers.component.GridHelper;
import com.subex.automation.helpers.component.TextBoxHelper;
import com.subex.automation.helpers.data.ValidationHelper;
import com.subex.automation.helpers.report.Log4jHelper;
import com.subex.automation.helpers.util.FailureHelper;
import com.subex.rocps.automation.helpers.application.genericHelpers.PSDataComponentHelper;
import com.subex.rocps.automation.helpers.application.genericHelpers.PSGenericHelper;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;
import com.subex.rocps.automation.utils.ExcelHolder;
import com.subex.rocps.automation.utils.PSStringUtils;

public class ReportModellingDetailImpl extends PSAcceptanceTest
{

	protected Map<String, String> repModellingDetailsMap = null;
	protected String repModellingNm;
	protected String prefix;
	protected String summaryType;
	protected String summaryName;
	protected String schema;
	protected String fieldNm;
	protected String fieldNmArr[];
	protected String displayNm;
	protected String displayNmArr[];
	protected String dataType;
	protected String dataTypeArr[];
	protected String summaryField;
	protected String summaryFieldArr[];
	protected String key;
	protected String keyArr[];
	protected String visible;
	protected String visibleArr[];
	protected String search;
	protected String searchArr[];
	protected String mandatoryFlg;
	protected String mandatoryFlgArr[];

	PSGenericHelper psGenericHelper = new PSGenericHelper();
	PSStringUtils psStringUtils = new PSStringUtils();
	PSDataComponentHelper psDataComponentHelper = new PSDataComponentHelper();

	/*
	 * Constructor
	 */
	public ReportModellingDetailImpl( Map<String, String> repModellingDetailsMap )
	{
		this.repModellingDetailsMap = repModellingDetailsMap;
	}

	/*
	 * This method is for initialize variable
	 */
	protected void initializeBasicVariable( Map<String, String> map ) throws Exception
	{
		repModellingNm = ExcelHolder.getKey( map, "ReportModellingName" );
		prefix = ExcelHolder.getKey( map, "Prefix" );
		summaryType = ExcelHolder.getKey( map, "SummaryType" );
		summaryName = ExcelHolder.getKey( map, "SummaryName" );
		schema = ExcelHolder.getKey( map, "Schema" );
	}

	/*
	 * This method is for initialize variable
	 */
	protected void initializeRepModColVariable( Map<String, String> map ) throws Exception
	{
		fieldNm = ExcelHolder.getKey( map, "FieldName" );
		displayNm = ExcelHolder.getKey( map, "DisplayName" );
		dataType = ExcelHolder.getKey( map, "DataType" );
		summaryField = ExcelHolder.getKey( map, "SummaryField" );
		key = ExcelHolder.getKey( map, "Key" );
		visible = ExcelHolder.getKey( map, "Visible" );
		search = ExcelHolder.getKey( map, "Search" );
		mandatoryFlg = ExcelHolder.getKey( map, "MandatoryFlg" );
	}

	/*
	 * This method is for configure Report Modelling
	 */
	public void configReportModelling() throws Exception
	{
		configBasicDetails();
		configRepModellingColGrid();
		saveReportModelling();
	}

	/*
	 * This method is for modify Report Modelling
	 */
	public void modifyReportModelling() throws Exception
	{
		modifyBasicDetails();
		configRepModellingColGrid();
		saveReportModelling();
	}

	/*
	 * This method is for configure Report Modelling basic details
	 */
	protected void configBasicDetails() throws Exception
	{
		initializeBasicVariable( repModellingDetailsMap );
		TextBoxHelper.type( "PS_Detail_repModelling_name_txtId", repModellingNm );
		TextBoxHelper.type( "PS_Detail_repModelling_prefix_txtId", prefix );
		ComboBoxHelper.select( "PS_Detail_repModelling_summaryType_comboId", summaryType );
		ComboBoxHelper.select( "PS_Detail_repModelling_summaryName_comboId", summaryName );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		psDataComponentHelper.checkComboBoxDisabled( "PS_Detail_repModelling_schema_comboId", schema );
	}

	/*
	 * This method is for modify Report Modelling basic details
	 */
	protected void modifyBasicDetails() throws Exception
	{
		initializeBasicVariable( repModellingDetailsMap );
		assertEquals( TextBoxHelper.getValue( "PS_Detail_repModelling_name_txtId" ), repModellingNm, "Reporting Modelling name is not matched" );
		psDataComponentHelper.modifyTextBox( "PS_Detail_repModelling_prefix_txtId", prefix );
		psDataComponentHelper.modifyComboBox( "PS_Detail_repModelling_summaryType_comboId", summaryType );
		psDataComponentHelper.modifyComboBox( "PS_Detail_repModelling_summaryName_comboId", summaryName );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		psDataComponentHelper.checkComboBoxDisabled( "PS_Detail_repModelling_schema_comboId", schema );
	}

	/*
	 * This method is for configure Report Modelling column  grid
	 */
	protected void configRepModellingColGrid() throws Exception
	{
		initializeRepModColVariable( repModellingDetailsMap );
		try
		{
			fieldNmArr = psStringUtils.stringSplitFirstLevel( fieldNm );
			displayNmArr = psStringUtils.stringSplitFirstLevel( displayNm );
			dataTypeArr = psStringUtils.stringSplitFirstLevel( dataType );
			summaryFieldArr = psStringUtils.stringSplitFirstLevel( summaryField );
			keyArr = psStringUtils.stringSplitFirstLevel( key );
			visibleArr = psStringUtils.stringSplitFirstLevel( visible );
			searchArr = psStringUtils.stringSplitFirstLevel( search );
			mandatoryFlgArr = psStringUtils.stringSplitFirstLevel( mandatoryFlg );
			for ( int i = 0; i < fieldNmArr.length; i++ )
			{
				int row = GridHelper.getRowNumber( "PS_Detail_repModellingColGrid_gridId", fieldNmArr[i], "Field  Name" );
				GridHelper.clickRow( "PS_Detail_repModellingColGrid_gridId", row, "Field  Name" );
				psDataComponentHelper.verifyGridCellValue( "PS_Detail_repModellingColGrid_gridId", row, "Field  Name", fieldNmArr[i] );
				if ( ValidationHelper.isNotEmpty( displayNm ) && ValidationHelper.isNotEmpty( displayNmArr[i] ) )
					psDataComponentHelper.verifyGridCellValue( "PS_Detail_repModellingColGrid_gridId", row, "Display  Name", displayNmArr[i] );
				if ( ValidationHelper.isNotEmpty( dataType ) && ValidationHelper.isNotEmpty( dataTypeArr[i] ) )
					psDataComponentHelper.verifyGridCellValue( "PS_Detail_repModellingColGrid_gridId", row, "Data  Type", dataTypeArr[i] );
				selectSummaryFieldColValue( row, summaryFieldArr[i] );
				if ( ValidationHelper.isNotEmpty( key ) && ValidationHelper.isNotEmpty( keyArr[i] ) )
					psDataComponentHelper.verifyGridCheckBox( "PS_Detail_repModellingColGrid_gridId", fieldNmArr[i], keyArr[i], "Key" );
				if ( ValidationHelper.isNotEmpty( visible ) && ValidationHelper.isNotEmpty( visibleArr[i] ) )
					GridHelper.updateGridCheckBox( "PS_Detail_repModellingColGrid_gridId", row, "Visible", visibleArr[i] );
				if ( ValidationHelper.isNotEmpty( search ) && ValidationHelper.isNotEmpty( searchArr[i] ) )
					GridHelper.updateGridCheckBox( "PS_Detail_repModellingColGrid_gridId", row, "Search", searchArr[i] );
				if ( ValidationHelper.isNotEmpty( mandatoryFlg ) && ValidationHelper.isNotEmpty( mandatoryFlgArr[i] ) )
					psDataComponentHelper.verifyGridCheckBox( "PS_Detail_repModellingColGrid_gridId", fieldNmArr[i], mandatoryFlgArr[i], "Mandatory Fl" );

			}
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}

	}

	/*
	 * This method is for select summary Filed Column values
	 */
	protected void selectSummaryFieldColValue( int row, String summaryFieldValue ) throws Exception
	{
		String gridColHeader = "Summary  Field";
		GridHelper.clickRow( "PS_Detail_repModellingColGrid_gridId", row, gridColHeader );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		if ( ValidationHelper.isNotEmpty( summaryFieldValue ) && !ComboBoxHelper.getValue( "PS_Detail_repModellingColGrid_summField_comboId" ).equals( summaryFieldValue ) )
			psDataComponentHelper.selectComboBoxVal( "PS_Detail_repModellingColGrid_summField_comboId", summaryFieldValue );
	}

	/*
	 * This method is for save Report Modelling
	 */
	protected void saveReportModelling() throws Exception
	{
		psGenericHelper.detailSave( "PS_Detail_repModelling_save_btnId", repModellingNm, "Name" );
	}
}
