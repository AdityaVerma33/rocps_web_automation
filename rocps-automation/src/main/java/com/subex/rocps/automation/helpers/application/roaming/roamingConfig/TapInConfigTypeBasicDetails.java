package com.subex.rocps.automation.helpers.application.roaming.roamingConfig;

import java.util.Map;

import com.subex.rocps.automation.utils.ExcelHolder;

import com.subex.automation.helpers.component.TextBoxHelper;

public class TapInConfigTypeBasicDetails implements ConfigTypeBasicDetailsStrategy
{
	protected Map<String, String> tapInConfTypeBasicDetailsMap = null;
	protected String cdStartSeq;
	protected String tdStartSeq;
	protected String endNumber;
	protected String restartNumber;
	protected String missingSeq;
	protected String histLoadDay;
	RoamingConfigBasicDetails roamingConfigBasicDetails = new RoamingConfigBasicDetails();

	/**Constructor
	 * @param tapInConfTypeBasicDetailsMap
	 */
	public TapInConfigTypeBasicDetails( Map<String, String> tapInConfTypeBasicDetailsMap )
	{
		this.tapInConfTypeBasicDetailsMap = tapInConfTypeBasicDetailsMap;
	}

	/*This method is for initialize  = variable*/
	private void initializeVariable( Map<String, String> map ) throws Exception
	{
		cdStartSeq = ExcelHolder.getKey( map, "CDStartSequence" );
		tdStartSeq = ExcelHolder.getKey( map, "TDStartSequence" );
		endNumber = ExcelHolder.getKey( map, "EndNumber" );
		restartNumber = ExcelHolder.getKey( map, "RestartNumber" );
		missingSeq = ExcelHolder.getKey( map, "MissingSeqWaitPeriod" );
		histLoadDay = ExcelHolder.getKey( map, "HistoricalLoadDays" );
	}
	
	@Override
	public void configureBasicDetails() throws Exception
	{
		initializeVariable( tapInConfTypeBasicDetailsMap );
		configFileSeqDetails(  );
		configTAPInAdvanceDetails();
	}

	private void configFileSeqDetails() throws Exception
	{
		roamingConfigBasicDetails.configFileSeqDetails( cdStartSeq, tdStartSeq, endNumber, restartNumber );
	}

	private void configTAPInAdvanceDetails() throws Exception
	{
		TextBoxHelper.type( "PS_Detail_roamConfig_BD_TAPIN_missSeq_txtId", missingSeq );
		TextBoxHelper.type( "PS_Detail_roamConfig_BD_TAPIN_histLoad_txtId", histLoadDay );
	}

}
