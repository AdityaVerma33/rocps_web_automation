package com.subex.rocps.automation.helpers.application.reportingAndExtraction.reportandExtDefn;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

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

public class ConfigParamtersWithFixedName extends PSAcceptanceTest implements ParametersStrategy
{
	protected String repAndExtParameterType;
	protected String repAndExtParameterTypeArr[];
	protected String repAndExtParameterMand;
	protected String repAndExtParameterMandArr[];
	protected Map<String, String> configParamWithTypeMap = null;
	PSGenericHelper psGenericHelper = new PSGenericHelper();
	PSStringUtils psStringUtils = new PSStringUtils();

	/**
	 * Default Constructor
	 */
	public ConfigParamtersWithFixedName()
	{
		
	}

	/**Constructor
	 * @param configParamWithTypeMap
	 */
	public ConfigParamtersWithFixedName( Map<String, String> configParamWithTypeMap )
	{
		this.configParamWithTypeMap = configParamWithTypeMap;
	}

	/*This method is for initialize  parameter variable*/
	private void initializeVarParameter( Map<String, String> map ) throws Exception
	{
		repAndExtParameterType = ExcelHolder.getKey( map, "ParameterType" );
		repAndExtParameterMand = ExcelHolder.getKey( map, "ParameterMandatory" );
	}

	@Override
	/*
	 * This method is for configure Report Extracte paramters panel*/
	public void configReportExtParameters() throws Exception
	{
		try
		{
			initializeVarParameter( configParamWithTypeMap );
			if ( ValidationHelper.isNotEmpty( repAndExtParameterType ) )
			{
				repAndExtParameterTypeArr = psStringUtils.stringSplitFirstLevel( repAndExtParameterType );
				repAndExtParameterMandArr = psStringUtils.stringSplitFirstLevel( repAndExtParameterMand );
				List<String> getKeysOfParamterFieldGrid = getKeysOfParamterFieldGrid();
				Map<String, String> mapOfParametersTypeNm = loadTypeNameOfParamters();
				for ( int i = 0; i < repAndExtParameterTypeArr.length; i++ )
				{
					String paramName = getNameOfParameters( mapOfParametersTypeNm, repAndExtParameterTypeArr[i] );
					Map<String, ArrayList<String>> mapOfParameterFieldGrid = ProductUtil.getGridColumnValues( "PS_Detail_reportAndExtDefn_parameters_gridID", "grid_column_header_undefined_", getKeysOfParamterFieldGrid );
					boolean isParameterNmPresentInGrid = ProductUtil.isDataPresentInGrid( "PS_Detail_reportAndExtDefn_parameters_gridID", mapOfParameterFieldGrid, "Name", paramName );
					if ( !isParameterNmPresentInGrid )
						configParamterField( ( i + 1 ), paramName, repAndExtParameterTypeArr[i], repAndExtParameterMandArr[i] );
					else
						Log4jHelper.logInfo( "This parameter name value: " + paramName + " is already present in this grid:" + GenericHelper.getORProperty( "PS_Detail_reportAndExtDefn_parameters_gridID" ) );
				}
			}
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

	/*This method is for configure Report And Extract Parameters Field*/
	public void configParamterField( int row, String name, String type, String mandatoryFlg ) throws Exception
	{
		ButtonHelper.click( "PS_Detail_reportAndExtDefn_parameters_add_btnID" );
		if ( ValidationHelper.isNotEmpty( name ) )
			GridHelper.updateGridTextBox( "PS_Detail_reportAndExtDefn_parameters_gridID", "PS_Detail_reportAndExtDefn_parameters_name_txtID", row, "Name", name );
		if ( ValidationHelper.isNotEmpty( type ) )
			GridHelper.updateGridComboBox( "PS_Detail_reportAndExtDefn_parameters_gridID", "PS_Detail_reportAndExtDefn_parameters_type_comboId", row, "Type", type );
		if ( ValidationHelper.isTrue( mandatoryFlg ) )
			GridHelper.updateGridCheckBox( "PS_Detail_reportAndExtDefn_parameters_gridID", "PS_Detail_reportAndExtDefn_parameters_mandatory_chckBxId", row, "Mandatory", mandatoryFlg );
	}

	//Paramters grid columns keys
	public List<String> getKeysOfParamterFieldGrid()
	{
		List<String> listColumn = new ArrayList<String>();
		listColumn.add( "Name" );
		return listColumn;
	}

	//Paramters key->Type Value->Name
	public Map<String, String> loadTypeNameOfParamters()
	{
		Map<String, String> hmap = new HashMap<String, String>();
		hmap.put( "Day Group", "Day Group" );
		hmap.put( "Image", "Image" );
		hmap.put( "Role", "Role" );
		hmap.put( "Stream", "Stream" );
		hmap.put( "User", "User" );
		hmap.put( "Country", "Country" );
		hmap.put( "Currency", "Currency" );
		hmap.put( "Band", "Band" );
		hmap.put( "Element", "Element" );
		hmap.put( "Route", "Route" );
		hmap.put( "Tariff Class", "Tariff Class" );
		hmap.put( "Tariff", "Tariff" );
		hmap.put( "Tariff Period", "Tariff Period" );
		hmap.put( "Tariff Metric Type", "Tariff Metric Type" );
		hmap.put( "Switch", "Switch" );
		hmap.put( "Account", "Account" );
		hmap.put( "Event Type", "Event Type" );
		hmap.put( "Bill Profile Type", "Bill Profile Type" );
		hmap.put( "Report Type", "Report Type" );
		hmap.put( "Line Of Business", "Line Of Business" );
		hmap.put( "Account Category", "Account Category" );
		hmap.put( "*Date", "Date" );
		hmap.put( "*String", "String" );
		hmap.put( "*FromDate", "From Date" );
		hmap.put( "*ToDate", "To Date" );

		return hmap;
	}

	/*
	 * This method is used to get the name of paramters from key of Parmater  type
	 */
	private String getNameOfParameters( Map<String, String> mapOfParameters, String type )

	{
		Optional<String> optParamName = mapOfParameters.entrySet().stream().filter( x -> x.getKey().equals( type ) ).map( Map.Entry::getValue ).findFirst();
		assertTrue( optParamName.isPresent(), "Parameters Type is not present in Parameters Grid with type: " + type );
		return optParamName.get();
	}

}
