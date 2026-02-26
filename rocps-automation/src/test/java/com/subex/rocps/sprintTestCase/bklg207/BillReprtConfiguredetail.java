package com.subex.rocps.sprintTestCase.bklg207;

import static org.testng.Assert.assertTrue;

import java.util.Map;

import com.subex.automation.helpers.component.ButtonHelper;
import com.subex.automation.helpers.component.CheckBoxHelper;
import com.subex.automation.helpers.component.ComboBoxHelper;
import com.subex.automation.helpers.component.GenericHelper;
import com.subex.automation.helpers.component.GridHelper;
import com.subex.automation.helpers.component.TextBoxHelper;
import com.subex.automation.helpers.data.ValidationHelper;
import com.subex.rocps.automation.helpers.application.filters.PSSearchGridHelper;
import com.subex.rocps.automation.helpers.application.genericHelpers.PSGenericHelper;
import com.subex.rocps.automation.utils.ExcelHolder;
import com.subex.rocps.automation.utils.PSStringUtils;

public class BillReprtConfiguredetail
{

	protected Map<String, String> billReportConfigDetailMap = null;
	protected String billReportName;
	protected String[] billReportNameArr;
	protected String customBillParameter;
	protected String[] customBillParameterArr;
	protected String fileDelimiter;

	protected String clientPartition;

	protected String datasetName;
	protected String[] datasetNameArr;
	protected String writeFileHeaderFlag;
	protected String[] writeFileHeaderFlagArr;
	protected String component;
	protected String[] componentArr;
	protected String systemParameter;
	protected String[] systemParameterArr;
	int searchScreenWaitSec = 60;
	PSGenericHelper psGenericHelper = new PSGenericHelper();
	PSStringUtils psStringUtils = new PSStringUtils();

	public BillReprtConfiguredetail( Map<String, String> billReportConfigDetailMap ) throws Exception
	{

		this.billReportConfigDetailMap = billReportConfigDetailMap;
		initializeInstanceVariable( billReportConfigDetailMap );
		intialiseArray();
	}

	/*
	 * This method is for initialize the Veriables
	 */
	public void initializeInstanceVariable( Map<String, String> map ) throws Exception
	{
		billReportName = ExcelHolder.getKey( map, "ConfigName" );
		component = ExcelHolder.getKey( map, "Component" );
		customBillParameter = ExcelHolder.getKey( map, "CustomBillParameter" );
		fileDelimiter = ExcelHolder.getKey( map, "FileDelimiter" );
		datasetName = ExcelHolder.getKey( map, "DatasetName" );
		writeFileHeaderFlag = ExcelHolder.getKey( map, "WriteFileHeaderFlag" );
		systemParameter = ExcelHolder.getKey( map, "SystemParameter" );

	}

	protected void intialiseArray() throws Exception
	{
		billReportNameArr = psStringUtils.stringSplitFirstLevel( billReportName );
		datasetNameArr = psStringUtils.stringSplitFirstLevel( datasetName );
		writeFileHeaderFlagArr = psStringUtils.stringSplitFirstLevel( writeFileHeaderFlag );
		customBillParameterArr = psStringUtils.stringSplitFirstLevel( customBillParameter );
		systemParameterArr = psStringUtils.stringSplitFirstLevel( systemParameter );
	}

	/*
	 * This method is for configure bill Report Configuration
	 */
	public void configBillReportConf() throws Exception
	{

		confPanelDetails();
		systemParamConfDetails();
		customBillParameterConfDetails();
		billDatasetConfDetails();

	}
	/*
	 * This method is for configure Panel Values
	 */
	public void confPanelDetails() throws Exception
	{
		TextBoxHelper.type( "PSDetail_Bill_Report_Conf_Name_TextId", billReportName );

	}

	/*
	 * This method is for configure System Parameter
	 */
	public void systemParamConfDetails() throws Exception
	{
		ComboBoxHelper.select( "PSDetail_Bill_Report_Conf_Component_comboid", component );
		for ( int i = 0; i < systemParameterArr.length; i++ )
		{
			int row = GridHelper.getRowNumber( "PSDetail_Bill_Report_Conf_Sytem_parameter_gridId", systemParameterArr[i], "Parameter  Name" );
			GenericHelper.waitForLoadmask();
			GridHelper.updateGridCheckBox( "PSDetail_Bill_Report_Conf_Sytem_parameter_gridId", "PSDetail_Bill_Report_Conf_CheckBoxId", row, "Select", true );
			GenericHelper.waitForLoadmask();
		}

	}
	
	/*
	 * This method is for configure Custom Bill Parameter
	 */

	public void customBillParameterConfDetails() throws Exception

	{
		for ( int i = 0; i < customBillParameterArr.length; i++ )
		{
			ButtonHelper.click( "PSDetail_Bill_Report_Conf_CustomBillPar_AddButtonId" );
			GenericHelper.waitForLoadmask( searchScreenWaitSec );
			billParameterSelection( customBillParameterArr[i] );

		}
	}

	public void billDatasetConfDetails() throws Exception
	{

		ComboBoxHelper.select( "PSDetail_Bill_Report_Conf_File_delimiter_comboid", fileDelimiter );

		GenericHelper.waitForLoadmask( searchScreenWaitSec );

		if ( ValidationHelper.isTrue( writeFileHeaderFlag ) )

			CheckBoxHelper.check( "PSDetail_Bill_Report_Conf_Write_file_reader_checkBoxId" );

		for ( int i = 0; i < datasetNameArr.length; i++ )
		{
			ButtonHelper.click( "PSDetail_Bill_Report_Conf_DataSetAddButtonId" );
			billDataSetSelection( datasetNameArr[i] );
		}
	}

	public void billParameterSelection( String customBillParameter ) throws Exception
	{
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		psGenericHelper.waitforPopupHeaderElement( "Parameter Name" );
		ButtonHelper.click( "PSDetail_clearButtonId" );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		TextBoxHelper.type( "PSDetail_Bill_Report_Conf_Cust_Bill_Name_TextId", customBillParameter );
		GenericHelper.waitForLoadmask();
		ButtonHelper.click( "PSDeatil_SearchButtonId" );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		GridHelper.clickRow( "SearchGrid", customBillParameter, "Parameter Name" );
		ButtonHelper.click( "ok" );
		psGenericHelper.waitforPopupHeaderElementToDisappear( "Parameter Name" );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
	}

	public void billDataSetSelection( String datasetName ) throws Exception
	{
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		psGenericHelper.waitforPopupHeaderElement( "Dataset Name" );
		ButtonHelper.click( "PSDetail_clearButtonId" );
		PSSearchGridHelper.gridFilterSearchWithTextBox( "PSDetail_Bill_Report_Conf_DataSetId", datasetName, "Dataset Name" );
		GenericHelper.waitForLoadmask();
		ButtonHelper.click( "PSDeatil_SearchButtonId" );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		GridHelper.clickRow( "SearchGrid", datasetName, "Dataset Name" );
		ButtonHelper.click( "ok" );
		psGenericHelper.waitforPopupHeaderElementToDisappear( "Dataset Name" );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
	}

}
