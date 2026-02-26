package com.subex.rocps.automation.helpers.application.reportingAndExtraction.reportandExtDefn.Comp;

import java.util.Map;

import com.subex.rocps.automation.helpers.application.reportingAndExtraction.reportandExtDefn.ReportAndExtDefnUtil;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;
import com.subex.rocps.automation.utils.ExcelHolder;

import com.subex.automation.helpers.component.PropertyGridHelper;

public class UsgRevCostOverviewRepComp extends PSAcceptanceTest implements ReportAndExtComponentStrategy

{
	protected Map<String, String> usgRevCostOverviewRepCompMap = null;
	protected String mappingConfigNm;
	protected String repAndExtEmailFormat;
	protected String repAndExtEmailSubject;
	
	ReportAndExtDefnUtil reportExtDefnUtil = new ReportAndExtDefnUtil();

	/**Constructor
	 * @param usgRevCostOverviewRepCompMap
	 */
	public UsgRevCostOverviewRepComp( Map<String, String> usgRevCostOverviewRepCompMap )
	{
		this.usgRevCostOverviewRepCompMap = usgRevCostOverviewRepCompMap;
	}

	/*
	 * This method is for initialize variable
	 */
	private void initializeVariable( Map<String, String> map ) throws Exception
	{
		repAndExtEmailFormat = ExcelHolder.getKey( map, "EmailFormat" );
		repAndExtEmailSubject = ExcelHolder.getKey( map, "EmailSubject" );
		mappingConfigNm = ExcelHolder.getKey( map, "MappingConfigName" );
		
	}

	/*
	 * This method is for configure report and extract component
	 */
	@Override
	public void configReportAndExtComponent() throws Exception
	{
		initializeVariable( usgRevCostOverviewRepCompMap );
		reportExtDefnUtil.emailPropertyConfig(repAndExtEmailFormat, repAndExtEmailSubject);
		PropertyGridHelper.selectInComboBox( "Mapping Configuration Name", mappingConfigNm );
	}

	/*
	 * This method is for modify report and extract component
	 */
	@Override
	public void modifyReportAndExtComponent() throws Exception
	{
		initializeVariable( usgRevCostOverviewRepCompMap );
		PropertyGridHelper.selectInComboBox( "Mapping Configuration Name", mappingConfigNm );

	}

}
