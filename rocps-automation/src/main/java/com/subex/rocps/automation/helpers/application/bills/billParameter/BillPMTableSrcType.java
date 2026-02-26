package com.subex.rocps.automation.helpers.application.bills.billParameter;

import java.util.Map;

public class BillPMTableSrcType implements BillPmSourceTypeStrategy
{

	protected Map<String, String> billPMTableSrcTypeMap = null;

	@Override
	public void configSourceTypeDetails( Map<String, String> map ) throws Exception
	{
		configTableDetails();
	}

	public BillPMTableSrcType( Map<String, String> billPMTableSrcTypeMap )
	{
		this.billPMTableSrcTypeMap = billPMTableSrcTypeMap;

	}

	/*
	 * This method is for configure Table info
	 */
	protected void configTableDetails() throws Exception
	{
		BillPMSorcesDetail bpsource = new BillPMSorcesDetail( billPMTableSrcTypeMap );
		bpsource.configureSource();
		BillPMColumnsDetail bpColumn = new BillPMColumnsDetail( billPMTableSrcTypeMap );
		bpColumn.ConfigureColumnsTab();
		BillPmFiltersDetail bpFilter = new BillPmFiltersDetail( billPMTableSrcTypeMap );
		bpFilter.ConfigureFiltersTab();

	}

}
