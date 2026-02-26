package com.subex.rocps.automation.helpers.application.reportingAndExtraction.reportandExtDefn.Comp;

import java.util.Map;


import com.subex.rocps.automation.helpers.application.reportingAndExtraction.reportandExtDefn.ReportAndExtDefnUtil;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;
import com.subex.rocps.automation.utils.ExcelHolder;

public class RepAndExtComponent extends PSAcceptanceTest
{
	protected Map<String, String> repAndExtComponentMap = null;
	protected String repAndExtEmailFormat;
	protected String repAndExtEmailSubject;
	
	ReportAndExtDefnUtil reportExtDefnUtil = new ReportAndExtDefnUtil();

	/**Consrutor
	 * @param repAndExtComponentMap
	 */
	public RepAndExtComponent( Map<String, String> repAndExtComponentMap )
	{
		this.repAndExtComponentMap = repAndExtComponentMap;
	}

	/*
	 * This method is for initialize variable
	 */
	private void initializeVariable( Map<String, String> map ) throws Exception
	{
		repAndExtEmailFormat = ExcelHolder.getKey( map, "EmailFormat" );
		repAndExtEmailSubject = ExcelHolder.getKey( map, "EmailSubject" );	
	}
	/*
	 * This method is for configure report and extract component
	 */
	public void configReportAndExtComponent() throws Exception
	{
		initializeVariable(repAndExtComponentMap);
		reportExtDefnUtil.emailPropertyConfig(repAndExtEmailFormat, repAndExtEmailSubject);
        
	}

}
