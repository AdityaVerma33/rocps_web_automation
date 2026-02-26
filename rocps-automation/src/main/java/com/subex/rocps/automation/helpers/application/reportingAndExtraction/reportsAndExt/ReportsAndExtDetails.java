package com.subex.rocps.automation.helpers.application.reportingAndExtraction.reportsAndExt;

import java.util.HashMap;
import java.util.Map;

import com.subex.rocps.automation.helpers.application.filters.DataSelectionHelper;
import com.subex.rocps.automation.helpers.application.filters.PSSearchGridHelper;
import com.subex.rocps.automation.helpers.application.genericHelpers.PSGenericHelper;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;
import com.subex.rocps.automation.utils.ExcelHolder;
import com.subex.rocps.automation.utils.PSStringUtils;

import com.subex.automation.helpers.component.ButtonHelper;
import com.subex.automation.helpers.component.ComboBoxHelper;
import com.subex.automation.helpers.component.ElementHelper;
import com.subex.automation.helpers.component.EntityComboHelper;
import com.subex.automation.helpers.component.GenericHelper;
import com.subex.automation.helpers.component.GridHelper;
import com.subex.automation.helpers.component.SearchGridHelper;
import com.subex.automation.helpers.component.TextBoxHelper;
import com.subex.automation.helpers.data.ValidationHelper;
import com.subex.automation.helpers.report.Log4jHelper;

public class ReportsAndExtDetails extends PSAcceptanceTest
{
	protected Map<String, String> reportsAndExtDetailsMap = null;
	protected String repAndExtDefnName;
	protected String parameterFieldsName;
	protected String parameterLabelsName;
	protected String parameterLabelsNameArr[];
	protected String parameterFieldsNameArr[];
	static PSGenericHelper psGenericHelper = new PSGenericHelper();
	DataSelectionHelper dataSelectionHelper = new DataSelectionHelper();
	PSStringUtils psStringUtils = new PSStringUtils();
	ParamtersSelection paramtersSelection = new ParamtersSelection();

	/**Constructor
	 * 
	 * @param reportsAndExtDetailsMap
	 */
	public ReportsAndExtDetails( Map<String, String> reportsAndExtDetailsMap )
	{

		this.reportsAndExtDetailsMap = reportsAndExtDetailsMap;
	}

	/*This method is for initialize variable */
	private void initializeVariable( Map<String, String> map ) throws Exception
	{
		repAndExtDefnName = ExcelHolder.getKey( map, "RepAndExtDefinitionName" );
		parameterFieldsName = ExcelHolder.getKey( map, "ParamterFieldsName" );
		parameterLabelsName = ExcelHolder.getKey( map, "ParameterFieldLabel" );
	}

	/*This method is for configure reports and extracts*/
	public void configureRepAndExtracts() throws Exception
	{
		initializeVariable( reportsAndExtDetailsMap );
		reportAndExtDefnEnititySearch( repAndExtDefnName );
		configureParameters();
		psGenericHelper.detailSave( "PS_Detail_reportAndExt_save_btnId", repAndExtDefnName, "Report and Extract Definition" );

	}
	/*This method is for configure paramters*/
	private void configureParameters() throws Exception
	{
		if ( ValidationHelper.isNotEmpty( parameterFieldsName ) )
		{
			ElementHelper.waitForElement( GenericHelper.getORProperty( "PS_Detail_reportAndExt_parameterLabel_xpath" ), searchScreenWaitSec );
			parameterFieldsNameArr = psStringUtils.stringSplitFirstLevel( parameterFieldsName );
			parameterLabelsNameArr = psStringUtils.stringSplitFirstLevel( parameterLabelsName );
			for ( int i = 0; i < parameterFieldsNameArr.length; i++ )
			{
				String paramFieldvalue = ExcelHolder.getKey( reportsAndExtDetailsMap, parameterFieldsNameArr[i] );
				paramtersSelection.configureParameters( parameterLabelsNameArr[i], paramFieldvalue );
			}

		}

	}

	/*This method is for report and extract definition entity search*/
	private void reportAndExtDefnEnititySearch( String reportAndExtDefnNm ) throws Exception
	{
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		psGenericHelper.waitforEntityElement();
		repAndExtDefnEntitySearch( "PS_Detail_reportAndExt_repExtDfn_entityId", reportAndExtDefnNm, "PS_Detail_reportAndExt_Details_xpath" );

	}

	/*This method is for report and extract definition entity search*/
	public static void repAndExtDefnEntitySearch( String entiTyId, String reportAndExtDefnNm, String detailsPageXpath ) throws Exception
	{
		EntityComboHelper.clickEntityIcon( entiTyId );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		psGenericHelper.waitforPopupHeaderElement( "Name" );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		PSSearchGridHelper.gridFilterSearchWithTextBox( "PS_Detail_reportAndExtDefn_RepExtDefname_textID", reportAndExtDefnNm, "Name" );
		boolean isrepExtDefnNmPresent = GridHelper.isValuePresent( "Detail_popUpWindowId", "SearchGrid", reportAndExtDefnNm, "Name" );
		assertTrue( isrepExtDefnNmPresent, "Report And Extract Definition  with name :'" + reportAndExtDefnNm + "'  is not found in 'Report And Extracts Definition Search' popupScreen " );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		GridHelper.clickRow( "Detail_popUpWindowId", "SearchGrid", reportAndExtDefnNm, "Name" );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		ButtonHelper.clickIfEnabled( "Detail_popUpWindowId", "OKButton" );
		GenericHelper.waitForLoadmask();
		psGenericHelper.waitforPopupHeaderElementToDisappear( "Name" );
		ElementHelper.waitForElement( detailsPageXpath, searchScreenWaitSec );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );

	}
}
