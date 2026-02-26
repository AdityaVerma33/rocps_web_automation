package com.subex.rocps.automation.helpers.application.bills.billDataset;

import java.util.Map;

import com.subex.automation.helpers.component.TextBoxHelper;
import com.subex.rocps.automation.helpers.application.genericHelpers.PSGenericHelper;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;
import com.subex.rocps.automation.utils.ExcelHolder;

public class BillDatasetDetailImpl extends PSAcceptanceTest
{

	protected Map<String, String> billDatasetDetailMap = null;
	protected String datasetName;
	protected String fileName;
	protected String tableInstance;
	protected String clientPartition;
	protected String constantDataType;
	protected String constantValue;
	PSGenericHelper psGenericHelper = new PSGenericHelper();

	public BillDatasetDetailImpl( Map<String, String> billDatasetDetailMap ) throws Exception
	{

		this.billDatasetDetailMap = billDatasetDetailMap;
		initializeInstanceVariable( billDatasetDetailMap );
	}

	public void initializeInstanceVariable( Map<String, String> map ) throws Exception
	{
		datasetName = ExcelHolder.getKey( map, "DatasetName" );
		fileName = ExcelHolder.getKey( map, "FileName" );

	}

	/*
	 * This method is for configure bill dataset 
	 */
	public void configBillDataset() throws Exception
	{

		configNamePanelDetails();
		configTablePanelDetails();
	}

	/*
	 * This method is for configure Upper Panel details
	 */
	protected void configNamePanelDetails() throws Exception
	{

		TextBoxHelper.type( "PSBillDataset_Detail_BillDatasetNameID", datasetName );
		TextBoxHelper.type( "PSBillDataset_Detail_FileName", fileName );

	}

	/*
	 * This method is for Configure Table 
	 */
	protected void configTablePanelDetails() throws Exception
	{

		BillDSTableSrcType billDSTableSrcType = new BillDSTableSrcType( billDatasetDetailMap );
		billDSTableSrcType.configTableDetails();

	}

}
