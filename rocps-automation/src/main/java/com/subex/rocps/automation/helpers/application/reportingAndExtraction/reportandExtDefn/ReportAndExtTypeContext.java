package com.subex.rocps.automation.helpers.application.reportingAndExtraction.reportandExtDefn;

public class ReportAndExtTypeContext
{
	/*This is for context of strategy pattern class*/
	private ReportAndExtTypeStrategy repAndExtTypeStrategy;

	/**Constructor
	 * @param repAndExtTypeStrategy
	 */
	public ReportAndExtTypeContext( ReportAndExtTypeStrategy repAndExtTypeStrategy )
	{

		this.repAndExtTypeStrategy = repAndExtTypeStrategy;
	}

	/*This method is for configure report and extract type paramters*/
	public void configReportAndExTypeParmeters() throws Exception
	{
		repAndExtTypeStrategy.configReportAndExtTypeParameters();
	}

	/*This method is for modify report and extract type paramters*/
	public void modifyReportAndExTypeParmeters() throws Exception
	{
		repAndExtTypeStrategy.modifyReportAndExtTypeParameters();
	}

}
