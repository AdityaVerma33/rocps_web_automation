package com.subex.rocps.automation.helpers.application.bills.billParameter;

import java.util.Map;

import com.subex.automation.helpers.component.ComboBoxHelper;
import com.subex.automation.helpers.component.TextBoxHelper;
import com.subex.rocps.automation.helpers.application.genericHelpers.PSGenericHelper;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;
import com.subex.rocps.automation.utils.ExcelHolder;

public class BillParameterDetailImpl extends PSAcceptanceTest
{

	protected Map<String, String> billParameterDetailMap = null;
	protected String parameterName;
	protected String sourceType;
	protected String tableInstance;
	protected String clientPartition;
	protected String constantDataType;
	protected String constantValue;
	PSGenericHelper psGenericHelper = new PSGenericHelper();

	public BillParameterDetailImpl( Map<String, String> billParameterDetailMap ) throws Exception
	{

		this.billParameterDetailMap = billParameterDetailMap;
		initializeInstanceVariable( billParameterDetailMap );
	}

	/*
	 * This method is for initialize the Veriables
	 */
	public void initializeInstanceVariable( Map<String, String> map ) throws Exception
	{
		parameterName = ExcelHolder.getKey( map, "ParameterName" );
		sourceType = ExcelHolder.getKey( map, "SourceType" );
	}

	/*
	 * This method is for configure bill parameter 
	 */
	public void configBillParameter() throws Exception
	{

		configNamePanelDetails();
		configSourceTypeDetails();

	}

	/*
	 * This method is for configure Panel details
	 */
	protected void configNamePanelDetails() throws Exception
	{

		TextBoxHelper.type( "PSDetail_Bill_Parameter_BillParameterName_textId", parameterName );
		ComboBoxHelper.select( "PSDetail_Bill_Parameter_SourceType_comboId", sourceType );

	}

	/*
	 * This method is for Configure Source type 
	 */
	protected void configSourceTypeDetails() throws Exception
	{

		if ( sourceType.equals( "Table" ) )
		{
			BillPMTableSrcType billPMTableSrcType = new BillPMTableSrcType( billParameterDetailMap );
			billPMTableSrcType.configTableDetails();

		}
		else
		{
			BillPMConstantSrcType billPMConstantSrcType = new BillPMConstantSrcType( billParameterDetailMap );
			try
			{
				billPMConstantSrcType.configSourceTypeDetails( billParameterDetailMap );
			}
			catch ( Exception e )
			{

				e.printStackTrace();
			}
		}

	}

}
