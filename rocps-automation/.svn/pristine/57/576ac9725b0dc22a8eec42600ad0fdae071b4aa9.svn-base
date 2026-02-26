package com.subex.rocps.automation.helpers.application.bills.billDataset;

import java.util.Map;

import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;

public class BillDSTableSrcType extends PSAcceptanceTest
{

	protected Map<String, String> billDSTableSrcTypeMap = null;

	public void configSourceTypeDetails( Map<String, String> map ) throws Exception
	{
		configTableDetails();

	}

	public BillDSTableSrcType( Map<String, String> billDSTableSrcTypeMap )
	{
		this.billDSTableSrcTypeMap = billDSTableSrcTypeMap;

	}

	/*
	 * This method is for configure Table os Source, Column, Filters info
	 */
	protected void configTableDetails() throws Exception
	{
		BillDSSourcesDetail bdsource = new BillDSSourcesDetail( billDSTableSrcTypeMap );
		bdsource.configureSource();
		BillDSColumnsDetail bdColumn = new BillDSColumnsDetail( billDSTableSrcTypeMap );
		bdColumn.ConfigureColumnsTab();
		BillDsFiltersDetail bdFilter = new BillDsFiltersDetail( billDSTableSrcTypeMap );
		bdFilter.ConfigureFiltersTab();

	}

}
