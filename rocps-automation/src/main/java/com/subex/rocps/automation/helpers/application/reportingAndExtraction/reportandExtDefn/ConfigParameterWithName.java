package com.subex.rocps.automation.helpers.application.reportingAndExtraction.reportandExtDefn;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.subex.rocps.automation.helpers.application.genericHelpers.PSDataComponentHelper;
import com.subex.rocps.automation.helpers.application.genericHelpers.PSGenericHelper;
import com.subex.rocps.automation.helpers.application.products.productBundles.product.ProductUtil;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;
import com.subex.rocps.automation.utils.ExcelHolder;
import com.subex.rocps.automation.utils.PSStringUtils;

import com.subex.automation.helpers.component.ButtonHelper;
import com.subex.automation.helpers.component.GenericHelper;
import com.subex.automation.helpers.component.GridHelper;
import com.subex.automation.helpers.data.ValidationHelper;
import com.subex.automation.helpers.report.Log4jHelper;
import com.subex.automation.helpers.util.FailureHelper;

public class ConfigParameterWithName extends PSAcceptanceTest implements ParametersStrategy
{
	protected String repAndExtParameterNm;
	protected String repAndExtParameterNmArr[];
	protected String repAndExtParameterType;
	protected String repAndExtParameterTypeArr[];
	protected String repAndExtParameterMand;
	protected String repAndExtParameterMandArr[];
	protected Map<String, String> configParamWithNameMap = null;
	PSGenericHelper psGenericHelper = new PSGenericHelper();
	PSStringUtils psStringUtils = new PSStringUtils();
	ConfigParamtersWithFixedName configParamtersWithType = new ConfigParamtersWithFixedName();
	PSDataComponentHelper psDataComponentHelper = new PSDataComponentHelper();

	/**Constructor
	 * @param configParamWithNameMap
	 */
	public ConfigParameterWithName( Map<String, String> configParamWithNameMap )
	{
		this.configParamWithNameMap = configParamWithNameMap;
	}

	/*This method is for initialize  parameter variable*/
	private void initializeVarParameter( Map<String, String> map ) throws Exception
	{
		repAndExtParameterNm = ExcelHolder.getKey( map, "ParameterName" );
		repAndExtParameterType = ExcelHolder.getKey( map, "ParameterType" );
		repAndExtParameterMand = ExcelHolder.getKey( map, "ParameterMandatory" );
	}

	/*
	 * This method is for configure Report Extracte paramters panel*/
	@Override
	public void configReportExtParameters() throws Exception
	{
		try
		{
			initializeVarParameter( configParamWithNameMap );
			if ( ValidationHelper.isNotEmpty( repAndExtParameterNm ) )
			{
				repAndExtParameterNmArr = psStringUtils.stringSplitFirstLevel( repAndExtParameterNm );
				repAndExtParameterTypeArr = psStringUtils.stringSplitFirstLevel( repAndExtParameterType );
				repAndExtParameterMandArr = psStringUtils.stringSplitFirstLevel( repAndExtParameterMand );
				List<String> getKeysOfParamterFieldGrid = configParamtersWithType.getKeysOfParamterFieldGrid();
				for ( int i = 0; i < repAndExtParameterNmArr.length; i++ )
				{
					Map<String, ArrayList<String>> mapOfParameterFieldGrid = psDataComponentHelper.getGridColumnValues( "PS_Detail_reportAndExtDefn_parameters_gridID", "grid_column_header_undefined_", getKeysOfParamterFieldGrid );
					boolean isParameterNmPresentInGrid = psDataComponentHelper.isDataPresentInGrid( "PS_Detail_reportAndExtDefn_parameters_gridID", mapOfParameterFieldGrid, "Name", repAndExtParameterNmArr[i] );
					if ( !isParameterNmPresentInGrid )
						configParamtersWithType.configParamterField( ( i + 1 ), repAndExtParameterNmArr[i], repAndExtParameterTypeArr[i], repAndExtParameterMandArr[i] );
					else
						Log4jHelper.logInfo( "This parameter name value: " + repAndExtParameterNmArr[i] + " is already present in this grid:" + GenericHelper.getORProperty( "PS_Detail_reportAndExtDefn_parameters_gridID" ) );
				}
			}
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

}
