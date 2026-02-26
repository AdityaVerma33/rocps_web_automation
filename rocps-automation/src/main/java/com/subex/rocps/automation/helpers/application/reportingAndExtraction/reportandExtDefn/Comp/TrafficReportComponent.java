package com.subex.rocps.automation.helpers.application.reportingAndExtraction.reportandExtDefn.Comp;

import java.util.Map;

import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;

public class TrafficReportComponent extends PSAcceptanceTest implements ReportAndExtComponentStrategy
{
	protected Map<String, String> trafficRepCompMap = null;

	/**Constructor
	 * @param trafficRepCompMap
	 */
	public TrafficReportComponent( Map<String, String> trafficRepCompMap )
	{

		this.trafficRepCompMap = trafficRepCompMap;
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
