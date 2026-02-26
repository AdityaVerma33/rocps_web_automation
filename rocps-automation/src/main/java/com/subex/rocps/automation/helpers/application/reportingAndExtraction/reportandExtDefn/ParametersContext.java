package com.subex.rocps.automation.helpers.application.reportingAndExtraction.reportandExtDefn;

public class ParametersContext
{
	ParametersStrategy parametersStrategy;

	/**Constructor
	 * @param parametersStrategy
	 */
	public ParametersContext( ParametersStrategy parametersStrategy )
	{

		this.parametersStrategy = parametersStrategy;
	}
	
	/*
	 * This method is for configure Report Extracte paramters panel*/
	public void configReportExtParameters() throws Exception
	{
		parametersStrategy.configReportExtParameters();

	}

}
