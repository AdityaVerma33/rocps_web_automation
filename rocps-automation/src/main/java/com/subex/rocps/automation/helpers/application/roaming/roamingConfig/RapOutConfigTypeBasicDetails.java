package com.subex.rocps.automation.helpers.application.roaming.roamingConfig;

import java.util.Map;

import com.subex.rocps.automation.utils.ExcelHolder;

import com.subex.automation.helpers.component.ComboBoxHelper;
import com.subex.automation.helpers.component.TextBoxHelper;

public class RapOutConfigTypeBasicDetails implements ConfigTypeBasicDetailsStrategy
{

	protected Map<String, String> rapOutConfTypeBasicDetailsMap = null;
	protected String cdStartSeq;
	protected String tdStartSeq;
	protected String endNumber;
	protected String restartNumber;
	protected String ftpLocation;
	protected String fileSize;
	RoamingConfigBasicDetails roamingConfigBasicDetails = new RoamingConfigBasicDetails();

	/**Constructor
	 * @param rapOutConfTypeBasicDetailsMap
	 */
	public RapOutConfigTypeBasicDetails( Map<String, String> rapOutConfTypeBasicDetailsMap )
	{
		this.rapOutConfTypeBasicDetailsMap = rapOutConfTypeBasicDetailsMap;
	}
	/*This method is for initialize  = variable*/
	private void initializeVariable( Map<String, String> map ) throws Exception
	{
		cdStartSeq = ExcelHolder.getKey( map, "CDStartSequence" );
		tdStartSeq = ExcelHolder.getKey( map, "TDStartSequence" );
		endNumber = ExcelHolder.getKey( map, "EndNumber" );
		restartNumber = ExcelHolder.getKey( map, "RestartNumber" );
		ftpLocation = ExcelHolder.getKey( map, "FTPLocation" );
		fileSize = ExcelHolder.getKey( map, "FileSize" );
	}
	@Override
	public void configureBasicDetails() throws Exception
	{
		initializeVariable( rapOutConfTypeBasicDetailsMap );
		configFileSeqDetails();
		configRAPOutAdvanceDetails();

	}

	private void configFileSeqDetails() throws Exception
	{
		roamingConfigBasicDetails.configFileSeqDetails( cdStartSeq, tdStartSeq, endNumber, restartNumber );
	}

	private void configRAPOutAdvanceDetails() throws Exception
	{
		TextBoxHelper.type( "PS_Detail_roamConfig_BD_RAPOUT_fileSize_txtId", fileSize );
		ComboBoxHelper.select( "PS_Detail_roamConfig_BD_RAPOUT_ftpLoc_comboId", ftpLocation );
	}
}
