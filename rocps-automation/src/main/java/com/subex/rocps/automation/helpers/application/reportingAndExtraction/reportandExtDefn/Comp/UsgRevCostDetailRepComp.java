package com.subex.rocps.automation.helpers.application.reportingAndExtraction.reportandExtDefn.Comp;

import java.util.Map;

import com.subex.rocps.automation.helpers.application.reportingAndExtraction.reportandExtDefn.ReportAndExtDefnUtil;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;
import com.subex.rocps.automation.utils.ExcelHolder;

import com.subex.automation.helpers.component.PropertyGridHelper;

public class UsgRevCostDetailRepComp extends PSAcceptanceTest implements ReportAndExtComponentStrategy
{
	protected Map<String, String> usgRevCostDetailRepCompMap = null;
	protected String mappingConfigNm;
	protected String repAndExtEmailFormat;
	protected String repAndExtEmailSubject;
	
	ReportAndExtDefnUtil reportExtDefnUtil = new ReportAndExtDefnUtil();

	/**Consrutor
	 * @param usgBirtRepCompMap
	 */
	public UsgRevCostDetailRepComp( Map<String, String> usgRevCostDetailRepCompMap )
	{
		this.usgRevCostDetailRepCompMap = usgRevCostDetailRepCompMap;
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
		initializeVariable(usgRevCostDetailRepCompMap);
		reportExtDefnUtil.emailPropertyConfig(repAndExtEmailFormat, repAndExtEmailSubject);
		PropertyGridHelper.selectInComboBox( "Mapping Configuration Name", mappingConfigNm );
        
	}
	/*
	 * This method is for modify report and extract component
	 */
	@Override
	public void modifyReportAndExtComponent() throws Exception
	{
		initializeVariable(usgRevCostDetailRepCompMap);
		PropertyGridHelper.selectInComboBox( "Mapping Configuration Name", mappingConfigNm );

	}

}
