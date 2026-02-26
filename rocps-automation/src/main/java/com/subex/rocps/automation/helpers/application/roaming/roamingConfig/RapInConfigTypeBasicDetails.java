package com.subex.rocps.automation.helpers.application.roaming.roamingConfig;

import java.util.Map;

import com.subex.rocps.automation.utils.ExcelHolder;

public class RapInConfigTypeBasicDetails implements ConfigTypeBasicDetailsStrategy
{

	protected Map<String, String> rapInConfTypeBasicDetailsMap = null;
	protected String cdStartSeq;
	protected String tdStartSeq;
	protected String endNumber;
	protected String restartNumber;
	RoamingConfigBasicDetails roamingConfigBasicDetails = new RoamingConfigBasicDetails();

	/**Constructor
	 * @param rapInConfTypeBasicDetailsMap
	 */
	public RapInConfigTypeBasicDetails( Map<String, String> rapInConfTypeBasicDetailsMap )
	{
		this.rapInConfTypeBasicDetailsMap = rapInConfTypeBasicDetailsMap;
	}
	/*This method is for initialize  = variable*/
	private void initializeVariable( Map<String, String> map ) throws Exception
	{
		cdStartSeq = ExcelHolder.getKey( map, "CDStartSequence" );
		tdStartSeq = ExcelHolder.getKey( map, "TDStartSequence" );
		endNumber = ExcelHolder.getKey( map, "EndNumber" );
		restartNumber = ExcelHolder.getKey( map, "RestartNumber" );
	}

	@Override
	public void configureBasicDetails() throws Exception
	{
		initializeVariable( rapInConfTypeBasicDetailsMap );
		configFileSeqDetails();

	}

	private void configFileSeqDetails() throws Exception
	{
		roamingConfigBasicDetails.configFileSeqDetails( cdStartSeq, tdStartSeq, endNumber, restartNumber );

	}

}
