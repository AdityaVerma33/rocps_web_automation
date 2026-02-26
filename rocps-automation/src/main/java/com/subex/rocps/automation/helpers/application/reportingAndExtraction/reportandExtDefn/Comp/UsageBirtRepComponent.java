package com.subex.rocps.automation.helpers.application.reportingAndExtraction.reportandExtDefn.Comp;

import java.util.Map;

import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;

public class UsageBirtRepComponent extends PSAcceptanceTest implements ReportAndExtComponentStrategy
{
	protected Map<String, String> usgBirtRepCompMap = null;
	
	/**Constructor
	 * @param usgBirtRepCompMap
	 */
	public UsageBirtRepComponent( Map<String, String> usgBirtRepCompMap )
	{
		
		this.usgBirtRepCompMap = usgBirtRepCompMap;
	}

	@Override
	public void configReportAndExtComponent() throws Exception
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void modifyReportAndExtComponent() throws Exception
	{
		// TODO Auto-generated method stub
		
	}

}
