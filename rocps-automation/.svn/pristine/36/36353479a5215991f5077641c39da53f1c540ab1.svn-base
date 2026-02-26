package com.subex.rocps.automation.helpers.application.matchandrate.Emr;

import java.util.Map;

import com.subex.rocps.automation.helpers.application.matchandrate.mnrInterfaces.EMRServicesStrategy;

public class EMRStrategyPattern
{

	EMRServicesStrategy emrServicesStrategyOb;
	public Map<String, String> emrStrategyPatternMap = null;

	/**Constructor
	 * @param emrServicesStrategyOb
	 * @param emrStrategyPatternMap
	 */
	public EMRStrategyPattern( EMRServicesStrategy emrServicesStrategyOb, Map<String, String> emrStrategyPatternMap )
	{
		this.emrServicesStrategyOb = emrServicesStrategyOb;
		this.emrStrategyPatternMap = emrStrategyPatternMap;
	}

	public void addServiceConfiguration() throws Exception
	{
		emrServicesStrategyOb.addServices( emrStrategyPatternMap );
	}

}
