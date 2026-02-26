package com.subex.rocps.automation.helpers.application.reportingAndExtraction.repAndExtSchedule;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.subex.rocps.automation.helpers.application.genericHelpers.PSDataComponentHelper;
import com.subex.rocps.automation.helpers.application.genericHelpers.PSGenericHelper;
import com.subex.rocps.automation.helpers.application.reportingAndExtraction.reportsAndExt.ParamtersSelection;
import com.subex.rocps.automation.helpers.application.reportingAndExtraction.reportsAndExt.ReportsAndExtDetails;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;
import com.subex.rocps.automation.utils.ExcelHolder;
import com.subex.rocps.automation.utils.PSStringUtils;

import com.subex.automation.helpers.component.ButtonHelper;
import com.subex.automation.helpers.component.ComboBoxHelper;
import com.subex.automation.helpers.component.ElementHelper;
import com.subex.automation.helpers.component.GenericHelper;
import com.subex.automation.helpers.component.GridHelper;
import com.subex.automation.helpers.component.MouseHelper;
import com.subex.automation.helpers.data.ValidationHelper;
import com.subex.automation.helpers.report.Log4jHelper;
import com.subex.automation.helpers.util.FailureHelper;

public class ReportAndExtInstance extends PSAcceptanceTest
{
	protected Map<String, String> repAndExtInstMap = null;
	protected String inst_RepAndExtDfn;
	protected String inst_RepAndExtLookBck;
	protected String parameterFieldsName;
	protected String parameterLabelsName;
	protected String parameterLabelsNameArr[];
	protected String parameterFieldsNameArr[];

	PSGenericHelper psGenericHelper = new PSGenericHelper();
	PSStringUtils psStringUtils = new PSStringUtils();
	PSDataComponentHelper psDataComponentHelper = new PSDataComponentHelper();
	ParamtersSelection paramtersSelection = new ParamtersSelection();

	/**Constructor
	 * @param repAndExtInstMap
	 */
	public ReportAndExtInstance( Map<String, String> repAndExtInstMap )
	{
		this.repAndExtInstMap = repAndExtInstMap;
	}

	/*Method is for initialize variable Report and extract Instance panel*/
	private void initializeVarInstance( Map<String, String> map ) throws Exception
	{
		inst_RepAndExtDfn = ExcelHolder.getKey( map, "Instance_RepAndExtDefn" );
		inst_RepAndExtLookBck = ExcelHolder.getKey( map, "Instance_RepAndExtLookBack" );
		parameterFieldsName = ExcelHolder.getKey( map, "ParamterFieldsName" );
		parameterLabelsName = ExcelHolder.getKey( map, "ParameterFieldLabel" );
	}

	/*
	 * This method is for configure Report Extracte Instance panel*/
	public void configReportExtInstance( int row ) throws Exception
	{
		try
		{
			initializeVarInstance( repAndExtInstMap );

			List<String> getKeysOfReportDefnGrid = getKeysOfReportDefnGrid();
			Map<String, ArrayList<String>> mapOfReportDefnGrid = psDataComponentHelper.getGridColumnValues( "PS_Detail_repAndExtSch_Instance_gridId", "grid_column_header_undefined_", getKeysOfReportDefnGrid );
			boolean isRepDefnNmPresentInGrid = psDataComponentHelper.isDataPresentInGrid( "PS_Detail_repAndExtSch_Instance_gridId", mapOfReportDefnGrid, "Report and  Extract  Definition", inst_RepAndExtDfn );
			if ( !isRepDefnNmPresentInGrid )
				configReportExtInstanceField( row, inst_RepAndExtDfn, inst_RepAndExtLookBck );
			else
				Log4jHelper.logInfo( "This parameter name value: " + inst_RepAndExtDfn + " is already present in this grid:" + GenericHelper.getORProperty( "PS_Detail_repAndExtSch_Instance_gridId" ) );

		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

	/*Method is for configure of   Report and extract instance field  details*/
	private void configReportExtInstanceField( int row, String repExtDfnNm, String repExtLookBck ) throws Exception
	{
		String gridId = GenericHelper.getORProperty( "PS_Detail_repAndExtSch_Instance_gridId" );
		ButtonHelper.click( "PS_Detail_repAndExtSch_InstAdd_btnId" );
		GridHelper.clickRow( gridId, row, "Report and  Extract  Definition" );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		waitforEntityElement();
		ReportsAndExtDetails.repAndExtDefnEntitySearch( "PS_Detail_reportAndExtSch_repExtDefn_entityId", repExtDfnNm, "PS_Detail_reportAndExtSch_Details_xpath" );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		GridHelper.clickRow( gridId, row, "Report and  Extract  Look  Back" );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		GridHelper.updateGridTextBox( gridId, "PS_Detail_reportAndExtSch_repExtLookBack_textId", row, "Report and  Extract  Look  Back", repExtLookBck );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		MouseHelper.click( "PS_Detail_repAndExtSch_Instance_gridmenu_clickXpath" );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		GridHelper.clickRow( gridId, row, "Report and  Extract  Look  Back" );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		configureParameters();
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		MouseHelper.click( "PS_Detail_repAndExtSch_Instance_gridmenu_clickXpath" );

	}

	/*This method is for configure paramters*/
	private void configureParameters() throws Exception
	{
		if ( ValidationHelper.isNotEmpty( parameterFieldsName ) )
		{
			ElementHelper.waitForElement( GenericHelper.getORProperty( "PS_Detail_repAndExtSch_parameter_labelXpath" ), searchScreenWaitSec );
			parameterFieldsNameArr = psStringUtils.stringSplitFirstLevel( parameterFieldsName );
			parameterLabelsNameArr = psStringUtils.stringSplitFirstLevel( parameterLabelsName );
			for ( int i = 0; i < parameterFieldsNameArr.length; i++ )
			{
				String paramFieldvalue = ExcelHolder.getKey( repAndExtInstMap, parameterFieldsNameArr[i] );
				paramtersSelection.configureParameters( parameterLabelsNameArr[i], paramFieldvalue );
			}
			GenericHelper.waitForLoadmask( searchScreenWaitSec );
			MouseHelper.click( "PS_Detail_repAndExtSch_Instance_gridmenu_clickXpath" );

		}

	}

	//Report Definition grid columns keys
	private List<String> getKeysOfReportDefnGrid()
	{
		List<String> listColumn = new ArrayList<String>();
		listColumn.add( "Report and  Extract  Definition" );
		return listColumn;
	}

	/*Method is for wait for entity element*/
	private void waitforEntityElement() throws Exception
	{
		String entityXpath = "//table[@id='rocpsInterfaceTblEditor']//div[contains(@id,'trigger')]";
		ElementHelper.waitForElement( entityXpath, searchScreenWaitSec );
		ElementHelper.waitForClickableElement( entityXpath, searchScreenWaitSec );
	}
}
