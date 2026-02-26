package com.subex.rocps.automation.helpers.application.reportingAndExtraction.reportandExtDefn;

import java.util.Map;

import com.subex.rocps.automation.helpers.application.genericHelpers.PSDataComponentHelper;
import com.subex.rocps.automation.helpers.application.genericHelpers.PSGenericHelper;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;
import com.subex.rocps.automation.utils.ExcelHolder;

import com.subex.automation.helpers.component.ComboBoxHelper;
import com.subex.automation.helpers.component.TextBoxHelper;

public class GenericUsageReports extends PSAcceptanceTest implements ReportAndExtTypeStrategy
{
	protected Map<String, String> genericUsageRepMap = null;
	protected String maxRepRange;
	protected String maxRepLookback;
	protected String dateTimeFormat;
	protected String reportDP;
	protected String roundingMode;

	PSGenericHelper psGenericHelper = new PSGenericHelper();
	PSDataComponentHelper psDataComponentHelper = new PSDataComponentHelper();

	/**Constructor
	 * @param genericUsageRepMap
	 */
	public GenericUsageReports( Map<String, String> genericUsageRepMap )
	{

		this.genericUsageRepMap = genericUsageRepMap;
	}

	/*This method is for initialize variable*/
	private void initializeVariable( Map<String, String> map ) throws Exception
	{
		maxRepRange = ExcelHolder.getKey( map, "MaxReportRange" );
		maxRepLookback = ExcelHolder.getKey( map, "MaxReportLookback" );
		dateTimeFormat = ExcelHolder.getKey( map, "DateTimeFormat" );
		reportDP = ExcelHolder.getKey( map, "ReportDP" );
		roundingMode = ExcelHolder.getKey( map, "RoundingMode" );

	}

	/*
	 * This method is for configure report and extract type parameters
	 */
	@Override
	public void configReportAndExtTypeParameters() throws Exception
	{
		initializeVariable( genericUsageRepMap );
		TextBoxHelper.type( "PS_Detail_reportAndExtDefn_genUsgReport_maxRepRange_txtId", maxRepRange );
		TextBoxHelper.type( "PS_Detail_reportAndExtDefn_genUsgReport_maxRepLookback_txtId", maxRepLookback );
		ComboBoxHelper.select( "PS_Detail_reportAndExtDefn_genUsgReport_dtTimeFormat_combId", dateTimeFormat );
		ComboBoxHelper.select( "PS_Detail_reportAndExtDefn_genUsgReport_reportDP_combId", reportDP );
		ComboBoxHelper.select( "PS_Detail_reportAndExtDefn_genUsgReport_roundingMd_combId", roundingMode );

	}

	/*
	 * This method is for modify report and extract type parameters
	 */
	@Override
	public void modifyReportAndExtTypeParameters() throws Exception
	{
		psDataComponentHelper.modifyTextBox( "PS_Detail_reportAndExtDefn_genUsgReport_maxRepRange_txtId", maxRepRange );
		psDataComponentHelper.modifyTextBox( "PS_Detail_reportAndExtDefn_genUsgReport_maxRepLookback_txtId", maxRepLookback );
		psDataComponentHelper.modifyComboBox( "PS_Detail_reportAndExtDefn_genUsgReport_dtTimeFormat_combId", dateTimeFormat );
		psDataComponentHelper.modifyComboBox( "PS_Detail_reportAndExtDefn_genUsgReport_reportDP_combId", reportDP );
		psDataComponentHelper.modifyComboBox( "PS_Detail_reportAndExtDefn_genUsgReport_roundingMd_combId", roundingMode );

	}

}
